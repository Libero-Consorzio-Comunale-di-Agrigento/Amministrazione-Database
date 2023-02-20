//AD4RichiestaParametriAction imports @1-DC80B6B5
package ad4web.AD4RichiestaParametri;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4RichiestaParametriAction imports

//AD4RichiestaParametriAction class @1-C3A33EF0
public class AD4RichiestaParametriAction extends Action {

//End AD4RichiestaParametriAction class

//AD4RichiestaParametriAction: method perform @1-41CF30EB
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4RichiestaParametriModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4RichiestaParametriModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4RichiestaParametriAction: method perform

//AD4RichiestaParametriAction: call children actions @1-915D4539
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
            TitoloClass Titolo = new TitoloClass();
            Titolo.perform(page.getGrid("Titolo"));
        }
        if (result == null) {
            PARAMETRI_RICHIESTAClass PARAMETRI_RICHIESTA = new PARAMETRI_RICHIESTAClass();
            PARAMETRI_RICHIESTA.perform(page.getGrid("PARAMETRI_RICHIESTA"));
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
//End AD4RichiestaParametriAction: call children actions

//Titolo Grid @135-AAABC7C5
    final class TitoloClass {
        com.codecharge.components.Grid model;
        Event e;
//End Titolo Grid

//Titolo Grid: method perform @135-B48879D3
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
//End Titolo Grid: method perform

//Titolo Grid: method read: head @135-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Titolo Grid: method read: head

//Titolo Grid: method read: init @135-60673B77
            if ( ! model.allowRead ) return true;
            TitoloDataObject ds = new TitoloDataObject(page);
            ds.setComponent( model );
//End Titolo Grid: method read: init

//Titolo Grid: set grid properties @135-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Titolo Grid: set grid properties

//Titolo Grid: retrieve data @135-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Titolo Grid: retrieve data

//Titolo Grid: check errors @135-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Titolo Grid: check errors

//Titolo Grid: method read: tail @135-F575E732
            return ( ! isErrors );
        }
//End Titolo Grid: method read: tail

//Titolo Grid: method bind @135-4C77DBBD
        public void bind(com.codecharge.components.Component model, TitoloRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            TitoloRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("Nuovo");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Nuovo").clone();
                    c.setValue(row.getNuovo());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End Titolo Grid: method bind

//Titolo Directory: getRowFieldByName @135-4A5C024C
        public Object getRowFieldByName( String name, TitoloRow row ) {
            Object value = null;
            if ( "Nuovo".equals(name) ) {
                value = row.getNuovo();
            }
            return value;
        }
//End Titolo Directory: getRowFieldByName

//Titolo Grid: method validate @135-104025BA
        boolean validate() {
            return true;
        }
//End Titolo Grid: method validate

//Titolo Grid Tail @135-FCB6E20C
    }
//End Titolo Grid Tail

//PARAMETRI_RICHIESTA Grid @6-9035EC8E
    final class PARAMETRI_RICHIESTAClass {
        com.codecharge.components.Grid model;
        Event e;
//End PARAMETRI_RICHIESTA Grid

//PARAMETRI_RICHIESTA Grid: method perform @6-B48879D3
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
//End PARAMETRI_RICHIESTA Grid: method perform

//PARAMETRI_RICHIESTA Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End PARAMETRI_RICHIESTA Grid: method read: head

//PARAMETRI_RICHIESTA Grid: method read: init @6-AD7ABB13
            if ( ! model.allowRead ) return true;
            PARAMETRI_RICHIESTADataObject ds = new PARAMETRI_RICHIESTADataObject(page);
            ds.setComponent( model );
//End PARAMETRI_RICHIESTA Grid: method read: init

//PARAMETRI_RICHIESTA Grid: set where parameters @6-6748F876
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
//End PARAMETRI_RICHIESTA Grid: set where parameters

//PARAMETRI_RICHIESTA Grid: set grid properties @6-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End PARAMETRI_RICHIESTA Grid: set grid properties

//PARAMETRI_RICHIESTA Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End PARAMETRI_RICHIESTA Grid: retrieve data

//PARAMETRI_RICHIESTA Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End PARAMETRI_RICHIESTA Grid: check errors

//PARAMETRI_RICHIESTA Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End PARAMETRI_RICHIESTA Grid: method read: tail

//PARAMETRI_RICHIESTA Grid: method bind @6-73CC257F
        public void bind(com.codecharge.components.Component model, PARAMETRI_RICHIESTARow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            PARAMETRI_RICHIESTARow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("ID_PARAMETRO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ID_PARAMETRO").clone();
                    c.setValue(row.getID_PARAMETRO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ID_RICHIESTA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ID_RICHIESTA").clone();
                    c.setValue(row.getID_RICHIESTA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PARAMETRO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PARAMETRO").clone();
                    c.setValue(row.getPARAMETRO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("IDPAR").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("IDPAR").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("VALORE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("VALORE").clone();
                    c.setValue(row.getVALORE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Indietro");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Indietro").clone();
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End PARAMETRI_RICHIESTA Grid: method bind

//PARAMETRI_RICHIESTA Directory: getRowFieldByName @6-E48C6F33
        public Object getRowFieldByName( String name, PARAMETRI_RICHIESTARow row ) {
            Object value = null;
            if ( "ID_PARAMETRO".equals(name) ) {
                value = row.getID_PARAMETRO();
            } else if ( "ID_RICHIESTA".equals(name) ) {
                value = row.getID_RICHIESTA();
            } else if ( "PARAMETRO".equals(name) ) {
                value = row.getPARAMETRO();
            } else if ( "VALORE".equals(name) ) {
                value = row.getVALORE();
            } else if ( "Indietro".equals(name) ) {
                value = row.getIndietro();
            } else if ( "IDPAR".equals(name) ) {
                value = row.getIDPAR();
            }
            return value;
        }
//End PARAMETRI_RICHIESTA Directory: getRowFieldByName

//PARAMETRI_RICHIESTA Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End PARAMETRI_RICHIESTA Grid: method validate

//PARAMETRI_RICHIESTA Grid Tail @6-FCB6E20C
    }
//End PARAMETRI_RICHIESTA Grid Tail

//AD4RichiestaParametri Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4RichiestaParametri Page: method validate

//AD4RichiestaParametriAction Tail @1-FCB6E20C
}
//End AD4RichiestaParametriAction Tail
