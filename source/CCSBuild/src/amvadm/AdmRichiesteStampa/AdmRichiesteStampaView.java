//AdmRichiesteStampaView imports @1-ACACE9CD
package amvadm.AdmRichiesteStampa;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AdmRichiesteStampaView imports

//AdmRichiesteStampaView class @1-376BC1D3
public class AdmRichiesteStampaView extends View {
//End AdmRichiesteStampaView class

//AdmRichiesteStampaView: method show @1-663A603B
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AdmRichiesteStampaModel) req.getAttribute( "AdmRichiesteStampaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/amvadm/AdmRichiesteStampa.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        RICHIESTE_STAMPAClass RICHIESTE_STAMPA = new RICHIESTE_STAMPAClass();
        RICHIESTE_STAMPA.show(page.getEditableGrid("RICHIESTE_STAMPA"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AdmRichiesteStampaView: method show

// //RICHIESTE_STAMPA EditGrid @2-F81417CB

//RICHIESTE_STAMPAClass head @2-9C8FEF79
    final class RICHIESTE_STAMPAClass {
//End RICHIESTE_STAMPAClass head

// //RICHIESTE_STAMPA EditGrid: method show @2-F81417CB

//show head @2-A8460A0E
        void show(com.codecharge.components.EditableGrid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DATA");
            rowControls.add("REPORT");
            rowControls.add("AUTORE");
            rowControls.add("ID_RICHIESTA");
            rowControls.add("NOTIFICATA");
            rowControls.add("BEGIN_HIDE");
            rowControls.add("NOTIFICATO");
            rowControls.add("STAMPA");
            rowControls.add("END_HIDE");
            ArrayList staticControls = new ArrayList();
            staticControls.add("LINK");
            staticControls.add("Button_Submit");
            if (!(model.isAllowInsert() || model.isAllowUpdate() || model.isAllowDelete())) {
                model.getButton("Button_Submit").setVisible(false);
            }
            
            view.show(model,staticControls,rowControls,false,false);
        }
//End show head

//RICHIESTE_STAMPA EditGrid Tail @2-FCB6E20C
    }
//End RICHIESTE_STAMPA EditGrid Tail

//AdmRichiesteStampaView Tail @1-FCB6E20C
}
//End AdmRichiesteStampaView Tail

