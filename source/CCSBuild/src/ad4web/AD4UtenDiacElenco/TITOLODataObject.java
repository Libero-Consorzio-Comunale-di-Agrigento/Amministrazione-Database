//TITOLO DataSource @146-EE197D03
package ad4web.AD4UtenDiacElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End TITOLO DataSource

//class DataObject Header @146-CAF63659
public class TITOLODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @146-0E03B5A9
    

    TextField urlMODULO = new TextField(null, null);
    
    TextField urlISTANZA = new TextField(null, null);
    
    TextField sesAD4BP = new TextField(null, null);
    

    private TITOLORow[] rows = new TITOLORow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @146-A3A88471

    public void  setUrlMODULO( String param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param, Format ignore ) {
        this.urlMODULO.setValue( param );
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

    public void  setSesAD4BP( String param ) {
        this.sesAD4BP.setValue( param );
    }

    public void  setSesAD4BP( Object param ) {
        this.sesAD4BP.setValue( param );
    }

    public void  setSesAD4BP( Object param, Format ignore ) {
        this.sesAD4BP.setValue( param );
    }

    public TITOLORow[] getRows() {
        return rows;
    }

    public void setRows(TITOLORow[] rows) {
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

//constructor @146-42CFF645
    public TITOLODataObject(Page page) {
        super(page);
    }
//End constructor

//load @146-E9B24BE2
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT DISTINCT 'Diritti di Accesso'|| "
                    + "       decode('{ISTANZA}','','',' Istanza '||ISTA.DESCRIZIONE||' ')|| "
                    + "       decode('{MODULO}','','',' Modulo '||MODU.DESCRIZIONE||' ') TITOLO, "
                    + "       decode(nvl('{MODULO}','{ISTANZA}'),'','','{AD4BP}') AD4BP, "
                    + "       decode(nvl('{MODULO}','{ISTANZA}'),'','','<img title=\"Indietro\" height=\"18\" src=\"../common/images/AD4/indietro.gif\" width=\"18\" border=\"0\">'||'Indietro') INDIETRO "
                    + "  FROM MODULI MODU,  "
                    + "ISTANZE ISTA "
                    + " WHERE MODU.MODULO    LIKE NVL('{MODULO}','%') "
                    + "   AND ISTA.ISTANZA   LIKE NVL('{ISTANZA}','%')" );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULO", urlMODULO, null );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZA", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) sesAD4BP.getObjectValue() ) ) sesAD4BP.setValue( "" );
        command.addParameter( "AD4BP", sesAD4BP, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT DISTINCT 'Diritti di Accesso'|| decode('{ISTANZA}','','',' Istanza '||ISTA.DESCRIZIONE||' ')|| decode('{MODULO}','','',' Modulo '||MODU.DESCRIZIONE||' ') TITOLO,  "
                                                         + "            decode(nvl('{MODULO}','{ISTANZA}'),'','','{AD4BP}') AD4BP,  "
                                                         + "            decode(nvl('{MODULO}','{ISTANZA}'),'','','<img title=\"Indietro\" height=\"18\" src=\"../common/images/AD4/indietro.gif\" width=\"18\" border=\"0\">'||'Indietro') INDIETRO FROM MODULI MODU, ISTANZE ISTA WHERE MODU.MODULO LIKE NVL('{MODULO}','%') AND ISTA.ISTANZA LIKE NVL('{ISTANZA}','%') ) cnt " );
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

//loadDataBind @146-61C3A4A6
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                TITOLORow row = new TITOLORow();
                DbRow record = (DbRow) records.nextElement();
                row.setTITOLO(Utils.convertToString(ds.parse(record.get("TITOLO"), row.getTITOLOField())));
                row.setINDIETRO(Utils.convertToString(ds.parse(record.get("INDIETRO"), row.getINDIETROField())));
                row.setAD4BP(Utils.convertToString(ds.parse(record.get("AD4BP"), row.getAD4BPField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @146-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @146-EE2D1D81
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "AD4BP".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4BP;
            } else if ( "AD4BP".equals(name) && prefix == null ) {
                param = sesAD4BP;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @146-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @146-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @146-238A81BB
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

//fireBeforeExecuteSelectEvent @146-9DA7B025
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

//fireAfterExecuteSelectEvent @146-F7E8A616
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

//class DataObject Tail @146-ED3F53A4
} // End of class DS
//End class DataObject Tail

