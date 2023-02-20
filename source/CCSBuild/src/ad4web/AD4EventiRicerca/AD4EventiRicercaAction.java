//AD4EventiRicercaAction imports @1-FB9079F2
package ad4web.AD4EventiRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4EventiRicercaAction imports

//AD4EventiRicercaAction class @1-A5AAD904
public class AD4EventiRicercaAction extends Action {

//End AD4EventiRicercaAction class

//AD4EventiRicercaAction: method perform @1-1736537A
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4EventiRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4EventiRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4EventiRicercaAction: method perform

//AD4EventiRicercaAction: call children actions @1-08C6333E
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
            AD4Eventi_VSearchClass AD4Eventi_VSearch = new AD4Eventi_VSearchClass();
            if ( ( redirect = AD4Eventi_VSearch.perform( page.getRecord("AD4Eventi_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4EventiVClass AD4EventiV = new AD4EventiVClass();
            AD4EventiV.perform(page.getGrid("AD4EventiV"));
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
//End AD4EventiRicercaAction: call children actions

//AD4Eventi_VSearch Record @358-496E8C6A
    final class AD4Eventi_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Eventi_VSearch Record

//AD4Eventi_VSearch Record: method perform @358-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Eventi_VSearch Record: method perform

//AD4Eventi_VSearch Record: children actions @358-C8919638
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Eventi_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("DoSearch") != null) {
                        if (validate()) {
                            DoSearchSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("RicercaAvanzataNo") != null) {
                        if (validate()) {
                            RicercaAvanzataNoAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("RicercaAvanzata") != null) {
                        if (validate()) {
                            RicercaAvanzataAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                        if (validate()) {
                            DoSearchSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                    if (page.getParameter("DoSearch") != null) {
                        if (validate()) {
                            DoSearchSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("RicercaAvanzataNo") != null) {
                        if (validate()) {
                            RicercaAvanzataNoAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("RicercaAvanzata") != null) {
                        if (validate()) {
                            RicercaAvanzataAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                        if (validate()) {
                            DoSearchSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_TIPO(model.getListBox("s_TIPO"));
            reads_UTENTE(model.getListBox("s_UTENTE"));
            reads_MODULO(model.getListBox("s_MODULO"));
            reads_ISTANZA(model.getListBox("s_ISTANZA"));
            reads_GRAVITA(model.getListBox("s_GRAVITA"));
            reads_STATO(model.getListBox("s_STATO"));
//End AD4Eventi_VSearch Record: children actions

//AD4Eventi_VSearch Record: method perform tail @358-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Eventi_VSearch Record: method perform tail

//DoSearch Button @414-F0F976D8
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4EventiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

//RicercaAvanzataNo Button @398-BACBDA94
        void RicercaAvanzataNoAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("RicercaAvanzataNo");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End RicercaAvanzataNo Button

//RicercaAvanzata Button @370-6AB253F2
        void RicercaAvanzataAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("RicercaAvanzata");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End RicercaAvanzata Button

//ListBoxAction @383-7E346022
        protected void reads_TIPO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "ERROR;Errore;ARC%;Archiviazione;ARC_%;Archiviazione - solo file;APPTRC;Messaggi applicativi;%;Tutti" );
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

//ListBoxAction @363-8B55B8A3
        protected void reads_GRAVITA(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "I;Informazione;E;Esclamazione;S;Errore Bloccante" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @364-B1BC337C
        protected void reads_STATO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "U;Attivo;D;Da Archiviare;A;Archiviato" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

void read() { //AD4Eventi_VSearch Record: method read @358-7F8AAE5A

//AD4Eventi_VSearch Record: method read head @358-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Eventi_VSearch Record: method read head

//AD4Eventi_VSearch Record: init DataSource @358-B125730C
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Eventi_VSearchDataObject ds = new AD4Eventi_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_TIPO( page.getHttpGetParams().getParameter("s_TIPO") );
            ds.setUrlS_TESTO( page.getHttpGetParams().getParameter("s_TESTO") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
            ds.setUrlS_DB_USER( page.getHttpGetParams().getParameter("s_DB_USER") );
            ds.setUrlS_DATA_DA( page.getHttpGetParams().getParameter("s_DATA_DA") );
            ds.setUrlS_DATA_A( page.getHttpGetParams().getParameter("s_DATA_A") );
            ds.setUrlS_NOTE( page.getHttpGetParams().getParameter("s_NOTE") );
            ds.setUrlS_GRAVITA( page.getHttpGetParams().getParameter("s_GRAVITA") );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            ds.setUrlS_FILE( page.getHttpGetParams().getParameter("s_FILE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Eventi_VSearch Record: init DataSource

//AD4Eventi_VSearch Record: check errors @358-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Eventi_VSearch Record: check errors

} //AD4Eventi_VSearch Record: method read tail @358-FCB6E20C

//AD4Eventi_VSearch Record: bind @358-BC6FDB2E
            public void bind(com.codecharge.components.Component model, AD4Eventi_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                    model.getControl("DESCRIZIONE_FILTRO").setValue(row.getDESCRIZIONE_FILTRO());
                }
            }
//End AD4Eventi_VSearch Record: bind

//AD4Eventi_VSearch Record: getRowFieldByName @358-F1615A08
            public Object getRowFieldByName( String name, AD4Eventi_VSearchRow row ) {
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
                } else if ( "s_TESTO".equals(name) ) {
                    value = row.getS_TESTO();
                } else if ( "s_UTENTE".equals(name) ) {
                    value = row.getS_UTENTE();
                } else if ( "s_MODULO".equals(name) ) {
                    value = row.getS_MODULO();
                } else if ( "s_ISTANZA".equals(name) ) {
                    value = row.getS_ISTANZA();
                } else if ( "s_DB_USER".equals(name) ) {
                    value = row.getS_DB_USER();
                } else if ( "s_GRAVITA".equals(name) ) {
                    value = row.getS_GRAVITA();
                } else if ( "s_STATO".equals(name) ) {
                    value = row.getS_STATO();
                } else if ( "s_FILE".equals(name) ) {
                    value = row.getS_FILE();
                } else if ( "s_NOTE".equals(name) ) {
                    value = row.getS_NOTE();
                }
                return value;
            }
//End AD4Eventi_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Eventi_VSearch Record: method insert @358-11643485

//AD4Eventi_VSearch Record: components insert actions @358-68525650
            if (! model.hasErrors()) {
            }
//End AD4Eventi_VSearch Record: components insert actions

} //AD4Eventi_VSearch Record: method insert tail @358-FCB6E20C

void UpdateAction() { //AD4Eventi_VSearch Record: method update @358-5771D0AA

//AD4Eventi_VSearch Record: components update actions @358-68525650
            if (! model.hasErrors()) {
            }
//End AD4Eventi_VSearch Record: components update actions

} //AD4Eventi_VSearch Record: method update tail @358-FCB6E20C

void DeleteAction() { //AD4Eventi_VSearch Record: method delete @358-11FC2E1E

//AD4Eventi_VSearch Record: components delete actions @358-68525650
            if (! model.hasErrors()) {
            }
//End AD4Eventi_VSearch Record: components delete actions

} //AD4Eventi_VSearch Record: method delete tail @358-FCB6E20C

//AD4Eventi_VSearch Record: method validate @358-F939C1F2
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

            com.codecharge.components.TextBox s_TESTO = (com.codecharge.components.TextBox) model.getChild( "s_TESTO" );
            if (! s_TESTO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_UTENTE = (com.codecharge.components.ListBox) model.getChild( "s_UTENTE" );
            if (! s_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_MODULO = (com.codecharge.components.ListBox) model.getChild( "s_MODULO" );
            if (! s_MODULO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_ISTANZA = (com.codecharge.components.ListBox) model.getChild( "s_ISTANZA" );
            if (! s_ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_DB_USER = (com.codecharge.components.TextBox) model.getChild( "s_DB_USER" );
            if (! s_DB_USER.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_GRAVITA = (com.codecharge.components.ListBox) model.getChild( "s_GRAVITA" );
            if (! s_GRAVITA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_STATO = (com.codecharge.components.ListBox) model.getChild( "s_STATO" );
            if (! s_STATO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_FILE = (com.codecharge.components.TextBox) model.getChild( "s_FILE" );
            if (! s_FILE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_NOTE = (com.codecharge.components.TextBox) model.getChild( "s_NOTE" );
            if (! s_NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Eventi_VSearch Record: method validate

//AD4Eventi_VSearch Record Tail @358-FCB6E20C
    }
//End AD4Eventi_VSearch Record Tail

//AD4EventiV Grid @142-395C6882
    final class AD4EventiVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4EventiV Grid

//AD4EventiV Grid: method perform @142-B48879D3
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
//End AD4EventiV Grid: method perform

//AD4EventiV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4EventiV Grid: method read: head

//AD4EventiV Grid: method read: init @142-5CC46813
            if ( ! model.allowRead ) return true;
            AD4EventiVDataObject ds = new AD4EventiVDataObject(page);
            ds.setComponent( model );
//End AD4EventiV Grid: method read: init

//AD4EventiV Grid: set where parameters @142-2B628AFA
            ds.setUrlS_TIPO( page.getHttpGetParams().getParameter("s_TIPO") );
            ds.setUrlS_TESTO( page.getHttpGetParams().getParameter("s_TESTO") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_DB_USER( page.getHttpGetParams().getParameter("s_DB_USER") );
            ds.setUrlS_DATA_DA( page.getHttpGetParams().getParameter("s_DATA_DA") );
            ds.setUrlS_DATA_A( page.getHttpGetParams().getParameter("s_DATA_A") );
            ds.setUrlS_GRAVITA( page.getHttpGetParams().getParameter("s_GRAVITA") );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            ds.setUrlS_NOTE( page.getHttpGetParams().getParameter("s_NOTE") );
            ds.setUrlS_FILE( page.getHttpGetParams().getParameter("s_FILE") );
            ds.setUrlS_SOLO_FILE( page.getHttpGetParams().getParameter("s_SOLO_FILE") );
//End AD4EventiV Grid: set where parameters

//AD4EventiV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4EventiV Grid: set grid properties

//AD4EventiV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4EventiV Grid: retrieve data

//AD4EventiV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4EventiV Grid: check errors

//AD4EventiV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4EventiV Grid: method read: tail

//AD4EventiV Grid: method bind @142-F1B97D5D
        public void bind(com.codecharge.components.Component model, AD4EventiVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4EventiVRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("ID_EVENTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ID_EVENTO").clone();
                    c.setValue(row.getID_EVENTO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ID_EVENTO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ID_EVENTO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DATA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATA").clone();
                    c.setValue(row.getDATA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESC_TIPO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_TIPO").clone();
                    c.setValue(row.getDESC_TIPO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESC_TIPO_RIDOTTA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_TIPO_RIDOTTA").clone();
                    c.setValue(row.getDESC_TIPO_RIDOTTA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("TESTO_NOTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TESTO_NOTE").clone();
                    c.setValue(row.getTESTO_NOTE());
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

                c = (com.codecharge.components.Control) hashRow.get("GRAVITA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("GRAVITA").clone();
                    c.setValue(row.getGRAVITA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESC_STATO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_STATO").clone();
                    c.setValue(row.getDESC_STATO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESC_NOTIFICATO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_NOTIFICATO").clone();
                    c.setValue(row.getDESC_NOTIFICATO());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4EventiV Grid: method bind

//AD4EventiV Directory: getRowFieldByName @142-620E5A9D
        public Object getRowFieldByName( String name, AD4EventiVRow row ) {
            Object value = null;
            if ( "ID_EVENTO".equals(name) ) {
                value = row.getID_EVENTO();
            } else if ( "DATA".equals(name) ) {
                value = row.getDATA();
            } else if ( "DESC_TIPO".equals(name) ) {
                value = row.getDESC_TIPO();
            } else if ( "DESC_TIPO_RIDOTTA".equals(name) ) {
                value = row.getDESC_TIPO_RIDOTTA();
            } else if ( "TESTO_NOTE".equals(name) ) {
                value = row.getTESTO_NOTE();
            } else if ( "PROVENIENZA".equals(name) ) {
                value = row.getPROVENIENZA();
            } else if ( "DB_USER".equals(name) ) {
                value = row.getDB_USER();
            } else if ( "GRAVITA".equals(name) ) {
                value = row.getGRAVITA();
            } else if ( "DESC_STATO".equals(name) ) {
                value = row.getDESC_STATO();
            } else if ( "DESC_NOTIFICATO".equals(name) ) {
                value = row.getDESC_NOTIFICATO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End AD4EventiV Directory: getRowFieldByName

//AD4EventiV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4EventiV Grid: method validate

//AD4EventiV Grid Tail @142-FCB6E20C
    }
//End AD4EventiV Grid Tail

//AD4EventiRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4EventiRicerca Page: method validate

//AD4EventiRicercaAction Tail @1-FCB6E20C
}
//End AD4EventiRicercaAction Tail
