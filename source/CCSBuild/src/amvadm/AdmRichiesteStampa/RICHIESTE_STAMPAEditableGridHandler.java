//RICHIESTE_STAMPAGridHandler @2-C26805DD
package amvadm.AdmRichiesteStampa;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RICHIESTE_STAMPAEditableGridHandler implements EditableGridListener {
//End RICHIESTE_STAMPAGridHandler

// //beforeSelect @2-F81417CB

//BeforeSelect Head @2-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @2-FCB6E20C
    }
//End BeforeSelect Tail

// //beforeShow @2-F81417CB

//BeforeShow Head @2-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action EditableGridRedirect @64-B7AF5575
if ((e.getPage().getHttpGetParameter("ccsForm") != null) && !(e.getPage().getRequest().getAttribute("GridError")!= null)) {
    try {
        String parametro = "ccsForm=" + e.getEditableGrid().getName();
        String parametri = e.getPage().getRequest().getQueryString().replaceAll(parametro,"");
        String percorso = e.getPage().getRequest().getRequestURI() + "?" + parametri;
        e.getPage().getResponse().sendRedirect(percorso);
    } catch (Exception ex)  {
        ex.printStackTrace();
    }
}
//End Event BeforeShow Action EditableGridRedirect

//Event BeforeShow Action JFC CCS Class caller @65-FAB0C8F1
it.finmatica.jfc.ccs.AdmRichiesteStampa istanza = new it.finmatica.jfc.ccs.AdmRichiesteStampa(e);
istanza.hideEditableGrid();
//End Event BeforeShow Action JFC CCS Class caller

//BeforeShow Tail @2-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @2-F81417CB

//beforeShowRow Head @2-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @2-FCB6E20C
    }
//End beforeShowRow Tail

//OnValidate Head @2-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @2-FCB6E20C
    }
//End OnValidate Tail

//BeforeUpdate Head @2-9D1B8475
    public void beforeSubmit(Event e) {
//End BeforeUpdate Head

//Event BeforeSubmit Action JFC CCS Class caller DB @56-C8686B31
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
    istanza.resetStampa();
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
//End Event BeforeSubmit Action JFC CCS Class caller DB

//BeforeUpdate Tail @2-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @2-9ED73E93
    public void afterSubmit(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @2-FCB6E20C
    }
//End AfterUpdate Tail

//RICHIESTE_STAMPAHandler Tail @2-FCB6E20C
}
//End RICHIESTE_STAMPAHandler Tail

