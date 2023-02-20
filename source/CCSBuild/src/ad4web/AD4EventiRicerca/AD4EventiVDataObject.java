//AD4EventiV DataSource @142-F58F784F
package ad4web.AD4EventiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4EventiV DataSource

//class DataObject Header @142-678BCE74
public class AD4EventiVDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @142-3BDC1C66
    

    TextField urlS_TIPO = new TextField(null, null);
    
    TextField urlS_TESTO = new TextField(null, null);
    
    TextField urlS_UTENTE = new TextField(null, null);
    
    TextField urlS_ISTANZA = new TextField(null, null);
    
    TextField urlS_MODULO = new TextField(null, null);
    
    TextField urlS_DB_USER = new TextField(null, null);
    
    TextField urlS_DATA_DA = new TextField(null, null);
    
    TextField urlS_DATA_A = new TextField(null, null);
    
    TextField urlS_GRAVITA = new TextField(null, null);
    
    TextField urlS_STATO = new TextField(null, null);
    
    TextField urlS_NOTE = new TextField(null, null);
    
    TextField urlS_FILE = new TextField(null, null);
    
    TextField urlS_SOLO_FILE = new TextField(null, null);
    

    private AD4EventiVRow[] rows = new AD4EventiVRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @142-B83866D2

    public void  setUrlS_TIPO( String param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param, Format ignore ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TESTO( String param ) {
        this.urlS_TESTO.setValue( param );
    }

    public void  setUrlS_TESTO( Object param ) {
        this.urlS_TESTO.setValue( param );
    }

    public void  setUrlS_TESTO( Object param, Format ignore ) {
        this.urlS_TESTO.setValue( param );
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

    public void  setUrlS_GRAVITA( String param ) {
        this.urlS_GRAVITA.setValue( param );
    }

    public void  setUrlS_GRAVITA( Object param ) {
        this.urlS_GRAVITA.setValue( param );
    }

    public void  setUrlS_GRAVITA( Object param, Format ignore ) {
        this.urlS_GRAVITA.setValue( param );
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

    public void  setUrlS_FILE( String param ) {
        this.urlS_FILE.setValue( param );
    }

    public void  setUrlS_FILE( Object param ) {
        this.urlS_FILE.setValue( param );
    }

    public void  setUrlS_FILE( Object param, Format ignore ) {
        this.urlS_FILE.setValue( param );
    }

    public void  setUrlS_SOLO_FILE( String param ) {
        this.urlS_SOLO_FILE.setValue( param );
    }

    public void  setUrlS_SOLO_FILE( Object param ) {
        this.urlS_SOLO_FILE.setValue( param );
    }

    public void  setUrlS_SOLO_FILE( Object param, Format ignore ) {
        this.urlS_SOLO_FILE.setValue( param );
    }

    public AD4EventiVRow[] getRows() {
        return rows;
    }

    public void setRows(AD4EventiVRow[] rows) {
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

//constructor @142-8CB1C344
    public AD4EventiVDataObject(Page page) {
        super(page);
    }
//End constructor

//load @142-4C63B99A
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT EVENTI.ID_EVENTO, "
                    + "         EVENTI.DATA, "
                    + "         EVENTI.NOTIFICATO,  "
                    + "   "
                    + "         '<img class=\"\" title=\"'||DECODE(EVENTI.NOTIFICATO, 1, 'Notificato',  "
                    + "'NON Notificato')||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.NOTIFICATO, 1,  "
                    + "'visto.GIF', 'elimina.gif')||'\" border=\"0\">&nbsp;' DESC_NOTIFICATO,  "
                    + "  "
                    + "         TO_CHAR(EVENTI.DATA,'dd/mm/yyyy hh24:mi:ss') DATA,  "
                    + "         EVENTI.TIPO,  "
                    + " "
                    + "         AD4_EVENTO.GET_TIPO_DESC(ID_EVENTO) DESC_TIPO,  "
                    + "   "
                    + "         AD4_EVENTO.GET_TIPO_DESC_RIDOTTA(ID_EVENTO) DESC_TIPO_RIDOTTA, "
                    + "         EVENTI.UTENTE,  "
                    + " "
                    + "         UTENTI.NOMINATIVO,   "
                    + "         EVENTI.ISTANZA, "
                    + "         EVENTI.MODULO,  "
                    + "   "
                    + "         UTENTI.NOMINATIVO||'<br>'||DECODE(EVENTI.ISTANZA,'Istanza non definita.','',EVENTI.ISTANZA||' - '||ISTANZE.DESCRIZIONE)||'<br>'||DECODE(EVENTI.MODULO,'Modulo non definito.','',EVENTI.MODULO||' - '||MODULI.DESCRIZIONE) PROVENIENZA, "
                    + "         EVENTI.TESTO, "
                    + "         EVENTI.ANNOTAZIONI,    "
                    + "         DECODE(EVENTI.TESTO,'','',EVENTI.TESTO||' ')||ad4web.GET_EVEN_LINK(EVENTI.ID_EVENTO)||DECODE(EVENTI.ANNOTAZIONI,'','','<br><br>'||EVENTI.ANNOTAZIONI) TESTO_NOTE,       "
                    + "         '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_GRAVITA(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.GRAVITA,'I','info.GIF', 'E', 'excla.GIF','S','stop.GIF')||'\" border=\"0\">&nbsp;' GRAVITA, "
                    + "         EVENTI.DB_USER,    "
                    + "         EVENTI.STATO STATO, "
                    + "         '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_STATO(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.STATO, 'U', 'in_uso.GIF', 'D', 'da_arch.GIF', 'A', 'arch.GIF')||'\" border=\"0\">' DESC_STATO,    "
                    + "         ad4_evento.check_file_locator(EVENTI.ID_EVENTO) check_file_locator "
                    + "    FROM EVENTI,    "
                    + "         UTENTI, "
                    + "         ISTANZE, "
                    + "         MODULI "
                    + "   WHERE EVENTI.utente  = UTENTI.utente (+) "
                    + "     AND EVENTI.istanza = ISTANZE.istanza (+) "
                    + "     AND EVENTI.modulo  = MODULI.modulo (+) "
                    + "     AND EVENTI.TIPO LIKE '{s_TIPO}' "
                    + "     AND EVENTI.testo LIKE '%{s_TESTO}%' "
                    + "     AND NVL(EVENTI.utente, ' ') LIKE NVL('{s_UTENTE}','%') "
                    + "     AND NVL(EVENTI.istanza, ' ') LIKE NVL('{s_ISTANZA}','%') "
                    + "     AND NVL(EVENTI.modulo, ' ') LIKE NVL('{s_MODULO}','%') "
                    + "     AND EVENTI.db_user LIKE NVL('%{s_DB_USER}%','%') "
                    + "     AND EVENTI.DATA BETWEEN TO_DATE(NVL('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') AND TO_DATE(NVL('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') "
                    + "     AND EVENTI.stato LIKE NVL('{s_STATO}','%') "
                    + "     AND NVL(EVENTI.gravita, ' ') LIKE NVL('{s_GRAVITA}','%') "
                    + "     AND NVL(EVENTI.annotazioni, ' ') LIKE '%{s_NOTE}%' "
                    + "     AND DECODE('{s_FILE}','',' ',AD4_EVENTO.GET_FILENAME(EVENTI.ID_EVENTO)) LIKE '%{s_FILE}%' "
                    + "     AND '{s_TIPO}' <> 'ARC_%' "
                    + "UNION "
                    + "  SELECT EVENTI.ID_EVENTO,    "
                    + "         EVENTI.DATA, "
                    + "         EVENTI.NOTIFICATO,    "
                    + "         '<img class=\"\" title=\"'||DECODE(EVENTI.NOTIFICATO, 1, 'Notificato', 'NON Notificato')||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.NOTIFICATO, 1, 'visto.GIF', 'elimina.gif')||'\" border=\"0\">&nbsp;' DESC_NOTIFICATO,   "
                    + "         TO_CHAR(EVENTI.DATA,'dd/mm/yyyy hh24:mi:ss') DATA,  "
                    + "         EVENTI.TIPO,  "
                    + "         AD4_EVENTO.GET_TIPO_DESC(ID_EVENTO) DESC_TIPO,    "
                    + "         AD4_EVENTO.GET_TIPO_DESC_RIDOTTA(ID_EVENTO) DESC_TIPO_RIDOTTA, "
                    + "         EVENTI.UTENTE,  "
                    + "         UTENTI.NOMINATIVO,   "
                    + "         EVENTI.ISTANZA, "
                    + "         EVENTI.MODULO,    "
                    + "         UTENTI.NOMINATIVO||'<br>'||DECODE(EVENTI.ISTANZA,'Istanza non definita.','',EVENTI.ISTANZA||' - '||ISTANZE.DESCRIZIONE)||'<br>'||DECODE(EVENTI.MODULO,'Modulo non definito.','',EVENTI.MODULO||' - '||MODULI.DESCRIZIONE) PROVENIENZA, "
                    + "         EVENTI.TESTO, "
                    + "         EVENTI.ANNOTAZIONI,    "
                    + "         DECODE(EVENTI.TESTO,'','',EVENTI.TESTO||' ')||ad4web.GET_EVEN_LINK(EVENTI.ID_EVENTO)||DECODE(EVENTI.ANNOTAZIONI,'','','<br><br>'||EVENTI.ANNOTAZIONI) TESTO_NOTE,       "
                    + "         '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_GRAVITA(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.GRAVITA,'I','info.GIF', 'E', 'excla.GIF','S','stop.GIF')||'\" border=\"0\">&nbsp;' GRAVITA, "
                    + "         EVENTI.DB_USER,    "
                    + "         EVENTI.STATO STATO, "
                    + "         '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_STATO(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.STATO, 'U', 'in_uso.GIF', 'D', 'da_arch.GIF', 'A', 'arch.GIF')||'\" border=\"0\">' DESC_STATO,    "
                    + "         ad4_evento.check_file_locator(EVENTI.ID_EVENTO) check_file_locator "
                    + "    FROM EVENTI, UTENTI, "
                    + "         ISTANZE, "
                    + "         MODULI "
                    + "   WHERE EVENTI.utente  = UTENTI.utente (+)   "
                    + "     AND EVENTI.istanza = ISTANZE.istanza (+)  "
                    + "     AND EVENTI.modulo  = MODULI.modulo (+) "
                    + "     AND EVENTI.TIPO = 'ARCLOG' "
                    + "     AND EVENTI.testo LIKE '%{s_TESTO}%' "
                    + "     AND NVL(EVENTI.utente, ' ') LIKE NVL('{s_UTENTE}','%') "
                    + "     AND NVL(EVENTI.istanza, ' ') LIKE NVL('{s_ISTANZA}','%') "
                    + "     AND NVL(EVENTI.modulo, ' ') LIKE NVL('{s_MODULO}','%') "
                    + "     AND EVENTI.db_user LIKE NVL('%{s_DB_USER}%','%') "
                    + "     AND EVENTI.DATA BETWEEN TO_DATE(NVL('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') AND TO_DATE(NVL('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') "
                    + "     AND EVENTI.stato LIKE NVL('{s_STATO}','%') "
                    + "     AND NVL(EVENTI.gravita, ' ') LIKE NVL('{s_GRAVITA}','%') "
                    + "     AND NVL(EVENTI.annotazioni, ' ') LIKE '%{s_NOTE}%' "
                    + "     AND DECODE('{s_FILE}','',' ',AD4_EVENTO.GET_FILENAME(EVENTI.ID_EVENTO)) LIKE '%{s_FILE}%'	  "
                    + "     AND EVENTI.ID_EVENTO IN ( "
                    + "                     SELECT   MAX (EVENTI.ID_EVENTO) "
                    + "                         FROM EVENTI "
                    + "                        WHERE EVENTI.FILE_LOCATOR IS NOT NULL "
                    + "                          AND EVENTI.GRAVITA = 'I' "
                    + "                          AND EVENTI.TIPO = 'ARCLOG' "
                    + "                     GROUP BY ad4_evento.get_filename (EVENTI.ID_EVENTO)) "
                    + "     AND '{s_TIPO}' = 'ARC_%' "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlS_TIPO.getObjectValue() ) ) urlS_TIPO.setValue( "ERROR" );
        command.addParameter( "s_TIPO", urlS_TIPO, null );
        if ( StringUtils.isEmpty( (String) urlS_TESTO.getObjectValue() ) ) urlS_TESTO.setValue( "" );
        command.addParameter( "s_TESTO", urlS_TESTO, null );
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
        if ( StringUtils.isEmpty( (String) urlS_GRAVITA.getObjectValue() ) ) urlS_GRAVITA.setValue( "" );
        command.addParameter( "s_GRAVITA", urlS_GRAVITA, null );
        if ( StringUtils.isEmpty( (String) urlS_STATO.getObjectValue() ) ) urlS_STATO.setValue( "" );
        command.addParameter( "s_STATO", urlS_STATO, null );
        if ( StringUtils.isEmpty( (String) urlS_NOTE.getObjectValue() ) ) urlS_NOTE.setValue( "" );
        command.addParameter( "s_NOTE", urlS_NOTE, null );
        if ( StringUtils.isEmpty( (String) urlS_FILE.getObjectValue() ) ) urlS_FILE.setValue( "" );
        command.addParameter( "s_FILE", urlS_FILE, null );
        if ( StringUtils.isEmpty( (String) urlS_SOLO_FILE.getObjectValue() ) ) urlS_SOLO_FILE.setValue( "N" );
        command.addParameter( "s_SOLO_FILE", urlS_SOLO_FILE, null );
        command.setCountSql( "SELECT COUNT(*) FROM (  SELECT EVENTI.ID_EVENTO, EVENTI.DATA, EVENTI.NOTIFICATO,  "
                                                         + "            '<img class=\"\" title=\"'||DECODE(EVENTI.NOTIFICATO, 1, 'Notificato',  "
                                                         + "            'NON Notificato')||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.NOTIFICATO, 1,  "
                                                         + "            'visto.GIF', 'elimina.gif')||'\" border=\"0\">&nbsp;' DESC_NOTIFICATO,  "
                                                         + "            TO_CHAR(EVENTI.DATA,'dd/mm/yyyy hh24:mi:ss') DATA, EVENTI.TIPO,  "
                                                         + "            AD4_EVENTO.GET_TIPO_DESC(ID_EVENTO) DESC_TIPO,  "
                                                         + "            AD4_EVENTO.GET_TIPO_DESC_RIDOTTA(ID_EVENTO) DESC_TIPO_RIDOTTA, EVENTI.UTENTE, UTENTI.NOMINATIVO,  "
                                                         + "            EVENTI.ISTANZA, EVENTI.MODULO,  "
                                                         + "            UTENTI.NOMINATIVO||'<br>'||DECODE(EVENTI.ISTANZA,'Istanza non definita.','',EVENTI.ISTANZA||' - '||ISTANZE.DESCRIZIONE)||'<br>'||DECODE(EVENTI.MODULO,'Modulo non definito.','',EVENTI.MODULO||' - '||MODULI.DESCRIZIONE) PROVENIENZA, EVENTI.TESTO, EVENTI.ANNOTAZIONI, DECODE(EVENTI.TESTO,'','',EVENTI.TESTO||' ')||ad4web.GET_EVEN_LINK(EVENTI.ID_EVENTO)||DECODE(EVENTI.ANNOTAZIONI,'','','<br><br>'||EVENTI.ANNOTAZIONI) TESTO_NOTE, '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_GRAVITA(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.GRAVITA,'I','info.GIF', 'E', 'excla.GIF','S','stop.GIF')||'\" border=\"0\">&nbsp;' GRAVITA, EVENTI.DB_USER, EVENTI.STATO STATO, '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_STATO(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.STATO, 'U', 'in_uso.GIF', 'D', 'da_arch.GIF', 'A', 'arch.GIF')||'\" border=\"0\">' DESC_STATO, ad4_evento.check_file_locator(EVENTI.ID_EVENTO) check_file_locator FROM EVENTI, UTENTI, ISTANZE, MODULI WHERE EVENTI.utente = UTENTI.utente (+) AND EVENTI.istanza = ISTANZE.istanza (+) AND EVENTI.modulo = MODULI.modulo (+) AND EVENTI.TIPO LIKE '{s_TIPO}' AND EVENTI.testo LIKE '%{s_TESTO}%' AND NVL(EVENTI.utente, ' ') LIKE NVL('{s_UTENTE}','%') AND NVL(EVENTI.istanza, ' ') LIKE NVL('{s_ISTANZA}','%') AND NVL(EVENTI.modulo, ' ') LIKE NVL('{s_MODULO}','%') AND EVENTI.db_user LIKE NVL('%{s_DB_USER}%','%') AND EVENTI.DATA BETWEEN TO_DATE(NVL('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') AND TO_DATE(NVL('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') AND EVENTI.stato LIKE NVL('{s_STATO}','%') AND NVL(EVENTI.gravita, ' ') LIKE NVL('{s_GRAVITA}','%') AND NVL(EVENTI.annotazioni, ' ') LIKE '%{s_NOTE}%' AND DECODE('{s_FILE}','',' ',AD4_EVENTO.GET_FILENAME(EVENTI.ID_EVENTO)) LIKE '%{s_FILE}%' AND '{s_TIPO}' <> 'ARC_%' UNION SELECT EVENTI.ID_EVENTO, EVENTI.DATA, EVENTI.NOTIFICATO, '<img class=\"\" title=\"'||DECODE(EVENTI.NOTIFICATO, 1, 'Notificato', 'NON Notificato')||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.NOTIFICATO, 1, 'visto.GIF', 'elimina.gif')||'\" border=\"0\">&nbsp;' DESC_NOTIFICATO, TO_CHAR(EVENTI.DATA,'dd/mm/yyyy hh24:mi:ss') DATA, EVENTI.TIPO, AD4_EVENTO.GET_TIPO_DESC(ID_EVENTO) DESC_TIPO, AD4_EVENTO.GET_TIPO_DESC_RIDOTTA(ID_EVENTO) DESC_TIPO_RIDOTTA, EVENTI.UTENTE, UTENTI.NOMINATIVO, EVENTI.ISTANZA, EVENTI.MODULO, UTENTI.NOMINATIVO||'<br>'||DECODE(EVENTI.ISTANZA,'Istanza non definita.','',EVENTI.ISTANZA||' - '||ISTANZE.DESCRIZIONE)||'<br>'||DECODE(EVENTI.MODULO,'Modulo non definito.','',EVENTI.MODULO||' - '||MODULI.DESCRIZIONE) PROVENIENZA, EVENTI.TESTO, EVENTI.ANNOTAZIONI, DECODE(EVENTI.TESTO,'','',EVENTI.TESTO||' ')||ad4web.GET_EVEN_LINK(EVENTI.ID_EVENTO)||DECODE(EVENTI.ANNOTAZIONI,'','','<br><br>'||EVENTI.ANNOTAZIONI) TESTO_NOTE, '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_GRAVITA(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.GRAVITA,'I','info.GIF', 'E', 'excla.GIF','S','stop.GIF')||'\" border=\"0\">&nbsp;' GRAVITA, EVENTI.DB_USER, EVENTI.STATO STATO, '<img class=\"\" title=\"'||AD4_EVENTO.GET_DESC_STATO(ID_EVENTO)||'\" src=\"../common/images/AD4/'||DECODE(EVENTI.STATO, 'U', 'in_uso.GIF', 'D', 'da_arch.GIF', 'A', 'arch.GIF')||'\" border=\"0\">' DESC_STATO, ad4_evento.check_file_locator(EVENTI.ID_EVENTO) check_file_locator FROM EVENTI, UTENTI, ISTANZE, MODULI WHERE EVENTI.utente = UTENTI.utente (+) AND EVENTI.istanza = ISTANZE.istanza (+) AND EVENTI.modulo = MODULI.modulo (+) AND EVENTI.TIPO = 'ARCLOG' AND EVENTI.testo LIKE '%{s_TESTO}%' AND NVL(EVENTI.utente, ' ') LIKE NVL('{s_UTENTE}','%') AND NVL(EVENTI.istanza, ' ') LIKE NVL('{s_ISTANZA}','%') AND NVL(EVENTI.modulo, ' ') LIKE NVL('{s_MODULO}','%') AND EVENTI.db_user LIKE NVL('%{s_DB_USER}%','%') AND EVENTI.DATA BETWEEN TO_DATE(NVL('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') AND TO_DATE(NVL('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') AND EVENTI.stato LIKE NVL('{s_STATO}','%') AND NVL(EVENTI.gravita, ' ') LIKE NVL('{s_GRAVITA}','%') AND NVL(EVENTI.annotazioni, ' ') LIKE '%{s_NOTE}%' AND DECODE('{s_FILE}','',' ',AD4_EVENTO.GET_FILENAME(EVENTI.ID_EVENTO)) LIKE '%{s_FILE}%'	 AND EVENTI.ID_EVENTO IN ( SELECT MAX (EVENTI.ID_EVENTO) FROM EVENTI WHERE EVENTI.FILE_LOCATOR IS NOT NULL AND EVENTI.GRAVITA = 'I' AND EVENTI.TIPO = 'ARCLOG' GROUP BY ad4_evento.get_filename (EVENTI.ID_EVENTO)) AND '{s_TIPO}' = 'ARC_%'  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "2 DESC, 1 ASC" );
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

//loadDataBind @142-FD556972
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4EventiVRow row = new AD4EventiVRow();
                DbRow record = (DbRow) records.nextElement();
                row.setID_EVENTO(Utils.convertToLong(ds.parse(record.get("ID_EVENTO"), row.getID_EVENTOField())));
                row.setDATA(Utils.convertToString(ds.parse(record.get("DATA"), row.getDATAField())));
                row.setDESC_TIPO(Utils.convertToString(ds.parse(record.get("DESC_TIPO"), row.getDESC_TIPOField())));
                row.setDESC_TIPO_RIDOTTA(Utils.convertToString(ds.parse(record.get("DESC_TIPO_RIDOTTA"), row.getDESC_TIPO_RIDOTTAField())));
                row.setTESTO_NOTE(Utils.convertToString(ds.parse(record.get("TESTO_NOTE"), row.getTESTO_NOTEField())));
                row.setPROVENIENZA(Utils.convertToString(ds.parse(record.get("PROVENIENZA"), row.getPROVENIENZAField())));
                row.setDB_USER(Utils.convertToString(ds.parse(record.get("DB_USER"), row.getDB_USERField())));
                row.setGRAVITA(Utils.convertToString(ds.parse(record.get("GRAVITA"), row.getGRAVITAField())));
                row.setDESC_STATO(Utils.convertToString(ds.parse(record.get("DESC_STATO"), row.getDESC_STATOField())));
                row.setDESC_NOTIFICATO(Utils.convertToString(ds.parse(record.get("DESC_NOTIFICATO"), row.getDESC_NOTIFICATOField())));
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

//getParameterByName @142-6EBB88DC
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_TIPO".equals(name) && "url".equals(prefix) ) {
                param = urlS_TIPO;
            } else if ( "s_TIPO".equals(name) && prefix == null ) {
                param = urlS_TIPO;
            }
            if ( "s_TESTO".equals(name) && "url".equals(prefix) ) {
                param = urlS_TESTO;
            } else if ( "s_TESTO".equals(name) && prefix == null ) {
                param = urlS_TESTO;
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
            if ( "s_GRAVITA".equals(name) && "url".equals(prefix) ) {
                param = urlS_GRAVITA;
            } else if ( "s_GRAVITA".equals(name) && prefix == null ) {
                param = urlS_GRAVITA;
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
            if ( "s_FILE".equals(name) && "url".equals(prefix) ) {
                param = urlS_FILE;
            } else if ( "s_FILE".equals(name) && prefix == null ) {
                param = urlS_FILE;
            }
            if ( "s_SOLO_FILE".equals(name) && "url".equals(prefix) ) {
                param = urlS_SOLO_FILE;
            } else if ( "s_SOLO_FILE".equals(name) && prefix == null ) {
                param = urlS_SOLO_FILE;
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

