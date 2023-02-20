//AD4_RICHIESTE_ABILITAZIONERecordHandler Head @7-35F0524F
package amvadm.AdmRichiesta;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_RICHIESTE_ABILITAZIONERecordRecordHandler implements RecordListener {
//End AD4_RICHIESTE_ABILITAZIONERecordHandler Head

//BeforeShow Head @7-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @7-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @7-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @7-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @7-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @7-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @7-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @7-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @7-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @7-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @7-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//Event BeforeUpdate Action JFC CCS Record Class caller DB @71-3FB75946
/* ------------------------------------------- *
 *  Codice per la creazione della connessione  *
 * ------------------------------------------- */
 java.sql.Connection con = null;
try{
    java.util.Properties siteProps = new java.util.Properties();
    siteProps = (java.util.Properties)ContextStorage.getInstance().getAttribute(com.codecharge.Names.SITE_PROPERTIES_KEY);
    String connName = siteProps.getProperty("cn.url");
    connName = connName.substring(connName.indexOf("jdbc"));
    String charEncoding = (new CCSLocale()).getCharacterEncoding();
    javax.naming.Context initContext = new javax.naming.InitialContext();
    javax.naming.Context envContext = (javax.naming.Context)initContext.lookup("java:comp/env/");
    javax.sql.DataSource ds = (javax.sql.DataSource)envContext.lookup(connName);
    con = ds.getConnection();
    /****CREAZIONE ISTANZA DELLA CLASSE E CHIAMATA DEL METODO SPECIFICATO******/
    it.finmatica.jfc.ccs.AdmRichiesta istanza = new it.finmatica.jfc.ccs.AdmRichiesta(e,con);
    istanza.gestisciRichiesta();
    /****CHIUSURA DELLA CONNESSIONE****/
    con.close();
} catch (java.sql.SQLException sqlexc) {
     /* Gestione delle exception SQL che possono essere lanciate dai metodi
        record degli oggetti log */
     sqlexc.printStackTrace();
        try {
     e.getRecord().addError(it.finmatica.jfc.ccs.ErrorHandler.getFormattedOracleExceptionMessage(sqlexc,con));
            con.close();
        } catch (java.lang.Exception e1){
            System.out.println("Errore nella chiusura della connessione"); 
            e1.printStackTrace();
        }
} catch (java.lang.Exception exc) {
     /* In presenza di altre tipologie di eccezione 
        chiudo la connessione */
        exc.printStackTrace(); 
     e.getRecord().addError(exc.getMessage());
    try {
        con.close();
    } catch (java.lang.Exception e1){
        System.out.println("Errore nella chiusura della connessione"); 
        e1.printStackTrace();
    }
}
//End Event BeforeUpdate Action JFC CCS Record Class caller DB

//BeforeUpdate Tail @7-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @7-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @7-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @7-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @7-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @7-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @7-FCB6E20C
    }
//End AfterDelete Tail

//AD4_RICHIESTE_ABILITAZIONERecordHandler Tail @7-FCB6E20C
}
//End AD4_RICHIESTE_ABILITAZIONERecordHandler Tail

