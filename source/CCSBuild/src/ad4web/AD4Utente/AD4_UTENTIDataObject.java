//AD4_UTENTI DataSource @6-ACE1F22F
package ad4web.AD4Utente;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_UTENTI DataSource

//class DataObject Header @6-587BC4AB
public class AD4_UTENTIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-5D9610AF
    

    TextField ctrlID_UTENTE = new TextField(null, null);
    
    TextField postUTENTE_VIS = new TextField(null, null);
    
    TextField postNOMINATIVO = new TextField(null, null);
    
    TextField postPASSWORD = new TextField(null, null);
    
    TextField ctrlPWD_MODIFIED = new TextField(null, null);
    
    TextField postRINNOVO_PASSWORD = new TextField(null, null);
    
    TextField postSTATO = new TextField(null, null);
    
    TextField postLINGUA = new TextField(null, null);
    
    TextField postGRUPPO_LAVORO = new TextField(null, null);
    
    LongField postIMPORTANZA = new LongField(null, null);
    
    TextField postNOTE = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    LongField postSOGGETTO = new LongField(null, null);
    
    TextField ctrlAMMINISTRATORE = new TextField(null, null);
    
    TextField postINFO_IDENTIFICAZIONE = new TextField(null, null);
    
    TextField sesAD4UTENTE = new TextField(null, null);
    
    TextField urlUTENTE = new TextField(null, null);
    
    LongField urlSOGGETTO = new LongField(null, null);
    
    TextField urlSE_NUOVO = new TextField(null, null);
    

    private AD4_UTENTIRow row = new AD4_UTENTIRow();

//End attributes of DataObject

