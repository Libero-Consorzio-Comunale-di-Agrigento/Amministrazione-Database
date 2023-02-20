//AD4StampaVariazioniDiacView imports @1-EEC5A9F7
package ad4web.AD4StampaVariazioniDiac;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4StampaVariazioniDiacView imports

//AD4StampaVariazioniDiacView class @1-9D278376
public class AD4StampaVariazioniDiacView extends View {
//End AD4StampaVariazioniDiacView class

//AD4StampaVariazioniDiacView: method show @1-1266302D
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4StampaVariazioniDiacModel) req.getAttribute( "AD4StampaVariazioniDiacModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4StampaVariazioniDiac.html";
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
//End AD4StampaVariazioniDiacView: method show

//PARAMETRI Record @6-5A152C72
    final class PARAMETRIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End PARAMETRI Record

//AD4StampaVariazioniDiacView Tail @1-FCB6E20C
}
//End AD4StampaVariazioniDiacView Tail

