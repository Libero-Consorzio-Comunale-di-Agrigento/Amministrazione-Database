//AD4DiacElencoSTEAction imports @1-4544193C
package common.AD4DiacElencoSTE;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4DiacElencoSTEAction imports

//AD4DiacElencoSTEAction class @1-946C83B2
public class AD4DiacElencoSTEAction extends Action {

//End AD4DiacElencoSTEAction class

//AD4DiacElencoSTEAction: method perform @1-A03A2D88
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4DiacElencoSTEModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4DiacElencoSTEModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4DiacElencoSTEAction: method perform

//AD4DiacElencoSTEAction: call children actions @1-82C31BDB
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
            DIRITTI_ACCESSOClass DIRITTI_ACCESSO = new DIRITTI_ACCESSOClass();
            DIRITTI_ACCESSO.perform(page.getGrid("DIRITTI_ACCESSO"));
        }
        if (result == null) {
            AD4_DIRITTI_ACCESSOClass AD4_DIRITTI_ACCESSO = new AD4_DIRITTI_ACCESSOClass();
            AD4_DIRITTI_ACCESSO.perform(page.getGrid("AD4_DIRITTI_ACCESSO"));
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
//End AD4DiacElencoSTEAction: call children actions

//DIRITTI_ACCESSO Grid @146-C8A51662
    final class DIRITTI_ACCESSOClass {
        com.codecharge.components.Grid model;
        Event e;
//End DIRITTI_ACCESSO Grid

//DIRITTI_ACCESSO Grid: method perform @146-B48879D3
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
//End DIRITTI_ACCESSO Grid: method perform

//DIRITTI_ACCESSO Grid: method read: head @146-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End DIRITTI_ACCESSO Grid: method read: head

//DIRITTI_ACCESSO Grid: method read: init @146-21027308
            if ( ! model.allowRead ) return true;
            DIRITTI_ACCESSODataObject ds = new DIRITTI_ACCESSODataObject(page);
            ds.setComponent( model );
//End DIRITTI_ACCESSO Grid: method read: init

//DIRITTI_ACCESSO Grid: set where parameters @146-B7309692
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV") );
            ds.setSesAD4GRUPPO( SessionStorage.getInstance(req).getAttribute("AD4GRUPPO") );
//End DIRITTI_ACCESSO Grid: set where parameters

//DIRITTI_ACCESSO Grid: set grid properties @146-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End DIRITTI_ACCESSO Grid: set grid properties

//DIRITTI_ACCESSO Grid: retrieve data @146-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End DIRITTI_ACCESSO Grid: retrieve data

//DIRITTI_ACCESSO Grid: check errors @146-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End DIRITTI_ACCESSO Grid: check errors

//DIRITTI_ACCESSO Grid: method read: tail @146-F575E732
            return ( ! isErrors );
        }
//End DIRITTI_ACCESSO Grid: method read: tail

//DIRITTI_ACCESSO Grid: method bind @146-0DB9DA65
        public void bind(com.codecharge.components.Component model, DIRITTI_ACCESSORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            DIRITTI_ACCESSORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("Nuovo");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Nuovo").clone();
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Copia");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Copia").clone();
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("Unifica");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("Unifica").clone();
                    hashRow.put( c.getName(), c );
                }

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End DIRITTI_ACCESSO Grid: method bind

//DIRITTI_ACCESSO Directory: getRowFieldByName @146-06843322
        public Object getRowFieldByName( String name, DIRITTI_ACCESSORow row ) {
            Object value = null;
            if ( "NOMINATIVO".equals(name) ) {
                value = row.getNOMINATIVO();
            } else if ( "Nuovo".equals(name) ) {
                value = row.getNuovo();
            } else if ( "Copia".equals(name) ) {
                value = row.getCopia();
            } else if ( "Unifica".equals(name) ) {
                value = row.getUnifica();
            }
            return value;
        }
//End DIRITTI_ACCESSO Directory: getRowFieldByName

//DIRITTI_ACCESSO Grid: method validate @146-104025BA
        boolean validate() {
            return true;
        }
//End DIRITTI_ACCESSO Grid: method validate

//DIRITTI_ACCESSO Grid Tail @146-FCB6E20C
    }
//End DIRITTI_ACCESSO Grid Tail

