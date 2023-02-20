//AD4GruppoModView imports @1-87ABFAC6
package ad4web.AD4GruppoMod;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GruppoModView imports

//AD4GruppoModView class @1-7B45EAA4
public class AD4GruppoModView extends View {
//End AD4GruppoModView class

//AD4GruppoModView: method show @1-A87AFC0B
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GruppoModModel) req.getAttribute( "AD4GruppoModModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4GruppoMod.html";
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
        UTENTISearchClass UTENTISearch = new UTENTISearchClass();
        UTENTISearch.show(page.getRecord("UTENTISearch"));
        UTENTIClass UTENTI = new UTENTIClass();
        UTENTI.show(page.getGrid("UTENTI"));
        DISPONIBILIClass DISPONIBILI = new DISPONIBILIClass();
        DISPONIBILI.show(page.getRecord("DISPONIBILI"));
        ASSEGNATIClass ASSEGNATI = new ASSEGNATIClass();
        ASSEGNATI.show(page.getRecord("ASSEGNATI"));
        if ( page.getChild( "Footer" ).isVisible() ) {
            common.Footer.FooterView Footer = new common.Footer.FooterView();
            tmpl.setVar( "main/@Footer", Footer.show( req, resp, context ));
            page.setCookies();
        }
        view.show(page.getControl("JS_REFRESH_SLAVES"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4GruppoModView: method show

//UTENTISearch Record @84-A8CBB20C
    final class UTENTISearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End UTENTISearch Record

// //UTENTI Grid @80-F81417CB

//UTENTIClass head @80-25249EC6
    final class UTENTIClass {
//End UTENTIClass head

// //UTENTI Grid: method show @80-F81417CB

//show head @80-41472806
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOMINATIVO");
            rowControls.add("GRUPPO");
            rowControls.add("Indietro");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//UTENTI Grid Tail @80-FCB6E20C
    }
//End UTENTI Grid Tail

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

//AD4GruppoModView Tail @1-FCB6E20C
}
//End AD4GruppoModView Tail

