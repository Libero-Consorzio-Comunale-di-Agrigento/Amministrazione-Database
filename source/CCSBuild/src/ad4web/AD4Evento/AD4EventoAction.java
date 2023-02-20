//AD4EventoAction imports @1-825E9684
package ad4web.AD4Evento;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4EventoAction imports

//AD4EventoAction class @1-1338E2C1
public class AD4EventoAction extends Action {

//End AD4EventoAction class

//AD4EventoAction: method perform @1-40EE0545
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4EventoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4EventoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4EventoAction: method perform

//AD4EventoAction: call children actions @1-33791E0A
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
            EVENTIClass EVENTI = new EVENTIClass();
            if ( ( redirect = EVENTI.perform( page.getRecord("EVENTI")) ) != null ) result = redirect;
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
//End AD4EventoAction: call children actions

//EVENTI Record @56-B263446D
    final class EVENTIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End EVENTI Record

//EVENTI Record: method perform @56-62F5DE11
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4Evento" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End EVENTI Record: method perform

//EVENTI Record: children actions @56-11245F7E
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("EVENTI".equals(formName)) {
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
//End EVENTI Record: children actions

//EVENTI Record: method perform tail @56-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End EVENTI Record: method perform tail

//Button_Update Button @179-ACDB8F95
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Evento" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @58-442274D3
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Evento" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @60-32ABEA9D
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Evento" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //EVENTI Record: method read @56-7F8AAE5A

//EVENTI Record: method read head @56-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End EVENTI Record: method read head

//EVENTI Record: init DataSource @56-ECE8B854
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            EVENTIDataObject ds = new EVENTIDataObject(page);
            ds.setComponent( model );
            ds.setUrlID_EVENTO( page.getHttpGetParams().getParameter("ID_EVENTO") );
            try {
                ds.setSesAD4ACCERIPR( SessionStorage.getInstance(req).getAttribute("AD4ACCERIPR"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'AD4ACCERIPR'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'AD4ACCERIPR'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End EVENTI Record: init DataSource

//EVENTI Record: check errors @56-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End EVENTI Record: check errors

} //EVENTI Record: method read tail @56-FCB6E20C

//EVENTI Record: bind @56-8068357B
            public void bind(com.codecharge.components.Component model, EVENTIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("ID_EVENTO").setValue(row.getID_EVENTO());
                model.getControl("DATA").setValue(row.getDATA());
                model.getControl("DESC_TIPO").setValue(row.getDESC_TIPO());
                model.getControl("TESTO_FILE").setValue(row.getTESTO_FILE());
                model.getControl("NOMINATIVO").setValue(row.getNOMINATIVO());
                model.getControl("DB_USER").setValue(row.getDB_USER());
                model.getControl("ISTANZA_DESC").setValue(row.getISTANZA_DESC());
                model.getControl("MODULO_DESC").setValue(row.getMODULO_DESC());
                model.getControl("DESC_GRAVITA").setValue(row.getDESC_GRAVITA());
                model.getControl("DESC_STATO").setValue(row.getDESC_STATO());
                model.getControl("ANNOTAZIONI").setValue(row.getANNOTAZIONI());
                if ( this.valid ) {
                    model.getControl("ID_EVENTO_HIDDEN").setValue(row.getID_EVENTO_HIDDEN());
                    model.getControl("TIPO").setValue(row.getTIPO());
                    model.getControl("CHECK_FILE_LOCATOR").setValue(row.getCHECK_FILE_LOCATOR());
                    model.getControl("RIPRISTINATI").setValue(row.getRIPRISTINATI());
                }
            }
//End EVENTI Record: bind

//EVENTI Record: getRowFieldByName @56-5D5E15E1
            public Object getRowFieldByName( String name, EVENTIRow row ) {
                Object value = null;
                if ( "ID_EVENTO".equals(name) ) {
                    value = row.getID_EVENTO();
                } else if ( "DATA".equals(name) ) {
                    value = row.getDATA();
                } else if ( "ID_EVENTO_HIDDEN".equals(name) ) {
                    value = row.getID_EVENTO_HIDDEN();
                } else if ( "DESC_TIPO".equals(name) ) {
                    value = row.getDESC_TIPO();
                } else if ( "TIPO".equals(name) ) {
                    value = row.getTIPO();
                } else if ( "TESTO_FILE".equals(name) ) {
                    value = row.getTESTO_FILE();
                } else if ( "CHECK_FILE_LOCATOR".equals(name) ) {
                    value = row.getCHECK_FILE_LOCATOR();
                } else if ( "NOMINATIVO".equals(name) ) {
                    value = row.getNOMINATIVO();
                } else if ( "RIPRISTINATI".equals(name) ) {
                    value = row.getRIPRISTINATI();
                } else if ( "DB_USER".equals(name) ) {
                    value = row.getDB_USER();
                } else if ( "ISTANZA_DESC".equals(name) ) {
                    value = row.getISTANZA_DESC();
                } else if ( "MODULO_DESC".equals(name) ) {
                    value = row.getMODULO_DESC();
                } else if ( "DESC_GRAVITA".equals(name) ) {
                    value = row.getDESC_GRAVITA();
                } else if ( "DESC_STATO".equals(name) ) {
                    value = row.getDESC_STATO();
                } else if ( "ANNOTAZIONI".equals(name) ) {
                    value = row.getANNOTAZIONI();
                }
                return value;
            }
//End EVENTI Record: getRowFieldByName

void InsertAction() { //EVENTI Record: method insert @56-11643485

//EVENTI Record: components insert actions @56-68525650
            if (! model.hasErrors()) {
            }
//End EVENTI Record: components insert actions

} //EVENTI Record: method insert tail @56-FCB6E20C

void UpdateAction() { //EVENTI Record: method update @56-5771D0AA

//EVENTI Record: method update head @56-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End EVENTI Record: method update head

//EVENTI Record: method update body @56-E1589DFE
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            EVENTIDataObject ds = new EVENTIDataObject(page);
            ds.setComponent( model );
            EVENTIRow row = new EVENTIRow();
            ds.setRow(row);
            try {
                ds.setPostID_EVENTO_HIDDEN( page.getHttpPostParams().getParameter("ID_EVENTO_HIDDEN"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_EVENTO_HIDDEN'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_EVENTO_HIDDEN'" );
            }
//End EVENTI Record: method update body

//EVENTI Record: ds.update @56-6E956EDC
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
//End EVENTI Record: ds.update

} //EVENTI Record: method update tail @56-FCB6E20C

void DeleteAction() { //EVENTI Record: method delete @56-11FC2E1E

//EVENTI Record: method delete head @56-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End EVENTI Record: method delete head

//EVENTI Record: method delete body @56-55000766
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            EVENTIDataObject ds = new EVENTIDataObject(page);
            ds.setComponent( model );
            EVENTIRow row = new EVENTIRow();
            ds.setRow(row);
            try {
                ds.setPostID_EVENTO_HIDDEN( page.getHttpPostParams().getParameter("ID_EVENTO_HIDDEN"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_EVENTO_HIDDEN'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_EVENTO_HIDDEN'" );
            }
//End EVENTI Record: method delete body

//EVENTI Record: ds.delete @56-3584344F
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
//End EVENTI Record: ds.delete

} //EVENTI Record: method delete tail @56-FCB6E20C

//EVENTI Record: method validate @56-0B22B321
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden ID_EVENTO_HIDDEN = (com.codecharge.components.Hidden) model.getChild( "ID_EVENTO_HIDDEN" );
            if (! ID_EVENTO_HIDDEN.validate()) { isControlError = true; }

            com.codecharge.components.Hidden TIPO = (com.codecharge.components.Hidden) model.getChild( "TIPO" );
            if (! TIPO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CHECK_FILE_LOCATOR = (com.codecharge.components.Hidden) model.getChild( "CHECK_FILE_LOCATOR" );
            if (! CHECK_FILE_LOCATOR.validate()) { isControlError = true; }

            com.codecharge.components.Hidden RIPRISTINATI = (com.codecharge.components.Hidden) model.getChild( "RIPRISTINATI" );
            if (! RIPRISTINATI.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End EVENTI Record: method validate

//EVENTI Record Tail @56-FCB6E20C
    }
//End EVENTI Record Tail

//AD4Evento Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Evento Page: method validate

//AD4EventoAction Tail @1-FCB6E20C
}
//End AD4EventoAction Tail
