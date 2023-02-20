//AD4RichiestaParametriView imports @1-1029593F
package ad4web.AD4RichiestaParametri;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4RichiestaParametriView imports

//AD4RichiestaParametriView class @1-4FE60D16
public class AD4RichiestaParametriView extends View {
//End AD4RichiestaParametriView class

//AD4RichiestaParametriView: method show @1-FA00B200
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4RichiestaParametriModel) req.getAttribute( "AD4RichiestaParametriModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4RichiestaParametri.html";
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
        TitoloClass Titolo = new TitoloClass();
        Titolo.show(page.getGrid("Titolo"));
        PARAMETRI_RICHIESTAClass PARAMETRI_RICHIESTA = new PARAMETRI_RICHIESTAClass();
        PARAMETRI_RICHIESTA.show(page.getGrid("PARAMETRI_RICHIESTA"));
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
//End AD4RichiestaParametriView: method show

// //Titolo Grid @135-F81417CB

//TitoloClass head @135-B581EF3A
    final class TitoloClass {
//End TitoloClass head

// //Titolo Grid: method show @135-F81417CB

//show head @135-E09EAD4E
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("Nuovo");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Titolo Grid Tail @135-FCB6E20C
    }
//End Titolo Grid Tail

// //PARAMETRI_RICHIESTA Grid @6-F81417CB

//PARAMETRI_RICHIESTAClass head @6-80774C44
    final class PARAMETRI_RICHIESTAClass {
//End PARAMETRI_RICHIESTAClass head

// //PARAMETRI_RICHIESTA Grid: method show @6-F81417CB

//show head @6-B49380C9
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("ID_PARAMETRO");
            rowControls.add("ID_RICHIESTA");
            rowControls.add("PARAMETRO");
            rowControls.add("VALORE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Indietro");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//PARAMETRI_RICHIESTA Grid Tail @6-FCB6E20C
    }
//End PARAMETRI_RICHIESTA Grid Tail

//AD4RichiestaParametriView Tail @1-FCB6E20C
}
//End AD4RichiestaParametriView Tail
