//AD4AccessiRicercaModel imports @1-580C87F7
package ad4web.AD4AccessiRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4AccessiRicercaModel imports

//AD4AccessiRicercaModel class head @1-32262BB7
public class AD4AccessiRicercaModel extends com.codecharge.components.Page {
    public AD4AccessiRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4AccessiRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4AccessiRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4AccessiRicercaModel class head

//page settings @1-970ED268
        super("AD4AccessiRicerca", locale );
        setResponse(response);
        addPageListener(new AD4AccessiRicercaPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__305 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__305.setVisible( true );
            add( Guida__305 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4Accessi_VSearch record @358-912B150B
        
        /*
            Model of AD4Accessi_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Accessi_VSearch = new com.codecharge.components.Record("AD4Accessi_VSearch");
            AD4Accessi_VSearch.setPageModel( this );
            AD4Accessi_VSearch.addExcludeParam( "ccsForm" );
            AD4Accessi_VSearch.addExcludeParam( "AD4AccessiVPage" );
            AD4Accessi_VSearch.setVisible( true );
            AD4Accessi_VSearch.setAllowInsert(false);
            AD4Accessi_VSearch.setAllowUpdate(false);
            AD4Accessi_VSearch.setAllowDelete(false);
            AD4Accessi_VSearch.setPreserveType(PreserveParameterType.GET);
            AD4Accessi_VSearch.setReturnPage("AD4AccessiRicerca" + Names.ACTION_SUFFIX);
            AD4Accessi_VSearch.addRecordListener(new AD4Accessi_VSearchRecordHandler());

            com.codecharge.components.ListBox s_TIPO__383 = new com.codecharge.components.ListBox("s_TIPO", "", this );
            s_TIPO__383.setType( com.codecharge.components.ControlType.TEXT );
            s_TIPO__383.setHtmlEncode( true );
            AD4Accessi_VSearch.add( s_TIPO__383 );

            com.codecharge.components.Hidden DESCRIZIONE_FILTRO__410 = new com.codecharge.components.Hidden("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO", this );
            DESCRIZIONE_FILTRO__410.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE_FILTRO__410.setHtmlEncode( true );
            AD4Accessi_VSearch.add( DESCRIZIONE_FILTRO__410 );

            com.codecharge.components.Link IMMAGINE_FILTRO__411 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__411.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__411.setHrefSourceValue( "AD4AccessiRicerca" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__411.setHrefType( "Page" );
            IMMAGINE_FILTRO__411.setConvertRule("Relative");
            IMMAGINE_FILTRO__411.setPreserveType(PreserveParameterType.NONE);
            AD4Accessi_VSearch.add( IMMAGINE_FILTRO__411 );

            com.codecharge.components.TextBox s_DATA_DA__367 = new com.codecharge.components.TextBox("s_DATA_DA", "", this );
            s_DATA_DA__367.setType( com.codecharge.components.ControlType.DATE );
            s_DATA_DA__367.setHtmlEncode( true );
            s_DATA_DA__367.setFormatPattern( "dd/MM/yyyy" );
            AD4Accessi_VSearch.add( s_DATA_DA__367 );

            com.codecharge.components.TextBox s_DATA_A__368 = new com.codecharge.components.TextBox("s_DATA_A", "", this );
            s_DATA_A__368.setType( com.codecharge.components.ControlType.DATE );
            s_DATA_A__368.setHtmlEncode( true );
            s_DATA_A__368.setFormatPattern( "dd/MM/yyyy" );
            AD4Accessi_VSearch.add( s_DATA_A__368 );

            com.codecharge.components.ListBox s_UTENTE__395 = new com.codecharge.components.ListBox("s_UTENTE", "", this );
            s_UTENTE__395.setType( com.codecharge.components.ControlType.TEXT );
            s_UTENTE__395.setHtmlEncode( true );
            s_UTENTE__395.setBoundColumn( "UTENTE" );
            s_UTENTE__395.setTextColumn( "NOMINATIVO" );
            AD4Accessi_VSearch.add( s_UTENTE__395 );

            com.codecharge.components.Hidden ELIMINATI__437 = new com.codecharge.components.Hidden("ELIMINATI", "ELIMINATI", this );
            ELIMINATI__437.setType( com.codecharge.components.ControlType.TEXT );
            ELIMINATI__437.setHtmlEncode( true );
            AD4Accessi_VSearch.add( ELIMINATI__437 );

            com.codecharge.components.ListBox s_MODULO__396 = new com.codecharge.components.ListBox("s_MODULO", "", this );
            s_MODULO__396.setType( com.codecharge.components.ControlType.TEXT );
            s_MODULO__396.setHtmlEncode( true );
            s_MODULO__396.setBoundColumn( "MODULO" );
            s_MODULO__396.setTextColumn( "DESCRIZIONE" );
            AD4Accessi_VSearch.add( s_MODULO__396 );

            com.codecharge.components.Hidden s_RICERCA__438 = new com.codecharge.components.Hidden("s_RICERCA", "", this );
            s_RICERCA__438.setType( com.codecharge.components.ControlType.TEXT );
            s_RICERCA__438.setHtmlEncode( true );
            AD4Accessi_VSearch.add( s_RICERCA__438 );

            com.codecharge.components.ListBox s_ISTANZA__391 = new com.codecharge.components.ListBox("s_ISTANZA", "", this );
            s_ISTANZA__391.setType( com.codecharge.components.ControlType.TEXT );
            s_ISTANZA__391.setHtmlEncode( true );
            s_ISTANZA__391.setBoundColumn( "ISTANZA" );
            s_ISTANZA__391.setTextColumn( "DESCRIZIONE" );
            AD4Accessi_VSearch.add( s_ISTANZA__391 );

            com.codecharge.components.TextBox s_NOTE__388 = new com.codecharge.components.TextBox("s_NOTE", "", this );
            s_NOTE__388.setType( com.codecharge.components.ControlType.TEXT );
            s_NOTE__388.setHtmlEncode( true );
            AD4Accessi_VSearch.add( s_NOTE__388 );

            com.codecharge.components.TextBox s_DB_USER__397 = new com.codecharge.components.TextBox("s_DB_USER", "", this );
            s_DB_USER__397.setType( com.codecharge.components.ControlType.TEXT );
            s_DB_USER__397.setHtmlEncode( true );
            AD4Accessi_VSearch.add( s_DB_USER__397 );

            com.codecharge.components.TextBox s_TERMINALE__362 = new com.codecharge.components.TextBox("s_TERMINALE", "", this );
            s_TERMINALE__362.setType( com.codecharge.components.ControlType.TEXT );
            s_TERMINALE__362.setHtmlEncode( true );
            AD4Accessi_VSearch.add( s_TERMINALE__362 );

            com.codecharge.components.ListBox s_AUTENTICAZIONE__363 = new com.codecharge.components.ListBox("s_AUTENTICAZIONE", "", this );
            s_AUTENTICAZIONE__363.setType( com.codecharge.components.ControlType.TEXT );
            s_AUTENTICAZIONE__363.setHtmlEncode( true );
            AD4Accessi_VSearch.add( s_AUTENTICAZIONE__363 );

            com.codecharge.components.ListBox s_STATO__364 = new com.codecharge.components.ListBox("s_STATO", "", this );
            s_STATO__364.setType( com.codecharge.components.ControlType.TEXT );
            s_STATO__364.setHtmlEncode( true );
            AD4Accessi_VSearch.add( s_STATO__364 );

            com.codecharge.components.Button DoSearch__414 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__414.addExcludeParam( "ccsForm" );
            DoSearch__414.addExcludeParam( "DoSearch" );
            DoSearch__414.setOperation( "Search" );
            AD4Accessi_VSearch.add( DoSearch__414 );
            add(AD4Accessi_VSearch);
        } // End definition of AD4Accessi_VSearch record model.
//End AD4Accessi_VSearch record

//Toolbox grid @418-DB1E9F5D
        
        /*
            // Begin definition of Toolbox grid model.
        */
        {
            com.codecharge.components.Grid Toolbox = new com.codecharge.components.Grid("Toolbox");
            Toolbox.setPageModel( this );
            Toolbox.setFetchSize(10);
            Toolbox.setVisible( true );

            com.codecharge.components.Label RICERCA__419 = new com.codecharge.components.Label("RICERCA", "RICERCA", this );
            RICERCA__419.setType( com.codecharge.components.ControlType.TEXT );
            Toolbox.add(RICERCA__419);

            com.codecharge.components.Label ELIMINA_ARCHIVIATI__431 = new com.codecharge.components.Label("ELIMINA_ARCHIVIATI", "ELIMINA_ARCHIVIATI", this );
            ELIMINA_ARCHIVIATI__431.setType( com.codecharge.components.ControlType.TEXT );
            Toolbox.add(ELIMINA_ARCHIVIATI__431);
            add(Toolbox);
        } // End definition of Toolbox grid model
//End Toolbox grid

//AD4AccessiV grid @142-97F53FBB
        
        /*
            // Begin definition of AD4AccessiV grid model.
        */
        {
            com.codecharge.components.Grid AD4AccessiV = new com.codecharge.components.Grid("AD4AccessiV");
            AD4AccessiV.setPageModel( this );
            AD4AccessiV.setFetchSize(20);
            AD4AccessiV.setVisible( true );
            AD4AccessiV.addGridListener( new AD4AccessiVGridHandler() );

            com.codecharge.components.Label TR_STYLE__415 = new com.codecharge.components.Label("TR_STYLE", "TR_STYLE", this );
            TR_STYLE__415.setType( com.codecharge.components.ControlType.TEXT );
            TR_STYLE__415.setHtmlEncode( true );
            AD4AccessiV.add(TR_STYLE__415);

            com.codecharge.components.Label ACCE_ID__278 = new com.codecharge.components.Label("ACCE_ID", "ACCE_ID", this );
            ACCE_ID__278.setType( com.codecharge.components.ControlType.INTEGER );
            ACCE_ID__278.setHtmlEncode( true );
            AD4AccessiV.add(ACCE_ID__278);

            com.codecharge.components.Label DATA__263 = new com.codecharge.components.Label("DATA", "DATA", this );
            DATA__263.setType( com.codecharge.components.ControlType.TEXT );
            DATA__263.setHtmlEncode( true );
            AD4AccessiV.add(DATA__263);

            com.codecharge.components.Label SESSION_ID__333 = new com.codecharge.components.Label("SESSION_ID", "SESSION_ID", this );
            SESSION_ID__333.setType( com.codecharge.components.ControlType.TEXT );
            SESSION_ID__333.setHtmlEncode( true );
            AD4AccessiV.add(SESSION_ID__333);

            com.codecharge.components.Label DESC_LOG__335 = new com.codecharge.components.Label("DESC_LOG", "DESC_LOG", this );
            DESC_LOG__335.setType( com.codecharge.components.ControlType.TEXT );
            DESC_LOG__335.setHtmlEncode( true );
            AD4AccessiV.add(DESC_LOG__335);

            com.codecharge.components.Label PROVENIENZA__348 = new com.codecharge.components.Label("PROVENIENZA", "PROVENIENZA", this );
            PROVENIENZA__348.setType( com.codecharge.components.ControlType.TEXT );
            AD4AccessiV.add(PROVENIENZA__348);

            com.codecharge.components.Label DB_USER__416 = new com.codecharge.components.Label("DB_USER", "DB_USER", this );
            DB_USER__416.setType( com.codecharge.components.ControlType.TEXT );
            DB_USER__416.setHtmlEncode( true );
            AD4AccessiV.add(DB_USER__416);

            com.codecharge.components.Label NOTE__442 = new com.codecharge.components.Label("NOTE", "NOTE", this );
            NOTE__442.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__442.setHtmlEncode( true );
            AD4AccessiV.add(NOTE__442);

            com.codecharge.components.Label ACCESSO__352 = new com.codecharge.components.Label("ACCESSO", "ACCESSO", this );
            ACCESSO__352.setType( com.codecharge.components.ControlType.TEXT );
            AD4AccessiV.add(ACCESSO__352);

            com.codecharge.components.Label TIPO_AUTENTICAZIONE__354 = new com.codecharge.components.Label("TIPO_AUTENTICAZIONE", "TIPO_AUTENTICAZIONE", this );
            TIPO_AUTENTICAZIONE__354.setType( com.codecharge.components.ControlType.TEXT );
            TIPO_AUTENTICAZIONE__354.setHtmlEncode( true );
            AD4AccessiV.add(TIPO_AUTENTICAZIONE__354);

            com.codecharge.components.Label DESC_STATO__353 = new com.codecharge.components.Label("DESC_STATO", "DESC_STATO", this );
            DESC_STATO__353.setType( com.codecharge.components.ControlType.TEXT );
            AD4AccessiV.add(DESC_STATO__353);

            com.codecharge.components.Label AFCNavigator__339 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__339.setType( com.codecharge.components.ControlType.TEXT );
            AD4AccessiV.add(AFCNavigator__339);
            add(AD4AccessiV);
        } // End definition of AD4AccessiV grid model
//End AD4AccessiV grid

//AD4AccessiRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4AccessiRicercaModel class tail
