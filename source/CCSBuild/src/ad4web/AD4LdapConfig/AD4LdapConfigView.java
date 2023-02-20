//AD4LdapConfigView imports @1-870A7AD0
package ad4web.AD4LdapConfig;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4LdapConfigView imports

//AD4LdapConfigView class @1-7D4FBD7E
public class AD4LdapConfigView extends View {
//End AD4LdapConfigView class

//AD4LdapConfigView: method show @1-6F3DE645
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4LdapConfigModel) req.getAttribute( "AD4LdapConfigModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4LdapConfig.html";
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
        guida_ldapClass guida_ldap = new guida_ldapClass();
        guida_ldap.show(page.getGrid("guida_ldap"));
        toolbox_gridClass toolbox_grid = new toolbox_gridClass();
        toolbox_grid.show(page.getGrid("toolbox_grid"));
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
//End AD4LdapConfigView: method show

// //guida_ldap Grid @83-F81417CB

//guida_ldapClass head @83-28C8A898
    final class guida_ldapClass {
//End guida_ldapClass head

// //guida_ldap Grid: method show @83-F81417CB

//show head @83-3DFD8270
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("guidaLDAP");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//guida_ldap Grid Tail @83-FCB6E20C
    }
//End guida_ldap Grid Tail

// //toolbox_grid Grid @120-F81417CB

//toolbox_gridClass head @120-C5E22196
    final class toolbox_gridClass {
//End toolbox_gridClass head

// //toolbox_grid Grid: method show @120-F81417CB

//show head @120-E38273AC
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("TIPO_SERVER");
            rowControls.add("CHIAVE");
            rowControls.add("Nuovo");
            rowControls.add("CREA_ALTERNATIVO");
            rowControls.add("ELIMINA_ALTERNATIVO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//toolbox_grid Grid Tail @120-FCB6E20C
    }
//End toolbox_grid Grid Tail

// //ldap_config Grid @110-F81417CB

//ldap_configClass head @110-C4C300D8
    final class ldap_configClass {
//End ldap_configClass head

// //ldap_config Grid: method show @110-F81417CB

//show head @110-C92B5CCF
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("STRINGA");
            rowControls.add("VALORE");
            rowControls.add("COMMENTO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//ldap_config Grid Tail @110-FCB6E20C
    }
//End ldap_config Grid Tail

//AD4LdapConfigView Tail @1-FCB6E20C
}
//End AD4LdapConfigView Tail
