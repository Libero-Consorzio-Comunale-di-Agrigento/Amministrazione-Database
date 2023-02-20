//AD4_UTENTEHandler Head @6-D507D841
package common.AmvRegistrazioneInizio;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_UTENTERecordHandler implements RecordListener {
//End AD4_UTENTEHandler Head

//BeforeShow Head @6-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @6-5A869A81
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("STATO_NASCITA").setDefaultValue(100);
            e.getRecord().getControl("RR").setDefaultValue("1");
        }
//End Set values

//BeforeShow Tail @6-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @6-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @6-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @6-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @6-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @6-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @6-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @6-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @6-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @6-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//Event BeforeUpdate Action JFC CCS Record Class caller DB @70-EFEAC976
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
    it.finmatica.jfc.ccs.AmvRegistrazioneInizio istanza = new it.finmatica.jfc.ccs.AmvRegistrazioneInizio(e,con);
    istanza.registraWeb();
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

//Event BeforeUpdate Action Custom Code @69-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
  // Se non ci sono errori ridirezione alla stringa impostata 
 // nel campo hidden REDIRECTION
 String nullString = new String("");
 if (e.getPage().getRequest().getAttribute("UPDATING")!=null) {
 	try {
		String url;
		if (!nullString.equals(e.getPage().getRequest().getParameter("REDIRECTION")))  {
			url = e.getPage().getRequest().getParameter("REDIRECTION");
    		e.getPage().setRedirectString(url);
		}
	} catch (Exception ex)  {
		ex.printStackTrace();
	}
} else System.out.println("Ridirezione fallita");

//End Event BeforeUpdate Action Custom Code

//BeforeUpdate Tail @6-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @6-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @6-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @6-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @6-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @6-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @6-FCB6E20C
    }
//End AfterDelete Tail

//AD4_UTENTEHandler Tail @6-FCB6E20C
}
//End AD4_UTENTEHandler Tail

