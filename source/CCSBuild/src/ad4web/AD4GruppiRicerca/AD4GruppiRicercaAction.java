//AD4GruppiRicercaAction imports @1-A18C1404
package ad4web.AD4GruppiRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GruppiRicercaAction imports

//AD4GruppiRicercaAction class @1-C10D653A
public class AD4GruppiRicercaAction extends Action {

//End AD4GruppiRicercaAction class

//AD4GruppiRicercaAction: method perform @1-259532EA
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GruppiRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GruppiRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GruppiRicercaAction: method perform

//AD4GruppiRicercaAction: call children actions @1-788BA237
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
            AD4Gruppi_VSearchClass AD4Gruppi_VSearch = new AD4Gruppi_VSearchClass();
            if ( ( redirect = AD4Gruppi_VSearch.perform( page.getRecord("AD4Gruppi_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4GruppiVClass AD4GruppiV = new AD4GruppiVClass();
            AD4GruppiV.perform(page.getGrid("AD4GruppiV"));
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
//End AD4GruppiRicercaAction: call children actions

//AD4Gruppi_VSearch Record @6-7BBE24B9
    final class AD4Gruppi_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Gruppi_VSearch Record

//AD4Gruppi_VSearch Record: method perform @6-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Gruppi_VSearch Record: method perform

//AD4Gruppi_VSearch Record: children actions @6-F71430D3
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Gruppi_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_UTENTE(model.getListBox("s_UTENTE"));
//End AD4Gruppi_VSearch Record: children actions

//AD4Gruppi_VSearch Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Gruppi_VSearch Record: method perform tail

//DoSearch Button @7-6B435665
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4GruppiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

//ListBoxAction @338-FC66F42B
        protected void reads_UTENTE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT UTENTE,  "
                        + "NOMINATIVO||' ('||UTENTE||')' NOMINATIVO "
                        + "FROM UTENTI "
                        + "WHERE TIPO_UTENTE = 'U' "
                        + "" );
            command.setOrder( "NOMINATIVO" );

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

void read() { //AD4Gruppi_VSearch Record: method read @6-7F8AAE5A

//AD4Gruppi_VSearch Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Gruppi_VSearch Record: method read head

//AD4Gruppi_VSearch Record: init DataSource @6-017AC793
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Gruppi_VSearchDataObject ds = new AD4Gruppi_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Gruppi_VSearch Record: init DataSource

//AD4Gruppi_VSearch Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Gruppi_VSearch Record: check errors

} //AD4Gruppi_VSearch Record: method read tail @6-FCB6E20C

//AD4Gruppi_VSearch Record: bind @6-F5FA684A
            public void bind(com.codecharge.components.Component model, AD4Gruppi_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                }
            }
//End AD4Gruppi_VSearch Record: bind

//AD4Gruppi_VSearch Record: getRowFieldByName @6-58D84C63
            public Object getRowFieldByName( String name, AD4Gruppi_VSearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_DESCRIZIONE".equals(name) ) {
                    value = row.getS_DESCRIZIONE();
                } else if ( "s_UTENTE".equals(name) ) {
                    value = row.getS_UTENTE();
                } else if ( "Nuovo".equals(name) ) {
                    value = row.getNuovo();
                }
                return value;
            }
//End AD4Gruppi_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Gruppi_VSearch Record: method insert @6-11643485

//AD4Gruppi_VSearch Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Gruppi_VSearch Record: components insert actions

} //AD4Gruppi_VSearch Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4Gruppi_VSearch Record: method update @6-5771D0AA

//AD4Gruppi_VSearch Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Gruppi_VSearch Record: components update actions

} //AD4Gruppi_VSearch Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4Gruppi_VSearch Record: method delete @6-11FC2E1E

//AD4Gruppi_VSearch Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Gruppi_VSearch Record: components delete actions

} //AD4Gruppi_VSearch Record: method delete tail @6-FCB6E20C

//AD4Gruppi_VSearch Record: method validate @6-48C09F50
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "s_DESCRIZIONE" );
            if (! s_DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_UTENTE = (com.codecharge.components.ListBox) model.getChild( "s_UTENTE" );
            if (! s_UTENTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Gruppi_VSearch Record: method validate

//AD4Gruppi_VSearch Record Tail @6-FCB6E20C
    }
//End AD4Gruppi_VSearch Record Tail

//AD4GruppiV Grid @142-F1AADA20
    final class AD4GruppiVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4GruppiV Grid

//AD4GruppiV Grid: method perform @142-B48879D3
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
//End AD4GruppiV Grid: method perform

//AD4GruppiV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4GruppiV Grid: method read: head

//AD4GruppiV Grid: method read: init @142-1CA7ACA8
            if ( ! model.allowRead ) return true;
            AD4GruppiVDataObject ds = new AD4GruppiVDataObject(page);
            ds.setComponent( model );
//End AD4GruppiV Grid: method read: init

//AD4GruppiV Grid: set where parameters @142-0342C0D5
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
//End AD4GruppiV Grid: set where parameters

//AD4GruppiV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4GruppiV Grid: set grid properties

//AD4GruppiV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4GruppiV Grid: retrieve data

//AD4GruppiV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4GruppiV Grid: check errors

//AD4GruppiV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4GruppiV Grid: method read: tail

//AD4GruppiV Grid: method bind @142-FF66EF6B
        public void bind(com.codecharge.components.Component model, AD4GruppiVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4GruppiVRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("GRUPPO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("GRUPPO").clone();
                    c.setValue(row.getGRUPPO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("GRUPPO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("GRUPPO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOTE").clone();
                    c.setValue(row.getNOTE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4GruppiV Grid: method bind

//AD4GruppiV Directory: getRowFieldByName @142-C8EF26A0
        public Object getRowFieldByName( String name, AD4GruppiVRow row ) {
            Object value = null;
            if ( "GRUPPO".equals(name) ) {
                value = row.getGRUPPO();
            } else if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "NOTE".equals(name) ) {
                value = row.getNOTE();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End AD4GruppiV Directory: getRowFieldByName

//AD4GruppiV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4GruppiV Grid: method validate

//AD4GruppiV Grid Tail @142-FCB6E20C
    }
//End AD4GruppiV Grid Tail

//AD4GruppiRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GruppiRicerca Page: method validate

//AD4GruppiRicercaAction Tail @1-FCB6E20C
}
//End AD4GruppiRicercaAction Tail

