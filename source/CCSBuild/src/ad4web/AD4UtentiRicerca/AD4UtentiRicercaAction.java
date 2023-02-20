//AD4UtentiRicercaAction imports @1-72C6DF1A
package ad4web.AD4UtentiRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4UtentiRicercaAction imports

//AD4UtentiRicercaAction class @1-44A4D7A3
public class AD4UtentiRicercaAction extends Action {

//End AD4UtentiRicercaAction class

//AD4UtentiRicercaAction: method perform @1-2FCF4384
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4UtentiRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4UtentiRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4UtentiRicercaAction: method perform

//AD4UtentiRicercaAction: call children actions @1-A6AA5F73
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
            AD4Utenti_VSearchClass AD4Utenti_VSearch = new AD4Utenti_VSearchClass();
            if ( ( redirect = AD4Utenti_VSearch.perform( page.getRecord("AD4Utenti_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4UtentiVClass AD4UtentiV = new AD4UtentiVClass();
            AD4UtentiV.perform(page.getGrid("AD4UtentiV"));
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
//End AD4UtentiRicercaAction: call children actions

//AD4Utenti_VSearch Record @6-38B24E45
    final class AD4Utenti_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Utenti_VSearch Record

//AD4Utenti_VSearch Record: method perform @6-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Utenti_VSearch Record: method perform

//AD4Utenti_VSearch Record: children actions @6-44482478
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Utenti_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("RicercaAvanzata") != null) {
                        if (validate()) {
                            RicercaAvanzataAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("DoSearch") != null) {
                        if (validate()) {
                            DoSearchSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                        if (validate()) {
                            DoSearchSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                    if (page.getParameter("RicercaAvanzata") != null) {
                        if (validate()) {
                            RicercaAvanzataAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("DoSearch") != null) {
                        if (validate()) {
                            DoSearchSearchAction();
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
            reads_STATO(model.getListBox("s_STATO"));
            reads_GRUPPO(model.getListBox("s_GRUPPO"));
            reads_ACCESSO(model.getListBox("s_ACCESSO"));
            reads_GRUPPO_LAVORO(model.getListBox("s_GRUPPO_LAVORO"));
//End AD4Utenti_VSearch Record: children actions

//AD4Utenti_VSearch Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Utenti_VSearch Record: method perform tail

//RicercaAvanzata Button @341-6AB253F2
        void RicercaAvanzataAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("RicercaAvanzata");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End RicercaAvanzata Button

//DoSearch Button @7-A95A0558
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4UtentiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

//ListBoxAction @322-5CA738D4
        protected void reads_STATO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "U;Attivo;R;Revocato;S;Sospeso" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @324-B03C399F
        protected void reads_GRUPPO(com.codecharge.components.ListBox model) {
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
                        + " where tipo_utente = 'G' "
                        + "   and utente <> 'ric_abil' "
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

//ListBoxAction @325-C95AA175
        protected void reads_ACCESSO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "N;Utenti che NON hanno mai fatto accesso;Y;Utenti che hanno fatto accesso almeno una volta" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @372-7998D994
        protected void reads_GRUPPO_LAVORO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT ruolo,  "
                        + "ruolo ||'-' || descrizione descrizione "
                        + "  FROM ruoli "
                        + " where gruppo_lavoro = 'S' "
                        + "" );
            command.setOrder( "ruolo" );

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

void read() { //AD4Utenti_VSearch Record: method read @6-7F8AAE5A

//AD4Utenti_VSearch Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Utenti_VSearch Record: method read head

//AD4Utenti_VSearch Record: init DataSource @6-CCC85589
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Utenti_VSearchDataObject ds = new AD4Utenti_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_NOMINATIVO( page.getHttpGetParams().getParameter("s_NOMINATIVO") );
            ds.setUrlS_NOME( page.getHttpGetParams().getParameter("s_NOME") );
            ds.setUrlS_COD_FISCALE( page.getHttpGetParams().getParameter("s_COD_FISCALE") );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            ds.setUrlS_GRUPPO( page.getHttpGetParams().getParameter("s_GRUPPO") );
            ds.setUrlS_ACCESSO( page.getHttpGetParams().getParameter("s_ACCESSO") );
            ds.setUrlS_DATA_DA( page.getHttpGetParams().getParameter("s_DATA_DA") );
            ds.setUrlS_DATA_A( page.getHttpGetParams().getParameter("s_DATA_A") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Utenti_VSearch Record: init DataSource

//AD4Utenti_VSearch Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Utenti_VSearch Record: check errors

} //AD4Utenti_VSearch Record: method read tail @6-FCB6E20C

//AD4Utenti_VSearch Record: bind @6-B03B14C5
            public void bind(com.codecharge.components.Component model, AD4Utenti_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                    model.getControl("DESCRIZIONE_FILTRO").setValue(row.getDESCRIZIONE_FILTRO());
                }
            }
//End AD4Utenti_VSearch Record: bind

//AD4Utenti_VSearch Record: getRowFieldByName @6-D13143F7
            public Object getRowFieldByName( String name, AD4Utenti_VSearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_NOMINATIVO".equals(name) ) {
                    value = row.getS_NOMINATIVO();
                } else if ( "DESCRIZIONE_FILTRO".equals(name) ) {
                    value = row.getDESCRIZIONE_FILTRO();
                } else if ( "s_NOME".equals(name) ) {
                    value = row.getS_NOME();
                } else if ( "s_COD_FISCALE".equals(name) ) {
                    value = row.getS_COD_FISCALE();
                } else if ( "s_STATO".equals(name) ) {
                    value = row.getS_STATO();
                } else if ( "s_GRUPPO".equals(name) ) {
                    value = row.getS_GRUPPO();
                } else if ( "s_ACCESSO".equals(name) ) {
                    value = row.getS_ACCESSO();
                } else if ( "s_DATA_DA".equals(name) ) {
                    value = row.getS_DATA_DA();
                } else if ( "s_DATA_A".equals(name) ) {
                    value = row.getS_DATA_A();
                } else if ( "s_RICERCA".equals(name) ) {
                    value = row.getS_RICERCA();
                } else if ( "s_GRUPPO_LAVORO".equals(name) ) {
                    value = row.getS_GRUPPO_LAVORO();
                } else if ( "Nuovo".equals(name) ) {
                    value = row.getNuovo();
                }
                return value;
            }
//End AD4Utenti_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Utenti_VSearch Record: method insert @6-11643485

//AD4Utenti_VSearch Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Utenti_VSearch Record: components insert actions

} //AD4Utenti_VSearch Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4Utenti_VSearch Record: method update @6-5771D0AA

//AD4Utenti_VSearch Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Utenti_VSearch Record: components update actions

} //AD4Utenti_VSearch Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4Utenti_VSearch Record: method delete @6-11FC2E1E

//AD4Utenti_VSearch Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Utenti_VSearch Record: components delete actions

} //AD4Utenti_VSearch Record: method delete tail @6-FCB6E20C

//AD4Utenti_VSearch Record: method validate @6-3B8C1034
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_NOMINATIVO = (com.codecharge.components.TextBox) model.getChild( "s_NOMINATIVO" );
            if (! s_NOMINATIVO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden DESCRIZIONE_FILTRO = (com.codecharge.components.Hidden) model.getChild( "DESCRIZIONE_FILTRO" );
            if (! DESCRIZIONE_FILTRO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_NOME = (com.codecharge.components.TextBox) model.getChild( "s_NOME" );
            if (! s_NOME.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_COD_FISCALE = (com.codecharge.components.TextBox) model.getChild( "s_COD_FISCALE" );
            if (! s_COD_FISCALE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_STATO = (com.codecharge.components.ListBox) model.getChild( "s_STATO" );
            if (! s_STATO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_GRUPPO = (com.codecharge.components.ListBox) model.getChild( "s_GRUPPO" );
            if (! s_GRUPPO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_ACCESSO = (com.codecharge.components.ListBox) model.getChild( "s_ACCESSO" );
            if (! s_ACCESSO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_DATA_DA = (com.codecharge.components.TextBox) model.getChild( "s_DATA_DA" );
            if (! s_DATA_DA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_DATA_A = (com.codecharge.components.TextBox) model.getChild( "s_DATA_A" );
            if (! s_DATA_A.validate()) { isControlError = true; }

            com.codecharge.components.Hidden s_RICERCA = (com.codecharge.components.Hidden) model.getChild( "s_RICERCA" );
            if (! s_RICERCA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_GRUPPO_LAVORO = (com.codecharge.components.ListBox) model.getChild( "s_GRUPPO_LAVORO" );
            if (! s_GRUPPO_LAVORO.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Utenti_VSearch Record: method validate

//AD4Utenti_VSearch Record Tail @6-FCB6E20C
    }
//End AD4Utenti_VSearch Record Tail

//AD4UtentiV Grid @142-7F7B3593
    final class AD4UtentiVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4UtentiV Grid

//AD4UtentiV Grid: method perform @142-B48879D3
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
//End AD4UtentiV Grid: method perform

//AD4UtentiV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4UtentiV Grid: method read: head

//AD4UtentiV Grid: method read: init @142-7929A3E0
            if ( ! model.allowRead ) return true;
            AD4UtentiVDataObject ds = new AD4UtentiVDataObject(page);
            ds.setComponent( model );
//End AD4UtentiV Grid: method read: init

//AD4UtentiV Grid: set where parameters @142-FFC8B197
            ds.setUrlS_NOMINATIVO( page.getHttpGetParams().getParameter("s_NOMINATIVO") );
            ds.setUrlS_DATA_DA( page.getHttpGetParams().getParameter("s_DATA_DA") );
            ds.setUrlS_DATA_A( page.getHttpGetParams().getParameter("s_DATA_A") );
            ds.setUrlS_NOME( page.getHttpGetParams().getParameter("s_NOME") );
            ds.setUrlS_RICERCA( page.getHttpGetParams().getParameter("s_RICERCA") );
            ds.setUrlS_COD_FISCALE( page.getHttpGetParams().getParameter("s_COD_FISCALE") );
            ds.setUrlS_STATO( page.getHttpGetParams().getParameter("s_STATO") );
            ds.setUrlS_ACCESSO( page.getHttpGetParams().getParameter("s_ACCESSO") );
            ds.setUrlS_GRUPPO( page.getHttpGetParams().getParameter("s_GRUPPO") );
            ds.setUrlUTENTE_ALIMENTARE( page.getHttpGetParams().getParameter("UTENTE_ALIMENTARE") );
            ds.setUrlUTENTE_ALIMENTARE_UNIFICARE( page.getHttpGetParams().getParameter("UTENTE_ALIMENTARE_UNIFICARE") );
            ds.setUrlS_GRUPPO_LAVORO( page.getHttpGetParams().getParameter("s_GRUPPO_LAVORO") );
//End AD4UtentiV Grid: set where parameters

//AD4UtentiV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4UtentiV Grid: set grid properties

//AD4UtentiV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4UtentiV Grid: retrieve data

//AD4UtentiV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4UtentiV Grid: check errors

//AD4UtentiV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4UtentiV Grid: method read: tail

//AD4UtentiV Grid: method bind @142-1ADDB1C1
        public void bind(com.codecharge.components.Component model, AD4UtentiVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4UtentiVRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("NOMINATIVO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOMINATIVO").clone();
                    c.setValue(row.getNOMINATIVO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("UTENTE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("UTENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UTENTE").clone();
                    c.setValue(row.getUTENTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DATI_UTENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATI_UTENTE").clone();
                    c.setValue(row.getDATI_UTENTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("COPIA_PROFILO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COPIA_PROFILO").clone();
                    c.setValue(row.getCOPIA_PROFILO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("UTENTE_ORIGINE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE_ORIGINE").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("UNIFICA_PROFILO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UNIFICA_PROFILO").clone();
                    c.setValue(row.getUNIFICA_PROFILO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("UTENTE_UNIFICARE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE_UNIFICARE").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4UtentiV Grid: method bind

//AD4UtentiV Directory: getRowFieldByName @142-A632E927
        public Object getRowFieldByName( String name, AD4UtentiVRow row ) {
            Object value = null;
            if ( "NOMINATIVO".equals(name) ) {
                value = row.getNOMINATIVO();
            } else if ( "UTENTE".equals(name) ) {
                value = row.getUTENTE();
            } else if ( "DATI_UTENTE".equals(name) ) {
                value = row.getDATI_UTENTE();
            } else if ( "COPIA_PROFILO".equals(name) ) {
                value = row.getCOPIA_PROFILO();
            } else if ( "UNIFICA_PROFILO".equals(name) ) {
                value = row.getUNIFICA_PROFILO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "UTENTE_ORIGINE".equals(name) ) {
                value = row.getUTENTE_ORIGINE();
            } else if ( "UTENTE_UNIFICARE".equals(name) ) {
                value = row.getUTENTE_UNIFICARE();
            }
            return value;
        }
//End AD4UtentiV Directory: getRowFieldByName

//AD4UtentiV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4UtentiV Grid: method validate

//AD4UtentiV Grid Tail @142-FCB6E20C
    }
//End AD4UtentiV Grid Tail

//AD4UtentiRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4UtentiRicerca Page: method validate

//AD4UtentiRicercaAction Tail @1-FCB6E20C
}
//End AD4UtentiRicercaAction Tail

