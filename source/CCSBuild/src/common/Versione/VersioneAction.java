//VersioneAction imports @1-1C9F2860
package common.Versione;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End VersioneAction imports

//VersioneAction class @1-82276862
public class VersioneAction extends Action {

//End VersioneAction class

//VersioneAction: method perform @1-356EE45C
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new VersioneModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "VersioneModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End VersioneAction: method perform

//VersioneAction: call children actions @1-7AFED39C
        if (result == null) {
            VersioneGridClass VersioneGrid = new VersioneGridClass();
            VersioneGrid.perform(page.getGrid("VersioneGrid"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End VersioneAction: call children actions

//VersioneGrid Grid @3-DE803CCB
    final class VersioneGridClass {
        com.codecharge.components.Grid model;
        Event e;
//End VersioneGrid Grid

//VersioneGrid Grid: method perform @3-B48879D3
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
//End VersioneGrid Grid: method perform

//VersioneGrid Grid: method read: head @3-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End VersioneGrid Grid: method read: head

//VersioneGrid Grid: method read: init @3-C5DB18BE
            if ( ! model.allowRead ) return true;
            VersioneGridDataObject ds = new VersioneGridDataObject(page);
            ds.setComponent( model );
//End VersioneGrid Grid: method read: init

//VersioneGrid Grid: set grid properties @3-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End VersioneGrid Grid: set grid properties

//VersioneGrid Grid: retrieve data @3-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End VersioneGrid Grid: retrieve data

//VersioneGrid Grid: check errors @3-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End VersioneGrid Grid: check errors

//VersioneGrid Grid: method read: tail @3-F575E732
            return ( ! isErrors );
        }
//End VersioneGrid Grid: method read: tail

//VersioneGrid Grid: method bind @3-DFDEC203
        public void bind(com.codecharge.components.Component model, VersioneGridRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            VersioneGridRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("VERSIONE_DB");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("VERSIONE_DB").clone();
                    c.setValue(row.getVERSIONE_DB());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End VersioneGrid Grid: method bind

//VersioneGrid Directory: getRowFieldByName @3-FA94D110
        public Object getRowFieldByName( String name, VersioneGridRow row ) {
            Object value = null;
            if ( "VERSIONE".equals(name) ) {
                value = row.getVERSIONE();
            } else if ( "VERSIONE_DB".equals(name) ) {
                value = row.getVERSIONE_DB();
            }
            return value;
        }
//End VersioneGrid Directory: getRowFieldByName

//VersioneGrid Grid: method validate @3-104025BA
        boolean validate() {
            return true;
        }
//End VersioneGrid Grid: method validate

//VersioneGrid Grid Tail @3-FCB6E20C
    }
//End VersioneGrid Grid Tail

//Versione Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Versione Page: method validate

//VersioneAction Tail @1-FCB6E20C
}
//End VersioneAction Tail

