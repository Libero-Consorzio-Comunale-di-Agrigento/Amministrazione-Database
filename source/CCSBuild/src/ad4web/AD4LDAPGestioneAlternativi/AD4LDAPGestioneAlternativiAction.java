//AD4LDAPGestioneAlternativiAction imports @1-5BF33098
package ad4web.AD4LDAPGestioneAlternativi;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4LDAPGestioneAlternativiAction imports

//AD4LDAPGestioneAlternativiAction class @1-63A43E24
public class AD4LDAPGestioneAlternativiAction extends Action {

//End AD4LDAPGestioneAlternativiAction class

//AD4LDAPGestioneAlternativiAction: method perform @1-00BFA07B
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4LDAPGestioneAlternativiModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4LDAPGestioneAlternativiModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4LDAPGestioneAlternativiAction: method perform

//AD4LDAPGestioneAlternativiAction: call children actions @1-B0B5D828
        if (result == null) {
            ldapClass ldap = new ldapClass();
            if ( ( redirect = ldap.perform( page.getRecord("ldap")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4LDAPGestioneAlternativiAction: call children actions

//ldap Record @11-44D8B82C
    final class ldapClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ldap Record

//ldap Record: method perform @11-34CA2E63
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4LdapConfig" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End ldap Record: method perform

//ldap Record: children actions @11-2DFCDB45
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ldap".equals(formName)) {
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
                        if (validate()) {
                            Button_DeleteAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
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
//End ldap Record: children actions

//ldap Record: method perform tail @11-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ldap Record: method perform tail

//Button_Update Button @13-CA8B2B1C
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4LdapConfig" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @20-A6B0D509
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4LdapConfig" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @14-EC52711F
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4LdapConfig" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //ldap Record: method read @11-7F8AAE5A

//ldap Record: method read head @11-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ldap Record: method read head

//ldap Record: init DataSource @11-B1FC410B
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ldapDataObject ds = new ldapDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ldap Record: init DataSource

//ldap Record: check errors @11-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ldap Record: check errors

} //ldap Record: method read tail @11-FCB6E20C

//ldap Record: bind @11-122EC6BA
            public void bind(com.codecharge.components.Component model, ldapRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End ldap Record: bind

//ldap Record: getRowFieldByName @11-031FBA90
            public Object getRowFieldByName( String name, ldapRow row ) {
                Object value = null;
                return value;
            }
//End ldap Record: getRowFieldByName

void InsertAction() { //ldap Record: method insert @11-11643485

//ldap Record: components insert actions @11-68525650
            if (! model.hasErrors()) {
            }
//End ldap Record: components insert actions

} //ldap Record: method insert tail @11-FCB6E20C

void UpdateAction() { //ldap Record: method update @11-5771D0AA

//ldap Record: method update head @11-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ldap Record: method update head

//ldap Record: method update body @11-25251EC1
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ldapDataObject ds = new ldapDataObject(page);
            ds.setComponent( model );
            ldapRow row = new ldapRow();
            ds.setRow(row);
//End ldap Record: method update body

//ldap Record: ds.update @11-6E956EDC
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
//End ldap Record: ds.update

} //ldap Record: method update tail @11-FCB6E20C

void DeleteAction() { //ldap Record: method delete @11-11FC2E1E

//ldap Record: method delete head @11-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ldap Record: method delete head

//ldap Record: method delete body @11-9CF77822
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ldapDataObject ds = new ldapDataObject(page);
            ds.setComponent( model );
            ldapRow row = new ldapRow();
            ds.setRow(row);
            ds.setUrlChiave( page.getHttpGetParams().getParameter("chiave") );
//End ldap Record: method delete body

//ldap Record: ds.delete @11-3584344F
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
//End ldap Record: ds.delete

} //ldap Record: method delete tail @11-FCB6E20C

//ldap Record: method validate @11-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ldap Record: method validate

//ldap Record Tail @11-FCB6E20C
    }
//End ldap Record Tail

//AD4LDAPGestioneAlternativi Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4LDAPGestioneAlternativi Page: method validate

//AD4LDAPGestioneAlternativiAction Tail @1-FCB6E20C
}
//End AD4LDAPGestioneAlternativiAction Tail
