//AD4StampaUtentiAction imports @1-980AD211
package ad4web.AD4StampaUtenti;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4StampaUtentiAction imports

//AD4StampaUtentiAction class @1-213B7C91
public class AD4StampaUtentiAction extends Action {

//End AD4StampaUtentiAction class

//AD4StampaUtentiAction: method perform @1-6637AABA
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4StampaUtentiModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4StampaUtentiModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4StampaUtentiAction: method perform

//AD4StampaUtentiAction: call children actions @1-0072BDF2
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
            PARAMETRI_STAMPAClass PARAMETRI_STAMPA = new PARAMETRI_STAMPAClass();
            if ( ( redirect = PARAMETRI_STAMPA.perform( page.getRecord("PARAMETRI_STAMPA")) ) != null ) result = redirect;
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
//End AD4StampaUtentiAction: call children actions

//PARAMETRI_STAMPA Record @6-5E457DDF
    final class PARAMETRI_STAMPAClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End PARAMETRI_STAMPA Record

//PARAMETRI_STAMPA Record: method perform @6-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End PARAMETRI_STAMPA Record: method perform

//PARAMETRI_STAMPA Record: children actions @6-9D63F86D
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("PARAMETRI_STAMPA".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        Button1Action();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            readTIPO_UTENTE(model.getListBox("TIPO_UTENTE"));
            readMODULO(model.getListBox("MODULO"));
            readISTANZA(model.getListBox("ISTANZA"));
            readFORMATO(model.getListBox("FORMATO"));
//End PARAMETRI_STAMPA Record: children actions

//PARAMETRI_STAMPA Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End PARAMETRI_STAMPA Record: method perform tail

//Button1 Button @43-1BE3999E
        void Button1Action() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button1");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button1 Button

//ListBoxAction @61-B302D216
        protected void readTIPO_UTENTE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select 'S' codice,  "
                        + "'Amministrazione' descrizione "
                        + "from dual "
                        + "union "
                        + "select 'N' codice, 'Applicativo' descrizione "
                        + "from dual "
                        + "union "
                        + "select '%' codice,  "
                        + "'Tutte' descrizione "
                        + "from dual" );
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

//ListBoxAction @34-86FE16E0
        protected void readMODULO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select modulo, descrizione from Moduli" );
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

//ListBoxAction @62-ED15CD8C
        protected void readISTANZA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT ISTANZA, DESCRIZIONE FROM ISTANZE" );
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

//ListBoxAction @63-4E08A406
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

void read() { //PARAMETRI_STAMPA Record: method read @6-7F8AAE5A

//PARAMETRI_STAMPA Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End PARAMETRI_STAMPA Record: method read head

//PARAMETRI_STAMPA Record: init DataSource @6-06CD1870
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            PARAMETRI_STAMPADataObject ds = new PARAMETRI_STAMPADataObject(page);
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
//End PARAMETRI_STAMPA Record: init DataSource

//PARAMETRI_STAMPA Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End PARAMETRI_STAMPA Record: check errors

} //PARAMETRI_STAMPA Record: method read tail @6-FCB6E20C

//PARAMETRI_STAMPA Record: bind @6-5920B47C
            public void bind(com.codecharge.components.Component model, PARAMETRI_STAMPARow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                    model.getControl("TIPO_UTENTE").setValue(row.getTIPO_UTENTE());
                    model.getControl("MODULO").setValue(row.getMODULO());
                    model.getControl("ISTANZA").setValue(row.getISTANZA());
                    model.getControl("FORMATO").setValue(row.getFORMATO());
                    model.getControl("CONTEXT").setValue(row.getCONTEXT());
                    model.getControl("DATA_SOURCE").setValue(row.getDATA_SOURCE());
                }
            }
//End PARAMETRI_STAMPA Record: bind

//PARAMETRI_STAMPA Record: getRowFieldByName @6-73ABA97C
            public Object getRowFieldByName( String name, PARAMETRI_STAMPARow row ) {
                Object value = null;
                if ( "TIPO_UTENTE".equals(name) ) {
                    value = row.getTIPO_UTENTE();
                } else if ( "MODULO".equals(name) ) {
                    value = row.getMODULO();
                } else if ( "ISTANZA".equals(name) ) {
                    value = row.getISTANZA();
                } else if ( "FORMATO".equals(name) ) {
                    value = row.getFORMATO();
                } else if ( "CONTEXT".equals(name) ) {
                    value = row.getCONTEXT();
                } else if ( "DATA_SOURCE".equals(name) ) {
                    value = row.getDATA_SOURCE();
                }
                return value;
            }
//End PARAMETRI_STAMPA Record: getRowFieldByName

void InsertAction() { //PARAMETRI_STAMPA Record: method insert @6-11643485

//PARAMETRI_STAMPA Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End PARAMETRI_STAMPA Record: components insert actions

} //PARAMETRI_STAMPA Record: method insert tail @6-FCB6E20C

void UpdateAction() { //PARAMETRI_STAMPA Record: method update @6-5771D0AA

//PARAMETRI_STAMPA Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End PARAMETRI_STAMPA Record: components update actions

} //PARAMETRI_STAMPA Record: method update tail @6-FCB6E20C

void DeleteAction() { //PARAMETRI_STAMPA Record: method delete @6-11FC2E1E

//PARAMETRI_STAMPA Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End PARAMETRI_STAMPA Record: components delete actions

} //PARAMETRI_STAMPA Record: method delete tail @6-FCB6E20C

//PARAMETRI_STAMPA Record: method validate @6-58202512
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox TIPO_UTENTE = (com.codecharge.components.ListBox) model.getChild( "TIPO_UTENTE" );
            if (! TIPO_UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.ListBox MODULO = (com.codecharge.components.ListBox) model.getChild( "MODULO" );
            if (! MODULO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox ISTANZA = (com.codecharge.components.ListBox) model.getChild( "ISTANZA" );
            if (! ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox FORMATO = (com.codecharge.components.ListBox) model.getChild( "FORMATO" );
            if (! FORMATO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden CONTEXT = (com.codecharge.components.Hidden) model.getChild( "CONTEXT" );
            if (! CONTEXT.validate()) { isControlError = true; }

            com.codecharge.components.Hidden DATA_SOURCE = (com.codecharge.components.Hidden) model.getChild( "DATA_SOURCE" );
            if (! DATA_SOURCE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End PARAMETRI_STAMPA Record: method validate

//PARAMETRI_STAMPA Record Tail @6-FCB6E20C
    }
//End PARAMETRI_STAMPA Record Tail

//AD4StampaUtenti Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4StampaUtenti Page: method validate

//AD4StampaUtentiAction Tail @1-FCB6E20C
}
//End AD4StampaUtentiAction Tail
