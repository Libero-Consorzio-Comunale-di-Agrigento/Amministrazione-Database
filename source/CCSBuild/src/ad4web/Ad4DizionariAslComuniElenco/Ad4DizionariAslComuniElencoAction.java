//Ad4DizionariAslComuniElencoAction imports @1-DBA50EDD
package ad4web.Ad4DizionariAslComuniElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariAslComuniElencoAction imports

//Ad4DizionariAslComuniElencoAction class @1-C8CA3D44
public class Ad4DizionariAslComuniElencoAction extends Action {

//End Ad4DizionariAslComuniElencoAction class

//Ad4DizionariAslComuniElencoAction: method perform @1-D809CA55
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariAslComuniElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariAslComuniElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariAslComuniElencoAction: method perform

//Ad4DizionariAslComuniElencoAction: call children actions @1-8DE7DCCE
        if (result == null) {
            ComuniFiltroClass ComuniFiltro = new ComuniFiltroClass();
            if ( ( redirect = ComuniFiltro.perform( page.getRecord("ComuniFiltro")) ) != null ) result = redirect;
        }
        if (result == null) {
            TitoloClass Titolo = new TitoloClass();
            Titolo.perform(page.getGrid("Titolo"));
        }
        if (result == null) {
            ComuniElencoClass ComuniElenco = new ComuniElencoClass();
            ComuniElenco.perform(page.getGrid("ComuniElenco"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariAslComuniElencoAction: call children actions

//ComuniFiltro Record @3-04B2C5B4
    final class ComuniFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End ComuniFiltro Record

//ComuniFiltro Record: method perform @3-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End ComuniFiltro Record: method perform

//ComuniFiltro Record: children actions @3-D7CB1570
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("ComuniFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End ComuniFiltro Record: children actions

//ComuniFiltro Record: method perform tail @3-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End ComuniFiltro Record: method perform tail

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

void read() { //ComuniFiltro Record: method read @3-7F8AAE5A

//ComuniFiltro Record: method read head @3-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End ComuniFiltro Record: method read head

//ComuniFiltro Record: init DataSource @3-913D8F4F
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            ComuniFiltroDataObject ds = new ComuniFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_COMUNE( page.getHttpGetParams().getParameter("s_COMUNE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End ComuniFiltro Record: init DataSource

//ComuniFiltro Record: check errors @3-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End ComuniFiltro Record: check errors

} //ComuniFiltro Record: method read tail @3-FCB6E20C

//ComuniFiltro Record: bind @3-51E356F1
            public void bind(com.codecharge.components.Component model, ComuniFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_COMUNE").setValue(row.getS_COMUNE());
                }
            }
//End ComuniFiltro Record: bind

//ComuniFiltro Record: getRowFieldByName @3-01FD0831
            public Object getRowFieldByName( String name, ComuniFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_COMUNE".equals(name) ) {
                    value = row.getS_COMUNE();
                }
                return value;
            }
//End ComuniFiltro Record: getRowFieldByName

void InsertAction() { //ComuniFiltro Record: method insert @3-11643485

//ComuniFiltro Record: components insert actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ComuniFiltro Record: components insert actions

} //ComuniFiltro Record: method insert tail @3-FCB6E20C

void UpdateAction() { //ComuniFiltro Record: method update @3-5771D0AA

//ComuniFiltro Record: components update actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ComuniFiltro Record: components update actions

} //ComuniFiltro Record: method update tail @3-FCB6E20C

void DeleteAction() { //ComuniFiltro Record: method delete @3-11FC2E1E

//ComuniFiltro Record: components delete actions @3-68525650
            if (! model.hasErrors()) {
            }
//End ComuniFiltro Record: components delete actions

} //ComuniFiltro Record: method delete tail @3-FCB6E20C

//ComuniFiltro Record: method validate @3-6288FC95
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_COMUNE = (com.codecharge.components.TextBox) model.getChild( "s_COMUNE" );
            if (! s_COMUNE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End ComuniFiltro Record: method validate

//ComuniFiltro Record Tail @3-FCB6E20C
    }
//End ComuniFiltro Record Tail

//Titolo Grid @40-AAABC7C5
    final class TitoloClass {
        com.codecharge.components.Grid model;
        Event e;
//End Titolo Grid

//Titolo Grid: method perform @40-B48879D3
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
//End Titolo Grid: method perform

//Titolo Grid: method read: head @40-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Titolo Grid: method read: head

//Titolo Grid: method read: init @40-60673B77
            if ( ! model.allowRead ) return true;
            TitoloDataObject ds = new TitoloDataObject(page);
            ds.setComponent( model );
//End Titolo Grid: method read: init

//Titolo Grid: set where parameters @40-33695DB2
            ds.setUrlREGIONE_ASL( page.getHttpGetParams().getParameter("REGIONE_ASL") );
            ds.setUrlCODICE_ASL( page.getHttpGetParams().getParameter("CODICE_ASL") );
//End Titolo Grid: set where parameters

//Titolo Grid: set grid properties @40-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Titolo Grid: set grid properties

//Titolo Grid: retrieve data @40-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Titolo Grid: retrieve data

//Titolo Grid: check errors @40-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Titolo Grid: check errors

//Titolo Grid: method read: tail @40-F575E732
            return ( ! isErrors );
        }
//End Titolo Grid: method read: tail

//Titolo Grid: method bind @40-50CAF00B
        public void bind(com.codecharge.components.Component model, TitoloRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            TitoloRow row = null;
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
//End Titolo Grid: method bind

//Titolo Directory: getRowFieldByName @40-6371508C
        public Object getRowFieldByName( String name, TitoloRow row ) {
            Object value = null;
            if ( "TITOLO".equals(name) ) {
                value = row.getTITOLO();
            } else if ( "Aggiungi".equals(name) ) {
                value = row.getAggiungi();
            }
            return value;
        }
//End Titolo Directory: getRowFieldByName

//Titolo Grid: method validate @40-104025BA
        boolean validate() {
            return true;
        }
//End Titolo Grid: method validate

//Titolo Grid Tail @40-FCB6E20C
    }
//End Titolo Grid Tail

//ComuniElenco Grid @8-5FDC2085
    final class ComuniElencoClass {
        com.codecharge.components.Grid model;
        Event e;
//End ComuniElenco Grid

//ComuniElenco Grid: method perform @8-B48879D3
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
//End ComuniElenco Grid: method perform

//ComuniElenco Grid: method read: head @8-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End ComuniElenco Grid: method read: head

//ComuniElenco Grid: method read: init @8-1AAEC237
            if ( ! model.allowRead ) return true;
            ComuniElencoDataObject ds = new ComuniElencoDataObject(page);
            ds.setComponent( model );
//End ComuniElenco Grid: method read: init

//ComuniElenco Grid: set where parameters @8-30FCB093
            ds.setUrlS_COMUNE( page.getHttpGetParams().getParameter("s_COMUNE") );
            ds.setUrlREGIONE_ASL( page.getHttpGetParams().getParameter("REGIONE_ASL") );
            ds.setUrlCODICE_ASL( page.getHttpGetParams().getParameter("CODICE_ASL") );
//End ComuniElenco Grid: set where parameters

//ComuniElenco Grid: set grid properties @8-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End ComuniElenco Grid: set grid properties

//ComuniElenco Grid: retrieve data @8-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End ComuniElenco Grid: retrieve data

//ComuniElenco Grid: check errors @8-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End ComuniElenco Grid: check errors

//ComuniElenco Grid: method read: tail @8-F575E732
            return ( ! isErrors );
        }
//End ComuniElenco Grid: method read: tail

//ComuniElenco Grid: method bind @8-C66E59A7
        public void bind(com.codecharge.components.Component model, ComuniElencoRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ComuniElencoRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("COMUNE_DESC");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COMUNE_DESC").clone();
                    c.setValue(row.getCOMUNE_DESC());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("COMUNE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("COMUNE").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("PROVINCIA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("PROVINCIA").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("REGIONE_ASL").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("REGIONE_ASL").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("CODICE_ASL").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("CODICE_ASL").getSourceName(), row ));

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

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End ComuniElenco Grid: method bind

//ComuniElenco Directory: getRowFieldByName @8-4ACD74F1
        public Object getRowFieldByName( String name, ComuniElencoRow row ) {
            Object value = null;
            if ( "COMUNE_DESC".equals(name) ) {
                value = row.getCOMUNE_DESC();
            } else if ( "UTENTE_AGGIORNAMENTO".equals(name) ) {
                value = row.getUTENTE_AGGIORNAMENTO();
            } else if ( "DATA_AGGIORNAMENTO".equals(name) ) {
                value = row.getDATA_AGGIORNAMENTO();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "COMUNE".equals(name) ) {
                value = row.getCOMUNE();
            } else if ( "PROVINCIA".equals(name) ) {
                value = row.getPROVINCIA();
            } else if ( "REGIONE_ASL".equals(name) ) {
                value = row.getREGIONE_ASL();
            } else if ( "CODICE_ASL".equals(name) ) {
                value = row.getCODICE_ASL();
            }
            return value;
        }
//End ComuniElenco Directory: getRowFieldByName

//ComuniElenco Grid: method validate @8-104025BA
        boolean validate() {
            return true;
        }
//End ComuniElenco Grid: method validate

//ComuniElenco Grid Tail @8-FCB6E20C
    }
//End ComuniElenco Grid Tail

//Ad4DizionariAslComuniElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariAslComuniElenco Page: method validate

//Ad4DizionariAslComuniElencoAction Tail @1-FCB6E20C
}
//End Ad4DizionariAslComuniElencoAction Tail

