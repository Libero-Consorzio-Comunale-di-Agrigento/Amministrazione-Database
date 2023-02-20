//AD4AccessiRicercaHandler imports @1-80BFC68C
package ad4web.AD4AccessiRicerca;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4AccessiRicercaHandler imports

//AD4AccessiRicercaHandler Head @1-2EA3125A
public class AD4AccessiRicercaPageHandler implements PageListener {
//End AD4AccessiRicercaHandler Head

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

//Event BeforeShow Action Set BackPage Value from Session URLs @346-6DAE9249
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
// Memorizza la pagina attuale senza parametri
StringTokenizer st = new StringTokenizer(mvUrl,"?");
String actualPage = st.nextToken();
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
        if(LastPage.equals(actualPage)){
            numOfTokens = numOfTokens - 2;
        }
    }
    else {
        numOfTokens = numOfTokens - 1;
    }
}
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
//End Event BeforeShow Action Set BackPage Value from Session URLs

//Event BeforeShow Action Custom Code @435-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
 SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4ACCEELI","0");
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @1-FCB6E20C
    }
//End BeforeShow Tail

//BeforeUnload Head @1-1DDBA584
    public void beforeUnload(Event e) {
//End BeforeUnload Head

//BeforeUnload Tail @1-FCB6E20C
    }
//End BeforeUnload Tail

//AD4AccessiRicercaHandler Tail @1-FCB6E20C
}
//End AD4AccessiRicercaHandler Tail

