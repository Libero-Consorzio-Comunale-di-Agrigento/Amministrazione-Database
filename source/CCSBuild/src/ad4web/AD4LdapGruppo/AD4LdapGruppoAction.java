//AD4LdapGruppoAction imports @1-32FF5AF2
package ad4web.AD4LdapGruppo;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4LdapGruppoAction imports

//AD4LdapGruppoAction class @1-746DAEB4
public class AD4LdapGruppoAction extends Action {

//End AD4LdapGruppoAction class

//AD4LdapGruppoAction: method perform @1-160888A7
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4LdapGruppoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4LdapGruppoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4LdapGruppoAction: method perform

//AD4LdapGruppoAction: call children actions @1-E7C390E9
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
            ldap_gruppoClass ldap_gruppo = new ldap_gruppoClass();
            if ( ( redirect = ldap_gruppo.perform( page.getRecord("ldap_gruppo")) ) != null ) result = redirect;
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
//End AD4LdapGruppoAction: call children actions

//ldap_gruppo Record @136-197464E3
    final class ldap_gruppoClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ldap_gruppo Record

//ldap_gruppo Record: method perform @136-8CBD5765
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowInsert() ) model.getChild( "Button_Insert" ).setVisible( false );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End ldap_gruppo Record: method perform

//ldap_gruppo Record: children actions @136-0BC1C3A9
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ldap_gruppo".equals(formName)) {
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
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readSTRINGA(model.getListBox("STRINGA"));
//End ldap_gruppo Record: children actions

//ldap_gruppo Record: method perform tail @136-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ldap_gruppo Record: method perform tail

//Button_Insert Button @139-A60A3466
        void Button_InsertAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Insert");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            InsertAction();
        }
//End Button_Insert Button

//Button_Update Button @140-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @141-79294D0A
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @146-EC03202E
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4LdapGruppi" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @137-26589C47
        protected void readSTRINGA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select utente,  "
                        + "nominativo ||' ('||utente||')' descrizione "
                        + "  from utenti "
                        + " where tipo_utente = 'G' "
                        + "   and utente <> 'ric_abil' "
                        + " " );
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

