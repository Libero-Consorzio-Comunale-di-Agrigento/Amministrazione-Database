//AD4CopiaUtentiAction imports @1-4E6F5C4F
package ad4web.AD4CopiaUtenti;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4CopiaUtentiAction imports

//AD4CopiaUtentiAction class @1-B39C83CC
public class AD4CopiaUtentiAction extends Action {

//End AD4CopiaUtentiAction class

//AD4CopiaUtentiAction: method perform @1-64821EB9
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4CopiaUtentiModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4CopiaUtentiModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4CopiaUtentiAction: method perform

//AD4CopiaUtentiAction: call children actions @1-D2B3291B
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
            AD4_CARATTERISTICHE_ACCESSOClass AD4_CARATTERISTICHE_ACCESSO = new AD4_CARATTERISTICHE_ACCESSOClass();
            if ( ( redirect = AD4_CARATTERISTICHE_ACCESSO.perform( page.getRecord("AD4_CARATTERISTICHE_ACCESSO")) ) != null ) result = redirect;
        }
        if (result == null) {
            invalidPwdLogClass invalidPwdLog = new invalidPwdLogClass();
            invalidPwdLog.perform(page.getGrid("invalidPwdLog"));
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
//End AD4CopiaUtentiAction: call children actions

//AD4_CARATTERISTICHE_ACCESSO Record @38-4DCE4A48
    final class AD4_CARATTERISTICHE_ACCESSOClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_CARATTERISTICHE_ACCESSO Record

//AD4_CARATTERISTICHE_ACCESSO Record: method perform @38-4F85605F
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_CARATTERISTICHE_ACCESSO Record: method perform

//AD4_CARATTERISTICHE_ACCESSO Record: children actions @38-806144CF
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_CARATTERISTICHE_ACCESSO".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("DettagliShow") != null) {
                        if (validate()) {
                            DettagliShowAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("DettagliHide") != null) {
                        if (validate()) {
                            DettagliHideAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
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
                    if (page.getParameter("DettagliShow") != null) {
                        if (validate()) {
                            DettagliShowAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("DettagliHide") != null) {
                        if (validate()) {
                            DettagliHideAction();
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
            readACCESSO(model.getListBox("ACCESSO"));
            readTRACCIA(model.getListBox("TRACCIA"));
//End AD4_CARATTERISTICHE_ACCESSO Record: children actions

//AD4_CARATTERISTICHE_ACCESSO Record: method perform tail @38-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_CARATTERISTICHE_ACCESSO Record: method perform tail

//DettagliShow Button @221-A517428D
        void DettagliShowAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DettagliShow");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End DettagliShow Button

//DettagliHide Button @236-64303FE7
        void DettagliHideAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DettagliHide");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End DettagliHide Button

//Button_Update Button @40-0BF11DCD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @41-8FBA8FA8
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @43-3175CAFE
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "../common/AmvRedirect" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @206-21C0B98A
        protected void readACCESSO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "I;Inibito;U;Unico;S;Segnalato;L;Libero" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @143-29846A33
        protected void readTRACCIA(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "M;Modulo;F;Funzione;I;Istruzione" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

void read() { //AD4_CARATTERISTICHE_ACCESSO Record: method read @38-7F8AAE5A

//AD4_CARATTERISTICHE_ACCESSO Record: method read head @38-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_CARATTERISTICHE_ACCESSO Record: method read head

//AD4_CARATTERISTICHE_ACCESSO Record: init DataSource @38-3003341A
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_CARATTERISTICHE_ACCESSODataObject ds = new AD4_CARATTERISTICHE_ACCESSODataObject(page);
            ds.setComponent( model );
            ds.setUrlUTENTE( page.getHttpGetParams().getParameter("UTENTE") );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setUrlTIPO_ACCESSO( page.getHttpGetParams().getParameter("TIPO_ACCESSO") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_CARATTERISTICHE_ACCESSO Record: init DataSource

//AD4_CARATTERISTICHE_ACCESSO Record: check errors @38-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_CARATTERISTICHE_ACCESSO Record: check errors

} //AD4_CARATTERISTICHE_ACCESSO Record: method read tail @38-FCB6E20C

//AD4_CARATTERISTICHE_ACCESSO Record: bind @38-B0220343
            public void bind(com.codecharge.components.Component model, AD4_CARATTERISTICHE_ACCESSORow row ) {
                if ( model == null || row == null ) return;
                model.getControl("CAAC_DESC").setValue(row.getCAAC_DESC());
                model.getControl("DESC_ACCESSO").setValue(row.getDESC_ACCESSO());
                model.getControl("JOB_ATTIVO").setValue(row.getJOB_ATTIVO());
                model.getControl("DESC_PASSWORD").setValue(row.getDESC_PASSWORD());
                model.getControl("DESC_AUTENTICAZIONE").setValue(row.getDESC_AUTENTICAZIONE());
                model.getControl("ACCESSO_SE_DESC").setValue(row.getACCESSO_SE_DESC());
                model.getControl("LABEL_ARCH").setValue(row.getLABEL_ARCH());
                model.getControl("DESC_DETTAGLI_LABEL").setValue(row.getDESC_DETTAGLI_LABEL());
                if ( this.valid ) {
                    model.getControl("OLD_CAAC_ID").setValue(row.getOLD_CAAC_ID());
                    model.getControl("CAAC_ID").setValue(row.getCAAC_ID());
                    model.getControl("OLD_MODULO").setValue(row.getOLD_MODULO());
                    model.getControl("MODULO").setValue(row.getMODULO());
                    model.getControl("INSERT_UPDATE").setValue(row.getINSERT_UPDATE());
                    model.getControl("OLD_PROGETTO").setValue(row.getOLD_PROGETTO());
                    model.getControl("PROGETTO").setValue(row.getPROGETTO());
                    model.getControl("OLD_UTENTE").setValue(row.getOLD_UTENTE());
                    model.getControl("UTENTE").setValue(row.getUTENTE());
                    model.getControl("TIPO_ACCESSO").setValue(row.getTIPO_ACCESSO());
                    model.getControl("OLD_ISTANZA").setValue(row.getOLD_ISTANZA());
                    model.getControl("ISTANZA").setValue(row.getISTANZA());
                    model.getControl("CAAC_DESC_Hidden").setValue(row.getCAAC_DESC_Hidden());
                    model.getControl("ACCESSO_SE").setValue(row.getACCESSO_SE());
                    model.getControl("ACCESSO").setValue(row.getACCESSO());
                    model.getControl("TRACCIA").setValue(row.getTRACCIA());
                    model.getControl("GIORNI_TRACCIA").setValue(row.getGIORNI_TRACCIA());
                    model.getControl("NUM_FILE_ARCHIVIATI").setValue(row.getNUM_FILE_ARCHIVIATI());
                    model.getControl("DIM_FILE_ARCHIVIATI").setValue(row.getDIM_FILE_ARCHIVIATI());
                    model.getControl("SPOSTA_FILE_ARCH").setValue(row.getSPOSTA_FILE_ARCH());
                    model.getControl("ARCHIVIAZIONE_TRACCIA").setValue(row.getARCHIVIAZIONE_TRACCIA());
                    model.getControl("DISLOCAZIONE_TRACCIA").setValue(row.getDISLOCAZIONE_TRACCIA());
                    model.getControl("INPUT_ARCH_CLASS").setValue(row.getINPUT_ARCH_CLASS());
                    model.getControl("CKB_ARCH_CLASS").setValue(row.getCKB_ARCH_CLASS());
                    model.getControl("DISLOCAZIONE_TRACCIA_OLD").setValue(row.getDISLOCAZIONE_TRACCIA_OLD());
                    model.getControl("SLEEP").setValue(row.getSLEEP());
                    model.getControl("VALIDITA_PASSWORD").setValue(row.getVALIDITA_PASSWORD());
                    model.getControl("TENTATIVI_PASSWORD").setValue(row.getTENTATIVI_PASSWORD());
                    model.getControl("MOD_PWD_PRIMO_ACCESSO").setValue(row.getMOD_PWD_PRIMO_ACCESSO());
                    model.getControl("MIN_LUNGHEZZA_PWD").setValue(row.getMIN_LUNGHEZZA_PWD());
                    model.getControl("MIN_LUNGHEZZA_PWD_OLD").setValue(row.getMIN_LUNGHEZZA_PWD_OLD());
                    model.getControl("AMMESSI_CAR_SPECIALI_PWD").setValue(row.getAMMESSI_CAR_SPECIALI_PWD());
                    model.getControl("AMMESSI_CAR_SPECIALI_PWD_OLD").setValue(row.getAMMESSI_CAR_SPECIALI_PWD_OLD());
                    model.getControl("NUMERI_OBB_PWD").setValue(row.getNUMERI_OBB_PWD());
                    model.getControl("NUMERI_OBB_PWD_OLD").setValue(row.getNUMERI_OBB_PWD_OLD());
                    model.getControl("LDAP").setValue(row.getLDAP());
                    model.getControl("SINGLE_SIGN_ON").setValue(row.getSINGLE_SIGN_ON());
                    model.getControl("DESC_DETTAGLI").setValue(row.getDESC_DETTAGLI());
                }
            }
//End AD4_CARATTERISTICHE_ACCESSO Record: bind

//AD4_CARATTERISTICHE_ACCESSO Record: getRowFieldByName @38-DE73F07F
            public Object getRowFieldByName( String name, AD4_CARATTERISTICHE_ACCESSORow row ) {
                Object value = null;
                if ( "CAAC_DESC".equals(name) ) {
                    value = row.getCAAC_DESC();
                } else if ( "DESC_ACCESSO".equals(name) ) {
                    value = row.getDESC_ACCESSO();
                } else if ( "OLD_CAAC_ID".equals(name) ) {
                    value = row.getOLD_CAAC_ID();
                } else if ( "CAAC_ID".equals(name) ) {
                    value = row.getCAAC_ID();
                } else if ( "OLD_MODULO".equals(name) ) {
                    value = row.getOLD_MODULO();
                } else if ( "MODULO".equals(name) ) {
                    value = row.getMODULO();
                } else if ( "INSERT_UPDATE".equals(name) ) {
                    value = row.getINSERT_UPDATE();
                } else if ( "JOB_ATTIVO".equals(name) ) {
                    value = row.getJOB_ATTIVO();
                } else if ( "DESC_PASSWORD".equals(name) ) {
                    value = row.getDESC_PASSWORD();
                } else if ( "OLD_PROGETTO".equals(name) ) {
                    value = row.getOLD_PROGETTO();
                } else if ( "PROGETTO".equals(name) ) {
                    value = row.getPROGETTO();
                } else if ( "OLD_UTENTE".equals(name) ) {
                    value = row.getOLD_UTENTE();
                } else if ( "UTENTE".equals(name) ) {
                    value = row.getUTENTE();
                } else if ( "TIPO_ACCESSO".equals(name) ) {
                    value = row.getTIPO_ACCESSO();
                } else if ( "DESC_AUTENTICAZIONE".equals(name) ) {
                    value = row.getDESC_AUTENTICAZIONE();
                } else if ( "OLD_ISTANZA".equals(name) ) {
                    value = row.getOLD_ISTANZA();
                } else if ( "ISTANZA".equals(name) ) {
                    value = row.getISTANZA();
                } else if ( "CAAC_DESC_Hidden".equals(name) ) {
                    value = row.getCAAC_DESC_Hidden();
                } else if ( "ACCESSO_SE".equals(name) ) {
                    value = row.getACCESSO_SE();
                } else if ( "ACCESSO_SE_DESC".equals(name) ) {
                    value = row.getACCESSO_SE_DESC();
                } else if ( "ACCESSO".equals(name) ) {
                    value = row.getACCESSO();
                } else if ( "TRACCIA".equals(name) ) {
                    value = row.getTRACCIA();
                } else if ( "GIORNI_TRACCIA".equals(name) ) {
                    value = row.getGIORNI_TRACCIA();
                } else if ( "NUM_FILE_ARCHIVIATI".equals(name) ) {
                    value = row.getNUM_FILE_ARCHIVIATI();
                } else if ( "DIM_FILE_ARCHIVIATI".equals(name) ) {
                    value = row.getDIM_FILE_ARCHIVIATI();
                } else if ( "SPOSTA_FILE_ARCH".equals(name) ) {
                    value = row.getSPOSTA_FILE_ARCH();
                } else if ( "ARCHIVIAZIONE_TRACCIA".equals(name) ) {
                    value = row.getARCHIVIAZIONE_TRACCIA();
                } else if ( "LABEL_ARCH".equals(name) ) {
                    value = row.getLABEL_ARCH();
                } else if ( "DISLOCAZIONE_TRACCIA".equals(name) ) {
                    value = row.getDISLOCAZIONE_TRACCIA();
                } else if ( "INPUT_ARCH_CLASS".equals(name) ) {
                    value = row.getINPUT_ARCH_CLASS();
                } else if ( "CKB_ARCH_CLASS".equals(name) ) {
                    value = row.getCKB_ARCH_CLASS();
                } else if ( "DISLOCAZIONE_TRACCIA_OLD".equals(name) ) {
                    value = row.getDISLOCAZIONE_TRACCIA_OLD();
                } else if ( "SLEEP".equals(name) ) {
                    value = row.getSLEEP();
                } else if ( "VALIDITA_PASSWORD".equals(name) ) {
                    value = row.getVALIDITA_PASSWORD();
                } else if ( "TENTATIVI_PASSWORD".equals(name) ) {
                    value = row.getTENTATIVI_PASSWORD();
                } else if ( "MOD_PWD_PRIMO_ACCESSO".equals(name) ) {
                    value = row.getMOD_PWD_PRIMO_ACCESSO();
                } else if ( "MIN_LUNGHEZZA_PWD".equals(name) ) {
                    value = row.getMIN_LUNGHEZZA_PWD();
                } else if ( "MIN_LUNGHEZZA_PWD_OLD".equals(name) ) {
                    value = row.getMIN_LUNGHEZZA_PWD_OLD();
                } else if ( "AMMESSI_CAR_SPECIALI_PWD".equals(name) ) {
                    value = row.getAMMESSI_CAR_SPECIALI_PWD();
                } else if ( "AMMESSI_CAR_SPECIALI_PWD_OLD".equals(name) ) {
                    value = row.getAMMESSI_CAR_SPECIALI_PWD_OLD();
                } else if ( "NUMERI_OBB_PWD".equals(name) ) {
                    value = row.getNUMERI_OBB_PWD();
                } else if ( "NUMERI_OBB_PWD_OLD".equals(name) ) {
                    value = row.getNUMERI_OBB_PWD_OLD();
                } else if ( "LDAP".equals(name) ) {
                    value = row.getLDAP();
                } else if ( "SINGLE_SIGN_ON".equals(name) ) {
                    value = row.getSINGLE_SIGN_ON();
                } else if ( "DESC_DETTAGLI_LABEL".equals(name) ) {
                    value = row.getDESC_DETTAGLI_LABEL();
                } else if ( "DESC_DETTAGLI".equals(name) ) {
                    value = row.getDESC_DETTAGLI();
                }
                return value;
            }
//End AD4_CARATTERISTICHE_ACCESSO Record: getRowFieldByName

void InsertAction() { //AD4_CARATTERISTICHE_ACCESSO Record: method insert @38-11643485

//AD4_CARATTERISTICHE_ACCESSO Record: method insert head @38-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_CARATTERISTICHE_ACCESSO Record: method insert head

//AD4_CARATTERISTICHE_ACCESSO Record: components insert actions @38-68525650
            if (! model.hasErrors()) {
            }
//End AD4_CARATTERISTICHE_ACCESSO Record: components insert actions

} //AD4_CARATTERISTICHE_ACCESSO Record: method insert tail @38-FCB6E20C

void UpdateAction() { //AD4_CARATTERISTICHE_ACCESSO Record: method update @38-5771D0AA

//AD4_CARATTERISTICHE_ACCESSO Record: method update head @38-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_CARATTERISTICHE_ACCESSO Record: method update head

//AD4_CARATTERISTICHE_ACCESSO Record: method update body @38-B1CC0033
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_CARATTERISTICHE_ACCESSODataObject ds = new AD4_CARATTERISTICHE_ACCESSODataObject(page);
            ds.setComponent( model );
            AD4_CARATTERISTICHE_ACCESSORow row = new AD4_CARATTERISTICHE_ACCESSORow();
            ds.setRow(row);
            ds.setPostINSERT_UPDATE( page.getHttpPostParams().getParameter("INSERT_UPDATE") );
            try {
                ds.setPostOLD_CAAC_ID( page.getHttpPostParams().getParameter("OLD_CAAC_ID"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'OLD_CAAC_ID'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'OLD_CAAC_ID'" );
            }
            ds.setPostOLD_PROGETTO( page.getHttpPostParams().getParameter("OLD_PROGETTO") );
            ds.setPostOLD_ISTANZA( page.getHttpPostParams().getParameter("OLD_ISTANZA") );
            ds.setPostOLD_MODULO( page.getHttpPostParams().getParameter("OLD_MODULO") );
            ds.setPostOLD_UTENTE( page.getHttpPostParams().getParameter("OLD_UTENTE") );
            try {
                ds.setPostCAAC_ID( page.getHttpPostParams().getParameter("CAAC_ID"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'CAAC_ID'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'CAAC_ID'" );
            }
            ds.setPostTIPO_ACCESSO( page.getHttpPostParams().getParameter("TIPO_ACCESSO") );
            ds.setPostPROGETTO( page.getHttpPostParams().getParameter("PROGETTO") );
            ds.setPostISTANZA( page.getHttpPostParams().getParameter("ISTANZA") );
            ds.setPostMODULO( page.getHttpPostParams().getParameter("MODULO") );
            ds.setPostUTENTE( page.getHttpPostParams().getParameter("UTENTE") );
            row.setACCESSO(Utils.convertToString(model.getControl("ACCESSO").getValue()));
            row.setACCESSO_SE(Utils.convertToString(model.getControl("ACCESSO_SE").getValue()));
            row.setTRACCIA(Utils.convertToString(model.getControl("TRACCIA").getValue()));
            try {
                ds.setPostGIORNI_TRACCIA( page.getHttpPostParams().getParameter("GIORNI_TRACCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'GIORNI_TRACCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'GIORNI_TRACCIA'" );
            }
            try {
                ds.setPostTENTATIVI_PASSWORD( page.getHttpPostParams().getParameter("TENTATIVI_PASSWORD"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'TENTATIVI_PASSWORD'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'TENTATIVI_PASSWORD'" );
            }
            try {
                ds.setPostVALIDITA_PASSWORD( page.getHttpPostParams().getParameter("VALIDITA_PASSWORD"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'VALIDITA_PASSWORD'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'VALIDITA_PASSWORD'" );
            }
            try {
                ds.setUrlSLEEP( page.getHttpGetParams().getParameter("SLEEP"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SLEEP'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SLEEP'" );
            }
            ds.setPostSINGLE_SIGN_ON( page.getHttpPostParams().getParameter("SINGLE_SIGN_ON") );
            row.setLDAP(Utils.convertToString(model.getControl("LDAP").getValue()));
            try {
                ds.setPostMIN_LUNGHEZZA_PWD( page.getHttpPostParams().getParameter("MIN_LUNGHEZZA_PWD"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'MIN_LUNGHEZZA_PWD'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'MIN_LUNGHEZZA_PWD'" );
            }
            row.setMOD_PWD_PRIMO_ACCESSO(Utils.convertToString(model.getControl("MOD_PWD_PRIMO_ACCESSO").getValue()));
            row.setARCHIVIAZIONE_TRACCIA(Utils.convertToString(model.getControl("ARCHIVIAZIONE_TRACCIA").getValue()));
            ds.setPostDISLOCAZIONE_TRACCIA( page.getHttpPostParams().getParameter("DISLOCAZIONE_TRACCIA") );
            row.setAMMESSI_CAR_SPECIALI_PWD(Utils.convertToString(model.getControl("AMMESSI_CAR_SPECIALI_PWD").getValue()));
            row.setNUMERI_OBB_PWD(Utils.convertToString(model.getControl("NUMERI_OBB_PWD").getValue()));
            try {
                ds.setPostSPOSTA_FILE_ARCH( page.getHttpPostParams().getParameter("SPOSTA_FILE_ARCH"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SPOSTA_FILE_ARCH'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SPOSTA_FILE_ARCH'" );
            }
//End AD4_CARATTERISTICHE_ACCESSO Record: method update body

//AD4_CARATTERISTICHE_ACCESSO Record: ds.update @38-6E956EDC
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
//End AD4_CARATTERISTICHE_ACCESSO Record: ds.update

} //AD4_CARATTERISTICHE_ACCESSO Record: method update tail @38-FCB6E20C

void DeleteAction() { //AD4_CARATTERISTICHE_ACCESSO Record: method delete @38-11FC2E1E

//AD4_CARATTERISTICHE_ACCESSO Record: method delete head @38-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_CARATTERISTICHE_ACCESSO Record: method delete head

//AD4_CARATTERISTICHE_ACCESSO Record: method delete body @38-5E88EE06
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_CARATTERISTICHE_ACCESSODataObject ds = new AD4_CARATTERISTICHE_ACCESSODataObject(page);
            ds.setComponent( model );
            AD4_CARATTERISTICHE_ACCESSORow row = new AD4_CARATTERISTICHE_ACCESSORow();
            ds.setRow(row);
            try {
                ds.setPostOLD_CAAC_ID( page.getHttpPostParams().getParameter("OLD_CAAC_ID"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'OLD_CAAC_ID'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'OLD_CAAC_ID'" );
            }
//End AD4_CARATTERISTICHE_ACCESSO Record: method delete body

//AD4_CARATTERISTICHE_ACCESSO Record: ds.delete @38-3584344F
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
//End AD4_CARATTERISTICHE_ACCESSO Record: ds.delete

} //AD4_CARATTERISTICHE_ACCESSO Record: method delete tail @38-FCB6E20C

//AD4_CARATTERISTICHE_ACCESSO Record: method validate @38-55EC15FA
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden OLD_CAAC_ID = (com.codecharge.components.Hidden) model.getChild( "OLD_CAAC_ID" );
            if (! OLD_CAAC_ID.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CAAC_ID = (com.codecharge.components.Hidden) model.getChild( "CAAC_ID" );
            if (! CAAC_ID.validate()) { isControlError = true; }

            com.codecharge.components.Hidden OLD_MODULO = (com.codecharge.components.Hidden) model.getChild( "OLD_MODULO" );
            if (! OLD_MODULO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden MODULO = (com.codecharge.components.Hidden) model.getChild( "MODULO" );
            if (! MODULO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden INSERT_UPDATE = (com.codecharge.components.Hidden) model.getChild( "INSERT_UPDATE" );
            if (! INSERT_UPDATE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden OLD_PROGETTO = (com.codecharge.components.Hidden) model.getChild( "OLD_PROGETTO" );
            if (! OLD_PROGETTO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROGETTO = (com.codecharge.components.Hidden) model.getChild( "PROGETTO" );
            if (! PROGETTO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden OLD_UTENTE = (com.codecharge.components.Hidden) model.getChild( "OLD_UTENTE" );
            if (! OLD_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden UTENTE = (com.codecharge.components.Hidden) model.getChild( "UTENTE" );
            if (! UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden TIPO_ACCESSO = (com.codecharge.components.Hidden) model.getChild( "TIPO_ACCESSO" );
            if (! TIPO_ACCESSO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden OLD_ISTANZA = (com.codecharge.components.Hidden) model.getChild( "OLD_ISTANZA" );
            if (! OLD_ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ISTANZA = (com.codecharge.components.Hidden) model.getChild( "ISTANZA" );
            if (! ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CAAC_DESC_Hidden = (com.codecharge.components.Hidden) model.getChild( "CAAC_DESC_Hidden" );
            if (! CAAC_DESC_Hidden.validate()) { isControlError = true; }

            com.codecharge.components.ListBox ACCESSO = (com.codecharge.components.ListBox) model.getChild( "ACCESSO" );
            if (! ACCESSO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox TRACCIA = (com.codecharge.components.ListBox) model.getChild( "TRACCIA" );
            if (! TRACCIA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox GIORNI_TRACCIA = (com.codecharge.components.TextBox) model.getChild( "GIORNI_TRACCIA" );
            if (! GIORNI_TRACCIA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden NUM_FILE_ARCHIVIATI = (com.codecharge.components.Hidden) model.getChild( "NUM_FILE_ARCHIVIATI" );
            if (! NUM_FILE_ARCHIVIATI.validate()) { isControlError = true; }

            com.codecharge.components.Hidden DIM_FILE_ARCHIVIATI = (com.codecharge.components.Hidden) model.getChild( "DIM_FILE_ARCHIVIATI" );
            if (! DIM_FILE_ARCHIVIATI.validate()) { isControlError = true; }

            com.codecharge.components.Hidden SPOSTA_FILE_ARCH = (com.codecharge.components.Hidden) model.getChild( "SPOSTA_FILE_ARCH" );
            if (! SPOSTA_FILE_ARCH.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DISLOCAZIONE_TRACCIA = (com.codecharge.components.TextBox) model.getChild( "DISLOCAZIONE_TRACCIA" );
            if (! DISLOCAZIONE_TRACCIA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden INPUT_ARCH_CLASS = (com.codecharge.components.Hidden) model.getChild( "INPUT_ARCH_CLASS" );
            if (! INPUT_ARCH_CLASS.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CKB_ARCH_CLASS = (com.codecharge.components.Hidden) model.getChild( "CKB_ARCH_CLASS" );
            if (! CKB_ARCH_CLASS.validate()) { isControlError = true; }

            com.codecharge.components.Hidden DISLOCAZIONE_TRACCIA_OLD = (com.codecharge.components.Hidden) model.getChild( "DISLOCAZIONE_TRACCIA_OLD" );
            if (! DISLOCAZIONE_TRACCIA_OLD.validate()) { isControlError = true; }

            com.codecharge.components.TextBox SLEEP = (com.codecharge.components.TextBox) model.getChild( "SLEEP" );
            if (! SLEEP.validate()) { isControlError = true; }

            com.codecharge.components.TextBox VALIDITA_PASSWORD = (com.codecharge.components.TextBox) model.getChild( "VALIDITA_PASSWORD" );
            if (! VALIDITA_PASSWORD.validate()) { isControlError = true; }

            com.codecharge.components.TextBox TENTATIVI_PASSWORD = (com.codecharge.components.TextBox) model.getChild( "TENTATIVI_PASSWORD" );
            if (! TENTATIVI_PASSWORD.validate()) { isControlError = true; }

            com.codecharge.components.TextBox MIN_LUNGHEZZA_PWD = (com.codecharge.components.TextBox) model.getChild( "MIN_LUNGHEZZA_PWD" );
            if (! MIN_LUNGHEZZA_PWD.validate()) { isControlError = true; }

            com.codecharge.components.Hidden MIN_LUNGHEZZA_PWD_OLD = (com.codecharge.components.Hidden) model.getChild( "MIN_LUNGHEZZA_PWD_OLD" );
            if (! MIN_LUNGHEZZA_PWD_OLD.validate()) { isControlError = true; }

            com.codecharge.components.Hidden AMMESSI_CAR_SPECIALI_PWD_OLD = (com.codecharge.components.Hidden) model.getChild( "AMMESSI_CAR_SPECIALI_PWD_OLD" );
            if (! AMMESSI_CAR_SPECIALI_PWD_OLD.validate()) { isControlError = true; }

            com.codecharge.components.Hidden NUMERI_OBB_PWD_OLD = (com.codecharge.components.Hidden) model.getChild( "NUMERI_OBB_PWD_OLD" );
            if (! NUMERI_OBB_PWD_OLD.validate()) { isControlError = true; }

            com.codecharge.components.Hidden SINGLE_SIGN_ON = (com.codecharge.components.Hidden) model.getChild( "SINGLE_SIGN_ON" );
            if (! SINGLE_SIGN_ON.validate()) { isControlError = true; }

            com.codecharge.components.Hidden DESC_DETTAGLI = (com.codecharge.components.Hidden) model.getChild( "DESC_DETTAGLI" );
            if (! DESC_DETTAGLI.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_CARATTERISTICHE_ACCESSO Record: method validate

//AD4_CARATTERISTICHE_ACCESSO Record Tail @38-FCB6E20C
    }
//End AD4_CARATTERISTICHE_ACCESSO Record Tail

//invalidPwdLog Grid @245-62F04AAC
    final class invalidPwdLogClass {
        com.codecharge.components.Grid model;
        Event e;
//End invalidPwdLog Grid

//invalidPwdLog Grid: method perform @245-B48879D3
        protected String perform(com.codecharge.components.Grid model) {
            if ( ! model.isVisible() ) { return null; }
            this.model = model;
            //e = new ActionEvent( model, page );
            setProperties( model, Action.GET );
            setActivePermissions( model );
            if ( ! model.isVisible() ) return null;
            read();
            return null;
        }
//End invalidPwdLog Grid: method perform

//invalidPwdLog Grid: method read: head @245-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End invalidPwdLog Grid: method read: head

//invalidPwdLog Grid: method read: init @245-9FC132A6
            if ( ! model.allowRead ) return true;
            invalidPwdLogDataObject ds = new invalidPwdLogDataObject(page);
            ds.setComponent( model );
//End invalidPwdLog Grid: method read: init

//invalidPwdLog Grid: set grid properties @245-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End invalidPwdLog Grid: set grid properties

//invalidPwdLog Grid: retrieve data @245-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End invalidPwdLog Grid: retrieve data

//invalidPwdLog Grid: check errors @245-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End invalidPwdLog Grid: check errors

//invalidPwdLog Grid: method read: tail @245-F575E732
            return ( ! isErrors );
        }
//End invalidPwdLog Grid: method read: tail

//invalidPwdLog Grid: method bind @245-08DA2E6A
        public void bind(com.codecharge.components.Component model, invalidPwdLogRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            invalidPwdLogRow row = null;
            while ( counter < rows.length && rows[counter] != null ) {
                row = rows[counter++];
                HashMap hashRow = null;
                com.codecharge.components.Control c = null;
                boolean isNew = false;
                if ( childRows.hasNext() ) {
                    hashRow = (HashMap) childRows.next();
                    if ( hashRow == null ) {
                        hashRow = new HashMap();
                        isNew = true;
                    }
                } else {
                    hashRow = new HashMap();
                    isNew = true;
                }

                c = (com.codecharge.components.Control) hashRow.get("INVALID_PWD_LOG");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("INVALID_PWD_LOG").clone();
                    c.setValue(row.getINVALID_PWD_LOG());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End invalidPwdLog Grid: method bind

//invalidPwdLog Directory: getRowFieldByName @245-B98F2A88
        public Object getRowFieldByName( String name, invalidPwdLogRow row ) {
            Object value = null;
            if ( "INVALID_PWD_LOG".equals(name) ) {
                value = row.getINVALID_PWD_LOG();
            }
            return value;
        }
//End invalidPwdLog Directory: getRowFieldByName

//invalidPwdLog Grid: method validate @245-104025BA
        boolean validate() {
            return true;
        }
//End invalidPwdLog Grid: method validate

//invalidPwdLog Grid Tail @245-FCB6E20C
    }
//End invalidPwdLog Grid Tail

//AD4CopiaUtenti Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4CopiaUtenti Page: method validate

//AD4CopiaUtentiAction Tail @1-FCB6E20C
}
//End AD4CopiaUtentiAction Tail
