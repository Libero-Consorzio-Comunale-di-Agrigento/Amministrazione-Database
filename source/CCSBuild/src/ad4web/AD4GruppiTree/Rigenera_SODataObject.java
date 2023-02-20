//Rigenera_SO DataSource @22-6B11442B
package ad4web.AD4GruppiTree;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End Rigenera_SO DataSource

//class DataObject Header @22-E2DC53B6
public class Rigenera_SODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @22-BD286B3A
    

    private Rigenera_SORow[] rows = new Rigenera_SORow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @22-43BABDC3

    public Rigenera_SORow[] getRows() {
        return rows;
    }

    public void setRows(Rigenera_SORow[] rows) {
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

//constructor @22-5807EEB4
    public Rigenera_SODataObject(Page page) {
        super(page);
    }
//End constructor

//load @22-0294F8D6
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT   decode "
                    + "            (count (1) "
                    + "           , 0, '' "
                    + "           ,  "
                    + "'<a class=\"AFCToolBox\" title=\"Rigenera Struttura Organizzativa.\" href=\"javascript:confermaRigenerazione()\"><img title=\"Rigenera Struttura Organizzativa\" height=\"18\" src=\"../common/images/AD4/home.gif\" width=\"18\" border=\"0\">Rigenera S.O.</a>' "
                    + "            ) RIGENERA "
                    + "       , decode "
                    + "            (nvl "
                    + "                (lower (REGISTRO_UTILITY.leggi_stringa ('PRODUCTS/LDAPCONFIG' "
                    + "                                                      , 'LdapMapping' "
                    + "                                                      , 0 "
                    + "                                                       ) "
                    + "                       ) "
                    + "               , 'no' "
                    + "                ) "
                    + "           , 'no', '' "
                    + "           , decode (v.db_ver, -1, '', '<a class=\"AFCToolBox\" title=\"Allinea Ldap a Struttura Organizzativa.\" href=\"javascript:allineaLdap()\"><img title=\"Allinea Ldap a Struttura Organizzativa\" height=\"18\" src=\"../common/images/AD4/allineaLdap.GIF\" width=\"18\" border=\"0\">Allinea Ldap a S.O.</a>') "
                    + "            ) ALLINEA_LDAP "
                    + "    FROM USER_OBJECTS "
                    + "       , (select sign "
                    + "                    (  to_number "
                    + "                              (substr (substr (banner "
                    + "                                             ,   instr (upper (banner) "
                    + "                                                      , 'RELEASE' "
                    + "                                                       ) "
                    + "                                               + 8 "
                    + "                                              ) "
                    + "                                     , 1 "
                    + "                                     ,   instr "
                    + "                                              (substr (banner "
                    + "                                                     ,   instr (upper (banner) "
                    + "                                                              , 'RELEASE' "
                    + "                                                               ) "
                    + "                                                       + 8 "
                    + "                                                      ) "
                    + "                                             , '.' "
                    + "                                              ) "
                    + "                                       - 1 "
                    + "                                      ) "
                    + "                              ) "
                    + "                     - 10 "
                    + "                    ) db_ver "
                    + "            from v$version "
                    + "           where upper (banner) like '%ORACLE%') v "
                    + "   WHERE object_name = 'SO4_UTIL' AND object_type = 'PACKAGE BODY' "
                    + "group by v.db_ver" );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT decode (count (1) , 0, '' ,  "
                                                         + "            '<a class=\"AFCToolBox\" title=\"Rigenera Struttura Organizzativa.\" href=\"javascript:confermaRigenerazione()\"><img title=\"Rigenera Struttura Organizzativa\" height=\"18\" src=\"../common/images/AD4/home.gif\" width=\"18\" border=\"0\">Rigenera S.O.</a>' ) RIGENERA , decode (nvl (lower (REGISTRO_UTILITY.leggi_stringa ('PRODUCTS/LDAPCONFIG' , 'LdapMapping' , 0 ) ) , 'no' ) , 'no', '' , decode (v.db_ver, -1, '', '<a class=\"AFCToolBox\" title=\"Allinea Ldap a Struttura Organizzativa.\" href=\"javascript:allineaLdap()\"><img title=\"Allinea Ldap a Struttura Organizzativa\" height=\"18\" src=\"../common/images/AD4/allineaLdap.GIF\" width=\"18\" border=\"0\">Allinea Ldap a S.O.</a>') ) ALLINEA_LDAP FROM USER_OBJECTS , (select sign ( to_number (substr (substr (banner , instr (upper (banner) , 'RELEASE' ) + 8 ) , 1 , instr (substr (banner , instr (upper (banner) , 'RELEASE' ) + 8 ) , '.' ) - 1 ) ) - 10 ) db_ver from v$version where upper (banner) like '%ORACLE%') v WHERE object_name = 'SO4_UTIL' AND object_type = 'PACKAGE BODY' group by v.db_ver ) cnt " );
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

//loadDataBind @22-C0BCE9D6
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                Rigenera_SORow row = new Rigenera_SORow();
                DbRow record = (DbRow) records.nextElement();
                row.setRIGENERA(Utils.convertToString(ds.parse(record.get("RIGENERA"), row.getRIGENERAField())));
                row.setALLINEA_LDAP(Utils.convertToString(ds.parse(record.get("ALLINEA_LDAP"), row.getALLINEA_LDAPField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @22-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @22-AE30C4C3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @22-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @22-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @22-238A81BB
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

//fireBeforeExecuteSelectEvent @22-9DA7B025
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

//fireAfterExecuteSelectEvent @22-F7E8A616
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

//class DataObject Tail @22-ED3F53A4
} // End of class DS
//End class DataObject Tail

