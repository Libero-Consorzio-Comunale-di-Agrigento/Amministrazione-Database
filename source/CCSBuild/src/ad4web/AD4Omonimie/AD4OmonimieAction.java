//AD4OmonimieAction imports @1-1F6AB2E8
package ad4web.AD4Omonimie;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4OmonimieAction imports

//AD4OmonimieAction class @1-A1B8E78B
public class AD4OmonimieAction extends Action {

//End AD4OmonimieAction class

//AD4OmonimieAction: method perform @1-831C23C8
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4OmonimieModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4OmonimieModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4OmonimieAction: method perform

//AD4OmonimieAction: call children actions @1-8EAC4C82
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
            ldap_configClass ldap_config = new ldap_configClass();
            ldap_config.perform(page.getGrid("ldap_config"));
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
//End AD4OmonimieAction: call children actions

//AD4Ruoli_VSearch Record @132-DC7642AB
    final class AD4Ruoli_VSearchClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4Ruoli_VSearch Record

//AD4Ruoli_VSearch Record: method perform @132-1CB004DD
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            page.setRedirectString( performReturnPage.toString() );
//End AD4Ruoli_VSearch Record: method perform

//AD4Ruoli_VSearch Record: children actions @132-7679E1E1
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
            reads_daignorare(model.getListBox("s_daignorare"));
            reads_unificati(model.getListBox("s_unificati"));
            reads_copiati(model.getListBox("s_copiati"));
//End AD4Ruoli_VSearch Record: children actions

//AD4Ruoli_VSearch Record: method perform tail @132-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4Ruoli_VSearch Record: method perform tail

//DoSearch Button @135-D6CF3BA1
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( "AD4Omonimie" + Names.ACTION_SUFFIX + "?"  + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

//ListBoxAction @134-138D75AC
        protected void reads_daignorare(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "1;Ignorare;0;Esaminare" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @141-2C8984EC
        protected void reads_unificati(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "1;Unificati;0;Non Unificati" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

//ListBoxAction @142-19F7C9BE
        protected void reads_copiati(com.codecharge.components.ListBox model) {
            DataObjectEvent e = new DataObjectEvent();
            e.setSql( "1;Copiati;0;Non Copiati" );
            model.fireBeforeBuildSelectEvent(e);
            model.fireBeforeExecuteSelectEvent(e);
            model.setListOfValues( e.getSql() );
            model.fireAfterExecuteSelectEvent(e);
        }
//End ListBoxAction

void read() { //AD4Ruoli_VSearch Record: method read @132-7F8AAE5A

//AD4Ruoli_VSearch Record: method read head @132-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4Ruoli_VSearch Record: method read head

//AD4Ruoli_VSearch Record: init DataSource @132-379F9923
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4Ruoli_VSearchDataObject ds = new AD4Ruoli_VSearchDataObject(page);
            ds.setComponent( model );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4Ruoli_VSearch Record: init DataSource

//AD4Ruoli_VSearch Record: check errors @132-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4Ruoli_VSearch Record: check errors

} //AD4Ruoli_VSearch Record: method read tail @132-FCB6E20C

//AD4Ruoli_VSearch Record: bind @132-8931DE3C
            public void bind(com.codecharge.components.Component model, AD4Ruoli_VSearchRow row ) {
                if ( model == null || row == null ) return;
                if ( this.valid ) {
                }
            }
//End AD4Ruoli_VSearch Record: bind

//AD4Ruoli_VSearch Record: getRowFieldByName @132-53209F13
            public Object getRowFieldByName( String name, AD4Ruoli_VSearchRow row ) {
                Object value = null;
                if ( "s_daignorare".equals(name) ) {
                    value = row.getS_daignorare();
                } else if ( "s_unificati".equals(name) ) {
                    value = row.getS_unificati();
                } else if ( "s_copiati".equals(name) ) {
                    value = row.getS_copiati();
                }
                return value;
            }
//End AD4Ruoli_VSearch Record: getRowFieldByName

void InsertAction() { //AD4Ruoli_VSearch Record: method insert @132-11643485

//AD4Ruoli_VSearch Record: components insert actions @132-68525650
            if (! model.hasErrors()) {
            }
//End AD4Ruoli_VSearch Record: components insert actions

} //AD4Ruoli_VSearch Record: method insert tail @132-FCB6E20C

void UpdateAction() { //AD4Ruoli_VSearch Record: method update @132-5771D0AA

//AD4Ruoli_VSearch Record: components update actions @132-68525650
            if (! model.hasErrors()) {
            }
//End AD4Ruoli_VSearch Record: components update actions

} //AD4Ruoli_VSearch Record: method update tail @132-FCB6E20C

void DeleteAction() { //AD4Ruoli_VSearch Record: method delete @132-11FC2E1E

//AD4Ruoli_VSearch Record: components delete actions @132-68525650
            if (! model.hasErrors()) {
            }
//End AD4Ruoli_VSearch Record: components delete actions

} //AD4Ruoli_VSearch Record: method delete tail @132-FCB6E20C

//AD4Ruoli_VSearch Record: method validate @132-CBAB0EA9
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.ListBox s_daignorare = (com.codecharge.components.ListBox) model.getChild( "s_daignorare" );
            if (! s_daignorare.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_unificati = (com.codecharge.components.ListBox) model.getChild( "s_unificati" );
            if (! s_unificati.validate()) { isControlError = true; }

            com.codecharge.components.ListBox s_copiati = (com.codecharge.components.ListBox) model.getChild( "s_copiati" );
            if (! s_copiati.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4Ruoli_VSearch Record: method validate

//AD4Ruoli_VSearch Record Tail @132-FCB6E20C
    }
//End AD4Ruoli_VSearch Record Tail

//ldap_config Grid @110-A2FF3085
    final class ldap_configClass {
        com.codecharge.components.Grid model;
        Event e;
//End ldap_config Grid

//ldap_config Grid: method perform @110-B48879D3
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
//End ldap_config Grid: method perform

//ldap_config Grid: method read: head @110-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End ldap_config Grid: method read: head

//ldap_config Grid: method read: init @110-264EECB1
            if ( ! model.allowRead ) return true;
            ldap_configDataObject ds = new ldap_configDataObject(page);
            ds.setComponent( model );
//End ldap_config Grid: method read: init

//ldap_config Grid: set where parameters @110-86B9DB79
            try {
                ds.setUrlS_daignorare( page.getHttpGetParams().getParameter("s_daignorare"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 's_daignorare'" );
                return false;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 's_daignorare'" );
                return false;
            }
            try {
                ds.setUrlS_copiati( page.getHttpGetParams().getParameter("s_copiati"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 's_copiati'" );
                return false;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 's_copiati'" );
                return false;
            }
            ds.setUrlS_unificati( page.getHttpGetParams().getParameter("s_unificati") );
//End ldap_config Grid: set where parameters

//ldap_config Grid: set grid properties @110-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End ldap_config Grid: set grid properties

//ldap_config Grid: retrieve data @110-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End ldap_config Grid: retrieve data

//ldap_config Grid: check errors @110-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End ldap_config Grid: check errors

//ldap_config Grid: method read: tail @110-F575E732
            return ( ! isErrors );
        }
//End ldap_config Grid: method read: tail

//ldap_config Grid: method bind @110-14D3BA97
        public void bind(com.codecharge.components.Component model, ldap_configRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            ldap_configRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("SOSIA_NOMINATIVO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SOSIA_NOMINATIVO").clone();
                    c.setValue(row.getSOSIA_NOMINATIVO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("UNIFICA_PROFILO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UNIFICA_PROFILO").clone();
                    c.setValue(row.getUNIFICA_PROFILO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("UTENTE_UNIFICARE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE_UNIFICARE").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("UTENTE_ALIMENTARE_UNIFICARE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE_ALIMENTARE_UNIFICARE").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("COPIA_PROFILO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COPIA_PROFILO").clone();
                    c.setValue(row.getCOPIA_PROFILO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("UTENTE_ALIMENTARE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE_ALIMENTARE").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("UTENTE_ORIGINE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("UTENTE_ORIGINE").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("IGNORA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("IGNORA").clone();
                    c.setValue(row.getIGNORA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("MODIFICA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("MODIFICA").clone();
                    c.setValue(row.getMODIFICA());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("S_SOSIA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("S_SOSIA").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("S_UTENTE").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("S_UTENTE").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End ldap_config Grid: method bind

//ldap_config Directory: getRowFieldByName @110-821D1744
        public Object getRowFieldByName( String name, ldap_configRow row ) {
            Object value = null;
            if ( "NOMINATIVO".equals(name) ) {
                value = row.getNOMINATIVO();
            } else if ( "SOSIA_NOMINATIVO".equals(name) ) {
                value = row.getSOSIA_NOMINATIVO();
            } else if ( "UNIFICA_PROFILO".equals(name) ) {
                value = row.getUNIFICA_PROFILO();
            } else if ( "COPIA_PROFILO".equals(name) ) {
                value = row.getCOPIA_PROFILO();
            } else if ( "IGNORA".equals(name) ) {
                value = row.getIGNORA();
            } else if ( "MODIFICA".equals(name) ) {
                value = row.getMODIFICA();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "STRINGA".equals(name) ) {
                value = row.getSTRINGA();
            } else if ( "UTENTE_UNIFICARE".equals(name) ) {
                value = row.getUTENTE_UNIFICARE();
            } else if ( "SOSIA".equals(name) ) {
                value = row.getSOSIA();
            } else if ( "UTENTE_ALIMENTARE_UNIFICARE".equals(name) ) {
                value = row.getUTENTE_ALIMENTARE_UNIFICARE();
            } else if ( "UTENTE".equals(name) ) {
                value = row.getUTENTE();
            } else if ( "UTENTE_ALIMENTARE".equals(name) ) {
                value = row.getUTENTE_ALIMENTARE();
            } else if ( "UTENTE_ORIGINE".equals(name) ) {
                value = row.getUTENTE_ORIGINE();
            } else if ( "S_SOSIA".equals(name) ) {
                value = row.getS_SOSIA();
            } else if ( "S_UTENTE".equals(name) ) {
                value = row.getS_UTENTE();
            }
            return value;
        }
//End ldap_config Directory: getRowFieldByName

//ldap_config Grid: method validate @110-104025BA
        boolean validate() {
            return true;
        }
//End ldap_config Grid: method validate

//ldap_config Grid Tail @110-FCB6E20C
    }
//End ldap_config Grid Tail

//AD4Omonimie Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4Omonimie Page: method validate

//AD4OmonimieAction Tail @1-FCB6E20C
}
//End AD4OmonimieAction Tail
