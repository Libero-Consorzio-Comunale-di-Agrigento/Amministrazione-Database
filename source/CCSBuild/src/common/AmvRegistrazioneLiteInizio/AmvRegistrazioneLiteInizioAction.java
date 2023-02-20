//AmvRegistrazioneLiteInizioAction imports @1-7DF79B61
package common.AmvRegistrazioneLiteInizio;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AmvRegistrazioneLiteInizioAction imports

//AmvRegistrazioneLiteInizioAction class @1-EA41F854
public class AmvRegistrazioneLiteInizioAction extends Action {

//End AmvRegistrazioneLiteInizioAction class

//AmvRegistrazioneLiteInizioAction: method perform @1-4E7EDAA5
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AmvRegistrazioneLiteInizioModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AmvRegistrazioneLiteInizioModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AmvRegistrazioneLiteInizioAction: method perform

//AmvRegistrazioneLiteInizioAction: call children actions @1-BBC290FF
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
            PRIVACYClass PRIVACY = new PRIVACYClass();
            PRIVACY.perform(page.getGrid("PRIVACY"));
        }
        if (result == null) {
            AD4_UTENTEClass AD4_UTENTE = new AD4_UTENTEClass();
            if ( ( redirect = AD4_UTENTE.perform( page.getRecord("AD4_UTENTE")) ) != null ) result = redirect;
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
//End AmvRegistrazioneLiteInizioAction: call children actions

//PRIVACY Grid @14-B4CBC71A
    final class PRIVACYClass {
        com.codecharge.components.Grid model;
        Event e;
//End PRIVACY Grid

//PRIVACY Grid: method perform @14-B48879D3
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
//End PRIVACY Grid: method perform

//PRIVACY Grid: method read: head @14-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End PRIVACY Grid: method read: head

//PRIVACY Grid: method read: init @14-48282CF9
            if ( ! model.allowRead ) return true;
            PRIVACYDataObject ds = new PRIVACYDataObject(page);
            ds.setComponent( model );
//End PRIVACY Grid: method read: init

//PRIVACY Grid: set where parameters @14-F17FFF38
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            ds.setSesModulo( SessionStorage.getInstance(req).getAttribute("Modulo") );
//End PRIVACY Grid: set where parameters

//PRIVACY Grid: set grid properties @14-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End PRIVACY Grid: set grid properties

//PRIVACY Grid: retrieve data @14-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End PRIVACY Grid: retrieve data

//PRIVACY Grid: check errors @14-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End PRIVACY Grid: check errors

//PRIVACY Grid: method read: tail @14-F575E732
            return ( ! isErrors );
        }
//End PRIVACY Grid: method read: tail

//PRIVACY Grid: method bind @14-6A80A965
        public void bind(com.codecharge.components.Component model, PRIVACYRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            PRIVACYRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("PRIVACY");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PRIVACY").clone();
                    c.setValue(row.getPRIVACY());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End PRIVACY Grid: method bind

//PRIVACY Directory: getRowFieldByName @14-E36EA290
        public Object getRowFieldByName( String name, PRIVACYRow row ) {
            Object value = null;
            if ( "PRIVACY".equals(name) ) {
                value = row.getPRIVACY();
            }
            return value;
        }
//End PRIVACY Directory: getRowFieldByName

//PRIVACY Grid: method validate @14-104025BA
        boolean validate() {
            return true;
        }
//End PRIVACY Grid: method validate

//PRIVACY Grid Tail @14-FCB6E20C
    }
//End PRIVACY Grid Tail

//AD4_UTENTE Record @6-20741C0E
    final class AD4_UTENTEClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_UTENTE Record

//AD4_UTENTE Record: method perform @6-8CBD5765
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
//End AD4_UTENTE Record: method perform

//AD4_UTENTE Record: children actions @6-29C06EE3
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_UTENTE".equals(formName)) {
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
            readVIA(model.getListBox("VIA"));
            readPROVINCIA(model.getListBox("PROVINCIA"));
            readCOMUNE(model.getListBox("COMUNE"));
//End AD4_UTENTE Record: children actions

//AD4_UTENTE Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_UTENTE Record: method perform tail

//Button_Insert Button @9-A60A3466
        void Button_InsertAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Insert");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            InsertAction();
        }
//End Button_Insert Button

