//AD4AutenticazioneAd4UtenteView imports @1-2464ADEE
package ad4web.AD4AutenticazioneAd4Utente;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4AutenticazioneAd4UtenteView imports

//AD4AutenticazioneAd4UtenteView class @1-B9263EC3
public class AD4AutenticazioneAd4UtenteView extends View {
//End AD4AutenticazioneAd4UtenteView class

//AD4AutenticazioneAd4UtenteView: method show @1-86BC2EE4
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4AutenticazioneAd4UtenteModel) req.getAttribute( "AD4AutenticazioneAd4UtenteModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4AutenticazioneAd4Utente.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        ad4AuthenticateClass ad4Authenticate = new ad4AuthenticateClass();
        ad4Authenticate.show(page.getRecord("ad4Authenticate"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4AutenticazioneAd4UtenteView: method show

//ad4Authenticate Record @11-E7705F2B
    final class ad4AuthenticateClass {
        void show(com.codecharge.components.Record model) {
            view.show(model);
        }
    }
//End ad4Authenticate Record

//AD4AutenticazioneAd4UtenteView Tail @1-FCB6E20C
}
//End AD4AutenticazioneAd4UtenteView Tail
