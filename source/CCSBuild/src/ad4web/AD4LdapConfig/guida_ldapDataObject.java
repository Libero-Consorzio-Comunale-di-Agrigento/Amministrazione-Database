//guida_ldap DataSource @83-B39E5070
package ad4web.AD4LdapConfig;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End guida_ldap DataSource

//class DataObject Header @83-B48C3E8B
public class guida_ldapDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @83-8FD45620
    

    TextField urlChiave = new TextField(null, null);
    
    TextField sesAD4LDAPCA = new TextField(null, null);
    

    private guida_ldapRow[] rows = new guida_ldapRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @83-1EED42A2

    public void  setUrlChiave( String param ) {
        this.urlChiave.setValue( param );
    }

    public void  setUrlChiave( Object param ) {
        this.urlChiave.setValue( param );
    }

    public void  setUrlChiave( Object param, Format ignore ) {
        this.urlChiave.setValue( param );
    }

    public void  setSesAD4LDAPCA( String param ) {
        this.sesAD4LDAPCA.setValue( param );
    }

    public void  setSesAD4LDAPCA( Object param ) {
        this.sesAD4LDAPCA.setValue( param );
    }

    public void  setSesAD4LDAPCA( Object param, Format ignore ) {
        this.sesAD4LDAPCA.setValue( param );
    }

    public guida_ldapRow[] getRows() {
        return rows;
    }

    public void setRows(guida_ldapRow[] rows) {
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

//constructor @83-4B8215B3
    public guida_ldapDataObject(Page page) {
        super(page);
    }
//End constructor

//load @83-1A5AFB62
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select ad4web.get_guida_ldap(nvl('{AD4LDAPCA}','{chiave}')) guidaLDAP "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlChiave.getObjectValue() ) ) urlChiave.setValue( "PRODUCTS/LDAPCONFIG" );
        command.addParameter( "chiave", urlChiave, null );
        if ( StringUtils.isEmpty( (String) sesAD4LDAPCA.getObjectValue() ) ) sesAD4LDAPCA.setValue( "" );
        command.addParameter( "AD4LDAPCA", sesAD4LDAPCA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select ad4web.get_guida_ldap(nvl('{AD4LDAPCA}','{chiave}')) guidaLDAP from dual ) cnt " );
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

//loadDataBind @83-FCCBDC62
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                guida_ldapRow row = new guida_ldapRow();
                DbRow record = (DbRow) records.nextElement();
                row.setGuidaLDAP(Utils.convertToString(ds.parse(record.get("GUIDALDAP"), row.getGuidaLDAPField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @83-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @83-3367B1A0
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "chiave".equals(name) && "url".equals(prefix) ) {
                param = urlChiave;
            } else if ( "chiave".equals(name) && prefix == null ) {
                param = urlChiave;
            }
            if ( "AD4LDAPCA".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4LDAPCA;
            } else if ( "AD4LDAPCA".equals(name) && prefix == null ) {
                param = sesAD4LDAPCA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @83-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @83-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @83-238A81BB
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

//fireBeforeExecuteSelectEvent @83-9DA7B025
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

//fireAfterExecuteSelectEvent @83-F7E8A616
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

//class DataObject Tail @83-ED3F53A4
} // End of class DS
//End class DataObject Tail

