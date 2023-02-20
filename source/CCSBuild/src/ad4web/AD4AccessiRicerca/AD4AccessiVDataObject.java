//AD4AccessiV DataSource @142-281BB8A3
package ad4web.AD4AccessiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4AccessiV DataSource

//class DataObject Header @142-E9EF3372
public class AD4AccessiVDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @142-8CA3F832
    

    TextField urlS_TIPO = new TextField(null, null);
    
    TextField urlS_TERMINALE = new TextField(null, null);
    
    TextField urlS_UTENTE = new TextField(null, null);
    
    TextField urlS_ISTANZA = new TextField(null, null);
    
    TextField urlS_MODULO = new TextField(null, null);
    
    TextField urlS_DB_USER = new TextField(null, null);
    
    TextField urlS_DATA_DA = new TextField(null, null);
    
    TextField urlS_DATA_A = new TextField(null, null);
    
    TextField urlS_AUTENTICAZIONE = new TextField(null, null);
    
    TextField urlS_STATO = new TextField(null, null);
    
    TextField urlS_NOTE = new TextField(null, null);
    
    TextField urlS_RICERCA = new TextField(null, null);
    

    private AD4AccessiVRow[] rows = new AD4AccessiVRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @142-B858D3AB

    public void  setUrlS_TIPO( String param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param, Format ignore ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TERMINALE( String param ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_TERMINALE( Object param ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_TERMINALE( Object param, Format ignore ) {
        this.urlS_TERMINALE.setValue( param );
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

    public void  setUrlS_ISTANZA( String param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param, Format ignore ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_MODULO( String param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param, Format ignore ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_DB_USER( String param ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DB_USER( Object param ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DB_USER( Object param, Format ignore ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DATA_DA( String param ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_DA( Object param ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_DA( Object param, Format ignore ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_A( String param ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_DATA_A( Object param ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_DATA_A( Object param, Format ignore ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( String param ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( Object param ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( Object param, Format ignore ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_STATO( String param ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_STATO( Object param ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_STATO( Object param, Format ignore ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_NOTE( String param ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_NOTE( Object param ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_NOTE( Object param, Format ignore ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_RICERCA( String param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param, Format ignore ) {
        this.urlS_RICERCA.setValue( param );
    }

    public AD4AccessiVRow[] getRows() {
        return rows;
    }

    public void setRows(AD4AccessiVRow[] rows) {
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

//constructor @142-CD04C052
    public AD4AccessiVDataObject(Page page) {
        super(page);
    }
//End constructor

//load @142-6E27DF6E
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT  ACCESSI.ACCE_ID, "
                    + "        ACCESSI.SESSION_ID, "
                    + "        to_char(ACCESSI.DATA,'dd/mm/yyyy hh24:mi:ss') data, "
                    + "        decode(sign(instr(accessi.terminale,'::::')) ,1,decode(ACCESSI.LOG,'ON','OFF',ACCESSI.LOG),ACCESSI.LOG) LOG, "
                    + "        DECODE(decode(sign(instr(accessi.terminale,'::::')) ,1,decode(ACCESSI.LOG,'ON','OFF',ACCESSI.LOG),ACCESSI.LOG) ,'ON','Attivo','OFF','Chiuso','OUT','Uscito','TRC','Funzione','ABT','Uscito con Errore','WPW','Password Errata',ACCESSI.LOG) DESC_LOG,       "
                    + "        ACCESSI.UTENTE,  "
                    + "      "
                    + "        ACCESSI.ISTANZA, "
                    + "        ACCESSI.MODULO, "
                    + "        decode(UTENTI.NOMINATIVO, '', accessi.utente,  "
                    + "UTENTI.NOMINATIVO)||decode(ACCESSI.ISTANZA,'Istanza non definita.','','SI4AU','','<br>'||ACCESSI.ISTANZA||' - '||ISTANZE.DESCRIZIONE)||decode(ACCESSI.MODULO,'Modulo non definito.','','SI4AU','','<br>'||ACCESSI.MODULO||' - '||MODULI.DESCRIZIONE) PROVENIENZA, "
                    + "        ACCESSI.DB_USER, "
                    + "        ACCESSI.NOTE, "
                    + "        DECODE(INSTR(lower(ACCESSI.NOTE),'http://'),0, ACCESSI.NOTE, SUBSTR(ACCESSI.NOTE,INSTR(ACCESSI.NOTE,'/',-1) + 1))||'<br>'||replace(ACCESSI.TERMINALE,'::',':: ') accesso, "
                    + "        SESSIONI.STATUS, "
                    + "        ACCESSI.ID_CREDENZIALE, "
                    + "        CREDENZIALI.TIPO_CREDENZIALE, "
                    + "        nvl(ACCESSI.TIPO_AUTENTICAZIONE,'AD4') TIPO_AUTENTICAZIONE, "
                    + "        ACCESSI.STATO, "
                    + "        '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_STATO(ACCE_ID)||'\" src=\"../common/images/AD4/'||decode(ACCESSI.STATO, 'U', 'in_uso.GIF', 'D', 'da_arch.GIF', 'A', 'arch.GIF')||'\" border=\"0\">' DESC_STATO, "
                    + "        decode(ACCESSI.LOG,'ON',decode(ACCESSI.SESSION_ID,userenv('sessionid'),'AcceAttualeTR',decode(sign(instr(accessi.terminale,'::::')) ,1,'AFCDataTR',decode(status,'','AcceCrashTR','KILLED','AcceCrashTR','AcceAttivoTR'))),'AFCDataTR') tr_style   FROM ACCESSI, "
                    + "        SESSIONI, "
                    + "        UTENTI, "
                    + "        CREDENZIALI, "
                    + "        ISTANZE, "
                    + "        MODULI "
                    + "  WHERE ACCESSI.SESSION_ID = SESSIONI.AUDSID (+) and "
                    + "        ACCESSI.ID_CREDENZIALE = CREDENZIALI.ID_CREDENZIALE (+) and "
                    + "        ACCESSI.UTENTE = UTENTI.UTENTE (+) and "
                    + "        ACCESSI.ISTANZA = ISTANZE.ISTANZA (+) and "
                    + "        ACCESSI.MODULO = MODULI.MODULO (+) and    "
                    + "        ACCESSI.UTENTE like nvl('{s_UTENTE}','%') and "
                    + "        ACCESSI.ISTANZA like nvl('{s_ISTANZA}','%') and "
                    + "        ACCESSI.MODULO like nvl('{s_MODULO}','%') and "
                    + "        decode(sign(instr(accessi.terminale,'::::')) ,1,decode(ACCESSI.LOG,'ON','OFF',ACCESSI.LOG),ACCESSI.LOG)  like nvl('{s_TIPO}','%') and "
                    + "        ACCESSI.TERMINALE like '%{s_TERMINALE}%' and "
                    + "        ACCESSI.db_user like nvl('%{s_DB_USER}%','%') and "
                    + "        trunc(ACCESSI.data) between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') and "
                    + "        ACCESSI.stato like nvl('{s_STATO}','%') and "
                    + "        nvl(ACCESSI.TIPO_AUTENTICAZIONE, ' ') like nvl('{s_AUTENTICAZIONE}','%') and  "
                    + "        nvl(ACCESSI.NOTE, ' ') like '%{s_NOTE}%' and "
                    + "        nvl('{s_RICERCA}','N') = 'Y' "
                    + "  " );
        if ( StringUtils.isEmpty( (String) urlS_TIPO.getObjectValue() ) ) urlS_TIPO.setValue( "" );
        command.addParameter( "s_TIPO", urlS_TIPO, null );
        if ( StringUtils.isEmpty( (String) urlS_TERMINALE.getObjectValue() ) ) urlS_TERMINALE.setValue( "" );
        command.addParameter( "s_TERMINALE", urlS_TERMINALE, null );
        if ( StringUtils.isEmpty( (String) urlS_UTENTE.getObjectValue() ) ) urlS_UTENTE.setValue( "" );
        command.addParameter( "s_UTENTE", urlS_UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlS_ISTANZA.getObjectValue() ) ) urlS_ISTANZA.setValue( "" );
        command.addParameter( "s_ISTANZA", urlS_ISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlS_MODULO.getObjectValue() ) ) urlS_MODULO.setValue( "" );
        command.addParameter( "s_MODULO", urlS_MODULO, null );
        if ( StringUtils.isEmpty( (String) urlS_DB_USER.getObjectValue() ) ) urlS_DB_USER.setValue( "" );
        command.addParameter( "s_DB_USER", urlS_DB_USER, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_DA.getObjectValue() ) ) urlS_DATA_DA.setValue( "" );
        command.addParameter( "s_DATA_DA", urlS_DATA_DA, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_A.getObjectValue() ) ) urlS_DATA_A.setValue( "" );
        command.addParameter( "s_DATA_A", urlS_DATA_A, null );
        if ( StringUtils.isEmpty( (String) urlS_AUTENTICAZIONE.getObjectValue() ) ) urlS_AUTENTICAZIONE.setValue( "" );
        command.addParameter( "s_AUTENTICAZIONE", urlS_AUTENTICAZIONE, null );
        if ( StringUtils.isEmpty( (String) urlS_STATO.getObjectValue() ) ) urlS_STATO.setValue( "" );
        command.addParameter( "s_STATO", urlS_STATO, null );
        if ( StringUtils.isEmpty( (String) urlS_NOTE.getObjectValue() ) ) urlS_NOTE.setValue( "" );
        command.addParameter( "s_NOTE", urlS_NOTE, null );
        if ( StringUtils.isEmpty( (String) urlS_RICERCA.getObjectValue() ) ) urlS_RICERCA.setValue( "N" );
        command.addParameter( "s_RICERCA", urlS_RICERCA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT ACCESSI.ACCE_ID, ACCESSI.SESSION_ID,  "
                                                         + "            to_char(ACCESSI.DATA,'dd/mm/yyyy hh24:mi:ss') data,  "
                                                         + "            decode(sign(instr(accessi.terminale,'::::')) ,1,decode(ACCESSI.LOG,'ON','OFF',ACCESSI.LOG),ACCESSI.LOG) LOG,  "
                                                         + "            DECODE(decode(sign(instr(accessi.terminale,'::::')) ,1,decode(ACCESSI.LOG,'ON','OFF',ACCESSI.LOG),ACCESSI.LOG) ,'ON','Attivo','OFF','Chiuso','OUT','Uscito','TRC','Funzione','ABT','Uscito con Errore','WPW','Password Errata',ACCESSI.LOG) DESC_LOG, ACCESSI.UTENTE, ACCESSI.ISTANZA, ACCESSI.MODULO, decode(UTENTI.NOMINATIVO, '', accessi.utente, UTENTI.NOMINATIVO)||decode(ACCESSI.ISTANZA,'Istanza non definita.','','SI4AU','','<br>'||ACCESSI.ISTANZA||' - '||ISTANZE.DESCRIZIONE)||decode(ACCESSI.MODULO,'Modulo non definito.','','SI4AU','','<br>'||ACCESSI.MODULO||' - '||MODULI.DESCRIZIONE) PROVENIENZA, ACCESSI.DB_USER, ACCESSI.NOTE, DECODE(INSTR(lower(ACCESSI.NOTE),'http://'),0, ACCESSI.NOTE, SUBSTR(ACCESSI.NOTE,INSTR(ACCESSI.NOTE,'/',-1) + 1))||'<br>'||replace(ACCESSI.TERMINALE,'::',':: ') accesso, SESSIONI.STATUS, ACCESSI.ID_CREDENZIALE, CREDENZIALI.TIPO_CREDENZIALE, nvl(ACCESSI.TIPO_AUTENTICAZIONE,'AD4') TIPO_AUTENTICAZIONE, ACCESSI.STATO, '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_STATO(ACCE_ID)||'\" src=\"../common/images/AD4/'||decode(ACCESSI.STATO, 'U', 'in_uso.GIF', 'D', 'da_arch.GIF', 'A', 'arch.GIF')||'\" border=\"0\">' DESC_STATO, decode(ACCESSI.LOG,'ON',decode(ACCESSI.SESSION_ID,userenv('sessionid'),'AcceAttualeTR',decode(sign(instr(accessi.terminale,'::::')) ,1,'AFCDataTR',decode(status,'','AcceCrashTR','KILLED','AcceCrashTR','AcceAttivoTR'))),'AFCDataTR') tr_style FROM ACCESSI, SESSIONI, UTENTI, CREDENZIALI, ISTANZE, MODULI WHERE ACCESSI.SESSION_ID = SESSIONI.AUDSID (+) and ACCESSI.ID_CREDENZIALE = CREDENZIALI.ID_CREDENZIALE (+) and ACCESSI.UTENTE = UTENTI.UTENTE (+) and ACCESSI.ISTANZA = ISTANZE.ISTANZA (+) and ACCESSI.MODULO = MODULI.MODULO (+) and ACCESSI.UTENTE like nvl('{s_UTENTE}','%') and ACCESSI.ISTANZA like nvl('{s_ISTANZA}','%') and ACCESSI.MODULO like nvl('{s_MODULO}','%') and decode(sign(instr(accessi.terminale,'::::')) ,1,decode(ACCESSI.LOG,'ON','OFF',ACCESSI.LOG),ACCESSI.LOG) like nvl('{s_TIPO}','%') and ACCESSI.TERMINALE like '%{s_TERMINALE}%' and ACCESSI.db_user like nvl('%{s_DB_USER}%','%') and trunc(ACCESSI.data) between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') and ACCESSI.stato like nvl('{s_STATO}','%') and nvl(ACCESSI.TIPO_AUTENTICAZIONE, ' ') like nvl('{s_AUTENTICAZIONE}','%') and nvl(ACCESSI.NOTE, ' ') like '%{s_NOTE}%' and nvl('{s_RICERCA}','N') = 'Y'  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "trunc(ACCESSI.DATA) desc, ACCESSI.SESSION_ID desc, ACCESSI.DATA desc " );
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

//loadDataBind @142-C00595B5
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4AccessiVRow row = new AD4AccessiVRow();
                DbRow record = (DbRow) records.nextElement();
                row.setTR_STYLE(Utils.convertToString(ds.parse(record.get("TR_STYLE"), row.getTR_STYLEField())));
                row.setACCE_ID(Utils.convertToLong(ds.parse(record.get("ACCE_ID"), row.getACCE_IDField())));
                row.setDATA(Utils.convertToString(ds.parse(record.get("DATA"), row.getDATAField())));
                row.setSESSION_ID(Utils.convertToString(ds.parse(record.get("SESSION_ID"), row.getSESSION_IDField())));
                row.setDESC_LOG(Utils.convertToString(ds.parse(record.get("DESC_LOG"), row.getDESC_LOGField())));
                row.setPROVENIENZA(Utils.convertToString(ds.parse(record.get("PROVENIENZA"), row.getPROVENIENZAField())));
                row.setDB_USER(Utils.convertToString(ds.parse(record.get("DB_USER"), row.getDB_USERField())));
                row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
                row.setACCESSO(Utils.convertToString(ds.parse(record.get("ACCESSO"), row.getACCESSOField())));
                row.setTIPO_AUTENTICAZIONE(Utils.convertToString(ds.parse(record.get("TIPO_AUTENTICAZIONE"), row.getTIPO_AUTENTICAZIONEField())));
                row.setDESC_STATO(Utils.convertToString(ds.parse(record.get("DESC_STATO"), row.getDESC_STATOField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @142-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @142-E3F62B44
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_TIPO".equals(name) && "url".equals(prefix) ) {
                param = urlS_TIPO;
            } else if ( "s_TIPO".equals(name) && prefix == null ) {
                param = urlS_TIPO;
            }
            if ( "s_TERMINALE".equals(name) && "url".equals(prefix) ) {
                param = urlS_TERMINALE;
            } else if ( "s_TERMINALE".equals(name) && prefix == null ) {
                param = urlS_TERMINALE;
            }
            if ( "s_UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_UTENTE;
            } else if ( "s_UTENTE".equals(name) && prefix == null ) {
                param = urlS_UTENTE;
            }
            if ( "s_ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlS_ISTANZA;
            } else if ( "s_ISTANZA".equals(name) && prefix == null ) {
                param = urlS_ISTANZA;
            }
            if ( "s_MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlS_MODULO;
            } else if ( "s_MODULO".equals(name) && prefix == null ) {
                param = urlS_MODULO;
            }
            if ( "s_DB_USER".equals(name) && "url".equals(prefix) ) {
                param = urlS_DB_USER;
            } else if ( "s_DB_USER".equals(name) && prefix == null ) {
                param = urlS_DB_USER;
            }
            if ( "s_DATA_DA".equals(name) && "url".equals(prefix) ) {
                param = urlS_DATA_DA;
            } else if ( "s_DATA_DA".equals(name) && prefix == null ) {
                param = urlS_DATA_DA;
            }
            if ( "s_DATA_A".equals(name) && "url".equals(prefix) ) {
                param = urlS_DATA_A;
            } else if ( "s_DATA_A".equals(name) && prefix == null ) {
                param = urlS_DATA_A;
            }
            if ( "s_AUTENTICAZIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_AUTENTICAZIONE;
            } else if ( "s_AUTENTICAZIONE".equals(name) && prefix == null ) {
                param = urlS_AUTENTICAZIONE;
            }
            if ( "s_STATO".equals(name) && "url".equals(prefix) ) {
                param = urlS_STATO;
            } else if ( "s_STATO".equals(name) && prefix == null ) {
                param = urlS_STATO;
            }
            if ( "s_NOTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOTE;
            } else if ( "s_NOTE".equals(name) && prefix == null ) {
                param = urlS_NOTE;
            }
            if ( "s_RICERCA".equals(name) && "url".equals(prefix) ) {
                param = urlS_RICERCA;
            } else if ( "s_RICERCA".equals(name) && prefix == null ) {
                param = urlS_RICERCA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @142-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @142-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @142-238A81BB
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

//fireBeforeExecuteSelectEvent @142-9DA7B025
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

//fireAfterExecuteSelectEvent @142-F7E8A616
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

//class DataObject Tail @142-ED3F53A4
} // End of class DS
//End class DataObject Tail

