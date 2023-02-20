//AD4_SOGGETTO DataSource @6-ACB6CA56
package ad4web.AD4SoggettoInclusa;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_SOGGETTO DataSource

//class DataObject Header @6-255A1D66
public class AD4_SOGGETTODataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-C6661B70
    

    TextField urlSOGGETTO = new TextField(null, null);
    
    TextField postCOGNOME = new TextField(null, null);
    
    TextField postNOME = new TextField(null, null);
    
    TextField postSESSO = new TextField(null, null);
    
    TextField postP_DATA_NAS = new TextField(null, null);
    
    LongField postCOMUNE_NASCITA = new LongField(null, null);
    
    LongField postPROVINCIA_NAS = new LongField(null, null);
    
    TextField postCODICE_FISCALE = new TextField(null, null);
    
    LongField postCOMUNE = new LongField(null, null);
    
    LongField postPROVINCIA = new LongField(null, null);
    
    TextField postCAP = new TextField(null, null);
    
    TextField postINDIRIZZO_COMPLETO = new TextField(null, null);
    
    TextField postINDIRIZZO_WEB = new TextField(null, null);
    
    TextField postTELEFONO = new TextField(null, null);
    
    TextField postFAX = new TextField(null, null);
    
    TextField postNOTE = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    TextField postDATA_NASCITA = new TextField(null, null);
    
    TextField urlPROVINCIA = new TextField(null, null);
    
    TextField urlSTATO = new TextField(null, null);
    
    TextField urlPROVINCIA_NAS = new TextField(null, null);
    
    TextField urlNOME = new TextField(null, null);
    
    TextField urlSESSO = new TextField(null, null);
    
    TextField urlDATA_NASCITA = new TextField(null, null);
    
    TextField urlCOMUNE_NASCITA = new TextField(null, null);
    
    TextField urlCODICE_FISCALE = new TextField(null, null);
    
    TextField urlINDIRIZZO_COMPLETO = new TextField(null, null);
    
    TextField urlCAP = new TextField(null, null);
    
    TextField urlCOMUNE = new TextField(null, null);
    
    TextField urlTELEFONO = new TextField(null, null);
    
    TextField urlFAX = new TextField(null, null);
    
    TextField urlINDIRIZZO_WEB = new TextField(null, null);
    
    TextField urlNOTE = new TextField(null, null);
    
    TextField urlISLISTBOX = new TextField(null, null);
    
    TextField urlCOGNOME = new TextField(null, null);
    
    TextField sesAD4SOGG = new TextField(null, null);
    

    private AD4_SOGGETTORow row = new AD4_SOGGETTORow();

//End attributes of DataObject

