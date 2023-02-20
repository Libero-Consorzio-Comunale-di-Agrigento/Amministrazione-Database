//AD4GruppiUtenteElencoAction imports @1-8E71F018
package ad4web.AD4GruppiUtenteElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GruppiUtenteElencoAction imports

//AD4GruppiUtenteElencoAction class @1-79960A7F
public class AD4GruppiUtenteElencoAction extends Action {

//End AD4GruppiUtenteElencoAction class

//AD4GruppiUtenteElencoAction: method perform @1-2EB59FA7
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GruppiUtenteElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GruppiUtenteElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GruppiUtenteElencoAction: method perform

//AD4GruppiUtenteElencoAction: call children actions @1-4EDE8DE1
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
            AD4_UTENTIClass AD4_UTENTI = new AD4_UTENTIClass();
            if ( ( redirect = AD4_UTENTI.perform( page.getRecord("AD4_UTENTI")) ) != null ) result = redirect;
        }
        if (result == null) {
            DISPONIBILIClass DISPONIBILI = new DISPONIBILIClass();
            if ( ( redirect = DISPONIBILI.perform( page.getRecord("DISPONIBILI")) ) != null ) result = redirect;
        }
        if (result == null) {
            ASSEGNATIClass ASSEGNATI = new ASSEGNATIClass();
            if ( ( redirect = ASSEGNATI.perform( page.getRecord("ASSEGNATI")) ) != null ) result = redirect;
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
//End AD4GruppiUtenteElencoAction: call children actions

//AD4_UTENTI Record @78-2850471E
    final class AD4_UTENTIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_UTENTI Record

//AD4_UTENTI Record: method perform @78-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4_UTENTI Record: method perform

//AD4_UTENTI Record: children actions @78-8EC3EBE6
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_UTENTI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4_UTENTI Record: children actions

//AD4_UTENTI Record: method perform tail @78-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_UTENTI Record: method perform tail

void read() { //AD4_UTENTI Record: method read @78-7F8AAE5A

//AD4_UTENTI Record: method read head @78-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_UTENTI Record: method read head

//AD4_UTENTI Record: init DataSource @78-513355DF
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_UTENTIDataObject ds = new AD4_UTENTIDataObject(page);
            ds.setComponent( model );
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_UTENTI Record: init DataSource

//AD4_UTENTI Record: check errors @78-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_UTENTI Record: check errors

} //AD4_UTENTI Record: method read tail @78-FCB6E20C

//AD4_UTENTI Record: bind @78-11121735
            public void bind(com.codecharge.components.Component model, AD4_UTENTIRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("NOMINATIVO").setValue(row.getNOMINATIVO());
                if ( this.valid ) {
                }
            }
//End AD4_UTENTI Record: bind

//AD4_UTENTI Record: getRowFieldByName @78-ABE6170D
            public Object getRowFieldByName( String name, AD4_UTENTIRow row ) {
                Object value = null;
                if ( "NOMINATIVO".equals(name) ) {
                    value = row.getNOMINATIVO();
                }
                return value;
            }
//End AD4_UTENTI Record: getRowFieldByName

void InsertAction() { //AD4_UTENTI Record: method insert @78-11643485

//AD4_UTENTI Record: components insert actions @78-68525650
            if (! model.hasErrors()) {
            }
//End AD4_UTENTI Record: components insert actions

} //AD4_UTENTI Record: method insert tail @78-FCB6E20C

void UpdateAction() { //AD4_UTENTI Record: method update @78-5771D0AA

//AD4_UTENTI Record: components update actions @78-68525650
            if (! model.hasErrors()) {
            }
//End AD4_UTENTI Record: components update actions

} //AD4_UTENTI Record: method update tail @78-FCB6E20C

void DeleteAction() { //AD4_UTENTI Record: method delete @78-11FC2E1E

//AD4_UTENTI Record: components delete actions @78-68525650
            if (! model.hasErrors()) {
            }
//End AD4_UTENTI Record: components delete actions

} //AD4_UTENTI Record: method delete tail @78-FCB6E20C

//AD4_UTENTI Record: method validate @78-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_UTENTI Record: method validate

//AD4_UTENTI Record Tail @78-FCB6E20C
    }
//End AD4_UTENTI Record Tail

//DISPONIBILI Record @52-ABF6303A
    final class DISPONIBILIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End DISPONIBILI Record

//DISPONIBILI Record: method perform @52-30DEE94A
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Uno" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Tutti" ).setVisible( false );
//End DISPONIBILI Record: method perform

