//AD4StampaVariazioniUtentiModel imports @1-AFE693CB
package ad4web.AD4StampaVariazioniUtenti;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4StampaVariazioniUtentiModel imports

//AD4StampaVariazioniUtentiModel class head @1-85353176
public class AD4StampaVariazioniUtentiModel extends com.codecharge.components.Page {
    public AD4StampaVariazioniUtentiModel() {
        this( new CCSLocale(), null );
    }

    public AD4StampaVariazioniUtentiModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4StampaVariazioniUtentiModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4StampaVariazioniUtentiModel class head

//page settings @1-A9EAD412
        super("AD4StampaVariazioniUtenti", locale );
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

//PARAMETRI record @6-1CC5CB25
        
        /*
            Model of PARAMETRI record defining.
        */
        {
            com.codecharge.components.Record PARAMETRI = new com.codecharge.components.Record("PARAMETRI");
            PARAMETRI.setPageModel( this );
            PARAMETRI.addExcludeParam( "ccsForm" );
            PARAMETRI.setVisible( true );
            PARAMETRI.setAllowInsert(false);
            PARAMETRI.setAllowUpdate(false);
            PARAMETRI.setAllowDelete(false);
            PARAMETRI.setPreserveType(PreserveParameterType.GET);
            PARAMETRI.setReturnPage("AD4StampaVariazioniUtenti" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox COD_UTENTE_ACCESSO__52 = new com.codecharge.components.ListBox("COD_UTENTE_ACCESSO", "", this );
            COD_UTENTE_ACCESSO__52.setType( com.codecharge.components.ControlType.TEXT );
            COD_UTENTE_ACCESSO__52.setHtmlEncode( true );
            COD_UTENTE_ACCESSO__52.setCaption( "Utente" );
            COD_UTENTE_ACCESSO__52.setBoundColumn( "UTENTE" );
            COD_UTENTE_ACCESSO__52.setTextColumn( "NOMINATIVO" );
            PARAMETRI.add( COD_UTENTE_ACCESSO__52 );

            com.codecharge.components.TextBox DAL__34 = new com.codecharge.components.TextBox("DAL", "S_DAL", this );
            DAL__34.setType( com.codecharge.components.ControlType.DATE );
            DAL__34.setHtmlEncode( true );
            DAL__34.setFormatPattern( "dd/MM/yyyy" );
            PARAMETRI.add( DAL__34 );
            com.codecharge.components.DatePicker DatePicker_DAL__50 = new com.codecharge.components.DatePicker("DatePicker_DAL", this);
            DatePicker_DAL__50.setControlName("DAL");
            DatePicker_DAL__50.setStyleName("../Themes/Afc/Style.css");
            PARAMETRI.add(DatePicker_DAL__50);

            com.codecharge.components.TextBox AL__36 = new com.codecharge.components.TextBox("AL", "S_AL", this );
            AL__36.setType( com.codecharge.components.ControlType.DATE );
            AL__36.setHtmlEncode( true );
            AL__36.setFormatPattern( "dd/MM/yyyy" );
            PARAMETRI.add( AL__36 );
            com.codecharge.components.DatePicker DatePicker_AL__51 = new com.codecharge.components.DatePicker("DatePicker_AL", this);
            DatePicker_AL__51.setControlName("AL");
            DatePicker_AL__51.setStyleName("../Themes/Afc/Style.css");
            PARAMETRI.add(DatePicker_AL__51);

            com.codecharge.components.ListBox FORMATO__56 = new com.codecharge.components.ListBox("FORMATO", "FORMATO", this );
            FORMATO__56.setType( com.codecharge.components.ControlType.TEXT );
            FORMATO__56.setHtmlEncode( true );
            FORMATO__56.setCaption( "Formato" );
            FORMATO__56.setBoundColumn( "CODICE" );
            FORMATO__56.setTextColumn( "DESCRIZIONE" );
            FORMATO__56.addValidateHandler( new RequiredHandler( "Il valore nel campo Formato è richiesto." ) );
            PARAMETRI.add( FORMATO__56 );

            com.codecharge.components.Hidden CONTEXT__54 = new com.codecharge.components.Hidden("CONTEXT", "CONTEXT", this );
            CONTEXT__54.setType( com.codecharge.components.ControlType.TEXT );
            CONTEXT__54.setHtmlEncode( true );
            PARAMETRI.add( CONTEXT__54 );

            com.codecharge.components.Hidden DATA_SOURCE__48 = new com.codecharge.components.Hidden("DATA_SOURCE", "DS", this );
            DATA_SOURCE__48.setType( com.codecharge.components.ControlType.TEXT );
            DATA_SOURCE__48.setHtmlEncode( true );
            PARAMETRI.add( DATA_SOURCE__48 );

            com.codecharge.components.Button Button1__43 = new com.codecharge.components.Button("Button1", this);
            Button1__43.addExcludeParam( "ccsForm" );
            Button1__43.addExcludeParam( "Button1" );
            PARAMETRI.add( Button1__43 );
            add(PARAMETRI);
        } // End definition of PARAMETRI record model.
//End PARAMETRI record

//AD4StampaVariazioniUtentiModel class tail @1-F5FC18C5
    }
}
//End AD4StampaVariazioniUtentiModel class tail
