//Titolo DataSource @40-FB04851B
package restrict.Ad4DizionariAslComuniElenco;

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

//attributes of DataObject @40-9F9B0D76
    

    TextField urlREGIONE_ASL = new TextField(null, null);
    
    TextField urlCODICE_ASL = new TextField(null, null);
    

    private TitoloRow[] rows = new TitoloRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @40-6F8BC440

    public void  setUrlREGIONE_ASL( String param ) {
        this.urlREGIONE_ASL.setValue( param );
    }

    public void  setUrlREGIONE_ASL( Object param ) {
        this.urlREGIONE_ASL.setValue( param );
    }

    public void  setUrlREGIONE_ASL( Object param, Format ignore ) {
        this.urlREGIONE_ASL.setValue( param );
    }

    public void  setUrlCODICE_ASL( String param ) {
        this.urlCODICE_ASL.setValue( param );
    }

    public void  setUrlCODICE_ASL( Object param ) {
        this.urlCODICE_ASL.setValue( param );
    }

    public void  setUrlCODICE_ASL( Object param, Format ignore ) {
        this.urlCODICE_ASL.setValue( param );
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

//load @40-D5AA056E
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select  'Elenco Comuni Asl ' "
                    + "      ||ad4_ccs.get_asl_descrizione('{REGIONE_ASL}','{CODICE_ASL}') titolo "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlREGIONE_ASL.getObjectValue() ) ) urlREGIONE_ASL.setValue( "" );
        command.addParameter( "REGIONE_ASL", urlREGIONE_ASL, null );
        if ( StringUtils.isEmpty( (String) urlCODICE_ASL.getObjectValue() ) ) urlCODICE_ASL.setValue( "" );
        command.addParameter( "CODICE_ASL", urlCODICE_ASL, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select 'Elenco Comuni Asl ' ||ad4_ccs.get_asl_descrizione('{REGIONE_ASL}','{CODICE_ASL}') titolo from dual ) cnt " );
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

//getParameterByName @40-2DC96646
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "REGIONE_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlREGIONE_ASL;
            } else if ( "REGIONE_ASL".equals(name) && prefix == null ) {
                param = urlREGIONE_ASL;
            }
            if ( "CODICE_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlCODICE_ASL;
            } else if ( "CODICE_ASL".equals(name) && prefix == null ) {
                param = urlCODICE_ASL;
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

