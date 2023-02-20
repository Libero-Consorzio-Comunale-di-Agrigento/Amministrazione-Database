//AD4GeneraPwdAction imports @1-8506C653
package ad4web.AD4GeneraPwd;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GeneraPwdAction imports

//AD4GeneraPwdAction class @1-5302D62A
public class AD4GeneraPwdAction extends Action {

//End AD4GeneraPwdAction class

//AD4GeneraPwdAction: method perform @1-235F41C9
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GeneraPwdModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GeneraPwdModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GeneraPwdAction: method perform

//AD4GeneraPwdAction: call children actions @1-E8183582
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
            UTENTIClass UTENTI = new UTENTIClass();
            if ( ( redirect = UTENTI.perform( page.getRecord("UTENTI")) ) != null ) result = redirect;
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
//End AD4GeneraPwdAction: call children actions

//UTENTI Record @6-19DEB144
    final class UTENTIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End UTENTI Record

//UTENTI Record: method perform @6-8CBD5765
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
//End UTENTI Record: method perform

//UTENTI Record: children actions @6-B7ABF0C3
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("UTENTI".equals(formName)) {
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
//End UTENTI Record: children actions

//UTENTI Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End UTENTI Record: method perform tail

//Button_Insert Button @9-A60A3466
        void Button_InsertAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Insert");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            InsertAction();
        }
//End Button_Insert Button

//Button_Update Button @10-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @11-79294D0A
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @12-BEBD3B4D
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //UTENTI Record: method read @6-7F8AAE5A

//UTENTI Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End UTENTI Record: method read head

//UTENTI Record: init DataSource @6-FC02E4F7
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            UTENTIDataObject ds = new UTENTIDataObject(page);
            ds.setComponent( model );
            ds.setUrlUTENTE( page.getHttpGetParams().getParameter("UTENTE") );
            ds.setSesAd4PwdGenerata( SessionStorage.getInstance(req).getAttribute("Ad4PwdGenerata") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End UTENTI Record: init DataSource

//UTENTI Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End UTENTI Record: check errors

} //UTENTI Record: method read tail @6-FCB6E20C

//UTENTI Record: bind @6-64948CE2
            public void bind(com.codecharge.components.Component model, UTENTIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("NOMINATIVO").setValue(row.getNOMINATIVO());
                model.getControl("DESCR").setValue(row.getDESCR());
                model.getControl("PWD").setValue(row.getPWD());
                if ( this.valid ) {
                    model.getControl("UTENTE").setValue(row.getUTENTE());
                    model.getControl("MIN_PWD_LENGTH").setValue(row.getMIN_PWD_LENGTH());
                }
            }
//End UTENTI Record: bind

//UTENTI Record: getRowFieldByName @6-996311FE
            public Object getRowFieldByName( String name, UTENTIRow row ) {
                Object value = null;
                if ( "NOMINATIVO".equals(name) ) {
                    value = row.getNOMINATIVO();
                } else if ( "DESCR".equals(name) ) {
                    value = row.getDESCR();
                } else if ( "UTENTE".equals(name) ) {
                    value = row.getUTENTE();
                } else if ( "MIN_PWD_LENGTH".equals(name) ) {
                    value = row.getMIN_PWD_LENGTH();
                } else if ( "PWD".equals(name) ) {
                    value = row.getPWD();
                }
                return value;
            }
//End UTENTI Record: getRowFieldByName

void InsertAction() { //UTENTI Record: method insert @6-11643485

//UTENTI Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End UTENTI Record: components insert actions

} //UTENTI Record: method insert tail @6-FCB6E20C

void UpdateAction() { //UTENTI Record: method update @6-5771D0AA

//UTENTI Record: method update head @6-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End UTENTI Record: method update head

//UTENTI Record: method update body @6-36D23D7E
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            UTENTIDataObject ds = new UTENTIDataObject(page);
            ds.setComponent( model );
            UTENTIRow row = new UTENTIRow();
            ds.setRow(row);
            ds.setUrlUTENTE( page.getHttpGetParams().getParameter("UTENTE") );
            try {
                ds.setPostMIN_PWD_LENGTH( page.getHttpPostParams().getParameter("MIN_PWD_LENGTH"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'MIN_PWD_LENGTH'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'MIN_PWD_LENGTH'" );
            }
//End UTENTI Record: method update body

//UTENTI Record: ds.update @6-6E956EDC
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
//End UTENTI Record: ds.update

} //UTENTI Record: method update tail @6-FCB6E20C

void DeleteAction() { //UTENTI Record: method delete @6-11FC2E1E

//UTENTI Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End UTENTI Record: components delete actions

} //UTENTI Record: method delete tail @6-FCB6E20C

//UTENTI Record: method validate @6-C26080C3
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden UTENTE = (com.codecharge.components.Hidden) model.getChild( "UTENTE" );
            if (! UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox MIN_PWD_LENGTH = (com.codecharge.components.TextBox) model.getChild( "MIN_PWD_LENGTH" );
            if (! MIN_PWD_LENGTH.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End UTENTI Record: method validate

//UTENTI Record Tail @6-FCB6E20C
    }
//End UTENTI Record Tail

//AD4GeneraPwd Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GeneraPwd Page: method validate

//AD4GeneraPwdAction Tail @1-FCB6E20C
}
//End AD4GeneraPwdAction Tail
