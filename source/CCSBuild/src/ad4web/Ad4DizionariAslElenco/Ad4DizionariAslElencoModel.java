//Ad4DizionariAslElencoModel imports @1-2AE221FC
package ad4web.Ad4DizionariAslElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariAslElencoModel imports

//Ad4DizionariAslElencoModel class head @1-EE23C224
public class Ad4DizionariAslElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariAslElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariAslElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariAslElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariAslElencoModel class head

//page settings @1-8A02A7F8
        super("Ad4DizionariAslElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariGuida__2 = new com.codecharge.components.IncludePage("Ad4DizionariGuida", this );
            Ad4DizionariGuida__2.setVisible( true );
            add( Ad4DizionariGuida__2 );
        } // end page
//End page settings

//AslFiltro record @3-E64DA128
        
        /*
            Model of AslFiltro record defining.
        */
        {
            com.codecharge.components.Record AslFiltro = new com.codecharge.components.Record("AslFiltro");
            AslFiltro.setPageModel( this );
            AslFiltro.addExcludeParam( "ccsForm" );
            AslFiltro.setVisible( true );
            AslFiltro.setAllowInsert(false);
            AslFiltro.setAllowUpdate(false);
            AslFiltro.setAllowDelete(false);
            AslFiltro.setPreserveType(PreserveParameterType.GET);
            AslFiltro.setReturnPage("Ad4DizionariAslElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__4 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__4.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__4.setHrefType( "Page" );
            FILTER_SEARCH__4.setConvertRule("Relative");
            FILTER_SEARCH__4.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__4.addExcludeParam( "s_ASL" );
            FILTER_SEARCH__4.addExcludeParam( "s_REGIONE" );
            AslFiltro.add( FILTER_SEARCH__4 );

            com.codecharge.components.ListBox s_REGIONE__27 = new com.codecharge.components.ListBox("s_REGIONE", "S_REGIONE", this );
            s_REGIONE__27.setType( com.codecharge.components.ControlType.TEXT );
            s_REGIONE__27.setHtmlEncode( true );
            AslFiltro.add( s_REGIONE__27 );

            com.codecharge.components.Button DoSearch__6 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__6.addExcludeParam( "ccsForm" );
            DoSearch__6.addExcludeParam( "DoSearch" );
            DoSearch__6.setOperation( "Search" );
            AslFiltro.add( DoSearch__6 );

            com.codecharge.components.TextBox s_ASL__28 = new com.codecharge.components.TextBox("s_ASL", "S_ASL", this );
            s_ASL__28.setType( com.codecharge.components.ControlType.TEXT );
            s_ASL__28.setHtmlEncode( true );
            s_ASL__28.setCaption( "Regione" );
            AslFiltro.add( s_ASL__28 );
            add(AslFiltro);
        } // End definition of AslFiltro record model.
//End AslFiltro record

//AslElenco grid @8-E6FD7200
        
        /*
            // Begin definition of AslElenco grid model.
        */
        {
            com.codecharge.components.Grid AslElenco = new com.codecharge.components.Grid("AslElenco");
            AslElenco.setPageModel( this );
            AslElenco.setFetchSize(25);
            AslElenco.setVisible( true );
            AslElenco.addGridListener( new AslElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AslElenco.add( Aggiungi__9 );

            com.codecharge.components.Label REGIONE_ASL__29 = new com.codecharge.components.Label("REGIONE_ASL", "REGIONE_ASL", this );
            REGIONE_ASL__29.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE_ASL__29.setHtmlEncode( true );
            AslElenco.add(REGIONE_ASL__29);

            com.codecharge.components.Label CODICE_ASL__30 = new com.codecharge.components.Label("CODICE_ASL", "CODICE_ASL", this );
            CODICE_ASL__30.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_ASL__30.setHtmlEncode( true );
            AslElenco.add(CODICE_ASL__30);

            com.codecharge.components.Link DESCRIZIONE__13 = new com.codecharge.components.Link("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__13.setHtmlEncode( true );
            DESCRIZIONE__13.setHrefType( "Page" );
            DESCRIZIONE__13.setConvertRule("Relative");
            DESCRIZIONE__13.setPreserveType(PreserveParameterType.GET);
            DESCRIZIONE__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            DESCRIZIONE__13.addParameter( new LinkParameter( "REGIONE_ASL", "REGIONE_ASL", ParameterSource.DATAFIELD) );
            DESCRIZIONE__13.addParameter( new LinkParameter( "CODICE_ASL", "CODICE_ASL", ParameterSource.DATAFIELD) );
            AslElenco.add( DESCRIZIONE__13 );

            com.codecharge.components.Label REGIONE_DENOMINAZIONE__16 = new com.codecharge.components.Label("REGIONE_DENOMINAZIONE", "REGIONE_DENOMINAZIONE", this );
            REGIONE_DENOMINAZIONE__16.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE_DENOMINAZIONE__16.setHtmlEncode( true );
            AslElenco.add(REGIONE_DENOMINAZIONE__16);

            com.codecharge.components.Label UTENTE_AGGIORNAMENTO__21 = new com.codecharge.components.Label("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO", this );
            UTENTE_AGGIORNAMENTO__21.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_AGGIORNAMENTO__21.setHtmlEncode( true );
            AslElenco.add(UTENTE_AGGIORNAMENTO__21);

            com.codecharge.components.Label DATA_AGGIORNAMENTO__20 = new com.codecharge.components.Label("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO", this );
            DATA_AGGIORNAMENTO__20.setType( com.codecharge.components.ControlType.TEXT );
            DATA_AGGIORNAMENTO__20.setHtmlEncode( true );
            AslElenco.add(DATA_AGGIORNAMENTO__20);

            com.codecharge.components.Label AFCNavigator__22 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__22.setType( com.codecharge.components.ControlType.TEXT );
            AslElenco.add(AFCNavigator__22);
            add(AslElenco);
        } // End definition of AslElenco grid model
//End AslElenco grid

//Ad4DizionariAslElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariAslElencoModel class tail

