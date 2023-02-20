//AD4AccessiRicercaView imports @1-C0999AF0
package ad4web.AD4AccessiRicerca;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4AccessiRicercaView imports

//AD4AccessiRicercaView class @1-383DD88E
public class AD4AccessiRicercaView extends View {
//End AD4AccessiRicercaView class

//AD4AccessiRicercaView: method show @1-ED3A8F48
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4AccessiRicercaModel) req.getAttribute( "AD4AccessiRicercaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4AccessiRicerca.html";
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
        AD4Accessi_VSearchClass AD4Accessi_VSearch = new AD4Accessi_VSearchClass();
        AD4Accessi_VSearch.show(page.getRecord("AD4Accessi_VSearch"));
        ToolboxClass Toolbox = new ToolboxClass();
        Toolbox.show(page.getGrid("Toolbox"));
        AD4AccessiVClass AD4AccessiV = new AD4AccessiVClass();
        AD4AccessiV.show(page.getGrid("AD4AccessiV"));
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
//End AD4AccessiRicercaView: method show

//AD4Accessi_VSearch Record @358-D2FF8B0D
    final class AD4Accessi_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Accessi_VSearch Record

// //Toolbox Grid @418-F81417CB

//ToolboxClass head @418-04B40E7C
    final class ToolboxClass {
//End ToolboxClass head

// //Toolbox Grid: method show @418-F81417CB

//show head @418-BE3BF8F7
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("RICERCA");
            rowControls.add("ELIMINA_ARCHIVIATI");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Toolbox Grid Tail @418-FCB6E20C
    }
//End Toolbox Grid Tail

// //AD4AccessiV Grid @142-F81417CB

//AD4AccessiVClass head @142-2159BAE6
    final class AD4AccessiVClass {
//End AD4AccessiVClass head

// //AD4AccessiV Grid: method show @142-F81417CB

//show head @142-8431F472
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("TR_STYLE");
            rowControls.add("ACCE_ID");
            rowControls.add("DATA");
            rowControls.add("SESSION_ID");
            rowControls.add("DESC_LOG");
            rowControls.add("PROVENIENZA");
            rowControls.add("DB_USER");
            rowControls.add("NOTE");
            rowControls.add("ACCESSO");
            rowControls.add("TIPO_AUTENTICAZIONE");
            rowControls.add("DESC_STATO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4AccessiV Grid Tail @142-FCB6E20C
    }
//End AD4AccessiV Grid Tail

//AD4AccessiRicercaView Tail @1-FCB6E20C
}
//End AD4AccessiRicercaView Tail
