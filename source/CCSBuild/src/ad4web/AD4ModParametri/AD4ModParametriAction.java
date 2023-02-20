//AD4ModParametriAction imports @1-2321253B
package ad4web.AD4ModParametri;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4ModParametriAction imports

//AD4ModParametriAction class @1-AC0CFC51
public class AD4ModParametriAction extends Action {

//End AD4ModParametriAction class

//AD4ModParametriAction: method perform @1-FF353B8F
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4ModParametriModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4ModParametriModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4ModParametriAction: method perform

//AD4ModParametriAction: call children actions @1-DCE5C60C
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
            AD4_PARAMETROClass AD4_PARAMETRO = new AD4_PARAMETROClass();
            if ( ( redirect = AD4_PARAMETRO.perform( page.getRecord("AD4_PARAMETRO")) ) != null ) result = redirect;
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
//End AD4ModParametriAction: call children actions

//AD4_PARAMETRO Record @23-A5A1BF7E
    final class AD4_PARAMETROClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_PARAMETRO Record

//AD4_PARAMETRO Record: method perform @23-A383C737
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4RichiestaParametri" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_PARAMETRO Record: method perform

//AD4_PARAMETRO Record: children actions @23-15EC2FCC
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_PARAMETRO".equals(formName)) {
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
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4_PARAMETRO Record: children actions

//AD4_PARAMETRO Record: method perform tail @23-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_PARAMETRO Record: method perform tail

//Button_Update Button @25-A8E2E02A
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RichiestaParametri" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @26-9964FC02
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RichiestaParametri" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @28-447B5B68
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RichiestaParametri" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //AD4_PARAMETRO Record: method read @23-7F8AAE5A

//AD4_PARAMETRO Record: method read head @23-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_PARAMETRO Record: method read head

//AD4_PARAMETRO Record: init DataSource @23-437B0FA9
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_PARAMETRODataObject ds = new AD4_PARAMETRODataObject(page);
            ds.setComponent( model );
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
            ds.setUrlIDPAR( page.getHttpGetParams().getParameter("IDPAR") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_PARAMETRO Record: init DataSource

//AD4_PARAMETRO Record: check errors @23-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_PARAMETRO Record: check errors

} //AD4_PARAMETRO Record: method read tail @23-FCB6E20C

//AD4_PARAMETRO Record: bind @23-58A39FF1
            public void bind(com.codecharge.components.Component model, AD4_PARAMETRORow row ) {
                if ( model == null || row == null ) return;
                model.getControl("PARAMETRO").setValue(row.getPARAMETRO());
                if ( this.valid ) {
                    model.getControl("ID_PARAMETRO").setValue(row.getID_PARAMETRO());
                    model.getControl("ID_RICHIESTA").setValue(row.getID_RICHIESTA());
                    model.getControl("VALORE").setValue(row.getVALORE());
                }
            }
//End AD4_PARAMETRO Record: bind

//AD4_PARAMETRO Record: getRowFieldByName @23-5C8CD669
            public Object getRowFieldByName( String name, AD4_PARAMETRORow row ) {
                Object value = null;
                if ( "PARAMETRO".equals(name) ) {
                    value = row.getPARAMETRO();
                } else if ( "ID_PARAMETRO".equals(name) ) {
                    value = row.getID_PARAMETRO();
                } else if ( "ID_RICHIESTA".equals(name) ) {
                    value = row.getID_RICHIESTA();
                } else if ( "VALORE".equals(name) ) {
                    value = row.getVALORE();
                }
                return value;
            }
//End AD4_PARAMETRO Record: getRowFieldByName

void InsertAction() { //AD4_PARAMETRO Record: method insert @23-11643485

//AD4_PARAMETRO Record: components insert actions @23-68525650
            if (! model.hasErrors()) {
            }
//End AD4_PARAMETRO Record: components insert actions

} //AD4_PARAMETRO Record: method insert tail @23-FCB6E20C

void UpdateAction() { //AD4_PARAMETRO Record: method update @23-5771D0AA

//AD4_PARAMETRO Record: method update head @23-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_PARAMETRO Record: method update head

//AD4_PARAMETRO Record: method update body @23-3E4D5840
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_PARAMETRODataObject ds = new AD4_PARAMETRODataObject(page);
            ds.setComponent( model );
            AD4_PARAMETRORow row = new AD4_PARAMETRORow();
            ds.setRow(row);
            row.setID_RICHIESTA(Utils.convertToString(model.getControl("ID_RICHIESTA").getValue()));
            row.setPARAMETRO(Utils.convertToString(model.getControl("PARAMETRO").getValue()));
            row.setVALORE(Utils.convertToString(model.getControl("VALORE").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End AD4_PARAMETRO Record: method update body

//AD4_PARAMETRO Record: ds.update @23-6E956EDC
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
//End AD4_PARAMETRO Record: ds.update

} //AD4_PARAMETRO Record: method update tail @23-FCB6E20C

void DeleteAction() { //AD4_PARAMETRO Record: method delete @23-11FC2E1E

//AD4_PARAMETRO Record: method delete head @23-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_PARAMETRO Record: method delete head

//AD4_PARAMETRO Record: method delete body @23-729C91EF
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_PARAMETRODataObject ds = new AD4_PARAMETRODataObject(page);
            ds.setComponent( model );
            AD4_PARAMETRORow row = new AD4_PARAMETRORow();
            ds.setRow(row);
            ds.setPostID_PARAMETRO( page.getHttpPostParams().getParameter("ID_PARAMETRO") );
//End AD4_PARAMETRO Record: method delete body

//AD4_PARAMETRO Record: ds.delete @23-3584344F
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
//End AD4_PARAMETRO Record: ds.delete

} //AD4_PARAMETRO Record: method delete tail @23-FCB6E20C

//AD4_PARAMETRO Record: method validate @23-D7F5A5DE
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden ID_PARAMETRO = (com.codecharge.components.Hidden) model.getChild( "ID_PARAMETRO" );
            if (! ID_PARAMETRO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ID_RICHIESTA = (com.codecharge.components.Hidden) model.getChild( "ID_RICHIESTA" );
            if (! ID_RICHIESTA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox VALORE = (com.codecharge.components.TextBox) model.getChild( "VALORE" );
            if (! VALORE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_PARAMETRO Record: method validate

//AD4_PARAMETRO Record Tail @23-FCB6E20C
    }
//End AD4_PARAMETRO Record Tail

//AD4ModParametri Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4ModParametri Page: method validate

//AD4ModParametriAction Tail @1-FCB6E20C
}
//End AD4ModParametriAction Tail
