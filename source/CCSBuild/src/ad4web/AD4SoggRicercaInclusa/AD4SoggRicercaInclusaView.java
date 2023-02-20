//AD4SoggRicercaInclusaView imports @1-A7F86565
package ad4web.AD4SoggRicercaInclusa;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4SoggRicercaInclusaView imports

//AD4SoggRicercaInclusaView class @1-15EC58BA
public class AD4SoggRicercaInclusaView extends View {
//End AD4SoggRicercaInclusaView class

//AD4SoggRicercaInclusaView: method show @1-18C0B2CD
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4SoggRicercaInclusaModel) req.getAttribute( "AD4SoggRicercaInclusaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4SoggRicercaInclusa.html";
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
//End AD4SoggRicercaInclusaView: method show

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

//AD4SoggRicercaInclusaView Tail @1-FCB6E20C
}
//End AD4SoggRicercaInclusaView Tail