//AD4_DIRITTI_ACCESSO Grid @6-7B165BF1
    final class AD4_DIRITTI_ACCESSOClass {
        com.codecharge.components.Grid model;
        Event e;
//End AD4_DIRITTI_ACCESSO Grid

//AD4_DIRITTI_ACCESSO Grid: method perform @6-B48879D3
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
//End AD4_DIRITTI_ACCESSO Grid: method perform

//AD4_DIRITTI_ACCESSO Grid: method read: head @6-A74AB037
        boolean read() {
            boolean isErrors = false;
            if ( ! model.allowRead ) return true;
            model.fireBeforeSelectEvent();
//End AD4_DIRITTI_ACCESSO Grid: method read: head

//AD4_DIRITTI_ACCESSO Grid: method read: init @6-1CB22FA3
            if ( ! model.allowRead ) return true;
            AD4_DIRITTI_ACCESSODataObject ds = new AD4_DIRITTI_ACCESSODataObject(page);
            ds.setComponent( model );
//End AD4_DIRITTI_ACCESSO Grid: method read: init

//AD4_DIRITTI_ACCESSO Grid: set where parameters @6-CBFEA74B
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setSesAD4GRUPPO( SessionStorage.getInstance(req).getAttribute("AD4GRUPPO") );
            ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV") );
//End AD4_DIRITTI_ACCESSO Grid: set where parameters

//AD4_DIRITTI_ACCESSO Grid: set grid properties @6-6674B46B
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
            Hashtable sortAscColumns = new Hashtable();
            Hashtable sortDescColumns = new Hashtable();
            sortAscColumns.put( "Sorter_SEQUENZA", "SEQUENZA" );
            sortAscColumns.put( "Sorter_MODULO", "MODULO" );
            sortAscColumns.put( "Sorter_ISTANZA", "ISTANZA" );
            sortAscColumns.put( "Sorter_DATI", "DATI" );
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
//End AD4_DIRITTI_ACCESSO Grid: set grid properties

//AD4_DIRITTI_ACCESSO Grid: retrieve data @6-FE4E4327
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind( model, ds.getRows());
            }
            model.setAmountOfRows( ds.getAmountOfRows() );
//End AD4_DIRITTI_ACCESSO Grid: retrieve data

//AD4_DIRITTI_ACCESSO Grid: check errors @6-B2B22DDA
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End AD4_DIRITTI_ACCESSO Grid: check errors

//AD4_DIRITTI_ACCESSO Grid: method read: tail @6-F575E732
            return ( ! isErrors );
        }
//End AD4_DIRITTI_ACCESSO Grid: method read: tail

//AD4_DIRITTI_ACCESSO Grid: method bind @6-B6895B2D
        public void bind(com.codecharge.components.Component model, AD4_DIRITTI_ACCESSORow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            AD4_DIRITTI_ACCESSORow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("SEQUENZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("SEQUENZA").clone();
                    c.setValue(row.getSEQUENZA());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("MODULO").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ISTANZA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("UTENTE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("UTENTE").clone();
                    c.setValue(row.getUTENTE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("DES_MODULO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_MODULO").clone();
                    c.setValue(row.getDES_MODULO());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("MODULO").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ISTANZA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DES_ISTANZA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DES_ISTANZA").clone();
                    c.setValue(row.getDES_ISTANZA());
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.Link) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("MODULO").getSourceName(), row ));
                ((com.codecharge.components.Link) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.Link) c).getParameter("ISTANZA").getSourceName(), row ));

                c = (com.codecharge.components.Control) hashRow.get("DATI");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATI").clone();
                    c.setValue(row.getDATI());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("CaratteristicheAccesso");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("CaratteristicheAccesso").clone();
                    hashRow.put( c.getName(), c );
                }
                ((com.codecharge.components.ImageLink) c).getParameter("TIPO_ACCESSO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("TIPO_ACCESSO").getSourceName(), row ));
                ((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("PROGETTO").getSourceName(), row ));
                ((com.codecharge.components.ImageLink) c).getParameter("MODULO").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("MODULO").getSourceName(), row ));
                ((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("ISTANZA").getSourceName(), row ));
                ((com.codecharge.components.ImageLink) c).getParameter("UTENTE").setValue( getRowFieldByName(((com.codecharge.components.ImageLink) c).getParameter("UTENTE").getSourceName(), row ));

                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End AD4_DIRITTI_ACCESSO Grid: method bind

//AD4_DIRITTI_ACCESSO Directory: getRowFieldByName @6-82C809A1
        public Object getRowFieldByName( String name, AD4_DIRITTI_ACCESSORow row ) {
            Object value = null;
            if ( "SEQUENZA".equals(name) ) {
                value = row.getSEQUENZA();
            } else if ( "UTENTE".equals(name) ) {
                value = row.getUTENTE();
            } else if ( "DES_MODULO".equals(name) ) {
                value = row.getDES_MODULO();
            } else if ( "DES_ISTANZA".equals(name) ) {
                value = row.getDES_ISTANZA();
            } else if ( "DATI".equals(name) ) {
                value = row.getDATI();
            } else if ( "CaratteristicheAccesso".equals(name) ) {
                value = row.getCaratteristicheAccesso();
            } else if ( "AFCNavigator".equals(name) ) {
                value = row.getAFCNavigator();
            } else if ( "MODULO".equals(name) ) {
                value = row.getMODULO();
            } else if ( "ISTANZA".equals(name) ) {
                value = row.getISTANZA();
            } else if ( "TIPO_ACCESSO".equals(name) ) {
                value = row.getTIPO_ACCESSO();
            } else if ( "PROGETTO".equals(name) ) {
                value = row.getPROGETTO();
            }
            return value;
        }
//End AD4_DIRITTI_ACCESSO Directory: getRowFieldByName

//AD4_DIRITTI_ACCESSO Grid: method validate @6-104025BA
        boolean validate() {
            return true;
        }
//End AD4_DIRITTI_ACCESSO Grid: method validate

//AD4_DIRITTI_ACCESSO Grid Tail @6-FCB6E20C
    }
//End AD4_DIRITTI_ACCESSO Grid Tail

//AD4DiacElencoSTE Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4DiacElencoSTE Page: method validate

//AD4DiacElencoSTEAction Tail @1-FCB6E20C
}
//End AD4DiacElencoSTEAction Tail
