//AD4DiacElencoSTEView imports @1-CE5C05FF
package ad4web.AD4DiacElencoSTE;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4DiacElencoSTEView imports

//AD4DiacElencoSTEView class @1-F90199C7
public class AD4DiacElencoSTEView extends View {
//End AD4DiacElencoSTEView class

//AD4DiacElencoSTEView: method show @1-B6DA1352
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4DiacElencoSTEModel) req.getAttribute( "AD4DiacElencoSTEModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4DiacElencoSTE.html";
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
//End AD4DiacElencoSTEView: method show

// //DIRITTI_ACCESSO Grid @146-F81417CB

//DIRITTI_ACCESSOClass head @146-06B789C4
    final class DIRITTI_ACCESSOClass {
//End DIRITTI_ACCESSOClass head

// //DIRITTI_ACCESSO Grid: method show @146-F81417CB

//show head @146-F794DC2E
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOMINATIVO");
            rowControls.add("Nuovo");
            rowControls.add("Copia");
            rowControls.add("Unifica");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//DIRITTI_ACCESSO Grid Tail @146-FCB6E20C
    }
//End DIRITTI_ACCESSO Grid Tail

// //AD4_DIRITTI_ACCESSO Grid @6-F81417CB

//AD4_DIRITTI_ACCESSOClass head @6-C897A615
    final class AD4_DIRITTI_ACCESSOClass {
//End AD4_DIRITTI_ACCESSOClass head

// //AD4_DIRITTI_ACCESSO Grid: method show @6-F81417CB

//show head @6-504F270D
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("SEQUENZA");
            rowControls.add("UTENTE");
            rowControls.add("DES_MODULO");
            rowControls.add("DES_ISTANZA");
            rowControls.add("DATI");
            rowControls.add("CaratteristicheAccesso");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
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

//AD4DiacElencoSTEView Tail @1-FCB6E20C
}
//End AD4DiacElencoSTEView Tail
