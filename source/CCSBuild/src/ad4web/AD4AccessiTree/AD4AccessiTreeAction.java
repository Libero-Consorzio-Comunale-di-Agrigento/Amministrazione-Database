//AD4AccessiTreeAction imports @1-5FAC5B39
package ad4web.AD4AccessiTree;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4AccessiTreeAction imports

//AD4AccessiTreeAction class @1-DE880794
public class AD4AccessiTreeAction extends Action {

//End AD4AccessiTreeAction class

//AD4AccessiTreeAction: method perform @1-605E2D8B
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4AccessiTreeModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4AccessiTreeModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4AccessiTreeAction: method perform

//AD4AccessiTreeAction: call children actions @1-B353ED5C
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
            AD4_ACCESSIClass AD4_ACCESSI = new AD4_ACCESSIClass();
            AD4_ACCESSI.perform(page.getGrid("AD4_ACCESSI"));
        }
        if (result == null) {
            TITOLOClass TITOLO = new TITOLOClass();
            TITOLO.perform(page.getGrid("TITOLO"));
        }
        if (result == null) {
            AccessiDettaglioClass AccessiDettaglio = new AccessiDettaglioClass();
            AccessiDettaglio.perform(page.getGrid("AccessiDettaglio"));
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
//End AD4AccessiTreeAction: call children actions

//AD4_ACCESSI Grid @6-6B81A9EF
    final class AD4_ACCESSIClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_ACCESSI Grid

//AD4_ACCESSI Grid: method perform @6-B48879D3
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
//End AD4_ACCESSI Grid: method perform

//AD4_ACCESSI Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_ACCESSI Grid: method read: head

//AD4_ACCESSI Grid: method read: init @6-F2FD6CCB
            if ( ! model.allowRead ) return true;
            AD4_ACCESSIDataObject ds = new AD4_ACCESSIDataObject(page);
            ds.setComponent( model );
//End AD4_ACCESSI Grid: method read: init

//AD4_ACCESSI Grid: set where parameters @6-99A28D78
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
            ds.setUrlID_OLD( page.getHttpGetParams().getParameter("ID_OLD") );
//End AD4_ACCESSI Grid: set where parameters

//AD4_ACCESSI Grid: set grid properties @6-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4_ACCESSI Grid: set grid properties

//AD4_ACCESSI Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_ACCESSI Grid: retrieve data

//AD4_ACCESSI Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_ACCESSI Grid: check errors

//AD4_ACCESSI Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_ACCESSI Grid: method read: tail

//AD4_ACCESSI Grid: method bind @6-090AC04C
        public void bind(com.codecharge.components.Component model, AD4_ACCESSIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_ACCESSIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("ALBERO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ALBERO").clone();
                    c.setValue(row.getALBERO());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_ACCESSI Grid: method bind

//AD4_ACCESSI Directory: getRowFieldByName @6-ED60B745
        public Object getRowFieldByName( String name, AD4_ACCESSIRow row ) {
            Object value = null;
            if ( "ALBERO".equals(name) ) {
                value = row.getALBERO();
            }
            return value;
        }
//End AD4_ACCESSI Directory: getRowFieldByName

//AD4_ACCESSI Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_ACCESSI Grid: method validate

//AD4_ACCESSI Grid Tail @6-FCB6E20C
    }
//End AD4_ACCESSI Grid Tail

//TITOLO Grid @31-66B5150A
    final class TITOLOClass {
        com.codecharge.components.Grid model;
        Event e;
//End TITOLO Grid

//TITOLO Grid: method perform @31-B48879D3
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
//End TITOLO Grid: method perform

//TITOLO Grid: method read: head @31-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End TITOLO Grid: method read: head

//TITOLO Grid: method read: init @31-5E70F9B5
            if ( ! model.allowRead ) return true;
            TITOLODataObject ds = new TITOLODataObject(page);
            ds.setComponent( model );
//End TITOLO Grid: method read: init

//TITOLO Grid: set where parameters @31-53872647
            ds.setUrlUTENTE( page.getHttpGetParams().getParameter("UTENTE") );
//End TITOLO Grid: set where parameters

//TITOLO Grid: set grid properties @31-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End TITOLO Grid: set grid properties

//TITOLO Grid: retrieve data @31-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End TITOLO Grid: retrieve data

//TITOLO Grid: check errors @31-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End TITOLO Grid: check errors

//TITOLO Grid: method read: tail @31-F575E732
            return ( ! isErrors );
        }
//End TITOLO Grid: method read: tail

//TITOLO Grid: method bind @31-94DBE635
        public void bind(com.codecharge.components.Component model, TITOLORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            TITOLORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("UTENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UTENTE").clone();
                    c.setValue(row.getUTENTE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End TITOLO Grid: method bind

//TITOLO Directory: getRowFieldByName @31-4BB97068
        public Object getRowFieldByName( String name, TITOLORow row ) {
            Object value = null;
            if ( "UTENTE".equals(name) ) {
                value = row.getUTENTE();
            }
            return value;
        }
//End TITOLO Directory: getRowFieldByName

//TITOLO Grid: method validate @31-104025BA
        boolean validate() {
            return true;
        }
//End TITOLO Grid: method validate

//TITOLO Grid Tail @31-FCB6E20C
    }
//End TITOLO Grid Tail

//AccessiDettaglio Grid @22-B9D84E55
    final class AccessiDettaglioClass {
        com.codecharge.components.Grid model;
        Event e;
//End AccessiDettaglio Grid

//AccessiDettaglio Grid: method perform @22-B48879D3
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
//End AccessiDettaglio Grid: method perform

//AccessiDettaglio Grid: method read: head @22-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AccessiDettaglio Grid: method read: head

//AccessiDettaglio Grid: method read: init @22-F851FE6A
            if ( ! model.allowRead ) return true;
            AccessiDettaglioDataObject ds = new AccessiDettaglioDataObject(page);
            ds.setComponent( model );
//End AccessiDettaglio Grid: method read: init

//AccessiDettaglio Grid: set where parameters @22-CFEC1A97
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
            ds.setSesProgetto( SessionStorage.getInstance(req).getAttribute("Progetto") );
            ds.setUrlUTENTE( page.getHttpGetParams().getParameter("UTENTE") );
//End AccessiDettaglio Grid: set where parameters

//AccessiDettaglio Grid: set grid properties @22-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AccessiDettaglio Grid: set grid properties

//AccessiDettaglio Grid: retrieve data @22-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AccessiDettaglio Grid: retrieve data

//AccessiDettaglio Grid: check errors @22-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AccessiDettaglio Grid: check errors

//AccessiDettaglio Grid: method read: tail @22-F575E732
            return ( ! isErrors );
        }
//End AccessiDettaglio Grid: method read: tail

//AccessiDettaglio Grid: method bind @22-CFC0D7A9
        public void bind(com.codecharge.components.Component model, AccessiDettaglioRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AccessiDettaglioRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("DES_ACCESSO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_ACCESSO").clone();
                    c.setValue(row.getDES_ACCESSO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DES_ORA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_ORA").clone();
                    c.setValue(row.getDES_ORA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOTE").clone();
                    c.setValue(row.getNOTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DSP_SESSIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DSP_SESSIONE").clone();
                    c.setValue(row.getDSP_SESSIONE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AccessiDettaglio Grid: method bind

//AccessiDettaglio Directory: getRowFieldByName @22-7D29AA01
        public Object getRowFieldByName( String name, AccessiDettaglioRow row ) {
            Object value = null;
            if ( "DES_ACCESSO".equals(name) ) {
                value = row.getDES_ACCESSO();
            } else if ( "DES_ORA".equals(name) ) {
                value = row.getDES_ORA();
            } else if ( "NOTE".equals(name) ) {
                value = row.getNOTE();
            } else if ( "DSP_SESSIONE".equals(name) ) {
                value = row.getDSP_SESSIONE();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End AccessiDettaglio Directory: getRowFieldByName

//AccessiDettaglio Grid: method validate @22-104025BA
        boolean validate() {
            return true;
        }
//End AccessiDettaglio Grid: method validate

//AccessiDettaglio Grid Tail @22-FCB6E20C
    }
//End AccessiDettaglio Grid Tail

//AD4AccessiTree Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4AccessiTree Page: method validate

//AD4AccessiTreeAction Tail @1-FCB6E20C
}
//End AD4AccessiTreeAction Tail
