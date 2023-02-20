//AD4_AMBIENTE DataSource @6-789D00C3
package ad4web.AD4Ambiente;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_AMBIENTE DataSource

//class DataObject Header @6-4744D1DE
public class AD4_AMBIENTEDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-D115D8B3
    

    TextField urlPROGETTO = new TextField(null, null);
    
    TextField urlISTANZA = new TextField(null, null);
    
    TextField urlSERVIZIO = new TextField(null, null);
    
    TextField urlMODULO = new TextField(null, null);
    

    private AD4_AMBIENTERow[] rows = new AD4_AMBIENTERow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @6-AE7FE650

    public void  setUrlPROGETTO( String param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param, Format ignore ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlISTANZA( String param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param, Format ignore ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlSERVIZIO( String param ) {
        this.urlSERVIZIO.setValue( param );
    }

    public void  setUrlSERVIZIO( Object param ) {
        this.urlSERVIZIO.setValue( param );
    }

    public void  setUrlSERVIZIO( Object param, Format ignore ) {
        this.urlSERVIZIO.setValue( param );
    }

    public void  setUrlMODULO( String param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param, Format ignore ) {
        this.urlMODULO.setValue( param );
    }

    public AD4_AMBIENTERow[] getRows() {
        return rows;
    }

    public void setRows(AD4_AMBIENTERow[] rows) {
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

//constructor @6-8279F975
    public AD4_AMBIENTEDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-F7381AF2
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select ad4web.GET_SITUAZIONE_AMBIENTE('{PROGETTI}','{ISTANZE}','{MODULI}','{SERVIZI}') AMBIENTE "
                    + "      ,registro_utility.leggi_stringa('PRODUCTS/AUTHENTICATION','ExternalAutentication',0) EXTERNAL_AUTENTICATION "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTI", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZE", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlSERVIZIO.getObjectValue() ) ) urlSERVIZIO.setValue( "" );
        command.addParameter( "SERVIZI", urlSERVIZIO, null );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULI", urlMODULO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select ad4web.GET_SITUAZIONE_AMBIENTE('{PROGETTI}','{ISTANZE}','{MODULI}','{SERVIZI}') AMBIENTE ,registro_utility.leggi_stringa('PRODUCTS/AUTHENTICATION','ExternalAutentication',0) EXTERNAL_AUTENTICATION from dual ) cnt " );
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

//loadDataBind @6-A4D1434A
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_AMBIENTERow row = new AD4_AMBIENTERow();
                DbRow record = (DbRow) records.nextElement();
                row.setAMBIENTE(Utils.convertToString(ds.parse(record.get("AMBIENTE"), row.getAMBIENTEField())));
                row.setEXTERNAL_AUTENTICATION(Utils.convertToString(ds.parse(record.get("EXTERNAL_AUTENTICATION"), row.getEXTERNAL_AUTENTICATIONField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @6-F24AE883
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "SERVIZIO".equals(name) && "url".equals(prefix) ) {
                param = urlSERVIZIO;
            } else if ( "SERVIZIO".equals(name) && prefix == null ) {
                param = urlSERVIZIO;
            }
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @6-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @6-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @6-238A81BB
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

//fireBeforeExecuteSelectEvent @6-9DA7B025
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

//fireAfterExecuteSelectEvent @6-F7E8A616
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

//class DataObject Tail @6-ED3F53A4
} // End of class DS
//End class DataObject Tail

