//AD4GuidaIstanzaAction imports @1-8E7E82FA
package ad4web.AD4GuidaIstanza;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4GuidaIstanzaAction imports

//AD4GuidaIstanzaAction class @1-E56EC840
public class AD4GuidaIstanzaAction extends Action {

//End AD4GuidaIstanzaAction class

//AD4GuidaIstanzaAction: method perform @1-E6D0F3C6
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4GuidaIstanzaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4GuidaIstanzaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4GuidaIstanzaAction: method perform

//AD4GuidaIstanzaAction: call children actions @1-B4EA81D6
        if (result == null) {
            NewGrid1Class NewGrid1 = new NewGrid1Class();
            NewGrid1.perform(page.getGrid("NewGrid1"));
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AD4GuidaIstanzaAction: call children actions

//NewGrid1 Grid @2-6A1F7436
    final class NewGrid1Class {
        com.codecharge.components.Grid model;
        Event e;
//End NewGrid1 Grid

//NewGrid1 Grid: method perform @2-B48879D3
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
//End NewGrid1 Grid: method perform

//NewGrid1 Grid: method read: head @2-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End NewGrid1 Grid: method read: head

//NewGrid1 Grid: method read: init @2-2EA3AAF9
            if ( ! model.allowRead ) return true;
            NewGrid1DataObject ds = new NewGrid1DataObject(page);
            ds.setComponent( model );
//End NewGrid1 Grid: method read: init

//NewGrid1 Grid: set where parameters @2-E1218BC8
            ds.setUrlSEL( page.getHttpGetParams().getParameter("SEL") );
            ds.setUrlISTANZA( page.getHttpGetParams().getParameter("ISTANZA") );
            ds.setUrlPROGETTO( page.getHttpGetParams().getParameter("PROGETTO") );
            ds.setSesAD4PROGETTO( SessionStorage.getInstance(req).getAttribute("AD4PROGETTO") );
//End NewGrid1 Grid: set where parameters

//NewGrid1 Grid: set grid properties @2-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End NewGrid1 Grid: set grid properties

//NewGrid1 Grid: retrieve data @2-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End NewGrid1 Grid: retrieve data

//NewGrid1 Grid: check errors @2-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End NewGrid1 Grid: check errors

//NewGrid1 Grid: method read: tail @2-F575E732
            return ( ! isErrors );
        }
//End NewGrid1 Grid: method read: tail

//NewGrid1 Grid: method bind @2-F83CF351
        public void bind(com.codecharge.components.Component model, NewGrid1Row[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            NewGrid1Row row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("FOLDER");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("FOLDER").clone();
                    c.setValue(row.getFOLDER());
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End NewGrid1 Grid: method bind

//NewGrid1 Directory: getRowFieldByName @2-66AB0C16
        public Object getRowFieldByName( String name, NewGrid1Row row ) {
            Object value = null;
            if ( "FOLDER".equals(name) ) {
                value = row.getFOLDER();
            }
            return value;
        }
//End NewGrid1 Directory: getRowFieldByName

//NewGrid1 Grid: method validate @2-104025BA
        boolean validate() {
            return true;
        }
//End NewGrid1 Grid: method validate

//NewGrid1 Grid Tail @2-FCB6E20C
    }
//End NewGrid1 Grid Tail

//AD4GuidaIstanza Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4GuidaIstanza Page: method validate

//AD4GuidaIstanzaAction Tail @1-FCB6E20C
}
//End AD4GuidaIstanzaAction Tail

