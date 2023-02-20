//AD4UtenDiacElencoAction imports @1-8DE55ABA
package ad4web.AD4UtenDiacElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4UtenDiacElencoAction imports

//AD4UtenDiacElencoAction class @1-D49129FD
public class AD4UtenDiacElencoAction extends Action {

//End AD4UtenDiacElencoAction class

//AD4UtenDiacElencoAction: method perform @1-4D00539F
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4UtenDiacElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4UtenDiacElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4UtenDiacElencoAction: method perform

//AD4UtenDiacElencoAction: call children actions @1-6F49E6B6
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
            AD4_DIRITTI_ACCESSOSearchClass AD4_DIRITTI_ACCESSOSearch = new AD4_DIRITTI_ACCESSOSearchClass();
            if ( ( redirect = AD4_DIRITTI_ACCESSOSearch.perform( page.getRecord("AD4_DIRITTI_ACCESSOSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            TITOLOClass TITOLO = new TITOLOClass();
            TITOLO.perform(page.getGrid("TITOLO"));
        }
        if (result == null) {
            AD4_DIRITTI_ACCESSOClass AD4_DIRITTI_ACCESSO = new AD4_DIRITTI_ACCESSOClass();
            AD4_DIRITTI_ACCESSO.perform(page.getGrid("AD4_DIRITTI_ACCESSO"));
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
//End AD4UtenDiacElencoAction: call children actions

//AD4_DIRITTI_ACCESSOSearch Record @151-DC0D9DC6
    final class AD4_DIRITTI_ACCESSOSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_DIRITTI_ACCESSOSearch Record

//AD4_DIRITTI_ACCESSOSearch Record: method perform @151-E1412CC8
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4_DIRITTI_ACCESSOSearch Record: method perform

//AD4_DIRITTI_ACCESSOSearch Record: children actions @151-2E75F37A
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_DIRITTI_ACCESSOSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        Button_DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_MODULO(model.getListBox("s_MODULO"));
            reads_ISTANZA(model.getListBox("s_ISTANZA"));
            reads_RUOLO(model.getListBox("s_RUOLO"));
            reads_GRUPPO(model.getListBox("s_GRUPPO"));
//End AD4_DIRITTI_ACCESSOSearch Record: children actions

//AD4_DIRITTI_ACCESSOSearch Record: method perform tail @151-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_DIRITTI_ACCESSOSearch Record: method perform tail

//Button_DoSearch Button @152-76C06B35
        void Button_DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_DoSearch Button

//ListBoxAction @161-A90F808A
        protected void reads_MODULO(com.codecharge.components.ListBox model) {

            TextField urlMODULO = new TextField(null, null);
            
            urlMODULO.setValue( page.getHttpGetParams().getParameter("MODULO") );
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

            command.setSql( "select modulo, "
                        + "       descrizione "
                        + "  from moduli "
                        + " WHERE MODULO LIKE NVL('{MODULO}','%') "
                        + "   AND PROGETTO IN (SELECT PROGETTO "
                        + "                      FROM ISTANZE "
                        + "                     WHERE ISTANZA LIKE NVL('{ISTANZA}','%')) "
                        + "UNION "
                        + "select '', "
                        + "       '- -' "
                        + "  from dual "
                        + " where 1 < (select count(1) "
                        + "              from moduli "
                        + "             WHERE MODULO LIKE NVL('{MODULO}','%') "
                        + "               AND PROGETTO IN (SELECT PROGETTO "
                        + "                                  FROM ISTANZE "
                        + "                                 WHERE ISTANZA LIKE NVL('{ISTANZA}','%')) "
                        + "           ) "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
            command.addParameter( "MODULO", urlMODULO, null );
            if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
            command.addParameter( "ISTANZA", urlISTANZA, null );
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

//ListBoxAction @162-C0DDDD56
        protected void reads_ISTANZA(com.codecharge.components.ListBox model) {

            TextField urlISTANZA = new TextField(null, null);
            
            urlISTANZA.setValue( page.getHttpGetParams().getParameter("ISTANZA") );
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

            command.setSql( "select istanza, "
                        + "       descrizione "
                        + "  from istanze "
                        + " WHERE ISTANZA LIKE NVL('{ISTANZA}','%') "
                        + "   AND PROGETTO IN (SELECT PROGETTO "
                        + "                      FROM MODULI "
                        + "                     WHERE MODULO LIKE NVL('{MODULO}','%')) "
                        + "UNION "
                        + "select '', "
                        + "       '- -' "
                        + "  from dual "
                        + " where 1 < (select count(1) "
                        + "              from istanze "
                        + "             WHERE ISTANZA LIKE NVL('{ISTANZA}','%') "
                        + "               AND PROGETTO IN (SELECT PROGETTO "
                        + "                                  FROM MODULI "
                        + "                                 WHERE MODULO LIKE NVL('{MODULO}','%')) "
                        + "           )          "
                        + " " );
            if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
            command.addParameter( "ISTANZA", urlISTANZA, null );
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

//ListBoxAction @163-DDD19A73
        protected void reads_RUOLO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select ruolo, "
                        + "       descrizione "
                        + "  from RUOLI "
                        + " where ruolo <> '*' "
                        + " " );
            command.setOrder( "descrizione" );

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

//ListBoxAction @164-02988A65
        protected void reads_GRUPPO(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select utente, "
                        + "       nominativo "
                        + "  from utenti "
                        + " where tipo_utente = 'G' "
                        + " " );
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

void read() { //AD4_DIRITTI_ACCESSOSearch Record: method read @151-7F8AAE5A

//AD4_DIRITTI_ACCESSOSearch Record: method read head @151-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_DIRITTI_ACCESSOSearch Record: method read head

//AD4_DIRITTI_ACCESSOSearch Record: init DataSource @151-1F5FF3F7
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_DIRITTI_ACCESSOSearchDataObject ds = new AD4_DIRITTI_ACCESSOSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_DIRITTI_ACCESSOSearch Record: init DataSource

//AD4_DIRITTI_ACCESSOSearch Record: check errors @151-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_DIRITTI_ACCESSOSearch Record: check errors

} //AD4_DIRITTI_ACCESSOSearch Record: method read tail @151-FCB6E20C

//AD4_DIRITTI_ACCESSOSearch Record: bind @151-9E51829F
            public void bind(com.codecharge.components.Component model, AD4_DIRITTI_ACCESSOSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("MODULO").setValue(row.getMODULO());
                model.getControl("ISTANZA").setValue(row.getISTANZA());
                if ( this.valid ) {
                }
            }
//End AD4_DIRITTI_ACCESSOSearch Record: bind

//AD4_DIRITTI_ACCESSOSearch Record: getRowFieldByName @151-B4A28D09
            public Object getRowFieldByName( String name, AD4_DIRITTI_ACCESSOSearchRow row ) {
                Object value = null;
                if ( "s_MODULO".equals(name) ) {
                    value = row.getS_MODULO();
                } else if ( "MODULO".equals(name) ) {
                    value = row.getMODULO();
                } else if ( "s_ISTANZA".equals(name) ) {
                    value = row.getS_ISTANZA();
                } else if ( "ISTANZA".equals(name) ) {
                    value = row.getISTANZA();
                } else if ( "s_RUOLO".equals(name) ) {
                    value = row.getS_RUOLO();
                } else if ( "s_GRUPPO".equals(name) ) {
                    value = row.getS_GRUPPO();
                }
                return value;
            }
//End AD4_DIRITTI_ACCESSOSearch Record: getRowFieldByName

void InsertAction() { //AD4_DIRITTI_ACCESSOSearch Record: method insert @151-11643485

//AD4_DIRITTI_ACCESSOSearch Record: components insert actions @151-68525650
            if (! model.hasErrors()) {
            }
//End AD4_DIRITTI_ACCESSOSearch Record: components insert actions

} //AD4_DIRITTI_ACCESSOSearch Record: method insert tail @151-FCB6E20C

void UpdateAction() { //AD4_DIRITTI_ACCESSOSearch Record: method update @151-5771D0AA

//AD4_DIRITTI_ACCESSOSearch Record: components update actions @151-68525650
            if (! model.hasErrors()) {
            }
//End AD4_DIRITTI_ACCESSOSearch Record: components update actions

} //AD4_DIRITTI_ACCESSOSearch Record: method update tail @151-FCB6E20C

void DeleteAction() { //AD4_DIRITTI_ACCESSOSearch Record: method delete @151-11FC2E1E

//AD4_DIRITTI_ACCESSOSearch Record: components delete actions @151-68525650
            if (! model.hasErrors()) {
            }
//End AD4_DIRITTI_ACCESSOSearch Record: components delete actions

} //AD4_DIRITTI_ACCESSOSearch Record: method delete tail @151-FCB6E20C

//AD4_DIRITTI_ACCESSOSearch Record: method validate @151-1633C073
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_MODULO = (com.codecharge.components.ListBox) model.getChild( "s_MODULO" );
            if (! s_MODULO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_ISTANZA = (com.codecharge.components.ListBox) model.getChild( "s_ISTANZA" );
            if (! s_ISTANZA.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_RUOLO = (com.codecharge.components.ListBox) model.getChild( "s_RUOLO" );
            if (! s_RUOLO.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_GRUPPO = (com.codecharge.components.ListBox) model.getChild( "s_GRUPPO" );
            if (! s_GRUPPO.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_DIRITTI_ACCESSOSearch Record: method validate

//AD4_DIRITTI_ACCESSOSearch Record Tail @151-FCB6E20C
    }
//End AD4_DIRITTI_ACCESSOSearch Record Tail

//TITOLO Grid @146-66B5150A
    final class TITOLOClass {
        com.codecharge.components.Grid model;
        Event e;
//End TITOLO Grid

//TITOLO Grid: method perform @146-B48879D3
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
//End TITOLO Grid: method perform

//TITOLO Grid: method read: head @146-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End TITOLO Grid: method read: head

//TITOLO Grid: method read: init @146-5E70F9B5
            if ( ! model.allowRead ) return true;
            TITOLODataObject ds = new TITOLODataObject(page);
            ds.setComponent( model );
//End TITOLO Grid: method read: init

//TITOLO Grid: set where parameters @146-5A14B7D1
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setSesAD4BP( SessionStorage.getInstance(req).getAttribute("AD4BP") );
//End TITOLO Grid: set where parameters

//TITOLO Grid: set grid properties @146-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End TITOLO Grid: set grid properties

//TITOLO Grid: retrieve data @146-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End TITOLO Grid: retrieve data

//TITOLO Grid: check errors @146-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End TITOLO Grid: check errors

//TITOLO Grid: method read: tail @146-F575E732
            return ( ! isErrors );
        }
//End TITOLO Grid: method read: tail

//TITOLO Grid: method bind @146-AB28EA60
        public void bind(com.codecharge.components.Component model, TITOLORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            TITOLORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("TITOLO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TITOLO").clone();
                    c.setValue(row.getTITOLO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("INDIETRO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("INDIETRO").clone();
                    c.setValue(row.getINDIETRO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).setHrefSourceValue( getRowFieldByName(((com.codecharge.components.Link) c).getHrefSource(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End TITOLO Grid: method bind

//TITOLO Directory: getRowFieldByName @146-2DC162FD
        public Object getRowFieldByName( String name, TITOLORow row ) {
            Object value = null;
            if ( "TITOLO".equals(name) ) {
                value = row.getTITOLO();
            } else if ( "INDIETRO".equals(name) ) {
                value = row.getINDIETRO();
            } else if ( "AD4BP".equals(name) ) {
                value = row.getAD4BP();
            }
            return value;
        }
//End TITOLO Directory: getRowFieldByName

//TITOLO Grid: method validate @146-104025BA
        boolean validate() {
            return true;
        }
//End TITOLO Grid: method validate

//TITOLO Grid Tail @146-FCB6E20C
    }
//End TITOLO Grid Tail

//AD4_DIRITTI_ACCESSO Grid @6-7B165BF1
    final class AD4_DIRITTI_ACCESSOClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_DIRITTI_ACCESSO Grid

//AD4_DIRITTI_ACCESSO Grid: method perform @6-B48879D3
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
//End AD4_DIRITTI_ACCESSO Grid: method perform

//AD4_DIRITTI_ACCESSO Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_DIRITTI_ACCESSO Grid: method read: head

//AD4_DIRITTI_ACCESSO Grid: method read: init @6-1CB22FA3
            if ( ! model.allowRead ) return true;
            AD4_DIRITTI_ACCESSODataObject ds = new AD4_DIRITTI_ACCESSODataObject(page);
            ds.setComponent( model );
//End AD4_DIRITTI_ACCESSO Grid: method read: init

//AD4_DIRITTI_ACCESSO Grid: set where parameters @6-D53C716C
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setUrlMODULO( page.getHttpGetParams().getParameter("MODULO") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_RUOLO( page.getHttpGetParams().getParameter("s_RUOLO") );
            ds.setUrlS_GRUPPO( page.getHttpGetParams().getParameter("s_GRUPPO") );
//End AD4_DIRITTI_ACCESSO Grid: set where parameters

//AD4_DIRITTI_ACCESSO Grid: set grid properties @6-612C1721
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
            Hashtable sortAscColumns = new Hashtable();
            Hashtable sortDescColumns = new Hashtable();
            sortAscColumns.put( "Sorter_NOMINATIVO", "NOMINATIVO" );
            sortAscColumns.put( "Sorter_SEQUENZA", "SEQUENZA" );
            sortAscColumns.put( "Sorter_MODULO", "MODULO" );
            sortAscColumns.put( "Sorter_ISTANZA", "ISTANZA" );
            sortAscColumns.put( "Sorter_DATI", "DATI" );
            if ( ! StringUtils.isEmpty( model.getSort() ) ) {
                if ( "desc".equalsIgnoreCase(model.getDir())) {
                    if ( sortDescColumns.get( model.getSort() ) != null  && "desc".equalsIgnoreCase(model.getDir())) {
                        ds.setOrderBy( (String) sortDescColumns.get( model.getSort() ) );
                    } else {
                        ds.setOrderBy( (String) sortAscColumns.get( model.getSort() ) + " DESC " );
                    }
                } else {
                    ds.setOrderBy( (String) sortAscColumns.get( model.getSort() ) );
                }
            }
//End AD4_DIRITTI_ACCESSO Grid: set grid properties

//AD4_DIRITTI_ACCESSO Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_DIRITTI_ACCESSO Grid: retrieve data

//AD4_DIRITTI_ACCESSO Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_DIRITTI_ACCESSO Grid: check errors

//AD4_DIRITTI_ACCESSO Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_DIRITTI_ACCESSO Grid: method read: tail

//AD4_DIRITTI_ACCESSO Grid: method bind @6-39F72C7B
        public void bind(com.codecharge.components.Component model, AD4_DIRITTI_ACCESSORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_DIRITTI_ACCESSORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("NOMINATIVO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOMINATIVO").clone();
                    c.setValue(row.getNOMINATIVO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("UTENTE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("SEQUENZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SEQUENZA").clone();
                    c.setValue(row.getSEQUENZA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("UTENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UTENTE").clone();
                    c.setValue(row.getUTENTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DES_MODULO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_MODULO").clone();
                    c.setValue(row.getDES_MODULO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DES_ISTANZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_ISTANZA").clone();
                    c.setValue(row.getDES_ISTANZA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DATI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATI").clone();
                    c.setValue(row.getDATI());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_DIRITTI_ACCESSO Grid: method bind

//AD4_DIRITTI_ACCESSO Directory: getRowFieldByName @6-6740C413
        public Object getRowFieldByName( String name, AD4_DIRITTI_ACCESSORow row ) {
            Object value = null;
            if ( "NOMINATIVO".equals(name) ) {
                value = row.getNOMINATIVO();
            } else if ( "SEQUENZA".equals(name) ) {
                value = row.getSEQUENZA();
            } else if ( "UTENTE".equals(name) ) {
                value = row.getUTENTE();
            } else if ( "DES_MODULO".equals(name) ) {
                value = row.getDES_MODULO();
            } else if ( "DES_ISTANZA".equals(name) ) {
                value = row.getDES_ISTANZA();
            } else if ( "DATI".equals(name) ) {
                value = row.getDATI();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "MODULO".equals(name) ) {
                value = row.getMODULO();
            } else if ( "ISTANZA".equals(name) ) {
                value = row.getISTANZA();
            }
            return value;
        }
//End AD4_DIRITTI_ACCESSO Directory: getRowFieldByName

//AD4_DIRITTI_ACCESSO Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_DIRITTI_ACCESSO Grid: method validate

//AD4_DIRITTI_ACCESSO Grid Tail @6-FCB6E20C
    }
//End AD4_DIRITTI_ACCESSO Grid Tail

//AD4UtenDiacElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4UtenDiacElenco Page: method validate

//AD4UtenDiacElencoAction Tail @1-FCB6E20C
}
//End AD4UtenDiacElencoAction Tail

