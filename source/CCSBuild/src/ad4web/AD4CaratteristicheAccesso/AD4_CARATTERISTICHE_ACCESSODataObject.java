//AD4_CARATTERISTICHE_ACCESSO DataSource @38-18C6952C
package ad4web.AD4CaratteristicheAccesso;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_CARATTERISTICHE_ACCESSO DataSource

//class DataObject Header @38-2313953C
public class AD4_CARATTERISTICHE_ACCESSODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @38-5529B20B
    

    TextField postINSERT_UPDATE = new TextField(null, null);
    
    DoubleField postOLD_CAAC_ID = new DoubleField(null, null);
    
    TextField postOLD_PROGETTO = new TextField(null, null);
    
    TextField postOLD_ISTANZA = new TextField(null, null);
    
    TextField postOLD_MODULO = new TextField(null, null);
    
    TextField postOLD_UTENTE = new TextField(null, null);
    
    DoubleField postCAAC_ID = new DoubleField(null, null);
    
    TextField postTIPO_ACCESSO = new TextField(null, null);
    
    TextField postPROGETTO = new TextField(null, null);
    
    TextField postISTANZA = new TextField(null, null);
    
    TextField postMODULO = new TextField(null, null);
    
    TextField postUTENTE = new TextField(null, null);
    
    TextField ctrlACCESSO = new TextField(null, null);
    
    TextField ctrlACCESSO_SE = new TextField(null, null);
    
    TextField ctrlTRACCIA = new TextField(null, null);
    
    DoubleField postGIORNI_TRACCIA = new DoubleField(null, null);
    
    DoubleField postTENTATIVI_PASSWORD = new DoubleField(null, null);
    
    DoubleField postVALIDITA_PASSWORD = new DoubleField(null, null);
    
    DoubleField urlSLEEP = new DoubleField(null, null);
    
    TextField postSINGLE_SIGN_ON = new TextField(null, null);
    
    TextField ctrlLDAP = new TextField(null, null);
    
    DoubleField postMIN_LUNGHEZZA_PWD = new DoubleField(null, null);
    
    TextField ctrlMOD_PWD_PRIMO_ACCESSO = new TextField(null, null);
    
    TextField ctrlARCHIVIAZIONE_TRACCIA = new TextField(null, null);
    
    TextField postDISLOCAZIONE_TRACCIA = new TextField(null, null);
    
    TextField ctrlAMMESSI_CAR_SPECIALI_PWD = new TextField(null, null);
    
    TextField ctrlNUMERI_OBB_PWD = new TextField(null, null);
    
    LongField ctrlGG_PRIMA_RIUTILIZZO_PWD = new LongField(null, null);
    
    LongField postSPOSTA_FILE_ARCH = new LongField(null, null);
    
    TextField urlUTENTE = new TextField(null, null);
    
    TextField urlMODULO = new TextField(null, null);
    
    TextField urlISTANZA = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    
    TextField urlTIPO_ACCESSO = new TextField(null, null);
    

    private AD4_CARATTERISTICHE_ACCESSORow row = new AD4_CARATTERISTICHE_ACCESSORow();

//End attributes of DataObject

