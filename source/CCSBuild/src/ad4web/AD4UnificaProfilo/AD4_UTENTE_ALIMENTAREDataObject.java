//AD4_UTENTE_ALIMENTARE DataSource @6-5CDE7478
package ad4web.AD4UnificaProfilo;

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

//attributes of DataObject @6-59431CCB
    

    TextField urlUTENTE_ALIMENTARE_UNIFICARE = new TextField(null, null);
    
    TextField urlUTENTE_UNIFICARE = new TextField(null, null);
    
    LongField ctrlSOGGETTO_ASSEGNARE = new LongField(null, null);
    
    TextField ctrlMANTIENI_GRUPPI = new TextField(null, null);
    
    TextField ctrlCOPIA_GRUPPI = new TextField(null, null);
    
    TextField ctrlMANTIENI_DIAC = new TextField(null, null);
    
    TextField ctrlCOPIA_DIAC = new TextField(null, null);
    
    TextField ctrlMANTIENI_CAAC = new TextField(null, null);
    
    TextField ctrlCOPIA_CAAC = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    

    private AD4_UTENTE_ALIMENTARERow row = new AD4_UTENTE_ALIMENTARERow();

//End attributes of DataObject

//properties of DataObject @6-C4B88326

    public void  setUrlUTENTE_ALIMENTARE_UNIFICARE( String param ) {
        this.urlUTENTE_ALIMENTARE_UNIFICARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE_UNIFICARE( Object param ) {
        this.urlUTENTE_ALIMENTARE_UNIFICARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE_UNIFICARE( Object param, Format ignore ) {
        this.urlUTENTE_ALIMENTARE_UNIFICARE.setValue( param );
    }

    public void  setUrlUTENTE_UNIFICARE( String param ) {
        this.urlUTENTE_UNIFICARE.setValue( param );
    }

    public void  setUrlUTENTE_UNIFICARE( Object param ) {
        this.urlUTENTE_UNIFICARE.setValue( param );
    }

    public void  setUrlUTENTE_UNIFICARE( Object param, Format ignore ) {
        this.urlUTENTE_UNIFICARE.setValue( param );
    }

    public void  setCtrlSOGGETTO_ASSEGNARE( long param ) {
        this.ctrlSOGGETTO_ASSEGNARE.setValue( param );
    }

    public void  setCtrlSOGGETTO_ASSEGNARE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlSOGGETTO_ASSEGNARE.setValue( param );
    }

    public void  setCtrlSOGGETTO_ASSEGNARE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlSOGGETTO_ASSEGNARE.setValue( param, format );
    }

    public void  setCtrlSOGGETTO_ASSEGNARE( Long param ) {
        this.ctrlSOGGETTO_ASSEGNARE.setValue( param );
    }

    public void  setCtrlMANTIENI_GRUPPI( String param ) {
        this.ctrlMANTIENI_GRUPPI.setValue( param );
    }

    public void  setCtrlMANTIENI_GRUPPI( Object param ) {
        this.ctrlMANTIENI_GRUPPI.setValue( param );
    }

    public void  setCtrlMANTIENI_GRUPPI( Object param, Format ignore ) {
        this.ctrlMANTIENI_GRUPPI.setValue( param );
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

    public void  setCtrlMANTIENI_DIAC( String param ) {
        this.ctrlMANTIENI_DIAC.setValue( param );
    }

    public void  setCtrlMANTIENI_DIAC( Object param ) {
        this.ctrlMANTIENI_DIAC.setValue( param );
    }

    public void  setCtrlMANTIENI_DIAC( Object param, Format ignore ) {
        this.ctrlMANTIENI_DIAC.setValue( param );
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

    public void  setCtrlMANTIENI_CAAC( String param ) {
        this.ctrlMANTIENI_CAAC.setValue( param );
    }

    public void  setCtrlMANTIENI_CAAC( Object param ) {
        this.ctrlMANTIENI_CAAC.setValue( param );
    }

    public void  setCtrlMANTIENI_CAAC( Object param, Format ignore ) {
        this.ctrlMANTIENI_CAAC.setValue( param );
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

    public void  setSesUtente( String param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param, Format ignore ) {
        this.sesUtente.setValue( param );
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

//load @6-4687BDDD
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
                    + "       utente.GET_SOGGETTO(uten_alimentare.Utente)SOGGETTO_ALIMENTARE, "
                    + "       Ad4web.GET_DATI_SOGGETTO(UTENTE.GET_SOGGETTO(uten_alimentare.Utente)) DATI_SOGGETTO_alimentare, "
                    + "       AD4WEB.GET_GRUPPI_DIAC_UTENTE(uten_alimentare.UTENTE) GRUPPI_DIAC_alimentare, "
                    + "       AD4WEB.GET_DIAC_UTENTE(uten_alimentare.UTENTE) DIAC_alimentare, "
                    + "       AD4WEB.GET_CAAC_UTENTE(uten_alimentare.UTENTE) CAAC_alimentare, "
                    + "       'NO' grp_si_no, "
                    + "       'NO' diac_si_no,        "
                    + "       'NO' caac_si_no, "
                    + "       'NO' mantieni_grp_si_no, "
                    + "       'NO' mantieni_diac_si_no,        "
                    + "       'NO' mantieni_caac_si_no, "
                    + "       uten_origine.utente utente_origine, "
                    + "       uten_origine.NOMINATIVO nominativo_origine,      "
                    + "       AD4WEB.GET_DATI_UTENTE(uten_origine.UTENTE) DATI_UTENTE_origine, "
                    + "       utente.GET_SOGGETTO(uten_origine.Utente)SOGGETTO_ORIGINE, "
                    + "       Ad4web.GET_DATI_SOGGETTO(utente.GET_SOGGETTO(uten_origine.Utente)) DATI_SOGGETTO_origine, "
                    + "       AD4WEB.GET_GRUPPI_DIAC_UTENTE(uten_origine.UTENTE) GRUPPI_DIAC_origine, "
                    + "       AD4WEB.GET_DIAC_UTENTE(uten_origine.UTENTE) DIAC_origine, "
                    + "       AD4WEB.GET_CAAC_UTENTE(uten_origine.UTENTE) CAAC_origine "
                    + "  FROM UTENTI uten_alimentare, "
                    + "       UTENTI uten_origine "
                    + " WHERE uten_alimentare.utente = '{UTENTE_ALIMENTARE_UNIFICARE}' "
                    + "  and  uten_origine.utente = '{UTENTE_UNIFICARE}' "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlUTENTE_UNIFICARE.getObjectValue() ) ) urlUTENTE_UNIFICARE.setValue( "" );
        command.addParameter( "UTENTE_UNIFICARE", urlUTENTE_UNIFICARE, null );
        if ( StringUtils.isEmpty( (String) urlUTENTE_ALIMENTARE_UNIFICARE.getObjectValue() ) ) urlUTENTE_ALIMENTARE_UNIFICARE.setValue( "" );
        command.addParameter( "UTENTE_ALIMENTARE_UNIFICARE", urlUTENTE_ALIMENTARE_UNIFICARE, null );
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

//loadDataBind @6-5B3D87FB
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setUTENTE_ALIMENTARE(Utils.convertToString(ds.parse(record.get("UTENTE_ALIMENTARE"), row.getUTENTE_ALIMENTAREField())));
            row.setUTENTE_ORIGINE(Utils.convertToString(ds.parse(record.get("UTENTE_ORIGINE"), row.getUTENTE_ORIGINEField())));
            row.setNOMINATIVO_ALIMENTARE(Utils.convertToString(ds.parse(record.get("NOMINATIVO_ALIMENTARE"), row.getNOMINATIVO_ALIMENTAREField())));
            row.setNOMINATIVO_ORIGINE(Utils.convertToString(ds.parse(record.get("NOMINATIVO_ORIGINE"), row.getNOMINATIVO_ORIGINEField())));
            row.setDATI_UTENTE_ALIMENTARE(Utils.convertToString(ds.parse(record.get("DATI_UTENTE_ALIMENTARE"), row.getDATI_UTENTE_ALIMENTAREField())));
            row.setDATI_UTENTE_ORIGINE(Utils.convertToString(ds.parse(record.get("DATI_UTENTE_ORIGINE"), row.getDATI_UTENTE_ORIGINEField())));
            row.setDATI_SOGGETTO_ALIMENTARE(Utils.convertToString(ds.parse(record.get("DATI_SOGGETTO_ALIMENTARE"), row.getDATI_SOGGETTO_ALIMENTAREField())));
            row.setSOGGETTO_ASSEGNARE(Utils.convertToLong(ds.parse(record.get("SOGGETTO_ALIMENTARE"), row.getSOGGETTO_ASSEGNAREField())));
            row.setSOGGETTO_ALIMENTARE(Utils.convertToLong(ds.parse(record.get("SOGGETTO_ALIMENTARE"), row.getSOGGETTO_ALIMENTAREField())));
            row.setDATI_SOGGETTO_ORIGINE(Utils.convertToString(ds.parse(record.get("DATI_SOGGETTO_ORIGINE"), row.getDATI_SOGGETTO_ORIGINEField())));
            row.setSOGGETTO_ORIGINE(Utils.convertToLong(ds.parse(record.get("SOGGETTO_ORIGINE"), row.getSOGGETTO_ORIGINEField())));
            row.setMANTIENI_GRUPPI(Utils.convertToString(ds.parse(record.get("MANTIENI_GRP_SI_NO"), row.getMANTIENI_GRUPPIField())));
            row.setGRUPPI_DIAC_ALIMENTARE(Utils.convertToString(ds.parse(record.get("GRUPPI_DIAC_ALIMENTARE"), row.getGRUPPI_DIAC_ALIMENTAREField())));
            row.setCOPIA_GRUPPI(Utils.convertToString(ds.parse(record.get("GRP_SI_NO"), row.getCOPIA_GRUPPIField())));
            row.setGRUPPI_DIAC_ORIGINE(Utils.convertToString(ds.parse(record.get("GRUPPI_DIAC_ORIGINE"), row.getGRUPPI_DIAC_ORIGINEField())));
            row.setMANTIENI_DIAC(Utils.convertToString(ds.parse(record.get("MANTIENI_DIAC_SI_NO"), row.getMANTIENI_DIACField())));
            row.setDIAC_ALIMENTARE(Utils.convertToString(ds.parse(record.get("DIAC_ALIMENTARE"), row.getDIAC_ALIMENTAREField())));
            row.setCOPIA_DIAC(Utils.convertToString(ds.parse(record.get("DIAC_SI_NO"), row.getCOPIA_DIACField())));
            row.setDIAC_ORIGINE(Utils.convertToString(ds.parse(record.get("DIAC_ORIGINE"), row.getDIAC_ORIGINEField())));
            row.setMANTIENI_CAAC(Utils.convertToString(ds.parse(record.get("MANTIENI_CAAC_SI_NO"), row.getMANTIENI_CAACField())));
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

//update @6-7ABE8756
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call UTENTE.UNIFICA_PROFILO(?,?,?,?,?,?,?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) urlUTENTE_ALIMENTARE_UNIFICARE.getObjectValue() ) ) urlUTENTE_ALIMENTARE_UNIFICARE.setValue( "" );
            command.addParameter( "P_UTENTE_ALIMENTARE", urlUTENTE_ALIMENTARE_UNIFICARE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlUTENTE_UNIFICARE.getObjectValue() ) ) urlUTENTE_UNIFICARE.setValue( "" );
            command.addParameter( "P_UTENTE_REVOCARE", urlUTENTE_UNIFICARE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_SOGGETTO_ASSEGNARE", row.getSOGGETTO_ASSEGNAREField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getMANTIENI_GRUPPI()) ) row.setMANTIENI_GRUPPI( "" );
            command.addParameter( "P_TENERE_UTGR_ALIMENTARE", row.getMANTIENI_GRUPPIField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOPIA_GRUPPI()) ) row.setCOPIA_GRUPPI( "" );
            command.addParameter( "P_TENERE_UTGR_REVOCARE", row.getCOPIA_GRUPPIField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getMANTIENI_DIAC()) ) row.setMANTIENI_DIAC( "" );
            command.addParameter( "P_TENERE_DIAC_ALIMENTARE", row.getMANTIENI_DIACField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOPIA_DIAC()) ) row.setCOPIA_DIAC( "" );
            command.addParameter( "P_TENERE_DIAC_REVOCARE", row.getCOPIA_DIACField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getMANTIENI_CAAC()) ) row.setMANTIENI_CAAC( "" );
            command.addParameter( "P_TENERE_CAAC_ALIMENTARE", row.getMANTIENI_CAACField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOPIA_CAAC()) ) row.setCOPIA_CAAC( "" );
            command.addParameter( "P_TENERE_CAAC_REVOCARE", row.getCOPIA_CAACField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "P_UTENTE_AGG", sesUtente, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @6-276F90E6
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "UTENTE_ALIMENTARE_UNIFICARE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE_ALIMENTARE_UNIFICARE;
            } else if ( "UTENTE_ALIMENTARE_UNIFICARE".equals(name) && prefix == null ) {
                param = urlUTENTE_ALIMENTARE_UNIFICARE;
            }
            if ( "UTENTE_UNIFICARE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE_UNIFICARE;
            } else if ( "UTENTE_UNIFICARE".equals(name) && prefix == null ) {
                param = urlUTENTE_UNIFICARE;
            }
            if ( "SOGGETTO_ASSEGNARE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSOGGETTO_ASSEGNARE;
            } else if ( "SOGGETTO_ASSEGNARE".equals(name) && prefix == null ) {
                param = ctrlSOGGETTO_ASSEGNARE;
            }
            if ( "MANTIENI_GRUPPI".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlMANTIENI_GRUPPI;
            } else if ( "MANTIENI_GRUPPI".equals(name) && prefix == null ) {
                param = ctrlMANTIENI_GRUPPI;
            }
            if ( "COPIA_GRUPPI".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOPIA_GRUPPI;
            } else if ( "COPIA_GRUPPI".equals(name) && prefix == null ) {
                param = ctrlCOPIA_GRUPPI;
            }
            if ( "MANTIENI_DIAC".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlMANTIENI_DIAC;
            } else if ( "MANTIENI_DIAC".equals(name) && prefix == null ) {
                param = ctrlMANTIENI_DIAC;
            }
            if ( "COPIA_DIAC".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOPIA_DIAC;
            } else if ( "COPIA_DIAC".equals(name) && prefix == null ) {
                param = ctrlCOPIA_DIAC;
            }
            if ( "MANTIENI_CAAC".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlMANTIENI_CAAC;
            } else if ( "MANTIENI_CAAC".equals(name) && prefix == null ) {
                param = ctrlMANTIENI_CAAC;
            }
            if ( "COPIA_CAAC".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOPIA_CAAC;
            } else if ( "COPIA_CAAC".equals(name) && prefix == null ) {
                param = ctrlCOPIA_CAAC;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
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

