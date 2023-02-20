//MESSAGGIO_ERRORE DataSource @5-5BD9F9EC
package common.AmvError;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End MESSAGGIO_ERRORE DataSource

//class DataObject Header @5-7A9C58A8
public class MESSAGGIO_ERROREDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-05A9FDD9
    

    LongField urlMVERR = new LongField(null, null);
    
    TextField sesModulo = new TextField(null, null);
    

    private MESSAGGIO_ERRORERow[] rows = new MESSAGGIO_ERRORERow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @5-8C8F8CA7

    public void  setUrlMVERR( long param ) {
        this.urlMVERR.setValue( param );
    }

    public void  setUrlMVERR( long param, Format ignore ) throws java.text.ParseException {
        this.urlMVERR.setValue( param );
    }

    public void  setUrlMVERR( Object param, Format format ) throws java.text.ParseException {
        this.urlMVERR.setValue( param, format );
    }

    public void  setUrlMVERR( Long param ) {
        this.urlMVERR.setValue( param );
    }

    public void  setSesModulo( String param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param, Format ignore ) {
        this.sesModulo.setValue( param );
    }

    public MESSAGGIO_ERRORERow[] getRows() {
        return rows;
    }

    public void setRows(MESSAGGIO_ERRORERow[] rows) {
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

//constructor @5-47A8D020
    public MESSAGGIO_ERROREDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-4FE2698F
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT decode({MVERR} "
                    + ",400 ,'Errore sulla Richiesta'  "
                    + ",401 ,'Richiesta Autorizzazione'  "
                    + ",403 ,'Accesso negato' "
                    + ",404 ,'Pagina non trovata'  "
                    + ",405 ,'Metodo non permesso'  "
                    + ",407 ,'Richiesta Autenticazione Proxy' "
                    + ",408 ,'Time Out scaduto'  "
                    + ",414 ,'Indirizzo richiesto troppo lungo'  "
                    + ",415 ,'Tipo di Media non supportato'  "
                    + ",500 ,'Errore Interno al Server'  "
                    + ",501 ,'Non Implementato'  "
                    + ",503 ,'Servizio non disponibile'  "
                    + ",'Servizio non disponibile') MSG "
                    + ",  "
                    + "amvweb.get_preferenza('Messaggio Pagina Errore','{Modulo}') custom_msg "
                    + "from dual" );
        if ( urlMVERR.getObjectValue() == null ) urlMVERR.setValue( 0 );
        command.addParameter( "MVERR", urlMVERR, null );
        if ( StringUtils.isEmpty( (String) sesModulo.getObjectValue() ) ) sesModulo.setValue( "" );
        command.addParameter( "Modulo", sesModulo, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT decode({MVERR} ,400 ,'Errore sulla Richiesta' ,401 ,'Richiesta Autorizzazione' ,403 ,'Accesso negato' ,404 ,'Pagina non trovata' ,405 ,'Metodo non permesso' ,407 ,'Richiesta Autenticazione Proxy' ,408 ,'Time Out scaduto' ,414 ,'Indirizzo richiesto troppo lungo' ,415 ,'Tipo di Media non supportato' ,500 ,'Errore Interno al Server' ,501 ,'Non Implementato' ,503 ,'Servizio non disponibile' ,'Servizio non disponibile') MSG ,  "
                                                         + "            amvweb.get_preferenza('Messaggio Pagina Errore','{Modulo}') custom_msg from dual ) cnt " );
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

//loadDataBind @5-C03185FE
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                MESSAGGIO_ERRORERow row = new MESSAGGIO_ERRORERow();
                DbRow record = (DbRow) records.nextElement();
                row.setMSG(Utils.convertToString(ds.parse(record.get("MSG"), row.getMSGField())));
                row.setCUSTOM_MSG(Utils.convertToString(ds.parse(record.get("CUSTOM_MSG"), row.getCUSTOM_MSGField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @5-E32FC09C
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "MVERR".equals(name) && "url".equals(prefix) ) {
                param = urlMVERR;
            } else if ( "MVERR".equals(name) && prefix == null ) {
                param = urlMVERR;
            }
            if ( "Modulo".equals(name) && "ses".equals(prefix) ) {
                param = sesModulo;
            } else if ( "Modulo".equals(name) && prefix == null ) {
                param = sesModulo;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @5-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @5-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @5-238A81BB
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

//fireBeforeExecuteSelectEvent @5-9DA7B025
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

//fireAfterExecuteSelectEvent @5-F7E8A616
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

//class DataObject Tail @5-ED3F53A4
} // End of class DS
//End class DataObject Tail

