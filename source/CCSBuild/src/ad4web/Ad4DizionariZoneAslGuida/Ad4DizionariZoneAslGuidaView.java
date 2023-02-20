//Ad4DizionariZoneAslGuidaView imports @1-45BB74A0
package ad4web.Ad4DizionariZoneAslGuida;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariZoneAslGuidaView imports

//Ad4DizionariZoneAslGuidaView class @1-DCEC472C
public class Ad4DizionariZoneAslGuidaView extends View {
//End Ad4DizionariZoneAslGuidaView class

//Ad4DizionariZoneAslGuidaView: method show @1-419FF441
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariZoneAslGuidaModel) req.getAttribute( "Ad4DizionariZoneAslGuidaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariZoneAslGuida.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        GuidaClass Guida = new GuidaClass();
        Guida.show(page.getGrid("Guida"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariZoneAslGuidaView: method show

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

//Ad4DizionariZoneAslGuidaView Tail @1-FCB6E20C
}
//End Ad4DizionariZoneAslGuidaView Tail

