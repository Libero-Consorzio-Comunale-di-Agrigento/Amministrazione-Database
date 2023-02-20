//AD4UsjoElencoView imports @1-42AF3C09
package ad4web.AD4UsjoElenco;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End AD4UsjoElencoView imports

//AD4UsjoElencoView class @1-C8A6AA36
public class AD4UsjoElencoView extends View {
//End AD4UsjoElencoView class

//AD4UsjoElencoView: method show @1-33C4FE32
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (AD4UsjoElencoModel) req.getAttribute( "AD4UsjoElencoModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/ad4web/AD4UsjoElenco.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        if ( page.getChild( "Header" ).isVisible() ) {
            common.Header.HeaderView Header = new common.Header.HeaderView();
            tmpl.setVar( "main/@Header", Header.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "Left" ).isVisible() ) {
            common.Left.LeftView Left = new common.Left.LeftView();
            tmpl.setVar( "main/@Left", Left.show( req, resp, context ));
            page.setCookies();
        }
        if ( page.getChild( "Guida" ).isVisible() ) {
            common.Guida.GuidaView Guida = new common.Guida.GuidaView();
            tmpl.setVar( "main/@Guida", Guida.show( req, resp, context ));
            page.setCookies();
        }
        user_jobsClass user_jobs = new user_jobsClass();
        user_jobs.show(page.getGrid("user_jobs"));
        if ( page.getChild( "Footer" ).isVisible() ) {
            common.Footer.FooterView Footer = new common.Footer.FooterView();
            tmpl.setVar( "main/@Footer", Footer.show( req, resp, context ));
            page.setCookies();
        }
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End AD4UsjoElencoView: method show

// //user_jobs Grid @6-F81417CB

//user_jobsClass head @6-71CA990E
    final class user_jobsClass {
//End user_jobsClass head

// //user_jobs Grid: method show @6-F81417CB

//show head @6-F82693AB
        void show(com.codecharge.components.Grid model) {
            ArrayList rowControls = new ArrayList();
            rowControls.add("JOB");
            rowControls.add("PROGETTO");
            rowControls.add("WHAT");
            rowControls.add("THIS_DATE");
            rowControls.add("NEXT_DATE");
            rowControls.add("LAST_DATE");
            rowControls.add("FAILURES");
            rowControls.add("TOTAL_TIME");
            rowControls.add("INTERVAL");
            rowControls.add("BROKEN");
            ArrayList altRowControls = new ArrayList();
            ArrayList staticControls = new ArrayList();
            staticControls.add("Sorter_JOB");
            staticControls.add("Sorter_WHAT");
            staticControls.add("Sorter_THIS_DATE");
            staticControls.add("Sorter_NEXT_DATE");
            staticControls.add("Sorter_LAST_DATE");
            staticControls.add("Sorter_FAILURES");
            staticControls.add("Sorter_TOTAL_TIME");
            staticControls.add("Sorter_INTERVAL");
            staticControls.add("Sorter_BROKEN");
            staticControls.add("AFCNavigator");
            view.show(model,staticControls,rowControls,altRowControls,false,false);
        }
//End show head

//user_jobs Grid Tail @6-FCB6E20C
    }
//End user_jobs Grid Tail

//AD4UsjoElencoView Tail @1-FCB6E20C
}
//End AD4UsjoElencoView Tail
