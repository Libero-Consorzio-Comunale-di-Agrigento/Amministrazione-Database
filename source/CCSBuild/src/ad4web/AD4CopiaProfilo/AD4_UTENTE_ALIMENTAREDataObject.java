//AD4_UTENTE_ALIMENTARE DataSource @6-AF19862B
package ad4web.AD4CopiaProfilo;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_UTENTE_ALIMENTARE DataSource

//class DataObject Header @6-9ED27A11
public class AD4_UTENTE_ALIMENTAREDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-8C9335F9
    

    TextField urlUTENTE_ALIMENTARE = new TextField(null, null);
    
    TextField urlUTENTE_ORIGINE = new TextField(null, null);
    
    TextField ctrlCOPIA_GRUPPI = new TextField(null, null);
    
    TextField ctrlCOPIA_DIAC = new TextField(null, null);
    
    TextField ctrlCOPIA_CAAC = new TextField(null, null);
    
    TextField ses = new TextField(null, null);
    

    private AD4_UTENTE_ALIMENTARERow row = new AD4_UTENTE_ALIMENTARERow();

//End attributes of DataObject

//properties of DataObject @6-BC918D38

    public void  setUrlUTENTE_ALIMENTARE( String param ) {
        this.urlUTENTE_ALIMENTARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE( Object param ) {
        this.urlUTENTE_ALIMENTARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE( Object param, Format ignore ) {
        this.urlUTENTE_ALIMENTARE.setValue( param );
    }

    public void  setUrlUTENTE_ORIGINE( String param ) {
        this.urlUTENTE_ORIGINE.setValue( param );
    }

    public void  setUrlUTENTE_ORIGINE( Object param ) {
        this.urlUTENTE_ORIGINE.setValue( param );
    }

    public void  setUrlUTENTE_ORIGINE( Object param, Format ignore ) {
        this.urlUTENTE_ORIGINE.setValue( param );
    }

    public void  setCtrlCOPIA_GRUPPI( String param ) {
        this.ctrlCOPIA_GRUPPI.setValue( param );
    }

    public void  setCtrlCOPIA_GRUPPI( Object param ) {
        this.ctrlCOPIA_GRUPPI.setValue( param );
    }

    public void  setCtrlCOPIA_GRUPPI( Object param, Format ignore ) {
        this.ctrlCOPIA_GRUPPI.setValue( param );
    }

    public void  setCtrlCOPIA_DIAC( String param ) {
        this.ctrlCOPIA_DIAC.setValue( param );
    }

    public void  setCtrlCOPIA_DIAC( Object param ) {
        this.ctrlCOPIA_DIAC.setValue( param );
    }

    public void  setCtrlCOPIA_DIAC( Object param, Format ignore ) {
        this.ctrlCOPIA_DIAC.setValue( param );
    }

    public void  setCtrlCOPIA_CAAC( String param ) {
        this.ctrlCOPIA_CAAC.setValue( param );
    }

    public void  setCtrlCOPIA_CAAC( Object param ) {
        this.ctrlCOPIA_CAAC.setValue( param );
    }

    public void  setCtrlCOPIA_CAAC( Object param, Format ignore ) {
        this.ctrlCOPIA_CAAC.setValue( param );
    }

    public void  setSes( String param ) {
        this.ses.setValue( param );
    }

    public void  setSes( Object param ) {
        this.ses.setValue( param );
    }

    public void  setSes( Object param, Format ignore ) {
        this.ses.setValue( param );
    }

    public AD4_UTENTE_ALIMENTARERow getRow() {
        return row;
    }

    public void setRow( AD4_UTENTE_ALIMENTARERow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-99ACA3E1
    public AD4_UTENTE_ALIMENTAREDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-97C53D64
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT uten_alimentare.utente utente_alimentare,  "
                    + " "
                    + "       uten_alimentare.NOMINATIVO nominativo_alimentare,  "
                    + "     "
                    + "       AD4WEB.GET_DATI_UTENTE(uten_alimentare.UTENTE) DATI_UTENTE_alimentare, "
                    + "       AD4WEB.GET_GRUPPI_DIAC_UTENTE(uten_alimentare.UTENTE) GRUPPI_DIAC_alimentare, "
                    + "       AD4WEB.GET_DIAC_UTENTE(uten_alimentare.UTENTE) DIAC_alimentare, "
                    + "       AD4WEB.GET_CAAC_UTENTE(uten_alimentare.UTENTE) CAAC_alimentare, "
                    + "       'NO' grp_si_no, "
                    + "       'NO' diac_si_no,        "
                    + "       'NO' caac_si_no, "
                    + "       uten_origine.utente utente_origine, "
                    + "       uten_origine.NOMINATIVO nominativo_origine,      "
                    + "       AD4WEB.GET_DATI_UTENTE(uten_origine.UTENTE) DATI_UTENTE_origine, "
                    + "       AD4WEB.GET_GRUPPI_DIAC_UTENTE(uten_origine.UTENTE) GRUPPI_DIAC_origine, "
                    + "       AD4WEB.GET_DIAC_UTENTE(uten_origine.UTENTE) DIAC_origine, "
                    + "       AD4WEB.GET_CAAC_UTENTE(uten_origine.UTENTE) CAAC_origine "
                    + "  FROM UTENTI uten_alimentare, "
                    + "       UTENTI uten_origine "
                    + " WHERE uten_alimentare.utente = '{s_UTENTE_ALIMENTARE}' "
                    + "  and  uten_origine.utente = '{s_UTENTE_ORIGINE}' "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlUTENTE_ALIMENTARE.getObjectValue() ) ) urlUTENTE_ALIMENTARE.setValue( "" );
        command.addParameter( "s_UTENTE_ALIMENTARE", urlUTENTE_ALIMENTARE, null );
        if ( StringUtils.isEmpty( (String) urlUTENTE_ORIGINE.getObjectValue() ) ) urlUTENTE_ORIGINE.setValue( "" );
        command.addParameter( "s_UTENTE_ORIGINE", urlUTENTE_ORIGINE, null );
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

//loadDataBind @6-10E288E2
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setUTENTE_ALIMENTARE(Utils.convertToString(ds.parse(record.get("UTENTE_ALIMENTARE"), row.getUTENTE_ALIMENTAREField())));
            row.setUTENTE_ORIGINE(Utils.convertToString(ds.parse(record.get("UTENTE_ORIGINE"), row.getUTENTE_ORIGINEField())));
            row.setNOMINATIVO_ALIMENTARE(Utils.convertToString(ds.parse(record.get("NOMINATIVO_ALIMENTARE"), row.getNOMINATIVO_ALIMENTAREField())));
            row.setNOMINATIVO_ORIGINE(Utils.convertToString(ds.parse(record.get("NOMINATIVO_ORIGINE"), row.getNOMINATIVO_ORIGINEField())));
            row.setDATI_UTENTE_ALIMENTARE(Utils.convertToString(ds.parse(record.get("DATI_UTENTE_ALIMENTARE"), row.getDATI_UTENTE_ALIMENTAREField())));
            row.setDATI_UTENTE_ORIGINE(Utils.convertToString(ds.parse(record.get("DATI_UTENTE_ORIGINE"), row.getDATI_UTENTE_ORIGINEField())));
            row.setGRUPPI_DIAC_ALIMENTARE(Utils.convertToString(ds.parse(record.get("GRUPPI_DIAC_ALIMENTARE"), row.getGRUPPI_DIAC_ALIMENTAREField())));
            row.setCOPIA_GRUPPI(Utils.convertToString(ds.parse(record.get("GRP_SI_NO"), row.getCOPIA_GRUPPIField())));
            row.setGRUPPI_DIAC_ORIGINE(Utils.convertToString(ds.parse(record.get("GRUPPI_DIAC_ORIGINE"), row.getGRUPPI_DIAC_ORIGINEField())));
            row.setDIAC_ALIMENTARE(Utils.convertToString(ds.parse(record.get("DIAC_ALIMENTARE"), row.getDIAC_ALIMENTAREField())));
            row.setCOPIA_DIAC(Utils.convertToString(ds.parse(record.get("DIAC_SI_NO"), row.getCOPIA_DIACField())));
            row.setDIAC_ORIGINE(Utils.convertToString(ds.parse(record.get("DIAC_ORIGINE"), row.getDIAC_ORIGINEField())));
            row.setCAAC_ALIMENTARE(Utils.convertToString(ds.parse(record.get("CAAC_ALIMENTARE"), row.getCAAC_ALIMENTAREField())));
            row.setCOPIA_CAAC(Utils.convertToString(ds.parse(record.get("CAAC_SI_NO"), row.getCOPIA_CAACField())));
            row.setCAAC_ORIGINE(Utils.convertToString(ds.parse(record.get("CAAC_ORIGINE"), row.getCAAC_ORIGINEField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @6-C0A1C2A4
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call UTENTE.COPIA_PROFILO(?,?,?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) urlUTENTE_ALIMENTARE.getObjectValue() ) ) urlUTENTE_ALIMENTARE.setValue( "" );
            command.addParameter( "P_UTENTE_ALIMENTARE", urlUTENTE_ALIMENTARE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlUTENTE_ORIGINE.getObjectValue() ) ) urlUTENTE_ORIGINE.setValue( "" );
            command.addParameter( "P_UTENTE_ORIGINE", urlUTENTE_ORIGINE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOPIA_GRUPPI()) ) row.setCOPIA_GRUPPI( "" );
            command.addParameter( "P_COPIARE_UTGR", row.getCOPIA_GRUPPIField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOPIA_DIAC()) ) row.setCOPIA_DIAC( "" );
            command.addParameter( "P_COPIARE_DIAC", row.getCOPIA_DIACField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOPIA_CAAC()) ) row.setCOPIA_CAAC( "" );
            command.addParameter( "P_COPIARE_CAAC", row.getCOPIA_CAACField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) ses.getObjectValue() ) ) ses.setValue( "" );
            command.addParameter( "P_UTENTE_AGG", ses, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @6-530D895F
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "UTENTE_ALIMENTARE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE_ALIMENTARE;
            } else if ( "UTENTE_ALIMENTARE".equals(name) && prefix == null ) {
                param = urlUTENTE_ALIMENTARE;
            }
            if ( "UTENTE_ORIGINE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE_ORIGINE;
            } else if ( "UTENTE_ORIGINE".equals(name) && prefix == null ) {
                param = urlUTENTE_ORIGINE;
            }
            if ( "COPIA_GRUPPI".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOPIA_GRUPPI;
            } else if ( "COPIA_GRUPPI".equals(name) && prefix == null ) {
                param = ctrlCOPIA_GRUPPI;
            }
            if ( "COPIA_DIAC".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOPIA_DIAC;
            } else if ( "COPIA_DIAC".equals(name) && prefix == null ) {
                param = ctrlCOPIA_DIAC;
            }
            if ( "COPIA_CAAC".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOPIA_CAAC;
            } else if ( "COPIA_CAAC".equals(name) && prefix == null ) {
                param = ctrlCOPIA_CAAC;
            }
            if ( "".equals(name) && "ses".equals(prefix) ) {
                param = ses;
            } else if ( "".equals(name) && prefix == null ) {
                param = ses;
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

