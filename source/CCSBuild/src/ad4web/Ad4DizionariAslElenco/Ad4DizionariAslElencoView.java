//Ad4DizionariAslElencoView imports @1-3BA7ADE1
package ad4web.Ad4DizionariAslElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariAslElencoView imports

//Ad4DizionariAslElencoView class @1-2EB774C1
public class Ad4DizionariAslElencoView extends View {
//End Ad4DizionariAslElencoView class

//Ad4DizionariAslElencoView: method show @1-75D39722
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariAslElencoModel) req.getAttribute( "Ad4DizionariAslElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariAslElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView Ad4DizionariGuida = new ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView();
            tmpl.setVar( "main/@Ad4DizionariGuida", Ad4DizionariGuida.show( req, resp, context ));
            page.setCookies();
        }
        AslFiltroClass AslFiltro = new AslFiltroClass();
        AslFiltro.show(page.getRecord("AslFiltro"));
        AslElencoClass AslElenco = new AslElencoClass();
        AslElenco.show(page.getGrid("AslElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariAslElencoView: method show

//AslFiltro Record @3-82C974FB
    final class AslFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AslFiltro Record

// //AslElenco Grid @8-F81417CB

//AslElencoClass head @8-EB191C28
    final class AslElencoClass {
//End AslElencoClass head

// //AslElenco Grid: method show @8-F81417CB

//show head @8-8AE0F508
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("REGIONE_ASL");
            rowControls.add("CODICE_ASL");
            rowControls.add("DESCRIZIONE");
            rowControls.add("REGIONE_DENOMINAZIONE");
            rowControls.add("UTENTE_AGGIORNAMENTO");
            rowControls.add("DATA_AGGIORNAMENTO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AslElenco Grid Tail @8-FCB6E20C
    }
//End AslElenco Grid Tail

//Ad4DizionariAslElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariAslElencoView Tail

