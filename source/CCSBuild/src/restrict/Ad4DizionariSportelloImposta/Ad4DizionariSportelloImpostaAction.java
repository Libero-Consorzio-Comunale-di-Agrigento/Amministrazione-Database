//Ad4DizionariSportelloImpostaAction imports @1-7C538AF1
package restrict.Ad4DizionariSportelloImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariSportelloImpostaAction imports

//Ad4DizionariSportelloImpostaAction class @1-37083509
public class Ad4DizionariSportelloImpostaAction extends Action {

//End Ad4DizionariSportelloImpostaAction class

//Ad4DizionariSportelloImpostaAction: method perform @1-3437EFCD
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariSportelloImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariSportelloImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariSportelloImpostaAction: method perform

//Ad4DizionariSportelloImpostaAction: call children actions @1-D39B18C9
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            SportelloImpostaClass SportelloImposta = new SportelloImpostaClass();
            if ( ( redirect = SportelloImposta.perform( page.getRecord("SportelloImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariSportelloImpostaAction: call children actions

//AttributoHeader Grid @2-F8FAAC80
    final class AttributoHeaderClass {
        com.codecharge.components.Grid model;
        Event e;
//End AttributoHeader Grid

//AttributoHeader Grid: method perform @2-B48879D3
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

//AttributoHeader Grid: method read: head @2-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AttributoHeader Grid: method read: head

//AttributoHeader Grid: method read: init @2-14C6E52C
            if ( ! model.allowRead ) return true;
            AttributoHeaderDataObject ds = new AttributoHeaderDataObject(page);
            ds.setComponent( model );
//End AttributoHeader Grid: method read: init

//AttributoHeader Grid: set grid properties @2-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AttributoHeader Grid: set grid properties

//AttributoHeader Grid: retrieve data @2-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AttributoHeader Grid: retrieve data

//AttributoHeader Grid: check errors @2-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AttributoHeader Grid: check errors

//AttributoHeader Grid: method read: tail @2-F575E732
            return ( ! isErrors );
        }
//End AttributoHeader Grid: method read: tail

//AttributoHeader Grid: method bind @2-03ABD677
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

//AttributoHeader Directory: getRowFieldByName @2-4C3EEF14
        public Object getRowFieldByName( String name, AttributoHeaderRow row ) {
            Object value = null;
            if ( "Indietro".equals(name) ) {
                value = row.getIndietro();
            }
            return value;
        }
//End AttributoHeader Directory: getRowFieldByName

//AttributoHeader Grid: method validate @2-104025BA
        boolean validate() {
            return true;
        }
//End AttributoHeader Grid: method validate

//AttributoHeader Grid Tail @2-FCB6E20C
    }
//End AttributoHeader Grid Tail

//SportelloImposta Record @5-6B98C971
    final class SportelloImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End SportelloImposta Record

//SportelloImposta Record: method perform @5-8CBD5765
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
//End SportelloImposta Record: method perform

//SportelloImposta Record: children actions @5-EF5EA778
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("SportelloImposta".equals(formName)) {
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
            readABI(model.getListBox("ABI"));
            readCOMUNE(model.getListBox("COMUNE"));
//End SportelloImposta Record: children actions

//SportelloImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End SportelloImposta Record: method perform tail

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

//ListBoxAction @8-A0B7D8D0
        protected void readABI(com.codecharge.components.ListBox model) {

            TextField urlS_BANCA = new TextField(null, null);
            
            urlS_BANCA.setValue( page.getHttpGetParams().getParameter("s_BANCA") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select abi,  "
                        + "denominazione  "
                        + "from ad4_banche "
                        + "where abi = '{s_BANCA}' "
                        + " or abi like '{s_BANCA}' "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlS_BANCA.getObjectValue() ) ) urlS_BANCA.setValue( "%" );
            command.addParameter( "s_BANCA", urlS_BANCA, null );
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

//ListBoxAction @52-E0A8051E
        protected void readCOMUNE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select denominazione,  "
                        + "denominazione denominazione_v "
                        + "  from ad4_comuni "
                        + "where data_soppressione is null "
                        + "" );
            command.setOrder( "denominazione" );

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

void read() { //SportelloImposta Record: method read @5-7F8AAE5A

//SportelloImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End SportelloImposta Record: method read head

//SportelloImposta Record: init DataSource @5-2C5F280E
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            SportelloImpostaDataObject ds = new SportelloImpostaDataObject(page);
            ds.setComponent( model );
            ds.setUrlBANCA( page.getHttpGetParams().getParameter("BANCA") );
            ds.setUrlSPORTELLO( page.getHttpGetParams().getParameter("SPORTELLO") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End SportelloImposta Record: init DataSource

//SportelloImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End SportelloImposta Record: check errors

} //SportelloImposta Record: method read tail @5-FCB6E20C

//SportelloImposta Record: bind @5-3BE291E0
            public void bind(com.codecharge.components.Component model, SportelloImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("ABI_LABEL").setValue(row.getABI_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("CAB_LABEL").setValue(row.getCAB_LABEL());
                model.getControl("HIDE_BEGIN2").setValue(row.getHIDE_BEGIN2());
                model.getControl("HIDE_END2").setValue(row.getHIDE_END2());
                model.getControl("TRADUZIONE").setValue(row.getTRADUZIONE());
                if ( this.valid ) {
                    model.getControl("ABI").setValue(row.getABI());
                    model.getControl("CAB").setValue(row.getCAB());
                    model.getControl("CIN_CAB").setValue(row.getCIN_CAB());
                    model.getControl("DESCRIZIONE").setValue(row.getDESCRIZIONE());
                    model.getControl("INDIRIZZO").setValue(row.getINDIRIZZO());
                    model.getControl("LOCALITA").setValue(row.getLOCALITA());
                    model.getControl("COMUNE").setValue(row.getCOMUNE());
                    model.getControl("PROVINCIA").setValue(row.getPROVINCIA());
                    model.getControl("CAP").setValue(row.getCAP());
                    model.getControl("DIPENDENZA").setValue(row.getDIPENDENZA());
                    model.getControl("BIC").setValue(row.getBIC());
                }
            }
//End SportelloImposta Record: bind

//SportelloImposta Record: getRowFieldByName @5-46A2B73C
            public Object getRowFieldByName( String name, SportelloImpostaRow row ) {
                Object value = null;
                if ( "ABI_LABEL".equals(name) ) {
                    value = row.getABI_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "ABI".equals(name) ) {
                    value = row.getABI();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "CAB_LABEL".equals(name) ) {
                    value = row.getCAB_LABEL();
                } else if ( "HIDE_BEGIN2".equals(name) ) {
                    value = row.getHIDE_BEGIN2();
                } else if ( "CAB".equals(name) ) {
                    value = row.getCAB();
                } else if ( "HIDE_END2".equals(name) ) {
                    value = row.getHIDE_END2();
                } else if ( "CIN_CAB".equals(name) ) {
                    value = row.getCIN_CAB();
                } else if ( "DESCRIZIONE".equals(name) ) {
                    value = row.getDESCRIZIONE();
                } else if ( "TRADUZIONE".equals(name) ) {
                    value = row.getTRADUZIONE();
                } else if ( "INDIRIZZO".equals(name) ) {
                    value = row.getINDIRIZZO();
                } else if ( "LOCALITA".equals(name) ) {
                    value = row.getLOCALITA();
                } else if ( "COMUNE".equals(name) ) {
                    value = row.getCOMUNE();
                } else if ( "PROVINCIA".equals(name) ) {
                    value = row.getPROVINCIA();
                } else if ( "CAP".equals(name) ) {
                    value = row.getCAP();
                } else if ( "DIPENDENZA".equals(name) ) {
                    value = row.getDIPENDENZA();
                } else if ( "BIC".equals(name) ) {
                    value = row.getBIC();
                }
                return value;
            }
//End SportelloImposta Record: getRowFieldByName

void InsertAction() { //SportelloImposta Record: method insert @5-11643485

//SportelloImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End SportelloImposta Record: method insert head

//SportelloImposta Record: method insert body @5-5D2F5677
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            SportelloImpostaDataObject ds = new SportelloImpostaDataObject(page);
            ds.setComponent( model );
            SportelloImpostaRow row = new SportelloImpostaRow();
            ds.setRow(row);
            row.setABI(Utils.convertToString(model.getControl("ABI").getValue()));
            row.setCAB(Utils.convertToString(model.getControl("CAB").getValue()));
            row.setCIN_CAB(Utils.convertToString(model.getControl("CIN_CAB").getValue()));
            row.setDESCRIZIONE(Utils.convertToString(model.getControl("DESCRIZIONE").getValue()));
            row.setINDIRIZZO(Utils.convertToString(model.getControl("INDIRIZZO").getValue()));
            row.setLOCALITA(Utils.convertToString(model.getControl("LOCALITA").getValue()));
            row.setCOMUNE(Utils.convertToString(model.getControl("COMUNE").getValue()));
            row.setPROVINCIA(Utils.convertToString(model.getControl("PROVINCIA").getValue()));
            row.setCAP(Utils.convertToString(model.getControl("CAP").getValue()));
            row.setDIPENDENZA(Utils.convertToString(model.getControl("DIPENDENZA").getValue()));
            row.setBIC(Utils.convertToString(model.getControl("BIC").getValue()));
//End SportelloImposta Record: method insert body

//SportelloImposta Record: ds.insert @5-9320B03B
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
//End SportelloImposta Record: ds.insert

} //SportelloImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //SportelloImposta Record: method update @5-5771D0AA

//SportelloImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End SportelloImposta Record: method update head

//SportelloImposta Record: method update body @5-77E05F3A
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            SportelloImpostaDataObject ds = new SportelloImpostaDataObject(page);
            ds.setComponent( model );
            SportelloImpostaRow row = new SportelloImpostaRow();
            ds.setRow(row);
            ds.setUrlBANCA( page.getHttpGetParams().getParameter("BANCA") );
            ds.setUrlSPORTELLO( page.getHttpGetParams().getParameter("SPORTELLO") );
            row.setCIN_CAB(Utils.convertToString(model.getControl("CIN_CAB").getValue()));
            row.setDESCRIZIONE(Utils.convertToString(model.getControl("DESCRIZIONE").getValue()));
            row.setINDIRIZZO(Utils.convertToString(model.getControl("INDIRIZZO").getValue()));
            row.setLOCALITA(Utils.convertToString(model.getControl("LOCALITA").getValue()));
            row.setCOMUNE(Utils.convertToString(model.getControl("COMUNE").getValue()));
            row.setPROVINCIA(Utils.convertToString(model.getControl("PROVINCIA").getValue()));
            row.setCAP(Utils.convertToString(model.getControl("CAP").getValue()));
            row.setDIPENDENZA(Utils.convertToString(model.getControl("DIPENDENZA").getValue()));
            row.setBIC(Utils.convertToString(model.getControl("BIC").getValue()));
//End SportelloImposta Record: method update body

//SportelloImposta Record: ds.update @5-6E956EDC
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
//End SportelloImposta Record: ds.update

} //SportelloImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //SportelloImposta Record: method delete @5-11FC2E1E

//SportelloImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End SportelloImposta Record: method delete head

//SportelloImposta Record: method delete body @5-3C3905DE
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            SportelloImpostaDataObject ds = new SportelloImpostaDataObject(page);
            ds.setComponent( model );
            SportelloImpostaRow row = new SportelloImpostaRow();
            ds.setRow(row);
            ds.setUrlBANCA( page.getHttpGetParams().getParameter("BANCA") );
            ds.setUrlSPORTELLO( page.getHttpGetParams().getParameter("SPORTELLO") );
//End SportelloImposta Record: method delete body

//SportelloImposta Record: ds.delete @5-3584344F
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
//End SportelloImposta Record: ds.delete

} //SportelloImposta Record: method delete tail @5-FCB6E20C

//SportelloImposta Record: method validate @5-62E5FF69
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox ABI = (com.codecharge.components.ListBox) model.getChild( "ABI" );
            if (! ABI.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CAB = (com.codecharge.components.TextBox) model.getChild( "CAB" );
            if (! CAB.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CIN_CAB = (com.codecharge.components.TextBox) model.getChild( "CIN_CAB" );
            if (! CIN_CAB.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "DESCRIZIONE" );
            if (! DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox INDIRIZZO = (com.codecharge.components.TextBox) model.getChild( "INDIRIZZO" );
            if (! INDIRIZZO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox LOCALITA = (com.codecharge.components.TextBox) model.getChild( "LOCALITA" );
            if (! LOCALITA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox COMUNE = (com.codecharge.components.ListBox) model.getChild( "COMUNE" );
            if (! COMUNE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox PROVINCIA = (com.codecharge.components.TextBox) model.getChild( "PROVINCIA" );
            if (! PROVINCIA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CAP = (com.codecharge.components.TextBox) model.getChild( "CAP" );
            if (! CAP.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DIPENDENZA = (com.codecharge.components.TextBox) model.getChild( "DIPENDENZA" );
            if (! DIPENDENZA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox BIC = (com.codecharge.components.TextBox) model.getChild( "BIC" );
            if (! BIC.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End SportelloImposta Record: method validate

//SportelloImposta Record Tail @5-FCB6E20C
    }
//End SportelloImposta Record Tail

//Ad4DizionariSportelloImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariSportelloImposta Page: method validate

//Ad4DizionariSportelloImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariSportelloImpostaAction Tail
