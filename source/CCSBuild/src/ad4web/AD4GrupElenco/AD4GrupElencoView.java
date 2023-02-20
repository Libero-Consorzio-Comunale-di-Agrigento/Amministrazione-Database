//AD4GrupElencoView imports @1-849671B4
package ad4web.AD4GrupElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GrupElencoView imports

//AD4GrupElencoView class @1-3939031F
public class AD4GrupElencoView extends View {
//End AD4GrupElencoView class

//AD4GrupElencoView: method show @1-F2458CCB
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GrupElencoModel) req.getAttribute( "AD4GrupElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4GrupElenco.html";
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
        DIRITTI_ACCESSOClass DIRITTI_ACCESSO = new DIRITTI_ACCESSOClass();
        DIRITTI_ACCESSO.show(page.getGrid("DIRITTI_ACCESSO"));
        AD4_GRUPPIClass AD4_GRUPPI = new AD4_GRUPPIClass();
        AD4_GRUPPI.show(page.getGrid("AD4_GRUPPI"));
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
//End AD4GrupElencoView: method show

// //DIRITTI_ACCESSO Grid @146-F81417CB

//DIRITTI_ACCESSOClass head @146-06B789C4
    final class DIRITTI_ACCESSOClass {
//End DIRITTI_ACCESSOClass head

// //DIRITTI_ACCESSO Grid: method show @146-F81417CB

//show head @146-0933EF3A
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOMINATIVO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//DIRITTI_ACCESSO Grid Tail @146-FCB6E20C
    }
//End DIRITTI_ACCESSO Grid Tail

// //AD4_GRUPPI Grid @6-F81417CB

//AD4_GRUPPIClass head @6-C6449123
    final class AD4_GRUPPIClass {
//End AD4_GRUPPIClass head

// //AD4_GRUPPI Grid: method show @6-F81417CB

//show head @6-5E24B950
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("GRUPPO");
            rowControls.add("UTENTE");
            rowControls.add("DESCRIZIONE");
            rowControls.add("IMPORTANZA");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_GRUPPO");
            staticControls.add("Sorter_DESCRIZIONE");
            staticControls.add("Sorter_ISTANZA");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_GRUPPI Grid Tail @6-FCB6E20C
    }
//End AD4_GRUPPI Grid Tail

//AD4GrupElencoView Tail @1-FCB6E20C
}
//End AD4GrupElencoView Tail
