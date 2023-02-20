//AD4GruppiUtenteElencoView imports @1-63244EDC
package ad4web.AD4GruppiUtenteElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GruppiUtenteElencoView imports

//AD4GruppiUtenteElencoView class @1-67762EF5
public class AD4GruppiUtenteElencoView extends View {
//End AD4GruppiUtenteElencoView class

//AD4GruppiUtenteElencoView: method show @1-EF0ECD25
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GruppiUtenteElencoModel) req.getAttribute( "AD4GruppiUtenteElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4GruppiUtenteElenco.html";
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
        AD4_UTENTIClass AD4_UTENTI = new AD4_UTENTIClass();
        AD4_UTENTI.show(page.getRecord("AD4_UTENTI"));
        DISPONIBILIClass DISPONIBILI = new DISPONIBILIClass();
        DISPONIBILI.show(page.getRecord("DISPONIBILI"));
        ASSEGNATIClass ASSEGNATI = new ASSEGNATIClass();
        ASSEGNATI.show(page.getRecord("ASSEGNATI"));
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
//End AD4GruppiUtenteElencoView: method show

//AD4_UTENTI Record @78-3FE1AF3E
    final class AD4_UTENTIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_UTENTI Record

//DISPONIBILI Record @52-D9292E6E
    final class DISPONIBILIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End DISPONIBILI Record

//ASSEGNATI Record @63-5D1454D7
    final class ASSEGNATIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ASSEGNATI Record

//AD4GruppiUtenteElencoView Tail @1-FCB6E20C
}
//End AD4GruppiUtenteElencoView Tail

