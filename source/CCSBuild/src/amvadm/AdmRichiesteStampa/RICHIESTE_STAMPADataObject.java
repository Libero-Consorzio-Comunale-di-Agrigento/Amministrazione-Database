//RICHIESTE_STAMPA DataSource @2-00293CA0
package amvadm.AdmRichiesteStampa;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End RICHIESTE_STAMPA DataSource

//class DataObject Header @2-5B8DE635
public class RICHIESTE_STAMPADataObject extends DS {
//End class DataObject Header

//attributes of DataObject @2-3D80CCC7
    

    DoubleField ctrlID_RICHIESTA = new DoubleField(null, null);
    
    LongField ctrlNOTIFICATO = new LongField(null, null);
    
    LongField ctrlSTAMPA = new LongField(null, null);
    
    TextField sesModulo = new TextField(null, null);
    
    TextField sesIstanza = new TextField(null, null);
    
    TextField urlSTATO = new TextField(null, null);
    
    TextField urlMOD = new TextField(null, null);
    
    TextField urlIST = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    LongField urlMVAV = new LongField(null, null);
    
    TextField sesMVCONTEXT = new TextField(null, null);
    
    TextField sesAD4UTENTE = new TextField(null, null);
    
    LongField sesGroupID = new LongField(null, null);
    

    private RICHIESTE_STAMPARow row = new RICHIESTE_STAMPARow();
    private boolean[] rowResults = null;
    private ArrayList[] rowErrors = null;
    private ArrayList rowError = null;
    private RICHIESTE_STAMPARow[] rows = new RICHIESTE_STAMPARow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @2-3FEB636E

    public void  setCtrlID_RICHIESTA( double param ) {
        this.ctrlID_RICHIESTA.setValue( param );
    }

    public void  setCtrlID_RICHIESTA( double param, Format ignore ) throws java.text.ParseException {
        this.ctrlID_RICHIESTA.setValue( param );
    }

    public void  setCtrlID_RICHIESTA( Object param, Format format ) throws java.text.ParseException {
        this.ctrlID_RICHIESTA.setValue( param, format );
    }

    public void  setCtrlID_RICHIESTA( Double param ) {
        this.ctrlID_RICHIESTA.setValue( param );
    }

    public void  setCtrlNOTIFICATO( long param ) {
        this.ctrlNOTIFICATO.setValue( param );
    }

    public void  setCtrlNOTIFICATO( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlNOTIFICATO.setValue( param );
    }

    public void  setCtrlNOTIFICATO( Object param, Format format ) throws java.text.ParseException {
        this.ctrlNOTIFICATO.setValue( param, format );
    }

    public void  setCtrlNOTIFICATO( Long param ) {
        this.ctrlNOTIFICATO.setValue( param );
    }

    public void  setCtrlSTAMPA( long param ) {
        this.ctrlSTAMPA.setValue( param );
    }

    public void  setCtrlSTAMPA( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlSTAMPA.setValue( param );
    }

    public void  setCtrlSTAMPA( Object param, Format format ) throws java.text.ParseException {
        this.ctrlSTAMPA.setValue( param, format );
    }

    public void  setCtrlSTAMPA( Long param ) {
        this.ctrlSTAMPA.setValue( param );
    }

