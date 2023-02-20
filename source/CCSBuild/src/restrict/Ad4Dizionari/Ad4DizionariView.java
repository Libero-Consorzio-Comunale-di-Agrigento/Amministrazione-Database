//Ad4DizionariView imports @1-8C0783F6
package restrict.Ad4Dizionari;

import com.codecharge.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.events.*;
import com.codecharge.validation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

//End Ad4DizionariView imports

//Ad4DizionariView class @1-1DC8C3E9
public class Ad4DizionariView extends View {
//End Ad4DizionariView class

//Ad4DizionariView: method show @1-AE1D5796
    public String show( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        this.page = (Ad4DizionariModel) req.getAttribute( "Ad4DizionariModel" );
        if (this.page == null) return "";
        String enc = (req.getAttribute(page.getName()+"Parent")!=null) ? ((com.codecharge.components.Page) req.getAttribute(page.getName()+"Parent")).getCharacterEncoding() : null;
        page.setCharacterEncoding("windows-1252");
        this.tmplPath = "/restrict/Ad4Dizionari.html";
        init(req, resp, context, page);
        if (req.getAttribute(page.getName()+"Parent")!=null) page.setCharacterEncoding(enc);
        String result = tmpl.render("main");
        page.fireBeforeUnloadEvent();
        if (!page.isVisible()) result="";
        return result;
    }
//End Ad4DizionariView: method show

//Ad4DizionariView Tail @1-FCB6E20C
}
//End Ad4DizionariView Tail

