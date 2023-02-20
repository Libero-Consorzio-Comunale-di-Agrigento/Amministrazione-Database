//Ad4DizionariSportelliElencoAction imports @1-A8CC4573
package restrict.Ad4DizionariSportelliElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariSportelliElencoAction imports

//Ad4DizionariSportelliElencoAction class @1-D9BA2353
public class Ad4DizionariSportelliElencoAction extends Action {

//End Ad4DizionariSportelliElencoAction class

//Ad4DizionariSportelliElencoAction: method perform @1-F7DFFA18
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariSportelliElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariSportelliElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariSportelliElencoAction: method perform

//Ad4DizionariSportelliElencoAction: call children actions @1-B753C8F3
        if ( page.getChild( "Ad4DizionariBancheGuida" ).isVisible() ) {
            page.getRequest().setAttribute("Ad4DizionariBancheGuidaParent",page);
            restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaAction Ad4DizionariBancheGuida = new restrict.Ad4DizionariBancheGuida.Ad4DizionariBancheGuidaAction();
            result = result != null ? result : Ad4DizionariBancheGuida.perform( req, resp,  context );
            page.setCookies();
        }
        if (result == null) {
            SportelliFiltroClass SportelliFiltro = new SportelliFiltroClass();
            if ( ( redirect = SportelliFiltro.perform( page.getRecord("SportelliFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            SportelliElencoClass SportelliElenco = new SportelliElencoClass();
            SportelliElenco.perform(page.getGrid("SportelliElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariSportelliElencoAction: call children actions

//SportelliFiltro Record @3-6FA3145A
    final class SportelliFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End SportelliFiltro Record

//SportelliFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End SportelliFiltro Record: method perform

//SportelliFiltro Record: children actions @3-6AD3A12E
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
            reads_BANCA(model.getListBox("s_BANCA"));
//End SportelliFiltro Record: children actions

//SportelliFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End SportelliFiltro Record: method perform tail

//DoSearch Button @25-7F387837
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

//ListBoxAction @26-C5F9BF38
        protected void reads_BANCA(com.codecharge.components.ListBox model) {
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            DataObjectEvent e = new DataObjectEvent();
            e.setSource(model);
            e.setCommand(command);
            e.setConnection(ds);
            command.setOptimizeSQL(false);

            command.setSql( "select ABI BANCA "
                        + "      ,denominazione "
                        + "  from ad4_BANCHE "
                        + " " );
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

void read() { //SportelliFiltro Record: method read @3-7F8AAE5A

//SportelliFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End SportelliFiltro Record: method read head

//SportelliFiltro Record: init DataSource @3-D1C0E880
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            SportelliFiltroDataObject ds = new SportelliFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_SPORTELLO( page.getHttpGetParams().getParameter("s_SPORTELLO") );
            ds.setUrlS_BANCA( page.getHttpGetParams().getParameter("s_BANCA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End SportelliFiltro Record: init DataSource

//SportelliFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End SportelliFiltro Record: check errors

} //SportelliFiltro Record: method read tail @3-FCB6E20C

//SportelliFiltro Record: bind @3-EA3D68B2
            public void bind(com.codecharge.components.Component model, SportelliFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_BANCA").setValue(row.getS_BANCA());
                    model.getControl("s_SPORTELLO").setValue(row.getS_SPORTELLO());
                }
            }
//End SportelliFiltro Record: bind

//SportelliFiltro Record: getRowFieldByName @3-9543742E
            public Object getRowFieldByName( String name, SportelliFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_BANCA".equals(name) ) {
                    value = row.getS_BANCA();
                } else if ( "s_SPORTELLO".equals(name) ) {
                    value = row.getS_SPORTELLO();
                }
                return value;
            }
//End SportelliFiltro Record: getRowFieldByName

void InsertAction() { //SportelliFiltro Record: method insert @3-11643485

//SportelliFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End SportelliFiltro Record: components insert actions

} //SportelliFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //SportelliFiltro Record: method update @3-5771D0AA

//SportelliFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End SportelliFiltro Record: components update actions

} //SportelliFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //SportelliFiltro Record: method delete @3-11FC2E1E

//SportelliFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End SportelliFiltro Record: components delete actions

} //SportelliFiltro Record: method delete tail @3-FCB6E20C

//SportelliFiltro Record: method validate @3-8927812B
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_BANCA = (com.codecharge.components.ListBox) model.getChild( "s_BANCA" );
            if (! s_BANCA.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_SPORTELLO = (com.codecharge.components.TextBox) model.getChild( "s_SPORTELLO" );
            if (! s_SPORTELLO.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End SportelliFiltro Record: method validate

//SportelliFiltro Record Tail @3-FCB6E20C
    }
//End SportelliFiltro Record Tail

//SportelliElenco Grid @8-1DBF03BC
    final class SportelliElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End SportelliElenco Grid

//SportelliElenco Grid: method perform @8-B48879D3
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
//End SportelliElenco Grid: method perform

//SportelliElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End SportelliElenco Grid: method read: head

//SportelliElenco Grid: method read: init @8-4F60CFBF
            if ( ! model.allowRead ) return true;
            SportelliElencoDataObject ds = new SportelliElencoDataObject(page);
            ds.setComponent( model );
//End SportelliElenco Grid: method read: init

//SportelliElenco Grid: set where parameters @8-B5F615C9
            ds.setUrlS_BANCA( page.getHttpGetParams().getParameter("s_BANCA") );
            ds.setUrlS_SPORTELLO( page.getHttpGetParams().getParameter("s_SPORTELLO") );
//End SportelliElenco Grid: set where parameters

//SportelliElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End SportelliElenco Grid: set grid properties

//SportelliElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End SportelliElenco Grid: retrieve data

//SportelliElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End SportelliElenco Grid: check errors

//SportelliElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End SportelliElenco Grid: method read: tail

//SportelliElenco Grid: method bind @8-81931C0A
        public void bind(com.codecharge.components.Component model, SportelliElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            SportelliElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("CAB");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CAB").clone();
                    c.setValue(row.getCAB());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("SPORTELLO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("SPORTELLO").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("BANCA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("BANCA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("ABI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ABI").clone();
                    c.setValue(row.getABI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CIN_CAB");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CIN_CAB").clone();
                    c.setValue(row.getCIN_CAB());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("INDIRIZZO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("INDIRIZZO").clone();
                    c.setValue(row.getINDIRIZZO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("LOCALITA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("LOCALITA").clone();
                    c.setValue(row.getLOCALITA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("COMUNE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COMUNE").clone();
                    c.setValue(row.getCOMUNE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PROVINCIA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROVINCIA").clone();
                    c.setValue(row.getPROVINCIA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CAP");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CAP").clone();
                    c.setValue(row.getCAP());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DIPENDENZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DIPENDENZA").clone();
                    c.setValue(row.getDIPENDENZA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("BIC");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("BIC").clone();
                    c.setValue(row.getBIC());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("BANCA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("BANCA").clone();
                    c.setValue(row.getBANCA());
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
//End SportelliElenco Grid: method bind

//SportelliElenco Directory: getRowFieldByName @8-D92ED90D
        public Object getRowFieldByName( String name, SportelliElencoRow row ) {
            Object value = null;
            if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            } else if ( "CAB".equals(name) ) {
                value = row.getCAB();
            } else if ( "ABI".equals(name) ) {
                value = row.getABI();
            } else if ( "CIN_CAB".equals(name) ) {
                value = row.getCIN_CAB();
            } else if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "INDIRIZZO".equals(name) ) {
                value = row.getINDIRIZZO();
            } else if ( "LOCALITA".equals(name) ) {
                value = row.getLOCALITA();
            } else if ( "COMUNE".equals(name) ) {
                value = row.getCOMUNE();
            } else if ( "PROVINCIA".equals(name) ) {
                value = row.getPROVINCIA();
            } else if ( "CAP".equals(name) ) {
                value = row.getCAP();
            } else if ( "DIPENDENZA".equals(name) ) {
                value = row.getDIPENDENZA();
            } else if ( "BIC".equals(name) ) {
                value = row.getBIC();
            } else if ( "BANCA".equals(name) ) {
                value = row.getBANCA();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "SPORTELLO".equals(name) ) {
                value = row.getSPORTELLO();
            }
            return value;
        }
//End SportelliElenco Directory: getRowFieldByName

//SportelliElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End SportelliElenco Grid: method validate

//SportelliElenco Grid Tail @8-FCB6E20C
    }
//End SportelliElenco Grid Tail

//Ad4DizionariSportelliElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariSportelliElenco Page: method validate

//Ad4DizionariSportelliElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariSportelliElencoAction Tail
