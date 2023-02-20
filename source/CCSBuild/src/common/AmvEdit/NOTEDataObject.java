//NOTE DataSource @2-90DE2F7C
package common.AmvEdit;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End NOTE DataSource

//class DataObject Header @2-FB5EA330
public class NOTEDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @2-619BEC70
    

    TextField urlTITLE = new TextField(null, null);
    
    TextField urlVALUE = new TextField(null, null);
    
    TextField postMOSTRA = new TextField(null, null);
    

    private NOTERow row = new NOTERow();

//End attributes of DataObject

//properties of DataObject @2-CE4DC52D

    public void  setUrlTITLE( String param ) {
        this.urlTITLE.setValue( param );
    }

    public void  setUrlTITLE( Object param ) {
        this.urlTITLE.setValue( param );
    }

    public void  setUrlTITLE( Object param, Format ignore ) {
        this.urlTITLE.setValue( param );
    }

    public void  setUrlVALUE( String param ) {
        this.urlVALUE.setValue( param );
    }

    public void  setUrlVALUE( Object param ) {
        this.urlVALUE.setValue( param );
    }

    public void  setUrlVALUE( Object param, Format ignore ) {
        this.urlVALUE.setValue( param );
    }

    public void  setPostMOSTRA( String param ) {
        this.postMOSTRA.setValue( param );
    }

    public void  setPostMOSTRA( Object param ) {
        this.postMOSTRA.setValue( param );
    }

    public void  setPostMOSTRA( Object param, Format ignore ) {
        this.postMOSTRA.setValue( param );
    }

    public NOTERow getRow() {
        return row;
    }

    public void setRow( NOTERow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @2-58A7EB7D
    public NOTEDataObject(Page page) {
        super(page);
    }
//End constructor

//load @2-343D4206
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select '{TITLE}'titolo "
                    + ", '{VALUE}' valore "
                    + ",  "
                    + "decode(nvl('{MOSTRA}','NO'),'NO' "
                    + ",'<a href=\"javascript:showFileUpload();\"><img style=\"border:0\" src=\"../common/images/AMV/apri.gif\" alt=\"Apri...\"></a>' "
                    + ",'<input class=\"AFCInput\" type=\"file\" name=\"FILE_UPLOAD\" onClick=\"showFileUploadNetscape();\" onChange=\"showFileUpload();\" onfocus=\"onFocusLOV();\" onblur=\"cambia();\" >') input_file "
                    + ", nvl('{MOSTRA}','NO') mostra "
                    + "from dual" );
        if ( StringUtils.isEmpty( (String) urlTITLE.getObjectValue() ) ) urlTITLE.setValue( "" );
        command.addParameter( "TITLE", urlTITLE, null );
        if ( StringUtils.isEmpty( (String) urlVALUE.getObjectValue() ) ) urlVALUE.setValue( "" );
        command.addParameter( "VALUE", urlVALUE, null );
        if ( StringUtils.isEmpty( (String) postMOSTRA.getObjectValue() ) ) postMOSTRA.setValue( "" );
        command.addParameter( "MOSTRA", postMOSTRA, null );
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

//loadDataBind @2-6C43A753
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setTITOLO(Utils.convertToString(ds.parse(record.get("TITOLO"), row.getTITOLOField())));
            row.setINPUT_FILE(Utils.convertToString(ds.parse(record.get("INPUT_FILE"), row.getINPUT_FILEField())));
            row.setMOSTRA(Utils.convertToString(ds.parse(record.get("MOSTRA"), row.getMOSTRAField())));
        }
//End loadDataBind

//End of load @2-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @2-9183C187
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setSql( "null" );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @2-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @2-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//getParameterByName @2-07D8C35E
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "TITLE".equals(name) && "url".equals(prefix) ) {
                param = urlTITLE;
            } else if ( "TITLE".equals(name) && prefix == null ) {
                param = urlTITLE;
            }
            if ( "VALUE".equals(name) && "url".equals(prefix) ) {
                param = urlVALUE;
            } else if ( "VALUE".equals(name) && prefix == null ) {
                param = urlVALUE;
            }
            if ( "MOSTRA".equals(name) && "post".equals(prefix) ) {
                param = postMOSTRA;
            } else if ( "MOSTRA".equals(name) && prefix == null ) {
                param = postMOSTRA;
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
