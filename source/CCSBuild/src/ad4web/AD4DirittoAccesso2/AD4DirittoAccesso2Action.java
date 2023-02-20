//AD4DirittoAccesso2Action imports @1-B8A40044
package ad4web.AD4DirittoAccesso2;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4DirittoAccesso2Action imports

//AD4DirittoAccesso2Action class @1-8E9E478F
public class AD4DirittoAccesso2Action extends Action {

//End AD4DirittoAccesso2Action class

//AD4DirittoAccesso2Action: method perform @1-B49F9F3B
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4DirittoAccesso2Model( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4DirittoAccesso2Model", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4DirittoAccesso2Action: method perform

//AD4DirittoAccesso2Action: call children actions @1-2FD5C0D3
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
            AD4_DIRITTI_ACCESSO1Class AD4_DIRITTI_ACCESSO1 = new AD4_DIRITTI_ACCESSO1Class();
            if ( ( redirect = AD4_DIRITTI_ACCESSO1.perform( page.getRecord("AD4_DIRITTI_ACCESSO1")) ) != null ) result = redirect;
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
//End AD4DirittoAccesso2Action: call children actions

//AD4_DIRITTI_ACCESSO1 Record @38-31557DBB
    final class AD4_DIRITTI_ACCESSO1Class {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_DIRITTI_ACCESSO1 Record

//AD4_DIRITTI_ACCESSO1 Record: method perform @38-4F85605F
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
            if ( ! model.isAllowDelete() ) model.getChild( "Button_Delete" ).setVisible( false );
//End AD4_DIRITTI_ACCESSO1 Record: method perform

//AD4_DIRITTI_ACCESSO1 Record: children actions @38-43C2E219
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_DIRITTI_ACCESSO1".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Refresh") != null) {
                        RefreshSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
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
                        RefreshSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                    if (page.getParameter("Refresh") != null) {
                        RefreshSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                        RefreshSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                }
            }
            setProperties(model, Action.GET, true );
            read();
            readMODULO(model.getListBox("MODULO"));
            readISTANZA(model.getListBox("ISTANZA"));
            readRUOLO(model.getListBox("RUOLO"));
//End AD4_DIRITTI_ACCESSO1 Record: children actions

//AD4_DIRITTI_ACCESSO1 Record: method perform tail @38-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_DIRITTI_ACCESSO1 Record: method perform tail

//Refresh Button @152-BF199923
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

//Button_Update Button @40-5A4B6BAD
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Delete Button @41-79294D0A
        void Button_DeleteAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Delete");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            DeleteAction();
        }
//End Button_Delete Button

//Button_Cancel Button @43-BEBD3B4D
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

