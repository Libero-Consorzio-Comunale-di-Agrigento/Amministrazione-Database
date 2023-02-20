//AD4StringaAction imports @1-79313E19
package ad4web.AD4Stringa;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4StringaAction imports

//AD4StringaAction class @1-C23DEE88
public class AD4StringaAction extends Action {

//End AD4StringaAction class

//AD4StringaAction: method perform @1-E386C6D1
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4StringaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4StringaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4StringaAction: method perform

//AD4StringaAction: call children actions @1-BCE404C7
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
            AD4_STRINGHEClass AD4_STRINGHE = new AD4_STRINGHEClass();
            if ( ( redirect = AD4_STRINGHE.perform( page.getRecord("AD4_STRINGHE")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4_CHIAVIClass AD4_CHIAVI = new AD4_CHIAVIClass();
            if ( ( redirect = AD4_CHIAVI.perform( page.getRecord("AD4_CHIAVI")) ) != null ) result = redirect;
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
//End AD4StringaAction: call children actions

//AD4_STRINGHE Record @23-12450101
    final class AD4_STRINGHEClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_STRINGHE Record

//AD4_STRINGHE Record: method perform @23-F9EA93F0
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4RegistroTree" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_STRINGHE Record: method perform

//AD4_STRINGHE Record: children actions @23-FEC34317
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_STRINGHE".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Button_Update") != null) {
                        Button_UpdateAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
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
//End AD4_STRINGHE Record: children actions

//AD4_STRINGHE Record: method perform tail @23-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_STRINGHE Record: method perform tail

//Button_Update Button @25-4176CF90
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RegistroTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @26-3BED05EB
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RegistroTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @28-8D1E0A21
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RegistroTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //AD4_STRINGHE Record: method read @23-7F8AAE5A

//AD4_STRINGHE Record: method read head @23-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_STRINGHE Record: method read head

//AD4_STRINGHE Record: init DataSource @23-146D4709
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_STRINGHEDataObject ds = new AD4_STRINGHEDataObject(page);
            ds.setComponent( model );
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
            ds.setUrlSTRINGA( page.getHttpGetParams().getParameter("STRINGA") );
            ds.setUrlTIPOR( page.getHttpGetParams().getParameter("TIPOR") );
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_STRINGHE Record: init DataSource

//AD4_STRINGHE Record: check errors @23-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_STRINGHE Record: check errors

} //AD4_STRINGHE Record: method read tail @23-FCB6E20C

//AD4_STRINGHE Record: bind @23-6E95052F
            public void bind(com.codecharge.components.Component model, AD4_STRINGHERow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                    model.getControl("CHIAVE").setValue(row.getCHIAVE());
                    model.getControl("CHIAVE_OLD").setValue(row.getCHIAVE_OLD());
                    model.getControl("STRINGA_NEW").setValue(row.getSTRINGA_NEW());
                    model.getControl("STRINGA_OLD").setValue(row.getSTRINGA_OLD());
                    model.getControl("VALORE").setValue(row.getVALORE());
                    model.getControl("VALORE_OLD").setValue(row.getVALORE_OLD());
                    model.getControl("COMMENTO").setValue(row.getCOMMENTO());
                    model.getControl("TIPOR_OLD").setValue(row.getTIPOR_OLD());
                }
            }
//End AD4_STRINGHE Record: bind

//AD4_STRINGHE Record: getRowFieldByName @23-49A7792F
            public Object getRowFieldByName( String name, AD4_STRINGHERow row ) {
                Object value = null;
                if ( "CHIAVE".equals(name) ) {
                    value = row.getCHIAVE();
                } else if ( "CHIAVE_OLD".equals(name) ) {
                    value = row.getCHIAVE_OLD();
                } else if ( "STRINGA_NEW".equals(name) ) {
                    value = row.getSTRINGA_NEW();
                } else if ( "STRINGA_OLD".equals(name) ) {
                    value = row.getSTRINGA_OLD();
                } else if ( "VALORE".equals(name) ) {
                    value = row.getVALORE();
                } else if ( "VALORE_OLD".equals(name) ) {
                    value = row.getVALORE_OLD();
                } else if ( "COMMENTO".equals(name) ) {
                    value = row.getCOMMENTO();
                } else if ( "TIPOR_OLD".equals(name) ) {
                    value = row.getTIPOR_OLD();
                }
                return value;
            }
//End AD4_STRINGHE Record: getRowFieldByName

void InsertAction() { //AD4_STRINGHE Record: method insert @23-11643485

//AD4_STRINGHE Record: components insert actions @23-68525650
            if (! model.hasErrors()) {
            }
//End AD4_STRINGHE Record: components insert actions

} //AD4_STRINGHE Record: method insert tail @23-FCB6E20C

void UpdateAction() { //AD4_STRINGHE Record: method update @23-5771D0AA

//AD4_STRINGHE Record: method update head @23-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_STRINGHE Record: method update head

//AD4_STRINGHE Record: method update body @23-35CF4452
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_STRINGHEDataObject ds = new AD4_STRINGHEDataObject(page);
            ds.setComponent( model );
            AD4_STRINGHERow row = new AD4_STRINGHERow();
            ds.setRow(row);
            row.setCHIAVE(Utils.convertToString(model.getControl("CHIAVE").getValue()));
            row.setCHIAVE_OLD(Utils.convertToString(model.getControl("CHIAVE_OLD").getValue()));
            row.setSTRINGA_NEW(Utils.convertToString(model.getControl("STRINGA_NEW").getValue()));
            row.setSTRINGA_OLD(Utils.convertToString(model.getControl("STRINGA_OLD").getValue()));
            row.setVALORE(Utils.convertToString(model.getControl("VALORE").getValue()));
            row.setVALORE_OLD(Utils.convertToString(model.getControl("VALORE_OLD").getValue()));
            row.setCOMMENTO(Utils.convertToString(model.getControl("COMMENTO").getValue()));
            row.setTIPOR_OLD(Utils.convertToString(model.getControl("TIPOR_OLD").getValue()));
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
//End AD4_STRINGHE Record: method update body

//AD4_STRINGHE Record: ds.update @23-6E956EDC
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
//End AD4_STRINGHE Record: ds.update

} //AD4_STRINGHE Record: method update tail @23-FCB6E20C

void DeleteAction() { //AD4_STRINGHE Record: method delete @23-11FC2E1E

//AD4_STRINGHE Record: method delete head @23-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_STRINGHE Record: method delete head

//AD4_STRINGHE Record: method delete body @23-DB27C136
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_STRINGHEDataObject ds = new AD4_STRINGHEDataObject(page);
            ds.setComponent( model );
            AD4_STRINGHERow row = new AD4_STRINGHERow();
            ds.setRow(row);
            row.setCHIAVE(Utils.convertToString(model.getControl("CHIAVE").getValue()));
            row.setSTRINGA_NEW(Utils.convertToString(model.getControl("STRINGA_NEW").getValue()));
            row.setTIPOR_OLD(Utils.convertToString(model.getControl("TIPOR_OLD").getValue()));
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
//End AD4_STRINGHE Record: method delete body

//AD4_STRINGHE Record: ds.delete @23-3584344F
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
//End AD4_STRINGHE Record: ds.delete

} //AD4_STRINGHE Record: method delete tail @23-FCB6E20C

//AD4_STRINGHE Record: method validate @23-A91E8E21
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox CHIAVE = (com.codecharge.components.TextBox) model.getChild( "CHIAVE" );
            if (! CHIAVE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CHIAVE_OLD = (com.codecharge.components.Hidden) model.getChild( "CHIAVE_OLD" );
            if (! CHIAVE_OLD.validate()) { isControlError = true; }

            com.codecharge.components.TextBox STRINGA_NEW = (com.codecharge.components.TextBox) model.getChild( "STRINGA_NEW" );
            if (! STRINGA_NEW.validate()) { isControlError = true; }

            com.codecharge.components.Hidden STRINGA_OLD = (com.codecharge.components.Hidden) model.getChild( "STRINGA_OLD" );
            if (! STRINGA_OLD.validate()) { isControlError = true; }

            com.codecharge.components.TextArea VALORE = (com.codecharge.components.TextArea) model.getChild( "VALORE" );
            if (! VALORE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden VALORE_OLD = (com.codecharge.components.Hidden) model.getChild( "VALORE_OLD" );
            if (! VALORE_OLD.validate()) { isControlError = true; }

            com.codecharge.components.TextArea COMMENTO = (com.codecharge.components.TextArea) model.getChild( "COMMENTO" );
            if (! COMMENTO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden TIPOR_OLD = (com.codecharge.components.Hidden) model.getChild( "TIPOR_OLD" );
            if (! TIPOR_OLD.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_STRINGHE Record: method validate

//AD4_STRINGHE Record Tail @23-FCB6E20C
    }
//End AD4_STRINGHE Record Tail

//AD4_CHIAVI Record @99-74A48AC2
    final class AD4_CHIAVIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_CHIAVI Record

//AD4_CHIAVI Record: method perform @99-F9EA93F0
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4RegistroTree" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_CHIAVI Record: method perform

//AD4_CHIAVI Record: children actions @99-5B291A1E
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_CHIAVI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Button_Update") != null) {
                        Button_UpdateAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
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
//End AD4_CHIAVI Record: children actions

//AD4_CHIAVI Record: method perform tail @99-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_CHIAVI Record: method perform tail

//Button_Update Button @113-4176CF90
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RegistroTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @115-3BED05EB
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RegistroTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @118-8D1E0A21
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RegistroTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //AD4_CHIAVI Record: method read @99-7F8AAE5A

//AD4_CHIAVI Record: method read head @99-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_CHIAVI Record: method read head

//AD4_CHIAVI Record: init DataSource @99-F460D20E
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_CHIAVIDataObject ds = new AD4_CHIAVIDataObject(page);
            ds.setComponent( model );
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
            ds.setUrlTIPOR( page.getHttpGetParams().getParameter("TIPOR") );
            ds.setUrlSE_NUOVO( page.getHttpGetParams().getParameter("SE_NUOVO") );
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_CHIAVI Record: init DataSource

//AD4_CHIAVI Record: check errors @99-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_CHIAVI Record: check errors

} //AD4_CHIAVI Record: method read tail @99-FCB6E20C

//AD4_CHIAVI Record: bind @99-EF3127E0
            public void bind(com.codecharge.components.Component model, AD4_CHIAVIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("TITOLO").setValue(row.getTITOLO());
                if ( this.valid ) {
                    model.getControl("PADRE").setValue(row.getPADRE());
                    model.getControl("SUB_CHIAVE").setValue(row.getSUB_CHIAVE());
                    model.getControl("CHIAVE").setValue(row.getCHIAVE());
                    model.getControl("CHIAVE_OLD").setValue(row.getCHIAVE_OLD());
                    model.getControl("TIPOR").setValue(row.getTIPOR());
                }
            }
//End AD4_CHIAVI Record: bind

//AD4_CHIAVI Record: getRowFieldByName @99-639B1640
            public Object getRowFieldByName( String name, AD4_CHIAVIRow row ) {
                Object value = null;
                if ( "TITOLO".equals(name) ) {
                    value = row.getTITOLO();
                } else if ( "PADRE".equals(name) ) {
                    value = row.getPADRE();
                } else if ( "SUB_CHIAVE".equals(name) ) {
                    value = row.getSUB_CHIAVE();
                } else if ( "CHIAVE".equals(name) ) {
                    value = row.getCHIAVE();
                } else if ( "CHIAVE_OLD".equals(name) ) {
                    value = row.getCHIAVE_OLD();
                } else if ( "TIPOR".equals(name) ) {
                    value = row.getTIPOR();
                }
                return value;
            }
//End AD4_CHIAVI Record: getRowFieldByName

void InsertAction() { //AD4_CHIAVI Record: method insert @99-11643485

//AD4_CHIAVI Record: components insert actions @99-68525650
            if (! model.hasErrors()) {
            }
//End AD4_CHIAVI Record: components insert actions

} //AD4_CHIAVI Record: method insert tail @99-FCB6E20C

void UpdateAction() { //AD4_CHIAVI Record: method update @99-5771D0AA

//AD4_CHIAVI Record: method update head @99-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_CHIAVI Record: method update head

//AD4_CHIAVI Record: method update body @99-F844D1C5
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_CHIAVIDataObject ds = new AD4_CHIAVIDataObject(page);
            ds.setComponent( model );
            AD4_CHIAVIRow row = new AD4_CHIAVIRow();
            ds.setRow(row);
            row.setCHIAVE(Utils.convertToString(model.getControl("CHIAVE").getValue()));
            row.setCHIAVE_OLD(Utils.convertToString(model.getControl("CHIAVE_OLD").getValue()));
            ds.setExpr137( ( "" ) );
            ds.setExpr138( ( "" ) );
            ds.setExpr139( ( "" ) );
            ds.setExpr140( ( "" ) );
            ds.setExpr141( ( "" ) );
            row.setTIPOR(Utils.convertToString(model.getControl("TIPOR").getValue()));
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
//End AD4_CHIAVI Record: method update body

//AD4_CHIAVI Record: ds.update @99-6E956EDC
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
//End AD4_CHIAVI Record: ds.update

} //AD4_CHIAVI Record: method update tail @99-FCB6E20C

void DeleteAction() { //AD4_CHIAVI Record: method delete @99-11FC2E1E

//AD4_CHIAVI Record: method delete head @99-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_CHIAVI Record: method delete head

//AD4_CHIAVI Record: method delete body @99-6157B8C5
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_CHIAVIDataObject ds = new AD4_CHIAVIDataObject(page);
            ds.setComponent( model );
            AD4_CHIAVIRow row = new AD4_CHIAVIRow();
            ds.setRow(row);
            row.setCHIAVE(Utils.convertToString(model.getControl("CHIAVE").getValue()));
            ds.setExpr151( ( "" ) );
            row.setTIPOR(Utils.convertToString(model.getControl("TIPOR").getValue()));
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
//End AD4_CHIAVI Record: method delete body

//AD4_CHIAVI Record: ds.delete @99-3584344F
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
//End AD4_CHIAVI Record: ds.delete

} //AD4_CHIAVI Record: method delete tail @99-FCB6E20C

//AD4_CHIAVI Record: method validate @99-C3D44236
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden PADRE = (com.codecharge.components.Hidden) model.getChild( "PADRE" );
            if (! PADRE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox SUB_CHIAVE = (com.codecharge.components.TextBox) model.getChild( "SUB_CHIAVE" );
            if (! SUB_CHIAVE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CHIAVE = (com.codecharge.components.Hidden) model.getChild( "CHIAVE" );
            if (! CHIAVE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CHIAVE_OLD = (com.codecharge.components.Hidden) model.getChild( "CHIAVE_OLD" );
            if (! CHIAVE_OLD.validate()) { isControlError = true; }

            com.codecharge.components.Hidden TIPOR = (com.codecharge.components.Hidden) model.getChild( "TIPOR" );
            if (! TIPOR.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_CHIAVI Record: method validate

//AD4_CHIAVI Record Tail @99-FCB6E20C
    }
//End AD4_CHIAVI Record Tail

//AD4Stringa Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Stringa Page: method validate

//AD4StringaAction Tail @1-FCB6E20C
}
//End AD4StringaAction Tail
