//Ad4DizionariZoneAslElencoAction imports @1-3010419F
package ad4web.Ad4DizionariZoneAslElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariZoneAslElencoAction imports

//Ad4DizionariZoneAslElencoAction class @1-B1B0C876
public class Ad4DizionariZoneAslElencoAction extends Action {

//End Ad4DizionariZoneAslElencoAction class

//Ad4DizionariZoneAslElencoAction: method perform @1-58B908E6
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariZoneAslElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariZoneAslElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariZoneAslElencoAction: method perform

//Ad4DizionariZoneAslElencoAction: call children actions @1-97E1537E
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariGuidaParent",page);
            ad4web.Ad4DizionariGuida.Ad4DizionariGuidaAction Ad4DizionariGuida = new ad4web.Ad4DizionariGuida.Ad4DizionariGuidaAction();
            result = result != null ? result : Ad4DizionariGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            ZoneAslFiltroClass ZoneAslFiltro = new ZoneAslFiltroClass();
            if ( ( redirect = ZoneAslFiltro.perform( page.getRecord("ZoneAslFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            ZoneAslElencoClass ZoneAslElenco = new ZoneAslElencoClass();
            ZoneAslElenco.perform(page.getGrid("ZoneAslElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariZoneAslElencoAction: call children actions

//ZoneAslFiltro Record @3-363F4E1F
    final class ZoneAslFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ZoneAslFiltro Record

//ZoneAslFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End ZoneAslFiltro Record: method perform

//ZoneAslFiltro Record: children actions @3-DE7ADAC4
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ZoneAslFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_REGIONE(model.getListBox("s_REGIONE"));
//End ZoneAslFiltro Record: children actions

//ZoneAslFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ZoneAslFiltro Record: method perform tail

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

//ListBoxAction @26-E1C3B92F
        protected void reads_REGIONE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select regione "
                        + "      ,denominazione "
                        + "  from ad4_regioni "
                        + " " );
            command.setOrder( "denominazione" );

            model.fireBeforeBuildSelectEvent( e );




            model.fireBeforeExecuteSelectEvent( e );

            Enumeration records = null;
            if ( ! ds.hasErrors() ) {
                model.setOptions( command.getRows(), ds );
            }

            CCLogger.getInstance().debug(command.toString());

            model.fireAfterExecuteSelectEvent( e );

            ds.closeConnection();
        }
//End ListBoxAction

void read() { //ZoneAslFiltro Record: method read @3-7F8AAE5A

//ZoneAslFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ZoneAslFiltro Record: method read head

//ZoneAslFiltro Record: init DataSource @3-4E1E2F80
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ZoneAslFiltroDataObject ds = new ZoneAslFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_ZONA_ASL( page.getHttpGetParams().getParameter("s_ZONA_ASL") );
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ZoneAslFiltro Record: init DataSource

//ZoneAslFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ZoneAslFiltro Record: check errors

} //ZoneAslFiltro Record: method read tail @3-FCB6E20C

//ZoneAslFiltro Record: bind @3-1420074A
            public void bind(com.codecharge.components.Component model, ZoneAslFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_REGIONE").setValue(row.getS_REGIONE());
                    model.getControl("s_ZONA_ASL").setValue(row.getS_ZONA_ASL());
                }
            }
//End ZoneAslFiltro Record: bind

//ZoneAslFiltro Record: getRowFieldByName @3-1503BA73
            public Object getRowFieldByName( String name, ZoneAslFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_REGIONE".equals(name) ) {
                    value = row.getS_REGIONE();
                } else if ( "s_ZONA_ASL".equals(name) ) {
                    value = row.getS_ZONA_ASL();
                }
                return value;
            }
//End ZoneAslFiltro Record: getRowFieldByName

void InsertAction() { //ZoneAslFiltro Record: method insert @3-11643485

//ZoneAslFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ZoneAslFiltro Record: components insert actions

} //ZoneAslFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //ZoneAslFiltro Record: method update @3-5771D0AA

//ZoneAslFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ZoneAslFiltro Record: components update actions

} //ZoneAslFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //ZoneAslFiltro Record: method delete @3-11FC2E1E

//ZoneAslFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ZoneAslFiltro Record: components delete actions

} //ZoneAslFiltro Record: method delete tail @3-FCB6E20C

//ZoneAslFiltro Record: method validate @3-FA09F47A
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_REGIONE = (com.codecharge.components.ListBox) model.getChild( "s_REGIONE" );
            if (! s_REGIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_ZONA_ASL = (com.codecharge.components.TextBox) model.getChild( "s_ZONA_ASL" );
            if (! s_ZONA_ASL.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ZoneAslFiltro Record: method validate

//ZoneAslFiltro Record Tail @3-FCB6E20C
    }
//End ZoneAslFiltro Record Tail

//ZoneAslElenco Grid @8-205FF3DC
    final class ZoneAslElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End ZoneAslElenco Grid

//ZoneAslElenco Grid: method perform @8-B48879D3
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
//End ZoneAslElenco Grid: method perform

//ZoneAslElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End ZoneAslElenco Grid: method read: head

//ZoneAslElenco Grid: method read: init @8-ECAAA012
            if ( ! model.allowRead ) return true;
            ZoneAslElencoDataObject ds = new ZoneAslElencoDataObject(page);
            ds.setComponent( model );
//End ZoneAslElenco Grid: method read: init

//ZoneAslElenco Grid: set where parameters @8-B3D39F3A
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
            ds.setUrlS_ZONA_ASL( page.getHttpGetParams().getParameter("s_ZONA_ASL") );
//End ZoneAslElenco Grid: set where parameters

//ZoneAslElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End ZoneAslElenco Grid: set grid properties

//ZoneAslElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End ZoneAslElenco Grid: retrieve data

//ZoneAslElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End ZoneAslElenco Grid: check errors

//ZoneAslElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End ZoneAslElenco Grid: method read: tail

//ZoneAslElenco Grid: method bind @8-B4EBF918
        public void bind(com.codecharge.components.Component model, ZoneAslElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ZoneAslElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("CODICE_REGIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CODICE_REGIONE").clone();
                    c.setValue(row.getCODICE_REGIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CODICE_ZONA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CODICE_ZONA").clone();
                    c.setValue(row.getCODICE_ZONA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("TITOLO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TITOLO").clone();
                    c.setValue(row.getTITOLO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ID_ZONA_ASL").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ID_ZONA_ASL").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("REGIONE_DENOMINAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("REGIONE_DENOMINAZIONE").clone();
                    c.setValue(row.getREGIONE_DENOMINAZIONE());
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
//End ZoneAslElenco Grid: method bind

//ZoneAslElenco Directory: getRowFieldByName @8-B6E6F4C1
        public Object getRowFieldByName( String name, ZoneAslElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "CODICE_REGIONE".equals(name) ) {
                value = row.getCODICE_REGIONE();
            } else if ( "CODICE_ZONA".equals(name) ) {
                value = row.getCODICE_ZONA();
            } else if ( "TITOLO".equals(name) ) {
                value = row.getTITOLO();
            } else if ( "REGIONE_DENOMINAZIONE".equals(name) ) {
                value = row.getREGIONE_DENOMINAZIONE();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "ID_ZONA_ASL".equals(name) ) {
                value = row.getID_ZONA_ASL();
            }
            return value;
        }
//End ZoneAslElenco Directory: getRowFieldByName

//ZoneAslElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End ZoneAslElenco Grid: method validate

//ZoneAslElenco Grid Tail @8-FCB6E20C
    }
//End ZoneAslElenco Grid Tail

//Ad4DizionariZoneAslElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariZoneAslElenco Page: method validate

//Ad4DizionariZoneAslElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariZoneAslElencoAction Tail