//properties of DataObject @6-BAC85212

    public void  setCtrlID_UTENTE( String param ) {
        this.ctrlID_UTENTE.setValue( param );
    }

    public void  setCtrlID_UTENTE( Object param ) {
        this.ctrlID_UTENTE.setValue( param );
    }

    public void  setCtrlID_UTENTE( Object param, Format ignore ) {
        this.ctrlID_UTENTE.setValue( param );
    }

    public void  setPostUTENTE_VIS( String param ) {
        this.postUTENTE_VIS.setValue( param );
    }

    public void  setPostUTENTE_VIS( Object param ) {
        this.postUTENTE_VIS.setValue( param );
    }

    public void  setPostUTENTE_VIS( Object param, Format ignore ) {
        this.postUTENTE_VIS.setValue( param );
    }

    public void  setPostNOMINATIVO( String param ) {
        this.postNOMINATIVO.setValue( param );
    }

    public void  setPostNOMINATIVO( Object param ) {
        this.postNOMINATIVO.setValue( param );
    }

    public void  setPostNOMINATIVO( Object param, Format ignore ) {
        this.postNOMINATIVO.setValue( param );
    }

    public void  setPostPASSWORD( String param ) {
        this.postPASSWORD.setValue( param );
    }

    public void  setPostPASSWORD( Object param ) {
        this.postPASSWORD.setValue( param );
    }

    public void  setPostPASSWORD( Object param, Format ignore ) {
        this.postPASSWORD.setValue( param );
    }

    public void  setCtrlPWD_MODIFIED( String param ) {
        this.ctrlPWD_MODIFIED.setValue( param );
    }

    public void  setCtrlPWD_MODIFIED( Object param ) {
        this.ctrlPWD_MODIFIED.setValue( param );
    }

    public void  setCtrlPWD_MODIFIED( Object param, Format ignore ) {
        this.ctrlPWD_MODIFIED.setValue( param );
    }

    public void  setPostRINNOVO_PASSWORD( String param ) {
        this.postRINNOVO_PASSWORD.setValue( param );
    }

    public void  setPostRINNOVO_PASSWORD( Object param ) {
        this.postRINNOVO_PASSWORD.setValue( param );
    }

    public void  setPostRINNOVO_PASSWORD( Object param, Format ignore ) {
        this.postRINNOVO_PASSWORD.setValue( param );
    }

    public void  setPostSTATO( String param ) {
        this.postSTATO.setValue( param );
    }

    public void  setPostSTATO( Object param ) {
        this.postSTATO.setValue( param );
    }

    public void  setPostSTATO( Object param, Format ignore ) {
        this.postSTATO.setValue( param );
    }

    public void  setPostLINGUA( String param ) {
        this.postLINGUA.setValue( param );
    }

    public void  setPostLINGUA( Object param ) {
        this.postLINGUA.setValue( param );
    }

    public void  setPostLINGUA( Object param, Format ignore ) {
        this.postLINGUA.setValue( param );
    }

    public void  setPostGRUPPO_LAVORO( String param ) {
        this.postGRUPPO_LAVORO.setValue( param );
    }

    public void  setPostGRUPPO_LAVORO( Object param ) {
        this.postGRUPPO_LAVORO.setValue( param );
    }

    public void  setPostGRUPPO_LAVORO( Object param, Format ignore ) {
        this.postGRUPPO_LAVORO.setValue( param );
    }

    public void  setPostIMPORTANZA( long param ) {
        this.postIMPORTANZA.setValue( param );
    }

    public void  setPostIMPORTANZA( long param, Format ignore ) throws java.text.ParseException {
        this.postIMPORTANZA.setValue( param );
    }

    public void  setPostIMPORTANZA( Object param, Format format ) throws java.text.ParseException {
        this.postIMPORTANZA.setValue( param, format );
    }

    public void  setPostIMPORTANZA( Long param ) {
        this.postIMPORTANZA.setValue( param );
    }

    public void  setPostNOTE( String param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param, Format ignore ) {
        this.postNOTE.setValue( param );
    }

    public void  setSesUtente( String param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param, Format ignore ) {
        this.sesUtente.setValue( param );
    }

    public void  setPostSOGGETTO( long param ) {
        this.postSOGGETTO.setValue( param );
    }

    public void  setPostSOGGETTO( long param, Format ignore ) throws java.text.ParseException {
        this.postSOGGETTO.setValue( param );
    }

    public void  setPostSOGGETTO( Object param, Format format ) throws java.text.ParseException {
        this.postSOGGETTO.setValue( param, format );
    }

    public void  setPostSOGGETTO( Long param ) {
        this.postSOGGETTO.setValue( param );
    }

    public void  setCtrlAMMINISTRATORE( String param ) {
        this.ctrlAMMINISTRATORE.setValue( param );
    }

    public void  setCtrlAMMINISTRATORE( Object param ) {
        this.ctrlAMMINISTRATORE.setValue( param );
    }

    public void  setCtrlAMMINISTRATORE( Object param, Format ignore ) {
        this.ctrlAMMINISTRATORE.setValue( param );
    }

    public void  setPostINFO_IDENTIFICAZIONE( String param ) {
        this.postINFO_IDENTIFICAZIONE.setValue( param );
    }

    public void  setPostINFO_IDENTIFICAZIONE( Object param ) {
        this.postINFO_IDENTIFICAZIONE.setValue( param );
    }

    public void  setPostINFO_IDENTIFICAZIONE( Object param, Format ignore ) {
        this.postINFO_IDENTIFICAZIONE.setValue( param );
    }

    public void  setSesAD4UTENTE( String param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param, Format ignore ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setUrlUTENTE( String param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param, Format ignore ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlSOGGETTO( long param ) {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setUrlSOGGETTO( long param, Format ignore ) throws java.text.ParseException {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setUrlSOGGETTO( Object param, Format format ) throws java.text.ParseException {
        this.urlSOGGETTO.setValue( param, format );
    }

    public void  setUrlSOGGETTO( Long param ) {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setUrlSE_NUOVO( String param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param, Format ignore ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public AD4_UTENTIRow getRow() {
        return row;
    }

    public void setRow( AD4_UTENTIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-31571C5F
    public AD4_UTENTIDataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new AD4_UTENTIDataObjectHandler() );
    }
//End constructor

//load @6-D2E876D9
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '<input class=\"AFCInputDisplay\" maxlength=\"10\" size=\"10\" value=\"'||UTENTI.UTENTE||'\" name=\"UTENTE_VIS\" readonly >' UTENTE_VIS "
                    + "     ,  "
                    + "utente "
                    + "     , nominativo  "
                    + "     , RPAD('XXXXXXXX',8,' ') PASSWORD "
                    + "     , DATA_PASSWORD "
                    + "     ,  "
                    + "NVL(RINNOVO_PASSWORD,'SI') RINNOVO_PASSWORD "
                    + "     , ULTIMO_TENTATIVO "
                    + "     ,  "
                    + "NUMERO_TENTATIVI "
                    + "     , TIPO_UTENTE "
                    + "     , NOTE "
                    + "     , ID_UTENTE "
                    + "     , STATO "
                    + "     ,  "
                    + "DECODE(DATA_INSERIMENTO,NULL,'','Inserito il '||TO_CHAR(DATA_INSERIMENTO,'dd/mm/yyyy')) DATA_INSERIMENTO "
                    + "     , DECODE(UTEN.UTENTE_AGGIORNAMENTO,NULL,'','Aggiornato da '||NVL(UTEN.NOMINATIVO_AGGIORNAMENTO,UTEN.UTENTE_AGGIORNAMENTO)||DECODE(DATA_AGGIORNAMENTO,NULL,'',' il '||TO_CHAR(DATA_AGGIORNAMENTO,'dd/mm/yyyy'))) UTENTE_DATA_AGGIORNAMENTO "
                    + "     , LINGUA "
                    + "     , GRUPPO_LAVORO "
                    + "     , IMPORTANZA "
                    + "     , DECODE(NVL({P_SOGGETTO},0),0,Utente.GET_SOGGETTO(Utente,'Y'),{P_SOGGETTO}) Soggetto     "
                    + "     , Ad4web.GET_DATI_SOGGETTO(DECODE(NVL({P_SOGGETTO},0),-1,0,0,Utente.GET_SOGGETTO(Utente),{P_SOGGETTO})) DATI_SOGGETTO "
                    + "     , DECODE(DECODE(NVL({P_SOGGETTO},0),-1,0,0,NVL(Utente.GET_SOGGETTO(Utente),0),{P_SOGGETTO}),0,'','Soggetto') titolo_sogg "
                    + "     , DECODE('{SE_NUOVO}','Y','','<img title=\"Modifica Anagrafica\" height=\"18\" src=\"../common/images/AD4/sogg_cambia.gif\" width=\"18\" border=\"0\">'||'Modifica&nbsp;Anagrafica') mod_registrazione_anagrafica "
                    + "     , DECODE(DECODE(NVL({P_SOGGETTO},0),-1,0,0,NVL(Utente.GET_SOGGETTO(Utente),0),{P_SOGGETTO}),0,'','<img title=\"Svuota Anagrafica\" height=\"18\" src=\"../common/images/AD4/sogg_svuota.gif\" width=\"18\" border=\"0\">'||'Svuota&nbsp;Anagrafica') svuota_reg_anagrafica "
                    + "     , DECODE('{SE_NUOVO}','Y','','<img title=\"Genera Password Casuale\" height=\"18\" src=\"../common/images/AD4/password2.gif\" width=\"18\" border=\"0\">'||'Genera&nbsp;Password') genera_password "
                    + "     , DECODE('{SE_NUOVO}','Y','','<a class=\"AFCToolBox\" title=\"Elimina tutte le caratteristiche di accesso dell''utente\" href=\"javascript:confermaEliminazioneCaac()\"><img title=\"Elimina tutte le caratteristiche di accesso dell''utente\" height=\"18\" src=\"../common/images/AD4/noCaac.gif\" width=\"18\" border=\"0\">Elimina&nbsp;caratteristiche</a>') ELIMINA_CAAC "
                    + "     , DECODE('{SE_NUOVO}','Y','',decode(nvl(caratteristica_accesso.is_ldapuser(NVL('{P_UTENTE}','{AD4UTENTE}')), 0), 0, '','<a class=\"AFCToolBox\" title=\"Forza autenticazione utente via Ad4\" href=\"javascript:confermaAutenticazioneAd4()\"><img title=\"Forza autenticazione utente via Ad4\" height=\"18\" src=\"../common/images/AD4/ad4.gif\" width=\"18\" border=\"0\">Autenticazione&nbsp;via&nbsp;Ad4</a>')) AUTENTICAZIONE_AD4  "
                    + "     , Utente.is_so4_user(NVL('{P_UTENTE}','{AD4UTENTE}')) is_so4_user "
                    + "     , decode(nvl(caratteristica_accesso.is_ldapuser(NVL('{P_UTENTE}','{AD4UTENTE}')), 0),0,'','<img title=\"Autenticazione utente tramite Ldap\" height=\"18\" src=\"../common/images/AD4/tree.gif\" width=\"18\" border=\"0\">') is_ldapuser "
                    + "     , amministratore "
                    + "     , info_identificazione "
                    + "     , decode(accesso.is_ldapuser(utenti.nominativo),1,'','<a href=\"javascript:showLOV(''AD4Password.do'',''scrollbars=no,width=460,height=105'',''AD4_UTENTI'',''PASSWORD'')\"><img id=\"ImgPwd\" border=\"0\" name=\"ImgPwd\" align=\"top\" src=\"../common/images/AD4/Password.gif\" width=\"20\" height=\"20\"></a>') modifica_password "
                    + "  FROM UTENTI,  "
                    + "       ( SELECT Utente UTENTE_AGGIORNAMENTO, NOMINATIVO NOMINATIVO_AGGIORNAMENTO "
                    + "          FROM UTENTI) UTEN "
                    + " WHERE Utente = NVL('{P_UTENTE}','{AD4UTENTE}') "
                    + "   AND UTENTI.UTENTE_AGGIORNAMENTO = UTEN.UTENTE_AGGIORNAMENTO(+) "
                    + "   AND TIPO_UTENTE = 'U' "
                    + "   AND nvl('{SE_NUOVO}', 'N') <> 'Y' "
                    + "UNION "
                    + "SELECT '<input class=\"AFCInput\" style=\"TEXT-TRANSFORM: uppercase\" maxlength=\"8\" size=\"8\" value=\"'||'{UTEN_FORM}'||'\" name=\"UTENTE_VIS\">' Utente_VIS "
                    + "     , '' utente "
                    + "     , '' nominativo  "
                    + "     , '        ' PASSWORD "
                    + "     , TO_DATE(NULL) DATA_PASSWORD "
                    + "     , '' RINNOVO_PASSWORD "
                    + "     , TO_DATE(NULL) ULTIMO_TENTATIVO "
                    + "     , TO_NUMBER(NULL) NUMERO_TENTATIVI "
                    + "     , 'U' TIPO_UTENTE "
                    + "     , '' NOTE "
                    + "     , TO_NUMBER(NULL) ID_UTENTE "
                    + "     , 'U' STATO "
                    + "     , '' DATA_INSERIMENTO "
                    + "     , '' UTENTE_DATA_AGGIORNAMENTO "
                    + "     , 'I' LINGUA "
                    + "     , 'def' GRUPPO_LAVORO "
                    + "     , TO_NUMBER(NULL) IMPORTANZA "
                    + "     , 0 Soggetto     "
                    + "     , '' DATI_SOGGETTO "
                    + "     , '' titolo_sogg "
                    + "     , '' mod_registrazione_anagrafica "
                    + "     , '' svuota_reg_anagrafica "
                    + "     , '' genera_password "
                    + "     , '' ELIMINA_CAAC "
                    + "     , '' AUTENTICAZIONE_AD4  "
                    + "     , 0 is_so4_user "
                    + "     , '' is_ldapuser "
                    + "     , 'N' amministratore "
                    + "     , info_identificazione "
                    + "     , '<a href=\"javascript:showLOV(''AD4Password.do'',''scrollbars=no,width=460,height=105'',''AD4_UTENTI'',''PASSWORD'')\"><img id=\"ImgPwd\" border=\"0\" name=\"ImgPwd\" align=\"top\" src=\"../common/images/AD4/Password.gif\" width=\"20\" height=\"20\"></a>' modifica_password "
                    + "   FROM UTENTI "
                    + " WHERE TIPO_UTENTE = 'U' "
                    + "   AND '{SE_NUOVO}' = 'Y'" );
        if ( StringUtils.isEmpty( (String) urlUTENTE.getObjectValue() ) ) urlUTENTE.setValue( "" );
        command.addParameter( "P_UTENTE", urlUTENTE, null );
        if ( urlSOGGETTO.getObjectValue() == null ) urlSOGGETTO.setValue( 0 );
        command.addParameter( "P_SOGGETTO", urlSOGGETTO, null );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
        command.addParameter( "AD4UTENTE", sesAD4UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlSE_NUOVO.getObjectValue() ) ) urlSE_NUOVO.setValue( "" );
        command.addParameter( "SE_NUOVO", urlSE_NUOVO, null );
        if ( StringUtils.isEmpty( (String) postUTENTE_VIS.getObjectValue() ) ) postUTENTE_VIS.setValue( "" );
        command.addParameter( "UTEN_FORM", postUTENTE_VIS, null );
        if ( ! command.isSetAllParams() ) {
            empty = true;
            ds.closeConnection();
            return true;
        }
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        }

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        DbRow record = null;
        if ( ! ds.hasErrors() ) {
            record = command.getOneRow();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @6-98931BE2
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setGENERA_PASSWORD(Utils.convertToString(ds.parse(record.get("GENERA_PASSWORD"), row.getGENERA_PASSWORDField())));
            row.setSVUOTA_REG_ANAGRAFICA(Utils.convertToString(ds.parse(record.get("SVUOTA_REG_ANAGRAFICA"), row.getSVUOTA_REG_ANAGRAFICAField())));
            row.setMOD_REGISTRAZIONE_ANAGRAFICA(Utils.convertToString(ds.parse(record.get("MOD_REGISTRAZIONE_ANAGRAFICA"), row.getMOD_REGISTRAZIONE_ANAGRAFICAField())));
            row.setAUTENTICAZIONE_AD4(Utils.convertToString(ds.parse(record.get("AUTENTICAZIONE_AD4"), row.getAUTENTICAZIONE_AD4Field())));
            row.setELIMINA_CAAC(Utils.convertToString(ds.parse(record.get("ELIMINA_CAAC"), row.getELIMINA_CAACField())));
            row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
            row.setIS_SO4_USER(Utils.convertToLong(ds.parse(record.get("IS_SO4_USER"), row.getIS_SO4_USERField())));
            row.setSOGGETTO(Utils.convertToLong(ds.parse(record.get("SOGGETTO"), row.getSOGGETTOField())));
            row.setID_UTENTE(Utils.convertToString(ds.parse(record.get("ID_UTENTE"), row.getID_UTENTEField())));
            row.setIS_LDAPUSER(Utils.convertToString(ds.parse(record.get("IS_LDAPUSER"), row.getIS_LDAPUSERField())));
            row.setUTENTE_VIS(Utils.convertToString(ds.parse(record.get("UTENTE_VIS"), row.getUTENTE_VISField())));
            row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
            row.setPASSWORD(Utils.convertToString(ds.parse(record.get("PASSWORD"), row.getPASSWORDField())));
            row.setMODIFICA_PASSWORD(Utils.convertToString(ds.parse(record.get("MODIFICA_PASSWORD"), row.getMODIFICA_PASSWORDField())));
            try {
                row.setDATA_PASSWORD(Utils.convertToDate(ds.parse(record.get("DATA_PASSWORD"), row.getDATA_PASSWORDField())));
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid data" );
            }
            row.setRINNOVO_PASSWORD(Utils.convertToString(ds.parse(record.get("RINNOVO_PASSWORD"), row.getRINNOVO_PASSWORDField())));
            row.setSTATO(Utils.convertToString(ds.parse(record.get("STATO"), row.getSTATOField())));
            row.setLINGUA(Utils.convertToString(ds.parse(record.get("LINGUA"), row.getLINGUAField())));
            row.setGRUPPO_LAVORO(Utils.convertToString(ds.parse(record.get("GRUPPO_LAVORO"), row.getGRUPPO_LAVOROField())));
            row.setIMPORTANZA(Utils.convertToLong(ds.parse(record.get("IMPORTANZA"), row.getIMPORTANZAField())));
            row.setAMMINISTRATORE(Utils.convertToString(ds.parse(record.get("AMMINISTRATORE"), row.getAMMINISTRATOREField())));
            row.setINFO_IDENTIFICAZIONE(Utils.convertToString(ds.parse(record.get("INFO_IDENTIFICAZIONE"), row.getINFO_IDENTIFICAZIONEField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
            row.setTITOLO_SOGG(Utils.convertToString(ds.parse(record.get("TITOLO_SOGG"), row.getTITOLO_SOGGField())));
            row.setDATI_SOGGETTO(Utils.convertToString(ds.parse(record.get("DATI_SOGGETTO"), row.getDATI_SOGGETTOField())));
            try {
                row.setULTIMO_TENTATIVO(Utils.convertToDate(ds.parse(record.get("ULTIMO_TENTATIVO"), row.getULTIMO_TENTATIVOField())));
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid data" );
            }
            row.setNUMERO_TENTATIVI(Utils.convertToLong(ds.parse(record.get("NUMERO_TENTATIVI"), row.getNUMERO_TENTATIVIField())));
            row.setDATA_INSERIMENTO(Utils.convertToString(ds.parse(record.get("DATA_INSERIMENTO"), row.getDATA_INSERIMENTOField())));
            row.setUTENTE_DATA_AGGIORNAMENTO(Utils.convertToString(ds.parse(record.get("UTENTE_DATA_AGGIORNAMENTO"), row.getUTENTE_DATA_AGGIORNAMENTOField())));
            row.setS_UTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getS_UTENTEField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @6-F98C1DAF
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{? = call AD4WEB.UTENTI_PM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getID_UTENTE()) ) row.setID_UTENTE( "" );
            command.addParameter( "P_ID_UTENTE", row.getID_UTENTEField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postUTENTE_VIS.getObjectValue() ) ) postUTENTE_VIS.setValue( "" );
            command.addParameter( "P_UTENTE", postUTENTE_VIS, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOMINATIVO.getObjectValue() ) ) postNOMINATIVO.setValue( "" );
            command.addParameter( "P_NOMINATIVO", postNOMINATIVO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPASSWORD.getObjectValue() ) ) postPASSWORD.setValue( "" );
            command.addParameter( "P_PASSWORD", postPASSWORD, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPWD_MODIFIED()) ) row.setPWD_MODIFIED( "" );
            command.addParameter( "P_PWD_MODIFIED", row.getPWD_MODIFIEDField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postRINNOVO_PASSWORD.getObjectValue() ) ) postRINNOVO_PASSWORD.setValue( "" );
            command.addParameter( "P_RINNOVO_PASSWORD", postRINNOVO_PASSWORD, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postSTATO.getObjectValue() ) ) postSTATO.setValue( "" );
            command.addParameter( "P_STATO", postSTATO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postLINGUA.getObjectValue() ) ) postLINGUA.setValue( "" );
            command.addParameter( "P_LINGUA", postLINGUA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postGRUPPO_LAVORO.getObjectValue() ) ) postGRUPPO_LAVORO.setValue( "" );
            command.addParameter( "P_GRUPPO_LAVORO", postGRUPPO_LAVORO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_IMPORTANZA", postIMPORTANZA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "P_UTENTE_AGG", sesUtente, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_SOGGETTO", postSOGGETTO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getAMMINISTRATORE()) ) row.setAMMINISTRATORE( "N" );
            command.addParameter( "P_AMMINISTRATORE", row.getAMMINISTRATOREField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postINFO_IDENTIFICAZIONE.getObjectValue() ) ) postINFO_IDENTIFICAZIONE.setValue( "" );
            command.addParameter( "P_INFO_IDENTIFICAZIONE", postINFO_IDENTIFICAZIONE, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildInsertEvent( new DataObjectEvent(command) );

//End insert

//insertDataBound @6-BC781F8A
            fireBeforeExecuteInsertEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteInsertEvent( new DataObjectEvent(command) );

//End insertDataBound

//End of insert @6-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of insert

//update @6-4D48F26F
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{? = call AD4WEB.UTENTI_PM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getID_UTENTE()) ) row.setID_UTENTE( "" );
            command.addParameter( "P_ID_UTENTE", row.getID_UTENTEField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
            command.addParameter( "P_UTENTE", sesAD4UTENTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOMINATIVO.getObjectValue() ) ) postNOMINATIVO.setValue( "" );
            command.addParameter( "P_NOMINATIVO", postNOMINATIVO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPASSWORD.getObjectValue() ) ) postPASSWORD.setValue( "" );
            command.addParameter( "P_PASSWORD", postPASSWORD, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPWD_MODIFIED()) ) row.setPWD_MODIFIED( "" );
            command.addParameter( "P_PWD_MODIFIED", row.getPWD_MODIFIEDField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postRINNOVO_PASSWORD.getObjectValue() ) ) postRINNOVO_PASSWORD.setValue( "" );
            command.addParameter( "P_RINNOVO_PASSWORD", postRINNOVO_PASSWORD, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postSTATO.getObjectValue() ) ) postSTATO.setValue( "" );
            command.addParameter( "P_STATO", postSTATO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postLINGUA.getObjectValue() ) ) postLINGUA.setValue( "" );
            command.addParameter( "P_LINGUA", postLINGUA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postGRUPPO_LAVORO.getObjectValue() ) ) postGRUPPO_LAVORO.setValue( "" );
            command.addParameter( "P_GRUPPO_LAVORO", postGRUPPO_LAVORO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_IMPORTANZA", postIMPORTANZA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "P_UTENTE_AGG", sesUtente, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_SOGGETTO", postSOGGETTO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getAMMINISTRATORE()) ) row.setAMMINISTRATORE( "N" );
            command.addParameter( "P_AMMINISTRATORE", row.getAMMINISTRATOREField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postINFO_IDENTIFICAZIONE.getObjectValue() ) ) postINFO_IDENTIFICAZIONE.setValue( "" );
            command.addParameter( "P_INFO_IDENTIFICAZIONE", postINFO_IDENTIFICAZIONE, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @6-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @6-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @6-E9C79D0E
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setSql( "delete utenti "
                        + " where UTENTE = '{UTENTE}'" );
            if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
            command.addParameter( "UTENTE", sesAD4UTENTE, null );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @6-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @6-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @6-FB152429
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_UTENTE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlID_UTENTE;
            } else if ( "ID_UTENTE".equals(name) && prefix == null ) {
                param = ctrlID_UTENTE;
            }
            if ( "UTENTE_VIS".equals(name) && "post".equals(prefix) ) {
                param = postUTENTE_VIS;
            } else if ( "UTENTE_VIS".equals(name) && prefix == null ) {
                param = postUTENTE_VIS;
            }
            if ( "NOMINATIVO".equals(name) && "post".equals(prefix) ) {
                param = postNOMINATIVO;
            } else if ( "NOMINATIVO".equals(name) && prefix == null ) {
                param = postNOMINATIVO;
            }
            if ( "PASSWORD".equals(name) && "post".equals(prefix) ) {
                param = postPASSWORD;
            } else if ( "PASSWORD".equals(name) && prefix == null ) {
                param = postPASSWORD;
            }
            if ( "PWD_MODIFIED".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPWD_MODIFIED;
            } else if ( "PWD_MODIFIED".equals(name) && prefix == null ) {
                param = ctrlPWD_MODIFIED;
            }
            if ( "RINNOVO_PASSWORD".equals(name) && "post".equals(prefix) ) {
                param = postRINNOVO_PASSWORD;
            } else if ( "RINNOVO_PASSWORD".equals(name) && prefix == null ) {
                param = postRINNOVO_PASSWORD;
            }
            if ( "STATO".equals(name) && "post".equals(prefix) ) {
                param = postSTATO;
            } else if ( "STATO".equals(name) && prefix == null ) {
                param = postSTATO;
            }
            if ( "LINGUA".equals(name) && "post".equals(prefix) ) {
                param = postLINGUA;
            } else if ( "LINGUA".equals(name) && prefix == null ) {
                param = postLINGUA;
            }
            if ( "GRUPPO_LAVORO".equals(name) && "post".equals(prefix) ) {
                param = postGRUPPO_LAVORO;
            } else if ( "GRUPPO_LAVORO".equals(name) && prefix == null ) {
                param = postGRUPPO_LAVORO;
            }
            if ( "IMPORTANZA".equals(name) && "post".equals(prefix) ) {
                param = postIMPORTANZA;
            } else if ( "IMPORTANZA".equals(name) && prefix == null ) {
                param = postIMPORTANZA;
            }
            if ( "NOTE".equals(name) && "post".equals(prefix) ) {
                param = postNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = postNOTE;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "SOGGETTO".equals(name) && "post".equals(prefix) ) {
                param = postSOGGETTO;
            } else if ( "SOGGETTO".equals(name) && prefix == null ) {
                param = postSOGGETTO;
            }
            if ( "AMMINISTRATORE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlAMMINISTRATORE;
            } else if ( "AMMINISTRATORE".equals(name) && prefix == null ) {
                param = ctrlAMMINISTRATORE;
            }
            if ( "INFO_IDENTIFICAZIONE".equals(name) && "post".equals(prefix) ) {
                param = postINFO_IDENTIFICAZIONE;
            } else if ( "INFO_IDENTIFICAZIONE".equals(name) && prefix == null ) {
                param = postINFO_IDENTIFICAZIONE;
            }
            if ( "AD4UTENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4UTENTE;
            } else if ( "AD4UTENTE".equals(name) && prefix == null ) {
                param = sesAD4UTENTE;
            }
            if ( "UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE;
            } else if ( "UTENTE".equals(name) && prefix == null ) {
                param = urlUTENTE;
            }
            if ( "SOGGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlSOGGETTO;
            } else if ( "SOGGETTO".equals(name) && prefix == null ) {
                param = urlSOGGETTO;
            }
            if ( "SE_NUOVO".equals(name) && "url".equals(prefix) ) {
                param = urlSE_NUOVO;
            } else if ( "SE_NUOVO".equals(name) && prefix == null ) {
                param = urlSE_NUOVO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @6-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @6-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @6-305A023C
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @6-D00ACF95
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @6-3BAD39CE
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildInsertEvent @6-FBA08B71
    public void fireBeforeBuildInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildInsert(e);
        }
    }
//End fireBeforeBuildInsertEvent

//fireBeforeExecuteInsertEvent @6-47AFA6A5
    public void fireBeforeExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteInsert(e);
        }
    }
//End fireBeforeExecuteInsertEvent

//fireAfterExecuteInsertEvent @6-E9CE95AE
    public void fireAfterExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteInsert(e);
        }
    }
//End fireAfterExecuteInsertEvent

//fireBeforeBuildSelectEvent @6-2405BE8B
    public void fireBeforeBuildUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildUpdate(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @6-E9DFF86B
    public void fireBeforeExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteUpdate(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @6-580A2987
    public void fireAfterExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteUpdate(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildSelectEvent @6-D021D0EA
    public void fireBeforeBuildDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildDelete(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteDeleteEvent @6-DD540FBB
    public void fireBeforeExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteDelete(e);
        }
    }
//End fireBeforeExecuteDeleteEvent

//fireAfterExecuteDeleteEvent @6-2A6E2049
    public void fireAfterExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteDelete(e);
        }
    }
//End fireAfterExecuteDeleteEvent

//class DataObject Tail @6-ED3F53A4
} // End of class DS
//End class DataObject Tail

