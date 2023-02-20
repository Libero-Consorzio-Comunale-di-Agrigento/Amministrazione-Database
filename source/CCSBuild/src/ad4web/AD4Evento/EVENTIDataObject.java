//EVENTI DataSource @56-BC1FCE6B
package ad4web.AD4Evento;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End EVENTI DataSource

//class DataObject Header @56-BE411A8E
public class EVENTIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @56-D5DF2BF0
    

    DoubleField postID_EVENTO_HIDDEN = new DoubleField(null, null);
    
    TextField urlID_EVENTO = new TextField(null, null);
    
    LongField sesAD4ACCERIPR = new LongField(null, null);
    

    private EVENTIRow row = new EVENTIRow();

//End attributes of DataObject

//properties of DataObject @56-CA64F51A

    public void  setPostID_EVENTO_HIDDEN( double param ) {
        this.postID_EVENTO_HIDDEN.setValue( param );
    }

    public void  setPostID_EVENTO_HIDDEN( double param, Format ignore ) throws java.text.ParseException {
        this.postID_EVENTO_HIDDEN.setValue( param );
    }

    public void  setPostID_EVENTO_HIDDEN( Object param, Format format ) throws java.text.ParseException {
        this.postID_EVENTO_HIDDEN.setValue( param, format );
    }

    public void  setPostID_EVENTO_HIDDEN( Double param ) {
        this.postID_EVENTO_HIDDEN.setValue( param );
    }

    public void  setUrlID_EVENTO( String param ) {
        this.urlID_EVENTO.setValue( param );
    }

    public void  setUrlID_EVENTO( Object param ) {
        this.urlID_EVENTO.setValue( param );
    }

    public void  setUrlID_EVENTO( Object param, Format ignore ) {
        this.urlID_EVENTO.setValue( param );
    }

    public void  setSesAD4ACCERIPR( long param ) {
        this.sesAD4ACCERIPR.setValue( param );
    }

    public void  setSesAD4ACCERIPR( long param, Format ignore ) throws java.text.ParseException {
        this.sesAD4ACCERIPR.setValue( param );
    }

    public void  setSesAD4ACCERIPR( Object param, Format format ) throws java.text.ParseException {
        this.sesAD4ACCERIPR.setValue( param, format );
    }

    public void  setSesAD4ACCERIPR( Long param ) {
        this.sesAD4ACCERIPR.setValue( param );
    }

    public EVENTIRow getRow() {
        return row;
    }

    public void setRow( EVENTIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @56-FCBB37DE
    public EVENTIDataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new EVENTIDataObjectHandler() );
    }
//End constructor

//load @56-F31C7625
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT EVENTI.ID_EVENTO,    "
                    + "       EVENTI.NOTIFICATO,  "
                    + "   "
                    + "       '<img class=\"\" title=\"'||decode(EVENTI.NOTIFICATO, 1, 'Notificato',  "
                    + "'NON Notificato')||'\" src=\"../common/images/AD4/'||decode(EVENTI.NOTIFICATO, 1, 'visto.GIF',  "
                    + "'elimina.gif')||'\" border=\"0\">&nbsp;' DESC_NOTIFICATO,  "
                    + "  "
                    + "       to_char(EVENTI.DATA,'dd/mm/yyyy hh24:mi:ss') data,  "
                    + "       EVENTI.TIPO,  "
                    + " "
                    + "       AD4_EVENTO.GET_TIPO_DESC(ID_EVENTO) DESC_TIPO,  "
                    + "   "
                    + "       AD4_EVENTO.GET_TIPO_DESC_RIDOTTA(ID_EVENTO) DESC_TIPO_RIDOTTA, "
                    + "       EVENTI.UTENTE,  "
                    + "       UTENTI.NOMINATIVO,  "
                    + "  "
                    + "       EVENTI.ISTANZA, "
                    + "       decode(EVENTI.ISTANZA,'','',EVENTI.ISTANZA||' - '||ISTANZE.DESCRIZIONE) ISTANZA_DESC, "
                    + "       EVENTI.MODULO,    "
                    + "       decode(EVENTI.MODULO,'','',EVENTI.MODULO||' - '||MODULI.DESCRIZIONE) MODULO_DESC, "
                    + "       EVENTI.TESTO, "
                    + "       EVENTI.TESTO||' '||ad4web.GET_EVEN_LINK(EVENTI.ID_EVENTO) testo_file, "
                    + "       EVENTI.ANNOTAZIONI,       "
                    + "       AD4_EVENTO.GET_DESC_GRAVITA(ID_EVENTO) DESC_GRAVITA, "
                    + "       EVENTI.DB_USER, "
                    + "       EVENTI.STATO STATO, "
                    + "       AD4_EVENTO.GET_DESC_STATO(ID_EVENTO) DESC_STATO,    "
                    + "       ad4_evento.check_file_locator(EVENTI.ID_EVENTO) check_file_locator, "
                    + "       {AD4ACCERIPR} RIPRISTINATI "
                    + "  FROM EVENTI, UTENTI, MODULI, ISTANZE "
                    + " WHERE EVENTI.ID_EVENTO = '{ID_EVENTO}' "
                    + "   AND UTENTI.UTENTE(+) = EVENTI.UTENTE "
                    + "   AND MODULI.MODULO(+) = EVENTI.MODULO "
                    + "   AND ISTANZE.ISTANZA(+) = EVENTI.ISTANZA" );
        if ( StringUtils.isEmpty( (String) urlID_EVENTO.getObjectValue() ) ) urlID_EVENTO.setValue( "" );
        command.addParameter( "ID_EVENTO", urlID_EVENTO, null );
        if ( sesAD4ACCERIPR.getObjectValue() == null ) sesAD4ACCERIPR.setValue( 0 );
        command.addParameter( "AD4ACCERIPR", sesAD4ACCERIPR, null );
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

