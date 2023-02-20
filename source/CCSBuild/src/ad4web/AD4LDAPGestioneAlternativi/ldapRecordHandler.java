//ldapHandler Head @11-C0907D02
package ad4web.AD4LDAPGestioneAlternativi;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class ldapRecordHandler implements RecordListener {
//End ldapHandler Head

//BeforeShow Head @11-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @11-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @11-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Set BackPage Value from Session URLs @26-7FA01B74
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
//End Event OnValidate Action Set BackPage Value from Session URLs

//OnValidate Tail @11-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @11-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @11-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @11-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @11-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @11-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @11-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @11-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @11-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @11-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @11-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @11-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @11-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @11-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @11-FCB6E20C
    }
//End AfterDelete Tail

//ldapHandler Tail @11-FCB6E20C
}
//End ldapHandler Tail

