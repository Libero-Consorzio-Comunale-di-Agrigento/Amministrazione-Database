//AD4EventiRicercaModel imports @1-79A33833
package ad4web.AD4EventiRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4EventiRicercaModel imports

//AD4EventiRicercaModel class head @1-DC826DF1
public class AD4EventiRicercaModel extends com.codecharge.components.Page {
    public AD4EventiRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4EventiRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4EventiRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4EventiRicercaModel class head

//page settings @1-A72C346B
        super("AD4EventiRicerca", locale );
        setResponse(response);
        addPageListener(new AD4EventiRicercaPageHandler());
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

//AD4Eventi_VSearch record @358-A62F172C
        
        /*
            Model of AD4Eventi_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Eventi_VSearch = new com.codecharge.components.Record("AD4Eventi_VSearch");
            AD4Eventi_VSearch.setPageModel( this );
            AD4Eventi_VSearch.addExcludeParam( "ccsForm" );
            AD4Eventi_VSearch.addExcludeParam( "AD4EventiVPage" );
            AD4Eventi_VSearch.setVisible( true );
            AD4Eventi_VSearch.setAllowInsert(false);
            AD4Eventi_VSearch.setAllowUpdate(false);
            AD4Eventi_VSearch.setAllowDelete(false);
            AD4Eventi_VSearch.setPreserveType(PreserveParameterType.GET);
            AD4Eventi_VSearch.setReturnPage("AD4EventiRicerca" + Names.ACTION_SUFFIX);
            AD4Eventi_VSearch.addRecordListener(new AD4Eventi_VSearchRecordHandler());

            com.codecharge.components.ListBox s_TIPO__383 = new com.codecharge.components.ListBox("s_TIPO", "", this );
            s_TIPO__383.setType( com.codecharge.components.ControlType.TEXT );
            s_TIPO__383.setHtmlEncode( true );
            AD4Eventi_VSearch.add( s_TIPO__383 );

            com.codecharge.components.Hidden DESCRIZIONE_FILTRO__410 = new com.codecharge.components.Hidden("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO", this );
            DESCRIZIONE_FILTRO__410.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE_FILTRO__410.setHtmlEncode( true );
            AD4Eventi_VSearch.add( DESCRIZIONE_FILTRO__410 );

            com.codecharge.components.Link IMMAGINE_FILTRO__411 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__411.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__411.setHrefSourceValue( "AD4EventiRicerca" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__411.setHrefType( "Page" );
            IMMAGINE_FILTRO__411.setConvertRule("Relative");
            IMMAGINE_FILTRO__411.setPreserveType(PreserveParameterType.NONE);
            AD4Eventi_VSearch.add( IMMAGINE_FILTRO__411 );

            com.codecharge.components.TextBox s_DATA_DA__367 = new com.codecharge.components.TextBox("s_DATA_DA", "", this );
            s_DATA_DA__367.setType( com.codecharge.components.ControlType.DATE );
            s_DATA_DA__367.setHtmlEncode( true );
            s_DATA_DA__367.setFormatPattern( "dd/MM/yyyy" );
            AD4Eventi_VSearch.add( s_DATA_DA__367 );

            com.codecharge.components.TextBox s_DATA_A__368 = new com.codecharge.components.TextBox("s_DATA_A", "", this );
            s_DATA_A__368.setType( com.codecharge.components.ControlType.DATE );
            s_DATA_A__368.setHtmlEncode( true );
            s_DATA_A__368.setFormatPattern( "dd/MM/yyyy" );
            AD4Eventi_VSearch.add( s_DATA_A__368 );

            com.codecharge.components.TextBox s_TESTO__362 = new com.codecharge.components.TextBox("s_TESTO", "", this );
            s_TESTO__362.setType( com.codecharge.components.ControlType.TEXT );
            s_TESTO__362.setHtmlEncode( true );
            AD4Eventi_VSearch.add( s_TESTO__362 );

            com.codecharge.components.ListBox s_UTENTE__395 = new com.codecharge.components.ListBox("s_UTENTE", "", this );
            s_UTENTE__395.setType( com.codecharge.components.ControlType.TEXT );
            s_UTENTE__395.setHtmlEncode( true );
            s_UTENTE__395.setBoundColumn( "UTENTE" );
            s_UTENTE__395.setTextColumn( "NOMINATIVO" );
            AD4Eventi_VSearch.add( s_UTENTE__395 );

            com.codecharge.components.ListBox s_MODULO__396 = new com.codecharge.components.ListBox("s_MODULO", "", this );
            s_MODULO__396.setType( com.codecharge.components.ControlType.TEXT );
            s_MODULO__396.setHtmlEncode( true );
            s_MODULO__396.setBoundColumn( "MODULO" );
            s_MODULO__396.setTextColumn( "DESCRIZIONE" );
            AD4Eventi_VSearch.add( s_MODULO__396 );

            com.codecharge.components.ListBox s_ISTANZA__391 = new com.codecharge.components.ListBox("s_ISTANZA", "", this );
            s_ISTANZA__391.setType( com.codecharge.components.ControlType.TEXT );
            s_ISTANZA__391.setHtmlEncode( true );
            s_ISTANZA__391.setBoundColumn( "ISTANZA" );
            s_ISTANZA__391.setTextColumn( "DESCRIZIONE" );
            AD4Eventi_VSearch.add( s_ISTANZA__391 );

            com.codecharge.components.TextBox s_DB_USER__397 = new com.codecharge.components.TextBox("s_DB_USER", "", this );
            s_DB_USER__397.setType( com.codecharge.components.ControlType.TEXT );
            s_DB_USER__397.setHtmlEncode( true );
            AD4Eventi_VSearch.add( s_DB_USER__397 );

            com.codecharge.components.ListBox s_GRAVITA__363 = new com.codecharge.components.ListBox("s_GRAVITA", "", this );
            s_GRAVITA__363.setType( com.codecharge.components.ControlType.TEXT );
            s_GRAVITA__363.setHtmlEncode( true );
            AD4Eventi_VSearch.add( s_GRAVITA__363 );

            com.codecharge.components.ListBox s_STATO__364 = new com.codecharge.components.ListBox("s_STATO", "", this );
            s_STATO__364.setType( com.codecharge.components.ControlType.TEXT );
            s_STATO__364.setHtmlEncode( true );
            AD4Eventi_VSearch.add( s_STATO__364 );

            com.codecharge.components.TextBox s_FILE__394 = new com.codecharge.components.TextBox("s_FILE", "", this );
            s_FILE__394.setType( com.codecharge.components.ControlType.TEXT );
            s_FILE__394.setHtmlEncode( true );
            AD4Eventi_VSearch.add( s_FILE__394 );

            com.codecharge.components.TextBox s_NOTE__388 = new com.codecharge.components.TextBox("s_NOTE", "", this );
            s_NOTE__388.setType( com.codecharge.components.ControlType.TEXT );
            s_NOTE__388.setHtmlEncode( true );
            AD4Eventi_VSearch.add( s_NOTE__388 );

            com.codecharge.components.Button DoSearch__414 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__414.addExcludeParam( "ccsForm" );
            DoSearch__414.addExcludeParam( "DoSearch" );
            DoSearch__414.setOperation( "Search" );
            AD4Eventi_VSearch.add( DoSearch__414 );

            com.codecharge.components.Button RicercaAvanzataNo__398 = new com.codecharge.components.Button("RicercaAvanzataNo", this);
            RicercaAvanzataNo__398.addExcludeParam( "ccsForm" );
            RicercaAvanzataNo__398.addExcludeParam( "RicercaAvanzataNo" );
            AD4Eventi_VSearch.add( RicercaAvanzataNo__398 );

            com.codecharge.components.Button RicercaAvanzata__370 = new com.codecharge.components.Button("RicercaAvanzata", this);
            RicercaAvanzata__370.addExcludeParam( "ccsForm" );
            RicercaAvanzata__370.addExcludeParam( "RicercaAvanzata" );
            AD4Eventi_VSearch.add( RicercaAvanzata__370 );
            add(AD4Eventi_VSearch);
        } // End definition of AD4Eventi_VSearch record model.
//End AD4Eventi_VSearch record

//AD4EventiV grid @142-344694FC
        
        /*
            // Begin definition of AD4EventiV grid model.
        */
        {
            com.codecharge.components.Grid AD4EventiV = new com.codecharge.components.Grid("AD4EventiV");
            AD4EventiV.setPageModel( this );
            AD4EventiV.setFetchSize(20);
            AD4EventiV.setVisible( true );
            AD4EventiV.addGridListener( new AD4EventiVGridHandler() );

            com.codecharge.components.Link ID_EVENTO__278 = new com.codecharge.components.Link("ID_EVENTO", "ID_EVENTO", this );
            ID_EVENTO__278.setType( com.codecharge.components.ControlType.INTEGER );
            ID_EVENTO__278.setHtmlEncode( true );
            ID_EVENTO__278.setHrefSourceValue( "AD4Evento" + Names.ACTION_SUFFIX );
            ID_EVENTO__278.setHrefType( "Page" );
            ID_EVENTO__278.setConvertRule("Relative");
            ID_EVENTO__278.setPreserveType(PreserveParameterType.NONE);
            ID_EVENTO__278.addParameter( new LinkParameter( "ID_EVENTO", "ID_EVENTO", ParameterSource.DATAFIELD) );
            AD4EventiV.add( ID_EVENTO__278 );

            com.codecharge.components.Label DATA__263 = new com.codecharge.components.Label("DATA", "DATA", this );
            DATA__263.setType( com.codecharge.components.ControlType.TEXT );
            AD4EventiV.add(DATA__263);

            com.codecharge.components.Label DESC_TIPO__355 = new com.codecharge.components.Label("DESC_TIPO", "DESC_TIPO", this );
            DESC_TIPO__355.setType( com.codecharge.components.ControlType.TEXT );
            DESC_TIPO__355.setHtmlEncode( true );
            AD4EventiV.add(DESC_TIPO__355);

            com.codecharge.components.Label DESC_TIPO_RIDOTTA__333 = new com.codecharge.components.Label("DESC_TIPO_RIDOTTA", "DESC_TIPO_RIDOTTA", this );
            DESC_TIPO_RIDOTTA__333.setType( com.codecharge.components.ControlType.TEXT );
            DESC_TIPO_RIDOTTA__333.setHtmlEncode( true );
            AD4EventiV.add(DESC_TIPO_RIDOTTA__333);

            com.codecharge.components.Label TESTO_NOTE__335 = new com.codecharge.components.Label("TESTO_NOTE", "TESTO_NOTE", this );
            TESTO_NOTE__335.setType( com.codecharge.components.ControlType.TEXT );
            AD4EventiV.add(TESTO_NOTE__335);

            com.codecharge.components.Label PROVENIENZA__348 = new com.codecharge.components.Label("PROVENIENZA", "PROVENIENZA", this );
            PROVENIENZA__348.setType( com.codecharge.components.ControlType.TEXT );
            AD4EventiV.add(PROVENIENZA__348);

            com.codecharge.components.Label DB_USER__352 = new com.codecharge.components.Label("DB_USER", "DB_USER", this );
            DB_USER__352.setType( com.codecharge.components.ControlType.TEXT );
            DB_USER__352.setHtmlEncode( true );
            AD4EventiV.add(DB_USER__352);

            com.codecharge.components.Label GRAVITA__356 = new com.codecharge.components.Label("GRAVITA", "GRAVITA", this );
            GRAVITA__356.setType( com.codecharge.components.ControlType.TEXT );
            AD4EventiV.add(GRAVITA__356);

            com.codecharge.components.Label DESC_STATO__353 = new com.codecharge.components.Label("DESC_STATO", "DESC_STATO", this );
            DESC_STATO__353.setType( com.codecharge.components.ControlType.TEXT );
            AD4EventiV.add(DESC_STATO__353);

            com.codecharge.components.Label DESC_NOTIFICATO__354 = new com.codecharge.components.Label("DESC_NOTIFICATO", "DESC_NOTIFICATO", this );
            DESC_NOTIFICATO__354.setType( com.codecharge.components.ControlType.TEXT );
            AD4EventiV.add(DESC_NOTIFICATO__354);

            com.codecharge.components.Label AFCNavigator__339 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__339.setType( com.codecharge.components.ControlType.TEXT );
            AD4EventiV.add(AFCNavigator__339);
            add(AD4EventiV);
        } // End definition of AD4EventiV grid model
//End AD4EventiV grid

//AD4EventiRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4EventiRicercaModel class tail
