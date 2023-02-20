//AmvDatiRegistrazioneView imports @1-7D48567F
package common.AmvDatiRegistrazione;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AmvDatiRegistrazioneView imports

//AmvDatiRegistrazioneView class @1-C5E58836
public class AmvDatiRegistrazioneView extends View {
//End AmvDatiRegistrazioneView class

//AmvDatiRegistrazioneView: method show @1-110DEF68
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AmvDatiRegistrazioneModel) req.getAttribute( "AmvDatiRegistrazioneModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/common/AmvDatiRegistrazione.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        AD4_UTENTEGridClass AD4_UTENTEGrid = new AD4_UTENTEGridClass();
        AD4_UTENTEGrid.show(page.getGrid("AD4_UTENTEGrid"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AmvDatiRegistrazioneView: method show

// //AD4_UTENTEGrid Grid @2-F81417CB

//AD4_UTENTEGridClass head @2-37151883
    final class AD4_UTENTEGridClass {
//End AD4_UTENTEGridClass head

// //AD4_UTENTEGrid Grid: method show @2-F81417CB

//show head @2-CAA453A1
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
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
            rowControls.add("TIPO_NOTIFICA");
            rowControls.add("INDIRIZZO_NOTIFICA");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//AD4_UTENTEGrid Grid Tail @2-FCB6E20C
    }
//End AD4_UTENTEGrid Grid Tail

//AmvDatiRegistrazioneView Tail @1-FCB6E20C
}
//End AmvDatiRegistrazioneView Tail