//properties of DataObject @38-285B578C

    public void  setPostINSERT_UPDATE( String param ) {
        this.postINSERT_UPDATE.setValue( param );
    }

    public void  setPostINSERT_UPDATE( Object param ) {
        this.postINSERT_UPDATE.setValue( param );
    }

    public void  setPostINSERT_UPDATE( Object param, Format ignore ) {
        this.postINSERT_UPDATE.setValue( param );
    }

    public void  setPostOLD_CAAC_ID( double param ) {
        this.postOLD_CAAC_ID.setValue( param );
    }

    public void  setPostOLD_CAAC_ID( double param, Format ignore ) throws java.text.ParseException {
        this.postOLD_CAAC_ID.setValue( param );
    }

    public void  setPostOLD_CAAC_ID( Object param, Format format ) throws java.text.ParseException {
        this.postOLD_CAAC_ID.setValue( param, format );
    }

    public void  setPostOLD_CAAC_ID( Double param ) {
        this.postOLD_CAAC_ID.setValue( param );
    }

    public void  setPostOLD_PROGETTO( String param ) {
        this.postOLD_PROGETTO.setValue( param );
    }

    public void  setPostOLD_PROGETTO( Object param ) {
        this.postOLD_PROGETTO.setValue( param );
    }

    public void  setPostOLD_PROGETTO( Object param, Format ignore ) {
        this.postOLD_PROGETTO.setValue( param );
    }

    public void  setPostOLD_ISTANZA( String param ) {
        this.postOLD_ISTANZA.setValue( param );
    }

    public void  setPostOLD_ISTANZA( Object param ) {
        this.postOLD_ISTANZA.setValue( param );
    }

    public void  setPostOLD_ISTANZA( Object param, Format ignore ) {
        this.postOLD_ISTANZA.setValue( param );
    }

    public void  setPostOLD_MODULO( String param ) {
        this.postOLD_MODULO.setValue( param );
    }

    public void  setPostOLD_MODULO( Object param ) {
        this.postOLD_MODULO.setValue( param );
    }

    public void  setPostOLD_MODULO( Object param, Format ignore ) {
        this.postOLD_MODULO.setValue( param );
    }

    public void  setPostOLD_UTENTE( String param ) {
        this.postOLD_UTENTE.setValue( param );
    }

    public void  setPostOLD_UTENTE( Object param ) {
        this.postOLD_UTENTE.setValue( param );
    }

    public void  setPostOLD_UTENTE( Object param, Format ignore ) {
        this.postOLD_UTENTE.setValue( param );
    }

    public void  setPostCAAC_ID( double param ) {
        this.postCAAC_ID.setValue( param );
    }

    public void  setPostCAAC_ID( double param, Format ignore ) throws java.text.ParseException {
        this.postCAAC_ID.setValue( param );
    }

    public void  setPostCAAC_ID( Object param, Format format ) throws java.text.ParseException {
        this.postCAAC_ID.setValue( param, format );
    }

    public void  setPostCAAC_ID( Double param ) {
        this.postCAAC_ID.setValue( param );
    }

    public void  setPostTIPO_ACCESSO( String param ) {
        this.postTIPO_ACCESSO.setValue( param );
    }

    public void  setPostTIPO_ACCESSO( Object param ) {
        this.postTIPO_ACCESSO.setValue( param );
    }

    public void  setPostTIPO_ACCESSO( Object param, Format ignore ) {
        this.postTIPO_ACCESSO.setValue( param );
    }

    public void  setPostPROGETTO( String param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param, Format ignore ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostISTANZA( String param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param, Format ignore ) {
        this.postISTANZA.setValue( param );
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

    public void  setPostUTENTE( String param ) {
        this.postUTENTE.setValue( param );
    }

    public void  setPostUTENTE( Object param ) {
        this.postUTENTE.setValue( param );
    }

    public void  setPostUTENTE( Object param, Format ignore ) {
        this.postUTENTE.setValue( param );
    }

    public void  setCtrlACCESSO( String param ) {
        this.ctrlACCESSO.setValue( param );
    }

    public void  setCtrlACCESSO( Object param ) {
        this.ctrlACCESSO.setValue( param );
    }

    public void  setCtrlACCESSO( Object param, Format ignore ) {
        this.ctrlACCESSO.setValue( param );
    }

    public void  setCtrlACCESSO_SE( String param ) {
        this.ctrlACCESSO_SE.setValue( param );
    }

    public void  setCtrlACCESSO_SE( Object param ) {
        this.ctrlACCESSO_SE.setValue( param );
    }

    public void  setCtrlACCESSO_SE( Object param, Format ignore ) {
        this.ctrlACCESSO_SE.setValue( param );
    }

    public void  setCtrlTRACCIA( String param ) {
        this.ctrlTRACCIA.setValue( param );
    }

    public void  setCtrlTRACCIA( Object param ) {
        this.ctrlTRACCIA.setValue( param );
    }

    public void  setCtrlTRACCIA( Object param, Format ignore ) {
        this.ctrlTRACCIA.setValue( param );
    }

    public void  setPostGIORNI_TRACCIA( double param ) {
        this.postGIORNI_TRACCIA.setValue( param );
    }

    public void  setPostGIORNI_TRACCIA( double param, Format ignore ) throws java.text.ParseException {
        this.postGIORNI_TRACCIA.setValue( param );
    }

    public void  setPostGIORNI_TRACCIA( Object param, Format format ) throws java.text.ParseException {
        this.postGIORNI_TRACCIA.setValue( param, format );
    }

    public void  setPostGIORNI_TRACCIA( Double param ) {
        this.postGIORNI_TRACCIA.setValue( param );
    }

    public void  setPostTENTATIVI_PASSWORD( double param ) {
        this.postTENTATIVI_PASSWORD.setValue( param );
    }

    public void  setPostTENTATIVI_PASSWORD( double param, Format ignore ) throws java.text.ParseException {
        this.postTENTATIVI_PASSWORD.setValue( param );
    }

    public void  setPostTENTATIVI_PASSWORD( Object param, Format format ) throws java.text.ParseException {
        this.postTENTATIVI_PASSWORD.setValue( param, format );
    }

    public void  setPostTENTATIVI_PASSWORD( Double param ) {
        this.postTENTATIVI_PASSWORD.setValue( param );
    }

    public void  setPostVALIDITA_PASSWORD( double param ) {
        this.postVALIDITA_PASSWORD.setValue( param );
    }

    public void  setPostVALIDITA_PASSWORD( double param, Format ignore ) throws java.text.ParseException {
        this.postVALIDITA_PASSWORD.setValue( param );
    }

    public void  setPostVALIDITA_PASSWORD( Object param, Format format ) throws java.text.ParseException {
        this.postVALIDITA_PASSWORD.setValue( param, format );
    }

    public void  setPostVALIDITA_PASSWORD( Double param ) {
        this.postVALIDITA_PASSWORD.setValue( param );
    }

    public void  setUrlSLEEP( double param ) {
        this.urlSLEEP.setValue( param );
    }

    public void  setUrlSLEEP( double param, Format ignore ) throws java.text.ParseException {
        this.urlSLEEP.setValue( param );
    }

    public void  setUrlSLEEP( Object param, Format format ) throws java.text.ParseException {
        this.urlSLEEP.setValue( param, format );
    }

    public void  setUrlSLEEP( Double param ) {
        this.urlSLEEP.setValue( param );
    }

    public void  setPostSINGLE_SIGN_ON( String param ) {
        this.postSINGLE_SIGN_ON.setValue( param );
    }

    public void  setPostSINGLE_SIGN_ON( Object param ) {
        this.postSINGLE_SIGN_ON.setValue( param );
    }

    public void  setPostSINGLE_SIGN_ON( Object param, Format ignore ) {
        this.postSINGLE_SIGN_ON.setValue( param );
    }

    public void  setCtrlLDAP( String param ) {
        this.ctrlLDAP.setValue( param );
    }

    public void  setCtrlLDAP( Object param ) {
        this.ctrlLDAP.setValue( param );
    }

    public void  setCtrlLDAP( Object param, Format ignore ) {
        this.ctrlLDAP.setValue( param );
    }

    public void  setPostMIN_LUNGHEZZA_PWD( double param ) {
        this.postMIN_LUNGHEZZA_PWD.setValue( param );
    }

    public void  setPostMIN_LUNGHEZZA_PWD( double param, Format ignore ) throws java.text.ParseException {
        this.postMIN_LUNGHEZZA_PWD.setValue( param );
    }

    public void  setPostMIN_LUNGHEZZA_PWD( Object param, Format format ) throws java.text.ParseException {
        this.postMIN_LUNGHEZZA_PWD.setValue( param, format );
    }

    public void  setPostMIN_LUNGHEZZA_PWD( Double param ) {
        this.postMIN_LUNGHEZZA_PWD.setValue( param );
    }

    public void  setCtrlMOD_PWD_PRIMO_ACCESSO( String param ) {
        this.ctrlMOD_PWD_PRIMO_ACCESSO.setValue( param );
    }

    public void  setCtrlMOD_PWD_PRIMO_ACCESSO( Object param ) {
        this.ctrlMOD_PWD_PRIMO_ACCESSO.setValue( param );
    }

    public void  setCtrlMOD_PWD_PRIMO_ACCESSO( Object param, Format ignore ) {
        this.ctrlMOD_PWD_PRIMO_ACCESSO.setValue( param );
    }

    public void  setCtrlARCHIVIAZIONE_TRACCIA( String param ) {
        this.ctrlARCHIVIAZIONE_TRACCIA.setValue( param );
    }

    public void  setCtrlARCHIVIAZIONE_TRACCIA( Object param ) {
        this.ctrlARCHIVIAZIONE_TRACCIA.setValue( param );
    }

    public void  setCtrlARCHIVIAZIONE_TRACCIA( Object param, Format ignore ) {
        this.ctrlARCHIVIAZIONE_TRACCIA.setValue( param );
    }

    public void  setPostDISLOCAZIONE_TRACCIA( String param ) {
        this.postDISLOCAZIONE_TRACCIA.setValue( param );
    }

    public void  setPostDISLOCAZIONE_TRACCIA( Object param ) {
        this.postDISLOCAZIONE_TRACCIA.setValue( param );
    }

    public void  setPostDISLOCAZIONE_TRACCIA( Object param, Format ignore ) {
        this.postDISLOCAZIONE_TRACCIA.setValue( param );
    }

    public void  setCtrlAMMESSI_CAR_SPECIALI_PWD( String param ) {
        this.ctrlAMMESSI_CAR_SPECIALI_PWD.setValue( param );
    }

    public void  setCtrlAMMESSI_CAR_SPECIALI_PWD( Object param ) {
        this.ctrlAMMESSI_CAR_SPECIALI_PWD.setValue( param );
    }

    public void  setCtrlAMMESSI_CAR_SPECIALI_PWD( Object param, Format ignore ) {
        this.ctrlAMMESSI_CAR_SPECIALI_PWD.setValue( param );
    }

    public void  setCtrlNUMERI_OBB_PWD( String param ) {
        this.ctrlNUMERI_OBB_PWD.setValue( param );
    }

    public void  setCtrlNUMERI_OBB_PWD( Object param ) {
        this.ctrlNUMERI_OBB_PWD.setValue( param );
    }

    public void  setCtrlNUMERI_OBB_PWD( Object param, Format ignore ) {
        this.ctrlNUMERI_OBB_PWD.setValue( param );
    }

    public void  setCtrlGG_PRIMA_RIUTILIZZO_PWD( long param ) {
        this.ctrlGG_PRIMA_RIUTILIZZO_PWD.setValue( param );
    }

    public void  setCtrlGG_PRIMA_RIUTILIZZO_PWD( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlGG_PRIMA_RIUTILIZZO_PWD.setValue( param );
    }

    public void  setCtrlGG_PRIMA_RIUTILIZZO_PWD( Object param, Format format ) throws java.text.ParseException {
        this.ctrlGG_PRIMA_RIUTILIZZO_PWD.setValue( param, format );
    }

    public void  setCtrlGG_PRIMA_RIUTILIZZO_PWD( Long param ) {
        this.ctrlGG_PRIMA_RIUTILIZZO_PWD.setValue( param );
    }

    public void  setPostSPOSTA_FILE_ARCH( long param ) {
        this.postSPOSTA_FILE_ARCH.setValue( param );
    }

    public void  setPostSPOSTA_FILE_ARCH( long param, Format ignore ) throws java.text.ParseException {
        this.postSPOSTA_FILE_ARCH.setValue( param );
    }

    public void  setPostSPOSTA_FILE_ARCH( Object param, Format format ) throws java.text.ParseException {
        this.postSPOSTA_FILE_ARCH.setValue( param, format );
    }

    public void  setPostSPOSTA_FILE_ARCH( Long param ) {
        this.postSPOSTA_FILE_ARCH.setValue( param );
    }

    public void  setUrlUTENTE( String param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param, Format ignore ) {
        this.urlUTENTE.setValue( param );
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

    public void  setUrlISTANZA( String param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param, Format ignore ) {
        this.urlISTANZA.setValue( param );
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

    public void  setUrlTIPO_ACCESSO( String param ) {
        this.urlTIPO_ACCESSO.setValue( param );
    }

    public void  setUrlTIPO_ACCESSO( Object param ) {
        this.urlTIPO_ACCESSO.setValue( param );
    }

    public void  setUrlTIPO_ACCESSO( Object param, Format ignore ) {
        this.urlTIPO_ACCESSO.setValue( param );
    }

    public AD4_CARATTERISTICHE_ACCESSORow getRow() {
        return row;
    }

    public void setRow( AD4_CARATTERISTICHE_ACCESSORow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @38-9F8FDF77
    public AD4_CARATTERISTICHE_ACCESSODataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new AD4_CARATTERISTICHE_ACCESSODataObjectHandler() );
    }
//End constructor

//load @38-5A27C13B
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT 'U' insert_update, "
                    + "         DECODE('{TIPO_ACCESSO}', 'P',  "
                    + "'del Progetto '||'{PROGETTO}', "
                    + "                                  'I',  "
                    + "'dell''Istanza '||'{ISTANZA}', "
                    + "                                  'M',  "
                    + "'del Modulo '||'{MODULO}', "
                    + "                                  'D',  "
                    + "'dell''Utente '||'{UTENTE}'||' al Progetto '||'{PROGETTO}'||' - Modulo '||'{MODULO}'||' - Istanza '||'{ISTANZA}', "
                    + "                                  'G', 'del Gruppo '||'{UTENTE}'||' al Progetto '||'{PROGETTO}'||' - Modulo '||'{MODULO}'||' - Istanza '||'{ISTANZA}') CAAC_DESC, "
                    + "         AD4_CARATTERISTICA_ACCESSO.GET_DESC_ACCESSO('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_ACCESSO, "
                    + "         AD4_CARATTERISTICA_ACCESSO.GET_DESC_PASSWORD('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_PASSWORD, "
                    + "         AD4_CARATTERISTICA_ACCESSO.GET_DESC_AUTENTICAZIONE('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_AUTENTICAZIONE, "
                    + "         Accesso,    "
                    + "         ACCESSO_SE, "
                    + "         DECODE('{TIPO_ACCESSO}', 'P', 'Applica Senza Eccezioni', "
                    + "                                  'I', 'Applica Senza Eccezioni', "
                    + "                                  'M', 'Applica Senza Eccezioni', "
                    + "                                  'D', 'Super Utente', "
                    + "                                  'G', 'Super Utente') ACCESSO_SE_DESC, "
                    + "         TRACCIA,    "
                    + "         GIORNI_TRACCIA,    "
                    + "         VALIDITA_PASSWORD,    "
                    + "         TENTATIVI_PASSWORD,    "
                    + "         CAAC_ID,    "
                    + "         TIPO_ACCESSO,    "
                    + "         PROGETTO,    "
                    + "         ISTANZA,    "
                    + "         MODULO,    "
                    + "         Utente,    "
                    + "         SINGLE_SIGN_ON,    "
                    + "         SLEEP,    "
                    + "         LDAP,    "
                    + "         MIN_LUNGHEZZA_PWD, "
                    + "         MIN_LUNGHEZZA_PWD MIN_LUNGHEZZA_PWD_OLD,    "
                    + "         MOD_PWD_PRIMO_ACCESSO, "
                    + "         ARCHIVIAZIONE_TRACCIA, "
                    + "         DISLOCAZIONE_TRACCIA,  "
                    + "         DISLOCAZIONE_TRACCIA DISLOCAZIONE_TRACCIA_OLD, "
                    + "         AMMESSI_CAR_SPECIALI_PWD,  "
                    + "         AMMESSI_CAR_SPECIALI_PWD AMMESSI_CAR_SPECIALI_PWD_OLD, "
                    + "         NUMERI_OBB_PWD, "
                    + "         NUMERI_OBB_PWD NUMERI_OBB_PWD_OLD, "
                    + "         GG_PRIMA_RIUTILIZZO_PWD, "
                    + "         GG_PRIMA_RIUTILIZZO_PWD GG_PRIMA_RIUTILIZZO_PWD_OLD, "
                    + "         CAAC_ID OLD_CAAC_ID,     "
                    + "         PROGETTO OLD_PROGETTO, "
                    + "         ISTANZA OLD_ISTANZA, "
                    + "         MODULO OLD_MODULO, "
                    + "         Utente OLD_UTENTE, "
                    + "         'Archiviazione traccia '||DECODE(Accesso.is_job_archiviazione_attivo,1,'','NON ')||'attivata.' JOB_ATTIVO, "
                    + "         DECODE(DISLOCAZIONE_TRACCIA,'',0,Ad4_Evento.get_dir_num_file(DISLOCAZIONE_TRACCIA, 'log_______.xml',1)) NUM_FILE_ARCHIVIATI, "
                    + "         DECODE(DISLOCAZIONE_TRACCIA,'',0,Ad4_Evento.get_dir_tot_dim(DISLOCAZIONE_TRACCIA)) DIM_FILE_ARCHIVIATI, "
                    + "         0 SPOSTA_FILE_ARCH, "
                    + "         Caratteristica_Accesso.GET_DESC_DETTAGLI('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_DETTAGLI, "
                    + "         DECODE('{TIPO_ACCESSO}','P',' Archivia sul DB Server in ','I',' Archivia sul DB Server in ',' ') label_arch, "
                    + "       DECODE('{TIPO_ACCESSO}','P','','I','','hidden') ckb_arch_class, "
                    + "       DECODE('{TIPO_ACCESSO}','P','AFCInput','I','AFCInput','hidden') input_arch_class "
                    + "    FROM CARATTERISTICHE_ACCESSO   "
                    + "   WHERE ( TIPO_ACCESSO = '{TIPO_ACCESSO}' ) AND   "
                    + "         ( PROGETTO = '{PROGETTO}' ) AND   "
                    + "         ( NVL(ISTANZA,'xxx') = DECODE('{TIPO_ACCESSO}','I','{ISTANZA}','G','{ISTANZA}','D','{ISTANZA}',NVL(ISTANZA,'xxx')) ) AND   "
                    + "         ( NVL(MODULO,'xxx') = DECODE('{TIPO_ACCESSO}','M','{MODULO}','G','{MODULO}','D','{MODULO}',NVL(MODULO,'xxx')) ) AND   "
                    + "         ( NVL(Utente,'xxx') = DECODE('{TIPO_ACCESSO}','G','{UTENTE}','D','{UTENTE}',NVL(Utente,'xxx')) ) "
                    + "UNION "
                    + "  SELECT 'I' insert_update, "
                    + "         DECODE('{TIPO_ACCESSO}', 'P', 'del Progetto '||'{PROGETTO}', "
                    + "                                  'I', 'dell''Istanza '||'{ISTANZA}', "
                    + "                                  'M', 'del Modulo '||'{MODULO}', "
                    + "                                  'D', 'dell''Utente '||'{UTENTE}'||' al Progetto '||'{PROGETTO}'||' - Modulo '||'{MODULO}'||' - Istanza '||'{ISTANZA}', "
                    + "                                  'G', 'del Gruppo '||'{UTENTE}'||' al Progetto '||'{PROGETTO}'||' - Modulo '||'{MODULO}'||' - Istanza '||'{ISTANZA}') CAAC_DESC, "
                    + "         AD4_CARATTERISTICA_ACCESSO.GET_DESC_ACCESSO('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_ACCESSO, "
                    + "         AD4_CARATTERISTICA_ACCESSO.GET_DESC_PASSWORD('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_PASSWORD, "
                    + "         AD4_CARATTERISTICA_ACCESSO.GET_DESC_AUTENTICAZIONE('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_AUTENTICAZIONE, "
                    + "         'L',    "
                    + "         'NO', "
                    + "         DECODE('{TIPO_ACCESSO}', 'P', 'Senza Eccezioni', "
                    + "                                  'I', 'Senza Eccezioni', "
                    + "                                  'M', 'Senza Eccezioni', "
                    + "                                  'D', 'Super Utente', "
                    + "                                  'G', 'Super Utente') ACCESSO_SE_DESC, "
                    + "         'M' TRACCIA,    "
                    + "         60 GIORNI_TRACCIA,    "
                    + "         TO_NUMBER(NULL) VALIDITA_PASSWORD,    "
                    + "         TO_NUMBER(NULL) TENTATIVI_PASSWORD,    "
                    + "         TO_NUMBER(NULL) CAAC_ID,    "
                    + "         '{TIPO_ACCESSO}' TIPO_ACCESSO,    "
                    + "         '{PROGETTO}' PROGETTO,    "
                    + "         '{ISTANZA}' ISTANZA,    "
                    + "         '{MODULO}' MODULO,    "
                    + "         '{UTENTE}' UTENTE,    "
                    + "         'NO' SINGLE_SIGN_ON,    "
                    + "         TO_NUMBER(NULL) SLEEP,    "
                    + "         'NO' LDAP,    "
                    + "         TO_NUMBER(NULL) MIN_LUNGHEZZA_PWD,   "
                    + "         TO_NUMBER(NULL) MIN_LUNGHEZZA_PWD_OLD,  "
                    + "         'NO' MOD_PWD_PRIMO_ACCESSO, "
                    + "         'NO' ARCHIVIAZIONE_TRACCIA, "
                    + "         TO_CHAR(NULL) DISLOCAZIONE_TRACCIA, "
                    + "         TO_CHAR(NULL) DISLOCAZIONE_TRACCIA_OLD,       "
                    + "         'SI' AMMESSI_CAR_SPECIALI_PWD,  "
                    + "         'SI' AMMESSI_CAR_SPECIALI_PWD_OLD, "
                    + "         'NO' NUMERI_OBB_PWD, "
                    + "         'NO' NUMERI_OBB_PWD_OLD,   "
                    + "         TO_NUMBER(NULL) GG_PRIMA_RIUTILIZZO_PWD,   "
                    + "         TO_NUMBER(NULL) GG_PRIMA_RIUTILIZZO_PWD_OLD,  "
                    + "         TO_NUMBER(NULL) OLD_CAAC_ID,   "
                    + "         '{PROGETTO}' OLD_PROGETTO,    "
                    + "         '{ISTANZA}' OLD_ISTANZA,    "
                    + "         '{MODULO}' OLD_MODULO,    "
                    + "         '{UTENTE}' OLD_UTENTE, "
                    + "         'Archiviazione traccia '||DECODE(Accesso.is_job_archiviazione_attivo,1,'','non ')||'attivata.' JOB_ATTIVO, "
                    + "         0 NUM_FILE_ARCHIVIATI, "
                    + "         0 DIM_FILE_ARCHIVIATI, "
                    + "         0 SPOSTA_FILE_ARCH, "
                    + "         Caratteristica_Accesso.GET_DESC_DETTAGLI('{TIPO_ACCESSO}','{PROGETTO}','{ISTANZA}','{MODULO}','{UTENTE}') DESC_DETTAGLI, "
                    + "         DECODE('{TIPO_ACCESSO}','P',' Archivia sul DB Server in ','I',' Archivia sul DB Server in ',' ') label_arch, "
                    + "         DECODE('{TIPO_ACCESSO}','P','','I','','hidden') ckb_arch_class, "
                    + "         DECODE('{TIPO_ACCESSO}','P','AFCInput','I','AFCInput','hidden') input_arch_class "
                    + "    FROM DUAL   "
                    + "   WHERE NOT EXISTS (SELECT 1 "
                    + "                       FROM CARATTERISTICHE_ACCESSO "
                    + "                 WHERE ( TIPO_ACCESSO = '{TIPO_ACCESSO}' ) AND   "
                    + "                            ( PROGETTO = '{PROGETTO}' ) AND   "
                    + "                            ( NVL(ISTANZA,'xxx') = DECODE('{TIPO_ACCESSO}','I','{ISTANZA}','G','{ISTANZA}','D','{ISTANZA}',NVL(ISTANZA,'xxx')) ) AND   "
                    + "                            ( NVL(MODULO,'xxx') = DECODE('{TIPO_ACCESSO}','M','{MODULO}','G','{MODULO}','D','{MODULO}',NVL(MODULO,'xxx')) ) AND   "
                    + "                            ( NVL(Utente,'xxx') = DECODE('{TIPO_ACCESSO}','G','{UTENTE}','D','{UTENTE}',NVL(Utente,'xxx')) ) "
                    + "               )    " );
        if ( StringUtils.isEmpty( (String) urlUTENTE.getObjectValue() ) ) urlUTENTE.setValue( "" );
        command.addParameter( "UTENTE", urlUTENTE, null );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULO", urlMODULO, null );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZA", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlTIPO_ACCESSO.getObjectValue() ) ) urlTIPO_ACCESSO.setValue( "" );
        command.addParameter( "TIPO_ACCESSO", urlTIPO_ACCESSO, null );
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

