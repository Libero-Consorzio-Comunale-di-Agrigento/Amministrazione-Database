//AmvDatiRegistrazioneAction imports @1-6BA3F3C4
package common.AmvDatiRegistrazione;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AmvDatiRegistrazioneAction imports

//AmvDatiRegistrazioneAction class @1-A22F7158
public class AmvDatiRegistrazioneAction extends Action {

//End AmvDatiRegistrazioneAction class

//AmvDatiRegistrazioneAction: method perform @1-27817E47
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AmvDatiRegistrazioneModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AmvDatiRegistrazioneModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AmvDatiRegistrazioneAction: method perform

//AmvDatiRegistrazioneAction: call children actions @1-45B05257
        if (result == null) {
            AD4_UTENTEGridClass AD4_UTENTEGrid = new AD4_UTENTEGridClass();
            AD4_UTENTEGrid.perform(page.getGrid("AD4_UTENTEGrid"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AmvDatiRegistrazioneAction: call children actions

//AD4_UTENTEGrid Grid @2-455F991D
    final class AD4_UTENTEGridClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_UTENTEGrid Grid

//AD4_UTENTEGrid Grid: method perform @2-B48879D3
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

//AD4_UTENTEGrid Grid: method read: head @2-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_UTENTEGrid Grid: method read: head

//AD4_UTENTEGrid Grid: method read: init @2-1FDBBADE
            if ( ! model.allowRead ) return true;
            AD4_UTENTEGridDataObject ds = new AD4_UTENTEGridDataObject(page);
            ds.setComponent( model );
//End AD4_UTENTEGrid Grid: method read: init

//AD4_UTENTEGrid Grid: set where parameters @2-8DDC3665
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            ds.setSesMVUTE( SessionStorage.getInstance(req).getAttribute("MVUTE") );
            ds.setSesModulo( SessionStorage.getInstance(req).getAttribute("Modulo") );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setSesIstanza( SessionStorage.getInstance(req).getAttribute("Istanza") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setSesMVPWD( SessionStorage.getInstance(req).getAttribute("MVPWD") );
//End AD4_UTENTEGrid Grid: set where parameters

//AD4_UTENTEGrid Grid: set grid properties @2-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4_UTENTEGrid Grid: set grid properties

//AD4_UTENTEGrid Grid: retrieve data @2-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_UTENTEGrid Grid: retrieve data

//AD4_UTENTEGrid Grid: check errors @2-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_UTENTEGrid Grid: check errors

//AD4_UTENTEGrid Grid: method read: tail @2-F575E732
            return ( ! isErrors );
        }
//End AD4_UTENTEGrid Grid: method read: tail

//AD4_UTENTEGrid Grid: method bind @2-77CA16C4
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

                c = (com.codecharge.components.Control) hashRow.get("TIPO_NOTIFICA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TIPO_NOTIFICA").clone();
                    c.setValue(row.getTIPO_NOTIFICA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("INDIRIZZO_NOTIFICA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("INDIRIZZO_NOTIFICA").clone();
                    c.setValue(row.getINDIRIZZO_NOTIFICA());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_UTENTEGrid Grid: method bind

//AD4_UTENTEGrid Directory: getRowFieldByName @2-1ACDE002
        public Object getRowFieldByName( String name, AD4_UTENTEGridRow row ) {
            Object value = null;
            if ( "NOME_SOGGETTO".equals(name) ) {
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
            } else if ( "TIPO_NOTIFICA".equals(name) ) {
                value = row.getTIPO_NOTIFICA();
            } else if ( "INDIRIZZO_NOTIFICA".equals(name) ) {
                value = row.getINDIRIZZO_NOTIFICA();
            }
            return value;
        }
//End AD4_UTENTEGrid Directory: getRowFieldByName

//AD4_UTENTEGrid Grid: method validate @2-104025BA
        boolean validate() {
            return true;
        }
//End AD4_UTENTEGrid Grid: method validate

//AD4_UTENTEGrid Grid Tail @2-FCB6E20C
    }
//End AD4_UTENTEGrid Grid Tail

//AmvDatiRegistrazione Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AmvDatiRegistrazione Page: method validate

//AmvDatiRegistrazioneAction Tail @1-FCB6E20C
}
//End AmvDatiRegistrazioneAction Tail

