//AD4AmbienteAction imports @1-D2B95308
package ad4web.AD4Ambiente;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4AmbienteAction imports

//AD4AmbienteAction class @1-6988E02D
public class AD4AmbienteAction extends Action {

//End AD4AmbienteAction class

//AD4AmbienteAction: method perform @1-E896D28D
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4AmbienteModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4AmbienteModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4AmbienteAction: method perform

//AD4AmbienteAction: call children actions @1-CB2D324A
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
        if ( page.getChild( "Guida" ).isVisible() ) {
            page.getRequest().setAttribute("GuidaParent",page);
            common.Guida.GuidaAction Guida = new common.Guida.GuidaAction();
            result = result != null ? result : Guida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            AD4_AMBIENTEClass AD4_AMBIENTE = new AD4_AMBIENTEClass();
            AD4_AMBIENTE.perform(page.getGrid("AD4_AMBIENTE"));
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
//End AD4AmbienteAction: call children actions

//AD4_AMBIENTE Grid @6-D5201C16
    final class AD4_AMBIENTEClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_AMBIENTE Grid

//AD4_AMBIENTE Grid: method perform @6-B48879D3
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
//End AD4_AMBIENTE Grid: method perform

//AD4_AMBIENTE Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_AMBIENTE Grid: method read: head

//AD4_AMBIENTE Grid: method read: init @6-0B374A5F
            if ( ! model.allowRead ) return true;
            AD4_AMBIENTEDataObject ds = new AD4_AMBIENTEDataObject(page);
            ds.setComponent( model );
//End AD4_AMBIENTE Grid: method read: init

//AD4_AMBIENTE Grid: set where parameters @6-50066C6C
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setUrlSERVIZIO( page.getHttpGetParams().getParameter("SERVIZIO") );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
//End AD4_AMBIENTE Grid: set where parameters

//AD4_AMBIENTE Grid: set grid properties @6-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4_AMBIENTE Grid: set grid properties

//AD4_AMBIENTE Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_AMBIENTE Grid: retrieve data

//AD4_AMBIENTE Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_AMBIENTE Grid: check errors

//AD4_AMBIENTE Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_AMBIENTE Grid: method read: tail

//AD4_AMBIENTE Grid: method bind @6-3059B1FF
        public void bind(com.codecharge.components.Component model, AD4_AMBIENTERow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_AMBIENTERow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("AMBIENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("AMBIENTE").clone();
                    c.setValue(row.getAMBIENTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("EXTERNAL_AUTENTICATION");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("EXTERNAL_AUTENTICATION").clone();
                    c.setValue(row.getEXTERNAL_AUTENTICATION());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_AMBIENTE Grid: method bind

//AD4_AMBIENTE Directory: getRowFieldByName @6-A60E3939
        public Object getRowFieldByName( String name, AD4_AMBIENTERow row ) {
            Object value = null;
            if ( "AMBIENTE".equals(name) ) {
                value = row.getAMBIENTE();
            } else if ( "EXTERNAL_AUTENTICATION".equals(name) ) {
                value = row.getEXTERNAL_AUTENTICATION();
            }
            return value;
        }
//End AD4_AMBIENTE Directory: getRowFieldByName

//AD4_AMBIENTE Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_AMBIENTE Grid: method validate

//AD4_AMBIENTE Grid Tail @6-FCB6E20C
    }
//End AD4_AMBIENTE Grid Tail

//AD4Ambiente Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Ambiente Page: method validate

//AD4AmbienteAction Tail @1-FCB6E20C
}
//End AD4AmbienteAction Tail

