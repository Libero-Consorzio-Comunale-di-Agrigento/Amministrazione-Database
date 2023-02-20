//AD4RuoliRicercaAction imports @1-518DD744
package ad4web.AD4RuoliRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4RuoliRicercaAction imports

//AD4RuoliRicercaAction class @1-6E4F9A05
public class AD4RuoliRicercaAction extends Action {

//End AD4RuoliRicercaAction class

//AD4RuoliRicercaAction: method perform @1-C3E2D31B
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4RuoliRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4RuoliRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4RuoliRicercaAction: method perform

//AD4RuoliRicercaAction: call children actions @1-36EAEE4E
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
            AD4Ruoli_VSearchClass AD4Ruoli_VSearch = new AD4Ruoli_VSearchClass();
            if ( ( redirect = AD4Ruoli_VSearch.perform( page.getRecord("AD4Ruoli_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4RuoliVClass AD4RuoliV = new AD4RuoliVClass();
            AD4RuoliV.perform(page.getGrid("AD4RuoliV"));
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
//End AD4RuoliRicercaAction: call children actions

//AD4Ruoli_VSearch Record @6-DC7642AB
    final class AD4Ruoli_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Ruoli_VSearch Record

//AD4Ruoli_VSearch Record: method perform @6-1CB004DD
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Ruoli_VSearch Record: method perform

//AD4Ruoli_VSearch Record: children actions @6-B218471E
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Ruoli_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4Ruoli_VSearch Record: children actions

//AD4Ruoli_VSearch Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Ruoli_VSearch Record: method perform tail

//DoSearch Button @7-F4B652CB
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4RuoliRicerca" + Names.ACTION_SUFFIX + "?"  + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //AD4Ruoli_VSearch Record: method read @6-7F8AAE5A

//AD4Ruoli_VSearch Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Ruoli_VSearch Record: method read head

//AD4Ruoli_VSearch Record: init DataSource @6-BBC1747A
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Ruoli_VSearchDataObject ds = new AD4Ruoli_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Ruoli_VSearch Record: init DataSource

//AD4Ruoli_VSearch Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Ruoli_VSearch Record: check errors

} //AD4Ruoli_VSearch Record: method read tail @6-FCB6E20C

//AD4Ruoli_VSearch Record: bind @6-61D8B2D7
            public void bind(com.codecharge.components.Component model, AD4Ruoli_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                }
            }
//End AD4Ruoli_VSearch Record: bind

//AD4Ruoli_VSearch Record: getRowFieldByName @6-04E53D99
            public Object getRowFieldByName( String name, AD4Ruoli_VSearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_DESCRIZIONE".equals(name) ) {
                    value = row.getS_DESCRIZIONE();
                } else if ( "AD4_RUOLI_Insert".equals(name) ) {
                    value = row.getAD4_RUOLI_Insert();
                }
                return value;
            }
//End AD4Ruoli_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Ruoli_VSearch Record: method insert @6-11643485

//AD4Ruoli_VSearch Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Ruoli_VSearch Record: components insert actions

} //AD4Ruoli_VSearch Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4Ruoli_VSearch Record: method update @6-5771D0AA

//AD4Ruoli_VSearch Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Ruoli_VSearch Record: components update actions

} //AD4Ruoli_VSearch Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4Ruoli_VSearch Record: method delete @6-11FC2E1E

//AD4Ruoli_VSearch Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Ruoli_VSearch Record: components delete actions

} //AD4Ruoli_VSearch Record: method delete tail @6-FCB6E20C

//AD4Ruoli_VSearch Record: method validate @6-58D16649
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "s_DESCRIZIONE" );
            if (! s_DESCRIZIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Ruoli_VSearch Record: method validate

//AD4Ruoli_VSearch Record Tail @6-FCB6E20C
    }
//End AD4Ruoli_VSearch Record Tail

//AD4RuoliV Grid @142-057D2A67
    final class AD4RuoliVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4RuoliV Grid

//AD4RuoliV Grid: method perform @142-B48879D3
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
//End AD4RuoliV Grid: method perform

//AD4RuoliV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4RuoliV Grid: method read: head

//AD4RuoliV Grid: method read: init @142-83B67E5F
            if ( ! model.allowRead ) return true;
            AD4RuoliVDataObject ds = new AD4RuoliVDataObject(page);
            ds.setComponent( model );
//End AD4RuoliV Grid: method read: init

//AD4RuoliV Grid: set where parameters @142-0440F0C8
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
//End AD4RuoliV Grid: set where parameters

//AD4RuoliV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4RuoliV Grid: set grid properties

//AD4RuoliV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4RuoliV Grid: retrieve data

//AD4RuoliV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4RuoliV Grid: check errors

//AD4RuoliV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4RuoliV Grid: method read: tail

//AD4RuoliV Grid: method bind @142-E2340061
        public void bind(com.codecharge.components.Component model, AD4RuoliVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4RuoliVRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("RUOLO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("RUOLO").clone();
                    c.setValue(row.getRUOLO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("RUOLO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("RUOLO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("RUOLO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("RUOLO").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("PROGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROGETTO").clone();
                    c.setValue(row.getPROGETTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("MODULO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("MODULO").clone();
                    c.setValue(row.getMODULO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("PROFILO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROFILO").clone();
                    c.setValue(row.getPROFILO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("STATO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("STATO").clone();
                    c.setValue(row.getSTATO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("GRUPPO_LAVORO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("GRUPPO_LAVORO").clone();
                    c.setValue(row.getGRUPPO_LAVORO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("GRUPPO_SO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("GRUPPO_SO").clone();
                    c.setValue(row.getGRUPPO_SO());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4RuoliV Grid: method bind

//AD4RuoliV Directory: getRowFieldByName @142-FF43F261
        public Object getRowFieldByName( String name, AD4RuoliVRow row ) {
            Object value = null;
            if ( "RUOLO".equals(name) ) {
                value = row.getRUOLO();
            } else if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
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
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "SOGGETTO".equals(name) ) {
                value = row.getSOGGETTO();
            }
            return value;
        }
//End AD4RuoliV Directory: getRowFieldByName

//AD4RuoliV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4RuoliV Grid: method validate

//AD4RuoliV Grid Tail @142-FCB6E20C
    }
//End AD4RuoliV Grid Tail

//AD4RuoliRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4RuoliRicerca Page: method validate

//AD4RuoliRicercaAction Tail @1-FCB6E20C
}
//End AD4RuoliRicercaAction Tail

