//AD4GruppiRicercaView imports @1-63460737
package ad4web.AD4GruppiRicerca;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GruppiRicercaView imports

//AD4GruppiRicercaView class @1-08DF732A
public class AD4GruppiRicercaView extends View {
//End AD4GruppiRicercaView class

//AD4GruppiRicercaView: method show @1-D1F5EA4F
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GruppiRicercaModel) req.getAttribute( "AD4GruppiRicercaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4GruppiRicerca.html";
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
        AD4Gruppi_VSearchClass AD4Gruppi_VSearch = new AD4Gruppi_VSearchClass();
        AD4Gruppi_VSearch.show(page.getRecord("AD4Gruppi_VSearch"));
        AD4GruppiVClass AD4GruppiV = new AD4GruppiVClass();
        AD4GruppiV.show(page.getGrid("AD4GruppiV"));
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
//End AD4GruppiRicercaView: method show

//AD4Gruppi_VSearch Record @6-C56D4EE3
    final class AD4Gruppi_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Gruppi_VSearch Record

// //AD4GruppiV Grid @142-F81417CB

//AD4GruppiVClass head @142-2BFCD192
    final class AD4GruppiVClass {
//End AD4GruppiVClass head

// //AD4GruppiV Grid: method show @142-F81417CB

//show head @142-3BD0E8AC
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("GRUPPO");
            rowControls.add("DESCRIZIONE");
            rowControls.add("NOTE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4GruppiV Grid Tail @142-FCB6E20C
    }
//End AD4GruppiV Grid Tail

//AD4GruppiRicercaView Tail @1-FCB6E20C
}
//End AD4GruppiRicercaView Tail

