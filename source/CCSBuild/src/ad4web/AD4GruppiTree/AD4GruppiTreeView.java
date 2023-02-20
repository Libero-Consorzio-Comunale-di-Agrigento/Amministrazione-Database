//AD4GruppiTreeView imports @1-76BEF461
package ad4web.AD4GruppiTree;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GruppiTreeView imports

//AD4GruppiTreeView class @1-8E7D23A1
public class AD4GruppiTreeView extends View {
//End AD4GruppiTreeView class

//AD4GruppiTreeView: method show @1-2C31CFB6
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GruppiTreeModel) req.getAttribute( "AD4GruppiTreeModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4GruppiTree.html";
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
        se_rigenerata_SOClass se_rigenerata_SO = new se_rigenerata_SOClass();
        se_rigenerata_SO.show(page.getRecord("se_rigenerata_SO"));
        Rigenera_SOClass Rigenera_SO = new Rigenera_SOClass();
        Rigenera_SO.show(page.getGrid("Rigenera_SO"));
        if ( page.getChild( "AD4GruppiTreeInclusa" ).isVisible() ) {
            ad4web.AD4GruppiTreeInclusa.AD4GruppiTreeInclusaView AD4GruppiTreeInclusa = new ad4web.AD4GruppiTreeInclusa.AD4GruppiTreeInclusaView();
            tmpl.setVar( "main/@AD4GruppiTreeInclusa", AD4GruppiTreeInclusa.show( req, resp, context ));
            page.setCookies();
        }
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
//End AD4GruppiTreeView: method show

//se_rigenerata_SO Record @28-9C8F8A93
    final class se_rigenerata_SOClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End se_rigenerata_SO Record

// //Rigenera_SO Grid @22-F81417CB

//Rigenera_SOClass head @22-BEC29AAF
    final class Rigenera_SOClass {
//End Rigenera_SOClass head

// //Rigenera_SO Grid: method show @22-F81417CB

//show head @22-49637FCA
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("RIGENERA");
            rowControls.add("ALLINEA_LDAP");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Rigenera_SO Grid Tail @22-FCB6E20C
    }
//End Rigenera_SO Grid Tail

//AD4GruppiTreeView Tail @1-FCB6E20C
}
//End AD4GruppiTreeView Tail
