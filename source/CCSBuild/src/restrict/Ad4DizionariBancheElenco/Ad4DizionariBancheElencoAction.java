//Ad4DizionariBancheElencoAction imports @1-7E4192B1
package restrict.Ad4DizionariBancheElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariBancheElencoAction imports

//Ad4DizionariBancheElencoAction class @1-7FF77F1B
public class Ad4DizionariBancheElencoAction extends Action {

//End Ad4DizionariBancheElencoAction class

//Ad4DizionariBancheElencoAction: method perform @1-7E9ED026
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariBancheElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariBancheElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariBancheElencoAction: method perform

//Ad4DizionariBancheElencoAction: call children actions @1-2C2F094A
        if ( page.getChild( "Ad4DizionariBancheGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariBancheGuidaParent",page);
            restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaAction Ad4DizionariBancheGuida = new restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaAction();
            result = result != null ? result : Ad4DizionariBancheGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            BancheFiltroClass BancheFiltro = new BancheFiltroClass();
            if ( ( redirect = BancheFiltro.perform( page.getRecord("BancheFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            BancheElencoClass BancheElenco = new BancheElencoClass();
            BancheElenco.perform(page.getGrid("BancheElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariBancheElencoAction: call children actions

//BancheFiltro Record @3-2EEB9AA8
    final class BancheFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End BancheFiltro Record

//BancheFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End BancheFiltro Record: method perform

//BancheFiltro Record: children actions @3-55F55F92
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("BancheFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End BancheFiltro Record: children actions

//BancheFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End BancheFiltro Record: method perform tail

//DoSearch Button @6-7F387837
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //BancheFiltro Record: method read @3-7F8AAE5A

//BancheFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End BancheFiltro Record: method read head

//BancheFiltro Record: init DataSource @3-C249B447
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            BancheFiltroDataObject ds = new BancheFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_BANCA( page.getHttpGetParams().getParameter("s_BANCA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End BancheFiltro Record: init DataSource

//BancheFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End BancheFiltro Record: check errors

} //BancheFiltro Record: method read tail @3-FCB6E20C

//BancheFiltro Record: bind @3-9B558BA2
            public void bind(com.codecharge.components.Component model, BancheFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_BANCA").setValue(row.getS_BANCA());
                }
            }
//End BancheFiltro Record: bind

//BancheFiltro Record: getRowFieldByName @3-DFB11E87
            public Object getRowFieldByName( String name, BancheFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_BANCA".equals(name) ) {
                    value = row.getS_BANCA();
                }
                return value;
            }
//End BancheFiltro Record: getRowFieldByName

void InsertAction() { //BancheFiltro Record: method insert @3-11643485

//BancheFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End BancheFiltro Record: components insert actions

} //BancheFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //BancheFiltro Record: method update @3-5771D0AA

//BancheFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End BancheFiltro Record: components update actions

} //BancheFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //BancheFiltro Record: method delete @3-11FC2E1E

//BancheFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End BancheFiltro Record: components delete actions

} //BancheFiltro Record: method delete tail @3-FCB6E20C

//BancheFiltro Record: method validate @3-FA1E684F
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_BANCA = (com.codecharge.components.TextBox) model.getChild( "s_BANCA" );
            if (! s_BANCA.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End BancheFiltro Record: method validate

//BancheFiltro Record Tail @3-FCB6E20C
    }
//End BancheFiltro Record Tail

//BancheElenco Grid @8-7121D254
    final class BancheElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End BancheElenco Grid

//BancheElenco Grid: method perform @8-B48879D3
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
//End BancheElenco Grid: method perform

//BancheElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End BancheElenco Grid: method read: head

//BancheElenco Grid: method read: init @8-9BD42B01
            if ( ! model.allowRead ) return true;
            BancheElencoDataObject ds = new BancheElencoDataObject(page);
            ds.setComponent( model );
//End BancheElenco Grid: method read: init

//BancheElenco Grid: set where parameters @8-A782B712
            ds.setUrlS_BANCA( page.getHttpGetParams().getParameter("s_BANCA") );
//End BancheElenco Grid: set where parameters

//BancheElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End BancheElenco Grid: set grid properties

//BancheElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End BancheElenco Grid: retrieve data

//BancheElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End BancheElenco Grid: check errors

//BancheElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End BancheElenco Grid: method read: tail

//BancheElenco Grid: method bind @8-AEAA922D
        public void bind(com.codecharge.components.Component model, BancheElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            BancheElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("ABI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ABI").clone();
                    c.setValue(row.getABI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("LINK_SPORTELLI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("LINK_SPORTELLI").clone();
                    c.setValue(row.getLINK_SPORTELLI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DENOMINAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DENOMINAZIONE").clone();
                    c.setValue(row.getDENOMINAZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("BANCA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("BANCA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("CIN_ABI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CIN_ABI").clone();
                    c.setValue(row.getCIN_ABI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Aggiungi");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Aggiungi").clone();
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End BancheElenco Grid: method bind

//BancheElenco Directory: getRowFieldByName @8-28D3DEEF
        public Object getRowFieldByName( String name, BancheElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "ABI".equals(name) ) {
                value = row.getABI();
            } else if ( "LINK_SPORTELLI".equals(name) ) {
                value = row.getLINK_SPORTELLI();
            } else if ( "DENOMINAZIONE".equals(name) ) {
                value = row.getDENOMINAZIONE();
            } else if ( "CIN_ABI".equals(name) ) {
                value = row.getCIN_ABI();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "BANCA".equals(name) ) {
                value = row.getBANCA();
            }
            return value;
        }
//End BancheElenco Directory: getRowFieldByName

//BancheElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End BancheElenco Grid: method validate

//BancheElenco Grid Tail @8-FCB6E20C
    }
//End BancheElenco Grid Tail

//Ad4DizionariBancheElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariBancheElenco Page: method validate

//Ad4DizionariBancheElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariBancheElencoAction Tail
