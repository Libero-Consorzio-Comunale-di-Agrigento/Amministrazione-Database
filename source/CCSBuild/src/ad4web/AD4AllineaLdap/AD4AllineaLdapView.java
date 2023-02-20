//AD4AllineaLdapView imports @1-BC59ADAB
package ad4web.AD4AllineaLdap;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4AllineaLdapView imports

//AD4AllineaLdapView class @1-DE2D6502
public class AD4AllineaLdapView extends View {
//End AD4AllineaLdapView class

//AD4AllineaLdapView: method show @1-C78FC1BF
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4AllineaLdapModel) req.getAttribute( "AD4AllineaLdapModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4AllineaLdap.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        allineaLdapClass allineaLdap = new allineaLdapClass();
        allineaLdap.show(page.getRecord("allineaLdap"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4AllineaLdapView: method show

//allineaLdap Record @11-0D50717F
    final class allineaLdapClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End allineaLdap Record

//AD4AllineaLdapView Tail @1-FCB6E20C
}
//End AD4AllineaLdapView Tail