//properties of DataObject @6-922C9979

    public void  setUrlSOGGETTO( String param ) {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setUrlSOGGETTO( Object param ) {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setUrlSOGGETTO( Object param, Format ignore ) {
        this.urlSOGGETTO.setValue( param );
    }

    public void  setPostCOGNOME( String param ) {
        this.postCOGNOME.setValue( param );
    }

    public void  setPostCOGNOME( Object param ) {
        this.postCOGNOME.setValue( param );
    }

    public void  setPostCOGNOME( Object param, Format ignore ) {
        this.postCOGNOME.setValue( param );
    }

    public void  setPostNOME( String param ) {
        this.postNOME.setValue( param );
    }

    public void  setPostNOME( Object param ) {
        this.postNOME.setValue( param );
    }

    public void  setPostNOME( Object param, Format ignore ) {
        this.postNOME.setValue( param );
    }

    public void  setPostSESSO( String param ) {
        this.postSESSO.setValue( param );
    }

    public void  setPostSESSO( Object param ) {
        this.postSESSO.setValue( param );
    }

    public void  setPostSESSO( Object param, Format ignore ) {
        this.postSESSO.setValue( param );
    }

    public void  setPostP_DATA_NAS( String param ) {
        this.postP_DATA_NAS.setValue( param );
    }

    public void  setPostP_DATA_NAS( Object param ) {
        this.postP_DATA_NAS.setValue( param );
    }

    public void  setPostP_DATA_NAS( Object param, Format ignore ) {
        this.postP_DATA_NAS.setValue( param );
    }

    public void  setPostCOMUNE_NASCITA( long param ) {
        this.postCOMUNE_NASCITA.setValue( param );
    }

    public void  setPostCOMUNE_NASCITA( long param, Format ignore ) throws java.text.ParseException {
        this.postCOMUNE_NASCITA.setValue( param );
    }

    public void  setPostCOMUNE_NASCITA( Object param, Format format ) throws java.text.ParseException {
        this.postCOMUNE_NASCITA.setValue( param, format );
    }

    public void  setPostCOMUNE_NASCITA( Long param ) {
        this.postCOMUNE_NASCITA.setValue( param );
    }

    public void  setPostPROVINCIA_NAS( long param ) {
        this.postPROVINCIA_NAS.setValue( param );
    }

    public void  setPostPROVINCIA_NAS( long param, Format ignore ) throws java.text.ParseException {
        this.postPROVINCIA_NAS.setValue( param );
    }

    public void  setPostPROVINCIA_NAS( Object param, Format format ) throws java.text.ParseException {
        this.postPROVINCIA_NAS.setValue( param, format );
    }

    public void  setPostPROVINCIA_NAS( Long param ) {
        this.postPROVINCIA_NAS.setValue( param );
    }

    public void  setPostCODICE_FISCALE( String param ) {
        this.postCODICE_FISCALE.setValue( param );
    }

    public void  setPostCODICE_FISCALE( Object param ) {
        this.postCODICE_FISCALE.setValue( param );
    }

    public void  setPostCODICE_FISCALE( Object param, Format ignore ) {
        this.postCODICE_FISCALE.setValue( param );
    }

    public void  setPostCOMUNE( long param ) {
        this.postCOMUNE.setValue( param );
    }

    public void  setPostCOMUNE( long param, Format ignore ) throws java.text.ParseException {
        this.postCOMUNE.setValue( param );
    }

    public void  setPostCOMUNE( Object param, Format format ) throws java.text.ParseException {
        this.postCOMUNE.setValue( param, format );
    }

    public void  setPostCOMUNE( Long param ) {
        this.postCOMUNE.setValue( param );
    }

    public void  setPostPROVINCIA( long param ) {
        this.postPROVINCIA.setValue( param );
    }

    public void  setPostPROVINCIA( long param, Format ignore ) throws java.text.ParseException {
        this.postPROVINCIA.setValue( param );
    }

    public void  setPostPROVINCIA( Object param, Format format ) throws java.text.ParseException {
        this.postPROVINCIA.setValue( param, format );
    }

    public void  setPostPROVINCIA( Long param ) {
        this.postPROVINCIA.setValue( param );
    }

    public void  setPostCAP( String param ) {
        this.postCAP.setValue( param );
    }

    public void  setPostCAP( Object param ) {
        this.postCAP.setValue( param );
    }

    public void  setPostCAP( Object param, Format ignore ) {
        this.postCAP.setValue( param );
    }

    public void  setPostINDIRIZZO_COMPLETO( String param ) {
        this.postINDIRIZZO_COMPLETO.setValue( param );
    }

    public void  setPostINDIRIZZO_COMPLETO( Object param ) {
        this.postINDIRIZZO_COMPLETO.setValue( param );
    }

    public void  setPostINDIRIZZO_COMPLETO( Object param, Format ignore ) {
        this.postINDIRIZZO_COMPLETO.setValue( param );
    }

    public void  setPostINDIRIZZO_WEB( String param ) {
        this.postINDIRIZZO_WEB.setValue( param );
    }

    public void  setPostINDIRIZZO_WEB( Object param ) {
        this.postINDIRIZZO_WEB.setValue( param );
    }

    public void  setPostINDIRIZZO_WEB( Object param, Format ignore ) {
        this.postINDIRIZZO_WEB.setValue( param );
    }

    public void  setPostTELEFONO( String param ) {
        this.postTELEFONO.setValue( param );
    }

    public void  setPostTELEFONO( Object param ) {
        this.postTELEFONO.setValue( param );
    }

    public void  setPostTELEFONO( Object param, Format ignore ) {
        this.postTELEFONO.setValue( param );
    }

    public void  setPostFAX( String param ) {
        this.postFAX.setValue( param );
    }

    public void  setPostFAX( Object param ) {
        this.postFAX.setValue( param );
    }

    public void  setPostFAX( Object param, Format ignore ) {
        this.postFAX.setValue( param );
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

    public void  setSesUtente( String param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param, Format ignore ) {
        this.sesUtente.setValue( param );
    }

    public void  setPostDATA_NASCITA( String param ) {
        this.postDATA_NASCITA.setValue( param );
    }

    public void  setPostDATA_NASCITA( Object param ) {
        this.postDATA_NASCITA.setValue( param );
    }

    public void  setPostDATA_NASCITA( Object param, Format ignore ) {
        this.postDATA_NASCITA.setValue( param );
    }

    public void  setUrlPROVINCIA( String param ) {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlPROVINCIA( Object param ) {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlPROVINCIA( Object param, Format ignore ) {
        this.urlPROVINCIA.setValue( param );
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

    public void  setUrlPROVINCIA_NAS( String param ) {
        this.urlPROVINCIA_NAS.setValue( param );
    }

    public void  setUrlPROVINCIA_NAS( Object param ) {
        this.urlPROVINCIA_NAS.setValue( param );
    }

    public void  setUrlPROVINCIA_NAS( Object param, Format ignore ) {
        this.urlPROVINCIA_NAS.setValue( param );
    }

    public void  setUrlNOME( String param ) {
        this.urlNOME.setValue( param );
    }

    public void  setUrlNOME( Object param ) {
        this.urlNOME.setValue( param );
    }

    public void  setUrlNOME( Object param, Format ignore ) {
        this.urlNOME.setValue( param );
    }

    public void  setUrlSESSO( String param ) {
        this.urlSESSO.setValue( param );
    }

    public void  setUrlSESSO( Object param ) {
        this.urlSESSO.setValue( param );
    }

    public void  setUrlSESSO( Object param, Format ignore ) {
        this.urlSESSO.setValue( param );
    }

    public void  setUrlDATA_NASCITA( String param ) {
        this.urlDATA_NASCITA.setValue( param );
    }

    public void  setUrlDATA_NASCITA( Object param ) {
        this.urlDATA_NASCITA.setValue( param );
    }

    public void  setUrlDATA_NASCITA( Object param, Format ignore ) {
        this.urlDATA_NASCITA.setValue( param );
    }

    public void  setUrlCOMUNE_NASCITA( String param ) {
        this.urlCOMUNE_NASCITA.setValue( param );
    }

    public void  setUrlCOMUNE_NASCITA( Object param ) {
        this.urlCOMUNE_NASCITA.setValue( param );
    }

    public void  setUrlCOMUNE_NASCITA( Object param, Format ignore ) {
        this.urlCOMUNE_NASCITA.setValue( param );
    }

    public void  setUrlCODICE_FISCALE( String param ) {
        this.urlCODICE_FISCALE.setValue( param );
    }

    public void  setUrlCODICE_FISCALE( Object param ) {
        this.urlCODICE_FISCALE.setValue( param );
    }

    public void  setUrlCODICE_FISCALE( Object param, Format ignore ) {
        this.urlCODICE_FISCALE.setValue( param );
    }

    public void  setUrlINDIRIZZO_COMPLETO( String param ) {
        this.urlINDIRIZZO_COMPLETO.setValue( param );
    }

    public void  setUrlINDIRIZZO_COMPLETO( Object param ) {
        this.urlINDIRIZZO_COMPLETO.setValue( param );
    }

    public void  setUrlINDIRIZZO_COMPLETO( Object param, Format ignore ) {
        this.urlINDIRIZZO_COMPLETO.setValue( param );
    }

    public void  setUrlCAP( String param ) {
        this.urlCAP.setValue( param );
    }

    public void  setUrlCAP( Object param ) {
        this.urlCAP.setValue( param );
    }

    public void  setUrlCAP( Object param, Format ignore ) {
        this.urlCAP.setValue( param );
    }

    public void  setUrlCOMUNE( String param ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( Object param ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( Object param, Format ignore ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlTELEFONO( String param ) {
        this.urlTELEFONO.setValue( param );
    }

    public void  setUrlTELEFONO( Object param ) {
        this.urlTELEFONO.setValue( param );
    }

    public void  setUrlTELEFONO( Object param, Format ignore ) {
        this.urlTELEFONO.setValue( param );
    }

    public void  setUrlFAX( String param ) {
        this.urlFAX.setValue( param );
    }

    public void  setUrlFAX( Object param ) {
        this.urlFAX.setValue( param );
    }

    public void  setUrlFAX( Object param, Format ignore ) {
        this.urlFAX.setValue( param );
    }

    public void  setUrlINDIRIZZO_WEB( String param ) {
        this.urlINDIRIZZO_WEB.setValue( param );
    }

    public void  setUrlINDIRIZZO_WEB( Object param ) {
        this.urlINDIRIZZO_WEB.setValue( param );
    }

    public void  setUrlINDIRIZZO_WEB( Object param, Format ignore ) {
        this.urlINDIRIZZO_WEB.setValue( param );
    }

    public void  setUrlNOTE( String param ) {
        this.urlNOTE.setValue( param );
    }

    public void  setUrlNOTE( Object param ) {
        this.urlNOTE.setValue( param );
    }

    public void  setUrlNOTE( Object param, Format ignore ) {
        this.urlNOTE.setValue( param );
    }

    public void  setUrlISLISTBOX( String param ) {
        this.urlISLISTBOX.setValue( param );
    }

    public void  setUrlISLISTBOX( Object param ) {
        this.urlISLISTBOX.setValue( param );
    }

    public void  setUrlISLISTBOX( Object param, Format ignore ) {
        this.urlISLISTBOX.setValue( param );
    }

    public void  setUrlCOGNOME( String param ) {
        this.urlCOGNOME.setValue( param );
    }

    public void  setUrlCOGNOME( Object param ) {
        this.urlCOGNOME.setValue( param );
    }

    public void  setUrlCOGNOME( Object param, Format ignore ) {
        this.urlCOGNOME.setValue( param );
    }

    public void  setSesAD4SOGG( String param ) {
        this.sesAD4SOGG.setValue( param );
    }

    public void  setSesAD4SOGG( Object param ) {
        this.sesAD4SOGG.setValue( param );
    }

    public void  setSesAD4SOGG( Object param, Format ignore ) {
        this.sesAD4SOGG.setValue( param );
    }

    public AD4_SOGGETTORow getRow() {
        return row;
    }

    public void setRow( AD4_SOGGETTORow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-13A2DBFE
    public AD4_SOGGETTODataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new AD4_SOGGETTODataObjectHandler() );
    }
//End constructor

//load @6-F46FFB2C
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT SOGG.SOGGETTO, "
                    + "       NVL('{NOME}',AD4_SOGGETTO.GET_NOME(SOGGETTO,'Y',0)) NOME, "
                    + "       NVL('{COGNOME}',AD4_SOGGETTO.GET_COGNOME(SOGGETTO)) COGNOME, "
                    + "       NVL('{SESSO}',SOGG.SESSO) SESSO, "
                    + "       DECODE('{DATA_NASCITA}','',SOGG.DATA_NASCITA,TO_DATE('{DATA_NASCITA}','DD/MM/YYYY')) DATA_NASCITA, "
                    + "       DECODE('{ISLISTBOX}','Y',TO_NUMBER('{STATO}'),100) STATO_TERRITORIO, "
                    + "       DECODE('{ISLISTBOX}','Y',TO_NUMBER('{PROVINCIA_NAS}'),SOGG.PROVINCIA_NAS) PROVINCIA_NAS, "
                    + "       DECODE('{ISLISTBOX}','Y',to_number('{COMUNE_NAS}'),SOGG.COMUNE_NAS) COMUNE_NAS, "
                    + "       NVL('{CODICE_FISCALE}',SOGG.CODICE_FISCALE) CODICE_FISCALE, "
                    + "       NVL('{INDIRIZZO}',SOGG.INDIRIZZO) INDIRIZZO, "
                    + "       NVL('{CAP}',SOGG.CAP) CAP, "
                    + "       decode('{COMUNE}','',SOGG.COMUNE,'NO',to_number(null),TO_NUMBER('{COMUNE}')) COMUNE, "
                    + "       DECODE('{ISLISTBOX}','Y',TO_NUMBER('{PROVINCIA}'),SOGG.PROVINCIA) PROVINCIA, "
                    + "       NVL('{TELEFONO}',SOGG.TELEFONO) TELEFONO, "
                    + "       NVL('{FAX}',SOGG.FAX) FAX, "
                    + "       NVL('{INDIRIZZO_WEB}',SOGG.INDIRIZZO_WEB) INDIRIZZO_WEB, "
                    + "       NVL('{NOTE}',SOGG.NOTE) NOTE, "
                    + "       SOGG.UTENTE_AGGIORNAMENTO, "
                    + "       SOGG.DATA_AGGIORNAMENTO "
                    + "  FROM SOGGETTI SOGG,  "
                    + "COMUNI COMU "
                    + " WHERE SOGG.SOGGETTO = NVL(TO_NUMBER('{SOGGETTO}'),TO_NUMBER('{AD4SOGG}')) "
                    + "   AND COMU.PROVINCIA_STATO(+) = SOGG.PROVINCIA_NAS "
                    + "   AND COMU.COMUNE(+) = nvl(SOGG.COMUNE_NAS,0) "
                    + "   AND NVL(SOGG.PROVINCIA_NAS,1) <= 199 "
                    + "UNION "
                    + "SELECT SOGG.SOGGETTO, "
                    + "       AD4_SOGGETTO.GET_NOME(SOGGETTO,'Y',0) NOME, "
                    + "       AD4_SOGGETTO.GET_COGNOME(SOGGETTO) COGNOME, "
                    + "       SOGG.SESSO, "
                    + "       SOGG.DATA_NASCITA, "
                    + "       DECODE('{ISLISTBOX}','Y',TO_NUMBER('{STATO}'),COMU.PROVINCIA_STATO) STATO_TERRITORIO, "
                    + "       DECODE('{ISLISTBOX}','Y',TO_NUMBER('{PROVINCIA_NAS}'),SOGG.PROVINCIA_NAS) PROVINCIA_NAS, "
                    + "       DECODE('{ISLISTBOX}','Y',to_number('{COMUNE_NAS}'),SOGG.COMUNE_NAS) COMUNE_NAS, "
                    + "       SOGG.CODICE_FISCALE, "
                    + "       SOGG.INDIRIZZO, "
                    + "       SOGG.CAP, "
                    + "       SOGG.COMUNE, "
                    + "       NVL(TO_NUMBER('{PROVINCIA}'),SOGG.PROVINCIA) PROVINCIA, "
                    + "       SOGG.TELEFONO, "
                    + "       SOGG.FAX, "
                    + "       SOGG.INDIRIZZO_WEB, "
                    + "       SOGG.NOTE, "
                    + "       SOGG.UTENTE_AGGIORNAMENTO, "
                    + "       SOGG.DATA_AGGIORNAMENTO "
                    + "  FROM SOGGETTI SOGG, COMUNI COMU "
                    + " WHERE SOGG.SOGGETTO = NVL(TO_NUMBER('{SOGGETTO}'),TO_NUMBER('{AD4SOGG}')) "
                    + "   AND COMU.PROVINCIA_STATO(+) = SOGG.PROVINCIA_NAS "
                    + "   AND COMU.COMUNE(+) = SOGG.COMUNE_NAS "
                    + "   AND SOGG.PROVINCIA_NAS > 199" );
        if ( StringUtils.isEmpty( (String) urlSOGGETTO.getObjectValue() ) ) urlSOGGETTO.setValue( "" );
        command.addParameter( "SOGGETTO", urlSOGGETTO, null );
        if ( StringUtils.isEmpty( (String) urlPROVINCIA.getObjectValue() ) ) urlPROVINCIA.setValue( "" );
        command.addParameter( "PROVINCIA", urlPROVINCIA, null );
        if ( StringUtils.isEmpty( (String) urlSTATO.getObjectValue() ) ) urlSTATO.setValue( "" );
        command.addParameter( "STATO", urlSTATO, null );
        if ( StringUtils.isEmpty( (String) urlPROVINCIA_NAS.getObjectValue() ) ) urlPROVINCIA_NAS.setValue( "" );
        command.addParameter( "PROVINCIA_NAS", urlPROVINCIA_NAS, null );
        if ( StringUtils.isEmpty( (String) urlNOME.getObjectValue() ) ) urlNOME.setValue( "" );
        command.addParameter( "NOME", urlNOME, null );
        if ( StringUtils.isEmpty( (String) urlSESSO.getObjectValue() ) ) urlSESSO.setValue( "" );
        command.addParameter( "SESSO", urlSESSO, null );
        if ( StringUtils.isEmpty( (String) urlDATA_NASCITA.getObjectValue() ) ) urlDATA_NASCITA.setValue( "" );
        command.addParameter( "DATA_NASCITA", urlDATA_NASCITA, null );
        if ( StringUtils.isEmpty( (String) urlCOMUNE_NASCITA.getObjectValue() ) ) urlCOMUNE_NASCITA.setValue( "" );
        command.addParameter( "COMUNE_NAS", urlCOMUNE_NASCITA, null );
        if ( StringUtils.isEmpty( (String) urlCODICE_FISCALE.getObjectValue() ) ) urlCODICE_FISCALE.setValue( "" );
        command.addParameter( "CODICE_FISCALE", urlCODICE_FISCALE, null );
        if ( StringUtils.isEmpty( (String) urlINDIRIZZO_COMPLETO.getObjectValue() ) ) urlINDIRIZZO_COMPLETO.setValue( "" );
        command.addParameter( "INDIRIZZO", urlINDIRIZZO_COMPLETO, null );
        if ( StringUtils.isEmpty( (String) urlCAP.getObjectValue() ) ) urlCAP.setValue( "" );
        command.addParameter( "CAP", urlCAP, null );
        if ( StringUtils.isEmpty( (String) urlCOMUNE.getObjectValue() ) ) urlCOMUNE.setValue( "" );
        command.addParameter( "COMUNE", urlCOMUNE, null );
        if ( StringUtils.isEmpty( (String) urlTELEFONO.getObjectValue() ) ) urlTELEFONO.setValue( "" );
        command.addParameter( "TELEFONO", urlTELEFONO, null );
        if ( StringUtils.isEmpty( (String) urlFAX.getObjectValue() ) ) urlFAX.setValue( "" );
        command.addParameter( "FAX", urlFAX, null );
        if ( StringUtils.isEmpty( (String) urlINDIRIZZO_WEB.getObjectValue() ) ) urlINDIRIZZO_WEB.setValue( "" );
        command.addParameter( "INDIRIZZO_WEB", urlINDIRIZZO_WEB, null );
        if ( StringUtils.isEmpty( (String) urlNOTE.getObjectValue() ) ) urlNOTE.setValue( "" );
        command.addParameter( "NOTE", urlNOTE, null );
        if ( StringUtils.isEmpty( (String) urlISLISTBOX.getObjectValue() ) ) urlISLISTBOX.setValue( "N" );
        command.addParameter( "ISLISTBOX", urlISLISTBOX, null );
        if ( StringUtils.isEmpty( (String) urlCOGNOME.getObjectValue() ) ) urlCOGNOME.setValue( "" );
        command.addParameter( "COGNOME", urlCOGNOME, null );
        if ( StringUtils.isEmpty( (String) sesAD4SOGG.getObjectValue() ) ) sesAD4SOGG.setValue( "" );
        command.addParameter( "AD4SOGG", sesAD4SOGG, null );
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

//loadDataBind @6-A9065EF8
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setID_SOGGETTO(Utils.convertToString(ds.parse(record.get("SOGGETTO"), row.getID_SOGGETTOField())));
            row.setCOGNOME(Utils.convertToString(ds.parse(record.get("COGNOME"), row.getCOGNOMEField())));
            row.setNOME(Utils.convertToString(ds.parse(record.get("NOME"), row.getNOMEField())));
            row.setSOGGETTO(Utils.convertToString(ds.parse(record.get("SOGGETTO"), row.getSOGGETTOField())));
            row.setSESSO(Utils.convertToString(ds.parse(record.get("SESSO"), row.getSESSOField())));
            row.setSTATO(Utils.convertToLong(ds.parse(record.get("STATO_TERRITORIO"), row.getSTATOField())));
            row.setPROVINCIA_NAS(Utils.convertToLong(ds.parse(record.get("PROVINCIA_NAS"), row.getPROVINCIA_NASField())));
            row.setCOMUNE_NASCITA(Utils.convertToLong(ds.parse(record.get("COMUNE_NAS"), row.getCOMUNE_NASCITAField())));
            try {
                row.setDATA_NASCITA(Utils.convertToDate(ds.parse(record.get("DATA_NASCITA"), row.getDATA_NASCITAField())));
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid data" );
            }
            row.setCODICE_FISCALE(Utils.convertToString(ds.parse(record.get("CODICE_FISCALE"), row.getCODICE_FISCALEField())));
            row.setINDIRIZZO_COMPLETO(Utils.convertToString(ds.parse(record.get("INDIRIZZO"), row.getINDIRIZZO_COMPLETOField())));
            row.setPROVINCIA(Utils.convertToLong(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
            row.setCOMUNE(Utils.convertToLong(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
            row.setCAP(Utils.convertToString(ds.parse(record.get("CAP"), row.getCAPField())));
            row.setINDIRIZZO_WEB(Utils.convertToString(ds.parse(record.get("INDIRIZZO_WEB"), row.getINDIRIZZO_WEBField())));
            row.setTELEFONO(Utils.convertToString(ds.parse(record.get("TELEFONO"), row.getTELEFONOField())));
            row.setFAX(Utils.convertToString(ds.parse(record.get("FAX"), row.getFAXField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @6-74669AE4
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4WEB.SOGGETTI_PM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            if ( urlSOGGETTO.getObjectValue() == null ) urlSOGGETTO.setValue( "" );
            command.addParameter( "P_SOGGETTO", urlSOGGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postCOGNOME.getObjectValue() ) ) postCOGNOME.setValue( "" );
            command.addParameter( "P_COGNOME", postCOGNOME, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOME.getObjectValue() ) ) postNOME.setValue( "" );
            command.addParameter( "P_NOME", postNOME, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postSESSO.getObjectValue() ) ) postSESSO.setValue( "" );
            command.addParameter( "P_SESSO", postSESSO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postP_DATA_NAS.getObjectValue() ) ) postP_DATA_NAS.setValue( "" );
            command.addParameter( "P_DATA_NAS", postP_DATA_NAS, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_COMUNE_NAS", postCOMUNE_NASCITA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_PROVINCIA_NAS", postPROVINCIA_NAS, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postCODICE_FISCALE.getObjectValue() ) ) postCODICE_FISCALE.setValue( "" );
            command.addParameter( "P_COD_FIS", postCODICE_FISCALE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_COMUNE", postCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_PROVINCIA", postPROVINCIA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postCAP.getObjectValue() ) ) postCAP.setValue( "" );
            command.addParameter( "P_CAP", postCAP, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postINDIRIZZO_COMPLETO.getObjectValue() ) ) postINDIRIZZO_COMPLETO.setValue( "" );
            command.addParameter( "P_INDIRIZZO", postINDIRIZZO_COMPLETO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postINDIRIZZO_WEB.getObjectValue() ) ) postINDIRIZZO_WEB.setValue( "" );
            command.addParameter( "P_INDIRIZZO_WEB", postINDIRIZZO_WEB, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postTELEFONO.getObjectValue() ) ) postTELEFONO.setValue( "" );
            command.addParameter( "P_TELEFONO", postTELEFONO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postFAX.getObjectValue() ) ) postFAX.setValue( "" );
            command.addParameter( "P_FAX", postFAX, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "P_UTENTE_AGG", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildInsertEvent( new DataObjectEvent(command) );

//End insert

//insertDataBound @6-BC781F8A
            fireBeforeExecuteInsertEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteInsertEvent( new DataObjectEvent(command) );

//End insertDataBound

//End of insert @6-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of insert

//update @6-4B1D6FCF
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4WEB.SOGGETTI_PM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            if ( urlSOGGETTO.getObjectValue() == null ) urlSOGGETTO.setValue( "" );
            command.addParameter( "P_SOGGETTO", urlSOGGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postCOGNOME.getObjectValue() ) ) postCOGNOME.setValue( "" );
            command.addParameter( "P_COGNOME", postCOGNOME, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOME.getObjectValue() ) ) postNOME.setValue( "" );
            command.addParameter( "P_NOME", postNOME, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postSESSO.getObjectValue() ) ) postSESSO.setValue( "" );
            command.addParameter( "P_SESSO", postSESSO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDATA_NASCITA.getObjectValue() ) ) postDATA_NASCITA.setValue( "" );
            command.addParameter( "P_DATA_NAS", postDATA_NASCITA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_COMUNE_NAS", postCOMUNE_NASCITA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_PROVINCIA_NAS", postPROVINCIA_NAS, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postCODICE_FISCALE.getObjectValue() ) ) postCODICE_FISCALE.setValue( "" );
            command.addParameter( "P_COD_FIS", postCODICE_FISCALE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_COMUNE", postCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_PROVINCIA", postPROVINCIA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postCAP.getObjectValue() ) ) postCAP.setValue( "" );
            command.addParameter( "P_CAP", postCAP, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postINDIRIZZO_COMPLETO.getObjectValue() ) ) postINDIRIZZO_COMPLETO.setValue( "" );
            command.addParameter( "P_INDIRIZZO", postINDIRIZZO_COMPLETO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postINDIRIZZO_WEB.getObjectValue() ) ) postINDIRIZZO_WEB.setValue( "" );
            command.addParameter( "P_INDIRIZZO_WEB", postINDIRIZZO_WEB, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postTELEFONO.getObjectValue() ) ) postTELEFONO.setValue( "" );
            command.addParameter( "P_TELEFONO", postTELEFONO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postFAX.getObjectValue() ) ) postFAX.setValue( "" );
            command.addParameter( "P_FAX", postFAX, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
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

//getParameterByName @6-F873FADF
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "SOGGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlSOGGETTO;
            } else if ( "SOGGETTO".equals(name) && prefix == null ) {
                param = urlSOGGETTO;
            }
            if ( "COGNOME".equals(name) && "post".equals(prefix) ) {
                param = postCOGNOME;
            } else if ( "COGNOME".equals(name) && prefix == null ) {
                param = postCOGNOME;
            }
            if ( "NOME".equals(name) && "post".equals(prefix) ) {
                param = postNOME;
            } else if ( "NOME".equals(name) && prefix == null ) {
                param = postNOME;
            }
            if ( "SESSO".equals(name) && "post".equals(prefix) ) {
                param = postSESSO;
            } else if ( "SESSO".equals(name) && prefix == null ) {
                param = postSESSO;
            }
            if ( "P_DATA_NAS".equals(name) && "post".equals(prefix) ) {
                param = postP_DATA_NAS;
            } else if ( "P_DATA_NAS".equals(name) && prefix == null ) {
                param = postP_DATA_NAS;
            }
            if ( "COMUNE_NASCITA".equals(name) && "post".equals(prefix) ) {
                param = postCOMUNE_NASCITA;
            } else if ( "COMUNE_NASCITA".equals(name) && prefix == null ) {
                param = postCOMUNE_NASCITA;
            }
            if ( "PROVINCIA_NAS".equals(name) && "post".equals(prefix) ) {
                param = postPROVINCIA_NAS;
            } else if ( "PROVINCIA_NAS".equals(name) && prefix == null ) {
                param = postPROVINCIA_NAS;
            }
            if ( "CODICE_FISCALE".equals(name) && "post".equals(prefix) ) {
                param = postCODICE_FISCALE;
            } else if ( "CODICE_FISCALE".equals(name) && prefix == null ) {
                param = postCODICE_FISCALE;
            }
            if ( "COMUNE".equals(name) && "post".equals(prefix) ) {
                param = postCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = postCOMUNE;
            }
            if ( "PROVINCIA".equals(name) && "post".equals(prefix) ) {
                param = postPROVINCIA;
            } else if ( "PROVINCIA".equals(name) && prefix == null ) {
                param = postPROVINCIA;
            }
            if ( "CAP".equals(name) && "post".equals(prefix) ) {
                param = postCAP;
            } else if ( "CAP".equals(name) && prefix == null ) {
                param = postCAP;
            }
            if ( "INDIRIZZO_COMPLETO".equals(name) && "post".equals(prefix) ) {
                param = postINDIRIZZO_COMPLETO;
            } else if ( "INDIRIZZO_COMPLETO".equals(name) && prefix == null ) {
                param = postINDIRIZZO_COMPLETO;
            }
            if ( "INDIRIZZO_WEB".equals(name) && "post".equals(prefix) ) {
                param = postINDIRIZZO_WEB;
            } else if ( "INDIRIZZO_WEB".equals(name) && prefix == null ) {
                param = postINDIRIZZO_WEB;
            }
            if ( "TELEFONO".equals(name) && "post".equals(prefix) ) {
                param = postTELEFONO;
            } else if ( "TELEFONO".equals(name) && prefix == null ) {
                param = postTELEFONO;
            }
            if ( "FAX".equals(name) && "post".equals(prefix) ) {
                param = postFAX;
            } else if ( "FAX".equals(name) && prefix == null ) {
                param = postFAX;
            }
            if ( "NOTE".equals(name) && "post".equals(prefix) ) {
                param = postNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = postNOTE;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "DATA_NASCITA".equals(name) && "post".equals(prefix) ) {
                param = postDATA_NASCITA;
            } else if ( "DATA_NASCITA".equals(name) && prefix == null ) {
                param = postDATA_NASCITA;
            }
            if ( "PROVINCIA".equals(name) && "url".equals(prefix) ) {
                param = urlPROVINCIA;
            } else if ( "PROVINCIA".equals(name) && prefix == null ) {
                param = urlPROVINCIA;
            }
            if ( "STATO".equals(name) && "url".equals(prefix) ) {
                param = urlSTATO;
            } else if ( "STATO".equals(name) && prefix == null ) {
                param = urlSTATO;
            }
            if ( "PROVINCIA_NAS".equals(name) && "url".equals(prefix) ) {
                param = urlPROVINCIA_NAS;
            } else if ( "PROVINCIA_NAS".equals(name) && prefix == null ) {
                param = urlPROVINCIA_NAS;
            }
            if ( "NOME".equals(name) && "url".equals(prefix) ) {
                param = urlNOME;
            } else if ( "NOME".equals(name) && prefix == null ) {
                param = urlNOME;
            }
            if ( "SESSO".equals(name) && "url".equals(prefix) ) {
                param = urlSESSO;
            } else if ( "SESSO".equals(name) && prefix == null ) {
                param = urlSESSO;
            }
            if ( "DATA_NASCITA".equals(name) && "url".equals(prefix) ) {
                param = urlDATA_NASCITA;
            } else if ( "DATA_NASCITA".equals(name) && prefix == null ) {
                param = urlDATA_NASCITA;
            }
            if ( "COMUNE_NASCITA".equals(name) && "url".equals(prefix) ) {
                param = urlCOMUNE_NASCITA;
            } else if ( "COMUNE_NASCITA".equals(name) && prefix == null ) {
                param = urlCOMUNE_NASCITA;
            }
            if ( "CODICE_FISCALE".equals(name) && "url".equals(prefix) ) {
                param = urlCODICE_FISCALE;
            } else if ( "CODICE_FISCALE".equals(name) && prefix == null ) {
                param = urlCODICE_FISCALE;
            }
            if ( "INDIRIZZO_COMPLETO".equals(name) && "url".equals(prefix) ) {
                param = urlINDIRIZZO_COMPLETO;
            } else if ( "INDIRIZZO_COMPLETO".equals(name) && prefix == null ) {
                param = urlINDIRIZZO_COMPLETO;
            }
            if ( "CAP".equals(name) && "url".equals(prefix) ) {
                param = urlCAP;
            } else if ( "CAP".equals(name) && prefix == null ) {
                param = urlCAP;
            }
            if ( "COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = urlCOMUNE;
            }
            if ( "TELEFONO".equals(name) && "url".equals(prefix) ) {
                param = urlTELEFONO;
            } else if ( "TELEFONO".equals(name) && prefix == null ) {
                param = urlTELEFONO;
            }
            if ( "FAX".equals(name) && "url".equals(prefix) ) {
                param = urlFAX;
            } else if ( "FAX".equals(name) && prefix == null ) {
                param = urlFAX;
            }
            if ( "INDIRIZZO_WEB".equals(name) && "url".equals(prefix) ) {
                param = urlINDIRIZZO_WEB;
            } else if ( "INDIRIZZO_WEB".equals(name) && prefix == null ) {
                param = urlINDIRIZZO_WEB;
            }
            if ( "NOTE".equals(name) && "url".equals(prefix) ) {
                param = urlNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = urlNOTE;
            }
            if ( "ISLISTBOX".equals(name) && "url".equals(prefix) ) {
                param = urlISLISTBOX;
            } else if ( "ISLISTBOX".equals(name) && prefix == null ) {
                param = urlISLISTBOX;
            }
            if ( "COGNOME".equals(name) && "url".equals(prefix) ) {
                param = urlCOGNOME;
            } else if ( "COGNOME".equals(name) && prefix == null ) {
                param = urlCOGNOME;
            }
            if ( "AD4SOGG".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4SOGG;
            } else if ( "AD4SOGG".equals(name) && prefix == null ) {
                param = sesAD4SOGG;
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

