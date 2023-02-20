//DIRITTI_ACCESSO DataSource @146-613C1002
package ad4web.AD4DiacElenco_ORIG;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End DIRITTI_ACCESSO DataSource

//class DataObject Header @146-81577A33
public class DIRITTI_ACCESSODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @146-2A1FCB53
    

    TextField sesAD4UTENTE = new TextField(null, null);
    
    TextField urlMVAV = new TextField(null, null);
    
    TextField sesAD4GRUPPO = new TextField(null, null);
    

    private DIRITTI_ACCESSORow[] rows = new DIRITTI_ACCESSORow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @146-95B8A740

    public void  setSesAD4UTENTE( String param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param, Format ignore ) {
        this.sesAD4UTENTE.setValue( param );
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

    public void  setSesAD4GRUPPO( String param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param, Format ignore ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public DIRITTI_ACCESSORow[] getRows() {
        return rows;
    }

    public void setRows(DIRITTI_ACCESSORow[] rows) {
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

//constructor @146-17A30817
    public DIRITTI_ACCESSODataObject(Page page) {
        super(page);
    }
//End constructor

//load @146-B4DB1D3D
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT 'Diritti di Accesso del'||decode(UTEN.TIPO_UTENTE,  "
                    + "'U','l''utente ',' gruppo ')||UTEN.NOMINATIVO NOMINATIVO "
                    + "  FROM UTENTI UTEN "
                    + " WHERE UTEN.UTENTE = decode(UTEN.TIPO_UTENTE, 'U', '{AD4UTENTE}', 'M', '', '{AD4GRUPPO}') "
                    + "   AND (UTEN.TIPO_UTENTE = '{TIPOUTENTE}' OR (UTEN.TIPO_UTENTE <> 'U' AND '{TIPOUTENTE}' = 'G')) "
                    + "UNION "
                    + "SELECT '<font color=\"#ff0000\">Selezionare un utente od un gruppo prima di accedere ai suoi Diritti di Accesso!</font>' NOMINATIVO "
                    + "  FROM DUAL "
                    + " WHERE ('{AD4UTENTE}' is null AND '{TIPOUTENTE}' = 'U') "
                    + "    OR ('{AD4GRUPPO}' is null AND '{TIPOUTENTE}' = 'G')" );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
        command.addParameter( "AD4UTENTE", sesAD4UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlMVAV.getObjectValue() ) ) urlMVAV.setValue( "" );
        command.addParameter( "TIPOUTENTE", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) sesAD4GRUPPO.getObjectValue() ) ) sesAD4GRUPPO.setValue( "" );
        command.addParameter( "AD4GRUPPO", sesAD4GRUPPO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT 'Diritti di Accesso del'||decode(UTEN.TIPO_UTENTE,  "
                                                         + "            'U','l''utente ',' gruppo ')||UTEN.NOMINATIVO NOMINATIVO FROM UTENTI UTEN WHERE UTEN.UTENTE = decode(UTEN.TIPO_UTENTE, 'U', '{AD4UTENTE}', 'M', '', '{AD4GRUPPO}') AND (UTEN.TIPO_UTENTE = '{TIPOUTENTE}' OR (UTEN.TIPO_UTENTE <> 'U' AND '{TIPOUTENTE}' = 'G')) UNION SELECT '<font color=\"#ff0000\">Selezionare un utente od un gruppo prima di accedere ai suoi Diritti di Accesso!</font>' NOMINATIVO FROM DUAL WHERE ('{AD4UTENTE}' is null AND '{TIPOUTENTE}' = 'U') OR ('{AD4GRUPPO}' is null AND '{TIPOUTENTE}' = 'G') ) cnt " );
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

//loadDataBind @146-D773E5FF
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                DIRITTI_ACCESSORow row = new DIRITTI_ACCESSORow();
                DbRow record = (DbRow) records.nextElement();
                row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
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

//getParameterByName @146-9C52FAD9
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "AD4UTENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4UTENTE;
            } else if ( "AD4UTENTE".equals(name) && prefix == null ) {
                param = sesAD4UTENTE;
            }
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            if ( "AD4GRUPPO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4GRUPPO;
            } else if ( "AD4GRUPPO".equals(name) && prefix == null ) {
                param = sesAD4GRUPPO;
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

