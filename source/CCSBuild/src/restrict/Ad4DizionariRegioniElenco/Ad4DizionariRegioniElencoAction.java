//Ad4DizionariRegioniElencoAction imports @1-962988FB
package restrict.Ad4DizionariRegioniElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariRegioniElencoAction imports

//Ad4DizionariRegioniElencoAction class @1-41AB05B3
public class Ad4DizionariRegioniElencoAction extends Action {

//End Ad4DizionariRegioniElencoAction class

//Ad4DizionariRegioniElencoAction: method perform @1-9D9CCE7E
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariRegioniElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariRegioniElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariRegioniElencoAction: method perform

//Ad4DizionariRegioniElencoAction: call children actions @1-00BA3408
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariGuidaParent",page);
            restrict.Ad4DizionariGuida.Ad4DizionariGuidaAction Ad4DizionariGuida = new restrict.Ad4DizionariGuida.Ad4DizionariGuidaAction();
            result = result != null ? result : Ad4DizionariGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            RegioniFiltroClass RegioniFiltro = new RegioniFiltroClass();
            if ( ( redirect = RegioniFiltro.perform( page.getRecord("RegioniFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            RegioniElencoClass RegioniElenco = new RegioniElencoClass();
            RegioniElenco.perform(page.getGrid("RegioniElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariRegioniElencoAction: call children actions

//RegioniFiltro Record @3-FF5875CE
    final class RegioniFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End RegioniFiltro Record

//RegioniFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End RegioniFiltro Record: method perform

//RegioniFiltro Record: children actions @3-CF5786B3
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("RegioniFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End RegioniFiltro Record: children actions

//RegioniFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End RegioniFiltro Record: method perform tail

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

void read() { //RegioniFiltro Record: method read @3-7F8AAE5A

//RegioniFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End RegioniFiltro Record: method read head

//RegioniFiltro Record: init DataSource @3-4FF74F3D
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            RegioniFiltroDataObject ds = new RegioniFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End RegioniFiltro Record: init DataSource

//RegioniFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End RegioniFiltro Record: check errors

} //RegioniFiltro Record: method read tail @3-FCB6E20C

//RegioniFiltro Record: bind @3-CC6ECC3F
            public void bind(com.codecharge.components.Component model, RegioniFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_REGIONE").setValue(row.getS_REGIONE());
                }
            }
//End RegioniFiltro Record: bind

//RegioniFiltro Record: getRowFieldByName @3-EAA2D0DA
            public Object getRowFieldByName( String name, RegioniFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_REGIONE".equals(name) ) {
                    value = row.getS_REGIONE();
                }
                return value;
            }
//End RegioniFiltro Record: getRowFieldByName

void InsertAction() { //RegioniFiltro Record: method insert @3-11643485

//RegioniFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End RegioniFiltro Record: components insert actions

} //RegioniFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //RegioniFiltro Record: method update @3-5771D0AA

//RegioniFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End RegioniFiltro Record: components update actions

} //RegioniFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //RegioniFiltro Record: method delete @3-11FC2E1E

//RegioniFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End RegioniFiltro Record: components delete actions

} //RegioniFiltro Record: method delete tail @3-FCB6E20C

//RegioniFiltro Record: method validate @3-DD7141A7
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_REGIONE = (com.codecharge.components.TextBox) model.getChild( "s_REGIONE" );
            if (! s_REGIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End RegioniFiltro Record: method validate

//RegioniFiltro Record Tail @3-FCB6E20C
    }
//End RegioniFiltro Record Tail

//RegioniElenco Grid @8-AE5337E0
    final class RegioniElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End RegioniElenco Grid

//RegioniElenco Grid: method perform @8-B48879D3
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
//End RegioniElenco Grid: method perform

//RegioniElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End RegioniElenco Grid: method read: head

//RegioniElenco Grid: method read: init @8-847BABB6
            if ( ! model.allowRead ) return true;
            RegioniElencoDataObject ds = new RegioniElencoDataObject(page);
            ds.setComponent( model );
//End RegioniElenco Grid: method read: init

//RegioniElenco Grid: set where parameters @8-55A95E1E
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
//End RegioniElenco Grid: set where parameters

//RegioniElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End RegioniElenco Grid: set grid properties

//RegioniElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End RegioniElenco Grid: retrieve data

//RegioniElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End RegioniElenco Grid: check errors

//RegioniElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End RegioniElenco Grid: method read: tail

//RegioniElenco Grid: method bind @8-61CE97E3
        public void bind(com.codecharge.components.Component model, RegioniElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            RegioniElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("REGIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("REGIONE").clone();
                    c.setValue(row.getREGIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("LINK_PROVINCE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("LINK_PROVINCE").clone();
                    c.setValue(row.getLINK_PROVINCE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DENOMINAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DENOMINAZIONE").clone();
                    c.setValue(row.getDENOMINAZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("REGIONE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("REGIONE").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("ID_REGIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ID_REGIONE").clone();
                    c.setValue(row.getID_REGIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("UTENTE_AGGIORNAMENTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UTENTE_AGGIORNAMENTO").clone();
                    c.setValue(row.getUTENTE_AGGIORNAMENTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DATA_AGGIORNAMENTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATA_AGGIORNAMENTO").clone();
                    c.setValue(row.getDATA_AGGIORNAMENTO());
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
//End RegioniElenco Grid: method bind

//RegioniElenco Directory: getRowFieldByName @8-85D96795
        public Object getRowFieldByName( String name, RegioniElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "REGIONE".equals(name) ) {
                value = row.getREGIONE();
            } else if ( "LINK_PROVINCE".equals(name) ) {
                value = row.getLINK_PROVINCE();
            } else if ( "DENOMINAZIONE".equals(name) ) {
                value = row.getDENOMINAZIONE();
            } else if ( "ID_REGIONE".equals(name) ) {
                value = row.getID_REGIONE();
            } else if ( "UTENTE_AGGIORNAMENTO".equals(name) ) {
                value = row.getUTENTE_AGGIORNAMENTO();
            } else if ( "DATA_AGGIORNAMENTO".equals(name) ) {
                value = row.getDATA_AGGIORNAMENTO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End RegioniElenco Directory: getRowFieldByName

//RegioniElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End RegioniElenco Grid: method validate

//RegioniElenco Grid Tail @8-FCB6E20C
    }
//End RegioniElenco Grid Tail

//Ad4DizionariRegioniElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariRegioniElenco Page: method validate

//Ad4DizionariRegioniElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariRegioniElencoAction Tail

