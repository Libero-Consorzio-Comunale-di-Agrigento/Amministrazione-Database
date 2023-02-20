//AD4SoggettoInclusaView imports @1-6292016A
package ad4web.AD4SoggettoInclusa;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4SoggettoInclusaView imports

//AD4SoggettoInclusaView class @1-9C222776
public class AD4SoggettoInclusaView extends View {
//End AD4SoggettoInclusaView class

//AD4SoggettoInclusaView: method show @1-FD6B927B
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4SoggettoInclusaModel) req.getAttribute( "AD4SoggettoInclusaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4SoggettoInclusa.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AD4_SOGGETTOClass AD4_SOGGETTO = new AD4_SOGGETTOClass();
        AD4_SOGGETTO.show(page.getRecord("AD4_SOGGETTO"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4SoggettoInclusaView: method show

//AD4_SOGGETTO Record @6-E0559E8A
    final class AD4_SOGGETTOClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_SOGGETTO Record

//AD4SoggettoInclusaView Tail @1-FCB6E20C
}
//End AD4SoggettoInclusaView Tail
