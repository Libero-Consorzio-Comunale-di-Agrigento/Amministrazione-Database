//AD4ModuloAction imports @1-EADFF618
package ad4web.AD4Modulo;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4ModuloAction imports

//AD4ModuloAction class @1-71391E9B
public class AD4ModuloAction extends Action {

//End AD4ModuloAction class

//AD4ModuloAction: method perform @1-C259DC87
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4ModuloModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4ModuloModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4ModuloAction: method perform

//AD4ModuloAction: call children actions @1-E6A82609
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
            AD4_MODULIClass AD4_MODULI = new AD4_MODULIClass();
            if ( ( redirect = AD4_MODULI.perform( page.getRecord("AD4_MODULI")) ) != null ) result = redirect;
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
//End AD4ModuloAction: call children actions

//AD4_MODULI Record @23-F999CD12
    final class AD4_MODULIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_MODULI Record

//AD4_MODULI Record: method perform @23-BA19EFF6
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4Modulo" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_MODULI Record: method perform

//AD4_MODULI Record: children actions @23-063BDA75
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_MODULI".equals(formName)) {
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
//End AD4_MODULI Record: children actions

//AD4_MODULI Record: method perform tail @23-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_MODULI Record: method perform tail

//Button_Update Button @25-BE3C9380
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @26-8FBA8FA8
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @28-3175CAFE
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //AD4_MODULI Record: method read @23-7F8AAE5A

//AD4_MODULI Record: method read head @23-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_MODULI Record: method read head

//AD4_MODULI Record: init DataSource @23-6AAD4F05
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_MODULIDataObject ds = new AD4_MODULIDataObject(page);
            ds.setComponent( model );
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setPostMODULO( page.getHttpPostParams().getParameter("MODULO") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setPostAMMINISTRATORE( page.getHttpPostParams().getParameter("AMMINISTRATORE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_MODULI Record: init DataSource

//AD4_MODULI Record: check errors @23-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_MODULI Record: check errors

} //AD4_MODULI Record: method read tail @23-FCB6E20C

//AD4_MODULI Record: bind @23-91A6CCA7
            public void bind(com.codecharge.components.Component model, AD4_MODULIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("PROGETTO_DESC").setValue(row.getPROGETTO_DESC());
                model.getControl("CARATTERISTICHE").setValue(row.getCARATTERISTICHE());
                model.getControl("ABILITAZIONI").setValue(row.getABILITAZIONI());
                model.getControl("MODULO").setValue(row.getMODULO());
                model.getLink("CARATTERISTICHE").getParameter("PROGETTO").setValue( getRowFieldByName(model.getLink("CARATTERISTICHE").getParameter("PROGETTO").getSourceName(), row ));
                model.getLink("CARATTERISTICHE").getParameter("MODULO").setValue( getRowFieldByName(model.getLink("CARATTERISTICHE").getParameter("MODULO").getSourceName(), row ));
                model.getLink("ABILITAZIONI").getParameter("MODULO").setValue( getRowFieldByName(model.getLink("ABILITAZIONI").getParameter("MODULO").getSourceName(), row ));
                if ( this.valid ) {
                    model.getControl("MODULO_ORIG").setValue(row.getMODULO_ORIG());
                    model.getControl("PROGETTO_ORIG").setValue(row.getPROGETTO_ORIG());
                    model.getControl("DESCRIZIONE").setValue(row.getDESCRIZIONE());
                    model.getControl("AMMINISTRATORE").setValue(row.getAMMINISTRATORE());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End AD4_MODULI Record: bind

//AD4_MODULI Record: getRowFieldByName @23-BA43D266
            public Object getRowFieldByName( String name, AD4_MODULIRow row ) {
                Object value = null;
                if ( "PROGETTO_DESC".equals(name) ) {
                    value = row.getPROGETTO_DESC();
                } else if ( "CARATTERISTICHE".equals(name) ) {
                    value = row.getCARATTERISTICHE();
                } else if ( "ABILITAZIONI".equals(name) ) {
                    value = row.getABILITAZIONI();
                } else if ( "MODULO".equals(name) ) {
                    value = row.getMODULO();
                } else if ( "MODULO_ORIG".equals(name) ) {
                    value = row.getMODULO_ORIG();
                } else if ( "PROGETTO_ORIG".equals(name) ) {
                    value = row.getPROGETTO_ORIG();
                } else if ( "DESCRIZIONE".equals(name) ) {
                    value = row.getDESCRIZIONE();
                } else if ( "AMMINISTRATORE".equals(name) ) {
                    value = row.getAMMINISTRATORE();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                } else if ( "PROGETTO".equals(name) ) {
                    value = row.getPROGETTO();
                }
                return value;
            }
//End AD4_MODULI Record: getRowFieldByName

void InsertAction() { //AD4_MODULI Record: method insert @23-11643485

//AD4_MODULI Record: method insert head @23-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_MODULI Record: method insert head

//AD4_MODULI Record: components insert actions @23-68525650
            if (! model.hasErrors()) {
            }
//End AD4_MODULI Record: components insert actions

} //AD4_MODULI Record: method insert tail @23-FCB6E20C

void UpdateAction() { //AD4_MODULI Record: method update @23-5771D0AA

//AD4_MODULI Record: method update head @23-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_MODULI Record: method update head

//AD4_MODULI Record: method update body @23-15B3F3DC
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_MODULIDataObject ds = new AD4_MODULIDataObject(page);
            ds.setComponent( model );
            AD4_MODULIRow row = new AD4_MODULIRow();
            ds.setRow(row);
            ds.setPostMODULO_ORIG( page.getHttpPostParams().getParameter("MODULO_ORIG") );
            ds.setPostPROGETTO_ORIG( page.getHttpPostParams().getParameter("PROGETTO_ORIG") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            row.setAMMINISTRATORE(Utils.convertToString(model.getControl("AMMINISTRATORE").getValue()));
//End AD4_MODULI Record: method update body

//AD4_MODULI Record: ds.update @23-6E956EDC
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
//End AD4_MODULI Record: ds.update

} //AD4_MODULI Record: method update tail @23-FCB6E20C

void DeleteAction() { //AD4_MODULI Record: method delete @23-11FC2E1E

//AD4_MODULI Record: method delete head @23-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_MODULI Record: method delete head

//AD4_MODULI Record: method delete body @23-1D9BEE89
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_MODULIDataObject ds = new AD4_MODULIDataObject(page);
            ds.setComponent( model );
            AD4_MODULIRow row = new AD4_MODULIRow();
            ds.setRow(row);
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
//End AD4_MODULI Record: method delete body

//AD4_MODULI Record: ds.delete @23-3584344F
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
//End AD4_MODULI Record: ds.delete

} //AD4_MODULI Record: method delete tail @23-FCB6E20C

//AD4_MODULI Record: method validate @23-6EEE4F8C
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden MODULO_ORIG = (com.codecharge.components.Hidden) model.getChild( "MODULO_ORIG" );
            if (! MODULO_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROGETTO_ORIG = (com.codecharge.components.Hidden) model.getChild( "PROGETTO_ORIG" );
            if (! PROGETTO_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "DESCRIZIONE" );
            if (! DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_MODULI Record: method validate

//AD4_MODULI Record Tail @23-FCB6E20C
    }
//End AD4_MODULI Record Tail

//AD4Modulo Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Modulo Page: method validate

//AD4ModuloAction Tail @1-FCB6E20C
}
//End AD4ModuloAction Tail
