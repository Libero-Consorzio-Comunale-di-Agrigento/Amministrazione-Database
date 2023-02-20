//UTENTI_GRUPPO DataSource @78-71F1F9F5
package ad4web.AD4Gruppo;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End UTENTI_GRUPPO DataSource

//class DataObject Header @78-089FBA0B
public class UTENTI_GRUPPODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @78-54A64CD9
    

    TextField urlGRUPPO = new TextField(null, null);
    
    TextField sesAD4GRUPPO = new TextField(null, null);
    
    TextField urlSE_NUOVO = new TextField(null, null);
    

    private UTENTI_GRUPPORow[] rows = new UTENTI_GRUPPORow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @78-A8B49274

    public void  setUrlGRUPPO( String param ) {
        this.urlGRUPPO.setValue( param );
    }

    public void  setUrlGRUPPO( Object param ) {
        this.urlGRUPPO.setValue( param );
    }

    public void  setUrlGRUPPO( Object param, Format ignore ) {
        this.urlGRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( String param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param, Format ignore ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setUrlSE_NUOVO( String param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param, Format ignore ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public UTENTI_GRUPPORow[] getRows() {
        return rows;
    }

    public void setRows(UTENTI_GRUPPORow[] rows) {
        this.rows = rows;
    }

    public void setPageNum( int pageNum ) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

//End properties of DataObject

//constructor @78-8E2E17E3
    public UTENTI_GRUPPODataObject(Page page) {
        super(page);
    }
//End constructor

//load @78-CAB93027
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '<img src=\"../common/images/AD4/'||DECODE(tipo_utente,'U','Uten','Gruppo')||'.gif\" BORDER=0><a CLASS=\"AFCDataLink\" href=\"'||DECODE(tipo_utente,'U','AD4Utente.do?UTENTE=','AD4Gruppo.do?GRUPPO=')||utgr.Utente||'\">'||' '||utgr.Utente||' - '||nominativo||DECODE(tipo_utente,'U','',' ('||tipo_utente||')')||'</a>' nominativo, "
                    + "       Ad4web.CHECK_DIAC_CAAC_UTEN_GRUPPO(utgr.Utente,utgr.Gruppo) check_diac_caac "
                    + "  FROM UTENTI uten,  "
                    + "UTENTI_GRUPPO utgr "
                    + " WHERE utgr.Utente = uten.Utente "
                    + "   AND utgr.Gruppo = NVL('{GRUPPO}','{AD4GRUPPO}') "
                    + "   AND NVL('{SE_NUOVO}','N') <> 'Y' "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlGRUPPO.getObjectValue() ) ) urlGRUPPO.setValue( "" );
        command.addParameter( "GRUPPO", urlGRUPPO, null );
        if ( StringUtils.isEmpty( (String) sesAD4GRUPPO.getObjectValue() ) ) sesAD4GRUPPO.setValue( "" );
        command.addParameter( "AD4GRUPPO", sesAD4GRUPPO, null );
        if ( StringUtils.isEmpty( (String) urlSE_NUOVO.getObjectValue() ) ) urlSE_NUOVO.setValue( "" );
        command.addParameter( "SE_NUOVO", urlSE_NUOVO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT '<img src=\"../common/images/AD4/'||DECODE(tipo_utente,'U','Uten','Gruppo')||'.gif\" BORDER=0><a CLASS=\"AFCDataLink\" href=\"'||DECODE(tipo_utente,'U','AD4Utente.do?UTENTE=','AD4Gruppo.do?GRUPPO=')||utgr.Utente||'\">'||' '||utgr.Utente||' - '||nominativo||DECODE(tipo_utente,'U','',' ('||tipo_utente||')')||'</a>' nominativo,  "
                                                         + "            Ad4web.CHECK_DIAC_CAAC_UTEN_GRUPPO(utgr.Utente,utgr.Gruppo) check_diac_caac FROM UTENTI uten, UTENTI_GRUPPO utgr WHERE utgr.Utente = uten.Utente AND utgr.Gruppo = NVL('{GRUPPO}','{AD4GRUPPO}') AND NVL('{SE_NUOVO}','N') <> 'Y'  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "1" );
        }
        command.setStartPos( ( pageNum - 1 ) * pageSize + 1 );
        command.setFetchSize( pageSize );

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        if ( ! StringUtils.isEmpty( command.getCountSql() ) ) {
            if ( ! ds.hasErrors() ) {
                amountOfRows = command.count();
                CCLogger.getInstance().debug(command.toString());
            }
        }
        Enumeration records = null;
        if ( ! ds.hasErrors() ) {
            records = command.getRows();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @78-5F01F187
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                UTENTI_GRUPPORow row = new UTENTI_GRUPPORow();
                DbRow record = (DbRow) records.nextElement();
                row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
                row.setCHECK_DIAC_CAAC(Utils.convertToString(ds.parse(record.get("CHECK_DIAC_CAAC"), row.getCHECK_DIAC_CAACField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @78-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @78-31069BBB
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "GRUPPO".equals(name) && "url".equals(prefix) ) {
                param = urlGRUPPO;
            } else if ( "GRUPPO".equals(name) && prefix == null ) {
                param = urlGRUPPO;
            }
            if ( "AD4GRUPPO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4GRUPPO;
            } else if ( "AD4GRUPPO".equals(name) && prefix == null ) {
                param = sesAD4GRUPPO;
            }
            if ( "SE_NUOVO".equals(name) && "url".equals(prefix) ) {
                param = urlSE_NUOVO;
            } else if ( "SE_NUOVO".equals(name) && prefix == null ) {
                param = urlSE_NUOVO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @78-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @78-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @78-238A81BB
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource(this);
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @78-9DA7B025
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @78-F7E8A616
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//class DataObject Tail @78-ED3F53A4
} // End of class DS
//End class DataObject Tail

