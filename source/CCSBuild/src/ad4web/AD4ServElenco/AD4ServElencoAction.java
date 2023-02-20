//AD4ServElencoAction imports @1-4DE588CC
package ad4web.AD4ServElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4ServElencoAction imports

//AD4ServElencoAction class @1-4662CBA7
public class AD4ServElencoAction extends Action {

//End AD4ServElencoAction class

//AD4ServElencoAction: method perform @1-0ED6DA0F
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4ServElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4ServElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4ServElencoAction: method perform

//AD4ServElencoAction: call children actions @1-8DF238E8
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
            PROGETTOClass PROGETTO = new PROGETTOClass();
            PROGETTO.perform(page.getGrid("PROGETTO"));
        }
        if (result == null) {
            AD4_SERVIZIClass AD4_SERVIZI = new AD4_SERVIZIClass();
            AD4_SERVIZI.perform(page.getGrid("AD4_SERVIZI"));
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
//End AD4ServElencoAction: call children actions

//PROGETTO Grid @160-2D1BE15F
    final class PROGETTOClass {
        com.codecharge.components.Grid model;
        Event e;
//End PROGETTO Grid

//PROGETTO Grid: method perform @160-B48879D3
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
//End PROGETTO Grid: method perform

//PROGETTO Grid: method read: head @160-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End PROGETTO Grid: method read: head

//PROGETTO Grid: method read: init @160-59A585B8
            if ( ! model.allowRead ) return true;
            PROGETTODataObject ds = new PROGETTODataObject(page);
            ds.setComponent( model );
//End PROGETTO Grid: method read: init

//PROGETTO Grid: set where parameters @160-4200BFF8
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
//End PROGETTO Grid: set where parameters

//PROGETTO Grid: set grid properties @160-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End PROGETTO Grid: set grid properties

//PROGETTO Grid: retrieve data @160-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End PROGETTO Grid: retrieve data

//PROGETTO Grid: check errors @160-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End PROGETTO Grid: check errors

//PROGETTO Grid: method read: tail @160-F575E732
            return ( ! isErrors );
        }
//End PROGETTO Grid: method read: tail

//PROGETTO Grid: method bind @160-B26C8FAC
        public void bind(com.codecharge.components.Component model, PROGETTORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            PROGETTORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("DESC_PROGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_PROGETTO").clone();
                    c.setValue(row.getDESC_PROGETTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("AD4_DIRITTI_ACCESSO_Insert");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("AD4_DIRITTI_ACCESSO_Insert").clone();
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End PROGETTO Grid: method bind

//PROGETTO Directory: getRowFieldByName @160-BF00219D
        public Object getRowFieldByName( String name, PROGETTORow row ) {
            Object value = null;
            if ( "DESC_PROGETTO".equals(name) ) {
                value = row.getDESC_PROGETTO();
            } else if ( "AD4_DIRITTI_ACCESSO_Insert".equals(name) ) {
                value = row.getAD4_DIRITTI_ACCESSO_Insert();
            }
            return value;
        }
//End PROGETTO Directory: getRowFieldByName

//PROGETTO Grid: method validate @160-104025BA
        boolean validate() {
            return true;
        }
//End PROGETTO Grid: method validate

//PROGETTO Grid Tail @160-FCB6E20C
    }
//End PROGETTO Grid Tail

//AD4_SERVIZI Grid @6-AA415B3A
    final class AD4_SERVIZIClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_SERVIZI Grid

//AD4_SERVIZI Grid: method perform @6-B48879D3
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
//End AD4_SERVIZI Grid: method perform

//AD4_SERVIZI Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_SERVIZI Grid: method read: head

//AD4_SERVIZI Grid: method read: init @6-B3F32C98
            if ( ! model.allowRead ) return true;
            AD4_SERVIZIDataObject ds = new AD4_SERVIZIDataObject(page);
            ds.setComponent( model );
//End AD4_SERVIZI Grid: method read: init

//AD4_SERVIZI Grid: set where parameters @6-023254CB
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
//End AD4_SERVIZI Grid: set where parameters

//AD4_SERVIZI Grid: set grid properties @6-D104EB4A
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
            Hashtable sortAscColumns = new Hashtable();
            Hashtable sortDescColumns = new Hashtable();
            sortAscColumns.put( "Sorter_SEQUENZA", "SEQUENZA" );
            sortAscColumns.put( "Sorter_MODULO", "MODULO" );
            sortAscColumns.put( "Sorter_ISTANZA", "ISTANZA" );
            sortAscColumns.put( "Sorter_RUOLO", "RUOLO" );
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
//End AD4_SERVIZI Grid: set grid properties

//AD4_SERVIZI Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_SERVIZI Grid: retrieve data

//AD4_SERVIZI Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_SERVIZI Grid: check errors

//AD4_SERVIZI Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_SERVIZI Grid: method read: tail

//AD4_SERVIZI Grid: method bind @6-E18A25CA
        public void bind(com.codecharge.components.Component model, AD4_SERVIZIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_SERVIZIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("ID_SERVIZIO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ID_SERVIZIO").clone();
                    c.setValue(row.getID_SERVIZIO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ID_SERVIZIO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ID_SERVIZIO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DES_MODULO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_MODULO").clone();
                    c.setValue(row.getDES_MODULO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ID_SERVIZIO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ID_SERVIZIO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DES_ISTANZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_ISTANZA").clone();
                    c.setValue(row.getDES_ISTANZA());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ID_SERVIZIO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ID_SERVIZIO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DATI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATI").clone();
                    c.setValue(row.getDATI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Abilitazioni");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Abilitazioni").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").getSourceName(), row ));
                ((com.codecharge.components.ImageLink) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("MODULO").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_SERVIZI Grid: method bind

//AD4_SERVIZI Directory: getRowFieldByName @6-833B521D
        public Object getRowFieldByName( String name, AD4_SERVIZIRow row ) {
            Object value = null;
            if ( "ID_SERVIZIO".equals(name) ) {
                value = row.getID_SERVIZIO();
            } else if ( "DES_MODULO".equals(name) ) {
                value = row.getDES_MODULO();
            } else if ( "DES_ISTANZA".equals(name) ) {
                value = row.getDES_ISTANZA();
            } else if ( "DATI".equals(name) ) {
                value = row.getDATI();
            } else if ( "Abilitazioni".equals(name) ) {
                value = row.getAbilitazioni();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "ISTANZA".equals(name) ) {
                value = row.getISTANZA();
            } else if ( "MODULO".equals(name) ) {
                value = row.getMODULO();
            }
            return value;
        }
//End AD4_SERVIZI Directory: getRowFieldByName

//AD4_SERVIZI Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_SERVIZI Grid: method validate

//AD4_SERVIZI Grid Tail @6-FCB6E20C
    }
//End AD4_SERVIZI Grid Tail

//AD4ServElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4ServElenco Page: method validate

//AD4ServElencoAction Tail @1-FCB6E20C
}
//End AD4ServElencoAction Tail

