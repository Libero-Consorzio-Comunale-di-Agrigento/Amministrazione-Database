//AD4GruppoModAction imports @1-D68165DA
package ad4web.AD4GruppoMod;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GruppoModAction imports

//AD4GruppoModAction class @1-7CEB655B
public class AD4GruppoModAction extends Action {

//End AD4GruppoModAction class

//AD4GruppoModAction: method perform @1-EF4D0942
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GruppoModModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GruppoModModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GruppoModAction: method perform

//AD4GruppoModAction: call children actions @1-4AA68FB9
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
            UTENTISearchClass UTENTISearch = new UTENTISearchClass();
            if ( ( redirect = UTENTISearch.perform( page.getRecord("UTENTISearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            UTENTIClass UTENTI = new UTENTIClass();
            UTENTI.perform(page.getGrid("UTENTI"));
        }
        if (result == null) {
            DISPONIBILIClass DISPONIBILI = new DISPONIBILIClass();
            if ( ( redirect = DISPONIBILI.perform( page.getRecord("DISPONIBILI")) ) != null ) result = redirect;
        }
        if (result == null) {
            ASSEGNATIClass ASSEGNATI = new ASSEGNATIClass();
            if ( ( redirect = ASSEGNATI.perform( page.getRecord("ASSEGNATI")) ) != null ) result = redirect;
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
//End AD4GruppoModAction: call children actions

//UTENTISearch Record @84-065CE010
    final class UTENTISearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End UTENTISearch Record

//UTENTISearch Record: method perform @84-B5A8A7A7
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4GruppoMod" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End UTENTISearch Record: method perform

//UTENTISearch Record: children actions @84-BAA5852C
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("UTENTISearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        Button_DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_TIPO_UTENTE(model.getRadioButton("s_TIPO_UTENTE"));
//End UTENTISearch Record: children actions

//UTENTISearch Record: method perform tail @84-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End UTENTISearch Record: method perform tail

//Button_DoSearch Button @97-FC79D73B
        void Button_DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4GruppoMod" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_DoSearch Button

//RadioButtonAction @96-920D4827
        protected void reads_TIPO_UTENTE(com.codecharge.components.RadioButton model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "U;Utenti;G;Gruppi;;Entrambi" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End RadioButtonAction

void read() { //UTENTISearch Record: method read @84-7F8AAE5A

//UTENTISearch Record: method read head @84-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End UTENTISearch Record: method read head

//UTENTISearch Record: init DataSource @84-2E82D06D
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            UTENTISearchDataObject ds = new UTENTISearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("s_UTENTE") );
            ds.setUrlS_TIPO_UTENTE( page.getHttpGetParams().getParameter("s_TIPO_UTENTE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End UTENTISearch Record: init DataSource

//UTENTISearch Record: check errors @84-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End UTENTISearch Record: check errors

} //UTENTISearch Record: method read tail @84-FCB6E20C

//UTENTISearch Record: bind @84-1D0CDC6C
            public void bind(com.codecharge.components.Component model, UTENTISearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                }
            }
//End UTENTISearch Record: bind

//UTENTISearch Record: getRowFieldByName @84-A62364E7
            public Object getRowFieldByName( String name, UTENTISearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_UTENTE".equals(name) ) {
                    value = row.getS_UTENTE();
                } else if ( "s_TIPO_UTENTE".equals(name) ) {
                    value = row.getS_TIPO_UTENTE();
                }
                return value;
            }
//End UTENTISearch Record: getRowFieldByName

void InsertAction() { //UTENTISearch Record: method insert @84-11643485

//UTENTISearch Record: components insert actions @84-68525650
            if (! model.hasErrors()) {
            }
//End UTENTISearch Record: components insert actions

} //UTENTISearch Record: method insert tail @84-FCB6E20C

void UpdateAction() { //UTENTISearch Record: method update @84-5771D0AA

//UTENTISearch Record: components update actions @84-68525650
            if (! model.hasErrors()) {
            }
//End UTENTISearch Record: components update actions

} //UTENTISearch Record: method update tail @84-FCB6E20C

void DeleteAction() { //UTENTISearch Record: method delete @84-11FC2E1E

//UTENTISearch Record: components delete actions @84-68525650
            if (! model.hasErrors()) {
            }
//End UTENTISearch Record: components delete actions

} //UTENTISearch Record: method delete tail @84-FCB6E20C

//UTENTISearch Record: method validate @84-E2486415
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_UTENTE = (com.codecharge.components.TextBox) model.getChild( "s_UTENTE" );
            if (! s_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.RadioButton s_TIPO_UTENTE = (com.codecharge.components.RadioButton) model.getChild( "s_TIPO_UTENTE" );
            if (! s_TIPO_UTENTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End UTENTISearch Record: method validate

//UTENTISearch Record Tail @84-FCB6E20C
    }
//End UTENTISearch Record Tail

//UTENTI Grid @80-9F5BF47E
    final class UTENTIClass {
        com.codecharge.components.Grid model;
        Event e;
//End UTENTI Grid

//UTENTI Grid: method perform @80-B48879D3
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
//End UTENTI Grid: method perform

//UTENTI Grid: method read: head @80-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End UTENTI Grid: method read: head

//UTENTI Grid: method read: init @80-D1CD13B1
            if ( ! model.allowRead ) return true;
            UTENTIDataObject ds = new UTENTIDataObject(page);
            ds.setComponent( model );
//End UTENTI Grid: method read: init

//UTENTI Grid: set where parameters @80-DF002727
            ds.setUrlGRUPPO( page.getHttpGetParams().getParameter("GRUPPO") );
//End UTENTI Grid: set where parameters

//UTENTI Grid: set grid properties @80-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End UTENTI Grid: set grid properties

//UTENTI Grid: retrieve data @80-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End UTENTI Grid: retrieve data

//UTENTI Grid: check errors @80-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End UTENTI Grid: check errors

//UTENTI Grid: method read: tail @80-F575E732
            return ( ! isErrors );
        }
//End UTENTI Grid: method read: tail

//UTENTI Grid: method bind @80-8B163717
        public void bind(com.codecharge.components.Component model, UTENTIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            UTENTIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("GRUPPO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("GRUPPO").clone();
                    c.setValue(row.getGRUPPO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Indietro");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Indietro").clone();
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End UTENTI Grid: method bind

//UTENTI Directory: getRowFieldByName @80-110D1E53
        public Object getRowFieldByName( String name, UTENTIRow row ) {
            Object value = null;
            if ( "NOMINATIVO".equals(name) ) {
                value = row.getNOMINATIVO();
            } else if ( "GRUPPO".equals(name) ) {
                value = row.getGRUPPO();
            } else if ( "Indietro".equals(name) ) {
                value = row.getIndietro();
            }
            return value;
        }
//End UTENTI Directory: getRowFieldByName

//UTENTI Grid: method validate @80-104025BA
        boolean validate() {
            return true;
        }
//End UTENTI Grid: method validate

//UTENTI Grid Tail @80-FCB6E20C
    }
//End UTENTI Grid Tail

//DISPONIBILI Record @52-ABF6303A
    final class DISPONIBILIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End DISPONIBILI Record

//DISPONIBILI Record: method perform @52-30DEE94A
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Uno" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Tutti" ).setVisible( false );
//End DISPONIBILI Record: method perform

//DISPONIBILI Record: children actions @52-EF0ED025
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("DISPONIBILI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Tutti") != null) {
                        if (validate()) {
                            TuttiAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Uno") != null) {
                        if (validate()) {
                            UnoAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readUTENTE_D(model.getListBox("UTENTE_D"));
//End DISPONIBILI Record: children actions

//DISPONIBILI Record: method perform tail @52-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End DISPONIBILI Record: method perform tail

//Tutti Button @55-A7DCB9A6
        void TuttiAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Tutti");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Tutti Button

//Uno Button @56-C01A99F0
        void UnoAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Uno");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Uno Button

//ListBoxAction @53-429B741D
        protected void readUTENTE_D(com.codecharge.components.ListBox model) {

            TextField urlGRUPPO = new TextField(null, null);
            
            urlGRUPPO.setValue( page.getHttpGetParams().getParameter("GRUPPO") );
            TextField urlS_UTENTE = new TextField(null, null);
            
            urlS_UTENTE.setValue( page.getHttpGetParams().getParameter("s_UTENTE") );
            TextField urlS_TIPO_UTENTE = new TextField(null, null);
            
            urlS_TIPO_UTENTE.setValue( page.getHttpGetParams().getParameter("s_TIPO_UTENTE") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select UTENTI.nominativo||decode(UTENTI.tipo_utente,'G',' ('||UTENTI.tipo_utente||')','') nome_utente "
                        + "     , UTENTI.utente "
                        + "  from UTENTI,  "
                        + "utenti gruppi "
                        + " where UTENTI.utente <> '{GRUPPO}' "
                        + "   and gruppi.utente = '{GRUPPO}' "
                        + "   and gruppi.tipo_utente = 'G' "
                        + "   and UTENTI.utente <> 'ric_abil' "
                        + "   and UTENTI.utente not in (select UTENTI_GRUPPO.utente "
                        + "                               from UTENTI_GRUPPO "
                        + "                              where UTENTI_GRUPPO.gruppo = '{GRUPPO}' "
                        + "                             ) "
                        + "   and UTENTI.tipo_utente like nvl('{TIPO_UTENTE}','%') "
                        + "   and UTENTI.TIPO_UTENTE IN ('U','G') "
                        + "   and (upper(UTENTI.nominativo) like upper('%{UTENTE}%') or upper(UTENTI.utente) like upper('%{UTENTE}%')) "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlGRUPPO.getObjectValue() ) ) urlGRUPPO.setValue( "" );
            command.addParameter( "GRUPPO", urlGRUPPO, null );
            if ( StringUtils.isEmpty( (String) urlS_UTENTE.getObjectValue() ) ) urlS_UTENTE.setValue( "" );
            command.addParameter( "UTENTE", urlS_UTENTE, null );
            if ( StringUtils.isEmpty( (String) urlS_TIPO_UTENTE.getObjectValue() ) ) urlS_TIPO_UTENTE.setValue( "" );
            command.addParameter( "TIPO_UTENTE", urlS_TIPO_UTENTE, null );
            command.setOrder( "1" );

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

void read() { //DISPONIBILI Record: method read @52-7F8AAE5A

//DISPONIBILI Record: method read head @52-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End DISPONIBILI Record: method read head

//DISPONIBILI Record: init DataSource @52-647D1AC6
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            DISPONIBILIDataObject ds = new DISPONIBILIDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End DISPONIBILI Record: init DataSource

//DISPONIBILI Record: check errors @52-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End DISPONIBILI Record: check errors

} //DISPONIBILI Record: method read tail @52-FCB6E20C

//DISPONIBILI Record: bind @52-895CAFBE
            public void bind(com.codecharge.components.Component model, DISPONIBILIRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End DISPONIBILI Record: bind

//DISPONIBILI Record: getRowFieldByName @52-F6E8CE38
            public Object getRowFieldByName( String name, DISPONIBILIRow row ) {
                Object value = null;
                if ( "UTENTE_D".equals(name) ) {
                    value = row.getUTENTE_D();
                }
                return value;
            }
//End DISPONIBILI Record: getRowFieldByName

void InsertAction() { //DISPONIBILI Record: method insert @52-11643485

//DISPONIBILI Record: method insert head @52-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End DISPONIBILI Record: method insert head

//DISPONIBILI Record: components insert actions @52-68525650
            if (! model.hasErrors()) {
            }
//End DISPONIBILI Record: components insert actions

} //DISPONIBILI Record: method insert tail @52-FCB6E20C

void UpdateAction() { //DISPONIBILI Record: method update @52-5771D0AA

//DISPONIBILI Record: method update head @52-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End DISPONIBILI Record: method update head

//DISPONIBILI Record: method update body @52-1E20D2F4
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            DISPONIBILIDataObject ds = new DISPONIBILIDataObject(page);
            ds.setComponent( model );
            DISPONIBILIRow row = new DISPONIBILIRow();
            ds.setRow(row);
            ds.setUrlGRUPPO( page.getHttpGetParams().getParameter("GRUPPO") );
            ds.setPostUTENTE_D( page.getHttpPostParams().getParameter("UTENTE_D") );
//End DISPONIBILI Record: method update body

//DISPONIBILI Record: ds.update @52-6E956EDC
            ds.update();
            if ( ! ds.hasErrors() ) {
            }
            model.fireAfterUpdateEvent();
            action = true;
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
                this.valid = false;
            }
//End DISPONIBILI Record: ds.update

} //DISPONIBILI Record: method update tail @52-FCB6E20C

void DeleteAction() { //DISPONIBILI Record: method delete @52-11FC2E1E

//DISPONIBILI Record: method delete head @52-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End DISPONIBILI Record: method delete head

//DISPONIBILI Record: method delete body @52-053E4D5F
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            DISPONIBILIDataObject ds = new DISPONIBILIDataObject(page);
            ds.setComponent( model );
            DISPONIBILIRow row = new DISPONIBILIRow();
            ds.setRow(row);
            ds.setPostUTENTE_D( page.getHttpPostParams().getParameter("UTENTE_D") );
            ds.setUrlGRUPPO( page.getHttpGetParams().getParameter("GRUPPO") );
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End DISPONIBILI Record: method delete body

//DISPONIBILI Record: ds.delete @52-3584344F
            ds.delete();
            if ( ! ds.hasErrors() ) {
            }
            model.fireAfterDeleteEvent();
            action = true;
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
                this.valid = false;
            }
//End DISPONIBILI Record: ds.delete

} //DISPONIBILI Record: method delete tail @52-FCB6E20C

//DISPONIBILI Record: method validate @52-068630F0
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox UTENTE_D = (com.codecharge.components.ListBox) model.getChild( "UTENTE_D" );
            if (! UTENTE_D.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End DISPONIBILI Record: method validate

//DISPONIBILI Record Tail @52-FCB6E20C
    }
//End DISPONIBILI Record Tail

//ASSEGNATI Record @63-820EEF07
    final class ASSEGNATIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ASSEGNATI Record

//ASSEGNATI Record: method perform @63-30DEE94A
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Uno" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Tutti" ).setVisible( false );
//End ASSEGNATI Record: method perform

//ASSEGNATI Record: children actions @63-DAF3A661
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ASSEGNATI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Uno") != null) {
                        if (validate()) {
                            UnoAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Tutti") != null) {
                        if (validate()) {
                            TuttiAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readUTENTE_A(model.getListBox("UTENTE_A"));
//End ASSEGNATI Record: children actions

//ASSEGNATI Record: method perform tail @63-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ASSEGNATI Record: method perform tail

//Uno Button @66-C01A99F0
        void UnoAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Uno");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Uno Button

//Tutti Button @68-A7DCB9A6
        void TuttiAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Tutti");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Tutti Button

//ListBoxAction @64-70B8E182
        protected void readUTENTE_A(com.codecharge.components.ListBox model) {

            TextField urlGRUPPO = new TextField(null, null);
            
            urlGRUPPO.setValue( page.getHttpGetParams().getParameter("GRUPPO") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select UTEN.nominativo||decode(UTEN.tipo_utente,'U','',' ('||UTEN.tipo_utente||')') nome_utente "
                        + "     , utgr.utente "
                        + "  from UTENTI uten, UTENTI_GRUPPO utgr,  "
                        + "UTENTI GRUPPI "
                        + " where utgr.utente = uten.utente "
                        + "   and utgr.gruppo = '{GRUPPO}' "
                        + "   AND GRUPPI.UTENTE = utgr.gruppo "
                        + "   AND GRUPPI.TIPO_UTENTE = 'G' "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlGRUPPO.getObjectValue() ) ) urlGRUPPO.setValue( "" );
            command.addParameter( "GRUPPO", urlGRUPPO, null );
            command.setOrder( "1" );

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

void read() { //ASSEGNATI Record: method read @63-7F8AAE5A

//ASSEGNATI Record: method read head @63-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ASSEGNATI Record: method read head

//ASSEGNATI Record: init DataSource @63-7DAF4F08
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ASSEGNATIDataObject ds = new ASSEGNATIDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ASSEGNATI Record: init DataSource

//ASSEGNATI Record: check errors @63-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ASSEGNATI Record: check errors

} //ASSEGNATI Record: method read tail @63-FCB6E20C

//ASSEGNATI Record: bind @63-B4FABB83
            public void bind(com.codecharge.components.Component model, ASSEGNATIRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End ASSEGNATI Record: bind

//ASSEGNATI Record: getRowFieldByName @63-84342451
            public Object getRowFieldByName( String name, ASSEGNATIRow row ) {
                Object value = null;
                if ( "UTENTE_A".equals(name) ) {
                    value = row.getUTENTE_A();
                }
                return value;
            }
//End ASSEGNATI Record: getRowFieldByName

void InsertAction() { //ASSEGNATI Record: method insert @63-11643485

//ASSEGNATI Record: method insert head @63-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ASSEGNATI Record: method insert head

//ASSEGNATI Record: components insert actions @63-68525650
            if (! model.hasErrors()) {
            }
//End ASSEGNATI Record: components insert actions

} //ASSEGNATI Record: method insert tail @63-FCB6E20C

void UpdateAction() { //ASSEGNATI Record: method update @63-5771D0AA

//ASSEGNATI Record: method update head @63-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ASSEGNATI Record: method update head

//ASSEGNATI Record: method update body @63-A1C446E2
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ASSEGNATIDataObject ds = new ASSEGNATIDataObject(page);
            ds.setComponent( model );
            ASSEGNATIRow row = new ASSEGNATIRow();
            ds.setRow(row);
            ds.setUrlGRUPPO( page.getHttpGetParams().getParameter("GRUPPO") );
            ds.setPostUTENTE_A( page.getHttpPostParams().getParameter("UTENTE_A") );
//End ASSEGNATI Record: method update body

//ASSEGNATI Record: ds.update @63-6E956EDC
            ds.update();
            if ( ! ds.hasErrors() ) {
            }
            model.fireAfterUpdateEvent();
            action = true;
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
                this.valid = false;
            }
//End ASSEGNATI Record: ds.update

} //ASSEGNATI Record: method update tail @63-FCB6E20C

void DeleteAction() { //ASSEGNATI Record: method delete @63-11FC2E1E

//ASSEGNATI Record: method delete head @63-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ASSEGNATI Record: method delete head

//ASSEGNATI Record: method delete body @63-9595A07D
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ASSEGNATIDataObject ds = new ASSEGNATIDataObject(page);
            ds.setComponent( model );
            ASSEGNATIRow row = new ASSEGNATIRow();
            ds.setRow(row);
            ds.setExprKey79( ( "%" ) );
            ds.setUrlGRUPPO( page.getHttpGetParams().getParameter("GRUPPO") );
//End ASSEGNATI Record: method delete body

//ASSEGNATI Record: ds.delete @63-3584344F
            ds.delete();
            if ( ! ds.hasErrors() ) {
            }
            model.fireAfterDeleteEvent();
            action = true;
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
                this.valid = false;
            }
//End ASSEGNATI Record: ds.delete

} //ASSEGNATI Record: method delete tail @63-FCB6E20C

//ASSEGNATI Record: method validate @63-3EE31FEA
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox UTENTE_A = (com.codecharge.components.ListBox) model.getChild( "UTENTE_A" );
            if (! UTENTE_A.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ASSEGNATI Record: method validate

//ASSEGNATI Record Tail @63-FCB6E20C
    }
//End ASSEGNATI Record Tail

//AD4GruppoMod Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GruppoMod Page: method validate

//AD4GruppoModAction Tail @1-FCB6E20C
}
//End AD4GruppoModAction Tail

