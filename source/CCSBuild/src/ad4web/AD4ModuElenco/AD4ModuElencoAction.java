//AD4ModuElencoAction imports @1-8028A438
package ad4web.AD4ModuElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4ModuElencoAction imports

//AD4ModuElencoAction class @1-421E2623
public class AD4ModuElencoAction extends Action {

//End AD4ModuElencoAction class

//AD4ModuElencoAction: method perform @1-592E105C
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4ModuElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4ModuElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4ModuElencoAction: method perform

//AD4ModuElencoAction: call children actions @1-2184FBBA
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
            PROGETTIClass PROGETTI = new PROGETTIClass();
            PROGETTI.perform(page.getGrid("PROGETTI"));
        }
        if (result == null) {
            AD4_MODULIClass AD4_MODULI = new AD4_MODULIClass();
            AD4_MODULI.perform(page.getGrid("AD4_MODULI"));
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
//End AD4ModuElencoAction: call children actions

//PROGETTI Grid @62-835FB9FB
    final class PROGETTIClass {
        com.codecharge.components.Grid model;
        Event e;
//End PROGETTI Grid

//PROGETTI Grid: method perform @62-B48879D3
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
//End PROGETTI Grid: method perform

//PROGETTI Grid: method read: head @62-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End PROGETTI Grid: method read: head

//PROGETTI Grid: method read: init @62-F0D80E6B
            if ( ! model.allowRead ) return true;
            PROGETTIDataObject ds = new PROGETTIDataObject(page);
            ds.setComponent( model );
//End PROGETTI Grid: method read: init

//PROGETTI Grid: set where parameters @62-4200BFF8
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
//End PROGETTI Grid: set where parameters

//PROGETTI Grid: set grid properties @62-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End PROGETTI Grid: set grid properties

//PROGETTI Grid: retrieve data @62-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End PROGETTI Grid: retrieve data

//PROGETTI Grid: check errors @62-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End PROGETTI Grid: check errors

//PROGETTI Grid: method read: tail @62-F575E732
            return ( ! isErrors );
        }
//End PROGETTI Grid: method read: tail

//PROGETTI Grid: method bind @62-14C1F18E
        public void bind(com.codecharge.components.Component model, PROGETTIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            PROGETTIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("Nuovo");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Nuovo").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End PROGETTI Grid: method bind

//PROGETTI Directory: getRowFieldByName @62-846577E2
        public Object getRowFieldByName( String name, PROGETTIRow row ) {
            Object value = null;
            if ( "DESC_PROGETTO".equals(name) ) {
                value = row.getDESC_PROGETTO();
            } else if ( "Nuovo".equals(name) ) {
                value = row.getNuovo();
            } else if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
            }
            return value;
        }
//End PROGETTI Directory: getRowFieldByName

//PROGETTI Grid: method validate @62-104025BA
        boolean validate() {
            return true;
        }
//End PROGETTI Grid: method validate

//PROGETTI Grid Tail @62-FCB6E20C
    }
//End PROGETTI Grid Tail

//AD4_MODULI Grid @6-77C54FE6
    final class AD4_MODULIClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_MODULI Grid

//AD4_MODULI Grid: method perform @6-B48879D3
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
//End AD4_MODULI Grid: method perform

//AD4_MODULI Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_MODULI Grid: method read: head

//AD4_MODULI Grid: method read: init @6-A2DACE28
            if ( ! model.allowRead ) return true;
            AD4_MODULIDataObject ds = new AD4_MODULIDataObject(page);
            ds.setComponent( model );
//End AD4_MODULI Grid: method read: init

//AD4_MODULI Grid: set where parameters @6-023254CB
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
//End AD4_MODULI Grid: set where parameters

//AD4_MODULI Grid: set grid properties @6-F573B280
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
            Hashtable sortAscColumns = new Hashtable();
            Hashtable sortDescColumns = new Hashtable();
            sortAscColumns.put( "Sorter_MODULO", "MODULO" );
            sortAscColumns.put( "Sorter_DESCRIZIONE", "DESCRIZIONE" );
            sortAscColumns.put( "Sorter_AMMINISTRATORE", "AMMINISTRATORE" );
            sortAscColumns.put( "Sorter_NOTE", "NOTE" );
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
//End AD4_MODULI Grid: set grid properties

//AD4_MODULI Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_MODULI Grid: retrieve data

//AD4_MODULI Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_MODULI Grid: check errors

//AD4_MODULI Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_MODULI Grid: method read: tail

//AD4_MODULI Grid: method bind @6-D426094F
        public void bind(com.codecharge.components.Component model, AD4_MODULIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_MODULIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("MODULO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("MODULO").clone();
                    c.setValue(row.getMODULO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("MODULO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("PROGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROGETTO").clone();
                    c.setValue(row.getPROGETTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("AMMINISTRATORE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("AMMINISTRATORE").clone();
                    c.setValue(row.getAMMINISTRATORE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOTE").clone();
                    c.setValue(row.getNOTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CaratteristicheAccesso");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CaratteristicheAccesso").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").getSourceName(), row ));
                ((com.codecharge.components.ImageLink) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("MODULO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("Abilitazioni");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Abilitazioni").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("MODULO").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_MODULI Grid: method bind

//AD4_MODULI Directory: getRowFieldByName @6-EDD24861
        public Object getRowFieldByName( String name, AD4_MODULIRow row ) {
            Object value = null;
            if ( "MODULO".equals(name) ) {
                value = row.getMODULO();
            } else if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
            } else if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "AMMINISTRATORE".equals(name) ) {
                value = row.getAMMINISTRATORE();
            } else if ( "NOTE".equals(name) ) {
                value = row.getNOTE();
            } else if ( "CaratteristicheAccesso".equals(name) ) {
                value = row.getCaratteristicheAccesso();
            } else if ( "Abilitazioni".equals(name) ) {
                value = row.getAbilitazioni();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End AD4_MODULI Directory: getRowFieldByName

//AD4_MODULI Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_MODULI Grid: method validate

//AD4_MODULI Grid Tail @6-FCB6E20C
    }
//End AD4_MODULI Grid Tail

//AD4ModuElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4ModuElenco Page: method validate

//AD4ModuElencoAction Tail @1-FCB6E20C
}
//End AD4ModuElencoAction Tail

