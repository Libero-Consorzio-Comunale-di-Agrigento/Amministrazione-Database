//AD4_STRINGHE DataSource @23-157B04AA
package ad4web.AD4Stringa;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_STRINGHE DataSource

//class DataObject Header @23-5A9404F7
public class AD4_STRINGHEDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @23-AE8B0271
    

    TextField ctrlCHIAVE = new TextField(null, null);
    
    TextField ctrlCHIAVE_OLD = new TextField(null, null);
    
    TextField ctrlSTRINGA_NEW = new TextField(null, null);
    
    TextField ctrlSTRINGA_OLD = new TextField(null, null);
    
    TextField ctrlVALORE = new TextField(null, null);
    
    TextField ctrlVALORE_OLD = new TextField(null, null);
    
    TextField ctrlCOMMENTO = new TextField(null, null);
    
    TextField ctrlTIPOR_OLD = new TextField(null, null);
    
    TextField sesUSRORCL = new TextField(null, null);
    
    TextField urlID = new TextField(null, null);
    
    TextField urlSTRINGA = new TextField(null, null);
    
    TextField urlTIPOR = new TextField(null, null);
    

    private AD4_STRINGHERow row = new AD4_STRINGHERow();

//End attributes of DataObject

//properties of DataObject @23-242D69B2

    public void  setCtrlCHIAVE( String param ) {
        this.ctrlCHIAVE.setValue( param );
    }

    public void  setCtrlCHIAVE( Object param ) {
        this.ctrlCHIAVE.setValue( param );
    }

    public void  setCtrlCHIAVE( Object param, Format ignore ) {
        this.ctrlCHIAVE.setValue( param );
    }

    public void  setCtrlCHIAVE_OLD( String param ) {
        this.ctrlCHIAVE_OLD.setValue( param );
    }

    public void  setCtrlCHIAVE_OLD( Object param ) {
        this.ctrlCHIAVE_OLD.setValue( param );
    }

    public void  setCtrlCHIAVE_OLD( Object param, Format ignore ) {
        this.ctrlCHIAVE_OLD.setValue( param );
    }

    public void  setCtrlSTRINGA_NEW( String param ) {
        this.ctrlSTRINGA_NEW.setValue( param );
    }

    public void  setCtrlSTRINGA_NEW( Object param ) {
        this.ctrlSTRINGA_NEW.setValue( param );
    }

    public void  setCtrlSTRINGA_NEW( Object param, Format ignore ) {
        this.ctrlSTRINGA_NEW.setValue( param );
    }

    public void  setCtrlSTRINGA_OLD( String param ) {
        this.ctrlSTRINGA_OLD.setValue( param );
    }

    public void  setCtrlSTRINGA_OLD( Object param ) {
        this.ctrlSTRINGA_OLD.setValue( param );
    }

    public void  setCtrlSTRINGA_OLD( Object param, Format ignore ) {
        this.ctrlSTRINGA_OLD.setValue( param );
    }

    public void  setCtrlVALORE( String param ) {
        this.ctrlVALORE.setValue( param );
    }

    public void  setCtrlVALORE( Object param ) {
        this.ctrlVALORE.setValue( param );
    }

    public void  setCtrlVALORE( Object param, Format ignore ) {
        this.ctrlVALORE.setValue( param );
    }

    public void  setCtrlVALORE_OLD( String param ) {
        this.ctrlVALORE_OLD.setValue( param );
    }

    public void  setCtrlVALORE_OLD( Object param ) {
        this.ctrlVALORE_OLD.setValue( param );
    }

    public void  setCtrlVALORE_OLD( Object param, Format ignore ) {
        this.ctrlVALORE_OLD.setValue( param );
    }

    public void  setCtrlCOMMENTO( String param ) {
        this.ctrlCOMMENTO.setValue( param );
    }

    public void  setCtrlCOMMENTO( Object param ) {
        this.ctrlCOMMENTO.setValue( param );
    }

    public void  setCtrlCOMMENTO( Object param, Format ignore ) {
        this.ctrlCOMMENTO.setValue( param );
    }

    public void  setCtrlTIPOR_OLD( String param ) {
        this.ctrlTIPOR_OLD.setValue( param );
    }

    public void  setCtrlTIPOR_OLD( Object param ) {
        this.ctrlTIPOR_OLD.setValue( param );
    }

    public void  setCtrlTIPOR_OLD( Object param, Format ignore ) {
        this.ctrlTIPOR_OLD.setValue( param );
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

    public void  setUrlTIPOR( String param ) {
        this.urlTIPOR.setValue( param );
    }

    public void  setUrlTIPOR( Object param ) {
        this.urlTIPOR.setValue( param );
    }

    public void  setUrlTIPOR( Object param, Format ignore ) {
        this.urlTIPOR.setValue( param );
    }

    public AD4_STRINGHERow getRow() {
        return row;
    }

    public void setRow( AD4_STRINGHERow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @23-6C5A63B8
    public AD4_STRINGHEDataObject(Page page) {
        super(page);
    }
//End constructor

//load @23-56107E80
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT replace('{CHIAVE}','%2F','/') CHIAVE, "
                    + "       replace('{CHIAVE}','%2F','/') CHIAVE_OLD, "
                    + "       '{STRINGA}' STRINGA, "
                    + "       '{STRINGA}' STRINGA_OLD, "
                    + "       registro_pac.get_valore(replace('{CHIAVE}','%2F','/'),'{STRINGA}','{USRORCL}') VALORE, "
                    + "       registro_pac.get_valore(replace('{CHIAVE}','%2F','/'),'{STRINGA}','{USRORCL}') VALORE_OLD, "
                    + "       registro_pac.get_commento(replace('{CHIAVE}','%2F','/'),'{STRINGA}','{USRORCL}') COMMENTO, "
                    + "       '{TIPOR}' TIPOR "
                    + "  FROM dual "
                    + " WHERE '{TIPOR}' = 'S' "
                    + "   and '{STRINGA}' is not null "
                    + "union "
                    + "select '{CHIAVE}' CHIAVE "
                    + "     , '' chiave_old "
                    + "     , '' STRINGA "
                    + "     , '' STRINGA_OLD "
                    + "     ,  "
                    + "'' valore "
                    + "     , '' valore_old "
                    + "     , '' commento "
                    + "     ,  "
                    + "'{TIPOR}' TIPOR "
                    + "  from dual "
                    + " where '{STRINGA}' is null "
                    + "   and '{TIPOR}' = 'S'" );
        if ( StringUtils.isEmpty( (String) urlID.getObjectValue() ) ) urlID.setValue( "" );
        command.addParameter( "CHIAVE", urlID, null );
        if ( StringUtils.isEmpty( (String) urlSTRINGA.getObjectValue() ) ) urlSTRINGA.setValue( "" );
        command.addParameter( "STRINGA", urlSTRINGA, null );
        if ( StringUtils.isEmpty( (String) urlTIPOR.getObjectValue() ) ) urlTIPOR.setValue( "" );
        command.addParameter( "TIPOR", urlTIPOR, null );
        if ( StringUtils.isEmpty( (String) sesUSRORCL.getObjectValue() ) ) sesUSRORCL.setValue( "" );
        command.addParameter( "USRORCL", sesUSRORCL, null );
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

//loadDataBind @23-AA995C4C
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setCHIAVE(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getCHIAVEField())));
            row.setCHIAVE_OLD(Utils.convertToString(ds.parse(record.get("CHIAVE_OLD"), row.getCHIAVE_OLDField())));
            row.setSTRINGA_NEW(Utils.convertToString(ds.parse(record.get("STRINGA"), row.getSTRINGA_NEWField())));
            row.setSTRINGA_OLD(Utils.convertToString(ds.parse(record.get("STRINGA_OLD"), row.getSTRINGA_OLDField())));
            row.setVALORE(Utils.convertToString(ds.parse(record.get("VALORE"), row.getVALOREField())));
            row.setVALORE_OLD(Utils.convertToString(ds.parse(record.get("VALORE_OLD"), row.getVALORE_OLDField())));
            row.setCOMMENTO(Utils.convertToString(ds.parse(record.get("COMMENTO"), row.getCOMMENTOField())));
            row.setTIPOR_OLD(Utils.convertToString(ds.parse(record.get("TIPOR"), row.getTIPOR_OLDField())));
        }
