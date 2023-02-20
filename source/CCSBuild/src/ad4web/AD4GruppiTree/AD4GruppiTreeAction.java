//AD4GruppiTreeAction imports @1-A395A1C2
package ad4web.AD4GruppiTree;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GruppiTreeAction imports

//AD4GruppiTreeAction class @1-0F0382CE
public class AD4GruppiTreeAction extends Action {

//End AD4GruppiTreeAction class

//AD4GruppiTreeAction: method perform @1-7E37E58A
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GruppiTreeModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GruppiTreeModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GruppiTreeAction: method perform

//AD4GruppiTreeAction: call children actions @1-4C3F1594
        if ( page.getChild( "Header" ).isVisible() ) {
            page.getRequest().setAttribute("HeaderParent",page);
            common.Header.HeaderAction Header = new common.Header.HeaderAction();
            result = result != null ? result : Header.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "Left" ).isVisible() ) {
            page.getRequest().setAttribute("LeftParent",page);
            common.Left.LeftAction Left = new common.Left.LeftAction();
            result = result != null ? result : Left.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "Guida" ).isVisible() ) {
            page.getRequest().setAttribute("GuidaParent",page);
            common.Guida.GuidaAction Guida = new common.Guida.GuidaAction();
            result = result != null ? result : Guida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            se_rigenerata_SOClass se_rigenerata_SO = new se_rigenerata_SOClass();
            if ( ( redirect = se_rigenerata_SO.perform( page.getRecord("se_rigenerata_SO")) ) != null ) result = redirect;
        }
        if (result == null) {
            Rigenera_SOClass Rigenera_SO = new Rigenera_SOClass();
            Rigenera_SO.perform(page.getGrid("Rigenera_SO"));
        }
        if ( page.getChild( "AD4GruppiTreeInclusa" ).isVisible() ) {
            page.getRequest().setAttribute("AD4GruppiTreeInclusaParent",page);
            ad4web.AD4GruppiTreeInclusa.AD4GruppiTreeInclusaAction AD4GruppiTreeInclusa = new ad4web.AD4GruppiTreeInclusa.AD4GruppiTreeInclusaAction();
            result = result != null ? result : AD4GruppiTreeInclusa.perform( req, resp,  context );
            page.setCookies();
        }
        if ( page.getChild( "Footer" ).isVisible() ) {
            page.getRequest().setAttribute("FooterParent",page);
            common.Footer.FooterAction Footer = new common.Footer.FooterAction();
            result = result != null ? result : Footer.perform( req, resp,  context );
            page.setCookies();
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4GruppiTreeAction: call children actions

//se_rigenerata_SO Record @28-0D8E4410
    final class se_rigenerata_SOClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End se_rigenerata_SO Record

//se_rigenerata_SO Record: method perform @28-8CBD5765
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
//End se_rigenerata_SO Record: method perform

//se_rigenerata_SO Record: children actions @28-9CBACD06
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("se_rigenerata_SO".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Button_Update") != null) {
                        if (validate()) {
                            Button_UpdateAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Delete") != null) {
                        Button_DeleteAction();
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
                }
            }
            setProperties(model, Action.GET, true );
            read();
//End se_rigenerata_SO Record: children actions

//se_rigenerata_SO Record: method perform tail @28-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End se_rigenerata_SO Record: method perform tail

//Button_Insert Button @31-A60A3466
        void Button_InsertAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Insert");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            InsertAction();
        }
//End Button_Insert Button

//Button_Update Button @32-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @33-79294D0A
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

