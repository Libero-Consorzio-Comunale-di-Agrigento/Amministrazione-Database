//ldap_config DataSource @110-B39E5070
package ad4web.AD4LdapConfig;

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

//attributes of DataObject @110-51CC531B
    

    TextField urlChiave = new TextField(null, null);
    
    TextField sesAD4LDAPCA = new TextField(null, null);
    

    private ldap_configRow[] rows = new ldap_configRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @110-5E000292

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

//load @110-344E0504
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT \"REGISTRO\".\"CHIAVE\",    "
                    + "         \"REGISTRO\".\"STRINGA\",  "
                    + "   "
                    + "         \"REGISTRO\".\"VALORE\",    "
                    + "         \"REGISTRO\".\"COMMENTO\",  "
                    + "   "
                    + "         nvl(Registro_Utility.get_sequenza_ordinamento('PRODUCTS/LDAPCONFIG',\"REGISTRO\".\"STRINGA\"),999999999) sequenza_ordinamento "
                    + "    FROM \"REGISTRO\"   "
                    + "   WHERE ( \"REGISTRO\".\"CHIAVE\" = nvl('{AD4LDAPCA}','{chiave}') ) AND   "
                    + "         ( \"REGISTRO\".\"STRINGA\" <> '(Predefinito)' )    "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlChiave.getObjectValue() ) ) urlChiave.setValue( "PRODUCTS/LDAPCONFIG" );
        command.addParameter( "chiave", urlChiave, null );
        if ( StringUtils.isEmpty( (String) sesAD4LDAPCA.getObjectValue() ) ) sesAD4LDAPCA.setValue( "" );
        command.addParameter( "AD4LDAPCA", sesAD4LDAPCA, null );
        command.setCountSql( "SELECT COUNT(*) FROM (  SELECT \"REGISTRO\".\"CHIAVE\", \"REGISTRO\".\"STRINGA\",  "
                                                         + "            \"REGISTRO\".\"VALORE\", \"REGISTRO\".\"COMMENTO\",  "
                                                         + "            nvl(Registro_Utility.get_sequenza_ordinamento('PRODUCTS/LDAPCONFIG',\"REGISTRO\".\"STRINGA\"),999999999) sequenza_ordinamento FROM \"REGISTRO\" WHERE ( \"REGISTRO\".\"CHIAVE\" = nvl('{AD4LDAPCA}','{chiave}') ) AND ( \"REGISTRO\".\"STRINGA\" <> '(Predefinito)' )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "5 ASC, \"REGISTRO\".\"CHIAVE\" ASC, \"REGISTRO\".\"STRINGA\" ASC " );
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

//loadDataBind @110-F137D86E
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ldap_configRow row = new ldap_configRow();
                DbRow record = (DbRow) records.nextElement();
                row.setSTRINGA(Utils.convertToString(ds.parse(record.get("STRINGA"), row.getSTRINGAField())));
                row.setVALORE(Utils.convertToString(ds.parse(record.get("VALORE"), row.getVALOREField())));
                row.setCOMMENTO(Utils.convertToString(ds.parse(record.get("COMMENTO"), row.getCOMMENTOField())));
                row.setID(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getIDField())));
                row.setCHIAVE(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getCHIAVEField())));
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

//getParameterByName @110-3367B1A0
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

