//AD4StampaUtentiView imports @1-D4554181
package ad4web.AD4StampaUtenti;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4StampaUtentiView imports

//AD4StampaUtentiView class @1-5296C554
public class AD4StampaUtentiView extends View {
//End AD4StampaUtentiView class

//AD4StampaUtentiView: method show @1-90D204A8
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4StampaUtentiModel) req.getAttribute( "AD4StampaUtentiModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4StampaUtenti.html";
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
        PARAMETRI_STAMPAClass PARAMETRI_STAMPA = new PARAMETRI_STAMPAClass();
        PARAMETRI_STAMPA.show(page.getRecord("PARAMETRI_STAMPA"));
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
//End AD4StampaUtentiView: method show

//PARAMETRI_STAMPA Record @6-C427F7E5
    final class PARAMETRI_STAMPAClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End PARAMETRI_STAMPA Record

//AD4StampaUtentiView Tail @1-FCB6E20C
}
//End AD4StampaUtentiView Tail