//loadDataBind @38-DC1EA758
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setCAAC_DESC(Utils.convertToString(ds.parse(record.get("CAAC_DESC"), row.getCAAC_DESCField())));
            row.setDESC_ACCESSO(Utils.convertToString(ds.parse(record.get("DESC_ACCESSO"), row.getDESC_ACCESSOField())));
            row.setOLD_CAAC_ID(Utils.convertToString(ds.parse(record.get("OLD_CAAC_ID"), row.getOLD_CAAC_IDField())));
            row.setCAAC_ID(Utils.convertToString(ds.parse(record.get("CAAC_ID"), row.getCAAC_IDField())));
            row.setOLD_MODULO(Utils.convertToString(ds.parse(record.get("OLD_MODULO"), row.getOLD_MODULOField())));
            row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
            row.setINSERT_UPDATE(Utils.convertToString(ds.parse(record.get("INSERT_UPDATE"), row.getINSERT_UPDATEField())));
            row.setJOB_ATTIVO(Utils.convertToString(ds.parse(record.get("JOB_ATTIVO"), row.getJOB_ATTIVOField())));
            row.setDESC_PASSWORD(Utils.convertToString(ds.parse(record.get("DESC_PASSWORD"), row.getDESC_PASSWORDField())));
            row.setOLD_PROGETTO(Utils.convertToString(ds.parse(record.get("OLD_PROGETTO"), row.getOLD_PROGETTOField())));
            row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
            row.setOLD_UTENTE(Utils.convertToString(ds.parse(record.get("OLD_UTENTE"), row.getOLD_UTENTEField())));
            row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
            row.setTIPO_ACCESSO(Utils.convertToString(ds.parse(record.get("TIPO_ACCESSO"), row.getTIPO_ACCESSOField())));
            row.setDESC_AUTENTICAZIONE(Utils.convertToString(ds.parse(record.get("DESC_AUTENTICAZIONE"), row.getDESC_AUTENTICAZIONEField())));
            row.setOLD_ISTANZA(Utils.convertToString(ds.parse(record.get("OLD_ISTANZA"), row.getOLD_ISTANZAField())));
            row.setISTANZA(Utils.convertToString(ds.parse(record.get("ISTANZA"), row.getISTANZAField())));
            row.setCAAC_DESC_Hidden(Utils.convertToString(ds.parse(record.get("CAAC_DESC"), row.getCAAC_DESC_HiddenField())));
            row.setACCESSO_SE(Utils.convertToString(ds.parse(record.get("ACCESSO_SE"), row.getACCESSO_SEField())));
            row.setACCESSO_SE_DESC(Utils.convertToString(ds.parse(record.get("ACCESSO_SE_DESC"), row.getACCESSO_SE_DESCField())));
            row.setACCESSO(Utils.convertToString(ds.parse(record.get("ACCESSO"), row.getACCESSOField())));
            row.setTRACCIA(Utils.convertToString(ds.parse(record.get("TRACCIA"), row.getTRACCIAField())));
            row.setGIORNI_TRACCIA(Utils.convertToLong(ds.parse(record.get("GIORNI_TRACCIA"), row.getGIORNI_TRACCIAField())));
            row.setNUM_FILE_ARCHIVIATI(Utils.convertToLong(ds.parse(record.get("NUM_FILE_ARCHIVIATI"), row.getNUM_FILE_ARCHIVIATIField())));
            row.setDIM_FILE_ARCHIVIATI(Utils.convertToDouble(ds.parse(record.get("DIM_FILE_ARCHIVIATI"), row.getDIM_FILE_ARCHIVIATIField())));
            row.setSPOSTA_FILE_ARCH(Utils.convertToLong(ds.parse(record.get("SPOSTA_FILE_ARCH"), row.getSPOSTA_FILE_ARCHField())));
            row.setARCHIVIAZIONE_TRACCIA(Utils.convertToString(ds.parse(record.get("ARCHIVIAZIONE_TRACCIA"), row.getARCHIVIAZIONE_TRACCIAField())));
            row.setLABEL_ARCH(Utils.convertToString(ds.parse(record.get("LABEL_ARCH"), row.getLABEL_ARCHField())));
            row.setDISLOCAZIONE_TRACCIA(Utils.convertToString(ds.parse(record.get("DISLOCAZIONE_TRACCIA"), row.getDISLOCAZIONE_TRACCIAField())));
            row.setINPUT_ARCH_CLASS(Utils.convertToString(ds.parse(record.get("INPUT_ARCH_CLASS"), row.getINPUT_ARCH_CLASSField())));
            row.setCKB_ARCH_CLASS(Utils.convertToString(ds.parse(record.get("CKB_ARCH_CLASS"), row.getCKB_ARCH_CLASSField())));
            row.setDISLOCAZIONE_TRACCIA_OLD(Utils.convertToString(ds.parse(record.get("DISLOCAZIONE_TRACCIA_OLD"), row.getDISLOCAZIONE_TRACCIA_OLDField())));
            row.setSLEEP(Utils.convertToLong(ds.parse(record.get("SLEEP"), row.getSLEEPField())));
            row.setVALIDITA_PASSWORD(Utils.convertToLong(ds.parse(record.get("VALIDITA_PASSWORD"), row.getVALIDITA_PASSWORDField())));
            row.setTENTATIVI_PASSWORD(Utils.convertToLong(ds.parse(record.get("TENTATIVI_PASSWORD"), row.getTENTATIVI_PASSWORDField())));
            row.setMOD_PWD_PRIMO_ACCESSO(Utils.convertToString(ds.parse(record.get("MOD_PWD_PRIMO_ACCESSO"), row.getMOD_PWD_PRIMO_ACCESSOField())));
            row.setMIN_LUNGHEZZA_PWD(Utils.convertToLong(ds.parse(record.get("MIN_LUNGHEZZA_PWD"), row.getMIN_LUNGHEZZA_PWDField())));
            row.setMIN_LUNGHEZZA_PWD_OLD(Utils.convertToLong(ds.parse(record.get("MIN_LUNGHEZZA_PWD_OLD"), row.getMIN_LUNGHEZZA_PWD_OLDField())));
            row.setAMMESSI_CAR_SPECIALI_PWD(Utils.convertToString(ds.parse(record.get("AMMESSI_CAR_SPECIALI_PWD"), row.getAMMESSI_CAR_SPECIALI_PWDField())));
            row.setAMMESSI_CAR_SPECIALI_PWD_OLD(Utils.convertToString(ds.parse(record.get("AMMESSI_CAR_SPECIALI_PWD_OLD"), row.getAMMESSI_CAR_SPECIALI_PWD_OLDField())));
            row.setNUMERI_OBB_PWD(Utils.convertToString(ds.parse(record.get("NUMERI_OBB_PWD"), row.getNUMERI_OBB_PWDField())));
            row.setNUMERI_OBB_PWD_OLD(Utils.convertToString(ds.parse(record.get("NUMERI_OBB_PWD_OLD"), row.getNUMERI_OBB_PWD_OLDField())));
            row.setGG_PRIMA_RIUTILIZZO_PWD(Utils.convertToLong(ds.parse(record.get("GG_PRIMA_RIUTILIZZO_PWD"), row.getGG_PRIMA_RIUTILIZZO_PWDField())));
            row.setGG_PRIMA_RIUTILIZZO_PWD_OLD(Utils.convertToLong(ds.parse(record.get("GG_PRIMA_RIUTILIZZO_PWD_OLD"), row.getGG_PRIMA_RIUTILIZZO_PWD_OLDField())));
            row.setLDAP(Utils.convertToString(ds.parse(record.get("LDAP"), row.getLDAPField())));
            row.setSINGLE_SIGN_ON(Utils.convertToString(ds.parse(record.get("SINGLE_SIGN_ON"), row.getSINGLE_SIGN_ONField())));
            row.setDESC_DETTAGLI_LABEL(Utils.convertToString(ds.parse(record.get("DESC_DETTAGLI"), row.getDESC_DETTAGLI_LABELField())));
            row.setDESC_DETTAGLI(Utils.convertToString(ds.parse(record.get("DESC_DETTAGLI"), row.getDESC_DETTAGLIField())));
        }
