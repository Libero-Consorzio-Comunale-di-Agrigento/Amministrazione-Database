//Ad4DizionariSportelliElencoView imports @1-C3A70C04
package restrict.Ad4DizionariSportelliElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariSportelliElencoView imports

//Ad4DizionariSportelliElencoView class @1-14E28C92
public class Ad4DizionariSportelliElencoView extends View {
//End Ad4DizionariSportelliElencoView class

//Ad4DizionariSportelliElencoView: method show @1-5A5A6FD4
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariSportelliElencoModel) req.getAttribute( "Ad4DizionariSportelliElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariSportelliElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Ad4DizionariBancheGuida" ).isVisible() ) {
            restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaView Ad4DizionariBancheGuida = new restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaView();
            tmpl.setVar( "main/@Ad4DizionariBancheGuida", Ad4DizionariBancheGuida.show( req, resp, context ));
            page.setCookies();
        }
        SportelliFiltroClass SportelliFiltro = new SportelliFiltroClass();
        SportelliFiltro.show(page.getRecord("SportelliFiltro"));
        SportelliElencoClass SportelliElenco = new SportelliElencoClass();
        SportelliElenco.show(page.getGrid("SportelliElenco"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariSportelliElencoView: method show

//SportelliFiltro Record @3-422B28B9
    final class SportelliFiltroClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End SportelliFiltro Record

// //SportelliElenco Grid @8-F81417CB

//SportelliElencoClass head @8-7EBC4EE2
    final class SportelliElencoClass {
//End SportelliElencoClass head

// //SportelliElenco Grid: method show @8-F81417CB

//show head @8-8883C575
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("CAB");
            rowControls.add("ABI");
            rowControls.add("CIN_CAB");
            rowControls.add("DESCRIZIONE");
            rowControls.add("INDIRIZZO");
            rowControls.add("LOCALITA");
            rowControls.add("COMUNE");
            rowControls.add("PROVINCIA");
            rowControls.add("CAP");
            rowControls.add("DIPENDENZA");
            rowControls.add("BIC");
            rowControls.add("BANCA");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Aggiungi");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//SportelliElenco Grid Tail @8-FCB6E20C
    }
//End SportelliElenco Grid Tail

//Ad4DizionariSportelliElencoView Tail @1-FCB6E20C
}
//End Ad4DizionariSportelliElencoView Tail
