//ZonaAslImposta DataSource @5-42CE27FD
package restrict.Ad4DizionariZonaAslImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ZonaAslImposta DataSource

//class DataObject Header @5-26006949
public class ZonaAslImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-817289D7
    

    LongField ctrlID_ZONA_ASL = new LongField(null, null);
    
    LongField ctrlCODICE_REGIONE = new LongField(null, null);
    
    TextField ctrlCODICE_ZONA = new TextField(null, null);
    
    TextField ctrlTITOLO = new TextField(null, null);
    
    TextField ctrlVAL_DISTRETTO_LEA = new TextField(null, null);
    
    LongField urlID_ZONA_ASL = new LongField(null, null);
    

    private ZonaAslImpostaRow row = new ZonaAslImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-F979BADB

    public void  setCtrlID_ZONA_ASL( long param ) {
        this.ctrlID_ZONA_ASL.setValue( param );
    }

    public void  setCtrlID_ZONA_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlID_ZONA_ASL.setValue( param );
    }

    public void  setCtrlID_ZONA_ASL( Object param, Format format ) throws java.text.ParseException {
        this.ctrlID_ZONA_ASL.setValue( param, format );
    }

    public void  setCtrlID_ZONA_ASL( Long param ) {
        this.ctrlID_ZONA_ASL.setValue( param );
    }

    public void  setCtrlCODICE_REGIONE( long param ) {
        this.ctrlCODICE_REGIONE.setValue( param );
    }

    public void  setCtrlCODICE_REGIONE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCODICE_REGIONE.setValue( param );
    }

    public void  setCtrlCODICE_REGIONE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCODICE_REGIONE.setValue( param, format );
    }

    public void  setCtrlCODICE_REGIONE( Long param ) {
        this.ctrlCODICE_REGIONE.setValue( param );
    }

    public void  setCtrlCODICE_ZONA( String param ) {
        this.ctrlCODICE_ZONA.setValue( param );
    }

    public void  setCtrlCODICE_ZONA( Object param ) {
        this.ctrlCODICE_ZONA.setValue( param );
    }

    public void  setCtrlCODICE_ZONA( Object param, Format ignore ) {
        this.ctrlCODICE_ZONA.setValue( param );
    }

    public void  setCtrlTITOLO( String param ) {
        this.ctrlTITOLO.setValue( param );
    }

    public void  setCtrlTITOLO( Object param ) {
        this.ctrlTITOLO.setValue( param );
    }

    public void  setCtrlTITOLO( Object param, Format ignore ) {
        this.ctrlTITOLO.setValue( param );
    }

    public void  setCtrlVAL_DISTRETTO_LEA( String param ) {
        this.ctrlVAL_DISTRETTO_LEA.setValue( param );
    }

    public void  setCtrlVAL_DISTRETTO_LEA( Object param ) {
        this.ctrlVAL_DISTRETTO_LEA.setValue( param );
    }

    public void  setCtrlVAL_DISTRETTO_LEA( Object param, Format ignore ) {
        this.ctrlVAL_DISTRETTO_LEA.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( long param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( Object param, Format format ) throws java.text.ParseException {
        this.urlID_ZONA_ASL.setValue( param, format );
    }

    public void  setUrlID_ZONA_ASL( Long param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public ZonaAslImpostaRow getRow() {
        return row;
    }

    public void setRow( ZonaAslImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-D5B019B6
    public ZonaAslImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-ADFF831D
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select id_zona_asl "
                    + "      ,codice_zona "
                    + "      ,codice_regione "
                    + "      ,titolo "
                    + "      ,val_distretto_lea "
                    + "      ,'<!--' hide_begin "
                    + "      ,'-->' hide_end "
                    + "  from zone_asl "
                    + " where id_zona_asl =  {ID_ZONA_ASL}" );
        if ( urlID_ZONA_ASL.getObjectValue() == null ) urlID_ZONA_ASL.setValue( -1 );
        command.addParameter( "ID_ZONA_ASL", urlID_ZONA_ASL, null );
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

//loadDataBind @5-FF0F307F
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setID_ZONA_ASL_LABEL(Utils.convertToString(ds.parse(record.get("ID_ZONA_ASL"), row.getID_ZONA_ASL_LABELField())));
            row.setHIDE_BEGIN(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGINField())));
            row.setID_ZONA_ASL(Utils.convertToLong(ds.parse(record.get("ID_ZONA_ASL"), row.getID_ZONA_ASLField())));
            row.setHIDE_END(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_ENDField())));
            row.setCODICE_REGIONE(Utils.convertToLong(ds.parse(record.get("CODICE_REGIONE"), row.getCODICE_REGIONEField())));
            row.setCODICE_ZONA(Utils.convertToString(ds.parse(record.get("CODICE_ZONA"), row.getCODICE_ZONAField())));
            row.setTITOLO(Utils.convertToString(ds.parse(record.get("TITOLO"), row.getTITOLOField())));
            row.setVAL_DISTRETTO_LEA(Utils.convertToString(ds.parse(record.get("VAL_DISTRETTO_LEA"), row.getVAL_DISTRETTO_LEAField())));
            row.setLABEL_UPD(Utils.convertToString(ds.parse(record.get("LABEL_UPD"), row.getLABEL_UPDField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-865C1537
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ZONA_ASL_INS(?,?,?,?,?)}" );
            command.addParameter( "IN_ID_ZONA_ASL", row.getID_ZONA_ASLField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CODICE_REGIONE", row.getCODICE_REGIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCODICE_ZONA()) ) row.setCODICE_ZONA( "" );
            command.addParameter( "IN_CODICE_ZONA", row.getCODICE_ZONAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getTITOLO()) ) row.setTITOLO( "" );
            command.addParameter( "IN_TITOLO", row.getTITOLOField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getVAL_DISTRETTO_LEA()) ) row.setVAL_DISTRETTO_LEA( "" );
            command.addParameter( "IN_VAL_DISTRETTO_LEA", row.getVAL_DISTRETTO_LEAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildInsertEvent( new DataObjectEvent(command) );

//End insert

//insertDataBound @5-BC781F8A
            fireBeforeExecuteInsertEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteInsertEvent( new DataObjectEvent(command) );

//End insertDataBound

//End of insert @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of insert

//update @5-BA917862
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ZONA_ASL_UPD(?,?,?,?,?)}" );
            command.addParameter( "IN_ID_ZONA_ASL", urlID_ZONA_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CODICE_REGIONE", row.getCODICE_REGIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCODICE_ZONA()) ) row.setCODICE_ZONA( "" );
            command.addParameter( "IN_CODICE_ZONA", row.getCODICE_ZONAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getTITOLO()) ) row.setTITOLO( "" );
            command.addParameter( "IN_TITOLO", row.getTITOLOField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getVAL_DISTRETTO_LEA()) ) row.setVAL_DISTRETTO_LEA( "" );
            command.addParameter( "IN_VAL_DISTRETTO_LEA", row.getVAL_DISTRETTO_LEAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @5-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @5-21694A1F
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ZONA_ASL_DEL(?)}" );
            command.addParameter( "IN_ID_ZONA_ASL", urlID_ZONA_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @5-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @5-E739A556
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_ZONA_ASL".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlID_ZONA_ASL;
            } else if ( "ID_ZONA_ASL".equals(name) && prefix == null ) {
                param = ctrlID_ZONA_ASL;
            }
            if ( "CODICE_REGIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCODICE_REGIONE;
            } else if ( "CODICE_REGIONE".equals(name) && prefix == null ) {
                param = ctrlCODICE_REGIONE;
            }
            if ( "CODICE_ZONA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCODICE_ZONA;
            } else if ( "CODICE_ZONA".equals(name) && prefix == null ) {
                param = ctrlCODICE_ZONA;
            }
            if ( "TITOLO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlTITOLO;
            } else if ( "TITOLO".equals(name) && prefix == null ) {
                param = ctrlTITOLO;
            }
            if ( "VAL_DISTRETTO_LEA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlVAL_DISTRETTO_LEA;
            } else if ( "VAL_DISTRETTO_LEA".equals(name) && prefix == null ) {
                param = ctrlVAL_DISTRETTO_LEA;
            }
            if ( "ID_ZONA_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlID_ZONA_ASL;
            } else if ( "ID_ZONA_ASL".equals(name) && prefix == null ) {
                param = urlID_ZONA_ASL;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @5-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @5-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @5-305A023C
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

//fireBeforeExecuteSelectEvent @5-D00ACF95
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

//fireAfterExecuteSelectEvent @5-3BAD39CE
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

//fireBeforeBuildInsertEvent @5-FBA08B71
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

//fireBeforeExecuteInsertEvent @5-47AFA6A5
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

//fireAfterExecuteInsertEvent @5-E9CE95AE
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

//fireBeforeBuildSelectEvent @5-2405BE8B
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

//fireBeforeExecuteSelectEvent @5-E9DFF86B
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

//fireAfterExecuteSelectEvent @5-580A2987
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

//fireBeforeBuildSelectEvent @5-D021D0EA
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

//fireBeforeExecuteDeleteEvent @5-DD540FBB
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

//fireAfterExecuteDeleteEvent @5-2A6E2049
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

//class DataObject Tail @5-ED3F53A4
} // End of class DS
//End class DataObject Tail

