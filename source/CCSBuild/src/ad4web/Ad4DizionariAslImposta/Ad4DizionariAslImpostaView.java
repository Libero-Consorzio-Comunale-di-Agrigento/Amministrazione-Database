//Ad4DizionariAslImpostaView imports @1-BB481218
package ad4web.Ad4DizionariAslImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariAslImpostaView imports

//Ad4DizionariAslImpostaView class @1-91DF0162
public class Ad4DizionariAslImpostaView extends View {
//End Ad4DizionariAslImpostaView class

//Ad4DizionariAslImpostaView: method show @1-8FEEA72C
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariAslImpostaModel) req.getAttribute( "Ad4DizionariAslImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariAslImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        AslImpostaClass AslImposta = new AslImpostaClass();
        AslImposta.show(page.getRecord("AslImposta"));
        if ( page.getChild( "Ad4DizionariAslComuniElenco" ).isVisible() ) {
            ad4web.Ad4DizionariAslComuniElenco.Ad4DizionariAslComuniElencoView Ad4DizionariAslComuniElenco = new ad4web.Ad4DizionariAslComuniElenco.Ad4DizionariAslComuniElencoView();
            tmpl.setVar( "main/@Ad4DizionariAslComuniElenco", Ad4DizionariAslComuniElenco.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariAslImpostaView: method show

// //AttributoHeader Grid @44-F81417CB

//AttributoHeaderClass head @44-A08D40B7
    final class AttributoHeaderClass {
//End AttributoHeaderClass head

// //AttributoHeader Grid: method show @44-F81417CB

//show head @44-E3608B72
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("Indietro");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AttributoHeader Grid Tail @44-FCB6E20C
    }
//End AttributoHeader Grid Tail

//AslImposta Record @5-B4077CA6
    final class AslImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AslImposta Record

//Ad4DizionariAslImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariAslImpostaView Tail

