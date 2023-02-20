//AD4SoggRicercaEsternaView imports @1-77281D85
package ad4web.AD4SoggRicercaEsterna;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4SoggRicercaEsternaView imports

//AD4SoggRicercaEsternaView class @1-03BD4076
public class AD4SoggRicercaEsternaView extends View {
//End AD4SoggRicercaEsternaView class

//AD4SoggRicercaEsternaView: method show @1-3B055478
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4SoggRicercaEsternaModel) req.getAttribute( "AD4SoggRicercaEsternaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4SoggRicercaEsterna.html";
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
        if ( page.getChild( "AD4SoggRicercaInclusa" ).isVisible() ) {
            ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaView AD4SoggRicercaInclusa = new ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaView();
            tmpl.setVar( "main/@AD4SoggRicercaInclusa", AD4SoggRicercaInclusa.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4SoggRicercaEsternaView: method show

//AD4SoggRicercaEsternaView Tail @1-FCB6E20C
}
//End AD4SoggRicercaEsternaView Tail
