//Ad4DizionariStatiTerritoriElencoView imports @1-CD9EA3E6
package ad4web.Ad4DizionariStatiTerritoriElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariStatiTerritoriElencoView imports

//Ad4DizionariStatiTerritoriElencoView class @1-2BA24018
public class Ad4DizionariStatiTerritoriElencoView extends View {
//End Ad4DizionariStatiTerritoriElencoView class

//Ad4DizionariStatiTerritoriElencoView: method show @1-BE7B6E72
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariStatiTerritoriElencoModel) req.getAttribute( "Ad4DizionariStatiTerritoriElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariStatiTerritoriElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView Ad4DizionariGuida = new ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView();
            tmpl.setVar( "main/@Ad4DizionariGuida", Ad4DizionariGuida.show( req, resp, context ));
            page.setCookies();
        }
        StatiFiltroClass StatiFiltro = new StatiFiltroClass();
        StatiFiltro.show(page.getRecord("StatiFiltro"));
        StatiElencoClass StatiElenco = new StatiElencoClass();
        StatiElenco.show(page.getGrid("StatiElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariStatiTerritoriElencoView: method show

//StatiFiltro Record @3-3B117C3D
    final class StatiFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End StatiFiltro Record

// //StatiElenco Grid @8-F81417CB

//StatiElencoClass head @8-EC5D08A4
    final class StatiElencoClass {
//End StatiElencoClass head

// //StatiElenco Grid: method show @8-F81417CB

//show head @8-9C2FE56E
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("STATO_TERRITORIO");
            rowControls.add("DENOMINAZIONE");
            rowControls.add("SIGLA");
            rowControls.add("DESC_CITTADINANZA");
            rowControls.add("UTENTE_AGGIORNAMENTO");
            rowControls.add("DATA_AGGIORNAMENTO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//StatiElenco Grid Tail @8-FCB6E20C
    }
//End StatiElenco Grid Tail

//Ad4DizionariStatiTerritoriElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariStatiTerritoriElencoView Tail

