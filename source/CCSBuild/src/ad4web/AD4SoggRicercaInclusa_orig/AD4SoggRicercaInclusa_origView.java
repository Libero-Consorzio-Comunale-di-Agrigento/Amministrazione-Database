//AD4SoggRicercaInclusa_origView imports @1-BFAF0A63
package ad4web.AD4SoggRicercaInclusa_orig;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4SoggRicercaInclusa_origView imports

//AD4SoggRicercaInclusa_origView class @1-D7F885AE
public class AD4SoggRicercaInclusa_origView extends View {
//End AD4SoggRicercaInclusa_origView class

//AD4SoggRicercaInclusa_origView: method show @1-0BE60E36
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4SoggRicercaInclusa_origModel) req.getAttribute( "AD4SoggRicercaInclusa_origModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4SoggRicercaInclusa_orig.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AD4Soggetti_VSearchClass AD4Soggetti_VSearch = new AD4Soggetti_VSearchClass();
        AD4Soggetti_VSearch.show(page.getRecord("AD4Soggetti_VSearch"));
        AD4SoggettiVClass AD4SoggettiV = new AD4SoggettiVClass();
        AD4SoggettiV.show(page.getGrid("AD4SoggettiV"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4SoggRicercaInclusa_origView: method show

//AD4Soggetti_VSearch Record @6-DA4B3731
    final class AD4Soggetti_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Soggetti_VSearch Record

// //AD4SoggettiV Grid @142-F81417CB

//AD4SoggettiVClass head @142-1AB63615
    final class AD4SoggettiVClass {
//End AD4SoggettiVClass head

// //AD4SoggettiV Grid: method show @142-F81417CB

//show head @142-0097BE25
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("SOGGETTO");
            rowControls.add("SOGGETTO_VIS");
            rowControls.add("DATI");
            rowControls.add("RIPORTA_UTENTE");
            rowControls.add("RIPORTA_ENTE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4SoggettiV Grid Tail @142-FCB6E20C
    }
//End AD4SoggettiV Grid Tail

//AD4SoggRicercaInclusa_origView Tail @1-FCB6E20C
}
//End AD4SoggRicercaInclusa_origView Tail
