//AD4RigeneraSOAction imports @1-1A733638
package ad4web.AD4RigeneraSO;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4RigeneraSOAction imports

//AD4RigeneraSOAction class @1-5D95CC27
public class AD4RigeneraSOAction extends Action {

//End AD4RigeneraSOAction class

//AD4RigeneraSOAction: method perform @1-0693E010
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4RigeneraSOModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4RigeneraSOModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4RigeneraSOAction: method perform

//AD4RigeneraSOAction: call children actions @1-21250451
        if (result == null) {
            rigeneraClass rigenera = new rigeneraClass();
            if ( ( redirect = rigenera.perform( page.getRecord("rigenera")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4RigeneraSOAction: call children actions

//rigenera Record @11-751F9868
    final class rigeneraClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End rigenera Record

//rigenera Record: method perform @11-323986BA
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4RigeneraSO" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End rigenera Record: method perform

//rigenera Record: children actions @11-02606E9A
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("rigenera".equals(formName)) {
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
//End rigenera Record: children actions

//rigenera Record: method perform tail @11-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End rigenera Record: method perform tail

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

void read() { //rigenera Record: method read @11-7F8AAE5A

//rigenera Record: method read head @11-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End rigenera Record: method read head

//rigenera Record: init DataSource @11-CCC682A2
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            rigeneraDataObject ds = new rigeneraDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End rigenera Record: init DataSource

//rigenera Record: check errors @11-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End rigenera Record: check errors

} //rigenera Record: method read tail @11-FCB6E20C

//rigenera Record: bind @11-38531639
            public void bind(com.codecharge.components.Component model, rigeneraRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End rigenera Record: bind

//rigenera Record: getRowFieldByName @11-5E0B6FD0
            public Object getRowFieldByName( String name, rigeneraRow row ) {
                Object value = null;
                return value;
            }
//End rigenera Record: getRowFieldByName

void InsertAction() { //rigenera Record: method insert @11-11643485

//rigenera Record: components insert actions @11-68525650
            if (! model.hasErrors()) {
            }
//End rigenera Record: components insert actions

} //rigenera Record: method insert tail @11-FCB6E20C

void UpdateAction() { //rigenera Record: method update @11-5771D0AA

//rigenera Record: method update head @11-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End rigenera Record: method update head

//rigenera Record: method update body @11-A0BE5B30
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            rigeneraDataObject ds = new rigeneraDataObject(page);
            ds.setComponent( model );
            rigeneraRow row = new rigeneraRow();
            ds.setRow(row);
//End rigenera Record: method update body

//rigenera Record: ds.update @11-6E956EDC
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
//End rigenera Record: ds.update

} //rigenera Record: method update tail @11-FCB6E20C

void DeleteAction() { //rigenera Record: method delete @11-11FC2E1E

//rigenera Record: components delete actions @11-68525650
            if (! model.hasErrors()) {
            }
//End rigenera Record: components delete actions

} //rigenera Record: method delete tail @11-FCB6E20C

//rigenera Record: method validate @11-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End rigenera Record: method validate

//rigenera Record Tail @11-FCB6E20C
    }
//End rigenera Record Tail

//AD4RigeneraSO Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4RigeneraSO Page: method validate

//AD4RigeneraSOAction Tail @1-FCB6E20C
}
//End AD4RigeneraSOAction Tail