//ListBoxAction @46-6BCC4460
        protected void readMODULO(com.codecharge.components.ListBox model) {

            TextField urlISTANZA = new TextField(null, null);
            
            urlISTANZA.setValue( page.getHttpGetParams().getParameter("ISTANZA") );
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
                        + " WHERE '{ISTANZA}'IS NOT NULL "
                        + "   AND PROGETTO in (SELECT PROGETTO "
                        + "                      FROM ISTANZE "
                        + "                     WHERE ISTANZA = '{ISTANZA}') "
                        + "UNION "
                        + "SELECT MODULO, DESCRIZIONE   "
                        + "  FROM MODULI "
                        + " WHERE '{ISTANZA}' IS NULL        "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
            command.addParameter( "ISTANZA", urlISTANZA, null );
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

//ListBoxAction @47-88A96D40
        protected void readISTANZA(com.codecharge.components.ListBox model) {

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

            command.setSql( "SELECT ISTANZA,  "
                        + "DESCRIZIONE  "
                        + "  FROM ISTANZE "
                        + " WHERE '{MODULO}' IS NOT NULL "
                        + "   AND PROGETTO in (SELECT PROGETTO "
                        + "                      FROM MODULI "
                        + "                     WHERE MODULO = '{MODULO}') "
                        + "UNION "
                        + "SELECT ISTANZA, DESCRIZIONE  "
                        + "  FROM ISTANZE "
                        + " WHERE '{MODULO_SCELTO}' IS NULL "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
            command.addParameter( "MODULO", urlMODULO, null );
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

//ListBoxAction @48-BDAFA50C
        protected void readRUOLO(com.codecharge.components.ListBox model) {

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

            command.setSql( "SELECT DISTINCT RUOL.*  "
                        + "FROM RUOLI RUOL,  "
                        + "MODULI MODU "
                        + "WHERE ('{MODULO}' IS NULL "
                        + "   OR (   MODU.MODULO = '{MODULO}' "
                        + "      AND NVL(RUOL.PROGETTO,MODU.PROGETTO) = MODU.PROGETTO "
                        + "      AND NVL(RUOL.MODULO,MODU.MODULO) = MODU.MODULO "
                        + "      )) "
                        + "  AND RUOL.RUOLO not in ('*', 'def') "
                        + "  AND STATO = 'U' "
                        + "" );
            if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
            command.addParameter( "MODULO", urlMODULO, null );
            command.setOrder( "RUOL.DESCRIZIONE" );

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

void read() { //AD4_DIRITTI_ACCESSO1 Record: method read @38-7F8AAE5A

//AD4_DIRITTI_ACCESSO1 Record: method read head @38-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_DIRITTI_ACCESSO1 Record: method read head

//AD4_DIRITTI_ACCESSO1 Record: init DataSource @38-D29A6C95
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_DIRITTI_ACCESSO1DataObject ds = new AD4_DIRITTI_ACCESSO1DataObject(page);
            ds.setComponent( model );
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setUrlISLISTBOX( page.getHttpGetParams().getParameter("ISLISTBOX") );
            ds.setPostRUOLO( page.getHttpPostParams().getParameter("RUOLO") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
            ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV") );
            ds.setSesAD4GRUPPO( SessionStorage.getInstance(req).getAttribute("AD4GRUPPO") );
            ds.setSesAD4REFRESHSLAVES( SessionStorage.getInstance(req).getAttribute("AD4REFRESHSLAVES") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_DIRITTI_ACCESSO1 Record: init DataSource

//AD4_DIRITTI_ACCESSO1 Record: check errors @38-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_DIRITTI_ACCESSO1 Record: check errors

} //AD4_DIRITTI_ACCESSO1 Record: method read tail @38-FCB6E20C

//AD4_DIRITTI_ACCESSO1 Record: bind @38-5DD762C8
            public void bind(com.codecharge.components.Component model, AD4_DIRITTI_ACCESSO1Row row ) {
                if ( model == null || row == null ) return;
                model.getControl("NOMINATIVO").setValue(row.getNOMINATIVO());
                model.getControl("ULTIMO_ACCESSO").setValue(row.getULTIMO_ACCESSO());
                model.getControl("NUMERO_ACCESSI").setValue(row.getNUMERO_ACCESSI());
                model.getControl("GRUPPO").setValue(row.getGRUPPO());
                model.getLink("CaratteristicheAccesso").getParameter("TIPO_ACCESSO").setValue( getRowFieldByName(model.getLink("CaratteristicheAccesso").getParameter("TIPO_ACCESSO").getSourceName(), row ));
                model.getLink("CaratteristicheAccesso").getParameter("PROGETTO").setValue( getRowFieldByName(model.getLink("CaratteristicheAccesso").getParameter("PROGETTO").getSourceName(), row ));
                model.getLink("CaratteristicheAccesso").getParameter("MODULO").setValue( getRowFieldByName(model.getLink("CaratteristicheAccesso").getParameter("MODULO").getSourceName(), row ));
                model.getLink("CaratteristicheAccesso").getParameter("ISTANZA").setValue( getRowFieldByName(model.getLink("CaratteristicheAccesso").getParameter("ISTANZA").getSourceName(), row ));
                model.getLink("CaratteristicheAccesso").getParameter("UTENTE").setValue( getRowFieldByName(model.getLink("CaratteristicheAccesso").getParameter("UTENTE").getSourceName(), row ));
                if ( this.valid ) {
                    model.getControl("SEQUENZA").setValue(row.getSEQUENZA());
                    model.getControl("UTENTE").setValue(row.getUTENTE());
                    model.getControl("MODULO").setValue(row.getMODULO());
                    model.getControl("MODULO_ORIG").setValue(row.getMODULO_ORIG());
                    model.getControl("ISTANZA").setValue(row.getISTANZA());
                    model.getControl("ISTANZA_ORIG").setValue(row.getISTANZA_ORIG());
                    model.getControl("RUOLO").setValue(row.getRUOLO());
                    model.getControl("NOTE").setValue(row.getNOTE());
                }
            }
//End AD4_DIRITTI_ACCESSO1 Record: bind

//AD4_DIRITTI_ACCESSO1 Record: getRowFieldByName @38-68253B6E
            public Object getRowFieldByName( String name, AD4_DIRITTI_ACCESSO1Row row ) {
                Object value = null;
                if ( "NOMINATIVO".equals(name) ) {
                    value = row.getNOMINATIVO();
                } else if ( "CaratteristicheAccesso".equals(name) ) {
                    value = row.getCaratteristicheAccesso();
                } else if ( "SEQUENZA".equals(name) ) {
                    value = row.getSEQUENZA();
                } else if ( "UTENTE".equals(name) ) {
                    value = row.getUTENTE();
                } else if ( "ISLISTBOX".equals(name) ) {
                    value = row.getISLISTBOX();
                } else if ( "MODULO".equals(name) ) {
                    value = row.getMODULO();
                } else if ( "MODULO_ORIG".equals(name) ) {
                    value = row.getMODULO_ORIG();
                } else if ( "ISTANZA".equals(name) ) {
                    value = row.getISTANZA();
                } else if ( "ISTANZA_ORIG".equals(name) ) {
                    value = row.getISTANZA_ORIG();
                } else if ( "RUOLO".equals(name) ) {
                    value = row.getRUOLO();
                } else if ( "NOTE".equals(name) ) {
                    value = row.getNOTE();
                } else if ( "ULTIMO_ACCESSO".equals(name) ) {
                    value = row.getULTIMO_ACCESSO();
                } else if ( "NUMERO_ACCESSI".equals(name) ) {
                    value = row.getNUMERO_ACCESSI();
                } else if ( "GRUPPO".equals(name) ) {
                    value = row.getGRUPPO();
                } else if ( "TIPO_ACCESSO".equals(name) ) {
                    value = row.getTIPO_ACCESSO();
                } else if ( "PROGETTO".equals(name) ) {
                    value = row.getPROGETTO();
                }
                return value;
            }
//End AD4_DIRITTI_ACCESSO1 Record: getRowFieldByName

void InsertAction() { //AD4_DIRITTI_ACCESSO1 Record: method insert @38-11643485

//AD4_DIRITTI_ACCESSO1 Record: method insert head @38-6D5B77FE
            if (!model.isAllowInsert()) return;
            model.fireBeforeInsertEvent();
//End AD4_DIRITTI_ACCESSO1 Record: method insert head

//AD4_DIRITTI_ACCESSO1 Record: components insert actions @38-68525650
            if (! model.hasErrors()) {
            }
//End AD4_DIRITTI_ACCESSO1 Record: components insert actions

} //AD4_DIRITTI_ACCESSO1 Record: method insert tail @38-FCB6E20C

void UpdateAction() { //AD4_DIRITTI_ACCESSO1 Record: method update @38-5771D0AA

//AD4_DIRITTI_ACCESSO1 Record: method update head @38-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_DIRITTI_ACCESSO1 Record: method update head

//AD4_DIRITTI_ACCESSO1 Record: method update body @38-EE40A9BD
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_DIRITTI_ACCESSO1DataObject ds = new AD4_DIRITTI_ACCESSO1DataObject(page);
            ds.setComponent( model );
            AD4_DIRITTI_ACCESSO1Row row = new AD4_DIRITTI_ACCESSO1Row();
            ds.setRow(row);
            try {
                ds.setPostSEQUENZA( page.getHttpPostParams().getParameter("SEQUENZA"), null );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'SEQUENZA'" );
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'SEQUENZA'" );
            }
            row.setUTENTE(Utils.convertToString(model.getControl("UTENTE").getValue()));
            ds.setPostMODULO( page.getHttpPostParams().getParameter("MODULO") );
            row.setMODULO_ORIG(Utils.convertToString(model.getControl("MODULO_ORIG").getValue()));
            ds.setPostISTANZA( page.getHttpPostParams().getParameter("ISTANZA") );
            row.setISTANZA_ORIG(Utils.convertToString(model.getControl("ISTANZA_ORIG").getValue()));
            ds.setPostRUOLO( page.getHttpPostParams().getParameter("RUOLO") );
            ds.setPostNOTE( page.getHttpPostParams().getParameter("NOTE") );
//End AD4_DIRITTI_ACCESSO1 Record: method update body

//AD4_DIRITTI_ACCESSO1 Record: ds.update @38-6E956EDC
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
//End AD4_DIRITTI_ACCESSO1 Record: ds.update

} //AD4_DIRITTI_ACCESSO1 Record: method update tail @38-FCB6E20C

void DeleteAction() { //AD4_DIRITTI_ACCESSO1 Record: method delete @38-11FC2E1E

//AD4_DIRITTI_ACCESSO1 Record: method delete head @38-9435BE8B
            if (!model.isAllowDelete()) return;
            model.fireBeforeDeleteEvent();
//End AD4_DIRITTI_ACCESSO1 Record: method delete head

//AD4_DIRITTI_ACCESSO1 Record: method delete body @38-4D55EE87
            if (!model.isAllowDelete()) return;
            boolean isErrors = false;
            AD4_DIRITTI_ACCESSO1DataObject ds = new AD4_DIRITTI_ACCESSO1DataObject(page);
            ds.setComponent( model );
            AD4_DIRITTI_ACCESSO1Row row = new AD4_DIRITTI_ACCESSO1Row();
            ds.setRow(row);
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setSesAD4GRUPPO( SessionStorage.getInstance(req).getAttribute("AD4GRUPPO") );
            ds.setPostISTANZA( page.getHttpPostParams().getParameter("ISTANZA") );
            ds.setPostMODULO( page.getHttpPostParams().getParameter("MODULO") );
            ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV") );
//End AD4_DIRITTI_ACCESSO1 Record: method delete body

//AD4_DIRITTI_ACCESSO1 Record: ds.delete @38-3584344F
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
//End AD4_DIRITTI_ACCESSO1 Record: ds.delete

} //AD4_DIRITTI_ACCESSO1 Record: method delete tail @38-FCB6E20C

//AD4_DIRITTI_ACCESSO1 Record: method validate @38-C15AE003
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox SEQUENZA = (com.codecharge.components.TextBox) model.getChild( "SEQUENZA" );
            if (! SEQUENZA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden UTENTE = (com.codecharge.components.Hidden) model.getChild( "UTENTE" );
            if (! UTENTE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ISLISTBOX = (com.codecharge.components.Hidden) model.getChild( "ISLISTBOX" );
            if (! ISLISTBOX.validate()) { isControlError = true; }

            com.codecharge.components.ListBox MODULO = (com.codecharge.components.ListBox) model.getChild( "MODULO" );
            if (! MODULO.validate()) { isControlError = true; }

            com.codecharge.components.Hidden MODULO_ORIG = (com.codecharge.components.Hidden) model.getChild( "MODULO_ORIG" );
            if (! MODULO_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.ListBox ISTANZA = (com.codecharge.components.ListBox) model.getChild( "ISTANZA" );
            if (! ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.Hidden ISTANZA_ORIG = (com.codecharge.components.Hidden) model.getChild( "ISTANZA_ORIG" );
            if (! ISTANZA_ORIG.validate()) { isControlError = true; }

            com.codecharge.components.ListBox RUOLO = (com.codecharge.components.ListBox) model.getChild( "RUOLO" );
            if (! RUOLO.validate()) { isControlError = true; }

            com.codecharge.components.TextArea NOTE = (com.codecharge.components.TextArea) model.getChild( "NOTE" );
            if (! NOTE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_DIRITTI_ACCESSO1 Record: method validate

//AD4_DIRITTI_ACCESSO1 Record Tail @38-FCB6E20C
    }
//End AD4_DIRITTI_ACCESSO1 Record Tail

//AD4DirittoAccesso2 Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4DirittoAccesso2 Page: method validate

//AD4DirittoAccesso2Action Tail @1-FCB6E20C
}
//End AD4DirittoAccesso2Action Tail
