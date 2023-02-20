//AD4AutenticazioneAd4UtenteAction imports @1-49FD9629
package ad4web.AD4AutenticazioneAd4Utente;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4AutenticazioneAd4UtenteAction imports

//AD4AutenticazioneAd4UtenteAction class @1-6B20ACF5
public class AD4AutenticazioneAd4UtenteAction extends Action {

//End AD4AutenticazioneAd4UtenteAction class

//AD4AutenticazioneAd4UtenteAction: method perform @1-C80860FB
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4AutenticazioneAd4UtenteModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4AutenticazioneAd4UtenteModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4AutenticazioneAd4UtenteAction: method perform

//AD4AutenticazioneAd4UtenteAction: call children actions @1-2434D858
        if (result == null) {
            ad4AuthenticateClass ad4Authenticate = new ad4AuthenticateClass();
            if ( ( redirect = ad4Authenticate.perform( page.getRecord("ad4Authenticate")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4AutenticazioneAd4UtenteAction: call children actions

//ad4Authenticate Record @11-561A4660
    final class ad4AuthenticateClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ad4Authenticate Record

//ad4Authenticate Record: method perform @11-D0EC1849
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4AutenticazioneAd4Utente" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End ad4Authenticate Record: method perform

//ad4Authenticate Record: children actions @11-53AFB1E8
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ad4Authenticate".equals(formName)) {
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
//End ad4Authenticate Record: children actions

//ad4Authenticate Record: method perform tail @11-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ad4Authenticate Record: method perform tail

//Button_Update Button @13-0B500C24
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Utente" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
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

void read() { //ad4Authenticate Record: method read @11-7F8AAE5A

//ad4Authenticate Record: method read head @11-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ad4Authenticate Record: method read head

//ad4Authenticate Record: init DataSource @11-20007D5E
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ad4AuthenticateDataObject ds = new ad4AuthenticateDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ad4Authenticate Record: init DataSource

//ad4Authenticate Record: check errors @11-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ad4Authenticate Record: check errors

} //ad4Authenticate Record: method read tail @11-FCB6E20C

//ad4Authenticate Record: bind @11-F36D2F81
            public void bind(com.codecharge.components.Component model, ad4AuthenticateRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End ad4Authenticate Record: bind

//ad4Authenticate Record: getRowFieldByName @11-2703E49F
            public Object getRowFieldByName( String name, ad4AuthenticateRow row ) {
                Object value = null;
                return value;
            }
//End ad4Authenticate Record: getRowFieldByName

void InsertAction() { //ad4Authenticate Record: method insert @11-11643485

//ad4Authenticate Record: components insert actions @11-68525650
            if (! model.hasErrors()) {
            }
//End ad4Authenticate Record: components insert actions

} //ad4Authenticate Record: method insert tail @11-FCB6E20C

void UpdateAction() { //ad4Authenticate Record: method update @11-5771D0AA

//ad4Authenticate Record: method update head @11-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ad4Authenticate Record: method update head

//ad4Authenticate Record: method update body @11-D4277F07
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ad4AuthenticateDataObject ds = new ad4AuthenticateDataObject(page);
            ds.setComponent( model );
            ad4AuthenticateRow row = new ad4AuthenticateRow();
            ds.setRow(row);
            ds.setUrlP_UTENTE( page.getHttpGetParams().getParameter("P_UTENTE") );
//End ad4Authenticate Record: method update body

//ad4Authenticate Record: ds.update @11-6E956EDC
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
//End ad4Authenticate Record: ds.update

} //ad4Authenticate Record: method update tail @11-FCB6E20C

void DeleteAction() { //ad4Authenticate Record: method delete @11-11FC2E1E

//ad4Authenticate Record: components delete actions @11-68525650
            if (! model.hasErrors()) {
            }
//End ad4Authenticate Record: components delete actions

} //ad4Authenticate Record: method delete tail @11-FCB6E20C

//ad4Authenticate Record: method validate @11-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ad4Authenticate Record: method validate

//ad4Authenticate Record Tail @11-FCB6E20C
    }
//End ad4Authenticate Record Tail

//AD4AutenticazioneAd4Utente Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4AutenticazioneAd4Utente Page: method validate

//AD4AutenticazioneAd4UtenteAction Tail @1-FCB6E20C
}
//End AD4AutenticazioneAd4UtenteAction Tail
