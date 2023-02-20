//AD4SoggModIndirizzoView imports @1-B181B6F2
package ad4web.AD4SoggModIndirizzo;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4SoggModIndirizzoView imports

//AD4SoggModIndirizzoView class @1-87DC9823
public class AD4SoggModIndirizzoView extends View {
//End AD4SoggModIndirizzoView class

//AD4SoggModIndirizzoView: method show @1-0C537BC0
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4SoggModIndirizzoModel) req.getAttribute( "AD4SoggModIndirizzoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4SoggModIndirizzo.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AD4_UTENTIClass AD4_UTENTI = new AD4_UTENTIClass();
        AD4_UTENTI.show(page.getRecord("AD4_UTENTI"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4SoggModIndirizzoView: method show

//AD4_UTENTI Record @2-3FE1AF3E
    final class AD4_UTENTIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_UTENTI Record

//AD4SoggModIndirizzoView Tail @1-FCB6E20C
}
//End AD4SoggModIndirizzoView Tail
