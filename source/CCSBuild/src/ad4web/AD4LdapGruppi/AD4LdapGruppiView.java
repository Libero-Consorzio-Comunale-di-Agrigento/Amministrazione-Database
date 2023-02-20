//AD4LdapGruppiView imports @1-37458120
package ad4web.AD4LdapGruppi;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4LdapGruppiView imports

//AD4LdapGruppiView class @1-09750EE6
public class AD4LdapGruppiView extends View {
//End AD4LdapGruppiView class

//AD4LdapGruppiView: method show @1-A12132F6
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4LdapGruppiModel) req.getAttribute( "AD4LdapGruppiModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4LdapGruppi.html";
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
        view.show(page.getImageLink("Nuovo"));
        ldap_configClass ldap_config = new ldap_configClass();
        ldap_config.show(page.getGrid("ldap_config"));
        checkAuthorizationMappingClass checkAuthorizationMapping = new checkAuthorizationMappingClass();
        checkAuthorizationMapping.show(page.getGrid("checkAuthorizationMapping"));
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
//End AD4LdapGruppiView: method show

// //ldap_config Grid @110-F81417CB

//ldap_configClass head @110-C4C300D8
    final class ldap_configClass {
//End ldap_configClass head

// //ldap_config Grid: method show @110-F81417CB

//show head @110-F61D6C3E
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("GRUPPO_AD4");
            rowControls.add("VALORE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ldap_config Grid Tail @110-FCB6E20C
    }
//End ldap_config Grid Tail

// //checkAuthorizationMapping Grid @130-F81417CB

//checkAuthorizationMappingClass head @130-F921ADA9
    final class checkAuthorizationMappingClass {
//End checkAuthorizationMappingClass head

// //checkAuthorizationMapping Grid: method show @130-F81417CB

//show head @130-6E839F60
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//checkAuthorizationMapping Grid Tail @130-FCB6E20C
    }
//End checkAuthorizationMapping Grid Tail

//AD4LdapGruppiView Tail @1-FCB6E20C
}
//End AD4LdapGruppiView Tail
