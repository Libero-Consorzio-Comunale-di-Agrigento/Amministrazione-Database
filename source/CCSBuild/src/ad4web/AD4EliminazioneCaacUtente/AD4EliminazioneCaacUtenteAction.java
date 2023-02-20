//AD4EliminazioneCaacUtenteAction imports @1-FC98EA5D
package ad4web.AD4EliminazioneCaacUtente;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4EliminazioneCaacUtenteAction imports

//AD4EliminazioneCaacUtenteAction class @1-697F1A2B
public class AD4EliminazioneCaacUtenteAction extends Action {

//End AD4EliminazioneCaacUtenteAction class

//AD4EliminazioneCaacUtenteAction: method perform @1-49EADE78
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4EliminazioneCaacUtenteModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4EliminazioneCaacUtenteModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4EliminazioneCaacUtenteAction: method perform

//AD4EliminazioneCaacUtenteAction: call children actions @1-9D48C924
        if (result == null) {
            ad4CaacEliminaClass ad4CaacElimina = new ad4CaacEliminaClass();
            if ( ( redirect = ad4CaacElimina.perform( page.getRecord("ad4CaacElimina")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4EliminazioneCaacUtenteAction: call children actions

//ad4CaacElimina Record @11-7E86F8F5
    final class ad4CaacEliminaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ad4CaacElimina Record

//ad4CaacElimina Record: method perform @11-A1F3D9CE
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4EliminazioneCaacUtente" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End ad4CaacElimina Record: method perform

//ad4CaacElimina Record: children actions @11-68508543
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ad4CaacElimina".equals(formName)) {
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
//End ad4CaacElimina Record: children actions

//ad4CaacElimina Record: method perform tail @11-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ad4CaacElimina Record: method perform tail

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

void read() { //ad4CaacElimina Record: method read @11-7F8AAE5A

//ad4CaacElimina Record: method read head @11-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ad4CaacElimina Record: method read head

//ad4CaacElimina Record: init DataSource @11-4BC7C9F8
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ad4CaacEliminaDataObject ds = new ad4CaacEliminaDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ad4CaacElimina Record: init DataSource

//ad4CaacElimina Record: check errors @11-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ad4CaacElimina Record: check errors

} //ad4CaacElimina Record: method read tail @11-FCB6E20C

//ad4CaacElimina Record: bind @11-1B25033D
            public void bind(com.codecharge.components.Component model, ad4CaacEliminaRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End ad4CaacElimina Record: bind

//ad4CaacElimina Record: getRowFieldByName @11-014E5161
            public Object getRowFieldByName( String name, ad4CaacEliminaRow row ) {
                Object value = null;
                return value;
            }
//End ad4CaacElimina Record: getRowFieldByName

void InsertAction() { //ad4CaacElimina Record: method insert @11-11643485

//ad4CaacElimina Record: components insert actions @11-68525650
            if (! model.hasErrors()) {
            }
//End ad4CaacElimina Record: components insert actions

} //ad4CaacElimina Record: method insert tail @11-FCB6E20C

void UpdateAction() { //ad4CaacElimina Record: method update @11-5771D0AA

//ad4CaacElimina Record: method update head @11-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ad4CaacElimina Record: method update head

//ad4CaacElimina Record: method update body @11-8C0E224C
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ad4CaacEliminaDataObject ds = new ad4CaacEliminaDataObject(page);
            ds.setComponent( model );
            ad4CaacEliminaRow row = new ad4CaacEliminaRow();
            ds.setRow(row);
            ds.setUrlP_UTENTE( page.getHttpGetParams().getParameter("P_UTENTE") );
//End ad4CaacElimina Record: method update body

//ad4CaacElimina Record: ds.update @11-6E956EDC
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
//End ad4CaacElimina Record: ds.update

} //ad4CaacElimina Record: method update tail @11-FCB6E20C

void DeleteAction() { //ad4CaacElimina Record: method delete @11-11FC2E1E

//ad4CaacElimina Record: components delete actions @11-68525650
            if (! model.hasErrors()) {
            }
//End ad4CaacElimina Record: components delete actions

} //ad4CaacElimina Record: method delete tail @11-FCB6E20C

//ad4CaacElimina Record: method validate @11-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ad4CaacElimina Record: method validate

//ad4CaacElimina Record Tail @11-FCB6E20C
    }
//End ad4CaacElimina Record Tail

//AD4EliminazioneCaacUtente Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4EliminazioneCaacUtente Page: method validate

//AD4EliminazioneCaacUtenteAction Tail @1-FCB6E20C
}
//End AD4EliminazioneCaacUtenteAction Tail
