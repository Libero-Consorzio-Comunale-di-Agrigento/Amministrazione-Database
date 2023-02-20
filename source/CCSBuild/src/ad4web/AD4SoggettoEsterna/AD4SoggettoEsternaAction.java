//AD4SoggettoEsternaAction imports @1-6B1B0AC1
package ad4web.AD4SoggettoEsterna;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4SoggettoEsternaAction imports

//AD4SoggettoEsternaAction class @1-04C9E1D3
public class AD4SoggettoEsternaAction extends Action {

//End AD4SoggettoEsternaAction class

//AD4SoggettoEsternaAction: method perform @1-31B29D48
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4SoggettoEsternaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4SoggettoEsternaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4SoggettoEsternaAction: method perform

//AD4SoggettoEsternaAction: call children actions @1-F00B07BE
        if ( page.getChild( "AmvStyle" ).isVisible() ) {
            page.getRequest().setAttribute("AmvStyleParent",page);
            common.AmvStyle.AmvStyleAction AmvStyle = new common.AmvStyle.AmvStyleAction();
            result = result != null ? result : AmvStyle.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "AmvControl" ).isVisible() ) {
            page.getRequest().setAttribute("AmvControlParent",page);
            common.AmvControl.AmvControlAction AmvControl = new common.AmvControl.AmvControlAction();
            result = result != null ? result : AmvControl.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "AD4SoggettoInclusa" ).isVisible() ) {
            page.getRequest().setAttribute("AD4SoggettoInclusaParent",page);
            ad4web.AD4SoggettoInclusa.AD4SoggettoInclusaAction AD4SoggettoInclusa = new ad4web.AD4SoggettoInclusa.AD4SoggettoInclusaAction();
            result = result != null ? result : AD4SoggettoInclusa.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4SoggettoEsternaAction: call children actions

//AD4SoggettoEsterna Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4SoggettoEsterna Page: method validate

//AD4SoggettoEsternaAction Tail @1-FCB6E20C
}
//End AD4SoggettoEsternaAction Tail
