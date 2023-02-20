//Ad4DizionariZonaAslImpostaAction imports @1-DD4B03A5
package ad4web.Ad4DizionariZonaAslImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariZonaAslImpostaAction imports

//Ad4DizionariZonaAslImpostaAction class @1-424746AF
public class Ad4DizionariZonaAslImpostaAction extends Action {

//End Ad4DizionariZonaAslImpostaAction class

//Ad4DizionariZonaAslImpostaAction: method perform @1-58262D43
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariZonaAslImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariZonaAslImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariZonaAslImpostaAction: method perform

//Ad4DizionariZonaAslImpostaAction: call children actions @1-F5FF482B
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            ZonaAslImpostaClass ZonaAslImposta = new ZonaAslImpostaClass();
            if ( ( redirect = ZonaAslImposta.perform( page.getRecord("ZonaAslImposta")) ) != null ) result = redirect;
        }
        if ( page.getChild( "Ad4DizionariZoneAslComuniElenco" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariZoneAslComuniElencoParent",page);
            ad4web.Ad4DizionariZoneAslComuniElenco.Ad4DizionariZoneAslComuniElencoAction Ad4DizionariZoneAslComuniElenco = new ad4web.Ad4DizionariZoneAslComuniElenco.Ad4DizionariZoneAslComuniElencoAction();
            result = result != null ? result : Ad4DizionariZoneAslComuniElenco.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariZonaAslImpostaAction: call children actions

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

//ZonaAslImposta Record @5-8FD7D584
    final class ZonaAslImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ZonaAslImposta Record

//ZonaAslImposta Record: method perform @5-8CBD5765
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
//End ZonaAslImposta Record: method perform

//ZonaAslImposta Record: children actions @5-6EB0C01C
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ZonaAslImposta".equals(formName)) {
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
            readCODICE_REGIONE(model.getListBox("CODICE_REGIONE"));
//End ZonaAslImposta Record: children actions

//ZonaAslImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ZonaAslImposta Record: method perform tail

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

//ListBoxAction @40-9355A32B
        protected void readCODICE_REGIONE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select regione "
                        + "      ,denominazione "
                        + "  from ad4_regioni" );
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

void read() { //ZonaAslImposta Record: method read @5-7F8AAE5A

//ZonaAslImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ZonaAslImposta Record: method read head

//ZonaAslImposta Record: init DataSource @5-04F2AF99
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ZonaAslImpostaDataObject ds = new ZonaAslImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlID_ZONA_ASL( page.getHttpGetParams().getParameter("ID_ZONA_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ZonaAslImposta Record: init DataSource

//ZonaAslImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ZonaAslImposta Record: check errors

} //ZonaAslImposta Record: method read tail @5-FCB6E20C

//ZonaAslImposta Record: bind @5-771FFFC1
            public void bind(com.codecharge.components.Component model, ZonaAslImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("ID_ZONA_ASL_LABEL").setValue(row.getID_ZONA_ASL_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("ID_ZONA_ASL").setValue(row.getID_ZONA_ASL());
                    model.getControl("CODICE_REGIONE").setValue(row.getCODICE_REGIONE());
                    model.getControl("CODICE_ZONA").setValue(row.getCODICE_ZONA());
                    model.getControl("TITOLO").setValue(row.getTITOLO());
                    model.getControl("VAL_DISTRETTO_LEA").setValue(row.getVAL_DISTRETTO_LEA());
                }
            }
//End ZonaAslImposta Record: bind

//ZonaAslImposta Record: getRowFieldByName @5-797848CF
            public Object getRowFieldByName( String name, ZonaAslImpostaRow row ) {
                Object value = null;
                if ( "ID_ZONA_ASL_LABEL".equals(name) ) {
                    value = row.getID_ZONA_ASL_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "ID_ZONA_ASL".equals(name) ) {
                    value = row.getID_ZONA_ASL();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "CODICE_REGIONE".equals(name) ) {
                    value = row.getCODICE_REGIONE();
                } else if ( "CODICE_ZONA".equals(name) ) {
                    value = row.getCODICE_ZONA();
                } else if ( "TITOLO".equals(name) ) {
                    value = row.getTITOLO();
                } else if ( "VAL_DISTRETTO_LEA".equals(name) ) {
                    value = row.getVAL_DISTRETTO_LEA();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End ZonaAslImposta Record: getRowFieldByName

void InsertAction() { //ZonaAslImposta Record: method insert @5-11643485

//ZonaAslImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ZonaAslImposta Record: method insert head

//ZonaAslImposta Record: method insert body @5-DB8047BE
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            ZonaAslImpostaDataObject ds = new ZonaAslImpostaDataObject(page);
            ds.setComponent( model );
            ZonaAslImpostaRow row = new ZonaAslImpostaRow();
            ds.setRow(row);
            row.setID_ZONA_ASL(Utils.convertToLong(model.getControl("ID_ZONA_ASL").getValue()));
            row.setCODICE_REGIONE(Utils.convertToLong(model.getControl("CODICE_REGIONE").getValue()));
            row.setCODICE_ZONA(Utils.convertToString(model.getControl("CODICE_ZONA").getValue()));
            row.setTITOLO(Utils.convertToString(model.getControl("TITOLO").getValue()));
            row.setVAL_DISTRETTO_LEA(Utils.convertToString(model.getControl("VAL_DISTRETTO_LEA").getValue()));
//End ZonaAslImposta Record: method insert body

//ZonaAslImposta Record: ds.insert @5-9320B03B
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
//End ZonaAslImposta Record: ds.insert

} //ZonaAslImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //ZonaAslImposta Record: method update @5-5771D0AA

//ZonaAslImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ZonaAslImposta Record: method update head

//ZonaAslImposta Record: method update body @5-6E59D2DA
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ZonaAslImpostaDataObject ds = new ZonaAslImpostaDataObject(page);
            ds.setComponent( model );
            ZonaAslImpostaRow row = new ZonaAslImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlID_ZONA_ASL( page.getHttpGetParams().getParameter("ID_ZONA_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            }
            row.setCODICE_REGIONE(Utils.convertToLong(model.getControl("CODICE_REGIONE").getValue()));
            row.setCODICE_ZONA(Utils.convertToString(model.getControl("CODICE_ZONA").getValue()));
            row.setTITOLO(Utils.convertToString(model.getControl("TITOLO").getValue()));
            row.setVAL_DISTRETTO_LEA(Utils.convertToString(model.getControl("VAL_DISTRETTO_LEA").getValue()));
//End ZonaAslImposta Record: method update body

//ZonaAslImposta Record: ds.update @5-6E956EDC
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
//End ZonaAslImposta Record: ds.update

} //ZonaAslImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //ZonaAslImposta Record: method delete @5-11FC2E1E

//ZonaAslImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ZonaAslImposta Record: method delete head

//ZonaAslImposta Record: method delete body @5-E9D6172D
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ZonaAslImpostaDataObject ds = new ZonaAslImpostaDataObject(page);
            ds.setComponent( model );
            ZonaAslImpostaRow row = new ZonaAslImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlID_ZONA_ASL( page.getHttpGetParams().getParameter("ID_ZONA_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            }
//End ZonaAslImposta Record: method delete body

//ZonaAslImposta Record: ds.delete @5-3584344F
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
//End ZonaAslImposta Record: ds.delete

} //ZonaAslImposta Record: method delete tail @5-FCB6E20C

//ZonaAslImposta Record: method validate @5-9D02E2B3
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox ID_ZONA_ASL = (com.codecharge.components.TextBox) model.getChild( "ID_ZONA_ASL" );
            if (! ID_ZONA_ASL.validate()) { isControlError = true; }

            com.codecharge.components.ListBox CODICE_REGIONE = (com.codecharge.components.ListBox) model.getChild( "CODICE_REGIONE" );
            if (! CODICE_REGIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CODICE_ZONA = (com.codecharge.components.TextBox) model.getChild( "CODICE_ZONA" );
            if (! CODICE_ZONA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox TITOLO = (com.codecharge.components.TextBox) model.getChild( "TITOLO" );
            if (! TITOLO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox VAL_DISTRETTO_LEA = (com.codecharge.components.TextBox) model.getChild( "VAL_DISTRETTO_LEA" );
            if (! VAL_DISTRETTO_LEA.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ZonaAslImposta Record: method validate

//ZonaAslImposta Record Tail @5-FCB6E20C
    }
//End ZonaAslImposta Record Tail

//Ad4DizionariZonaAslImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariZonaAslImposta Page: method validate

//Ad4DizionariZonaAslImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariZonaAslImpostaAction Tail

