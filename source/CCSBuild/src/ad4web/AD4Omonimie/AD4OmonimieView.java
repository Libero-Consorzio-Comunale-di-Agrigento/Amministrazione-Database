//AD4OmonimieView imports @1-63BABF4F
package ad4web.AD4Omonimie;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4OmonimieView imports

//AD4OmonimieView class @1-FA106078
public class AD4OmonimieView extends View {
//End AD4OmonimieView class

//AD4OmonimieView: method show @1-B5A64AEB
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4OmonimieModel) req.getAttribute( "AD4OmonimieModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4Omonimie.html";
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
        AD4Ruoli_VSearchClass AD4Ruoli_VSearch = new AD4Ruoli_VSearchClass();
        AD4Ruoli_VSearch.show(page.getRecord("AD4Ruoli_VSearch"));
        ldap_configClass ldap_config = new ldap_configClass();
        ldap_config.show(page.getGrid("ldap_config"));
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
//End AD4OmonimieView: method show

//AD4Ruoli_VSearch Record @132-297F3ACE
    final class AD4Ruoli_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Ruoli_VSearch Record

// //ldap_config Grid @110-F81417CB

//ldap_configClass head @110-C4C300D8
    final class ldap_configClass {
//End ldap_configClass head

// //ldap_config Grid: method show @110-F81417CB

//show head @110-3070F57D
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOMINATIVO");
            rowControls.add("SOSIA_NOMINATIVO");
            rowControls.add("UNIFICA_PROFILO");
            rowControls.add("COPIA_PROFILO");
            rowControls.add("IGNORA");
            rowControls.add("MODIFICA");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ldap_config Grid Tail @110-FCB6E20C
    }
//End ldap_config Grid Tail

//AD4OmonimieView Tail @1-FCB6E20C
}
//End AD4OmonimieView Tail
