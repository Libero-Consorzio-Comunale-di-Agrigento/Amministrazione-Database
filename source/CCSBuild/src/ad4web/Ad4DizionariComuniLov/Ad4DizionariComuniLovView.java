//Ad4DizionariComuniLovView imports @1-6BA71867
package ad4web.Ad4DizionariComuniLov;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariComuniLovView imports

//Ad4DizionariComuniLovView class @1-CF7695CF
public class Ad4DizionariComuniLovView extends View {
//End Ad4DizionariComuniLovView class

//Ad4DizionariComuniLovView: method show @1-D505F381
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariComuniLovModel) req.getAttribute( "Ad4DizionariComuniLovModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariComuniLov.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        page.getControl("CHIUDI").setValue("Chiudi");
        ComuniSearchClass ComuniSearch = new ComuniSearchClass();
        ComuniSearch.show(page.getRecord("ComuniSearch"));
        view.show(page.getLink("CHIUDI"));
        ComuniClass Comuni = new ComuniClass();
        Comuni.show(page.getGrid("Comuni"));
        FocusClass Focus = new FocusClass();
        Focus.show(page.getGrid("Focus"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariComuniLovView: method show

//ComuniSearch Record @2-2E79E1D7
    final class ComuniSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuniSearch Record

// //Comuni Grid @5-F81417CB

//ComuniClass head @5-E6147180
    final class ComuniClass {
//End ComuniClass head

// //Comuni Grid: method show @5-F81417CB

//show head @5-FA7CAED2
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("COMUNE");
            rowControls.add("PROVINCIA");
            rowControls.add("SOPPRESSIONE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Comuni Grid Tail @5-FCB6E20C
    }
//End Comuni Grid Tail

// //Focus Grid @21-F81417CB

//FocusClass head @21-C16EC2B8
    final class FocusClass {
//End FocusClass head

// //Focus Grid: method show @21-F81417CB

//show head @21-34EA4D3E
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("FIELD_FOCUS");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Focus Grid Tail @21-FCB6E20C
    }
//End Focus Grid Tail

//Ad4DizionariComuniLovView Tail @1-FCB6E20C
}
//End Ad4DizionariComuniLovView Tail

