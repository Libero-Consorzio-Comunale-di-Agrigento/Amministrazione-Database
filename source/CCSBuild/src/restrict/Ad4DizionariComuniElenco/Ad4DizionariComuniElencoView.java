//Ad4DizionariComuniElencoView imports @1-7F323904
package restrict.Ad4DizionariComuniElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariComuniElencoView imports

//Ad4DizionariComuniElencoView class @1-7555067D
public class Ad4DizionariComuniElencoView extends View {
//End Ad4DizionariComuniElencoView class

//Ad4DizionariComuniElencoView: method show @1-5CC17679
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariComuniElencoModel) req.getAttribute( "Ad4DizionariComuniElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariComuniElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            restrict.Ad4DizionariGuida.Ad4DizionariGuidaView Ad4DizionariGuida = new restrict.Ad4DizionariGuida.Ad4DizionariGuidaView();
            tmpl.setVar( "main/@Ad4DizionariGuida", Ad4DizionariGuida.show( req, resp, context ));
            page.setCookies();
        }
        ComuniFiltroClass ComuniFiltro = new ComuniFiltroClass();
        ComuniFiltro.show(page.getRecord("ComuniFiltro"));
        ComuniElencoClass ComuniElenco = new ComuniElencoClass();
        ComuniElenco.show(page.getGrid("ComuniElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariComuniElencoView: method show

//ComuniFiltro Record @3-8F32950A
    final class ComuniFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuniFiltro Record

// //ComuniElenco Grid @8-F81417CB

//ComuniElencoClass head @8-8E411FF4
    final class ComuniElencoClass {
//End ComuniElencoClass head

// //ComuniElenco Grid: method show @8-F81417CB

//show head @8-C30A1D26
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("PROVINCIA_STATO");
            rowControls.add("COMUNE");
            rowControls.add("DENOMINAZIONE");
            rowControls.add("CAP");
            rowControls.add("PROVINCIA_DESC");
            rowControls.add("SOPPRESSIONED_DATA");
            rowControls.add("FUSIONE_DESC");
            rowControls.add("UTENTE_AGGIORNAMENTO");
            rowControls.add("DATA_AGGIORNAMENTO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ComuniElenco Grid Tail @8-FCB6E20C
    }
//End ComuniElenco Grid Tail

//Ad4DizionariComuniElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariComuniElencoView Tail

