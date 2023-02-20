//Ad4DizionariZoneAslElencoView imports @1-F2D3C4D3
package ad4web.Ad4DizionariZoneAslElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariZoneAslElencoView imports

//Ad4DizionariZoneAslElencoView class @1-0E821C1C
public class Ad4DizionariZoneAslElencoView extends View {
//End Ad4DizionariZoneAslElencoView class

//Ad4DizionariZoneAslElencoView: method show @1-06B4AFAA
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariZoneAslElencoModel) req.getAttribute( "Ad4DizionariZoneAslElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariZoneAslElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView Ad4DizionariGuida = new ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView();
            tmpl.setVar( "main/@Ad4DizionariGuida", Ad4DizionariGuida.show( req, resp, context ));
            page.setCookies();
        }
        ZoneAslFiltroClass ZoneAslFiltro = new ZoneAslFiltroClass();
        ZoneAslFiltro.show(page.getRecord("ZoneAslFiltro"));
        ZoneAslElencoClass ZoneAslElenco = new ZoneAslElencoClass();
        ZoneAslElenco.show(page.getGrid("ZoneAslElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariZoneAslElencoView: method show

//ZoneAslFiltro Record @3-EE29C45E
    final class ZoneAslFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ZoneAslFiltro Record

// //ZoneAslElenco Grid @8-F81417CB

//ZoneAslElencoClass head @8-1F7F4AA2
    final class ZoneAslElencoClass {
//End ZoneAslElencoClass head

// //ZoneAslElenco Grid: method show @8-F81417CB

//show head @8-77A4ACCC
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("CODICE_REGIONE");
            rowControls.add("CODICE_ZONA");
            rowControls.add("TITOLO");
            rowControls.add("REGIONE_DENOMINAZIONE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ZoneAslElenco Grid Tail @8-FCB6E20C
    }
//End ZoneAslElenco Grid Tail

//Ad4DizionariZoneAslElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariZoneAslElencoView Tail

