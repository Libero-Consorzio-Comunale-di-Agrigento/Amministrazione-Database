//AD4GruppiTreeInclusaView imports @1-4059A543
package ad4web.AD4GruppiTreeInclusa;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GruppiTreeInclusaView imports

//AD4GruppiTreeInclusaView class @1-4C2A108F
public class AD4GruppiTreeInclusaView extends View {
//End AD4GruppiTreeInclusaView class

//AD4GruppiTreeInclusaView: method show @1-462DC3F0
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GruppiTreeInclusaModel) req.getAttribute( "AD4GruppiTreeInclusaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4GruppiTreeInclusa.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AD4_GRUPPIClass AD4_GRUPPI = new AD4_GRUPPIClass();
        AD4_GRUPPI.show(page.getGrid("AD4_GRUPPI"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4GruppiTreeInclusaView: method show

// //AD4_GRUPPI Grid @6-F81417CB

//AD4_GRUPPIClass head @6-C6449123
    final class AD4_GRUPPIClass {
//End AD4_GRUPPIClass head

// //AD4_GRUPPI Grid: method show @6-F81417CB

//show head @6-B2975CD3
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("ALBERO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_GRUPPI Grid Tail @6-FCB6E20C
    }
//End AD4_GRUPPI Grid Tail

//AD4GruppiTreeInclusaView Tail @1-FCB6E20C
}
//End AD4GruppiTreeInclusaView Tail
