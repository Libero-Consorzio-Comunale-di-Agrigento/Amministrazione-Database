//AD4SoggRicercaEsternaAction imports @1-44918DBB
package ad4web.AD4SoggRicercaEsterna;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4SoggRicercaEsternaAction imports

//AD4SoggRicercaEsternaAction class @1-A6E25E44
public class AD4SoggRicercaEsternaAction extends Action {

//End AD4SoggRicercaEsternaAction class

//AD4SoggRicercaEsternaAction: method perform @1-12482BE3
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4SoggRicercaEsternaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4SoggRicercaEsternaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4SoggRicercaEsternaAction: method perform

//AD4SoggRicercaEsternaAction: call children actions @1-C187C9C1
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
        if ( page.getChild( "AD4SoggRicercaInclusa" ).isVisible() ) {
            page.getRequest().setAttribute("AD4SoggRicercaInclusaParent",page);
            ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaAction AD4SoggRicercaInclusa = new ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaAction();
            result = result != null ? result : AD4SoggRicercaInclusa.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4SoggRicercaEsternaAction: call children actions

//AD4SoggRicercaEsterna Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4SoggRicercaEsterna Page: method validate

//AD4SoggRicercaEsternaAction Tail @1-FCB6E20C
}
//End AD4SoggRicercaEsternaAction Tail
