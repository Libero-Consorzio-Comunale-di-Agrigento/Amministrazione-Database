//AslComuneImposta DataSource @5-080BF2AF
package ad4web.Ad4DizionariAslComuneImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AslComuneImposta DataSource

//class DataObject Header @5-4EF28125
public class AslComuneImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-678F7C3F
    

    LongField ctrlPROVINCIA = new LongField(null, null);
    
    LongField ctrlCOMUNE = new LongField(null, null);
    
    LongField urlIN_REGIONE_ASL = new LongField(null, null);
    
    LongField urlCODICE_ASL = new LongField(null, null);
    
    TextField ctrlPROPOSTA = new TextField(null, null);
    
    TextField ctrlATTIVA = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    LongField urlREGIONE_ASL = new LongField(null, null);
    
    TextField urlPROVINCIA = new TextField(null, null);
    
    TextField urlCOMUNE = new TextField(null, null);
    

    private AslComuneImpostaRow row = new AslComuneImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-B22B009D

    public void  setCtrlPROVINCIA( long param ) {
        this.ctrlPROVINCIA.setValue( param );
    }

    public void  setCtrlPROVINCIA( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlPROVINCIA.setValue( param );
    }

    public void  setCtrlPROVINCIA( Object param, Format format ) throws java.text.ParseException {
        this.ctrlPROVINCIA.setValue( param, format );
    }

    public void  setCtrlPROVINCIA( Long param ) {
        this.ctrlPROVINCIA.setValue( param );
    }

    public void  setCtrlCOMUNE( long param ) {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlCOMUNE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlCOMUNE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCOMUNE.setValue( param, format );
    }

    public void  setCtrlCOMUNE( Long param ) {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setUrlIN_REGIONE_ASL( long param ) {
        this.urlIN_REGIONE_ASL.setValue( param );
    }

    public void  setUrlIN_REGIONE_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.urlIN_REGIONE_ASL.setValue( param );
    }

    public void  setUrlIN_REGIONE_ASL( Object param, Format format ) throws java.text.ParseException {
        this.urlIN_REGIONE_ASL.setValue( param, format );
    }

    public void  setUrlIN_REGIONE_ASL( Long param ) {
        this.urlIN_REGIONE_ASL.setValue( param );
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

    public void  setCtrlPROPOSTA( String param ) {
        this.ctrlPROPOSTA.setValue( param );
    }

    public void  setCtrlPROPOSTA( Object param ) {
        this.ctrlPROPOSTA.setValue( param );
    }

    public void  setCtrlPROPOSTA( Object param, Format ignore ) {
        this.ctrlPROPOSTA.setValue( param );
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

    public void  setSesUtente( String param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param, Format ignore ) {
        this.sesUtente.setValue( param );
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

    public void  setUrlPROVINCIA( String param ) {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlPROVINCIA( Object param ) {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlPROVINCIA( Object param, Format ignore ) {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlCOMUNE( String param ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( Object param ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( Object param, Format ignore ) {
        this.urlCOMUNE.setValue( param );
    }

    public AslComuneImpostaRow getRow() {
        return row;
    }

    public void setRow( AslComuneImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-0484D09F
    public AslComuneImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-67CBFFEA
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select provincia "
                    + "      ,comune "
                    + "      ,ad4_ccs.get_comune_denominazione(provincia,comune)  comune_desc "
                    + "      ,ad4_ccs.get_asl_descrizione(regione_asl,codice_asl) asl_desc "
                    + "      ,regione_asl "
                    + "      ,codice_asl "
                    + "      ,proposta "
                    + "      ,attiva "
                    + "      ,  "
                    + " '</td></tr><tr><td class=\"AFCDataTD\" align=\"right\" colspan=\"2\">' "
                    + "       ||utente_aggiornamento "
                    + "       ||' ' "
                    + "       ||ad4_ccs.format_data_trunc(data_aggiornamento) label_upd "
                    + "      ,null comune_lov "
                    + "  from ad4_asl_comune "
                    + " where provincia = '{PROVINCIA}' "
                    + "   and comune = '{COMUNE}' "
                    + "   and regione_asl = '{REGIONE_ASL}' "
                    + "   and codice_asl = '{CODICE_ASL}' "
                    + "union "
                    + "select null                                                         provincia "
                    + "      ,null                                                         comune "
                    + "      ,null                                                         comune_desc "
                    + "      ,ad4_ccs.get_asl_descrizione('{REGIONE_ASL}','{CODICE_ASL}')  asl_desc "
                    + "      ,to_number('{REGIONE_ASL}')                                   regione_asl "
                    + "      ,to_number('{CODICE_ASL}')                                    codice_asl "
                    + "      ,null                                                         proposta "
                    + "      ,null                                                         attiva "
                    + "      ,null                                                         label_upd "
                    + "      ,ad4_ccs.link_multi_lov('Ad4DizionariComuniLov','','Cerca Comune..','AslComuneImposta','PROVINCIA#COMUNE#COMUNE_DESC')  "
                    + "       ||'<a class=\"AFCDataLink\" href=\"#\" onclick=\"' "
                    + "       ||'self.document.forms[0].PROVINCIA.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE_DESC.value='''';' "
                    + "       ||'\" title=\"Elimina Comune\">' "
                    + "       ||'<img height=\"18\" src=\"../images/clear.gif\" width=\"18\" border=\"0\">' "
                    + "       ||'</a>'                                                                  comune_lov "
                    + "  from dual "
                    + " where '{PROVINCIA}' is null "
                    + "   and '{COMUNE}' is null" );
        if ( urlREGIONE_ASL.getObjectValue() == null ) urlREGIONE_ASL.setValue( -1 );
        command.addParameter( "REGIONE_ASL", urlREGIONE_ASL, null );
        if ( urlCODICE_ASL.getObjectValue() == null ) urlCODICE_ASL.setValue( -1 );
        command.addParameter( "CODICE_ASL", urlCODICE_ASL, null );
        if ( StringUtils.isEmpty( (String) urlPROVINCIA.getObjectValue() ) ) urlPROVINCIA.setValue( "" );
        command.addParameter( "PROVINCIA", urlPROVINCIA, null );
        if ( StringUtils.isEmpty( (String) urlCOMUNE.getObjectValue() ) ) urlCOMUNE.setValue( "" );
        command.addParameter( "COMUNE", urlCOMUNE, null );
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

//loadDataBind @5-4760FB26
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setASL_DESC(Utils.convertToString(ds.parse(record.get("ASL_DESC"), row.getASL_DESCField())));
            row.setREGIONE_ASL(Utils.convertToLong(ds.parse(record.get("REGIONE_ASL"), row.getREGIONE_ASLField())));
            row.setCODICE_ASL(Utils.convertToLong(ds.parse(record.get("CODICE_ASL"), row.getCODICE_ASLField())));
            row.setCOMUNE_DESC(Utils.convertToString(ds.parse(record.get("COMUNE_DESC"), row.getCOMUNE_DESCField())));
            row.setCOMUNE_LOV(Utils.convertToString(ds.parse(record.get("COMUNE_LOV"), row.getCOMUNE_LOVField())));
            row.setPROVINCIA(Utils.convertToLong(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
            row.setCOMUNE(Utils.convertToLong(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
            row.setPROPOSTA(Utils.convertToString(ds.parse(record.get("PROPOSTA"), row.getPROPOSTAField())));
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

//insert @5-A98274CB
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ASL_COMUNE_INS(?,?,?,?,?,?,?)}" );
            command.addParameter( "IN_PROVINCIA", row.getPROVINCIAField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", row.getCOMUNEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_REGIONE_ASL", urlIN_REGIONE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CODICE_ASL", urlCODICE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPROPOSTA()) ) row.setPROPOSTA( "" );
            command.addParameter( "IN_PROPOSTA", row.getPROPOSTAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getATTIVA()) ) row.setATTIVA( "" );
            command.addParameter( "IN_ATTIVA", row.getATTIVAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
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

//update @5-641A027F
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ASL_COMUNE_UPD(?,?,?,?,?,?,?)}" );
            command.addParameter( "IN_PROVINCIA", row.getPROVINCIAField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", row.getCOMUNEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_REGIONE_ASL", urlREGIONE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CODICE_ASL", urlCODICE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPROPOSTA()) ) row.setPROPOSTA( "" );
            command.addParameter( "IN_PROPOSTA", row.getPROPOSTAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getATTIVA()) ) row.setATTIVA( "" );
            command.addParameter( "IN_ATTIVA", row.getATTIVAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
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

//delete @5-559C78DC
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ASL_COMUNE_DEL(?,?,?,?)}" );
            command.addParameter( "IN_PROVINCIA", row.getPROVINCIAField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", row.getCOMUNEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_REGIONE_ASL", urlIN_REGIONE_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
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

//getParameterByName @5-F61A90F2
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "PROVINCIA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPROVINCIA;
            } else if ( "PROVINCIA".equals(name) && prefix == null ) {
                param = ctrlPROVINCIA;
            }
            if ( "COMUNE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = ctrlCOMUNE;
            }
            if ( "IN_REGIONE_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlIN_REGIONE_ASL;
            } else if ( "IN_REGIONE_ASL".equals(name) && prefix == null ) {
                param = urlIN_REGIONE_ASL;
            }
            if ( "CODICE_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlCODICE_ASL;
            } else if ( "CODICE_ASL".equals(name) && prefix == null ) {
                param = urlCODICE_ASL;
            }
            if ( "PROPOSTA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPROPOSTA;
            } else if ( "PROPOSTA".equals(name) && prefix == null ) {
                param = ctrlPROPOSTA;
            }
            if ( "ATTIVA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlATTIVA;
            } else if ( "ATTIVA".equals(name) && prefix == null ) {
                param = ctrlATTIVA;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "REGIONE_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlREGIONE_ASL;
            } else if ( "REGIONE_ASL".equals(name) && prefix == null ) {
                param = urlREGIONE_ASL;
            }
            if ( "PROVINCIA".equals(name) && "url".equals(prefix) ) {
                param = urlPROVINCIA;
            } else if ( "PROVINCIA".equals(name) && prefix == null ) {
                param = urlPROVINCIA;
            }
            if ( "COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = urlCOMUNE;
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

