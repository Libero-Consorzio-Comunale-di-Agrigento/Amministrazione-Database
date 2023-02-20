//AD4GruppoModModel imports @1-3CEABF65
package ad4web.AD4GruppoMod;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GruppoModModel imports

//AD4GruppoModModel class head @1-521390A6
public class AD4GruppoModModel extends com.codecharge.components.Page {
    public AD4GruppoModModel() {
        this( new CCSLocale(), null );
    }

    public AD4GruppoModModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GruppoModModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GruppoModModel class head

//page settings @1-224D5C4D
        super("AD4GruppoMod", locale );
        setResponse(response);
        addPageListener(new AD4GruppoModPageHandler());
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

            com.codecharge.components.Label JS_REFRESH_SLAVES__108 = new com.codecharge.components.Label("JS_REFRESH_SLAVES", "REFRESH_SLAVES", this );
            JS_REFRESH_SLAVES__108.setType( com.codecharge.components.ControlType.TEXT );
            JS_REFRESH_SLAVES__108.addControlListener( new AD4GruppoModJS_REFRESH_SLAVESHandler());
            add( JS_REFRESH_SLAVES__108 );
        } // end page
//End page settings

//UTENTISearch record @84-10A6B44B
        
        /*
            Model of UTENTISearch record defining.
        */
        {
            com.codecharge.components.Record UTENTISearch = new com.codecharge.components.Record("UTENTISearch");
            UTENTISearch.setPageModel( this );
            UTENTISearch.addExcludeParam( "ccsForm" );
            UTENTISearch.setVisible( true );
            UTENTISearch.setAllowInsert(false);
            UTENTISearch.setAllowUpdate(false);
            UTENTISearch.setAllowDelete(false);
            UTENTISearch.setPreserveType(PreserveParameterType.GET);
            UTENTISearch.setReturnPage("AD4GruppoMod" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link IMMAGINE_FILTRO__94 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__94.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__94.setHrefSourceValue( "AD4GruppoMod" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__94.setHrefType( "Page" );
            IMMAGINE_FILTRO__94.setConvertRule("Relative");
            IMMAGINE_FILTRO__94.setPreserveType(PreserveParameterType.NONE);
            IMMAGINE_FILTRO__94.addParameter( new LinkParameter( "GRUPPO", "GRUPPO", ParameterSource.URL) );
            UTENTISearch.add( IMMAGINE_FILTRO__94 );

            com.codecharge.components.TextBox s_UTENTE__95 = new com.codecharge.components.TextBox("s_UTENTE", "", this );
            s_UTENTE__95.setType( com.codecharge.components.ControlType.TEXT );
            s_UTENTE__95.setHtmlEncode( true );
            UTENTISearch.add( s_UTENTE__95 );

            com.codecharge.components.RadioButton s_TIPO_UTENTE__96 = new com.codecharge.components.RadioButton("s_TIPO_UTENTE", "", this );
            s_TIPO_UTENTE__96.setType( com.codecharge.components.ControlType.TEXT );
            UTENTISearch.add( s_TIPO_UTENTE__96 );

            com.codecharge.components.Button Button_DoSearch__97 = new com.codecharge.components.Button("Button_DoSearch", this);
            Button_DoSearch__97.addExcludeParam( "ccsForm" );
            Button_DoSearch__97.addExcludeParam( "Button_DoSearch" );
            Button_DoSearch__97.setOperation( "Search" );
            UTENTISearch.add( Button_DoSearch__97 );
            add(UTENTISearch);
        } // End definition of UTENTISearch record model.
//End UTENTISearch record

//UTENTI grid @80-AF8CB374
        
        /*
            // Begin definition of UTENTI grid model.
        */
        {
            com.codecharge.components.Grid UTENTI = new com.codecharge.components.Grid("UTENTI");
            UTENTI.setPageModel( this );
            UTENTI.setFetchSize(10);
            UTENTI.setVisible( true );
            UTENTI.addGridListener( new UTENTIGridHandler() );

            com.codecharge.components.Label NOMINATIVO__81 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__81.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__81.setHtmlEncode( true );
            UTENTI.add(NOMINATIVO__81);

            com.codecharge.components.Hidden GRUPPO__105 = new com.codecharge.components.Hidden("GRUPPO", "GRUPPO", this );
            GRUPPO__105.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO__105.setHtmlEncode( true );
            UTENTI.add( GRUPPO__105 );

            com.codecharge.components.ImageLink Indietro__101 = new com.codecharge.components.ImageLink("Indietro", "", this );
            Indietro__101.setType( com.codecharge.components.ControlType.TEXT );
            Indietro__101.setHrefSourceValue( "AD4Gruppo" + Names.ACTION_SUFFIX );
            Indietro__101.setHrefType( "Page" );
            Indietro__101.setConvertRule("Relative");
            Indietro__101.setPreserveType(PreserveParameterType.GET);
            Indietro__101.addExcludeParam( "s_UTENTE" );
            Indietro__101.addExcludeParam( "s_TIPO_UTENTE" );
            Indietro__101.addExcludeParam( "DESCRIZIONE_FILTRO" );
            Indietro__101.addParameter( new LinkParameter( "GRUPPO", "GRUPPO", ParameterSource.URL) );
            UTENTI.add( Indietro__101 );
            add(UTENTI);
        } // End definition of UTENTI grid model
//End UTENTI grid

//DISPONIBILI record @52-B3B73AAA
        
        /*
            Model of DISPONIBILI record defining.
        */
        {
            com.codecharge.components.Record DISPONIBILI = new com.codecharge.components.Record("DISPONIBILI");
            DISPONIBILI.setPageModel( this );
            DISPONIBILI.addExcludeParam( "ccsForm" );
            DISPONIBILI.setVisible( true );
            DISPONIBILI.setAllowInsert(false);
            DISPONIBILI.setPreserveType(PreserveParameterType.GET);
            DISPONIBILI.setReturnPage("AD4GruppoMod" + Names.ACTION_SUFFIX);
            DISPONIBILI.addRecordListener(new DISPONIBILIRecordHandler());

            com.codecharge.components.ListBox UTENTE_D__53 = new com.codecharge.components.ListBox("UTENTE_D", "", this );
            UTENTE_D__53.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_D__53.setHtmlEncode( true );
            UTENTE_D__53.setBoundColumn( "UTENTE" );
            UTENTE_D__53.setTextColumn( "NOME_UTENTE" );
            DISPONIBILI.add( UTENTE_D__53 );

            com.codecharge.components.Button Tutti__55 = new com.codecharge.components.Button("Tutti", this);
            Tutti__55.addExcludeParam( "ccsForm" );
            Tutti__55.addExcludeParam( "Tutti" );
            Tutti__55.setOperation( "Delete" );
            DISPONIBILI.add( Tutti__55 );

            com.codecharge.components.Button Uno__56 = new com.codecharge.components.Button("Uno", this);
            Uno__56.addExcludeParam( "ccsForm" );
            Uno__56.addExcludeParam( "Uno" );
            Uno__56.setOperation( "Update" );
            DISPONIBILI.add( Uno__56 );
            add(DISPONIBILI);
        } // End definition of DISPONIBILI record model.
//End DISPONIBILI record

//ASSEGNATI record @63-7DF33414
        
        /*
            Model of ASSEGNATI record defining.
        */
        {
            com.codecharge.components.Record ASSEGNATI = new com.codecharge.components.Record("ASSEGNATI");
            ASSEGNATI.setPageModel( this );
            ASSEGNATI.addExcludeParam( "ccsForm" );
            ASSEGNATI.setVisible( true );
            ASSEGNATI.setAllowInsert(false);
            ASSEGNATI.setPreserveType(PreserveParameterType.GET);
            ASSEGNATI.setReturnPage("AD4GruppoMod" + Names.ACTION_SUFFIX);
            ASSEGNATI.addRecordListener(new ASSEGNATIRecordHandler());

            com.codecharge.components.ListBox UTENTE_A__64 = new com.codecharge.components.ListBox("UTENTE_A", "", this );
            UTENTE_A__64.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_A__64.setHtmlEncode( true );
            UTENTE_A__64.setBoundColumn( "UTENTE" );
            UTENTE_A__64.setTextColumn( "NOME_UTENTE" );
            ASSEGNATI.add( UTENTE_A__64 );

            com.codecharge.components.Button Uno__66 = new com.codecharge.components.Button("Uno", this);
            Uno__66.addExcludeParam( "ccsForm" );
            Uno__66.addExcludeParam( "Uno" );
            Uno__66.setOperation( "Update" );
            ASSEGNATI.add( Uno__66 );

            com.codecharge.components.Button Tutti__68 = new com.codecharge.components.Button("Tutti", this);
            Tutti__68.addExcludeParam( "ccsForm" );
            Tutti__68.addExcludeParam( "Tutti" );
            Tutti__68.setOperation( "Delete" );
            ASSEGNATI.add( Tutti__68 );
            add(ASSEGNATI);
        } // End definition of ASSEGNATI record model.
//End ASSEGNATI record

//AD4GruppoModModel class tail @1-F5FC18C5
    }
}
//End AD4GruppoModModel class tail

