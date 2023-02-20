//UTENTI DataSource @80-86B84BDC
package ad4web.AD4GruppoMod;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End UTENTI DataSource

//class DataObject Header @80-847567AB
public class UTENTIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @80-EA781898
    

    TextField urlGRUPPO = new TextField(null, null);
    

    private UTENTIRow[] rows = new UTENTIRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @80-3D96EB78

    public void  setUrlGRUPPO( String param ) {
        this.urlGRUPPO.setValue( param );
    }

    public void  setUrlGRUPPO( Object param ) {
        this.urlGRUPPO.setValue( param );
    }

    public void  setUrlGRUPPO( Object param, Format ignore ) {
        this.urlGRUPPO.setValue( param );
    }

    public UTENTIRow[] getRows() {
        return rows;
    }

    public void setRows(UTENTIRow[] rows) {
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

//constructor @80-8DC60F42
    public UTENTIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @80-C9F209AA
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '\"'||nominativo||'\"' nominativo, "
                    + "       UTENTE GRUPPO "
                    + "FROM UTENTI "
                    + "WHERE TIPO_UTENTE = 'G' "
                    + "  AND UTENTE = '{GRUPPO}'" );
        if ( StringUtils.isEmpty( (String) urlGRUPPO.getObjectValue() ) ) urlGRUPPO.setValue( "" );
        command.addParameter( "GRUPPO", urlGRUPPO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT '\"'||nominativo||'\"' nominativo,  "
                                                         + "            UTENTE GRUPPO FROM UTENTI WHERE TIPO_UTENTE = 'G' AND UTENTE = '{GRUPPO}' ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
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

//loadDataBind @80-270A65FD
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                UTENTIRow row = new UTENTIRow();
                DbRow record = (DbRow) records.nextElement();
                row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
                row.setGRUPPO(Utils.convertToString(ds.parse(record.get("GRUPPO"), row.getGRUPPOField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @80-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @80-3CE3ACED
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "GRUPPO".equals(name) && "url".equals(prefix) ) {
                param = urlGRUPPO;
            } else if ( "GRUPPO".equals(name) && prefix == null ) {
                param = urlGRUPPO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @80-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @80-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @80-238A81BB
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

//fireBeforeExecuteSelectEvent @80-9DA7B025
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

//fireAfterExecuteSelectEvent @80-F7E8A616
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

//class DataObject Tail @80-ED3F53A4
} // End of class DS
//End class DataObject Tail

