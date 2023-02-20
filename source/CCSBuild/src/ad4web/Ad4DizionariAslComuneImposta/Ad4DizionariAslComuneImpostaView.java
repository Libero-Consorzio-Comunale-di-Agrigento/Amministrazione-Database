//Ad4DizionariAslComuneImpostaView imports @1-BFC923F8
package ad4web.Ad4DizionariAslComuneImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariAslComuneImpostaView imports

//Ad4DizionariAslComuneImpostaView class @1-C71ADF53
public class Ad4DizionariAslComuneImpostaView extends View {
//End Ad4DizionariAslComuneImpostaView class

//Ad4DizionariAslComuneImpostaView: method show @1-F02D9837
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariAslComuneImpostaModel) req.getAttribute( "Ad4DizionariAslComuneImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/Ad4DizionariAslComuneImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        AslComuneImpostaClass AslComuneImposta = new AslComuneImpostaClass();
        AslComuneImposta.show(page.getRecord("AslComuneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariAslComuneImpostaView: method show

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

//AslComuneImposta Record @5-93C9BD69
    final class AslComuneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End AslComuneImposta Record

//Ad4DizionariAslComuneImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariAslComuneImpostaView Tail

