//AD4GruppoView imports @1-5858DB62
package ad4web.AD4Gruppo;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GruppoView imports

//AD4GruppoView class @1-D391E9C8
public class AD4GruppoView extends View {
//End AD4GruppoView class

//AD4GruppoView: method show @1-028CC6A9
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GruppoModel) req.getAttribute( "AD4GruppoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4Gruppo.html";
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
        TOOLBOXClass TOOLBOX = new TOOLBOXClass();
        TOOLBOX.show(page.getGrid("TOOLBOX"));
        UTENTI_GRUPPOClass UTENTI_GRUPPO = new UTENTI_GRUPPOClass();
        UTENTI_GRUPPO.show(page.getGrid("UTENTI_GRUPPO"));
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
//End AD4GruppoView: method show

//AD4_UTENTI Record @23-3FE1AF3E
    final class AD4_UTENTIClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AD4_UTENTI Record

// //TOOLBOX Grid @97-F81417CB

//TOOLBOXClass head @97-617E80EA
    final class TOOLBOXClass {
//End TOOLBOXClass head

// //TOOLBOX Grid: method show @97-F81417CB

//show head @97-A20C3A64
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("UTGR_INS_MOD");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//TOOLBOX Grid Tail @97-FCB6E20C
    }
//End TOOLBOX Grid Tail

// //UTENTI_GRUPPO Grid @78-F81417CB

//UTENTI_GRUPPOClass head @78-6C7009FF
    final class UTENTI_GRUPPOClass {
//End UTENTI_GRUPPOClass head

// //UTENTI_GRUPPO Grid: method show @78-F81417CB

//show head @78-6D334300
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("NOMINATIVO");
            rowControls.add("CHECK_DIAC_CAAC");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//UTENTI_GRUPPO Grid Tail @78-FCB6E20C
    }
//End UTENTI_GRUPPO Grid Tail

//AD4GruppoView Tail @1-FCB6E20C
}
//End AD4GruppoView Tail

