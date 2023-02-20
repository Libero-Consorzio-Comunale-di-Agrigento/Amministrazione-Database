//RICHIESTE_STAMPALINKHandler Head @61-F1E1864C
package amvadm.AdmRichiesteStampa;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class RICHIESTE_STAMPALINKHandler implements ControlListener {
//End RICHIESTE_STAMPALINKHandler Head

//BeforeShow Head @61-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action JFC CCS Class caller DB @62-6BB3293E
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
    it.finmatica.jfc.ccs.AdmRichiesteStampa istanza = new it.finmatica.jfc.ccs.AdmRichiesteStampa(e,con);
    istanza.setLinkStampaTutto();
    /****CHIUSURA DELLA CONNESSIONE****/
    con.close();
} catch (java.sql.SQLException sqlexc) {
     /* Gestione delle exception SQL che possono essere lanciate dai metodi
        record degli oggetti log */
     sqlexc.printStackTrace();
        try {
            con.close();
        } catch (java.lang.Exception e1){
            System.out.println("Errore nella chiusura della connessione"); 
            e1.printStackTrace();
        }
} catch (java.lang.Exception exc) {
     /* In presenza di altre tipologie di eccezione 
        chiudo la connessione */
        exc.printStackTrace(); 
    try {
        con.close();
    } catch (java.lang.Exception e1){
        System.out.println("Errore nella chiusura della connessione"); 
        e1.printStackTrace();
    }
}
//End Event BeforeShow Action JFC CCS Class caller DB

//BeforeShow Tail @61-FCB6E20C
    }
//End BeforeShow Tail

//RICHIESTE_STAMPALINKHandler Tail @61-FCB6E20C
}
//End RICHIESTE_STAMPALINKHandler Tail

