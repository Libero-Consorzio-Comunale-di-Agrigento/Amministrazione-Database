//TOOLBOX DataSource @97-71F1F9F5
package ad4web.AD4Gruppo;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End TOOLBOX DataSource

//class DataObject Header @97-E5F16F46
public class TOOLBOXDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @97-65DDD737
    

    TextField urlGRUPPO = new TextField(null, null);
    
    TextField sesAD4GRUPPO = new TextField(null, null);
    
    TextField urlSE_NUOVO = new TextField(null, null);
    

    private TOOLBOXRow[] rows = new TOOLBOXRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @97-5EC8DE30

    public void  setUrlGRUPPO( String param ) {
        this.urlGRUPPO.setValue( param );
    }

    public void  setUrlGRUPPO( Object param ) {
        this.urlGRUPPO.setValue( param );
    }

    public void  setUrlGRUPPO( Object param, Format ignore ) {
        this.urlGRUPPO.setValue( param );
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

    public void  setUrlSE_NUOVO( String param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param, Format ignore ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public TOOLBOXRow[] getRows() {
        return rows;
    }

    public void setRows(TOOLBOXRow[] rows) {
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

//constructor @97-41BBC0FE
    public TOOLBOXDataObject(Page page) {
        super(page);
    }
//End constructor

//load @97-3DB6F4DF
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT UTGR_INS_MOD,  "
                    + "GRUPPO "
                    + "FROM (select decode(NVL('{SE_NUOVO}','N'),'Y','','<img height=\"18\" src=\"../common/images/AMV/edit.gif\" width=\"18\" border=\"0\" title=\"Modifica\">Modifica') UTGR_INS_MOD, "
                    + "       utgr.gruppo "
                    + "  from UTENTI uten, UTENTI_GRUPPO utgr "
                    + " where utgr.utente = uten.utente "
                    + "   and utgr.gruppo = NVL('{GRUPPO}','{AD4GRUPPO}') "
                    + " group by utgr.gruppo "
                    + "UNION "
                    + "select decode(NVL('{SE_NUOVO}','N'),'Y','','<img height=\"18\" src=\"../common/images/AMV/edit.gif\" width=\"18\" border=\"0\" title=\"Inserisci\">Inserisci') UTGR_INS_MOD, "
                    + "       NVL('{GRUPPO}','{AD4GRUPPO}') GRUPPO "
                    + "  from DUAL "
                    + " where NOT EXISTS (SELECT 1 "
                    + "                     FROM UTENTI_GRUPPO utgr "
                    + "					WHERE utgr.gruppo = NVL('{GRUPPO}','{AD4GRUPPO}')) "
                    + " ) "
                    + "WHERE GRUPPO NOT LIKE 'Z_%Z_' ESCAPE 'Z' "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlGRUPPO.getObjectValue() ) ) urlGRUPPO.setValue( "" );
        command.addParameter( "GRUPPO", urlGRUPPO, null );
        if ( StringUtils.isEmpty( (String) sesAD4GRUPPO.getObjectValue() ) ) sesAD4GRUPPO.setValue( "" );
        command.addParameter( "AD4GRUPPO", sesAD4GRUPPO, null );
        if ( StringUtils.isEmpty( (String) urlSE_NUOVO.getObjectValue() ) ) urlSE_NUOVO.setValue( "" );
        command.addParameter( "SE_NUOVO", urlSE_NUOVO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT UTGR_INS_MOD,  "
                                                         + "            GRUPPO FROM (select decode(NVL('{SE_NUOVO}','N'),'Y','','<img height=\"18\" src=\"../common/images/AMV/edit.gif\" width=\"18\" border=\"0\" title=\"Modifica\">Modifica') UTGR_INS_MOD, utgr.gruppo from UTENTI uten, UTENTI_GRUPPO utgr where utgr.utente = uten.utente and utgr.gruppo = NVL('{GRUPPO}','{AD4GRUPPO}') group by utgr.gruppo UNION select decode(NVL('{SE_NUOVO}','N'),'Y','','<img height=\"18\" src=\"../common/images/AMV/edit.gif\" width=\"18\" border=\"0\" title=\"Inserisci\">Inserisci') UTGR_INS_MOD, NVL('{GRUPPO}','{AD4GRUPPO}') GRUPPO from DUAL where NOT EXISTS (SELECT 1 FROM UTENTI_GRUPPO utgr 					WHERE utgr.gruppo = NVL('{GRUPPO}','{AD4GRUPPO}')) ) WHERE GRUPPO NOT LIKE 'Z_%Z_' ESCAPE 'Z'  ) cnt " );
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

//loadDataBind @97-936B59C6
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                TOOLBOXRow row = new TOOLBOXRow();
                DbRow record = (DbRow) records.nextElement();
                row.setUTGR_INS_MOD(Utils.convertToString(ds.parse(record.get("UTGR_INS_MOD"), row.getUTGR_INS_MODField())));
                row.setGRUPPO(Utils.convertToString(ds.parse(record.get("GRUPPO"), row.getGRUPPOField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @97-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @97-31069BBB
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "GRUPPO".equals(name) && "url".equals(prefix) ) {
                param = urlGRUPPO;
            } else if ( "GRUPPO".equals(name) && prefix == null ) {
                param = urlGRUPPO;
            }
            if ( "AD4GRUPPO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4GRUPPO;
            } else if ( "AD4GRUPPO".equals(name) && prefix == null ) {
                param = sesAD4GRUPPO;
            }
            if ( "SE_NUOVO".equals(name) && "url".equals(prefix) ) {
                param = urlSE_NUOVO;
            } else if ( "SE_NUOVO".equals(name) && prefix == null ) {
                param = urlSE_NUOVO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @97-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @97-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @97-238A81BB
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

//fireBeforeExecuteSelectEvent @97-9DA7B025
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

//fireAfterExecuteSelectEvent @97-F7E8A616
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

//class DataObject Tail @97-ED3F53A4
} // End of class DS
//End class DataObject Tail

