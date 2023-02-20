//AD4AccessiTreeView imports @1-CDC759A1
package ad4web.AD4AccessiTree;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4AccessiTreeView imports

//AD4AccessiTreeView class @1-75538685
public class AD4AccessiTreeView extends View {
//End AD4AccessiTreeView class

//AD4AccessiTreeView: method show @1-E068363C
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4AccessiTreeModel) req.getAttribute( "AD4AccessiTreeModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4AccessiTree.html";
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
        AD4_ACCESSIClass AD4_ACCESSI = new AD4_ACCESSIClass();
        AD4_ACCESSI.show(page.getGrid("AD4_ACCESSI"));
        TITOLOClass TITOLO = new TITOLOClass();
        TITOLO.show(page.getGrid("TITOLO"));
        AccessiDettaglioClass AccessiDettaglio = new AccessiDettaglioClass();
        AccessiDettaglio.show(page.getGrid("AccessiDettaglio"));
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
//End AD4AccessiTreeView: method show

// //AD4_ACCESSI Grid @6-F81417CB

//AD4_ACCESSIClass head @6-4BCD6096
    final class AD4_ACCESSIClass {
//End AD4_ACCESSIClass head

// //AD4_ACCESSI Grid: method show @6-F81417CB

//show head @6-B2975CD3
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("ALBERO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_ACCESSI Grid Tail @6-FCB6E20C
    }
//End AD4_ACCESSI Grid Tail

// //TITOLO Grid @31-F81417CB

//TITOLOClass head @31-C87ABA54
    final class TITOLOClass {
//End TITOLOClass head

// //TITOLO Grid: method show @31-F81417CB

//show head @31-CBB62AE6
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("UTENTE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//TITOLO Grid Tail @31-FCB6E20C
    }
//End TITOLO Grid Tail

// //AccessiDettaglio Grid @22-F81417CB

//AccessiDettaglioClass head @22-914C7864
    final class AccessiDettaglioClass {
//End AccessiDettaglioClass head

// //AccessiDettaglio Grid: method show @22-F81417CB

//show head @22-52B2E56D
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DES_ACCESSO");
            rowControls.add("DES_ORA");
            rowControls.add("NOTE");
            rowControls.add("DSP_SESSIONE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AccessiDettaglio Grid Tail @22-FCB6E20C
    }
//End AccessiDettaglio Grid Tail

//AD4AccessiTreeView Tail @1-FCB6E20C
}
//End AD4AccessiTreeView Tail
