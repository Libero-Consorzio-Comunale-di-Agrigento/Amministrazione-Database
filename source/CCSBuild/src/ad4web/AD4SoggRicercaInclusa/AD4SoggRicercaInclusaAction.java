//AD4SoggRicercaInclusaAction imports @1-95D6945E
package ad4web.AD4SoggRicercaInclusa;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4SoggRicercaInclusaAction imports

//AD4SoggRicercaInclusaAction class @1-DF382476
public class AD4SoggRicercaInclusaAction extends Action {

//End AD4SoggRicercaInclusaAction class

//AD4SoggRicercaInclusaAction: method perform @1-CD4C8113
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4SoggRicercaInclusaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4SoggRicercaInclusaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4SoggRicercaInclusaAction: method perform

//AD4SoggRicercaInclusaAction: call children actions @1-C743B240
        if (result == null) {
            AD4Soggetti_VSearchClass AD4Soggetti_VSearch = new AD4Soggetti_VSearchClass();
            if ( ( redirect = AD4Soggetti_VSearch.perform( page.getRecord("AD4Soggetti_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4SoggettiVClass AD4SoggettiV = new AD4SoggettiVClass();
            AD4SoggettiV.perform(page.getGrid("AD4SoggettiV"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4SoggRicercaInclusaAction: call children actions

//AD4Soggetti_VSearch Record @6-756E3AAB
    final class AD4Soggetti_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Soggetti_VSearch Record

//AD4Soggetti_VSearch Record: method perform @6-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Soggetti_VSearch Record: method perform

//AD4Soggetti_VSearch Record: children actions @6-9B80BBFE
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Soggetti_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4Soggetti_VSearch Record: children actions

//AD4Soggetti_VSearch Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Soggetti_VSearch Record: method perform tail

//DoSearch Button @7-5E3FB936
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4SoggRicercaInclusa" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //AD4Soggetti_VSearch Record: method read @6-7F8AAE5A

//AD4Soggetti_VSearch Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Soggetti_VSearch Record: method read head

//AD4Soggetti_VSearch Record: init DataSource @6-EAFB1B07
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Soggetti_VSearchDataObject ds = new AD4Soggetti_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_NOME( page.getHttpGetParams().getParameter("s_NOME") );
            ds.setSesAD4PaginaSoggetto( SessionStorage.getInstance(req).getAttribute("AD4PaginaSoggetto") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Soggetti_VSearch Record: init DataSource

//AD4Soggetti_VSearch Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Soggetti_VSearch Record: check errors

} //AD4Soggetti_VSearch Record: method read tail @6-FCB6E20C

//AD4Soggetti_VSearch Record: bind @6-EAD0A795
            public void bind(com.codecharge.components.Component model, AD4Soggetti_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                model.getControl("Nuovo").setValue(row.getNuovo());
                if ( this.valid ) {
                }
            }
//End AD4Soggetti_VSearch Record: bind

//AD4Soggetti_VSearch Record: getRowFieldByName @6-B471AD48
            public Object getRowFieldByName( String name, AD4Soggetti_VSearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_NOME".equals(name) ) {
                    value = row.getS_NOME();
                } else if ( "s_RICERCA".equals(name) ) {
                    value = row.getS_RICERCA();
                } else if ( "s_UTENTE".equals(name) ) {
                    value = row.getS_UTENTE();
                } else if ( "s_ENTE".equals(name) ) {
                    value = row.getS_ENTE();
                } else if ( "Nuovo".equals(name) ) {
                    value = row.getNuovo();
                }
                return value;
            }
//End AD4Soggetti_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Soggetti_VSearch Record: method insert @6-11643485

//AD4Soggetti_VSearch Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Soggetti_VSearch Record: components insert actions

} //AD4Soggetti_VSearch Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4Soggetti_VSearch Record: method update @6-5771D0AA

//AD4Soggetti_VSearch Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Soggetti_VSearch Record: components update actions

} //AD4Soggetti_VSearch Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4Soggetti_VSearch Record: method delete @6-11FC2E1E

//AD4Soggetti_VSearch Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Soggetti_VSearch Record: components delete actions

} //AD4Soggetti_VSearch Record: method delete tail @6-FCB6E20C

//AD4Soggetti_VSearch Record: method validate @6-DFB9B7DB
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_NOME = (com.codecharge.components.TextBox) model.getChild( "s_NOME" );
            if (! s_NOME.validate()) { isControlError = true; }

            com.codecharge.components.Hidden s_RICERCA = (com.codecharge.components.Hidden) model.getChild( "s_RICERCA" );
            if (! s_RICERCA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden s_UTENTE = (com.codecharge.components.Hidden) model.getChild( "s_UTENTE" );
            if (! s_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden s_ENTE = (com.codecharge.components.Hidden) model.getChild( "s_ENTE" );
            if (! s_ENTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Soggetti_VSearch Record: method validate

//AD4Soggetti_VSearch Record Tail @6-FCB6E20C
    }
//End AD4Soggetti_VSearch Record Tail

//AD4SoggettiV Grid @142-72FBBDC1
    final class AD4SoggettiVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4SoggettiV Grid

//AD4SoggettiV Grid: method perform @142-B48879D3
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
//End AD4SoggettiV Grid: method perform

//AD4SoggettiV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4SoggettiV Grid: method read: head

//AD4SoggettiV Grid: method read: init @142-2BEFCC22
            if ( ! model.allowRead ) return true;
            AD4SoggettiVDataObject ds = new AD4SoggettiVDataObject(page);
            ds.setComponent( model );
//End AD4SoggettiV Grid: method read: init

//AD4SoggettiV Grid: set where parameters @142-E807DB37
            ds.setUrlS_NOME( page.getHttpGetParams().getParameter("s_NOME") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.setUrlS_ENTE( page.getHttpGetParams().getParameter("s_ENTE") );
            ds.setUrlS_RICERCA( page.getHttpGetParams().getParameter("s_RICERCA") );
            ds.setSesAD4PaginaSoggetto( SessionStorage.getInstance(req).getAttribute("AD4PaginaSoggetto") );
//End AD4SoggettiV Grid: set where parameters

//AD4SoggettiV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4SoggettiV Grid: set grid properties

//AD4SoggettiV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4SoggettiV Grid: retrieve data

//AD4SoggettiV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4SoggettiV Grid: check errors

//AD4SoggettiV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4SoggettiV Grid: method read: tail

//AD4SoggettiV Grid: method bind @142-7D1AAB52
        public void bind(com.codecharge.components.Component model, AD4SoggettiVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4SoggettiVRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("SOGGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SOGGETTO").clone();
                    c.setValue(row.getSOGGETTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("SOGGETTO_VIS");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SOGGETTO_VIS").clone();
                    c.setValue(row.getSOGGETTO_VIS());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DATI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATI").clone();
                    c.setValue(row.getDATI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("RIPORTA_UTENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("RIPORTA_UTENTE").clone();
                    c.setValue(row.getRIPORTA_UTENTE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("SOGGETTO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("SOGGETTO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("RIPORTA_ENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("RIPORTA_ENTE").clone();
                    c.setValue(row.getRIPORTA_ENTE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("SOGGETTO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("SOGGETTO").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4SoggettiV Grid: method bind

//AD4SoggettiV Directory: getRowFieldByName @142-8E231540
        public Object getRowFieldByName( String name, AD4SoggettiVRow row ) {
            Object value = null;
            if ( "SOGGETTO".equals(name) ) {
                value = row.getSOGGETTO();
            } else if ( "SOGGETTO_VIS".equals(name) ) {
                value = row.getSOGGETTO_VIS();
            } else if ( "DATI".equals(name) ) {
                value = row.getDATI();
            } else if ( "RIPORTA_UTENTE".equals(name) ) {
                value = row.getRIPORTA_UTENTE();
            } else if ( "RIPORTA_ENTE".equals(name) ) {
                value = row.getRIPORTA_ENTE();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "PAGINA".equals(name) ) {
                value = row.getPAGINA();
            }
            return value;
        }
//End AD4SoggettiV Directory: getRowFieldByName

//AD4SoggettiV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4SoggettiV Grid: method validate

//AD4SoggettiV Grid Tail @142-FCB6E20C
    }
//End AD4SoggettiV Grid Tail

//AD4SoggRicercaInclusa Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4SoggRicercaInclusa Page: method validate

//AD4SoggRicercaInclusaAction Tail @1-FCB6E20C
}
//End AD4SoggRicercaInclusaAction Tail
