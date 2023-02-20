//AD4AmbienteView imports @1-198F01DB
package ad4web.AD4Ambiente;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4AmbienteView imports

//AD4AmbienteView class @1-AE6571BF
public class AD4AmbienteView extends View {
//End AD4AmbienteView class

//AD4AmbienteView: method show @1-763CF91B
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4AmbienteModel) req.getAttribute( "AD4AmbienteModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4Ambiente.html";
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
        AD4_AMBIENTEClass AD4_AMBIENTE = new AD4_AMBIENTEClass();
        AD4_AMBIENTE.show(page.getGrid("AD4_AMBIENTE"));
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
//End AD4AmbienteView: method show

// //AD4_AMBIENTE Grid @6-F81417CB

//AD4_AMBIENTEClass head @6-A8594D37
    final class AD4_AMBIENTEClass {
//End AD4_AMBIENTEClass head

// //AD4_AMBIENTE Grid: method show @6-F81417CB

//show head @6-D0DE80D0
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("AMBIENTE");
            rowControls.add("EXTERNAL_AUTENTICATION");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_AMBIENTE Grid Tail @6-FCB6E20C
    }
//End AD4_AMBIENTE Grid Tail

//AD4AmbienteView Tail @1-FCB6E20C
}
//End AD4AmbienteView Tail

