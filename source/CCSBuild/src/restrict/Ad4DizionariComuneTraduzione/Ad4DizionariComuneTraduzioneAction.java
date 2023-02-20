//Ad4DizionariComuneTraduzioneAction imports @1-44643D82
package restrict.Ad4DizionariComuneTraduzione;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariComuneTraduzioneAction imports

//Ad4DizionariComuneTraduzioneAction class @1-E8CD58FB
public class Ad4DizionariComuneTraduzioneAction extends Action {

//End Ad4DizionariComuneTraduzioneAction class

//Ad4DizionariComuneTraduzioneAction: method perform @1-DF3BE188
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariComuneTraduzioneModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariComuneTraduzioneModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariComuneTraduzioneAction: method perform

//Ad4DizionariComuneTraduzioneAction: call children actions @1-E0E9B835
        if (result == null) {
            ComuneImpostaClass ComuneImposta = new ComuneImpostaClass();
            if ( ( redirect = ComuneImposta.perform( page.getRecord("ComuneImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariComuneTraduzioneAction: call children actions

//ComuneImposta Record @5-49AA33C6
    final class ComuneImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ComuneImposta Record

//ComuneImposta Record: method perform @5-8CBD5765
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
//End ComuneImposta Record: method perform

//ComuneImposta Record: children actions @5-A78C25CE
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ComuneImposta".equals(formName)) {
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
//End ComuneImposta Record: children actions

//ComuneImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ComuneImposta Record: method perform tail

//Button_Insert Button @12-A60A3466
        void Button_InsertAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Insert");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            InsertAction();
        }
//End Button_Insert Button

//Button_Update Button @14-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @16-79294D0A
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @19-BEBD3B4D
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //ComuneImposta Record: method read @5-7F8AAE5A

//ComuneImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ComuneImposta Record: method read head

//ComuneImposta Record: init DataSource @5-91EE3F6A
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlPROVINCIA_STATO( page.getHttpGetParams().getParameter("PROVINCIA_STATO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            ds.setUrlLINGUA( page.getHttpGetParams().getParameter("LINGUA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ComuneImposta Record: init DataSource

//ComuneImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ComuneImposta Record: check errors

} //ComuneImposta Record: method read tail @5-FCB6E20C

//ComuneImposta Record: bind @5-0E2EF3A3
            public void bind(com.codecharge.components.Component model, ComuneImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("DENOMINAZIONE").setValue(row.getDENOMINAZIONE());
                if ( this.valid ) {
                    model.getControl("COMUNE_LABEL").setValue(row.getCOMUNE_LABEL());
                    model.getControl("PROVINCIA_LABEL").setValue(row.getPROVINCIA_LABEL());
                    model.getControl("DENOMINAZIONE_ALT").setValue(row.getDENOMINAZIONE_ALT());
                }
            }
//End ComuneImposta Record: bind

//ComuneImposta Record: getRowFieldByName @5-59F76E1E
            public Object getRowFieldByName( String name, ComuneImpostaRow row ) {
                Object value = null;
                if ( "COMUNE_LABEL".equals(name) ) {
                    value = row.getCOMUNE_LABEL();
                } else if ( "DENOMINAZIONE".equals(name) ) {
                    value = row.getDENOMINAZIONE();
                } else if ( "PROVINCIA_LABEL".equals(name) ) {
                    value = row.getPROVINCIA_LABEL();
                } else if ( "DENOMINAZIONE_ALT".equals(name) ) {
                    value = row.getDENOMINAZIONE_ALT();
                }
                return value;
            }
//End ComuneImposta Record: getRowFieldByName

void InsertAction() { //ComuneImposta Record: method insert @5-11643485

//ComuneImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ComuneImposta Record: method insert head

//ComuneImposta Record: method insert body @5-F70ABDCA
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            ComuneImpostaRow row = new ComuneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlPROVINCIA_STATO( page.getHttpGetParams().getParameter("PROVINCIA_STATO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            ds.setUrlLINGUA( page.getHttpGetParams().getParameter("LINGUA") );
            ds.setPostDENOMINAZIONE_ALT( page.getHttpPostParams().getParameter("DENOMINAZIONE_ALT") );
//End ComuneImposta Record: method insert body

//ComuneImposta Record: ds.insert @5-9320B03B
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
//End ComuneImposta Record: ds.insert

} //ComuneImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //ComuneImposta Record: method update @5-5771D0AA

//ComuneImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ComuneImposta Record: method update head

//ComuneImposta Record: method update body @5-4436A5F4
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            ComuneImpostaRow row = new ComuneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlPROVINCIA_STATO( page.getHttpGetParams().getParameter("PROVINCIA_STATO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            ds.setUrlLINGUA( page.getHttpGetParams().getParameter("LINGUA") );
            ds.setPostDENOMINAZIONE_ALT( page.getHttpPostParams().getParameter("DENOMINAZIONE_ALT") );
//End ComuneImposta Record: method update body

//ComuneImposta Record: ds.update @5-6E956EDC
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
//End ComuneImposta Record: ds.update

} //ComuneImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //ComuneImposta Record: method delete @5-11FC2E1E

//ComuneImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ComuneImposta Record: method delete head

//ComuneImposta Record: method delete body @5-F80C5DD2
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            ComuneImpostaRow row = new ComuneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlPROVINCIA_STATO( page.getHttpGetParams().getParameter("PROVINCIA_STATO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            ds.setUrlLINGUA( page.getHttpGetParams().getParameter("LINGUA") );
//End ComuneImposta Record: method delete body

//ComuneImposta Record: ds.delete @5-3584344F
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
//End ComuneImposta Record: ds.delete

} //ComuneImposta Record: method delete tail @5-FCB6E20C

//ComuneImposta Record: method validate @5-59786FD0
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden COMUNE_LABEL = (com.codecharge.components.Hidden) model.getChild( "COMUNE_LABEL" );
            if (! COMUNE_LABEL.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROVINCIA_LABEL = (com.codecharge.components.Hidden) model.getChild( "PROVINCIA_LABEL" );
            if (! PROVINCIA_LABEL.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DENOMINAZIONE_ALT = (com.codecharge.components.TextBox) model.getChild( "DENOMINAZIONE_ALT" );
            if (! DENOMINAZIONE_ALT.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ComuneImposta Record: method validate

//ComuneImposta Record Tail @5-FCB6E20C
    }
//End ComuneImposta Record Tail

//Ad4DizionariComuneTraduzione Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariComuneTraduzione Page: method validate

//Ad4DizionariComuneTraduzioneAction Tail @1-FCB6E20C
}
//End Ad4DizionariComuneTraduzioneAction Tail
