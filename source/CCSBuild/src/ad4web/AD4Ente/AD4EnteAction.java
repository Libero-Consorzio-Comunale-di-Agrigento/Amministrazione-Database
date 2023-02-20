//AD4EnteAction imports @1-0058CCBA
package ad4web.AD4Ente;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4EnteAction imports

//AD4EnteAction class @1-BAE85432
public class AD4EnteAction extends Action {

//End AD4EnteAction class

//AD4EnteAction: method perform @1-CE9F5551
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4EnteModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4EnteModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4EnteAction: method perform

//AD4EnteAction: call children actions @1-54D9A188
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
            AD4_ENTIClass AD4_ENTI = new AD4_ENTIClass();
            if ( ( redirect = AD4_ENTI.perform( page.getRecord("AD4_ENTI")) ) != null ) result = redirect;
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
//End AD4EnteAction: call children actions

//AD4_ENTI Record @6-8B43FB7F
    final class AD4_ENTIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_ENTI Record

//AD4_ENTI Record: method perform @6-B8D62D4A
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4Ente" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_ENTI Record: method perform

//AD4_ENTI Record: children actions @6-5784E257
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_ENTI".equals(formName)) {
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
//End AD4_ENTI Record: children actions

//AD4_ENTI Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_ENTI Record: method perform tail

//Button_Update Button @8-058B4A63
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Ente" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @9-068E5A0C
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Ente" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @11-7DA3CB96
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Ente" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //AD4_ENTI Record: method read @6-7F8AAE5A

//AD4_ENTI Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_ENTI Record: method read head

//AD4_ENTI Record: init DataSource @6-2E20F473
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_ENTIDataObject ds = new AD4_ENTIDataObject(page);
            ds.setComponent( model );
            ds.setUrlENTE( page.getHttpGetParams().getParameter("ENTE") );
            ds.setUrlSE_NUOVO( page.getHttpGetParams().getParameter("SE_NUOVO") );
            try {
                ds.setUrlSOGGETTO( page.getHttpGetParams().getParameter("SOGGETTO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            }
            ds.setSesAD4ENTE( SessionStorage.getInstance(req).getAttribute("AD4ENTE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_ENTI Record: init DataSource

//AD4_ENTI Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_ENTI Record: check errors

} //AD4_ENTI Record: method read tail @6-FCB6E20C

//AD4_ENTI Record: bind @6-07945E6F
            public void bind(com.codecharge.components.Component model, AD4_ENTIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("SVUOTA_REG_ANAGRAFICA").setValue(row.getSVUOTA_REG_ANAGRAFICA());
                model.getControl("MOD_REGISTRAZIONE_ANAGRAFICA").setValue(row.getMOD_REGISTRAZIONE_ANAGRAFICA());
                model.getControl("ENTE").setValue(row.getENTE());
                model.getControl("TITOLO_SOGG").setValue(row.getTITOLO_SOGG());
                model.getControl("DATI_SOGGETTO").setValue(row.getDATI_SOGGETTO());
                model.getLink("MOD_REGISTRAZIONE_ANAGRAFICA").getParameter("s_ENTE").setValue( getRowFieldByName(model.getLink("MOD_REGISTRAZIONE_ANAGRAFICA").getParameter("s_ENTE").getSourceName(), row ));
                if ( this.valid ) {
                    model.getControl("ENTE_ORIG").setValue(row.getENTE_ORIG());
                    model.getControl("SOGGETTO").setValue(row.getSOGGETTO());
                    model.getControl("DESCRIZIONE").setValue(row.getDESCRIZIONE());
                    model.getControl("BITMAP").setValue(row.getBITMAP());
                    model.getControl("DISEGNO").setValue(row.getDISEGNO());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End AD4_ENTI Record: bind

//AD4_ENTI Record: getRowFieldByName @6-328AE77C
            public Object getRowFieldByName( String name, AD4_ENTIRow row ) {
                Object value = null;
                if ( "SVUOTA_REG_ANAGRAFICA".equals(name) ) {
                    value = row.getSVUOTA_REG_ANAGRAFICA();
                } else if ( "MOD_REGISTRAZIONE_ANAGRAFICA".equals(name) ) {
                    value = row.getMOD_REGISTRAZIONE_ANAGRAFICA();
                } else if ( "ENTE".equals(name) ) {
                    value = row.getENTE();
                } else if ( "ENTE_ORIG".equals(name) ) {
                    value = row.getENTE_ORIG();
                } else if ( "SOGGETTO".equals(name) ) {
                    value = row.getSOGGETTO();
                } else if ( "DESCRIZIONE".equals(name) ) {
                    value = row.getDESCRIZIONE();
                } else if ( "BITMAP".equals(name) ) {
                    value = row.getBITMAP();
                } else if ( "DISEGNO".equals(name) ) {
                    value = row.getDISEGNO();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                } else if ( "TITOLO_SOGG".equals(name) ) {
                    value = row.getTITOLO_SOGG();
                } else if ( "DATI_SOGGETTO".equals(name) ) {
                    value = row.getDATI_SOGGETTO();
                } else if ( "s_ENTE".equals(name) ) {
                    value = row.getS_ENTE();
                }
                return value;
            }
//End AD4_ENTI Record: getRowFieldByName

void InsertAction() { //AD4_ENTI Record: method insert @6-11643485

//AD4_ENTI Record: method insert head @6-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_ENTI Record: method insert head

//AD4_ENTI Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4_ENTI Record: components insert actions

} //AD4_ENTI Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4_ENTI Record: method update @6-5771D0AA

//AD4_ENTI Record: method update head @6-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_ENTI Record: method update head

//AD4_ENTI Record: method update body @6-81078B8A
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_ENTIDataObject ds = new AD4_ENTIDataObject(page);
            ds.setComponent( model );
            AD4_ENTIRow row = new AD4_ENTIRow();
            ds.setRow(row);
            ds.setPostENTE( page.getHttpPostParams().getParameter("ENTE") );
            ds.setPostENTE_ORIG( page.getHttpPostParams().getParameter("ENTE_ORIG") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            ds.setPostBITMAP( page.getHttpPostParams().getParameter("BITMAP") );
            ds.setPostDISEGNO( page.getHttpPostParams().getParameter("DISEGNO") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            try {
                ds.setPostSOGGETTO( page.getHttpPostParams().getParameter("SOGGETTO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            }
//End AD4_ENTI Record: method update body

//AD4_ENTI Record: ds.update @6-6E956EDC
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
//End AD4_ENTI Record: ds.update

} //AD4_ENTI Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4_ENTI Record: method delete @6-11FC2E1E

//AD4_ENTI Record: method delete head @6-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_ENTI Record: method delete head

//AD4_ENTI Record: method delete body @6-C0F92058
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_ENTIDataObject ds = new AD4_ENTIDataObject(page);
            ds.setComponent( model );
            AD4_ENTIRow row = new AD4_ENTIRow();
            ds.setRow(row);
            ds.setPostENTE( page.getHttpPostParams().getParameter("ENTE") );
//End AD4_ENTI Record: method delete body

//AD4_ENTI Record: ds.delete @6-3584344F
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
//End AD4_ENTI Record: ds.delete

} //AD4_ENTI Record: method delete tail @6-FCB6E20C

//AD4_ENTI Record: method validate @6-85B2EA9B
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden ENTE_ORIG = (com.codecharge.components.Hidden) model.getChild( "ENTE_ORIG" );
            if (! ENTE_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.Hidden SOGGETTO = (com.codecharge.components.Hidden) model.getChild( "SOGGETTO" );
            if (! SOGGETTO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "DESCRIZIONE" );
            if (! DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox BITMAP = (com.codecharge.components.TextBox) model.getChild( "BITMAP" );
            if (! BITMAP.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DISEGNO = (com.codecharge.components.TextBox) model.getChild( "DISEGNO" );
            if (! DISEGNO.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_ENTI Record: method validate

//AD4_ENTI Record Tail @6-FCB6E20C
    }
//End AD4_ENTI Record Tail

//AD4Ente Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Ente Page: method validate

//AD4EnteAction Tail @1-FCB6E20C
}
//End AD4EnteAction Tail

