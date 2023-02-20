//LOGIN DataSource @2-531B75CA
package common.AmvLoginSecure;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End LOGIN DataSource

//class DataObject Header @2-A5266266
public class LOGINDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @2-4CEC5D74
    

    TextField urlMVERR = new TextField(null, null);
    
    TextField sesUsername = new TextField(null, null);
    
    TextField sesPassword = new TextField(null, null);
    

    private LOGINRow row = new LOGINRow();

//End attributes of DataObject

//properties of DataObject @2-91571911

    public void  setUrlMVERR( String param ) {
        this.urlMVERR.setValue( param );
    }

    public void  setUrlMVERR( Object param ) {
        this.urlMVERR.setValue( param );
    }

    public void  setUrlMVERR( Object param, Format ignore ) {
        this.urlMVERR.setValue( param );
    }

    public void  setSesUsername( String param ) {
        this.sesUsername.setValue( param );
    }

    public void  setSesUsername( Object param ) {
        this.sesUsername.setValue( param );
    }

    public void  setSesUsername( Object param, Format ignore ) {
        this.sesUsername.setValue( param );
    }

    public void  setSesPassword( String param ) {
        this.sesPassword.setValue( param );
    }

    public void  setSesPassword( Object param ) {
        this.sesPassword.setValue( param );
    }

    public void  setSesPassword( Object param, Format ignore ) {
        this.sesPassword.setValue( param );
    }

    public LOGINRow getRow() {
        return row;
    }

    public void setRow( LOGINRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @2-65F115DB
    public LOGINDataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new LOGINDataObjectHandler() );
    }
//End constructor

//load @2-906EDA1C
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT 'Accesso negato.<br>Autenticazione fallita.' ERRORE, "
                    + "       '' username, "
                    + "       '' password, "
                    + "       '' jslogin "
                    + "  FROM DUAL "
                    + " WHERE '{MVERR}' = 'S' "
                    + "UNION "
                    + "SELECT '' ERRORE, "
                    + "       '{username}' username, "
                    + "       '{password}' password, "
                    + "       '<script>document.LOGIN.submit();</script>' JSLOGIN "
                    + "  FROM DUAL "
                    + " WHERE '{username}' IS NOT NULL AND '{MVERR}' IS NULL" );
        if ( StringUtils.isEmpty( (String) urlMVERR.getObjectValue() ) ) urlMVERR.setValue( "" );
        command.addParameter( "MVERR", urlMVERR, null );
        if ( StringUtils.isEmpty( (String) sesUsername.getObjectValue() ) ) sesUsername.setValue( "" );
        command.addParameter( "username", sesUsername, null );
        if ( StringUtils.isEmpty( (String) sesPassword.getObjectValue() ) ) sesPassword.setValue( "" );
        command.addParameter( "password", sesPassword, null );
        if ( ! command.isSetAllParams() ) {
            empty = true;
            ds.closeConnection();
            return true;
        }
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        }

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        DbRow record = null;
        if ( ! ds.hasErrors() ) {
            record = command.getOneRow();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @2-AF6FFF96
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setERRORE(Utils.convertToString(ds.parse(record.get("ERRORE"), row.getERROREField())));
            row.setU(Utils.convertToString(ds.parse(record.get("USERNAME"), row.getUField())));
            row.setP(Utils.convertToString(ds.parse(record.get("PASSWORD"), row.getPField())));
            row.setJSLOGIN(Utils.convertToString(ds.parse(record.get("JSLOGIN"), row.getJSLOGINField())));
        }
//End loadDataBind

//End of load @2-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @2-EA62B41F
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "MVERR".equals(name) && "url".equals(prefix) ) {
                param = urlMVERR;
            } else if ( "MVERR".equals(name) && prefix == null ) {
                param = urlMVERR;
            }
            if ( "username".equals(name) && "ses".equals(prefix) ) {
                param = sesUsername;
            } else if ( "username".equals(name) && prefix == null ) {
                param = sesUsername;
            }
            if ( "password".equals(name) && "ses".equals(prefix) ) {
                param = sesPassword;
            } else if ( "password".equals(name) && prefix == null ) {
                param = sesPassword;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @2-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @2-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @2-305A023C
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @2-D00ACF95
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @2-3BAD39CE
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildInsertEvent @2-FBA08B71
    public void fireBeforeBuildInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildInsert(e);
        }
    }
//End fireBeforeBuildInsertEvent

//fireBeforeExecuteInsertEvent @2-47AFA6A5
    public void fireBeforeExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteInsert(e);
        }
    }
//End fireBeforeExecuteInsertEvent

//fireAfterExecuteInsertEvent @2-E9CE95AE
    public void fireAfterExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteInsert(e);
        }
    }
//End fireAfterExecuteInsertEvent

//fireBeforeBuildSelectEvent @2-2405BE8B
    public void fireBeforeBuildUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildUpdate(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @2-E9DFF86B
    public void fireBeforeExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteUpdate(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @2-580A2987
    public void fireAfterExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteUpdate(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildSelectEvent @2-D021D0EA
    public void fireBeforeBuildDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildDelete(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteDeleteEvent @2-DD540FBB
    public void fireBeforeExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteDelete(e);
        }
    }
//End fireBeforeExecuteDeleteEvent

//fireAfterExecuteDeleteEvent @2-2A6E2049
    public void fireAfterExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteDelete(e);
        }
    }
//End fireAfterExecuteDeleteEvent

//class DataObject Tail @2-ED3F53A4
} // End of class DS
//End class DataObject Tail

