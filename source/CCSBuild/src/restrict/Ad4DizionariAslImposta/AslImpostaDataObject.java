//AslImposta DataSource @5-4EC059D1
package restrict.Ad4DizionariAslImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AslImposta DataSource

//class DataObject Header @5-0FEE24F3
public class AslImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-4C0FE845
    

    LongField ctrlREGIONE_ASL = new LongField(null, null);
    
    LongField ctrlCODICE_ASL = new LongField(null, null);
    
    TextField ctrlDESCRIZIONE = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    TextField ctrlATTIVA = new TextField(null, null);
    
    LongField urlREGIONE_ASL = new LongField(null, null);
    
    LongField urlCODICE_ASL = new LongField(null, null);
    

    private AslImpostaRow row = new AslImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-838115EC

    public void  setCtrlREGIONE_ASL( long param ) {
        this.ctrlREGIONE_ASL.setValue( param );
    }

    public void  setCtrlREGIONE_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlREGIONE_ASL.setValue( param );
    }

    public void  setCtrlREGIONE_ASL( Object param, Format format ) throws java.text.ParseException {
        this.ctrlREGIONE_ASL.setValue( param, format );
    }

    public void  setCtrlREGIONE_ASL( Long param ) {
        this.ctrlREGIONE_ASL.setValue( param );
    }

    public void  setCtrlCODICE_ASL( long param ) {
        this.ctrlCODICE_ASL.setValue( param );
    }

    public void  setCtrlCODICE_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCODICE_ASL.setValue( param );
    }

    public void  setCtrlCODICE_ASL( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCODICE_ASL.setValue( param, format );
    }

    public void  setCtrlCODICE_ASL( Long param ) {
        this.ctrlCODICE_ASL.setValue( param );
    }

    public void  setCtrlDESCRIZIONE( String param ) {
        this.ctrlDESCRIZIONE.setValue( param );
    }

    public void  setCtrlDESCRIZIONE( Object param ) {
        this.ctrlDESCRIZIONE.setValue( param );
    }

    public void  setCtrlDESCRIZIONE( Object param, Format ignore ) {
        this.ctrlDESCRIZIONE.setValue( param );
    }

    public void  setSesUtente( String param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param, Format ignore ) {
        this.sesUtente.setValue( param );
    }

    public void  setCtrlATTIVA( String param ) {
        this.ctrlATTIVA.setValue( param );
    }

    public void  setCtrlATTIVA( Object param ) {
        this.ctrlATTIVA.setValue( param );
    }

    public void  setCtrlATTIVA( Object param, Format ignore ) {
        this.ctrlATTIVA.setValue( param );
    }

    public void  setUrlREGIONE_ASL( long param ) {
        this.urlREGIONE_ASL.setValue( param );
    }

    public void  setUrlREGIONE_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.urlREGIONE_ASL.setValue( param );
    }

    public void  setUrlREGIONE_ASL( Object param, Format format ) throws java.text.ParseException {
        this.urlREGIONE_ASL.setValue( param, format );
    }

    public void  setUrlREGIONE_ASL( Long param ) {
        this.urlREGIONE_ASL.setValue( param );
    }

    public void  setUrlCODICE_ASL( long param ) {
        this.urlCODICE_ASL.setValue( param );
    }

    public void  setUrlCODICE_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.urlCODICE_ASL.setValue( param );
    }

    public void  setUrlCODICE_ASL( Object param, Format format ) throws java.text.ParseException {
        this.urlCODICE_ASL.setValue( param, format );
    }

    public void  setUrlCODICE_ASL( Long param ) {
        this.urlCODICE_ASL.setValue( param );
    }

    public AslImpostaRow getRow() {
        return row;
    }

    public void setRow( AslImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-A42F736C
    public AslImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-B043EF12
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select regione_asl "
                    + "      ,codice_asl "
                    + "      ,descrizione "
                    + "      ,attiva "
                    + "      ,'<!--' hide_begin "
                    + "      ,'-->' hide_end "
                    + "      ,  "
                    + " '</td></tr><tr><td class=\"AFCDataTD\" align=\"right\" colspan=\"2\">' "
                    + "       ||utente_aggiornamento "
                    + "       ||' ' "
                    + "       ||ad4_ccs.format_data_trunc(data_aggiornamento) label_upd "
                    + "  from ad4_asl "
                    + " where regione_asl = {REGIONE_ASL} "
                    + "   and codice_asl = {CODICE_ASL}" );
        if ( urlREGIONE_ASL.getObjectValue() == null ) urlREGIONE_ASL.setValue( -1 );
        command.addParameter( "REGIONE_ASL", urlREGIONE_ASL, null );
        if ( urlCODICE_ASL.getObjectValue() == null ) urlCODICE_ASL.setValue( -1 );
        command.addParameter( "CODICE_ASL", urlCODICE_ASL, null );
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

