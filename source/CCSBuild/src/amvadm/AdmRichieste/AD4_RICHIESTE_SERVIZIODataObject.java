//AD4_RICHIESTE_SERVIZIO DataSource @123-2786B8F7
package amvadm.AdmRichieste;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_RICHIESTE_SERVIZIO DataSource

//class DataObject Header @123-607F61E8
public class AD4_RICHIESTE_SERVIZIODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @123-71A2DA52
    

    LongField sesGroupID = new LongField(null, null);
    
    TextField urlMOD = new TextField(null, null);
    
    TextField urlIST = new TextField(null, null);
    
    LongField urlMVAV = new LongField(null, null);
    
    TextField sesModulo = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    TextField sesAD4UTENTE = new TextField(null, null);
    
    TextField urlSTATO = new TextField(null, null);
    
    TextField urlNOTIFICATA = new TextField(null, null);
    
    TextField urlTIPO_NOTIFICA = new TextField(null, null);
    

    private AD4_RICHIESTE_SERVIZIORow[] rows = new AD4_RICHIESTE_SERVIZIORow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @123-67F54985

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

    public void  setSesModulo( String param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param, Format ignore ) {
        this.sesModulo.setValue( param );
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

    public void  setSesAD4UTENTE( String param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param, Format ignore ) {
        this.sesAD4UTENTE.setValue( param );
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

    public void  setUrlNOTIFICATA( String param ) {
        this.urlNOTIFICATA.setValue( param );
    }

    public void  setUrlNOTIFICATA( Object param ) {
        this.urlNOTIFICATA.setValue( param );
    }

    public void  setUrlNOTIFICATA( Object param, Format ignore ) {
        this.urlNOTIFICATA.setValue( param );
    }

    public void  setUrlTIPO_NOTIFICA( String param ) {
        this.urlTIPO_NOTIFICA.setValue( param );
    }

    public void  setUrlTIPO_NOTIFICA( Object param ) {
        this.urlTIPO_NOTIFICA.setValue( param );
    }

    public void  setUrlTIPO_NOTIFICA( Object param, Format ignore ) {
        this.urlTIPO_NOTIFICA.setValue( param );
    }

    public AD4_RICHIESTE_SERVIZIORow[] getRows() {
        return rows;
    }

    public void setRows(AD4_RICHIESTE_SERVIZIORow[] rows) {
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

//constructor @123-4275BE45
    public AD4_RICHIESTE_SERVIZIODataObject(Page page) {
        super(page);
    }
//End constructor

//load @123-A4D30C5B
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT   r.id_richiesta ID "
                    + "       ,  "
                    + "   DECODE({MVAV},5,'','<a CLASS=\"AFCDataLink\" title=\"Gestione della richiesta\" href=\"AdmRichiesta.do?ID='||r.id_richiesta||'\">') "
                    + "	     ||'n.' "
                    + "         || r.id_richiesta "
                    + "         || ' del ' "
                    + "         || TO_CHAR (r.DATA, 'dd/mm/yyyy') "
                    + "		 ||DECODE({MVAV},5,'','</a>') DATA "
                    + "       , u.nominativo autore, m.descrizione || '-' || r.istanza servizio "
                    + "       , DECODE (ad4_soggetto.get_indirizzo_web (s.Soggetto, 'Y') "
                    + "               , NULL, '' "
                    + "               , '&nbsp;(' || ad4_soggetto.get_indirizzo_web (s.Soggetto) "
                    + "                 || ')' "
                    + "                ) indirizzo_web "
                    + "       , DECODE (r.notificata "
                    + "               , 'S', '(Notifica avvenuta)' "
                    + "               , 'F', '(Notifica fallita)' "
                    + "               , '' "
                    + "                ) notificata "
                    + "       , r.tipo_notifica || ' ' || r.indirizzo_notifica indirizzo_notifica "
                    + "       , DECODE (r.stato, 'C', 'Approva', 'F', 'Approva', '') APPROVA "
                    + "       , DECODE (r.stato, 'C', 'Respingi', 'F', 'Respingi', '') RESPINGI "
                    + "       , DECODE (r.stato, 'A', 'Notifica') NOTIFICA "
                    + "       , serv.abilitazione abilitazione "
                    + "       , amv_utente.GET_DATI_RICHIESTA(R.id_richiesta) dati "
                    + "    FROM ad4_livelli_sicurezza l "
                    + "       , ad4_richieste_abilitazione r "
                    + "       , ad4_utenti u "
                    + "       , ad4_utenti_soggetti s "
                    + "       , ad4_moduli m "
                    + "       , ad4_servizi serv "
                    + "   WHERE u.Utente = r.Utente "
                    + "     AND serv.modulo = r.modulo "
                    + "     AND serv.istanza = r.istanza "
                    + "     AND s.Utente(+) = u.Utente "
                    + "     AND m.modulo = r.modulo "
                    + "     AND r.modulo = '{MOD}' "
                    + "     AND r.istanza = '{IST}' "
                    + "     AND r.notificata = DECODE('{STATO}','A',NVL('{NOTIFICATA}','S'),r.notificata) "
                    + "     AND ('{STATO}' !='A' OR ('{STATO}' ='A' AND NVL('{NOTIFICATA}','S')!= 'N' AND NVL(r.tipo_notifica, serv.tipo_notifica) = NVL('{TIPO_NOTIFICA}','MAIL'))) "
                    + "     AND r.stato LIKE DECODE ({MVAV}, 1, 'F', 2, 'A', 3, 'R', 4, '%', 5, '%', 'C') "
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
                    + "     AND r.utente LIKE DECODE({MVAV},5,'{UTENTE}',r.utente) "
                    + "" );
        if ( sesGroupID.getObjectValue() == null ) sesGroupID.setValue( -1 );
        command.addParameter( "GroupID", sesGroupID, null );
        if ( StringUtils.isEmpty( (String) urlMOD.getObjectValue() ) ) urlMOD.setValue( "" );
        command.addParameter( "MOD", urlMOD, null );
        if ( StringUtils.isEmpty( (String) urlIST.getObjectValue() ) ) urlIST.setValue( "" );
        command.addParameter( "IST", urlIST, null );
        if ( urlMVAV.getObjectValue() == null ) urlMVAV.setValue( 0 );
        command.addParameter( "MVAV", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) sesModulo.getObjectValue() ) ) sesModulo.setValue( "" );
        command.addParameter( "Modulo", sesModulo, null );
        if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
        command.addParameter( "Utente", sesUtente, null );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "%" );
        command.addParameter( "UTENTE", sesAD4UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlSTATO.getObjectValue() ) ) urlSTATO.setValue( "" );
        command.addParameter( "STATO", urlSTATO, null );
        if ( StringUtils.isEmpty( (String) urlNOTIFICATA.getObjectValue() ) ) urlNOTIFICATA.setValue( "" );
        command.addParameter( "NOTIFICATA", urlNOTIFICATA, null );
        if ( StringUtils.isEmpty( (String) urlTIPO_NOTIFICA.getObjectValue() ) ) urlTIPO_NOTIFICA.setValue( "" );
        command.addParameter( "TIPO_NOTIFICA", urlTIPO_NOTIFICA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT r.id_richiesta ID ,  "
                                                         + "            DECODE({MVAV},5,'','<a CLASS=\"AFCDataLink\" title=\"Gestione della richiesta\" href=\"AdmRichiesta.do?ID='||r.id_richiesta||'\">') 	 ||'n.' || r.id_richiesta || ' del ' || TO_CHAR (r.DATA, 'dd/mm/yyyy') 		 ||DECODE({MVAV},5,'','</a>') DATA , u.nominativo autore, m.descrizione || '-' || r.istanza servizio , DECODE (ad4_soggetto.get_indirizzo_web (s.Soggetto, 'Y') , NULL, '' , '&nbsp;(' || ad4_soggetto.get_indirizzo_web (s.Soggetto) || ')' ) indirizzo_web , DECODE (r.notificata , 'S', '(Notifica avvenuta)' , 'F', '(Notifica fallita)' , '' ) notificata , r.tipo_notifica || ' ' || r.indirizzo_notifica indirizzo_notifica , DECODE (r.stato, 'C', 'Approva', 'F', 'Approva', '') APPROVA , DECODE (r.stato, 'C', 'Respingi', 'F', 'Respingi', '') RESPINGI , DECODE (r.stato, 'A', 'Notifica') NOTIFICA , serv.abilitazione abilitazione , amv_utente.GET_DATI_RICHIESTA(R.id_richiesta) dati FROM ad4_livelli_sicurezza l , ad4_richieste_abilitazione r , ad4_utenti u , ad4_utenti_soggetti s , ad4_moduli m , ad4_servizi serv WHERE u.Utente = r.Utente AND serv.modulo = r.modulo AND serv.istanza = r.istanza AND s.Utente(+) = u.Utente AND m.modulo = r.modulo AND r.modulo = '{MOD}' AND r.istanza = '{IST}' AND r.notificata = DECODE('{STATO}','A',NVL('{NOTIFICATA}','S'),r.notificata) AND ('{STATO}' !='A' OR ('{STATO}' ='A' AND NVL('{NOTIFICATA}','S')!= 'N' AND NVL(r.tipo_notifica, serv.tipo_notifica) = NVL('{TIPO_NOTIFICA}','MAIL'))) AND r.stato LIKE DECODE ({MVAV}, 1, 'F', 2, 'A', 3, 'R', 4, '%', 5, '%', 'C') AND ( '{Modulo}' = 'AD4WEB' OR EXISTS ( SELECT 1 FROM ad4_ruoli ru, ad4_diritti_accesso d WHERE ru.ruolo = d.ruolo AND NVL (ru.profilo, -1) = NVL ({GroupID}, -1) AND d.Utente = '{Utente}' AND d.modulo = r.modulo AND d.istanza = r.istanza) ) AND l.livello(+) = serv.livello AND r.utente LIKE DECODE({MVAV},5,'{UTENTE}',r.utente)  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "r.DATA DESC" );
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

//loadDataBind @123-AAE7C23C
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_RICHIESTE_SERVIZIORow row = new AD4_RICHIESTE_SERVIZIORow();
                DbRow record = (DbRow) records.nextElement();
                row.setDATA(Utils.convertToString(ds.parse(record.get("DATA"), row.getDATAField())));
                row.setAUTORE(Utils.convertToString(ds.parse(record.get("AUTORE"), row.getAUTOREField())));
                row.setINDIRIZZO_WEB(Utils.convertToString(ds.parse(record.get("INDIRIZZO_WEB"), row.getINDIRIZZO_WEBField())));
                row.setDATI(Utils.convertToString(ds.parse(record.get("DATI"), row.getDATIField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @123-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @123-DE743378
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "GroupID".equals(name) && "ses".equals(prefix) ) {
                param = sesGroupID;
            } else if ( "GroupID".equals(name) && prefix == null ) {
                param = sesGroupID;
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
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            if ( "Modulo".equals(name) && "ses".equals(prefix) ) {
                param = sesModulo;
            } else if ( "Modulo".equals(name) && prefix == null ) {
                param = sesModulo;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "AD4UTENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4UTENTE;
            } else if ( "AD4UTENTE".equals(name) && prefix == null ) {
                param = sesAD4UTENTE;
            }
            if ( "STATO".equals(name) && "url".equals(prefix) ) {
                param = urlSTATO;
            } else if ( "STATO".equals(name) && prefix == null ) {
                param = urlSTATO;
            }
            if ( "NOTIFICATA".equals(name) && "url".equals(prefix) ) {
                param = urlNOTIFICATA;
            } else if ( "NOTIFICATA".equals(name) && prefix == null ) {
                param = urlNOTIFICATA;
            }
            if ( "TIPO_NOTIFICA".equals(name) && "url".equals(prefix) ) {
                param = urlTIPO_NOTIFICA;
            } else if ( "TIPO_NOTIFICA".equals(name) && prefix == null ) {
                param = urlTIPO_NOTIFICA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @123-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @123-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @123-238A81BB
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource(this);
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @123-9DA7B025
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @123-F7E8A616
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//class DataObject Tail @123-ED3F53A4
} // End of class DS
//End class DataObject Tail

