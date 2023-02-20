//AD4ModuElencoView imports @1-EE81335B
package ad4web.AD4ModuElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4ModuElencoView imports

//AD4ModuElencoView class @1-36115ABD
public class AD4ModuElencoView extends View {
//End AD4ModuElencoView class

//AD4ModuElencoView: method show @1-7C8EB41A
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4ModuElencoModel) req.getAttribute( "AD4ModuElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4ModuElenco.html";
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
        PROGETTIClass PROGETTI = new PROGETTIClass();
        PROGETTI.show(page.getGrid("PROGETTI"));
        AD4_MODULIClass AD4_MODULI = new AD4_MODULIClass();
        AD4_MODULI.show(page.getGrid("AD4_MODULI"));
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
//End AD4ModuElencoView: method show

// //PROGETTI Grid @62-F81417CB

//PROGETTIClass head @62-9FF817BE
    final class PROGETTIClass {
//End PROGETTIClass head

// //PROGETTI Grid: method show @62-F81417CB

//show head @62-A97D9AFF
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DESC_PROGETTO");
            rowControls.add("Nuovo");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//PROGETTI Grid Tail @62-FCB6E20C
    }
//End PROGETTI Grid Tail

// //AD4_MODULI Grid @6-F81417CB

//AD4_MODULIClass head @6-E0FE7B73
    final class AD4_MODULIClass {
//End AD4_MODULIClass head

// //AD4_MODULI Grid: method show @6-F81417CB

//show head @6-DAD182F6
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("MODULO");
            rowControls.add("PROGETTO");
            rowControls.add("DESCRIZIONE");
            rowControls.add("AMMINISTRATORE");
            rowControls.add("NOTE");
            rowControls.add("CaratteristicheAccesso");
            rowControls.add("Abilitazioni");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_MODULO");
            staticControls.add("Sorter_DESCRIZIONE");
            staticControls.add("Sorter_AMMINISTRATORE");
            staticControls.add("Sorter_NOTE");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_MODULI Grid Tail @6-FCB6E20C
    }
//End AD4_MODULI Grid Tail

//AD4ModuElencoView Tail @1-FCB6E20C
}
//End AD4ModuElencoView Tail

