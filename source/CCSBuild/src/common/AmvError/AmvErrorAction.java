//AmvErrorAction imports @1-1B1B1073
package common.AmvError;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AmvErrorAction imports

//AmvErrorAction class @1-AC329B53
public class AmvErrorAction extends Action {

//End AmvErrorAction class

//AmvErrorAction: method perform @1-889943CF
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AmvErrorModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AmvErrorModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AmvErrorAction: method perform

//AmvErrorAction: call children actions @1-C8ED7B38
        if ( page.getChild( "Header" ).isVisible() ) {
            page.getRequest().setAttribute("HeaderParent",page);
            common.Header.HeaderAction Header = new common.Header.HeaderAction();
            result = result != null ? result : Header.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "Left" ).isVisible() ) {
            page.getRequest().setAttribute("LeftParent",page);
            common.Left.LeftAction Left = new common.Left.LeftAction();
            result = result != null ? result : Left.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            MESSAGGIO_ERROREClass MESSAGGIO_ERRORE = new MESSAGGIO_ERROREClass();
            MESSAGGIO_ERRORE.perform(page.getGrid("MESSAGGIO_ERRORE"));
        }
        if ( page.getChild( "Footer" ).isVisible() ) {
            page.getRequest().setAttribute("FooterParent",page);
            common.Footer.FooterAction Footer = new common.Footer.FooterAction();
            result = result != null ? result : Footer.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AmvErrorAction: call children actions

//MESSAGGIO_ERRORE Grid @5-2759EB47
    final class MESSAGGIO_ERROREClass {
        com.codecharge.components.Grid model;
        Event e;
//End MESSAGGIO_ERRORE Grid

//MESSAGGIO_ERRORE Grid: method perform @5-B48879D3
        protected String perform(com.codecharge.components.Grid model) {
            if ( ! model.isVisible() ) { return null; }
            this.model = model;
            //e = new ActionEvent( model, page );
            setProperties( model, Action.GET );
            setActivePermissions( model );
            if ( ! model.isVisible() ) return null;
            read();
            return null;
        }
//End MESSAGGIO_ERRORE Grid: method perform

//MESSAGGIO_ERRORE Grid: method read: head @5-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End MESSAGGIO_ERRORE Grid: method read: head

//MESSAGGIO_ERRORE Grid: method read: init @5-9083F520
            if ( ! model.allowRead ) return true;
            MESSAGGIO_ERROREDataObject ds = new MESSAGGIO_ERROREDataObject(page);
            ds.setComponent( model );
//End MESSAGGIO_ERRORE Grid: method read: init

//MESSAGGIO_ERRORE Grid: set where parameters @5-468C35E0
            try {
                ds.setUrlMVERR( page.getHttpGetParams().getParameter("MVERR"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'MVERR'" );
                return false;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'MVERR'" );
                return false;
            }
            ds.setSesModulo( SessionStorage.getInstance(req).getAttribute("Modulo") );
//End MESSAGGIO_ERRORE Grid: set where parameters

//MESSAGGIO_ERRORE Grid: set grid properties @5-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End MESSAGGIO_ERRORE Grid: set grid properties

//MESSAGGIO_ERRORE Grid: retrieve data @5-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End MESSAGGIO_ERRORE Grid: retrieve data

//MESSAGGIO_ERRORE Grid: check errors @5-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End MESSAGGIO_ERRORE Grid: check errors

//MESSAGGIO_ERRORE Grid: method read: tail @5-F575E732
            return ( ! isErrors );
        }
//End MESSAGGIO_ERRORE Grid: method read: tail

//MESSAGGIO_ERRORE Grid: method bind @5-90074F3B
        public void bind(com.codecharge.components.Component model, MESSAGGIO_ERRORERow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            MESSAGGIO_ERRORERow row = null;
            while ( counter < rows.length && rows[counter] != null ) {
                row = rows[counter++];
                HashMap hashRow = null;
                com.codecharge.components.Control c = null;
                boolean isNew = false;
                if ( childRows.hasNext() ) {
                    hashRow = (HashMap) childRows.next();
                    if ( hashRow == null ) {
                        hashRow = new HashMap();
                        isNew = true;
                    }
                } else {
                    hashRow = new HashMap();
                    isNew = true;
                }

                c = (com.codecharge.components.Control) hashRow.get("MSG");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("MSG").clone();
                    c.setValue(row.getMSG());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CUSTOM_MSG");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CUSTOM_MSG").clone();
                    c.setValue(row.getCUSTOM_MSG());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End MESSAGGIO_ERRORE Grid: method bind

//MESSAGGIO_ERRORE Directory: getRowFieldByName @5-A13D6DAA
        public Object getRowFieldByName( String name, MESSAGGIO_ERRORERow row ) {
            Object value = null;
            if ( "MSG".equals(name) ) {
                value = row.getMSG();
            } else if ( "CUSTOM_MSG".equals(name) ) {
                value = row.getCUSTOM_MSG();
            }
            return value;
        }
//End MESSAGGIO_ERRORE Directory: getRowFieldByName

//MESSAGGIO_ERRORE Grid: method validate @5-104025BA
        boolean validate() {
            return true;
        }
//End MESSAGGIO_ERRORE Grid: method validate

//MESSAGGIO_ERRORE Grid Tail @5-FCB6E20C
    }
//End MESSAGGIO_ERRORE Grid Tail

//AmvError Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AmvError Page: method validate

//AmvErrorAction Tail @1-FCB6E20C
}
//End AmvErrorAction Tail
