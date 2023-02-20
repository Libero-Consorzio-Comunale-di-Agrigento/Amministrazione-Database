//Ad4DizionariProvinciaImpostaView imports @1-D0412329
package restrict.Ad4DizionariProvinciaImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariProvinciaImpostaView imports

//Ad4DizionariProvinciaImpostaView class @1-816C94D1
public class Ad4DizionariProvinciaImpostaView extends View {
//End Ad4DizionariProvinciaImpostaView class

//Ad4DizionariProvinciaImpostaView: method show @1-91F0E75B
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariProvinciaImpostaModel) req.getAttribute( "Ad4DizionariProvinciaImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariProvinciaImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        ProvinciaImpostaClass ProvinciaImposta = new ProvinciaImpostaClass();
        ProvinciaImposta.show(page.getRecord("ProvinciaImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariProvinciaImpostaView: method show

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

//ProvinciaImposta Record @5-18EEC233
    final class ProvinciaImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ProvinciaImposta Record

//Ad4DizionariProvinciaImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariProvinciaImpostaView Tail

