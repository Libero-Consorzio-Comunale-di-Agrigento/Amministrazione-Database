//AD4AllineaLdapAction imports @1-F381243A
package ad4web.AD4AllineaLdap;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4AllineaLdapAction imports

//AD4AllineaLdapAction class @1-F51AC9F7
public class AD4AllineaLdapAction extends Action {

//End AD4AllineaLdapAction class

//AD4AllineaLdapAction: method perform @1-44EB5983
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4AllineaLdapModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4AllineaLdapModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4AllineaLdapAction: method perform

//AD4AllineaLdapAction: call children actions @1-B6FEB934
        if (result == null) {
            allineaLdapClass allineaLdap = new allineaLdapClass();
            if ( ( redirect = allineaLdap.perform( page.getRecord("allineaLdap")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4AllineaLdapAction: call children actions

//allineaLdap Record @11-840BB242
    final class allineaLdapClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End allineaLdap Record

//allineaLdap Record: method perform @11-C45C7264
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4AllineaLdap" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End allineaLdap Record: method perform

//allineaLdap Record: children actions @11-AFE33E42
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("allineaLdap".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
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
//End allineaLdap Record: children actions

//allineaLdap Record: method perform tail @11-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End allineaLdap Record: method perform tail

//Button_Update Button @13-773FC6B3
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4GruppiTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Cancel Button @14-7EECFEFE
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4GruppiTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //allineaLdap Record: method read @11-7F8AAE5A

//allineaLdap Record: method read head @11-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End allineaLdap Record: method read head

//allineaLdap Record: init DataSource @11-A5D3E5B1
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            allineaLdapDataObject ds = new allineaLdapDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End allineaLdap Record: init DataSource

//allineaLdap Record: check errors @11-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End allineaLdap Record: check errors

} //allineaLdap Record: method read tail @11-FCB6E20C

//allineaLdap Record: bind @11-29127650
            public void bind(com.codecharge.components.Component model, allineaLdapRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End allineaLdap Record: bind

//allineaLdap Record: getRowFieldByName @11-2FCF524B
            public Object getRowFieldByName( String name, allineaLdapRow row ) {
                Object value = null;
                return value;
            }
//End allineaLdap Record: getRowFieldByName

void InsertAction() { //allineaLdap Record: method insert @11-11643485

//allineaLdap Record: components insert actions @11-68525650
            if (! model.hasErrors()) {
            }
//End allineaLdap Record: components insert actions

} //allineaLdap Record: method insert tail @11-FCB6E20C

void UpdateAction() { //allineaLdap Record: method update @11-5771D0AA

//allineaLdap Record: method update head @11-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End allineaLdap Record: method update head

//allineaLdap Record: method update body @11-F07733E3
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            allineaLdapDataObject ds = new allineaLdapDataObject(page);
            ds.setComponent( model );
            allineaLdapRow row = new allineaLdapRow();
            ds.setRow(row);
//End allineaLdap Record: method update body

//allineaLdap Record: ds.update @11-6E956EDC
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
//End allineaLdap Record: ds.update

} //allineaLdap Record: method update tail @11-FCB6E20C

void DeleteAction() { //allineaLdap Record: method delete @11-11FC2E1E

//allineaLdap Record: components delete actions @11-68525650
            if (! model.hasErrors()) {
            }
//End allineaLdap Record: components delete actions

} //allineaLdap Record: method delete tail @11-FCB6E20C

//allineaLdap Record: method validate @11-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End allineaLdap Record: method validate

//allineaLdap Record Tail @11-FCB6E20C
    }
//End allineaLdap Record Tail

//AD4AllineaLdap Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4AllineaLdap Page: method validate

//AD4AllineaLdapAction Tail @1-FCB6E20C
}
//End AD4AllineaLdapAction Tail
