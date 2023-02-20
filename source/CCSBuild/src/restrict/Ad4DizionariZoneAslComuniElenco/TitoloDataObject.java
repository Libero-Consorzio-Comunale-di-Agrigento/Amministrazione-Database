//Titolo DataSource @40-7929C798
package restrict.Ad4DizionariZoneAslComuniElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End Titolo DataSource

//class DataObject Header @40-99BC3D7C
public class TitoloDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @40-1D0DE6DA
    

    TextField urlID_ZONA_ASL = new TextField(null, null);
    

    private TitoloRow[] rows = new TitoloRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @40-02D8898C

    public void  setUrlID_ZONA_ASL( String param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( Object param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( Object param, Format ignore ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public TitoloRow[] getRows() {
        return rows;
    }

    public void setRows(TitoloRow[] rows) {
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

//constructor @40-D7C9BBE7
    public TitoloDataObject(Page page) {
        super(page);
    }
//End constructor

//load @40-7B001D6C
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select  'Elenco Comuni Zona ' "
                    + "      ||ad4_ccs.get_zona_asl_titolo('{ID_ZONA_ASL}') titolo "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlID_ZONA_ASL.getObjectValue() ) ) urlID_ZONA_ASL.setValue( "" );
        command.addParameter( "ID_ZONA_ASL", urlID_ZONA_ASL, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select 'Elenco Comuni Zona ' ||ad4_ccs.get_zona_asl_titolo('{ID_ZONA_ASL}') titolo from dual ) cnt " );
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

//loadDataBind @40-252F82A2
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                TitoloRow row = new TitoloRow();
                DbRow record = (DbRow) records.nextElement();
                row.setTITOLO(Utils.convertToString(ds.parse(record.get("TITOLO"), row.getTITOLOField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @40-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @40-C4094F16
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_ZONA_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlID_ZONA_ASL;
            } else if ( "ID_ZONA_ASL".equals(name) && prefix == null ) {
                param = urlID_ZONA_ASL;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @40-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @40-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @40-238A81BB
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

//fireBeforeExecuteSelectEvent @40-9DA7B025
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

//fireAfterExecuteSelectEvent @40-F7E8A616
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

//class DataObject Tail @40-ED3F53A4
} // End of class DS
//End class DataObject Tail

