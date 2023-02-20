//AD4SoggRicercaView imports @1-E8A69868
package ad4web.AD4SoggRicerca;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4SoggRicercaView imports

//AD4SoggRicercaView class @1-2F693660
public class AD4SoggRicercaView extends View {
//End AD4SoggRicercaView class

//AD4SoggRicercaView: method show @1-DBE6E060
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4SoggRicercaModel) req.getAttribute( "AD4SoggRicercaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4SoggRicerca.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Header" ).isVisible() ) {
            common.Header.HeaderView Header = new common.Header.HeaderView();
            tmpl.setVar( "main/@Header", Header.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "Left" ).isVisible() ) {
            common.Left.LeftView Left = new common.Left.LeftView();
            tmpl.setVar( "main/@Left", Left.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "Guida" ).isVisible() ) {
            common.Guida.GuidaView Guida = new common.Guida.GuidaView();
            tmpl.setVar( "main/@Guida", Guida.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "AD4SoggRicercaInclusa" ).isVisible() ) {
            ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaView AD4SoggRicercaInclusa = new ad4web.AD4SoggRicercaInclusa.AD4SoggRicercaInclusaView();
            tmpl.setVar( "main/@AD4SoggRicercaInclusa", AD4SoggRicercaInclusa.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "Footer" ).isVisible() ) {
            common.Footer.FooterView Footer = new common.Footer.FooterView();
            tmpl.setVar( "main/@Footer", Footer.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4SoggRicercaView: method show

//AD4SoggRicercaView Tail @1-FCB6E20C
}
//End AD4SoggRicercaView Tail
