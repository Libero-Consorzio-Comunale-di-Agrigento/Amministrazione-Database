//Ad4DizionariZonaAslComuneImpostaView imports @1-CB39F88D
package restrict.Ad4DizionariZonaAslComuneImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariZonaAslComuneImpostaView imports

//Ad4DizionariZonaAslComuneImpostaView class @1-FB90EBE5
public class Ad4DizionariZonaAslComuneImpostaView extends View {
//End Ad4DizionariZonaAslComuneImpostaView class

//Ad4DizionariZonaAslComuneImpostaView: method show @1-4850E3BC
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariZonaAslComuneImpostaModel) req.getAttribute( "Ad4DizionariZonaAslComuneImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariZonaAslComuneImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        ZonaAslComuneImpostaClass ZonaAslComuneImposta = new ZonaAslComuneImpostaClass();
        ZonaAslComuneImposta.show(page.getRecord("ZonaAslComuneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariZonaAslComuneImpostaView: method show

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

//ZonaAslComuneImposta Record @5-FF4A0CCF
    final class ZonaAslComuneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ZonaAslComuneImposta Record

//Ad4DizionariZonaAslComuneImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariZonaAslComuneImpostaView Tail

