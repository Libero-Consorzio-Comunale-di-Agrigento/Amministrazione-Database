//Ad4DizionariZonaAslImpostaView imports @1-60C472B7
package ad4web.Ad4DizionariZonaAslImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariZonaAslImpostaView imports

//Ad4DizionariZonaAslImpostaView class @1-08DCF126
public class Ad4DizionariZonaAslImpostaView extends View {
//End Ad4DizionariZonaAslImpostaView class

//Ad4DizionariZonaAslImpostaView: method show @1-F7F2A200
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariZonaAslImpostaModel) req.getAttribute( "Ad4DizionariZonaAslImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariZonaAslImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        ZonaAslImpostaClass ZonaAslImposta = new ZonaAslImpostaClass();
        ZonaAslImposta.show(page.getRecord("ZonaAslImposta"));
        if ( page.getChild( "Ad4DizionariZoneAslComuniElenco" ).isVisible() ) {
            ad4web.Ad4DizionariZoneAslComuniElenco.Ad4DizionariZoneAslComuniElencoView Ad4DizionariZoneAslComuniElenco = new ad4web.Ad4DizionariZoneAslComuniElenco.Ad4DizionariZoneAslComuniElencoView();
            tmpl.setVar( "main/@Ad4DizionariZoneAslComuniElenco", Ad4DizionariZoneAslComuniElenco.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariZonaAslImpostaView: method show

// //AttributoHeader Grid @2-F81417CB

//AttributoHeaderClass head @2-A08D40B7
    final class AttributoHeaderClass {
//End AttributoHeaderClass head

// //AttributoHeader Grid: method show @2-F81417CB

//show head @2-E3608B72
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("Indietro");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AttributoHeader Grid Tail @2-FCB6E20C
    }
//End AttributoHeader Grid Tail

//ZonaAslImposta Record @5-A1A84E0C
    final class ZonaAslImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ZonaAslImposta Record

//Ad4DizionariZonaAslImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariZonaAslImpostaView Tail

