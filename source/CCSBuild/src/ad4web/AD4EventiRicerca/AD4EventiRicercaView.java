//AD4EventiRicercaView imports @1-010C5FD7
package ad4web.AD4EventiRicerca;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4EventiRicercaView imports

//AD4EventiRicercaView class @1-AD6B7FCD
public class AD4EventiRicercaView extends View {
//End AD4EventiRicercaView class

//AD4EventiRicercaView: method show @1-4A1C6C13
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4EventiRicercaModel) req.getAttribute( "AD4EventiRicercaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4EventiRicerca.html";
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
        AD4Eventi_VSearchClass AD4Eventi_VSearch = new AD4Eventi_VSearchClass();
        AD4Eventi_VSearch.show(page.getRecord("AD4Eventi_VSearch"));
        AD4EventiVClass AD4EventiV = new AD4EventiVClass();
        AD4EventiV.show(page.getGrid("AD4EventiV"));
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
//End AD4EventiRicercaView: method show

//AD4Eventi_VSearch Record @358-F9DCBFA6
    final class AD4Eventi_VSearchClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4Eventi_VSearch Record

// //AD4EventiV Grid @142-F81417CB

//AD4EventiVClass head @142-82DB7B3A
    final class AD4EventiVClass {
//End AD4EventiVClass head

// //AD4EventiV Grid: method show @142-F81417CB

//show head @142-86E1EB4D
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("ID_EVENTO");
            rowControls.add("DATA");
            rowControls.add("DESC_TIPO");
            rowControls.add("DESC_TIPO_RIDOTTA");
            rowControls.add("TESTO_NOTE");
            rowControls.add("PROVENIENZA");
            rowControls.add("DB_USER");
            rowControls.add("GRAVITA");
            rowControls.add("DESC_STATO");
            rowControls.add("DESC_NOTIFICATO");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4EventiV Grid Tail @142-FCB6E20C
    }
//End AD4EventiV Grid Tail

//AD4EventiRicercaView Tail @1-FCB6E20C
}
//End AD4EventiRicercaView Tail
