//RegistrazioneFineAction imports @1-E8C8C2AD
package common.RegistrazioneFine;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End RegistrazioneFineAction imports

//RegistrazioneFineAction class @1-6EFFE0E0
public class RegistrazioneFineAction extends Action {

//End RegistrazioneFineAction class

//RegistrazioneFineAction: method perform @1-F12E4070
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new RegistrazioneFineModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "RegistrazioneFineModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End RegistrazioneFineAction: method perform

//RegistrazioneFineAction: call children actions @1-8FA02E15
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End RegistrazioneFineAction: call children actions

//RegistrazioneFine Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End RegistrazioneFine Page: method validate

//RegistrazioneFineAction Tail @1-FCB6E20C
}
//End RegistrazioneFineAction Tail