void read() { //se_rigenerata_SO Record: method read @28-7F8AAE5A

//se_rigenerata_SO Record: method read head @28-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End se_rigenerata_SO Record: method read head

//se_rigenerata_SO Record: init DataSource @28-458A1499
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            se_rigenerata_SODataObject ds = new se_rigenerata_SODataObject(page);
            ds.setComponent( model );
            try {
                ds.setSesAD4JOBRIGSO( SessionStorage.getInstance(req).getAttribute("AD4JOBRIGSO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'AD4JOBRIGSO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'AD4JOBRIGSO'" );
            }
            try {
                ds.setSesAD4JOBLDAPFROMSO( SessionStorage.getInstance(req).getAttribute("AD4JOBLDAPFROMSO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'AD4JOBLDAPFROMSO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'AD4JOBLDAPFROMSO'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End se_rigenerata_SO Record: init DataSource

//se_rigenerata_SO Record: check errors @28-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End se_rigenerata_SO Record: check errors

} //se_rigenerata_SO Record: method read tail @28-FCB6E20C

//se_rigenerata_SO Record: bind @28-4C48ED43
            public void bind(com.codecharge.components.Component model, se_rigenerata_SORow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                    model.getControl("JOB_RIG_SO").setValue(row.getJOB_RIG_SO());
                    model.getControl("JOB_LDAP_FROM_SO").setValue(row.getJOB_LDAP_FROM_SO());
                }
            }
//End se_rigenerata_SO Record: bind

//se_rigenerata_SO Record: getRowFieldByName @28-4D312092
            public Object getRowFieldByName( String name, se_rigenerata_SORow row ) {
                Object value = null;
                if ( "JOB_RIG_SO".equals(name) ) {
                    value = row.getJOB_RIG_SO();
                } else if ( "JOB_LDAP_FROM_SO".equals(name) ) {
                    value = row.getJOB_LDAP_FROM_SO();
                }
                return value;
            }
//End se_rigenerata_SO Record: getRowFieldByName

void InsertAction() { //se_rigenerata_SO Record: method insert @28-11643485

//se_rigenerata_SO Record: components insert actions @28-68525650
            if (! model.hasErrors()) {
            }
//End se_rigenerata_SO Record: components insert actions

} //se_rigenerata_SO Record: method insert tail @28-FCB6E20C

void UpdateAction() { //se_rigenerata_SO Record: method update @28-5771D0AA

//se_rigenerata_SO Record: components update actions @28-68525650
            if (! model.hasErrors()) {
            }
//End se_rigenerata_SO Record: components update actions

} //se_rigenerata_SO Record: method update tail @28-FCB6E20C

void DeleteAction() { //se_rigenerata_SO Record: method delete @28-11FC2E1E

//se_rigenerata_SO Record: components delete actions @28-68525650
            if (! model.hasErrors()) {
            }
//End se_rigenerata_SO Record: components delete actions

} //se_rigenerata_SO Record: method delete tail @28-FCB6E20C

//se_rigenerata_SO Record: method validate @28-D1E9A24D
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox JOB_RIG_SO = (com.codecharge.components.TextBox) model.getChild( "JOB_RIG_SO" );
            if (! JOB_RIG_SO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox JOB_LDAP_FROM_SO = (com.codecharge.components.TextBox) model.getChild( "JOB_LDAP_FROM_SO" );
            if (! JOB_LDAP_FROM_SO.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End se_rigenerata_SO Record: method validate

//se_rigenerata_SO Record Tail @28-FCB6E20C
    }
//End se_rigenerata_SO Record Tail

//Rigenera_SO Grid @22-E2FFB7B9
    final class Rigenera_SOClass {
        com.codecharge.components.Grid model;
        Event e;
//End Rigenera_SO Grid

//Rigenera_SO Grid: method perform @22-B48879D3
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
//End Rigenera_SO Grid: method perform

//Rigenera_SO Grid: method read: head @22-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Rigenera_SO Grid: method read: head

//Rigenera_SO Grid: method read: init @22-C3DCDCCD
            if ( ! model.allowRead ) return true;
            Rigenera_SODataObject ds = new Rigenera_SODataObject(page);
            ds.setComponent( model );
//End Rigenera_SO Grid: method read: init

//Rigenera_SO Grid: set grid properties @22-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Rigenera_SO Grid: set grid properties

//Rigenera_SO Grid: retrieve data @22-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Rigenera_SO Grid: retrieve data

//Rigenera_SO Grid: check errors @22-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Rigenera_SO Grid: check errors

//Rigenera_SO Grid: method read: tail @22-F575E732
            return ( ! isErrors );
        }
//End Rigenera_SO Grid: method read: tail

//Rigenera_SO Grid: method bind @22-4E971545
        public void bind(com.codecharge.components.Component model, Rigenera_SORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            Rigenera_SORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("RIGENERA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("RIGENERA").clone();
                    c.setValue(row.getRIGENERA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ALLINEA_LDAP");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ALLINEA_LDAP").clone();
                    c.setValue(row.getALLINEA_LDAP());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End Rigenera_SO Grid: method bind

//Rigenera_SO Directory: getRowFieldByName @22-18E253D6
        public Object getRowFieldByName( String name, Rigenera_SORow row ) {
            Object value = null;
            if ( "RIGENERA".equals(name) ) {
                value = row.getRIGENERA();
            } else if ( "ALLINEA_LDAP".equals(name) ) {
                value = row.getALLINEA_LDAP();
            }
            return value;
        }
//End Rigenera_SO Directory: getRowFieldByName

//Rigenera_SO Grid: method validate @22-104025BA
        boolean validate() {
            return true;
        }
//End Rigenera_SO Grid: method validate

//Rigenera_SO Grid Tail @22-FCB6E20C
    }
//End Rigenera_SO Grid Tail

//AD4GruppiTree Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GruppiTree Page: method validate

//AD4GruppiTreeAction Tail @1-FCB6E20C
}
//End AD4GruppiTreeAction Tail
