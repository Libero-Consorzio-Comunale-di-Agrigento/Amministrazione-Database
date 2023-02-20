//AD4SoggettoEsternaView imports @1-B242798A
package ad4web.AD4SoggettoEsterna;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4SoggettoEsternaView imports

//AD4SoggettoEsternaView class @1-8A733FBA
public class AD4SoggettoEsternaView extends View {
//End AD4SoggettoEsternaView class

//AD4SoggettoEsternaView: method show @1-89823975
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4SoggettoEsternaModel) req.getAttribute( "AD4SoggettoEsternaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4SoggettoEsterna.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "AmvStyle" ).isVisible() ) {
            common.AmvStyle.AmvStyleView AmvStyle = new common.AmvStyle.AmvStyleView();
            tmpl.setVar( "main/@AmvStyle", AmvStyle.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "AmvControl" ).isVisible() ) {
            common.AmvControl.AmvControlView AmvControl = new common.AmvControl.AmvControlView();
            tmpl.setVar( "main/@AmvControl", AmvControl.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "AD4SoggettoInclusa" ).isVisible() ) {
            ad4web.AD4SoggettoInclusa.AD4SoggettoInclusaView AD4SoggettoInclusa = new ad4web.AD4SoggettoInclusa.AD4SoggettoInclusaView();
            tmpl.setVar( "main/@AD4SoggettoInclusa", AD4SoggettoInclusa.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4SoggettoEsternaView: method show

//AD4SoggettoEsternaView Tail @1-FCB6E20C
}
//End AD4SoggettoEsternaView Tail
