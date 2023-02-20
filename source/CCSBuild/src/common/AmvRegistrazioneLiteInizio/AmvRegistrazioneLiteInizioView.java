//AmvRegistrazioneLiteInizioView imports @1-FE6429EE
package common.AmvRegistrazioneLiteInizio;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AmvRegistrazioneLiteInizioView imports

//AmvRegistrazioneLiteInizioView class @1-06254517
public class AmvRegistrazioneLiteInizioView extends View {
//End AmvRegistrazioneLiteInizioView class

//AmvRegistrazioneLiteInizioView: method show @1-1B3AF741
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AmvRegistrazioneLiteInizioModel) req.getAttribute( "AmvRegistrazioneLiteInizioModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/common/AmvRegistrazioneLiteInizio.html";
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
        PRIVACYClass PRIVACY = new PRIVACYClass();
        PRIVACY.show(page.getGrid("PRIVACY"));
        AD4_UTENTEClass AD4_UTENTE = new AD4_UTENTEClass();
        AD4_UTENTE.show(page.getRecord("AD4_UTENTE"));
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
//End AmvRegistrazioneLiteInizioView: method show

// //PRIVACY Grid @14-F81417CB

//PRIVACYClass head @14-231CFF32
    final class PRIVACYClass {
//End PRIVACYClass head

// //PRIVACY Grid: method show @14-F81417CB

//show head @14-8CE48B41
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("PRIVACY");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//PRIVACY Grid Tail @14-FCB6E20C
    }
//End PRIVACY Grid Tail

//AD4_UTENTE Record @6-4387350C
    final class AD4_UTENTEClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_UTENTE Record

//AmvRegistrazioneLiteInizioView Tail @1-FCB6E20C
}
//End AmvRegistrazioneLiteInizioView Tail
