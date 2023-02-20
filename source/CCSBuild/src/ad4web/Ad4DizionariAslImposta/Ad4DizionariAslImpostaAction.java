//Ad4DizionariAslImpostaAction imports @1-742B1477
package ad4web.Ad4DizionariAslImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariAslImpostaAction imports

//Ad4DizionariAslImpostaAction class @1-410B8236
public class Ad4DizionariAslImpostaAction extends Action {

//End Ad4DizionariAslImpostaAction class

//Ad4DizionariAslImpostaAction: method perform @1-367BB1A2
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariAslImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariAslImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariAslImpostaAction: method perform

//Ad4DizionariAslImpostaAction: call children actions @1-A1A3B34F
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            AslImpostaClass AslImposta = new AslImpostaClass();
            if ( ( redirect = AslImposta.perform( page.getRecord("AslImposta")) ) != null ) result = redirect;
        }
        if ( page.getChild( "Ad4DizionariAslComuniElenco" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariAslComuniElencoParent",page);
            ad4web.Ad4DizionariAslComuniElenco.Ad4DizionariAslComuniElencoAction Ad4DizionariAslComuniElenco = new ad4web.Ad4DizionariAslComuniElenco.Ad4DizionariAslComuniElencoAction();
            result = result != null ? result : Ad4DizionariAslComuniElenco.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariAslImpostaAction: call children actions

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

//AslImposta Record @5-B0A11CEF
    final class AslImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AslImposta Record

//AslImposta Record: method perform @5-8CBD5765
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
//End AslImposta Record: method perform

//AslImposta Record: children actions @5-313B56C1
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AslImposta".equals(formName)) {
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
            readREGIONE_ASL(model.getListBox("REGIONE_ASL"));
//End AslImposta Record: children actions

//AslImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AslImposta Record: method perform tail

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

//ListBoxAction @40-5EC00620
        protected void readREGIONE_ASL(com.codecharge.components.ListBox model) {
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

void read() { //AslImposta Record: method read @5-7F8AAE5A

//AslImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AslImposta Record: method read head

//AslImposta Record: init DataSource @5-A554295E
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AslImpostaDataObject ds = new AslImpostaDataObject(page);
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
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AslImposta Record: init DataSource

//AslImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AslImposta Record: check errors

} //AslImposta Record: method read tail @5-FCB6E20C

//AslImposta Record: bind @5-56AD07E4
            public void bind(com.codecharge.components.Component model, AslImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("CODICE_ASL_LABEL").setValue(row.getCODICE_ASL_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("REGIONE_ASL").setValue(row.getREGIONE_ASL());
                    model.getControl("CODICE_ASL").setValue(row.getCODICE_ASL());
                    model.getControl("DESCRIZIONE").setValue(row.getDESCRIZIONE());
                    model.getControl("ATTIVA").setValue(row.getATTIVA());
                }
            }
//End AslImposta Record: bind

//AslImposta Record: getRowFieldByName @5-75C8E617
            public Object getRowFieldByName( String name, AslImpostaRow row ) {
                Object value = null;
                if ( "REGIONE_ASL".equals(name) ) {
                    value = row.getREGIONE_ASL();
                } else if ( "CODICE_ASL_LABEL".equals(name) ) {
                    value = row.getCODICE_ASL_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "CODICE_ASL".equals(name) ) {
                    value = row.getCODICE_ASL();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "DESCRIZIONE".equals(name) ) {
                    value = row.getDESCRIZIONE();
                } else if ( "ATTIVA".equals(name) ) {
                    value = row.getATTIVA();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End AslImposta Record: getRowFieldByName

void InsertAction() { //AslImposta Record: method insert @5-11643485

//AslImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AslImposta Record: method insert head

//AslImposta Record: method insert body @5-7E9EFE7B
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            AslImpostaDataObject ds = new AslImpostaDataObject(page);
            ds.setComponent( model );
            AslImpostaRow row = new AslImpostaRow();
            ds.setRow(row);
            row.setREGIONE_ASL(Utils.convertToLong(model.getControl("REGIONE_ASL").getValue()));
            row.setCODICE_ASL(Utils.convertToLong(model.getControl("CODICE_ASL").getValue()));
            row.setDESCRIZIONE(Utils.convertToString(model.getControl("DESCRIZIONE").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            row.setATTIVA(Utils.convertToString(model.getControl("ATTIVA").getValue()));
//End AslImposta Record: method insert body

//AslImposta Record: ds.insert @5-9320B03B
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
//End AslImposta Record: ds.insert

} //AslImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //AslImposta Record: method update @5-5771D0AA

//AslImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AslImposta Record: method update head

//AslImposta Record: method update body @5-64BB8D20
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AslImpostaDataObject ds = new AslImpostaDataObject(page);
            ds.setComponent( model );
            AslImpostaRow row = new AslImpostaRow();
            ds.setRow(row);
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
            row.setDESCRIZIONE(Utils.convertToString(model.getControl("DESCRIZIONE").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            row.setATTIVA(Utils.convertToString(model.getControl("ATTIVA").getValue()));
//End AslImposta Record: method update body

//AslImposta Record: ds.update @5-6E956EDC
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
//End AslImposta Record: ds.update

} //AslImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //AslImposta Record: method delete @5-11FC2E1E

//AslImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AslImposta Record: method delete head

//AslImposta Record: method delete body @5-9EBFD2C1
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AslImpostaDataObject ds = new AslImpostaDataObject(page);
            ds.setComponent( model );
            AslImpostaRow row = new AslImpostaRow();
            ds.setRow(row);
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
//End AslImposta Record: method delete body

//AslImposta Record: ds.delete @5-3584344F
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
//End AslImposta Record: ds.delete

} //AslImposta Record: method delete tail @5-FCB6E20C

//AslImposta Record: method validate @5-D5F9EEF4
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox REGIONE_ASL = (com.codecharge.components.ListBox) model.getChild( "REGIONE_ASL" );
            if (! REGIONE_ASL.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CODICE_ASL = (com.codecharge.components.TextBox) model.getChild( "CODICE_ASL" );
            if (! CODICE_ASL.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "DESCRIZIONE" );
            if (! DESCRIZIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AslImposta Record: method validate

//AslImposta Record Tail @5-FCB6E20C
    }
//End AslImposta Record Tail

//Ad4DizionariAslImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariAslImposta Page: method validate

//Ad4DizionariAslImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariAslImpostaAction Tail

