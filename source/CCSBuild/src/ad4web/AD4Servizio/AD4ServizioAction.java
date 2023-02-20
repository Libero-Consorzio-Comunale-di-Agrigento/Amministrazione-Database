//AD4ServizioAction imports @1-0F16F33C
package ad4web.AD4Servizio;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4ServizioAction imports

//AD4ServizioAction class @1-830E18F0
public class AD4ServizioAction extends Action {

//End AD4ServizioAction class

//AD4ServizioAction: method perform @1-3DA77D31
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4ServizioModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4ServizioModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4ServizioAction: method perform

//AD4ServizioAction: call children actions @1-1122A405
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
            AD4_SERVIZIClass AD4_SERVIZI = new AD4_SERVIZIClass();
            if ( ( redirect = AD4_SERVIZI.perform( page.getRecord("AD4_SERVIZI")) ) != null ) result = redirect;
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
//End AD4ServizioAction: call children actions

//AD4_SERVIZI Record @38-E126117B
    final class AD4_SERVIZIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_SERVIZI Record

//AD4_SERVIZI Record: method perform @38-4F85605F
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_SERVIZI Record: method perform

//AD4_SERVIZI Record: children actions @38-A94869B2
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_SERVIZI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Button_Update") != null) {
                        if (validate()) {
                            Button_UpdateAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Delete") != null) {
                        Button_DeleteAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readMODULO(model.getListBox("MODULO"));
            readISTANZA(model.getListBox("ISTANZA"));
            readLIVELLO(model.getListBox("LIVELLO"));
            readABILITAZIONE(model.getListBox("ABILITAZIONE"));
            readMULTIPLO(model.getListBox("MULTIPLO"));
            readGRUPPO_ABILITAZIONE(model.getListBox("GRUPPO_ABILITAZIONE"));
            readTIPO_NOTIFICA(model.getListBox("TIPO_NOTIFICA"));
//End AD4_SERVIZI Record: children actions

//AD4_SERVIZI Record: method perform tail @38-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_SERVIZI Record: method perform tail

//Button_Update Button @40-BE3C9380
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @41-8FBA8FA8
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @43-3175CAFE
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @46-978F64C7
        protected void readMODULO(com.codecharge.components.ListBox model) {

            TextField urlPROGETTO = new TextField(null, null);
            
            urlPROGETTO.setValue( page.getHttpGetParams().getParameter("PROGETTO") );
            TextField sesAD4PROGETTO = new TextField(null, null);
            
            sesAD4PROGETTO.setValue( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT MODULO,  "
                        + "DESCRIZIONE "
                        + "  FROM MODULI "
                        + " WHERE PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
            command.addParameter( "PROGETTO", urlPROGETTO, null );
            if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
            command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
            command.setOrder( "DESCRIZIONE" );

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

//ListBoxAction @47-CFD64324
        protected void readISTANZA(com.codecharge.components.ListBox model) {

            TextField sesAD4PROGETTO = new TextField(null, null);
            
            sesAD4PROGETTO.setValue( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            TextField urlPROGETTO = new TextField(null, null);
            
            urlPROGETTO.setValue( page.getHttpGetParams().getParameter("PROGETTO") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT ISTANZA,  "
                        + "DESCRIZIONE  "
                        + "  FROM ISTANZE "
                        + "WHERE PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                        + " " );
            if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
            command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
            if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
            command.addParameter( "PROGETTO", urlPROGETTO, null );
            command.setOrder( "DESCRIZIONE" );

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

//ListBoxAction @48-AD04BB40
        protected void readLIVELLO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT *  "
                        + "FROM LIVELLI_SICUREZZA" );
            command.setOrder( "descrizione" );

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

//ListBoxAction @53-65C7AEFB
        protected void readABILITAZIONE(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "A;Automatica;C;Controllata" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @142-57AD0189
        protected void readMULTIPLO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "N;Singola;S;Multipla" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @50-F2AAAA85
        protected void readGRUPPO_ABILITAZIONE(com.codecharge.components.ListBox model) {
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
                        + " WHERE TIPO_UTENTE = 'G' "
                        + "   AND UTENTE <> 'ric_abil' "
                        + " " );
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

//ListBoxAction @51-5272B517
        protected void readTIPO_NOTIFICA(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "MAIL;Mail;POSTA;Posta" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

void read() { //AD4_SERVIZI Record: method read @38-7F8AAE5A

//AD4_SERVIZI Record: method read head @38-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_SERVIZI Record: method read head

//AD4_SERVIZI Record: init DataSource @38-930E4AE7
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_SERVIZIDataObject ds = new AD4_SERVIZIDataObject(page);
            ds.setComponent( model );
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            ds.setPostMODULO( page.getHttpPostParams().getParameter("MODULO") );
            ds.setPostISTANZA( page.getHttpPostParams().getParameter("ISTANZA") );
            ds.setUrlID_SERVIZIO( page.getHttpGetParams().getParameter("ID_SERVIZIO") );
            ds.setPostLIVELLO( page.getHttpPostParams().getParameter("LIVELLO") );
            ds.setPostABILITAZIONE( page.getHttpPostParams().getParameter("ABILITAZIONE") );
            ds.setPostGRUPPO_ABILITAZIONE( page.getHttpPostParams().getParameter("GRUPPO_ABILITAZIONE") );
            ds.setPostMULTIPLO( page.getHttpPostParams().getParameter("MULTIPLO") );
            ds.setPostTIPO_NOTIFICA( page.getHttpPostParams().getParameter("TIPO_NOTIFICA") );
            ds.setPostMAIL_NOTIFICHE( page.getHttpPostParams().getParameter("MAIL_NOTIFICHE") );
            ds.setPostCCR_NOTIFICHE( page.getHttpPostParams().getParameter("CCR_NOTIFICHE") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setPostTAG_CIM( page.getHttpPostParams().getParameter("TAG_CIM") );
            ds.setPostRECUPERO_PASSWORD( page.getHttpPostParams().getParameter("RECUPERO_PASSWORD") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_SERVIZI Record: init DataSource

//AD4_SERVIZI Record: check errors @38-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_SERVIZI Record: check errors

} //AD4_SERVIZI Record: method read tail @38-FCB6E20C

//AD4_SERVIZI Record: bind @38-F9BD7030
            public void bind(com.codecharge.components.Component model, AD4_SERVIZIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("DESC_PROGETTO").setValue(row.getDESC_PROGETTO());
                model.getControl("ABILITAZIONI").setValue(row.getABILITAZIONI());
                model.getLink("ABILITAZIONI").getParameter("MODULO").setValue( getRowFieldByName(model.getLink("ABILITAZIONI").getParameter("MODULO").getSourceName(), row ));
                model.getLink("ABILITAZIONI").getParameter("ISTANZA").setValue( getRowFieldByName(model.getLink("ABILITAZIONI").getParameter("ISTANZA").getSourceName(), row ));
                if ( this.valid ) {
                    model.getControl("ID_SERVIZIO").setValue(row.getID_SERVIZIO());
                    model.getControl("MODULO").setValue(row.getMODULO());
                    model.getControl("ISTANZA").setValue(row.getISTANZA());
                    model.getControl("LIVELLO").setValue(row.getLIVELLO());
                    model.getControl("ABILITAZIONE").setValue(row.getABILITAZIONE());
                    model.getControl("MULTIPLO").setValue(row.getMULTIPLO());
                    model.getControl("GRUPPO_ABILITAZIONE").setValue(row.getGRUPPO_ABILITAZIONE());
                    model.getControl("TIPO_NOTIFICA").setValue(row.getTIPO_NOTIFICA());
                    model.getControl("TAG_CIM").setValue(row.getTAG_CIM());
                    model.getControl("MAIL_NOTIFICHE").setValue(row.getMAIL_NOTIFICHE());
                    model.getControl("CCR_NOTIFICHE").setValue(row.getCCR_NOTIFICHE());
                    model.getControl("RECUPERO_PASSWORD").setValue(row.getRECUPERO_PASSWORD());
                }
            }
//End AD4_SERVIZI Record: bind

//AD4_SERVIZI Record: getRowFieldByName @38-2E9B3794
            public Object getRowFieldByName( String name, AD4_SERVIZIRow row ) {
                Object value = null;
                if ( "DESC_PROGETTO".equals(name) ) {
                    value = row.getDESC_PROGETTO();
                } else if ( "ABILITAZIONI".equals(name) ) {
                    value = row.getABILITAZIONI();
                } else if ( "ID_SERVIZIO".equals(name) ) {
                    value = row.getID_SERVIZIO();
                } else if ( "MODULO".equals(name) ) {
                    value = row.getMODULO();
                } else if ( "ISTANZA".equals(name) ) {
                    value = row.getISTANZA();
                } else if ( "LIVELLO".equals(name) ) {
                    value = row.getLIVELLO();
                } else if ( "ABILITAZIONE".equals(name) ) {
                    value = row.getABILITAZIONE();
                } else if ( "MULTIPLO".equals(name) ) {
                    value = row.getMULTIPLO();
                } else if ( "GRUPPO_ABILITAZIONE".equals(name) ) {
                    value = row.getGRUPPO_ABILITAZIONE();
                } else if ( "TIPO_NOTIFICA".equals(name) ) {
                    value = row.getTIPO_NOTIFICA();
                } else if ( "TAG_CIM".equals(name) ) {
                    value = row.getTAG_CIM();
                } else if ( "MAIL_NOTIFICHE".equals(name) ) {
                    value = row.getMAIL_NOTIFICHE();
                } else if ( "CCR_NOTIFICHE".equals(name) ) {
                    value = row.getCCR_NOTIFICHE();
                } else if ( "RECUPERO_PASSWORD".equals(name) ) {
                    value = row.getRECUPERO_PASSWORD();
                }
                return value;
            }
//End AD4_SERVIZI Record: getRowFieldByName

void InsertAction() { //AD4_SERVIZI Record: method insert @38-11643485

//AD4_SERVIZI Record: method insert head @38-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_SERVIZI Record: method insert head

//AD4_SERVIZI Record: components insert actions @38-68525650
            if (! model.hasErrors()) {
            }
//End AD4_SERVIZI Record: components insert actions

} //AD4_SERVIZI Record: method insert tail @38-FCB6E20C

void UpdateAction() { //AD4_SERVIZI Record: method update @38-5771D0AA

//AD4_SERVIZI Record: method update head @38-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_SERVIZI Record: method update head

//AD4_SERVIZI Record: method update body @38-F0DB6BE7
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_SERVIZIDataObject ds = new AD4_SERVIZIDataObject(page);
            ds.setComponent( model );
            AD4_SERVIZIRow row = new AD4_SERVIZIRow();
            ds.setRow(row);
            try {
                ds.setPostID_SERVIZIO( page.getHttpPostParams().getParameter("ID_SERVIZIO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_SERVIZIO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_SERVIZIO'" );
            }
            ds.setPostISTANZA( page.getHttpPostParams().getParameter("ISTANZA") );
            ds.setPostMODULO( page.getHttpPostParams().getParameter("MODULO") );
            ds.setPostLIVELLO( page.getHttpPostParams().getParameter("LIVELLO") );
            ds.setPostABILITAZIONE( page.getHttpPostParams().getParameter("ABILITAZIONE") );
            ds.setPostMULTIPLO( page.getHttpPostParams().getParameter("MULTIPLO") );
            ds.setPostGRUPPO_ABILITAZIONE( page.getHttpPostParams().getParameter("GRUPPO_ABILITAZIONE") );
            ds.setPostTIPO_NOTIFICA( page.getHttpPostParams().getParameter("TIPO_NOTIFICA") );
            ds.setPostMAIL_NOTIFICHE( page.getHttpPostParams().getParameter("MAIL_NOTIFICHE") );
            ds.setPostCCR_NOTIFICHE( page.getHttpPostParams().getParameter("CCR_NOTIFICHE") );
            row.setRECUPERO_PASSWORD(Utils.convertToString(model.getControl("RECUPERO_PASSWORD").getValue()));
            ds.setPostTAG_CIM( page.getHttpPostParams().getParameter("TAG_CIM") );
//End AD4_SERVIZI Record: method update body

//AD4_SERVIZI Record: ds.update @38-6E956EDC
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
//End AD4_SERVIZI Record: ds.update

} //AD4_SERVIZI Record: method update tail @38-FCB6E20C

void DeleteAction() { //AD4_SERVIZI Record: method delete @38-11FC2E1E

//AD4_SERVIZI Record: method delete head @38-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_SERVIZI Record: method delete head

//AD4_SERVIZI Record: method delete body @38-1D007F2A
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_SERVIZIDataObject ds = new AD4_SERVIZIDataObject(page);
            ds.setComponent( model );
            AD4_SERVIZIRow row = new AD4_SERVIZIRow();
            ds.setRow(row);
            try {
                ds.setPostID_SERVIZIO( page.getHttpPostParams().getParameter("ID_SERVIZIO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_SERVIZIO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_SERVIZIO'" );
            }
//End AD4_SERVIZI Record: method delete body

//AD4_SERVIZI Record: ds.delete @38-3584344F
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
//End AD4_SERVIZI Record: ds.delete

} //AD4_SERVIZI Record: method delete tail @38-FCB6E20C

//AD4_SERVIZI Record: method validate @38-A618F490
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox ID_SERVIZIO = (com.codecharge.components.TextBox) model.getChild( "ID_SERVIZIO" );
            if (! ID_SERVIZIO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox MODULO = (com.codecharge.components.ListBox) model.getChild( "MODULO" );
            if (! MODULO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox ISTANZA = (com.codecharge.components.ListBox) model.getChild( "ISTANZA" );
            if (! ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox LIVELLO = (com.codecharge.components.ListBox) model.getChild( "LIVELLO" );
            if (! LIVELLO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox ABILITAZIONE = (com.codecharge.components.ListBox) model.getChild( "ABILITAZIONE" );
            if (! ABILITAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox MULTIPLO = (com.codecharge.components.ListBox) model.getChild( "MULTIPLO" );
            if (! MULTIPLO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox GRUPPO_ABILITAZIONE = (com.codecharge.components.ListBox) model.getChild( "GRUPPO_ABILITAZIONE" );
            if (! GRUPPO_ABILITAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox TIPO_NOTIFICA = (com.codecharge.components.ListBox) model.getChild( "TIPO_NOTIFICA" );
            if (! TIPO_NOTIFICA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox TAG_CIM = (com.codecharge.components.TextBox) model.getChild( "TAG_CIM" );
            if (! TAG_CIM.validate()) { isControlError = true; }

            com.codecharge.components.TextBox MAIL_NOTIFICHE = (com.codecharge.components.TextBox) model.getChild( "MAIL_NOTIFICHE" );
            if (! MAIL_NOTIFICHE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CCR_NOTIFICHE = (com.codecharge.components.TextBox) model.getChild( "CCR_NOTIFICHE" );
            if (! CCR_NOTIFICHE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_SERVIZI Record: method validate

//AD4_SERVIZI Record Tail @38-FCB6E20C
    }
//End AD4_SERVIZI Record Tail

//AD4Servizio Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Servizio Page: method validate

//AD4ServizioAction Tail @1-FCB6E20C
}
//End AD4ServizioAction Tail
