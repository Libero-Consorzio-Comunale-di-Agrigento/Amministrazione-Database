//AD4StampaVariazioniUtentiAction imports @1-8FD1399A
package ad4web.AD4StampaVariazioniUtenti;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4StampaVariazioniUtentiAction imports

//AD4StampaVariazioniUtentiAction class @1-9E8B1B77
public class AD4StampaVariazioniUtentiAction extends Action {

//End AD4StampaVariazioniUtentiAction class

//AD4StampaVariazioniUtentiAction: method perform @1-497C8E32
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4StampaVariazioniUtentiModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4StampaVariazioniUtentiModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4StampaVariazioniUtentiAction: method perform

//AD4StampaVariazioniUtentiAction: call children actions @1-B290FDEE
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
            PARAMETRIClass PARAMETRI = new PARAMETRIClass();
            if ( ( redirect = PARAMETRI.perform( page.getRecord("PARAMETRI")) ) != null ) result = redirect;
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
//End AD4StampaVariazioniUtentiAction: call children actions

//PARAMETRI Record @6-887ED1BD
    final class PARAMETRIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End PARAMETRI Record

//PARAMETRI Record: method perform @6-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End PARAMETRI Record: method perform

//PARAMETRI Record: children actions @6-998760A7
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("PARAMETRI".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        Button1Action();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            readCOD_UTENTE_ACCESSO(model.getListBox("COD_UTENTE_ACCESSO"));
            readFORMATO(model.getListBox("FORMATO"));
//End PARAMETRI Record: children actions

//PARAMETRI Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End PARAMETRI Record: method perform tail

//Button1 Button @43-1BE3999E
        void Button1Action() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button1");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button1 Button

//ListBoxAction @52-AE7BEB7B
        protected void readCOD_UTENTE_ACCESSO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select utente, nominativo from utenti  "
                        + "where tipo_utente ='U' "
                        + "" );
            command.setOrder( "nominativo" );

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

//ListBoxAction @56-4E08A406
        protected void readFORMATO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select 'xls' codice, 'Excel XLS' descrizione from dual union "
                        + "select 'pdf' codice,  "
                        + "'Acrobat PDF' descrizione from dual" );
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

void read() { //PARAMETRI Record: method read @6-7F8AAE5A

//PARAMETRI Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End PARAMETRI Record: method read head

//PARAMETRI Record: init DataSource @6-8B5A53D5
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            PARAMETRIDataObject ds = new PARAMETRIDataObject(page);
            ds.setComponent( model );
            ds.setSesMVDATASOURCE( SessionStorage.getInstance(req).getAttribute("MVDATASOURCE") );
            ds.setSesMVCONTEXT( SessionStorage.getInstance(req).getAttribute("MVCONTEXT") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End PARAMETRI Record: init DataSource

//PARAMETRI Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End PARAMETRI Record: check errors

} //PARAMETRI Record: method read tail @6-FCB6E20C

//PARAMETRI Record: bind @6-381E044A
            public void bind(com.codecharge.components.Component model, PARAMETRIRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                    model.getControl("DAL").setValue(row.getDAL());
                    model.getControl("AL").setValue(row.getAL());
                    model.getControl("FORMATO").setValue(row.getFORMATO());
                    model.getControl("CONTEXT").setValue(row.getCONTEXT());
                    model.getControl("DATA_SOURCE").setValue(row.getDATA_SOURCE());
                }
            }
//End PARAMETRI Record: bind

//PARAMETRI Record: getRowFieldByName @6-740DD56D
            public Object getRowFieldByName( String name, PARAMETRIRow row ) {
                Object value = null;
                if ( "COD_UTENTE_ACCESSO".equals(name) ) {
                    value = row.getCOD_UTENTE_ACCESSO();
                } else if ( "DAL".equals(name) ) {
                    value = row.getDAL();
                } else if ( "AL".equals(name) ) {
                    value = row.getAL();
                } else if ( "FORMATO".equals(name) ) {
                    value = row.getFORMATO();
                } else if ( "CONTEXT".equals(name) ) {
                    value = row.getCONTEXT();
                } else if ( "DATA_SOURCE".equals(name) ) {
                    value = row.getDATA_SOURCE();
                }
                return value;
            }
//End PARAMETRI Record: getRowFieldByName

void InsertAction() { //PARAMETRI Record: method insert @6-11643485

//PARAMETRI Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End PARAMETRI Record: components insert actions

} //PARAMETRI Record: method insert tail @6-FCB6E20C

void UpdateAction() { //PARAMETRI Record: method update @6-5771D0AA

//PARAMETRI Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End PARAMETRI Record: components update actions

} //PARAMETRI Record: method update tail @6-FCB6E20C

void DeleteAction() { //PARAMETRI Record: method delete @6-11FC2E1E

//PARAMETRI Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End PARAMETRI Record: components delete actions

} //PARAMETRI Record: method delete tail @6-FCB6E20C

//PARAMETRI Record: method validate @6-BB11504D
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox COD_UTENTE_ACCESSO = (com.codecharge.components.ListBox) model.getChild( "COD_UTENTE_ACCESSO" );
            if (! COD_UTENTE_ACCESSO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox DAL = (com.codecharge.components.TextBox) model.getChild( "DAL" );
            if (! DAL.validate()) { isControlError = true; }

            com.codecharge.components.TextBox AL = (com.codecharge.components.TextBox) model.getChild( "AL" );
            if (! AL.validate()) { isControlError = true; }

            com.codecharge.components.ListBox FORMATO = (com.codecharge.components.ListBox) model.getChild( "FORMATO" );
            if (! FORMATO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CONTEXT = (com.codecharge.components.Hidden) model.getChild( "CONTEXT" );
            if (! CONTEXT.validate()) { isControlError = true; }

            com.codecharge.components.Hidden DATA_SOURCE = (com.codecharge.components.Hidden) model.getChild( "DATA_SOURCE" );
            if (! DATA_SOURCE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End PARAMETRI Record: method validate

//PARAMETRI Record Tail @6-FCB6E20C
    }
//End PARAMETRI Record Tail

//AD4StampaVariazioniUtenti Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4StampaVariazioniUtenti Page: method validate

//AD4StampaVariazioniUtentiAction Tail @1-FCB6E20C
}
//End AD4StampaVariazioniUtentiAction Tail
