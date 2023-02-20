//AD4DirittoAccesso2View imports @1-C319B964
package ad4web.AD4DirittoAccesso2;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4DirittoAccesso2View imports

//AD4DirittoAccesso2View class @1-459B9264
public class AD4DirittoAccesso2View extends View {
//End AD4DirittoAccesso2View class

//AD4DirittoAccesso2View: method show @1-BEB6E951
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4DirittoAccesso2Model) req.getAttribute( "AD4DirittoAccesso2Model" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4DirittoAccesso2.html";
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
        AD4_DIRITTI_ACCESSO1Class AD4_DIRITTI_ACCESSO1 = new AD4_DIRITTI_ACCESSO1Class();
        AD4_DIRITTI_ACCESSO1.show(page.getRecord("AD4_DIRITTI_ACCESSO1"));
        if ( page.getChild( "Footer" ).isVisible() ) {
            common.Footer.FooterView Footer = new common.Footer.FooterView();
            tmpl.setVar( "main/@Footer", Footer.show( req, resp, context ));
            page.setCookies();
        }
        view.show(page.getControl("JS_REFRESH_SLAVES"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4DirittoAccesso2View: method show

//AD4_DIRITTI_ACCESSO1 Record @38-D30B9E7D
    final class AD4_DIRITTI_ACCESSO1Class {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_DIRITTI_ACCESSO1 Record

//AD4DirittoAccesso2View Tail @1-FCB6E20C
}
//End AD4DirittoAccesso2View Tail
