//AD4IstanzaModel imports @1-7FAB0858
package ad4web.AD4Istanza;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4IstanzaModel imports

//AD4IstanzaModel class head @1-74168C09
public class AD4IstanzaModel extends com.codecharge.components.Page {
    public AD4IstanzaModel() {
        this( new CCSLocale(), null );
    }

    public AD4IstanzaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4IstanzaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4IstanzaModel class head

//page settings @1-84B67657
        super("AD4Istanza", locale );
        setResponse(response);
        addPageListener(new AD4IstanzaPageHandler());
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

//AD4_ISTANZE record @56-87980DFC
        
        /*
            Model of AD4_ISTANZE record defining.
        */
        {
            com.codecharge.components.Record AD4_ISTANZE = new com.codecharge.components.Record("AD4_ISTANZE");
            AD4_ISTANZE.setPageModel( this );
            AD4_ISTANZE.addExcludeParam( "ccsForm" );
            AD4_ISTANZE.addExcludeParam( "MVVC" );
            AD4_ISTANZE.setVisible( true );
            AD4_ISTANZE.setAllowInsert(false);
            AD4_ISTANZE.setPreserveType(PreserveParameterType.GET);
            AD4_ISTANZE.setReturnPage("AD4Istanza" + Names.ACTION_SUFFIX);
            AD4_ISTANZE.addRecordListener(new AD4_ISTANZERecordHandler());

            com.codecharge.components.Label DESC_PROGETTO__135 = new com.codecharge.components.Label("DESC_PROGETTO", "DESC_PROGETTO", this );
            DESC_PROGETTO__135.setType( com.codecharge.components.ControlType.TEXT );
            DESC_PROGETTO__135.setHtmlEncode( true );
            AD4_ISTANZE.add(DESC_PROGETTO__135);

            com.codecharge.components.Link CARATTERISTICHE__138 = new com.codecharge.components.Link("CARATTERISTICHE", "CARATTERISTICHE", this );
            CARATTERISTICHE__138.setType( com.codecharge.components.ControlType.TEXT );
            CARATTERISTICHE__138.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            CARATTERISTICHE__138.setHrefType( "Page" );
            CARATTERISTICHE__138.setConvertRule("Relative");
            CARATTERISTICHE__138.setPreserveType(PreserveParameterType.NONE);
            CARATTERISTICHE__138.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            CARATTERISTICHE__138.addParameter( new LinkParameter( "TIPO_ACCESSO", "", ParameterSource.EXPRESSION) );
            CARATTERISTICHE__138.addParameter( new LinkParameter( "ISTANZA", "ISTANZA_ORIG", ParameterSource.DATAFIELD) );
            AD4_ISTANZE.add( CARATTERISTICHE__138 );

            com.codecharge.components.Link ABILITAZIONI__136 = new com.codecharge.components.Link("ABILITAZIONI", "ABILITAZIONI", this );
            ABILITAZIONI__136.setType( com.codecharge.components.ControlType.TEXT );
            ABILITAZIONI__136.setHrefSourceValue( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX );
            ABILITAZIONI__136.setHrefType( "Page" );
            ABILITAZIONI__136.setConvertRule("Relative");
            ABILITAZIONI__136.setPreserveType(PreserveParameterType.GET);
            ABILITAZIONI__136.addExcludeParam( "MVVC" );
            ABILITAZIONI__136.addParameter( new LinkParameter( "ISTANZA", "ISTANZA_ORIG", ParameterSource.DATAFIELD) );
            AD4_ISTANZE.add( ABILITAZIONI__136 );

            com.codecharge.components.Link REGISTRO__169 = new com.codecharge.components.Link("REGISTRO", "REGISTRO", this );
            REGISTRO__169.setType( com.codecharge.components.ControlType.TEXT );
            REGISTRO__169.setHrefSourceValue( "AD4RegistroTree" + Names.ACTION_SUFFIX );
            REGISTRO__169.setHrefType( "Page" );
            REGISTRO__169.setConvertRule("Relative");
            REGISTRO__169.setPreserveType(PreserveParameterType.GET);
            REGISTRO__169.addParameter( new LinkParameter( "USRORCL", "USER_ORACLE", ParameterSource.DATAFIELD) );
            REGISTRO__169.addParameter( new LinkParameter( "MENU", "", ParameterSource.EXPRESSION) );
            AD4_ISTANZE.add( REGISTRO__169 );

            com.codecharge.components.Label ISTANZA__102 = new com.codecharge.components.Label("ISTANZA", "ISTANZA", this );
            ISTANZA__102.setType( com.codecharge.components.ControlType.TEXT );
            AD4_ISTANZE.add(ISTANZA__102);

            com.codecharge.components.Hidden ISTANZA_ORIG__103 = new com.codecharge.components.Hidden("ISTANZA_ORIG", "ISTANZA_ORIG", this );
            ISTANZA_ORIG__103.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA_ORIG__103.setHtmlEncode( true );
            AD4_ISTANZE.add( ISTANZA_ORIG__103 );

            com.codecharge.components.TextBox DESCRIZIONE__101 = new com.codecharge.components.TextBox("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__101.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__101.setHtmlEncode( true );
            DESCRIZIONE__101.setCaption( "DESCRIZIONE" );
            DESCRIZIONE__101.addValidateHandler( new RequiredHandler( "Il valore nel campo DESCRIZIONE è richiesto." ) );
            AD4_ISTANZE.add( DESCRIZIONE__101 );

            com.codecharge.components.ListBox ENTE__104 = new com.codecharge.components.ListBox("ENTE", "ENTE", this );
            ENTE__104.setType( com.codecharge.components.ControlType.TEXT );
            ENTE__104.setHtmlEncode( true );
            ENTE__104.setCaption( "ENTE" );
            ENTE__104.setBoundColumn( "ENTE" );
            ENTE__104.setTextColumn( "DESCRIZIONE" );
            ENTE__104.addValidateHandler( new RequiredHandler( "Il valore nel campo ENTE è richiesto." ) );
            AD4_ISTANZE.add( ENTE__104 );

            com.codecharge.components.Hidden PROGETTO__142 = new com.codecharge.components.Hidden("PROGETTO", "PROGETTO", this );
            PROGETTO__142.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO__142.setHtmlEncode( true );
            AD4_ISTANZE.add( PROGETTO__142 );

            com.codecharge.components.ListBox LINGUA__107 = new com.codecharge.components.ListBox("LINGUA", "LINGUA", this );
            LINGUA__107.setType( com.codecharge.components.ControlType.TEXT );
            LINGUA__107.setHtmlEncode( true );
            LINGUA__107.setCaption( "LINGUA" );
            LINGUA__107.setBoundColumn( "LINGUA" );
            LINGUA__107.setTextColumn( "DESCRIZIONE" );
            LINGUA__107.addValidateHandler( new RequiredHandler( "Il valore nel campo LINGUA è richiesto." ) );
            AD4_ISTANZE.add( LINGUA__107 );

            com.codecharge.components.TextBox DISLOCAZIONE__105 = new com.codecharge.components.TextBox("DISLOCAZIONE", "DISLOCAZIONE", this );
            DISLOCAZIONE__105.setType( com.codecharge.components.ControlType.TEXT );
            DISLOCAZIONE__105.setHtmlEncode( true );
            DISLOCAZIONE__105.setCaption( "DISLOCAZIONE" );
            DISLOCAZIONE__105.addValidateHandler( new RequiredHandler( "Il valore nel campo DISLOCAZIONE è richiesto." ) );
            AD4_ISTANZE.add( DISLOCAZIONE__105 );

            com.codecharge.components.TextBox DISLOCAZIONE_TEMPORANEA__106 = new com.codecharge.components.TextBox("DISLOCAZIONE_TEMPORANEA", "DISLOCAZIONE_TEMPORANEA", this );
            DISLOCAZIONE_TEMPORANEA__106.setType( com.codecharge.components.ControlType.TEXT );
            DISLOCAZIONE_TEMPORANEA__106.setHtmlEncode( true );
            DISLOCAZIONE_TEMPORANEA__106.setCaption( "DISLOCAZIONE TEMPORANEA" );
            AD4_ISTANZE.add( DISLOCAZIONE_TEMPORANEA__106 );

            com.codecharge.components.TextBox USER_ORACLE__108 = new com.codecharge.components.TextBox("USER_ORACLE", "USER_ORACLE", this );
            USER_ORACLE__108.setType( com.codecharge.components.ControlType.TEXT );
            USER_ORACLE__108.setHtmlEncode( true );
            USER_ORACLE__108.setCaption( "USER ORACLE" );
            USER_ORACLE__108.addValidateHandler( new RequiredHandler( "Il valore nel campo USER ORACLE è richiesto." ) );
            AD4_ISTANZE.add( USER_ORACLE__108 );

            com.codecharge.components.TextBox PASSWORD_ORACLE__70 = new com.codecharge.components.TextBox("PASSWORD_ORACLE", "PASSWORD_ORACLE", this );
            PASSWORD_ORACLE__70.setType( com.codecharge.components.ControlType.TEXT );
            PASSWORD_ORACLE__70.setHtmlEncode( true );
            PASSWORD_ORACLE__70.setCaption( "PASSWORD ORACLE" );
            PASSWORD_ORACLE__70.addValidateHandler( new RequiredHandler( "Il valore nel campo PASSWORD ORACLE è richiesto." ) );
            AD4_ISTANZE.add( PASSWORD_ORACLE__70 );

            com.codecharge.components.Hidden PWD_MODIFIED__99 = new com.codecharge.components.Hidden("PWD_MODIFIED", this);
            PWD_MODIFIED__99.setType( com.codecharge.components.ControlType.TEXT );
            PWD_MODIFIED__99.setHtmlEncode( true );
            AD4_ISTANZE.add( PWD_MODIFIED__99 );

            com.codecharge.components.ListBox ISTANZA_AMMINISTRATORE__176 = new com.codecharge.components.ListBox("ISTANZA_AMMINISTRATORE", "ISTANZA_AMMINISTRATORE", this );
            ISTANZA_AMMINISTRATORE__176.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA_AMMINISTRATORE__176.setHtmlEncode( true );
            ISTANZA_AMMINISTRATORE__176.setCaption( "ISTANZA_AMMINISTRATORE" );
            ISTANZA_AMMINISTRATORE__176.setBoundColumn( "ISTANZA" );
            ISTANZA_AMMINISTRATORE__176.setTextColumn( "DES_ISTANZA" );
            AD4_ISTANZE.add( ISTANZA_AMMINISTRATORE__176 );

            com.codecharge.components.Hidden ISTANZA_AMMINISTRATORE_ORIG__179 = new com.codecharge.components.Hidden("ISTANZA_AMMINISTRATORE_ORIG", "ISTANZA_AMMINISTRATORE_ORIG", this );
            ISTANZA_AMMINISTRATORE_ORIG__179.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA_AMMINISTRATORE_ORIG__179.setHtmlEncode( true );
            AD4_ISTANZE.add( ISTANZA_AMMINISTRATORE_ORIG__179 );

            com.codecharge.components.TextBox LINK_ORACLE__109 = new com.codecharge.components.TextBox("LINK_ORACLE", "LINK_ORACLE", this );
            LINK_ORACLE__109.setType( com.codecharge.components.ControlType.TEXT );
            LINK_ORACLE__109.setHtmlEncode( true );
            LINK_ORACLE__109.setCaption( "LINK ORACLE" );
            AD4_ISTANZE.add( LINK_ORACLE__109 );

            com.codecharge.components.TextBox DATABASE_LINK__173 = new com.codecharge.components.TextBox("DATABASE_LINK", "DATABASE_LINK", this );
            DATABASE_LINK__173.setType( com.codecharge.components.ControlType.TEXT );
            DATABASE_LINK__173.setHtmlEncode( true );
            DATABASE_LINK__173.setCaption( "DATABASE LINK" );
            AD4_ISTANZE.add( DATABASE_LINK__173 );

            com.codecharge.components.TextBox DATABASE_DRIVER__174 = new com.codecharge.components.TextBox("DATABASE_DRIVER", "DATABASE_DRIVER", this );
            DATABASE_DRIVER__174.setType( com.codecharge.components.ControlType.TEXT );
            DATABASE_DRIVER__174.setHtmlEncode( true );
            DATABASE_DRIVER__174.setCaption( "DATABASE_DRIVER" );
            AD4_ISTANZE.add( DATABASE_DRIVER__174 );

            com.codecharge.components.TextBox SERVIZIO__113 = new com.codecharge.components.TextBox("SERVIZIO", "SERVIZIO", this );
            SERVIZIO__113.setType( com.codecharge.components.ControlType.TEXT );
            SERVIZIO__113.setHtmlEncode( true );
            SERVIZIO__113.setCaption( "SERVIZIO" );
            AD4_ISTANZE.add( SERVIZIO__113 );

            com.codecharge.components.TextArea NOTE__114 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__114.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__114.setHtmlEncode( true );
            NOTE__114.addControlListener( new AD4_ISTANZENOTEHandler());
            NOTE__114.setCaption( "NOTE" );
            AD4_ISTANZE.add( NOTE__114 );

            com.codecharge.components.Label INSTALLAZIONE__116 = new com.codecharge.components.Label("INSTALLAZIONE", "INSTALLAZIONE", this );
            INSTALLAZIONE__116.setType( com.codecharge.components.ControlType.TEXT );
            INSTALLAZIONE__116.setHtmlEncode( true );
            AD4_ISTANZE.add(INSTALLAZIONE__116);

            com.codecharge.components.Label VERSIONE__117 = new com.codecharge.components.Label("VERSIONE", "VERSIONE", this );
            VERSIONE__117.setType( com.codecharge.components.ControlType.TEXT );
            VERSIONE__117.setHtmlEncode( true );
            AD4_ISTANZE.add(VERSIONE__117);

            com.codecharge.components.Button Button_Update__57 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__57.addButtonListener(new AD4_ISTANZEButton_UpdateHandler());
            Button_Update__57.addExcludeParam( "ccsForm" );
            Button_Update__57.addExcludeParam( "Button_Update" );
            Button_Update__57.setOperation( "Update" );
            AD4_ISTANZE.add( Button_Update__57 );

            com.codecharge.components.Button Button_Delete__58 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__58.addButtonListener(new AD4_ISTANZEButton_DeleteHandler());
            Button_Delete__58.addExcludeParam( "ccsForm" );
            Button_Delete__58.addExcludeParam( "Button_Delete" );
            Button_Delete__58.setOperation( "Delete" );
            AD4_ISTANZE.add( Button_Delete__58 );

            com.codecharge.components.Button Button_Cancel__60 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__60.addButtonListener(new AD4_ISTANZEButton_CancelHandler());
            Button_Cancel__60.addExcludeParam( "ccsForm" );
            Button_Cancel__60.addExcludeParam( "Button_Cancel" );
            Button_Cancel__60.setOperation( "Cancel" );
            AD4_ISTANZE.add( Button_Cancel__60 );
            add(AD4_ISTANZE);
        } // End definition of AD4_ISTANZE record model.
//End AD4_ISTANZE record

//AD4IstanzaModel class tail @1-F5FC18C5
    }
}
//End AD4IstanzaModel class tail
