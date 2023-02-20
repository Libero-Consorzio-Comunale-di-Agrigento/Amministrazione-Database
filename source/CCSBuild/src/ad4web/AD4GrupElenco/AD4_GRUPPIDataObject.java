//AD4_GRUPPI DataSource @6-187DFA6B
package ad4web.AD4GrupElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_GRUPPI DataSource

//class DataObject Header @6-4373E337
public class AD4_GRUPPIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-E120F290
    

    TextField sesAD4UTENTE = new TextField(null, null);
    
    TextField urlMVAV = new TextField(null, null);
    
    TextField sesAD4GRUPPO = new TextField(null, null);
    
    TextField sesMVIDP = new TextField(null, null);
    
    TextField sesMVAV = new TextField(null, null);
    

    private AD4_GRUPPIRow[] rows = new AD4_GRUPPIRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @6-8B9387BD

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

    public void  setSesMVIDP( String param ) {
        this.sesMVIDP.setValue( param );
    }

    public void  setSesMVIDP( Object param ) {
        this.sesMVIDP.setValue( param );
    }

    public void  setSesMVIDP( Object param, Format ignore ) {
        this.sesMVIDP.setValue( param );
    }

    public void  setSesMVAV( String param ) {
        this.sesMVAV.setValue( param );
    }

    public void  setSesMVAV( Object param ) {
        this.sesMVAV.setValue( param );
    }

    public void  setSesMVAV( Object param, Format ignore ) {
        this.sesMVAV.setValue( param );
    }

    public AD4_GRUPPIRow[] getRows() {
        return rows;
    }

    public void setRows(AD4_GRUPPIRow[] rows) {
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

//constructor @6-31FF5E5A
    public AD4_GRUPPIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-07F0376C
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT '<img src=\"../common/images/AD4/'||DECODE(tipo_utente,'O','UniOrg','Gruppo')||'.gif\" BORDER=0><a class=\"AFCDataLink\" href=\"AD4Gruppo.do?GRUPPO='||UTGR.GRUPPO||'\">'||UTGR.GRUPPO||'</a>' gruppo "
                    + "       ,  "
                    + "UTEN.NOMINATIVO DESCRIZIONE "
                    + "       , UTEN.IMPORTANZA "
                    + "   FROM UTENTI uten "
                    + "      ,  "
                    + "UTENTI_GRUPPO utgr "
                    + "  WHERE UTEN.UTENTE = UTGR.GRUPPO "
                    + "    AND UTGR.UTENTE like decode('{TIPOUTENTE}','U','{AD4UTENTE}','G','{AD4GRUPPO}','M','%') "
                    + "    AND UTGR.GRUPPO <> 'ric_abil' "
                    + "" );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
        command.addParameter( "AD4UTENTE", sesAD4UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlMVAV.getObjectValue() ) ) urlMVAV.setValue( "" );
        command.addParameter( "TIPOUTENTE", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) sesAD4GRUPPO.getObjectValue() ) ) sesAD4GRUPPO.setValue( "" );
        command.addParameter( "AD4GRUPPO", sesAD4GRUPPO, null );
        if ( StringUtils.isEmpty( (String) sesMVIDP.getObjectValue() ) ) sesMVIDP.setValue( "" );
        command.addParameter( "MVIDP", sesMVIDP, null );
        if ( StringUtils.isEmpty( (String) sesMVAV.getObjectValue() ) ) sesMVAV.setValue( "" );
        command.addParameter( "MVAV", sesMVAV, null );
        command.setCountSql( "SELECT COUNT(*) FROM (  SELECT '<img src=\"../common/images/AD4/'||DECODE(tipo_utente,'O','UniOrg','Gruppo')||'.gif\" BORDER=0><a class=\"AFCDataLink\" href=\"AD4Gruppo.do?GRUPPO='||UTGR.GRUPPO||'\">'||UTGR.GRUPPO||'</a>' gruppo ,  "
                                                         + "            UTEN.NOMINATIVO DESCRIZIONE , UTEN.IMPORTANZA FROM UTENTI uten ,  "
                                                         + "            UTENTI_GRUPPO utgr WHERE UTEN.UTENTE = UTGR.GRUPPO AND UTGR.UTENTE like decode('{TIPOUTENTE}','U','{AD4UTENTE}','G','{AD4GRUPPO}','M','%') AND UTGR.GRUPPO <> 'ric_abil'  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "UTGR.GRUPPO ASC" );
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

//loadDataBind @6-4D0C1BDB
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_GRUPPIRow row = new AD4_GRUPPIRow();
                DbRow record = (DbRow) records.nextElement();
                row.setGRUPPO(Utils.convertToString(ds.parse(record.get("GRUPPO"), row.getGRUPPOField())));
                row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
                row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
                row.setIMPORTANZA(Utils.convertToString(ds.parse(record.get("IMPORTANZA"), row.getIMPORTANZAField())));
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

//getParameterByName @6-903D544F
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
            if ( "MVIDP".equals(name) && "ses".equals(prefix) ) {
                param = sesMVIDP;
            } else if ( "MVIDP".equals(name) && prefix == null ) {
                param = sesMVIDP;
            }
            if ( "MVAV".equals(name) && "ses".equals(prefix) ) {
                param = sesMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = sesMVAV;
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

