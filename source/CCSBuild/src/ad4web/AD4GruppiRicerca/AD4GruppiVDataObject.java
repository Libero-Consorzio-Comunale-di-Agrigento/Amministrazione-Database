//AD4GruppiV DataSource @142-40C40D5E
package ad4web.AD4GruppiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4GruppiV DataSource

//class DataObject Header @142-D517F906
public class AD4GruppiVDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @142-C7890A8B
    

    TextField urlS_DESCRIZIONE = new TextField(null, null);
    
    TextField urlS_UTENTE = new TextField(null, null);
    

    private AD4GruppiVRow[] rows = new AD4GruppiVRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @142-14FD3025

    public void  setUrlS_DESCRIZIONE( String param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param, Format ignore ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_UTENTE( String param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param, Format ignore ) {
        this.urlS_UTENTE.setValue( param );
    }

    public AD4GruppiVRow[] getRows() {
        return rows;
    }

    public void setRows(AD4GruppiVRow[] rows) {
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

//constructor @142-93885704
    public AD4GruppiVDataObject(Page page) {
        super(page);
    }
//End constructor

//load @142-A83A36D8
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT tipo_utente, "
                    + "       utente gruppo,  "
                    + "       NOMINATIVO descrizione,  "
                    + "NOTE "
                    + "  FROM UTENTI "
                    + " WHERE (upper(UTENTE) like upper('%{s_DESCRIZIONE}%') "
                    + "   or upper(NOMINATIVO) like upper('%{s_DESCRIZIONE}%')) "
                    + "   and tipo_utente <> 'U' "
                    + "   and utente <> 'ric_abil' "
                    + "   AND ('{s_UTENTE}' is null "
                    + "        or exists (select 1 "
                    + "                     from utenti_gruppo utgr "
                    + "                    where utgr.utente = '{s_UTENTE}' "
                    + "                      and utgr.gruppo = UTENTI.UTENTE) "
                    + "       ) "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlS_DESCRIZIONE.getObjectValue() ) ) urlS_DESCRIZIONE.setValue( "" );
        command.addParameter( "s_DESCRIZIONE", urlS_DESCRIZIONE, null );
        if ( StringUtils.isEmpty( (String) urlS_UTENTE.getObjectValue() ) ) urlS_UTENTE.setValue( "" );
        command.addParameter( "s_UTENTE", urlS_UTENTE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT tipo_utente, utente gruppo, NOMINATIVO descrizione,  "
                                                         + "            NOTE FROM UTENTI WHERE (upper(UTENTE) like upper('%{s_DESCRIZIONE}%') or upper(NOMINATIVO) like upper('%{s_DESCRIZIONE}%')) and tipo_utente <> 'U' and utente <> 'ric_abil' AND ('{s_UTENTE}' is null or exists (select 1 from utenti_gruppo utgr where utgr.utente = '{s_UTENTE}' and utgr.gruppo = UTENTI.UTENTE) )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "1,2" );
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

//loadDataBind @142-CFEEB5B4
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4GruppiVRow row = new AD4GruppiVRow();
                DbRow record = (DbRow) records.nextElement();
                row.setGRUPPO(Utils.convertToString(ds.parse(record.get("GRUPPO"), row.getGRUPPOField())));
                row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
                row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
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

//getParameterByName @142-0B00883B
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_DESCRIZIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_DESCRIZIONE;
            } else if ( "s_DESCRIZIONE".equals(name) && prefix == null ) {
                param = urlS_DESCRIZIONE;
            }
            if ( "s_UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_UTENTE;
            } else if ( "s_UTENTE".equals(name) && prefix == null ) {
                param = urlS_UTENTE;
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

