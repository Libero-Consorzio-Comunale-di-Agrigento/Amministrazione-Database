//AD4_PARAMETRO DataSource @23-46FA00C4
package ad4web.AD4ModParametri;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_PARAMETRO DataSource

//class DataObject Header @23-F03DA4AD
public class AD4_PARAMETRODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @23-B3CF1470
    

    TextField ctrlID_RICHIESTA = new TextField(null, null);
    
    TextField ctrlPARAMETRO = new TextField(null, null);
    
    TextField ctrlVALORE = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    TextField urlID = new TextField(null, null);
    
    TextField urlIDPAR = new TextField(null, null);
    
    TextField postID_PARAMETRO = new TextField(null, null);
    

    private AD4_PARAMETRORow row = new AD4_PARAMETRORow();

//End attributes of DataObject

//properties of DataObject @23-9A46CE68

    public void  setCtrlID_RICHIESTA( String param ) {
        this.ctrlID_RICHIESTA.setValue( param );
    }

    public void  setCtrlID_RICHIESTA( Object param ) {
        this.ctrlID_RICHIESTA.setValue( param );
    }

    public void  setCtrlID_RICHIESTA( Object param, Format ignore ) {
        this.ctrlID_RICHIESTA.setValue( param );
    }

    public void  setCtrlPARAMETRO( String param ) {
        this.ctrlPARAMETRO.setValue( param );
    }

    public void  setCtrlPARAMETRO( Object param ) {
        this.ctrlPARAMETRO.setValue( param );
    }

    public void  setCtrlPARAMETRO( Object param, Format ignore ) {
        this.ctrlPARAMETRO.setValue( param );
    }

    public void  setCtrlVALORE( String param ) {
        this.ctrlVALORE.setValue( param );
    }

    public void  setCtrlVALORE( Object param ) {
        this.ctrlVALORE.setValue( param );
    }

    public void  setCtrlVALORE( Object param, Format ignore ) {
        this.ctrlVALORE.setValue( param );
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

    public void  setUrlID( String param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param, Format ignore ) {
        this.urlID.setValue( param );
    }

    public void  setUrlIDPAR( String param ) {
        this.urlIDPAR.setValue( param );
    }

    public void  setUrlIDPAR( Object param ) {
        this.urlIDPAR.setValue( param );
    }

    public void  setUrlIDPAR( Object param, Format ignore ) {
        this.urlIDPAR.setValue( param );
    }

    public void  setPostID_PARAMETRO( String param ) {
        this.postID_PARAMETRO.setValue( param );
    }

    public void  setPostID_PARAMETRO( Object param ) {
        this.postID_PARAMETRO.setValue( param );
    }

    public void  setPostID_PARAMETRO( Object param, Format ignore ) {
        this.postID_PARAMETRO.setValue( param );
    }

    public AD4_PARAMETRORow getRow() {
        return row;
    }

    public void setRow( AD4_PARAMETRORow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @23-D1D7CDAB
    public AD4_PARAMETRODataObject(Page page) {
        super(page);
    }
//End constructor

//load @23-352361ED
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '<input class=\"AFCInputDisplay\" maxlength=\"60\" size=\"20\" value=\"'||PARAMETRO||'\" name=\"PARAMETRO\" readonly ></input>' PARAMETRO, "
                    + "       VALORE, "
                    + "       to_char(id_richiesta) id_richiesta, "
                    + "       to_char(id_parametro) id_parametro "
                    + "  FROM PARAMETRI_RICHIESTA "
                    + " WHERE id_parametro = '{IDPAR}' "
                    + "UNION "
                    + "SELECT '<input class=\"AFCInput\" style=\"TEXT-TRANSFORM: uppercase\" maxlength=\"60\" size=\"20\" value=\"\" name=\"PARAMETRO\"></input>' PARAMETRO,  "
                    + " "
                    + "       '' VALORE, "
                    + "       '{IDRIC}' ID_RICHIESTA, "
                    + "       '' ID_PARAMETRO  "
                    + "  FROM DUAL "
                    + " WHERE '{IDPAR}' is null" );
        if ( StringUtils.isEmpty( (String) urlID.getObjectValue() ) ) urlID.setValue( "" );
        command.addParameter( "IDRIC", urlID, null );
        if ( StringUtils.isEmpty( (String) urlIDPAR.getObjectValue() ) ) urlIDPAR.setValue( "" );
        command.addParameter( "IDPAR", urlIDPAR, null );
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

//loadDataBind @23-03ED6267
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setPARAMETRO(Utils.convertToString(ds.parse(record.get("PARAMETRO"), row.getPARAMETROField())));
            row.setID_PARAMETRO(Utils.convertToString(ds.parse(record.get("ID_PARAMETRO"), row.getID_PARAMETROField())));
            row.setID_RICHIESTA(Utils.convertToString(ds.parse(record.get("ID_RICHIESTA"), row.getID_RICHIESTAField())));
            row.setVALORE(Utils.convertToString(ds.parse(record.get("VALORE"), row.getVALOREField())));
        }
//End loadDataBind

//End of load @23-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @23-3F55634B
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ call AMV_UTENTE.SET_PARAMETRO_RICHIESTA ( ?, ?, ?, ? ) }" );
            if ( StringUtils.isEmpty( (String) row.getID_RICHIESTA()) ) row.setID_RICHIESTA( "" );
            command.addParameter( "P_ID_RICHIESTA", row.getID_RICHIESTAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPARAMETRO()) ) row.setPARAMETRO( "" );
            command.addParameter( "P_NOME", row.getPARAMETROField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getVALORE()) ) row.setVALORE( "" );
            command.addParameter( "P_VALORE", row.getVALOREField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "P_UTENTE", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @23-66D3A001
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM AD4_PARAMETRI_RICHIESTA");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1 = WhereParams.rawOperation( "ID_PARAMETRO", FieldOperation.EQUAL, postID_PARAMETRO, null, ds );
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

//getParameterByName @23-EAA3D396
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_RICHIESTA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlID_RICHIESTA;
            } else if ( "ID_RICHIESTA".equals(name) && prefix == null ) {
                param = ctrlID_RICHIESTA;
            }
            if ( "PARAMETRO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPARAMETRO;
            } else if ( "PARAMETRO".equals(name) && prefix == null ) {
                param = ctrlPARAMETRO;
            }
            if ( "VALORE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlVALORE;
            } else if ( "VALORE".equals(name) && prefix == null ) {
                param = ctrlVALORE;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "ID".equals(name) && "url".equals(prefix) ) {
                param = urlID;
            } else if ( "ID".equals(name) && prefix == null ) {
                param = urlID;
            }
            if ( "IDPAR".equals(name) && "url".equals(prefix) ) {
                param = urlIDPAR;
            } else if ( "IDPAR".equals(name) && prefix == null ) {
                param = urlIDPAR;
            }
            if ( "ID_PARAMETRO".equals(name) && "post".equals(prefix) ) {
                param = postID_PARAMETRO;
            } else if ( "ID_PARAMETRO".equals(name) && prefix == null ) {
                param = postID_PARAMETRO;
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

