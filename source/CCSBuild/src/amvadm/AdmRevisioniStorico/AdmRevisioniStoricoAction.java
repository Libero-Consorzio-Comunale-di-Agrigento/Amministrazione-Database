//AdmRevisioniStoricoAction imports @1-B7A2B9E2
package amvadm.AdmRevisioniStorico;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AdmRevisioniStoricoAction imports

//AdmRevisioniStoricoAction class @1-08BBACDF
public class AdmRevisioniStoricoAction extends Action {

//End AdmRevisioniStoricoAction class

//AdmRevisioniStoricoAction: method perform @1-7A9BEC1D
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AdmRevisioniStoricoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AdmRevisioniStoricoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AdmRevisioniStoricoAction: method perform

//AdmRevisioniStoricoAction: call children actions @1-6791385E
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
        if ( page.getChild( "AdmDocumentoRevisioni" ).isVisible() ) {
            page.getRequest().setAttribute("AdmDocumentoRevisioniParent",page);
            amvadm.AdmDocumentoRevisioni.AdmDocumentoRevisioniAction AdmDocumentoRevisioni = new amvadm.AdmDocumentoRevisioni.AdmDocumentoRevisioniAction();
            result = result != null ? result : AdmDocumentoRevisioni.perform( req, resp,  context );
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
//End AdmRevisioniStoricoAction: call children actions

//AdmRevisioniStorico Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AdmRevisioniStorico Page: method validate

//AdmRevisioniStoricoAction Tail @1-FCB6E20C
}
//End AdmRevisioniStoricoAction Tail
