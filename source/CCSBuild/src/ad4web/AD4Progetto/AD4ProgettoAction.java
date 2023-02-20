//AD4ProgettoAction imports @1-507E654E
package ad4web.AD4Progetto;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4ProgettoAction imports

//AD4ProgettoAction class @1-CF5A0BD5
public class AD4ProgettoAction extends Action {

//End AD4ProgettoAction class

//AD4ProgettoAction: method perform @1-F542681B
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4ProgettoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4ProgettoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4ProgettoAction: method perform

//AD4ProgettoAction: call children actions @1-7283D87E
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
            AD4_PROGETTIClass AD4_PROGETTI = new AD4_PROGETTIClass();
            if ( ( redirect = AD4_PROGETTI.perform( page.getRecord("AD4_PROGETTI")) ) != null ) result = redirect;
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
//End AD4ProgettoAction: call children actions

//AD4_PROGETTI Record @23-A902C8BA
    final class AD4_PROGETTIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_PROGETTI Record

//AD4_PROGETTI Record: method perform @23-85229883
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4Progetto" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_PROGETTI Record: method perform

//AD4_PROGETTI Record: children actions @23-147902BB
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_PROGETTI".equals(formName)) {
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
//End AD4_PROGETTI Record: children actions

//AD4_PROGETTI Record: method perform tail @23-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_PROGETTI Record: method perform tail

//Button_Update Button @25-1882C0F6
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Progetto" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @26-517B46AF
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4ProgettiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @28-042FF819
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4ProgettiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //AD4_PROGETTI Record: method read @23-7F8AAE5A

//AD4_PROGETTI Record: method read head @23-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_PROGETTI Record: method read head

//AD4_PROGETTI Record: init DataSource @23-F40E06AD
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_PROGETTIDataObject ds = new AD4_PROGETTIDataObject(page);
            ds.setComponent( model );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setUrlSE_NUOVO( page.getHttpGetParams().getParameter("SE_NUOVO") );
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            ds.setPostPROGETTO( page.getHttpPostParams().getParameter("PROGETTO") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            try {
                ds.setPostPRIORITA( page.getHttpPostParams().getParameter("PRIORITA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PRIORITA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PRIORITA'" );
            }
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_PROGETTI Record: init DataSource

//AD4_PROGETTI Record: check errors @23-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_PROGETTI Record: check errors

} //AD4_PROGETTI Record: method read tail @23-FCB6E20C

//AD4_PROGETTI Record: bind @23-6857E2D5
            public void bind(com.codecharge.components.Component model, AD4_PROGETTIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("PROGETTO").setValue(row.getPROGETTO());
                model.getLink("Caratteristiche").getParameter("PROGETTO").setValue( getRowFieldByName(model.getLink("Caratteristiche").getParameter("PROGETTO").getSourceName(), row ));
                if ( this.valid ) {
                    model.getControl("PROGETTO_ORIG").setValue(row.getPROGETTO_ORIG());
                    model.getControl("DESCRIZIONE").setValue(row.getDESCRIZIONE());
                    model.getControl("PRIORITA").setValue(row.getPRIORITA());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End AD4_PROGETTI Record: bind

//AD4_PROGETTI Record: getRowFieldByName @23-72A440B1
            public Object getRowFieldByName( String name, AD4_PROGETTIRow row ) {
                Object value = null;
                if ( "Caratteristiche".equals(name) ) {
                    value = row.getCaratteristiche();
                } else if ( "PROGETTO".equals(name) ) {
                    value = row.getPROGETTO();
                } else if ( "PROGETTO_ORIG".equals(name) ) {
                    value = row.getPROGETTO_ORIG();
                } else if ( "DESCRIZIONE".equals(name) ) {
                    value = row.getDESCRIZIONE();
                } else if ( "PRIORITA".equals(name) ) {
                    value = row.getPRIORITA();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                }
                return value;
            }
//End AD4_PROGETTI Record: getRowFieldByName

void InsertAction() { //AD4_PROGETTI Record: method insert @23-11643485

//AD4_PROGETTI Record: components insert actions @23-68525650
            if (! model.hasErrors()) {
            }
//End AD4_PROGETTI Record: components insert actions

} //AD4_PROGETTI Record: method insert tail @23-FCB6E20C

void UpdateAction() { //AD4_PROGETTI Record: method update @23-5771D0AA

//AD4_PROGETTI Record: method update head @23-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_PROGETTI Record: method update head

//AD4_PROGETTI Record: method update body @23-13B8169A
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_PROGETTIDataObject ds = new AD4_PROGETTIDataObject(page);
            ds.setComponent( model );
            AD4_PROGETTIRow row = new AD4_PROGETTIRow();
            ds.setRow(row);
            ds.setPostPROGETTO( page.getHttpPostParams().getParameter("PROGETTO") );
            ds.setPostPROGETTO_ORIG( page.getHttpPostParams().getParameter("PROGETTO_ORIG") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            try {
                ds.setPostPRIORITA( page.getHttpPostParams().getParameter("PRIORITA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PRIORITA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PRIORITA'" );
            }
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
//End AD4_PROGETTI Record: method update body

//AD4_PROGETTI Record: ds.update @23-6E956EDC
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
//End AD4_PROGETTI Record: ds.update

} //AD4_PROGETTI Record: method update tail @23-FCB6E20C

void DeleteAction() { //AD4_PROGETTI Record: method delete @23-11FC2E1E

//AD4_PROGETTI Record: method delete head @23-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_PROGETTI Record: method delete head

//AD4_PROGETTI Record: method delete body @23-B666F34E
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_PROGETTIDataObject ds = new AD4_PROGETTIDataObject(page);
            ds.setComponent( model );
            AD4_PROGETTIRow row = new AD4_PROGETTIRow();
            ds.setRow(row);
            ds.setPostPROGETTO( page.getHttpPostParams().getParameter("PROGETTO") );
//End AD4_PROGETTI Record: method delete body

//AD4_PROGETTI Record: ds.delete @23-3584344F
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
//End AD4_PROGETTI Record: ds.delete

} //AD4_PROGETTI Record: method delete tail @23-FCB6E20C

//AD4_PROGETTI Record: method validate @23-3194853B
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden PROGETTO_ORIG = (com.codecharge.components.Hidden) model.getChild( "PROGETTO_ORIG" );
            if (! PROGETTO_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "DESCRIZIONE" );
            if (! DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox PRIORITA = (com.codecharge.components.TextBox) model.getChild( "PRIORITA" );
            if (! PRIORITA.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_PROGETTI Record: method validate

//AD4_PROGETTI Record Tail @23-FCB6E20C
    }
//End AD4_PROGETTI Record Tail

//AD4Progetto Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Progetto Page: method validate

//AD4ProgettoAction Tail @1-FCB6E20C
}
//End AD4ProgettoAction Tail

