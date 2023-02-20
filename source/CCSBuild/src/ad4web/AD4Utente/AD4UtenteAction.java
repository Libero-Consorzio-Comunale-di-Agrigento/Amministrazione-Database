//AD4UtenteAction imports @1-8DA66147
package ad4web.AD4Utente;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4UtenteAction imports

//AD4UtenteAction class @1-E83A97EF
public class AD4UtenteAction extends Action {

//End AD4UtenteAction class

//AD4UtenteAction: method perform @1-42028C02
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4UtenteModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4UtenteModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4UtenteAction: method perform

//AD4UtenteAction: call children actions @1-6E1DB979
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
            AD4_UTENTIClass AD4_UTENTI = new AD4_UTENTIClass();
            if ( ( redirect = AD4_UTENTI.perform( page.getRecord("AD4_UTENTI")) ) != null ) result = redirect;
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
//End AD4UtenteAction: call children actions

//AD4_UTENTI Record @6-2850471E
    final class AD4_UTENTIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_UTENTI Record

//AD4_UTENTI Record: method perform @6-BA0E4BD7
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4Utente" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_UTENTI Record: method perform

//AD4_UTENTI Record: children actions @6-A840B57E
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_UTENTI".equals(formName)) {
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
            readRINNOVO_PASSWORD(model.getListBox("RINNOVO_PASSWORD"));
            readSTATO(model.getListBox("STATO"));
            readLINGUA(model.getListBox("LINGUA"));
            readGRUPPO_LAVORO(model.getListBox("GRUPPO_LAVORO"));
//End AD4_UTENTI Record: children actions

//AD4_UTENTI Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_UTENTI Record: method perform tail

//Button_Update Button @8-0B500C24
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Utente" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @9-E3A9F762
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Utente" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @11-3CFE6648
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4Utente" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @103-ABDF2203
        protected void readRINNOVO_PASSWORD(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "SI;CON possibilità di Rinnovo;NO;SENZA possibilità di Rinnovo" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @24-6DE847C3
        protected void readSTATO(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "U;Attivo;S;Sospeso;R;Revocato" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @48-1601AF39
        protected void readLINGUA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT LINGUA, "
                        + "       DESCRIZIONE "
                        + "  FROM LINGUE_VIEW "
                        + " " );
            command.setOrder( "DESCRIZIONE" );

            model.fireBeforeBuildSelectEvent( e );




            model.fireBeforeExecuteSelectEvent( e );

            Enumeration records = null;
            if ( ! ds.hasErrors() ) {
                model.setOptions( command.getRows(), ds );
            }

            CCLogger.getInstance().debug(command.toString());

            model.fireAfterExecuteSelectEvent( e );

            ds.closeConnection();
        }
//End ListBoxAction

//ListBoxAction @31-563B7671
        protected void readGRUPPO_LAVORO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT GRUPPO_LAVORO, DESCRIZIONE "
                        + "  FROM GRUPPI_LAVORO "
                        + "UNION "
                        + "SELECT to_char(null),  "
                        + "'- -' "
                        + "  FROM DUAL "
                        + " " );
            command.setOrder( "DESCRIZIONE" );

            model.fireBeforeBuildSelectEvent( e );




            model.fireBeforeExecuteSelectEvent( e );

            Enumeration records = null;
            if ( ! ds.hasErrors() ) {
                model.setOptions( command.getRows(), ds );
            }

            CCLogger.getInstance().debug(command.toString());

            model.fireAfterExecuteSelectEvent( e );

            ds.closeConnection();
        }
//End ListBoxAction

