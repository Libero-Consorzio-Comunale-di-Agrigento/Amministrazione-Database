//AD4UsjoElencoAction imports @1-8E87F4E2
package ad4web.AD4UsjoElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4UsjoElencoAction imports

//AD4UsjoElencoAction class @1-3C0383AD
public class AD4UsjoElencoAction extends Action {

//End AD4UsjoElencoAction class

//AD4UsjoElencoAction: method perform @1-703346A0
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4UsjoElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4UsjoElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4UsjoElencoAction: method perform

//AD4UsjoElencoAction: call children actions @1-EAFDB474
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
            user_jobsClass user_jobs = new user_jobsClass();
            user_jobs.perform(page.getGrid("user_jobs"));
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
//End AD4UsjoElencoAction: call children actions

//user_jobs Grid @6-40BC1141
    final class user_jobsClass {
        com.codecharge.components.Grid model;
        Event e;
//End user_jobs Grid

//user_jobs Grid: method perform @6-B48879D3
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
//End user_jobs Grid: method perform

//user_jobs Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End user_jobs Grid: method read: head

//user_jobs Grid: method read: init @6-F90973F4
            if ( ! model.allowRead ) return true;
            user_jobsDataObject ds = new user_jobsDataObject(page);
            ds.setComponent( model );
//End user_jobs Grid: method read: init

//user_jobs Grid: set grid properties @6-55FEB0E5
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
            Hashtable sortAscColumns = new Hashtable();
            Hashtable sortDescColumns = new Hashtable();
            sortAscColumns.put( "Sorter_JOB", "JOB" );
            sortAscColumns.put( "Sorter_WHAT", "WHAT" );
            sortAscColumns.put( "Sorter_THIS_DATE", "THIS_DATE" );
            sortAscColumns.put( "Sorter_NEXT_DATE", "NEXT_DATE" );
            sortAscColumns.put( "Sorter_LAST_DATE", "LAST_DATE" );
            sortAscColumns.put( "Sorter_FAILURES", "FAILURES" );
            sortAscColumns.put( "Sorter_TOTAL_TIME", "FAILURES" );
            sortAscColumns.put( "Sorter_INTERVAL", "\"INTERVAL\"" );
            sortAscColumns.put( "Sorter_BROKEN", "BROKEN" );
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
//End user_jobs Grid: set grid properties

//user_jobs Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End user_jobs Grid: retrieve data

//user_jobs Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End user_jobs Grid: check errors

//user_jobs Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End user_jobs Grid: method read: tail

//user_jobs Grid: method bind @6-591A8EF8
        public void bind(com.codecharge.components.Component model, user_jobsRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            user_jobsRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("JOB");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("JOB").clone();
                    c.setValue(row.getJOB());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("ID_JOB").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ID_JOB").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("PROGETTO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("PROGETTO").clone();
                    c.setValue(row.getPROGETTO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("WHAT");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("WHAT").clone();
                    c.setValue(row.getWHAT());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("THIS_DATE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("THIS_DATE").clone();
                    c.setValue(row.getTHIS_DATE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NEXT_DATE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NEXT_DATE").clone();
                    c.setValue(row.getNEXT_DATE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("LAST_DATE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("LAST_DATE").clone();
                    c.setValue(row.getLAST_DATE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("FAILURES");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FAILURES").clone();
                    c.setValue(row.getFAILURES());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("TOTAL_TIME");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("TOTAL_TIME").clone();
                    c.setValue(row.getTOTAL_TIME());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("INTERVAL");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("INTERVAL").clone();
                    c.setValue(row.getINTERVAL());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("BROKEN");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("BROKEN").clone();
                    c.setValue(row.getBROKEN());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End user_jobs Grid: method bind

//user_jobs Directory: getRowFieldByName @6-671DAABD
        public Object getRowFieldByName( String name, user_jobsRow row ) {
            Object value = null;
            if ( "JOB".equals(name) ) {
                value = row.getJOB();
            } else if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
            } else if ( "WHAT".equals(name) ) {
                value = row.getWHAT();
            } else if ( "THIS_DATE".equals(name) ) {
                value = row.getTHIS_DATE();
            } else if ( "NEXT_DATE".equals(name) ) {
                value = row.getNEXT_DATE();
            } else if ( "LAST_DATE".equals(name) ) {
                value = row.getLAST_DATE();
            } else if ( "FAILURES".equals(name) ) {
                value = row.getFAILURES();
            } else if ( "TOTAL_TIME".equals(name) ) {
                value = row.getTOTAL_TIME();
            } else if ( "INTERVAL".equals(name) ) {
                value = row.getINTERVAL();
            } else if ( "BROKEN".equals(name) ) {
                value = row.getBROKEN();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "ID_JOB".equals(name) ) {
                value = row.getID_JOB();
            }
            return value;
        }
//End user_jobs Directory: getRowFieldByName

//user_jobs Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End user_jobs Grid: method validate

//user_jobs Grid Tail @6-FCB6E20C
    }
//End user_jobs Grid Tail

//AD4UsjoElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4UsjoElenco Page: method validate

//AD4UsjoElencoAction Tail @1-FCB6E20C
}
//End AD4UsjoElencoAction Tail
