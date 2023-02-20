//AD4DirittoAccessoJS_REFRESH_SLAVESHandler Head @165-7DD9BEAB
package ad4web.AD4DirittoAccesso;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4DirittoAccessoJS_REFRESH_SLAVESHandler implements ControlListener {
//End AD4DirittoAccessoJS_REFRESH_SLAVESHandler Head

//BeforeShow Head @165-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action AD4ShowResultRefreshSlaves @170-3C8FE74C
String	js;
String backPage;
String resultRefresh;
resultRefresh = (String)SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("AD4REFRESHSLAVES");
backPage = (String)SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("AD4BP");
if (!("".equals(resultRefresh ) || resultRefresh == null)) {
    js = "<script language=\"javascript\">";
    if (!resultRefresh.equals("NO")) {
        js += "alert(\""+ resultRefresh + "\");";
    }
    if("Y".equals("Y")){
        js += "window.location = \""+ backPage +"\";";
    }
    if (!js.equals("<script language=\"javascript\">")) {
        js += "</script>";
        e.getControl().setValue(js);
    }
}
SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4REFRESHSLAVES","");
//End Event BeforeShow Action AD4ShowResultRefreshSlaves

//BeforeShow Tail @165-FCB6E20C
    }
//End BeforeShow Tail

//AD4DirittoAccessoJS_REFRESH_SLAVESHandler Tail @165-FCB6E20C
}
//End AD4DirittoAccessoJS_REFRESH_SLAVESHandler Tail

