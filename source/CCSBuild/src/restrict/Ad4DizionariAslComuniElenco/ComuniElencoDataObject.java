//ComuniElenco DataSource @8-FB04851B
package restrict.Ad4DizionariAslComuniElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ComuniElenco DataSource

//class DataObject Header @8-F404B0BA
public class ComuniElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-A4ECEED7
    

    TextField urlS_COMUNE = new TextField(null, null);
    
    TextField urlREGIONE_ASL = new TextField(null, null);
    
    TextField urlCODICE_ASL = new TextField(null, null);
    

    private ComuniElencoRow[] rows = new ComuniElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-A71317D2

    public void  setUrlS_COMUNE( String param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param, Format ignore ) {
        this.urlS_COMUNE.setValue( param );
    }

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

    public ComuniElencoRow[] getRows() {
        return rows;
    }

    public void setRows(ComuniElencoRow[] rows) {
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

//constructor @8-326755EE
    public ComuniElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-E54A0EA6
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select provincia "
                    + "      ,comune  "
                    + "      ,ad4_ccs.get_comune_denominazione(provincia,comune)      comune_desc "
                    + "      ,regione_asl "
                    + "      ,codice_asl "
                    + "      ,utente_aggiornamento "
                    + "      ,ad4_ccs.format_data_trunc(data_aggiornamento)               data_aggiornamento "
                    + "  from ad4_asl_comune "
                    + " where regione_asl = '{REGIONE_ASL}' "
                    + "   and codice_asl = '{CODICE_ASL}' "
                    + "   and ad4_ccs.get_comune_denominazione(provincia,comune) like upper('%'||'{s_COMUNE}')||'%' "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_COMUNE.getObjectValue() ) ) urlS_COMUNE.setValue( "" );
        command.addParameter( "s_COMUNE", urlS_COMUNE, null );
        if ( StringUtils.isEmpty( (String) urlREGIONE_ASL.getObjectValue() ) ) urlREGIONE_ASL.setValue( "" );
        command.addParameter( "REGIONE_ASL", urlREGIONE_ASL, null );
        if ( StringUtils.isEmpty( (String) urlCODICE_ASL.getObjectValue() ) ) urlCODICE_ASL.setValue( "" );
        command.addParameter( "CODICE_ASL", urlCODICE_ASL, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select provincia ,comune ,ad4_ccs.get_comune_denominazione(provincia,comune) comune_desc ,regione_asl ,codice_asl ,utente_aggiornamento ,ad4_ccs.format_data_trunc(data_aggiornamento) data_aggiornamento from ad4_asl_comune where regione_asl = '{REGIONE_ASL}' and codice_asl = '{CODICE_ASL}' and ad4_ccs.get_comune_denominazione(provincia,comune) like upper('%'||'{s_COMUNE}')||'%'  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "comune_desc" );
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

//loadDataBind @8-7BA9CE68
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ComuniElencoRow row = new ComuniElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setCOMUNE_DESC(Utils.convertToString(ds.parse(record.get("COMUNE_DESC"), row.getCOMUNE_DESCField())));
                row.setUTENTE_AGGIORNAMENTO(Utils.convertToString(ds.parse(record.get("UTENTE_AGGIORNAMENTO"), row.getUTENTE_AGGIORNAMENTOField())));
                row.setDATA_AGGIORNAMENTO(Utils.convertToString(ds.parse(record.get("DATA_AGGIORNAMENTO"), row.getDATA_AGGIORNAMENTOField())));
                row.setCOMUNE(Utils.convertToString(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
                row.setPROVINCIA(Utils.convertToString(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
                row.setREGIONE_ASL(Utils.convertToString(ds.parse(record.get("REGIONE_ASL"), row.getREGIONE_ASLField())));
                row.setCODICE_ASL(Utils.convertToString(ds.parse(record.get("CODICE_ASL"), row.getCODICE_ASLField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @8-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @8-4CD581D8
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlS_COMUNE;
            } else if ( "s_COMUNE".equals(name) && prefix == null ) {
                param = urlS_COMUNE;
            }
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

//addGridDataObjectListener @8-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @8-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @8-238A81BB
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

//fireBeforeExecuteSelectEvent @8-9DA7B025
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

//fireAfterExecuteSelectEvent @8-F7E8A616
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

//class DataObject Tail @8-ED3F53A4
} // End of class DS
//End class DataObject Tail

