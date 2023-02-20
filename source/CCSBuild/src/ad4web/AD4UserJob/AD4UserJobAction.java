//AD4UserJobAction imports @1-21E676F5
package ad4web.AD4UserJob;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4UserJobAction imports

//AD4UserJobAction class @1-057CF0EC
public class AD4UserJobAction extends Action {

//End AD4UserJobAction class

//AD4UserJobAction: method perform @1-7C94E080
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4UserJobModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4UserJobModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4UserJobAction: method perform

//AD4UserJobAction: call children actions @1-EAFEF27A
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
            USER_JOBSClass USER_JOBS = new USER_JOBSClass();
            if ( ( redirect = USER_JOBS.perform( page.getRecord("USER_JOBS")) ) != null ) result = redirect;
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
//End AD4UserJobAction: call children actions

//USER_JOBS Record @56-97CF922D
    final class USER_JOBSClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End USER_JOBS Record

//USER_JOBS Record: method perform @56-148ECE47
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4UserJob" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End USER_JOBS Record: method perform

//USER_JOBS Record: children actions @56-9E9D0337
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("USER_JOBS".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Button_Delete") != null) {
                        if (validate()) {
                            Button_DeleteAction();
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
//End USER_JOBS Record: children actions

//USER_JOBS Record: method perform tail @56-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End USER_JOBS Record: method perform tail

//Button_Delete Button @181-FD0B2BC7
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4UserJob" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Update Button @57-74BA3ED8
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4UserJob" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Cancel Button @60-3175CAFE
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //USER_JOBS Record: method read @56-7F8AAE5A

//USER_JOBS Record: method read head @56-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End USER_JOBS Record: method read head

//USER_JOBS Record: init DataSource @56-4B3361A8
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            USER_JOBSDataObject ds = new USER_JOBSDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlID_JOB( page.getHttpGetParams().getParameter("ID_JOB"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_JOB'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_JOB'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End USER_JOBS Record: init DataSource

//USER_JOBS Record: check errors @56-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End USER_JOBS Record: check errors

} //USER_JOBS Record: method read tail @56-FCB6E20C

//USER_JOBS Record: bind @56-187199DA
            public void bind(com.codecharge.components.Component model, USER_JOBSRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("JOB").setValue(row.getJOB());
                model.getControl("WHAT").setValue(row.getWHAT());
                model.getControl("BROKEN").setValue(row.getBROKEN());
                if ( this.valid ) {
                    model.getControl("WHAT_HIDDEN").setValue(row.getWHAT_HIDDEN());
                    model.getControl("JOB_NUM").setValue(row.getJOB_NUM());
                    model.getControl("NEXT_DATE").setValue(row.getNEXT_DATE());
                    model.getControl("BROKEN_JOB").setValue(row.getBROKEN_JOB());
                    model.getControl("INTERVAL").setValue(row.getINTERVAL());
                }
            }
//End USER_JOBS Record: bind

//USER_JOBS Record: getRowFieldByName @56-188547C2
            public Object getRowFieldByName( String name, USER_JOBSRow row ) {
                Object value = null;
                if ( "JOB".equals(name) ) {
                    value = row.getJOB();
                } else if ( "WHAT".equals(name) ) {
                    value = row.getWHAT();
                } else if ( "WHAT_HIDDEN".equals(name) ) {
                    value = row.getWHAT_HIDDEN();
                } else if ( "JOB_NUM".equals(name) ) {
                    value = row.getJOB_NUM();
                } else if ( "NEXT_DATE".equals(name) ) {
                    value = row.getNEXT_DATE();
                } else if ( "BROKEN_JOB".equals(name) ) {
                    value = row.getBROKEN_JOB();
                } else if ( "BROKEN".equals(name) ) {
                    value = row.getBROKEN();
                } else if ( "INTERVAL".equals(name) ) {
                    value = row.getINTERVAL();
                }
                return value;
            }
//End USER_JOBS Record: getRowFieldByName

void InsertAction() { //USER_JOBS Record: method insert @56-11643485

//USER_JOBS Record: components insert actions @56-68525650
            if (! model.hasErrors()) {
            }
//End USER_JOBS Record: components insert actions

} //USER_JOBS Record: method insert tail @56-FCB6E20C

void UpdateAction() { //USER_JOBS Record: method update @56-5771D0AA

//USER_JOBS Record: method update head @56-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End USER_JOBS Record: method update head

//USER_JOBS Record: method update body @56-8A435D4D
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            USER_JOBSDataObject ds = new USER_JOBSDataObject(page);
            ds.setComponent( model );
            USER_JOBSRow row = new USER_JOBSRow();
            ds.setRow(row);
            row.setJOB_NUM(Utils.convertToLong(model.getControl("JOB_NUM").getValue()));
            row.setWHAT_HIDDEN(Utils.convertToString(model.getControl("WHAT_HIDDEN").getValue()));
            ds.setPostNEXT_DATE( page.getHttpPostParams().getParameter("NEXT_DATE") );
            ds.setPostINTERVAL( page.getHttpPostParams().getParameter("INTERVAL") );
//End USER_JOBS Record: method update body

//USER_JOBS Record: ds.update @56-6E956EDC
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
//End USER_JOBS Record: ds.update

} //USER_JOBS Record: method update tail @56-FCB6E20C

void DeleteAction() { //USER_JOBS Record: method delete @56-11FC2E1E

//USER_JOBS Record: method delete head @56-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End USER_JOBS Record: method delete head

//USER_JOBS Record: method delete body @56-CFA2590F
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            USER_JOBSDataObject ds = new USER_JOBSDataObject(page);
            ds.setComponent( model );
            USER_JOBSRow row = new USER_JOBSRow();
            ds.setRow(row);
            row.setJOB_NUM(Utils.convertToLong(model.getControl("JOB_NUM").getValue()));
            row.setBROKEN_JOB(Utils.convertToString(model.getControl("BROKEN_JOB").getValue()));
            ds.setPostNEXT_DATE( page.getHttpPostParams().getParameter("NEXT_DATE") );
//End USER_JOBS Record: method delete body

//USER_JOBS Record: ds.delete @56-3584344F
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
//End USER_JOBS Record: ds.delete

} //USER_JOBS Record: method delete tail @56-FCB6E20C

//USER_JOBS Record: method validate @56-4AF7E376
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden WHAT_HIDDEN = (com.codecharge.components.Hidden) model.getChild( "WHAT_HIDDEN" );
            if (! WHAT_HIDDEN.validate()) { isControlError = true; }

            com.codecharge.components.Hidden JOB_NUM = (com.codecharge.components.Hidden) model.getChild( "JOB_NUM" );
            if (! JOB_NUM.validate()) { isControlError = true; }

            com.codecharge.components.TextBox NEXT_DATE = (com.codecharge.components.TextBox) model.getChild( "NEXT_DATE" );
            if (! NEXT_DATE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden BROKEN_JOB = (com.codecharge.components.Hidden) model.getChild( "BROKEN_JOB" );
            if (! BROKEN_JOB.validate()) { isControlError = true; }

            com.codecharge.components.TextBox INTERVAL = (com.codecharge.components.TextBox) model.getChild( "INTERVAL" );
            if (! INTERVAL.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End USER_JOBS Record: method validate

//USER_JOBS Record Tail @56-FCB6E20C
    }
//End USER_JOBS Record Tail

//AD4UserJob Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4UserJob Page: method validate

//AD4UserJobAction Tail @1-FCB6E20C
}
//End AD4UserJobAction Tail
