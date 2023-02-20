//AD4_ISTANZE DataSource @6-FB1DC46F
package ad4web.AD4IstaElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_ISTANZE DataSource

//class DataObject Header @6-D9888AE3
public class AD4_ISTANZEDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-47326AD5
    

    TextField sesAD4PROGETTO = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    
    TextField urlS_DESCRIZIONE = new TextField(null, null);
    

    private AD4_ISTANZERow[] rows = new AD4_ISTANZERow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @6-C41D2D7B

    public void  setSesAD4PROGETTO( String param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param, Format ignore ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( String param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param, Format ignore ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( String param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public void  setUrlS_DESCRIZIONE( Object param, Format ignore ) {
        this.urlS_DESCRIZIONE.setValue( param );
    }

    public AD4_ISTANZERow[] getRows() {
        return rows;
    }

    public void setRows(AD4_ISTANZERow[] rows) {
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

//constructor @6-E1ECFEE1
    public AD4_ISTANZEDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-6AA86CEA
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT ISTA.ISTANZA,  "
                    + " "
                    + "       ISTA.DESCRIZIONE, "
                    + "       AD4WEB.GET_DATI_ISTANZA(ISTA.ISTANZA) DATI, "
                    + "       ISTA.PROGETTO, "
                    + "       ISTA.USER_ORACLE "
                    + "  FROM ISTANZE ISTA "
                    + " WHERE ISTA.PROGETTO = decode('{PROGETTO}',null,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   and ( "
                    + "      upper(istanza) like UPPER('%{s_DESCRIZIONE}%') "
                    + "   or upper(DESCRIZIONE) like upper('%{s_DESCRIZIONE}%') "
                    + "   or upper(user_oracle) like upper('%{s_DESCRIZIONE}%') "
                    + ") "
                    + "UNION "
                    + "SELECT '', "
                    + "       '', "
                    + "       '', "
                    + "       '', "
                    + "       '' "
                    + "  FROM DUAL "
                    + " WHERE '{PROGETTO}' IS NULL "
                    + "   AND '{AD4PROGETTO}' IS NULL "
                    + "" );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlS_DESCRIZIONE.getObjectValue() ) ) urlS_DESCRIZIONE.setValue( "" );
        command.addParameter( "s_DESCRIZIONE", urlS_DESCRIZIONE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT ISTA.ISTANZA, ISTA.DESCRIZIONE, AD4WEB.GET_DATI_ISTANZA(ISTA.ISTANZA) DATI,  "
                                                         + "            ISTA.PROGETTO,  "
                                                         + "            ISTA.USER_ORACLE FROM ISTANZE ISTA WHERE ISTA.PROGETTO = decode('{PROGETTO}',null,'{AD4PROGETTO}','{PROGETTO}') and ( upper(istanza) like UPPER('%{s_DESCRIZIONE}%') or upper(DESCRIZIONE) like upper('%{s_DESCRIZIONE}%') or upper(user_oracle) like upper('%{s_DESCRIZIONE}%') ) UNION SELECT '', '', '', '', '' FROM DUAL WHERE '{PROGETTO}' IS NULL AND '{AD4PROGETTO}' IS NULL  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "2" );
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

//loadDataBind @6-ABDC5F22
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_ISTANZERow row = new AD4_ISTANZERow();
                DbRow record = (DbRow) records.nextElement();
                row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
                row.setDATI(Utils.convertToString(ds.parse(record.get("DATI"), row.getDATIField())));
                row.setISTANZA(Utils.convertToString(ds.parse(record.get("ISTANZA"), row.getISTANZAField())));
                row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
                row.setUSRORCL(Utils.convertToString(ds.parse(record.get("USER_ORACLE"), row.getUSRORCLField())));
                row.setUSER_ORACLE(Utils.convertToString(ds.parse(record.get("USER_ORACLE"), row.getUSER_ORACLEField())));
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

//getParameterByName @6-8058257F
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "AD4PROGETTO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PROGETTO;
            } else if ( "AD4PROGETTO".equals(name) && prefix == null ) {
                param = sesAD4PROGETTO;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
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

