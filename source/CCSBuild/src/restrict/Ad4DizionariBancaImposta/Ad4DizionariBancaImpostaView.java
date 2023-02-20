//Ad4DizionariBancaImpostaView imports @1-A31B9531
package restrict.Ad4DizionariBancaImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariBancaImpostaView imports

//Ad4DizionariBancaImpostaView class @1-9AB6DF4F
public class Ad4DizionariBancaImpostaView extends View {
//End Ad4DizionariBancaImpostaView class

//Ad4DizionariBancaImpostaView: method show @1-DBBCC27D
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariBancaImpostaModel) req.getAttribute( "Ad4DizionariBancaImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariBancaImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        BancaImpostaClass BancaImposta = new BancaImpostaClass();
        BancaImposta.show(page.getRecord("BancaImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariBancaImpostaView: method show

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

//BancaImposta Record @5-F7EAF3AC
    final class BancaImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End BancaImposta Record

//Ad4DizionariBancaImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariBancaImpostaView Tail
