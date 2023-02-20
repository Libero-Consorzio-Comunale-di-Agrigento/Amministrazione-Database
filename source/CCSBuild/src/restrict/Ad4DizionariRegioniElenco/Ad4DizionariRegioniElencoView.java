//Ad4DizionariRegioniElencoView imports @1-5D1439D3
package restrict.Ad4DizionariRegioniElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariRegioniElencoView imports

//Ad4DizionariRegioniElencoView class @1-54BDFFB4
public class Ad4DizionariRegioniElencoView extends View {
//End Ad4DizionariRegioniElencoView class

//Ad4DizionariRegioniElencoView: method show @1-531C27B9
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariRegioniElencoModel) req.getAttribute( "Ad4DizionariRegioniElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariRegioniElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            restrict.Ad4DizionariGuida.Ad4DizionariGuidaView Ad4DizionariGuida = new restrict.Ad4DizionariGuida.Ad4DizionariGuidaView();
            tmpl.setVar( "main/@Ad4DizionariGuida", Ad4DizionariGuida.show( req, resp, context ));
            page.setCookies();
        }
        RegioniFiltroClass RegioniFiltro = new RegioniFiltroClass();
        RegioniFiltro.show(page.getRecord("RegioniFiltro"));
        RegioniElencoClass RegioniElenco = new RegioniElencoClass();
        RegioniElenco.show(page.getGrid("RegioniElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariRegioniElencoView: method show

//RegioniFiltro Record @3-DE034928
    final class RegioniFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End RegioniFiltro Record

// //RegioniElenco Grid @8-F81417CB

//RegioniElencoClass head @8-6B1E1C72
    final class RegioniElencoClass {
//End RegioniElencoClass head

// //RegioniElenco Grid: method show @8-F81417CB

//show head @8-E9996EF4
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("REGIONE");
            rowControls.add("LINK_PROVINCE");
            rowControls.add("DENOMINAZIONE");
            rowControls.add("ID_REGIONE");
            rowControls.add("UTENTE_AGGIORNAMENTO");
            rowControls.add("DATA_AGGIORNAMENTO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//RegioniElenco Grid Tail @8-FCB6E20C
    }
//End RegioniElenco Grid Tail

//Ad4DizionariRegioniElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariRegioniElencoView Tail

