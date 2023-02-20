//AmvLoginAction imports @1-B0A45CA2
package common.AmvLogin;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AmvLoginAction imports

//AmvLoginAction class @1-F4B4A8BB
public class AmvLoginAction extends Action {

//End AmvLoginAction class

//AmvLoginAction: method perform @1-E5B3E5D6
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AmvLoginModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AmvLoginModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AmvLoginAction: method perform

//AmvLoginAction: call children actions @1-AEB8E008
        if (result == null) {
            LOGINClass LOGIN = new LOGINClass();
            if ( ( redirect = LOGIN.perform( page.getRecord("LOGIN")) ) != null ) result = redirect;
        }
        if (result == null) {
            RECUPERO_LOGIN_CUSTOMClass RECUPERO_LOGIN_CUSTOM = new RECUPERO_LOGIN_CUSTOMClass();
            RECUPERO_LOGIN_CUSTOM.perform(page.getGrid("RECUPERO_LOGIN_CUSTOM"));
        }
        if (result == null) {
            RECUPERO_LOGINClass RECUPERO_LOGIN = new RECUPERO_LOGINClass();
            RECUPERO_LOGIN.perform(page.getGrid("RECUPERO_LOGIN"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AmvLoginAction: call children actions

//LOGIN Record @2-582F5D4B
    final class LOGINClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End LOGIN Record

//LOGIN Record: method perform @2-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End LOGIN Record: method perform

//LOGIN Record: children actions @2-DADCFCF1
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("LOGIN".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        LoginSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End LOGIN Record: children actions

//LOGIN Record: method perform tail @2-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End LOGIN Record: method perform tail

//Login Button @11-C8F2A6FC
        void LoginSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Login");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End Login Button

void read() { //LOGIN Record: method read @2-7F8AAE5A

//LOGIN Record: method read head @2-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End LOGIN Record: method read head

//LOGIN Record: init DataSource @2-60424396
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            LOGINDataObject ds = new LOGINDataObject(page);
            ds.setComponent( model );
            ds.setUrlMVERR( page.getHttpGetParams().getParameter("MVERR") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End LOGIN Record: init DataSource

//LOGIN Record: check errors @2-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End LOGIN Record: check errors

} //LOGIN Record: method read tail @2-FCB6E20C

//LOGIN Record: bind @2-F07CB4C1
            public void bind(com.codecharge.components.Component model, LOGINRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("ERRORE").setValue(row.getERRORE());
                if ( this.valid ) {
                }
            }
//End LOGIN Record: bind

//LOGIN Record: getRowFieldByName @2-70B2A986
            public Object getRowFieldByName( String name, LOGINRow row ) {
                Object value = null;
                if ( "ERRORE".equals(name) ) {
                    value = row.getERRORE();
                } else if ( "j_username".equals(name) ) {
                    value = row.getJ_username();
                } else if ( "j_password".equals(name) ) {
                    value = row.getJ_password();
                }
                return value;
            }
//End LOGIN Record: getRowFieldByName

void InsertAction() { //LOGIN Record: method insert @2-11643485

//LOGIN Record: method insert head @2-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End LOGIN Record: method insert head

//LOGIN Record: components insert actions @2-68525650
            if (! model.hasErrors()) {
            }
//End LOGIN Record: components insert actions

} //LOGIN Record: method insert tail @2-FCB6E20C

void UpdateAction() { //LOGIN Record: method update @2-5771D0AA

//LOGIN Record: method update head @2-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End LOGIN Record: method update head

//LOGIN Record: components update actions @2-68525650
            if (! model.hasErrors()) {
            }
//End LOGIN Record: components update actions

} //LOGIN Record: method update tail @2-FCB6E20C

void DeleteAction() { //LOGIN Record: method delete @2-11FC2E1E

//LOGIN Record: method delete head @2-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End LOGIN Record: method delete head

//LOGIN Record: components delete actions @2-68525650
            if (! model.hasErrors()) {
            }
//End LOGIN Record: components delete actions

} //LOGIN Record: method delete tail @2-FCB6E20C

//LOGIN Record: method validate @2-EA7A537A
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox j_username = (com.codecharge.components.TextBox) model.getChild( "j_username" );
            if (! j_username.validate()) { isControlError = true; }

            com.codecharge.components.TextBox j_password = (com.codecharge.components.TextBox) model.getChild( "j_password" );
            if (! j_password.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End LOGIN Record: method validate

//LOGIN Record Tail @2-FCB6E20C
    }
//End LOGIN Record Tail

//RECUPERO_LOGIN_CUSTOM Grid @17-CAF04914
    final class RECUPERO_LOGIN_CUSTOMClass {
        com.codecharge.components.Grid model;
        Event e;
//End RECUPERO_LOGIN_CUSTOM Grid

//RECUPERO_LOGIN_CUSTOM Grid: method perform @17-B48879D3
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
//End RECUPERO_LOGIN_CUSTOM Grid: method perform

//RECUPERO_LOGIN_CUSTOM Grid: method read: head @17-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End RECUPERO_LOGIN_CUSTOM Grid: method read: head

//RECUPERO_LOGIN_CUSTOM Grid: method read: init @17-1362FFFA
            if ( ! model.allowRead ) return true;
            RECUPERO_LOGIN_CUSTOMDataObject ds = new RECUPERO_LOGIN_CUSTOMDataObject(page);
            ds.setComponent( model );
//End RECUPERO_LOGIN_CUSTOM Grid: method read: init

//RECUPERO_LOGIN_CUSTOM Grid: set where parameters @17-B5B3F448
            ds.setSesModulo( SessionStorage.getInstance(req).getAttribute("Modulo") );
            ds.setSesIstanza( SessionStorage.getInstance(req).getAttribute("Istanza") );
//End RECUPERO_LOGIN_CUSTOM Grid: set where parameters

//RECUPERO_LOGIN_CUSTOM Grid: set grid properties @17-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End RECUPERO_LOGIN_CUSTOM Grid: set grid properties

//RECUPERO_LOGIN_CUSTOM Grid: retrieve data @17-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End RECUPERO_LOGIN_CUSTOM Grid: retrieve data

//RECUPERO_LOGIN_CUSTOM Grid: check errors @17-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End RECUPERO_LOGIN_CUSTOM Grid: check errors

//RECUPERO_LOGIN_CUSTOM Grid: method read: tail @17-F575E732
            return ( ! isErrors );
        }
//End RECUPERO_LOGIN_CUSTOM Grid: method read: tail

//RECUPERO_LOGIN_CUSTOM Grid: method bind @17-6E5B5B15
        public void bind(com.codecharge.components.Component model, RECUPERO_LOGIN_CUSTOMRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            RECUPERO_LOGIN_CUSTOMRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("LOSTMSGCUSTOM");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("LOSTMSGCUSTOM").clone();
                    c.setValue(row.getLOSTMSGCUSTOM());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End RECUPERO_LOGIN_CUSTOM Grid: method bind

//RECUPERO_LOGIN_CUSTOM Directory: getRowFieldByName @17-8F30D31D
        public Object getRowFieldByName( String name, RECUPERO_LOGIN_CUSTOMRow row ) {
            Object value = null;
            if ( "LOSTMSGCUSTOM".equals(name) ) {
                value = row.getLOSTMSGCUSTOM();
            }
            return value;
        }
//End RECUPERO_LOGIN_CUSTOM Directory: getRowFieldByName

//RECUPERO_LOGIN_CUSTOM Grid: method validate @17-104025BA
        boolean validate() {
            return true;
        }
//End RECUPERO_LOGIN_CUSTOM Grid: method validate

//RECUPERO_LOGIN_CUSTOM Grid Tail @17-FCB6E20C
    }
//End RECUPERO_LOGIN_CUSTOM Grid Tail

//RECUPERO_LOGIN Grid @12-55C7AD3E
    final class RECUPERO_LOGINClass {
        com.codecharge.components.Grid model;
        Event e;
//End RECUPERO_LOGIN Grid

//RECUPERO_LOGIN Grid: method perform @12-B48879D3
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
//End RECUPERO_LOGIN Grid: method perform

//RECUPERO_LOGIN Grid: method read: head @12-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End RECUPERO_LOGIN Grid: method read: head

//RECUPERO_LOGIN Grid: method read: init @12-81A0418D
            if ( ! model.allowRead ) return true;
            RECUPERO_LOGINDataObject ds = new RECUPERO_LOGINDataObject(page);
            ds.setComponent( model );
//End RECUPERO_LOGIN Grid: method read: init

//RECUPERO_LOGIN Grid: set where parameters @12-B5B3F448
            ds.setSesModulo( SessionStorage.getInstance(req).getAttribute("Modulo") );
            ds.setSesIstanza( SessionStorage.getInstance(req).getAttribute("Istanza") );
//End RECUPERO_LOGIN Grid: set where parameters

//RECUPERO_LOGIN Grid: set grid properties @12-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End RECUPERO_LOGIN Grid: set grid properties

//RECUPERO_LOGIN Grid: retrieve data @12-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End RECUPERO_LOGIN Grid: retrieve data

//RECUPERO_LOGIN Grid: check errors @12-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End RECUPERO_LOGIN Grid: check errors

//RECUPERO_LOGIN Grid: method read: tail @12-F575E732
            return ( ! isErrors );
        }
//End RECUPERO_LOGIN Grid: method read: tail

//RECUPERO_LOGIN Grid: method bind @12-C3FEB786
        public void bind(com.codecharge.components.Component model, RECUPERO_LOGINRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            RECUPERO_LOGINRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("LOSTMSG");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("LOSTMSG").clone();
                    c.setValue(row.getLOSTMSG());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End RECUPERO_LOGIN Grid: method bind

//RECUPERO_LOGIN Directory: getRowFieldByName @12-52C96E8C
        public Object getRowFieldByName( String name, RECUPERO_LOGINRow row ) {
            Object value = null;
            if ( "LOSTMSG".equals(name) ) {
                value = row.getLOSTMSG();
            }
            return value;
        }
//End RECUPERO_LOGIN Directory: getRowFieldByName

//RECUPERO_LOGIN Grid: method validate @12-104025BA
        boolean validate() {
            return true;
        }
//End RECUPERO_LOGIN Grid: method validate

//RECUPERO_LOGIN Grid Tail @12-FCB6E20C
    }
//End RECUPERO_LOGIN Grid Tail

//AmvLogin Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AmvLogin Page: method validate

//AmvLoginAction Tail @1-FCB6E20C
}
//End AmvLoginAction Tail

