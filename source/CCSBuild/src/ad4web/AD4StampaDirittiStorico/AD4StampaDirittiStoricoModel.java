//AD4StampaDirittiStoricoModel imports @1-0901B7D3
package ad4web.AD4StampaDirittiStorico;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4StampaDirittiStoricoModel imports

//AD4StampaDirittiStoricoModel class head @1-7ACE7118
public class AD4StampaDirittiStoricoModel extends com.codecharge.components.Page {
    public AD4StampaDirittiStoricoModel() {
        this( new CCSLocale(), null );
    }

    public AD4StampaDirittiStoricoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4StampaDirittiStoricoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4StampaDirittiStoricoModel class head

//page settings @1-5BD4F214
        super("AD4StampaDirittiStorico", locale );
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

//PARAMETRI_STAMPA record @6-39A875F6
        
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
            PARAMETRI_STAMPA.setReturnPage("AD4StampaDirittiStorico" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox UTENTE__61 = new com.codecharge.components.ListBox("UTENTE", "UTENTE", this );
            UTENTE__61.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__61.setHtmlEncode( true );
            UTENTE__61.setCaption( "Tipo Ordinamento" );
            UTENTE__61.setBoundColumn( "UTENTE" );
            UTENTE__61.setTextColumn( "NOMINATIVO" );
            PARAMETRI_STAMPA.add( UTENTE__61 );

            com.codecharge.components.ListBox MODULO__34 = new com.codecharge.components.ListBox("MODULO", "MODULO", this );
            MODULO__34.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__34.setHtmlEncode( true );
            MODULO__34.setCaption( "Modulo" );
            MODULO__34.setBoundColumn( "MODULO" );
            MODULO__34.setTextColumn( "DESCRIZIONE" );
            PARAMETRI_STAMPA.add( MODULO__34 );

            com.codecharge.components.TextBox DAL__66 = new com.codecharge.components.TextBox("DAL", "DAL", this );
            DAL__66.setType( com.codecharge.components.ControlType.DATE );
            DAL__66.setHtmlEncode( true );
            DAL__66.setFormatPattern( "dd/MM/yyyy" );
            DAL__66.setCaption( "Dal" );
            PARAMETRI_STAMPA.add( DAL__66 );
            com.codecharge.components.DatePicker DatePickerDAL__64 = new com.codecharge.components.DatePicker("DatePickerDAL", this);
            DatePickerDAL__64.setControlName("DAL");
            DatePickerDAL__64.setStyleName("../Themes/Afc/Style.css");
            PARAMETRI_STAMPA.add(DatePickerDAL__64);

            com.codecharge.components.TextBox AL__65 = new com.codecharge.components.TextBox("AL", "AL", this );
            AL__65.setType( com.codecharge.components.ControlType.DATE );
            AL__65.setHtmlEncode( true );
            AL__65.setFormatPattern( "dd/MM/yyyy" );
            AL__65.setCaption( "Al" );
            PARAMETRI_STAMPA.add( AL__65 );
            com.codecharge.components.DatePicker DatePickerAL__67 = new com.codecharge.components.DatePicker("DatePickerAL", this);
            DatePickerAL__67.setControlName("AL");
            DatePickerAL__67.setStyleName("../Themes/Afc/Style.css");
            PARAMETRI_STAMPA.add(DatePickerAL__67);

            com.codecharge.components.ListBox FORMATO__68 = new com.codecharge.components.ListBox("FORMATO", "FORMATO", this );
            FORMATO__68.setType( com.codecharge.components.ControlType.TEXT );
            FORMATO__68.setHtmlEncode( true );
            FORMATO__68.setCaption( "Formato" );
            FORMATO__68.setBoundColumn( "CODICE" );
            FORMATO__68.setTextColumn( "DESCRIZIONE" );
            FORMATO__68.addValidateHandler( new RequiredHandler( "Il valore nel campo Formato è richiesto." ) );
            PARAMETRI_STAMPA.add( FORMATO__68 );

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

//AD4StampaDirittiStoricoModel class tail @1-F5FC18C5
    }
}
//End AD4StampaDirittiStoricoModel class tail
