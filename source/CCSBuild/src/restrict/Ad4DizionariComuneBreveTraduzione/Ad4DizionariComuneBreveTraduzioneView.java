//Ad4DizionariComuneBreveTraduzioneView imports @1-58837C0E
package restrict.Ad4DizionariComuneBreveTraduzione;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariComuneBreveTraduzioneView imports

//Ad4DizionariComuneBreveTraduzioneView class @1-904E6A3F
public class Ad4DizionariComuneBreveTraduzioneView extends View {
//End Ad4DizionariComuneBreveTraduzioneView class

//Ad4DizionariComuneBreveTraduzioneView: method show @1-DCA44BC1
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariComuneBreveTraduzioneModel) req.getAttribute( "Ad4DizionariComuneBreveTraduzioneModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariComuneBreveTraduzione.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ComuneImpostaClass ComuneImposta = new ComuneImpostaClass();
        ComuneImposta.show(page.getRecord("ComuneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariComuneBreveTraduzioneView: method show

//ComuneImposta Record @5-CA2A595A
    final class ComuneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuneImposta Record

//Ad4DizionariComuneBreveTraduzioneView Tail @1-FCB6E20C
}
//End Ad4DizionariComuneBreveTraduzioneView Tail
