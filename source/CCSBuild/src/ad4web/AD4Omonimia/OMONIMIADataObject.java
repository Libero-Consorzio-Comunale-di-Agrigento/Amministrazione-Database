//OMONIMIA DataSource @6-97658B0D
package ad4web.AD4Omonimia;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End OMONIMIA DataSource

//class DataObject Header @6-D252FC9F
public class OMONIMIADataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-BF71F5DE
    

    DoubleField postID_OMONIMIA = new DoubleField(null, null);
    
    TextField ctrlP_UTENTE = new TextField(null, null);
    
    TextField ctrlP_SOSIA = new TextField(null, null);
    
    LongField ctrlS_primario = new LongField(null, null);
    
    LongField ctrlUNIFICATO = new LongField(null, null);
    
    LongField ctrlCOPIATO = new LongField(null, null);
    
    LongField ctrlS_ignorare = new LongField(null, null);
    
    TextField ctrlNOTE = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    TextField urlS_SOSIA = new TextField(null, null);
    
    TextField urlS_UTENTE = new TextField(null, null);
    

    private OMONIMIARow row = new OMONIMIARow();

//End attributes of DataObject

//properties of DataObject @6-A43AD9E0

    public void  setPostID_OMONIMIA( double param ) {
        this.postID_OMONIMIA.setValue( param );
    }

    public void  setPostID_OMONIMIA( double param, Format ignore ) throws java.text.ParseException {
        this.postID_OMONIMIA.setValue( param );
    }

    public void  setPostID_OMONIMIA( Object param, Format format ) throws java.text.ParseException {
        this.postID_OMONIMIA.setValue( param, format );
    }

    public void  setPostID_OMONIMIA( Double param ) {
        this.postID_OMONIMIA.setValue( param );
    }

    public void  setCtrlP_UTENTE( String param ) {
        this.ctrlP_UTENTE.setValue( param );
    }

    public void  setCtrlP_UTENTE( Object param ) {
        this.ctrlP_UTENTE.setValue( param );
    }

    public void  setCtrlP_UTENTE( Object param, Format ignore ) {
        this.ctrlP_UTENTE.setValue( param );
    }

    public void  setCtrlP_SOSIA( String param ) {
        this.ctrlP_SOSIA.setValue( param );
    }

    public void  setCtrlP_SOSIA( Object param ) {
        this.ctrlP_SOSIA.setValue( param );
    }

    public void  setCtrlP_SOSIA( Object param, Format ignore ) {
        this.ctrlP_SOSIA.setValue( param );
    }

    public void  setCtrlS_primario( long param ) {
        this.ctrlS_primario.setValue( param );
    }

    public void  setCtrlS_primario( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlS_primario.setValue( param );
    }

    public void  setCtrlS_primario( Object param, Format format ) throws java.text.ParseException {
        this.ctrlS_primario.setValue( param, format );
    }

    public void  setCtrlS_primario( Long param ) {
        this.ctrlS_primario.setValue( param );
    }

    public void  setCtrlUNIFICATO( long param ) {
        this.ctrlUNIFICATO.setValue( param );
    }

    public void  setCtrlUNIFICATO( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlUNIFICATO.setValue( param );
    }

    public void  setCtrlUNIFICATO( Object param, Format format ) throws java.text.ParseException {
        this.ctrlUNIFICATO.setValue( param, format );
    }

    public void  setCtrlUNIFICATO( Long param ) {
        this.ctrlUNIFICATO.setValue( param );
    }

    public void  setCtrlCOPIATO( long param ) {
        this.ctrlCOPIATO.setValue( param );
    }

    public void  setCtrlCOPIATO( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCOPIATO.setValue( param );
    }

    public void  setCtrlCOPIATO( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCOPIATO.setValue( param, format );
    }

    public void  setCtrlCOPIATO( Long param ) {
        this.ctrlCOPIATO.setValue( param );
    }

    public void  setCtrlS_ignorare( long param ) {
        this.ctrlS_ignorare.setValue( param );
    }

    public void  setCtrlS_ignorare( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlS_ignorare.setValue( param );
    }

    public void  setCtrlS_ignorare( Object param, Format format ) throws java.text.ParseException {
        this.ctrlS_ignorare.setValue( param, format );
    }

    public void  setCtrlS_ignorare( Long param ) {
        this.ctrlS_ignorare.setValue( param );
    }

    public void  setCtrlNOTE( String param ) {
        this.ctrlNOTE.setValue( param );
    }

    public void  setCtrlNOTE( Object param ) {
        this.ctrlNOTE.setValue( param );
    }

    public void  setCtrlNOTE( Object param, Format ignore ) {
        this.ctrlNOTE.setValue( param );
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

    public void  setUrlS_SOSIA( String param ) {
        this.urlS_SOSIA.setValue( param );
    }

    public void  setUrlS_SOSIA( Object param ) {
        this.urlS_SOSIA.setValue( param );
    }

    public void  setUrlS_SOSIA( Object param, Format ignore ) {
        this.urlS_SOSIA.setValue( param );
    }

    public void  setUrlS_UTENTE( String param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param, Format ignore ) {
        this.urlS_UTENTE.setValue( param );
    }

    public OMONIMIARow getRow() {
        return row;
    }

    public void setRow( OMONIMIARow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-11B93EC8
    public OMONIMIADataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new OMONIMIADataObjectHandler() );
    }
