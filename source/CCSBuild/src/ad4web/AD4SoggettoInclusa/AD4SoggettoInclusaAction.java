//AD4SoggettoInclusaAction imports @1-BA5C1324
package ad4web.AD4SoggettoInclusa;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4SoggettoInclusaAction imports

//AD4SoggettoInclusaAction class @1-7D139BE1
public class AD4SoggettoInclusaAction extends Action {

//End AD4SoggettoInclusaAction class

//AD4SoggettoInclusaAction: method perform @1-5DA51B8E
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4SoggettoInclusaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4SoggettoInclusaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4SoggettoInclusaAction: method perform

//AD4SoggettoInclusaAction: call children actions @1-0E8A5FF6
        if (result == null) {
            AD4_SOGGETTOClass AD4_SOGGETTO = new AD4_SOGGETTOClass();
            if ( ( redirect = AD4_SOGGETTO.perform( page.getRecord("AD4_SOGGETTO")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4SoggettoInclusaAction: call children actions

//AD4_SOGGETTO Record @6-54097E83
    final class AD4_SOGGETTOClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_SOGGETTO Record

//AD4_SOGGETTO Record: method perform @6-4E359F0A
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowInsert() ) model.getChild( "Button_Insert" ).setVisible( false );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End AD4_SOGGETTO Record: method perform

//AD4_SOGGETTO Record: children actions @6-2218F874
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_SOGGETTO".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Refresh") != null) {
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Update") != null) {
                        if (validate()) {
                            Button_UpdateAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                    if (page.getParameter("Refresh") != null) {
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Insert") != null) {
                        if (validate()) {
                            Button_InsertAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readSESSO(model.getListBox("SESSO"));
            readSTATO(model.getListBox("STATO"));
            readPROVINCIA_NAS(model.getListBox("PROVINCIA_NAS"));
            readCOMUNE_NASCITA(model.getListBox("COMUNE_NASCITA"));
            readPROVINCIA(model.getListBox("PROVINCIA"));
            readCOMUNE(model.getListBox("COMUNE"));
//End AD4_SOGGETTO Record: children actions

//AD4_SOGGETTO Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_SOGGETTO Record: method perform tail

//Refresh Button @155-BF199923
        void RefreshSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Refresh");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End Refresh Button

//Button_Insert Button @181-A60A3466
        void Button_InsertAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Insert");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            InsertAction();
        }
//End Button_Insert Button

//Button_Update Button @22-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Cancel Button @23-BEBD3B4D
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @161-156C862E
        protected void readSESSO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "F;F;M;M" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @116-EE18436C
        protected void readSTATO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT *  "
                        + "  FROM STATI_TERRITORI "
                        + " " );
            command.setOrder( "DENOMINAZIONE" );

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

//ListBoxAction @123-2991E974
        protected void readPROVINCIA_NAS(com.codecharge.components.ListBox model) {

            LongField urlSTATO = new LongField(null, null);
            
            try {
                urlSTATO.setValue( page.getHttpGetParams().getParameter("STATO"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter" );
                return;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter" );
                return;
            }
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select to_number(null) provincia,  "
                        + "'- -' denominazione "
                        + "  from dual "
                        + " where NVL({STATO_LISTBOX},100) = 100  "
                        + "union "
                        + "select provincia,  "
                        + "denominazione "
                        + "  from provincie  "
                        + " where NVL({STATO_LISTBOX},100) = 100  "
                        + "union "
                        + "select provincia_stato,  "
                        + "'EE' denominazione  "
                        + "  from comuni  "
                        + " where provincia_stato = NVL({STATO_LISTBOX},100) "
                        + "   and comune = 0 "
                        + "" );
            if ( urlSTATO.getObjectValue() == null ) urlSTATO.setValue( 100 );
            command.addParameter( "STATO_LISTBOX", urlSTATO, null );
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

//ListBoxAction @129-E886E02A
        protected void readCOMUNE_NASCITA(com.codecharge.components.ListBox model) {

            TextField urlPROVINCIA_NAS = new TextField(null, null);
            
            urlPROVINCIA_NAS.setValue( page.getHttpGetParams().getParameter("PROVINCIA_NAS") );
            LongField urlSOGGETTO = new LongField(null, null);
            
            try {
                urlSOGGETTO.setValue( page.getHttpGetParams().getParameter("SOGGETTO"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter" );
                return;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter" );
                return;
            }
            TextField urlSTATO = new TextField(null, null);
            
            urlSTATO.setValue( page.getHttpGetParams().getParameter("STATO") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT COMUNE,  "
                        + "DENOMINAZIONE "
                        + "  FROM COMUNI "
                        + "  where PROVINCIA_STATO = to_number('{PROV}') "
                        + "   AND NVL(NVL(to_number('{PROV}'),to_number('{STATO}')),0) > 0 "
                        + "   AND NVL(NVL(to_number('{PROV}'),to_number('{STATO}')),0) < 200 "
                        + "   AND COMUNE > 0 "
                        + "UNION "
                        + "SELECT COMUNE, DENOMINAZIONE "
                        + "  FROM COMUNI "
                        + "  where PROVINCIA_STATO = to_number('{PROV}') "
                        + "   AND NVL(NVL(to_number('{PROV}'),to_number('{STATO}')),0) > 0 "
                        + "   AND NVL(NVL(to_number('{PROV}'),to_number('{STATO}')),0) >= 200 "
                        + "UNION "
                        + "SELECT COMU.COMUNE, COMU.DENOMINAZIONE "
                        + "  FROM COMUNI COMU, SOGGETTI SOGG "
                        + " where SOGG.SOGGETTO = {SOGGETTO} "
                        + "   AND COMU.PROVINCIA_STATO = SOGG.PROVINCIA_NAS "
                        + "   AND NVL(NVL(to_number('{PROV}'),to_number('{STATO}')),0) = 0 "
                        + "   AND COMU.COMUNE > 0 "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlPROVINCIA_NAS.getObjectValue() ) ) urlPROVINCIA_NAS.setValue( "" );
            command.addParameter( "PROV", urlPROVINCIA_NAS, null );
            if ( urlSOGGETTO.getObjectValue() == null ) urlSOGGETTO.setValue( 0 );
            command.addParameter( "SOGGETTO", urlSOGGETTO, null );
            if ( StringUtils.isEmpty( (String) urlSTATO.getObjectValue() ) ) urlSTATO.setValue( "" );
            command.addParameter( "STATO", urlSTATO, null );
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

//ListBoxAction @134-AD106F0B
        protected void readPROVINCIA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT *  "
                        + "FROM AD4_PROVINCIE" );
            command.setOrder( "DENOMINAZIONE" );

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

//ListBoxAction @147-8749A4FB
        protected void readCOMUNE(com.codecharge.components.ListBox model) {

            LongField urlPROVINCIA = new LongField(null, null);
            
            try {
                urlPROVINCIA.setValue( page.getHttpGetParams().getParameter("PROVINCIA"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter" );
                return;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter" );
                return;
            }
            LongField urlSOGGETTO = new LongField(null, null);
            
            try {
                urlSOGGETTO.setValue( page.getHttpGetParams().getParameter("SOGGETTO"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter" );
                return;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter" );
                return;
            }
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT COMUNE,  "
                        + "DENOMINAZIONE "
                        + "  FROM COMUNI "
                        + "  where PROVINCIA_STATO = {PROV} "
                        + "    AND NVL({PROV},0) > 0 "
                        + "    AND COMUNE > 0 "
                        + "UNION "
                        + "SELECT COMU.COMUNE,  "
                        + "COMU.DENOMINAZIONE "
                        + "  FROM COMUNI COMU,  "
                        + "SOGGETTI SOGG "
                        + " where SOGG.SOGGETTO = {SOGGETTO} "
                        + "   AND COMU.PROVINCIA_STATO = SOGG.PROVINCIA "
                        + "   AND NVL({PROV},0) = 0 "
                        + "   AND COMU.COMUNE > 0 "
                        + " " );
            if ( urlPROVINCIA.getObjectValue() == null ) urlPROVINCIA.setValue( 0 );
            command.addParameter( "PROV", urlPROVINCIA, null );
            if ( urlSOGGETTO.getObjectValue() == null ) urlSOGGETTO.setValue( 0 );
            command.addParameter( "SOGGETTO", urlSOGGETTO, null );
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

void read() { //AD4_SOGGETTO Record: method read @6-7F8AAE5A

//AD4_SOGGETTO Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_SOGGETTO Record: method read head

//AD4_SOGGETTO Record: init DataSource @6-31138080
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_SOGGETTODataObject ds = new AD4_SOGGETTODataObject(page);
            ds.setComponent( model );
            ds.setUrlSOGGETTO( page.getHttpGetParams().getParameter("SOGGETTO") );
            ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA") );
            ds.setUrlSTATO( page.getHttpGetParams().getParameter("STATO") );
            ds.setUrlPROVINCIA_NAS( page.getHttpGetParams().getParameter("PROVINCIA_NAS") );
            ds.setUrlNOME( page.getHttpGetParams().getParameter("NOME") );
            ds.setUrlSESSO( page.getHttpGetParams().getParameter("SESSO") );
            ds.setUrlDATA_NASCITA( page.getHttpGetParams().getParameter("DATA_NASCITA") );
            ds.setUrlCOMUNE_NASCITA( page.getHttpGetParams().getParameter("COMUNE_NASCITA") );
            ds.setUrlCODICE_FISCALE( page.getHttpGetParams().getParameter("CODICE_FISCALE") );
            ds.setUrlINDIRIZZO_COMPLETO( page.getHttpGetParams().getParameter("INDIRIZZO_COMPLETO") );
            ds.setUrlCAP( page.getHttpGetParams().getParameter("CAP") );
            ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE") );
            ds.setUrlTELEFONO( page.getHttpGetParams().getParameter("TELEFONO") );
            ds.setUrlFAX( page.getHttpGetParams().getParameter("FAX") );
            ds.setUrlINDIRIZZO_WEB( page.getHttpGetParams().getParameter("INDIRIZZO_WEB") );
            ds.setUrlNOTE( page.getHttpGetParams().getParameter("NOTE") );
            ds.setUrlISLISTBOX( page.getHttpGetParams().getParameter("ISLISTBOX") );
            ds.setUrlCOGNOME( page.getHttpGetParams().getParameter("COGNOME") );
            ds.setSesAD4SOGG( SessionStorage.getInstance(req).getAttribute("AD4SOGG") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_SOGGETTO Record: init DataSource

//AD4_SOGGETTO Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_SOGGETTO Record: check errors

} //AD4_SOGGETTO Record: method read tail @6-FCB6E20C

//AD4_SOGGETTO Record: bind @6-572C54D8
            public void bind(com.codecharge.components.Component model, AD4_SOGGETTORow row ) {
                if ( model == null || row == null ) return;
                model.getControl("ID_SOGGETTO").setValue(row.getID_SOGGETTO());
                if ( this.valid ) {
                    model.getControl("COGNOME").setValue(row.getCOGNOME());
                    model.getControl("NOME").setValue(row.getNOME());
                    model.getControl("SOGGETTO").setValue(row.getSOGGETTO());
                    model.getControl("SESSO").setValue(row.getSESSO());
                    model.getControl("STATO").setValue(row.getSTATO());
                    model.getControl("PROVINCIA_NAS").setValue(row.getPROVINCIA_NAS());
                    model.getControl("COMUNE_NASCITA").setValue(row.getCOMUNE_NASCITA());
                    model.getControl("DATA_NASCITA").setValue(row.getDATA_NASCITA());
                    model.getControl("CODICE_FISCALE").setValue(row.getCODICE_FISCALE());
                    model.getControl("INDIRIZZO_COMPLETO").setValue(row.getINDIRIZZO_COMPLETO());
                    model.getControl("PROVINCIA").setValue(row.getPROVINCIA());
                    model.getControl("COMUNE").setValue(row.getCOMUNE());
                    model.getControl("CAP").setValue(row.getCAP());
                    model.getControl("INDIRIZZO_WEB").setValue(row.getINDIRIZZO_WEB());
                    model.getControl("TELEFONO").setValue(row.getTELEFONO());
                    model.getControl("FAX").setValue(row.getFAX());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End AD4_SOGGETTO Record: bind

//AD4_SOGGETTO Record: getRowFieldByName @6-20542224
            public Object getRowFieldByName( String name, AD4_SOGGETTORow row ) {
                Object value = null;
                if ( "ID_SOGGETTO".equals(name) ) {
                    value = row.getID_SOGGETTO();
                } else if ( "COGNOME".equals(name) ) {
                    value = row.getCOGNOME();
                } else if ( "NOME".equals(name) ) {
                    value = row.getNOME();
                } else if ( "SOGGETTO".equals(name) ) {
                    value = row.getSOGGETTO();
                } else if ( "SESSO".equals(name) ) {
                    value = row.getSESSO();
                } else if ( "STATO".equals(name) ) {
                    value = row.getSTATO();
                } else if ( "ISLISTBOX".equals(name) ) {
                    value = row.getISLISTBOX();
                } else if ( "PROVINCIA_NAS".equals(name) ) {
                    value = row.getPROVINCIA_NAS();
                } else if ( "COMUNE_NASCITA".equals(name) ) {
                    value = row.getCOMUNE_NASCITA();
                } else if ( "DATA_NASCITA".equals(name) ) {
                    value = row.getDATA_NASCITA();
                } else if ( "CODICE_FISCALE".equals(name) ) {
                    value = row.getCODICE_FISCALE();
                } else if ( "INDIRIZZO_COMPLETO".equals(name) ) {
                    value = row.getINDIRIZZO_COMPLETO();
                } else if ( "PROVINCIA".equals(name) ) {
                    value = row.getPROVINCIA();
                } else if ( "COMUNE".equals(name) ) {
                    value = row.getCOMUNE();
                } else if ( "CAP".equals(name) ) {
                    value = row.getCAP();
                } else if ( "INDIRIZZO_WEB".equals(name) ) {
                    value = row.getINDIRIZZO_WEB();
                } else if ( "TELEFONO".equals(name) ) {
                    value = row.getTELEFONO();
                } else if ( "FAX".equals(name) ) {
                    value = row.getFAX();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                }
                return value;
            }
//End AD4_SOGGETTO Record: getRowFieldByName

void InsertAction() { //AD4_SOGGETTO Record: method insert @6-11643485

//AD4_SOGGETTO Record: method insert head @6-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_SOGGETTO Record: method insert head

//AD4_SOGGETTO Record: method insert body @6-48871527
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            AD4_SOGGETTODataObject ds = new AD4_SOGGETTODataObject(page);
            ds.setComponent( model );
            AD4_SOGGETTORow row = new AD4_SOGGETTORow();
            ds.setRow(row);
            ds.setUrlSOGGETTO( page.getHttpGetParams().getParameter("SOGGETTO") );
            ds.setPostCOGNOME( page.getHttpPostParams().getParameter("COGNOME") );
            ds.setPostNOME( page.getHttpPostParams().getParameter("NOME") );
            ds.setPostSESSO( page.getHttpPostParams().getParameter("SESSO") );
            ds.setPostP_DATA_NAS( page.getHttpPostParams().getParameter("P_DATA_NAS") );
            try {
                ds.setPostCOMUNE_NASCITA( page.getHttpPostParams().getParameter("COMUNE_NASCITA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE_NASCITA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE_NASCITA'" );
            }
            try {
                ds.setPostPROVINCIA_NAS( page.getHttpPostParams().getParameter("PROVINCIA_NAS"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_NAS'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_NAS'" );
            }
            ds.setPostCODICE_FISCALE( page.getHttpPostParams().getParameter("CODICE_FISCALE") );
            try {
                ds.setPostCOMUNE( page.getHttpPostParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            try {
                ds.setPostPROVINCIA( page.getHttpPostParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
            ds.setPostCAP( page.getHttpPostParams().getParameter("CAP") );
            ds.setPostINDIRIZZO_COMPLETO( page.getHttpPostParams().getParameter("INDIRIZZO_COMPLETO") );
            ds.setPostINDIRIZZO_WEB( page.getHttpPostParams().getParameter("INDIRIZZO_WEB") );
            ds.setPostTELEFONO( page.getHttpPostParams().getParameter("TELEFONO") );
            ds.setPostFAX( page.getHttpPostParams().getParameter("FAX") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End AD4_SOGGETTO Record: method insert body

//AD4_SOGGETTO Record: ds.insert @6-9320B03B
            ds.insert();
            if ( ! ds.hasErrors() ) {
            }
            model.fireAfterInsertEvent();
            action = true;
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
                this.valid = false;
            }
//End AD4_SOGGETTO Record: ds.insert

} //AD4_SOGGETTO Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4_SOGGETTO Record: method update @6-5771D0AA

//AD4_SOGGETTO Record: method update head @6-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_SOGGETTO Record: method update head

//AD4_SOGGETTO Record: method update body @6-69C8DCBB
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_SOGGETTODataObject ds = new AD4_SOGGETTODataObject(page);
            ds.setComponent( model );
            AD4_SOGGETTORow row = new AD4_SOGGETTORow();
            ds.setRow(row);
            ds.setUrlSOGGETTO( page.getHttpGetParams().getParameter("SOGGETTO") );
            ds.setPostCOGNOME( page.getHttpPostParams().getParameter("COGNOME") );
            ds.setPostNOME( page.getHttpPostParams().getParameter("NOME") );
            ds.setPostSESSO( page.getHttpPostParams().getParameter("SESSO") );
            ds.setPostDATA_NASCITA( page.getHttpPostParams().getParameter("DATA_NASCITA") );
            try {
                ds.setPostCOMUNE_NASCITA( page.getHttpPostParams().getParameter("COMUNE_NASCITA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE_NASCITA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE_NASCITA'" );
            }
            try {
                ds.setPostPROVINCIA_NAS( page.getHttpPostParams().getParameter("PROVINCIA_NAS"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_NAS'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_NAS'" );
            }
            ds.setPostCODICE_FISCALE( page.getHttpPostParams().getParameter("CODICE_FISCALE") );
            try {
                ds.setPostCOMUNE( page.getHttpPostParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            try {
                ds.setPostPROVINCIA( page.getHttpPostParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
            ds.setPostCAP( page.getHttpPostParams().getParameter("CAP") );
            ds.setPostINDIRIZZO_COMPLETO( page.getHttpPostParams().getParameter("INDIRIZZO_COMPLETO") );
            ds.setPostINDIRIZZO_WEB( page.getHttpPostParams().getParameter("INDIRIZZO_WEB") );
            ds.setPostTELEFONO( page.getHttpPostParams().getParameter("TELEFONO") );
            ds.setPostFAX( page.getHttpPostParams().getParameter("FAX") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End AD4_SOGGETTO Record: method update body

//AD4_SOGGETTO Record: ds.update @6-6E956EDC
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
//End AD4_SOGGETTO Record: ds.update

} //AD4_SOGGETTO Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4_SOGGETTO Record: method delete @6-11FC2E1E

//AD4_SOGGETTO Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4_SOGGETTO Record: components delete actions

} //AD4_SOGGETTO Record: method delete tail @6-FCB6E20C

//AD4_SOGGETTO Record: method validate @6-553A1A9C
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox COGNOME = (com.codecharge.components.TextBox) model.getChild( "COGNOME" );
            if (! COGNOME.validate()) { isControlError = true; }

            com.codecharge.components.TextBox NOME = (com.codecharge.components.TextBox) model.getChild( "NOME" );
            if (! NOME.validate()) { isControlError = true; }

            com.codecharge.components.Hidden SOGGETTO = (com.codecharge.components.Hidden) model.getChild( "SOGGETTO" );
            if (! SOGGETTO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox SESSO = (com.codecharge.components.ListBox) model.getChild( "SESSO" );
            if (! SESSO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox STATO = (com.codecharge.components.ListBox) model.getChild( "STATO" );
            if (! STATO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ISLISTBOX = (com.codecharge.components.Hidden) model.getChild( "ISLISTBOX" );
            if (! ISLISTBOX.validate()) { isControlError = true; }

            com.codecharge.components.ListBox PROVINCIA_NAS = (com.codecharge.components.ListBox) model.getChild( "PROVINCIA_NAS" );
            if (! PROVINCIA_NAS.validate()) { isControlError = true; }

            com.codecharge.components.ListBox COMUNE_NASCITA = (com.codecharge.components.ListBox) model.getChild( "COMUNE_NASCITA" );
            if (! COMUNE_NASCITA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DATA_NASCITA = (com.codecharge.components.TextBox) model.getChild( "DATA_NASCITA" );
            if (! DATA_NASCITA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CODICE_FISCALE = (com.codecharge.components.TextBox) model.getChild( "CODICE_FISCALE" );
            if (! CODICE_FISCALE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox INDIRIZZO_COMPLETO = (com.codecharge.components.TextBox) model.getChild( "INDIRIZZO_COMPLETO" );
            if (! INDIRIZZO_COMPLETO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox PROVINCIA = (com.codecharge.components.ListBox) model.getChild( "PROVINCIA" );
            if (! PROVINCIA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox COMUNE = (com.codecharge.components.ListBox) model.getChild( "COMUNE" );
            if (! COMUNE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CAP = (com.codecharge.components.TextBox) model.getChild( "CAP" );
            if (! CAP.validate()) { isControlError = true; }

            com.codecharge.components.TextBox INDIRIZZO_WEB = (com.codecharge.components.TextBox) model.getChild( "INDIRIZZO_WEB" );
            if (! INDIRIZZO_WEB.validate()) { isControlError = true; }

            com.codecharge.components.TextBox TELEFONO = (com.codecharge.components.TextBox) model.getChild( "TELEFONO" );
            if (! TELEFONO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox FAX = (com.codecharge.components.TextBox) model.getChild( "FAX" );
            if (! FAX.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_SOGGETTO Record: method validate

//AD4_SOGGETTO Record Tail @6-FCB6E20C
    }
//End AD4_SOGGETTO Record Tail

//AD4SoggettoInclusa Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4SoggettoInclusa Page: method validate

//AD4SoggettoInclusaAction Tail @1-FCB6E20C
}
//End AD4SoggettoInclusaAction Tail
