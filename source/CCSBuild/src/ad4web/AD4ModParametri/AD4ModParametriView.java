//AD4ModParametriView imports @1-0F415C2F
package ad4web.AD4ModParametri;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4ModParametriView imports

//AD4ModParametriView class @1-5CC25891
public class AD4ModParametriView extends View {
//End AD4ModParametriView class

//AD4ModParametriView: method show @1-7B8CECD8
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4ModParametriModel) req.getAttribute( "AD4ModParametriModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4ModParametri.html";
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
        AD4_PARAMETROClass AD4_PARAMETRO = new AD4_PARAMETROClass();
        AD4_PARAMETRO.show(page.getRecord("AD4_PARAMETRO"));
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
//End AD4ModParametriView: method show

//AD4_PARAMETRO Record @23-56E0E780
    final class AD4_PARAMETROClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_PARAMETRO Record

//AD4ModParametriView Tail @1-FCB6E20C
}
//End AD4ModParametriView Tail