//End constructor

//load @6-9BB10C8D
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT utente utente "
                    + "       , sosia sosia "
                    + "       ,  "
                    + "nominativo nominativo "
                    + "       ,  "
                    + "sosia_nominativo sosia_nominativo "
                    + "       ,Ad4web.GET_DATI_SOGGETTO(UTENTE.GET_SOGGETTO(Utente)) DATI_SOGGETTO "
                    + "       ,Ad4web.GET_DATI_SOGGETTO(UTENTE.GET_SOGGETTO(sosia)) DATI_SOGGETTO_SOSIA "
                    + "       , note "
                    + "       , ignorare "
                    + "       , scelto_primario "
                    + "       , unificato "
                    + "       , copiato "
                    + "       , omge.id_omonimia "
                    + "     FROM ricerca_omonimie riom "
                    + "       , omonimie_gestite omge "
                    + "   WHERE omge.primario (+)= utente "
                    + "      AND omge.secondario (+)= sosia "
                    + "      AND utente = '{S_UTENTE}' "
                    + "      AND sosia = '{S_SOSIA}' "
                    + "      AND not exists (select 1 from omonimie_gestite  where primario = sosia and secondario = utente)  "
                    + "UNION ALL "
                    + "  SELECT sosia utente "
                    + "       , utente sosia "
                    + "       , sosia_nominativo nominativo "
                    + "       , nominativo sosia_nominativo "
                    + "       ,Ad4web.GET_DATI_SOGGETTO(UTENTE.GET_SOGGETTO(sosia)) DATI_SOGGETTO "
                    + "       ,Ad4web.GET_DATI_SOGGETTO(UTENTE.GET_SOGGETTO(utente)) DATI_SOGGETTO_SOSIA "
                    + "       , note "
                    + "       , ignorare "
                    + "       , scelto_primario "
                    + "       , unificato "
                    + "       , copiato "
                    + "       , omge.id_omonimia "
                    + "    FROM ricerca_omonimie riom "
                    + "       , omonimie_gestite omge "
                    + "   WHERE omge.primario = sosia "
                    + "      AND omge.secondario = utente                 "
                    + "      AND sosia= '{S_UTENTE}' "
                    + "      AND utente= '{S_SOSIA}'" );
        if ( StringUtils.isEmpty( (String) urlS_SOSIA.getObjectValue() ) ) urlS_SOSIA.setValue( "" );
        command.addParameter( "S_SOSIA", urlS_SOSIA, null );
        if ( StringUtils.isEmpty( (String) urlS_UTENTE.getObjectValue() ) ) urlS_UTENTE.setValue( "" );
        command.addParameter( "S_UTENTE", urlS_UTENTE, null );
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

//loadDataBind @6-7CB89A4B
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
            row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
            row.setID_OMONIMIA(Utils.convertToLong(ds.parse(record.get("ID_OMONIMIA"), row.getID_OMONIMIAField())));
            row.setSCELTO_PRIMARIO(Utils.convertToLong(ds.parse(record.get("SCELTO_PRIMARIO"), row.getSCELTO_PRIMARIOField())));
            row.setDATI_SOGGETTO(Utils.convertToString(ds.parse(record.get("DATI_SOGGETTO"), row.getDATI_SOGGETTOField())));
            row.setUNIFICATO(Utils.convertToLong(ds.parse(record.get("UNIFICATO"), row.getUNIFICATOField())));
            row.setCOPIATO(Utils.convertToLong(ds.parse(record.get("COPIATO"), row.getCOPIATOField())));
            row.setP_UTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getP_UTENTEField())));
            row.setSOSIA(Utils.convertToString(ds.parse(record.get("SOSIA"), row.getSOSIAField())));
            row.setSOSIA_NOMINATIVO(Utils.convertToString(ds.parse(record.get("SOSIA_NOMINATIVO"), row.getSOSIA_NOMINATIVOField())));
            row.setDATI_SOGGETTO_SOSIA(Utils.convertToString(ds.parse(record.get("DATI_SOGGETTO_SOSIA"), row.getDATI_SOGGETTO_SOSIAField())));
            row.setP_SOSIA(Utils.convertToString(ds.parse(record.get("SOSIA"), row.getP_SOSIAField())));
            row.setS_ignorare(Utils.convertToLong(ds.parse(record.get("IGNORARE"), row.getS_ignorareField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @6-5B16DD8C
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call OMONIMIE_GESTITE_PKG.INS_UPD(?,?,?,?,?,?,?,?,?)}" );
            command.addParameter( "P_ID_OMONIMIA", postID_OMONIMIA, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getP_UTENTE()) ) row.setP_UTENTE( "" );
            command.addParameter( "P_PRIMARIO", row.getP_UTENTEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getP_SOSIA()) ) row.setP_SOSIA( "" );
            command.addParameter( "P_SECONDARIO", row.getP_SOSIAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_SCELTO_PRIMARIO", row.getS_primarioField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_UNIFICATO", row.getUNIFICATOField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_COPIATO", row.getCOPIATOField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_IGNORARE", row.getS_ignorareField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getNOTE()) ) row.setNOTE( "" );
            command.addParameter( "P_NOTE", row.getNOTEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "P_UTENTE_AGG", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @6-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @6-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//getParameterByName @6-5D15A15A
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_OMONIMIA".equals(name) && "post".equals(prefix) ) {
                param = postID_OMONIMIA;
            } else if ( "ID_OMONIMIA".equals(name) && prefix == null ) {
                param = postID_OMONIMIA;
            }
            if ( "P_UTENTE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlP_UTENTE;
            } else if ( "P_UTENTE".equals(name) && prefix == null ) {
                param = ctrlP_UTENTE;
            }
            if ( "P_SOSIA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlP_SOSIA;
            } else if ( "P_SOSIA".equals(name) && prefix == null ) {
                param = ctrlP_SOSIA;
            }
            if ( "s_primario".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlS_primario;
            } else if ( "s_primario".equals(name) && prefix == null ) {
                param = ctrlS_primario;
            }
            if ( "UNIFICATO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlUNIFICATO;
            } else if ( "UNIFICATO".equals(name) && prefix == null ) {
                param = ctrlUNIFICATO;
            }
            if ( "COPIATO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOPIATO;
            } else if ( "COPIATO".equals(name) && prefix == null ) {
                param = ctrlCOPIATO;
            }
            if ( "s_ignorare".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlS_ignorare;
            } else if ( "s_ignorare".equals(name) && prefix == null ) {
                param = ctrlS_ignorare;
            }
            if ( "NOTE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = ctrlNOTE;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "S_SOSIA".equals(name) && "url".equals(prefix) ) {
                param = urlS_SOSIA;
            } else if ( "S_SOSIA".equals(name) && prefix == null ) {
                param = urlS_SOSIA;
            }
            if ( "S_UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_UTENTE;
            } else if ( "S_UTENTE".equals(name) && prefix == null ) {
                param = urlS_UTENTE;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @6-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @6-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @6-305A023C
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

//fireBeforeExecuteSelectEvent @6-D00ACF95
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

//fireAfterExecuteSelectEvent @6-3BAD39CE
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

//fireBeforeBuildInsertEvent @6-FBA08B71
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

//fireBeforeExecuteInsertEvent @6-47AFA6A5
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

//fireAfterExecuteInsertEvent @6-E9CE95AE
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

//fireBeforeBuildSelectEvent @6-2405BE8B
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

//fireBeforeExecuteSelectEvent @6-E9DFF86B
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

//fireAfterExecuteSelectEvent @6-580A2987
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

//fireBeforeBuildSelectEvent @6-D021D0EA
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

//fireBeforeExecuteDeleteEvent @6-DD540FBB
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

//fireAfterExecuteDeleteEvent @6-2A6E2049
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

//class DataObject Tail @6-ED3F53A4
} // End of class DS
//End class DataObject Tail

