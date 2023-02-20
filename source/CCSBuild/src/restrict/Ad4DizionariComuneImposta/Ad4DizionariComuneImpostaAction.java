//Ad4DizionariComuneImpostaAction imports @1-AFC10681
package restrict.Ad4DizionariComuneImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariComuneImpostaAction imports

//Ad4DizionariComuneImpostaAction class @1-AD5B93F9
public class Ad4DizionariComuneImpostaAction extends Action {

//End Ad4DizionariComuneImpostaAction class

//Ad4DizionariComuneImpostaAction: method perform @1-C0F55E30
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariComuneImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariComuneImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariComuneImpostaAction: method perform

//Ad4DizionariComuneImpostaAction: call children actions @1-35775FF7
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            ComuneImpostaClass ComuneImposta = new ComuneImpostaClass();
            if ( ( redirect = ComuneImposta.perform( page.getRecord("ComuneImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariComuneImpostaAction: call children actions

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

//ComuneImposta Record @5-49AA33C6
    final class ComuneImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ComuneImposta Record

//ComuneImposta Record: method perform @5-8CBD5765
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
//End ComuneImposta Record: method perform

//ComuneImposta Record: children actions @5-651421D7
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ComuneImposta".equals(formName)) {
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
            readPROVINCIA_STATO(model.getListBox("PROVINCIA_STATO"));
//End ComuneImposta Record: children actions

//ComuneImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ComuneImposta Record: method perform tail

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

//ListBoxAction @8-13122A22
        protected void readPROVINCIA_STATO(com.codecharge.components.ListBox model) {

            LongField urlS_PROVINCIA = new LongField(null, null);
            
            try {
                urlS_PROVINCIA.setValue( page.getHttpGetParams().getParameter("s_PROVINCIA"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter" );
                return;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter" );
                return;
            }
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select provincia,  "
                        + "denominazione "
                        + "from ad4_province "
                        + "where provincia = nvl({s_PROVINCIA},provincia) "
                        + "union "
                        + "select stato_territorio provincia,  "
                        + "denominazione "
                        + "from ad4_stati_territori "
                        + "where stato_territorio = {s_PROVINCIA} "
                        + "" );
            if ( urlS_PROVINCIA.getObjectValue() == null ) urlS_PROVINCIA.setValue( null );
            command.addParameter( "s_PROVINCIA", urlS_PROVINCIA, null );
            command.setOrder( "2" );

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

void read() { //ComuneImposta Record: method read @5-7F8AAE5A

//ComuneImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ComuneImposta Record: method read head

//ComuneImposta Record: init DataSource @5-20FE859E
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlPROVINCIA_STATO( page.getHttpGetParams().getParameter("PROVINCIA_STATO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ComuneImposta Record: init DataSource

//ComuneImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ComuneImposta Record: check errors

} //ComuneImposta Record: method read tail @5-FCB6E20C

//ComuneImposta Record: bind @5-DCE14314
            public void bind(com.codecharge.components.Component model, ComuneImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("PROVINCIA_LABEL").setValue(row.getPROVINCIA_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("COMUNE_LABEL").setValue(row.getCOMUNE_LABEL());
                model.getControl("HIDE_BEGIN2").setValue(row.getHIDE_BEGIN2());
                model.getControl("HIDE_END2").setValue(row.getHIDE_END2());
                model.getControl("TRADUZIONE").setValue(row.getTRADUZIONE());
                model.getControl("TRADUZIONE_BREVE").setValue(row.getTRADUZIONE_BREVE());
                model.getControl("TRIBUNALE_LOV").setValue(row.getTRIBUNALE_LOV());
                model.getControl("FUSIONE_LOV").setValue(row.getFUSIONE_LOV());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("PROVINCIA_STATO").setValue(row.getPROVINCIA_STATO());
                    model.getControl("COMUNE").setValue(row.getCOMUNE());
                    model.getControl("DENOMINAZIONE").setValue(row.getDENOMINAZIONE());
                    model.getControl("DENOMINAZIONE_BREVE").setValue(row.getDENOMINAZIONE_BREVE());
                    model.getControl("CAPOLUOGO_PROVINCIA").setValue(row.getCAPOLUOGO_PROVINCIA());
                    model.getControl("CAP").setValue(row.getCAP());
                    model.getControl("TRIBUNALE_DESC").setValue(row.getTRIBUNALE_DESC());
                    model.getControl("PROVINCIA_TRIBUNALE").setValue(row.getPROVINCIA_TRIBUNALE());
                    model.getControl("COMUNE_TRIBUNALE").setValue(row.getCOMUNE_TRIBUNALE());
                    model.getControl("DATA_SOPPRESSIONE").setValue(row.getDATA_SOPPRESSIONE());
                    model.getControl("FUSIONE_DESC").setValue(row.getFUSIONE_DESC());
                    model.getControl("PROVINCIA_FUSIONE").setValue(row.getPROVINCIA_FUSIONE());
                    model.getControl("COMUNE_FUSIONE").setValue(row.getCOMUNE_FUSIONE());
                    model.getControl("SIGLA_CFIS").setValue(row.getSIGLA_CFIS());
                    model.getControl("DATA_ISTITUZIONE").setValue(row.getDATA_ISTITUZIONE());
                }
            }
//End ComuneImposta Record: bind

//ComuneImposta Record: getRowFieldByName @5-04F66340
            public Object getRowFieldByName( String name, ComuneImpostaRow row ) {
                Object value = null;
                if ( "PROVINCIA_LABEL".equals(name) ) {
                    value = row.getPROVINCIA_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "PROVINCIA_STATO".equals(name) ) {
                    value = row.getPROVINCIA_STATO();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "COMUNE_LABEL".equals(name) ) {
                    value = row.getCOMUNE_LABEL();
                } else if ( "HIDE_BEGIN2".equals(name) ) {
                    value = row.getHIDE_BEGIN2();
                } else if ( "COMUNE".equals(name) ) {
                    value = row.getCOMUNE();
                } else if ( "HIDE_END2".equals(name) ) {
                    value = row.getHIDE_END2();
                } else if ( "DENOMINAZIONE".equals(name) ) {
                    value = row.getDENOMINAZIONE();
                } else if ( "TRADUZIONE".equals(name) ) {
                    value = row.getTRADUZIONE();
                } else if ( "DENOMINAZIONE_BREVE".equals(name) ) {
                    value = row.getDENOMINAZIONE_BREVE();
                } else if ( "TRADUZIONE_BREVE".equals(name) ) {
                    value = row.getTRADUZIONE_BREVE();
                } else if ( "CAPOLUOGO_PROVINCIA".equals(name) ) {
                    value = row.getCAPOLUOGO_PROVINCIA();
                } else if ( "CAP".equals(name) ) {
                    value = row.getCAP();
                } else if ( "TRIBUNALE_DESC".equals(name) ) {
                    value = row.getTRIBUNALE_DESC();
                } else if ( "TRIBUNALE_LOV".equals(name) ) {
                    value = row.getTRIBUNALE_LOV();
                } else if ( "PROVINCIA_TRIBUNALE".equals(name) ) {
                    value = row.getPROVINCIA_TRIBUNALE();
                } else if ( "COMUNE_TRIBUNALE".equals(name) ) {
                    value = row.getCOMUNE_TRIBUNALE();
                } else if ( "DATA_SOPPRESSIONE".equals(name) ) {
                    value = row.getDATA_SOPPRESSIONE();
                } else if ( "FUSIONE_DESC".equals(name) ) {
                    value = row.getFUSIONE_DESC();
                } else if ( "FUSIONE_LOV".equals(name) ) {
                    value = row.getFUSIONE_LOV();
                } else if ( "PROVINCIA_FUSIONE".equals(name) ) {
                    value = row.getPROVINCIA_FUSIONE();
                } else if ( "COMUNE_FUSIONE".equals(name) ) {
                    value = row.getCOMUNE_FUSIONE();
                } else if ( "SIGLA_CFIS".equals(name) ) {
                    value = row.getSIGLA_CFIS();
                } else if ( "DATA_ISTITUZIONE".equals(name) ) {
                    value = row.getDATA_ISTITUZIONE();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End ComuneImposta Record: getRowFieldByName

void InsertAction() { //ComuneImposta Record: method insert @5-11643485

//ComuneImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ComuneImposta Record: method insert head

//ComuneImposta Record: method insert body @5-0A2BA442
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            ComuneImpostaRow row = new ComuneImpostaRow();
            ds.setRow(row);
            row.setPROVINCIA_STATO(Utils.convertToLong(model.getControl("PROVINCIA_STATO").getValue()));
            row.setCOMUNE(Utils.convertToLong(model.getControl("COMUNE").getValue()));
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setDENOMINAZIONE_BREVE(Utils.convertToString(model.getControl("DENOMINAZIONE_BREVE").getValue()));
            row.setCAPOLUOGO_PROVINCIA(Utils.convertToString(model.getControl("CAPOLUOGO_PROVINCIA").getValue()));
            row.setCAP(Utils.convertToLong(model.getControl("CAP").getValue()));
            row.setPROVINCIA_TRIBUNALE(Utils.convertToLong(model.getControl("PROVINCIA_TRIBUNALE").getValue()));
            row.setCOMUNE_TRIBUNALE(Utils.convertToLong(model.getControl("COMUNE_TRIBUNALE").getValue()));
            row.setDATA_SOPPRESSIONE(Utils.convertToString(model.getControl("DATA_SOPPRESSIONE").getValue()));
            row.setPROVINCIA_FUSIONE(Utils.convertToLong(model.getControl("PROVINCIA_FUSIONE").getValue()));
            row.setCOMUNE_FUSIONE(Utils.convertToLong(model.getControl("COMUNE_FUSIONE").getValue()));
            row.setSIGLA_CFIS(Utils.convertToString(model.getControl("SIGLA_CFIS").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            row.setDATA_ISTITUZIONE(Utils.convertToString(model.getControl("DATA_ISTITUZIONE").getValue()));
//End ComuneImposta Record: method insert body

//ComuneImposta Record: ds.insert @5-9320B03B
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
//End ComuneImposta Record: ds.insert

} //ComuneImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //ComuneImposta Record: method update @5-5771D0AA

//ComuneImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ComuneImposta Record: method update head

//ComuneImposta Record: method update body @5-ED4BEF96
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            ComuneImpostaRow row = new ComuneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlPROVINCIA_STATO( page.getHttpGetParams().getParameter("PROVINCIA_STATO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setDENOMINAZIONE_BREVE(Utils.convertToString(model.getControl("DENOMINAZIONE_BREVE").getValue()));
            row.setCAPOLUOGO_PROVINCIA(Utils.convertToString(model.getControl("CAPOLUOGO_PROVINCIA").getValue()));
            row.setCAP(Utils.convertToLong(model.getControl("CAP").getValue()));
            row.setPROVINCIA_TRIBUNALE(Utils.convertToLong(model.getControl("PROVINCIA_TRIBUNALE").getValue()));
            row.setCOMUNE_TRIBUNALE(Utils.convertToLong(model.getControl("COMUNE_TRIBUNALE").getValue()));
            row.setDATA_SOPPRESSIONE(Utils.convertToString(model.getControl("DATA_SOPPRESSIONE").getValue()));
            row.setPROVINCIA_FUSIONE(Utils.convertToLong(model.getControl("PROVINCIA_FUSIONE").getValue()));
            row.setCOMUNE_FUSIONE(Utils.convertToLong(model.getControl("COMUNE_FUSIONE").getValue()));
            row.setSIGLA_CFIS(Utils.convertToString(model.getControl("SIGLA_CFIS").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            row.setDATA_ISTITUZIONE(Utils.convertToString(model.getControl("DATA_ISTITUZIONE").getValue()));
//End ComuneImposta Record: method update body

//ComuneImposta Record: ds.update @5-6E956EDC
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
//End ComuneImposta Record: ds.update

} //ComuneImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //ComuneImposta Record: method delete @5-11FC2E1E

//ComuneImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ComuneImposta Record: method delete head

//ComuneImposta Record: method delete body @5-20813E41
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ComuneImpostaDataObject ds = new ComuneImpostaDataObject(page);
            ds.setComponent( model );
            ComuneImpostaRow row = new ComuneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlPROVINCIA_STATO( page.getHttpGetParams().getParameter("PROVINCIA_STATO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA_STATO'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
//End ComuneImposta Record: method delete body

//ComuneImposta Record: ds.delete @5-3584344F
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
//End ComuneImposta Record: ds.delete

} //ComuneImposta Record: method delete tail @5-FCB6E20C

//ComuneImposta Record: method validate @5-597B5087
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox PROVINCIA_STATO = (com.codecharge.components.ListBox) model.getChild( "PROVINCIA_STATO" );
            if (! PROVINCIA_STATO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox COMUNE = (com.codecharge.components.TextBox) model.getChild( "COMUNE" );
            if (! COMUNE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DENOMINAZIONE = (com.codecharge.components.TextBox) model.getChild( "DENOMINAZIONE" );
            if (! DENOMINAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DENOMINAZIONE_BREVE = (com.codecharge.components.TextBox) model.getChild( "DENOMINAZIONE_BREVE" );
            if (! DENOMINAZIONE_BREVE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CAP = (com.codecharge.components.TextBox) model.getChild( "CAP" );
            if (! CAP.validate()) { isControlError = true; }

            com.codecharge.components.TextBox TRIBUNALE_DESC = (com.codecharge.components.TextBox) model.getChild( "TRIBUNALE_DESC" );
            if (! TRIBUNALE_DESC.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROVINCIA_TRIBUNALE = (com.codecharge.components.Hidden) model.getChild( "PROVINCIA_TRIBUNALE" );
            if (! PROVINCIA_TRIBUNALE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden COMUNE_TRIBUNALE = (com.codecharge.components.Hidden) model.getChild( "COMUNE_TRIBUNALE" );
            if (! COMUNE_TRIBUNALE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DATA_SOPPRESSIONE = (com.codecharge.components.TextBox) model.getChild( "DATA_SOPPRESSIONE" );
            if (! DATA_SOPPRESSIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox FUSIONE_DESC = (com.codecharge.components.TextBox) model.getChild( "FUSIONE_DESC" );
            if (! FUSIONE_DESC.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROVINCIA_FUSIONE = (com.codecharge.components.Hidden) model.getChild( "PROVINCIA_FUSIONE" );
            if (! PROVINCIA_FUSIONE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden COMUNE_FUSIONE = (com.codecharge.components.Hidden) model.getChild( "COMUNE_FUSIONE" );
            if (! COMUNE_FUSIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox SIGLA_CFIS = (com.codecharge.components.TextBox) model.getChild( "SIGLA_CFIS" );
            if (! SIGLA_CFIS.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DATA_ISTITUZIONE = (com.codecharge.components.TextBox) model.getChild( "DATA_ISTITUZIONE" );
            if (! DATA_ISTITUZIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ComuneImposta Record: method validate

//ComuneImposta Record Tail @5-FCB6E20C
    }
//End ComuneImposta Record Tail

//Ad4DizionariComuneImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariComuneImposta Page: method validate

//Ad4DizionariComuneImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariComuneImpostaAction Tail

