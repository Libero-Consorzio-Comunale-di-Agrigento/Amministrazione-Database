//Ad4DizionariSportelloTraduzioneView imports @1-DDBB307C
package restrict.Ad4DizionariSportelloTraduzione;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariSportelloTraduzioneView imports

//Ad4DizionariSportelloTraduzioneView class @1-71126B5B
public class Ad4DizionariSportelloTraduzioneView extends View {
//End Ad4DizionariSportelloTraduzioneView class

//Ad4DizionariSportelloTraduzioneView: method show @1-D7900572
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariSportelloTraduzioneModel) req.getAttribute( "Ad4DizionariSportelloTraduzioneModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariSportelloTraduzione.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ComuneImpostaClass ComuneImposta = new ComuneImpostaClass();
        ComuneImposta.show(page.getRecord("ComuneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariSportelloTraduzioneView: method show

//ComuneImposta Record @5-CA2A595A
    final class ComuneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuneImposta Record

//Ad4DizionariSportelloTraduzioneView Tail @1-FCB6E20C
}
//End Ad4DizionariSportelloTraduzioneView Tail
