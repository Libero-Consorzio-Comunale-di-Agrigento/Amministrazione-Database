//Ad4DizionariProvinceElencoAction imports @1-34BCB154
package restrict.Ad4DizionariProvinceElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariProvinceElencoAction imports

//Ad4DizionariProvinceElencoAction class @1-93142860
public class Ad4DizionariProvinceElencoAction extends Action {

//End Ad4DizionariProvinceElencoAction class

//Ad4DizionariProvinceElencoAction: method perform @1-4218574F
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariProvinceElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariProvinceElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariProvinceElencoAction: method perform

//Ad4DizionariProvinceElencoAction: call children actions @1-50032797
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariGuidaParent",page);
            restrict.Ad4DizionariGuida.Ad4DizionariGuidaAction Ad4DizionariGuida = new restrict.Ad4DizionariGuida.Ad4DizionariGuidaAction();
            result = result != null ? result : Ad4DizionariGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            ProvinceFiltroClass ProvinceFiltro = new ProvinceFiltroClass();
            if ( ( redirect = ProvinceFiltro.perform( page.getRecord("ProvinceFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            ProvinceElencoClass ProvinceElenco = new ProvinceElencoClass();
            ProvinceElenco.perform(page.getGrid("ProvinceElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariProvinceElencoAction: call children actions

//ProvinceFiltro Record @3-FC18A3A1
    final class ProvinceFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ProvinceFiltro Record

//ProvinceFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End ProvinceFiltro Record: method perform

//ProvinceFiltro Record: children actions @3-CF1136A2
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ProvinceFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_REGIONE(model.getListBox("s_REGIONE"));
//End ProvinceFiltro Record: children actions

//ProvinceFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ProvinceFiltro Record: method perform tail

//DoSearch Button @25-7F387837
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

void read() { //ProvinceFiltro Record: method read @3-7F8AAE5A

//ProvinceFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ProvinceFiltro Record: method read head

//ProvinceFiltro Record: init DataSource @3-E079D99F
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ProvinceFiltroDataObject ds = new ProvinceFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_PROVINCIA( page.getHttpGetParams().getParameter("s_PROVINCIA") );
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ProvinceFiltro Record: init DataSource

//ProvinceFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ProvinceFiltro Record: check errors

} //ProvinceFiltro Record: method read tail @3-FCB6E20C

//ProvinceFiltro Record: bind @3-762B9E33
            public void bind(com.codecharge.components.Component model, ProvinceFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_REGIONE").setValue(row.getS_REGIONE());
                    model.getControl("s_PROVINCIA").setValue(row.getS_PROVINCIA());
                }
            }
//End ProvinceFiltro Record: bind

//ProvinceFiltro Record: getRowFieldByName @3-91EEF892
            public Object getRowFieldByName( String name, ProvinceFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_REGIONE".equals(name) ) {
                    value = row.getS_REGIONE();
                } else if ( "s_PROVINCIA".equals(name) ) {
                    value = row.getS_PROVINCIA();
                }
                return value;
            }
//End ProvinceFiltro Record: getRowFieldByName

void InsertAction() { //ProvinceFiltro Record: method insert @3-11643485

//ProvinceFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ProvinceFiltro Record: components insert actions

} //ProvinceFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //ProvinceFiltro Record: method update @3-5771D0AA

//ProvinceFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ProvinceFiltro Record: components update actions

} //ProvinceFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //ProvinceFiltro Record: method delete @3-11FC2E1E

//ProvinceFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ProvinceFiltro Record: components delete actions

} //ProvinceFiltro Record: method delete tail @3-FCB6E20C

//ProvinceFiltro Record: method validate @3-51D819C7
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_REGIONE = (com.codecharge.components.ListBox) model.getChild( "s_REGIONE" );
            if (! s_REGIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_PROVINCIA = (com.codecharge.components.TextBox) model.getChild( "s_PROVINCIA" );
            if (! s_PROVINCIA.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ProvinceFiltro Record: method validate

//ProvinceFiltro Record Tail @3-FCB6E20C
    }
//End ProvinceFiltro Record Tail

//ProvinceElenco Grid @8-E463F950
    final class ProvinceElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End ProvinceElenco Grid

//ProvinceElenco Grid: method perform @8-B48879D3
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
//End ProvinceElenco Grid: method perform

//ProvinceElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End ProvinceElenco Grid: method read: head

//ProvinceElenco Grid: method read: init @8-1BE51505
            if ( ! model.allowRead ) return true;
            ProvinceElencoDataObject ds = new ProvinceElencoDataObject(page);
            ds.setComponent( model );
//End ProvinceElenco Grid: method read: init

//ProvinceElenco Grid: set where parameters @8-D5B4C822
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
            ds.setUrlS_PROVINCIA( page.getHttpGetParams().getParameter("s_PROVINCIA") );
//End ProvinceElenco Grid: set where parameters

//ProvinceElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End ProvinceElenco Grid: set grid properties

//ProvinceElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End ProvinceElenco Grid: retrieve data

//ProvinceElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End ProvinceElenco Grid: check errors

//ProvinceElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End ProvinceElenco Grid: method read: tail

//ProvinceElenco Grid: method bind @8-478FD0D0
        public void bind(com.codecharge.components.Component model, ProvinceElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ProvinceElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("PROVINCIA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROVINCIA").clone();
                    c.setValue(row.getPROVINCIA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("LINK_COMUNI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("LINK_COMUNI").clone();
                    c.setValue(row.getLINK_COMUNI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DENOMINAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DENOMINAZIONE").clone();
                    c.setValue(row.getDENOMINAZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("PROVINCIA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("PROVINCIA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("SIGLA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SIGLA").clone();
                    c.setValue(row.getSIGLA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("REGIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("REGIONE").clone();
                    c.setValue(row.getREGIONE());
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
//End ProvinceElenco Grid: method bind

//ProvinceElenco Directory: getRowFieldByName @8-3EF3F036
        public Object getRowFieldByName( String name, ProvinceElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "PROVINCIA".equals(name) ) {
                value = row.getPROVINCIA();
            } else if ( "LINK_COMUNI".equals(name) ) {
                value = row.getLINK_COMUNI();
            } else if ( "DENOMINAZIONE".equals(name) ) {
                value = row.getDENOMINAZIONE();
            } else if ( "SIGLA".equals(name) ) {
                value = row.getSIGLA();
            } else if ( "REGIONE".equals(name) ) {
                value = row.getREGIONE();
            } else if ( "UTENTE_AGGIORNAMENTO".equals(name) ) {
                value = row.getUTENTE_AGGIORNAMENTO();
            } else if ( "DATA_AGGIORNAMENTO".equals(name) ) {
                value = row.getDATA_AGGIORNAMENTO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End ProvinceElenco Directory: getRowFieldByName

//ProvinceElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End ProvinceElenco Grid: method validate

//ProvinceElenco Grid Tail @8-FCB6E20C
    }
//End ProvinceElenco Grid Tail

//Ad4DizionariProvinceElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariProvinceElenco Page: method validate

//Ad4DizionariProvinceElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariProvinceElencoAction Tail

