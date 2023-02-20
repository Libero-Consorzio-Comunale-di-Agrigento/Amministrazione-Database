//AD4RegistroTreeAction imports @1-8B926AAB
package ad4web.AD4RegistroTree;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4RegistroTreeAction imports

//AD4RegistroTreeAction class @1-230F82A5
public class AD4RegistroTreeAction extends Action {

//End AD4RegistroTreeAction class

//AD4RegistroTreeAction: method perform @1-DC88912D
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4RegistroTreeModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4RegistroTreeModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4RegistroTreeAction: method perform

//AD4RegistroTreeAction: call children actions @1-FD6E6725
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
            AD4Registro_VSearchClass AD4Registro_VSearch = new AD4Registro_VSearchClass();
            if ( ( redirect = AD4Registro_VSearch.perform( page.getRecord("AD4Registro_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            TitoloClass Titolo = new TitoloClass();
            Titolo.perform(page.getGrid("Titolo"));
        }
        if (result == null) {
            AD4_REGISTROClass AD4_REGISTRO = new AD4_REGISTROClass();
            AD4_REGISTRO.perform(page.getGrid("AD4_REGISTRO"));
        }
        if ( page.getChild( "AD4RegistroElenco" ).isVisible() ) {
            page.getRequest().setAttribute("AD4RegistroElencoParent",page);
            ad4web.AD4RegistroElenco.AD4RegistroElencoAction AD4RegistroElenco = new ad4web.AD4RegistroElenco.AD4RegistroElencoAction();
            result = result != null ? result : AD4RegistroElenco.perform( req, resp,  context );
            page.setCookies();
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
//End AD4RegistroTreeAction: call children actions

//AD4Registro_VSearch Record @10-A0660851
    final class AD4Registro_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Registro_VSearch Record

//AD4Registro_VSearch Record: method perform @10-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Registro_VSearch Record: method perform

//AD4Registro_VSearch Record: children actions @10-DE8270AF
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Registro_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4Registro_VSearch Record: children actions

//AD4Registro_VSearch Record: method perform tail @10-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Registro_VSearch Record: method perform tail

//DoSearch Button @14-ED2E25DB
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4RegistroTree" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //AD4Registro_VSearch Record: method read @10-7F8AAE5A

//AD4Registro_VSearch Record: method read head @10-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Registro_VSearch Record: method read head

//AD4Registro_VSearch Record: init DataSource @10-726F637B
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Registro_VSearchDataObject ds = new AD4Registro_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.setUrlUSRORCL( page.getHttpGetParams().getParameter("USRORCL") );
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Registro_VSearch Record: init DataSource

//AD4Registro_VSearch Record: check errors @10-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Registro_VSearch Record: check errors

} //AD4Registro_VSearch Record: method read tail @10-FCB6E20C

//AD4Registro_VSearch Record: bind @10-D65D12E2
            public void bind(com.codecharge.components.Component model, AD4Registro_VSearchRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                    model.getControl("DESCRIZIONE_FILTRO").setValue(row.getDESCRIZIONE_FILTRO());
                    model.getControl("USRORCL").setValue(row.getUSRORCL());
                }
            }
//End AD4Registro_VSearch Record: bind

//AD4Registro_VSearch Record: getRowFieldByName @10-7679E47A
            public Object getRowFieldByName( String name, AD4Registro_VSearchRow row ) {
                Object value = null;
                if ( "DESCRIZIONE_FILTRO".equals(name) ) {
                    value = row.getDESCRIZIONE_FILTRO();
                } else if ( "s_DESCRIZIONE".equals(name) ) {
                    value = row.getS_DESCRIZIONE();
                } else if ( "USRORCL".equals(name) ) {
                    value = row.getUSRORCL();
                }
                return value;
            }
//End AD4Registro_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Registro_VSearch Record: method insert @10-11643485

//AD4Registro_VSearch Record: components insert actions @10-68525650
            if (! model.hasErrors()) {
            }
//End AD4Registro_VSearch Record: components insert actions

} //AD4Registro_VSearch Record: method insert tail @10-FCB6E20C

void UpdateAction() { //AD4Registro_VSearch Record: method update @10-5771D0AA

//AD4Registro_VSearch Record: components update actions @10-68525650
            if (! model.hasErrors()) {
            }
//End AD4Registro_VSearch Record: components update actions

} //AD4Registro_VSearch Record: method update tail @10-FCB6E20C

void DeleteAction() { //AD4Registro_VSearch Record: method delete @10-11FC2E1E

//AD4Registro_VSearch Record: components delete actions @10-68525650
            if (! model.hasErrors()) {
            }
//End AD4Registro_VSearch Record: components delete actions

} //AD4Registro_VSearch Record: method delete tail @10-FCB6E20C

//AD4Registro_VSearch Record: method validate @10-857971B9
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.Hidden DESCRIZIONE_FILTRO = (com.codecharge.components.Hidden) model.getChild( "DESCRIZIONE_FILTRO" );
            if (! DESCRIZIONE_FILTRO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "s_DESCRIZIONE" );
            if (! s_DESCRIZIONE.validate()) { isControlError = true; }

            com.codecharge.components.Hidden USRORCL = (com.codecharge.components.Hidden) model.getChild( "USRORCL" );
            if (! USRORCL.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Registro_VSearch Record: method validate

//AD4Registro_VSearch Record Tail @10-FCB6E20C
    }
//End AD4Registro_VSearch Record Tail

//Titolo Grid @43-AAABC7C5
    final class TitoloClass {
        com.codecharge.components.Grid model;
        Event e;
//End Titolo Grid

//Titolo Grid: method perform @43-B48879D3
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

//Titolo Grid: method read: head @43-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Titolo Grid: method read: head

//Titolo Grid: method read: init @43-60673B77
            if ( ! model.allowRead ) return true;
            TitoloDataObject ds = new TitoloDataObject(page);
            ds.setComponent( model );
//End Titolo Grid: method read: init

//Titolo Grid: set where parameters @43-4DFEFA89
            ds.setSesAD4BP( SessionStorage.getInstance(req).getAttribute("AD4BP") );
            ds.setSesISMENU( SessionStorage.getInstance(req).getAttribute("ISMENU") );
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
//End Titolo Grid: set where parameters

//Titolo Grid: set grid properties @43-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Titolo Grid: set grid properties

//Titolo Grid: retrieve data @43-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Titolo Grid: retrieve data

//Titolo Grid: check errors @43-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Titolo Grid: check errors

//Titolo Grid: method read: tail @43-F575E732
            return ( ! isErrors );
        }
//End Titolo Grid: method read: tail

//Titolo Grid: method bind @43-4720F4D6
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

                c = (com.codecharge.components.Control) hashRow.get("USRORCL");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("USRORCL").clone();
                    c.setValue(row.getUSRORCL());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ModChiave");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ModChiave").clone();
                    c.setValue(row.getModChiave());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NuovaChiave");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NuovaChiave").clone();
                    c.setValue(row.getNuovaChiave());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NuovaStringa");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NuovaStringa").clone();
                    c.setValue(row.getNuovaStringa());
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
//End Titolo Grid: method bind

