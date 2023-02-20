//RegioneImposta DataSource @5-005BF1A5
package restrict.Ad4DizionariRegioneImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End RegioneImposta DataSource

//class DataObject Header @5-FD19FB63
public class RegioneImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-F2F8921D
    

    LongField ctrlREGIONE = new LongField(null, null);
    
    TextField ctrlDENOMINAZIONE = new TextField(null, null);
    
    LongField ctrlID_REGIONE = new LongField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    LongField urlREGIONE = new LongField(null, null);
    

    private RegioneImpostaRow row = new RegioneImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-AC5E3FA1

    public void  setCtrlREGIONE( long param ) {
        this.ctrlREGIONE.setValue( param );
    }

    public void  setCtrlREGIONE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlREGIONE.setValue( param );
    }

    public void  setCtrlREGIONE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlREGIONE.setValue( param, format );
    }

    public void  setCtrlREGIONE( Long param ) {
        this.ctrlREGIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( String param ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( Object param ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( Object param, Format ignore ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlID_REGIONE( long param ) {
        this.ctrlID_REGIONE.setValue( param );
    }

    public void  setCtrlID_REGIONE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlID_REGIONE.setValue( param );
    }

    public void  setCtrlID_REGIONE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlID_REGIONE.setValue( param, format );
    }

    public void  setCtrlID_REGIONE( Long param ) {
        this.ctrlID_REGIONE.setValue( param );
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

    public void  setUrlREGIONE( long param ) {
        this.urlREGIONE.setValue( param );
    }

    public void  setUrlREGIONE( long param, Format ignore ) throws java.text.ParseException {
        this.urlREGIONE.setValue( param );
    }

    public void  setUrlREGIONE( Object param, Format format ) throws java.text.ParseException {
        this.urlREGIONE.setValue( param, format );
    }

    public void  setUrlREGIONE( Long param ) {
        this.urlREGIONE.setValue( param );
    }

    public RegioneImpostaRow getRow() {
        return row;
    }

    public void setRow( RegioneImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-031D35E1
    public RegioneImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-448B79DA
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select regione "
                    + "      ,denominazione "
                    + "      ,'<!--' hide_begin "
                    + "      ,'-->' hide_end "
                    + "      ,id_regione "
                    + "      ,  "
                    + " '</td></tr><tr><td class=\"AFCDataTD\" align=\"right\" colspan=\"2\">' "
                    + "       ||utente_aggiornamento "
                    + "       ||' ' "
                    + "       ||ad4_ccs.format_data_trunc(data_aggiornamento) label_upd "
                    + "  from ad4_regioni "
                    + " where regione = {REGIONE}" );
        if ( urlREGIONE.getObjectValue() == null ) urlREGIONE.setValue( -1 );
        command.addParameter( "REGIONE", urlREGIONE, null );
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

//loadDataBind @5-AD2680CC
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setREGIONE_LABEL(Utils.convertToString(ds.parse(record.get("REGIONE"), row.getREGIONE_LABELField())));
            row.setHIDE_BEGIN(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGINField())));
            row.setREGIONE(Utils.convertToLong(ds.parse(record.get("REGIONE"), row.getREGIONEField())));
            row.setHIDE_END(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_ENDField())));
            row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
            row.setID_REGIONE(Utils.convertToLong(ds.parse(record.get("ID_REGIONE"), row.getID_REGIONEField())));
            row.setLABEL_UPD(Utils.convertToString(ds.parse(record.get("LABEL_UPD"), row.getLABEL_UPDField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-762EBCB7
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.REGIONE_INS(?,?,?,?)}" );
            command.addParameter( "IN_REGIONE", row.getREGIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_ID_REGIONE", row.getID_REGIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE_AGGIORNAMENTO", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//update @5-0F3252F8
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.REGIONE_UPD(?,?,?,?)}" );
            command.addParameter( "IN_REGIONE", urlREGIONE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_ID_REGIONE", row.getID_REGIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE_AGGIORNAMENTO", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @5-3323E803
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.REGIONE_DEL(?)}" );
            command.addParameter( "IN_REGIONE", urlREGIONE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @5-B6185DB3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "REGIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlREGIONE;
            } else if ( "REGIONE".equals(name) && prefix == null ) {
                param = ctrlREGIONE;
            }
            if ( "DENOMINAZIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDENOMINAZIONE;
            } else if ( "DENOMINAZIONE".equals(name) && prefix == null ) {
                param = ctrlDENOMINAZIONE;
            }
            if ( "ID_REGIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlID_REGIONE;
            } else if ( "ID_REGIONE".equals(name) && prefix == null ) {
                param = ctrlID_REGIONE;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "REGIONE".equals(name) && "url".equals(prefix) ) {
                param = urlREGIONE;
            } else if ( "REGIONE".equals(name) && prefix == null ) {
                param = urlREGIONE;
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

