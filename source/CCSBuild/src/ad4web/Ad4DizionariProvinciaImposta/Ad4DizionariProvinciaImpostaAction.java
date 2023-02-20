//Ad4DizionariProvinciaImpostaAction imports @1-1C27A5FF
package ad4web.Ad4DizionariProvinciaImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariProvinciaImpostaAction imports

//Ad4DizionariProvinciaImpostaAction class @1-674BF446
public class Ad4DizionariProvinciaImpostaAction extends Action {

//End Ad4DizionariProvinciaImpostaAction class

//Ad4DizionariProvinciaImpostaAction: method perform @1-88CD8701
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariProvinciaImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariProvinciaImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariProvinciaImpostaAction: method perform

//Ad4DizionariProvinciaImpostaAction: call children actions @1-0E553AE6
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            ProvinciaImpostaClass ProvinciaImposta = new ProvinciaImpostaClass();
            if ( ( redirect = ProvinciaImposta.perform( page.getRecord("ProvinciaImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariProvinciaImpostaAction: call children actions

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

//ProvinciaImposta Record @5-791CEE1D
    final class ProvinciaImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ProvinciaImposta Record

//ProvinciaImposta Record: method perform @5-8CBD5765
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
//End ProvinciaImposta Record: method perform

//ProvinciaImposta Record: children actions @5-56A676D3
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ProvinciaImposta".equals(formName)) {
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
            readREGIONE(model.getListBox("REGIONE"));
//End ProvinciaImposta Record: children actions

//ProvinciaImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ProvinciaImposta Record: method perform tail

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

//ListBoxAction @40-73141138
        protected void readREGIONE(com.codecharge.components.ListBox model) {
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

void read() { //ProvinciaImposta Record: method read @5-7F8AAE5A

//ProvinciaImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ProvinciaImposta Record: method read head

//ProvinciaImposta Record: init DataSource @5-67B4AB97
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ProvinciaImpostaDataObject ds = new ProvinciaImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ProvinciaImposta Record: init DataSource

//ProvinciaImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ProvinciaImposta Record: check errors

} //ProvinciaImposta Record: method read tail @5-FCB6E20C

//ProvinciaImposta Record: bind @5-EF199D0D
            public void bind(com.codecharge.components.Component model, ProvinciaImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("PROVINCIA_LABEL").setValue(row.getPROVINCIA_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("PROVINCIA").setValue(row.getPROVINCIA());
                    model.getControl("DENOMINAZIONE").setValue(row.getDENOMINAZIONE());
                    model.getControl("SIGLA").setValue(row.getSIGLA());
                    model.getControl("REGIONE").setValue(row.getREGIONE());
                }
            }
//End ProvinciaImposta Record: bind

//ProvinciaImposta Record: getRowFieldByName @5-16376081
            public Object getRowFieldByName( String name, ProvinciaImpostaRow row ) {
                Object value = null;
                if ( "PROVINCIA_LABEL".equals(name) ) {
                    value = row.getPROVINCIA_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "PROVINCIA".equals(name) ) {
                    value = row.getPROVINCIA();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "DENOMINAZIONE".equals(name) ) {
                    value = row.getDENOMINAZIONE();
                } else if ( "SIGLA".equals(name) ) {
                    value = row.getSIGLA();
                } else if ( "REGIONE".equals(name) ) {
                    value = row.getREGIONE();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End ProvinciaImposta Record: getRowFieldByName

void InsertAction() { //ProvinciaImposta Record: method insert @5-11643485

//ProvinciaImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ProvinciaImposta Record: method insert head

//ProvinciaImposta Record: method insert body @5-73CD9A33
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            ProvinciaImpostaDataObject ds = new ProvinciaImpostaDataObject(page);
            ds.setComponent( model );
            ProvinciaImpostaRow row = new ProvinciaImpostaRow();
            ds.setRow(row);
            row.setPROVINCIA(Utils.convertToLong(model.getControl("PROVINCIA").getValue()));
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setREGIONE(Utils.convertToLong(model.getControl("REGIONE").getValue()));
            row.setSIGLA(Utils.convertToString(model.getControl("SIGLA").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End ProvinciaImposta Record: method insert body

//ProvinciaImposta Record: ds.insert @5-9320B03B
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
//End ProvinciaImposta Record: ds.insert

} //ProvinciaImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //ProvinciaImposta Record: method update @5-5771D0AA

//ProvinciaImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ProvinciaImposta Record: method update head

//ProvinciaImposta Record: method update body @5-44F8EC2C
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ProvinciaImpostaDataObject ds = new ProvinciaImpostaDataObject(page);
            ds.setComponent( model );
            ProvinciaImpostaRow row = new ProvinciaImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setREGIONE(Utils.convertToLong(model.getControl("REGIONE").getValue()));
            row.setSIGLA(Utils.convertToString(model.getControl("SIGLA").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End ProvinciaImposta Record: method update body

//ProvinciaImposta Record: ds.update @5-6E956EDC
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
//End ProvinciaImposta Record: ds.update

} //ProvinciaImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //ProvinciaImposta Record: method delete @5-11FC2E1E

//ProvinciaImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ProvinciaImposta Record: method delete head

//ProvinciaImposta Record: method delete body @5-F6021A1B
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ProvinciaImpostaDataObject ds = new ProvinciaImpostaDataObject(page);
            ds.setComponent( model );
            ProvinciaImpostaRow row = new ProvinciaImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
//End ProvinciaImposta Record: method delete body

//ProvinciaImposta Record: ds.delete @5-3584344F
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
//End ProvinciaImposta Record: ds.delete

} //ProvinciaImposta Record: method delete tail @5-FCB6E20C

//ProvinciaImposta Record: method validate @5-B3D9C5B4
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox PROVINCIA = (com.codecharge.components.TextBox) model.getChild( "PROVINCIA" );
            if (! PROVINCIA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DENOMINAZIONE = (com.codecharge.components.TextBox) model.getChild( "DENOMINAZIONE" );
            if (! DENOMINAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox SIGLA = (com.codecharge.components.TextBox) model.getChild( "SIGLA" );
            if (! SIGLA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox REGIONE = (com.codecharge.components.ListBox) model.getChild( "REGIONE" );
            if (! REGIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ProvinciaImposta Record: method validate

//ProvinciaImposta Record Tail @5-FCB6E20C
    }
//End ProvinciaImposta Record Tail

//Ad4DizionariProvinciaImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariProvinciaImposta Page: method validate

//Ad4DizionariProvinciaImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariProvinciaImpostaAction Tail

