//AD4GuidaIstanzaView imports @1-F9D20948
package ad4web.AD4GuidaIstanza;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4GuidaIstanzaView imports

//AD4GuidaIstanzaView class @1-4A97FE1D
public class AD4GuidaIstanzaView extends View {
//End AD4GuidaIstanzaView class

//AD4GuidaIstanzaView: method show @1-2794AE77
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4GuidaIstanzaModel) req.getAttribute( "AD4GuidaIstanzaModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4GuidaIstanza.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        NewGrid1Class NewGrid1 = new NewGrid1Class();
        NewGrid1.show(page.getGrid("NewGrid1"));
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4GuidaIstanzaView: method show

// //NewGrid1 Grid @2-F81417CB

//NewGrid1Class head @2-55E5A62A
    final class NewGrid1Class {
//End NewGrid1Class head

// //NewGrid1 Grid: method show @2-F81417CB

//show head @2-493E5B75
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("FOLDER");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//NewGrid1 Grid Tail @2-FCB6E20C
    }
//End NewGrid1 Grid Tail

//AD4GuidaIstanzaView Tail @1-FCB6E20C
}
//End AD4GuidaIstanzaView Tail

