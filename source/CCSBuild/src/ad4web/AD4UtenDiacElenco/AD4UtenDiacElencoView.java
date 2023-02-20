//AD4UtenDiacElencoView imports @1-99D70DF0
package ad4web.AD4UtenDiacElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4UtenDiacElencoView imports

//AD4UtenDiacElencoView class @1-79463CD0
public class AD4UtenDiacElencoView extends View {
//End AD4UtenDiacElencoView class

//AD4UtenDiacElencoView: method show @1-CEE0D057
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4UtenDiacElencoModel) req.getAttribute( "AD4UtenDiacElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4UtenDiacElenco.html";
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
        AD4_DIRITTI_ACCESSOSearchClass AD4_DIRITTI_ACCESSOSearch = new AD4_DIRITTI_ACCESSOSearchClass();
        AD4_DIRITTI_ACCESSOSearch.show(page.getRecord("AD4_DIRITTI_ACCESSOSearch"));
        TITOLOClass TITOLO = new TITOLOClass();
        TITOLO.show(page.getGrid("TITOLO"));
        AD4_DIRITTI_ACCESSOClass AD4_DIRITTI_ACCESSO = new AD4_DIRITTI_ACCESSOClass();
        AD4_DIRITTI_ACCESSO.show(page.getGrid("AD4_DIRITTI_ACCESSO"));
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
//End AD4UtenDiacElencoView: method show

//AD4_DIRITTI_ACCESSOSearch Record @151-F5AB240D
    final class AD4_DIRITTI_ACCESSOSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_DIRITTI_ACCESSOSearch Record

// //TITOLO Grid @146-F81417CB

//TITOLOClass head @146-C87ABA54
    final class TITOLOClass {
//End TITOLOClass head

// //TITOLO Grid: method show @146-F81417CB

//show head @146-9CAAEA19
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("TITOLO");
            rowControls.add("INDIETRO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//TITOLO Grid Tail @146-FCB6E20C
    }
//End TITOLO Grid Tail

// //AD4_DIRITTI_ACCESSO Grid @6-F81417CB

//AD4_DIRITTI_ACCESSOClass head @6-C897A615
    final class AD4_DIRITTI_ACCESSOClass {
//End AD4_DIRITTI_ACCESSOClass head

// //AD4_DIRITTI_ACCESSO Grid: method show @6-F81417CB

//show head @6-D69DF64A
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOMINATIVO");
            rowControls.add("SEQUENZA");
            rowControls.add("UTENTE");
            rowControls.add("DES_MODULO");
            rowControls.add("DES_ISTANZA");
            rowControls.add("DATI");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_NOMINATIVO");
            staticControls.add("Sorter_SEQUENZA");
            staticControls.add("Sorter_MODULO");
            staticControls.add("Sorter_ISTANZA");
            staticControls.add("Sorter_DATI");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_DIRITTI_ACCESSO Grid Tail @6-FCB6E20C
    }
//End AD4_DIRITTI_ACCESSO Grid Tail

//AD4UtenDiacElencoView Tail @1-FCB6E20C
}
//End AD4UtenDiacElencoView Tail

