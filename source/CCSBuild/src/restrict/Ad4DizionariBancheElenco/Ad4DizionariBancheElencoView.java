//Ad4DizionariBancheElencoView imports @1-95DDBBB0
package restrict.Ad4DizionariBancheElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariBancheElencoView imports

//Ad4DizionariBancheElencoView class @1-7B7866F8
public class Ad4DizionariBancheElencoView extends View {
//End Ad4DizionariBancheElencoView class

//Ad4DizionariBancheElencoView: method show @1-19046D17
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariBancheElencoModel) req.getAttribute( "Ad4DizionariBancheElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariBancheElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariBancheGuida" ).isVisible() ) {
            restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaView Ad4DizionariBancheGuida = new restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaView();
            tmpl.setVar( "main/@Ad4DizionariBancheGuida", Ad4DizionariBancheGuida.show( req, resp, context ));
            page.setCookies();
        }
        BancheFiltroClass BancheFiltro = new BancheFiltroClass();
        BancheFiltro.show(page.getRecord("BancheFiltro"));
        BancheElencoClass BancheElenco = new BancheElencoClass();
        BancheElenco.show(page.getGrid("BancheElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariBancheElencoView: method show

//BancheFiltro Record @3-E840B080
    final class BancheFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End BancheFiltro Record

// //BancheElenco Grid @8-F81417CB

//BancheElencoClass head @8-0D1F5C81
    final class BancheElencoClass {
//End BancheElencoClass head

// //BancheElenco Grid: method show @8-F81417CB

//show head @8-EC4748C3
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("ABI");
            rowControls.add("LINK_SPORTELLI");
            rowControls.add("DENOMINAZIONE");
            rowControls.add("CIN_ABI");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//BancheElenco Grid Tail @8-FCB6E20C
    }
//End BancheElenco Grid Tail

//Ad4DizionariBancheElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariBancheElencoView Tail
