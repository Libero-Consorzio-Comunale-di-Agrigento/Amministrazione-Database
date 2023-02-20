//AD4CaratteristicheAccessoJS_REFRESH_SLAVESHandler Head @247-ECAC57F3
package ad4web.AD4CaratteristicheAccesso;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4CaratteristicheAccessoJS_REFRESH_SLAVESHandler implements ControlListener {
//End AD4CaratteristicheAccessoJS_REFRESH_SLAVESHandler Head

//BeforeShow Head @247-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action AD4ShowResultRefreshSlaves @248-85CD81DE
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
    if("N".equals("Y")){
        js += "window.location = \""+ backPage +"\";";
    }
    if (!js.equals("<script language=\"javascript\">")) {
        js += "</script>";
        e.getControl().setValue(js);
    }
}
SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4REFRESHSLAVES","");
//End Event BeforeShow Action AD4ShowResultRefreshSlaves

//Event BeforeShow Action RedirectToSessionBackPage @251-EC8CFF53
String jsValue = (String)e.getControl().getValue();
String backPageRedirect;
String backPageVar="AD4BPCOPIA";
if (!("".equals(backPageVar) || backPageVar == null)) {
    backPageRedirect = (String)SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(backPageVar);
    if (!("".equals(backPageRedirect) || backPageRedirect == null)) {
        if ("".equals(jsValue) || jsValue == null) {
            jsValue = "<script language=\"javascript\">";
            jsValue += "window.location = \""+ backPageRedirect +"\";";
            jsValue += "</script>";
        }
        else {
            jsValue = jsValue.replace("</script>","window.location = \""+ backPageRedirect +"\"; </script>");
        }
        e.getControl().setValue(jsValue);
    }
}
SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(backPageVar,"");
//End Event BeforeShow Action RedirectToSessionBackPage

//BeforeShow Tail @247-FCB6E20C
    }
//End BeforeShow Tail

//AD4CaratteristicheAccessoJS_REFRESH_SLAVESHandler Tail @247-FCB6E20C
}
//End AD4CaratteristicheAccessoJS_REFRESH_SLAVESHandler Tail

