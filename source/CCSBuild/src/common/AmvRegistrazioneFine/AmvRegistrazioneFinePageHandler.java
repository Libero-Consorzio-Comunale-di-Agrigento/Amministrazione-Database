//AmvRegistrazioneFineHandler imports @1-096F6155
package common.AmvRegistrazioneFine;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AmvRegistrazioneFineHandler imports

//AmvRegistrazioneFineHandler Head @1-54401573
public class AmvRegistrazioneFinePageHandler implements PageListener {
//End AmvRegistrazioneFineHandler Head

//AfterInitialize Head @1-89E84600
    public void afterInitialize(Event e) {
//End AfterInitialize Head

//Event AfterInitialize Action JFC CCS Class caller DB @25-F1D6AD22
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
    it.finmatica.jfc.ccs.AmvRegistrazioneFine istanza = new it.finmatica.jfc.ccs.AmvRegistrazioneFine(e,con);
    istanza.validaRichiesta();
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
//End Event AfterInitialize Action JFC CCS Class caller DB

//AfterInitialize Tail @1-FCB6E20C
    }
//End AfterInitialize Tail

//OnInitializeView Head @1-E3C15E0F
    public void onInitializeView(Event e) {
//End OnInitializeView Head

//OnInitializeView Tail @1-FCB6E20C
    }
//End OnInitializeView Tail

//BeforeShow Head @1-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @1-FCB6E20C
    }
//End BeforeShow Tail

//BeforeUnload Head @1-1DDBA584
    public void beforeUnload(Event e) {
//End BeforeUnload Head

//BeforeUnload Tail @1-FCB6E20C
    }
//End BeforeUnload Tail

//AmvRegistrazioneFineHandler Tail @1-FCB6E20C
}
//End AmvRegistrazioneFineHandler Tail

