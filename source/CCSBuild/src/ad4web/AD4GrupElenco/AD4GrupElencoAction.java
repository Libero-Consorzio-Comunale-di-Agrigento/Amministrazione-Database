//AD4GrupElencoAction imports @1-EF1DF2DC
package ad4web.AD4GrupElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GrupElencoAction imports

//AD4GrupElencoAction class @1-0BF351A9
public class AD4GrupElencoAction extends Action {

//End AD4GrupElencoAction class

//AD4GrupElencoAction: method perform @1-493DEC2E
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GrupElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GrupElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GrupElencoAction: method perform

//AD4GrupElencoAction: call children actions @1-E39CEE25
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
            DIRITTI_ACCESSOClass DIRITTI_ACCESSO = new DIRITTI_ACCESSOClass();
            DIRITTI_ACCESSO.perform(page.getGrid("DIRITTI_ACCESSO"));
        }
        if (result == null) {
            AD4_GRUPPIClass AD4_GRUPPI = new AD4_GRUPPIClass();
            AD4_GRUPPI.perform(page.getGrid("AD4_GRUPPI"));
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
//End AD4GrupElencoAction: call children actions

//DIRITTI_ACCESSO Grid @146-C8A51662
    final class DIRITTI_ACCESSOClass {
        com.codecharge.components.Grid model;
        Event e;
//End DIRITTI_ACCESSO Grid

//DIRITTI_ACCESSO Grid: method perform @146-B48879D3
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
//End DIRITTI_ACCESSO Grid: method perform

//DIRITTI_ACCESSO Grid: method read: head @146-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End DIRITTI_ACCESSO Grid: method read: head

//DIRITTI_ACCESSO Grid: method read: init @146-21027308
            if ( ! model.allowRead ) return true;
            DIRITTI_ACCESSODataObject ds = new DIRITTI_ACCESSODataObject(page);
            ds.setComponent( model );
//End DIRITTI_ACCESSO Grid: method read: init

//DIRITTI_ACCESSO Grid: set where parameters @146-B7309692
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV") );
            ds.setSesAD4GRUPPO( SessionStorage.getInstance(req).getAttribute("AD4GRUPPO") );
//End DIRITTI_ACCESSO Grid: set where parameters

//DIRITTI_ACCESSO Grid: set grid properties @146-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End DIRITTI_ACCESSO Grid: set grid properties

//DIRITTI_ACCESSO Grid: retrieve data @146-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End DIRITTI_ACCESSO Grid: retrieve data

//DIRITTI_ACCESSO Grid: check errors @146-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End DIRITTI_ACCESSO Grid: check errors

//DIRITTI_ACCESSO Grid: method read: tail @146-F575E732
            return ( ! isErrors );
        }
//End DIRITTI_ACCESSO Grid: method read: tail

//DIRITTI_ACCESSO Grid: method bind @146-6BADC354
        public void bind(com.codecharge.components.Component model, DIRITTI_ACCESSORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            DIRITTI_ACCESSORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("NOMINATIVO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOMINATIVO").clone();
                    c.setValue(row.getNOMINATIVO());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End DIRITTI_ACCESSO Grid: method bind

//DIRITTI_ACCESSO Directory: getRowFieldByName @146-4B4D1B62
        public Object getRowFieldByName( String name, DIRITTI_ACCESSORow row ) {
            Object value = null;
            if ( "NOMINATIVO".equals(name) ) {
                value = row.getNOMINATIVO();
            }
            return value;
        }
//End DIRITTI_ACCESSO Directory: getRowFieldByName

//DIRITTI_ACCESSO Grid: method validate @146-104025BA
        boolean validate() {
            return true;
        }
//End DIRITTI_ACCESSO Grid: method validate

//DIRITTI_ACCESSO Grid Tail @146-FCB6E20C
    }
//End DIRITTI_ACCESSO Grid Tail

//AD4_GRUPPI Grid @6-6F5C897C
    final class AD4_GRUPPIClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_GRUPPI Grid

//AD4_GRUPPI Grid: method perform @6-B48879D3
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
//End AD4_GRUPPI Grid: method perform

//AD4_GRUPPI Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_GRUPPI Grid: method read: head

//AD4_GRUPPI Grid: method read: init @6-71C226D0
            if ( ! model.allowRead ) return true;
            AD4_GRUPPIDataObject ds = new AD4_GRUPPIDataObject(page);
            ds.setComponent( model );
//End AD4_GRUPPI Grid: method read: init

//AD4_GRUPPI Grid: set where parameters @6-529ED611
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV") );
            ds.setSesAD4GRUPPO( SessionStorage.getInstance(req).getAttribute("AD4GRUPPO") );
            ds.setSesMVIDP( SessionStorage.getInstance(req).getAttribute("MVIDP") );
            ds.setSesMVAV( SessionStorage.getInstance(req).getAttribute("MVAV") );
//End AD4_GRUPPI Grid: set where parameters

//AD4_GRUPPI Grid: set grid properties @6-590D299A
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
            Hashtable sortAscColumns = new Hashtable();
            Hashtable sortDescColumns = new Hashtable();
            sortAscColumns.put( "Sorter_GRUPPO", "GRUPPO" );
            sortAscColumns.put( "Sorter_DESCRIZIONE", "DESCRIZIONE" );
            sortAscColumns.put( "Sorter_ISTANZA", "ISTANZA" );
            if ( ! StringUtils.isEmpty( model.getSort() ) ) {
                if ( "desc".equalsIgnoreCase(model.getDir())) {
                    if ( sortDescColumns.get( model.getSort() ) != null  && "desc".equalsIgnoreCase(model.getDir())) {
                        ds.setOrderBy( (String) sortDescColumns.get( model.getSort() ) );
                    } else {
                        ds.setOrderBy( (String) sortAscColumns.get( model.getSort() ) + " DESC " );
                    }
                } else {
                    ds.setOrderBy( (String) sortAscColumns.get( model.getSort() ) );
                }
            }
//End AD4_GRUPPI Grid: set grid properties

//AD4_GRUPPI Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_GRUPPI Grid: retrieve data

//AD4_GRUPPI Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_GRUPPI Grid: check errors

//AD4_GRUPPI Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_GRUPPI Grid: method read: tail

//AD4_GRUPPI Grid: method bind @6-3EDC950C
        public void bind(com.codecharge.components.Component model, AD4_GRUPPIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_GRUPPIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("GRUPPO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("GRUPPO").clone();
                    c.setValue(row.getGRUPPO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("UTENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UTENTE").clone();
                    c.setValue(row.getUTENTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("IMPORTANZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("IMPORTANZA").clone();
                    c.setValue(row.getIMPORTANZA());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_GRUPPI Grid: method bind

//AD4_GRUPPI Directory: getRowFieldByName @6-01B42639
        public Object getRowFieldByName( String name, AD4_GRUPPIRow row ) {
            Object value = null;
            if ( "GRUPPO".equals(name) ) {
                value = row.getGRUPPO();
            } else if ( "UTENTE".equals(name) ) {
                value = row.getUTENTE();
            } else if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "IMPORTANZA".equals(name) ) {
                value = row.getIMPORTANZA();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "MODULO".equals(name) ) {
                value = row.getMODULO();
            } else if ( "ISTANZA".equals(name) ) {
                value = row.getISTANZA();
            }
            return value;
        }
//End AD4_GRUPPI Directory: getRowFieldByName

//AD4_GRUPPI Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_GRUPPI Grid: method validate

//AD4_GRUPPI Grid Tail @6-FCB6E20C
    }
//End AD4_GRUPPI Grid Tail

//AD4GrupElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GrupElenco Page: method validate

//AD4GrupElencoAction Tail @1-FCB6E20C
}
//End AD4GrupElencoAction Tail
