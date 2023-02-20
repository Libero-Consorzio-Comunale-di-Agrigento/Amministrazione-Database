//user_jobs DataSource @6-AF0AADA8
package ad4web.AD4UsjoElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End user_jobs DataSource

//class DataObject Header @6-D30312D4
public class user_jobsDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-C15173CC
    

    private user_jobsRow[] rows = new user_jobsRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @6-4278054F

    public user_jobsRow[] getRows() {
        return rows;
    }

    public void setRows(user_jobsRow[] rows) {
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

//constructor @6-AB1A7178
    public user_jobsDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-1A9346CA
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
                    + "         decode(\"USER_JOBS\".\"BROKEN\",'Y','Sì', 'No') BROKEN,    "
                    + "         \"USER_JOBS\".\"FAILURES\",    "
                    + "         \"USER_JOBS\".\"INTERVAL\",    "
                    + "         \"USER_JOBS\".\"SCHEMA_USER\" "
                    + "    FROM \"USER_JOBS\"      "
                    + "" );
        command.setCountSql( "SELECT COUNT(*) FROM (  SELECT \"USER_JOBS\".\"JOB\", \"USER_JOBS\".\"WHAT\",  "
                                                         + "            TO_CHAR(\"USER_JOBS\".\"LAST_DATE\", 'dd/mm/yyyy hh24:mi:ss') LAST_DATE,  "
                                                         + "            TO_CHAR(\"USER_JOBS\".\"THIS_DATE\", 'dd/mm/yyyy hh24:mi:ss') THIS_DATE,  "
                                                         + "            LPAD(TO_CHAR(TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600)),2,'0')||':'|| LPAD(TO_CHAR(TRUNC((\"USER_JOBS\".\"TOTAL_TIME\" - TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600) * 3600) / 60)),2,'0')||':'|| 	 LPAD(TO_CHAR(TRUNC(((\"USER_JOBS\".\"TOTAL_TIME\" - TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600) * 3600)) - TRUNC((\"USER_JOBS\".\"TOTAL_TIME\" - TRUNC(\"USER_JOBS\".\"TOTAL_TIME\" / 3600) * 3600) / 60) * 60 )),2,'0')||' h' total_time, TO_CHAR(\"USER_JOBS\".\"NEXT_DATE\", 'dd/mm/yyyy hh24:mi:ss') NEXT_DATE, decode(\"USER_JOBS\".\"BROKEN\",'Y','Sì', 'No') BROKEN, \"USER_JOBS\".\"FAILURES\", \"USER_JOBS\".\"INTERVAL\", \"USER_JOBS\".\"SCHEMA_USER\" FROM \"USER_JOBS\"  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "\"USER_JOBS\".\"THIS_DATE\" ASC, \"USER_JOBS\".\"NEXT_DATE\" ASC " );
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

//loadDataBind @6-20166FB8
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                user_jobsRow row = new user_jobsRow();
                DbRow record = (DbRow) records.nextElement();
                row.setJOB(Utils.convertToString(ds.parse(record.get("JOB"), row.getJOBField())));
                row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
                row.setWHAT(Utils.convertToString(ds.parse(record.get("WHAT"), row.getWHATField())));
                row.setTHIS_DATE(Utils.convertToString(ds.parse(record.get("THIS_DATE"), row.getTHIS_DATEField())));
                row.setNEXT_DATE(Utils.convertToString(ds.parse(record.get("NEXT_DATE"), row.getNEXT_DATEField())));
                row.setLAST_DATE(Utils.convertToString(ds.parse(record.get("LAST_DATE"), row.getLAST_DATEField())));
                row.setFAILURES(Utils.convertToString(ds.parse(record.get("FAILURES"), row.getFAILURESField())));
                row.setTOTAL_TIME(Utils.convertToString(ds.parse(record.get("TOTAL_TIME"), row.getTOTAL_TIMEField())));
                row.setINTERVAL(Utils.convertToString(ds.parse(record.get("INTERVAL"), row.getINTERVALField())));
                row.setBROKEN(Utils.convertToString(ds.parse(record.get("BROKEN"), row.getBROKENField())));
                row.setID_JOB(Utils.convertToString(ds.parse(record.get("JOB"), row.getID_JOBField())));
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

//getParameterByName @6-AE30C4C3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
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

