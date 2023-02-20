//USER_JOBS DataSource @56-42931018
package ad4web.AD4UserJob;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End USER_JOBS DataSource

//class DataObject Header @56-E3040EDD
public class USER_JOBSDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @56-AEAB55F1
    

    LongField ctrlJOB_NUM = new LongField(null, null);
    
    TextField ctrlBROKEN_JOB = new TextField(null, null);
    
    TextField postNEXT_DATE = new TextField(null, null);
    
    TextField ctrlWHAT_HIDDEN = new TextField(null, null);
    
    TextField postINTERVAL = new TextField(null, null);
    
    LongField urlID_JOB = new LongField(null, null);
    

    private USER_JOBSRow row = new USER_JOBSRow();

//End attributes of DataObject

//properties of DataObject @56-52D80224

    public void  setCtrlJOB_NUM( long param ) {
        this.ctrlJOB_NUM.setValue( param );
    }

    public void  setCtrlJOB_NUM( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlJOB_NUM.setValue( param );
    }

    public void  setCtrlJOB_NUM( Object param, Format format ) throws java.text.ParseException {
        this.ctrlJOB_NUM.setValue( param, format );
    }

    public void  setCtrlJOB_NUM( Long param ) {
        this.ctrlJOB_NUM.setValue( param );
    }

    public void  setCtrlBROKEN_JOB( String param ) {
        this.ctrlBROKEN_JOB.setValue( param );
    }

    public void  setCtrlBROKEN_JOB( Object param ) {
        this.ctrlBROKEN_JOB.setValue( param );
    }

    public void  setCtrlBROKEN_JOB( Object param, Format ignore ) {
        this.ctrlBROKEN_JOB.setValue( param );
    }

    public void  setPostNEXT_DATE( String param ) {
        this.postNEXT_DATE.setValue( param );
    }

    public void  setPostNEXT_DATE( Object param ) {
        this.postNEXT_DATE.setValue( param );
    }

    public void  setPostNEXT_DATE( Object param, Format ignore ) {
        this.postNEXT_DATE.setValue( param );
    }

    public void  setCtrlWHAT_HIDDEN( String param ) {
        this.ctrlWHAT_HIDDEN.setValue( param );
    }

    public void  setCtrlWHAT_HIDDEN( Object param ) {
        this.ctrlWHAT_HIDDEN.setValue( param );
    }

    public void  setCtrlWHAT_HIDDEN( Object param, Format ignore ) {
        this.ctrlWHAT_HIDDEN.setValue( param );
    }

    public void  setPostINTERVAL( String param ) {
        this.postINTERVAL.setValue( param );
    }

    public void  setPostINTERVAL( Object param ) {
        this.postINTERVAL.setValue( param );
    }

    public void  setPostINTERVAL( Object param, Format ignore ) {
        this.postINTERVAL.setValue( param );
    }

    public void  setUrlID_JOB( long param ) {
        this.urlID_JOB.setValue( param );
    }

    public void  setUrlID_JOB( long param, Format ignore ) throws java.text.ParseException {
        this.urlID_JOB.setValue( param );
    }

    public void  setUrlID_JOB( Object param, Format format ) throws java.text.ParseException {
        this.urlID_JOB.setValue( param, format );
    }

    public void  setUrlID_JOB( Long param ) {
        this.urlID_JOB.setValue( param );
    }

    public USER_JOBSRow getRow() {
        return row;
    }

    public void setRow( USER_JOBSRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @56-7703338A
    public USER_JOBSDataObject(Page page) {
        super(page);
    }
//End constructor

//load @56-7E67543A
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT \"USER_JOBS\".\"JOB\",    "
                    + "         \"USER_JOBS\".\"WHAT\",  "
                    + "   "
                    + "         TO_CHAR(\"USER_JOBS\".\"LAST_DATE\", 'dd/mm/yyyy hh24:mi:ss') LAST_DATE,  "
                    + "   "
                    + "         TO_CHAR(\"USER_JOBS\".\"THIS_DATE\", 'dd/mm/yyyy hh24:mi:ss') THIS_DATE,  "
                    + "   "
                    + "         LPAD(TO_CHAR(TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600)),2,'0')||':'|| "
                    + "         LPAD(TO_CHAR(TRUNC((\"USER_JOBS\".\"TOTAL_TIME\" - TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600) * 3600) / 60)),2,'0')||':'|| "
                    + "	     LPAD(TO_CHAR(TRUNC(((\"USER_JOBS\".\"TOTAL_TIME\" - TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600) * 3600)) - TRUNC((\"USER_JOBS\".\"TOTAL_TIME\" - TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600) * 3600) / 60) * 60 )),2,'0')||' h' total_time, "
                    + "         TO_CHAR(\"USER_JOBS\".\"NEXT_DATE\", 'dd/mm/yyyy hh24:mi:ss') NEXT_DATE,    "
                    + "         decode(\"USER_JOBS\".\"BROKEN\",'Y','<font color=\"#ff0000\">Bloccata</font>', 'Non Bloccata') BROKEN,    "
                    + "         \"USER_JOBS\".\"FAILURES\",    "
                    + "         \"USER_JOBS\".\"INTERVAL\",    "
                    + "         \"USER_JOBS\".\"SCHEMA_USER\", "
                    + "         \"USER_JOBS\".\"BROKEN\" BROKEN_JOB "
                    + "    FROM \"USER_JOBS\"     "
                    + "   where \"USER_JOBS\".\"JOB\" = {ID_JOB} "
                    + "" );
        if ( urlID_JOB.getObjectValue() == null ) urlID_JOB.setValue( 0 );
        command.addParameter( "ID_JOB", urlID_JOB, null );
        if ( ! command.isSetAllParams() ) {
            empty = true;
            ds.closeConnection();
            return true;
        }
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "\"USER_JOBS\".\"THIS_DATE\" ASC, \"USER_JOBS\".\"NEXT_DATE\" ASC " );
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

