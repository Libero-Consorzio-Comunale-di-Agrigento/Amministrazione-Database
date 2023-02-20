//AD4DiacElencoAction imports @1-5EA343BA
package ad4web.AD4DiacElenco;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4DiacElencoAction imports

//AD4DiacElencoAction class @1-3226F448
public class AD4DiacElencoAction extends Action {

//End AD4DiacElencoAction class

//AD4DiacElencoAction: method perform @1-756E7FF9
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4DiacElencoModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4DiacElencoModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4DiacElencoAction: method perform

//AD4DiacElencoAction: call children actions @1-39F999F0
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
            RegioniFiltroClass RegioniFiltro = new RegioniFiltroClass();
            if ( ( redirect = RegioniFiltro.perform( page.getRecord("RegioniFiltro")) ) != null ) result = redirect;
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
//End AD4DiacElencoAction: call children actions

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

//RegioniFiltro Record @169-FF5875CE
    final class RegioniFiltroClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End RegioniFiltro Record

//RegioniFiltro Record: method perform @169-65A7DEFC
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( page.getActionPageName() + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
//End RegioniFiltro Record: method perform

//RegioniFiltro Record: children actions @169-CF5786B3
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("RegioniFiltro".equals(formName)) {
                setProperties(model, Action.POST);
                    if (validate()) {
                        DoSearchSearchAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
            }
            setProperties(model, Action.GET, true );
            read();
//End RegioniFiltro Record: children actions

//RegioniFiltro Record: method perform tail @169-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End RegioniFiltro Record: method perform tail

//DoSearch Button @172-7F387837
        void DoSearchSearchAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("DoSearch");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            for ( Iterator params = model.getChildren().iterator(); params.hasNext(); ) {
                excludeParams.add( ((com.codecharge.components.Model) params.next()).getName() );
            }
            page.setRedirectString( page.getActionPageName() + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) + "&" + page.getHttpPostParams().toString( buttonModel.getExcludeParams() ) );
            buttonModel.fireOnClickEvent();
        }
//End DoSearch Button

void read() { //RegioniFiltro Record: method read @169-7F8AAE5A

//RegioniFiltro Record: method read head @169-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End RegioniFiltro Record: method read head

//RegioniFiltro Record: init DataSource @169-9EDF9246
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            RegioniFiltroDataObject ds = new RegioniFiltroDataObject(page);
            ds.setComponent( model );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End RegioniFiltro Record: init DataSource

//RegioniFiltro Record: check errors @169-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End RegioniFiltro Record: check errors

} //RegioniFiltro Record: method read tail @169-FCB6E20C

//RegioniFiltro Record: bind @169-843A2048
            public void bind(com.codecharge.components.Component model, RegioniFiltroRow row ) {
                if ( model == null || row == null ) return;
                model.getControl("FILTER_SEARCH").setValue(row.getFILTER_SEARCH());
                if ( this.valid ) {
                    model.getControl("s_MODULO").setValue(row.getS_MODULO());
                    model.getControl("s_ISTANZA").setValue(row.getS_ISTANZA());
                }
            }
//End RegioniFiltro Record: bind

//RegioniFiltro Record: getRowFieldByName @169-4D1E2D89
            public Object getRowFieldByName( String name, RegioniFiltroRow row ) {
                Object value = null;
                if ( "FILTER_SEARCH".equals(name) ) {
                    value = row.getFILTER_SEARCH();
                } else if ( "s_MODULO".equals(name) ) {
                    value = row.getS_MODULO();
                } else if ( "s_ISTANZA".equals(name) ) {
                    value = row.getS_ISTANZA();
                }
                return value;
            }
//End RegioniFiltro Record: getRowFieldByName

void InsertAction() { //RegioniFiltro Record: method insert @169-11643485

//RegioniFiltro Record: components insert actions @169-68525650
            if (! model.hasErrors()) {
            }
//End RegioniFiltro Record: components insert actions

} //RegioniFiltro Record: method insert tail @169-FCB6E20C

void UpdateAction() { //RegioniFiltro Record: method update @169-5771D0AA

//RegioniFiltro Record: components update actions @169-68525650
            if (! model.hasErrors()) {
            }
//End RegioniFiltro Record: components update actions

} //RegioniFiltro Record: method update tail @169-FCB6E20C

void DeleteAction() { //RegioniFiltro Record: method delete @169-11FC2E1E

//RegioniFiltro Record: components delete actions @169-68525650
            if (! model.hasErrors()) {
            }
//End RegioniFiltro Record: components delete actions

} //RegioniFiltro Record: method delete tail @169-FCB6E20C

//RegioniFiltro Record: method validate @169-3B8035B1
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;

            com.codecharge.components.TextBox s_MODULO = (com.codecharge.components.TextBox) model.getChild( "s_MODULO" );
            if (! s_MODULO.validate()) { isControlError = true; }

            com.codecharge.components.TextBox s_ISTANZA = (com.codecharge.components.TextBox) model.getChild( "s_ISTANZA" );
            if (! s_ISTANZA.validate()) { isControlError = true; }
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End RegioniFiltro Record: method validate

//RegioniFiltro Record Tail @169-FCB6E20C
    }
//End RegioniFiltro Record Tail

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

//AD4_DIRITTI_ACCESSO Grid: set where parameters @6-5A79DD46
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            ds.setSesAD4GRUPPO( SessionStorage.getInstance(req).getAttribute("AD4GRUPPO") );
            ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV") );
            ds.setUrlS_MODULO( page.getHttpGetParams().getParameter("s_MODULO") );
            ds.setUrlS_ISTANZA( page.getHttpGetParams().getParameter("s_ISTANZA") );
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

//AD4DiacElenco Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4DiacElenco Page: method validate

//AD4DiacElencoAction Tail @1-FCB6E20C
}
//End AD4DiacElencoAction Tail