void read() { //AD4_UTENTI Record: method read @6-7F8AAE5A

//AD4_UTENTI Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_UTENTI Record: method read head

//AD4_UTENTI Record: init DataSource @6-B7C8DDD4
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_UTENTIDataObject ds = new AD4_UTENTIDataObject(page);
            ds.setComponent( model );
            ds.setUrlUTENTE( page.getHttpGetParams().getParameter("UTENTE") );
            try {
                ds.setUrlSOGGETTO( page.getHttpGetParams().getParameter("SOGGETTO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            }
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setUrlSE_NUOVO( page.getHttpGetParams().getParameter("SE_NUOVO") );
            ds.setPostUTENTE_VIS( page.getHttpPostParams().getParameter("UTENTE_VIS") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_UTENTI Record: init DataSource

//AD4_UTENTI Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_UTENTI Record: check errors

} //AD4_UTENTI Record: method read tail @6-FCB6E20C

//AD4_UTENTI Record: bind @6-F9B43DBE
            public void bind(com.codecharge.components.Component model, AD4_UTENTIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("GENERA_PASSWORD").setValue(row.getGENERA_PASSWORD());
                model.getControl("SVUOTA_REG_ANAGRAFICA").setValue(row.getSVUOTA_REG_ANAGRAFICA());
                model.getControl("MOD_REGISTRAZIONE_ANAGRAFICA").setValue(row.getMOD_REGISTRAZIONE_ANAGRAFICA());
                model.getControl("AUTENTICAZIONE_AD4").setValue(row.getAUTENTICAZIONE_AD4());
                model.getControl("ELIMINA_CAAC").setValue(row.getELIMINA_CAAC());
                model.getControl("IS_LDAPUSER").setValue(row.getIS_LDAPUSER());
                model.getControl("UTENTE_VIS").setValue(row.getUTENTE_VIS());
                model.getControl("MODIFICA_PASSWORD").setValue(row.getMODIFICA_PASSWORD());
                model.getControl("DATA_PASSWORD").setValue(row.getDATA_PASSWORD());
                model.getControl("TITOLO_SOGG").setValue(row.getTITOLO_SOGG());
                model.getControl("DATI_SOGGETTO").setValue(row.getDATI_SOGGETTO());
                model.getControl("ULTIMO_TENTATIVO").setValue(row.getULTIMO_TENTATIVO());
                model.getControl("NUMERO_TENTATIVI").setValue(row.getNUMERO_TENTATIVI());
                model.getControl("DATA_INSERIMENTO").setValue(row.getDATA_INSERIMENTO());
                model.getControl("UTENTE_DATA_AGGIORNAMENTO").setValue(row.getUTENTE_DATA_AGGIORNAMENTO());
                model.getLink("GENERA_PASSWORD").getParameter("UTENTE").setValue( getRowFieldByName(model.getLink("GENERA_PASSWORD").getParameter("UTENTE").getSourceName(), row ));
                model.getLink("MOD_REGISTRAZIONE_ANAGRAFICA").getParameter("s_UTENTE").setValue( getRowFieldByName(model.getLink("MOD_REGISTRAZIONE_ANAGRAFICA").getParameter("s_UTENTE").getSourceName(), row ));
                if ( this.valid ) {
                    model.getControl("NOMINATIVO").setValue(row.getNOMINATIVO());
                    model.getControl("IS_SO4_USER").setValue(row.getIS_SO4_USER());
                    model.getControl("SOGGETTO").setValue(row.getSOGGETTO());
                    model.getControl("ID_UTENTE").setValue(row.getID_UTENTE());
                    model.getControl("UTENTE").setValue(row.getUTENTE());
                    model.getControl("PASSWORD").setValue(row.getPASSWORD());
                    model.getControl("RINNOVO_PASSWORD").setValue(row.getRINNOVO_PASSWORD());
                    model.getControl("STATO").setValue(row.getSTATO());
                    model.getControl("LINGUA").setValue(row.getLINGUA());
                    model.getControl("GRUPPO_LAVORO").setValue(row.getGRUPPO_LAVORO());
                    model.getControl("IMPORTANZA").setValue(row.getIMPORTANZA());
                    model.getControl("AMMINISTRATORE").setValue(row.getAMMINISTRATORE());
                    model.getControl("INFO_IDENTIFICAZIONE").setValue(row.getINFO_IDENTIFICAZIONE());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End AD4_UTENTI Record: bind

//AD4_UTENTI Record: getRowFieldByName @6-6D775C8D
            public Object getRowFieldByName( String name, AD4_UTENTIRow row ) {
                Object value = null;
                if ( "GENERA_PASSWORD".equals(name) ) {
                    value = row.getGENERA_PASSWORD();
                } else if ( "SVUOTA_REG_ANAGRAFICA".equals(name) ) {
                    value = row.getSVUOTA_REG_ANAGRAFICA();
                } else if ( "MOD_REGISTRAZIONE_ANAGRAFICA".equals(name) ) {
                    value = row.getMOD_REGISTRAZIONE_ANAGRAFICA();
                } else if ( "AUTENTICAZIONE_AD4".equals(name) ) {
                    value = row.getAUTENTICAZIONE_AD4();
                } else if ( "ELIMINA_CAAC".equals(name) ) {
                    value = row.getELIMINA_CAAC();
                } else if ( "NOMINATIVO".equals(name) ) {
                    value = row.getNOMINATIVO();
                } else if ( "IS_SO4_USER".equals(name) ) {
                    value = row.getIS_SO4_USER();
                } else if ( "SOGGETTO".equals(name) ) {
                    value = row.getSOGGETTO();
                } else if ( "ID_UTENTE".equals(name) ) {
                    value = row.getID_UTENTE();
                } else if ( "IS_LDAPUSER".equals(name) ) {
                    value = row.getIS_LDAPUSER();
                } else if ( "UTENTE_VIS".equals(name) ) {
                    value = row.getUTENTE_VIS();
                } else if ( "UTENTE".equals(name) ) {
                    value = row.getUTENTE();
                } else if ( "PASSWORD".equals(name) ) {
                    value = row.getPASSWORD();
                } else if ( "MODIFICA_PASSWORD".equals(name) ) {
                    value = row.getMODIFICA_PASSWORD();
                } else if ( "PWD_MODIFIED".equals(name) ) {
                    value = row.getPWD_MODIFIED();
                } else if ( "DATA_PASSWORD".equals(name) ) {
                    value = row.getDATA_PASSWORD();
                } else if ( "RINNOVO_PASSWORD".equals(name) ) {
                    value = row.getRINNOVO_PASSWORD();
                } else if ( "STATO".equals(name) ) {
                    value = row.getSTATO();
                } else if ( "LINGUA".equals(name) ) {
                    value = row.getLINGUA();
                } else if ( "GRUPPO_LAVORO".equals(name) ) {
                    value = row.getGRUPPO_LAVORO();
                } else if ( "IMPORTANZA".equals(name) ) {
                    value = row.getIMPORTANZA();
                } else if ( "AMMINISTRATORE".equals(name) ) {
                    value = row.getAMMINISTRATORE();
                } else if ( "INFO_IDENTIFICAZIONE".equals(name) ) {
                    value = row.getINFO_IDENTIFICAZIONE();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                } else if ( "TITOLO_SOGG".equals(name) ) {
                    value = row.getTITOLO_SOGG();
                } else if ( "DATI_SOGGETTO".equals(name) ) {
                    value = row.getDATI_SOGGETTO();
                } else if ( "ULTIMO_TENTATIVO".equals(name) ) {
                    value = row.getULTIMO_TENTATIVO();
                } else if ( "NUMERO_TENTATIVI".equals(name) ) {
                    value = row.getNUMERO_TENTATIVI();
                } else if ( "DATA_INSERIMENTO".equals(name) ) {
                    value = row.getDATA_INSERIMENTO();
                } else if ( "UTENTE_DATA_AGGIORNAMENTO".equals(name) ) {
                    value = row.getUTENTE_DATA_AGGIORNAMENTO();
                } else if ( "s_UTENTE".equals(name) ) {
                    value = row.getS_UTENTE();
                }
                return value;
            }
//End AD4_UTENTI Record: getRowFieldByName

void InsertAction() { //AD4_UTENTI Record: method insert @6-11643485

//AD4_UTENTI Record: method insert head @6-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_UTENTI Record: method insert head

//AD4_UTENTI Record: method insert body @6-8435A53E
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            AD4_UTENTIDataObject ds = new AD4_UTENTIDataObject(page);
            ds.setComponent( model );
            AD4_UTENTIRow row = new AD4_UTENTIRow();
            ds.setRow(row);
            row.setID_UTENTE(Utils.convertToString(model.getControl("ID_UTENTE").getValue()));
            ds.setPostUTENTE_VIS( page.getHttpPostParams().getParameter("UTENTE_VIS") );
            ds.setPostNOMINATIVO( page.getHttpPostParams().getParameter("NOMINATIVO") );
            ds.setPostPASSWORD( page.getHttpPostParams().getParameter("PASSWORD") );
            row.setPWD_MODIFIED(Utils.convertToString(model.getControl("PWD_MODIFIED").getValue()));
            ds.setPostRINNOVO_PASSWORD( page.getHttpPostParams().getParameter("RINNOVO_PASSWORD") );
            ds.setPostSTATO( page.getHttpPostParams().getParameter("STATO") );
            ds.setPostLINGUA( page.getHttpPostParams().getParameter("LINGUA") );
            ds.setPostGRUPPO_LAVORO( page.getHttpPostParams().getParameter("GRUPPO_LAVORO") );
            try {
                ds.setPostIMPORTANZA( page.getHttpPostParams().getParameter("IMPORTANZA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'IMPORTANZA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'IMPORTANZA'" );
            }
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            try {
                ds.setPostSOGGETTO( page.getHttpPostParams().getParameter("SOGGETTO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            }
            row.setAMMINISTRATORE(Utils.convertToString(model.getControl("AMMINISTRATORE").getValue()));
            ds.setPostINFO_IDENTIFICAZIONE( page.getHttpPostParams().getParameter("INFO_IDENTIFICAZIONE") );
//End AD4_UTENTI Record: method insert body

//AD4_UTENTI Record: ds.insert @6-9320B03B
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
//End AD4_UTENTI Record: ds.insert

} //AD4_UTENTI Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4_UTENTI Record: method update @6-5771D0AA

//AD4_UTENTI Record: method update head @6-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_UTENTI Record: method update head

//AD4_UTENTI Record: method update body @6-8FFDDABF
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_UTENTIDataObject ds = new AD4_UTENTIDataObject(page);
            ds.setComponent( model );
            AD4_UTENTIRow row = new AD4_UTENTIRow();
            ds.setRow(row);
            row.setID_UTENTE(Utils.convertToString(model.getControl("ID_UTENTE").getValue()));
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setPostNOMINATIVO( page.getHttpPostParams().getParameter("NOMINATIVO") );
            ds.setPostPASSWORD( page.getHttpPostParams().getParameter("PASSWORD") );
            row.setPWD_MODIFIED(Utils.convertToString(model.getControl("PWD_MODIFIED").getValue()));
            ds.setPostRINNOVO_PASSWORD( page.getHttpPostParams().getParameter("RINNOVO_PASSWORD") );
            ds.setPostSTATO( page.getHttpPostParams().getParameter("STATO") );
            ds.setPostLINGUA( page.getHttpPostParams().getParameter("LINGUA") );
            ds.setPostGRUPPO_LAVORO( page.getHttpPostParams().getParameter("GRUPPO_LAVORO") );
            try {
                ds.setPostIMPORTANZA( page.getHttpPostParams().getParameter("IMPORTANZA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'IMPORTANZA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'IMPORTANZA'" );
            }
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            try {
                ds.setPostSOGGETTO( page.getHttpPostParams().getParameter("SOGGETTO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SOGGETTO'" );
            }
            row.setAMMINISTRATORE(Utils.convertToString(model.getControl("AMMINISTRATORE").getValue()));
            ds.setPostINFO_IDENTIFICAZIONE( page.getHttpPostParams().getParameter("INFO_IDENTIFICAZIONE") );
//End AD4_UTENTI Record: method update body

//AD4_UTENTI Record: ds.update @6-6E956EDC
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
//End AD4_UTENTI Record: ds.update

} //AD4_UTENTI Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4_UTENTI Record: method delete @6-11FC2E1E

//AD4_UTENTI Record: method delete head @6-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_UTENTI Record: method delete head

//AD4_UTENTI Record: method delete body @6-644AF408
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_UTENTIDataObject ds = new AD4_UTENTIDataObject(page);
            ds.setComponent( model );
            AD4_UTENTIRow row = new AD4_UTENTIRow();
            ds.setRow(row);
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
//End AD4_UTENTI Record: method delete body

//AD4_UTENTI Record: ds.delete @6-3584344F
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
//End AD4_UTENTI Record: ds.delete

} //AD4_UTENTI Record: method delete tail @6-FCB6E20C

//AD4_UTENTI Record: method validate @6-7433793B
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox NOMINATIVO = (com.codecharge.components.TextBox) model.getChild( "NOMINATIVO" );
            if (! NOMINATIVO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden IS_SO4_USER = (com.codecharge.components.Hidden) model.getChild( "IS_SO4_USER" );
            if (! IS_SO4_USER.validate()) { isControlError = true; }

            com.codecharge.components.Hidden SOGGETTO = (com.codecharge.components.Hidden) model.getChild( "SOGGETTO" );
            if (! SOGGETTO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ID_UTENTE = (com.codecharge.components.Hidden) model.getChild( "ID_UTENTE" );
            if (! ID_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden UTENTE = (com.codecharge.components.Hidden) model.getChild( "UTENTE" );
            if (! UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox PASSWORD = (com.codecharge.components.TextBox) model.getChild( "PASSWORD" );
            if (! PASSWORD.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PWD_MODIFIED = (com.codecharge.components.Hidden) model.getChild( "PWD_MODIFIED" );
            if (! PWD_MODIFIED.validate()) { isControlError = true; }

            com.codecharge.components.ListBox RINNOVO_PASSWORD = (com.codecharge.components.ListBox) model.getChild( "RINNOVO_PASSWORD" );
            if (! RINNOVO_PASSWORD.validate()) { isControlError = true; }

            com.codecharge.components.ListBox STATO = (com.codecharge.components.ListBox) model.getChild( "STATO" );
            if (! STATO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox LINGUA = (com.codecharge.components.ListBox) model.getChild( "LINGUA" );
            if (! LINGUA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox GRUPPO_LAVORO = (com.codecharge.components.ListBox) model.getChild( "GRUPPO_LAVORO" );
            if (! GRUPPO_LAVORO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox IMPORTANZA = (com.codecharge.components.TextBox) model.getChild( "IMPORTANZA" );
            if (! IMPORTANZA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox INFO_IDENTIFICAZIONE = (com.codecharge.components.TextBox) model.getChild( "INFO_IDENTIFICAZIONE" );
            if (! INFO_IDENTIFICAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_UTENTI Record: method validate

//AD4_UTENTI Record Tail @6-FCB6E20C
    }
//End AD4_UTENTI Record Tail

//AD4Utente Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Utente Page: method validate

//AD4UtenteAction Tail @1-FCB6E20C
}
//End AD4UtenteAction Tail

