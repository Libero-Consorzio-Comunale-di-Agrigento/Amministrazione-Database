//Ad4DizionariStatiTerritoriElencoAction imports @1-6A7BE609
package ad4web.Ad4DizionariStatiTerritoriElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariStatiTerritoriElencoAction imports

//Ad4DizionariStatiTerritoriElencoAction class @1-85F47810
public class Ad4DizionariStatiTerritoriElencoAction extends Action {

//End Ad4DizionariStatiTerritoriElencoAction class

//Ad4DizionariStatiTerritoriElencoAction: method perform @1-273B94C8
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariStatiTerritoriElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariStatiTerritoriElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariStatiTerritoriElencoAction: method perform

//Ad4DizionariStatiTerritoriElencoAction: call children actions @1-F6E65838
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariGuidaParent",page);
            ad4web.Ad4DizionariGuida.Ad4DizionariGuidaAction Ad4DizionariGuida = new ad4web.Ad4DizionariGuida.Ad4DizionariGuidaAction();
            result = result != null ? result : Ad4DizionariGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            StatiFiltroClass StatiFiltro = new StatiFiltroClass();
            if ( ( redirect = StatiFiltro.perform( page.getRecord("StatiFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            StatiElencoClass StatiElenco = new StatiElencoClass();
            StatiElenco.perform(page.getGrid("StatiElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariStatiTerritoriElencoAction: call children actions

//StatiFiltro Record @3-ED92B818
    final class StatiFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End StatiFiltro Record

//StatiFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End StatiFiltro Record: method perform

//StatiFiltro Record: children actions @3-9D2A1550
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("StatiFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End StatiFiltro Record: children actions

//StatiFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End StatiFiltro Record: method perform tail

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

void read() { //StatiFiltro Record: method read @3-7F8AAE5A

//StatiFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End StatiFiltro Record: method read head

//StatiFiltro Record: init DataSource @3-80BFED83
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            StatiFiltroDataObject ds = new StatiFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End StatiFiltro Record: init DataSource

//StatiFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End StatiFiltro Record: check errors

} //StatiFiltro Record: method read tail @3-FCB6E20C

//StatiFiltro Record: bind @3-80E42E7F
            public void bind(com.codecharge.components.Component model, StatiFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_STATO").setValue(row.getS_STATO());
                }
            }
//End StatiFiltro Record: bind

//StatiFiltro Record: getRowFieldByName @3-75718675
            public Object getRowFieldByName( String name, StatiFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_STATO".equals(name) ) {
                    value = row.getS_STATO();
                }
                return value;
            }
//End StatiFiltro Record: getRowFieldByName

void InsertAction() { //StatiFiltro Record: method insert @3-11643485

//StatiFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End StatiFiltro Record: components insert actions

} //StatiFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //StatiFiltro Record: method update @3-5771D0AA

//StatiFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End StatiFiltro Record: components update actions

} //StatiFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //StatiFiltro Record: method delete @3-11FC2E1E

//StatiFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End StatiFiltro Record: components delete actions

} //StatiFiltro Record: method delete tail @3-FCB6E20C

//StatiFiltro Record: method validate @3-87FE262C
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_STATO = (com.codecharge.components.TextBox) model.getChild( "s_STATO" );
            if (! s_STATO.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End StatiFiltro Record: method validate

//StatiFiltro Record Tail @3-FCB6E20C
    }
//End StatiFiltro Record Tail

//StatiElenco Grid @8-414789D4
    final class StatiElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End StatiElenco Grid

//StatiElenco Grid: method perform @8-B48879D3
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
//End StatiElenco Grid: method perform

//StatiElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End StatiElenco Grid: method read: head

//StatiElenco Grid: method read: init @8-A83F1C59
            if ( ! model.allowRead ) return true;
            StatiElencoDataObject ds = new StatiElencoDataObject(page);
            ds.setComponent( model );
//End StatiElenco Grid: method read: init

//StatiElenco Grid: set where parameters @8-346C2A96
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
//End StatiElenco Grid: set where parameters

//StatiElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End StatiElenco Grid: set grid properties

//StatiElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End StatiElenco Grid: retrieve data

//StatiElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End StatiElenco Grid: check errors

//StatiElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End StatiElenco Grid: method read: tail

//StatiElenco Grid: method bind @8-DCB940F7
        public void bind(com.codecharge.components.Component model, StatiElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            StatiElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("STATO_TERRITORIO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("STATO_TERRITORIO").clone();
                    c.setValue(row.getSTATO_TERRITORIO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DENOMINAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DENOMINAZIONE").clone();
                    c.setValue(row.getDENOMINAZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("STATO_TERRITORIO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("STATO_TERRITORIO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("SIGLA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SIGLA").clone();
                    c.setValue(row.getSIGLA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESC_CITTADINANZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_CITTADINANZA").clone();
                    c.setValue(row.getDESC_CITTADINANZA());
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
//End StatiElenco Grid: method bind

//StatiElenco Directory: getRowFieldByName @8-342D3DEF
        public Object getRowFieldByName( String name, StatiElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "STATO_TERRITORIO".equals(name) ) {
                value = row.getSTATO_TERRITORIO();
            } else if ( "DENOMINAZIONE".equals(name) ) {
                value = row.getDENOMINAZIONE();
            } else if ( "SIGLA".equals(name) ) {
                value = row.getSIGLA();
            } else if ( "DESC_CITTADINANZA".equals(name) ) {
                value = row.getDESC_CITTADINANZA();
            } else if ( "UTENTE_AGGIORNAMENTO".equals(name) ) {
                value = row.getUTENTE_AGGIORNAMENTO();
            } else if ( "DATA_AGGIORNAMENTO".equals(name) ) {
                value = row.getDATA_AGGIORNAMENTO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End StatiElenco Directory: getRowFieldByName

//StatiElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End StatiElenco Grid: method validate

//StatiElenco Grid Tail @8-FCB6E20C
    }
//End StatiElenco Grid Tail

//Ad4DizionariStatiTerritoriElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariStatiTerritoriElenco Page: method validate

//Ad4DizionariStatiTerritoriElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariStatiTerritoriElencoAction Tail

