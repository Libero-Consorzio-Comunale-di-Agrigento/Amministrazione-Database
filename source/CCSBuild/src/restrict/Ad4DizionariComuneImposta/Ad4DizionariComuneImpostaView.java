//Ad4DizionariComuneImpostaView imports @1-777A422E
package restrict.Ad4DizionariComuneImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariComuneImpostaView imports

//Ad4DizionariComuneImpostaView class @1-E858BE56
public class Ad4DizionariComuneImpostaView extends View {
//End Ad4DizionariComuneImpostaView class

//Ad4DizionariComuneImpostaView: method show @1-FAAA3421
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariComuneImpostaModel) req.getAttribute( "Ad4DizionariComuneImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariComuneImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        ComuneImpostaClass ComuneImposta = new ComuneImpostaClass();
        ComuneImposta.show(page.getRecord("ComuneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariComuneImpostaView: method show

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

//ComuneImposta Record @5-CA2A595A
    final class ComuneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuneImposta Record

//Ad4DizionariComuneImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariComuneImpostaView Tail

