//Ad4DizionariStatoTerritorioImpostaView imports @1-96264F44
package ad4web.Ad4DizionariStatoTerritorioImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariStatoTerritorioImpostaView imports

//Ad4DizionariStatoTerritorioImpostaView class @1-A3AEE999
public class Ad4DizionariStatoTerritorioImpostaView extends View {
//End Ad4DizionariStatoTerritorioImpostaView class

//Ad4DizionariStatoTerritorioImpostaView: method show @1-4B00D5CE
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariStatoTerritorioImpostaModel) req.getAttribute( "Ad4DizionariStatoTerritorioImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariStatoTerritorioImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        StatoTerritorioImpostaClass StatoTerritorioImposta = new StatoTerritorioImpostaClass();
        StatoTerritorioImposta.show(page.getRecord("StatoTerritorioImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariStatoTerritorioImpostaView: method show

// //AttributoHeader Grid @2-F81417CB

//AttributoHeaderClass head @2-A08D40B7
    final class AttributoHeaderClass {
//End AttributoHeaderClass head

// //AttributoHeader Grid: method show @2-F81417CB

//show head @2-E3608B72
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("Indietro");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AttributoHeader Grid Tail @2-FCB6E20C
    }
//End AttributoHeader Grid Tail

//StatoTerritorioImposta Record @5-0FC4EECD
    final class StatoTerritorioImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End StatoTerritorioImposta Record

//Ad4DizionariStatoTerritorioImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariStatoTerritorioImpostaView Tail

