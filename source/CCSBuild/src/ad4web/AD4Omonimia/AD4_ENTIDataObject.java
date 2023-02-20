//AD4_ENTI DataSource @6-97658B0D
package ad4web.AD4Omonimia;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_ENTI DataSource

//class DataObject Header @6-7ED12517
public class AD4_ENTIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-9743D7E9
    

    TextField postENTE = new TextField(null, null);
    
    TextField postENTE_ORIG = new TextField(null, null);
    
    TextField postDESCRIZIONE = new TextField(null, null);
    
    TextField postBITMAP = new TextField(null, null);
    
    TextField postDISEGNO = new TextField(null, null);
    
    TextField postNOTE = new TextField(null, null);
    
    LongField postSOGGETTO = new LongField(null, null);
    
    TextField urlENTE = new TextField(null, null);
    
    TextField urlSE_NUOVO = new TextField(null, null);
    
    LongField urlSOGGETTO = new LongField(null, null);
    
    TextField sesAD4ENTE = new TextField(null, null);
    

    private AD4_ENTIRow row = new AD4_ENTIRow();

//End attributes of DataObject

//properties of DataObject @6-C10624F1

    public void  setPostENTE( String param ) {
        this.postENTE.setValue( param );
    }

    public void  setPostENTE( Object param ) {
        this.postENTE.setValue( param );
    }

    public void  setPostENTE( Object param, Format ignore ) {
        this.postENTE.setValue( param );
    }

    public void  setPostENTE_ORIG( String param ) {
        this.postENTE_ORIG.setValue( param );
    }

    public void  setPostENTE_ORIG( Object param ) {
        this.postENTE_ORIG.setValue( param );
    }

    public void  setPostENTE_ORIG( Object param, Format ignore ) {
        this.postENTE_ORIG.setValue( param );
    }

    public void  setPostDESCRIZIONE( String param ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostDESCRIZIONE( Object param ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostDESCRIZIONE( Object param, Format ignore ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostBITMAP( String param ) {
        this.postBITMAP.setValue( param );
    }

    public void  setPostBITMAP( Object param ) {
        this.postBITMAP.setValue( param );
    }

    public void  setPostBITMAP( Object param, Format ignore ) {
        this.postBITMAP.setValue( param );
    }

    public void  setPostDISEGNO( String param ) {
        this.postDISEGNO.setValue( param );
    }

    public void  setPostDISEGNO( Object param ) {
        this.postDISEGNO.setValue( param );
    }

    public void  setPostDISEGNO( Object param, Format ignore ) {
        this.postDISEGNO.setValue( param );
    }

    public void  setPostNOTE( String param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param, Format ignore ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostSOGGETTO( long param ) {
        this.postSOGGETTO.setValue( param );
    }

    public void  setPostSOGGETTO( long param, Format ignore ) throws java.text.ParseException {
        this.postSOGGETTO.setValue( param );
    }

    public void  setPostSOGGETTO( Object param, Format format ) throws java.text.ParseException {
        this.postSOGGETTO.setValue( param, format );
    }

    public void  setPostSOGGETTO( Long param ) {
        this.postSOGGETTO.setValue( param );
    }

    public void  setUrlENTE( String param ) {
        this.urlENTE.setValue( param );
    }

    public void  setUrlENTE( Object param ) {
        this.urlENTE.setValue( param );
    }

    public void  setUrlENTE( Object param, Format ignore ) {
        this.urlENTE.setValue( param );
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

    public void  setUrlSOGGETTO( long param ) {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setUrlSOGGETTO( long param, Format ignore ) throws java.text.ParseException {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setUrlSOGGETTO( Object param, Format format ) throws java.text.ParseException {
        this.urlSOGGETTO.setValue( param, format );
    }

    public void  setUrlSOGGETTO( Long param ) {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setSesAD4ENTE( String param ) {
        this.sesAD4ENTE.setValue( param );
    }

    public void  setSesAD4ENTE( Object param ) {
        this.sesAD4ENTE.setValue( param );
    }

    public void  setSesAD4ENTE( Object param, Format ignore ) {
        this.sesAD4ENTE.setValue( param );
    }

    public AD4_ENTIRow getRow() {
        return row;
    }

    public void setRow( AD4_ENTIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-6FFB40B1
    public AD4_ENTIDataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new AD4_ENTIDataObjectHandler() );
    }
//End constructor

//load @6-DD9C78CD
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '<input class=\"AFCInputDisplay\" maxlength=\"4\" size=\"5\" value=\"'||ente||'\" name=\"ENTE\" readonly >' ENTE "
                    + "     , ENTE ENTE_ORIG "
                    + "     ,  "
                    + "DESCRIZIONE "
                    + "     , BITMAP "
                    + "     , DISEGNO "
                    + "     , NOTE "
                    + "     ,  "
                    + "decode(nvl({P_SOGGETTO},0),-1,0,0,SOGGETTO,{P_SOGGETTO}) SOGGETTO "
                    + "     ,  "
                    + "AD4WEB.GET_DATI_SOGGETTO(decode(nvl({P_SOGGETTO},0),-1,0,0,SOGGETTO,{P_SOGGETTO})) DATI_SOGGETTO "
                    + "     ,  "
                    + "decode(decode(nvl({P_SOGGETTO},0),-1,0,0,nvl(SOGGETTO,0),{P_SOGGETTO}),0,'',' ') titolo_sogg "
                    + "     , decode('{SE_NUOVO}','Y','','<img title=\"Modifica Anagrafica\" height=\"18\" src=\"../common/images/AD4/sogg_cambia.gif\" width=\"18\" border=\"0\">Modifica&nbsp;Anagrafica') mod_registrazione_anagrafica "
                    + "     , decode(decode(nvl({P_SOGGETTO},0),-1,0,0,nvl(SOGGETTO,0),{P_SOGGETTO}),0,'',decode('{SE_NUOVO}','Y','','<img title=\"Svuota Anagrafica\" height=\"18\" src=\"../common/images/AD4/sogg_svuota.gif\" width=\"18\" border=\"0\">Svuota&nbsp;Anagrafica')) svuota_reg_anagrafica "
                    + "     , '{SE_NUOVO}' SE_NUOVO "
                    + "     , '{P_ENTE}' P_ENTE "
                    + "  FROM ENTI "
                    + " WHERE ENTE = nvl('{P_ENTE}','{AD4ENTE}') "
                    + "   AND 'N'  = '{P_SE_NUOVO}' "
                    + "UNION "
                    + "SELECT '<input class=\"AFCInput\" style=\"TEXT-TRANSFORM: uppercase\" maxlength=\"4\" size=\"5\" value=\"\" name=\"ENTE\"></input>' ENTE "
                    + "     , '' ENTE_ORIG "
                    + "     , '' DESCRIZIONE "
                    + "     , '' BITMAP "
                    + "     , '' DISEGNO "
                    + "     , '' NOTE "
                    + "     , TO_NUMBER(NULL) SOGGETTO "
                    + "     , '' DATI_SOGGETTO "
                    + "     , '' titolo_sogg "
                    + "     , '' mod_registrazione_anagrafica "
                    + "     , '' svuota_reg_anagrafica "
                    + "     , '{SE_NUOVO}' SE_NUOVO "
                    + "     , '{P_ENTE}' P_ENTE "
                    + "  FROM DUAL "
                    + " WHERE 'Y'  = '{P_SE_NUOVO}'" );
        if ( StringUtils.isEmpty( (String) urlENTE.getObjectValue() ) ) urlENTE.setValue( "" );
        command.addParameter( "P_ENTE", urlENTE, null );
        if ( StringUtils.isEmpty( (String) urlSE_NUOVO.getObjectValue() ) ) urlSE_NUOVO.setValue( "N" );
        command.addParameter( "P_SE_NUOVO", urlSE_NUOVO, null );
        if ( urlSOGGETTO.getObjectValue() == null ) urlSOGGETTO.setValue( 0 );
        command.addParameter( "P_SOGGETTO", urlSOGGETTO, null );
        if ( StringUtils.isEmpty( (String) sesAD4ENTE.getObjectValue() ) ) sesAD4ENTE.setValue( "" );
        command.addParameter( "AD4ENTE", sesAD4ENTE, null );
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

//loadDataBind @6-98A0E6D8
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setSVUOTA_REG_ANAGRAFICA(Utils.convertToString(ds.parse(record.get("SVUOTA_REG_ANAGRAFICA"), row.getSVUOTA_REG_ANAGRAFICAField())));
            row.setMOD_REGISTRAZIONE_ANAGRAFICA(Utils.convertToString(ds.parse(record.get("MOD_REGISTRAZIONE_ANAGRAFICA"), row.getMOD_REGISTRAZIONE_ANAGRAFICAField())));
            row.setENTE(Utils.convertToString(ds.parse(record.get("ENTE"), row.getENTEField())));
            row.setENTE_ORIG(Utils.convertToString(ds.parse(record.get("ENTE_ORIG"), row.getENTE_ORIGField())));
            row.setSOGGETTO(Utils.convertToLong(ds.parse(record.get("SOGGETTO"), row.getSOGGETTOField())));
            row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
            row.setBITMAP(Utils.convertToString(ds.parse(record.get("BITMAP"), row.getBITMAPField())));
            row.setDISEGNO(Utils.convertToString(ds.parse(record.get("DISEGNO"), row.getDISEGNOField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
            row.setTITOLO_SOGG(Utils.convertToString(ds.parse(record.get("TITOLO_SOGG"), row.getTITOLO_SOGGField())));
            row.setDATI_SOGGETTO(Utils.convertToString(ds.parse(record.get("DATI_SOGGETTO"), row.getDATI_SOGGETTOField())));
            row.setS_ENTE(Utils.convertToString(ds.parse(record.get("ENTE_ORIG"), row.getS_ENTEField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @6-0B548BA7
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{? = call AD4WEB.ENTI_PM(?,?,?,?,?,?,?)}" );
            command.addParameter( "ENTE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postENTE.getObjectValue() ) ) postENTE.setValue( "" );
            command.addParameter( "P_ENTE", postENTE, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postENTE_ORIG.getObjectValue() ) ) postENTE_ORIG.setValue( "" );
            command.addParameter( "P_ENTE_OLD", postENTE_ORIG, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
            command.addParameter( "P_DESCRIZIONE", postDESCRIZIONE, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postBITMAP.getObjectValue() ) ) postBITMAP.setValue( "" );
            command.addParameter( "P_BITMAP", postBITMAP, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDISEGNO.getObjectValue() ) ) postDISEGNO.setValue( "" );
            command.addParameter( "P_DISEGNO", postDISEGNO, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_SOGGETTO", postSOGGETTO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

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

//delete @6-F2B36554
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM ENTI");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1 = WhereParams.rawOperation( "ENTE", FieldOperation.EQUAL, postENTE, null, ds );
            if (StringUtils.isEmpty(where1)) command.setCmdExecution(false);
            String whereParams = where1;

            if ( where1 == null ) {
                addError(getResourceBundle().getString("CustomOperationError_MissingParameters"));
            }
            if ( ! StringUtils.isEmpty(whereParams) ) {
                if ( ! StringUtils.isEmpty(command.getWhere()) ) {
                    command.setWhere( command.getWhere() + " AND (" + whereParams + ")" );
                } else {
                    command.setWhere( whereParams );
                }
            }

//End delete

//deleteDataBound @6-5B959F17
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );
            if (!command.isCmdExecution()) {
                ds.closeConnection();
                return false;
            }

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @6-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @6-4DAA5833
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ENTE".equals(name) && "post".equals(prefix) ) {
                param = postENTE;
            } else if ( "ENTE".equals(name) && prefix == null ) {
                param = postENTE;
            }
            if ( "ENTE_ORIG".equals(name) && "post".equals(prefix) ) {
                param = postENTE_ORIG;
            } else if ( "ENTE_ORIG".equals(name) && prefix == null ) {
                param = postENTE_ORIG;
            }
            if ( "DESCRIZIONE".equals(name) && "post".equals(prefix) ) {
                param = postDESCRIZIONE;
            } else if ( "DESCRIZIONE".equals(name) && prefix == null ) {
                param = postDESCRIZIONE;
            }
            if ( "BITMAP".equals(name) && "post".equals(prefix) ) {
                param = postBITMAP;
            } else if ( "BITMAP".equals(name) && prefix == null ) {
                param = postBITMAP;
            }
            if ( "DISEGNO".equals(name) && "post".equals(prefix) ) {
                param = postDISEGNO;
            } else if ( "DISEGNO".equals(name) && prefix == null ) {
                param = postDISEGNO;
            }
            if ( "NOTE".equals(name) && "post".equals(prefix) ) {
                param = postNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = postNOTE;
            }
            if ( "SOGGETTO".equals(name) && "post".equals(prefix) ) {
                param = postSOGGETTO;
            } else if ( "SOGGETTO".equals(name) && prefix == null ) {
                param = postSOGGETTO;
            }
            if ( "ENTE".equals(name) && "url".equals(prefix) ) {
                param = urlENTE;
            } else if ( "ENTE".equals(name) && prefix == null ) {
                param = urlENTE;
            }
            if ( "SE_NUOVO".equals(name) && "url".equals(prefix) ) {
                param = urlSE_NUOVO;
            } else if ( "SE_NUOVO".equals(name) && prefix == null ) {
                param = urlSE_NUOVO;
            }
            if ( "SOGGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlSOGGETTO;
            } else if ( "SOGGETTO".equals(name) && prefix == null ) {
                param = urlSOGGETTO;
            }
            if ( "AD4ENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4ENTE;
            } else if ( "AD4ENTE".equals(name) && prefix == null ) {
                param = sesAD4ENTE;
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

