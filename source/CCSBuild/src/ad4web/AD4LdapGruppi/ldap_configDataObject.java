//ldap_config DataSource @110-07126ADA
package ad4web.AD4LdapGruppi;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ldap_config DataSource

//class DataObject Header @110-C464E9C0
public class ldap_configDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @110-9F3F2AEA
    

    private ldap_configRow[] rows = new ldap_configRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @110-94D7815C

    public ldap_configRow[] getRows() {
        return rows;
    }

    public void setRows(ldap_configRow[] rows) {
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

//constructor @110-CB2DD2D7
    public ldap_configDataObject(Page page) {
        super(page);
    }
//End constructor

//load @110-5B0FBDFE
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT \"REGISTRO\".\"CHIAVE\",  "
                    + "   "
                    + "         \"REGISTRO\".\"STRINGA\", "
                    + "         \"UTENTI\".\"NOMINATIVO\"||' ('||\"REGISTRO\".\"STRINGA\"||')' gruppo_ad4,  "
                    + "   "
                    + "         \"REGISTRO\".\"VALORE\" "
                    + "    FROM \"REGISTRO\",  "
                    + "   "
                    + "         \"UTENTI\"   "
                    + "   WHERE ( registro.stringa = utenti.utente (+)) and   "
                    + "         ( ( \"REGISTRO\".\"CHIAVE\" = 'PRODUCTS/LDAPCONFIG/GRUPPI' ) AND   "
                    + "         ( \"REGISTRO\".\"STRINGA\" <> '(Predefinito)' ) )    " );
        command.setCountSql( "SELECT COUNT(*) FROM (  SELECT \"REGISTRO\".\"CHIAVE\", \"REGISTRO\".\"STRINGA\",  "
                                                         + "            \"UTENTI\".\"NOMINATIVO\"||' ('||\"REGISTRO\".\"STRINGA\"||')' gruppo_ad4,  "
                                                         + "            \"REGISTRO\".\"VALORE\" FROM \"REGISTRO\",  "
                                                         + "            \"UTENTI\" WHERE ( registro.stringa = utenti.utente (+)) and ( ( \"REGISTRO\".\"CHIAVE\" = 'PRODUCTS/LDAPCONFIG/GRUPPI' ) AND ( \"REGISTRO\".\"STRINGA\" <> '(Predefinito)' ) )  ) cnt " );
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

//loadDataBind @110-570EA6B1
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ldap_configRow row = new ldap_configRow();
                DbRow record = (DbRow) records.nextElement();
                row.setGRUPPO_AD4(Utils.convertToString(ds.parse(record.get("GRUPPO_AD4"), row.getGRUPPO_AD4Field())));
                row.setVALORE(Utils.convertToString(ds.parse(record.get("VALORE"), row.getVALOREField())));
                row.setSTRINGA(Utils.convertToString(ds.parse(record.get("STRINGA"), row.getSTRINGAField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @110-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @110-AE30C4C3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @110-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @110-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @110-238A81BB
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

//fireBeforeExecuteSelectEvent @110-9DA7B025
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

//fireAfterExecuteSelectEvent @110-F7E8A616
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

//class DataObject Tail @110-ED3F53A4
} // End of class DS
//End class DataObject Tail

