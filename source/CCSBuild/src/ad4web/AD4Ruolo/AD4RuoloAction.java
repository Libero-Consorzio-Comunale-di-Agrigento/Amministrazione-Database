//AD4RuoloAction imports @1-76B812C3
package ad4web.AD4Ruolo;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4RuoloAction imports

//AD4RuoloAction class @1-8AB37F1D
public class AD4RuoloAction extends Action {

//End AD4RuoloAction class

//AD4RuoloAction: method perform @1-E6A3511A
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4RuoloModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4RuoloModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4RuoloAction: method perform

//AD4RuoloAction: call children actions @1-B173AE09
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
            AD4_RUOLIClass AD4_RUOLI = new AD4_RUOLIClass();
            if ( ( redirect = AD4_RUOLI.perform( page.getRecord("AD4_RUOLI")) ) != null ) result = redirect;
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
//End AD4RuoloAction: call children actions

//AD4_RUOLI Record @23-DCD263E2
    final class AD4_RUOLIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_RUOLI Record

//AD4_RUOLI Record: method perform @23-4F85605F
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_RUOLI Record: method perform

//AD4_RUOLI Record: children actions @23-FCB66E24
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_RUOLI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Refresh") != null) {
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
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
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                    if (page.getParameter("Refresh") != null) {
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                        if (validate()) {
                            RefreshSearchAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readPROGETTO(model.getListBox("PROGETTO"));
            readMODULO(model.getListBox("MODULO"));
//End AD4_RUOLI Record: children actions

//AD4_RUOLI Record: method perform tail @23-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_RUOLI Record: method perform tail

//Refresh Button @73-BF199923
        void RefreshSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Refresh");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End Refresh Button

//Button_Update Button @25-7744BBFA
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RuoliRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @26-0DDF7181
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RuoliRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @28-83772A26
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4RuoliRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @61-CCF4A48A
        protected void readPROGETTO(com.codecharge.components.ListBox model) {

            TextField urlMODULO = new TextField(null, null);
            
            urlMODULO.setValue( page.getHttpGetParams().getParameter("MODULO") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT PROGETTO,  "
                        + "DESCRIZIONE  "
                        + "  FROM PROGETTI "
                        + " WHERE '{MODULO}' IS NOT NULL "
                        + "   AND PROGETTO in (SELECT PROGETTO "
                        + "                      FROM MODULI "
                        + "                     WHERE MODULO = '{MODULO}') "
                        + "UNION "
                        + "SELECT PROGETTO, DESCRIZIONE  "
                        + "  FROM PROGETTI "
                        + " WHERE '{MODULO}' IS NULL "
                        + "UNION "
                        + "SELECT '', '- -' "
                        + "  FROM dual "
                        + " WHERE '{MODULO}' IS NULL "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
            command.addParameter( "MODULO", urlMODULO, null );
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

//ListBoxAction @33-497E6DA5
        protected void readMODULO(com.codecharge.components.ListBox model) {

            TextField urlPROGETTO = new TextField(null, null);
            
            urlPROGETTO.setValue( page.getHttpGetParams().getParameter("PROGETTO") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT MODULO,  "
                        + "DESCRIZIONE "
                        + "  FROM MODULI "
                        + " WHERE NVL(PROGETTO,' ') = NVL('{PROGETTO}', NVL(PROGETTO,' ')) "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
            command.addParameter( "PROGETTO", urlPROGETTO, null );
            command.setOrder( "DESCRIZIONE" );

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

void read() { //AD4_RUOLI Record: method read @23-7F8AAE5A

//AD4_RUOLI Record: method read head @23-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_RUOLI Record: method read head

//AD4_RUOLI Record: init DataSource @23-AB0277D2
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_RUOLIDataObject ds = new AD4_RUOLIDataObject(page);
            ds.setComponent( model );
            ds.setUrlRUOLO( page.getHttpGetParams().getParameter("RUOLO") );
            ds.setUrlSE_NUOVO( page.getHttpGetParams().getParameter("SE_NUOVO") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setUrlDESCRIZIONE( page.getHttpGetParams().getParameter("DESCRIZIONE") );
            ds.setUrlPROFILO( page.getHttpGetParams().getParameter("PROFILO") );
            ds.setUrlISLISTBOX( page.getHttpGetParams().getParameter("ISLISTBOX") );
            ds.setUrlSTATO( page.getHttpGetParams().getParameter("STATO") );
            ds.setUrlGRUPPO_LAVORO( page.getHttpGetParams().getParameter("GRUPPO_LAVORO") );
            ds.setUrlGRUPPO_SO( page.getHttpGetParams().getParameter("GRUPPO_SO") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_RUOLI Record: init DataSource

//AD4_RUOLI Record: check errors @23-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_RUOLI Record: check errors

} //AD4_RUOLI Record: method read tail @23-FCB6E20C

//AD4_RUOLI Record: bind @23-C6F2D83E
            public void bind(com.codecharge.components.Component model, AD4_RUOLIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("RUOLO").setValue(row.getRUOLO());
                if ( this.valid ) {
                    model.getControl("RUOLO_ORIG").setValue(row.getRUOLO_ORIG());
                    model.getControl("DESCRIZIONE").setValue(row.getDESCRIZIONE());
                    model.getControl("PROGETTO").setValue(row.getPROGETTO());
                    model.getControl("MODULO").setValue(row.getMODULO());
                    model.getControl("PROFILO").setValue(row.getPROFILO());
                    model.getControl("STATO").setValue(row.getSTATO());
                    model.getControl("GRUPPO_LAVORO").setValue(row.getGRUPPO_LAVORO());
                    model.getControl("GRUPPO_SO").setValue(row.getGRUPPO_SO());
                }
            }
//End AD4_RUOLI Record: bind

//AD4_RUOLI Record: getRowFieldByName @23-FD81DBF5
            public Object getRowFieldByName( String name, AD4_RUOLIRow row ) {
                Object value = null;
                if ( "RUOLO".equals(name) ) {
                    value = row.getRUOLO();
                } else if ( "RUOLO_ORIG".equals(name) ) {
                    value = row.getRUOLO_ORIG();
                } else if ( "DESCRIZIONE".equals(name) ) {
                    value = row.getDESCRIZIONE();
                } else if ( "PROGETTO".equals(name) ) {
                    value = row.getPROGETTO();
                } else if ( "ISLISTBOX".equals(name) ) {
                    value = row.getISLISTBOX();
                } else if ( "MODULO".equals(name) ) {
                    value = row.getMODULO();
                } else if ( "PROFILO".equals(name) ) {
                    value = row.getPROFILO();
                } else if ( "STATO".equals(name) ) {
                    value = row.getSTATO();
                } else if ( "GRUPPO_LAVORO".equals(name) ) {
                    value = row.getGRUPPO_LAVORO();
                } else if ( "GRUPPO_SO".equals(name) ) {
                    value = row.getGRUPPO_SO();
                }
                return value;
            }
//End AD4_RUOLI Record: getRowFieldByName

void InsertAction() { //AD4_RUOLI Record: method insert @23-11643485

//AD4_RUOLI Record: components insert actions @23-68525650
            if (! model.hasErrors()) {
            }
//End AD4_RUOLI Record: components insert actions

} //AD4_RUOLI Record: method insert tail @23-FCB6E20C

void UpdateAction() { //AD4_RUOLI Record: method update @23-5771D0AA

//AD4_RUOLI Record: method update head @23-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_RUOLI Record: method update head

//AD4_RUOLI Record: method update body @23-3EED183F
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_RUOLIDataObject ds = new AD4_RUOLIDataObject(page);
            ds.setComponent( model );
            AD4_RUOLIRow row = new AD4_RUOLIRow();
            ds.setRow(row);
            ds.setPostRUOLO( page.getHttpPostParams().getParameter("RUOLO") );
            ds.setPostRUOLO_ORIG( page.getHttpPostParams().getParameter("RUOLO_ORIG") );
            ds.setPostDESCRIZIONE( page.getHttpPostParams().getParameter("DESCRIZIONE") );
            ds.setPostPROGETTO( page.getHttpPostParams().getParameter("PROGETTO") );
            ds.setPostMODULO( page.getHttpPostParams().getParameter("MODULO") );
            try {
                ds.setPostPROFILO( page.getHttpPostParams().getParameter("PROFILO"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'PROFILO'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'PROFILO'" );
            }
            row.setSTATO(Utils.convertToString(model.getControl("STATO").getValue()));
            row.setGRUPPO_LAVORO(Utils.convertToString(model.getControl("GRUPPO_LAVORO").getValue()));
            row.setGRUPPO_SO(Utils.convertToString(model.getControl("GRUPPO_SO").getValue()));
//End AD4_RUOLI Record: method update body

//AD4_RUOLI Record: ds.update @23-6E956EDC
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
//End AD4_RUOLI Record: ds.update

} //AD4_RUOLI Record: method update tail @23-FCB6E20C

void DeleteAction() { //AD4_RUOLI Record: method delete @23-11FC2E1E

//AD4_RUOLI Record: method delete head @23-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_RUOLI Record: method delete head

//AD4_RUOLI Record: method delete body @23-397A4E9F
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_RUOLIDataObject ds = new AD4_RUOLIDataObject(page);
            ds.setComponent( model );
            AD4_RUOLIRow row = new AD4_RUOLIRow();
            ds.setRow(row);
            ds.setPostRUOLO( page.getHttpPostParams().getParameter("RUOLO") );
//End AD4_RUOLI Record: method delete body

//AD4_RUOLI Record: ds.delete @23-3584344F
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
//End AD4_RUOLI Record: ds.delete

} //AD4_RUOLI Record: method delete tail @23-FCB6E20C

//AD4_RUOLI Record: method validate @23-89144E0E
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden RUOLO_ORIG = (com.codecharge.components.Hidden) model.getChild( "RUOLO_ORIG" );
            if (! RUOLO_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "DESCRIZIONE" );
            if (! DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox PROGETTO = (com.codecharge.components.ListBox) model.getChild( "PROGETTO" );
            if (! PROGETTO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ISLISTBOX = (com.codecharge.components.Hidden) model.getChild( "ISLISTBOX" );
            if (! ISLISTBOX.validate()) { isControlError = true; }

            com.codecharge.components.ListBox MODULO = (com.codecharge.components.ListBox) model.getChild( "MODULO" );
            if (! MODULO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox PROFILO = (com.codecharge.components.TextBox) model.getChild( "PROFILO" );
            if (! PROFILO.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_RUOLI Record: method validate

//AD4_RUOLI Record Tail @23-FCB6E20C
    }
//End AD4_RUOLI Record Tail

//AD4Ruolo Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Ruolo Page: method validate

//AD4RuoloAction Tail @1-FCB6E20C
}
//End AD4RuoloAction Tail

