//TAB_FOLDER DataSource @186-2786B8F7
package amvadm.AdmRichieste;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End TAB_FOLDER DataSource

//class DataObject Header @186-981ADDAE
public class TAB_FOLDERDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @186-B5E79E16
    

    TextField urlNOTIFICATA = new TextField(null, null);
    
    TextField urlTIPO_NOTIFICA = new TextField(null, null);
    
    TextField urlMVAV = new TextField(null, null);
    
    TextField urlIST = new TextField(null, null);
    
    TextField urlMOD = new TextField(null, null);
    
    TextField urlSTATO = new TextField(null, null);
    

    private TAB_FOLDERRow[] rows = new TAB_FOLDERRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @186-727239DC

    public void  setUrlNOTIFICATA( String param ) {
        this.urlNOTIFICATA.setValue( param );
    }

    public void  setUrlNOTIFICATA( Object param ) {
        this.urlNOTIFICATA.setValue( param );
    }

    public void  setUrlNOTIFICATA( Object param, Format ignore ) {
        this.urlNOTIFICATA.setValue( param );
    }

    public void  setUrlTIPO_NOTIFICA( String param ) {
        this.urlTIPO_NOTIFICA.setValue( param );
    }

    public void  setUrlTIPO_NOTIFICA( Object param ) {
        this.urlTIPO_NOTIFICA.setValue( param );
    }

    public void  setUrlTIPO_NOTIFICA( Object param, Format ignore ) {
        this.urlTIPO_NOTIFICA.setValue( param );
    }

    public void  setUrlMVAV( String param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( Object param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( Object param, Format ignore ) {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlIST( String param ) {
        this.urlIST.setValue( param );
    }

    public void  setUrlIST( Object param ) {
        this.urlIST.setValue( param );
    }

    public void  setUrlIST( Object param, Format ignore ) {
        this.urlIST.setValue( param );
    }

    public void  setUrlMOD( String param ) {
        this.urlMOD.setValue( param );
    }

    public void  setUrlMOD( Object param ) {
        this.urlMOD.setValue( param );
    }

    public void  setUrlMOD( Object param, Format ignore ) {
        this.urlMOD.setValue( param );
    }

    public void  setUrlSTATO( String param ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlSTATO( Object param ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlSTATO( Object param, Format ignore ) {
        this.urlSTATO.setValue( param );
    }

    public TAB_FOLDERRow[] getRows() {
        return rows;
    }

    public void setRows(TAB_FOLDERRow[] rows) {
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

//constructor @186-1E72AA6D
    public TAB_FOLDERDataObject(Page page) {
        super(page);
    }
//End constructor

//load @186-7BF0CF1E
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select amv_utente.tab_folder_richieste( "
                    + "        '{NOTIFICATA}' "
                    + "       ,'{TIPO_NOTIFICA}' "
                    + "       ,'{MVAV}' "
                    + "       ,'{MOD}' "
                    + "       ,'{IST}' "
                    + "       ,'{STATO}') tab "
                    + "  from dual "
                    + "where '{IST}' is not null "
                    + "  and '{MOD}' is not null "
                    + "  and to_number('{MVAV}') = 2" );
        if ( StringUtils.isEmpty( (String) urlNOTIFICATA.getObjectValue() ) ) urlNOTIFICATA.setValue( "" );
        command.addParameter( "NOTIFICATA", urlNOTIFICATA, null );
        if ( StringUtils.isEmpty( (String) urlTIPO_NOTIFICA.getObjectValue() ) ) urlTIPO_NOTIFICA.setValue( "" );
        command.addParameter( "TIPO_NOTIFICA", urlTIPO_NOTIFICA, null );
        if ( StringUtils.isEmpty( (String) urlMVAV.getObjectValue() ) ) urlMVAV.setValue( "" );
        command.addParameter( "MVAV", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) urlIST.getObjectValue() ) ) urlIST.setValue( "" );
        command.addParameter( "IST", urlIST, null );
        if ( StringUtils.isEmpty( (String) urlMOD.getObjectValue() ) ) urlMOD.setValue( "" );
        command.addParameter( "MOD", urlMOD, null );
        if ( StringUtils.isEmpty( (String) urlSTATO.getObjectValue() ) ) urlSTATO.setValue( "" );
        command.addParameter( "STATO", urlSTATO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select amv_utente.tab_folder_richieste( '{NOTIFICATA}' ,'{TIPO_NOTIFICA}' ,'{MVAV}' ,'{MOD}' ,'{IST}' ,'{STATO}') tab from dual where '{IST}' is not null and '{MOD}' is not null and to_number('{MVAV}') = 2 ) cnt " );
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

//loadDataBind @186-742E49E2
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                TAB_FOLDERRow row = new TAB_FOLDERRow();
                DbRow record = (DbRow) records.nextElement();
                row.setTAB(Utils.convertToString(ds.parse(record.get("TAB"), row.getTABField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @186-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @186-B8D2B397
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "NOTIFICATA".equals(name) && "url".equals(prefix) ) {
                param = urlNOTIFICATA;
            } else if ( "NOTIFICATA".equals(name) && prefix == null ) {
                param = urlNOTIFICATA;
            }
            if ( "TIPO_NOTIFICA".equals(name) && "url".equals(prefix) ) {
                param = urlTIPO_NOTIFICA;
            } else if ( "TIPO_NOTIFICA".equals(name) && prefix == null ) {
                param = urlTIPO_NOTIFICA;
            }
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            if ( "IST".equals(name) && "url".equals(prefix) ) {
                param = urlIST;
            } else if ( "IST".equals(name) && prefix == null ) {
                param = urlIST;
            }
            if ( "MOD".equals(name) && "url".equals(prefix) ) {
                param = urlMOD;
            } else if ( "MOD".equals(name) && prefix == null ) {
                param = urlMOD;
            }
            if ( "STATO".equals(name) && "url".equals(prefix) ) {
                param = urlSTATO;
            } else if ( "STATO".equals(name) && prefix == null ) {
                param = urlSTATO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @186-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @186-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @186-238A81BB
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

//fireBeforeExecuteSelectEvent @186-9DA7B025
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

//fireAfterExecuteSelectEvent @186-F7E8A616
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

//class DataObject Tail @186-ED3F53A4
} // End of class DS
//End class DataObject Tail

