//AdmRichiesteView imports @1-A4C20DF7
package amvadm.AdmRichieste;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AdmRichiesteView imports

//AdmRichiesteView class @1-47F503BE
public class AdmRichiesteView extends View {
//End AdmRichiesteView class

//AdmRichiesteView: method show @1-87830E97
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AdmRichiesteModel) req.getAttribute( "AdmRichiesteModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/amvadm/AdmRichieste.html";
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
        TITLEGridClass TITLEGrid = new TITLEGridClass();
        TITLEGrid.show(page.getGrid("TITLEGrid"));
        AD4_RICHIESTE_ABILITAZIONEClass AD4_RICHIESTE_ABILITAZIONE = new AD4_RICHIESTE_ABILITAZIONEClass();
        AD4_RICHIESTE_ABILITAZIONE.show(page.getGrid("AD4_RICHIESTE_ABILITAZIONE"));
        AD4_SERVIZIO_SELClass AD4_SERVIZIO_SEL = new AD4_SERVIZIO_SELClass();
        AD4_SERVIZIO_SEL.show(page.getGrid("AD4_SERVIZIO_SEL"));
        TAB_FOLDERClass TAB_FOLDER = new TAB_FOLDERClass();
        TAB_FOLDER.show(page.getGrid("TAB_FOLDER"));
        AD4_RICHIESTE_SERVIZIOClass AD4_RICHIESTE_SERVIZIO = new AD4_RICHIESTE_SERVIZIOClass();
        AD4_RICHIESTE_SERVIZIO.show(page.getGrid("AD4_RICHIESTE_SERVIZIO"));
        if ( page.getChild( "AdmRichiesteStampa" ).isVisible() ) {
            amvadm.AdmRichiesteStampa.AdmRichiesteStampaView AdmRichiesteStampa = new amvadm.AdmRichiesteStampa.AdmRichiesteStampaView();
            tmpl.setVar( "main/@AdmRichiesteStampa", AdmRichiesteStampa.show( req, resp, context ));
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
//End AdmRichiesteView: method show

// //TITLEGrid Grid @148-F81417CB

//TITLEGridClass head @148-4A843CF8
    final class TITLEGridClass {
//End TITLEGridClass head

// //TITLEGrid Grid: method show @148-F81417CB

//show head @148-134BBFCA
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("STATO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//TITLEGrid Grid Tail @148-FCB6E20C
    }
//End TITLEGrid Grid Tail

// //AD4_RICHIESTE_ABILITAZIONE Grid @30-F81417CB

//AD4_RICHIESTE_ABILITAZIONEClass head @30-776A1933
    final class AD4_RICHIESTE_ABILITAZIONEClass {
//End AD4_RICHIESTE_ABILITAZIONEClass head

// //AD4_RICHIESTE_ABILITAZIONE Grid: method show @30-F81417CB

//show head @30-513EE504
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("SERVIZIO");
            rowControls.add("ABILITAZIONE");
            rowControls.add("LIVELLO");
            rowControls.add("TOTALE_RICHIESTE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_SERVIZIO");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_RICHIESTE_ABILITAZIONE Grid Tail @30-FCB6E20C
    }
//End AD4_RICHIESTE_ABILITAZIONE Grid Tail

// //AD4_SERVIZIO_SEL Grid @98-F81417CB

//AD4_SERVIZIO_SELClass head @98-BD28604D
    final class AD4_SERVIZIO_SELClass {
//End AD4_SERVIZIO_SELClass head

// //AD4_SERVIZIO_SEL Grid: method show @98-F81417CB

//show head @98-7F6533C8
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("SERVIZIO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_SERVIZIO_SEL Grid Tail @98-FCB6E20C
    }
//End AD4_SERVIZIO_SEL Grid Tail

// //TAB_FOLDER Grid @186-F81417CB

//TAB_FOLDERClass head @186-FEC2BF54
    final class TAB_FOLDERClass {
//End TAB_FOLDERClass head

// //TAB_FOLDER Grid: method show @186-F81417CB

//show head @186-B97371E4
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("TAB");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//TAB_FOLDER Grid Tail @186-FCB6E20C
    }
//End TAB_FOLDER Grid Tail

// //AD4_RICHIESTE_SERVIZIO Grid @123-F81417CB

//AD4_RICHIESTE_SERVIZIOClass head @123-B0C15692
    final class AD4_RICHIESTE_SERVIZIOClass {
//End AD4_RICHIESTE_SERVIZIOClass head

// //AD4_RICHIESTE_SERVIZIO Grid: method show @123-F81417CB

//show head @123-1A77108D
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DATA");
            rowControls.add("AUTORE");
            rowControls.add("INDIRIZZO_WEB");
            rowControls.add("DATI");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_RICHIESTE_SERVIZIO Grid Tail @123-FCB6E20C
    }
//End AD4_RICHIESTE_SERVIZIO Grid Tail

//AdmRichiesteView Tail @1-FCB6E20C
}
//End AdmRichiesteView Tail

