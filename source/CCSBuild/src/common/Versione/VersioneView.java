//VersioneView imports @1-938DDC07
package common.Versione;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End VersioneView imports

//VersioneView class @1-D1B6974D
public class VersioneView extends View {
//End VersioneView class

//VersioneView: method show @1-1A9722EA
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (VersioneModel) req.getAttribute( "VersioneModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/common/Versione.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        VersioneGridClass VersioneGrid = new VersioneGridClass();
        VersioneGrid.show(page.getGrid("VersioneGrid"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End VersioneView: method show

// //VersioneGrid Grid @3-F81417CB

//VersioneGridClass head @3-795E6ED0
    final class VersioneGridClass {
//End VersioneGridClass head

// //VersioneGrid Grid: method show @3-F81417CB

//show head @3-B38FA850
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("VERSIONE");
            rowControls.add("VERSIONE_DB");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//VersioneGrid Grid Tail @3-FCB6E20C
    }
//End VersioneGrid Grid Tail

//VersioneView Tail @1-FCB6E20C
}
//End VersioneView Tail

