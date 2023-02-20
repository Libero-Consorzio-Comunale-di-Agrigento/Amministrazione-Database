//AD4ServizioModel imports @1-3618056E
package ad4web.AD4Servizio;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4ServizioModel imports

//AD4ServizioModel class head @1-680447B1
public class AD4ServizioModel extends com.codecharge.components.Page {
    public AD4ServizioModel() {
        this( new CCSLocale(), null );
    }

    public AD4ServizioModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4ServizioModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4ServizioModel class head

//page settings @1-988B9C73
        super("AD4Servizio", locale );
        setResponse(response);
        addPageListener(new AD4ServizioPageHandler());
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
        } // end page
//End page settings

//AD4_SERVIZI record @38-6ABF8D6C
        
        /*
            Model of AD4_SERVIZI record defining.
        */
        {
            com.codecharge.components.Record AD4_SERVIZI = new com.codecharge.components.Record("AD4_SERVIZI");
            AD4_SERVIZI.setPageModel( this );
            AD4_SERVIZI.addExcludeParam( "ccsForm" );
            AD4_SERVIZI.setVisible( true );
            AD4_SERVIZI.setAllowInsert(false);
            AD4_SERVIZI.setPreserveType(PreserveParameterType.GET);
            AD4_SERVIZI.setReturnPage("AD4Servizio" + Names.ACTION_SUFFIX);
            AD4_SERVIZI.addRecordListener(new AD4_SERVIZIRecordHandler());

            com.codecharge.components.Label DESC_PROGETTO__175 = new com.codecharge.components.Label("DESC_PROGETTO", "DESC_PROGETTO", this );
            DESC_PROGETTO__175.setType( com.codecharge.components.ControlType.TEXT );
            DESC_PROGETTO__175.setHtmlEncode( true );
            AD4_SERVIZI.add(DESC_PROGETTO__175);

            com.codecharge.components.Link ABILITAZIONI__176 = new com.codecharge.components.Link("ABILITAZIONI", "ABILITAZIONI", this );
            ABILITAZIONI__176.setType( com.codecharge.components.ControlType.TEXT );
            ABILITAZIONI__176.setHrefSourceValue( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX );
            ABILITAZIONI__176.setHrefType( "Page" );
            ABILITAZIONI__176.setConvertRule("Relative");
            ABILITAZIONI__176.setPreserveType(PreserveParameterType.GET);
            ABILITAZIONI__176.addExcludeParam( "MVVC" );
            ABILITAZIONI__176.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            ABILITAZIONI__176.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            AD4_SERVIZI.add( ABILITAZIONI__176 );

            com.codecharge.components.TextBox ID_SERVIZIO__49 = new com.codecharge.components.TextBox("ID_SERVIZIO", "ID_SERVIZIO", this );
            ID_SERVIZIO__49.setType( com.codecharge.components.ControlType.INTEGER );
            ID_SERVIZIO__49.setHtmlEncode( true );
            ID_SERVIZIO__49.setCaption( "ID_SERVIZIO" );
            AD4_SERVIZI.add( ID_SERVIZIO__49 );

            com.codecharge.components.ListBox MODULO__46 = new com.codecharge.components.ListBox("MODULO", "MODULO", this );
            MODULO__46.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__46.setHtmlEncode( true );
            MODULO__46.setCaption( "MODULO" );
            MODULO__46.setBoundColumn( "MODULO" );
            MODULO__46.setTextColumn( "DESCRIZIONE" );
            MODULO__46.addValidateHandler( new RequiredHandler( "Il valore nel campo MODULO è richiesto." ) );
            AD4_SERVIZI.add( MODULO__46 );

            com.codecharge.components.ListBox ISTANZA__47 = new com.codecharge.components.ListBox("ISTANZA", "ISTANZA", this );
            ISTANZA__47.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA__47.setHtmlEncode( true );
            ISTANZA__47.setCaption( "ISTANZA" );
            ISTANZA__47.setBoundColumn( "ISTANZA" );
            ISTANZA__47.setTextColumn( "DESCRIZIONE" );
            ISTANZA__47.addValidateHandler( new RequiredHandler( "Il valore nel campo ISTANZA è richiesto." ) );
            AD4_SERVIZI.add( ISTANZA__47 );

            com.codecharge.components.ListBox LIVELLO__48 = new com.codecharge.components.ListBox("LIVELLO", "LIVELLO", this );
            LIVELLO__48.setType( com.codecharge.components.ControlType.TEXT );
            LIVELLO__48.setHtmlEncode( true );
            LIVELLO__48.setCaption( "LIVELLO" );
            LIVELLO__48.setBoundColumn( "LIVELLO" );
            LIVELLO__48.setTextColumn( "DESCRIZIONE" );
            AD4_SERVIZI.add( LIVELLO__48 );

            com.codecharge.components.ListBox ABILITAZIONE__53 = new com.codecharge.components.ListBox("ABILITAZIONE", "ABILITAZIONE", this );
            ABILITAZIONE__53.setType( com.codecharge.components.ControlType.TEXT );
            ABILITAZIONE__53.setHtmlEncode( true );
            ABILITAZIONE__53.setCaption( "ABILITAZIONE" );
            ABILITAZIONE__53.addValidateHandler( new RequiredHandler( "Il valore nel campo ABILITAZIONE è richiesto." ) );
            AD4_SERVIZI.add( ABILITAZIONE__53 );

            com.codecharge.components.ListBox MULTIPLO__142 = new com.codecharge.components.ListBox("MULTIPLO", "MULTIPLO", this );
            MULTIPLO__142.setType( com.codecharge.components.ControlType.TEXT );
            MULTIPLO__142.setHtmlEncode( true );
            MULTIPLO__142.addValidateHandler( new RequiredHandler( "Il valore nel campo MULTIPLO è richiesto." ) );
            AD4_SERVIZI.add( MULTIPLO__142 );

            com.codecharge.components.ListBox GRUPPO_ABILITAZIONE__50 = new com.codecharge.components.ListBox("GRUPPO_ABILITAZIONE", "GRUPPO_ABILITAZIONE", this );
            GRUPPO_ABILITAZIONE__50.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO_ABILITAZIONE__50.setHtmlEncode( true );
            GRUPPO_ABILITAZIONE__50.setCaption( "GRUPPO_ABILITAZIONE" );
            GRUPPO_ABILITAZIONE__50.setBoundColumn( "UTENTE" );
            GRUPPO_ABILITAZIONE__50.setTextColumn( "NOMINATIVO" );
            GRUPPO_ABILITAZIONE__50.addValidateHandler( new RequiredHandler( "Il valore nel campo GRUPPO_ABILITAZIONE è richiesto." ) );
            AD4_SERVIZI.add( GRUPPO_ABILITAZIONE__50 );

            com.codecharge.components.ListBox TIPO_NOTIFICA__51 = new com.codecharge.components.ListBox("TIPO_NOTIFICA", "TIPO_NOTIFICA", this );
            TIPO_NOTIFICA__51.setType( com.codecharge.components.ControlType.TEXT );
            TIPO_NOTIFICA__51.setHtmlEncode( true );
            TIPO_NOTIFICA__51.setCaption( "TIPO_NOTIFICA" );
            AD4_SERVIZI.add( TIPO_NOTIFICA__51 );

            com.codecharge.components.TextBox TAG_CIM__188 = new com.codecharge.components.TextBox("TAG_CIM", "TAG_CIM", this );
            TAG_CIM__188.setType( com.codecharge.components.ControlType.TEXT );
            TAG_CIM__188.setHtmlEncode( true );
            AD4_SERVIZI.add( TAG_CIM__188 );

            com.codecharge.components.TextBox MAIL_NOTIFICHE__52 = new com.codecharge.components.TextBox("MAIL_NOTIFICHE", "MAIL_NOTIFICHE", this );
            MAIL_NOTIFICHE__52.setType( com.codecharge.components.ControlType.TEXT );
            MAIL_NOTIFICHE__52.setHtmlEncode( true );
            MAIL_NOTIFICHE__52.setCaption( "MAIL_NOTIFICHE" );
            AD4_SERVIZI.add( MAIL_NOTIFICHE__52 );

            com.codecharge.components.TextBox CCR_NOTIFICHE__141 = new com.codecharge.components.TextBox("CCR_NOTIFICHE", "CCR_NOTIFICHE", this );
            CCR_NOTIFICHE__141.setType( com.codecharge.components.ControlType.TEXT );
            CCR_NOTIFICHE__141.setHtmlEncode( true );
            CCR_NOTIFICHE__141.setCaption( "CCR_NOTIFICHE" );
            AD4_SERVIZI.add( CCR_NOTIFICHE__141 );

            com.codecharge.components.CheckBox RECUPERO_PASSWORD__187=  new com.codecharge.components.CheckBox( "RECUPERO_PASSWORD", "RECUPERO_PASSWORD", this );
            RECUPERO_PASSWORD__187.setType( com.codecharge.components.ControlType.TEXT );
            RECUPERO_PASSWORD__187.setCheckedValue( "S" );
            RECUPERO_PASSWORD__187.setUncheckedValue( "N" );
            AD4_SERVIZI.add(RECUPERO_PASSWORD__187);

            com.codecharge.components.Button Button_Update__40 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__40.addButtonListener(new AD4_SERVIZIButton_UpdateHandler());
            Button_Update__40.addExcludeParam( "ccsForm" );
            Button_Update__40.addExcludeParam( "Button_Update" );
            Button_Update__40.setOperation( "Update" );
            AD4_SERVIZI.add( Button_Update__40 );

            com.codecharge.components.Button Button_Delete__41 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__41.addButtonListener(new AD4_SERVIZIButton_DeleteHandler());
            Button_Delete__41.addExcludeParam( "ccsForm" );
            Button_Delete__41.addExcludeParam( "Button_Delete" );
            Button_Delete__41.setOperation( "Delete" );
            AD4_SERVIZI.add( Button_Delete__41 );

            com.codecharge.components.Button Button_Cancel__43 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__43.addButtonListener(new AD4_SERVIZIButton_CancelHandler());
            Button_Cancel__43.addExcludeParam( "ccsForm" );
            Button_Cancel__43.addExcludeParam( "Button_Cancel" );
            Button_Cancel__43.setOperation( "Cancel" );
            AD4_SERVIZI.add( Button_Cancel__43 );
            add(AD4_SERVIZI);
        } // End definition of AD4_SERVIZI record model.
//End AD4_SERVIZI record

//AD4ServizioModel class tail @1-F5FC18C5
    }
}
//End AD4ServizioModel class tail
