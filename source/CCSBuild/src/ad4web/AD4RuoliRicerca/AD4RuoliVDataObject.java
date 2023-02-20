//AD4RuoliV DataSource @142-8004FB53
package ad4web.AD4RuoliRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4RuoliV DataSource

//class DataObject Header @142-E877DB16
public class AD4RuoliVDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @142-38744163
    

    TextField urlS_DESCRIZIONE = new TextField(null, null);
    

    private AD4RuoliVRow[] rows = new AD4RuoliVRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @142-C811A39E

    public void  setUrlS_DESCRIZIONE( String param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param, Format ignore ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public AD4RuoliVRow[] getRows() {
        return rows;
    }

    public void setRows(AD4RuoliVRow[] rows) {
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

//constructor @142-3ECB6541
    public AD4RuoliVDataObject(Page page) {
        super(page);
    }
//End constructor

//load @142-46D9D2A7
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT RUOL.RUOLO,	   "
                    + "       RUOL.DESCRIZIONE, "
                    + "	   PROG.DESCRIZIONE PROGETTO, "
                    + "	   MODU.DESCRIZIONE MODULO, "
                    + "	   PROFILO, "
                    + "           decode(stato, 'U',  "
                    + "'<img src=\"../common/images/AD4/visto.GIF\" title=\"In uso\" height=\"18\" width=\"18\" border=\"0\">','<img src=\"../common/images/AD4/stop.GIF\" title=\"Non in uso\" height=\"18\" width=\"18\" border=\"0\">') STATO, "
                    + "           decode(GRUPPO_LAVORO, 'S', '<img src=\"../common/images/AD4/kuser.gif\" title=\"Il ruolo è un gruppo di lavoro\" height=\"18\" width=\"18\" border=\"0\">',' ') GRUPPO_LAVORO, "
                    + "           decode(GRUPPO_SO, 'S', '<img src=\"../common/images/AD4/UniOrg.gif\" title=\"Il ruolo corrisponde ad un gruppo della Struttura Organizzativa\" height=\"18\" width=\"18\" border=\"0\">',' ') GRUPPO_SO "
                    + "  FROM RUOLI RUOL, PROGETTI PROG, MODULI MODU "
                    + " where PROG.PROGETTO(+) = RUOL.PROGETTO "
                    + "   AND MODU.MODULO(+) = RUOL.MODULO "
                    + "   AND (upper(RUOL.RUOLO) LIKE upper('%{s_DESCRIZIONE}%') "
                    + "       or upper(ruol.descrizione) LIKE upper('%{s_DESCRIZIONE}%')) "
                    + "   and ruolo not in ('def','*') "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlS_DESCRIZIONE.getObjectValue() ) ) urlS_DESCRIZIONE.setValue( "" );
        command.addParameter( "s_DESCRIZIONE", urlS_DESCRIZIONE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT RUOL.RUOLO,	 RUOL.DESCRIZIONE, 	 PROG.DESCRIZIONE PROGETTO,  "
                                                         + "            	 MODU.DESCRIZIONE MODULO, 	 PROFILO, decode(stato, 'U',  "
                                                         + "            '<img src=\"../common/images/AD4/visto.GIF\" title=\"In uso\" height=\"18\" width=\"18\" border=\"0\">','<img src=\"../common/images/AD4/stop.GIF\" title=\"Non in uso\" height=\"18\" width=\"18\" border=\"0\">') STATO, decode(GRUPPO_LAVORO, 'S', '<img src=\"../common/images/AD4/kuser.gif\" title=\"Il ruolo è un gruppo di lavoro\" height=\"18\" width=\"18\" border=\"0\">',' ') GRUPPO_LAVORO, decode(GRUPPO_SO, 'S', '<img src=\"../common/images/AD4/UniOrg.gif\" title=\"Il ruolo corrisponde ad un gruppo della Struttura Organizzativa\" height=\"18\" width=\"18\" border=\"0\">',' ') GRUPPO_SO FROM RUOLI RUOL, PROGETTI PROG, MODULI MODU where PROG.PROGETTO(+) = RUOL.PROGETTO AND MODU.MODULO(+) = RUOL.MODULO AND (upper(RUOL.RUOLO) LIKE upper('%{s_DESCRIZIONE}%') or upper(ruol.descrizione) LIKE upper('%{s_DESCRIZIONE}%')) and ruolo not in ('def','*')  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "ruolo" );
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

//loadDataBind @142-93287BA8
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4RuoliVRow row = new AD4RuoliVRow();
                DbRow record = (DbRow) records.nextElement();
                row.setRUOLO(Utils.convertToString(ds.parse(record.get("RUOLO"), row.getRUOLOField())));
                row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
                row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
                row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
                row.setPROFILO(Utils.convertToLong(ds.parse(record.get("PROFILO"), row.getPROFILOField())));
                row.setSTATO(Utils.convertToString(ds.parse(record.get("STATO"), row.getSTATOField())));
                row.setGRUPPO_LAVORO(Utils.convertToString(ds.parse(record.get("GRUPPO_LAVORO"), row.getGRUPPO_LAVOROField())));
                row.setGRUPPO_SO(Utils.convertToString(ds.parse(record.get("GRUPPO_SO"), row.getGRUPPO_SOField())));
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

