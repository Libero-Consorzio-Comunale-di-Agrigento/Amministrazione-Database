//AD4SoggRicercaAction imports @1-F57EDF47
package ad4web.AD4SoggRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4SoggRicercaAction imports

//AD4SoggRicercaAction class @1-54602E1D
public class AD4SoggRicercaAction extends Action {

//End AD4SoggRicercaAction class

//AD4SoggRicercaAction: method perform @1-4170EAD3
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4SoggRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4SoggRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4SoggRicercaAction: method perform

//AD4SoggRicercaAction: call children actions @1-4B9DD320
        if ( page.getChild( "Header" ).isVisible() ) {
            page.getRequest().setAttribute("HeaderParent",page);
            common.Header.HeaderAction Header = new common.Header.HeaderAction();
            result = result != null ? result : Header.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "Left" ).isVisible() ) {
            page.getRequest().setAttribute("LeftParent",page);
            common.Left.LeftAction Left = new common.Left.LeftAction();
            result = result != null ? result : Left.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "Guida" ).isVisible() ) {
            page.getRequest().setAttribute("GuidaParent",page);
            common.Guida.GuidaAction Guida = new common.Guida.GuidaAction();
            result = result != null ? result : Guida.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "AD4SoggRicercaInclusa" ).isVisible() ) {
            page.getRequest().setAttribute("AD4SoggRicercaInclusaParent",page);
            ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaAction AD4SoggRicercaInclusa = new ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaAction();
            result = result != null ? result : AD4SoggRicercaInclusa.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "Footer" ).isVisible() ) {
            page.getRequest().setAttribute("FooterParent",page);
            common.Footer.FooterAction Footer = new common.Footer.FooterAction();
            result = result != null ? result : Footer.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4SoggRicercaAction: call children actions

//AD4SoggRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4SoggRicerca Page: method validate

//AD4SoggRicercaAction Tail @1-FCB6E20C
}
//End AD4SoggRicercaAction Tail