//loadDataBind @5-AA5B302E
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setREGIONE_ASL(Utils.convertToLong(ds.parse(record.get("REGIONE_ASL"), row.getREGIONE_ASLField())));
            row.setCODICE_ASL_LABEL(Utils.convertToString(ds.parse(record.get("CODICE_ASL"), row.getCODICE_ASL_LABELField())));
            row.setHIDE_BEGIN(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGINField())));
            row.setCODICE_ASL(Utils.convertToLong(ds.parse(record.get("CODICE_ASL"), row.getCODICE_ASLField())));
            row.setHIDE_END(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_ENDField())));
            row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
            row.setATTIVA(Utils.convertToString(ds.parse(record.get("ATTIVA"), row.getATTIVAField())));
            row.setLABEL_UPD(Utils.convertToString(ds.parse(record.get("LABEL_UPD"), row.getLABEL_UPDField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-F2616EA4
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ASL_INS(?,?,?,?,?)}" );
            command.addParameter( "IN_REGIONE_ASL", row.getREGIONE_ASLField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CODICE_ASL", row.getCODICE_ASLField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDESCRIZIONE()) ) row.setDESCRIZIONE( "" );
            command.addParameter( "IN_DESCRIZIONE", row.getDESCRIZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getATTIVA()) ) row.setATTIVA( "" );
            command.addParameter( "IN_ATTIVA", row.getATTIVAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//update @5-860717F2
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ASL_UPD(?,?,?,?,?)}" );
            command.addParameter( "IN_REGIONE_ASL", urlREGIONE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CODICE_ASL", urlCODICE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDESCRIZIONE()) ) row.setDESCRIZIONE( "" );
            command.addParameter( "IN_DESCRIZIONE", row.getDESCRIZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getATTIVA()) ) row.setATTIVA( "" );
            command.addParameter( "IN_ATTIVA", row.getATTIVAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @5-6E4A3210
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ASL_DEL(?,?)}" );
            command.addParameter( "IN_REGIONE_ASL", urlREGIONE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CODICE_ASL", urlCODICE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @5-1A90683D
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "REGIONE_ASL".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlREGIONE_ASL;
            } else if ( "REGIONE_ASL".equals(name) && prefix == null ) {
                param = ctrlREGIONE_ASL;
            }
            if ( "CODICE_ASL".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCODICE_ASL;
            } else if ( "CODICE_ASL".equals(name) && prefix == null ) {
                param = ctrlCODICE_ASL;
            }
            if ( "DESCRIZIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDESCRIZIONE;
            } else if ( "DESCRIZIONE".equals(name) && prefix == null ) {
                param = ctrlDESCRIZIONE;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "ATTIVA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlATTIVA;
            } else if ( "ATTIVA".equals(name) && prefix == null ) {
                param = ctrlATTIVA;
            }
            if ( "REGIONE_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlREGIONE_ASL;
            } else if ( "REGIONE_ASL".equals(name) && prefix == null ) {
                param = urlREGIONE_ASL;
            }
            if ( "CODICE_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlCODICE_ASL;
            } else if ( "CODICE_ASL".equals(name) && prefix == null ) {
                param = urlCODICE_ASL;
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

