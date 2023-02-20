//guida_ldapconfig DataSource @137-B39E5070
package ad4web.AD4LdapConfig;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End guida_ldapconfig DataSource

//class DataObject Header @137-606FB74F
public class guida_ldapconfigDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @137-7786AB2A
    

    LongField urlId = new LongField(null, null);
    

    private guida_ldapconfigRow[] rows = new guida_ldapconfigRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @137-ABE16BCB

    public void  setUrlId( long param ) {
        this.urlId.setValue( param );
    }

    public void  setUrlId( long param, Format ignore ) throws java.text.ParseException {
        this.urlId.setValue( param );
    }

    public void  setUrlId( Object param, Format format ) throws java.text.ParseException {
        this.urlId.setValue( param, format );
    }

    public void  setUrlId( Long param ) {
        this.urlId.setValue( param );
    }

    public guida_ldapconfigRow[] getRows() {
        return rows;
    }

    public void setRows(guida_ldapconfigRow[] rows) {
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

//constructor @137-DB6890FC
    public guida_ldapconfigDataObject(Page page) {
        super(page);
    }
//End constructor

//load @137-D9108A88
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select ad4web.GET_GUIDA_LDAPCONFIG({id}) GUIDALDAPC "
                    + "  from dual" );
        if ( urlId.getObjectValue() == null ) urlId.setValue( 0 );
        command.addParameter( "id", urlId, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select ad4web.GET_GUIDA_LDAPCONFIG({id}) GUIDALDAPC from dual ) cnt " );
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

//loadDataBind @137-E5CB0507
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                guida_ldapconfigRow row = new guida_ldapconfigRow();
                DbRow record = (DbRow) records.nextElement();
                row.setGUIDALDAPC(Utils.convertToString(ds.parse(record.get("GUIDALDAPC"), row.getGUIDALDAPCField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @137-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @137-14DAAA59
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "id".equals(name) && "url".equals(prefix) ) {
                param = urlId;
            } else if ( "id".equals(name) && prefix == null ) {
                param = urlId;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @137-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @137-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @137-238A81BB
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

//fireBeforeExecuteSelectEvent @137-9DA7B025
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

//fireAfterExecuteSelectEvent @137-F7E8A616
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

//class DataObject Tail @137-ED3F53A4
} // End of class DS
//End class DataObject Tail

