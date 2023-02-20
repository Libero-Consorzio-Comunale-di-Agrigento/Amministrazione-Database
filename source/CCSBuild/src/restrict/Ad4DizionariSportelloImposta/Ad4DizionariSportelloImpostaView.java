//Ad4DizionariSportelloImpostaView imports @1-8863EEC4
package restrict.Ad4DizionariSportelloImposta;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariSportelloImpostaView imports

//Ad4DizionariSportelloImpostaView class @1-D3BA0684
public class Ad4DizionariSportelloImpostaView extends View {
//End Ad4DizionariSportelloImpostaView class

//Ad4DizionariSportelloImpostaView: method show @1-DB56DBA5
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariSportelloImpostaModel) req.getAttribute( "Ad4DizionariSportelloImpostaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariSportelloImposta.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
        AttributoHeader.show(page.getGrid("AttributoHeader"));
        SportelloImpostaClass SportelloImposta = new SportelloImpostaClass();
        SportelloImposta.show(page.getRecord("SportelloImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariSportelloImpostaView: method show

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

//SportelloImposta Record @5-9BE0332D
    final class SportelloImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End SportelloImposta Record

//Ad4DizionariSportelloImpostaView Tail @1-FCB6E20C
}
//End Ad4DizionariSportelloImpostaView Tail
