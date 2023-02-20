//Ad4DizionariBancaTraduzioneView imports @1-FCA1D85F
package restrict.Ad4DizionariBancaTraduzione;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariBancaTraduzioneView imports

//Ad4DizionariBancaTraduzioneView class @1-B5A420CE
public class Ad4DizionariBancaTraduzioneView extends View {
//End Ad4DizionariBancaTraduzioneView class

//Ad4DizionariBancaTraduzioneView: method show @1-73A2FDA7
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariBancaTraduzioneModel) req.getAttribute( "Ad4DizionariBancaTraduzioneModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariBancaTraduzione.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ComuneImpostaClass ComuneImposta = new ComuneImpostaClass();
        ComuneImposta.show(page.getRecord("ComuneImposta"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariBancaTraduzioneView: method show

//ComuneImposta Record @5-CA2A595A
    final class ComuneImpostaClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ComuneImposta Record

//Ad4DizionariBancaTraduzioneView Tail @1-FCB6E20C
}
//End Ad4DizionariBancaTraduzioneView Tail
