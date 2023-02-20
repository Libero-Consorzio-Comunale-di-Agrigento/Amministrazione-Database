//Ad4DizionariAslElencoAction imports @1-DFF28FA0
package restrict.Ad4DizionariAslElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariAslElencoAction imports

//Ad4DizionariAslElencoAction class @1-BA15D1D4
public class Ad4DizionariAslElencoAction extends Action {

//End Ad4DizionariAslElencoAction class

//Ad4DizionariAslElencoAction: method perform @1-A3A286D3
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariAslElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariAslElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariAslElencoAction: method perform

//Ad4DizionariAslElencoAction: call children actions @1-3235F399
        if ( page.getChild( "Ad4DizionariGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariGuidaParent",page);
            restrict.Ad4DizionariGuida.Ad4DizionariGuidaAction Ad4DizionariGuida = new restrict.Ad4DizionariGuida.Ad4DizionariGuidaAction();
            result = result != null ? result : Ad4DizionariGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            SportelliFiltroClass SportelliFiltro = new SportelliFiltroClass();
            if ( ( redirect = SportelliFiltro.perform( page.getRecord("SportelliFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            AslElencoClass AslElenco = new AslElencoClass();
            AslElenco.perform(page.getGrid("AslElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariAslElencoAction: call children actions

//SportelliFiltro Record @32-6FA3145A
    final class SportelliFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End SportelliFiltro Record

//SportelliFiltro Record: method perform @32-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End SportelliFiltro Record: method perform

//SportelliFiltro Record: children actions @32-74412493
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("SportelliFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
            reads_REGIONE(model.getListBox("s_REGIONE"));
            reads_ATTIVA(model.getListBox("s_ATTIVA"));
//End SportelliFiltro Record: children actions

//SportelliFiltro Record: method perform tail @32-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End SportelliFiltro Record: method perform tail

//DoSearch Button @35-7F387837
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

//ListBoxAction @34-E1C3B92F
        protected void reads_REGIONE(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select regione "
                        + "      ,denominazione "
                        + "  from ad4_regioni "
                        + " " );
            command.setOrder( "denominazione" );

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

//ListBoxAction @40-10789319
        protected void reads_ATTIVA(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "S;Attiva;N;Non Attiva;%;Tutte" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

void read() { //SportelliFiltro Record: method read @32-7F8AAE5A

//SportelliFiltro Record: method read head @32-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End SportelliFiltro Record: method read head

//SportelliFiltro Record: init DataSource @32-8CADCA35
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            SportelliFiltroDataObject ds = new SportelliFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_ASL( page.getHttpGetParams().getParameter("s_ASL") );
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End SportelliFiltro Record: init DataSource

//SportelliFiltro Record: check errors @32-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End SportelliFiltro Record: check errors

} //SportelliFiltro Record: method read tail @32-FCB6E20C

//SportelliFiltro Record: bind @32-D03752B0
            public void bind(com.codecharge.components.Component model, SportelliFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_REGIONE").setValue(row.getS_REGIONE());
                    model.getControl("s_ASL").setValue(row.getS_ASL());
                }
            }
//End SportelliFiltro Record: bind

//SportelliFiltro Record: getRowFieldByName @32-8607AAB0
            public Object getRowFieldByName( String name, SportelliFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_REGIONE".equals(name) ) {
                    value = row.getS_REGIONE();
                } else if ( "s_ASL".equals(name) ) {
                    value = row.getS_ASL();
                } else if ( "s_ATTIVA".equals(name) ) {
                    value = row.getS_ATTIVA();
                }
                return value;
            }
//End SportelliFiltro Record: getRowFieldByName

void InsertAction() { //SportelliFiltro Record: method insert @32-11643485

//SportelliFiltro Record: components insert actions @32-68525650
            if (! model.hasErrors()) {
            }
//End SportelliFiltro Record: components insert actions

} //SportelliFiltro Record: method insert tail @32-FCB6E20C

void UpdateAction() { //SportelliFiltro Record: method update @32-5771D0AA

//SportelliFiltro Record: components update actions @32-68525650
            if (! model.hasErrors()) {
            }
//End SportelliFiltro Record: components update actions

} //SportelliFiltro Record: method update tail @32-FCB6E20C

void DeleteAction() { //SportelliFiltro Record: method delete @32-11FC2E1E

//SportelliFiltro Record: components delete actions @32-68525650
            if (! model.hasErrors()) {
            }
//End SportelliFiltro Record: components delete actions

} //SportelliFiltro Record: method delete tail @32-FCB6E20C

//SportelliFiltro Record: method validate @32-6C9B3763
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_REGIONE = (com.codecharge.components.ListBox) model.getChild( "s_REGIONE" );
            if (! s_REGIONE.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_ASL = (com.codecharge.components.TextBox) model.getChild( "s_ASL" );
            if (! s_ASL.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_ATTIVA = (com.codecharge.components.ListBox) model.getChild( "s_ATTIVA" );
            if (! s_ATTIVA.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End SportelliFiltro Record: method validate

//SportelliFiltro Record Tail @32-FCB6E20C
    }
//End SportelliFiltro Record Tail

//AslElenco Grid @8-259E0070
    final class AslElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End AslElenco Grid

//AslElenco Grid: method perform @8-B48879D3
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
//End AslElenco Grid: method perform

//AslElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AslElenco Grid: method read: head

//AslElenco Grid: method read: init @8-B0D4F305
            if ( ! model.allowRead ) return true;
            AslElencoDataObject ds = new AslElencoDataObject(page);
            ds.setComponent( model );
//End AslElenco Grid: method read: init

//AslElenco Grid: set where parameters @8-E33BDD9F
            ds.setUrlS_REGIONE( page.getHttpGetParams().getParameter("s_REGIONE") );
            ds.setUrlS_ASL( page.getHttpGetParams().getParameter("s_ASL") );
            ds.setUrlS_ATTIVA( page.getHttpGetParams().getParameter("s_ATTIVA") );
//End AslElenco Grid: set where parameters

//AslElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AslElenco Grid: set grid properties

//AslElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AslElenco Grid: retrieve data

//AslElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AslElenco Grid: check errors

//AslElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End AslElenco Grid: method read: tail

//AslElenco Grid: method bind @8-B071A38E
        public void bind(com.codecharge.components.Component model, AslElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AslElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("REGIONE_ASL");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("REGIONE_ASL").clone();
                    c.setValue(row.getREGIONE_ASL());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CODICE_ASL");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CODICE_ASL").clone();
                    c.setValue(row.getCODICE_ASL());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("REGIONE_ASL").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("REGIONE_ASL").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("CODICE_ASL").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("CODICE_ASL").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("REGIONE_DENOMINAZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("REGIONE_DENOMINAZIONE").clone();
                    c.setValue(row.getREGIONE_DENOMINAZIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ATTIVA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ATTIVA").clone();
                    c.setValue(row.getATTIVA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("UTENTE_AGGIORNAMENTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UTENTE_AGGIORNAMENTO").clone();
                    c.setValue(row.getUTENTE_AGGIORNAMENTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DATA_AGGIORNAMENTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATA_AGGIORNAMENTO").clone();
                    c.setValue(row.getDATA_AGGIORNAMENTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Aggiungi");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Aggiungi").clone();
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AslElenco Grid: method bind

//AslElenco Directory: getRowFieldByName @8-DD0A62E7
        public Object getRowFieldByName( String name, AslElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "REGIONE_ASL".equals(name) ) {
                value = row.getREGIONE_ASL();
            } else if ( "CODICE_ASL".equals(name) ) {
                value = row.getCODICE_ASL();
            } else if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "REGIONE_DENOMINAZIONE".equals(name) ) {
                value = row.getREGIONE_DENOMINAZIONE();
            } else if ( "ATTIVA".equals(name) ) {
                value = row.getATTIVA();
            } else if ( "UTENTE_AGGIORNAMENTO".equals(name) ) {
                value = row.getUTENTE_AGGIORNAMENTO();
            } else if ( "DATA_AGGIORNAMENTO".equals(name) ) {
                value = row.getDATA_AGGIORNAMENTO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            }
            return value;
        }
//End AslElenco Directory: getRowFieldByName

//AslElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End AslElenco Grid: method validate

//AslElenco Grid Tail @8-FCB6E20C
    }
//End AslElenco Grid Tail

//Ad4DizionariAslElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariAslElenco Page: method validate

//Ad4DizionariAslElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariAslElencoAction Tail

