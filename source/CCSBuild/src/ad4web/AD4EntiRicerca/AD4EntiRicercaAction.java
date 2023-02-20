//AD4EntiRicercaAction imports @1-3ADA06E9
package ad4web.AD4EntiRicerca;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4EntiRicercaAction imports

//AD4EntiRicercaAction class @1-8BFD322C
public class AD4EntiRicercaAction extends Action {

//End AD4EntiRicercaAction class

//AD4EntiRicercaAction: method perform @1-D13A4AFE
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4EntiRicercaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4EntiRicercaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4EntiRicercaAction: method perform

//AD4EntiRicercaAction: call children actions @1-BCCDE46A
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
            AD4Enti_VSearchClass AD4Enti_VSearch = new AD4Enti_VSearchClass();
            if ( ( redirect = AD4Enti_VSearch.perform( page.getRecord("AD4Enti_VSearch")) ) != null ) result = redirect;
        }
        if (result == null) {
            AD4EntiVClass AD4EntiV = new AD4EntiVClass();
            AD4EntiV.perform(page.getGrid("AD4EntiV"));
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
//End AD4EntiRicercaAction: call children actions

//AD4Enti_VSearch Record @6-DFDF9DEE
    final class AD4Enti_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Enti_VSearch Record

//AD4Enti_VSearch Record: method perform @6-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Enti_VSearch Record: method perform

//AD4Enti_VSearch Record: children actions @6-59CD9B4B
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4Enti_VSearch".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4Enti_VSearch Record: children actions

//AD4Enti_VSearch Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Enti_VSearch Record: method perform tail

//DoSearch Button @342-062BAE7B
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4EntiRicerca" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //AD4Enti_VSearch Record: method read @6-7F8AAE5A

//AD4Enti_VSearch Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Enti_VSearch Record: method read head

//AD4Enti_VSearch Record: init DataSource @6-4B2AAC37
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Enti_VSearchDataObject ds = new AD4Enti_VSearchDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Enti_VSearch Record: init DataSource

//AD4Enti_VSearch Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Enti_VSearch Record: check errors

} //AD4Enti_VSearch Record: method read tail @6-FCB6E20C

//AD4Enti_VSearch Record: bind @6-8A42A102
            public void bind(com.codecharge.components.Component model, AD4Enti_VSearchRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("IMMAGINE_FILTRO").setValue(row.getIMMAGINE_FILTRO());
                if ( this.valid ) {
                }
            }
//End AD4Enti_VSearch Record: bind

//AD4Enti_VSearch Record: getRowFieldByName @6-28D4339D
            public Object getRowFieldByName( String name, AD4Enti_VSearchRow row ) {
                Object value = null;
                if ( "IMMAGINE_FILTRO".equals(name) ) {
                    value = row.getIMMAGINE_FILTRO();
                } else if ( "s_DESCRIZIONE".equals(name) ) {
                    value = row.getS_DESCRIZIONE();
                } else if ( "Nuovo".equals(name) ) {
                    value = row.getNuovo();
                }
                return value;
            }
//End AD4Enti_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Enti_VSearch Record: method insert @6-11643485

//AD4Enti_VSearch Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Enti_VSearch Record: components insert actions

} //AD4Enti_VSearch Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4Enti_VSearch Record: method update @6-5771D0AA

//AD4Enti_VSearch Record: components update actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Enti_VSearch Record: components update actions

} //AD4Enti_VSearch Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4Enti_VSearch Record: method delete @6-11FC2E1E

//AD4Enti_VSearch Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4Enti_VSearch Record: components delete actions

} //AD4Enti_VSearch Record: method delete tail @6-FCB6E20C

//AD4Enti_VSearch Record: method validate @6-58D16649
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_DESCRIZIONE = (com.codecharge.components.TextBox) model.getChild( "s_DESCRIZIONE" );
            if (! s_DESCRIZIONE.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Enti_VSearch Record: method validate

//AD4Enti_VSearch Record Tail @6-FCB6E20C
    }
//End AD4Enti_VSearch Record Tail

//AD4EntiV Grid @142-2FA7E149
    final class AD4EntiVClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4EntiV Grid

//AD4EntiV Grid: method perform @142-B48879D3
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
//End AD4EntiV Grid: method perform

//AD4EntiV Grid: method read: head @142-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4EntiV Grid: method read: head

//AD4EntiV Grid: method read: init @142-F23398CD
            if ( ! model.allowRead ) return true;
            AD4EntiVDataObject ds = new AD4EntiVDataObject(page);
            ds.setComponent( model );
//End AD4EntiV Grid: method read: init

//AD4EntiV Grid: set where parameters @142-0440F0C8
            ds.setUrlS_DESCRIZIONE( page.getHttpGetParams().getParameter("s_DESCRIZIONE") );
//End AD4EntiV Grid: set where parameters

//AD4EntiV Grid: set grid properties @142-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4EntiV Grid: set grid properties

//AD4EntiV Grid: retrieve data @142-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4EntiV Grid: retrieve data

//AD4EntiV Grid: check errors @142-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4EntiV Grid: check errors

//AD4EntiV Grid: method read: tail @142-F575E732
            return ( ! isErrors );
        }
//End AD4EntiV Grid: method read: tail

//AD4EntiV Grid: method bind @142-3D2EBF36
        public void bind(com.codecharge.components.Component model, AD4EntiVRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4EntiVRow row = null;
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
                ((com.codecharge.components.Link) c).getParameter("ENTE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ENTE").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DATI_ENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATI_ENTE").clone();
                    c.setValue(row.getDATI_ENTE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4EntiV Grid: method bind

//AD4EntiV Directory: getRowFieldByName @142-B46E2E72
        public Object getRowFieldByName( String name, AD4EntiVRow row ) {
            Object value = null;
            if ( "DESCRIZIONE".equals(name) ) {
                value = row.getDESCRIZIONE();
            } else if ( "DATI_ENTE".equals(name) ) {
                value = row.getDATI_ENTE();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "ENTE".equals(name) ) {
                value = row.getENTE();
            }
            return value;
        }
//End AD4EntiV Directory: getRowFieldByName

//AD4EntiV Grid: method validate @142-104025BA
        boolean validate() {
            return true;
        }
//End AD4EntiV Grid: method validate

//AD4EntiV Grid Tail @142-FCB6E20C
    }
//End AD4EntiV Grid Tail

//AD4EntiRicerca Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4EntiRicerca Page: method validate

//AD4EntiRicercaAction Tail @1-FCB6E20C
}
//End AD4EntiRicercaAction Tail

