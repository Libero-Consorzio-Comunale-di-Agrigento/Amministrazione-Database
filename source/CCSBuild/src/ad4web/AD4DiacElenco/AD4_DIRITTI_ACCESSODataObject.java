//AD4_DIRITTI_ACCESSO DataSource @6-E2336357
package ad4web.AD4DiacElenco;

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

//attributes of DataObject @6-AE587251
    

    TextField sesAD4UTENTE = new TextField(null, null);
    
    TextField sesAD4GRUPPO = new TextField(null, null);
    
    TextField urlMVAV = new TextField(null, null);
    
    TextField urlS_MODULO = new TextField(null, null);
    
    TextField urlS_ISTANZA = new TextField(null, null);
    

    private AD4_DIRITTI_ACCESSORow[] rows = new AD4_DIRITTI_ACCESSORow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @6-A77B451F

    public void  setSesAD4UTENTE( String param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param, Format ignore ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4GRUPPO( String param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param, Format ignore ) {
        this.sesAD4GRUPPO.setValue( param );
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

    public void  setUrlS_MODULO( String param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param, Format ignore ) {
        this.urlS_MODULO.setValue( param );
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

//load @6-EBC01A38
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT DIAC.UTENTE, "
                    + "       decode(UTEN.TIPO_UTENTE,  "
                    + "'U','D','G') TIPO_ACCESSO, "
                    + "       DIAC.MODULO MODULO, "
                    + "       MODU.DESCRIZIONE DES_MODULO,  "
                    + " "
                    + "       DIAC.ISTANZA ISTANZA, "
                    + "       ISTA.DESCRIZIONE DES_ISTANZA,  "
                    + "       ISTA.PROGETTO PROGETTO,  "
                    + " "
                    + "       DIAC.SEQUENZA SEQUENZA,  "
                    + "       AD4WEB.GET_DATI_DIAC(DIAC.UTENTE,  "
                    + "DIAC.MODULO, DIAC.ISTANZA) DATI "
                    + "  FROM DIRITTI_ACCESSO DIAC, MODULI MODU,  "
                    + " "
                    + "       ISTANZE ISTA,  "
                    + "UTENTI UTEN "
                    + " WHERE DIAC.UTENTE    = UTEN.UTENTE "
                    + "   AND MODU.MODULO    = DIAC.MODULO "
                    + "   AND ISTA.ISTANZA   = DIAC.ISTANZA "
                    + "   AND UTEN.UTENTE    like decode(UTEN.TIPO_UTENTE, 'U','{AD4UTENTE}','M','%','{AD4GRUPPO}') "
                    + "   AND (TIPO_UTENTE   like decode('{TIPOUTENTE}','M','%','{TIPOUTENTE}') OR ('{TIPOUTENTE}' = 'G' AND TIPO_UTENTE = 'O')) "
                    + "   AND (   upper(modu.modulo) like upper('%'||'{s_MODULO}')||'%' "
                    + "        or upper(modu.descrizione) like upper('%'||'{s_MODULO}')||'%' "
                    + "       ) "
                    + "   AND (   upper(ISTA.ISTANZA) like upper('%'||'{s_ISTANZA}')||'%' "
                    + "        or upper(ISTA.descrizione) like upper('%'||'{s_ISTANZA}')||'%' "
                    + "       ) "
                    + "" );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
        command.addParameter( "AD4UTENTE", sesAD4UTENTE, null );
        if ( StringUtils.isEmpty( (String) sesAD4GRUPPO.getObjectValue() ) ) sesAD4GRUPPO.setValue( "" );
        command.addParameter( "AD4GRUPPO", sesAD4GRUPPO, null );
        if ( StringUtils.isEmpty( (String) urlMVAV.getObjectValue() ) ) urlMVAV.setValue( "" );
        command.addParameter( "TIPOUTENTE", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) urlS_MODULO.getObjectValue() ) ) urlS_MODULO.setValue( "" );
        command.addParameter( "s_MODULO", urlS_MODULO, null );
        if ( StringUtils.isEmpty( (String) urlS_ISTANZA.getObjectValue() ) ) urlS_ISTANZA.setValue( "" );
        command.addParameter( "s_ISTANZA", urlS_ISTANZA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT DIAC.UTENTE, decode(UTEN.TIPO_UTENTE, 'U','D','G') TIPO_ACCESSO,  "
                                                         + "            DIAC.MODULO MODULO, MODU.DESCRIZIONE DES_MODULO, DIAC.ISTANZA ISTANZA,  "
                                                         + "            ISTA.DESCRIZIONE DES_ISTANZA, ISTA.PROGETTO PROGETTO,  "
                                                         + "            DIAC.SEQUENZA SEQUENZA, AD4WEB.GET_DATI_DIAC(DIAC.UTENTE, DIAC.MODULO,  "
                                                         + "            DIAC.ISTANZA) DATI FROM DIRITTI_ACCESSO DIAC, MODULI MODU, ISTANZE ISTA,  "
                                                         + "            UTENTI UTEN WHERE DIAC.UTENTE = UTEN.UTENTE AND MODU.MODULO = DIAC.MODULO AND ISTA.ISTANZA = DIAC.ISTANZA AND UTEN.UTENTE like decode(UTEN.TIPO_UTENTE, 'U','{AD4UTENTE}','M','%','{AD4GRUPPO}') AND (TIPO_UTENTE like decode('{TIPOUTENTE}','M','%','{TIPOUTENTE}') OR ('{TIPOUTENTE}' = 'G' AND TIPO_UTENTE = 'O')) AND ( upper(modu.modulo) like upper('%'||'{s_MODULO}')||'%' or upper(modu.descrizione) like upper('%'||'{s_MODULO}')||'%' ) AND ( upper(ISTA.ISTANZA) like upper('%'||'{s_ISTANZA}')||'%' or upper(ISTA.descrizione) like upper('%'||'{s_ISTANZA}')||'%' )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "sequenza" );
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

//loadDataBind @6-AE23E40B
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_DIRITTI_ACCESSORow row = new AD4_DIRITTI_ACCESSORow();
                DbRow record = (DbRow) records.nextElement();
                row.setSEQUENZA(Utils.convertToLong(ds.parse(record.get("SEQUENZA"), row.getSEQUENZAField())));
                row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
                row.setDES_MODULO(Utils.convertToString(ds.parse(record.get("DES_MODULO"), row.getDES_MODULOField())));
                row.setDES_ISTANZA(Utils.convertToString(ds.parse(record.get("DES_ISTANZA"), row.getDES_ISTANZAField())));
                row.setDATI(Utils.convertToString(ds.parse(record.get("DATI"), row.getDATIField())));
                row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
                row.setISTANZA(Utils.convertToString(ds.parse(record.get("ISTANZA"), row.getISTANZAField())));
                row.setTIPO_ACCESSO(Utils.convertToString(ds.parse(record.get("TIPO_ACCESSO"), row.getTIPO_ACCESSOField())));
                row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
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

//getParameterByName @6-D57B26F3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "AD4UTENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4UTENTE;
            } else if ( "AD4UTENTE".equals(name) && prefix == null ) {
                param = sesAD4UTENTE;
            }
            if ( "AD4GRUPPO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4GRUPPO;
            } else if ( "AD4GRUPPO".equals(name) && prefix == null ) {
                param = sesAD4GRUPPO;
            }
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            if ( "s_MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlS_MODULO;
            } else if ( "s_MODULO".equals(name) && prefix == null ) {
                param = urlS_MODULO;
            }
            if ( "s_ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlS_ISTANZA;
            } else if ( "s_ISTANZA".equals(name) && prefix == null ) {
                param = urlS_ISTANZA;
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

