//AD4EliminazioneCaacUtenteView imports @1-06A3080C
package ad4web.AD4EliminazioneCaacUtente;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4EliminazioneCaacUtenteView imports

//AD4EliminazioneCaacUtenteView class @1-00C8F4A8
public class AD4EliminazioneCaacUtenteView extends View {
//End AD4EliminazioneCaacUtenteView class

//AD4EliminazioneCaacUtenteView: method show @1-58649718
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4EliminazioneCaacUtenteModel) req.getAttribute( "AD4EliminazioneCaacUtenteModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4EliminazioneCaacUtente.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ad4CaacEliminaClass ad4CaacElimina = new ad4CaacEliminaClass();
        ad4CaacElimina.show(page.getRecord("ad4CaacElimina"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4EliminazioneCaacUtenteView: method show

//ad4CaacElimina Record @11-7D094516
    final class ad4CaacEliminaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ad4CaacElimina Record

//AD4EliminazioneCaacUtenteView Tail @1-FCB6E20C
}
//End AD4EliminazioneCaacUtenteView Tail
