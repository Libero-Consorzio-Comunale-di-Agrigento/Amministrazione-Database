//AD4IstaElencoView imports @1-39A3B58B
package ad4web.AD4IstaElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4IstaElencoView imports

//AD4IstaElencoView class @1-ED616AA3
public class AD4IstaElencoView extends View {
//End AD4IstaElencoView class

//AD4IstaElencoView: method show @1-16776693
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4IstaElencoModel) req.getAttribute( "AD4IstaElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4IstaElenco.html";
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
        AD4Istanze_VSearchClass AD4Istanze_VSearch = new AD4Istanze_VSearchClass();
        AD4Istanze_VSearch.show(page.getRecord("AD4Istanze_VSearch"));
        PROGETTIClass PROGETTI = new PROGETTIClass();
        PROGETTI.show(page.getGrid("PROGETTI"));
        AD4_ISTANZEClass AD4_ISTANZE = new AD4_ISTANZEClass();
        AD4_ISTANZE.show(page.getGrid("AD4_ISTANZE"));
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
//End AD4IstaElencoView: method show

//AD4Istanze_VSearch Record @175-311C143F
    final class AD4Istanze_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Istanze_VSearch Record

// //PROGETTI Grid @135-F81417CB

//PROGETTIClass head @135-9FF817BE
    final class PROGETTIClass {
//End PROGETTIClass head

// //PROGETTI Grid: method show @135-F81417CB

//show head @135-A97D9AFF
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DESC_PROGETTO");
            rowControls.add("Nuovo");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//PROGETTI Grid Tail @135-FCB6E20C
    }
//End PROGETTI Grid Tail

// //AD4_ISTANZE Grid @6-F81417CB

//AD4_ISTANZEClass head @6-1690FB4C
    final class AD4_ISTANZEClass {
//End AD4_ISTANZEClass head

// //AD4_ISTANZE Grid: method show @6-F81417CB

//show head @6-0ECB8A84
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DESCRIZIONE");
            rowControls.add("DATI");
            rowControls.add("CaratteristicheAccesso");
            rowControls.add("Abilitazioni");
            rowControls.add("Registro");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_ISTANZA");
            staticControls.add("Sorter_DESCRIZIONE");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_ISTANZE Grid Tail @6-FCB6E20C
    }
//End AD4_ISTANZE Grid Tail

//AD4IstaElencoView Tail @1-FCB6E20C
}
//End AD4IstaElencoView Tail

