//AD4_CHIAVI DataSource @99-157B04AA
package ad4web.AD4Stringa;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_CHIAVI DataSource

//class DataObject Header @99-A10D0CA9
public class AD4_CHIAVIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @99-00554E20
    

    TextField ctrlCHIAVE = new TextField(null, null);
    
    TextField ctrlCHIAVE_OLD = new TextField(null, null);
    
    TextField expr137 = new TextField(null, null);
    
    TextField expr138 = new TextField(null, null);
    
    TextField expr139 = new TextField(null, null);
    
    TextField expr140 = new TextField(null, null);
    
    TextField expr141 = new TextField(null, null);
    
    TextField ctrlTIPOR = new TextField(null, null);
    
    TextField sesUSRORCL = new TextField(null, null);
    
    TextField expr151 = new TextField(null, null);
    
    TextField urlID = new TextField(null, null);
    
    TextField urlTIPOR = new TextField(null, null);
    
    TextField urlSE_NUOVO = new TextField(null, null);
    

    private AD4_CHIAVIRow row = new AD4_CHIAVIRow();

//End attributes of DataObject

//properties of DataObject @99-22786834

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

    public void  setExpr137( String param ) {
        this.expr137.setValue( param );
    }

    public void  setExpr137( Object param ) {
        this.expr137.setValue( param );
    }

    public void  setExpr137( Object param, Format ignore ) {
        this.expr137.setValue( param );
    }

    public void  setExpr138( String param ) {
        this.expr138.setValue( param );
    }

    public void  setExpr138( Object param ) {
        this.expr138.setValue( param );
    }

    public void  setExpr138( Object param, Format ignore ) {
        this.expr138.setValue( param );
    }

    public void  setExpr139( String param ) {
        this.expr139.setValue( param );
    }

    public void  setExpr139( Object param ) {
        this.expr139.setValue( param );
    }

    public void  setExpr139( Object param, Format ignore ) {
        this.expr139.setValue( param );
    }

    public void  setExpr140( String param ) {
        this.expr140.setValue( param );
    }

    public void  setExpr140( Object param ) {
        this.expr140.setValue( param );
    }

    public void  setExpr140( Object param, Format ignore ) {
        this.expr140.setValue( param );
    }

    public void  setExpr141( String param ) {
        this.expr141.setValue( param );
    }

    public void  setExpr141( Object param ) {
        this.expr141.setValue( param );
    }

    public void  setExpr141( Object param, Format ignore ) {
        this.expr141.setValue( param );
    }

    public void  setCtrlTIPOR( String param ) {
        this.ctrlTIPOR.setValue( param );
    }

    public void  setCtrlTIPOR( Object param ) {
        this.ctrlTIPOR.setValue( param );
    }

    public void  setCtrlTIPOR( Object param, Format ignore ) {
        this.ctrlTIPOR.setValue( param );
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

    public void  setExpr151( String param ) {
        this.expr151.setValue( param );
    }

    public void  setExpr151( Object param ) {
        this.expr151.setValue( param );
    }

    public void  setExpr151( Object param, Format ignore ) {
        this.expr151.setValue( param );
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

    public void  setUrlTIPOR( String param ) {
        this.urlTIPOR.setValue( param );
    }

    public void  setUrlTIPOR( Object param ) {
        this.urlTIPOR.setValue( param );
    }

    public void  setUrlTIPOR( Object param, Format ignore ) {
        this.urlTIPOR.setValue( param );
    }

    public void  setUrlSE_NUOVO( String param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param, Format ignore ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public AD4_CHIAVIRow getRow() {
        return row;
    }

    public void setRow( AD4_CHIAVIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @99-246EBAA4
    public AD4_CHIAVIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @99-3692C8CC
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select decode('{SE_NUOVO}','S','Nuova Chiave in '||'{CHIAVE}','Modifica chiave di '||substr(registro_pac.get_padre('{CHIAVE}','{USRORCL}'),1,length(registro_pac.get_padre('{CHIAVE}','{USRORCL}'))-1)) titolo "
                    + "     ,  "
                    + "decode('{SE_NUOVO}','S','{CHIAVE}'||'/',registro_pac.get_padre('{CHIAVE}','{USRORCL}')) padre "
                    + "     ,  "
                    + "decode('{SE_NUOVO}','S','',registro_pac.get_chiave('{CHIAVE}','{USRORCL}')) sub_chiave "
                    + "     , decode('{SE_NUOVO}','S','','{CHIAVE}') CHIAVE "
                    + "     , decode('{SE_NUOVO}','S','','{CHIAVE}') chiave_old "
                    + "     , '{TIPOR}' TIPOR "
                    + "  from dual "
                    + " where '{TIPOR}' = 'C'" );
        if ( StringUtils.isEmpty( (String) urlID.getObjectValue() ) ) urlID.setValue( "" );
        command.addParameter( "CHIAVE", urlID, null );
        if ( StringUtils.isEmpty( (String) urlTIPOR.getObjectValue() ) ) urlTIPOR.setValue( "" );
        command.addParameter( "TIPOR", urlTIPOR, null );
        if ( StringUtils.isEmpty( (String) urlSE_NUOVO.getObjectValue() ) ) urlSE_NUOVO.setValue( "" );
        command.addParameter( "SE_NUOVO", urlSE_NUOVO, null );
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

//loadDataBind @99-EAA96272
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setTITOLO(Utils.convertToString(ds.parse(record.get("TITOLO"), row.getTITOLOField())));
            row.setPADRE(Utils.convertToString(ds.parse(record.get("PADRE"), row.getPADREField())));
            row.setSUB_CHIAVE(Utils.convertToString(ds.parse(record.get("SUB_CHIAVE"), row.getSUB_CHIAVEField())));
            row.setCHIAVE(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getCHIAVEField())));
            row.setCHIAVE_OLD(Utils.convertToString(ds.parse(record.get("CHIAVE_OLD"), row.getCHIAVE_OLDField())));
            row.setTIPOR(Utils.convertToString(ds.parse(record.get("TIPOR"), row.getTIPORField())));
        }
