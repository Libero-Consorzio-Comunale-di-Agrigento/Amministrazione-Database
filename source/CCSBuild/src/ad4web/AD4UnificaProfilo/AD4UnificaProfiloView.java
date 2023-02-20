//AD4UnificaProfiloView imports @1-58CB984D
package ad4web.AD4UnificaProfilo;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4UnificaProfiloView imports

//AD4UnificaProfiloView class @1-AC8AA1A6
public class AD4UnificaProfiloView extends View {
//End AD4UnificaProfiloView class

//AD4UnificaProfiloView: method show @1-241558F9
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4UnificaProfiloModel) req.getAttribute( "AD4UnificaProfiloModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4UnificaProfilo.html";
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
        AD4_UTENTE_ALIMENTAREClass AD4_UTENTE_ALIMENTARE = new AD4_UTENTE_ALIMENTAREClass();
        AD4_UTENTE_ALIMENTARE.show(page.getRecord("AD4_UTENTE_ALIMENTARE"));
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
//End AD4UnificaProfiloView: method show

//AD4_UTENTE_ALIMENTARE Record @6-E524F7E4
    final class AD4_UTENTE_ALIMENTAREClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_UTENTE_ALIMENTARE Record

//AD4UnificaProfiloView Tail @1-FCB6E20C
}
//End AD4UnificaProfiloView Tail