//DISPONIBILI Record: children actions @52-EF0ED025
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("DISPONIBILI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Tutti") != null) {
                        if (validate()) {
                            TuttiAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Uno") != null) {
                        if (validate()) {
                            UnoAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readUTENTE_D(model.getListBox("UTENTE_D"));
//End DISPONIBILI Record: children actions

//DISPONIBILI Record: method perform tail @52-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End DISPONIBILI Record: method perform tail

//Tutti Button @55-A7DCB9A6
        void TuttiAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Tutti");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Tutti Button

//Uno Button @56-C01A99F0
        void UnoAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Uno");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Uno Button

//ListBoxAction @53-507B9FD6
        protected void readUTENTE_D(com.codecharge.components.ListBox model) {

            TextField sesAD4UTENTE = new TextField(null, null);
            
            sesAD4UTENTE.setValue( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select nominativo nome_utente,  "
                        + "utente "
                        + "  from UTENTI "
                        + " where tipo_utente = 'G' "
                        + "   and utente <> 'ric_abil' "
                        + "   and utente not in (select gruppo "
                        + "                        from UTENTI_GRUPPO "
                        + "                       where utente = '{UTENTE}' "
                        + "                      ) "
                        + " " );
            if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
            command.addParameter( "UTENTE", sesAD4UTENTE, null );
            command.setOrder( "1" );

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

void read() { //DISPONIBILI Record: method read @52-7F8AAE5A

//DISPONIBILI Record: method read head @52-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End DISPONIBILI Record: method read head

//DISPONIBILI Record: init DataSource @52-647D1AC6
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            DISPONIBILIDataObject ds = new DISPONIBILIDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End DISPONIBILI Record: init DataSource

//DISPONIBILI Record: check errors @52-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End DISPONIBILI Record: check errors

} //DISPONIBILI Record: method read tail @52-FCB6E20C

//DISPONIBILI Record: bind @52-895CAFBE
            public void bind(com.codecharge.components.Component model, DISPONIBILIRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End DISPONIBILI Record: bind

//DISPONIBILI Record: getRowFieldByName @52-F6E8CE38
            public Object getRowFieldByName( String name, DISPONIBILIRow row ) {
                Object value = null;
                if ( "UTENTE_D".equals(name) ) {
                    value = row.getUTENTE_D();
                }
                return value;
            }
//End DISPONIBILI Record: getRowFieldByName

void InsertAction() { //DISPONIBILI Record: method insert @52-11643485

//DISPONIBILI Record: method insert head @52-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End DISPONIBILI Record: method insert head

//DISPONIBILI Record: components insert actions @52-68525650
            if (! model.hasErrors()) {
            }
//End DISPONIBILI Record: components insert actions

} //DISPONIBILI Record: method insert tail @52-FCB6E20C

void UpdateAction() { //DISPONIBILI Record: method update @52-5771D0AA

//DISPONIBILI Record: method update head @52-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End DISPONIBILI Record: method update head

//DISPONIBILI Record: method update body @52-A5FD26C2
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            DISPONIBILIDataObject ds = new DISPONIBILIDataObject(page);
            ds.setComponent( model );
            DISPONIBILIRow row = new DISPONIBILIRow();
            ds.setRow(row);
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setPostUTENTE_D( page.getHttpPostParams().getParameter("UTENTE_D") );
//End DISPONIBILI Record: method update body

//DISPONIBILI Record: ds.update @52-6E956EDC
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
//End DISPONIBILI Record: ds.update

} //DISPONIBILI Record: method update tail @52-FCB6E20C

void DeleteAction() { //DISPONIBILI Record: method delete @52-11FC2E1E

//DISPONIBILI Record: method delete head @52-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End DISPONIBILI Record: method delete head

//DISPONIBILI Record: method delete body @52-7DA710C5
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            DISPONIBILIDataObject ds = new DISPONIBILIDataObject(page);
            ds.setComponent( model );
            DISPONIBILIRow row = new DISPONIBILIRow();
            ds.setRow(row);
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
//End DISPONIBILI Record: method delete body

//DISPONIBILI Record: ds.delete @52-3584344F
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
//End DISPONIBILI Record: ds.delete

} //DISPONIBILI Record: method delete tail @52-FCB6E20C

//DISPONIBILI Record: method validate @52-068630F0
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox UTENTE_D = (com.codecharge.components.ListBox) model.getChild( "UTENTE_D" );
            if (! UTENTE_D.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End DISPONIBILI Record: method validate

//DISPONIBILI Record Tail @52-FCB6E20C
    }
//End DISPONIBILI Record Tail

//ASSEGNATI Record @63-820EEF07
    final class ASSEGNATIClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ASSEGNATI Record

//ASSEGNATI Record: method perform @63-30DEE94A
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Uno" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Tutti" ).setVisible( false );
//End ASSEGNATI Record: method perform

//ASSEGNATI Record: children actions @63-DAF3A661
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ASSEGNATI".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Uno") != null) {
                        if (validate()) {
                            UnoAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Tutti") != null) {
                        if (validate()) {
                            TuttiAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readUTENTE_A(model.getListBox("UTENTE_A"));
//End ASSEGNATI Record: children actions

//ASSEGNATI Record: method perform tail @63-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ASSEGNATI Record: method perform tail

//Uno Button @66-C01A99F0
        void UnoAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Uno");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Uno Button

//Tutti Button @68-A7DCB9A6
        void TuttiAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Tutti");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Tutti Button

//ListBoxAction @64-F24F2855
        protected void readUTENTE_A(com.codecharge.components.ListBox model) {

            TextField sesAD4UTENTE = new TextField(null, null);
            
            sesAD4UTENTE.setValue( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select uten.nominativo nome_utente, uten.utente "
                        + "  from UTENTI uten,  "
                        + "UTENTI_GRUPPO utgr "
                        + " where uten.tipo_utente = 'G' "
                        + "   and utgr.gruppo <> 'ric_abil' "
                        + "   and utgr.gruppo = uten.utente "
                        + "   and utgr.utente = '{UTENTE}' "
                        + " " );
            if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
            command.addParameter( "UTENTE", sesAD4UTENTE, null );
            command.setOrder( "1" );

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

void read() { //ASSEGNATI Record: method read @63-7F8AAE5A

//ASSEGNATI Record: method read head @63-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ASSEGNATI Record: method read head

//ASSEGNATI Record: init DataSource @63-7DAF4F08
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ASSEGNATIDataObject ds = new ASSEGNATIDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ASSEGNATI Record: init DataSource

//ASSEGNATI Record: check errors @63-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ASSEGNATI Record: check errors

} //ASSEGNATI Record: method read tail @63-FCB6E20C

//ASSEGNATI Record: bind @63-B4FABB83
            public void bind(com.codecharge.components.Component model, ASSEGNATIRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End ASSEGNATI Record: bind

//ASSEGNATI Record: getRowFieldByName @63-84342451
            public Object getRowFieldByName( String name, ASSEGNATIRow row ) {
                Object value = null;
                if ( "UTENTE_A".equals(name) ) {
                    value = row.getUTENTE_A();
                }
                return value;
            }
//End ASSEGNATI Record: getRowFieldByName

void InsertAction() { //ASSEGNATI Record: method insert @63-11643485

//ASSEGNATI Record: method insert head @63-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End ASSEGNATI Record: method insert head

//ASSEGNATI Record: components insert actions @63-68525650
            if (! model.hasErrors()) {
            }
//End ASSEGNATI Record: components insert actions

} //ASSEGNATI Record: method insert tail @63-FCB6E20C

void UpdateAction() { //ASSEGNATI Record: method update @63-5771D0AA

//ASSEGNATI Record: method update head @63-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End ASSEGNATI Record: method update head

//ASSEGNATI Record: method update body @63-15CD00A4
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            ASSEGNATIDataObject ds = new ASSEGNATIDataObject(page);
            ds.setComponent( model );
            ASSEGNATIRow row = new ASSEGNATIRow();
            ds.setRow(row);
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setPostUTENTE_A( page.getHttpPostParams().getParameter("UTENTE_A") );
//End ASSEGNATI Record: method update body

//ASSEGNATI Record: ds.update @63-6E956EDC
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
//End ASSEGNATI Record: ds.update

} //ASSEGNATI Record: method update tail @63-FCB6E20C

void DeleteAction() { //ASSEGNATI Record: method delete @63-11FC2E1E

//ASSEGNATI Record: method delete head @63-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End ASSEGNATI Record: method delete head

//ASSEGNATI Record: method delete body @63-1D35BEED
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            ASSEGNATIDataObject ds = new ASSEGNATIDataObject(page);
            ds.setComponent( model );
            ASSEGNATIRow row = new ASSEGNATIRow();
            ds.setRow(row);
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
//End ASSEGNATI Record: method delete body

//ASSEGNATI Record: ds.delete @63-3584344F
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
//End ASSEGNATI Record: ds.delete

} //ASSEGNATI Record: method delete tail @63-FCB6E20C

//ASSEGNATI Record: method validate @63-3EE31FEA
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox UTENTE_A = (com.codecharge.components.ListBox) model.getChild( "UTENTE_A" );
            if (! UTENTE_A.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ASSEGNATI Record: method validate

//ASSEGNATI Record Tail @63-FCB6E20C
    }
//End ASSEGNATI Record Tail

//AD4GruppiUtenteElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GruppiUtenteElenco Page: method validate

//AD4GruppiUtenteElencoAction Tail @1-FCB6E20C
}
//End AD4GruppiUtenteElencoAction Tail