//End loadDataBind

//End of load @99-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @99-70B684C7
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ call registro_pac.str_update ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) }" );
            if ( StringUtils.isEmpty( (String) row.getCHIAVE()) ) row.setCHIAVE( "" );
            command.addParameter( "P_CHIAVE", row.getCHIAVEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCHIAVE_OLD()) ) row.setCHIAVE_OLD( "" );
            command.addParameter( "P_CHIAVE_OLD", row.getCHIAVE_OLDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) expr137.getObjectValue() ) ) expr137.setValue( "" );
            command.addParameter( "P_STRINGA", expr137, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) expr138.getObjectValue() ) ) expr138.setValue( "" );
            command.addParameter( "P_STRINGA_OLD", expr138, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) expr139.getObjectValue() ) ) expr139.setValue( "" );
            command.addParameter( "P_VALORE", expr139, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) expr140.getObjectValue() ) ) expr140.setValue( "" );
            command.addParameter( "P_VALORE_OLD", expr140, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) expr141.getObjectValue() ) ) expr141.setValue( "" );
            command.addParameter( "P_COMMENTO", expr141, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getTIPOR()) ) row.setTIPOR( "" );
            command.addParameter( "P_TIPOR", row.getTIPORField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUSRORCL.getObjectValue() ) ) sesUSRORCL.setValue( "AD4" );
            command.addParameter( "P_UTENTE", sesUSRORCL, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @99-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @99-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @99-D5BDC023
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ call registro_pac.str_delete ( ?, ?, ?, ? ) }" );
            if ( StringUtils.isEmpty( (String) row.getCHIAVE()) ) row.setCHIAVE( "" );
            command.addParameter( "P_CHIAVE", row.getCHIAVEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) expr151.getObjectValue() ) ) expr151.setValue( "" );
            command.addParameter( "P_STRINGA", expr151, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getTIPOR()) ) row.setTIPOR( "" );
            command.addParameter( "P_TIPOR", row.getTIPORField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUSRORCL.getObjectValue() ) ) sesUSRORCL.setValue( "AD4" );
            command.addParameter( "P_UTENTE", sesUSRORCL, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @99-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @99-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @99-22B84313
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
            if ( "expr137".equals(name) && "expr".equals(prefix) ) {
                param = expr137;
            } else if ( "expr137".equals(name) && prefix == null ) {
                param = expr137;
            }
            if ( "expr138".equals(name) && "expr".equals(prefix) ) {
                param = expr138;
            } else if ( "expr138".equals(name) && prefix == null ) {
                param = expr138;
            }
            if ( "expr139".equals(name) && "expr".equals(prefix) ) {
                param = expr139;
            } else if ( "expr139".equals(name) && prefix == null ) {
                param = expr139;
            }
            if ( "expr140".equals(name) && "expr".equals(prefix) ) {
                param = expr140;
            } else if ( "expr140".equals(name) && prefix == null ) {
                param = expr140;
            }
            if ( "expr141".equals(name) && "expr".equals(prefix) ) {
                param = expr141;
            } else if ( "expr141".equals(name) && prefix == null ) {
                param = expr141;
            }
            if ( "TIPOR".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlTIPOR;
            } else if ( "TIPOR".equals(name) && prefix == null ) {
                param = ctrlTIPOR;
            }
            if ( "USRORCL".equals(name) && "ses".equals(prefix) ) {
                param = sesUSRORCL;
            } else if ( "USRORCL".equals(name) && prefix == null ) {
                param = sesUSRORCL;
            }
            if ( "expr151".equals(name) && "expr".equals(prefix) ) {
                param = expr151;
            } else if ( "expr151".equals(name) && prefix == null ) {
                param = expr151;
            }
            if ( "ID".equals(name) && "url".equals(prefix) ) {
                param = urlID;
            } else if ( "ID".equals(name) && prefix == null ) {
                param = urlID;
            }
            if ( "TIPOR".equals(name) && "url".equals(prefix) ) {
                param = urlTIPOR;
            } else if ( "TIPOR".equals(name) && prefix == null ) {
                param = urlTIPOR;
            }
            if ( "SE_NUOVO".equals(name) && "url".equals(prefix) ) {
                param = urlSE_NUOVO;
            } else if ( "SE_NUOVO".equals(name) && prefix == null ) {
                param = urlSE_NUOVO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @99-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @99-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @99-305A023C
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

//fireBeforeExecuteSelectEvent @99-D00ACF95
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

//fireAfterExecuteSelectEvent @99-3BAD39CE
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

//fireBeforeBuildInsertEvent @99-FBA08B71
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

//fireBeforeExecuteInsertEvent @99-47AFA6A5
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

//fireAfterExecuteInsertEvent @99-E9CE95AE
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

//fireBeforeBuildSelectEvent @99-2405BE8B
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

//fireBeforeExecuteSelectEvent @99-E9DFF86B
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

//fireAfterExecuteSelectEvent @99-580A2987
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

//fireBeforeBuildSelectEvent @99-D021D0EA
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

//fireBeforeExecuteDeleteEvent @99-DD540FBB
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

//fireAfterExecuteDeleteEvent @99-2A6E2049
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

//class DataObject Tail @99-ED3F53A4
} // End of class DS
//End class DataObject Tail

