//AD4StampaVariazioniUtentiView imports @1-AFADD5A7
package ad4web.AD4StampaVariazioniUtenti;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4StampaVariazioniUtentiView imports

//AD4StampaVariazioniUtentiView class @1-2E738D83
public class AD4StampaVariazioniUtentiView extends View {
//End AD4StampaVariazioniUtentiView class

//AD4StampaVariazioniUtentiView: method show @1-4695B24C
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4StampaVariazioniUtentiModel) req.getAttribute( "AD4StampaVariazioniUtentiModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4StampaVariazioniUtenti.html";
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
        PARAMETRIClass PARAMETRI = new PARAMETRIClass();
        PARAMETRI.show(page.getRecord("PARAMETRI"));
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
//End AD4StampaVariazioniUtentiView: method show

//PARAMETRI Record @6-5A152C72
    final class PARAMETRIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End PARAMETRI Record

//AD4StampaVariazioniUtentiView Tail @1-FCB6E20C
}
//End AD4StampaVariazioniUtentiView Tail
