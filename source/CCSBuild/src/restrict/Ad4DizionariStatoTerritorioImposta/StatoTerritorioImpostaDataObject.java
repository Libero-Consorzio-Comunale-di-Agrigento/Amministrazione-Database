//StatoTerritorioImposta DataSource @5-EB4ECEC4
package restrict.Ad4DizionariStatoTerritorioImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End StatoTerritorioImposta DataSource

//class DataObject Header @5-8312DEEF
public class StatoTerritorioImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-6665F054
    

    LongField ctrlSTATO_TERRITORIO = new LongField(null, null);
    
    TextField ctrlDENOMINAZIONE = new TextField(null, null);
    
    TextField ctrlSIGLA = new TextField(null, null);
    
    TextField ctrlDESC_CITTADINANZA = new TextField(null, null);
    
    LongField ctrlRAGGRUPPAMENTO = new LongField(null, null);
    
    LongField ctrlSTATO_APPARTENENZA = new LongField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    LongField urlSTATO_TERRITORIO = new LongField(null, null);
    

    private StatoTerritorioImpostaRow row = new StatoTerritorioImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-FC2285B2

    public void  setCtrlSTATO_TERRITORIO( long param ) {
        this.ctrlSTATO_TERRITORIO.setValue( param );
    }

    public void  setCtrlSTATO_TERRITORIO( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlSTATO_TERRITORIO.setValue( param );
    }

    public void  setCtrlSTATO_TERRITORIO( Object param, Format format ) throws java.text.ParseException {
        this.ctrlSTATO_TERRITORIO.setValue( param, format );
    }

    public void  setCtrlSTATO_TERRITORIO( Long param ) {
        this.ctrlSTATO_TERRITORIO.setValue( param );
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

    public void  setCtrlSIGLA( String param ) {
        this.ctrlSIGLA.setValue( param );
    }

    public void  setCtrlSIGLA( Object param ) {
        this.ctrlSIGLA.setValue( param );
    }

    public void  setCtrlSIGLA( Object param, Format ignore ) {
        this.ctrlSIGLA.setValue( param );
    }

    public void  setCtrlDESC_CITTADINANZA( String param ) {
        this.ctrlDESC_CITTADINANZA.setValue( param );
    }

    public void  setCtrlDESC_CITTADINANZA( Object param ) {
        this.ctrlDESC_CITTADINANZA.setValue( param );
    }

    public void  setCtrlDESC_CITTADINANZA( Object param, Format ignore ) {
        this.ctrlDESC_CITTADINANZA.setValue( param );
    }

    public void  setCtrlRAGGRUPPAMENTO( long param ) {
        this.ctrlRAGGRUPPAMENTO.setValue( param );
    }

    public void  setCtrlRAGGRUPPAMENTO( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlRAGGRUPPAMENTO.setValue( param );
    }

    public void  setCtrlRAGGRUPPAMENTO( Object param, Format format ) throws java.text.ParseException {
        this.ctrlRAGGRUPPAMENTO.setValue( param, format );
    }

    public void  setCtrlRAGGRUPPAMENTO( Long param ) {
        this.ctrlRAGGRUPPAMENTO.setValue( param );
    }

    public void  setCtrlSTATO_APPARTENENZA( long param ) {
        this.ctrlSTATO_APPARTENENZA.setValue( param );
    }

    public void  setCtrlSTATO_APPARTENENZA( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlSTATO_APPARTENENZA.setValue( param );
    }

    public void  setCtrlSTATO_APPARTENENZA( Object param, Format format ) throws java.text.ParseException {
        this.ctrlSTATO_APPARTENENZA.setValue( param, format );
    }

    public void  setCtrlSTATO_APPARTENENZA( Long param ) {
        this.ctrlSTATO_APPARTENENZA.setValue( param );
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

    public void  setUrlSTATO_TERRITORIO( long param ) {
        this.urlSTATO_TERRITORIO.setValue( param );
    }

    public void  setUrlSTATO_TERRITORIO( long param, Format ignore ) throws java.text.ParseException {
        this.urlSTATO_TERRITORIO.setValue( param );
    }

    public void  setUrlSTATO_TERRITORIO( Object param, Format format ) throws java.text.ParseException {
        this.urlSTATO_TERRITORIO.setValue( param, format );
    }

    public void  setUrlSTATO_TERRITORIO( Long param ) {
        this.urlSTATO_TERRITORIO.setValue( param );
    }

    public StatoTerritorioImpostaRow getRow() {
        return row;
    }

    public void setRow( StatoTerritorioImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-AFF1A1C8
    public StatoTerritorioImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-453D3795
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select stato_territorio "
                    + "      ,denominazione "
                    + "      ,sigla "
                    + "      ,desc_cittadinanza "
                    + "      ,raggruppamento "
                    + "      ,stato_appartenenza "
                    + "      ,'<!--' hide_begin "
                    + "      ,'-->' hide_end "
                    + "      ,  "
                    + " '</td></tr><tr><td class=\"AFCDataTD\" align=\"right\" colspan=\"2\">' "
                    + "       ||utente_aggiornamento "
                    + "       ||' ' "
                    + "       ||ad4_ccs.format_data_trunc(data_aggiornamento) label_upd "
                    + "  from ad4_stati_territori "
                    + " where stato_territorio = {STATO_TERRITORIO}" );
        if ( urlSTATO_TERRITORIO.getObjectValue() == null ) urlSTATO_TERRITORIO.setValue( -1 );
        command.addParameter( "STATO_TERRITORIO", urlSTATO_TERRITORIO, null );
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

//loadDataBind @5-5EBF202F
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setSTATO_TERRITORIO_LABEL(Utils.convertToString(ds.parse(record.get("STATO_TERRITORIO"), row.getSTATO_TERRITORIO_LABELField())));
            row.setHIDE_BEGIN(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGINField())));
            row.setSTATO_TERRITORIO(Utils.convertToLong(ds.parse(record.get("STATO_TERRITORIO"), row.getSTATO_TERRITORIOField())));
            row.setHIDE_END(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_ENDField())));
            row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
            row.setSIGLA(Utils.convertToString(ds.parse(record.get("SIGLA"), row.getSIGLAField())));
            row.setDESC_CITTADINANZA(Utils.convertToString(ds.parse(record.get("DESC_CITTADINANZA"), row.getDESC_CITTADINANZAField())));
            row.setRAGGRUPPAMENTO(Utils.convertToLong(ds.parse(record.get("RAGGRUPPAMENTO"), row.getRAGGRUPPAMENTOField())));
            row.setSTATO_APPARTENENZA(Utils.convertToLong(ds.parse(record.get("STATO_APPARTENENZA"), row.getSTATO_APPARTENENZAField())));
            row.setLABEL_UPD(Utils.convertToString(ds.parse(record.get("LABEL_UPD"), row.getLABEL_UPDField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-ECFCFBE5
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.STATO_TERRITORIO_INS(?,?,?,?,?,?,?)}" );
            command.addParameter( "IN_STATO_TERRITORIO", row.getSTATO_TERRITORIOField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSIGLA()) ) row.setSIGLA( "" );
            command.addParameter( "IN_SIGLA", row.getSIGLAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDESC_CITTADINANZA()) ) row.setDESC_CITTADINANZA( "" );
            command.addParameter( "IN_DESC_CITTADINANZA", row.getDESC_CITTADINANZAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_RAGGRUPPAMENTO", row.getRAGGRUPPAMENTOField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_STATO_APPARTENENZA", row.getSTATO_APPARTENENZAField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//update @5-BF25B137
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.STATO_TERRITORIO_UPD(?,?,?,?,?,?,?)}" );
            command.addParameter( "IN_STATO_TERRITORIO", urlSTATO_TERRITORIO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSIGLA()) ) row.setSIGLA( "" );
            command.addParameter( "IN_SIGLA", row.getSIGLAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDESC_CITTADINANZA()) ) row.setDESC_CITTADINANZA( "" );
            command.addParameter( "IN_DESC_CITTADINANZA", row.getDESC_CITTADINANZAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_RAGGRUPPAMENTO", row.getRAGGRUPPAMENTOField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_STATO_APPARTENENZA", row.getSTATO_APPARTENENZAField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @5-4A944004
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.STATO_TERRITORIO_DEL(?)}" );
            command.addParameter( "IN_STATO_TERRITORIO", urlSTATO_TERRITORIO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @5-8FA2D056
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "STATO_TERRITORIO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSTATO_TERRITORIO;
            } else if ( "STATO_TERRITORIO".equals(name) && prefix == null ) {
                param = ctrlSTATO_TERRITORIO;
            }
            if ( "DENOMINAZIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDENOMINAZIONE;
            } else if ( "DENOMINAZIONE".equals(name) && prefix == null ) {
                param = ctrlDENOMINAZIONE;
            }
            if ( "SIGLA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSIGLA;
            } else if ( "SIGLA".equals(name) && prefix == null ) {
                param = ctrlSIGLA;
            }
            if ( "DESC_CITTADINANZA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDESC_CITTADINANZA;
            } else if ( "DESC_CITTADINANZA".equals(name) && prefix == null ) {
                param = ctrlDESC_CITTADINANZA;
            }
            if ( "RAGGRUPPAMENTO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlRAGGRUPPAMENTO;
            } else if ( "RAGGRUPPAMENTO".equals(name) && prefix == null ) {
                param = ctrlRAGGRUPPAMENTO;
            }
            if ( "STATO_APPARTENENZA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSTATO_APPARTENENZA;
            } else if ( "STATO_APPARTENENZA".equals(name) && prefix == null ) {
                param = ctrlSTATO_APPARTENENZA;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "STATO_TERRITORIO".equals(name) && "url".equals(prefix) ) {
                param = urlSTATO_TERRITORIO;
            } else if ( "STATO_TERRITORIO".equals(name) && prefix == null ) {
                param = urlSTATO_TERRITORIO;
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

