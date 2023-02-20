//Ad4DizionariStatoTerritorioImpostaAction imports @1-3F153E45
package ad4web.Ad4DizionariStatoTerritorioImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariStatoTerritorioImpostaAction imports

//Ad4DizionariStatoTerritorioImpostaAction class @1-496E8BBF
public class Ad4DizionariStatoTerritorioImpostaAction extends Action {

//End Ad4DizionariStatoTerritorioImpostaAction class

//Ad4DizionariStatoTerritorioImpostaAction: method perform @1-416F41FD
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariStatoTerritorioImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariStatoTerritorioImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariStatoTerritorioImpostaAction: method perform

//Ad4DizionariStatoTerritorioImpostaAction: call children actions @1-1B82E027
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            StatoTerritorioImpostaClass StatoTerritorioImposta = new StatoTerritorioImpostaClass();
            if ( ( redirect = StatoTerritorioImposta.perform( page.getRecord("StatoTerritorioImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariStatoTerritorioImpostaAction: call children actions

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

//StatoTerritorioImposta Record @5-6DCFA4E7
    final class StatoTerritorioImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End StatoTerritorioImposta Record

//StatoTerritorioImposta Record: method perform @5-8CBD5765
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
//End StatoTerritorioImposta Record: method perform

//StatoTerritorioImposta Record: children actions @5-50AE8D1B
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("StatoTerritorioImposta".equals(formName)) {
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
//End StatoTerritorioImposta Record: children actions

//StatoTerritorioImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End StatoTerritorioImposta Record: method perform tail

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

void read() { //StatoTerritorioImposta Record: method read @5-7F8AAE5A

//StatoTerritorioImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End StatoTerritorioImposta Record: method read head

//StatoTerritorioImposta Record: init DataSource @5-E184792C
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            StatoTerritorioImpostaDataObject ds = new StatoTerritorioImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlSTATO_TERRITORIO( page.getHttpGetParams().getParameter("STATO_TERRITORIO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'STATO_TERRITORIO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'STATO_TERRITORIO'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End StatoTerritorioImposta Record: init DataSource

//StatoTerritorioImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End StatoTerritorioImposta Record: check errors

} //StatoTerritorioImposta Record: method read tail @5-FCB6E20C

//StatoTerritorioImposta Record: bind @5-61172B64
            public void bind(com.codecharge.components.Component model, StatoTerritorioImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("STATO_TERRITORIO_LABEL").setValue(row.getSTATO_TERRITORIO_LABEL());
                model.getControl("HIDE_BEGIN").setValue(row.getHIDE_BEGIN());
                model.getControl("HIDE_END").setValue(row.getHIDE_END());
                model.getControl("LABEL_UPD").setValue(row.getLABEL_UPD());
                if ( this.valid ) {
                    model.getControl("STATO_TERRITORIO").setValue(row.getSTATO_TERRITORIO());
                    model.getControl("DENOMINAZIONE").setValue(row.getDENOMINAZIONE());
                    model.getControl("SIGLA").setValue(row.getSIGLA());
                    model.getControl("DESC_CITTADINANZA").setValue(row.getDESC_CITTADINANZA());
                    model.getControl("RAGGRUPPAMENTO").setValue(row.getRAGGRUPPAMENTO());
                    model.getControl("STATO_APPARTENENZA").setValue(row.getSTATO_APPARTENENZA());
                }
            }
//End StatoTerritorioImposta Record: bind

//StatoTerritorioImposta Record: getRowFieldByName @5-644764BC
            public Object getRowFieldByName( String name, StatoTerritorioImpostaRow row ) {
                Object value = null;
                if ( "STATO_TERRITORIO_LABEL".equals(name) ) {
                    value = row.getSTATO_TERRITORIO_LABEL();
                } else if ( "HIDE_BEGIN".equals(name) ) {
                    value = row.getHIDE_BEGIN();
                } else if ( "STATO_TERRITORIO".equals(name) ) {
                    value = row.getSTATO_TERRITORIO();
                } else if ( "HIDE_END".equals(name) ) {
                    value = row.getHIDE_END();
                } else if ( "DENOMINAZIONE".equals(name) ) {
                    value = row.getDENOMINAZIONE();
                } else if ( "SIGLA".equals(name) ) {
                    value = row.getSIGLA();
                } else if ( "DESC_CITTADINANZA".equals(name) ) {
                    value = row.getDESC_CITTADINANZA();
                } else if ( "RAGGRUPPAMENTO".equals(name) ) {
                    value = row.getRAGGRUPPAMENTO();
                } else if ( "STATO_APPARTENENZA".equals(name) ) {
                    value = row.getSTATO_APPARTENENZA();
                } else if ( "LABEL_UPD".equals(name) ) {
                    value = row.getLABEL_UPD();
                }
                return value;
            }
//End StatoTerritorioImposta Record: getRowFieldByName

void InsertAction() { //StatoTerritorioImposta Record: method insert @5-11643485

//StatoTerritorioImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End StatoTerritorioImposta Record: method insert head

//StatoTerritorioImposta Record: method insert body @5-1EB2FD16
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            StatoTerritorioImpostaDataObject ds = new StatoTerritorioImpostaDataObject(page);
            ds.setComponent( model );
            StatoTerritorioImpostaRow row = new StatoTerritorioImpostaRow();
            ds.setRow(row);
            row.setSTATO_TERRITORIO(Utils.convertToLong(model.getControl("STATO_TERRITORIO").getValue()));
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setSIGLA(Utils.convertToString(model.getControl("SIGLA").getValue()));
            row.setDESC_CITTADINANZA(Utils.convertToString(model.getControl("DESC_CITTADINANZA").getValue()));
            row.setRAGGRUPPAMENTO(Utils.convertToLong(model.getControl("RAGGRUPPAMENTO").getValue()));
            row.setSTATO_APPARTENENZA(Utils.convertToLong(model.getControl("STATO_APPARTENENZA").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End StatoTerritorioImposta Record: method insert body

//StatoTerritorioImposta Record: ds.insert @5-9320B03B
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
//End StatoTerritorioImposta Record: ds.insert

} //StatoTerritorioImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //StatoTerritorioImposta Record: method update @5-5771D0AA

//StatoTerritorioImposta Record: method update head @5-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End StatoTerritorioImposta Record: method update head

//StatoTerritorioImposta Record: method update body @5-FB28A90D
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            StatoTerritorioImpostaDataObject ds = new StatoTerritorioImpostaDataObject(page);
            ds.setComponent( model );
            StatoTerritorioImpostaRow row = new StatoTerritorioImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlSTATO_TERRITORIO( page.getHttpGetParams().getParameter("STATO_TERRITORIO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'STATO_TERRITORIO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'STATO_TERRITORIO'" );
            }
            row.setDENOMINAZIONE(Utils.convertToString(model.getControl("DENOMINAZIONE").getValue()));
            row.setSIGLA(Utils.convertToString(model.getControl("SIGLA").getValue()));
            row.setDESC_CITTADINANZA(Utils.convertToString(model.getControl("DESC_CITTADINANZA").getValue()));
            row.setRAGGRUPPAMENTO(Utils.convertToLong(model.getControl("RAGGRUPPAMENTO").getValue()));
            row.setSTATO_APPARTENENZA(Utils.convertToLong(model.getControl("STATO_APPARTENENZA").getValue()));
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
//End StatoTerritorioImposta Record: method update body

//StatoTerritorioImposta Record: ds.update @5-6E956EDC
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
//End StatoTerritorioImposta Record: ds.update

} //StatoTerritorioImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //StatoTerritorioImposta Record: method delete @5-11FC2E1E

//StatoTerritorioImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End StatoTerritorioImposta Record: method delete head

//StatoTerritorioImposta Record: method delete body @5-4A17DBC6
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            StatoTerritorioImpostaDataObject ds = new StatoTerritorioImpostaDataObject(page);
            ds.setComponent( model );
            StatoTerritorioImpostaRow row = new StatoTerritorioImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlSTATO_TERRITORIO( page.getHttpGetParams().getParameter("STATO_TERRITORIO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'STATO_TERRITORIO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'STATO_TERRITORIO'" );
            }
//End StatoTerritorioImposta Record: method delete body

//StatoTerritorioImposta Record: ds.delete @5-3584344F
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
//End StatoTerritorioImposta Record: ds.delete

} //StatoTerritorioImposta Record: method delete tail @5-FCB6E20C

//StatoTerritorioImposta Record: method validate @5-5A8442E7
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox STATO_TERRITORIO = (com.codecharge.components.TextBox) model.getChild( "STATO_TERRITORIO" );
            if (! STATO_TERRITORIO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DENOMINAZIONE = (com.codecharge.components.TextBox) model.getChild( "DENOMINAZIONE" );
            if (! DENOMINAZIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox SIGLA = (com.codecharge.components.TextBox) model.getChild( "SIGLA" );
            if (! SIGLA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESC_CITTADINANZA = (com.codecharge.components.TextBox) model.getChild( "DESC_CITTADINANZA" );
            if (! DESC_CITTADINANZA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox RAGGRUPPAMENTO = (com.codecharge.components.TextBox) model.getChild( "RAGGRUPPAMENTO" );
            if (! RAGGRUPPAMENTO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox STATO_APPARTENENZA = (com.codecharge.components.TextBox) model.getChild( "STATO_APPARTENENZA" );
            if (! STATO_APPARTENENZA.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End StatoTerritorioImposta Record: method validate

//StatoTerritorioImposta Record Tail @5-FCB6E20C
    }
//End StatoTerritorioImposta Record Tail

//Ad4DizionariStatoTerritorioImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariStatoTerritorioImposta Page: method validate

//Ad4DizionariStatoTerritorioImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariStatoTerritorioImpostaAction Tail

