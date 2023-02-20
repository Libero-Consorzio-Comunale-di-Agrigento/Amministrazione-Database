//AD4EnteModel imports @1-B701B821
package ad4web.AD4Ente;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4EnteModel imports

//AD4EnteModel class head @1-4BD7C6F0
public class AD4EnteModel extends com.codecharge.components.Page {
    public AD4EnteModel() {
        this( new CCSLocale(), null );
    }

    public AD4EnteModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4EnteModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4EnteModel class head

//page settings @1-218D2141
        super("AD4Ente", locale );
        setResponse(response);
        addPageListener(new AD4EntePageHandler());
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

//AD4_ENTI record @6-E9C8837E
        
        /*
            Model of AD4_ENTI record defining.
        */
        {
            com.codecharge.components.Record AD4_ENTI = new com.codecharge.components.Record("AD4_ENTI");
            AD4_ENTI.setPageModel( this );
            AD4_ENTI.addExcludeParam( "ccsForm" );
            AD4_ENTI.setVisible( true );
            AD4_ENTI.setAllowInsert(false);
            AD4_ENTI.setPreserveType(PreserveParameterType.GET);
            AD4_ENTI.setReturnPage("AD4Ente" + Names.ACTION_SUFFIX);
            AD4_ENTI.addRecordListener(new AD4_ENTIRecordHandler());

            com.codecharge.components.Link SVUOTA_REG_ANAGRAFICA__111 = new com.codecharge.components.Link("SVUOTA_REG_ANAGRAFICA", "SVUOTA_REG_ANAGRAFICA", this );
            SVUOTA_REG_ANAGRAFICA__111.setType( com.codecharge.components.ControlType.TEXT );
            SVUOTA_REG_ANAGRAFICA__111.setHrefSourceValue( "AD4Ente" + Names.ACTION_SUFFIX );
            SVUOTA_REG_ANAGRAFICA__111.setHrefType( "Page" );
            SVUOTA_REG_ANAGRAFICA__111.setConvertRule("Relative");
            SVUOTA_REG_ANAGRAFICA__111.setPreserveType(PreserveParameterType.GET);
            SVUOTA_REG_ANAGRAFICA__111.addExcludeParam( "SOGGETTO" );
            SVUOTA_REG_ANAGRAFICA__111.addParameter( new LinkParameter( "SOGGETTO", "", ParameterSource.EXPRESSION) );
            AD4_ENTI.add( SVUOTA_REG_ANAGRAFICA__111 );

            com.codecharge.components.Link MOD_REGISTRAZIONE_ANAGRAFICA__113 = new com.codecharge.components.Link("MOD_REGISTRAZIONE_ANAGRAFICA", "MOD_REGISTRAZIONE_ANAGRAFICA", this );
            MOD_REGISTRAZIONE_ANAGRAFICA__113.setType( com.codecharge.components.ControlType.TEXT );
            MOD_REGISTRAZIONE_ANAGRAFICA__113.setHrefSourceValue( "AD4SoggRicerca" + Names.ACTION_SUFFIX );
            MOD_REGISTRAZIONE_ANAGRAFICA__113.setHrefType( "Page" );
            MOD_REGISTRAZIONE_ANAGRAFICA__113.setConvertRule("Relative");
            MOD_REGISTRAZIONE_ANAGRAFICA__113.setPreserveType(PreserveParameterType.GET);
            MOD_REGISTRAZIONE_ANAGRAFICA__113.addParameter( new LinkParameter( "s_ENTE", "ENTE_ORIG", ParameterSource.DATAFIELD) );
            AD4_ENTI.add( MOD_REGISTRAZIONE_ANAGRAFICA__113 );

            com.codecharge.components.Label ENTE__78 = new com.codecharge.components.Label("ENTE", "ENTE", this );
            ENTE__78.setType( com.codecharge.components.ControlType.TEXT );
            AD4_ENTI.add(ENTE__78);

            com.codecharge.components.Hidden ENTE_ORIG__98 = new com.codecharge.components.Hidden("ENTE_ORIG", "ENTE_ORIG", this );
            ENTE_ORIG__98.setType( com.codecharge.components.ControlType.TEXT );
            ENTE_ORIG__98.setHtmlEncode( true );
            AD4_ENTI.add( ENTE_ORIG__98 );

            com.codecharge.components.Hidden SOGGETTO__115 = new com.codecharge.components.Hidden("SOGGETTO", "SOGGETTO", this );
            SOGGETTO__115.setType( com.codecharge.components.ControlType.INTEGER );
            SOGGETTO__115.setHtmlEncode( true );
            AD4_ENTI.add( SOGGETTO__115 );

            com.codecharge.components.TextBox DESCRIZIONE__119 = new com.codecharge.components.TextBox("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__119.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__119.setHtmlEncode( true );
            DESCRIZIONE__119.setCaption( "DESCRIZIONE" );
            DESCRIZIONE__119.addValidateHandler( new RequiredHandler( "Il valore nel campo DESCRIZIONE è richiesto." ) );
            AD4_ENTI.add( DESCRIZIONE__119 );

            com.codecharge.components.TextBox BITMAP__95 = new com.codecharge.components.TextBox("BITMAP", "BITMAP", this );
            BITMAP__95.setType( com.codecharge.components.ControlType.TEXT );
            BITMAP__95.setHtmlEncode( true );
            AD4_ENTI.add( BITMAP__95 );

            com.codecharge.components.TextBox DISEGNO__96 = new com.codecharge.components.TextBox("DISEGNO", "DISEGNO", this );
            DISEGNO__96.setType( com.codecharge.components.ControlType.TEXT );
            DISEGNO__96.setHtmlEncode( true );
            AD4_ENTI.add( DISEGNO__96 );

            com.codecharge.components.TextArea NOTE__22 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__22.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__22.setHtmlEncode( true );
            NOTE__22.addControlListener( new AD4_ENTINOTEHandler());
            NOTE__22.setCaption( "NOTE" );
            AD4_ENTI.add( NOTE__22 );

            com.codecharge.components.Label TITOLO_SOGG__116 = new com.codecharge.components.Label("TITOLO_SOGG", "TITOLO_SOGG", this );
            TITOLO_SOGG__116.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO_SOGG__116.setHtmlEncode( true );
            AD4_ENTI.add(TITOLO_SOGG__116);

            com.codecharge.components.Label DATI_SOGGETTO__40 = new com.codecharge.components.Label("DATI_SOGGETTO", "DATI_SOGGETTO", this );
            DATI_SOGGETTO__40.setType( com.codecharge.components.ControlType.TEXT );
            AD4_ENTI.add(DATI_SOGGETTO__40);

            com.codecharge.components.Button Button_Update__8 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__8.addExcludeParam( "ccsForm" );
            Button_Update__8.addExcludeParam( "Button_Update" );
            Button_Update__8.addExcludeParam( "SE_NUOVO" );
            Button_Update__8.addExcludeParam( "MVVC" );
            Button_Update__8.setOperation( "Update" );
            AD4_ENTI.add( Button_Update__8 );

            com.codecharge.components.Button Button_Delete__9 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__9.addButtonListener(new AD4_ENTIButton_DeleteHandler());
            Button_Delete__9.addExcludeParam( "ccsForm" );
            Button_Delete__9.addExcludeParam( "Button_Delete" );
            Button_Delete__9.setOperation( "Delete" );
            AD4_ENTI.add( Button_Delete__9 );

            com.codecharge.components.Button Button_Cancel__11 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__11.addButtonListener(new AD4_ENTIButton_CancelHandler());
            Button_Cancel__11.addExcludeParam( "ccsForm" );
            Button_Cancel__11.addExcludeParam( "Button_Cancel" );
            Button_Cancel__11.setOperation( "Cancel" );
            AD4_ENTI.add( Button_Cancel__11 );
            add(AD4_ENTI);
        } // End definition of AD4_ENTI record model.
//End AD4_ENTI record

//AD4EnteModel class tail @1-F5FC18C5
    }
}
//End AD4EnteModel class tail

