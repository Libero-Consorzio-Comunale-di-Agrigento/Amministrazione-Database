//AD4RegistroElencoAction imports @1-95514CB6
package ad4web.AD4RegistroElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4RegistroElencoAction imports

//AD4RegistroElencoAction class @1-567CEF48
public class AD4RegistroElencoAction extends Action {

//End AD4RegistroElencoAction class

//AD4RegistroElencoAction: method perform @1-FA10D5AC
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4RegistroElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4RegistroElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4RegistroElencoAction: method perform

//AD4RegistroElencoAction: call children actions @1-6D1F410A
        if (result == null) {
            AD4_REGISTRO_SELClass AD4_REGISTRO_SEL = new AD4_REGISTRO_SELClass();
            AD4_REGISTRO_SEL.perform(page.getGrid("AD4_REGISTRO_SEL"));
        }
        if (result == null) {
            ELENCO_CHIAVIClass ELENCO_CHIAVI = new ELENCO_CHIAVIClass();
            ELENCO_CHIAVI.perform(page.getGrid("ELENCO_CHIAVI"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4RegistroElencoAction: call children actions

//AD4_REGISTRO_SEL Grid @10-7A2A8B11
    final class AD4_REGISTRO_SELClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_REGISTRO_SEL Grid

//AD4_REGISTRO_SEL Grid: method perform @10-B48879D3
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
//End AD4_REGISTRO_SEL Grid: method perform

//AD4_REGISTRO_SEL Grid: method read: head @10-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_REGISTRO_SEL Grid: method read: head

//AD4_REGISTRO_SEL Grid: method read: init @10-D914F6BA
            if ( ! model.allowRead ) return true;
            AD4_REGISTRO_SELDataObject ds = new AD4_REGISTRO_SELDataObject(page);
            ds.setComponent( model );
//End AD4_REGISTRO_SEL Grid: method read: init

//AD4_REGISTRO_SEL Grid: set where parameters @10-6748F876
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
//End AD4_REGISTRO_SEL Grid: set where parameters

//AD4_REGISTRO_SEL Grid: set grid properties @10-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4_REGISTRO_SEL Grid: set grid properties

//AD4_REGISTRO_SEL Grid: retrieve data @10-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_REGISTRO_SEL Grid: retrieve data

//AD4_REGISTRO_SEL Grid: check errors @10-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_REGISTRO_SEL Grid: check errors

//AD4_REGISTRO_SEL Grid: method read: tail @10-F575E732
            return ( ! isErrors );
        }
//End AD4_REGISTRO_SEL Grid: method read: tail

//AD4_REGISTRO_SEL Grid: method bind @10-E7003C35
        public void bind(com.codecharge.components.Component model, AD4_REGISTRO_SELRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_REGISTRO_SELRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("NOME_SEZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOME_SEZIONE").clone();
                    c.setValue(row.getNOME_SEZIONE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_REGISTRO_SEL Grid: method bind

//AD4_REGISTRO_SEL Directory: getRowFieldByName @10-8869D683
        public Object getRowFieldByName( String name, AD4_REGISTRO_SELRow row ) {
            Object value = null;
            if ( "NOME_SEZIONE".equals(name) ) {
                value = row.getNOME_SEZIONE();
            }
            return value;
        }
//End AD4_REGISTRO_SEL Directory: getRowFieldByName

//AD4_REGISTRO_SEL Grid: method validate @10-104025BA
        boolean validate() {
            return true;
        }
//End AD4_REGISTRO_SEL Grid: method validate

//AD4_REGISTRO_SEL Grid Tail @10-FCB6E20C
    }
//End AD4_REGISTRO_SEL Grid Tail

//ELENCO_CHIAVI Grid @2-A7BFE2CB
    final class ELENCO_CHIAVIClass {
        com.codecharge.components.Grid model;
        Event e;
//End ELENCO_CHIAVI Grid

//ELENCO_CHIAVI Grid: method perform @2-B48879D3
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
//End ELENCO_CHIAVI Grid: method perform

//ELENCO_CHIAVI Grid: method read: head @2-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End ELENCO_CHIAVI Grid: method read: head

//ELENCO_CHIAVI Grid: method read: init @2-89D4A1E6
            if ( ! model.allowRead ) return true;
            ELENCO_CHIAVIDataObject ds = new ELENCO_CHIAVIDataObject(page);
            ds.setComponent( model );
//End ELENCO_CHIAVI Grid: method read: init

//ELENCO_CHIAVI Grid: set where parameters @2-4ED53878
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
            ds.setUrlSTRINGA( page.getHttpGetParams().getParameter("STRINGA") );
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
//End ELENCO_CHIAVI Grid: set where parameters

//ELENCO_CHIAVI Grid: set grid properties @2-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End ELENCO_CHIAVI Grid: set grid properties

//ELENCO_CHIAVI Grid: retrieve data @2-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End ELENCO_CHIAVI Grid: retrieve data

//ELENCO_CHIAVI Grid: check errors @2-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End ELENCO_CHIAVI Grid: check errors

//ELENCO_CHIAVI Grid: method read: tail @2-F575E732
            return ( ! isErrors );
        }
//End ELENCO_CHIAVI Grid: method read: tail

//ELENCO_CHIAVI Grid: method bind @2-EFB16251
        public void bind(com.codecharge.components.Component model, ELENCO_CHIAVIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ELENCO_CHIAVIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("STRINGA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("STRINGA").clone();
                    c.setValue(row.getSTRINGA());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("STRINGA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("STRINGA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("VALORE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("VALORE").clone();
                    c.setValue(row.getVALORE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("COMMENTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COMMENTO").clone();
                    c.setValue(row.getCOMMENTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CHIAVE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CHIAVE").clone();
                    c.setValue(row.getCHIAVE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End ELENCO_CHIAVI Grid: method bind

//ELENCO_CHIAVI Directory: getRowFieldByName @2-0627D3BF
        public Object getRowFieldByName( String name, ELENCO_CHIAVIRow row ) {
            Object value = null;
            if ( "STRINGA".equals(name) ) {
                value = row.getSTRINGA();
            } else if ( "VALORE".equals(name) ) {
                value = row.getVALORE();
            } else if ( "COMMENTO".equals(name) ) {
                value = row.getCOMMENTO();
            } else if ( "CHIAVE".equals(name) ) {
                value = row.getCHIAVE();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End ELENCO_CHIAVI Directory: getRowFieldByName

//ELENCO_CHIAVI Grid: method validate @2-104025BA
        boolean validate() {
            return true;
        }
//End ELENCO_CHIAVI Grid: method validate

//ELENCO_CHIAVI Grid Tail @2-FCB6E20C
    }
//End ELENCO_CHIAVI Grid Tail

//AD4RegistroElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4RegistroElenco Page: method validate

//AD4RegistroElencoAction Tail @1-FCB6E20C
}
//End AD4RegistroElencoAction Tail
