//AD4CopiaUtentiView imports @1-7F68D882
package ad4web.AD4CopiaUtenti;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4CopiaUtentiView imports

//AD4CopiaUtentiView class @1-CC87504D
public class AD4CopiaUtentiView extends View {
//End AD4CopiaUtentiView class

//AD4CopiaUtentiView: method show @1-69DB0DA4
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4CopiaUtentiModel) req.getAttribute( "AD4CopiaUtentiModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4CopiaUtenti.html";
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
        if ( page.getChild( "Guida" ).isVisible() ) {
            common.Guida.GuidaView Guida = new common.Guida.GuidaView();
            tmpl.setVar( "main/@Guida", Guida.show( req, resp, context ));
            page.setCookies();
        }
        AD4_CARATTERISTICHE_ACCESSOClass AD4_CARATTERISTICHE_ACCESSO = new AD4_CARATTERISTICHE_ACCESSOClass();
        AD4_CARATTERISTICHE_ACCESSO.show(page.getRecord("AD4_CARATTERISTICHE_ACCESSO"));
        invalidPwdLogClass invalidPwdLog = new invalidPwdLogClass();
        invalidPwdLog.show(page.getGrid("invalidPwdLog"));
        if ( page.getChild( "Footer" ).isVisible() ) {
            common.Footer.FooterView Footer = new common.Footer.FooterView();
            tmpl.setVar( "main/@Footer", Footer.show( req, resp, context ));
            page.setCookies();
        }
        view.show(page.getControl("JS_REFRESH_SLAVES"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4CopiaUtentiView: method show

//AD4_CARATTERISTICHE_ACCESSO Record @38-7DFEEEF5
    final class AD4_CARATTERISTICHE_ACCESSOClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_CARATTERISTICHE_ACCESSO Record

// //invalidPwdLog Grid @245-F81417CB

//invalidPwdLogClass head @245-E76997C8
    final class invalidPwdLogClass {
//End invalidPwdLogClass head

// //invalidPwdLog Grid: method show @245-F81417CB

//show head @245-CB05B3B9
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("INVALID_PWD_LOG");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//invalidPwdLog Grid Tail @245-FCB6E20C
    }
//End invalidPwdLog Grid Tail

//AD4CopiaUtentiView Tail @1-FCB6E20C
}
//End AD4CopiaUtentiView Tail
