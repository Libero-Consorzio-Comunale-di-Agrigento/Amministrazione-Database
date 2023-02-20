//AD4_STRINGHEHandler Head @23-F01D11B8
package ad4web.AD4Stringa;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_STRINGHERecordHandler implements RecordListener {
//End AD4_STRINGHEHandler Head

//BeforeShow Head @23-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @159-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
 String urlH="";
	if(e.getPage().getRequest().getQueryString().indexOf("_h")>=0){
       try{
       urlH=com.codecharge.util.StringUtils.decryptB64URL(e.getPage().getRequest().getQueryString().split("=")[1]);
       }catch(Exception exc){exc.printStackTrace();}
   }


   if (e.getPage().getRequest().getParameter("TIPOR")  != null) {
		SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("TIPOR", e.getPage().getRequest().getParameter("TIPOR"));
		if (e.getPage().getRequest().getParameter("TIPOR").equals("C")) {
 	           e.getRecord().setVisible(false);
           }
	} else if((!urlH.equals(""))&&(!com.codecharge.util.StringUtils.getParamValue(urlH,"TIPOR").equals(""))){
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("TIPOR",   com.codecharge.util.StringUtils.getParamValue(urlH,"TIPOR") );
		if (com.codecharge.util.StringUtils.getParamValue(urlH,"TIPOR").equals("C")) {
 	      e.getRecord().setVisible(false);
           }
    }


//  if (e.getPage().getRequest().getParameter("TIPOR").equals("C")) {
// 	e.getRecord().setVisible(false);
// }

//End Event BeforeShow Action Custom Code

//Event BeforeShow Action Set BackPage Value from Session URLs with parameter @176-E550F109
// Memorizza il separatore scelto
String separatore=" ";
// Memorizza il valore del flag initializeSessionURL
int    initialize = 0;
String SessionURLs = "AD4BACK";
String SessionBackPage = "AD4BP";
String parameterName = "chiave";
String parameterValue = e.getPage().getRequest().getParameter("ID");
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
// Aggiunge parametro aggiuntivo
if(!parameterName.equals("")){
    st = new StringTokenizer(backPage,"?");
    numOfTokens = st.countTokens();
    if (numOfTokens == 1){
        parameterName = "?"+ parameterName + "=" + parameterValue;
    }
    else{
        String sParm = "&"+ parameterName + "=";
        String sParm2 = "?"+ parameterName + "=";
        if (backPage.toUpperCase().indexOf(sParm.toUpperCase()) == -1 && backPage.toUpperCase().indexOf(sParm2.toUpperCase()) == -1){
            parameterName = sParm + parameterValue;
        }
        else{
            parameterName = "";
        }
    }
    backPage += parameterName;
}
BackURLs = newBACKURLS + mvUrl + separatore;
// Memorizza il valore del penultimo URL nella variabile di sessione backPage
SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(SessionBackPage, backPage);
// Memorizza il nuovo elenco degli URL nella var di sessione
SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(SessionURLs, BackURLs);
//End Event BeforeShow Action Set BackPage Value from Session URLs with parameter

//BeforeShow Tail @23-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @23-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @23-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @23-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @23-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @23-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @23-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @23-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @23-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @23-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @23-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @23-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @23-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @23-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @23-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @23-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @23-FCB6E20C
    }
//End AfterDelete Tail

//AD4_STRINGHEHandler Tail @23-FCB6E20C
}
//End AD4_STRINGHEHandler Tail