//loadDataBind @56-41D91694
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setJOB(Utils.convertToString(ds.parse(record.get("JOB"), row.getJOBField())));
            row.setWHAT(Utils.convertToString(ds.parse(record.get("WHAT"), row.getWHATField())));
            row.setWHAT_HIDDEN(Utils.convertToString(ds.parse(record.get("WHAT"), row.getWHAT_HIDDENField())));
            row.setJOB_NUM(Utils.convertToLong(ds.parse(record.get("JOB"), row.getJOB_NUMField())));
            row.setNEXT_DATE(Utils.convertToString(ds.parse(record.get("NEXT_DATE"), row.getNEXT_DATEField())));
            row.setBROKEN_JOB(Utils.convertToString(ds.parse(record.get("BROKEN_JOB"), row.getBROKEN_JOBField())));
            row.setBROKEN(Utils.convertToString(ds.parse(record.get("BROKEN"), row.getBROKENField())));
            row.setINTERVAL(Utils.convertToString(ds.parse(record.get("INTERVAL"), row.getINTERVALField())));
        }
//End loadDataBind

//End of load @56-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @56-0F049A4A
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ call JOB_UTILITY.MODIFICA ( ?, ?, ?, ? ) }" );
            command.addParameter( "P_JOB", row.getJOB_NUMField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getWHAT_HIDDEN()) ) row.setWHAT_HIDDEN( "" );
            command.addParameter( "P_WHAT", row.getWHAT_HIDDENField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNEXT_DATE.getObjectValue() ) ) postNEXT_DATE.setValue( "" );
            command.addParameter( "P_NEXT_DATE", postNEXT_DATE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postINTERVAL.getObjectValue() ) ) postINTERVAL.setValue( "" );
            command.addParameter( "P_INTERVAL", postINTERVAL, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @56-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @56-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @56-EC73D4DE
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ call JOB_UTILITY.BLOCCA ( ?, ?, ? ) }" );
            command.addParameter( "P_JOB", row.getJOB_NUMField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getBROKEN_JOB()) ) row.setBROKEN_JOB( "" );
            command.addParameter( "P_BROKEN", row.getBROKEN_JOBField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNEXT_DATE.getObjectValue() ) ) postNEXT_DATE.setValue( "" );
            command.addParameter( "P_NEXT_DATE", postNEXT_DATE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @56-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @56-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @56-6898C5F7
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "JOB_NUM".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlJOB_NUM;
            } else if ( "JOB_NUM".equals(name) && prefix == null ) {
                param = ctrlJOB_NUM;
            }
            if ( "BROKEN_JOB".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlBROKEN_JOB;
            } else if ( "BROKEN_JOB".equals(name) && prefix == null ) {
                param = ctrlBROKEN_JOB;
            }
            if ( "NEXT_DATE".equals(name) && "post".equals(prefix) ) {
                param = postNEXT_DATE;
            } else if ( "NEXT_DATE".equals(name) && prefix == null ) {
                param = postNEXT_DATE;
            }
            if ( "WHAT_HIDDEN".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlWHAT_HIDDEN;
            } else if ( "WHAT_HIDDEN".equals(name) && prefix == null ) {
                param = ctrlWHAT_HIDDEN;
            }
            if ( "INTERVAL".equals(name) && "post".equals(prefix) ) {
                param = postINTERVAL;
            } else if ( "INTERVAL".equals(name) && prefix == null ) {
                param = postINTERVAL;
            }
            if ( "ID_JOB".equals(name) && "url".equals(prefix) ) {
                param = urlID_JOB;
            } else if ( "ID_JOB".equals(name) && prefix == null ) {
                param = urlID_JOB;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @56-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @56-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @56-305A023C
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

//fireBeforeExecuteSelectEvent @56-D00ACF95
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

//fireAfterExecuteSelectEvent @56-3BAD39CE
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

//fireBeforeBuildInsertEvent @56-FBA08B71
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

//fireBeforeExecuteInsertEvent @56-47AFA6A5
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

//fireAfterExecuteInsertEvent @56-E9CE95AE
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

//fireBeforeBuildSelectEvent @56-2405BE8B
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

//fireBeforeExecuteSelectEvent @56-E9DFF86B
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

//fireAfterExecuteSelectEvent @56-580A2987
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

//fireBeforeBuildSelectEvent @56-D021D0EA
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

//fireBeforeExecuteDeleteEvent @56-DD540FBB
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

//fireAfterExecuteDeleteEvent @56-2A6E2049
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

//class DataObject Tail @56-ED3F53A4
} // End of class DS
//End class DataObject Tail

