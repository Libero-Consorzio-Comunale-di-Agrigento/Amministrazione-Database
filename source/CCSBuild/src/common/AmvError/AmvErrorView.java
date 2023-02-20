//AmvErrorView imports @1-5ACFA19A
package common.AmvError;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AmvErrorView imports

//AmvErrorView class @1-C780F56C
public class AmvErrorView extends View {
//End AmvErrorView class

//AmvErrorView: method show @1-BF8188D6
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AmvErrorModel) req.getAttribute( "AmvErrorModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/common/AmvError.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Header" ).isVisible() ) {
            common.Header.HeaderView Header = new common.Header.HeaderView();
            tmpl.setVar( "main/@Header", Header.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "Left" ).isVisible() ) {
            common.Left.LeftView Left = new common.Left.LeftView();
            tmpl.setVar( "main/@Left", Left.show( req, resp, context ));
            page.setCookies();
        }
        MESSAGGIO_ERROREClass MESSAGGIO_ERRORE = new MESSAGGIO_ERROREClass();
        MESSAGGIO_ERRORE.show(page.getGrid("MESSAGGIO_ERRORE"));
        if ( page.getChild( "Footer" ).isVisible() ) {
            common.Footer.FooterView Footer = new common.Footer.FooterView();
            tmpl.setVar( "main/@Footer", Footer.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AmvErrorView: method show

// //MESSAGGIO_ERRORE Grid @5-F81417CB

//MESSAGGIO_ERROREClass head @5-432F73E0
    final class MESSAGGIO_ERROREClass {
//End MESSAGGIO_ERROREClass head

// //MESSAGGIO_ERRORE Grid: method show @5-F81417CB

//show head @5-6D49E745
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("MSG");
            rowControls.add("CUSTOM_MSG");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//MESSAGGIO_ERRORE Grid Tail @5-FCB6E20C
    }
//End MESSAGGIO_ERRORE Grid Tail

//AmvErrorView Tail @1-FCB6E20C
}
//End AmvErrorView Tail
