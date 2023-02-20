//Ad4DizionariComuneTraduzioneView imports @1-1F173ED0
package restrict.Ad4DizionariComuneTraduzione;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariComuneTraduzioneView imports

//Ad4DizionariComuneTraduzioneView class @1-9D072DDA
public class Ad4DizionariComuneTraduzioneView extends View {
//End Ad4DizionariComuneTraduzioneView class

//Ad4DizionariComuneTraduzioneView: method show @1-4FC32708
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariComuneTraduzioneModel) req.getAttribute( "Ad4DizionariComuneTraduzioneModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariComuneTraduzione.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ComuneImpostaClass ComuneImposta = new ComuneImpostaClass();
        ComuneImposta.show(page.getRecord("ComuneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariComuneTraduzioneView: method show

//ComuneImposta Record @5-CA2A595A
    final class ComuneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuneImposta Record

//Ad4DizionariComuneTraduzioneView Tail @1-FCB6E20C
}
//End Ad4DizionariComuneTraduzioneView Tail
