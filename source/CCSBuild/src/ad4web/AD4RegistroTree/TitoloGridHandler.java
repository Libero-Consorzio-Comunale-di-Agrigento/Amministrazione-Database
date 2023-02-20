//TitoloGridHandler @43-908E57E1
package ad4web.AD4RegistroTree;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TitoloGridHandler implements GridListener {
//End TitoloGridHandler

// //beforeShow @43-F81417CB

//BeforeShow Head @43-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @43-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @43-F81417CB

//beforeShowRow Head @43-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @43-E1296F38
        e.getGrid().getLink("ModChiave").getParameter("TIPOR").setValue(( "C" ));
        e.getGrid().getLink("ModChiave").getParameter("SE_NUOVO").setValue(( "N" ));
        e.getGrid().getLink("NuovaChiave").getParameter("TIPOR").setValue(( "C" ));
        e.getGrid().getLink("NuovaChiave").getParameter("SE_NUOVO").setValue(( "S" ));
        e.getGrid().getLink("NuovaStringa").getParameter("TIPOR").setValue(( "S" ));
//End Set values for static controls

//beforeShowRow Tail @43-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @43-F81417CB

//BeforeSelect Head @43-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//Event BeforeSelect Action Set BackPage Value from Session URLs @58-7FA01B74
// Inizializza il logger
org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("finmatica");
log.debug("------------ "+ e.getPage().getName() +" ------------ ");
log.debug("------------ Set BackPage Value from Session URLs ------------ ");
// Memorizza il separatore scelto
String separatore=" ";
// Memorizza il valore del flag initializeSessionURL
int    initialize = 0;
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
//End Event BeforeSelect Action Set BackPage Value from Session URLs

//BeforeSelect Tail @43-FCB6E20C
    }
//End BeforeSelect Tail

//TitoloHandler Tail @43-FCB6E20C
}
//End TitoloHandler Tail

