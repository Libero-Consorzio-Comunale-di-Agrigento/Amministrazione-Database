//AD4StampaUtentiModel imports @1-41E34DC9
package ad4web.AD4StampaUtenti;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4StampaUtentiModel imports

//AD4StampaUtentiModel class head @1-6D499D91
public class AD4StampaUtentiModel extends com.codecharge.components.Page {
    public AD4StampaUtentiModel() {
        this( new CCSLocale(), null );
    }

    public AD4StampaUtentiModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4StampaUtentiModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4StampaUtentiModel class head

//page settings @1-9A57F662
        super("AD4StampaUtenti", locale );
        setResponse(response);
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__38 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__38.setVisible( true );
            add( Guida__38 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//PARAMETRI_STAMPA record @6-B8C4D85D
        
        /*
            Model of PARAMETRI_STAMPA record defining.
        */
        {
            com.codecharge.components.Record PARAMETRI_STAMPA = new com.codecharge.components.Record("PARAMETRI_STAMPA");
            PARAMETRI_STAMPA.setPageModel( this );
            PARAMETRI_STAMPA.addExcludeParam( "ccsForm" );
            PARAMETRI_STAMPA.setVisible( true );
            PARAMETRI_STAMPA.setAllowInsert(false);
            PARAMETRI_STAMPA.setAllowUpdate(false);
            PARAMETRI_STAMPA.setAllowDelete(false);
            PARAMETRI_STAMPA.setPreserveType(PreserveParameterType.ALL);
            PARAMETRI_STAMPA.setReturnPage("AD4StampaUtenti" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox TIPO_UTENTE__61 = new com.codecharge.components.ListBox("TIPO_UTENTE", "TIPO_UTENTE", this );
            TIPO_UTENTE__61.setType( com.codecharge.components.ControlType.TEXT );
            TIPO_UTENTE__61.setHtmlEncode( true );
            TIPO_UTENTE__61.setCaption( "Tipo Utente" );
            TIPO_UTENTE__61.setBoundColumn( "CODICE" );
            TIPO_UTENTE__61.setTextColumn( "DESCRIZIONE" );
            TIPO_UTENTE__61.addValidateHandler( new RequiredHandler( "Il valore nel campo Tipo Utente è richiesto." ) );
            PARAMETRI_STAMPA.add( TIPO_UTENTE__61 );

            com.codecharge.components.ListBox MODULO__34 = new com.codecharge.components.ListBox("MODULO", "MODULO", this );
            MODULO__34.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__34.setHtmlEncode( true );
            MODULO__34.setCaption( "Modulo" );
            MODULO__34.setBoundColumn( "MODULO" );
            MODULO__34.setTextColumn( "DESCRIZIONE" );
            PARAMETRI_STAMPA.add( MODULO__34 );

            com.codecharge.components.ListBox ISTANZA__62 = new com.codecharge.components.ListBox("ISTANZA", "ISTANZA", this );
            ISTANZA__62.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA__62.setHtmlEncode( true );
            ISTANZA__62.setCaption( "Istanza" );
            ISTANZA__62.setBoundColumn( "ISTANZA" );
            ISTANZA__62.setTextColumn( "DESCRIZIONE" );
            PARAMETRI_STAMPA.add( ISTANZA__62 );

            com.codecharge.components.ListBox FORMATO__63 = new com.codecharge.components.ListBox("FORMATO", "FORMATO", this );
            FORMATO__63.setType( com.codecharge.components.ControlType.TEXT );
            FORMATO__63.setHtmlEncode( true );
            FORMATO__63.setCaption( "Formato" );
            FORMATO__63.setBoundColumn( "CODICE" );
            FORMATO__63.setTextColumn( "DESCRIZIONE" );
            FORMATO__63.addValidateHandler( new RequiredHandler( "Il valore nel campo Formato è richiesto." ) );
            PARAMETRI_STAMPA.add( FORMATO__63 );

            com.codecharge.components.Hidden CONTEXT__51 = new com.codecharge.components.Hidden("CONTEXT", "CONTEXT", this );
            CONTEXT__51.setType( com.codecharge.components.ControlType.TEXT );
            CONTEXT__51.setHtmlEncode( true );
            PARAMETRI_STAMPA.add( CONTEXT__51 );

            com.codecharge.components.Hidden DATA_SOURCE__48 = new com.codecharge.components.Hidden("DATA_SOURCE", "DS", this );
            DATA_SOURCE__48.setType( com.codecharge.components.ControlType.TEXT );
            DATA_SOURCE__48.setHtmlEncode( true );
            PARAMETRI_STAMPA.add( DATA_SOURCE__48 );

            com.codecharge.components.Button Button1__43 = new com.codecharge.components.Button("Button1", this);
            Button1__43.addExcludeParam( "ccsForm" );
            Button1__43.addExcludeParam( "Button1" );
            PARAMETRI_STAMPA.add( Button1__43 );
            add(PARAMETRI_STAMPA);
        } // End definition of PARAMETRI_STAMPA record model.
//End PARAMETRI_STAMPA record

//AD4StampaUtentiModel class tail @1-F5FC18C5
    }
}
//End AD4StampaUtentiModel class tail
