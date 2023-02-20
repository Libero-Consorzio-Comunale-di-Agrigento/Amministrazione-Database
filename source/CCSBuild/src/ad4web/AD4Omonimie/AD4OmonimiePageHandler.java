//AD4OmonimieHandler imports @1-AA3275D1
package ad4web.AD4Omonimie;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4OmonimieHandler imports

//AD4OmonimieHandler Head @1-103DF094
public class AD4OmonimiePageHandler implements PageListener {
//End AD4OmonimieHandler Head

//AfterInitialize Head @1-89E84600
    public void afterInitialize(Event e) {
//End AfterInitialize Head

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

//Event BeforeShow Action Set BackPage Value from Session URLs @129-EA258D47
// Inizializza il logger
org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("finmatica");
log.debug("------------ "+ e.getPage().getName() +" ------------ ");
log.debug("------------ Set BackPage Value from Session URLs ------------ ");
// Memorizza il separatore scelto
String separatore=" ";
// Memorizza il valore del flag initializeSessionURL
int    initialize = 1;
String SessionURLs = "AD4BACK";
String SessionBackPage = "AD4BP";
String BackURLs = "";
String LastPage = "";
String mvUrl = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("MVURL");
// Se l'elenco degli URL non deve essere inizializzato, lo memorizza
if(initialize != 1){
    BackURLs = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionURLs);
}
/*       LOG          */
log.debug("BackURLs iniziale: " + BackURLs);
/*       FINE LOG     */
// Memorizza la pagina attuale senza parametri
StringTokenizer st = new StringTokenizer(mvUrl,"?");
String actualPage = st.nextToken();
/*       LOG          */
log.debug("actualPage: " + actualPage);
/*       FINE LOG     */
// Memorizza ultima pagina a cui si e' fatto accesso senza parametri
int    numOfTokens;
st = new StringTokenizer(BackURLs,separatore);
numOfTokens = st.countTokens();
while(st.hasMoreTokens()){
    LastPage = st.nextToken();
}
if (!LastPage.equals("")){
    st = new StringTokenizer(LastPage,"?");
    LastPage = st.nextToken();
    /*       LOG          */
    log.debug("LastPage: " + LastPage);
    /*       FINE LOG     */
    // Se l'ultima pagina a cui si e' fatto accesso non e' gia' quella corrente
    // controlla la penultima
    if(!LastPage.equals(actualPage)){
        LastPage = "";
        st = new StringTokenizer(BackURLs,separatore);
        numOfTokens = st.countTokens();
        for (int i=0; i < numOfTokens - 1; i++){
            LastPage = st.nextToken();
        }
        if(!LastPage.equals(actualPage) && !LastPage.equals("")){
            st = new StringTokenizer(LastPage,"?");
            LastPage = st.nextToken();
        }
        /*       LOG          */
        log.debug("LastPage: " + LastPage);
        /*       FINE LOG     */
        if(LastPage.equals(actualPage)){
            numOfTokens = numOfTokens - 2;
        }
    }
    else {
        numOfTokens = numOfTokens - 1;
    }
}
/*       LOG          */
log.debug("LastPage: " + LastPage);
log.debug("numero di URL: "+ numOfTokens);
/*       FINE LOG     */
// Ricostruisce l'elenco degli URL togliendo l'ultima pagina o la penultima e l'ultima
// Memorizza il valore del penultimo URL
String newBACKURLS = "";
String backPage = "";
String dep;
st = new StringTokenizer(BackURLs,separatore);
for (int i=0; i < numOfTokens; i++){
    dep = st.nextToken();
    newBACKURLS = newBACKURLS + dep + separatore;
    if (i == numOfTokens - 1){
        backPage = dep;
    }
}
BackURLs = newBACKURLS + mvUrl + separatore;
// Memorizza il valore del penultimo URL nella variabile di sessione backPage
SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(SessionBackPage, backPage);
// Memorizza il nuovo elenco degli URL nella var di sessione
SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(SessionURLs, BackURLs);
/*       LOG          */
log.debug("BACKURLS: "+ newBACKURLS);
log.debug("BACKPAGE: "+ backPage);
log.debug("BackURLs FINALE: " + BackURLs);
/*       FINE LOG     */
//End Event BeforeShow Action Set BackPage Value from Session URLs

//BeforeShow Tail @1-FCB6E20C
    }
//End BeforeShow Tail

//BeforeUnload Head @1-1DDBA584
    public void beforeUnload(Event e) {
//End BeforeUnload Head

//BeforeUnload Tail @1-FCB6E20C
    }
//End BeforeUnload Tail

//AD4OmonimieHandler Tail @1-FCB6E20C
}
//End AD4OmonimieHandler Tail

