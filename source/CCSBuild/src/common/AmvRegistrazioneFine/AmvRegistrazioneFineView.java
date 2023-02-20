//AmvRegistrazioneFineView imports @1-334C9148
package common.AmvRegistrazioneFine;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AmvRegistrazioneFineView imports

//AmvRegistrazioneFineView class @1-FBB5C37C
public class AmvRegistrazioneFineView extends View {
//End AmvRegistrazioneFineView class

//AmvRegistrazioneFineView: method show @1-E56A306E
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AmvRegistrazioneFineModel) req.getAttribute( "AmvRegistrazioneFineModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/common/AmvRegistrazioneFine.html";
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
        if ( page.getChild( "AmvDatiRegistrazione" ).isVisible() ) {
            common.AmvDatiRegistrazione.AmvDatiRegistrazioneView AmvDatiRegistrazione = new common.AmvDatiRegistrazione.AmvDatiRegistrazioneView();
            tmpl.setVar( "main/@AmvDatiRegistrazione", AmvDatiRegistrazione.show( req, resp, context ));
            page.setCookies();
        }
        MESSAGGIO_RICHIESTAClass MESSAGGIO_RICHIESTA = new MESSAGGIO_RICHIESTAClass();
        MESSAGGIO_RICHIESTA.show(page.getGrid("MESSAGGIO_RICHIESTA"));
        if ( page.getChild( "RegistrazioneFine" ).isVisible() ) {
            common.RegistrazioneFine.RegistrazioneFineView RegistrazioneFine = new common.RegistrazioneFine.RegistrazioneFineView();
            tmpl.setVar( "main/@RegistrazioneFine", RegistrazioneFine.show( req, resp, context ));
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
//End AmvRegistrazioneFineView: method show

// //MESSAGGIO_RICHIESTA Grid @7-F81417CB

//MESSAGGIO_RICHIESTAClass head @7-E61E8BBE
    final class MESSAGGIO_RICHIESTAClass {
//End MESSAGGIO_RICHIESTAClass head

// //MESSAGGIO_RICHIESTA Grid: method show @7-F81417CB

//show head @7-A4C4B901
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("MSG");
            rowControls.add("MSG_PROCEDURE");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//MESSAGGIO_RICHIESTA Grid Tail @7-FCB6E20C
    }
//End MESSAGGIO_RICHIESTA Grid Tail

//AmvRegistrazioneFineView Tail @1-FCB6E20C
}
//End AmvRegistrazioneFineView Tail

