//Ad4DizionariBancaImpostaAction imports @1-4ACF7767
package restrict.Ad4DizionariBancaImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariBancaImpostaAction imports

//Ad4DizionariBancaImpostaAction class @1-B7A9E489
public class Ad4DizionariBancaImpostaAction extends Action {

//End Ad4DizionariBancaImpostaAction class

//Ad4DizionariBancaImpostaAction: method perform @1-C291331D
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariBancaImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariBancaImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariBancaImpostaAction: method perform

//Ad4DizionariBancaImpostaAction: call children actions @1-746B1EF4
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            BancaImpostaClass BancaImposta = new BancaImpostaClass();
            if ( ( redirect = BancaImposta.perform( page.getRecord("BancaImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariBancaImpostaAction: call children actions

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

//BancaImposta Record @5-DD90209A
    final class BancaImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End BancaImposta Record

//BancaImposta Record: method perform @5-8CBD5765
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
//End BancaImposta Record: method perform

//BancaImposta Record: children actions @5-68AB43D9
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("BancaImposta".equals(formName)) {
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
//End BancaImposta Record: children actions

//BancaImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End BancaImposta Record: method perform tail

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

void read() { //BancaImposta Record: method read @5-7F8AAE5A

//BancaImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End BancaImposta Record: method read head

//BancaImposta Record: init DataSource @5-9FD017B4
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            BancaImpostaDataObject ds = new BancaImpostaDataObject(page);
            ds.setComponent( model );
            ds.setUrlBANCA( page.getHttpGetParams().getParameter("BANCA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End BancaImposta Record: init DataSource

//BancaImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End BancaImposta Record: check errors

} //BancaImposta Record: method read tail @5-FCB6E20C

//BancaImposta Record: bind @5-26475E8D
            public void bind(com.codecharge.components.Component model, BancaImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("BANCA_LABEL").setValue(row.getBANCA_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("TRADUZIONE").setValue(row.getTRADUZIONE());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("BANCA").setValue(row.getBANCA());
                    model.getControl("DENOMINAZIONE").setValue(row.getDENOMINAZIONE());
                    model.getControl("CIN_ABI").setValue(row.getCIN_ABI());
                }
            }
//End BancaImposta Record: bind

//BancaImposta Record: getRowFieldByName @5-A0477D19
            public Object getRowFieldByName( String name, BancaImpostaRow row ) {
                Object value = null;
                if ( "BANCA_LABEL".equals(name) ) {
                    value = row.getBANCA_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "BANCA".equals(name) ) {
                    value = row.getBANCA();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "DENOMINAZIONE".equals(name) ) {
                    value = row.getDENOMINAZIONE();
                } else if ( "TRADUZIONE".equals(name) ) {
                    value = row.getTRADUZIONE();
                } else if ( "CIN_ABI".equals(name) ) {
                    value = row.getCIN_ABI();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End BancaImposta Record: getRowFieldByName

void InsertAction() { //BancaImposta Record: method insert @5-11643485

//BancaImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End BancaImposta Record: method insert head

//BancaImposta Record: method insert body @5-9BF1C460
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            BancaImpostaDataObject ds = new BancaImpostaDataObject(page);
            ds.setComponent( model );
            BancaImpostaRow row = new BancaImpostaRow();
            ds.setRow(row);
            row.setBANCA(Utils.convertToString(model.getControl("BANCA").getValue()));
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setCIN_ABI(Utils.convertToString(model.getControl("CIN_ABI").getValue()));
//End BancaImposta Record: method insert body

//BancaImposta Record: ds.insert @5-9320B03B
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
//End BancaImposta Record: ds.insert

} //BancaImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //BancaImposta Record: method update @5-5771D0AA

//BancaImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End BancaImposta Record: method update head

//BancaImposta Record: method update body @5-B383EB21
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            BancaImpostaDataObject ds = new BancaImpostaDataObject(page);
            ds.setComponent( model );
            BancaImpostaRow row = new BancaImpostaRow();
            ds.setRow(row);
            ds.setUrlBANCA( page.getHttpGetParams().getParameter("BANCA") );
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setCIN_ABI(Utils.convertToString(model.getControl("CIN_ABI").getValue()));
//End BancaImposta Record: method update body

//BancaImposta Record: ds.update @5-6E956EDC
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
//End BancaImposta Record: ds.update

} //BancaImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //BancaImposta Record: method delete @5-11FC2E1E

//BancaImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End BancaImposta Record: method delete head

//BancaImposta Record: method delete body @5-67ED227D
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            BancaImpostaDataObject ds = new BancaImpostaDataObject(page);
            ds.setComponent( model );
            BancaImpostaRow row = new BancaImpostaRow();
            ds.setRow(row);
            ds.setUrlBANCA( page.getHttpGetParams().getParameter("BANCA") );
//End BancaImposta Record: method delete body

//BancaImposta Record: ds.delete @5-3584344F
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
//End BancaImposta Record: ds.delete

} //BancaImposta Record: method delete tail @5-FCB6E20C

//BancaImposta Record: method validate @5-7B6F63AB
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox BANCA = (com.codecharge.components.TextBox) model.getChild( "BANCA" );
            if (! BANCA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DENOMINAZIONE = (com.codecharge.components.TextBox) model.getChild( "DENOMINAZIONE" );
            if (! DENOMINAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CIN_ABI = (com.codecharge.components.TextBox) model.getChild( "CIN_ABI" );
            if (! CIN_ABI.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End BancaImposta Record: method validate

//BancaImposta Record Tail @5-FCB6E20C
    }
//End BancaImposta Record Tail

//Ad4DizionariBancaImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariBancaImposta Page: method validate

//Ad4DizionariBancaImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariBancaImpostaAction Tail
