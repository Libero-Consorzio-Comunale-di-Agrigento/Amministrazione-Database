//AD4OmonimiaAction imports @1-0CF8F7AE
package ad4web.AD4Omonimia;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4OmonimiaAction imports

//AD4OmonimiaAction class @1-B341E330
public class AD4OmonimiaAction extends Action {

//End AD4OmonimiaAction class

//AD4OmonimiaAction: method perform @1-C7F31060
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4OmonimiaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4OmonimiaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4OmonimiaAction: method perform

//AD4OmonimiaAction: call children actions @1-AB4F2EE5
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
            OMONIMIAClass OMONIMIA = new OMONIMIAClass();
            if ( ( redirect = OMONIMIA.perform( page.getRecord("OMONIMIA")) ) != null ) result = redirect;
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
//End AD4OmonimiaAction: call children actions

//OMONIMIA Record @6-BC7E35CB
    final class OMONIMIAClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End OMONIMIA Record

//OMONIMIA Record: method perform @6-B6945194
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4Omonimia" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End OMONIMIA Record: method perform

//OMONIMIA Record: children actions @6-6560BCAE
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("OMONIMIA".equals(formName)) {
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
//End OMONIMIA Record: children actions

//OMONIMIA Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End OMONIMIA Record: method perform tail

//Button_Update Button @8-AC55F7B9
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Omonimie" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Cancel Button @11-ECCAAB94
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Omonimia" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //OMONIMIA Record: method read @6-7F8AAE5A

//OMONIMIA Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End OMONIMIA Record: method read head

//OMONIMIA Record: init DataSource @6-F04996E8
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            OMONIMIADataObject ds = new OMONIMIADataObject(page);
            ds.setComponent( model );
            ds.setUrlS_SOSIA( page.getHttpGetParams().getParameter("S_SOSIA") );
            ds.setUrlS_UTENTE( page.getHttpGetParams().getParameter("S_UTENTE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End OMONIMIA Record: init DataSource

//OMONIMIA Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End OMONIMIA Record: check errors

} //OMONIMIA Record: method read tail @6-FCB6E20C

//OMONIMIA Record: bind @6-F67C3316
            public void bind(com.codecharge.components.Component model, OMONIMIARow row ) {
                if ( model == null || row == null ) return;
                model.getControl("UTENTE").setValue(row.getUTENTE());
                model.getControl("NOMINATIVO").setValue(row.getNOMINATIVO());
                model.getControl("DATI_SOGGETTO").setValue(row.getDATI_SOGGETTO());
                model.getControl("SOSIA").setValue(row.getSOSIA());
                model.getControl("SOSIA_NOMINATIVO").setValue(row.getSOSIA_NOMINATIVO());
                model.getControl("DATI_SOGGETTO_SOSIA").setValue(row.getDATI_SOGGETTO_SOSIA());
                if ( this.valid ) {
                    model.getControl("ID_OMONIMIA").setValue(row.getID_OMONIMIA());
                    model.getControl("SCELTO_PRIMARIO").setValue(row.getSCELTO_PRIMARIO());
                    model.getControl("UNIFICATO").setValue(row.getUNIFICATO());
                    model.getControl("COPIATO").setValue(row.getCOPIATO());
                    model.getControl("P_UTENTE").setValue(row.getP_UTENTE());
                    model.getControl("P_SOSIA").setValue(row.getP_SOSIA());
                    model.getControl("s_ignorare").setValue(row.getS_ignorare());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End OMONIMIA Record: bind

//OMONIMIA Record: getRowFieldByName @6-9EBF015B
            public Object getRowFieldByName( String name, OMONIMIARow row ) {
                Object value = null;
                if ( "UTENTE".equals(name) ) {
                    value = row.getUTENTE();
                } else if ( "NOMINATIVO".equals(name) ) {
                    value = row.getNOMINATIVO();
                } else if ( "ID_OMONIMIA".equals(name) ) {
                    value = row.getID_OMONIMIA();
                } else if ( "SCELTO_PRIMARIO".equals(name) ) {
                    value = row.getSCELTO_PRIMARIO();
                } else if ( "DATI_SOGGETTO".equals(name) ) {
                    value = row.getDATI_SOGGETTO();
                } else if ( "UNIFICATO".equals(name) ) {
                    value = row.getUNIFICATO();
                } else if ( "COPIATO".equals(name) ) {
                    value = row.getCOPIATO();
                } else if ( "P_UTENTE".equals(name) ) {
                    value = row.getP_UTENTE();
                } else if ( "SOSIA".equals(name) ) {
                    value = row.getSOSIA();
                } else if ( "SOSIA_NOMINATIVO".equals(name) ) {
                    value = row.getSOSIA_NOMINATIVO();
                } else if ( "DATI_SOGGETTO_SOSIA".equals(name) ) {
                    value = row.getDATI_SOGGETTO_SOSIA();
                } else if ( "P_SOSIA".equals(name) ) {
                    value = row.getP_SOSIA();
                } else if ( "s_primario".equals(name) ) {
                    value = row.getS_primario();
                } else if ( "s_ignorare".equals(name) ) {
                    value = row.getS_ignorare();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                }
                return value;
            }
//End OMONIMIA Record: getRowFieldByName

void InsertAction() { //OMONIMIA Record: method insert @6-11643485

//OMONIMIA Record: method insert head @6-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End OMONIMIA Record: method insert head

//OMONIMIA Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End OMONIMIA Record: components insert actions

} //OMONIMIA Record: method insert tail @6-FCB6E20C

void UpdateAction() { //OMONIMIA Record: method update @6-5771D0AA

//OMONIMIA Record: method update head @6-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End OMONIMIA Record: method update head

//OMONIMIA Record: method update body @6-46CDFB3B
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            OMONIMIADataObject ds = new OMONIMIADataObject(page);
            ds.setComponent( model );
            OMONIMIARow row = new OMONIMIARow();
            ds.setRow(row);
            try {
                ds.setPostID_OMONIMIA( page.getHttpPostParams().getParameter("ID_OMONIMIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_OMONIMIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_OMONIMIA'" );
            }
            row.setP_UTENTE(Utils.convertToString(model.getControl("P_UTENTE").getValue()));
            row.setP_SOSIA(Utils.convertToString(model.getControl("P_SOSIA").getValue()));
            row.setS_primario(Utils.convertToLong(model.getControl("s_primario").getValue()));
            row.setUNIFICATO(Utils.convertToLong(model.getControl("UNIFICATO").getValue()));
            row.setCOPIATO(Utils.convertToLong(model.getControl("COPIATO").getValue()));
            row.setS_ignorare(Utils.convertToLong(model.getControl("s_ignorare").getValue()));
            row.setNOTE(Utils.convertToString(model.getControl("NOTE").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End OMONIMIA Record: method update body

//OMONIMIA Record: ds.update @6-6E956EDC
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
//End OMONIMIA Record: ds.update

} //OMONIMIA Record: method update tail @6-FCB6E20C

void DeleteAction() { //OMONIMIA Record: method delete @6-11FC2E1E

//OMONIMIA Record: method delete head @6-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End OMONIMIA Record: method delete head

//OMONIMIA Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End OMONIMIA Record: components delete actions

} //OMONIMIA Record: method delete tail @6-FCB6E20C

//OMONIMIA Record: method validate @6-F18EF336
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden ID_OMONIMIA = (com.codecharge.components.Hidden) model.getChild( "ID_OMONIMIA" );
            if (! ID_OMONIMIA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden SCELTO_PRIMARIO = (com.codecharge.components.Hidden) model.getChild( "SCELTO_PRIMARIO" );
            if (! SCELTO_PRIMARIO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden UNIFICATO = (com.codecharge.components.Hidden) model.getChild( "UNIFICATO" );
            if (! UNIFICATO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden COPIATO = (com.codecharge.components.Hidden) model.getChild( "COPIATO" );
            if (! COPIATO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden P_UTENTE = (com.codecharge.components.Hidden) model.getChild( "P_UTENTE" );
            if (! P_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden P_SOSIA = (com.codecharge.components.Hidden) model.getChild( "P_SOSIA" );
            if (! P_SOSIA.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End OMONIMIA Record: method validate

//OMONIMIA Record Tail @6-FCB6E20C
    }
//End OMONIMIA Record Tail

//AD4Omonimia Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Omonimia Page: method validate

//AD4OmonimiaAction Tail @1-FCB6E20C
}
//End AD4OmonimiaAction Tail
