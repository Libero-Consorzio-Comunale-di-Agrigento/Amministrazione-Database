//se_rigenerata_SO DataSource @28-6B11442B
package ad4web.AD4GruppiTree;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End se_rigenerata_SO DataSource

//class DataObject Header @28-CDD6B518
public class se_rigenerata_SODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @28-A9A79E32
    

    LongField sesAD4JOBRIGSO = new LongField(null, null);
    
    LongField sesAD4JOBLDAPFROMSO = new LongField(null, null);
    

    private se_rigenerata_SORow row = new se_rigenerata_SORow();

//End attributes of DataObject

//properties of DataObject @28-D0CA1F66

    public void  setSesAD4JOBRIGSO( long param ) {
        this.sesAD4JOBRIGSO.setValue( param );
    }

    public void  setSesAD4JOBRIGSO( long param, Format ignore ) throws java.text.ParseException {
        this.sesAD4JOBRIGSO.setValue( param );
    }

    public void  setSesAD4JOBRIGSO( Object param, Format format ) throws java.text.ParseException {
        this.sesAD4JOBRIGSO.setValue( param, format );
    }

    public void  setSesAD4JOBRIGSO( Long param ) {
        this.sesAD4JOBRIGSO.setValue( param );
    }

    public void  setSesAD4JOBLDAPFROMSO( long param ) {
        this.sesAD4JOBLDAPFROMSO.setValue( param );
    }

    public void  setSesAD4JOBLDAPFROMSO( long param, Format ignore ) throws java.text.ParseException {
        this.sesAD4JOBLDAPFROMSO.setValue( param );
    }

    public void  setSesAD4JOBLDAPFROMSO( Object param, Format format ) throws java.text.ParseException {
        this.sesAD4JOBLDAPFROMSO.setValue( param, format );
    }

    public void  setSesAD4JOBLDAPFROMSO( Long param ) {
        this.sesAD4JOBLDAPFROMSO.setValue( param );
    }

    public se_rigenerata_SORow getRow() {
        return row;
    }

    public void setRow( se_rigenerata_SORow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @28-2A5CD0D0
    public se_rigenerata_SODataObject(Page page) {
        super(page);
    }
//End constructor

//load @28-879FC210
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT {AD4JOBRIGSO} JOB_RIG_SO "
                    + "     ,  "
                    + "{AD4JOBLDAPFROMSO} JOB_LDAP_FROM_SO "
                    + "  FROM dual" );
        if ( sesAD4JOBRIGSO.getObjectValue() == null ) sesAD4JOBRIGSO.setValue( 0 );
        command.addParameter( "AD4JOBRIGSO", sesAD4JOBRIGSO, null );
        if ( sesAD4JOBLDAPFROMSO.getObjectValue() == null ) sesAD4JOBLDAPFROMSO.setValue( 0 );
        command.addParameter( "AD4JOBLDAPFROMSO", sesAD4JOBLDAPFROMSO, null );
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

//loadDataBind @28-DF97D8B4
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setJOB_RIG_SO(Utils.convertToString(ds.parse(record.get("JOB_RIG_SO"), row.getJOB_RIG_SOField())));
            row.setJOB_LDAP_FROM_SO(Utils.convertToString(ds.parse(record.get("JOB_LDAP_FROM_SO"), row.getJOB_LDAP_FROM_SOField())));
        }
//End loadDataBind

//End of load @28-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @28-E8CBB19C
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "AD4JOBRIGSO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4JOBRIGSO;
            } else if ( "AD4JOBRIGSO".equals(name) && prefix == null ) {
                param = sesAD4JOBRIGSO;
            }
            if ( "AD4JOBLDAPFROMSO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4JOBLDAPFROMSO;
            } else if ( "AD4JOBLDAPFROMSO".equals(name) && prefix == null ) {
                param = sesAD4JOBLDAPFROMSO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @28-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @28-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @28-305A023C
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

//fireBeforeExecuteSelectEvent @28-D00ACF95
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

//fireAfterExecuteSelectEvent @28-3BAD39CE
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

//fireBeforeBuildInsertEvent @28-FBA08B71
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

//fireBeforeExecuteInsertEvent @28-47AFA6A5
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

//fireAfterExecuteInsertEvent @28-E9CE95AE
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

//fireBeforeBuildSelectEvent @28-2405BE8B
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

//fireBeforeExecuteSelectEvent @28-E9DFF86B
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

//fireAfterExecuteSelectEvent @28-580A2987
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

//fireBeforeBuildSelectEvent @28-D021D0EA
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

//fireBeforeExecuteDeleteEvent @28-DD540FBB
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

//fireAfterExecuteDeleteEvent @28-2A6E2049
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

//class DataObject Tail @28-ED3F53A4
} // End of class DS
//End class DataObject Tail