//Titolo Directory: getRowFieldByName @43-B4DCEB8B
        public Object getRowFieldByName( String name, TitoloRow row ) {
            Object value = null;
            if ( "USRORCL".equals(name) ) {
                value = row.getUSRORCL();
            } else if ( "ModChiave".equals(name) ) {
                value = row.getModChiave();
            } else if ( "NuovaChiave".equals(name) ) {
                value = row.getNuovaChiave();
            } else if ( "NuovaStringa".equals(name) ) {
                value = row.getNuovaStringa();
            } else if ( "INDIETRO".equals(name) ) {
                value = row.getINDIETRO();
            } else if ( "AD4BP".equals(name) ) {
                value = row.getAD4BP();
            }
            return value;
        }
//End Titolo Directory: getRowFieldByName

//Titolo Grid: method validate @43-104025BA
        boolean validate() {
            return true;
        }
//End Titolo Grid: method validate

//Titolo Grid Tail @43-FCB6E20C
    }
//End Titolo Grid Tail

//AD4_REGISTRO Grid @6-4F8516E1
    final class AD4_REGISTROClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_REGISTRO Grid

//AD4_REGISTRO Grid: method perform @6-B48879D3
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
//End AD4_REGISTRO Grid: method perform

//AD4_REGISTRO Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_REGISTRO Grid: method read: head

//AD4_REGISTRO Grid: method read: init @6-06A2CBAA
            if ( ! model.allowRead ) return true;
            AD4_REGISTRODataObject ds = new AD4_REGISTRODataObject(page);
            ds.setComponent( model );
//End AD4_REGISTRO Grid: method read: init

//AD4_REGISTRO Grid: set where parameters @6-8B1301C9
            ds.setUrlRG( page.getHttpGetParams().getParameter("RG") );
            ds.setSesMVPC( SessionStorage.getInstance(req).getAttribute("MVPC") );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.setSesUSRORCL( SessionStorage.getInstance(req).getAttribute("USRORCL") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setUrlSEL( page.getHttpGetParams().getParameter("SEL") );
//End AD4_REGISTRO Grid: set where parameters

//AD4_REGISTRO Grid: set grid properties @6-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4_REGISTRO Grid: set grid properties

//AD4_REGISTRO Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_REGISTRO Grid: retrieve data

//AD4_REGISTRO Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_REGISTRO Grid: check errors

//AD4_REGISTRO Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_REGISTRO Grid: method read: tail

//AD4_REGISTRO Grid: method bind @6-90C6474B
        public void bind(com.codecharge.components.Component model, AD4_REGISTRORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_REGISTRORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("ALBERO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ALBERO").clone();
                    c.setValue(row.getALBERO());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_REGISTRO Grid: method bind

//AD4_REGISTRO Directory: getRowFieldByName @6-EF80A0B9
        public Object getRowFieldByName( String name, AD4_REGISTRORow row ) {
            Object value = null;
            if ( "ALBERO".equals(name) ) {
                value = row.getALBERO();
            }
            return value;
        }
//End AD4_REGISTRO Directory: getRowFieldByName

//AD4_REGISTRO Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_REGISTRO Grid: method validate

//AD4_REGISTRO Grid Tail @6-FCB6E20C
    }
//End AD4_REGISTRO Grid Tail

//AD4RegistroTree Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4RegistroTree Page: method validate

//AD4RegistroTreeAction Tail @1-FCB6E20C
}
//End AD4RegistroTreeAction Tail
