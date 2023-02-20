//Ad4DizionariBancheGuidaView imports @1-968EDB3B
package restrict.Ad4DizionariBancheGuida;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariBancheGuidaView imports

//Ad4DizionariBancheGuidaView class @1-4C353FEE
public class Ad4DizionariBancheGuidaView extends View {
//End Ad4DizionariBancheGuidaView class

//Ad4DizionariBancheGuidaView: method show @1-224A8CCF
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariBancheGuidaModel) req.getAttribute( "Ad4DizionariBancheGuidaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4DizionariBancheGuida.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        GuidaClass Guida = new GuidaClass();
        Guida.show(page.getGrid("Guida"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariBancheGuidaView: method show

// //Guida Grid @2-F81417CB

//GuidaClass head @2-E5123A95
    final class GuidaClass {
//End GuidaClass head

// //Guida Grid: method show @2-F81417CB

//show head @2-DFF17537
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("FOLDER1");
            rowControls.add("FOLDER2");
            rowControls.add("FOLDER5");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//Guida Grid Tail @2-FCB6E20C
    }
//End Guida Grid Tail

//Ad4DizionariBancheGuidaView Tail @1-FCB6E20C
}
//End Ad4DizionariBancheGuidaView Tail
