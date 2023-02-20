//AD4LDAPGestioneAlternativiView imports @1-06442189
package ad4web.AD4LDAPGestioneAlternativi;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4LDAPGestioneAlternativiView imports

//AD4LDAPGestioneAlternativiView class @1-B393A574
public class AD4LDAPGestioneAlternativiView extends View {
//End AD4LDAPGestioneAlternativiView class

//AD4LDAPGestioneAlternativiView: method show @1-AAB1E9BC
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4LDAPGestioneAlternativiModel) req.getAttribute( "AD4LDAPGestioneAlternativiModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4LDAPGestioneAlternativi.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ldapClass ldap = new ldapClass();
        ldap.show(page.getRecord("ldap"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4LDAPGestioneAlternativiView: method show

//ldap Record @11-7518DE5A
    final class ldapClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ldap Record

//AD4LDAPGestioneAlternativiView Tail @1-FCB6E20C
}
//End AD4LDAPGestioneAlternativiView Tail
