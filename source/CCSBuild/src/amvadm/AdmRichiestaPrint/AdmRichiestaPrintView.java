//AdmRichiestaPrintView imports @1-DD6C840D
package amvadm.AdmRichiestaPrint;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AdmRichiestaPrintView imports

//AdmRichiestaPrintView class @1-FF7DD5F9
public class AdmRichiestaPrintView extends View {
//End AdmRichiestaPrintView class

//AdmRichiestaPrintView: method show @1-1CAB2F1B
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AdmRichiestaPrintModel) req.getAttribute( "AdmRichiestaPrintModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/amvadm/AdmRichiestaPrint.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AD4_UTENTEGridClass AD4_UTENTEGrid = new AD4_UTENTEGridClass();
        AD4_UTENTEGrid.show(page.getGrid("AD4_UTENTEGrid"));
        if ( page.getChild( "AmvFooter" ).isVisible() ) {
            common.AmvFooter.AmvFooterView AmvFooter = new common.AmvFooter.AmvFooterView();
            tmpl.setVar( "main/@AmvFooter", AmvFooter.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AdmRichiestaPrintView: method show

// //AD4_UTENTEGrid Grid @71-F81417CB

//AD4_UTENTEGridClass head @71-37151883
    final class AD4_UTENTEGridClass {
//End AD4_UTENTEGridClass head

// //AD4_UTENTEGrid Grid: method show @71-F81417CB

//show head @71-A8981EF2
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("DATA_RICHIESTA");
            rowControls.add("NOME_SOGGETTO");
            rowControls.add("DATA_NASCITA");
            rowControls.add("COMUNE_NASCITA");
            rowControls.add("PROVINCIA_NASCITA");
            rowControls.add("CODICE_FISCALE");
            rowControls.add("INDIRIZZO_COMPLETO");
            rowControls.add("INDIRIZZO_WEB");
            rowControls.add("TELEFONO");
            rowControls.add("FAX");
            rowControls.add("NOMINATIVO");
            rowControls.add("PASSWORD");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_UTENTEGrid Grid Tail @71-FCB6E20C
    }
//End AD4_UTENTEGrid Grid Tail

//AdmRichiestaPrintView Tail @1-FCB6E20C
}
//End AdmRichiestaPrintView Tail

