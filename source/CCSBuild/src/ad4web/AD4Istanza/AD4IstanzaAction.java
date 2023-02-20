//AD4IstanzaAction imports @1-1266C35D
package ad4web.AD4Istanza;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4IstanzaAction imports

//AD4IstanzaAction class @1-B9FB156A
public class AD4IstanzaAction extends Action {

//End AD4IstanzaAction class

//AD4IstanzaAction: method perform @1-DC1C9D33
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4IstanzaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4IstanzaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4IstanzaAction: method perform

//AD4IstanzaAction: call children actions @1-93F4C3F6
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
            AD4_ISTANZEClass AD4_ISTANZE = new AD4_ISTANZEClass();
            if ( ( redirect = AD4_ISTANZE.perform( page.getRecord("AD4_ISTANZE")) ) != null ) result = redirect;
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
//End AD4IstanzaAction: call children actions

//AD4_ISTANZE Record @56-9F2C99DB
    final class AD4_ISTANZEClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_ISTANZE Record

//AD4_ISTANZE Record: method perform @56-779BB41F
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4Istanza" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_ISTANZE Record: method perform

//AD4_ISTANZE Record: children actions @56-D345B8CF
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_ISTANZE".equals(formName)) {
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
            readENTE(model.getListBox("ENTE"));
            readLINGUA(model.getListBox("LINGUA"));
            readISTANZA_AMMINISTRATORE(model.getListBox("ISTANZA_AMMINISTRATORE"));
//End AD4_ISTANZE Record: children actions

//AD4_ISTANZE Record: method perform tail @56-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_ISTANZE Record: method perform tail

//Button_Update Button @57-BE3C9380
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @58-8FBA8FA8
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @60-3175CAFE
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @104-82B838F4
        protected void readENTE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT *  "
                        + "FROM ENTI" );

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

//ListBoxAction @107-42258E8E
        protected void readLINGUA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT *  "
                        + "FROM LINGUE_VIEW" );

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

