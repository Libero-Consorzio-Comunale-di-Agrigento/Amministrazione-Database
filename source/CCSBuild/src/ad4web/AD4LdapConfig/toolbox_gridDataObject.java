//toolbox_grid DataSource @120-B39E5070
package ad4web.AD4LdapConfig;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End toolbox_grid DataSource

//class DataObject Header @120-9E29E2E2
public class toolbox_gridDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @120-7E8BA07A
    

    TextField urlChiave = new TextField(null, null);
    
    TextField sesAD4LDAPCA = new TextField(null, null);
    

    private toolbox_gridRow[] rows = new toolbox_gridRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @120-713D4F9D

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

    public toolbox_gridRow[] getRows() {
        return rows;
    }

    public void setRows(toolbox_gridRow[] rows) {
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

//constructor @120-7B0616D5
    public toolbox_gridDataObject(Page page) {
        super(page);
    }
//End constructor

//load @120-8655D82B
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT nvl('{AD4LDAPCA}','{chiave}') CHIAVE "
                    + "     ,  "
                    + "DECODE(nvl('{AD4LDAPCA}','{chiave}'),'PRODUCTS/LDAPCONFIG','principale','alternativo') tipo_server "
                    + "     ,  "
                    + "DECODE(nvl('{AD4LDAPCA}','{chiave}'),'PRODUCTS/LDAPCONFIG','<a class=\"AFCToolBox\" id=\"linkCreaAlt\" title=\"Crea configurazione nuovo server alternativo.\" href=\"javascript:confermaCrea()\"><img title=\"Crea configurazione nuovo server alternativo\" height=\"18\" src=\"../common/images/AMV/new.gif\" width=\"18\" border=\"0\">'||'Crea&nbsp;Alternativo</a>','') crea_alternativo "
                    + "     , DECODE(nvl('{AD4LDAPCA}','{chiave}'),'PRODUCTS/LDAPCONFIG','','<a class=\"AFCToolBox\" id=\"linkEliminaAlt\" title=\"Elimina configurazione server secondario.\" href=\"javascript:confermaElimina('''||nvl('{AD4LDAPCA}','{chiave}')||''')\"><img title=\"Elimina configurazione server alternativo\" height=\"18\" src=\"../common/images/AD4/elimina.gif\" width=\"18\" border=\"0\">'||'Elimina&nbsp;Alternativo</a>') elimina_alternativo "
                    + "  FROM dual" );
        if ( StringUtils.isEmpty( (String) urlChiave.getObjectValue() ) ) urlChiave.setValue( "PRODUCTS/LDAPCONFIG" );
        command.addParameter( "chiave", urlChiave, null );
        if ( StringUtils.isEmpty( (String) sesAD4LDAPCA.getObjectValue() ) ) sesAD4LDAPCA.setValue( "" );
        command.addParameter( "AD4LDAPCA", sesAD4LDAPCA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT nvl('{AD4LDAPCA}','{chiave}') CHIAVE ,  "
                                                         + "            DECODE(nvl('{AD4LDAPCA}','{chiave}'),'PRODUCTS/LDAPCONFIG','principale','alternativo') tipo_server ,  "
                                                         + "            DECODE(nvl('{AD4LDAPCA}','{chiave}'),'PRODUCTS/LDAPCONFIG','<a class=\"AFCToolBox\" id=\"linkCreaAlt\" title=\"Crea configurazione nuovo server alternativo.\" href=\"javascript:confermaCrea()\"><img title=\"Crea configurazione nuovo server alternativo\" height=\"18\" src=\"../common/images/AMV/new.gif\" width=\"18\" border=\"0\">'||'Crea&nbsp;Alternativo</a>','') crea_alternativo , DECODE(nvl('{AD4LDAPCA}','{chiave}'),'PRODUCTS/LDAPCONFIG','','<a class=\"AFCToolBox\" id=\"linkEliminaAlt\" title=\"Elimina configurazione server secondario.\" href=\"javascript:confermaElimina('''||nvl('{AD4LDAPCA}','{chiave}')||''')\"><img title=\"Elimina configurazione server alternativo\" height=\"18\" src=\"../common/images/AD4/elimina.gif\" width=\"18\" border=\"0\">'||'Elimina&nbsp;Alternativo</a>') elimina_alternativo FROM dual ) cnt " );
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

//loadDataBind @120-6358A120
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                toolbox_gridRow row = new toolbox_gridRow();
                DbRow record = (DbRow) records.nextElement();
                row.setTIPO_SERVER(Utils.convertToString(ds.parse(record.get("TIPO_SERVER"), row.getTIPO_SERVERField())));
                row.setCHIAVE(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getCHIAVEField())));
                row.setCREA_ALTERNATIVO(Utils.convertToString(ds.parse(record.get("CREA_ALTERNATIVO"), row.getCREA_ALTERNATIVOField())));
                row.setELIMINA_ALTERNATIVO(Utils.convertToString(ds.parse(record.get("ELIMINA_ALTERNATIVO"), row.getELIMINA_ALTERNATIVOField())));
                row.setID(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getIDField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @120-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @120-3367B1A0
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

//addGridDataObjectListener @120-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @120-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @120-238A81BB
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

//fireBeforeExecuteSelectEvent @120-9DA7B025
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

//fireAfterExecuteSelectEvent @120-F7E8A616
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

//class DataObject Tail @120-ED3F53A4
} // End of class DS
//End class DataObject Tail

