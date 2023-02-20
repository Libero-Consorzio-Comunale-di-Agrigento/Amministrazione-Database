//Ad4DizionariRegioneImpostaAction imports @1-C31D65BB
package restrict.Ad4DizionariRegioneImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariRegioneImpostaAction imports

//Ad4DizionariRegioneImpostaAction class @1-995ED485
public class Ad4DizionariRegioneImpostaAction extends Action {

//End Ad4DizionariRegioneImpostaAction class

//Ad4DizionariRegioneImpostaAction: method perform @1-3D77381B
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariRegioneImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariRegioneImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariRegioneImpostaAction: method perform

//Ad4DizionariRegioneImpostaAction: call children actions @1-16831EB0
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            RegioneImpostaClass RegioneImposta = new RegioneImpostaClass();
            if ( ( redirect = RegioneImposta.perform( page.getRecord("RegioneImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariRegioneImpostaAction: call children actions

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

//RegioneImposta Record @5-F9814154
    final class RegioneImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End RegioneImposta Record

//RegioneImposta Record: method perform @5-8CBD5765
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
//End RegioneImposta Record: method perform

//RegioneImposta Record: children actions @5-6CD4999A
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("RegioneImposta".equals(formName)) {
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
//End RegioneImposta Record: children actions

//RegioneImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End RegioneImposta Record: method perform tail

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

void read() { //RegioneImposta Record: method read @5-7F8AAE5A

//RegioneImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End RegioneImposta Record: method read head

//RegioneImposta Record: init DataSource @5-6D98AA34
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            RegioneImpostaDataObject ds = new RegioneImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlREGIONE( page.getHttpGetParams().getParameter("REGIONE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'REGIONE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'REGIONE'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End RegioneImposta Record: init DataSource

//RegioneImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End RegioneImposta Record: check errors

} //RegioneImposta Record: method read tail @5-FCB6E20C

//RegioneImposta Record: bind @5-35A56EAF
            public void bind(com.codecharge.components.Component model, RegioneImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("REGIONE_LABEL").setValue(row.getREGIONE_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("REGIONE").setValue(row.getREGIONE());
                    model.getControl("DENOMINAZIONE").setValue(row.getDENOMINAZIONE());
                    model.getControl("ID_REGIONE").setValue(row.getID_REGIONE());
                }
            }
//End RegioneImposta Record: bind

//RegioneImposta Record: getRowFieldByName @5-892FFB7A
            public Object getRowFieldByName( String name, RegioneImpostaRow row ) {
                Object value = null;
                if ( "REGIONE_LABEL".equals(name) ) {
                    value = row.getREGIONE_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "REGIONE".equals(name) ) {
                    value = row.getREGIONE();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "DENOMINAZIONE".equals(name) ) {
                    value = row.getDENOMINAZIONE();
                } else if ( "ID_REGIONE".equals(name) ) {
                    value = row.getID_REGIONE();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End RegioneImposta Record: getRowFieldByName

void InsertAction() { //RegioneImposta Record: method insert @5-11643485

//RegioneImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End RegioneImposta Record: method insert head

//RegioneImposta Record: method insert body @5-56C0FEC3
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            RegioneImpostaDataObject ds = new RegioneImpostaDataObject(page);
            ds.setComponent( model );
            RegioneImpostaRow row = new RegioneImpostaRow();
            ds.setRow(row);
            row.setREGIONE(Utils.convertToLong(model.getControl("REGIONE").getValue()));
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setID_REGIONE(Utils.convertToLong(model.getControl("ID_REGIONE").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End RegioneImposta Record: method insert body

//RegioneImposta Record: ds.insert @5-9320B03B
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
//End RegioneImposta Record: ds.insert

} //RegioneImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //RegioneImposta Record: method update @5-5771D0AA

//RegioneImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End RegioneImposta Record: method update head

//RegioneImposta Record: method update body @5-63647554
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            RegioneImpostaDataObject ds = new RegioneImpostaDataObject(page);
            ds.setComponent( model );
            RegioneImpostaRow row = new RegioneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlREGIONE( page.getHttpGetParams().getParameter("REGIONE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'REGIONE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'REGIONE'" );
            }
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setID_REGIONE(Utils.convertToLong(model.getControl("ID_REGIONE").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End RegioneImposta Record: method update body

//RegioneImposta Record: ds.update @5-6E956EDC
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
//End RegioneImposta Record: ds.update

} //RegioneImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //RegioneImposta Record: method delete @5-11FC2E1E

//RegioneImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End RegioneImposta Record: method delete head

//RegioneImposta Record: method delete body @5-B222623D
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            RegioneImpostaDataObject ds = new RegioneImpostaDataObject(page);
            ds.setComponent( model );
            RegioneImpostaRow row = new RegioneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlREGIONE( page.getHttpGetParams().getParameter("REGIONE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'REGIONE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'REGIONE'" );
            }
//End RegioneImposta Record: method delete body

//RegioneImposta Record: ds.delete @5-3584344F
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
//End RegioneImposta Record: ds.delete

} //RegioneImposta Record: method delete tail @5-FCB6E20C

//RegioneImposta Record: method validate @5-7DC7F430
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox REGIONE = (com.codecharge.components.TextBox) model.getChild( "REGIONE" );
            if (! REGIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DENOMINAZIONE = (com.codecharge.components.TextBox) model.getChild( "DENOMINAZIONE" );
            if (! DENOMINAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox ID_REGIONE = (com.codecharge.components.TextBox) model.getChild( "ID_REGIONE" );
            if (! ID_REGIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End RegioneImposta Record: method validate

//RegioneImposta Record Tail @5-FCB6E20C
    }
//End RegioneImposta Record Tail

//Ad4DizionariRegioneImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariRegioneImposta Page: method validate

//Ad4DizionariRegioneImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariRegioneImpostaAction Tail

