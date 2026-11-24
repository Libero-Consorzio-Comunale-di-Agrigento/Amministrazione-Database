--liquibase formatted sql

--changeset adadamo:202604011058_456 runOnChange:true stripComments:false

CREATE OR REPLACE PACKAGE BODY si4cimv2 is  

    B_MESSAGE_TYPE      VARCHAR2(4000) := 'csagentmail';
    B_CS_TAG            VARCHAR2(4000);
    B_OGGETTO_MESSAGGIO VARCHAR2(4000);
    B_TESTO_MESSAGGIO   VARCHAR2(4000);
    B_name_recipient    VARCHAR2(200);
    B_adress_recipient  VARCHAR2(200);
    B_nome_mittente     VARCHAR2(200);
    B_mail_mittente     VARCHAR2(200);
    b_blob_out          blob;
    B_FILENAME          VARCHAR2(200);
    b_name_bcc          varchar2(200);
    b_mail_bcc          varchar2(200);
    b_name_cc          varchar2(200);
    b_mail_cc          varchar2(200);

    function version return varchar2 is
    begin
        return '1.0';
    end;     
    
    function get_integrazione_si4cs return boolean is
        dummy   varchar2(1);
    begin
        begin
            SELECT 'x' 
              into dummy    
              FROM all_synonyms s
              JOIN all_tab_privs p ON s.table_name = p.table_name AND s.table_owner = p.grantor
             WHERE s.synonym_name = 'SI4CS_SI4CSSENDPLSQLPROC'
               AND p.privilege = 'EXECUTE'             
             ;
        exception when no_data_found then
            dummy := 'n';
        end;
        return dummy = 'x';
    end; 
    
    function initializemessage (P_tag in varchar2) return number is
    begin
      if get_integrazione_si4cs then
        B_CS_TAG := p_tag;
        return 0;         
      else
        return ad4_si4cim.initializemessage (p_tag);
      end if;  
    end;        
                   
    PROCEDURE setSender
    ( senderIP IN VARCHAR2
    , senderName IN VARCHAR2
    , ID IN VARCHAR2
    , NAME IN VARCHAR2
    , company IN VARCHAR2
    , address IN VARCHAR2
    , code IN VARCHAR2
    , city IN VARCHAR2
    , PROVINCE IN VARCHAR2
    , state IN VARCHAR2
    , email IN VARCHAR2
    , phoneNumber IN VARCHAR2
    , faxNumber IN VARCHAR2
    , user      in varchar2 default null
    , password in varchar2 default null)
    AS
    BEGIN
      if get_integrazione_si4cs then
        B_NOME_MITTENTE := NAME; 
        B_mail_mittente := EMAIL;              
      else
        ad4_si4cim.setSender( senderIP
                            , senderName
                            , ID
                            , NAME
                            , company
                            , address
                            , code
                            , city
                            , PROVINCE
                            , state
                            , email
                            , phoneNumber
                            , faxNumber
                            );
      end if;     
    end;
    
     PROCEDURE addBcc
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2) is
     begin
        if get_integrazione_si4cs then
            B_name_bcc := NAME;    /* al momento predisposto ma non gestito */
            B_mail_bcc := EMAIL;   /* al momento predisposto ma non gestito */           
        else
            ad4_si4cim.addBcc ( ID , name , company
            , address , code , city 
            , province , state , email
            , phoneNumber , faxNumber );
        end if;     
     end;  
 PROCEDURE addCc
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2) is
  begin
        if get_integrazione_si4cs then
            B_name_cc := NAME;      /* al momento predisposto ma non gestito */
            B_mail_cc := EMAIL;     /* al momento predisposto ma non gestito */         
        else
            ad4_si4cim.addCc ( ID , name , company
            , address , code , city 
            , province , state , email
            , phoneNumber , faxNumber );
        end if;     

  end;           
    
    
    PROCEDURE addrecipient ( ID IN VARCHAR2
                           , NAME IN VARCHAR2
                           , company IN VARCHAR2
                           , address IN VARCHAR2
                           , code IN VARCHAR2
                           , city IN VARCHAR2
                           , PROVINCE IN VARCHAR2
                           , state IN VARCHAR2
                           , email IN VARCHAR2
                           , phoneNumber IN VARCHAR2
                           , faxNumber IN VARCHAR2
                           ) is
    begin
      if get_integrazione_si4cs then
        B_name_recipient := NAME; 
        B_adress_recipient := EMAIL;            
      else
          ad4_si4cim.addrecipient (id 
                                  , name 
                                  , company 
                                  , address
                                  , code 
                                  , city 
                                  , province
                                  , state
                                  , email 
                                  , phonenumber 
                                  , faxnumber 
                                  );
      end if;        
    end;                           
    
    procedure  setsubject (newSubject IN VARCHAR2) is
    begin
      if get_integrazione_si4cs then
        B_OGGETTO_MESSAGGIO := newSubject; -- to do              
      else
        ad4_si4cim.setsubject(newSubject);
      end if;     
    end;
    
    PROCEDURE setText  (newText IN VARCHAR2) is
    begin
      if get_integrazione_si4cs then
        B_TESTO_MESSAGGIO := newText; -- to do              
      else
        ad4_si4cim.setText(newText);
      end if;     
    end;
    
    PROCEDURE addAttachment
    ( dbAlias IN VARCHAR2
    , dbDriver IN VARCHAR2
    , dbConnect IN VARCHAR2
    , dbUser IN VARCHAR2
    , dbEncPwd IN VARCHAR2
    , newTableName IN VARCHAR2
    , newBlobField IN VARCHAR2
    , newWhereCondition IN VARCHAR2
    , newDataType IN VARCHAR2
    , newFileName IN VARCHAR2) is
        v_sql       VARCHAR2(1000);
        b_clob_out          clob;
        v_dest_offset INTEGER := 1;
        v_src_offset  INTEGER := 1;
        v_lang_ctx    INTEGER := DBMS_LOB.DEFAULT_LANG_CTX;  
        v_warning     INTEGER;              
    begin
      if get_integrazione_si4cs then
        DBMS_LOB.CREATETEMPORARY(b_blob_out, FALSE);  
        if newDataType = 'BLOB' then
 
            v_sql := 'SELECT '||newBlobField||
                  ' FROM '|| newTableName||' where '||newWhereCondition;          
            EXECUTE IMMEDIATE v_sql 
                INTO B_blob_out;
        elsif newDataType = 'CLOB' then
            v_sql := 'SELECT '||newBlobField||
                  ' FROM '|| newTableName||' where '||newWhereCondition;          
            EXECUTE IMMEDIATE v_sql 
                INTO b_clob_out;                
            DBMS_LOB.CONVERTTOBLOB( dest_lob    => b_blob_out,          -- Destinazione
                                    src_clob    => b_clob_out,          -- Sorgente
                                    amount      => DBMS_LOB.LOBMAXSIZE,
                                    dest_offset => v_dest_offset,   -- Deve essere inizializzato a 1
                                    src_offset  => v_src_offset,    -- Deve essere inizializzato a 1
                                    blob_csid   => DBMS_LOB.DEFAULT_CSID,
                                    lang_context=> v_lang_ctx,      -- Deve essere inizializzato a 0
                                    warning     => v_warning
                                );
        end if;             
        B_FILENAME := newFileName;                                      
      else
        ad4_si4cim.addAttachment( dbAlias 
                                    , dbDriver 
                                    , dbConnect 
                                    , dbUser 
                                    , dbEncPwd 
                                    , newTableName
                                    , newBlobField
                                    , newWhereCondition 
                                    , newDataType 
                                    , newFileName 
                                    );
      end if;      
    end;
    
     FUNCTION send RETURN NUMBER AS
        v_sql VARCHAR2(2000);
     begin
      if get_integrazione_si4cs then
         v_sql := 'BEGIN 
                SI4CS_SI4CSSENDPLSQLPROC(:1, :2, :3, :4, :5, :6, NULL, NULL, NULL, SYSDATE, :7, :8,''READYTOSEND'',NULL,NULL,:9,:10); 
              END;';
        -- Esecuzione dinamica mappando le variabili d'ingresso
        EXECUTE IMMEDIATE v_sql 
        USING 
            b_message_type,      -- :1
            b_cs_tag,            -- :2
            B_OGGETTO_MESSAGGIO, -- :3
            b_testo_messaggio,   -- :4
            b_name_recipient,    -- :5
            b_adress_recipient,  -- :6
            b_nome_mittente,     -- :7
            b_mail_mittente,     -- :8
            B_BLOB_OUT,          -- :9
            B_FILENAME;        --:10
        return 0;                                          
      else
        return ad4_si4cim.send;
      end if;     
    end;
                         
end;
/