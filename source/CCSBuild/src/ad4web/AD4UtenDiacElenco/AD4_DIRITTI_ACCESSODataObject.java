//AD4_DIRITTI_ACCESSO DataSource @6-EE197D03
package ad4web.AD4UtenDiacElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_DIRITTI_ACCESSO DataSource

//class DataObject Header @6-90563D55
public class AD4_DIRITTI_ACCESSODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-DEEF2883
    

    TextField urlISTANZA = new TextField(null, null);
    
    TextField urlMODULO = new TextField(null, null);
    
    TextField urlS_ISTANZA = new TextField(null, null);
    
    TextField urlS_MODULO = new TextField(null, null);
    
    TextField urlS_RUOLO = new TextField(null, null);
    
    TextField urlS_GRUPPO = new TextField(null, null);
    

    private AD4_DIRITTI_ACCESSORow[] rows = new AD4_DIRITTI_ACCESSORow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @6-612DF010

    public void  setUrlISTANZA( String param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param, Format ignore ) {
        this.urlISTANZA.setValue( param );
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

    public void  setUrlS_ISTANZA( String param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param, Format ignore ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_MODULO( String param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param, Format ignore ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_RUOLO( String param ) {
        this.urlS_RUOLO.setValue( param );
    }

    public void  setUrlS_RUOLO( Object param ) {
        this.urlS_RUOLO.setValue( param );
    }

    public void  setUrlS_RUOLO( Object param, Format ignore ) {
        this.urlS_RUOLO.setValue( param );
    }

    public void  setUrlS_GRUPPO( String param ) {
        this.urlS_GRUPPO.setValue( param );
    }

    public void  setUrlS_GRUPPO( Object param ) {
        this.urlS_GRUPPO.setValue( param );
    }

    public void  setUrlS_GRUPPO( Object param, Format ignore ) {
        this.urlS_GRUPPO.setValue( param );
    }

    public AD4_DIRITTI_ACCESSORow[] getRows() {
        return rows;
    }

    public void setRows(AD4_DIRITTI_ACCESSORow[] rows) {
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

//constructor @6-D9C7EB98
    public AD4_DIRITTI_ACCESSODataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-A021B96C
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT DIAC.UTENTE , "
                    + "       UTEN.NOMINATIVO NOMINATIVO, "
                    + "       DIAC.MODULO MODULO, "
                    + "       MODU.DESCRIZIONE DES_MODULO,  "
                    + " "
                    + "       DIAC.ISTANZA ISTANZA, "
                    + "       ISTA.DESCRIZIONE DES_ISTANZA,   "
                    + "       DIAC.SEQUENZA SEQUENZA,  "
                    + " "
                    + "       AD4WEB.GET_DATI_DIAC(DIAC.UTENTE, DIAC.MODULO, DIAC.ISTANZA) DATI "
                    + "  FROM DIRITTI_ACCESSO DIAC,  "
                    + "MODULI MODU, ISTANZE ISTA,  "
                    + "UTENTI UTEN "
                    + " WHERE UTEN.UTENTE    = DIAC.UTENTE    "
                    + "   AND MODU.MODULO    = DIAC.MODULO "
                    + "   AND ISTA.ISTANZA   = DIAC.ISTANZA "
                    + "   AND DIAC.MODULO    LIKE NVL('{MODULO}',NVL('{s_MODULO}','%')) "
                    + "   AND DIAC.ISTANZA   LIKE NVL('{ISTANZA}',NVL('{s_ISTANZA}','%')) "
                    + "   AND DIAC.RUOLO     LIKE NVL('{s_RUOLO}','%') "
                    + "   AND NVL(DIAC.GRUPPO,'XXXXXXXXX') LIKE NVL('{s_GRUPPO}','%') "
                    + "   AND ('{s_MODULO}' is not null or '{s_ISTANZA}' is not null or '{s_RUOLO}' is not null or '{s_GRUPPO}'is not null) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZA", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULO", urlMODULO, null );
        if ( StringUtils.isEmpty( (String) urlS_ISTANZA.getObjectValue() ) ) urlS_ISTANZA.setValue( "" );
        command.addParameter( "s_ISTANZA", urlS_ISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlS_MODULO.getObjectValue() ) ) urlS_MODULO.setValue( "" );
        command.addParameter( "s_MODULO", urlS_MODULO, null );
        if ( StringUtils.isEmpty( (String) urlS_RUOLO.getObjectValue() ) ) urlS_RUOLO.setValue( "" );
        command.addParameter( "s_RUOLO", urlS_RUOLO, null );
        if ( StringUtils.isEmpty( (String) urlS_GRUPPO.getObjectValue() ) ) urlS_GRUPPO.setValue( "" );
        command.addParameter( "s_GRUPPO", urlS_GRUPPO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT DIAC.UTENTE , UTEN.NOMINATIVO NOMINATIVO, DIAC.MODULO MODULO,  "
                                                         + "            MODU.DESCRIZIONE DES_MODULO, DIAC.ISTANZA ISTANZA, ISTA.DESCRIZIONE DES_ISTANZA,  "
                                                         + "            DIAC.SEQUENZA SEQUENZA, AD4WEB.GET_DATI_DIAC(DIAC.UTENTE,  "
                                                         + "            DIAC.MODULO, DIAC.ISTANZA) DATI FROM DIRITTI_ACCESSO DIAC, MODULI MODU,  "
                                                         + "            ISTANZE ISTA,  "
                                                         + "            UTENTI UTEN WHERE UTEN.UTENTE = DIAC.UTENTE AND MODU.MODULO = DIAC.MODULO AND ISTA.ISTANZA = DIAC.ISTANZA AND DIAC.MODULO LIKE NVL('{MODULO}',NVL('{s_MODULO}','%')) AND DIAC.ISTANZA LIKE NVL('{ISTANZA}',NVL('{s_ISTANZA}','%')) AND DIAC.RUOLO LIKE NVL('{s_RUOLO}','%') AND NVL(DIAC.GRUPPO,'XXXXXXXXX') LIKE NVL('{s_GRUPPO}','%') AND ('{s_MODULO}' is not null or '{s_ISTANZA}' is not null or '{s_RUOLO}' is not null or '{s_GRUPPO}'is not null)  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "utente, sequenza, DIAC.modulo, DIAC.istanza" );
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

//loadDataBind @6-49F009C5
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_DIRITTI_ACCESSORow row = new AD4_DIRITTI_ACCESSORow();
                DbRow record = (DbRow) records.nextElement();
                row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
                row.setSEQUENZA(Utils.convertToLong(ds.parse(record.get("SEQUENZA"), row.getSEQUENZAField())));
                row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
                row.setDES_MODULO(Utils.convertToString(ds.parse(record.get("DES_MODULO"), row.getDES_MODULOField())));
                row.setDES_ISTANZA(Utils.convertToString(ds.parse(record.get("DES_ISTANZA"), row.getDES_ISTANZAField())));
                row.setDATI(Utils.convertToString(ds.parse(record.get("DATI"), row.getDATIField())));
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

//getParameterByName @6-49FDD091
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            if ( "s_ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlS_ISTANZA;
            } else if ( "s_ISTANZA".equals(name) && prefix == null ) {
                param = urlS_ISTANZA;
            }
            if ( "s_MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlS_MODULO;
            } else if ( "s_MODULO".equals(name) && prefix == null ) {
                param = urlS_MODULO;
            }
            if ( "s_RUOLO".equals(name) && "url".equals(prefix) ) {
                param = urlS_RUOLO;
            } else if ( "s_RUOLO".equals(name) && prefix == null ) {
                param = urlS_RUOLO;
            }
            if ( "s_GRUPPO".equals(name) && "url".equals(prefix) ) {
                param = urlS_GRUPPO;
            } else if ( "s_GRUPPO".equals(name) && prefix == null ) {
                param = urlS_GRUPPO;
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

