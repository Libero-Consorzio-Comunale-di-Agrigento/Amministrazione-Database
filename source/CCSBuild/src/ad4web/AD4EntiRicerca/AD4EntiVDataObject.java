//AD4EntiV DataSource @142-A6B19382
package ad4web.AD4EntiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4EntiV DataSource

//class DataObject Header @142-D48552E4
public class AD4EntiVDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @142-8D2970AA
    

    TextField urlS_DESCRIZIONE = new TextField(null, null);
    

    private AD4EntiVRow[] rows = new AD4EntiVRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @142-448DD8DF

    public void  setUrlS_DESCRIZIONE( String param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param, Format ignore ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public AD4EntiVRow[] getRows() {
        return rows;
    }

    public void setRows(AD4EntiVRow[] rows) {
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

//constructor @142-B2EE0483
    public AD4EntiVDataObject(Page page) {
        super(page);
    }
//End constructor

//load @142-6B09F6C6
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT ente, "
                    + "       descrizione DESCRIZIONE, "
                    + "       AD4WEB.GET_DATI_ENTE(ENTE) DATI_ENTE "
                    + "  FROM ENTI "
                    + " WHERE '{s_DESCRIZIONE}' is null "
                    + "UNION "
                    + "SELECT ente, "
                    + "       descrizione DESCRIZIONE, "
                    + "       AD4WEB.GET_DATI_ENTE(ENTE) DATI_ENTE "
                    + "  FROM ENTI "
                    + " WHERE upper(ente) like UPPER('%{s_DESCRIZIONE}%') "
                    + "   or upper(DESCRIZIONE) like upper('%{s_DESCRIZIONE}%') "
                    + "   or upper(NVL(AD4_SOGGETTO.GET_NOME(SOGGETTO,'Y'),' ')) like UPPER('%{s_DESCRIZIONE}%') "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlS_DESCRIZIONE.getObjectValue() ) ) urlS_DESCRIZIONE.setValue( "" );
        command.addParameter( "s_DESCRIZIONE", urlS_DESCRIZIONE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT ente, descrizione DESCRIZIONE,  "
                                                         + "            AD4WEB.GET_DATI_ENTE(ENTE) DATI_ENTE FROM ENTI WHERE '{s_DESCRIZIONE}' is null UNION SELECT ente,  "
                                                         + "            descrizione DESCRIZIONE,  "
                                                         + "            AD4WEB.GET_DATI_ENTE(ENTE) DATI_ENTE FROM ENTI WHERE upper(ente) like UPPER('%{s_DESCRIZIONE}%') or upper(DESCRIZIONE) like upper('%{s_DESCRIZIONE}%') or upper(NVL(AD4_SOGGETTO.GET_NOME(SOGGETTO,'Y'),' ')) like UPPER('%{s_DESCRIZIONE}%')  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "1 ASC" );
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

//loadDataBind @142-D61B2FDC
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4EntiVRow row = new AD4EntiVRow();
                DbRow record = (DbRow) records.nextElement();
                row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
                row.setDATI_ENTE(Utils.convertToString(ds.parse(record.get("DATI_ENTE"), row.getDATI_ENTEField())));
                row.setENTE(Utils.convertToString(ds.parse(record.get("ENTE"), row.getENTEField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @142-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @142-ED1D1965
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_DESCRIZIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_DESCRIZIONE;
            } else if ( "s_DESCRIZIONE".equals(name) && prefix == null ) {
                param = urlS_DESCRIZIONE;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @142-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @142-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @142-238A81BB
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

//fireBeforeExecuteSelectEvent @142-9DA7B025
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

//fireAfterExecuteSelectEvent @142-F7E8A616
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

//class DataObject Tail @142-ED3F53A4
} // End of class DS
//End class DataObject Tail

