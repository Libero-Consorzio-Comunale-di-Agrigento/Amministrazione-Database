//AD4AcceEliminaArchAction imports @1-69EACE9D
package ad4web.AD4AcceEliminaArch;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4AcceEliminaArchAction imports

//AD4AcceEliminaArchAction class @1-25D055CD
public class AD4AcceEliminaArchAction extends Action {

//End AD4AcceEliminaArchAction class

//AD4AcceEliminaArchAction: method perform @1-78ED3064
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4AcceEliminaArchModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4AcceEliminaArchModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4AcceEliminaArchAction: method perform

//AD4AcceEliminaArchAction: call children actions @1-83C8C5FA
        if (result == null) {
            accessiClass accessi = new accessiClass();
            if ( ( redirect = accessi.perform( page.getRecord("accessi")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4AcceEliminaArchAction: call children actions

//accessi Record @11-7D8BB8FF
    final class accessiClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End accessi Record

//accessi Record: method perform @11-14861005
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4AcceEliminaArch" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End accessi Record: method perform

//accessi Record: children actions @11-49841453
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("accessi".equals(formName)) {
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
//End accessi Record: children actions

//accessi Record: method perform tail @11-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End accessi Record: method perform tail

//Button_Update Button @13-B2E970A8
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4AccessiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Cancel Button @14-2C4F183E
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4AccessiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //accessi Record: method read @11-7F8AAE5A

//accessi Record: method read head @11-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End accessi Record: method read head

//accessi Record: init DataSource @11-EC7DB660
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            accessiDataObject ds = new accessiDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End accessi Record: init DataSource

//accessi Record: check errors @11-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End accessi Record: check errors

} //accessi Record: method read tail @11-FCB6E20C

//accessi Record: bind @11-DB8F259C
            public void bind(com.codecharge.components.Component model, accessiRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End accessi Record: bind

//accessi Record: getRowFieldByName @11-6B9F5701
            public Object getRowFieldByName( String name, accessiRow row ) {
                Object value = null;
                return value;
            }
//End accessi Record: getRowFieldByName

void InsertAction() { //accessi Record: method insert @11-11643485

//accessi Record: components insert actions @11-68525650
            if (! model.hasErrors()) {
            }
//End accessi Record: components insert actions

} //accessi Record: method insert tail @11-FCB6E20C

void UpdateAction() { //accessi Record: method update @11-5771D0AA

//accessi Record: method update head @11-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End accessi Record: method update head

//accessi Record: method update body @11-2CF4CE34
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            accessiDataObject ds = new accessiDataObject(page);
            ds.setComponent( model );
            accessiRow row = new accessiRow();
            ds.setRow(row);
//End accessi Record: method update body

//accessi Record: ds.update @11-6E956EDC
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
//End accessi Record: ds.update

} //accessi Record: method update tail @11-FCB6E20C

void DeleteAction() { //accessi Record: method delete @11-11FC2E1E

//accessi Record: components delete actions @11-68525650
            if (! model.hasErrors()) {
            }
//End accessi Record: components delete actions

} //accessi Record: method delete tail @11-FCB6E20C

//accessi Record: method validate @11-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End accessi Record: method validate

//accessi Record Tail @11-FCB6E20C
    }
//End accessi Record Tail

//AD4AcceEliminaArch Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4AcceEliminaArch Page: method validate

//AD4AcceEliminaArchAction Tail @1-FCB6E20C
}
//End AD4AcceEliminaArchAction Tail

