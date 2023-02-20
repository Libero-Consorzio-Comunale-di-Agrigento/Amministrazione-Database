//AmvLoginView imports @1-A86026F1
package common.AmvLogin;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AmvLoginView imports

//AmvLoginView class @1-8AC22915
public class AmvLoginView extends View {
//End AmvLoginView class

//AmvLoginView: method show @1-84F5E78D
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AmvLoginModel) req.getAttribute( "AmvLoginModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/common/AmvLogin.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        LOGINClass LOGIN = new LOGINClass();
        LOGIN.show(page.getRecord("LOGIN"));
        RECUPERO_LOGIN_CUSTOMClass RECUPERO_LOGIN_CUSTOM = new RECUPERO_LOGIN_CUSTOMClass();
        RECUPERO_LOGIN_CUSTOM.show(page.getGrid("RECUPERO_LOGIN_CUSTOM"));
        RECUPERO_LOGINClass RECUPERO_LOGIN = new RECUPERO_LOGINClass();
        RECUPERO_LOGIN.show(page.getGrid("RECUPERO_LOGIN"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AmvLoginView: method show

//LOGIN Record @2-AF19329C
    final class LOGINClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End LOGIN Record

// //RECUPERO_LOGIN_CUSTOM Grid @17-F81417CB

//RECUPERO_LOGIN_CUSTOMClass head @17-CA184BCF
    final class RECUPERO_LOGIN_CUSTOMClass {
//End RECUPERO_LOGIN_CUSTOMClass head

// //RECUPERO_LOGIN_CUSTOM Grid: method show @17-F81417CB

//show head @17-27EB7A66
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("LOSTMSGCUSTOM");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//RECUPERO_LOGIN_CUSTOM Grid Tail @17-FCB6E20C
    }
//End RECUPERO_LOGIN_CUSTOM Grid Tail

// //RECUPERO_LOGIN Grid @12-F81417CB

//RECUPERO_LOGINClass head @12-03CEB2BF
    final class RECUPERO_LOGINClass {
//End RECUPERO_LOGINClass head

// //RECUPERO_LOGIN Grid: method show @12-F81417CB

//show head @12-C8D0D059
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("LOSTMSG");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//RECUPERO_LOGIN Grid Tail @12-FCB6E20C
    }
//End RECUPERO_LOGIN Grid Tail

//AmvLoginView Tail @1-FCB6E20C
}
//End AmvLoginView Tail

