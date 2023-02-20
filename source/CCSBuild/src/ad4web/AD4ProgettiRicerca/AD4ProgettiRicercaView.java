//AD4ProgettiRicercaView imports @1-68753A54
package ad4web.AD4ProgettiRicerca;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4ProgettiRicercaView imports

//AD4ProgettiRicercaView class @1-9A443C6C
public class AD4ProgettiRicercaView extends View {
//End AD4ProgettiRicercaView class

//AD4ProgettiRicercaView: method show @1-07C86DD6
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4ProgettiRicercaModel) req.getAttribute( "AD4ProgettiRicercaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4ProgettiRicerca.html";
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
        AD4Progetti_VSearchClass AD4Progetti_VSearch = new AD4Progetti_VSearchClass();
        AD4Progetti_VSearch.show(page.getRecord("AD4Progetti_VSearch"));
        AD4ProgettiVClass AD4ProgettiV = new AD4ProgettiVClass();
        AD4ProgettiV.show(page.getGrid("AD4ProgettiV"));
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
//End AD4ProgettiRicercaView: method show

//AD4Progetti_VSearch Record @6-65AAC7AE
    final class AD4Progetti_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Progetti_VSearch Record

// //AD4ProgettiV Grid @142-F81417CB

//AD4ProgettiVClass head @142-8080C336
    final class AD4ProgettiVClass {
//End AD4ProgettiVClass head

// //AD4ProgettiV Grid: method show @142-F81417CB

//show head @142-FA42A335
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("PROGETTO");
            rowControls.add("DESCRIZIONE");
            rowControls.add("PRIORITA");
            rowControls.add("NOTE");
            rowControls.add("CaratteristicheAccesso");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4ProgettiV Grid Tail @142-FCB6E20C
    }
//End AD4ProgettiV Grid Tail

//AD4ProgettiRicercaView Tail @1-FCB6E20C
}
//End AD4ProgettiRicercaView Tail

