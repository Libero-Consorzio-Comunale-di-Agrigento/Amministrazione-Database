//TITOLO DataSource @31-9698737B
package ad4web.AD4AccessiTree;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End TITOLO DataSource

//class DataObject Header @31-CAF63659
public class TITOLODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @31-F17B7C49
    

    TextField urlUTENTE = new TextField(null, null);
    

    private TITOLORow[] rows = new TITOLORow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @31-2F56014C

    public void  setUrlUTENTE( String param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param, Format ignore ) {
        this.urlUTENTE.setValue( param );
    }

    public TITOLORow[] getRows() {
        return rows;
    }

    public void setRows(TITOLORow[] rows) {
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

//constructor @31-42CFF645
    public TITOLODataObject(Page page) {
        super(page);
    }
//End constructor

//load @31-F2563D46
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT uten.nominativo||' ('||uten.utente||')' utente "
                    + "  FROM utenti uten "
                    + " WHERE uten.utente(+) = '{UTENTE}'" );
        if ( StringUtils.isEmpty( (String) urlUTENTE.getObjectValue() ) ) urlUTENTE.setValue( "" );
        command.addParameter( "UTENTE", urlUTENTE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT uten.nominativo||' ('||uten.utente||')' utente FROM utenti uten WHERE uten.utente(+) = '{UTENTE}' ) cnt " );
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

//loadDataBind @31-3A315402
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                TITOLORow row = new TITOLORow();
                DbRow record = (DbRow) records.nextElement();
                row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @31-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @31-4DB03AB1
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE;
            } else if ( "UTENTE".equals(name) && prefix == null ) {
                param = urlUTENTE;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @31-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @31-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @31-238A81BB
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

//fireBeforeExecuteSelectEvent @31-9DA7B025
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

//fireAfterExecuteSelectEvent @31-F7E8A616
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

//class DataObject Tail @31-ED3F53A4
} // End of class DS
//End class DataObject Tail