void read() { //ldap_gruppo Record: method read @136-7F8AAE5A

//ldap_gruppo Record: method read head @136-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ldap_gruppo Record: method read head

//ldap_gruppo Record: init DataSource @136-BC88D403
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ldap_gruppoDataObject ds = new ldap_gruppoDataObject(page);
            ds.setComponent( model );
            ds.setUrlSTRINGA( page.getHttpGetParams().getParameter("STRINGA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ldap_gruppo Record: init DataSource

//ldap_gruppo Record: check errors @136-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ldap_gruppo Record: check errors

} //ldap_gruppo Record: method read tail @136-FCB6E20C

//ldap_gruppo Record: bind @136-F5489203
            public void bind(com.codecharge.components.Component model, ldap_gruppoRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                    model.getControl("STRINGA").setValue(row.getSTRINGA());
                    model.getControl("CHIAVE").setValue(row.getCHIAVE());
                    model.getControl("OLD_STRINGA").setValue(row.getOLD_STRINGA());
                    model.getControl("VALORE").setValue(row.getVALORE());
                }
            }
//End ldap_gruppo Record: bind

//ldap_gruppo Record: getRowFieldByName @136-DB9A7BE6
            public Object getRowFieldByName( String name, ldap_gruppoRow row ) {
                Object value = null;
                if ( "STRINGA".equals(name) ) {
                    value = row.getSTRINGA();
                } else if ( "CHIAVE".equals(name) ) {
                    value = row.getCHIAVE();
                } else if ( "OLD_STRINGA".equals(name) ) {
                    value = row.getOLD_STRINGA();
                } else if ( "VALORE".equals(name) ) {
                    value = row.getVALORE();
                }
                return value;
            }
//End ldap_gruppo Record: getRowFieldByName

void InsertAction() { //ldap_gruppo Record: method insert @136-11643485

//ldap_gruppo Record: method insert head @136-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ldap_gruppo Record: method insert head

//ldap_gruppo Record: method insert body @136-1ACCCF03
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            ldap_gruppoDataObject ds = new ldap_gruppoDataObject(page);
            ds.setComponent( model );
            ldap_gruppoRow row = new ldap_gruppoRow();
            ds.setRow(row);
            ds.setRow(row);
            row.setSTRINGA(Utils.convertToString(model.getControl("STRINGA").getValue()));
            row.setVALORE(Utils.convertToString(model.getControl("VALORE").getValue()));
            row.setCHIAVE(Utils.convertToString(model.getControl("CHIAVE").getValue()));
//End ldap_gruppo Record: method insert body

//ldap_gruppo Record: ds.insert @136-9320B03B
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
//End ldap_gruppo Record: ds.insert

} //ldap_gruppo Record: method insert tail @136-FCB6E20C

void UpdateAction() { //ldap_gruppo Record: method update @136-5771D0AA

//ldap_gruppo Record: method update head @136-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ldap_gruppo Record: method update head

//ldap_gruppo Record: method update body @136-C4DB6990
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ldap_gruppoDataObject ds = new ldap_gruppoDataObject(page);
            ds.setComponent( model );
            ldap_gruppoRow row = new ldap_gruppoRow();
            ds.setRow(row);
            ds.setRow(row);
            row.setSTRINGA(Utils.convertToString(model.getControl("STRINGA").getValue()));
            row.setCHIAVE(Utils.convertToString(model.getControl("CHIAVE").getValue()));
            row.setVALORE(Utils.convertToString(model.getControl("VALORE").getValue()));
            ds.setPostCHIAVE( page.getHttpPostParams().getParameter("CHIAVE") );
            ds.setPostOLD_STRINGA( page.getHttpPostParams().getParameter("OLD_STRINGA") );
//End ldap_gruppo Record: method update body

//ldap_gruppo Record: ds.update @136-6E956EDC
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
//End ldap_gruppo Record: ds.update

} //ldap_gruppo Record: method update tail @136-FCB6E20C

void DeleteAction() { //ldap_gruppo Record: method delete @136-11FC2E1E

//ldap_gruppo Record: method delete head @136-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ldap_gruppo Record: method delete head

//ldap_gruppo Record: method delete body @136-CD9ED8FC
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ldap_gruppoDataObject ds = new ldap_gruppoDataObject(page);
            ds.setComponent( model );
            ldap_gruppoRow row = new ldap_gruppoRow();
            ds.setRow(row);
            ds.setPostCHIAVE( page.getHttpPostParams().getParameter("CHIAVE") );
            ds.setPostOLD_STRINGA( page.getHttpPostParams().getParameter("OLD_STRINGA") );
//End ldap_gruppo Record: method delete body

//ldap_gruppo Record: ds.delete @136-3584344F
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
//End ldap_gruppo Record: ds.delete

} //ldap_gruppo Record: method delete tail @136-FCB6E20C

//ldap_gruppo Record: method validate @136-F2F81A17
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox STRINGA = (com.codecharge.components.ListBox) model.getChild( "STRINGA" );
            if (! STRINGA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CHIAVE = (com.codecharge.components.Hidden) model.getChild( "CHIAVE" );
            if (! CHIAVE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden OLD_STRINGA = (com.codecharge.components.Hidden) model.getChild( "OLD_STRINGA" );
            if (! OLD_STRINGA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox VALORE = (com.codecharge.components.TextBox) model.getChild( "VALORE" );
            if (! VALORE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ldap_gruppo Record: method validate

//ldap_gruppo Record Tail @136-FCB6E20C
    }
//End ldap_gruppo Record Tail

//AD4LdapGruppo Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4LdapGruppo Page: method validate

//AD4LdapGruppoAction Tail @1-FCB6E20C
}
//End AD4LdapGruppoAction Tail

