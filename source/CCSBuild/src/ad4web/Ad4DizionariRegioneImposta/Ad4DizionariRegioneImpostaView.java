//Ad4DizionariRegioneImpostaView imports @1-D4297CDE
package ad4web.Ad4DizionariRegioneImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariRegioneImpostaView imports

//Ad4DizionariRegioneImpostaView class @1-0AC12CBB
public class Ad4DizionariRegioneImpostaView extends View {
//End Ad4DizionariRegioneImpostaView class

//Ad4DizionariRegioneImpostaView: method show @1-7C321FC9
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariRegioneImpostaModel) req.getAttribute( "Ad4DizionariRegioneImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariRegioneImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        RegioneImpostaClass RegioneImposta = new RegioneImpostaClass();
        RegioneImposta.show(page.getRecord("RegioneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariRegioneImpostaView: method show

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

//RegioneImposta Record @5-1F1B2962
    final class RegioneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End RegioneImposta Record

//Ad4DizionariRegioneImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariRegioneImpostaView Tail

