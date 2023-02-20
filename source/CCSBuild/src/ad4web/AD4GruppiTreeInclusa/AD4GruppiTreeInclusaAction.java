//AD4GruppiTreeInclusaAction imports @1-CFB29B0A
package ad4web.AD4GruppiTreeInclusa;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GruppiTreeInclusaAction imports

//AD4GruppiTreeInclusaAction class @1-E9536905
public class AD4GruppiTreeInclusaAction extends Action {

//End AD4GruppiTreeInclusaAction class

//AD4GruppiTreeInclusaAction: method perform @1-7C1388F9
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GruppiTreeInclusaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GruppiTreeInclusaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GruppiTreeInclusaAction: method perform

//AD4GruppiTreeInclusaAction: call children actions @1-B7ECB963
        if (result == null) {
            AD4_GRUPPIClass AD4_GRUPPI = new AD4_GRUPPIClass();
            AD4_GRUPPI.perform(page.getGrid("AD4_GRUPPI"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4GruppiTreeInclusaAction: call children actions

//AD4_GRUPPI Grid @6-6F5C897C
    final class AD4_GRUPPIClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_GRUPPI Grid

//AD4_GRUPPI Grid: method perform @6-B48879D3
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
//End AD4_GRUPPI Grid: method perform

//AD4_GRUPPI Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_GRUPPI Grid: method read: head

//AD4_GRUPPI Grid: method read: init @6-71C226D0
            if ( ! model.allowRead ) return true;
            AD4_GRUPPIDataObject ds = new AD4_GRUPPIDataObject(page);
            ds.setComponent( model );
//End AD4_GRUPPI Grid: method read: init

//AD4_GRUPPI Grid: set where parameters @6-796FA13D
            ds.setUrlID( page.getHttpGetParams().getParameter("ID") );
            ds.setUrlID_PADRE( page.getHttpGetParams().getParameter("ID_PADRE") );
//End AD4_GRUPPI Grid: set where parameters

//AD4_GRUPPI Grid: set grid properties @6-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End AD4_GRUPPI Grid: set grid properties

//AD4_GRUPPI Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_GRUPPI Grid: retrieve data

//AD4_GRUPPI Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_GRUPPI Grid: check errors

//AD4_GRUPPI Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_GRUPPI Grid: method read: tail

//AD4_GRUPPI Grid: method bind @6-65641D75
        public void bind(com.codecharge.components.Component model, AD4_GRUPPIRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_GRUPPIRow row = null;
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
//End AD4_GRUPPI Grid: method bind

//AD4_GRUPPI Directory: getRowFieldByName @6-9B83CF19
        public Object getRowFieldByName( String name, AD4_GRUPPIRow row ) {
            Object value = null;
            if ( "ALBERO".equals(name) ) {
                value = row.getALBERO();
            }
            return value;
        }
//End AD4_GRUPPI Directory: getRowFieldByName

//AD4_GRUPPI Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_GRUPPI Grid: method validate

//AD4_GRUPPI Grid Tail @6-FCB6E20C
    }
//End AD4_GRUPPI Grid Tail

//AD4GruppiTreeInclusa Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GruppiTreeInclusa Page: method validate

//AD4GruppiTreeInclusaAction Tail @1-FCB6E20C
}
//End AD4GruppiTreeInclusaAction Tail