//Button_Update Button @10-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @11-79294D0A
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//ListBoxAction @35-CFA0E4B0
        protected void readVIA(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "Corso;Corso;Largo;Largo;Piazza;Piazza;Via;Via;Viale;Viale;Vicolo;Vicolo" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @38-AD106F0B
        protected void readPROVINCIA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT *  "
                        + "FROM AD4_PROVINCIE" );
            command.setOrder( "DENOMINAZIONE" );

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

//ListBoxAction @40-78B8C1DD
        protected void readCOMUNE(com.codecharge.components.ListBox model) {

            LongField postPROVINCIA = new LongField(null, null);
            
            try {
                postPROVINCIA.setValue( page.getHttpPostParams().getParameter("PROVINCIA"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter" );
                return;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter" );
                return;
            }
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "SELECT * FROM AD4_COMUNI  where PROVINCIA_STATO = {PROV} " );
            if ( postPROVINCIA.getObjectValue() == null ) postPROVINCIA.setValue( 0 );
            command.addParameter( "PROV", postPROVINCIA, null );
            command.setOrder( "DENOMINAZIONE" );

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

void read() { //AD4_UTENTE Record: method read @6-7F8AAE5A

//AD4_UTENTE Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_UTENTE Record: method read head

//AD4_UTENTE Record: init DataSource @6-143E6592
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_UTENTEDataObject ds = new AD4_UTENTEDataObject(page);
            ds.setComponent( model );
            ds.setSesModulo( SessionStorage.getInstance(req).getAttribute("Modulo") );
            ds.setSesIstanza( SessionStorage.getInstance(req).getAttribute("Istanza") );
            try {
                ds.setPostSTATO_NASCITA( page.getHttpPostParams().getParameter("STATO_NASCITA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'STATO_NASCITA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'STATO_NASCITA'" );
            }
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_UTENTE Record: init DataSource

//AD4_UTENTE Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_UTENTE Record: check errors

} //AD4_UTENTE Record: method read tail @6-FCB6E20C

//AD4_UTENTE Record: bind @6-DC0E8777
            public void bind(com.codecharge.components.Component model, AD4_UTENTERow row ) {
                if ( model == null || row == null ) return;
                model.getControl("POSTA_OBBL1").setValue(row.getPOSTA_OBBL1());
                model.getControl("POSTA_OBBL2").setValue(row.getPOSTA_OBBL2());
                model.getControl("POSTA_OBBL3").setValue(row.getPOSTA_OBBL3());
                model.getControl("POSTA_OBBL4").setValue(row.getPOSTA_OBBL4());
                model.getControl("MAIL_OBBL1").setValue(row.getMAIL_OBBL1());
                model.getControl("SMS_OBBL1").setValue(row.getSMS_OBBL1());
                model.getControl("FAX_OBBL1").setValue(row.getFAX_OBBL1());
                if ( this.valid ) {
                    model.getControl("REDIRECTION").setValue(row.getREDIRECTION());
                }
            }
//End AD4_UTENTE Record: bind

//AD4_UTENTE Record: getRowFieldByName @6-10277D8C
            public Object getRowFieldByName( String name, AD4_UTENTERow row ) {
                Object value = null;
                if ( "NOMINATIVO".equals(name) ) {
                    value = row.getNOMINATIVO();
                } else if ( "COGNOME".equals(name) ) {
                    value = row.getCOGNOME();
                } else if ( "NOME".equals(name) ) {
                    value = row.getNOME();
                } else if ( "POSTA_OBBL1".equals(name) ) {
                    value = row.getPOSTA_OBBL1();
                } else if ( "VIA".equals(name) ) {
                    value = row.getVIA();
                } else if ( "INDIRIZZO".equals(name) ) {
                    value = row.getINDIRIZZO();
                } else if ( "NUM".equals(name) ) {
                    value = row.getNUM();
                } else if ( "POSTA_OBBL2".equals(name) ) {
                    value = row.getPOSTA_OBBL2();
                } else if ( "PROVINCIA".equals(name) ) {
                    value = row.getPROVINCIA();
                } else if ( "POSTA_OBBL3".equals(name) ) {
                    value = row.getPOSTA_OBBL3();
                } else if ( "COMUNE".equals(name) ) {
                    value = row.getCOMUNE();
                } else if ( "POSTA_OBBL4".equals(name) ) {
                    value = row.getPOSTA_OBBL4();
                } else if ( "CAP".equals(name) ) {
                    value = row.getCAP();
                } else if ( "MAIL_OBBL1".equals(name) ) {
                    value = row.getMAIL_OBBL1();
                } else if ( "INDIRIZZO_WEB".equals(name) ) {
                    value = row.getINDIRIZZO_WEB();
                } else if ( "SMS_OBBL1".equals(name) ) {
                    value = row.getSMS_OBBL1();
                } else if ( "TELEFONO".equals(name) ) {
                    value = row.getTELEFONO();
                } else if ( "FAX_OBBL1".equals(name) ) {
                    value = row.getFAX_OBBL1();
                } else if ( "FAX".equals(name) ) {
                    value = row.getFAX();
                } else if ( "RR".equals(name) ) {
                    value = row.getRR();
                } else if ( "REDIRECTION".equals(name) ) {
                    value = row.getREDIRECTION();
                }
                return value;
            }
//End AD4_UTENTE Record: getRowFieldByName

void InsertAction() { //AD4_UTENTE Record: method insert @6-11643485

//AD4_UTENTE Record: method insert head @6-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_UTENTE Record: method insert head

//AD4_UTENTE Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4_UTENTE Record: components insert actions

} //AD4_UTENTE Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4_UTENTE Record: method update @6-5771D0AA

//AD4_UTENTE Record: method update head @6-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_UTENTE Record: method update head

//AD4_UTENTE Record: method update body @6-188E65A7
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_UTENTEDataObject ds = new AD4_UTENTEDataObject(page);
            ds.setComponent( model );
            AD4_UTENTERow row = new AD4_UTENTERow();
            ds.setRow(row);
//End AD4_UTENTE Record: method update body

//AD4_UTENTE Record: ds.update @6-6E956EDC
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
//End AD4_UTENTE Record: ds.update

} //AD4_UTENTE Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4_UTENTE Record: method delete @6-11FC2E1E

//AD4_UTENTE Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4_UTENTE Record: components delete actions

} //AD4_UTENTE Record: method delete tail @6-FCB6E20C

//AD4_UTENTE Record: method validate @6-822F08A0
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox NOMINATIVO = (com.codecharge.components.TextBox) model.getChild( "NOMINATIVO" );
            if (! NOMINATIVO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox COGNOME = (com.codecharge.components.TextBox) model.getChild( "COGNOME" );
            if (! COGNOME.validate()) { isControlError = true; }

            com.codecharge.components.TextBox NOME = (com.codecharge.components.TextBox) model.getChild( "NOME" );
            if (! NOME.validate()) { isControlError = true; }

            com.codecharge.components.ListBox VIA = (com.codecharge.components.ListBox) model.getChild( "VIA" );
            if (! VIA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox INDIRIZZO = (com.codecharge.components.TextBox) model.getChild( "INDIRIZZO" );
            if (! INDIRIZZO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox NUM = (com.codecharge.components.TextBox) model.getChild( "NUM" );
            if (! NUM.validate()) { isControlError = true; }

            com.codecharge.components.ListBox PROVINCIA = (com.codecharge.components.ListBox) model.getChild( "PROVINCIA" );
            if (! PROVINCIA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox COMUNE = (com.codecharge.components.ListBox) model.getChild( "COMUNE" );
            if (! COMUNE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox CAP = (com.codecharge.components.TextBox) model.getChild( "CAP" );
            if (! CAP.validate()) { isControlError = true; }

            com.codecharge.components.TextBox INDIRIZZO_WEB = (com.codecharge.components.TextBox) model.getChild( "INDIRIZZO_WEB" );
            if (! INDIRIZZO_WEB.validate()) { isControlError = true; }

            com.codecharge.components.TextBox TELEFONO = (com.codecharge.components.TextBox) model.getChild( "TELEFONO" );
            if (! TELEFONO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox FAX = (com.codecharge.components.TextBox) model.getChild( "FAX" );
            if (! FAX.validate()) { isControlError = true; }

            com.codecharge.components.Hidden RR = (com.codecharge.components.Hidden) model.getChild( "RR" );
            if (! RR.validate()) { isControlError = true; }

            com.codecharge.components.Hidden REDIRECTION = (com.codecharge.components.Hidden) model.getChild( "REDIRECTION" );
            if (! REDIRECTION.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_UTENTE Record: method validate

//AD4_UTENTE Record Tail @6-FCB6E20C
    }
//End AD4_UTENTE Record Tail

//AmvRegistrazioneLiteInizio Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AmvRegistrazioneLiteInizio Page: method validate

//AmvRegistrazioneLiteInizioAction Tail @1-FCB6E20C
}
//End AmvRegistrazioneLiteInizioAction Tail
