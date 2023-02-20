//RegistrazioneFineView imports @1-21CD8ACF
package common.RegistrazioneFine;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End RegistrazioneFineView imports

//RegistrazioneFineView class @1-FE68E5FE
public class RegistrazioneFineView extends View {
//End RegistrazioneFineView class

//RegistrazioneFineView: method show @1-4E2FC80C
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (RegistrazioneFineModel) req.getAttribute( "RegistrazioneFineModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/common/RegistrazioneFine.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End RegistrazioneFineView: method show

//RegistrazioneFineView Tail @1-FCB6E20C
}
//End RegistrazioneFineView Tail