//End loadDataBind

//End of load @23-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @23-EDEFD97C
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call registro_pac.str_update(?,?,?,?,?,?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) row.getCHIAVE()) ) row.setCHIAVE( "" );
            command.addParameter( "P_CHIAVE", row.getCHIAVEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCHIAVE_OLD()) ) row.setCHIAVE_OLD( "" );
            command.addParameter( "P_CHIAVE_OLD", row.getCHIAVE_OLDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSTRINGA_NEW()) ) row.setSTRINGA_NEW( "" );
            command.addParameter( "P_STRINGA", row.getSTRINGA_NEWField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSTRINGA_OLD()) ) row.setSTRINGA_OLD( "" );
            command.addParameter( "P_STRINGA_OLD", row.getSTRINGA_OLDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getVALORE()) ) row.setVALORE( "" );
            command.addParameter( "P_VALORE", row.getVALOREField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getVALORE_OLD()) ) row.setVALORE_OLD( "" );
            command.addParameter( "P_VALORE_OLD", row.getVALORE_OLDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOMMENTO()) ) row.setCOMMENTO( "" );
            command.addParameter( "P_COMMENTO", row.getCOMMENTOField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getTIPOR_OLD()) ) row.setTIPOR_OLD( "" );
            command.addParameter( "P_TIPOR", row.getTIPOR_OLDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUSRORCL.getObjectValue() ) ) sesUSRORCL.setValue( "AD4" );
            command.addParameter( "P_UTENTE", sesUSRORCL, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @23-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @23-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @23-D8130DDB
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call registro_pac.str_delete(?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) row.getCHIAVE()) ) row.setCHIAVE( "" );
            command.addParameter( "P_CHIAVE", row.getCHIAVEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSTRINGA_NEW()) ) row.setSTRINGA_NEW( "" );
            command.addParameter( "P_STRINGA", row.getSTRINGA_NEWField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getTIPOR_OLD()) ) row.setTIPOR_OLD( "" );
            command.addParameter( "P_TIPOR", row.getTIPOR_OLDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUSRORCL.getObjectValue() ) ) sesUSRORCL.setValue( "AD4" );
            command.addParameter( "P_UTENTE", sesUSRORCL, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @23-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @23-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @23-F9439B2A
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "CHIAVE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCHIAVE;
            } else if ( "CHIAVE".equals(name) && prefix == null ) {
                param = ctrlCHIAVE;
            }
            if ( "CHIAVE_OLD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCHIAVE_OLD;
            } else if ( "CHIAVE_OLD".equals(name) && prefix == null ) {
                param = ctrlCHIAVE_OLD;
            }
            if ( "STRINGA_NEW".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSTRINGA_NEW;
            } else if ( "STRINGA_NEW".equals(name) && prefix == null ) {
                param = ctrlSTRINGA_NEW;
            }
            if ( "STRINGA_OLD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSTRINGA_OLD;
            } else if ( "STRINGA_OLD".equals(name) && prefix == null ) {
                param = ctrlSTRINGA_OLD;
            }
            if ( "VALORE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlVALORE;
            } else if ( "VALORE".equals(name) && prefix == null ) {
                param = ctrlVALORE;
            }
            if ( "VALORE_OLD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlVALORE_OLD;
            } else if ( "VALORE_OLD".equals(name) && prefix == null ) {
                param = ctrlVALORE_OLD;
            }
            if ( "COMMENTO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOMMENTO;
            } else if ( "COMMENTO".equals(name) && prefix == null ) {
                param = ctrlCOMMENTO;
            }
            if ( "TIPOR_OLD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlTIPOR_OLD;
            } else if ( "TIPOR_OLD".equals(name) && prefix == null ) {
                param = ctrlTIPOR_OLD;
            }
            if ( "USRORCL".equals(name) && "ses".equals(prefix) ) {
                param = sesUSRORCL;
            } else if ( "USRORCL".equals(name) && prefix == null ) {
                param = sesUSRORCL;
            }
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
            if ( "TIPOR".equals(name) && "url".equals(prefix) ) {
                param = urlTIPOR;
            } else if ( "TIPOR".equals(name) && prefix == null ) {
                param = urlTIPOR;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @23-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @23-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @23-305A023C
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

//fireBeforeExecuteSelectEvent @23-D00ACF95
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

//fireAfterExecuteSelectEvent @23-3BAD39CE
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

//fireBeforeBuildInsertEvent @23-FBA08B71
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

//fireBeforeExecuteInsertEvent @23-47AFA6A5
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

//fireAfterExecuteInsertEvent @23-E9CE95AE
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

//fireBeforeBuildSelectEvent @23-2405BE8B
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

//fireBeforeExecuteSelectEvent @23-E9DFF86B
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

//fireAfterExecuteSelectEvent @23-580A2987
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

//fireBeforeBuildSelectEvent @23-D021D0EA
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

//fireBeforeExecuteDeleteEvent @23-DD540FBB
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

//fireAfterExecuteDeleteEvent @23-2A6E2049
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

//class DataObject Tail @23-ED3F53A4
} // End of class DS
//End class DataObject Tail

