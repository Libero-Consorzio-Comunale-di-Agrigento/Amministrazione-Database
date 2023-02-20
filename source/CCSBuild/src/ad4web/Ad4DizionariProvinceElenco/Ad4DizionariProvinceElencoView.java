//Ad4DizionariProvinceElencoView imports @1-3460398F
package ad4web.Ad4DizionariProvinceElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariProvinceElencoView imports

//Ad4DizionariProvinceElencoView class @1-7827D26D
public class Ad4DizionariProvinceElencoView extends View {
//End Ad4DizionariProvinceElencoView class

//Ad4DizionariProvinceElencoView: method show @1-2644B5DC
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariProvinceElencoModel) req.getAttribute( "Ad4DizionariProvinceElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariProvinceElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView Ad4DizionariGuida = new ad4web.Ad4DizionariGuida.Ad4DizionariGuidaView();
            tmpl.setVar( "main/@Ad4DizionariGuida", Ad4DizionariGuida.show( req, resp, context ));
            page.setCookies();
        }
        ProvinceFiltroClass ProvinceFiltro = new ProvinceFiltroClass();
        ProvinceFiltro.show(page.getRecord("ProvinceFiltro"));
        ProvinceElencoClass ProvinceElenco = new ProvinceElencoClass();
        ProvinceElenco.show(page.getGrid("ProvinceElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariProvinceElencoView: method show

//ProvinceFiltro Record @3-E595BA71
    final class ProvinceFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ProvinceFiltro Record

// //ProvinceElenco Grid @8-F81417CB

//ProvinceElencoClass head @8-58369073
    final class ProvinceElencoClass {
//End ProvinceElencoClass head

// //ProvinceElenco Grid: method show @8-F81417CB

//show head @8-02644911
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("PROVINCIA");
            rowControls.add("LINK_COMUNI");
            rowControls.add("DENOMINAZIONE");
            rowControls.add("SIGLA");
            rowControls.add("REGIONE");
            rowControls.add("UTENTE_AGGIORNAMENTO");
            rowControls.add("DATA_AGGIORNAMENTO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ProvinceElenco Grid Tail @8-FCB6E20C
    }
//End ProvinceElenco Grid Tail

//Ad4DizionariProvinceElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariProvinceElencoView Tail

