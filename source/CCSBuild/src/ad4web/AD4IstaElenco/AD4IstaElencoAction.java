//AD4IstaElencoAction imports @1-1F410A54
package ad4web.AD4IstaElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4IstaElencoAction imports

//AD4IstaElencoAction class @1-E88D91B5
public class AD4IstaElencoAction extends Action {

//End AD4IstaElencoAction class

//AD4IstaElencoAction: method perform @1-9412E0DF
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4IstaElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4IstaElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4IstaElencoAction: method perform

//AD4IstaElencoAction: call children actions @1-55EC2BC9
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
            AD4Istanze_VSearchClass AD4Istanze_VSearch = new AD4Istanze_VSearchClass();
            if ( ( redirect = AD4Istanze_VSearch.perform( page.getRecord("AD4Istanze_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            PROGETTIClass PROGETTI = new PROGETTIClass();
            PROGETTI.perform(page.getGrid("PROGETTI"));
        }
        if (result == null) {
            AD4_ISTANZEClass AD4_ISTANZE = new AD4_ISTANZEClass();
            AD4_ISTANZE.perform(page.getGrid("AD4_ISTANZE"));
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
//End AD4IstaElencoAction: call children actions

//AD4Istanze_VSearch Record @175-F42D9135
    final class AD4Istanze_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Istanze_VSearch Record

//AD4Istanze_VSearch Record: method perform @175-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Istanze_VSearch Record: method perform

//AD4Istanze_VSearch Record: children actions @175-AC43D6B8
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Istanze_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4Istanze_VSearch Record: children actions

//AD4Istanze_VSearch Record: method perform tail @175-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Istanze_VSearch Record: method perform tail

//DoSearch Button @178-91886726
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4IstaElenco" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //AD4Istanze_VSearch Record: method read @175-7F8AAE5A

//AD4Istanze_VSearch Record: method read head @175-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Istanze_VSearch Record: method read head

//AD4Istanze_VSearch Record: init DataSource @175-D43571A5
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Istanze_VSearchDataObject ds = new AD4Istanze_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Istanze_VSearch Record: init DataSource

//AD4Istanze_VSearch Record: check errors @175-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Istanze_VSearch Record: check errors

} //AD4Istanze_VSearch Record: method read tail @175-FCB6E20C

//AD4Istanze_VSearch Record: bind @175-B4B9CC52
            public void bind(com.codecharge.components.Component model, AD4Istanze_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                }
            }
//End AD4Istanze_VSearch Record: bind

//AD4Istanze_VSearch Record: getRowFieldByName @175-A50DCBFD
            public Object getRowFieldByName( String name, AD4Istanze_VSearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_DESCRIZIONE".equals(name) ) {
                    value = row.getS_DESCRIZIONE();
                }
                return value;
            }
//End AD4Istanze_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Istanze_VSearch Record: method insert @175-11643485

//AD4Istanze_VSearch Record: components insert actions @175-68525650
            if (! model.hasErrors()) {
            }
//End AD4Istanze_VSearch Record: components insert actions

} //AD4Istanze_VSearch Record: method insert tail @175-FCB6E20C

void UpdateAction() { //AD4Istanze_VSearch Record: method update @175-5771D0AA

//AD4Istanze_VSearch Record: components update actions @175-68525650
            if (! model.hasErrors()) {
            }
//End AD4Istanze_VSearch Record: components update actions

} //AD4Istanze_VSearch Record: method update tail @175-FCB6E20C

void DeleteAction() { //AD4Istanze_VSearch Record: method delete @175-11FC2E1E

//AD4Istanze_VSearch Record: components delete actions @175-68525650
            if (! model.hasErrors()) {
            }
//End AD4Istanze_VSearch Record: components delete actions

} //AD4Istanze_VSearch Record: method delete tail @175-FCB6E20C

//AD4Istanze_VSearch Record: method validate @175-58D16649
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "s_DESCRIZIONE" );
            if (! s_DESCRIZIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Istanze_VSearch Record: method validate

//AD4Istanze_VSearch Record Tail @175-FCB6E20C
    }
//End AD4Istanze_VSearch Record Tail

//PROGETTI Grid @135-835FB9FB
    final class PROGETTIClass {
        com.codecharge.components.Grid model;
        Event e;
//End PROGETTI Grid

//PROGETTI Grid: method perform @135-B48879D3
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
//End PROGETTI Grid: method perform

//PROGETTI Grid: method read: head @135-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End PROGETTI Grid: method read: head

//PROGETTI Grid: method read: init @135-F0D80E6B
            if ( ! model.allowRead ) return true;
            PROGETTIDataObject ds = new PROGETTIDataObject(page);
            ds.setComponent( model );
//End PROGETTI Grid: method read: init

//PROGETTI Grid: set where parameters @135-BCB1A40F
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
//End PROGETTI Grid: set where parameters

//PROGETTI Grid: set grid properties @135-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End PROGETTI Grid: set grid properties

//PROGETTI Grid: retrieve data @135-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End PROGETTI Grid: retrieve data

//PROGETTI Grid: check errors @135-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End PROGETTI Grid: check errors

//PROGETTI Grid: method read: tail @135-F575E732
            return ( ! isErrors );
        }
