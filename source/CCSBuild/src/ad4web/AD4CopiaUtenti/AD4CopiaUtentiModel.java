//AD4CopiaUtentiModel imports @1-CC0DE8FB
package ad4web.AD4CopiaUtenti;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4CopiaUtentiModel imports

//AD4CopiaUtentiModel class head @1-07DACBA9
public class AD4CopiaUtentiModel extends com.codecharge.components.Page {
    public AD4CopiaUtentiModel() {
        this( new CCSLocale(), null );
    }

    public AD4CopiaUtentiModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4CopiaUtentiModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4CopiaUtentiModel class head

//page settings @1-1E29F1E1
        super("AD4CopiaUtenti", locale );
        setResponse(response);
        addPageListener(new AD4CopiaUtentiPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__5 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__5.setVisible( true );
            add( Guida__5 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );

            com.codecharge.components.Label JS_REFRESH_SLAVES__247 = new com.codecharge.components.Label("JS_REFRESH_SLAVES", "REFRESH_SLAVES", this );
            JS_REFRESH_SLAVES__247.setType( com.codecharge.components.ControlType.TEXT );
            JS_REFRESH_SLAVES__247.addControlListener( new AD4CopiaUtentiJS_REFRESH_SLAVESHandler());
            add( JS_REFRESH_SLAVES__247 );
        } // end page
//End page settings

//AD4_CARATTERISTICHE_ACCESSO record @38-E638DF7B
        
