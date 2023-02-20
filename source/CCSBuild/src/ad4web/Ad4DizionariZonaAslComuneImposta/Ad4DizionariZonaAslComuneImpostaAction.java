//Ad4DizionariZonaAslComuneImpostaAction imports @1-71E40B9A
package ad4web.Ad4DizionariZonaAslComuneImposta;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariZonaAslComuneImpostaAction imports

//Ad4DizionariZonaAslComuneImpostaAction class @1-4F76824E
public class Ad4DizionariZonaAslComuneImpostaAction extends Action {

//End Ad4DizionariZonaAslComuneImpostaAction class

//Ad4DizionariZonaAslComuneImpostaAction: method perform @1-907D13C6
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariZonaAslComuneImpostaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariZonaAslComuneImpostaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariZonaAslComuneImpostaAction: method perform

//Ad4DizionariZonaAslComuneImpostaAction: call children actions @1-3E59DE93
        if (result == null) {
            AttributoHeaderClass AttributoHeader = new AttributoHeaderClass();
            AttributoHeader.perform(page.getGrid("AttributoHeader"));
        }
        if (result == null) {
            ZonaAslComuneImpostaClass ZonaAslComuneImposta = new ZonaAslComuneImpostaClass();
            if ( ( redirect = ZonaAslComuneImposta.perform( page.getRecord("ZonaAslComuneImposta")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariZonaAslComuneImpostaAction: call children actions

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

//ZonaAslComuneImposta Record @5-2FBAFE13
    final class ZonaAslComuneImpostaClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ZonaAslComuneImposta Record

//ZonaAslComuneImposta Record: method perform @5-69DC0B37
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowInsert() ) model.getChild( "Button_Insert" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End ZonaAslComuneImposta Record: method perform

//ZonaAslComuneImposta Record: children actions @5-B8765443
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ZonaAslComuneImposta".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
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
//End ZonaAslComuneImposta Record: children actions

//ZonaAslComuneImposta Record: method perform tail @5-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ZonaAslComuneImposta Record: method perform tail

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

void read() { //ZonaAslComuneImposta Record: method read @5-7F8AAE5A

//ZonaAslComuneImposta Record: method read head @5-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ZonaAslComuneImposta Record: method read head

//ZonaAslComuneImposta Record: init DataSource @5-50F5193B
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ZonaAslComuneImpostaDataObject ds = new ZonaAslComuneImpostaDataObject(page);
            ds.setComponent( model );
            try {
                ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
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
//End ZonaAslComuneImposta Record: init DataSource

//ZonaAslComuneImposta Record: check errors @5-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ZonaAslComuneImposta Record: check errors

} //ZonaAslComuneImposta Record: method read tail @5-FCB6E20C

//ZonaAslComuneImposta Record: bind @5-EBE91A47
            public void bind(com.codecharge.components.Component model, ZonaAslComuneImpostaRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("TITOLO").setValue(row.getTITOLO());
                model.getControl("COMUNE_LOV").setValue(row.getCOMUNE_LOV());
                if ( this.valid ) {
                    model.getControl("ID_ZONA_ASL").setValue(row.getID_ZONA_ASL());
                    model.getControl("COMUNE_DESC").setValue(row.getCOMUNE_DESC());
                    model.getControl("PROVINCIA").setValue(row.getPROVINCIA());
                    model.getControl("COMUNE").setValue(row.getCOMUNE());
                }
            }
//End ZonaAslComuneImposta Record: bind

//ZonaAslComuneImposta Record: getRowFieldByName @5-33810F21
            public Object getRowFieldByName( String name, ZonaAslComuneImpostaRow row ) {
                Object value = null;
                if ( "TITOLO".equals(name) ) {
                    value = row.getTITOLO();
                } else if ( "ID_ZONA_ASL".equals(name) ) {
                    value = row.getID_ZONA_ASL();
                } else if ( "COMUNE_DESC".equals(name) ) {
                    value = row.getCOMUNE_DESC();
                } else if ( "COMUNE_LOV".equals(name) ) {
                    value = row.getCOMUNE_LOV();
                } else if ( "PROVINCIA".equals(name) ) {
                    value = row.getPROVINCIA();
                } else if ( "COMUNE".equals(name) ) {
                    value = row.getCOMUNE();
                }
                return value;
            }
//End ZonaAslComuneImposta Record: getRowFieldByName

void InsertAction() { //ZonaAslComuneImposta Record: method insert @5-11643485

//ZonaAslComuneImposta Record: method insert head @5-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ZonaAslComuneImposta Record: method insert head

//ZonaAslComuneImposta Record: method insert body @5-959C46DA
            if (!model.isAllowInsert()) return;
            boolean isErrors = false;
            ZonaAslComuneImpostaDataObject ds = new ZonaAslComuneImpostaDataObject(page);
            ds.setComponent( model );
            ZonaAslComuneImpostaRow row = new ZonaAslComuneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlID_ZONA_ASL( page.getHttpGetParams().getParameter("ID_ZONA_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            }
            try {
                ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
//End ZonaAslComuneImposta Record: method insert body

//ZonaAslComuneImposta Record: ds.insert @5-9320B03B
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
//End ZonaAslComuneImposta Record: ds.insert

} //ZonaAslComuneImposta Record: method insert tail @5-FCB6E20C

void UpdateAction() { //ZonaAslComuneImposta Record: method update @5-5771D0AA

//ZonaAslComuneImposta Record: components update actions @5-68525650
            if (! model.hasErrors()) {
            }
//End ZonaAslComuneImposta Record: components update actions

} //ZonaAslComuneImposta Record: method update tail @5-FCB6E20C

void DeleteAction() { //ZonaAslComuneImposta Record: method delete @5-11FC2E1E

//ZonaAslComuneImposta Record: method delete head @5-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ZonaAslComuneImposta Record: method delete head

//ZonaAslComuneImposta Record: method delete body @5-F9AC3DB8
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ZonaAslComuneImpostaDataObject ds = new ZonaAslComuneImpostaDataObject(page);
            ds.setComponent( model );
            ZonaAslComuneImpostaRow row = new ZonaAslComuneImpostaRow();
            ds.setRow(row);
            try {
                ds.setUrlID_ZONA_ASL( page.getHttpGetParams().getParameter("ID_ZONA_ASL"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'ID_ZONA_ASL'" );
            }
            try {
                ds.setUrlPROVINCIA( page.getHttpGetParams().getParameter("PROVINCIA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROVINCIA'" );
            }
            try {
                ds.setUrlCOMUNE( page.getHttpGetParams().getParameter("COMUNE"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'COMUNE'" );
            }
//End ZonaAslComuneImposta Record: method delete body

//ZonaAslComuneImposta Record: ds.delete @5-3584344F
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
//End ZonaAslComuneImposta Record: ds.delete

} //ZonaAslComuneImposta Record: method delete tail @5-FCB6E20C

//ZonaAslComuneImposta Record: method validate @5-369160CA
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden ID_ZONA_ASL = (com.codecharge.components.Hidden) model.getChild( "ID_ZONA_ASL" );
            if (! ID_ZONA_ASL.validate()) { isControlError = true; }

            com.codecharge.components.TextBox COMUNE_DESC = (com.codecharge.components.TextBox) model.getChild( "COMUNE_DESC" );
            if (! COMUNE_DESC.validate()) { isControlError = true; }

            com.codecharge.components.Hidden PROVINCIA = (com.codecharge.components.Hidden) model.getChild( "PROVINCIA" );
            if (! PROVINCIA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden COMUNE = (com.codecharge.components.Hidden) model.getChild( "COMUNE" );
            if (! COMUNE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ZonaAslComuneImposta Record: method validate

//ZonaAslComuneImposta Record Tail @5-FCB6E20C
    }
//End ZonaAslComuneImposta Record Tail

//Ad4DizionariZonaAslComuneImposta Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariZonaAslComuneImposta Page: method validate

//Ad4DizionariZonaAslComuneImpostaAction Tail @1-FCB6E20C
}
//End Ad4DizionariZonaAslComuneImpostaAction Tail

