//AD4LdapConfigAction imports @1-FC837328
package ad4web.AD4LdapConfig;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4LdapConfigAction imports

//AD4LdapConfigAction class @1-4F7D492C
public class AD4LdapConfigAction extends Action {

//End AD4LdapConfigAction class

//AD4LdapConfigAction: method perform @1-65E71FCA
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4LdapConfigModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4LdapConfigModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4LdapConfigAction: method perform

//AD4LdapConfigAction: call children actions @1-FAD190FB
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
            guida_ldapClass guida_ldap = new guida_ldapClass();
            guida_ldap.perform(page.getGrid("guida_ldap"));
        }
        if (result == null) {
            toolbox_gridClass toolbox_grid = new toolbox_gridClass();
            toolbox_grid.perform(page.getGrid("toolbox_grid"));
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
//End AD4LdapConfigAction: call children actions

//guida_ldap Grid @83-28B39238
    final class guida_ldapClass {
        com.codecharge.components.Grid model;
        Event e;
//End guida_ldap Grid

//guida_ldap Grid: method perform @83-B48879D3
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
//End guida_ldap Grid: method perform

//guida_ldap Grid: method read: head @83-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End guida_ldap Grid: method read: head

//guida_ldap Grid: method read: init @83-416EECE9
            if ( ! model.allowRead ) return true;
            guida_ldapDataObject ds = new guida_ldapDataObject(page);
            ds.setComponent( model );
//End guida_ldap Grid: method read: init

//guida_ldap Grid: set where parameters @83-25FF3A64
            ds.setUrlChiave( page.getHttpGetParams().getParameter("chiave") );
            ds.setSesAD4LDAPCA( SessionStorage.getInstance(req).getAttribute("AD4LDAPCA") );
//End guida_ldap Grid: set where parameters

//guida_ldap Grid: set grid properties @83-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End guida_ldap Grid: set grid properties

//guida_ldap Grid: retrieve data @83-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End guida_ldap Grid: retrieve data

//guida_ldap Grid: check errors @83-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End guida_ldap Grid: check errors

//guida_ldap Grid: method read: tail @83-F575E732
            return ( ! isErrors );
        }
//End guida_ldap Grid: method read: tail

//guida_ldap Grid: method bind @83-8DC502DB
        public void bind(com.codecharge.components.Component model, guida_ldapRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            guida_ldapRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("guidaLDAP");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("guidaLDAP").clone();
                    c.setValue(row.getGuidaLDAP());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End guida_ldap Grid: method bind

//guida_ldap Directory: getRowFieldByName @83-8B725D5B
        public Object getRowFieldByName( String name, guida_ldapRow row ) {
            Object value = null;
            if ( "guidaLDAP".equals(name) ) {
                value = row.getGuidaLDAP();
            }
            return value;
        }
//End guida_ldap Directory: getRowFieldByName

//guida_ldap Grid: method validate @83-104025BA
        boolean validate() {
            return true;
        }
//End guida_ldap Grid: method validate

//guida_ldap Grid Tail @83-FCB6E20C
    }
//End guida_ldap Grid Tail

//toolbox_grid Grid @120-0EF87B3A
    final class toolbox_gridClass {
        com.codecharge.components.Grid model;
        Event e;
//End toolbox_grid Grid

//toolbox_grid Grid: method perform @120-B48879D3
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
//End toolbox_grid Grid: method perform

//toolbox_grid Grid: method read: head @120-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End toolbox_grid Grid: method read: head

//toolbox_grid Grid: method read: init @120-29BA21BF
            if ( ! model.allowRead ) return true;
            toolbox_gridDataObject ds = new toolbox_gridDataObject(page);
            ds.setComponent( model );
//End toolbox_grid Grid: method read: init

//toolbox_grid Grid: set where parameters @120-25FF3A64
            ds.setUrlChiave( page.getHttpGetParams().getParameter("chiave") );
            ds.setSesAD4LDAPCA( SessionStorage.getInstance(req).getAttribute("AD4LDAPCA") );
//End toolbox_grid Grid: set where parameters

//toolbox_grid Grid: set grid properties @120-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End toolbox_grid Grid: set grid properties

//toolbox_grid Grid: retrieve data @120-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End toolbox_grid Grid: retrieve data

//toolbox_grid Grid: check errors @120-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End toolbox_grid Grid: check errors

//toolbox_grid Grid: method read: tail @120-F575E732
            return ( ! isErrors );
        }
//End toolbox_grid Grid: method read: tail

//toolbox_grid Grid: method bind @120-0787C35D
        public void bind(com.codecharge.components.Component model, toolbox_gridRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            toolbox_gridRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("TIPO_SERVER");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TIPO_SERVER").clone();
                    c.setValue(row.getTIPO_SERVER());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CHIAVE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CHIAVE").clone();
                    c.setValue(row.getCHIAVE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CREA_ALTERNATIVO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CREA_ALTERNATIVO").clone();
                    c.setValue(row.getCREA_ALTERNATIVO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ELIMINA_ALTERNATIVO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ELIMINA_ALTERNATIVO").clone();
                    c.setValue(row.getELIMINA_ALTERNATIVO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Nuovo");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Nuovo").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("ID").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("ID").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End toolbox_grid Grid: method bind

//toolbox_grid Directory: getRowFieldByName @120-701594CE
        public Object getRowFieldByName( String name, toolbox_gridRow row ) {
            Object value = null;
            if ( "TIPO_SERVER".equals(name) ) {
                value = row.getTIPO_SERVER();
            } else if ( "CHIAVE".equals(name) ) {
                value = row.getCHIAVE();
            } else if ( "Nuovo".equals(name) ) {
                value = row.getNuovo();
            } else if ( "CREA_ALTERNATIVO".equals(name) ) {
                value = row.getCREA_ALTERNATIVO();
            } else if ( "ELIMINA_ALTERNATIVO".equals(name) ) {
                value = row.getELIMINA_ALTERNATIVO();
            } else if ( "ID".equals(name) ) {
                value = row.getID();
            }
            return value;
        }
//End toolbox_grid Directory: getRowFieldByName

//toolbox_grid Grid: method validate @120-104025BA
        boolean validate() {
            return true;
        }
//End toolbox_grid Grid: method validate

//toolbox_grid Grid Tail @120-FCB6E20C
    }
//End toolbox_grid Grid Tail

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

//ldap_config Grid: set where parameters @110-25FF3A64
            ds.setUrlChiave( page.getHttpGetParams().getParameter("chiave") );
            ds.setSesAD4LDAPCA( SessionStorage.getInstance(req).getAttribute("AD4LDAPCA") );
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

//ldap_config Grid: method bind @110-3B3E63AF
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

                c = (com.codecharge.components.Control) hashRow.get("STRINGA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("STRINGA").clone();
                    c.setValue(row.getSTRINGA());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ID").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ID").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("STRINGA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("STRINGA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("VALORE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("VALORE").clone();
                    c.setValue(row.getVALORE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("COMMENTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("COMMENTO").clone();
                    c.setValue(row.getCOMMENTO());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End ldap_config Grid: method bind

//ldap_config Directory: getRowFieldByName @110-28478E7E
        public Object getRowFieldByName( String name, ldap_configRow row ) {
            Object value = null;
            if ( "STRINGA".equals(name) ) {
                value = row.getSTRINGA();
            } else if ( "VALORE".equals(name) ) {
                value = row.getVALORE();
            } else if ( "COMMENTO".equals(name) ) {
                value = row.getCOMMENTO();
            } else if ( "ID".equals(name) ) {
                value = row.getID();
            } else if ( "CHIAVE".equals(name) ) {
                value = row.getCHIAVE();
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

//AD4LdapConfig Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4LdapConfig Page: method validate

//AD4LdapConfigAction Tail @1-FCB6E20C
}
//End AD4LdapConfigAction Tail