    public void  setSesModulo( String param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param, Format ignore ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesIstanza( String param ) {
        this.sesIstanza.setValue( param );
    }

    public void  setSesIstanza( Object param ) {
        this.sesIstanza.setValue( param );
    }

    public void  setSesIstanza( Object param, Format ignore ) {
        this.sesIstanza.setValue( param );
    }

    public void  setUrlSTATO( String param ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlSTATO( Object param ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlSTATO( Object param, Format ignore ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlMOD( String param ) {
        this.urlMOD.setValue( param );
    }

    public void  setUrlMOD( Object param ) {
        this.urlMOD.setValue( param );
    }

    public void  setUrlMOD( Object param, Format ignore ) {
        this.urlMOD.setValue( param );
    }

    public void  setUrlIST( String param ) {
        this.urlIST.setValue( param );
    }

    public void  setUrlIST( Object param ) {
        this.urlIST.setValue( param );
    }

    public void  setUrlIST( Object param, Format ignore ) {
        this.urlIST.setValue( param );
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

    public void  setUrlMVAV( long param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( long param, Format ignore ) throws java.text.ParseException {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( Object param, Format format ) throws java.text.ParseException {
        this.urlMVAV.setValue( param, format );
    }

    public void  setUrlMVAV( Long param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setSesMVCONTEXT( String param ) {
        this.sesMVCONTEXT.setValue( param );
    }

    public void  setSesMVCONTEXT( Object param ) {
        this.sesMVCONTEXT.setValue( param );
    }

    public void  setSesMVCONTEXT( Object param, Format ignore ) {
        this.sesMVCONTEXT.setValue( param );
    }

    public void  setSesAD4UTENTE( String param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param, Format ignore ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesGroupID( long param ) {
        this.sesGroupID.setValue( param );
    }

    public void  setSesGroupID( long param, Format ignore ) throws java.text.ParseException {
        this.sesGroupID.setValue( param );
    }

    public void  setSesGroupID( Object param, Format format ) throws java.text.ParseException {
        this.sesGroupID.setValue( param, format );
    }

    public void  setSesGroupID( Long param ) {
        this.sesGroupID.setValue( param );
    }

    public RICHIESTE_STAMPARow getRow() {
        return row;
    }

    public void setRow( RICHIESTE_STAMPARow row ) {
        this.row = row;
    }

    public boolean[] getRowResults() {
        return rowResults;
    }

    public Collection[] getRowErrors() {
        return rowErrors;
    }

    public RICHIESTE_STAMPARow[] getRows() {
        return rows;
    }

    public void setRows(RICHIESTE_STAMPARow[] rows) {
        this.rows = rows;
    }

    public void setPageNum( int pageNum ) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

//End properties of DataObject

//constructor @2-46B6D0C8
    public RICHIESTE_STAMPADataObject(Page page) {
        super(page);
        addEditableGridDataObjectListener( new RICHIESTE_STAMPADataObjectHandler() );
    }
//End constructor

//load @2-6197F63A
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT r.id_richiesta id_richiesta "
                    + ",  "
                    + "'n.'||r.id_richiesta||' del '||to_char(r.data,'dd/mm/yyyy') DATA "
                    + ", u.nominativo autore "
                    + ",  "
                    + "m.descrizione||'-'||r.istanza servizio "
                    + ",  "
                    + "decode(ad4_soggetto.get_indirizzo_web(s.SOGGETTO,'Y'),null,'','&nbsp;('||ad4_soggetto.get_indirizzo_web(s.SOGGETTO)||')') indirizzo_web "
                    + ",  "
                    + "decode(r.notificata,'S','(Notifica avvenuta)','F','(Notifica fallita)','N','(In attesa di notifica)','') notificata "
                    + ", decode(r.notificata,'S',1,0) notificato "
                    + ", decode(r.note_notifica,'STAMPA',1,0) stampa "
                    + ", decode(r.notificata,'S','<!--','') BEGIN_HIDE "
                    + ", decode(r.notificata,'S','-->','') END_HIDE "
                    + ", r.tipo_notifica||' '||r.indirizzo_notifica indirizzo_notifica "
                    + ", decode('{STATO}','C','Approva','F','Approva','') APPROVA "
                    + ", decode('{STATO}','C','Respingi','F','Respingi','') RESPINGI "
                    + ", decode('{STATO}','A','Notifica') NOTIFICA "
                    + ", serv.abilitazione abilitazione "
                    + ", amv_utente.link_report( "
                    + "          r.id_richiesta "
                    + "         ,'N' "
                    + "         ,r.modulo "
                    + "         ,r.istanza "
                    + "         , '{MVCONTEXT}' "
                    + "        ) link "
                    + "FROM ad4_livelli_sicurezza l "
                    + "       , ad4_richieste_abilitazione r "
                    + "       , ad4_utenti u "
                    + "       , ad4_utenti_soggetti s "
                    + "       , ad4_moduli m "
                    + "       , ad4_servizi serv "
                    + "WHERE u.utente = r.utente "
                    + "AND serv.modulo = r.modulo "
                    + "AND serv.istanza = r.istanza "
                    + "AND s.utente (+) = u.utente  "
                    + "AND m.modulo = r.modulo "
                    + "AND r.stato LIKE DECODE ({MVAV}, 1, 'F', 2, 'A', 3, 'R', 4, '%', 5, '%', 'C') "
                    + "AND r.modulo = nvl('{MOD}','') "
                    + "AND r.istanza = nvl('{IST}','') "
                    + "AND r.TIPO_NOTIFICA = 'POSTA' "
                    + "AND r.NOTIFICATA = 'N' "
                    + "     AND (   '{Modulo}' = 'AD4WEB' "
                    + "          OR EXISTS ( "
                    + "                SELECT 1 "
                    + "                  FROM ad4_ruoli ru, ad4_diritti_accesso d "
                    + "                 WHERE ru.ruolo = d.ruolo "
                    + "                   AND NVL (ru.profilo, -1) = NVL ({GroupID}, -1) "
                    + "                   AND d.Utente = '{Utente}' "
                    + "                   AND d.modulo = r.modulo "
                    + "                   AND d.istanza = r.istanza) "
                    + "         ) "
                    + "     AND l.livello(+) = serv.livello "
                    + "     AND r.utente LIKE DECODE(2,5,'{UTENTE}',r.utente) "
                    + "" );
        if ( StringUtils.isEmpty( (String) sesModulo.getObjectValue() ) ) sesModulo.setValue( "" );
        command.addParameter( "Modulo", sesModulo, null );
        if ( StringUtils.isEmpty( (String) sesIstanza.getObjectValue() ) ) sesIstanza.setValue( "" );
        command.addParameter( "Istanza", sesIstanza, null );
        if ( StringUtils.isEmpty( (String) urlSTATO.getObjectValue() ) ) urlSTATO.setValue( "" );
        command.addParameter( "STATO", urlSTATO, null );
        if ( StringUtils.isEmpty( (String) urlMOD.getObjectValue() ) ) urlMOD.setValue( "" );
        command.addParameter( "MOD", urlMOD, null );
        if ( StringUtils.isEmpty( (String) urlIST.getObjectValue() ) ) urlIST.setValue( "" );
        command.addParameter( "IST", urlIST, null );
        if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
        command.addParameter( "Utente", sesUtente, null );
        if ( urlMVAV.getObjectValue() == null ) urlMVAV.setValue( null );
        command.addParameter( "MVAV", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) sesMVCONTEXT.getObjectValue() ) ) sesMVCONTEXT.setValue( "" );
        command.addParameter( "MVCONTEXT", sesMVCONTEXT, null );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "%" );
        command.addParameter( "UTENTE", sesAD4UTENTE, null );
        if ( sesGroupID.getObjectValue() == null ) sesGroupID.setValue( -1 );
        command.addParameter( "GroupID", sesGroupID, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT r.id_richiesta id_richiesta ,  "
                                                         + "            'n.'||r.id_richiesta||' del '||to_char(r.data,'dd/mm/yyyy') DATA , u.nominativo autore ,  "
                                                         + "            m.descrizione||'-'||r.istanza servizio ,  "
                                                         + "            decode(ad4_soggetto.get_indirizzo_web(s.SOGGETTO,'Y'),null,'','&nbsp;('||ad4_soggetto.get_indirizzo_web(s.SOGGETTO)||')') indirizzo_web , decode(r.notificata,'S','(Notifica avvenuta)','F','(Notifica fallita)','N','(In attesa di notifica)','') notificata , decode(r.notificata,'S',1,0) notificato , decode(r.note_notifica,'STAMPA',1,0) stampa , decode(r.notificata,'S','<!--','') BEGIN_HIDE , decode(r.notificata,'S','-->','') END_HIDE , r.tipo_notifica||' '||r.indirizzo_notifica indirizzo_notifica , decode('{STATO}','C','Approva','F','Approva','') APPROVA , decode('{STATO}','C','Respingi','F','Respingi','') RESPINGI , decode('{STATO}','A','Notifica') NOTIFICA , serv.abilitazione abilitazione , amv_utente.link_report( r.id_richiesta ,'N' ,r.modulo ,r.istanza , '{MVCONTEXT}' ) link FROM ad4_livelli_sicurezza l , ad4_richieste_abilitazione r , ad4_utenti u , ad4_utenti_soggetti s , ad4_moduli m , ad4_servizi serv WHERE u.utente = r.utente AND serv.modulo = r.modulo AND serv.istanza = r.istanza AND s.utente (+) = u.utente AND m.modulo = r.modulo AND r.stato LIKE DECODE ({MVAV}, 1, 'F', 2, 'A', 3, 'R', 4, '%', 5, '%', 'C') AND r.modulo = nvl('{MOD}','') AND r.istanza = nvl('{IST}','') AND r.TIPO_NOTIFICA = 'POSTA' AND r.NOTIFICATA = 'N' AND ( '{Modulo}' = 'AD4WEB' OR EXISTS ( SELECT 1 FROM ad4_ruoli ru, ad4_diritti_accesso d WHERE ru.ruolo = d.ruolo AND NVL (ru.profilo, -1) = NVL ({GroupID}, -1) AND d.Utente = '{Utente}' AND d.modulo = r.modulo AND d.istanza = r.istanza) ) AND l.livello(+) = serv.livello AND r.utente LIKE DECODE(2,5,'{UTENTE}',r.utente)  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "r.data, r.id_richiesta DESC" );
        }
        command.setStartPos( ( pageNum - 1 ) * pageSize + 1 );
        command.setFetchSize( pageSize );

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        if ( ! StringUtils.isEmpty( command.getCountSql() ) ) {
            if ( ! ds.hasErrors() ) {
                amountOfRows = command.count();
                CCLogger.getInstance().debug(command.toString());
            }
        }
        Enumeration records = null;
        if ( ! ds.hasErrors() ) {
            records = command.getRows();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @2-7AF7001D
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                RICHIESTE_STAMPARow row = new RICHIESTE_STAMPARow();
                PK_RICHIESTE_STAMPA pk = new PK_RICHIESTE_STAMPA();
                DbRow record = (DbRow) records.nextElement();
                row.setDATA(Utils.convertToString(ds.parse(record.get("DATA"), row.getDATAField())));
                row.setREPORT(Utils.convertToString(ds.parse(record.get("LINK"), row.getREPORTField())));
                row.setAUTORE(Utils.convertToString(ds.parse(record.get("AUTORE"), row.getAUTOREField())));
                row.setID_RICHIESTA(Utils.convertToString(ds.parse(record.get("ID_RICHIESTA"), row.getID_RICHIESTAField())));
                row.setNOTIFICATA(Utils.convertToString(ds.parse(record.get("NOTIFICATA"), row.getNOTIFICATAField())));
                row.setBEGIN_HIDE(Utils.convertToString(ds.parse(record.get("BEGIN_HIDE"), row.getBEGIN_HIDEField())));
                row.setNOTIFICATO(Utils.convertToLong(ds.parse(record.get("NOTIFICATO"), row.getNOTIFICATOField())));
                row.setSTAMPA(Utils.convertToLong(ds.parse(record.get("STAMPA"), row.getSTAMPAField())));
                row.setEND_HIDE(Utils.convertToString(ds.parse(record.get("END_HIDE"), row.getEND_HIDEField())));
                pk.setID_CREDENZIALE(Utils.convertToDouble(ds.parse(record.get("ID_CREDENZIALE"), pk.getID_CREDENZIALEField())));
                row.setPrimaryKey(pk);
                ArrayList ccsCachedColumns = new ArrayList();
                CachedColumn column1 = new CachedColumn( "ID_CREDENZIALE", ControlType.FLOAT, page.getCCSLocale());
                column1.setValue(ds.parse(record.get("ID_CREDENZIALE"), new DoubleField()));
                ccsCachedColumns.add(column1);
                row.setCCSCachedColumns(ccsCachedColumns);
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @2-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//updateGrid @2-5BA59CBF
    boolean updateGrid() {
        boolean isErrors = false;
        if ( rows == null ) return ( ! isErrors );
        rowResults = new boolean[rows.length];
        rowErrors = new ArrayList[rows.length];
        for( int i=0; i < rows.length; i++ ) {
            row = rows[i];
            rowError = new ArrayList();
            if ( row.isApply() ) {
                
                
                    rowResults[i] = update();
                    rowErrors[i] = new ArrayList(rowError);
                
            } else {
                rowResults[i] = false;
            }
        }
//End updateGrid

//End of updateGrid @2-F575E732
        return ( ! isErrors );
    }
//End End of updateGrid

//update @2-75CA4C15
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AMV_UTENTE.SET_STAMPA_NOTIFICA(?,?,?)}" );
            command.addParameter( "P_ID_RICHIESTA", row.getID_RICHIESTAField(), java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_NOTIFICA", row.getNOTIFICATOField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_STAMPA", row.getSTAMPAField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @2-BB1231DF
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            Enumeration records = null;
            if ( ! ds.hasErrors() ) {
                records = command.getRows();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @2-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//getParameterByName @2-43DA514B
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_RICHIESTA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlID_RICHIESTA;
            } else if ( "ID_RICHIESTA".equals(name) && prefix == null ) {
                param = ctrlID_RICHIESTA;
            }
            if ( "NOTIFICATO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlNOTIFICATO;
            } else if ( "NOTIFICATO".equals(name) && prefix == null ) {
                param = ctrlNOTIFICATO;
            }
            if ( "STAMPA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSTAMPA;
            } else if ( "STAMPA".equals(name) && prefix == null ) {
                param = ctrlSTAMPA;
            }
            if ( "Modulo".equals(name) && "ses".equals(prefix) ) {
                param = sesModulo;
            } else if ( "Modulo".equals(name) && prefix == null ) {
                param = sesModulo;
            }
            if ( "Istanza".equals(name) && "ses".equals(prefix) ) {
                param = sesIstanza;
            } else if ( "Istanza".equals(name) && prefix == null ) {
                param = sesIstanza;
            }
            if ( "STATO".equals(name) && "url".equals(prefix) ) {
                param = urlSTATO;
            } else if ( "STATO".equals(name) && prefix == null ) {
                param = urlSTATO;
            }
            if ( "MOD".equals(name) && "url".equals(prefix) ) {
                param = urlMOD;
            } else if ( "MOD".equals(name) && prefix == null ) {
                param = urlMOD;
            }
            if ( "IST".equals(name) && "url".equals(prefix) ) {
                param = urlIST;
            } else if ( "IST".equals(name) && prefix == null ) {
                param = urlIST;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            if ( "MVCONTEXT".equals(name) && "ses".equals(prefix) ) {
                param = sesMVCONTEXT;
            } else if ( "MVCONTEXT".equals(name) && prefix == null ) {
                param = sesMVCONTEXT;
            }
            if ( "AD4UTENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4UTENTE;
            } else if ( "AD4UTENTE".equals(name) && prefix == null ) {
                param = sesAD4UTENTE;
            }
            if ( "GroupID".equals(name) && "ses".equals(prefix) ) {
                param = sesGroupID;
            } else if ( "GroupID".equals(name) && prefix == null ) {
                param = sesGroupID;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @2-25514F84
    public synchronized void addEditableGridDataObjectListener( EditableGridDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @2-9D4236FF
    public synchronized void removeEditableGridDataObjectListener( EditableGridDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @2-4379EB32
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @2-E42AFD15
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @2-5BEEEBAA
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildInsertEvent @2-810ECF3B
    public void fireBeforeBuildInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeBuildInsert(e);
        }
    }
//End fireBeforeBuildInsertEvent

//fireBeforeExecuteInsertEvent @2-4FFDC8FC
    public void fireBeforeExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeExecuteInsert(e);
        }
    }
//End fireBeforeExecuteInsertEvent

//fireAfterExecuteInsertEvent @2-F8354FEE
    public void fireAfterExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).afterExecuteInsert(e);
        }
    }
//End fireAfterExecuteInsertEvent

//fireBeforeBuildSelectEvent @2-AEB290C0
    public void fireBeforeBuildUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeBuildUpdate(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @2-55F1DF2C
    public void fireBeforeExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeExecuteUpdate(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @2-3E06DA3B
    public void fireAfterExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).afterExecuteUpdate(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildSelectEvent @2-0BC3DBB0
    public void fireBeforeBuildDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeBuildDelete(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteDeleteEvent @2-79A78EFE
    public void fireBeforeExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).beforeExecuteDelete(e);
        }
    }
//End fireBeforeExecuteDeleteEvent

//fireAfterExecuteDeleteEvent @2-2683A622
    public void fireAfterExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((EditableGridDataObjectListener)v.elementAt(i)).afterExecuteDelete(e);
        }
    }
//End fireAfterExecuteDeleteEvent

//class DataObject Tail @2-ED3F53A4
} // End of class DS
//End class DataObject Tail

