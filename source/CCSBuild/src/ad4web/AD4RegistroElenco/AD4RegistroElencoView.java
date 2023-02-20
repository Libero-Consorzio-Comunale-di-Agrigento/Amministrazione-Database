//AD4RegistroElencoView imports @1-FED5763E
package ad4web.AD4RegistroElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4RegistroElencoView imports

//AD4RegistroElencoView class @1-08EBB716
public class AD4RegistroElencoView extends View {
//End AD4RegistroElencoView class

//AD4RegistroElencoView: method show @1-F9B45D22
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4RegistroElencoModel) req.getAttribute( "AD4RegistroElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4RegistroElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AD4_REGISTRO_SELClass AD4_REGISTRO_SEL = new AD4_REGISTRO_SELClass();
        AD4_REGISTRO_SEL.show(page.getGrid("AD4_REGISTRO_SEL"));
        ELENCO_CHIAVIClass ELENCO_CHIAVI = new ELENCO_CHIAVIClass();
        ELENCO_CHIAVI.show(page.getGrid("ELENCO_CHIAVI"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4RegistroElencoView: method show

// //AD4_REGISTRO_SEL Grid @10-F81417CB

//AD4_REGISTRO_SELClass head @10-ED07CA43
    final class AD4_REGISTRO_SELClass {
//End AD4_REGISTRO_SELClass head

// //AD4_REGISTRO_SEL Grid: method show @10-F81417CB

//show head @10-1A8CB41B
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOME_SEZIONE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_REGISTRO_SEL Grid Tail @10-FCB6E20C
    }
//End AD4_REGISTRO_SEL Grid Tail

// //ELENCO_CHIAVI Grid @2-F81417CB

//ELENCO_CHIAVIClass head @2-0DA8B7DB
    final class ELENCO_CHIAVIClass {
//End ELENCO_CHIAVIClass head

// //ELENCO_CHIAVI Grid: method show @2-F81417CB

//show head @2-E197867E
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("STRINGA");
            rowControls.add("VALORE");
            rowControls.add("COMMENTO");
            rowControls.add("CHIAVE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ELENCO_CHIAVI Grid Tail @2-FCB6E20C
    }
//End ELENCO_CHIAVI Grid Tail

//AD4RegistroElencoView Tail @1-FCB6E20C
}
//End AD4RegistroElencoView Tail
