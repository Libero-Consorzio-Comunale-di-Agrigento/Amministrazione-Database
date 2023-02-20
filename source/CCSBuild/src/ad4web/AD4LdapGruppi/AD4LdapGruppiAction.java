//AD4LdapGruppiAction imports @1-28A43D17
package ad4web.AD4LdapGruppi;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4LdapGruppiAction imports

//AD4LdapGruppiAction class @1-8250AB72
public class AD4LdapGruppiAction extends Action {

//End AD4LdapGruppiAction class

//AD4LdapGruppiAction: method perform @1-6333403C
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4LdapGruppiModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4LdapGruppiModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4LdapGruppiAction: method perform

//AD4LdapGruppiAction: call children actions @1-4954CB92
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
            ldap_configClass ldap_config = new ldap_configClass();
            ldap_config.perform(page.getGrid("ldap_config"));
        }
        if (result == null) {
            checkAuthorizationMappingClass checkAuthorizationMapping = new checkAuthorizationMappingClass();
            checkAuthorizationMapping.perform(page.getGrid("checkAuthorizationMapping"));
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
//End AD4LdapGruppiAction: call children actions

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

//ldap_config Grid: method bind @110-C94D2525
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

                c = (com.codecharge.components.Control) hashRow.get("GRUPPO_AD4");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("GRUPPO_AD4").clone();
                    c.setValue(row.getGRUPPO_AD4());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("STRINGA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("STRINGA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("VALORE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("VALORE").clone();
                    c.setValue(row.getVALORE());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End ldap_config Grid: method bind

//ldap_config Directory: getRowFieldByName @110-2AC2BCD7
        public Object getRowFieldByName( String name, ldap_configRow row ) {
            Object value = null;
            if ( "GRUPPO_AD4".equals(name) ) {
                value = row.getGRUPPO_AD4();
            } else if ( "VALORE".equals(name) ) {
                value = row.getVALORE();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "STRINGA".equals(name) ) {
                value = row.getSTRINGA();
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

//checkAuthorizationMapping Grid @130-8CB5B5C5
    final class checkAuthorizationMappingClass {
        com.codecharge.components.Grid model;
        Event e;
//End checkAuthorizationMapping Grid

//checkAuthorizationMapping Grid: method perform @130-B48879D3
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
//End checkAuthorizationMapping Grid: method perform

//checkAuthorizationMapping Grid: method read: head @130-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End checkAuthorizationMapping Grid: method read: head

//checkAuthorizationMapping Grid: method read: init @130-C16AA4D9
            if ( ! model.allowRead ) return true;
            checkAuthorizationMappingDataObject ds = new checkAuthorizationMappingDataObject(page);
            ds.setComponent( model );
//End checkAuthorizationMapping Grid: method read: init

//checkAuthorizationMapping Grid: set grid properties @130-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End checkAuthorizationMapping Grid: set grid properties

//checkAuthorizationMapping Grid: retrieve data @130-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End checkAuthorizationMapping Grid: retrieve data

//checkAuthorizationMapping Grid: check errors @130-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End checkAuthorizationMapping Grid: check errors

//checkAuthorizationMapping Grid: method read: tail @130-F575E732
            return ( ! isErrors );
        }
//End checkAuthorizationMapping Grid: method read: tail

//checkAuthorizationMapping Grid: method bind @130-D9238757
        public void bind(com.codecharge.components.Component model, checkAuthorizationMappingRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            checkAuthorizationMappingRow row = null;
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
                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End checkAuthorizationMapping Grid: method bind

//checkAuthorizationMapping Directory: getRowFieldByName @130-09FEA32F
        public Object getRowFieldByName( String name, checkAuthorizationMappingRow row ) {
            Object value = null;
            return value;
        }
//End checkAuthorizationMapping Directory: getRowFieldByName

//checkAuthorizationMapping Grid: method validate @130-104025BA
        boolean validate() {
            return true;
        }
//End checkAuthorizationMapping Grid: method validate

//checkAuthorizationMapping Grid Tail @130-FCB6E20C
    }
//End checkAuthorizationMapping Grid Tail

//AD4LdapGruppi Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4LdapGruppi Page: method validate

//AD4LdapGruppiAction Tail @1-FCB6E20C
}
//End AD4LdapGruppiAction Tail
