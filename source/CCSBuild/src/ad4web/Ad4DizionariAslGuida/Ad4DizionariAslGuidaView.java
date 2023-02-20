//Ad4DizionariAslGuidaView imports @1-3DF2C0D3
package ad4web.Ad4DizionariAslGuida;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariAslGuidaView imports

//Ad4DizionariAslGuidaView class @1-86012959
public class Ad4DizionariAslGuidaView extends View {
//End Ad4DizionariAslGuidaView class

//Ad4DizionariAslGuidaView: method show @1-C0797ABA
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariAslGuidaModel) req.getAttribute( "Ad4DizionariAslGuidaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariAslGuida.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        GuidaClass Guida = new GuidaClass();
        Guida.show(page.getGrid("Guida"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariAslGuidaView: method show

// //Guida Grid @2-F81417CB

//GuidaClass head @2-E5123A95
    final class GuidaClass {
//End GuidaClass head

// //Guida Grid: method show @2-F81417CB

//show head @2-4112FD3D
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("FOLDER1");
            rowControls.add("FOLDER2");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Guida Grid Tail @2-FCB6E20C
    }
//End Guida Grid Tail

//Ad4DizionariAslGuidaView Tail @1-FCB6E20C
}
//End Ad4DizionariAslGuidaView Tail

