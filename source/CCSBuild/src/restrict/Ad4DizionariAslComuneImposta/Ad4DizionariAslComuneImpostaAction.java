//Ad4DizionariAslComuneImpostaAction imports @1-51D4E3EB
package restrict.Ad4DizionariAslComuneImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariAslComuneImpostaAction imports

//Ad4DizionariAslComuneImpostaAction class @1-BA0ED202
public class Ad4DizionariAslComuneImpostaAction extends Action {

//End Ad4DizionariAslComuneImpostaAction class

//Ad4DizionariAslComuneImpostaAction: method perform @1-CC502167
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariAslComuneImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariAslComuneImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariAslComuneImpostaAction: method perform

//Ad4DizionariAslComuneImpostaAction: call children actions @1-853FCC50
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            AslComuneImpostaClass AslComuneImposta = new AslComuneImpostaClass();
            if ( ( redirect = AslComuneImposta.perform( page.getRecord("AslComuneImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariAslComuneImpostaAction: call children actions

//AttributoHeader Grid @44-F8FAAC80
    final class AttributoHeaderClass {
        com.codecharge.components.Grid model;
        Event e;
//End AttributoHeader Grid

//AttributoHeader Grid: method perform @44-B48879D3
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
//End AttributoHeader Grid: method perform

//AttributoHeader Grid: method read: head @44-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AttributoHeader Grid: method read: head

//AttributoHeader Grid: method read: init @44-14C6E52C
            if ( ! model.allowRead ) return true;
            AttributoHeaderDataObject ds = new AttributoHeaderDataObject(page);
            ds.setComponent( model );
//End AttributoHeader Grid: method read: init

//AttributoHeader Grid: set grid properties @44-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AttributoHeader Grid: set grid properties

//AttributoHeader Grid: retrieve data @44-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AttributoHeader Grid: retrieve data

//AttributoHeader Grid: check errors @44-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AttributoHeader Grid: check errors

//AttributoHeader Grid: method read: tail @44-F575E732
            return ( ! isErrors );
        }
//End AttributoHeader Grid: method read: tail

//AttributoHeader Grid: method bind @44-03ABD677
        public void bind(com.codecharge.components.Component model, AttributoHeaderRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AttributoHeaderRow row = null;
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
                c = (com.codecharge.components.Control) hashRow.get("Indietro");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Indietro").clone();
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AttributoHeader Grid: method bind

//AttributoHeader Directory: getRowFieldByName @44-4C3EEF14
        public Object getRowFieldByName( String name, AttributoHeaderRow row ) {
            Object value = null;
            if ( "Indietro".equals(name) ) {
                value = row.getIndietro();
            }
            return value;
        }
//End AttributoHeader Directory: getRowFieldByName

//AttributoHeader Grid: method validate @44-104025BA
        boolean validate() {
            return true;
        }
//End AttributoHeader Grid: method validate

//AttributoHeader Grid Tail @44-FCB6E20C
    }
//End AttributoHeader Grid Tail

//AslComuneImposta Record @5-C8EFD6CD
    final class AslComuneImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AslComuneImposta Record

//AslComuneImposta Record: method perform @5-8CBD5765
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowInsert() ) model.getChild( "Button_Insert" ).setVisible( false );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AslComuneImposta Record: method perform

//AslComuneImposta Record: children actions @5-90E91C46
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AslComuneImposta".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Button_Update") != null) {
                        Button_UpdateAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
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
                    if (page.getParameter("Button_Insert") != null) {
                        if (validate()) {
                            Button_InsertAction();
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
            readCOD_ASL(model.getListBox("COD_ASL"));
            readCOD_COMUNE_ASL(model.getListBox("COD_COMUNE_ASL"));
//End AslComuneImposta Record: children actions

//AslComuneImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AslComuneImposta Record: method perform tail

//Button_Insert Button @12-A60A3466
        void Button_InsertAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Insert");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            InsertAction();
        }
//End Button_Insert Button

//Button_Update Button @14-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @16-79294D0A
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @19-BEBD3B4D
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @55-2C376DB1
        protected void readCOD_ASL(com.codecharge.components.ListBox model) {

            TextField urlREGIONE_ASL = new TextField(null, null);
            
            urlREGIONE_ASL.setValue( page.getHttpGetParams().getParameter("REGIONE_ASL") );
            TextField urlCODICE_ASL = new TextField(null, null);
            
            urlCODICE_ASL.setValue( page.getHttpGetParams().getParameter("CODICE_ASL") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT '{REGIONE_ASL}'||'#'||'{CODICE_ASL}' cod_asl,  "
                        + "deSCRIzione "
                        + "FROM ad4_ASL "
                        + "where REGIONE_ASL ='{REGIONE_ASL}' "
                        + "AND CODICE_ASL = '{CODICE_ASL}'" );
            if ( StringUtils.isEmpty( (String) urlREGIONE_ASL.getObjectValue() ) ) urlREGIONE_ASL.setValue( "" );
            command.addParameter( "REGIONE_ASL", urlREGIONE_ASL, null );
            if ( StringUtils.isEmpty( (String) urlCODICE_ASL.getObjectValue() ) ) urlCODICE_ASL.setValue( "" );
            command.addParameter( "CODICE_ASL", urlCODICE_ASL, null );
            command.setOrder( "" );

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

//ListBoxAction @58-6A86CE01
        protected void readCOD_COMUNE_ASL(com.codecharge.components.ListBox model) {

            TextField urlREGIONE_ASL = new TextField(null, null);
            
            urlREGIONE_ASL.setValue( page.getHttpGetParams().getParameter("REGIONE_ASL") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT provincia_stato||'#'||comune cod_com_asl,  "
                        + "denominazione "
                        + "FROM ad4_comuni "
                        + "where provincia_stato in (select provincia from ad4_province where regione = '{REGIONE_ASL}') "
                        + "and data_soppressione is null "
                        + "" );
            if ( StringUtils.isEmpty( (String) urlREGIONE_ASL.getObjectValue() ) ) urlREGIONE_ASL.setValue( "" );
            command.addParameter( "REGIONE_ASL", urlREGIONE_ASL, null );
            command.setOrder( "PROVINCIA_STATO, DENOMINAZIONE" );

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

void read() { //AslComuneImposta Record: method read @5-7F8AAE5A

//AslComuneImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AslComuneImposta Record: method read head

//AslComuneImposta Record: init DataSource @5-40073E13
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AslComuneImpostaDataObject ds = new AslComuneImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlREGIONE_ASL( page.getHttpGetParams().getParameter("REGIONE_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'REGIONE_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'REGIONE_ASL'" );
            }
            try {
                ds.setUrlCODICE_ASL( page.getHttpGetParams().getParameter("CODICE_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'CODICE_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'CODICE_ASL'" );
            }
            ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA") );
            ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AslComuneImposta Record: init DataSource

//AslComuneImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AslComuneImposta Record: check errors

} //AslComuneImposta Record: method read tail @5-FCB6E20C

//AslComuneImposta Record: bind @5-F5648217
            public void bind(com.codecharge.components.Component model, AslComuneImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("ASL_DESC").setValue(row.getASL_DESC());
                model.getControl("Label1").setValue(row.getLabel1());
                model.getControl("Label2").setValue(row.getLabel2());
                model.getControl("COMUNE_DESC").setValue(row.getCOMUNE_DESC());
                model.getControl("Label3").setValue(row.getLabel3());
                model.getControl("Label4").setValue(row.getLabel4());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("REGIONE_ASL").setValue(row.getREGIONE_ASL());
                    model.getControl("CODICE_ASL").setValue(row.getCODICE_ASL());
                    model.getControl("COD_ASL").setValue(row.getCOD_ASL());
                    model.getControl("PROVINCIA").setValue(row.getPROVINCIA());
                    model.getControl("COMUNE").setValue(row.getCOMUNE());
                    model.getControl("COD_COMUNE_ASL").setValue(row.getCOD_COMUNE_ASL());
                    model.getControl("PROPOSTA").setValue(row.getPROPOSTA());
                    model.getControl("ATTIVA").setValue(row.getATTIVA());
                }
            }
//End AslComuneImposta Record: bind

//AslComuneImposta Record: getRowFieldByName @5-74890906
            public Object getRowFieldByName( String name, AslComuneImpostaRow row ) {
                Object value = null;
                if ( "ASL_DESC".equals(name) ) {
                    value = row.getASL_DESC();
                } else if ( "REGIONE_ASL".equals(name) ) {
                    value = row.getREGIONE_ASL();
                } else if ( "CODICE_ASL".equals(name) ) {
                    value = row.getCODICE_ASL();
                } else if ( "Label1".equals(name) ) {
                    value = row.getLabel1();
                } else if ( "COD_ASL".equals(name) ) {
                    value = row.getCOD_ASL();
                } else if ( "Label2".equals(name) ) {
                    value = row.getLabel2();
                } else if ( "COMUNE_DESC".equals(name) ) {
                    value = row.getCOMUNE_DESC();
                } else if ( "PROVINCIA".equals(name) ) {
                    value = row.getPROVINCIA();
                } else if ( "COMUNE".equals(name) ) {
                    value = row.getCOMUNE();
                } else if ( "Label3".equals(name) ) {
                    value = row.getLabel3();
                } else if ( "COD_COMUNE_ASL".equals(name) ) {
                    value = row.getCOD_COMUNE_ASL();
                } else if ( "Label4".equals(name) ) {
                    value = row.getLabel4();
                } else if ( "PROPOSTA".equals(name) ) {
                    value = row.getPROPOSTA();
                } else if ( "ATTIVA".equals(name) ) {
                    value = row.getATTIVA();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End AslComuneImposta Record: getRowFieldByName

void InsertAction() { //AslComuneImposta Record: method insert @5-11643485

//AslComuneImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AslComuneImposta Record: method insert head

//AslComuneImposta Record: method insert body @5-780C711B
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            AslComuneImpostaDataObject ds = new AslComuneImpostaDataObject(page);
            ds.setComponent( model );
            AslComuneImpostaRow row = new AslComuneImpostaRow();
            ds.setRow(row);
            row.setCOD_COMUNE_ASL(Utils.convertToString(model.getControl("COD_COMUNE_ASL").getValue()));
            row.setCOD_ASL(Utils.convertToString(model.getControl("COD_ASL").getValue()));
            row.setPROPOSTA(Utils.convertToString(model.getControl("PROPOSTA").getValue()));
            row.setATTIVA(Utils.convertToString(model.getControl("ATTIVA").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End AslComuneImposta Record: method insert body

//AslComuneImposta Record: ds.insert @5-9320B03B
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
//End AslComuneImposta Record: ds.insert

} //AslComuneImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //AslComuneImposta Record: method update @5-5771D0AA

//AslComuneImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AslComuneImposta Record: method update head

//AslComuneImposta Record: method update body @5-5459762C
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AslComuneImpostaDataObject ds = new AslComuneImpostaDataObject(page);
            ds.setComponent( model );
            AslComuneImpostaRow row = new AslComuneImpostaRow();
            ds.setRow(row);
            row.setPROVINCIA(Utils.convertToLong(model.getControl("PROVINCIA").getValue()));
            row.setCOMUNE(Utils.convertToLong(model.getControl("COMUNE").getValue()));
            try {
                ds.setUrlREGIONE_ASL( page.getHttpGetParams().getParameter("REGIONE_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'REGIONE_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'REGIONE_ASL'" );
            }
            try {
                ds.setUrlCODICE_ASL( page.getHttpGetParams().getParameter("CODICE_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'CODICE_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'CODICE_ASL'" );
            }
            row.setPROPOSTA(Utils.convertToString(model.getControl("PROPOSTA").getValue()));
            row.setATTIVA(Utils.convertToString(model.getControl("ATTIVA").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End AslComuneImposta Record: method update body

//AslComuneImposta Record: ds.update @5-6E956EDC
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
//End AslComuneImposta Record: ds.update

} //AslComuneImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //AslComuneImposta Record: method delete @5-11FC2E1E

//AslComuneImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AslComuneImposta Record: method delete head

//AslComuneImposta Record: method delete body @5-B44C62C1
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AslComuneImpostaDataObject ds = new AslComuneImpostaDataObject(page);
            ds.setComponent( model );
            AslComuneImpostaRow row = new AslComuneImpostaRow();
            ds.setRow(row);
            row.setPROVINCIA(Utils.convertToLong(model.getControl("PROVINCIA").getValue()));
            row.setCOMUNE(Utils.convertToLong(model.getControl("COMUNE").getValue()));
            row.setREGIONE_ASL(Utils.convertToLong(model.getControl("REGIONE_ASL").getValue()));
            row.setCODICE_ASL(Utils.convertToLong(model.getControl("CODICE_ASL").getValue()));
//End AslComuneImposta Record: method delete body

//AslComuneImposta Record: ds.delete @5-3584344F
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
//End AslComuneImposta Record: ds.delete

} //AslComuneImposta Record: method delete tail @5-FCB6E20C

//AslComuneImposta Record: method validate @5-41E4E2E9
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden REGIONE_ASL = (com.codecharge.components.Hidden) model.getChild( "REGIONE_ASL" );
            if (! REGIONE_ASL.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CODICE_ASL = (com.codecharge.components.Hidden) model.getChild( "CODICE_ASL" );
            if (! CODICE_ASL.validate()) { isControlError = true; }

            com.codecharge.components.ListBox COD_ASL = (com.codecharge.components.ListBox) model.getChild( "COD_ASL" );
            if (! COD_ASL.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROVINCIA = (com.codecharge.components.Hidden) model.getChild( "PROVINCIA" );
            if (! PROVINCIA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden COMUNE = (com.codecharge.components.Hidden) model.getChild( "COMUNE" );
            if (! COMUNE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox COD_COMUNE_ASL = (com.codecharge.components.ListBox) model.getChild( "COD_COMUNE_ASL" );
            if (! COD_COMUNE_ASL.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AslComuneImposta Record: method validate

//AslComuneImposta Record Tail @5-FCB6E20C
    }
//End AslComuneImposta Record Tail

//Ad4DizionariAslComuneImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariAslComuneImposta Page: method validate

//Ad4DizionariAslComuneImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariAslComuneImpostaAction Tail

