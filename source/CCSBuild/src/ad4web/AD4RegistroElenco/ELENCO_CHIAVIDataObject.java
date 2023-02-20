//ELENCO_CHIAVI DataSource @2-130288B4
package ad4web.AD4RegistroElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ELENCO_CHIAVI DataSource

//class DataObject Header @2-4A4883AA
public class ELENCO_CHIAVIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @2-BA4372CB
    

    TextField urlID = new TextField(null, null);
    
    TextField urlSTRINGA = new TextField(null, null);
    
    TextField sesUSRORCL = new TextField(null, null);
    

    private ELENCO_CHIAVIRow[] rows = new ELENCO_CHIAVIRow[50];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @2-38BBDB84

    public void  setUrlID( String param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param, Format ignore ) {
        this.urlID.setValue( param );
    }

    public void  setUrlSTRINGA( String param ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setUrlSTRINGA( Object param ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setUrlSTRINGA( Object param, Format ignore ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setSesUSRORCL( String param ) {
        this.sesUSRORCL.setValue( param );
    }

    public void  setSesUSRORCL( Object param ) {
        this.sesUSRORCL.setValue( param );
    }

    public void  setSesUSRORCL( Object param, Format ignore ) {
        this.sesUSRORCL.setValue( param );
    }

    public ELENCO_CHIAVIRow[] getRows() {
        return rows;
    }

    public void setRows(ELENCO_CHIAVIRow[] rows) {
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

//constructor @2-7BDAFA25
    public ELENCO_CHIAVIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @2-EC832EA6
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        SPCommand command = SPCommandFactory.getSPCommand( "cn" );
        command.setJdbcConnection( ds );

        command.setSql( "{ ? = call REGISTRO_PAC.GET_REGISTRO_RC ( ?, ?, ? ) }" );
        command.addParameter( "RETURN_VALUE", null, OracleSPCommand.ORACLE_CURSOR, 0, SPParameter.OUTPUT_PARAMETER );
        if ( StringUtils.isEmpty( (String) urlID.getObjectValue() ) ) urlID.setValue( "" );
        command.addParameter( "P_CHIAVE", urlID, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
        if ( StringUtils.isEmpty( (String) urlSTRINGA.getObjectValue() ) ) urlSTRINGA.setValue( "" );
        command.addParameter( "P_STRINGA", urlSTRINGA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
        if ( StringUtils.isEmpty( (String) sesUSRORCL.getObjectValue() ) ) sesUSRORCL.setValue( "" );
        command.addParameter( "P_UTENTE", sesUSRORCL, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
        command.setStartPos( ( pageNum - 1 ) * pageSize + 1 );
        command.setFetchSize( pageSize );

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        Enumeration records = null;
        if ( ! ds.hasErrors() ) {
            records = command.getRows();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @2-5E62A65E
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ELENCO_CHIAVIRow row = new ELENCO_CHIAVIRow();
                DbRow record = (DbRow) records.nextElement();
                row.setSTRINGA(Utils.convertToString(ds.parse(record.get("STRINGA"), row.getSTRINGAField())));
                row.setVALORE(Utils.convertToString(ds.parse(record.get("VALORE"), row.getVALOREField())));
                row.setCOMMENTO(Utils.convertToString(ds.parse(record.get("COMMENTO"), row.getCOMMENTOField())));
                row.setCHIAVE(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getCHIAVEField())));
                rows[counter++] = row;
            }
            amountOfRows = counter;
        }
//End loadDataBind

//End of load @2-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @2-18C61CB7
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID".equals(name) && "url".equals(prefix) ) {
                param = urlID;
            } else if ( "ID".equals(name) && prefix == null ) {
                param = urlID;
            }
            if ( "STRINGA".equals(name) && "url".equals(prefix) ) {
                param = urlSTRINGA;
            } else if ( "STRINGA".equals(name) && prefix == null ) {
                param = urlSTRINGA;
            }
            if ( "USRORCL".equals(name) && "ses".equals(prefix) ) {
                param = sesUSRORCL;
            } else if ( "USRORCL".equals(name) && prefix == null ) {
                param = sesUSRORCL;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @2-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @2-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @2-238A81BB
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

//fireBeforeExecuteSelectEvent @2-9DA7B025
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

//fireAfterExecuteSelectEvent @2-F7E8A616
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

//class DataObject Tail @2-ED3F53A4
} // End of class DS
//End class DataObject Tail

