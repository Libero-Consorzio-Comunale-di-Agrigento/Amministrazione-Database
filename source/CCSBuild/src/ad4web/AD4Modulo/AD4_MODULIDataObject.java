//AD4_MODULI DataSource @23-065B1125
package ad4web.AD4Modulo;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_MODULI DataSource

//class DataObject Header @23-004E45D4
public class AD4_MODULIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @23-1F60A2DF
    

    TextField postMODULO_ORIG = new TextField(null, null);
    
    TextField postPROGETTO_ORIG = new TextField(null, null);
    
    TextField postDESCRIZIONE = new TextField(null, null);
    
    TextField postNOTE = new TextField(null, null);
    
    TextField ctrlAMMINISTRATORE = new TextField(null, null);
    
    TextField sesAD4PROGETTO = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    
    TextField urlMODULO = new TextField(null, null);
    
    TextField postMODULO = new TextField(null, null);
    
    TextField postAMMINISTRATORE = new TextField(null, null);
    

    private AD4_MODULIRow row = new AD4_MODULIRow();

//End attributes of DataObject

//properties of DataObject @23-D8A31905

    public void  setPostMODULO_ORIG( String param ) {
        this.postMODULO_ORIG.setValue( param );
    }

    public void  setPostMODULO_ORIG( Object param ) {
        this.postMODULO_ORIG.setValue( param );
    }

    public void  setPostMODULO_ORIG( Object param, Format ignore ) {
        this.postMODULO_ORIG.setValue( param );
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

    public void  setPostNOTE( String param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param, Format ignore ) {
        this.postNOTE.setValue( param );
    }

    public void  setCtrlAMMINISTRATORE( String param ) {
        this.ctrlAMMINISTRATORE.setValue( param );
    }

    public void  setCtrlAMMINISTRATORE( Object param ) {
        this.ctrlAMMINISTRATORE.setValue( param );
    }

    public void  setCtrlAMMINISTRATORE( Object param, Format ignore ) {
        this.ctrlAMMINISTRATORE.setValue( param );
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

    public void  setUrlPROGETTO( String param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param, Format ignore ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlMODULO( String param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param, Format ignore ) {
        this.urlMODULO.setValue( param );
    }

    public void  setPostMODULO( String param ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostMODULO( Object param ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostMODULO( Object param, Format ignore ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostAMMINISTRATORE( String param ) {
        this.postAMMINISTRATORE.setValue( param );
    }

    public void  setPostAMMINISTRATORE( Object param ) {
        this.postAMMINISTRATORE.setValue( param );
    }

    public void  setPostAMMINISTRATORE( Object param, Format ignore ) {
        this.postAMMINISTRATORE.setValue( param );
    }

    public AD4_MODULIRow getRow() {
        return row;
    }

    public void setRow( AD4_MODULIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @23-34EDE271
    public AD4_MODULIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @23-F5A0BC3F
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT PROG.PROGETTO PROGETTO, "
                    + "       'Modulo del Progetto '||PROG.DESCRIZIONE PROGETTO_DESC, "
                    + "       '<input class=\"AFCInputDisplay\" maxlength=\"10\" size=\"10\" value=\"'||MODU.MODULO||'\" name=\"MODULO\" readonly ></input>' MODULO, "
                    + "       MODU.MODULO MODULO_ORIG, "
                    + "       MODU.DESCRIZIONE DESCRIZIONE, "
                    + "       MODU.NOTE NOTE,  "
                    + "modu.amministratore, "
                    + "       '<img title=\"Caratteristiche\" height=\"18\" src=\"../common/images/AD4/caac.GIF\" width=\"18\" border=\"0\">'||'Caratteristiche' Caratteristiche, "
                    + "       '<img title=\"Abilitazioni\" height=\"18\" src=\"../common/images/AD4/UtenDiac.gif\" width=\"18\" border=\"0\">'||'Abilitazioni' Abilitazioni "
                    + "  FROM PROGETTI PROG, MODULI MODU "
                    + " WHERE MODU.PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   AND DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') is not null "
                    + "   AND MODU.MODULO = '{MODULO}' "
                    + "   AND PROG.PROGETTO = MODU.PROGETTO "
                    + "UNION "
                    + "SELECT PROG.PROGETTO PROGETTO, "
                    + "       'Modulo del Progetto '||PROG.DESCRIZIONE PROGETTO_DESC, "
                    + "       '<input class=\"AFCInput\" style=\"TEXT-TRANSFORM: uppercase\" maxlength=\"10\" size=\"10\" value=\"'||'{MODU_FORM}'||'\" name=\"MODULO\"></input>' MODULO, "
                    + "       '' MODULO_ORIG, "
                    + "       '{DESC_FORM}' DESCRIZIONE, "
                    + "       '{NOTE_FORM}' NOTE, "
                    + "       '{AMMI_FORM}' AMMINISTRATORE, "
                    + "       '' caratteristiche, "
                    + "       '' abilitazioni "
                    + "  FROM PROGETTI PROG "
                    + " WHERE PROG.PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   AND DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') is not null "
                    + "   AND '{MODULO}' IS NULL" );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULO", urlMODULO, null );
        if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
        command.addParameter( "MODU_FORM", postMODULO, null );
        if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
        command.addParameter( "DESC_FORM", postDESCRIZIONE, null );
        if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
        command.addParameter( "NOTE_FORM", postNOTE, null );
        if ( StringUtils.isEmpty( (String) postAMMINISTRATORE.getObjectValue() ) ) postAMMINISTRATORE.setValue( "" );
        command.addParameter( "AMMI_FORM", postAMMINISTRATORE, null );
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

//loadDataBind @23-4E146F1A
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setPROGETTO_DESC(Utils.convertToString(ds.parse(record.get("PROGETTO_DESC"), row.getPROGETTO_DESCField())));
            row.setCARATTERISTICHE(Utils.convertToString(ds.parse(record.get("CARATTERISTICHE"), row.getCARATTERISTICHEField())));
            row.setABILITAZIONI(Utils.convertToString(ds.parse(record.get("ABILITAZIONI"), row.getABILITAZIONIField())));
            row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
            row.setMODULO_ORIG(Utils.convertToString(ds.parse(record.get("MODULO_ORIG"), row.getMODULO_ORIGField())));
            row.setPROGETTO_ORIG(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTO_ORIGField())));
            row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
            row.setAMMINISTRATORE(Utils.convertToString(ds.parse(record.get("AMMINISTRATORE"), row.getAMMINISTRATOREField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
            row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
        }
//End loadDataBind

//End of load @23-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @23-755CD1BF
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{? = call AD4WEB.MODULI_PM(?,?,?,?,?,?)}" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO_ORIG.getObjectValue() ) ) postMODULO_ORIG.setValue( "" );
            command.addParameter( "P_MODULO", postMODULO_ORIG, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO_ORIG.getObjectValue() ) ) postMODULO_ORIG.setValue( "" );
            command.addParameter( "P_MODULO_OLD", postMODULO_ORIG, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPROGETTO_ORIG.getObjectValue() ) ) postPROGETTO_ORIG.setValue( "" );
            command.addParameter( "P_PROGETTO", postPROGETTO_ORIG, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
            command.addParameter( "P_DESCRIZIONE", postDESCRIZIONE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getAMMINISTRATORE()) ) row.setAMMINISTRATORE( "N" );
            command.addParameter( "P_AMMINISTRATORE", row.getAMMINISTRATOREField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @23-400C6CB5
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM AD4_MODULI");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1 = WhereParams.rawOperation( "MODULO", FieldOperation.EQUAL, urlMODULO, null, ds );
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

//deleteDataBound @23-5B959F17
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

//End of delete @23-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @23-A9D670CA
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "MODULO_ORIG".equals(name) && "post".equals(prefix) ) {
                param = postMODULO_ORIG;
            } else if ( "MODULO_ORIG".equals(name) && prefix == null ) {
                param = postMODULO_ORIG;
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
            if ( "NOTE".equals(name) && "post".equals(prefix) ) {
                param = postNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = postNOTE;
            }
            if ( "AMMINISTRATORE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlAMMINISTRATORE;
            } else if ( "AMMINISTRATORE".equals(name) && prefix == null ) {
                param = ctrlAMMINISTRATORE;
            }
            if ( "AD4PROGETTO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PROGETTO;
            } else if ( "AD4PROGETTO".equals(name) && prefix == null ) {
                param = sesAD4PROGETTO;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            if ( "MODULO".equals(name) && "post".equals(prefix) ) {
                param = postMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = postMODULO;
            }
            if ( "AMMINISTRATORE".equals(name) && "post".equals(prefix) ) {
                param = postAMMINISTRATORE;
            } else if ( "AMMINISTRATORE".equals(name) && prefix == null ) {
                param = postAMMINISTRATORE;
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