//End PROGETTI Grid: method read: tail

//PROGETTI Grid: method bind @135-2591D325
        public void bind(com.codecharge.components.Component model, PROGETTIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            PROGETTIRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("DESC_PROGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESC_PROGETTO").clone();
                    c.setValue(row.getDESC_PROGETTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Nuovo");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Nuovo").clone();
                    c.setValue(row.getNuovo());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End PROGETTI Grid: method bind

//PROGETTI Directory: getRowFieldByName @135-846577E2
        public Object getRowFieldByName( String name, PROGETTIRow row ) {
            Object value = null;
            if ( "DESC_PROGETTO".equals(name) ) {
                value = row.getDESC_PROGETTO();
            } else if ( "Nuovo".equals(name) ) {
                value = row.getNuovo();
            } else if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
            }
            return value;
        }
//End PROGETTI Directory: getRowFieldByName

//PROGETTI Grid: method validate @135-104025BA
        boolean validate() {
            return true;
        }
//End PROGETTI Grid: method validate

//PROGETTI Grid Tail @135-FCB6E20C
    }
//End PROGETTI Grid Tail

//AD4_ISTANZE Grid @6-9944F2F3
    final class AD4_ISTANZEClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_ISTANZE Grid

//AD4_ISTANZE Grid: method perform @6-B48879D3
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
//End AD4_ISTANZE Grid: method perform

//AD4_ISTANZE Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_ISTANZE Grid: method read: head

//AD4_ISTANZE Grid: method read: init @6-1AF812C2
            if ( ! model.allowRead ) return true;
            AD4_ISTANZEDataObject ds = new AD4_ISTANZEDataObject(page);
            ds.setComponent( model );
//End AD4_ISTANZE Grid: method read: init

//AD4_ISTANZE Grid: set where parameters @6-FAD00C25
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
//End AD4_ISTANZE Grid: set where parameters

//AD4_ISTANZE Grid: set grid properties @6-16A28C6F
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
            Hashtable sortAscColumns = new Hashtable();
            Hashtable sortDescColumns = new Hashtable();
            sortAscColumns.put( "Sorter_ISTANZA", "DESCRIZIONE" );
            sortAscColumns.put( "Sorter_DESCRIZIONE", "DESCRIZIONE" );
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
//End AD4_ISTANZE Grid: set grid properties

//AD4_ISTANZE Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_ISTANZE Grid: retrieve data

//AD4_ISTANZE Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_ISTANZE Grid: check errors

//AD4_ISTANZE Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_ISTANZE Grid: method read: tail

//AD4_ISTANZE Grid: method bind @6-407B8C8C
        public void bind(com.codecharge.components.Component model, AD4_ISTANZERow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_ISTANZERow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("DESCRIZIONE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DESCRIZIONE").clone();
                    c.setValue(row.getDESCRIZIONE());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ISTANZA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DATI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATI").clone();
                    c.setValue(row.getDATI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CaratteristicheAccesso");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CaratteristicheAccesso").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").getSourceName(), row ));
                ((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("Abilitazioni");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Abilitazioni").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("Registro");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Registro").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("USRORCL").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("USRORCL").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_ISTANZE Grid: method bind

//AD4_ISTANZE Directory: getRowFieldByName @6-0997AB49
        public Object getRowFieldByName( String name, AD4_ISTANZERow row ) {
            Object value = null;
            if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "DATI".equals(name) ) {
                value = row.getDATI();
            } else if ( "CaratteristicheAccesso".equals(name) ) {
                value = row.getCaratteristicheAccesso();
            } else if ( "Abilitazioni".equals(name) ) {
                value = row.getAbilitazioni();
            } else if ( "Registro".equals(name) ) {
                value = row.getRegistro();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "ISTANZA".equals(name) ) {
                value = row.getISTANZA();
            } else if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
            } else if ( "USRORCL".equals(name) ) {
                value = row.getUSRORCL();
            } else if ( "USER_ORACLE".equals(name) ) {
                value = row.getUSER_ORACLE();
            }
            return value;
        }
//End AD4_ISTANZE Directory: getRowFieldByName

//AD4_ISTANZE Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_ISTANZE Grid: method validate

//AD4_ISTANZE Grid Tail @6-FCB6E20C
    }
//End AD4_ISTANZE Grid Tail

//AD4IstaElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4IstaElenco Page: method validate

//AD4IstaElencoAction Tail @1-FCB6E20C
}
//End AD4IstaElencoAction Tail

