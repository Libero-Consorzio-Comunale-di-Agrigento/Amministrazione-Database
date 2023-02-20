//Ad4DizionariZoneAslComuniElencoView imports @1-BCB146A8
package ad4web.Ad4DizionariZoneAslComuniElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariZoneAslComuniElencoView imports

//Ad4DizionariZoneAslComuniElencoView class @1-A012F94F
public class Ad4DizionariZoneAslComuniElencoView extends View {
//End Ad4DizionariZoneAslComuniElencoView class

//Ad4DizionariZoneAslComuniElencoView: method show @1-4CFEA2B6
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariZoneAslComuniElencoModel) req.getAttribute( "Ad4DizionariZoneAslComuniElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariZoneAslComuniElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ComuniFiltroClass ComuniFiltro = new ComuniFiltroClass();
        ComuniFiltro.show(page.getRecord("ComuniFiltro"));
        TitoloClass Titolo = new TitoloClass();
        Titolo.show(page.getGrid("Titolo"));
        ComuniElencoClass ComuniElenco = new ComuniElencoClass();
        ComuniElenco.show(page.getGrid("ComuniElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariZoneAslComuniElencoView: method show

//ComuniFiltro Record @3-8F32950A
    final class ComuniFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuniFiltro Record

// //Titolo Grid @40-F81417CB

//TitoloClass head @40-B581EF3A
    final class TitoloClass {
//End TitoloClass head

// //Titolo Grid: method show @40-F81417CB

//show head @40-1527DDFA
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("TITOLO");
            rowControls.add("Aggiungi");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Titolo Grid Tail @40-FCB6E20C
    }
//End Titolo Grid Tail

// //ComuniElenco Grid @8-F81417CB

//ComuniElencoClass head @8-8E411FF4
    final class ComuniElencoClass {
//End ComuniElencoClass head

// //ComuniElenco Grid: method show @8-F81417CB

//show head @8-ED1D5524
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("COMUNE_DESC");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ComuniElenco Grid Tail @8-FCB6E20C
    }
//End ComuniElenco Grid Tail

//Ad4DizionariZoneAslComuniElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariZoneAslComuniElencoView Tail

