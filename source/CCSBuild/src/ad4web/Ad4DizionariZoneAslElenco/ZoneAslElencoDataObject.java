//ZoneAslElenco DataSource @8-B6474048
package ad4web.Ad4DizionariZoneAslElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ZoneAslElenco DataSource

//class DataObject Header @8-8D7D572B
public class ZoneAslElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-1FA7B769
    

    TextField urlS_REGIONE = new TextField(null, null);
    
    TextField urlS_ZONA_ASL = new TextField(null, null);
    

    private ZoneAslElencoRow[] rows = new ZoneAslElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-FDCF4E10

    public void  setUrlS_REGIONE( String param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param, Format ignore ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_ZONA_ASL( String param ) {
        this.urlS_ZONA_ASL.setValue( param );
    }

    public void  setUrlS_ZONA_ASL( Object param ) {
        this.urlS_ZONA_ASL.setValue( param );
    }

    public void  setUrlS_ZONA_ASL( Object param, Format ignore ) {
        this.urlS_ZONA_ASL.setValue( param );
    }

    public ZoneAslElencoRow[] getRows() {
        return rows;
    }

    public void setRows(ZoneAslElencoRow[] rows) {
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

//constructor @8-08E7F4A6
    public ZoneAslElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-C35835DE
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select titolo "
                    + "      ,id_zona_asl "
                    + "      ,codice_regione "
                    + "      ,codice_zona "
                    + "      ,ad4_ccs.get_regione_denominazione(codice_regione)                   regione_denominazione "
                    + "  from zone_asl           zone "
                    + " where (   titolo like upper('%'||'{s_ZONA_ASL}')||'%' "
                    + "        or codice_zona like '{s_ZONA_ASL}'||'%' "
                    + "       ) "
                    + "   and (   codice_regione = '{s_REGIONE}' "
                    + "        or '{s_REGIONE}' is null "
                    + "       ) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_REGIONE.getObjectValue() ) ) urlS_REGIONE.setValue( "" );
        command.addParameter( "s_REGIONE", urlS_REGIONE, null );
        if ( StringUtils.isEmpty( (String) urlS_ZONA_ASL.getObjectValue() ) ) urlS_ZONA_ASL.setValue( "" );
        command.addParameter( "s_ZONA_ASL", urlS_ZONA_ASL, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select titolo ,id_zona_asl ,codice_regione ,codice_zona ,ad4_ccs.get_regione_denominazione(codice_regione) regione_denominazione from zone_asl zone where ( titolo like upper('%'||'{s_ZONA_ASL}')||'%' or codice_zona like '{s_ZONA_ASL}'||'%' ) and ( codice_regione = '{s_REGIONE}' or '{s_REGIONE}' is null )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "titolo" );
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

//loadDataBind @8-36117417
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ZoneAslElencoRow row = new ZoneAslElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setCODICE_REGIONE(Utils.convertToString(ds.parse(record.get("CODICE_REGIONE"), row.getCODICE_REGIONEField())));
                row.setCODICE_ZONA(Utils.convertToString(ds.parse(record.get("CODICE_ZONA"), row.getCODICE_ZONAField())));
                row.setTITOLO(Utils.convertToString(ds.parse(record.get("TITOLO"), row.getTITOLOField())));
                row.setREGIONE_DENOMINAZIONE(Utils.convertToString(ds.parse(record.get("REGIONE_DENOMINAZIONE"), row.getREGIONE_DENOMINAZIONEField())));
                row.setID_ZONA_ASL(Utils.convertToString(ds.parse(record.get("ID_ZONA_ASL"), row.getID_ZONA_ASLField())));
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

//getParameterByName @8-8275555E
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_REGIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_REGIONE;
            } else if ( "s_REGIONE".equals(name) && prefix == null ) {
                param = urlS_REGIONE;
            }
            if ( "s_ZONA_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlS_ZONA_ASL;
            } else if ( "s_ZONA_ASL".equals(name) && prefix == null ) {
                param = urlS_ZONA_ASL;
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

