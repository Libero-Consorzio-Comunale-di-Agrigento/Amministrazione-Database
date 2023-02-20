//TITLEGrid DataSource @148-2786B8F7
package amvadm.AdmRichieste;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End TITLEGrid DataSource

//class DataObject Header @148-9EFF7082
public class TITLEGridDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @148-AEB28182
    

    LongField urlMVAV = new LongField(null, null);
    
    TextField sesAD4UTENTE = new TextField(null, null);
    

    private TITLEGridRow[] rows = new TITLEGridRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @148-69715D1E

    public void  setUrlMVAV( long param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( long param, Format ignore ) throws java.text.ParseException {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( Object param, Format format ) throws java.text.ParseException {
        this.urlMVAV.setValue( param, format );
    }

    public void  setUrlMVAV( Long param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setSesAD4UTENTE( String param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param, Format ignore ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public TITLEGridRow[] getRows() {
        return rows;
    }

    public void setRows(TITLEGridRow[] rows) {
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

//constructor @148-4627693B
    public TITLEGridDataObject(Page page) {
        super(page);
    }
//End constructor

//load @148-75D860CD
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT decode('{MVAV}', 1, 'FALLITE da VERIFICARE', 2,'APPROVATE', 3, 'RESPINTE',  "
                    + "4, 'TUTTE', 5,  "
                    + "DECODE('{UTENTE}','','','utente '||AD4_UTENTE.GET_NOMINATIVO('{UTENTE}','Y')), 'INOLTRATE in CARICO') stato "
                    + "FROM dual" );
        if ( urlMVAV.getObjectValue() == null ) urlMVAV.setValue( 0 );
        command.addParameter( "MVAV", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
        command.addParameter( "UTENTE", sesAD4UTENTE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT decode('{MVAV}', 1, 'FALLITE da VERIFICARE', 2,'APPROVATE', 3, 'RESPINTE',  "
                                                         + "            4, 'TUTTE', 5,  "
                                                         + "            DECODE('{UTENTE}','','','utente '||AD4_UTENTE.GET_NOMINATIVO('{UTENTE}','Y')), 'INOLTRATE in CARICO') stato FROM dual ) cnt " );
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

//loadDataBind @148-A3E9189D
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                TITLEGridRow row = new TITLEGridRow();
                DbRow record = (DbRow) records.nextElement();
                row.setSTATO(Utils.convertToString(ds.parse(record.get("STATO"), row.getSTATOField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @148-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @148-7D0B075D
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            if ( "AD4UTENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4UTENTE;
            } else if ( "AD4UTENTE".equals(name) && prefix == null ) {
                param = sesAD4UTENTE;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @148-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @148-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @148-238A81BB
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

//fireBeforeExecuteSelectEvent @148-9DA7B025
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

//fireAfterExecuteSelectEvent @148-F7E8A616
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

//class DataObject Tail @148-ED3F53A4
} // End of class DS
//End class DataObject Tail

