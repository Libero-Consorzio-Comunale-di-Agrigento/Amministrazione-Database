//AD4RegistroTreeHandler imports @1-197A075A
package ad4web.AD4RegistroTree;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4RegistroTreeHandler imports

//AD4RegistroTreeHandler Head @1-2AE588B4
public class AD4RegistroTreePageHandler implements PageListener {
//End AD4RegistroTreeHandler Head

//AfterInitialize Head @1-89E84600
    public void afterInitialize(Event e) {
//End AfterInitialize Head

//Event AfterInitialize Action Custom Code @35-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
    String urlH="";
	if(e.getPage().getRequest().getQueryString().indexOf("_h")>=0){
       try{
       urlH=com.codecharge.util.StringUtils.decryptB64URL(e.getPage().getRequest().getQueryString().split("=")[1]);
       }catch(Exception exc){exc.printStackTrace();}
   }
    if (e.getPage().getRequest().getParameter("USRORCL")  != null) {
		SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("USRORCL", e.getPage().getRequest().getParameter("USRORCL"));
	} else if((!urlH.equals(""))&&(!com.codecharge.util.StringUtils.getParamValue(urlH,"USRORCL").equals(""))){
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("USRORCL",   com.codecharge.util.StringUtils.getParamValue(urlH,"USRORCL") );
    }
	if (e.getPage().getRequest().getParameter("MENU")  != null) {
		SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("ISMENU", e.getPage().getRequest().getParameter("MENU"));
	}else if((!urlH.equals(""))&&(!com.codecharge.util.StringUtils.getParamValue(urlH,"MENU").equals(""))){
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("ISMENU",   com.codecharge.util.StringUtils.getParamValue(urlH,"MENU") );
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

//AD4RegistroTreeHandler Tail @1-FCB6E20C
}
//End AD4RegistroTreeHandler Tail

