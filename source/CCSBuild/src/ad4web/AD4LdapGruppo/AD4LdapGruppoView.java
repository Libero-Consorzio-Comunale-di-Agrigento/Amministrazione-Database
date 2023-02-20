//AD4LdapGruppoView imports @1-685BFFE5
package ad4web.AD4LdapGruppo;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4LdapGruppoView imports

//AD4LdapGruppoView class @1-AE41B9EE
public class AD4LdapGruppoView extends View {
//End AD4LdapGruppoView class

//AD4LdapGruppoView: method show @1-B7FB51F8
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4LdapGruppoModel) req.getAttribute( "AD4LdapGruppoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4LdapGruppo.html";
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
        ldap_gruppoClass ldap_gruppo = new ldap_gruppoClass();
        ldap_gruppo.show(page.getRecord("ldap_gruppo"));
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
//End AD4LdapGruppoView: method show

//ldap_gruppo Record @136-2AE1EC50
    final class ldap_gruppoClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ldap_gruppo Record

//AD4LdapGruppoView Tail @1-FCB6E20C
}
//End AD4LdapGruppoView Tail