//End loadDataBind

//End of load @38-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @38-6EEEDD03
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call CARATTERISTICHE_ACCESSO_PM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) postINSERT_UPDATE.getObjectValue() ) ) postINSERT_UPDATE.setValue( "" );
            command.addParameter( "P_INSERT_UPDATE", postINSERT_UPDATE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "OLD_CAAC_ID", postOLD_CAAC_ID, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postOLD_PROGETTO.getObjectValue() ) ) postOLD_PROGETTO.setValue( "" );
            command.addParameter( "OLD_PROGETTO", postOLD_PROGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postOLD_ISTANZA.getObjectValue() ) ) postOLD_ISTANZA.setValue( "" );
            command.addParameter( "OLD_ISTANZA", postOLD_ISTANZA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postOLD_MODULO.getObjectValue() ) ) postOLD_MODULO.setValue( "" );
            command.addParameter( "OLD_MODULO", postOLD_MODULO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postOLD_UTENTE.getObjectValue() ) ) postOLD_UTENTE.setValue( "" );
            command.addParameter( "OLD_UTENTE", postOLD_UTENTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "NEW_CAAC_ID", postCAAC_ID, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postTIPO_ACCESSO.getObjectValue() ) ) postTIPO_ACCESSO.setValue( "" );
            command.addParameter( "NEW_TIPO_ACCESSO", postTIPO_ACCESSO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPROGETTO.getObjectValue() ) ) postPROGETTO.setValue( "" );
            command.addParameter( "NEW_PROGETTO", postPROGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postISTANZA.getObjectValue() ) ) postISTANZA.setValue( "" );
            command.addParameter( "NEW_ISTANZA", postISTANZA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
            command.addParameter( "NEW_MODULO", postMODULO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postUTENTE.getObjectValue() ) ) postUTENTE.setValue( "" );
            command.addParameter( "NEW_UTENTE", postUTENTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getACCESSO()) ) row.setACCESSO( "" );
            command.addParameter( "NEW_ACCESSO", row.getACCESSOField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getACCESSO_SE()) ) row.setACCESSO_SE( "" );
            command.addParameter( "NEW_ACCESSO_SE", row.getACCESSO_SEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getTRACCIA()) ) row.setTRACCIA( "" );
            command.addParameter( "NEW_TRACCIA", row.getTRACCIAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "NEW_GIORNI_TRACCIA", postGIORNI_TRACCIA, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "NEW_TENTATIVI_PASSWORD", postTENTATIVI_PASSWORD, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "NEW_VALIDITA_PASSWORD", postVALIDITA_PASSWORD, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "NEW_SLEEP", urlSLEEP, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postSINGLE_SIGN_ON.getObjectValue() ) ) postSINGLE_SIGN_ON.setValue( "" );
            command.addParameter( "NEW_SINGLE_SIGN_ON", postSINGLE_SIGN_ON, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getLDAP()) ) row.setLDAP( "" );
            command.addParameter( "NEW_LDAP", row.getLDAPField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "NEW_MIN_LUNGHEZZA_PWD", postMIN_LUNGHEZZA_PWD, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getMOD_PWD_PRIMO_ACCESSO()) ) row.setMOD_PWD_PRIMO_ACCESSO( "" );
            command.addParameter( "NEW_MOD_PWD_PRIMO_ACCESSO", row.getMOD_PWD_PRIMO_ACCESSOField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getARCHIVIAZIONE_TRACCIA()) ) row.setARCHIVIAZIONE_TRACCIA( "" );
            command.addParameter( "NEW_ARCHIVIAZIONE_TRACCIA", row.getARCHIVIAZIONE_TRACCIAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDISLOCAZIONE_TRACCIA.getObjectValue() ) ) postDISLOCAZIONE_TRACCIA.setValue( "" );
            command.addParameter( "NEW_DISLOCAZIONE_TRACCIA", postDISLOCAZIONE_TRACCIA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getAMMESSI_CAR_SPECIALI_PWD()) ) row.setAMMESSI_CAR_SPECIALI_PWD( "" );
            command.addParameter( "NEW_CAR_SPECIALI_PWD", row.getAMMESSI_CAR_SPECIALI_PWDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getNUMERI_OBB_PWD()) ) row.setNUMERI_OBB_PWD( "" );
            command.addParameter( "NEW_NUM_OBB_PWD", row.getNUMERI_OBB_PWDField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( row.getGG_PRIMA_RIUTILIZZO_PWD() == null ) row.setGG_PRIMA_RIUTILIZZO_PWD( null );
            command.addParameter( "NEW_GG_PRIMA_RIUTILIZZO_PWC", row.getGG_PRIMA_RIUTILIZZO_PWDField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( postSPOSTA_FILE_ARCH.getObjectValue() == null ) postSPOSTA_FILE_ARCH.setValue( 0 );
            command.addParameter( "P_MOVE_FILE", postSPOSTA_FILE_ARCH, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @38-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @38-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @38-51FDC758
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM CARATTERISTICHE_ACCESSO");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1;
            if ( postOLD_CAAC_ID.getObjectValue() == null ) {
                where1 = WhereParams.rawOperation( "CAAC_ID", FieldOperation.EQUAL.getNullRelation(), new TextField("null", "null", "null") , null, ds );
            } else {
                where1 = WhereParams.rawOperation( "CAAC_ID", FieldOperation.EQUAL, postOLD_CAAC_ID, null, ds );
            }
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

//deleteDataBound @38-5B959F17
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

//End of delete @38-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @38-4654F32B
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "INSERT_UPDATE".equals(name) && "post".equals(prefix) ) {
                param = postINSERT_UPDATE;
            } else if ( "INSERT_UPDATE".equals(name) && prefix == null ) {
                param = postINSERT_UPDATE;
            }
            if ( "OLD_CAAC_ID".equals(name) && "post".equals(prefix) ) {
                param = postOLD_CAAC_ID;
            } else if ( "OLD_CAAC_ID".equals(name) && prefix == null ) {
                param = postOLD_CAAC_ID;
            }
            if ( "OLD_PROGETTO".equals(name) && "post".equals(prefix) ) {
                param = postOLD_PROGETTO;
            } else if ( "OLD_PROGETTO".equals(name) && prefix == null ) {
                param = postOLD_PROGETTO;
            }
            if ( "OLD_ISTANZA".equals(name) && "post".equals(prefix) ) {
                param = postOLD_ISTANZA;
            } else if ( "OLD_ISTANZA".equals(name) && prefix == null ) {
                param = postOLD_ISTANZA;
            }
            if ( "OLD_MODULO".equals(name) && "post".equals(prefix) ) {
                param = postOLD_MODULO;
            } else if ( "OLD_MODULO".equals(name) && prefix == null ) {
                param = postOLD_MODULO;
            }
            if ( "OLD_UTENTE".equals(name) && "post".equals(prefix) ) {
                param = postOLD_UTENTE;
            } else if ( "OLD_UTENTE".equals(name) && prefix == null ) {
                param = postOLD_UTENTE;
            }
            if ( "CAAC_ID".equals(name) && "post".equals(prefix) ) {
                param = postCAAC_ID;
            } else if ( "CAAC_ID".equals(name) && prefix == null ) {
                param = postCAAC_ID;
            }
            if ( "TIPO_ACCESSO".equals(name) && "post".equals(prefix) ) {
                param = postTIPO_ACCESSO;
            } else if ( "TIPO_ACCESSO".equals(name) && prefix == null ) {
                param = postTIPO_ACCESSO;
            }
            if ( "PROGETTO".equals(name) && "post".equals(prefix) ) {
                param = postPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = postPROGETTO;
            }
            if ( "ISTANZA".equals(name) && "post".equals(prefix) ) {
                param = postISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = postISTANZA;
            }
            if ( "MODULO".equals(name) && "post".equals(prefix) ) {
                param = postMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = postMODULO;
            }
            if ( "UTENTE".equals(name) && "post".equals(prefix) ) {
                param = postUTENTE;
            } else if ( "UTENTE".equals(name) && prefix == null ) {
                param = postUTENTE;
            }
            if ( "ACCESSO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlACCESSO;
            } else if ( "ACCESSO".equals(name) && prefix == null ) {
                param = ctrlACCESSO;
            }
            if ( "ACCESSO_SE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlACCESSO_SE;
            } else if ( "ACCESSO_SE".equals(name) && prefix == null ) {
                param = ctrlACCESSO_SE;
            }
            if ( "TRACCIA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlTRACCIA;
            } else if ( "TRACCIA".equals(name) && prefix == null ) {
                param = ctrlTRACCIA;
            }
            if ( "GIORNI_TRACCIA".equals(name) && "post".equals(prefix) ) {
                param = postGIORNI_TRACCIA;
            } else if ( "GIORNI_TRACCIA".equals(name) && prefix == null ) {
                param = postGIORNI_TRACCIA;
            }
            if ( "TENTATIVI_PASSWORD".equals(name) && "post".equals(prefix) ) {
                param = postTENTATIVI_PASSWORD;
            } else if ( "TENTATIVI_PASSWORD".equals(name) && prefix == null ) {
                param = postTENTATIVI_PASSWORD;
            }
            if ( "VALIDITA_PASSWORD".equals(name) && "post".equals(prefix) ) {
                param = postVALIDITA_PASSWORD;
            } else if ( "VALIDITA_PASSWORD".equals(name) && prefix == null ) {
                param = postVALIDITA_PASSWORD;
            }
            if ( "SLEEP".equals(name) && "url".equals(prefix) ) {
                param = urlSLEEP;
            } else if ( "SLEEP".equals(name) && prefix == null ) {
                param = urlSLEEP;
            }
            if ( "SINGLE_SIGN_ON".equals(name) && "post".equals(prefix) ) {
                param = postSINGLE_SIGN_ON;
            } else if ( "SINGLE_SIGN_ON".equals(name) && prefix == null ) {
                param = postSINGLE_SIGN_ON;
            }
            if ( "LDAP".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlLDAP;
            } else if ( "LDAP".equals(name) && prefix == null ) {
                param = ctrlLDAP;
            }
            if ( "MIN_LUNGHEZZA_PWD".equals(name) && "post".equals(prefix) ) {
                param = postMIN_LUNGHEZZA_PWD;
            } else if ( "MIN_LUNGHEZZA_PWD".equals(name) && prefix == null ) {
                param = postMIN_LUNGHEZZA_PWD;
            }
            if ( "MOD_PWD_PRIMO_ACCESSO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlMOD_PWD_PRIMO_ACCESSO;
            } else if ( "MOD_PWD_PRIMO_ACCESSO".equals(name) && prefix == null ) {
                param = ctrlMOD_PWD_PRIMO_ACCESSO;
            }
            if ( "ARCHIVIAZIONE_TRACCIA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlARCHIVIAZIONE_TRACCIA;
            } else if ( "ARCHIVIAZIONE_TRACCIA".equals(name) && prefix == null ) {
                param = ctrlARCHIVIAZIONE_TRACCIA;
            }
            if ( "DISLOCAZIONE_TRACCIA".equals(name) && "post".equals(prefix) ) {
                param = postDISLOCAZIONE_TRACCIA;
            } else if ( "DISLOCAZIONE_TRACCIA".equals(name) && prefix == null ) {
                param = postDISLOCAZIONE_TRACCIA;
            }
            if ( "AMMESSI_CAR_SPECIALI_PWD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlAMMESSI_CAR_SPECIALI_PWD;
            } else if ( "AMMESSI_CAR_SPECIALI_PWD".equals(name) && prefix == null ) {
                param = ctrlAMMESSI_CAR_SPECIALI_PWD;
            }
            if ( "NUMERI_OBB_PWD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlNUMERI_OBB_PWD;
            } else if ( "NUMERI_OBB_PWD".equals(name) && prefix == null ) {
                param = ctrlNUMERI_OBB_PWD;
            }
            if ( "GG_PRIMA_RIUTILIZZO_PWD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlGG_PRIMA_RIUTILIZZO_PWD;
            } else if ( "GG_PRIMA_RIUTILIZZO_PWD".equals(name) && prefix == null ) {
                param = ctrlGG_PRIMA_RIUTILIZZO_PWD;
            }
            if ( "SPOSTA_FILE_ARCH".equals(name) && "post".equals(prefix) ) {
                param = postSPOSTA_FILE_ARCH;
            } else if ( "SPOSTA_FILE_ARCH".equals(name) && prefix == null ) {
                param = postSPOSTA_FILE_ARCH;
            }
            if ( "UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE;
            } else if ( "UTENTE".equals(name) && prefix == null ) {
                param = urlUTENTE;
            }
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "TIPO_ACCESSO".equals(name) && "url".equals(prefix) ) {
                param = urlTIPO_ACCESSO;
            } else if ( "TIPO_ACCESSO".equals(name) && prefix == null ) {
                param = urlTIPO_ACCESSO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @38-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @38-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @38-305A023C
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

//fireBeforeExecuteSelectEvent @38-D00ACF95
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

//fireAfterExecuteSelectEvent @38-3BAD39CE
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

//fireBeforeBuildInsertEvent @38-FBA08B71
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

//fireBeforeExecuteInsertEvent @38-47AFA6A5
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

//fireAfterExecuteInsertEvent @38-E9CE95AE
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

//fireBeforeBuildSelectEvent @38-2405BE8B
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

//fireBeforeExecuteSelectEvent @38-E9DFF86B
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

//fireAfterExecuteSelectEvent @38-580A2987
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

//fireBeforeBuildSelectEvent @38-D021D0EA
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

//fireBeforeExecuteDeleteEvent @38-DD540FBB
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

//fireAfterExecuteDeleteEvent @38-2A6E2049
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

//class DataObject Tail @38-ED3F53A4
} // End of class DS
//End class DataObject Tail