//ListBoxAction @176-22EE639B
        protected void readISTANZA_AMMINISTRATORE(com.codecharge.components.ListBox model) {

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

            command.setSql( "  SELECT ISTANZA,  "
                        + "   "
                        + "         ISTANZA||' - '||DESCRIZIONE DES_ISTANZA "
                        + "    FROM ISTANZE  "
                        + "   WHERE PROGETTO = 'AD4' "
                        + "     AND 'AD4' <> DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                        + "" );
            if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
            command.addParameter( "PROGETTO", urlPROGETTO, null );
            if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
            command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
            command.setOrder( "1 ASC " );

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

void read() { //AD4_ISTANZE Record: method read @56-7F8AAE5A

//AD4_ISTANZE Record: method read head @56-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_ISTANZE Record: method read head

//AD4_ISTANZE Record: init DataSource @56-47A5A697
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_ISTANZEDataObject ds = new AD4_ISTANZEDataObject(page);
            ds.setComponent( model );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setPostISTANZA( page.getHttpPostParams().getParameter("ISTANZA") );
            ds.setPostENTE( page.getHttpPostParams().getParameter("ENTE") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            ds.setPostUSER_ORACLE( page.getHttpPostParams().getParameter("USER_ORACLE") );
            ds.setPostPASSWORD_ORACLE( page.getHttpPostParams().getParameter("PASSWORD_ORACLE") );
            ds.setPostDISLOCAZIONE( page.getHttpPostParams().getParameter("DISLOCAZIONE") );
            ds.setPostDISLOCAZIONE_TEMPORANEA( page.getHttpPostParams().getParameter("DISLOCAZIONE_TEMPORANEA") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setPostLINGUA( page.getHttpPostParams().getParameter("LINGUA") );
            ds.setPostLINK_ORACLE( page.getHttpPostParams().getParameter("LINK_ORACLE") );
            ds.setPostSERVIZIO( page.getHttpPostParams().getParameter("SERVIZIO") );
            ds.setPostDATABASE_LINK( page.getHttpPostParams().getParameter("DATABASE_LINK") );
            ds.setPostDATABASE_DRIVER( page.getHttpPostParams().getParameter("DATABASE_DRIVER") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_ISTANZE Record: init DataSource

//AD4_ISTANZE Record: check errors @56-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_ISTANZE Record: check errors

} //AD4_ISTANZE Record: method read tail @56-FCB6E20C

//AD4_ISTANZE Record: bind @56-C6333636
            public void bind(com.codecharge.components.Component model, AD4_ISTANZERow row ) {
                if ( model == null || row == null ) return;
                model.getControl("DESC_PROGETTO").setValue(row.getDESC_PROGETTO());
                model.getControl("CARATTERISTICHE").setValue(row.getCARATTERISTICHE());
                model.getControl("ABILITAZIONI").setValue(row.getABILITAZIONI());
                model.getControl("REGISTRO").setValue(row.getREGISTRO());
                model.getControl("ISTANZA").setValue(row.getISTANZA());
                model.getControl("INSTALLAZIONE").setValue(row.getINSTALLAZIONE());
                model.getControl("VERSIONE").setValue(row.getVERSIONE());
                model.getLink("CARATTERISTICHE").getParameter("PROGETTO").setValue( getRowFieldByName(model.getLink("CARATTERISTICHE").getParameter("PROGETTO").getSourceName(), row ));
                model.getLink("CARATTERISTICHE").getParameter("ISTANZA").setValue( getRowFieldByName(model.getLink("CARATTERISTICHE").getParameter("ISTANZA").getSourceName(), row ));
                model.getLink("ABILITAZIONI").getParameter("ISTANZA").setValue( getRowFieldByName(model.getLink("ABILITAZIONI").getParameter("ISTANZA").getSourceName(), row ));
                model.getLink("REGISTRO").getParameter("USRORCL").setValue( getRowFieldByName(model.getLink("REGISTRO").getParameter("USRORCL").getSourceName(), row ));
                if ( this.valid ) {
                    model.getControl("ISTANZA_ORIG").setValue(row.getISTANZA_ORIG());
                    model.getControl("DESCRIZIONE").setValue(row.getDESCRIZIONE());
                    model.getControl("ENTE").setValue(row.getENTE());
                    model.getControl("PROGETTO").setValue(row.getPROGETTO());
                    model.getControl("LINGUA").setValue(row.getLINGUA());
                    model.getControl("DISLOCAZIONE").setValue(row.getDISLOCAZIONE());
                    model.getControl("DISLOCAZIONE_TEMPORANEA").setValue(row.getDISLOCAZIONE_TEMPORANEA());
                    model.getControl("USER_ORACLE").setValue(row.getUSER_ORACLE());
                    model.getControl("PASSWORD_ORACLE").setValue(row.getPASSWORD_ORACLE());
                    model.getControl("ISTANZA_AMMINISTRATORE").setValue(row.getISTANZA_AMMINISTRATORE());
                    model.getControl("ISTANZA_AMMINISTRATORE_ORIG").setValue(row.getISTANZA_AMMINISTRATORE_ORIG());
                    model.getControl("LINK_ORACLE").setValue(row.getLINK_ORACLE());
                    model.getControl("DATABASE_LINK").setValue(row.getDATABASE_LINK());
                    model.getControl("DATABASE_DRIVER").setValue(row.getDATABASE_DRIVER());
                    model.getControl("SERVIZIO").setValue(row.getSERVIZIO());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End AD4_ISTANZE Record: bind

//AD4_ISTANZE Record: getRowFieldByName @56-D7B10F8F
            public Object getRowFieldByName( String name, AD4_ISTANZERow row ) {
                Object value = null;
                if ( "DESC_PROGETTO".equals(name) ) {
                    value = row.getDESC_PROGETTO();
                } else if ( "CARATTERISTICHE".equals(name) ) {
                    value = row.getCARATTERISTICHE();
                } else if ( "ABILITAZIONI".equals(name) ) {
                    value = row.getABILITAZIONI();
                } else if ( "REGISTRO".equals(name) ) {
                    value = row.getREGISTRO();
                } else if ( "ISTANZA".equals(name) ) {
                    value = row.getISTANZA();
                } else if ( "ISTANZA_ORIG".equals(name) ) {
                    value = row.getISTANZA_ORIG();
                } else if ( "DESCRIZIONE".equals(name) ) {
                    value = row.getDESCRIZIONE();
                } else if ( "ENTE".equals(name) ) {
                    value = row.getENTE();
                } else if ( "PROGETTO".equals(name) ) {
                    value = row.getPROGETTO();
                } else if ( "LINGUA".equals(name) ) {
                    value = row.getLINGUA();
                } else if ( "DISLOCAZIONE".equals(name) ) {
                    value = row.getDISLOCAZIONE();
                } else if ( "DISLOCAZIONE_TEMPORANEA".equals(name) ) {
                    value = row.getDISLOCAZIONE_TEMPORANEA();
                } else if ( "USER_ORACLE".equals(name) ) {
                    value = row.getUSER_ORACLE();
                } else if ( "PASSWORD_ORACLE".equals(name) ) {
                    value = row.getPASSWORD_ORACLE();
                } else if ( "PWD_MODIFIED".equals(name) ) {
                    value = row.getPWD_MODIFIED();
                } else if ( "ISTANZA_AMMINISTRATORE".equals(name) ) {
                    value = row.getISTANZA_AMMINISTRATORE();
                } else if ( "ISTANZA_AMMINISTRATORE_ORIG".equals(name) ) {
                    value = row.getISTANZA_AMMINISTRATORE_ORIG();
                } else if ( "LINK_ORACLE".equals(name) ) {
                    value = row.getLINK_ORACLE();
                } else if ( "DATABASE_LINK".equals(name) ) {
                    value = row.getDATABASE_LINK();
                } else if ( "DATABASE_DRIVER".equals(name) ) {
                    value = row.getDATABASE_DRIVER();
                } else if ( "SERVIZIO".equals(name) ) {
                    value = row.getSERVIZIO();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                } else if ( "INSTALLAZIONE".equals(name) ) {
                    value = row.getINSTALLAZIONE();
                } else if ( "VERSIONE".equals(name) ) {
                    value = row.getVERSIONE();
                } else if ( "USRORCL".equals(name) ) {
                    value = row.getUSRORCL();
                }
                return value;
            }
//End AD4_ISTANZE Record: getRowFieldByName

void InsertAction() { //AD4_ISTANZE Record: method insert @56-11643485

//AD4_ISTANZE Record: components insert actions @56-68525650
            if (! model.hasErrors()) {
            }
//End AD4_ISTANZE Record: components insert actions

} //AD4_ISTANZE Record: method insert tail @56-FCB6E20C

void UpdateAction() { //AD4_ISTANZE Record: method update @56-5771D0AA

//AD4_ISTANZE Record: method update head @56-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_ISTANZE Record: method update head

//AD4_ISTANZE Record: method update body @56-208F10AF
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_ISTANZEDataObject ds = new AD4_ISTANZEDataObject(page);
            ds.setComponent( model );
            AD4_ISTANZERow row = new AD4_ISTANZERow();
            ds.setRow(row);
            ds.setPostISTANZA( page.getHttpPostParams().getParameter("ISTANZA") );
            ds.setPostISTANZA_ORIG( page.getHttpPostParams().getParameter("ISTANZA_ORIG") );
            ds.setPostPROGETTO( page.getHttpPostParams().getParameter("PROGETTO") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            ds.setPostENTE( page.getHttpPostParams().getParameter("ENTE") );
            ds.setPostUSER_ORACLE( page.getHttpPostParams().getParameter("USER_ORACLE") );
            ds.setPostPASSWORD_ORACLE( page.getHttpPostParams().getParameter("PASSWORD_ORACLE") );
            row.setPWD_MODIFIED(Utils.convertToString(model.getControl("PWD_MODIFIED").getValue()));
            ds.setPostDISLOCAZIONE( page.getHttpPostParams().getParameter("DISLOCAZIONE") );
            ds.setPostDISLOCAZIONE_TEMPORANEA( page.getHttpPostParams().getParameter("DISLOCAZIONE_TEMPORANEA") );
            ds.setPostLINGUA( page.getHttpPostParams().getParameter("LINGUA") );
            ds.setPostLINK_ORACLE( page.getHttpPostParams().getParameter("LINK_ORACLE") );
            ds.setPostDATABASE_LINK( page.getHttpPostParams().getParameter("DATABASE_LINK") );
            ds.setPostSERVIZIO( page.getHttpPostParams().getParameter("SERVIZIO") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setPostDATABASE_DRIVER( page.getHttpPostParams().getParameter("DATABASE_DRIVER") );
            ds.setPostISTANZA_AMMINISTRATORE( page.getHttpPostParams().getParameter("ISTANZA_AMMINISTRATORE") );
            row.setISTANZA_AMMINISTRATORE_ORIG(Utils.convertToString(model.getControl("ISTANZA_AMMINISTRATORE_ORIG").getValue()));
//End AD4_ISTANZE Record: method update body

//AD4_ISTANZE Record: ds.update @56-6E956EDC
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
//End AD4_ISTANZE Record: ds.update

} //AD4_ISTANZE Record: method update tail @56-FCB6E20C

void DeleteAction() { //AD4_ISTANZE Record: method delete @56-11FC2E1E

//AD4_ISTANZE Record: method delete head @56-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_ISTANZE Record: method delete head

//AD4_ISTANZE Record: method delete body @56-24DCC265
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_ISTANZEDataObject ds = new AD4_ISTANZEDataObject(page);
            ds.setComponent( model );
            AD4_ISTANZERow row = new AD4_ISTANZERow();
            ds.setRow(row);
            row.setISTANZA_ORIG(Utils.convertToString(model.getControl("ISTANZA_ORIG").getValue()));
            ds.setPostUSER_ORACLE( page.getHttpPostParams().getParameter("USER_ORACLE") );
            row.setISTANZA_AMMINISTRATORE_ORIG(Utils.convertToString(model.getControl("ISTANZA_AMMINISTRATORE_ORIG").getValue()));
//End AD4_ISTANZE Record: method delete body

//AD4_ISTANZE Record: ds.delete @56-3584344F
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
//End AD4_ISTANZE Record: ds.delete

} //AD4_ISTANZE Record: method delete tail @56-FCB6E20C

//AD4_ISTANZE Record: method validate @56-CB63851E
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden ISTANZA_ORIG = (com.codecharge.components.Hidden) model.getChild( "ISTANZA_ORIG" );
            if (! ISTANZA_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "DESCRIZIONE" );
            if (! DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox ENTE = (com.codecharge.components.ListBox) model.getChild( "ENTE" );
            if (! ENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROGETTO = (com.codecharge.components.Hidden) model.getChild( "PROGETTO" );
            if (! PROGETTO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox LINGUA = (com.codecharge.components.ListBox) model.getChild( "LINGUA" );
            if (! LINGUA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DISLOCAZIONE = (com.codecharge.components.TextBox) model.getChild( "DISLOCAZIONE" );
            if (! DISLOCAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DISLOCAZIONE_TEMPORANEA = (com.codecharge.components.TextBox) model.getChild( "DISLOCAZIONE_TEMPORANEA" );
            if (! DISLOCAZIONE_TEMPORANEA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox USER_ORACLE = (com.codecharge.components.TextBox) model.getChild( "USER_ORACLE" );
            if (! USER_ORACLE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox PASSWORD_ORACLE = (com.codecharge.components.TextBox) model.getChild( "PASSWORD_ORACLE" );
            if (! PASSWORD_ORACLE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PWD_MODIFIED = (com.codecharge.components.Hidden) model.getChild( "PWD_MODIFIED" );
            if (! PWD_MODIFIED.validate()) { isControlError = true; }

            com.codecharge.components.ListBox ISTANZA_AMMINISTRATORE = (com.codecharge.components.ListBox) model.getChild( "ISTANZA_AMMINISTRATORE" );
            if (! ISTANZA_AMMINISTRATORE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ISTANZA_AMMINISTRATORE_ORIG = (com.codecharge.components.Hidden) model.getChild( "ISTANZA_AMMINISTRATORE_ORIG" );
            if (! ISTANZA_AMMINISTRATORE_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.TextBox LINK_ORACLE = (com.codecharge.components.TextBox) model.getChild( "LINK_ORACLE" );
            if (! LINK_ORACLE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DATABASE_LINK = (com.codecharge.components.TextBox) model.getChild( "DATABASE_LINK" );
            if (! DATABASE_LINK.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DATABASE_DRIVER = (com.codecharge.components.TextBox) model.getChild( "DATABASE_DRIVER" );
            if (! DATABASE_DRIVER.validate()) { isControlError = true; }

            com.codecharge.components.TextBox SERVIZIO = (com.codecharge.components.TextBox) model.getChild( "SERVIZIO" );
            if (! SERVIZIO.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_ISTANZE Record: method validate

//AD4_ISTANZE Record Tail @56-FCB6E20C
    }
//End AD4_ISTANZE Record Tail

//AD4Istanza Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Istanza Page: method validate

//AD4IstanzaAction Tail @1-FCB6E20C
}
//End AD4IstanzaAction Tail
