//Ad4DizionariZoneAslGuidaAction imports @1-AF13A488
package restrict.Ad4DizionariZoneAslGuida;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariZoneAslGuidaAction imports

//Ad4DizionariZoneAslGuidaAction class @1-B2D10457
public class Ad4DizionariZoneAslGuidaAction extends Action {

//End Ad4DizionariZoneAslGuidaAction class

//Ad4DizionariZoneAslGuidaAction: method perform @1-0A35FBD2
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariZoneAslGuidaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariZoneAslGuidaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariZoneAslGuidaAction: method perform

//Ad4DizionariZoneAslGuidaAction: call children actions @1-292B923C
        if (result == null) {
            GuidaClass Guida = new GuidaClass();
            Guida.perform(page.getGrid("Guida"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariZoneAslGuidaAction: call children actions

//Guida Grid @2-3259D32A
    final class GuidaClass {
        com.codecharge.components.Grid model;
        Event e;
//End Guida Grid

//Guida Grid: method perform @2-B48879D3
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
//End Guida Grid: method perform

//Guida Grid: method read: head @2-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End Guida Grid: method read: head

//Guida Grid: method read: init @2-78F89622
            if ( ! model.allowRead ) return true;
            GuidaDataObject ds = new GuidaDataObject(page);
            ds.setComponent( model );
//End Guida Grid: method read: init

//Guida Grid: set where parameters @2-F5050BE0
            ds.setUrlMVPG( page.getHttpGetParams().getParameter("MVPG") );
            ds.setUrlID_ZONA_ASL( page.getHttpGetParams().getParameter("ID_ZONA_ASL") );
//End Guida Grid: set where parameters

//Guida Grid: set grid properties @2-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End Guida Grid: set grid properties

//Guida Grid: retrieve data @2-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End Guida Grid: retrieve data

//Guida Grid: check errors @2-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End Guida Grid: check errors

//Guida Grid: method read: tail @2-F575E732
            return ( ! isErrors );
        }
//End Guida Grid: method read: tail

//Guida Grid: method bind @2-C7A23484
        public void bind(com.codecharge.components.Component model, GuidaRow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            GuidaRow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("FOLDER1");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FOLDER1").clone();
                    c.setValue(row.getFOLDER1());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("FOLDER2");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FOLDER2").clone();
                    c.setValue(row.getFOLDER2());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End Guida Grid: method bind

//Guida Directory: getRowFieldByName @2-F7EB3B91
        public Object getRowFieldByName( String name, GuidaRow row ) {
            Object value = null;
            if ( "FOLDER1".equals(name) ) {
                value = row.getFOLDER1();
            } else if ( "FOLDER2".equals(name) ) {
                value = row.getFOLDER2();
            }
            return value;
        }
//End Guida Directory: getRowFieldByName

//Guida Grid: method validate @2-104025BA
        boolean validate() {
            return true;
        }
//End Guida Grid: method validate

//Guida Grid Tail @2-FCB6E20C
    }
//End Guida Grid Tail

//Ad4DizionariZoneAslGuida Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariZoneAslGuida Page: method validate

//Ad4DizionariZoneAslGuidaAction Tail @1-FCB6E20C
}
//End Ad4DizionariZoneAslGuidaAction Tail

