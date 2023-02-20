//AD4RigeneraSOView imports @1-F95096F6
package ad4web.AD4RigeneraSO;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4RigeneraSOView imports

//AD4RigeneraSOView class @1-8EC9CA5C
public class AD4RigeneraSOView extends View {
//End AD4RigeneraSOView class

//AD4RigeneraSOView: method show @1-A6E656DE
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4RigeneraSOModel) req.getAttribute( "AD4RigeneraSOModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4RigeneraSO.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        rigeneraClass rigenera = new rigeneraClass();
        rigenera.show(page.getRecord("rigenera"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4RigeneraSOView: method show

//rigenera Record @11-06696BD8
    final class rigeneraClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End rigenera Record

//AD4RigeneraSOView Tail @1-FCB6E20C
}
//End AD4RigeneraSOView Tail
