//AD4UtentiRicercaView imports @1-A11C565C
package ad4web.AD4UtentiRicerca;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4UtentiRicercaView imports

//AD4UtentiRicercaView class @1-28C808F2
public class AD4UtentiRicercaView extends View {
//End AD4UtentiRicercaView class

//AD4UtentiRicercaView: method show @1-B82BD7C8
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4UtentiRicercaModel) req.getAttribute( "AD4UtentiRicercaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4UtentiRicerca.html";
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
        AD4Utenti_VSearchClass AD4Utenti_VSearch = new AD4Utenti_VSearchClass();
        AD4Utenti_VSearch.show(page.getRecord("AD4Utenti_VSearch"));
        AD4UtentiVClass AD4UtentiV = new AD4UtentiVClass();
        AD4UtentiV.show(page.getGrid("AD4UtentiV"));
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
//End AD4UtentiRicercaView: method show

//AD4Utenti_VSearch Record @6-C8B83DFD
    final class AD4Utenti_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Utenti_VSearch Record

// //AD4UtentiV Grid @142-F81417CB

//AD4UtentiVClass head @142-DAE7437F
    final class AD4UtentiVClass {
//End AD4UtentiVClass head

// //AD4UtentiV Grid: method show @142-F81417CB

//show head @142-1427E3B0
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOMINATIVO");
            rowControls.add("UTENTE");
            rowControls.add("DATI_UTENTE");
            rowControls.add("COPIA_PROFILO");
            rowControls.add("UNIFICA_PROFILO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4UtentiV Grid Tail @142-FCB6E20C
    }
//End AD4UtentiV Grid Tail

//AD4UtentiRicercaView Tail @1-FCB6E20C
}
//End AD4UtentiRicercaView Tail

