//AD4RuoliRicercaView imports @1-185C46D1
package ad4web.AD4RuoliRicerca;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4RuoliRicercaView imports

//AD4RuoliRicercaView class @1-977D1DFF
public class AD4RuoliRicercaView extends View {
//End AD4RuoliRicercaView class

//AD4RuoliRicercaView: method show @1-8D460042
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4RuoliRicercaModel) req.getAttribute( "AD4RuoliRicercaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4RuoliRicerca.html";
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
        AD4Ruoli_VSearchClass AD4Ruoli_VSearch = new AD4Ruoli_VSearchClass();
        AD4Ruoli_VSearch.show(page.getRecord("AD4Ruoli_VSearch"));
        AD4RuoliVClass AD4RuoliV = new AD4RuoliVClass();
        AD4RuoliV.show(page.getGrid("AD4RuoliV"));
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
//End AD4RuoliRicercaView: method show

//AD4Ruoli_VSearch Record @6-297F3ACE
    final class AD4Ruoli_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Ruoli_VSearch Record

// //AD4RuoliV Grid @142-F81417CB

//AD4RuoliVClass head @142-4E5FAC7B
    final class AD4RuoliVClass {
//End AD4RuoliVClass head

// //AD4RuoliV Grid: method show @142-F81417CB

//show head @142-2CE94389
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("RUOLO");
            rowControls.add("DESCRIZIONE");
            rowControls.add("PROGETTO");
            rowControls.add("MODULO");
            rowControls.add("PROFILO");
            rowControls.add("STATO");
            rowControls.add("GRUPPO_LAVORO");
            rowControls.add("GRUPPO_SO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4RuoliV Grid Tail @142-FCB6E20C
    }
//End AD4RuoliV Grid Tail

//AD4RuoliRicercaView Tail @1-FCB6E20C
}
//End AD4RuoliRicercaView Tail

