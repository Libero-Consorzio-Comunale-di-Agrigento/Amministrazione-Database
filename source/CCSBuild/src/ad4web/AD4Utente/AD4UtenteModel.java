//AD4UtenteModel imports @1-773D4208
package ad4web.AD4Utente;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4UtenteModel imports

//AD4UtenteModel class head @1-536DF2C2
public class AD4UtenteModel extends com.codecharge.components.Page {
    public AD4UtenteModel() {
        this( new CCSLocale(), null );
    }

    public AD4UtenteModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4UtenteModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4UtenteModel class head

//page settings @1-19193006
        super("AD4Utente", locale );
        setResponse(response);
        addPageListener(new AD4UtentePageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__4 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__4.setVisible( true );
            add( Guida__4 );
            com.codecharge.components.IncludePage Footer__5 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__5.setVisible( true );
            add( Footer__5 );
        } // end page
//End page settings

//AD4_UTENTI record @6-3CF5D6A0
        
        /*
            Model of AD4_UTENTI record defining.
        */
        {
            com.codecharge.components.Record AD4_UTENTI = new com.codecharge.components.Record("AD4_UTENTI");
            AD4_UTENTI.setPageModel( this );
            AD4_UTENTI.addExcludeParam( "ccsForm" );
            AD4_UTENTI.setVisible( true );
            AD4_UTENTI.setPreserveType(PreserveParameterType.GET);
            AD4_UTENTI.setReturnPage("AD4Utente" + Names.ACTION_SUFFIX);
            AD4_UTENTI.addRecordListener(new AD4_UTENTIRecordHandler());

            com.codecharge.components.Link GENERA_PASSWORD__127 = new com.codecharge.components.Link("GENERA_PASSWORD", "GENERA_PASSWORD", this );
            GENERA_PASSWORD__127.setType( com.codecharge.components.ControlType.TEXT );
            GENERA_PASSWORD__127.setHrefSourceValue( "AD4GeneraPwd.do" );
            GENERA_PASSWORD__127.setHrefType( "Page" );
            GENERA_PASSWORD__127.setConvertRule("Relative");
            GENERA_PASSWORD__127.setPreserveType(PreserveParameterType.GET);
            GENERA_PASSWORD__127.addParameter( new LinkParameter( "UTENTE", "UTENTE", ParameterSource.DATAFIELD) );
            AD4_UTENTI.add( GENERA_PASSWORD__127 );

            com.codecharge.components.Link SVUOTA_REG_ANAGRAFICA__117 = new com.codecharge.components.Link("SVUOTA_REG_ANAGRAFICA", "SVUOTA_REG_ANAGRAFICA", this );
            SVUOTA_REG_ANAGRAFICA__117.setType( com.codecharge.components.ControlType.TEXT );
            SVUOTA_REG_ANAGRAFICA__117.setHrefSourceValue( "AD4Utente" + Names.ACTION_SUFFIX );
            SVUOTA_REG_ANAGRAFICA__117.setHrefType( "Page" );
            SVUOTA_REG_ANAGRAFICA__117.setConvertRule("Relative");
            SVUOTA_REG_ANAGRAFICA__117.setPreserveType(PreserveParameterType.GET);
            SVUOTA_REG_ANAGRAFICA__117.addExcludeParam( "SOGGETTO" );
            SVUOTA_REG_ANAGRAFICA__117.addParameter( new LinkParameter( "SOGGETTO", "", ParameterSource.EXPRESSION) );
            AD4_UTENTI.add( SVUOTA_REG_ANAGRAFICA__117 );

            com.codecharge.components.Link MOD_REGISTRAZIONE_ANAGRAFICA__119 = new com.codecharge.components.Link("MOD_REGISTRAZIONE_ANAGRAFICA", "MOD_REGISTRAZIONE_ANAGRAFICA", this );
            MOD_REGISTRAZIONE_ANAGRAFICA__119.setType( com.codecharge.components.ControlType.TEXT );
            MOD_REGISTRAZIONE_ANAGRAFICA__119.setHrefSourceValue( "AD4SoggRicerca" + Names.ACTION_SUFFIX );
            MOD_REGISTRAZIONE_ANAGRAFICA__119.setHrefType( "Page" );
            MOD_REGISTRAZIONE_ANAGRAFICA__119.setConvertRule("Relative");
            MOD_REGISTRAZIONE_ANAGRAFICA__119.setPreserveType(PreserveParameterType.GET);
            MOD_REGISTRAZIONE_ANAGRAFICA__119.addParameter( new LinkParameter( "s_UTENTE", "UTENTE", ParameterSource.DATAFIELD) );
            AD4_UTENTI.add( MOD_REGISTRAZIONE_ANAGRAFICA__119 );

            com.codecharge.components.Label AUTENTICAZIONE_AD4__141 = new com.codecharge.components.Label("AUTENTICAZIONE_AD4", "AUTENTICAZIONE_AD4", this );
            AUTENTICAZIONE_AD4__141.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTI.add(AUTENTICAZIONE_AD4__141);

            com.codecharge.components.Label ELIMINA_CAAC__143 = new com.codecharge.components.Label("ELIMINA_CAAC", "ELIMINA_CAAC", this );
            ELIMINA_CAAC__143.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTI.add(ELIMINA_CAAC__143);

            com.codecharge.components.TextBox NOMINATIVO__132 = new com.codecharge.components.TextBox("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__132.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__132.setHtmlEncode( true );
            NOMINATIVO__132.addValidateHandler( new RequiredHandler( "Il valore nel campo NOMINATIVO è richiesto." ) );
            AD4_UTENTI.add( NOMINATIVO__132 );

            com.codecharge.components.Hidden IS_SO4_USER__139 = new com.codecharge.components.Hidden("IS_SO4_USER", "IS_SO4_USER", this );
            IS_SO4_USER__139.setType( com.codecharge.components.ControlType.INTEGER );
            IS_SO4_USER__139.setHtmlEncode( true );
            AD4_UTENTI.add( IS_SO4_USER__139 );

            com.codecharge.components.Hidden SOGGETTO__122 = new com.codecharge.components.Hidden("SOGGETTO", "SOGGETTO", this );
            SOGGETTO__122.setType( com.codecharge.components.ControlType.INTEGER );
            SOGGETTO__122.setHtmlEncode( true );
            AD4_UTENTI.add( SOGGETTO__122 );

            com.codecharge.components.Hidden ID_UTENTE__146 = new com.codecharge.components.Hidden("ID_UTENTE", "ID_UTENTE", this );
            ID_UTENTE__146.setType( com.codecharge.components.ControlType.TEXT );
            ID_UTENTE__146.setHtmlEncode( true );
            AD4_UTENTI.add( ID_UTENTE__146 );

            com.codecharge.components.Label IS_LDAPUSER__145 = new com.codecharge.components.Label("IS_LDAPUSER", "IS_LDAPUSER", this );
            IS_LDAPUSER__145.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTI.add(IS_LDAPUSER__145);

            com.codecharge.components.Label UTENTE_VIS__151 = new com.codecharge.components.Label("UTENTE_VIS", "UTENTE_VIS", this );
            UTENTE_VIS__151.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTI.add(UTENTE_VIS__151);

            com.codecharge.components.Hidden UTENTE__156 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__156.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__156.setHtmlEncode( true );
            AD4_UTENTI.add( UTENTE__156 );

            com.codecharge.components.TextBox PASSWORD__14 = new com.codecharge.components.TextBox("PASSWORD", "PASSWORD", this );
            PASSWORD__14.setType( com.codecharge.components.ControlType.TEXT );
            PASSWORD__14.setHtmlEncode( true );
            PASSWORD__14.setCaption( "PASSWORD" );
            AD4_UTENTI.add( PASSWORD__14 );

            com.codecharge.components.Label MODIFICA_PASSWORD__164 = new com.codecharge.components.Label("MODIFICA_PASSWORD", "MODIFICA_PASSWORD", this );
            MODIFICA_PASSWORD__164.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTI.add(MODIFICA_PASSWORD__164);

            com.codecharge.components.Hidden PWD_MODIFIED__88 = new com.codecharge.components.Hidden("PWD_MODIFIED", this);
            PWD_MODIFIED__88.setType( com.codecharge.components.ControlType.TEXT );
            PWD_MODIFIED__88.setHtmlEncode( true );
            AD4_UTENTI.add( PWD_MODIFIED__88 );

            com.codecharge.components.Label DATA_PASSWORD__39 = new com.codecharge.components.Label("DATA_PASSWORD", "DATA_PASSWORD", this );
            DATA_PASSWORD__39.setType( com.codecharge.components.ControlType.DATE );
            DATA_PASSWORD__39.setHtmlEncode( true );
            DATA_PASSWORD__39.setFormatPattern( "dd/MM/yyyy" );
            AD4_UTENTI.add(DATA_PASSWORD__39);

            com.codecharge.components.ListBox RINNOVO_PASSWORD__103 = new com.codecharge.components.ListBox("RINNOVO_PASSWORD", "RINNOVO_PASSWORD", this );
            RINNOVO_PASSWORD__103.setType( com.codecharge.components.ControlType.TEXT );
            RINNOVO_PASSWORD__103.setHtmlEncode( true );
            RINNOVO_PASSWORD__103.setCaption( "RINNOVO PASSWORD" );
            RINNOVO_PASSWORD__103.addValidateHandler( new RequiredHandler( "Il valore nel campo RINNOVO PASSWORD è richiesto." ) );
            AD4_UTENTI.add( RINNOVO_PASSWORD__103 );

            com.codecharge.components.ListBox STATO__24 = new com.codecharge.components.ListBox("STATO", "STATO", this );
            STATO__24.setType( com.codecharge.components.ControlType.TEXT );
            STATO__24.setHtmlEncode( true );
            STATO__24.setCaption( "STATO" );
            STATO__24.addValidateHandler( new RequiredHandler( "Il valore nel campo STATO è richiesto." ) );
            AD4_UTENTI.add( STATO__24 );

            com.codecharge.components.ListBox LINGUA__48 = new com.codecharge.components.ListBox("LINGUA", "LINGUA", this );
            LINGUA__48.setType( com.codecharge.components.ControlType.TEXT );
            LINGUA__48.setHtmlEncode( true );
            LINGUA__48.setCaption( "LINGUA" );
            LINGUA__48.setBoundColumn( "LINGUA" );
            LINGUA__48.setTextColumn( "DESCRIZIONE" );
            LINGUA__48.addValidateHandler( new RequiredHandler( "Il valore nel campo LINGUA è richiesto." ) );
            AD4_UTENTI.add( LINGUA__48 );

            com.codecharge.components.ListBox GRUPPO_LAVORO__31 = new com.codecharge.components.ListBox("GRUPPO_LAVORO", "GRUPPO_LAVORO", this );
            GRUPPO_LAVORO__31.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO_LAVORO__31.setHtmlEncode( true );
            GRUPPO_LAVORO__31.setCaption( "GRUPPO LAVORO" );
            GRUPPO_LAVORO__31.setBoundColumn( "GRUPPO_LAVORO" );
            GRUPPO_LAVORO__31.setTextColumn( "DESCRIZIONE" );
            AD4_UTENTI.add( GRUPPO_LAVORO__31 );

            com.codecharge.components.TextBox IMPORTANZA__79 = new com.codecharge.components.TextBox("IMPORTANZA", "IMPORTANZA", this );
            IMPORTANZA__79.setType( com.codecharge.components.ControlType.INTEGER );
            IMPORTANZA__79.setHtmlEncode( true );
            AD4_UTENTI.add( IMPORTANZA__79 );

            com.codecharge.components.CheckBox AMMINISTRATORE__162=  new com.codecharge.components.CheckBox( "AMMINISTRATORE", "AMMINISTRATORE", this );
            AMMINISTRATORE__162.setType( com.codecharge.components.ControlType.TEXT );
            AMMINISTRATORE__162.setCheckedValue( "S" );
            AMMINISTRATORE__162.setUncheckedValue( "N" );
            AD4_UTENTI.add(AMMINISTRATORE__162);

            com.codecharge.components.TextBox INFO_IDENTIFICAZIONE__163 = new com.codecharge.components.TextBox("INFO_IDENTIFICAZIONE", "INFO_IDENTIFICAZIONE", this );
            INFO_IDENTIFICAZIONE__163.setType( com.codecharge.components.ControlType.TEXT );
            INFO_IDENTIFICAZIONE__163.setHtmlEncode( true );
            AD4_UTENTI.add( INFO_IDENTIFICAZIONE__163 );

            com.codecharge.components.TextArea NOTE__22 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__22.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__22.setHtmlEncode( true );
            NOTE__22.addControlListener( new AD4_UTENTINOTEHandler());
            NOTE__22.setCaption( "NOTE" );
            AD4_UTENTI.add( NOTE__22 );

            com.codecharge.components.Label TITOLO_SOGG__116 = new com.codecharge.components.Label("TITOLO_SOGG", "TITOLO_SOGG", this );
            TITOLO_SOGG__116.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO_SOGG__116.setHtmlEncode( true );
            AD4_UTENTI.add(TITOLO_SOGG__116);

            com.codecharge.components.Label DATI_SOGGETTO__42 = new com.codecharge.components.Label("DATI_SOGGETTO", "DATI_SOGGETTO", this );
            DATI_SOGGETTO__42.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTI.add(DATI_SOGGETTO__42);

            com.codecharge.components.Label ULTIMO_TENTATIVO__18 = new com.codecharge.components.Label("ULTIMO_TENTATIVO", "ULTIMO_TENTATIVO", this );
            ULTIMO_TENTATIVO__18.setType( com.codecharge.components.ControlType.DATE );
            ULTIMO_TENTATIVO__18.setHtmlEncode( true );
            ULTIMO_TENTATIVO__18.setFormatPattern( "dd/MM/yyyy" );
            AD4_UTENTI.add(ULTIMO_TENTATIVO__18);

            com.codecharge.components.Label NUMERO_TENTATIVI__50 = new com.codecharge.components.Label("NUMERO_TENTATIVI", "NUMERO_TENTATIVI", this );
            NUMERO_TENTATIVI__50.setType( com.codecharge.components.ControlType.INTEGER );
            NUMERO_TENTATIVI__50.setHtmlEncode( true );
            AD4_UTENTI.add(NUMERO_TENTATIVI__50);

            com.codecharge.components.Label DATA_INSERIMENTO__80 = new com.codecharge.components.Label("DATA_INSERIMENTO", "DATA_INSERIMENTO", this );
            DATA_INSERIMENTO__80.setType( com.codecharge.components.ControlType.TEXT );
            DATA_INSERIMENTO__80.setHtmlEncode( true );
            AD4_UTENTI.add(DATA_INSERIMENTO__80);

            com.codecharge.components.Label UTENTE_DATA_AGGIORNAMENTO__54 = new com.codecharge.components.Label("UTENTE_DATA_AGGIORNAMENTO", "UTENTE_DATA_AGGIORNAMENTO", this );
            UTENTE_DATA_AGGIORNAMENTO__54.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_DATA_AGGIORNAMENTO__54.setHtmlEncode( true );
            AD4_UTENTI.add(UTENTE_DATA_AGGIORNAMENTO__54);

            com.codecharge.components.Button Button_Update__8 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__8.addButtonListener(new AD4_UTENTIButton_UpdateHandler());
            Button_Update__8.addExcludeParam( "ccsForm" );
            Button_Update__8.addExcludeParam( "Button_Update" );
            Button_Update__8.addExcludeParam( "SE_NUOVO" );
            Button_Update__8.setOperation( "Update" );
            AD4_UTENTI.add( Button_Update__8 );

            com.codecharge.components.Button Button_Delete__9 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__9.addButtonListener(new AD4_UTENTIButton_DeleteHandler());
            Button_Delete__9.addExcludeParam( "ccsForm" );
            Button_Delete__9.addExcludeParam( "Button_Delete" );
            Button_Delete__9.setOperation( "Delete" );
            AD4_UTENTI.add( Button_Delete__9 );

            com.codecharge.components.Button Button_Cancel__11 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__11.addButtonListener(new AD4_UTENTIButton_CancelHandler());
            Button_Cancel__11.addExcludeParam( "ccsForm" );
            Button_Cancel__11.addExcludeParam( "Button_Cancel" );
            Button_Cancel__11.setOperation( "Cancel" );
            AD4_UTENTI.add( Button_Cancel__11 );
            add(AD4_UTENTI);
        } // End definition of AD4_UTENTI record model.
//End AD4_UTENTI record

//AD4UtenteModel class tail @1-F5FC18C5
    }
}
//End AD4UtenteModel class tail

