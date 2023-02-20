//AD4StringaSecureView imports @1-1716771F
package ad4web.AD4StringaSecure;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4StringaSecureView imports

//AD4StringaSecureView class @1-2251E871
public class AD4StringaSecureView extends View {
//End AD4StringaSecureView class

//AD4StringaSecureView: method show @1-168BB913
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4StringaSecureModel) req.getAttribute( "AD4StringaSecureModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4StringaSecure.html";
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
        AMV_AREEClass AMV_AREE = new AMV_AREEClass();
        AMV_AREE.show(page.getGrid("AMV_AREE"));
        AMV_AREE1Class AMV_AREE1 = new AMV_AREE1Class();
        AMV_AREE1.show(page.getRecord("AMV_AREE1"));
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
//End AD4StringaSecureView: method show

// //AMV_AREE Grid @2-F81417CB

//AMV_AREEClass head @2-7FF6C999
    final class AMV_AREEClass {
//End AMV_AREEClass head

// //AMV_AREE Grid: method show @2-F81417CB

//show head @2-3A77DD16
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOME");
            rowControls.add("DESCRIZIONE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_NOME");
            staticControls.add("Sorter_DESCRIZIONE");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AMV_AREE Grid Tail @2-FCB6E20C
    }
//End AMV_AREE Grid Tail

//AMV_AREE1 Record @11-F5E8ADAD
    final class AMV_AREE1Class {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AMV_AREE1 Record

//AD4StringaSecureView Tail @1-FCB6E20C
}
//End AD4StringaSecureView Tail
