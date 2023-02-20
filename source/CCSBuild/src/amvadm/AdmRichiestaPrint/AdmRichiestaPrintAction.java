//AdmRichiestaPrintAction imports @1-9979701C
package amvadm.AdmRichiestaPrint;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AdmRichiestaPrintAction imports

//AdmRichiestaPrintAction class @1-97F1EB4B
public class AdmRichiestaPrintAction extends Action {

//End AdmRichiestaPrintAction class

//AdmRichiestaPrintAction: method perform @1-FE39678E
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AdmRichiestaPrintModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AdmRichiestaPrintModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AdmRichiestaPrintAction: method perform

//AdmRichiestaPrintAction: call children actions @1-E6823D0E
        if (result == null) {
            AD4_UTENTEGridClass AD4_UTENTEGrid = new AD4_UTENTEGridClass();
            AD4_UTENTEGrid.perform(page.getGrid("AD4_UTENTEGrid"));
        }
        if ( page.getChild( "AmvFooter" ).isVisible() ) {
            page.getRequest().setAttribute("AmvFooterParent",page);
            common.AmvFooter.AmvFooterAction AmvFooter = new common.AmvFooter.AmvFooterAction();
            result = result != null ? result : AmvFooter.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AdmRichiestaPrintAction: call children actions

//AD4_UTENTEGrid Grid @71-455F991D
    final class AD4_UTENTEGridClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_UTENTEGrid Grid

//AD4_UTENTEGrid Grid: method perform @71-B48879D3
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
//End AD4_UTENTEGrid Grid: method perform

//AD4_UTENTEGrid Grid: method read: head @71-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_UTENTEGrid Grid: method read: head

//AD4_UTENTEGrid Grid: method read: init @71-1FDBBADE
            if ( ! model.allowRead ) return true;
            AD4_UTENTEGridDataObject ds = new AD4_UTENTEGridDataObject(page);
            ds.setComponent( model );
//End AD4_UTENTEGrid Grid: method read: init

//AD4_UTENTEGrid Grid: set where parameters @71-6748F876
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
//End AD4_UTENTEGrid Grid: set where parameters

//AD4_UTENTEGrid Grid: set grid properties @71-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4_UTENTEGrid Grid: set grid properties

//AD4_UTENTEGrid Grid: retrieve data @71-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_UTENTEGrid Grid: retrieve data

//AD4_UTENTEGrid Grid: check errors @71-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_UTENTEGrid Grid: check errors

//AD4_UTENTEGrid Grid: method read: tail @71-F575E732
            return ( ! isErrors );
        }
//End AD4_UTENTEGrid Grid: method read: tail

//AD4_UTENTEGrid Grid: method bind @71-74F33E90
        public void bind(com.codecharge.components.Component model, AD4_UTENTEGridRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_UTENTEGridRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("DATA_RICHIESTA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATA_RICHIESTA").clone();
                    c.setValue(row.getDATA_RICHIESTA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOME_SOGGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOME_SOGGETTO").clone();
                    c.setValue(row.getNOME_SOGGETTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DATA_NASCITA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATA_NASCITA").clone();
                    c.setValue(row.getDATA_NASCITA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("COMUNE_NASCITA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COMUNE_NASCITA").clone();
                    c.setValue(row.getCOMUNE_NASCITA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PROVINCIA_NASCITA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROVINCIA_NASCITA").clone();
                    c.setValue(row.getPROVINCIA_NASCITA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CODICE_FISCALE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CODICE_FISCALE").clone();
                    c.setValue(row.getCODICE_FISCALE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("INDIRIZZO_COMPLETO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("INDIRIZZO_COMPLETO").clone();
                    c.setValue(row.getINDIRIZZO_COMPLETO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("INDIRIZZO_WEB");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("INDIRIZZO_WEB").clone();
                    c.setValue(row.getINDIRIZZO_WEB());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("TELEFONO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TELEFONO").clone();
                    c.setValue(row.getTELEFONO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("FAX");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FAX").clone();
                    c.setValue(row.getFAX());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOMINATIVO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOMINATIVO").clone();
                    c.setValue(row.getNOMINATIVO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PASSWORD");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PASSWORD").clone();
                    c.setValue(row.getPASSWORD());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_UTENTEGrid Grid: method bind

//AD4_UTENTEGrid Directory: getRowFieldByName @71-E3358EBE
        public Object getRowFieldByName( String name, AD4_UTENTEGridRow row ) {
            Object value = null;
            if ( "DATA_RICHIESTA".equals(name) ) {
                value = row.getDATA_RICHIESTA();
            } else if ( "NOME_SOGGETTO".equals(name) ) {
                value = row.getNOME_SOGGETTO();
            } else if ( "DATA_NASCITA".equals(name) ) {
                value = row.getDATA_NASCITA();
            } else if ( "COMUNE_NASCITA".equals(name) ) {
                value = row.getCOMUNE_NASCITA();
            } else if ( "PROVINCIA_NASCITA".equals(name) ) {
                value = row.getPROVINCIA_NASCITA();
            } else if ( "CODICE_FISCALE".equals(name) ) {
                value = row.getCODICE_FISCALE();
            } else if ( "INDIRIZZO_COMPLETO".equals(name) ) {
                value = row.getINDIRIZZO_COMPLETO();
            } else if ( "INDIRIZZO_WEB".equals(name) ) {
                value = row.getINDIRIZZO_WEB();
            } else if ( "TELEFONO".equals(name) ) {
                value = row.getTELEFONO();
            } else if ( "FAX".equals(name) ) {
                value = row.getFAX();
            } else if ( "NOMINATIVO".equals(name) ) {
                value = row.getNOMINATIVO();
            } else if ( "PASSWORD".equals(name) ) {
                value = row.getPASSWORD();
            }
            return value;
        }
//End AD4_UTENTEGrid Directory: getRowFieldByName

//AD4_UTENTEGrid Grid: method validate @71-104025BA
        boolean validate() {
            return true;
        }
//End AD4_UTENTEGrid Grid: method validate

//AD4_UTENTEGrid Grid Tail @71-FCB6E20C
    }
//End AD4_UTENTEGrid Grid Tail

//AdmRichiestaPrint Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AdmRichiestaPrint Page: method validate

//AdmRichiestaPrintAction Tail @1-FCB6E20C
}
//End AdmRichiestaPrintAction Tail

