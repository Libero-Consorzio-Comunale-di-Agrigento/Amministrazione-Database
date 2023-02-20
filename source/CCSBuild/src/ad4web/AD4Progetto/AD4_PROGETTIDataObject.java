//AD4_PROGETTI DataSource @23-3736D629
package ad4web.AD4Progetto;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_PROGETTI DataSource

//class DataObject Header @23-17AB3FE0
public class AD4_PROGETTIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @23-9BA8C9DF
    

    TextField postPROGETTO = new TextField(null, null);
    
    TextField postPROGETTO_ORIG = new TextField(null, null);
    
    TextField postDESCRIZIONE = new TextField(null, null);
    
    DoubleField postPRIORITA = new DoubleField(null, null);
    
    TextField postNOTE = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    
    TextField urlSE_NUOVO = new TextField(null, null);
    
    TextField sesAD4PROGETTO = new TextField(null, null);
    

    private AD4_PROGETTIRow row = new AD4_PROGETTIRow();

//End attributes of DataObject

//properties of DataObject @23-D09E6418

    public void  setPostPROGETTO( String param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param, Format ignore ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO_ORIG( String param ) {
        this.postPROGETTO_ORIG.setValue( param );
    }

    public void  setPostPROGETTO_ORIG( Object param ) {
        this.postPROGETTO_ORIG.setValue( param );
    }

    public void  setPostPROGETTO_ORIG( Object param, Format ignore ) {
        this.postPROGETTO_ORIG.setValue( param );
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

    public void  setPostPRIORITA( double param ) {
        this.postPRIORITA.setValue( param );
    }

    public void  setPostPRIORITA( double param, Format ignore ) throws java.text.ParseException {
        this.postPRIORITA.setValue( param );
    }

    public void  setPostPRIORITA( Object param, Format format ) throws java.text.ParseException {
        this.postPRIORITA.setValue( param, format );
    }

    public void  setPostPRIORITA( Double param ) {
        this.postPRIORITA.setValue( param );
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

    public void  setUrlPROGETTO( String param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param, Format ignore ) {
        this.urlPROGETTO.setValue( param );
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

    public void  setSesAD4PROGETTO( String param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param, Format ignore ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public AD4_PROGETTIRow getRow() {
        return row;
    }

    public void setRow( AD4_PROGETTIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @23-F4F45A05
    public AD4_PROGETTIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @23-3C53CAFC
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '<input class=\"AFCInputDisplay\" maxlength=\"8\" size=\"8\" value=\"'||progetto||'\" name=\"PROGETTO\" readonly >' PROGETTO, "
                    + "       PROGETTO PROGETTO_ORIG, "
                    + "       DESCRIZIONE,  "
                    + "       PRIORITA,  "
                    + " "
                    + "       NOTE, "
                    + "       '{SE_NUOVO}' SE_NUOVO, "
                    + "       '{PROGETTO}' P_PROGETTO, "
                    + "       '{AD4PROGETTO}' AD4PROGETTO "
                    + "  FROM PROGETTI "
                    + " WHERE PROGETTO = NVL('{PROGETTO}','{AD4PROGETTO}') "
                    + "   AND '{SE_NUOVO}' = 'N' "
                    + "UNION "
                    + "SELECT '<input class=\"AFCInput\" style=\"TEXT-TRANSFORM: uppercase\" maxlength=\"8\" size=\"8\" value=\"'||'{PROG_FORM}'||'\" name=\"PROGETTO\"></input>' PROGETTO,  "
                    + "       '' PROGETTO_ORIG, "
                    + "       '{DESC_FORM}' DESCRIZIONE,  "
                    + "       DECODE({PRIO_FORM},-1,TO_NUMBER(NULL),{PRIO_FORM}) PRIORITA,  "
                    + "       '{NOTE_FORM}' NOTE, "
                    + "       '{SE_NUOVO}' SE_NUOVO, "
                    + "       '{PROGETTO}' P_PROGETTO, "
                    + "       '{AD4PROGETTO}' AD4PROGETTO "
                    + "  FROM DUAL "
                    + " WHERE '{SE_NUOVO}' = 'Y' "
                    + "    OR NVL('{PROGETTO}','{AD4PROGETTO}') is null" );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlSE_NUOVO.getObjectValue() ) ) urlSE_NUOVO.setValue( "N" );
        command.addParameter( "SE_NUOVO", urlSE_NUOVO, null );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        if ( StringUtils.isEmpty( (String) postPROGETTO.getObjectValue() ) ) postPROGETTO.setValue( "" );
        command.addParameter( "PROG_FORM", postPROGETTO, null );
        if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
        command.addParameter( "DESC_FORM", postDESCRIZIONE, null );
        if ( postPRIORITA.getObjectValue() == null ) postPRIORITA.setValue( -1 );
        command.addParameter( "PRIO_FORM", postPRIORITA, null );
        if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
        command.addParameter( "NOTE_FORM", postNOTE, null );
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

//loadDataBind @23-6C9716D4
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
            row.setPROGETTO_ORIG(Utils.convertToString(ds.parse(record.get("PROGETTO_ORIG"), row.getPROGETTO_ORIGField())));
            row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
            row.setPRIORITA(Utils.convertToDouble(ds.parse(record.get("PRIORITA"), row.getPRIORITAField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
        }
//End loadDataBind

//End of load @23-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @23-CEE989C8
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ ? = call AD4WEB.PROGETTI_PM ( ?, ?, ?, ?, ? ) }" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPROGETTO.getObjectValue() ) ) postPROGETTO.setValue( "" );
            command.addParameter( "P_PROGETTO", postPROGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPROGETTO_ORIG.getObjectValue() ) ) postPROGETTO_ORIG.setValue( "" );
            command.addParameter( "P_PROGETTO_OLD", postPROGETTO_ORIG, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
            command.addParameter( "P_DESCRIZIONE", postDESCRIZIONE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_PRIORITA", postPRIORITA, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @23-8F830377
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ call AD4WEB.PROGETTI_PD ( ? ) }" );
            if ( StringUtils.isEmpty( (String) postPROGETTO.getObjectValue() ) ) postPROGETTO.setValue( "" );
            command.addParameter( "P_PROGETTO", postPROGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @23-C34F3E9A
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "PROGETTO".equals(name) && "post".equals(prefix) ) {
                param = postPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = postPROGETTO;
            }
            if ( "PROGETTO_ORIG".equals(name) && "post".equals(prefix) ) {
                param = postPROGETTO_ORIG;
            } else if ( "PROGETTO_ORIG".equals(name) && prefix == null ) {
                param = postPROGETTO_ORIG;
            }
            if ( "DESCRIZIONE".equals(name) && "post".equals(prefix) ) {
                param = postDESCRIZIONE;
            } else if ( "DESCRIZIONE".equals(name) && prefix == null ) {
                param = postDESCRIZIONE;
            }
            if ( "PRIORITA".equals(name) && "post".equals(prefix) ) {
                param = postPRIORITA;
            } else if ( "PRIORITA".equals(name) && prefix == null ) {
                param = postPRIORITA;
            }
            if ( "NOTE".equals(name) && "post".equals(prefix) ) {
                param = postNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = postNOTE;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "SE_NUOVO".equals(name) && "url".equals(prefix) ) {
                param = urlSE_NUOVO;
            } else if ( "SE_NUOVO".equals(name) && prefix == null ) {
                param = urlSE_NUOVO;
            }
            if ( "AD4PROGETTO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PROGETTO;
            } else if ( "AD4PROGETTO".equals(name) && prefix == null ) {
                param = sesAD4PROGETTO;
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

