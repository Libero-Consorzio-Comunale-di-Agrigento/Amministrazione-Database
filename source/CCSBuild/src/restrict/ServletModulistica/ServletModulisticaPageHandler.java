//ServletModulisticaHandler imports @1-C5B5D9CD
package restrict.ServletModulistica;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import it.finmatica.modulistica.*;
//End ServletModulisticaHandler imports

//ServletModulisticaHandler Head @1-10F14451
public class ServletModulisticaPageHandler implements PageListener {
//End ServletModulisticaHandler Head

//AfterInitialize Head @1-89E84600
    public void afterInitialize(Event e) {
//End AfterInitialize Head

//Event AfterInitialize Action Custom Code @28-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
String queryString = e.getPage().getRequest().getQueryString();
String codiceRichiesta = e.getPage().getRequest().getSession().getId()+System.currentTimeMillis();
String servletPath = e.getPage().getRequest().getRequestURI();

// chiamata iniziale della pagina
// la richiamo nuovamente aggiungendo un cr calcolato in maniera univoca  
   if ( StringUtils.isEmpty(e.getPage().getHttpGetParameter("cr")) && StringUtils.isEmpty(e.getPage().getHttpPostParameter("cr"))) {
    queryString = StringUtils.isEmpty(queryString) ? "cr=" + codiceRichiesta : queryString + "&" + "cr=" + codiceRichiesta;
    SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVEXE",null);
    e.getPage().setRedirectString(servletPath + "?" + queryString);
  } 

// genero modello

   	boolean inoltri = false;
	boolean inoltro_modello = false;
	boolean erroriControlli = false;
	boolean modelliSuccessivi = false;
	boolean modelliPrecedenti = false;
   	String path = "/";
    String realPath = e.getPage().getRequest().getRealPath(path);
    Modulistica modello = new Modulistica(realPath);
	// la richiesta ha inoltri da effettuare
	inoltri = modello.esistonoInoltri(e.getPage().getParameter("area"), e.getPage().getParameter("cm"), e.getPage().getParameter("cr"));
    e.getPage().getRequest().setAttribute("INOLTRI", inoltri?"true":"false");
	// il modello ha inoltri
	inoltro_modello = modello.esistonoInoltri(e.getPage().getParameter("area"), e.getPage().getParameter("cm"),"");
	e.getPage().getRequest().setAttribute("INOLTRO_MODELLO", inoltro_modello?"true":"false");
	//String ruolo = SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("Ruolo").toString();
	modello.genera(e.getPage().getRequest(),"CC");
	// errori generati dai controlli
	erroriControlli = modello.esistonoErrori();
	e.getPage().getRequest().setAttribute("ERRORI_CONTROLLI", erroriControlli?"true":"false");
	//presenza di modelli precedenti
    if (SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("MVMODELLI_SUCCESSIVI") != null) {
	    if (SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("MVMODELLI_SUCCESSIVI").toString().equals("true")) {
			modelliPrecedenti = true;
		}
	}
	e.getPage().getRequest().setAttribute("MODELLI_PRECEDENTI", modelliPrecedenti?"true":"false");
			
	// presenza di modelli successivi
	// utilizza try perchè il metodo potrebbe non essere implementato
	try {
		if (modello.getModelloSuccessivo() != null) {
			System.out.println("1 - Modello Successivo:" + modello.getModelloSuccessivo());
			if (!modello.getModelloSuccessivo().equals("")) {
				//System.out.println("2 - Modello Successivo:" + modello.getModelloSuccessivo());
				modelliSuccessivi = true;			
				//System.out.println("3 - Modelli Successivi True");
			}
			e.getPage().getRequest().setAttribute("MODELLI_SUCCESSIVI", modelliSuccessivi?"true":"false");
			SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVMODELLI_SUCCESSIVI",modelliSuccessivi?"true":"false");
			//System.out.println("4 - Attributo di request MODELLI_SUCCESSIVI" + e.getPage().getRequest().getAttribute("MODELLI_SUCCESSIVI"));
	        ((com.codecharge.components.Label) ((com.codecharge.components.Grid) (e.getPage().getChild( "INSERISCI_RICHIESTA" ))).getChild( "MESSAGGIO" )).setValue( "" );
			}
	} catch(Exception exc){
		System.out.println(exc.getMessage());
	}

	//SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("Ruolo",ruolo);
	String corpo = modello.getValue();	
	if (!( StringUtils.isEmpty(e.getPage().getHttpGetParameter("cr")) && StringUtils.isEmpty(e.getPage().getHttpPostParameter("cr")) )) {
		//e.getPage().getRequest().setAttribute("INOLTRI", inoltri?"true":"false");
		//corpo = corpo + "<br>Esistono Inoltri" + inoltri;
	}
    ((com.codecharge.components.Label) (e.getPage().getChild( "corpoHtml" ))).setValue( corpo );
 

// se si sta effettuando la registrazione istanzio una variabile che viene
// utlizzata dalla grid di inserimento richiesta
   if (StringUtils.isEmpty(e.getPage().getHttpPostParams().getParameter("reload")) && !StringUtils.isEmpty(e.getPage().getHttpPostParams().getParameter("cr")) && !erroriControlli) {
	//e.getPage().getRequest().setAttribute("RIC", "SI");
    SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVEXE","SI");
	}	


// imposto MVDOCID di sessione con ID del documento
if (!StringUtils.isEmpty(e.getPage().getParameter("ID"))) {
	SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVIDRIC",e.getPage().getHttpGetParams().getParameter("ID"));
}
if (!StringUtils.isEmpty(e.getPage().getParameter("IDPD"))) {
	SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVIDPDRIC",e.getPage().getHttpGetParams().getParameter("IDPD"));
}
if (!StringUtils.isEmpty(e.getPage().getParameter("REV"))) {
	SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("MVREVRIC",e.getPage().getHttpGetParams().getParameter("REV"));
}

//End Event AfterInitialize Action Custom Code

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

//ServletModulisticaHandler Tail @1-FCB6E20C
}
//End ServletModulisticaHandler Tail

