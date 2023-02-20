//AD4RegistroTreeView imports @1-E51BAABE
package ad4web.AD4RegistroTree;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4RegistroTreeView imports

//AD4RegistroTreeView class @1-A58A2763
public class AD4RegistroTreeView extends View {
//End AD4RegistroTreeView class

//AD4RegistroTreeView: method show @1-BC4D7384
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4RegistroTreeModel) req.getAttribute( "AD4RegistroTreeModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4RegistroTree.html";
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
        AD4Registro_VSearchClass AD4Registro_VSearch = new AD4Registro_VSearchClass();
        AD4Registro_VSearch.show(page.getRecord("AD4Registro_VSearch"));
        TitoloClass Titolo = new TitoloClass();
        Titolo.show(page.getGrid("Titolo"));
        AD4_REGISTROClass AD4_REGISTRO = new AD4_REGISTROClass();
        AD4_REGISTRO.show(page.getGrid("AD4_REGISTRO"));
        if ( page.getChild( "AD4RegistroElenco" ).isVisible() ) {
            ad4web.AD4RegistroElenco.AD4RegistroElencoView AD4RegistroElenco = new ad4web.AD4RegistroElenco.AD4RegistroElencoView();
            tmpl.setVar( "main/@AD4RegistroElenco", AD4RegistroElenco.show( req, resp, context ));
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
//End AD4RegistroTreeView: method show

//AD4Registro_VSearch Record @10-6B5713E7
    final class AD4Registro_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Registro_VSearch Record

// //Titolo Grid @43-F81417CB

//TitoloClass head @43-B581EF3A
    final class TitoloClass {
//End TitoloClass head

// //Titolo Grid: method show @43-F81417CB

//show head @43-33598A24
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("USRORCL");
            rowControls.add("ModChiave");
            rowControls.add("NuovaChiave");
            rowControls.add("NuovaStringa");
            rowControls.add("INDIETRO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Titolo Grid Tail @43-FCB6E20C
    }
//End Titolo Grid Tail

// //AD4_REGISTRO Grid @6-F81417CB

//AD4_REGISTROClass head @6-016B9F74
    final class AD4_REGISTROClass {
//End AD4_REGISTROClass head

// //AD4_REGISTRO Grid: method show @6-F81417CB

//show head @6-B2975CD3
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("ALBERO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_REGISTRO Grid Tail @6-FCB6E20C
    }
//End AD4_REGISTRO Grid Tail

//AD4RegistroTreeView Tail @1-FCB6E20C
}
//End AD4RegistroTreeView Tail
