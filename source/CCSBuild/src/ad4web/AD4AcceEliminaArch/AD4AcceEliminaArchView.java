//AD4AcceEliminaArchView imports @1-18E08068
package ad4web.AD4AcceEliminaArch;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4AcceEliminaArchView imports

//AD4AcceEliminaArchView class @1-0D47FB8E
public class AD4AcceEliminaArchView extends View {
//End AD4AcceEliminaArchView class

//AD4AcceEliminaArchView: method show @1-A2607FA8
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4AcceEliminaArchModel) req.getAttribute( "AD4AcceEliminaArchModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4AcceEliminaArch.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        accessiClass accessi = new accessiClass();
        accessi.show(page.getRecord("accessi"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4AcceEliminaArchView: method show

//accessi Record @11-BDD544A3
    final class accessiClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End accessi Record

//AD4AcceEliminaArchView Tail @1-FCB6E20C
}
//End AD4AcceEliminaArchView Tail

