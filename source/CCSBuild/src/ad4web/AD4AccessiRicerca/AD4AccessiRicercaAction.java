//AD4AccessiRicercaAction imports @1-A94B09F6
package ad4web.AD4AccessiRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4AccessiRicercaAction imports

//AD4AccessiRicercaAction class @1-9CB8F82B
public class AD4AccessiRicercaAction extends Action {

//End AD4AccessiRicercaAction class

//AD4AccessiRicercaAction: method perform @1-27C10AC9
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4AccessiRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4AccessiRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4AccessiRicercaAction: method perform

//AD4AccessiRicercaAction: call children actions @1-013C3268
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
            AD4Accessi_VSearchClass AD4Accessi_VSearch = new AD4Accessi_VSearchClass();
            if ( ( redirect = AD4Accessi_VSearch.perform( page.getRecord("AD4Accessi_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            ToolboxClass Toolbox = new ToolboxClass();
            Toolbox.perform(page.getGrid("Toolbox"));
        }
        if (result == null) {
            AD4AccessiVClass AD4AccessiV = new AD4AccessiVClass();
            AD4AccessiV.perform(page.getGrid("AD4AccessiV"));
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
//End AD4AccessiRicercaAction: call children actions

//AD4Accessi_VSearch Record @358-DEFA5B60
    final class AD4Accessi_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Accessi_VSearch Record

//AD4Accessi_VSearch Record: method perform @358-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Accessi_VSearch Record: method perform

//AD4Accessi_VSearch Record: children actions @358-2AA2ACFD
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Accessi_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_TIPO(model.getListBox("s_TIPO"));
            reads_UTENTE(model.getListBox("s_UTENTE"));
            reads_MODULO(model.getListBox("s_MODULO"));
            reads_ISTANZA(model.getListBox("s_ISTANZA"));
            reads_AUTENTICAZIONE(model.getListBox("s_AUTENTICAZIONE"));
            reads_STATO(model.getListBox("s_STATO"));
//End AD4Accessi_VSearch Record: children actions

//AD4Accessi_VSearch Record: method perform tail @358-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Accessi_VSearch Record: method perform tail

//DoSearch Button @414-461964CC
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4AccessiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

//ListBoxAction @383-5F054E70
        protected void reads_TIPO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "ON;Attivo;OFF;Chiuso;OUT;Uscito;TRC;Funzione;ABT;Uscito con Errore;WPW;Password Errata" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @395-982C8C3A
        protected void reads_UTENTE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT *  "
                        + "  FROM UTENTI "
                        + " where tipo_utente = 'U' "
                        + "" );
            command.setOrder( "nominativo" );

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

//ListBoxAction @396-46505A1F
        protected void reads_MODULO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT MODULO, MODULO||' - '||DESCRIZIONE DESCRIZIONE "
                        + "  FROM MODULI "
                        + "" );
            command.setOrder( "2" );

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

//ListBoxAction @391-C8232017
        protected void reads_ISTANZA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT ISTANZA, ISTANZA||' - '||descrizione descrizione "
                        + "FROM ISTANZE "
                        + "" );
            command.setOrder( "2" );

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

//ListBoxAction @363-33BD1593
        protected void reads_AUTENTICAZIONE(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "LDAP;Tramite LDAP;AD4;Tramite AD4" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @364-E44E4E27
        protected void reads_STATO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "U;In Uso;D;Da Archiviare;A;Archiviato" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

void read() { //AD4Accessi_VSearch Record: method read @358-7F8AAE5A

//AD4Accessi_VSearch Record: method read head @358-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Accessi_VSearch Record: method read head

//AD4Accessi_VSearch Record: init DataSource @358-3B2FAFEB
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Accessi_VSearchDataObject ds = new AD4Accessi_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_TIPO( page.getHttpGetParams().getParameter("s_TIPO") );
            ds.setUrlS_TERMINALE( page.getHttpGetParams().getParameter("s_TERMINALE") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
            ds.setUrlS_DB_USER( page.getHttpGetParams().getParameter("s_DB_USER") );
            ds.setUrlS_DATA_DA( page.getHttpGetParams().getParameter("s_DATA_DA") );
            ds.setUrlS_DATA_A( page.getHttpGetParams().getParameter("s_DATA_A") );
            ds.setUrlS_NOTE( page.getHttpGetParams().getParameter("s_NOTE") );
            ds.setUrlS_AUTENTICAZIONE( page.getHttpGetParams().getParameter("s_AUTENTICAZIONE") );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            try {
                ds.setSesAD4ACCEELI( SessionStorage.getInstance(req).getAttribute("AD4ACCEELI"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'AD4ACCEELI'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'AD4ACCEELI'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Accessi_VSearch Record: init DataSource

//AD4Accessi_VSearch Record: check errors @358-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Accessi_VSearch Record: check errors

} //AD4Accessi_VSearch Record: method read tail @358-FCB6E20C

//AD4Accessi_VSearch Record: bind @358-BA9D8CDE
            public void bind(com.codecharge.components.Component model, AD4Accessi_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                    model.getControl("DESCRIZIONE_FILTRO").setValue(row.getDESCRIZIONE_FILTRO());
                    model.getControl("ELIMINATI").setValue(row.getELIMINATI());
                }
            }
//End AD4Accessi_VSearch Record: bind

//AD4Accessi_VSearch Record: getRowFieldByName @358-ACE71646
            public Object getRowFieldByName( String name, AD4Accessi_VSearchRow row ) {
                Object value = null;
                if ( "s_TIPO".equals(name) ) {
                    value = row.getS_TIPO();
                } else if ( "DESCRIZIONE_FILTRO".equals(name) ) {
                    value = row.getDESCRIZIONE_FILTRO();
                } else if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_DATA_DA".equals(name) ) {
                    value = row.getS_DATA_DA();
                } else if ( "s_DATA_A".equals(name) ) {
                    value = row.getS_DATA_A();
                } else if ( "s_UTENTE".equals(name) ) {
                    value = row.getS_UTENTE();
                } else if ( "ELIMINATI".equals(name) ) {
                    value = row.getELIMINATI();
                } else if ( "s_MODULO".equals(name) ) {
                    value = row.getS_MODULO();
                } else if ( "s_RICERCA".equals(name) ) {
                    value = row.getS_RICERCA();
                } else if ( "s_ISTANZA".equals(name) ) {
                    value = row.getS_ISTANZA();
                } else if ( "s_NOTE".equals(name) ) {
                    value = row.getS_NOTE();
                } else if ( "s_DB_USER".equals(name) ) {
                    value = row.getS_DB_USER();
                } else if ( "s_TERMINALE".equals(name) ) {
                    value = row.getS_TERMINALE();
                } else if ( "s_AUTENTICAZIONE".equals(name) ) {
                    value = row.getS_AUTENTICAZIONE();
                } else if ( "s_STATO".equals(name) ) {
                    value = row.getS_STATO();
                }
                return value;
            }
//End AD4Accessi_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Accessi_VSearch Record: method insert @358-11643485

//AD4Accessi_VSearch Record: components insert actions @358-68525650
            if (! model.hasErrors()) {
            }
//End AD4Accessi_VSearch Record: components insert actions

} //AD4Accessi_VSearch Record: method insert tail @358-FCB6E20C

void UpdateAction() { //AD4Accessi_VSearch Record: method update @358-5771D0AA

//AD4Accessi_VSearch Record: components update actions @358-68525650
            if (! model.hasErrors()) {
            }
//End AD4Accessi_VSearch Record: components update actions

} //AD4Accessi_VSearch Record: method update tail @358-FCB6E20C

void DeleteAction() { //AD4Accessi_VSearch Record: method delete @358-11FC2E1E

//AD4Accessi_VSearch Record: components delete actions @358-68525650
            if (! model.hasErrors()) {
            }
//End AD4Accessi_VSearch Record: components delete actions

} //AD4Accessi_VSearch Record: method delete tail @358-FCB6E20C

//AD4Accessi_VSearch Record: method validate @358-F7D21A26
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_TIPO = (com.codecharge.components.ListBox) model.getChild( "s_TIPO" );
            if (! s_TIPO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden DESCRIZIONE_FILTRO = (com.codecharge.components.Hidden) model.getChild( "DESCRIZIONE_FILTRO" );
            if (! DESCRIZIONE_FILTRO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_DATA_DA = (com.codecharge.components.TextBox) model.getChild( "s_DATA_DA" );
            if (! s_DATA_DA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_DATA_A = (com.codecharge.components.TextBox) model.getChild( "s_DATA_A" );
            if (! s_DATA_A.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_UTENTE = (com.codecharge.components.ListBox) model.getChild( "s_UTENTE" );
            if (! s_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ELIMINATI = (com.codecharge.components.Hidden) model.getChild( "ELIMINATI" );
            if (! ELIMINATI.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_MODULO = (com.codecharge.components.ListBox) model.getChild( "s_MODULO" );
            if (! s_MODULO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden s_RICERCA = (com.codecharge.components.Hidden) model.getChild( "s_RICERCA" );
            if (! s_RICERCA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_ISTANZA = (com.codecharge.components.ListBox) model.getChild( "s_ISTANZA" );
            if (! s_ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_NOTE = (com.codecharge.components.TextBox) model.getChild( "s_NOTE" );
            if (! s_NOTE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_DB_USER = (com.codecharge.components.TextBox) model.getChild( "s_DB_USER" );
            if (! s_DB_USER.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_TERMINALE = (com.codecharge.components.TextBox) model.getChild( "s_TERMINALE" );
            if (! s_TERMINALE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_AUTENTICAZIONE = (com.codecharge.components.ListBox) model.getChild( "s_AUTENTICAZIONE" );
            if (! s_AUTENTICAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_STATO = (com.codecharge.components.ListBox) model.getChild( "s_STATO" );
            if (! s_STATO.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Accessi_VSearch Record: method validate

//AD4Accessi_VSearch Record Tail @358-FCB6E20C
    }
//End AD4Accessi_VSearch Record Tail

//Toolbox Grid @418-C623AD00
    final class ToolboxClass {
        com.codecharge.components.Grid model;
        Event e;
//End Toolbox Grid

//Toolbox Grid: method perform @418-B48879D3
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
//End Toolbox Grid: method perform

//Toolbox Grid: method read: head @418-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Toolbox Grid: method read: head

//Toolbox Grid: method read: init @418-A0CB6DF8
            if ( ! model.allowRead ) return true;
            ToolboxDataObject ds = new ToolboxDataObject(page);
            ds.setComponent( model );
//End Toolbox Grid: method read: init

//Toolbox Grid: set where parameters @418-8813CB01
            ds.setUrlS_TIPO( page.getHttpGetParams().getParameter("s_TIPO") );
            ds.setUrlS_TERMINALE( page.getHttpGetParams().getParameter("s_TERMINALE") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_DB_USER( page.getHttpGetParams().getParameter("s_DB_USER") );
            ds.setUrlS_DATA_DA( page.getHttpGetParams().getParameter("s_DATA_DA") );
            ds.setUrlS_DATA_A( page.getHttpGetParams().getParameter("s_DATA_A") );
            ds.setUrlS_NOTE( page.getHttpGetParams().getParameter("s_NOTE") );
            ds.setUrlS_AUTENTICAZIONE( page.getHttpGetParams().getParameter("s_AUTENTICAZIONE") );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            ds.setUrlS_RICERCA( page.getHttpGetParams().getParameter("s_RICERCA") );
//End Toolbox Grid: set where parameters

//Toolbox Grid: set grid properties @418-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Toolbox Grid: set grid properties

//Toolbox Grid: retrieve data @418-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Toolbox Grid: retrieve data

//Toolbox Grid: check errors @418-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Toolbox Grid: check errors

//Toolbox Grid: method read: tail @418-F575E732
            return ( ! isErrors );
        }
//End Toolbox Grid: method read: tail

//Toolbox Grid: method bind @418-0533C6A3
        public void bind(com.codecharge.components.Component model, ToolboxRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ToolboxRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("RICERCA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("RICERCA").clone();
                    c.setValue(row.getRICERCA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ELIMINA_ARCHIVIATI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ELIMINA_ARCHIVIATI").clone();
                    c.setValue(row.getELIMINA_ARCHIVIATI());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End Toolbox Grid: method bind

//Toolbox Directory: getRowFieldByName @418-9D36E459
        public Object getRowFieldByName( String name, ToolboxRow row ) {
            Object value = null;
            if ( "RICERCA".equals(name) ) {
                value = row.getRICERCA();
            } else if ( "ELIMINA_ARCHIVIATI".equals(name) ) {
                value = row.getELIMINA_ARCHIVIATI();
            }
            return value;
        }
//End Toolbox Directory: getRowFieldByName

//Toolbox Grid: method validate @418-104025BA
        boolean validate() {
            return true;
        }
//End Toolbox Grid: method validate

//Toolbox Grid Tail @418-FCB6E20C
    }
//End Toolbox Grid Tail

//AD4AccessiV Grid @142-83F56289
    final class AD4AccessiVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4AccessiV Grid

//AD4AccessiV Grid: method perform @142-B48879D3
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
//End AD4AccessiV Grid: method perform

//AD4AccessiV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4AccessiV Grid: method read: head

//AD4AccessiV Grid: method read: init @142-ECFB7B20
            if ( ! model.allowRead ) return true;
            AD4AccessiVDataObject ds = new AD4AccessiVDataObject(page);
            ds.setComponent( model );
//End AD4AccessiV Grid: method read: init

//AD4AccessiV Grid: set where parameters @142-7DD22DB6
            ds.setUrlS_TIPO( page.getHttpGetParams().getParameter("s_TIPO") );
            ds.setUrlS_TERMINALE( page.getHttpGetParams().getParameter("s_TERMINALE") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_DB_USER( page.getHttpGetParams().getParameter("s_DB_USER") );
            ds.setUrlS_DATA_DA( page.getHttpGetParams().getParameter("s_DATA_DA") );
            ds.setUrlS_DATA_A( page.getHttpGetParams().getParameter("s_DATA_A") );
            ds.setUrlS_AUTENTICAZIONE( page.getHttpGetParams().getParameter("s_AUTENTICAZIONE") );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            ds.setUrlS_NOTE( page.getHttpGetParams().getParameter("s_NOTE") );
            ds.setUrlS_RICERCA( page.getHttpGetParams().getParameter("s_RICERCA") );
//End AD4AccessiV Grid: set where parameters

//AD4AccessiV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4AccessiV Grid: set grid properties

//AD4AccessiV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4AccessiV Grid: retrieve data

//AD4AccessiV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4AccessiV Grid: check errors

//AD4AccessiV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4AccessiV Grid: method read: tail

//AD4AccessiV Grid: method bind @142-AD74638F
        public void bind(com.codecharge.components.Component model, AD4AccessiVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4AccessiVRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("TR_STYLE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TR_STYLE").clone();
                    c.setValue(row.getTR_STYLE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ACCE_ID");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ACCE_ID").clone();
                    c.setValue(row.getACCE_ID());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DATA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATA").clone();
                    c.setValue(row.getDATA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("SESSION_ID");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SESSION_ID").clone();
                    c.setValue(row.getSESSION_ID());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESC_LOG");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_LOG").clone();
                    c.setValue(row.getDESC_LOG());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PROVENIENZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROVENIENZA").clone();
                    c.setValue(row.getPROVENIENZA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DB_USER");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DB_USER").clone();
                    c.setValue(row.getDB_USER());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOTE").clone();
                    c.setValue(row.getNOTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ACCESSO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ACCESSO").clone();
                    c.setValue(row.getACCESSO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("TIPO_AUTENTICAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TIPO_AUTENTICAZIONE").clone();
                    c.setValue(row.getTIPO_AUTENTICAZIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESC_STATO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_STATO").clone();
                    c.setValue(row.getDESC_STATO());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4AccessiV Grid: method bind

//AD4AccessiV Directory: getRowFieldByName @142-CCC5B42A
        public Object getRowFieldByName( String name, AD4AccessiVRow row ) {
            Object value = null;
            if ( "TR_STYLE".equals(name) ) {
                value = row.getTR_STYLE();
            } else if ( "ACCE_ID".equals(name) ) {
                value = row.getACCE_ID();
            } else if ( "DATA".equals(name) ) {
                value = row.getDATA();
            } else if ( "SESSION_ID".equals(name) ) {
                value = row.getSESSION_ID();
            } else if ( "DESC_LOG".equals(name) ) {
                value = row.getDESC_LOG();
            } else if ( "PROVENIENZA".equals(name) ) {
                value = row.getPROVENIENZA();
            } else if ( "DB_USER".equals(name) ) {
                value = row.getDB_USER();
            } else if ( "NOTE".equals(name) ) {
                value = row.getNOTE();
            } else if ( "ACCESSO".equals(name) ) {
                value = row.getACCESSO();
            } else if ( "TIPO_AUTENTICAZIONE".equals(name) ) {
                value = row.getTIPO_AUTENTICAZIONE();
            } else if ( "DESC_STATO".equals(name) ) {
                value = row.getDESC_STATO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "ID_EVENTO".equals(name) ) {
                value = row.getID_EVENTO();
            }
            return value;
        }
//End AD4AccessiV Directory: getRowFieldByName

//AD4AccessiV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4AccessiV Grid: method validate

//AD4AccessiV Grid Tail @142-FCB6E20C
    }
//End AD4AccessiV Grid Tail

//AD4AccessiRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4AccessiRicerca Page: method validate

//AD4AccessiRicercaAction Tail @1-FCB6E20C
}
//End AD4AccessiRicercaAction Tail
