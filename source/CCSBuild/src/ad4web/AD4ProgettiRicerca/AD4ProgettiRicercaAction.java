//AD4ProgettiRicercaAction imports @1-4D678C96
package ad4web.AD4ProgettiRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4ProgettiRicercaAction imports

//AD4ProgettiRicercaAction class @1-8800099F
public class AD4ProgettiRicercaAction extends Action {

//End AD4ProgettiRicercaAction class

//AD4ProgettiRicercaAction: method perform @1-E473C154
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4ProgettiRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4ProgettiRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4ProgettiRicercaAction: method perform

//AD4ProgettiRicercaAction: call children actions @1-E4F8F525
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
            AD4Progetti_VSearchClass AD4Progetti_VSearch = new AD4Progetti_VSearchClass();
            if ( ( redirect = AD4Progetti_VSearch.perform( page.getRecord("AD4Progetti_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4ProgettiVClass AD4ProgettiV = new AD4ProgettiVClass();
            AD4ProgettiV.perform(page.getGrid("AD4ProgettiV"));
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
//End AD4ProgettiRicercaAction: call children actions

//AD4Progetti_VSearch Record @6-E300AF2B
    final class AD4Progetti_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Progetti_VSearch Record

//AD4Progetti_VSearch Record: method perform @6-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Progetti_VSearch Record: method perform

//AD4Progetti_VSearch Record: children actions @6-0E9EEA17
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Progetti_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4Progetti_VSearch Record: children actions

//AD4Progetti_VSearch Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Progetti_VSearch Record: method perform tail

//DoSearch Button @7-3A07D6D1
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4ProgettiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //AD4Progetti_VSearch Record: method read @6-7F8AAE5A

//AD4Progetti_VSearch Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Progetti_VSearch Record: method read head

//AD4Progetti_VSearch Record: init DataSource @6-5999032D
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Progetti_VSearchDataObject ds = new AD4Progetti_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Progetti_VSearch Record: init DataSource

//AD4Progetti_VSearch Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Progetti_VSearch Record: check errors

} //AD4Progetti_VSearch Record: method read tail @6-FCB6E20C

//AD4Progetti_VSearch Record: bind @6-8407A141
            public void bind(com.codecharge.components.Component model, AD4Progetti_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                }
            }
//End AD4Progetti_VSearch Record: bind

//AD4Progetti_VSearch Record: getRowFieldByName @6-262440AB
            public Object getRowFieldByName( String name, AD4Progetti_VSearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_DESCRIZIONE".equals(name) ) {
                    value = row.getS_DESCRIZIONE();
                } else if ( "Nuovo".equals(name) ) {
                    value = row.getNuovo();
                }
                return value;
            }
//End AD4Progetti_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Progetti_VSearch Record: method insert @6-11643485

//AD4Progetti_VSearch Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Progetti_VSearch Record: components insert actions

} //AD4Progetti_VSearch Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4Progetti_VSearch Record: method update @6-5771D0AA

//AD4Progetti_VSearch Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Progetti_VSearch Record: components update actions

} //AD4Progetti_VSearch Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4Progetti_VSearch Record: method delete @6-11FC2E1E

//AD4Progetti_VSearch Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Progetti_VSearch Record: components delete actions

} //AD4Progetti_VSearch Record: method delete tail @6-FCB6E20C

//AD4Progetti_VSearch Record: method validate @6-58D16649
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "s_DESCRIZIONE" );
            if (! s_DESCRIZIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Progetti_VSearch Record: method validate

//AD4Progetti_VSearch Record Tail @6-FCB6E20C
    }
//End AD4Progetti_VSearch Record Tail

//AD4ProgettiV Grid @142-AF911B19
    final class AD4ProgettiVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4ProgettiV Grid

//AD4ProgettiV Grid: method perform @142-B48879D3
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
//End AD4ProgettiV Grid: method perform

//AD4ProgettiV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4ProgettiV Grid: method read: head

//AD4ProgettiV Grid: method read: init @142-E0A40DEE
            if ( ! model.allowRead ) return true;
            AD4ProgettiVDataObject ds = new AD4ProgettiVDataObject(page);
            ds.setComponent( model );
//End AD4ProgettiV Grid: method read: init

//AD4ProgettiV Grid: set where parameters @142-0440F0C8
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
//End AD4ProgettiV Grid: set where parameters

//AD4ProgettiV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4ProgettiV Grid: set grid properties

//AD4ProgettiV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4ProgettiV Grid: retrieve data

//AD4ProgettiV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4ProgettiV Grid: check errors

//AD4ProgettiV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4ProgettiV Grid: method read: tail

//AD4ProgettiV Grid: method bind @142-DBD3FD82
        public void bind(com.codecharge.components.Component model, AD4ProgettiVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4ProgettiVRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("PROGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROGETTO").clone();
                    c.setValue(row.getPROGETTO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("PROGETTO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("PROGETTO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PRIORITA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PRIORITA").clone();
                    c.setValue(row.getPRIORITA());
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

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4ProgettiV Grid: method bind

//AD4ProgettiV Directory: getRowFieldByName @142-95369707
        public Object getRowFieldByName( String name, AD4ProgettiVRow row ) {
            Object value = null;
            if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
            } else if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "PRIORITA".equals(name) ) {
                value = row.getPRIORITA();
            } else if ( "NOTE".equals(name) ) {
                value = row.getNOTE();
            } else if ( "CaratteristicheAccesso".equals(name) ) {
                value = row.getCaratteristicheAccesso();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End AD4ProgettiV Directory: getRowFieldByName

//AD4ProgettiV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4ProgettiV Grid: method validate

//AD4ProgettiV Grid Tail @142-FCB6E20C
    }
//End AD4ProgettiV Grid Tail

//AD4ProgettiRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4ProgettiRicerca Page: method validate

//AD4ProgettiRicercaAction Tail @1-FCB6E20C
}
//End AD4ProgettiRicercaAction Tail

