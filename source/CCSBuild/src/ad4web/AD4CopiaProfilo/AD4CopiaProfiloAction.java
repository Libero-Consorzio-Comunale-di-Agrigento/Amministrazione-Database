//AD4CopiaProfiloAction imports @1-CC17A12D
package ad4web.AD4CopiaProfilo;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AD4CopiaProfiloAction imports

//AD4CopiaProfiloAction class @1-E5DB0627
public class AD4CopiaProfiloAction extends Action {

//End AD4CopiaProfiloAction class

//AD4CopiaProfiloAction: method perform @1-895A201A
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AD4CopiaProfiloModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AD4CopiaProfiloModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AD4CopiaProfiloAction: method perform

//AD4CopiaProfiloAction: call children actions @1-7835741F
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
            AD4_UTENTE_ALIMENTAREClass AD4_UTENTE_ALIMENTARE = new AD4_UTENTE_ALIMENTAREClass();
            if ( ( redirect = AD4_UTENTE_ALIMENTARE.perform( page.getRecord("AD4_UTENTE_ALIMENTARE")) ) != null ) result = redirect;
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
//End AD4CopiaProfiloAction: call children actions

//AD4_UTENTE_ALIMENTARE Record @6-EC9592D9
    final class AD4_UTENTE_ALIMENTAREClass {
        com.codecharge.components.Record model;
        boolean valid = true;
        boolean action = false;
        Vector errors = new Vector();
        //ActionEvent e;

//End AD4_UTENTE_ALIMENTARE Record

//AD4_UTENTE_ALIMENTARE Record: method perform @6-0FAB408D
        String perform(com.codecharge.components.Record model) {
            //e = new ActionEvent( model, page );
            this.model = model;
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AD4DiacElenco" + Names.ACTION_SUFFIX );
            performReturnPage.append( "?" + page.getHttpGetParams().toString( model.getExcludeParams() ) );
            page.setRedirectString( performReturnPage.toString() );
            if ( ! model.isAllowUpdate() ) model.getChild( "Button_Update" ).setVisible( false );
//End AD4_UTENTE_ALIMENTARE Record: method perform

//AD4_UTENTE_ALIMENTARE Record: children actions @6-60530F50
            String formName = page.getHttpGetParams().getParameterAsString("ccsForm");
            int mode = formName.indexOf(":Edit");
            if (mode != -1) {
                formName = formName.substring(0, mode);
            }
            model.setMode(com.codecharge.components.Record.INSERT_MODE);
            if ("AD4_UTENTE_ALIMENTARE".equals(formName)) {
                setProperties(model, Action.POST);
                if (mode != -1) { // Update mode
                    model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                    if (page.getParameter("Button_Update") != null) {
                        if (validate()) {
                            Button_UpdateAction();
                            if ( !model.hasErrors()) return page.getRedirectString();
                        }
                    }
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                } else { // Insert mode
                    model.setMode(com.codecharge.components.Record.INSERT_MODE);
                    if (page.getParameter("Button_Cancel") != null) {
                        Button_CancelAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                }
            }
            setProperties(model, Action.GET, true );
            read();
//End AD4_UTENTE_ALIMENTARE Record: children actions

//AD4_UTENTE_ALIMENTARE Record: method perform tail @6-71EF9E38
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End AD4_UTENTE_ALIMENTARE Record: method perform tail

//Button_Update Button @111-C49A13F6
        void Button_UpdateAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Update");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4DiacElenco" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            UpdateAction();
        }
//End Button_Update Button

//Button_Cancel Button @99-32D5D53E
        void Button_CancelAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Cancel");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AD4DiacElenco" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
        }
//End Button_Cancel Button

void read() { //AD4_UTENTE_ALIMENTARE Record: method read @6-7F8AAE5A

//AD4_UTENTE_ALIMENTARE Record: method read head @6-DB74C422
            if (!model.isAllowRead()) return;
            model.fireBeforeSelectEvent();
//End AD4_UTENTE_ALIMENTARE Record: method read head

//AD4_UTENTE_ALIMENTARE Record: init DataSource @6-2D96E01D
            if (!model.isAllowRead()) return;
            boolean isErrors = false;
            AD4_UTENTE_ALIMENTAREDataObject ds = new AD4_UTENTE_ALIMENTAREDataObject(page);
            ds.setComponent( model );
            ds.setUrlUTENTE_ALIMENTARE( page.getHttpGetParams().getParameter("UTENTE_ALIMENTARE") );
            ds.setUrlUTENTE_ORIGINE( page.getHttpGetParams().getParameter("UTENTE_ORIGINE") );
            ds.load();
            if ( ds.isEmpty() ) {
                model.setMode(com.codecharge.components.Record.INSERT_MODE);
            } else {
                model.setMode(com.codecharge.components.Record.UPDATE_MODE);
                bind( model, ds.getRow() );
            }
//End AD4_UTENTE_ALIMENTARE Record: init DataSource

//AD4_UTENTE_ALIMENTARE Record: check errors @6-5C658222
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
            }
//End AD4_UTENTE_ALIMENTARE Record: check errors

} //AD4_UTENTE_ALIMENTARE Record: method read tail @6-FCB6E20C

//AD4_UTENTE_ALIMENTARE Record: bind @6-E2A162B5
            public void bind(com.codecharge.components.Component model, AD4_UTENTE_ALIMENTARERow row ) {
                if ( model == null || row == null ) return;
                model.getControl("UTENTE_ALIMENTARE").setValue(row.getUTENTE_ALIMENTARE());
                model.getControl("UTENTE_ORIGINE").setValue(row.getUTENTE_ORIGINE());
                model.getControl("NOMINATIVO_ALIMENTARE").setValue(row.getNOMINATIVO_ALIMENTARE());
                model.getControl("NOMINATIVO_ORIGINE").setValue(row.getNOMINATIVO_ORIGINE());
                model.getControl("DATI_UTENTE_ALIMENTARE").setValue(row.getDATI_UTENTE_ALIMENTARE());
                model.getControl("DATI_UTENTE_ORIGINE").setValue(row.getDATI_UTENTE_ORIGINE());
                model.getControl("GRUPPI_DIAC_ALIMENTARE").setValue(row.getGRUPPI_DIAC_ALIMENTARE());
                model.getControl("GRUPPI_DIAC_ORIGINE").setValue(row.getGRUPPI_DIAC_ORIGINE());
                model.getControl("DIAC_ALIMENTARE").setValue(row.getDIAC_ALIMENTARE());
                model.getControl("DIAC_ORIGINE").setValue(row.getDIAC_ORIGINE());
                model.getControl("CAAC_ALIMENTARE").setValue(row.getCAAC_ALIMENTARE());
                model.getControl("CAAC_ORIGINE").setValue(row.getCAAC_ORIGINE());
                if ( this.valid ) {
                    model.getControl("COPIA_GRUPPI").setValue(row.getCOPIA_GRUPPI());
                    model.getControl("COPIA_DIAC").setValue(row.getCOPIA_DIAC());
                    model.getControl("COPIA_CAAC").setValue(row.getCOPIA_CAAC());
                }
            }
//End AD4_UTENTE_ALIMENTARE Record: bind

//AD4_UTENTE_ALIMENTARE Record: getRowFieldByName @6-29A2BDC4
            public Object getRowFieldByName( String name, AD4_UTENTE_ALIMENTARERow row ) {
                Object value = null;
                if ( "UTENTE_ALIMENTARE".equals(name) ) {
                    value = row.getUTENTE_ALIMENTARE();
                } else if ( "UTENTE_ORIGINE".equals(name) ) {
                    value = row.getUTENTE_ORIGINE();
                } else if ( "NOMINATIVO_ALIMENTARE".equals(name) ) {
                    value = row.getNOMINATIVO_ALIMENTARE();
                } else if ( "NOMINATIVO_ORIGINE".equals(name) ) {
                    value = row.getNOMINATIVO_ORIGINE();
                } else if ( "DATI_UTENTE_ALIMENTARE".equals(name) ) {
                    value = row.getDATI_UTENTE_ALIMENTARE();
                } else if ( "DATI_UTENTE_ORIGINE".equals(name) ) {
                    value = row.getDATI_UTENTE_ORIGINE();
                } else if ( "GRUPPI_DIAC_ALIMENTARE".equals(name) ) {
                    value = row.getGRUPPI_DIAC_ALIMENTARE();
                } else if ( "COPIA_GRUPPI".equals(name) ) {
                    value = row.getCOPIA_GRUPPI();
                } else if ( "GRUPPI_DIAC_ORIGINE".equals(name) ) {
                    value = row.getGRUPPI_DIAC_ORIGINE();
                } else if ( "DIAC_ALIMENTARE".equals(name) ) {
                    value = row.getDIAC_ALIMENTARE();
                } else if ( "COPIA_DIAC".equals(name) ) {
                    value = row.getCOPIA_DIAC();
                } else if ( "DIAC_ORIGINE".equals(name) ) {
                    value = row.getDIAC_ORIGINE();
                } else if ( "CAAC_ALIMENTARE".equals(name) ) {
                    value = row.getCAAC_ALIMENTARE();
                } else if ( "COPIA_CAAC".equals(name) ) {
                    value = row.getCOPIA_CAAC();
                } else if ( "CAAC_ORIGINE".equals(name) ) {
                    value = row.getCAAC_ORIGINE();
                }
                return value;
            }
//End AD4_UTENTE_ALIMENTARE Record: getRowFieldByName

void InsertAction() { //AD4_UTENTE_ALIMENTARE Record: method insert @6-11643485

//AD4_UTENTE_ALIMENTARE Record: components insert actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4_UTENTE_ALIMENTARE Record: components insert actions

} //AD4_UTENTE_ALIMENTARE Record: method insert tail @6-FCB6E20C

void UpdateAction() { //AD4_UTENTE_ALIMENTARE Record: method update @6-5771D0AA

//AD4_UTENTE_ALIMENTARE Record: method update head @6-AA7E708E
            if (!model.isAllowUpdate()) return;
            model.fireBeforeUpdateEvent();
//End AD4_UTENTE_ALIMENTARE Record: method update head

//AD4_UTENTE_ALIMENTARE Record: method update body @6-F038AA4F
            if (!model.isAllowUpdate()) return;
            boolean isErrors = false;
            AD4_UTENTE_ALIMENTAREDataObject ds = new AD4_UTENTE_ALIMENTAREDataObject(page);
            ds.setComponent( model );
            AD4_UTENTE_ALIMENTARERow row = new AD4_UTENTE_ALIMENTARERow();
            ds.setRow(row);
            ds.setUrlUTENTE_ALIMENTARE( page.getHttpGetParams().getParameter("UTENTE_ALIMENTARE") );
            ds.setUrlUTENTE_ORIGINE( page.getHttpGetParams().getParameter("UTENTE_ORIGINE") );
            row.setCOPIA_GRUPPI(Utils.convertToString(model.getControl("COPIA_GRUPPI").getValue()));
            row.setCOPIA_DIAC(Utils.convertToString(model.getControl("COPIA_DIAC").getValue()));
            row.setCOPIA_CAAC(Utils.convertToString(model.getControl("COPIA_CAAC").getValue()));
            ds.setSes( SessionStorage.getInstance(req).getAttribute("") );
//End AD4_UTENTE_ALIMENTARE Record: method update body

//AD4_UTENTE_ALIMENTARE Record: ds.update @6-6E956EDC
            ds.update();
            if ( ! ds.hasErrors() ) {
            }
            model.fireAfterUpdateEvent();
            action = true;
            isErrors = ds.hasErrors();
            if ( isErrors ) {
                model.addErrors( ds.getErrors() );
                page.setRedirectString( null );
                this.valid = false;
            }
//End AD4_UTENTE_ALIMENTARE Record: ds.update

} //AD4_UTENTE_ALIMENTARE Record: method update tail @6-FCB6E20C

void DeleteAction() { //AD4_UTENTE_ALIMENTARE Record: method delete @6-11FC2E1E

//AD4_UTENTE_ALIMENTARE Record: components delete actions @6-68525650
            if (! model.hasErrors()) {
            }
//End AD4_UTENTE_ALIMENTARE Record: components delete actions

} //AD4_UTENTE_ALIMENTARE Record: method delete tail @6-FCB6E20C

//AD4_UTENTE_ALIMENTARE Record: method validate @6-A8FFD717
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isControlError = false;
            this.valid = ( ! (model.hasErrors() || isControlError) );
            return ( ! (model.hasErrors() || isControlError) );
        }
//End AD4_UTENTE_ALIMENTARE Record: method validate

//AD4_UTENTE_ALIMENTARE Record Tail @6-FCB6E20C
    }
//End AD4_UTENTE_ALIMENTARE Record Tail

//AD4CopiaProfilo Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AD4CopiaProfilo Page: method validate

//AD4CopiaProfiloAction Tail @1-FCB6E20C
}
//End AD4CopiaProfiloAction Tail