        /*
            Model of AD4_CARATTERISTICHE_ACCESSO record defining.
        */
        {
            com.codecharge.components.Record AD4_CARATTERISTICHE_ACCESSO = new com.codecharge.components.Record("AD4_CARATTERISTICHE_ACCESSO");
            AD4_CARATTERISTICHE_ACCESSO.setPageModel( this );
            AD4_CARATTERISTICHE_ACCESSO.addExcludeParam( "ccsForm" );
            AD4_CARATTERISTICHE_ACCESSO.setVisible( true );
            AD4_CARATTERISTICHE_ACCESSO.setAllowInsert(false);
            AD4_CARATTERISTICHE_ACCESSO.setPreserveType(PreserveParameterType.GET);
            AD4_CARATTERISTICHE_ACCESSO.setReturnPage("AD4CopiaUtenti" + Names.ACTION_SUFFIX);
            AD4_CARATTERISTICHE_ACCESSO.addRecordListener(new AD4_CARATTERISTICHE_ACCESSORecordHandler());

            com.codecharge.components.Label CAAC_DESC__80 = new com.codecharge.components.Label("CAAC_DESC", "CAAC_DESC", this );
            CAAC_DESC__80.setType( com.codecharge.components.ControlType.TEXT );
            CAAC_DESC__80.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(CAAC_DESC__80);

            com.codecharge.components.Label DESC_ACCESSO__166 = new com.codecharge.components.Label("DESC_ACCESSO", "DESC_ACCESSO", this );
            DESC_ACCESSO__166.setType( com.codecharge.components.ControlType.TEXT );
            DESC_ACCESSO__166.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(DESC_ACCESSO__166);

            com.codecharge.components.Hidden OLD_CAAC_ID__170 = new com.codecharge.components.Hidden("OLD_CAAC_ID", "OLD_CAAC_ID", this );
            OLD_CAAC_ID__170.setType( com.codecharge.components.ControlType.TEXT );
            OLD_CAAC_ID__170.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( OLD_CAAC_ID__170 );

            com.codecharge.components.Hidden CAAC_ID__178 = new com.codecharge.components.Hidden("CAAC_ID", "CAAC_ID", this );
            CAAC_ID__178.setType( com.codecharge.components.ControlType.TEXT );
            CAAC_ID__178.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( CAAC_ID__178 );

            com.codecharge.components.Hidden OLD_MODULO__173 = new com.codecharge.components.Hidden("OLD_MODULO", "OLD_MODULO", this );
            OLD_MODULO__173.setType( com.codecharge.components.ControlType.TEXT );
            OLD_MODULO__173.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( OLD_MODULO__173 );

            com.codecharge.components.Hidden MODULO__179 = new com.codecharge.components.Hidden("MODULO", "MODULO", this );
            MODULO__179.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__179.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( MODULO__179 );

            com.codecharge.components.Hidden INSERT_UPDATE__176 = new com.codecharge.components.Hidden("INSERT_UPDATE", "INSERT_UPDATE", this );
            INSERT_UPDATE__176.setType( com.codecharge.components.ControlType.TEXT );
            INSERT_UPDATE__176.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( INSERT_UPDATE__176 );

            com.codecharge.components.Label JOB_ATTIVO__214 = new com.codecharge.components.Label("JOB_ATTIVO", "JOB_ATTIVO", this );
            JOB_ATTIVO__214.setType( com.codecharge.components.ControlType.TEXT );
            JOB_ATTIVO__214.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(JOB_ATTIVO__214);

            com.codecharge.components.Label DESC_PASSWORD__167 = new com.codecharge.components.Label("DESC_PASSWORD", "DESC_PASSWORD", this );
            DESC_PASSWORD__167.setType( com.codecharge.components.ControlType.TEXT );
            DESC_PASSWORD__167.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(DESC_PASSWORD__167);

            com.codecharge.components.Hidden OLD_PROGETTO__171 = new com.codecharge.components.Hidden("OLD_PROGETTO", "OLD_PROGETTO", this );
            OLD_PROGETTO__171.setType( com.codecharge.components.ControlType.TEXT );
            OLD_PROGETTO__171.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( OLD_PROGETTO__171 );

            com.codecharge.components.Hidden PROGETTO__180 = new com.codecharge.components.Hidden("PROGETTO", "PROGETTO", this );
            PROGETTO__180.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO__180.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( PROGETTO__180 );

            com.codecharge.components.Hidden OLD_UTENTE__174 = new com.codecharge.components.Hidden("OLD_UTENTE", "OLD_UTENTE", this );
            OLD_UTENTE__174.setType( com.codecharge.components.ControlType.TEXT );
            OLD_UTENTE__174.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( OLD_UTENTE__174 );

            com.codecharge.components.Hidden UTENTE__181 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__181.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__181.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( UTENTE__181 );

            com.codecharge.components.Hidden TIPO_ACCESSO__177 = new com.codecharge.components.Hidden("TIPO_ACCESSO", "TIPO_ACCESSO", this );
            TIPO_ACCESSO__177.setType( com.codecharge.components.ControlType.TEXT );
            TIPO_ACCESSO__177.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( TIPO_ACCESSO__177 );

            com.codecharge.components.Label DESC_AUTENTICAZIONE__168 = new com.codecharge.components.Label("DESC_AUTENTICAZIONE", "DESC_AUTENTICAZIONE", this );
            DESC_AUTENTICAZIONE__168.setType( com.codecharge.components.ControlType.TEXT );
            DESC_AUTENTICAZIONE__168.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(DESC_AUTENTICAZIONE__168);

            com.codecharge.components.Hidden OLD_ISTANZA__172 = new com.codecharge.components.Hidden("OLD_ISTANZA", "OLD_ISTANZA", this );
            OLD_ISTANZA__172.setType( com.codecharge.components.ControlType.TEXT );
            OLD_ISTANZA__172.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( OLD_ISTANZA__172 );

            com.codecharge.components.Hidden ISTANZA__182 = new com.codecharge.components.Hidden("ISTANZA", "ISTANZA", this );
            ISTANZA__182.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA__182.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( ISTANZA__182 );

            com.codecharge.components.Hidden CAAC_DESC_Hidden__175 = new com.codecharge.components.Hidden("CAAC_DESC_Hidden", "CAAC_DESC", this );
            CAAC_DESC_Hidden__175.setType( com.codecharge.components.ControlType.TEXT );
            CAAC_DESC_Hidden__175.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( CAAC_DESC_Hidden__175 );

            com.codecharge.components.CheckBox ACCESSO_SE__196=  new com.codecharge.components.CheckBox( "ACCESSO_SE", "ACCESSO_SE", this );
            ACCESSO_SE__196.setType( com.codecharge.components.ControlType.TEXT );
            ACCESSO_SE__196.setCheckedValue( "SI" );
            ACCESSO_SE__196.setUncheckedValue( "NO" );
            AD4_CARATTERISTICHE_ACCESSO.add(ACCESSO_SE__196);

            com.codecharge.components.Label ACCESSO_SE_DESC__197 = new com.codecharge.components.Label("ACCESSO_SE_DESC", "ACCESSO_SE_DESC", this );
            ACCESSO_SE_DESC__197.setType( com.codecharge.components.ControlType.TEXT );
            ACCESSO_SE_DESC__197.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(ACCESSO_SE_DESC__197);

            com.codecharge.components.ListBox ACCESSO__206 = new com.codecharge.components.ListBox("ACCESSO", "ACCESSO", this );
            ACCESSO__206.setType( com.codecharge.components.ControlType.TEXT );
            ACCESSO__206.setHtmlEncode( true );
            ACCESSO__206.setCaption( "ACCESSO" );
            ACCESSO__206.setBoundColumn( "MODULO" );
            ACCESSO__206.setTextColumn( "DESCRIZIONE" );
            ACCESSO__206.addValidateHandler( new RequiredHandler( "The value in field ACCESSO is required." ) );
            AD4_CARATTERISTICHE_ACCESSO.add( ACCESSO__206 );

            com.codecharge.components.ListBox TRACCIA__143 = new com.codecharge.components.ListBox("TRACCIA", "TRACCIA", this );
            TRACCIA__143.setType( com.codecharge.components.ControlType.TEXT );
            TRACCIA__143.setHtmlEncode( true );
            TRACCIA__143.setCaption( "TRACCIA" );
            AD4_CARATTERISTICHE_ACCESSO.add( TRACCIA__143 );

            com.codecharge.components.TextBox GIORNI_TRACCIA__160 = new com.codecharge.components.TextBox("GIORNI_TRACCIA", "GIORNI_TRACCIA", this );
            GIORNI_TRACCIA__160.setType( com.codecharge.components.ControlType.INTEGER );
            GIORNI_TRACCIA__160.setHtmlEncode( true );
            GIORNI_TRACCIA__160.addControlListener( new AD4_CARATTERISTICHE_ACCESSOGIORNI_TRACCIAHandler());
            GIORNI_TRACCIA__160.setCaption( "GIORNI_TRACCIA" );
            AD4_CARATTERISTICHE_ACCESSO.add( GIORNI_TRACCIA__160 );

            com.codecharge.components.Hidden NUM_FILE_ARCHIVIATI__215 = new com.codecharge.components.Hidden("NUM_FILE_ARCHIVIATI", "NUM_FILE_ARCHIVIATI", this );
            NUM_FILE_ARCHIVIATI__215.setType( com.codecharge.components.ControlType.INTEGER );
            NUM_FILE_ARCHIVIATI__215.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( NUM_FILE_ARCHIVIATI__215 );

            com.codecharge.components.Hidden DIM_FILE_ARCHIVIATI__216 = new com.codecharge.components.Hidden("DIM_FILE_ARCHIVIATI", "DIM_FILE_ARCHIVIATI", this );
            DIM_FILE_ARCHIVIATI__216.setType( com.codecharge.components.ControlType.FLOAT );
            DIM_FILE_ARCHIVIATI__216.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( DIM_FILE_ARCHIVIATI__216 );

            com.codecharge.components.Hidden SPOSTA_FILE_ARCH__220 = new com.codecharge.components.Hidden("SPOSTA_FILE_ARCH", "SPOSTA_FILE_ARCH", this );
            SPOSTA_FILE_ARCH__220.setType( com.codecharge.components.ControlType.INTEGER );
            SPOSTA_FILE_ARCH__220.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( SPOSTA_FILE_ARCH__220 );

            com.codecharge.components.CheckBox ARCHIVIAZIONE_TRACCIA__202=  new com.codecharge.components.CheckBox( "ARCHIVIAZIONE_TRACCIA", "ARCHIVIAZIONE_TRACCIA", this );
            ARCHIVIAZIONE_TRACCIA__202.setType( com.codecharge.components.ControlType.TEXT );
            ARCHIVIAZIONE_TRACCIA__202.setCheckedValue( "SI" );
            ARCHIVIAZIONE_TRACCIA__202.setUncheckedValue( "NO" );
            AD4_CARATTERISTICHE_ACCESSO.add(ARCHIVIAZIONE_TRACCIA__202);

            com.codecharge.components.Label LABEL_ARCH__238 = new com.codecharge.components.Label("LABEL_ARCH", "LABEL_ARCH", this );
            LABEL_ARCH__238.setType( com.codecharge.components.ControlType.TEXT );
            LABEL_ARCH__238.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(LABEL_ARCH__238);

            com.codecharge.components.TextBox DISLOCAZIONE_TRACCIA__241 = new com.codecharge.components.TextBox("DISLOCAZIONE_TRACCIA", "DISLOCAZIONE_TRACCIA", this );
            DISLOCAZIONE_TRACCIA__241.setType( com.codecharge.components.ControlType.TEXT );
            DISLOCAZIONE_TRACCIA__241.setHtmlEncode( true );
            DISLOCAZIONE_TRACCIA__241.setCaption( "DISLOCAZIONE_TRACCIA" );
            AD4_CARATTERISTICHE_ACCESSO.add( DISLOCAZIONE_TRACCIA__241 );

            com.codecharge.components.Hidden INPUT_ARCH_CLASS__240 = new com.codecharge.components.Hidden("INPUT_ARCH_CLASS", "INPUT_ARCH_CLASS", this );
            INPUT_ARCH_CLASS__240.setType( com.codecharge.components.ControlType.TEXT );
            INPUT_ARCH_CLASS__240.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( INPUT_ARCH_CLASS__240 );

            com.codecharge.components.Hidden CKB_ARCH_CLASS__239 = new com.codecharge.components.Hidden("CKB_ARCH_CLASS", "CKB_ARCH_CLASS", this );
            CKB_ARCH_CLASS__239.setType( com.codecharge.components.ControlType.TEXT );
            CKB_ARCH_CLASS__239.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( CKB_ARCH_CLASS__239 );

            com.codecharge.components.Hidden DISLOCAZIONE_TRACCIA_OLD__219 = new com.codecharge.components.Hidden("DISLOCAZIONE_TRACCIA_OLD", "DISLOCAZIONE_TRACCIA_OLD", this );
            DISLOCAZIONE_TRACCIA_OLD__219.setType( com.codecharge.components.ControlType.TEXT );
            DISLOCAZIONE_TRACCIA_OLD__219.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( DISLOCAZIONE_TRACCIA_OLD__219 );

            com.codecharge.components.TextBox SLEEP__204 = new com.codecharge.components.TextBox("SLEEP", "SLEEP", this );
            SLEEP__204.setType( com.codecharge.components.ControlType.INTEGER );
            SLEEP__204.setHtmlEncode( true );
            SLEEP__204.addControlListener( new AD4_CARATTERISTICHE_ACCESSOSLEEPHandler());
            SLEEP__204.setCaption( "SLEEP" );
            AD4_CARATTERISTICHE_ACCESSO.add( SLEEP__204 );

            com.codecharge.components.TextBox VALIDITA_PASSWORD__148 = new com.codecharge.components.TextBox("VALIDITA_PASSWORD", "VALIDITA_PASSWORD", this );
            VALIDITA_PASSWORD__148.setType( com.codecharge.components.ControlType.INTEGER );
            VALIDITA_PASSWORD__148.setHtmlEncode( true );
            VALIDITA_PASSWORD__148.addControlListener( new AD4_CARATTERISTICHE_ACCESSOVALIDITA_PASSWORDHandler());
            VALIDITA_PASSWORD__148.setCaption( "VALIDITA_PASSWORD" );
            AD4_CARATTERISTICHE_ACCESSO.add( VALIDITA_PASSWORD__148 );

            com.codecharge.components.TextBox TENTATIVI_PASSWORD__149 = new com.codecharge.components.TextBox("TENTATIVI_PASSWORD", "TENTATIVI_PASSWORD", this );
            TENTATIVI_PASSWORD__149.setType( com.codecharge.components.ControlType.INTEGER );
            TENTATIVI_PASSWORD__149.setHtmlEncode( true );
            TENTATIVI_PASSWORD__149.addControlListener( new AD4_CARATTERISTICHE_ACCESSOTENTATIVI_PASSWORDHandler());
            TENTATIVI_PASSWORD__149.setCaption( "TENTATIVI_PASSWORD" );
            AD4_CARATTERISTICHE_ACCESSO.add( TENTATIVI_PASSWORD__149 );

            com.codecharge.components.CheckBox MOD_PWD_PRIMO_ACCESSO__208=  new com.codecharge.components.CheckBox( "MOD_PWD_PRIMO_ACCESSO", "MOD_PWD_PRIMO_ACCESSO", this );
            MOD_PWD_PRIMO_ACCESSO__208.setType( com.codecharge.components.ControlType.TEXT );
            MOD_PWD_PRIMO_ACCESSO__208.setCheckedValue( "SI" );
            MOD_PWD_PRIMO_ACCESSO__208.setUncheckedValue( "NO" );
            AD4_CARATTERISTICHE_ACCESSO.add(MOD_PWD_PRIMO_ACCESSO__208);

            com.codecharge.components.TextBox MIN_LUNGHEZZA_PWD__210 = new com.codecharge.components.TextBox("MIN_LUNGHEZZA_PWD", "MIN_LUNGHEZZA_PWD", this );
            MIN_LUNGHEZZA_PWD__210.setType( com.codecharge.components.ControlType.INTEGER );
            MIN_LUNGHEZZA_PWD__210.setHtmlEncode( true );
            MIN_LUNGHEZZA_PWD__210.addControlListener( new AD4_CARATTERISTICHE_ACCESSOMIN_LUNGHEZZA_PWDHandler());
            MIN_LUNGHEZZA_PWD__210.setCaption( "MIN_LUNGHEZZA_PWD" );
            AD4_CARATTERISTICHE_ACCESSO.add( MIN_LUNGHEZZA_PWD__210 );

            com.codecharge.components.Hidden MIN_LUNGHEZZA_PWD_OLD__242 = new com.codecharge.components.Hidden("MIN_LUNGHEZZA_PWD_OLD", "MIN_LUNGHEZZA_PWD_OLD", this );
            MIN_LUNGHEZZA_PWD_OLD__242.setType( com.codecharge.components.ControlType.INTEGER );
            MIN_LUNGHEZZA_PWD_OLD__242.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( MIN_LUNGHEZZA_PWD_OLD__242 );

            com.codecharge.components.CheckBox AMMESSI_CAR_SPECIALI_PWD__209=  new com.codecharge.components.CheckBox( "AMMESSI_CAR_SPECIALI_PWD", "AMMESSI_CAR_SPECIALI_PWD", this );
            AMMESSI_CAR_SPECIALI_PWD__209.setType( com.codecharge.components.ControlType.TEXT );
            AMMESSI_CAR_SPECIALI_PWD__209.setCheckedValue( "SI" );
            AMMESSI_CAR_SPECIALI_PWD__209.setUncheckedValue( "NO" );
            AD4_CARATTERISTICHE_ACCESSO.add(AMMESSI_CAR_SPECIALI_PWD__209);

            com.codecharge.components.Hidden AMMESSI_CAR_SPECIALI_PWD_OLD__243 = new com.codecharge.components.Hidden("AMMESSI_CAR_SPECIALI_PWD_OLD", "AMMESSI_CAR_SPECIALI_PWD_OLD", this );
            AMMESSI_CAR_SPECIALI_PWD_OLD__243.setType( com.codecharge.components.ControlType.TEXT );
            AMMESSI_CAR_SPECIALI_PWD_OLD__243.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( AMMESSI_CAR_SPECIALI_PWD_OLD__243 );

            com.codecharge.components.CheckBox NUMERI_OBB_PWD__213=  new com.codecharge.components.CheckBox( "NUMERI_OBB_PWD", "NUMERI_OBB_PWD", this );
            NUMERI_OBB_PWD__213.setType( com.codecharge.components.ControlType.TEXT );
            NUMERI_OBB_PWD__213.setCheckedValue( "SI" );
            NUMERI_OBB_PWD__213.setUncheckedValue( "NO" );
            AD4_CARATTERISTICHE_ACCESSO.add(NUMERI_OBB_PWD__213);

            com.codecharge.components.Hidden NUMERI_OBB_PWD_OLD__244 = new com.codecharge.components.Hidden("NUMERI_OBB_PWD_OLD", "NUMERI_OBB_PWD_OLD", this );
            NUMERI_OBB_PWD_OLD__244.setType( com.codecharge.components.ControlType.TEXT );
            NUMERI_OBB_PWD_OLD__244.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( NUMERI_OBB_PWD_OLD__244 );

            com.codecharge.components.CheckBox LDAP__146=  new com.codecharge.components.CheckBox( "LDAP", "LDAP", this );
            LDAP__146.setType( com.codecharge.components.ControlType.TEXT );
            LDAP__146.setCheckedValue( "SI" );
            LDAP__146.setUncheckedValue( "NO" );
            AD4_CARATTERISTICHE_ACCESSO.add(LDAP__146);

            com.codecharge.components.Hidden SINGLE_SIGN_ON__147 = new com.codecharge.components.Hidden("SINGLE_SIGN_ON", "SINGLE_SIGN_ON", this );
            SINGLE_SIGN_ON__147.setType( com.codecharge.components.ControlType.TEXT );
            SINGLE_SIGN_ON__147.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( SINGLE_SIGN_ON__147 );

            com.codecharge.components.Button DettagliShow__221 = new com.codecharge.components.Button("DettagliShow", this);
            DettagliShow__221.addExcludeParam( "ccsForm" );
            DettagliShow__221.addExcludeParam( "DettagliShow" );
            AD4_CARATTERISTICHE_ACCESSO.add( DettagliShow__221 );

            com.codecharge.components.Button DettagliHide__236 = new com.codecharge.components.Button("DettagliHide", this);
            DettagliHide__236.addExcludeParam( "ccsForm" );
            DettagliHide__236.addExcludeParam( "DettagliHide" );
            AD4_CARATTERISTICHE_ACCESSO.add( DettagliHide__236 );

            com.codecharge.components.Button Button_Update__40 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__40.addExcludeParam( "ccsForm" );
            Button_Update__40.addExcludeParam( "Button_Update" );
            Button_Update__40.setOperation( "Update" );
            AD4_CARATTERISTICHE_ACCESSO.add( Button_Update__40 );

            com.codecharge.components.Button Button_Delete__41 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__41.addExcludeParam( "ccsForm" );
            Button_Delete__41.addExcludeParam( "Button_Delete" );
            Button_Delete__41.setOperation( "Delete" );
            AD4_CARATTERISTICHE_ACCESSO.add( Button_Delete__41 );

            com.codecharge.components.Button Button_Cancel__43 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__43.addButtonListener(new AD4_CARATTERISTICHE_ACCESSOButton_CancelHandler());
            Button_Cancel__43.addExcludeParam( "ccsForm" );
            Button_Cancel__43.addExcludeParam( "Button_Cancel" );
            Button_Cancel__43.setOperation( "Cancel" );
            AD4_CARATTERISTICHE_ACCESSO.add( Button_Cancel__43 );

            com.codecharge.components.Label DESC_DETTAGLI_LABEL__237 = new com.codecharge.components.Label("DESC_DETTAGLI_LABEL", "DESC_DETTAGLI", this );
            DESC_DETTAGLI_LABEL__237.setType( com.codecharge.components.ControlType.TEXT );
            DESC_DETTAGLI_LABEL__237.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add(DESC_DETTAGLI_LABEL__237);

            com.codecharge.components.Hidden DESC_DETTAGLI__222 = new com.codecharge.components.Hidden("DESC_DETTAGLI", "DESC_DETTAGLI", this );
            DESC_DETTAGLI__222.setType( com.codecharge.components.ControlType.TEXT );
            DESC_DETTAGLI__222.setHtmlEncode( true );
            AD4_CARATTERISTICHE_ACCESSO.add( DESC_DETTAGLI__222 );
            add(AD4_CARATTERISTICHE_ACCESSO);
        } // End definition of AD4_CARATTERISTICHE_ACCESSO record model.
//End AD4_CARATTERISTICHE_ACCESSO record

//invalidPwdLog grid @245-8CC419C1
        
        /*
            // Begin definition of invalidPwdLog grid model.
        */
        {
            com.codecharge.components.Grid invalidPwdLog = new com.codecharge.components.Grid("invalidPwdLog");
            invalidPwdLog.setPageModel( this );
            invalidPwdLog.setFetchSize(20);
            invalidPwdLog.setVisible( true );

            com.codecharge.components.Label INVALID_PWD_LOG__246 = new com.codecharge.components.Label("INVALID_PWD_LOG", "INVALID_PWD_LOG", this );
            INVALID_PWD_LOG__246.setType( com.codecharge.components.ControlType.MEMO );
            INVALID_PWD_LOG__246.setHtmlEncode( true );
            invalidPwdLog.add(INVALID_PWD_LOG__246);
            add(invalidPwdLog);
        } // End definition of invalidPwdLog grid model
//End invalidPwdLog grid

//AD4CopiaUtentiModel class tail @1-F5FC18C5
    }
}
//End AD4CopiaUtentiModel class tail
