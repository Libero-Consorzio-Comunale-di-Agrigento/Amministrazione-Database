//AdmRichiesteStampaAction imports @1-7C5384E6
package amvadm.AdmRichiesteStampa;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.codecharge.*;
import com.codecharge.events.*;
import com.codecharge.db.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
//End AdmRichiesteStampaAction imports

//AdmRichiesteStampaAction class @1-CA7650E8
public class AdmRichiesteStampaAction extends Action {

//End AdmRichiesteStampaAction class

//AdmRichiesteStampaAction: method perform @1-D317887A
    public String perform( HttpServletRequest req, HttpServletResponse resp, ServletContext context ) {
        String result = null;
        String redirect;
        this.req = req;
        this.resp = resp;
        this.context = context;
        page = new AdmRichiesteStampaModel( (CCSLocale) SessionStorage.getInstance( req ).getAttribute(Names.CCS_LOCALE_KEY));
        if (req.getAttribute(page.getName()+"Parent")==null) page.setCharacterEncoding("windows-1252");
        page.setResponse(resp);
        page.setRequest(req);
        res = ResourceBundle.getBundle("MessagesBundle", page.getCCSLocale().getLocale());
        req.setAttribute( "AdmRichiesteStampaModel", page );
        //e = new ActionEvent( page, page );
        if ( ! StringUtils.isEmpty( pageName ) ) page.setName( pageName );
        setProperties( page, Action.GET );
        setProperties( page, Action.POST );
        page.fireAfterInitializeEvent();
        if (! StringUtils.isEmpty(page.getRedirectString())) return page.getRedirectString();
//End AdmRichiesteStampaAction: method perform

//AdmRichiesteStampaAction: call children actions @1-B4930437
        if (result == null) {
            RICHIESTE_STAMPAClass RICHIESTE_STAMPA = new RICHIESTE_STAMPAClass();
            if ( ( redirect = RICHIESTE_STAMPA.perform( page.getEditableGrid("RICHIESTE_STAMPA")) ) != null ) result = redirect;
        }
        if (result==null) { result = page.getRedirectString(); }
        return result;
    }
//End AdmRichiesteStampaAction: call children actions

//RICHIESTE_STAMPA EditGrid @2-2C6CC330
    final class RICHIESTE_STAMPAClass {
        boolean valid = false;
        com.codecharge.components.EditableGrid model;
//End RICHIESTE_STAMPA EditGrid

//RICHIESTE_STAMPA EditGrid: method read: head @2-F8937F65
        boolean read() {
            boolean isErrors = false;
            if ( ! model.isAllowRead() ) return true;
            model.fireBeforeSelectEvent();
//End RICHIESTE_STAMPA EditGrid: method read: head

//RICHIESTE_STAMPA EditGrid: method read: init @2-B32AC26B
            if ( ! model.isAllowRead() ) return true;
            RICHIESTE_STAMPADataObject ds = new RICHIESTE_STAMPADataObject(page);
            ds.setComponent( model );
//End RICHIESTE_STAMPA EditGrid: method read: init

//RICHIESTE_STAMPA EditGrid: set where parameters @2-9B80383B
            ds.setSesModulo( SessionStorage.getInstance(req).getAttribute("Modulo") );
            ds.setSesIstanza( SessionStorage.getInstance(req).getAttribute("Istanza") );
            ds.setUrlSTATO( page.getHttpGetParams().getParameter("STATO") );
            ds.setUrlMOD( page.getHttpGetParams().getParameter("MOD") );
            ds.setUrlIST( page.getHttpGetParams().getParameter("IST") );
            ds.setSesUtente( SessionStorage.getInstance(req).getAttribute("Utente") );
            try {
                ds.setUrlMVAV( page.getHttpGetParams().getParameter("MVAV"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'MVAV'" );
                return false;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'MVAV'" );
                return false;
            }
            ds.setSesMVCONTEXT( SessionStorage.getInstance(req).getAttribute("MVCONTEXT") );
            ds.setSesAD4UTENTE( SessionStorage.getInstance(req).getAttribute("AD4UTENTE") );
            try {
                ds.setSesGroupID( SessionStorage.getInstance(req).getAttribute("GroupID"), page.getCCSLocale().getFormat("Integer") );
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid parameter 'GroupID'" );
                return false;
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter 'GroupID'" );
                return false;
            }
//End RICHIESTE_STAMPA EditGrid: set where parameters

//RICHIESTE_STAMPA EditGrid: set EditGrid properties @2-11C2D650
            
            ds.setPageNum( (model.getPage() < 1 ? 1 : model.getPage()) );
            ds.setPageSize( model.getFetchSize() );
//End RICHIESTE_STAMPA EditGrid: set EditGrid properties

//RICHIESTE_STAMPA EditGrid: retrieve data @2-012A0CD3
            ds.load();
            if ( ! ds.isEmpty() ) {
                bind(model, ds.getRows());
            }
            model.setCount( (int) ds.getAmountOfRows() );
            isErrors = ds.hasErrors();
            if ( isErrors ) model.addErrors( ds.getErrors() );
//End RICHIESTE_STAMPA EditGrid: retrieve data

//RICHIESTE_STAMPA EditGrid: method read: tail @2-F575E732
            return ( ! isErrors );
        }
//End RICHIESTE_STAMPA EditGrid: method read: tail

//RICHIESTE_STAMPA EditGrid: method perform @2-0C53946F
        protected String perform(com.codecharge.components.EditableGrid model) {
            this.model = model;
            setProperties( model, Action.GET );
            setProperties( model, Action.POST );
            setActivePermissions( model );
            if ( ! model.isVisible() ) { return null;}
            StringBuffer performReturnPage = new StringBuffer( "AdmRichieste" + Names.ACTION_SUFFIX );
            String queryStr = page.getHttpGetParams().toString( model.getExcludeParams() );
            performReturnPage.append( "?" + queryStr.toString() );
            page.setRedirectString( performReturnPage.toString() );
            model.processRows();
            if ( model.getName().equals(page.getHttpGetParams().getParameter("ccsForm")) ) {
                if (page.getParameter("Button_Submit") != null) {
                    if (validate()) {
                        Button_SubmitAction();
                        if ( !model.hasErrors()) return page.getRedirectString();
                    }
                }
            }
            read();
            page.setRedirectString(null);
            return page.getRedirectString();
        }
//End RICHIESTE_STAMPA EditGrid: method perform

//Button_Submit Button @12-FF395ECA
        void Button_SubmitAction() {
            com.codecharge.components.Button buttonModel = (com.codecharge.components.Button) model.getChild("Button_Submit");
            Vector excludeParams = new Vector(buttonModel.getExcludeParams());
            excludeParams.addAll(model.getExcludeParams());
            page.setRedirectString( "AdmRichieste" + Names.ACTION_SUFFIX + "?" + page.getHttpGetParams().toString( excludeParams ) );
            buttonModel.fireOnClickEvent();
            SubmitAction();
        }
//End Button_Submit Button

//RICHIESTE_STAMPA EditGrid: method SubmitAction head @2-C3C69DE3
        void SubmitAction() {
            if ( !( model.isAllowInsert() || model.isAllowUpdate() || model.isAllowDelete() ) ) return;
            model.fireBeforeSubmitEvent();
            if ( !( model.isAllowInsert() || model.isAllowUpdate() || model.isAllowDelete() ) ) return;
            Iterator rows = model.getChildRows().iterator();
            ArrayList dsRows = new ArrayList();
            while ( rows.hasNext() ) {
                HashMap row = (HashMap) rows.next();
                RICHIESTE_STAMPARow dsRow = new RICHIESTE_STAMPARow();
                if (row.get(Names.CCS_ROW_IS_NOT_APPLY_KEY) == null) {
                    com.codecharge.components.Model m = null;
                    m = (com.codecharge.components.Model) row.get("DATA");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setDATA(Utils.convertToString(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("REPORT");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setREPORT(Utils.convertToString(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("AUTORE");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setAUTORE(Utils.convertToString(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("ID_RICHIESTA");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setID_RICHIESTA(Utils.convertToString(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("NOTIFICATA");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setNOTIFICATA(Utils.convertToString(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("BEGIN_HIDE");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setBEGIN_HIDE(Utils.convertToString(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("NOTIFICATO");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setNOTIFICATO(Utils.convertToLong(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("STAMPA");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setSTAMPA(Utils.convertToLong(((com.codecharge.components.Control) m).getValue()));
                    }
                    m = (com.codecharge.components.Model) row.get("END_HIDE");
                    if ( m != null && m instanceof com.codecharge.components.Control) {
                        dsRow.setEND_HIDE(Utils.convertToString(((com.codecharge.components.Control) m).getValue()));
                    }
                    if (row.get(Names.CCS_CACHED_COLUMNS) != null) {
                        dsRow.setCCSCachedColumns((ArrayList) row.get(Names.CCS_CACHED_COLUMNS));
                    }
                    if (row.get(Names.CCS_ROW_IS_NEW_KEY) != null) {
                        dsRow.setNew(true);
                        if (!model.isAllowInsert()) dsRow = null;
                    } else if (row.get(Names.CCS_ROW_IS_DELETE_KEY) != null) {
                        dsRow.setDeleted(true);
                        if (!model.isAllowDelete()) dsRow = null;
                    } else {
                        if (!model.isAllowUpdate()) dsRow = null;
                    }
                    if ( dsRow != null ) dsRows.add(dsRow);
                } else {
                    dsRow.setApply(false);
                }
            }
            RICHIESTE_STAMPARow[] ds_Rows = new RICHIESTE_STAMPARow[dsRows.size()];
            ds_Rows = (RICHIESTE_STAMPARow[]) dsRows.toArray(ds_Rows);
            RICHIESTE_STAMPADataObject ds = new RICHIESTE_STAMPADataObject(page);
            ds.setComponent( model );
            ds.setRows(ds_Rows);
            try {
            } catch ( NumberFormatException nfe ) {
                model.addError( "Invalid parameter" );
            }
            if ( ds_Rows.length > 0 ) ds.updateGrid();
            boolean[] rowResults = ds.getRowResults();
            Collection[] rowErrors = ds.getRowErrors();
            model.initializeRows();
            int i = 0;
            while ( model.hasNextRow() ) {
                HashMap row = model.nextRow();
                if ( i >= ds_Rows.length ) break;
                if (rowResults[i]) {
                    if (ds_Rows[i].isDeleted()) {
                    } else {
                    }
                } else {
                    ErrorCollection errCollection = (ErrorCollection) row.get(Names.CCS_ROW_ERROR_KEY);
                    if ( errCollection == null ) {
                        errCollection = new ErrorCollection();
                        row.put(Names.CCS_ROW_ERROR_KEY, errCollection);
                    }
                    errCollection.addErrors(rowErrors[i]);
                    page.setRedirectString(null);
                }
                i++;
            }
            model.fireAfterSubmitEvent();
//End RICHIESTE_STAMPA EditGrid: method SubmitAction head

//RICHIESTE_STAMPA EditGrid: method SubmitAction tail @2-6221662A
            return;
        }
//End RICHIESTE_STAMPA EditGrid: method SubmitAction tail

//RICHIESTE_STAMPA EditGrid: method validate @2-278AC7E2
        boolean validate() {
            model.fireOnValidateEvent();
            boolean isValid = true;
            model.checkUnique();
            Iterator rows = model.getChildRows().iterator();
            while ( rows.hasNext() ) {
                HashMap row = (HashMap) rows.next();
                if (row.get(Names.CCS_ROW_IS_NOT_APPLY_KEY) == null && row.get(Names.CCS_ROW_IS_DELETE_KEY) == null) {
                    ErrorCollection rowErrors = (ErrorCollection) row.get(Names.CCS_ROW_ERROR_KEY);
                    if ( rowErrors == null ) {
                        rowErrors = new ErrorCollection();
                        row.put(Names.CCS_ROW_ERROR_KEY, rowErrors);
                    }
                    boolean isControlError = false;

                    com.codecharge.components.Hidden ID_RICHIESTA = (com.codecharge.components.Hidden) row.get( "ID_RICHIESTA" );
                    if (! ID_RICHIESTA.validate()) { isControlError = true; }
                    if (isControlError || rowErrors.hasErrors()) {
                        isValid = false;
                    }
                } // end if row is applied and is not deleted
            }
            this.valid = isValid;
            return isValid;
        }
//End RICHIESTE_STAMPA EditGrid: method validate

//RICHIESTE_STAMPA EditGrid: bind @2-C16EC4BE
        public void bind(com.codecharge.components.Component model, RICHIESTE_STAMPARow[] rows ) {
            if ( model == null || rows == null ) return;
            ArrayList listRows = (ArrayList) ((ArrayList) model.getChildRows()).clone();
            Iterator childRows = listRows.iterator();
            int counter = 0;
            RICHIESTE_STAMPARow row = null;
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

                c = (com.codecharge.components.Control) hashRow.get("DATA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("DATA").clone();
                    c.setValue(row.getDATA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("REPORT");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("REPORT").clone();
                    c.setValue(row.getREPORT());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("AUTORE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("AUTORE").clone();
                    c.setValue(row.getAUTORE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("ID_RICHIESTA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("ID_RICHIESTA").clone();
                    c.setValue(row.getID_RICHIESTA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOTIFICATA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOTIFICATA").clone();
                    c.setValue(row.getNOTIFICATA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("BEGIN_HIDE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("BEGIN_HIDE").clone();
                    c.setValue(row.getBEGIN_HIDE());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("NOTIFICATO");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("NOTIFICATO").clone();
                    c.setValue(row.getNOTIFICATO());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("STAMPA");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("STAMPA").clone();
                    c.setValue(row.getSTAMPA());
                    hashRow.put( c.getName(), c );
                }

                c = (com.codecharge.components.Control) hashRow.get("END_HIDE");
                if ( c == null ) { 
                    c = (com.codecharge.components.Control) model.getControl("END_HIDE").clone();
                    c.setValue(row.getEND_HIDE());
                    hashRow.put( c.getName(), c );
                }

                ArrayList pk = new ArrayList();
                {
                    DoubleField PK_field = new DoubleField("ID_CREDENZIALE", "ID_CREDENZIALE");
                    PK_field.setValue(row.getPrimaryKey().getID_CREDENZIALE());
                    pk.add(PK_field);
                }
                hashRow.put(Names.CCS_PK_KEY, pk);
                hashRow.put(Names.CCS_CACHED_COLUMNS, row.getCCSCachedColumns());
                if ( isNew ) {
                    model.addChildRow( hashRow );
                }
            } // end while
        }
//End RICHIESTE_STAMPA EditGrid: bind

//RICHIESTE_STAMPA EditGrid: getRowFieldByName @2-A7468DD8
        public Object getRowFieldByName( String name, RICHIESTE_STAMPARow row ) {
            Object value = null;
            if ( "DATA".equals(name) ) {
                value = row.getDATA();
            } else if ( "REPORT".equals(name) ) {
                value = row.getREPORT();
            } else if ( "AUTORE".equals(name) ) {
                value = row.getAUTORE();
            } else if ( "ID_RICHIESTA".equals(name) ) {
                value = row.getID_RICHIESTA();
            } else if ( "NOTIFICATA".equals(name) ) {
                value = row.getNOTIFICATA();
            } else if ( "BEGIN_HIDE".equals(name) ) {
                value = row.getBEGIN_HIDE();
            } else if ( "NOTIFICATO".equals(name) ) {
                value = row.getNOTIFICATO();
            } else if ( "STAMPA".equals(name) ) {
                value = row.getSTAMPA();
            } else if ( "END_HIDE".equals(name) ) {
                value = row.getEND_HIDE();
            } else if ( "LINK".equals(name) ) {
                value = row.getLINK();
            }
            return value;
        }
//End RICHIESTE_STAMPA EditGrid: getRowFieldByName

//RICHIESTE_STAMPA EditGrid Tail @2-FCB6E20C
    }
//End RICHIESTE_STAMPA EditGrid Tail

//AdmRichiesteStampa Page: method validate @1-104025BA
    boolean validate() {
        return true;
    }
//End AdmRichiesteStampa Page: method validate

//AdmRichiesteStampaAction Tail @1-FCB6E20C
}
//End AdmRichiesteStampaAction Tail

