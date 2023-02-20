//AD4ServElencoView imports @1-00264B9D
package ad4web.AD4ServElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4ServElencoView imports

//AD4ServElencoView class @1-F36A5072
public class AD4ServElencoView extends View {
//End AD4ServElencoView class

//AD4ServElencoView: method show @1-671F90C7
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4ServElencoModel) req.getAttribute( "AD4ServElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4ServElenco.html";
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
        PROGETTOClass PROGETTO = new PROGETTOClass();
        PROGETTO.show(page.getGrid("PROGETTO"));
        AD4_SERVIZIClass AD4_SERVIZI = new AD4_SERVIZIClass();
        AD4_SERVIZI.show(page.getGrid("AD4_SERVIZI"));
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
//End AD4ServElencoView: method show

// //PROGETTO Grid @160-F81417CB

//PROGETTOClass head @160-FC282284
    final class PROGETTOClass {
//End PROGETTOClass head

// //PROGETTO Grid: method show @160-F81417CB

//show head @160-46FAB7B3
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DESC_PROGETTO");
            rowControls.add("AD4_DIRITTI_ACCESSO_Insert");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//PROGETTO Grid Tail @160-FCB6E20C
    }
//End PROGETTO Grid Tail

// //AD4_SERVIZI Grid @6-F81417CB

//AD4_SERVIZIClass head @6-F5EB24C3
    final class AD4_SERVIZIClass {
//End AD4_SERVIZIClass head

// //AD4_SERVIZI Grid: method show @6-F81417CB

//show head @6-4547A93F
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("ID_SERVIZIO");
            rowControls.add("DES_MODULO");
            rowControls.add("DES_ISTANZA");
            rowControls.add("DATI");
            rowControls.add("Abilitazioni");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_SEQUENZA");
            staticControls.add("Sorter_MODULO");
            staticControls.add("Sorter_ISTANZA");
            staticControls.add("Sorter_RUOLO");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_SERVIZI Grid Tail @6-FCB6E20C
    }
//End AD4_SERVIZI Grid Tail

//AD4ServElencoView Tail @1-FCB6E20C
}
//End AD4ServElencoView Tail