//loadDataBind @56-E4C0AA45
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setID_EVENTO(Utils.convertToString(ds.parse(record.get("ID_EVENTO"), row.getID_EVENTOField())));
            row.setDATA(Utils.convertToString(ds.parse(record.get("DATA"), row.getDATAField())));
            row.setID_EVENTO_HIDDEN(Utils.convertToString(ds.parse(record.get("ID_EVENTO"), row.getID_EVENTO_HIDDENField())));
            row.setDESC_TIPO(Utils.convertToString(ds.parse(record.get("DESC_TIPO"), row.getDESC_TIPOField())));
            row.setTIPO(Utils.convertToString(ds.parse(record.get("TIPO"), row.getTIPOField())));
            row.setTESTO_FILE(Utils.convertToString(ds.parse(record.get("TESTO_FILE"), row.getTESTO_FILEField())));
            row.setCHECK_FILE_LOCATOR(Utils.convertToString(ds.parse(record.get("CHECK_FILE_LOCATOR"), row.getCHECK_FILE_LOCATORField())));
            row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
            row.setRIPRISTINATI(Utils.convertToString(ds.parse(record.get("RIPRISTINATI"), row.getRIPRISTINATIField())));
            row.setDB_USER(Utils.convertToString(ds.parse(record.get("DB_USER"), row.getDB_USERField())));
            row.setISTANZA_DESC(Utils.convertToString(ds.parse(record.get("ISTANZA_DESC"), row.getISTANZA_DESCField())));
            row.setMODULO_DESC(Utils.convertToString(ds.parse(record.get("MODULO_DESC"), row.getMODULO_DESCField())));
            row.setDESC_GRAVITA(Utils.convertToString(ds.parse(record.get("DESC_GRAVITA"), row.getDESC_GRAVITAField())));
            row.setDESC_STATO(Utils.convertToString(ds.parse(record.get("DESC_STATO"), row.getDESC_STATOField())));
            row.setANNOTAZIONI(Utils.convertToString(ds.parse(record.get("ANNOTAZIONI"), row.getANNOTAZIONIField())));
        }
//End loadDataBind

//End of load @56-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @56-3876B954
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ ? = call ACCESSO.RIPRISTINA ( ? ) }" );
            command.addParameter( "RIPRISTINATI", null, java.sql.Types.DOUBLE, 0, SPParameter.OUTPUT_PARAMETER );
            command.addParameter( "ID_EVENTO_HIDDEN", postID_EVENTO_HIDDEN, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @56-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @56-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @56-8125E789
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM EVENTI");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1 = WhereParams.rawOperation( "ID_EVENTO", FieldOperation.EQUAL, postID_EVENTO_HIDDEN, null, ds );
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

//deleteDataBound @56-5B959F17
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

//End of delete @56-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @56-C04CE08E
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_EVENTO_HIDDEN".equals(name) && "post".equals(prefix) ) {
                param = postID_EVENTO_HIDDEN;
            } else if ( "ID_EVENTO_HIDDEN".equals(name) && prefix == null ) {
                param = postID_EVENTO_HIDDEN;
            }
            if ( "ID_EVENTO".equals(name) && "url".equals(prefix) ) {
                param = urlID_EVENTO;
            } else if ( "ID_EVENTO".equals(name) && prefix == null ) {
                param = urlID_EVENTO;
            }
            if ( "AD4ACCERIPR".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4ACCERIPR;
            } else if ( "AD4ACCERIPR".equals(name) && prefix == null ) {
                param = sesAD4ACCERIPR;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @56-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @56-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @56-305A023C
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

//fireBeforeExecuteSelectEvent @56-D00ACF95
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

//fireAfterExecuteSelectEvent @56-3BAD39CE
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

//fireBeforeBuildInsertEvent @56-FBA08B71
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

//fireBeforeExecuteInsertEvent @56-47AFA6A5
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

//fireAfterExecuteInsertEvent @56-E9CE95AE
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

//fireBeforeBuildSelectEvent @56-2405BE8B
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

//fireBeforeExecuteSelectEvent @56-E9DFF86B
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

//fireAfterExecuteSelectEvent @56-580A2987
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

//fireBeforeBuildSelectEvent @56-D021D0EA
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

//fireBeforeExecuteDeleteEvent @56-DD540FBB
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

//fireAfterExecuteDeleteEvent @56-2A6E2049
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

//class DataObject Tail @56-ED3F53A4
} // End of class DS
//End class DataObject Tail

