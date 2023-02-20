//PROGETTO DataSource @160-259B7B96
package ad4web.AD4ServElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End PROGETTO DataSource

//class DataObject Header @160-AE49147A
public class PROGETTODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @160-0A22AE47
    

    TextField urlPROGETTO = new TextField(null, null);
    
    TextField sesAD4PROGETTO = new TextField(null, null);
    

    private PROGETTORow[] rows = new PROGETTORow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @160-5960B6AF

    public void  setUrlPROGETTO( String param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param, Format ignore ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( String param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param, Format ignore ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public PROGETTORow[] getRows() {
        return rows;
    }

    public void setRows(PROGETTORow[] rows) {
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

//constructor @160-C8FF531D
    public PROGETTODataObject(Page page) {
        super(page);
    }
//End constructor

//load @160-B2254EEE
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT PROG.PROGETTO PROGETTO, "
                    + "       'Servizi del Progetto '||PROG.DESCRIZIONE DESC_PROGETTO "
                    + "  FROM PROGETTI PROG "
                    + " WHERE PROG.PROGETTO = decode('{PROGETTO}',null,'{AD4PROGETTO}','{PROGETTO}') "
                    + "UNION "
                    + "SELECT '' PROGETTO, "
                    + "       '<font color=\"#ff0000\">Selezionare un progetto prima di accedere ai suoi Servizi!</font>' DESC_ "
                    + "  FROM DUAL "
                    + " WHERE '{PROGETTO}' IS NULL "
                    + "   AND '{AD4PROGETTO}' IS NULL" );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT PROG.PROGETTO PROGETTO,  "
                                                         + "            'Servizi del Progetto '||PROG.DESCRIZIONE DESC_PROGETTO FROM PROGETTI PROG WHERE PROG.PROGETTO = decode('{PROGETTO}',null,'{AD4PROGETTO}','{PROGETTO}') UNION SELECT '' PROGETTO, '<font color=\"#ff0000\">Selezionare un progetto prima di accedere ai suoi Servizi!</font>' DESC_ FROM DUAL WHERE '{PROGETTO}' IS NULL AND '{AD4PROGETTO}' IS NULL ) cnt " );
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

//loadDataBind @160-C4855798
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                PROGETTORow row = new PROGETTORow();
                DbRow record = (DbRow) records.nextElement();
                row.setDESC_PROGETTO(Utils.convertToString(ds.parse(record.get("DESC_PROGETTO"), row.getDESC_PROGETTOField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @160-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @160-677903B3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "AD4PROGETTO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PROGETTO;
            } else if ( "AD4PROGETTO".equals(name) && prefix == null ) {
                param = sesAD4PROGETTO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @160-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @160-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @160-238A81BB
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

//fireBeforeExecuteSelectEvent @160-9DA7B025
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

//fireAfterExecuteSelectEvent @160-F7E8A616
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

//class DataObject Tail @160-ED3F53A4
} // End of class DS
//End class DataObject Tail

