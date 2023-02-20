//Ad4DizionariComuniElencoAction imports @1-601A8E9E
package ad4web.Ad4DizionariComuniElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariComuniElencoAction imports

//Ad4DizionariComuniElencoAction class @1-2A3585DA
public class Ad4DizionariComuniElencoAction extends Action {

//End Ad4DizionariComuniElencoAction class

//Ad4DizionariComuniElencoAction: method perform @1-0C767317
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariComuniElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariComuniElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariComuniElencoAction: method perform

//Ad4DizionariComuniElencoAction: call children actions @1-7DE08BD8
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariGuidaParent",page);
            ad4web.Ad4DizionariGuida.Ad4DizionariGuidaAction Ad4DizionariGuida = new ad4web.Ad4DizionariGuida.Ad4DizionariGuidaAction();
            result = result != null ? result : Ad4DizionariGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            ComuniFiltroClass ComuniFiltro = new ComuniFiltroClass();
            if ( ( redirect = ComuniFiltro.perform( page.getRecord("ComuniFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            ComuniElencoClass ComuniElenco = new ComuniElencoClass();
            ComuniElenco.perform(page.getGrid("ComuniElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariComuniElencoAction: call children actions

//ComuniFiltro Record @3-04B2C5B4
    final class ComuniFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ComuniFiltro Record

//ComuniFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End ComuniFiltro Record: method perform

//ComuniFiltro Record: children actions @3-6D6B960C
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ComuniFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_PROVINCIA(model.getListBox("s_PROVINCIA"));
//End ComuniFiltro Record: children actions

//ComuniFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ComuniFiltro Record: method perform tail

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

//ListBoxAction @26-828A78A1
        protected void reads_PROVINCIA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select provincia "
                        + "      ,denominazione "
                        + "  from ad4_province "
                        + "" );
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

void read() { //ComuniFiltro Record: method read @3-7F8AAE5A

//ComuniFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ComuniFiltro Record: method read head

//ComuniFiltro Record: init DataSource @3-54770B71
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ComuniFiltroDataObject ds = new ComuniFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_PROVINCIA( page.getHttpGetParams().getParameter("s_PROVINCIA") );
            ds.setUrlS_COMUNE( page.getHttpGetParams().getParameter("s_COMUNE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ComuniFiltro Record: init DataSource

//ComuniFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ComuniFiltro Record: check errors

} //ComuniFiltro Record: method read tail @3-FCB6E20C

//ComuniFiltro Record: bind @3-450A3B64
            public void bind(com.codecharge.components.Component model, ComuniFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_PROVINCIA").setValue(row.getS_PROVINCIA());
                    model.getControl("s_COMUNE").setValue(row.getS_COMUNE());
                }
            }
//End ComuniFiltro Record: bind

//ComuniFiltro Record: getRowFieldByName @3-19CB540A
            public Object getRowFieldByName( String name, ComuniFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_PROVINCIA".equals(name) ) {
                    value = row.getS_PROVINCIA();
                } else if ( "s_COMUNE".equals(name) ) {
                    value = row.getS_COMUNE();
                }
                return value;
            }
//End ComuniFiltro Record: getRowFieldByName

void InsertAction() { //ComuniFiltro Record: method insert @3-11643485

//ComuniFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ComuniFiltro Record: components insert actions

} //ComuniFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //ComuniFiltro Record: method update @3-5771D0AA

//ComuniFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ComuniFiltro Record: components update actions

} //ComuniFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //ComuniFiltro Record: method delete @3-11FC2E1E

//ComuniFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ComuniFiltro Record: components delete actions

} //ComuniFiltro Record: method delete tail @3-FCB6E20C

//ComuniFiltro Record: method validate @3-780FA307
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_PROVINCIA = (com.codecharge.components.ListBox) model.getChild( "s_PROVINCIA" );
            if (! s_PROVINCIA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_COMUNE = (com.codecharge.components.TextBox) model.getChild( "s_COMUNE" );
            if (! s_COMUNE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ComuniFiltro Record: method validate

//ComuniFiltro Record Tail @3-FCB6E20C
    }
//End ComuniFiltro Record Tail

//ComuniElenco Grid @8-5FDC2085
    final class ComuniElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End ComuniElenco Grid

//ComuniElenco Grid: method perform @8-B48879D3
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
//End ComuniElenco Grid: method perform

//ComuniElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End ComuniElenco Grid: method read: head

//ComuniElenco Grid: method read: init @8-1AAEC237
            if ( ! model.allowRead ) return true;
            ComuniElencoDataObject ds = new ComuniElencoDataObject(page);
            ds.setComponent( model );
//End ComuniElenco Grid: method read: init

//ComuniElenco Grid: set where parameters @8-AE3D2EAD
            ds.setUrlS_COMUNE( page.getHttpGetParams().getParameter("s_COMUNE") );
            ds.setUrlS_PROVINCIA( page.getHttpGetParams().getParameter("s_PROVINCIA") );
//End ComuniElenco Grid: set where parameters

//ComuniElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End ComuniElenco Grid: set grid properties

//ComuniElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End ComuniElenco Grid: retrieve data

//ComuniElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End ComuniElenco Grid: check errors

//ComuniElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End ComuniElenco Grid: method read: tail

//ComuniElenco Grid: method bind @8-391FA0BB
        public void bind(com.codecharge.components.Component model, ComuniElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ComuniElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("PROVINCIA_STATO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROVINCIA_STATO").clone();
                    c.setValue(row.getPROVINCIA_STATO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("COMUNE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COMUNE").clone();
                    c.setValue(row.getCOMUNE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DENOMINAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DENOMINAZIONE").clone();
                    c.setValue(row.getDENOMINAZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("COMUNE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("COMUNE").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("PROVINCIA_STATO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("PROVINCIA_STATO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("CAP");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CAP").clone();
                    c.setValue(row.getCAP());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PROVINCIA_DESC");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROVINCIA_DESC").clone();
                    c.setValue(row.getPROVINCIA_DESC());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("SOPPRESSIONED_DATA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SOPPRESSIONED_DATA").clone();
                    c.setValue(row.getSOPPRESSIONED_DATA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("FUSIONE_DESC");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FUSIONE_DESC").clone();
                    c.setValue(row.getFUSIONE_DESC());
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
//End ComuniElenco Grid: method bind

//ComuniElenco Directory: getRowFieldByName @8-9D30123D
        public Object getRowFieldByName( String name, ComuniElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "PROVINCIA_STATO".equals(name) ) {
                value = row.getPROVINCIA_STATO();
            } else if ( "COMUNE".equals(name) ) {
                value = row.getCOMUNE();
            } else if ( "DENOMINAZIONE".equals(name) ) {
                value = row.getDENOMINAZIONE();
            } else if ( "CAP".equals(name) ) {
                value = row.getCAP();
            } else if ( "PROVINCIA_DESC".equals(name) ) {
                value = row.getPROVINCIA_DESC();
            } else if ( "SOPPRESSIONED_DATA".equals(name) ) {
                value = row.getSOPPRESSIONED_DATA();
            } else if ( "FUSIONE_DESC".equals(name) ) {
                value = row.getFUSIONE_DESC();
            } else if ( "UTENTE_AGGIORNAMENTO".equals(name) ) {
                value = row.getUTENTE_AGGIORNAMENTO();
            } else if ( "DATA_AGGIORNAMENTO".equals(name) ) {
                value = row.getDATA_AGGIORNAMENTO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End ComuniElenco Directory: getRowFieldByName

//ComuniElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End ComuniElenco Grid: method validate

//ComuniElenco Grid Tail @8-FCB6E20C
    }
//End ComuniElenco Grid Tail

//Ad4DizionariComuniElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariComuniElenco Page: method validate

//Ad4DizionariComuniElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariComuniElencoAction Tail

