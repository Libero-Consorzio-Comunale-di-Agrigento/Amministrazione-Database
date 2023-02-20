//Ad4DizionariComuniLovAction imports @1-17747474
package ad4web.Ad4DizionariComuniLov;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariComuniLovAction imports

//Ad4DizionariComuniLovAction class @1-F7062891
public class Ad4DizionariComuniLovAction extends Action {

//End Ad4DizionariComuniLovAction class

//Ad4DizionariComuniLovAction: method perform @1-BE61EB80
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariComuniLovModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariComuniLovModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariComuniLovAction: method perform

//Ad4DizionariComuniLovAction: call children actions @1-C37B541D
        if (result == null) {
            ComuniSearchClass ComuniSearch = new ComuniSearchClass();
            if ( ( redirect = ComuniSearch.perform( page.getRecord("ComuniSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            ComuniClass Comuni = new ComuniClass();
            Comuni.perform(page.getGrid("Comuni"));
        }
        if (result == null) {
            FocusClass Focus = new FocusClass();
            Focus.perform(page.getGrid("Focus"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariComuniLovAction: call children actions

//ComuniSearch Record @2-CA4AB420
    final class ComuniSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ComuniSearch Record

//ComuniSearch Record: method perform @2-1CB004DD
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            page.setRedirectString( performReturnPage.toString() );
//End ComuniSearch Record: method perform

//ComuniSearch Record: children actions @2-D9FF817D
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ComuniSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End ComuniSearch Record: children actions

//ComuniSearch Record: method perform tail @2-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ComuniSearch Record: method perform tail

//DoSearch Button @4-1E74C0F1
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?"  + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //ComuniSearch Record: method read @2-7F8AAE5A

//ComuniSearch Record: method read head @2-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ComuniSearch Record: method read head

//ComuniSearch Record: init DataSource @2-9424C4BA
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ComuniSearchDataObject ds = new ComuniSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_COMUNE( page.getHttpGetParams().getParameter("s_COMUNE") );
            ds.setUrlS_PROVINCIA_SIGLA( page.getHttpGetParams().getParameter("s_PROVINCIA_SIGLA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ComuniSearch Record: init DataSource

//ComuniSearch Record: check errors @2-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ComuniSearch Record: check errors

} //ComuniSearch Record: method read tail @2-FCB6E20C

//ComuniSearch Record: bind @2-C7F256CF
            public void bind(com.codecharge.components.Component model, ComuniSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                }
            }
//End ComuniSearch Record: bind

//ComuniSearch Record: getRowFieldByName @2-D1CAC624
            public Object getRowFieldByName( String name, ComuniSearchRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_COMUNE".equals(name) ) {
                    value = row.getS_COMUNE();
                } else if ( "s_PROVINCIA_SIGLA".equals(name) ) {
                    value = row.getS_PROVINCIA_SIGLA();
                }
                return value;
            }
//End ComuniSearch Record: getRowFieldByName

void InsertAction() { //ComuniSearch Record: method insert @2-11643485

//ComuniSearch Record: components insert actions @2-68525650
            if (! model.hasErrors()) {
            }
//End ComuniSearch Record: components insert actions

} //ComuniSearch Record: method insert tail @2-FCB6E20C

void UpdateAction() { //ComuniSearch Record: method update @2-5771D0AA

//ComuniSearch Record: components update actions @2-68525650
            if (! model.hasErrors()) {
            }
//End ComuniSearch Record: components update actions

} //ComuniSearch Record: method update tail @2-FCB6E20C

void DeleteAction() { //ComuniSearch Record: method delete @2-11FC2E1E

//ComuniSearch Record: components delete actions @2-68525650
            if (! model.hasErrors()) {
            }
//End ComuniSearch Record: components delete actions

} //ComuniSearch Record: method delete tail @2-FCB6E20C

//ComuniSearch Record: method validate @2-01267EB5
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_COMUNE = (com.codecharge.components.TextBox) model.getChild( "s_COMUNE" );
            if (! s_COMUNE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_PROVINCIA_SIGLA = (com.codecharge.components.TextBox) model.getChild( "s_PROVINCIA_SIGLA" );
            if (! s_PROVINCIA_SIGLA.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ComuniSearch Record: method validate

//ComuniSearch Record Tail @2-FCB6E20C
    }
//End ComuniSearch Record Tail

//Comuni Grid @5-524A41BB
    final class ComuniClass {
        com.codecharge.components.Grid model;
        Event e;
//End Comuni Grid

//Comuni Grid: method perform @5-B48879D3
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
//End Comuni Grid: method perform

//Comuni Grid: method read: head @5-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Comuni Grid: method read: head

//Comuni Grid: method read: init @5-9AB3957C
            if ( ! model.allowRead ) return true;
            ComuniDataObject ds = new ComuniDataObject(page);
            ds.setComponent( model );
//End Comuni Grid: method read: init

//Comuni Grid: set where parameters @5-898D1ACE
            ds.setUrlS_COMUNE( page.getHttpGetParams().getParameter("s_COMUNE") );
            ds.setUrlS_PROVINCIA_SIGLA( page.getHttpGetParams().getParameter("s_PROVINCIA_SIGLA") );
//End Comuni Grid: set where parameters

//Comuni Grid: set grid properties @5-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Comuni Grid: set grid properties

//Comuni Grid: retrieve data @5-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Comuni Grid: retrieve data

//Comuni Grid: check errors @5-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Comuni Grid: check errors

//Comuni Grid: method read: tail @5-F575E732
            return ( ! isErrors );
        }
//End Comuni Grid: method read: tail

//Comuni Grid: method bind @5-2C271C9A
        public void bind(com.codecharge.components.Component model, ComuniRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ComuniRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("COMUNE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COMUNE").clone();
                    c.setValue(row.getCOMUNE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PROVINCIA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROVINCIA").clone();
                    c.setValue(row.getPROVINCIA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("SOPPRESSIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SOPPRESSIONE").clone();
                    c.setValue(row.getSOPPRESSIONE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End Comuni Grid: method bind

//Comuni Directory: getRowFieldByName @5-6E3DB43E
        public Object getRowFieldByName( String name, ComuniRow row ) {
            Object value = null;
            if ( "COMUNE".equals(name) ) {
                value = row.getCOMUNE();
            } else if ( "PROVINCIA".equals(name) ) {
                value = row.getPROVINCIA();
            } else if ( "SOPPRESSIONE".equals(name) ) {
                value = row.getSOPPRESSIONE();
            }
            return value;
        }
//End Comuni Directory: getRowFieldByName

//Comuni Grid: method validate @5-104025BA
        boolean validate() {
            return true;
        }
//End Comuni Grid: method validate

//Comuni Grid Tail @5-FCB6E20C
    }
//End Comuni Grid Tail

//Focus Grid @21-07CCE8E6
    final class FocusClass {
        com.codecharge.components.Grid model;
        Event e;
//End Focus Grid

//Focus Grid: method perform @21-B48879D3
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
//End Focus Grid: method perform

//Focus Grid: method read: head @21-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Focus Grid: method read: head

//Focus Grid: method read: init @21-EF54BA97
            if ( ! model.allowRead ) return true;
            FocusDataObject ds = new FocusDataObject(page);
            ds.setComponent( model );
//End Focus Grid: method read: init

//Focus Grid: set grid properties @21-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Focus Grid: set grid properties

//Focus Grid: retrieve data @21-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Focus Grid: retrieve data

//Focus Grid: check errors @21-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Focus Grid: check errors

//Focus Grid: method read: tail @21-F575E732
            return ( ! isErrors );
        }
//End Focus Grid: method read: tail

//Focus Grid: method bind @21-5C377B9A
        public void bind(com.codecharge.components.Component model, FocusRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            FocusRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("FIELD_FOCUS");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FIELD_FOCUS").clone();
                    c.setValue(row.getFIELD_FOCUS());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End Focus Grid: method bind

//Focus Directory: getRowFieldByName @21-B9F793BD
        public Object getRowFieldByName( String name, FocusRow row ) {
            Object value = null;
            if ( "FIELD_FOCUS".equals(name) ) {
                value = row.getFIELD_FOCUS();
            }
            return value;
        }
//End Focus Directory: getRowFieldByName

//Focus Grid: method validate @21-104025BA
        boolean validate() {
            return true;
        }
//End Focus Grid: method validate

//Focus Grid Tail @21-FCB6E20C
    }
//End Focus Grid Tail

//Ad4DizionariComuniLov Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariComuniLov Page: method validate

//Ad4DizionariComuniLovAction Tail @1-FCB6E20C
}
//End Ad4DizionariComuniLovAction Tail

