//AD4StringaView imports @1-B58FE792
package ad4web.AD4Stringa;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4StringaView imports

//AD4StringaView class @1-0E899D9B
public class AD4StringaView extends View {
//End AD4StringaView class

//AD4StringaView: method show @1-9AE1A08A
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4StringaModel) req.getAttribute( "AD4StringaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4Stringa.html";
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
        AD4_STRINGHEClass AD4_STRINGHE = new AD4_STRINGHEClass();
        AD4_STRINGHE.show(page.getRecord("AD4_STRINGHE"));
        AD4_CHIAVIClass AD4_CHIAVI = new AD4_CHIAVIClass();
        AD4_CHIAVI.show(page.getRecord("AD4_CHIAVI"));
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
//End AD4StringaView: method show

//AD4_STRINGHE Record @23-A8942D03
    final class AD4_STRINGHEClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_STRINGHE Record

//AD4_CHIAVI Record @99-BD0CC966
    final class AD4_CHIAVIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_CHIAVI Record

//AD4StringaView Tail @1-FCB6E20C
}
//End AD4StringaView Tail
