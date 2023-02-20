//Ad4DizionariGuidaAction imports @1-FCC5B601
package ad4web.Ad4DizionariGuida;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End Ad4DizionariGuidaAction imports

//Ad4DizionariGuidaAction class @1-43DA6FE6
public class Ad4DizionariGuidaAction extends Action {

//End Ad4DizionariGuidaAction class

//Ad4DizionariGuidaAction: method perform @1-51BCB8D8
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new Ad4DizionariGuidaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "Ad4DizionariGuidaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End Ad4DizionariGuidaAction: method perform

//Ad4DizionariGuidaAction: call children actions @1-292B923C
        if (result == null) {
            GuidaClass Guida = new GuidaClass();
            Guida.perform(page.getGrid("Guida"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End Ad4DizionariGuidaAction: call children actions

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

//Guida Grid: set where parameters @2-9359276E
            ds.setUrlMVPG( page.getHttpGetParams().getParameter("MVPG") );
            ds.setSesMVURL( SessionStorage.getInstance(req).getAttribute("MVURL") );
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

//Guida Grid: method bind @2-0EEF844F
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

                c = (com.codecharge.components.Control) hashRow.get("FOLDER3");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FOLDER3").clone();
                    c.setValue(row.getFOLDER3());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("FOLDER4");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FOLDER4").clone();
                    c.setValue(row.getFOLDER4());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("FOLDER5");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FOLDER5").clone();
                    c.setValue(row.getFOLDER5());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("FOLDER6");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FOLDER6").clone();
                    c.setValue(row.getFOLDER6());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End Guida Grid: method bind

//Guida Directory: getRowFieldByName @2-993E7ACE
        public Object getRowFieldByName( String name, GuidaRow row ) {
            Object value = null;
            if ( "FOLDER1".equals(name) ) {
                value = row.getFOLDER1();
            } else if ( "FOLDER2".equals(name) ) {
                value = row.getFOLDER2();
            } else if ( "FOLDER3".equals(name) ) {
                value = row.getFOLDER3();
            } else if ( "FOLDER4".equals(name) ) {
                value = row.getFOLDER4();
            } else if ( "FOLDER5".equals(name) ) {
                value = row.getFOLDER5();
            } else if ( "FOLDER6".equals(name) ) {
                value = row.getFOLDER6();
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

//Ad4DizionariGuida Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End Ad4DizionariGuida Page: method validate

//Ad4DizionariGuidaAction Tail @1-FCB6E20C
}
//End Ad4DizionariGuidaAction Tail

