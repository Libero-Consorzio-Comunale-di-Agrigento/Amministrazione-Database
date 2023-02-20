//AD4_SERVIZI DataSource @6-259B7B96
package ad4web.AD4ServElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_SERVIZI DataSource

//class DataObject Header @6-69F0F6F8
public class AD4_SERVIZIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-566360FC
    

    TextField sesAD4PROGETTO = new TextField(null, null);
    

    private AD4_SERVIZIRow[] rows = new AD4_SERVIZIRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @6-80A27556

    public void  setSesAD4PROGETTO( String param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param, Format ignore ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public AD4_SERVIZIRow[] getRows() {
        return rows;
    }

    public void setRows(AD4_SERVIZIRow[] rows) {
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

//constructor @6-95E5B596
    public AD4_SERVIZIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-F60C9B20
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT SERV.ID_SERVIZIO, "
                    + "       SERV.MODULO, "
                    + "       SERV.ISTANZA, "
                    + "       AD4WEB.GET_DATI_SERVIZIO(SERV.ID_SERVIZIO) DATI, "
                    + "       MODU.DESCRIZIONE DES_MODULO,  "
                    + " "
                    + "       ISTA.DESCRIZIONE DES_ISTANZA, "
                    + "       'Servizi del Progetto '||PROG.DESCRIZIONE DES_PROGETTO "
                    + "  FROM SERVIZI SERV, PROGETTI PROG,  "
                    + "       MODULI MODU, ISTANZE ISTA "
                    + " WHERE MODU.PROGETTO  = '{AD4PROGETTO}' "
                    + "   AND PROG.PROGETTO  = MODU.PROGETTO "
                    + "   AND SERV.MODULO    = MODU.MODULO "
                    + "   AND ISTA.ISTANZA   = SERV.ISTANZA "
                    + "UNION "
                    + "SELECT TO_NUMBER(NULL), "
                    + "       '', "
                    + "       '', "
                    + "       '', "
                    + "       '',  "
                    + "       '', "
                    + "       '<font color=\"#ff0000\">Selezionare un progetto prima di accedere ai suoi Servizi!</font>' DES_PROGETTO "
                    + "  FROM DUAL "
                    + " WHERE '{AD4PROGETTO}' IS NULL "
                    + " " );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT SERV.ID_SERVIZIO, SERV.MODULO, SERV.ISTANZA,  "
                                                         + "            AD4WEB.GET_DATI_SERVIZIO(SERV.ID_SERVIZIO) DATI, MODU.DESCRIZIONE DES_MODULO,  "
                                                         + "            ISTA.DESCRIZIONE DES_ISTANZA,  "
                                                         + "            'Servizi del Progetto '||PROG.DESCRIZIONE DES_PROGETTO FROM SERVIZI SERV, PROGETTI PROG, MODULI MODU,  "
                                                         + "            ISTANZE ISTA WHERE MODU.PROGETTO = '{AD4PROGETTO}' AND PROG.PROGETTO = MODU.PROGETTO AND SERV.MODULO = MODU.MODULO AND ISTA.ISTANZA = SERV.ISTANZA UNION SELECT TO_NUMBER(NULL), '', '', '', '', '', '<font color=\"#ff0000\">Selezionare un progetto prima di accedere ai suoi Servizi!</font>' DES_PROGETTO FROM DUAL WHERE '{AD4PROGETTO}' IS NULL  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "1" );
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

//loadDataBind @6-34E5DE5E
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_SERVIZIRow row = new AD4_SERVIZIRow();
                DbRow record = (DbRow) records.nextElement();
                row.setID_SERVIZIO(Utils.convertToLong(ds.parse(record.get("ID_SERVIZIO"), row.getID_SERVIZIOField())));
                row.setDES_MODULO(Utils.convertToString(ds.parse(record.get("DES_MODULO"), row.getDES_MODULOField())));
                row.setDES_ISTANZA(Utils.convertToString(ds.parse(record.get("DES_ISTANZA"), row.getDES_ISTANZAField())));
                row.setDATI(Utils.convertToString(ds.parse(record.get("DATI"), row.getDATIField())));
                row.setISTANZA(Utils.convertToString(ds.parse(record.get("ISTANZA"), row.getISTANZAField())));
                row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
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

//getParameterByName @6-96A9131F
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
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

