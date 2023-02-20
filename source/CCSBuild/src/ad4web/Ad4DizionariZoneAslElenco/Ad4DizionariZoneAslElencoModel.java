//Ad4DizionariZoneAslElencoModel imports @1-8D510862
package ad4web.Ad4DizionariZoneAslElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariZoneAslElencoModel imports

//Ad4DizionariZoneAslElencoModel class head @1-323CDC95
public class Ad4DizionariZoneAslElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariZoneAslElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariZoneAslElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariZoneAslElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariZoneAslElencoModel class head

//page settings @1-6A074A42
        super("Ad4DizionariZoneAslElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariGuida__2 = new com.codecharge.components.IncludePage("Ad4DizionariGuida", this );
            Ad4DizionariGuida__2.setVisible( true );
            add( Ad4DizionariGuida__2 );
        } // end page
//End page settings

//ZoneAslFiltro record @3-8D7AB9CE
        
        /*
            Model of ZoneAslFiltro record defining.
        */
        {
            com.codecharge.components.Record ZoneAslFiltro = new com.codecharge.components.Record("ZoneAslFiltro");
            ZoneAslFiltro.setPageModel( this );
            ZoneAslFiltro.addExcludeParam( "ccsForm" );
            ZoneAslFiltro.setVisible( true );
            ZoneAslFiltro.setAllowInsert(false);
            ZoneAslFiltro.setAllowUpdate(false);
            ZoneAslFiltro.setAllowDelete(false);
            ZoneAslFiltro.setPreserveType(PreserveParameterType.GET);
            ZoneAslFiltro.setReturnPage("Ad4DizionariZoneAslElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__4 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__4.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__4.setHrefType( "Page" );
            FILTER_SEARCH__4.setConvertRule("Relative");
            FILTER_SEARCH__4.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__4.addExcludeParam( "s_ZONA_ASL" );
            FILTER_SEARCH__4.addExcludeParam( "s_REGIONE" );
            ZoneAslFiltro.add( FILTER_SEARCH__4 );

            com.codecharge.components.ListBox s_REGIONE__26 = new com.codecharge.components.ListBox("s_REGIONE", "S_REGIONE", this );
            s_REGIONE__26.setType( com.codecharge.components.ControlType.TEXT );
            s_REGIONE__26.setHtmlEncode( true );
            ZoneAslFiltro.add( s_REGIONE__26 );

            com.codecharge.components.Button DoSearch__6 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__6.addExcludeParam( "ccsForm" );
            DoSearch__6.addExcludeParam( "DoSearch" );
            DoSearch__6.setOperation( "Search" );
            ZoneAslFiltro.add( DoSearch__6 );

            com.codecharge.components.TextBox s_ZONA_ASL__27 = new com.codecharge.components.TextBox("s_ZONA_ASL", "S_ZONA_ASL", this );
            s_ZONA_ASL__27.setType( com.codecharge.components.ControlType.TEXT );
            s_ZONA_ASL__27.setHtmlEncode( true );
            s_ZONA_ASL__27.setCaption( "Zona" );
            ZoneAslFiltro.add( s_ZONA_ASL__27 );
            add(ZoneAslFiltro);
        } // End definition of ZoneAslFiltro record model.
//End ZoneAslFiltro record

//ZoneAslElenco grid @8-8C8B29F2
        
        /*
            // Begin definition of ZoneAslElenco grid model.
        */
        {
            com.codecharge.components.Grid ZoneAslElenco = new com.codecharge.components.Grid("ZoneAslElenco");
            ZoneAslElenco.setPageModel( this );
            ZoneAslElenco.setFetchSize(25);
            ZoneAslElenco.setVisible( true );
            ZoneAslElenco.addGridListener( new ZoneAslElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            ZoneAslElenco.add( Aggiungi__9 );

            com.codecharge.components.Label CODICE_REGIONE__28 = new com.codecharge.components.Label("CODICE_REGIONE", "CODICE_REGIONE", this );
            CODICE_REGIONE__28.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_REGIONE__28.setHtmlEncode( true );
            ZoneAslElenco.add(CODICE_REGIONE__28);

            com.codecharge.components.Label CODICE_ZONA__29 = new com.codecharge.components.Label("CODICE_ZONA", "CODICE_ZONA", this );
            CODICE_ZONA__29.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_ZONA__29.setHtmlEncode( true );
            ZoneAslElenco.add(CODICE_ZONA__29);

            com.codecharge.components.Link TITOLO__13 = new com.codecharge.components.Link("TITOLO", "TITOLO", this );
            TITOLO__13.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO__13.setHtmlEncode( true );
            TITOLO__13.setHrefType( "Page" );
            TITOLO__13.setConvertRule("Relative");
            TITOLO__13.setPreserveType(PreserveParameterType.GET);
            TITOLO__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            TITOLO__13.addParameter( new LinkParameter( "ID_ZONA_ASL", "ID_ZONA_ASL", ParameterSource.DATAFIELD) );
            ZoneAslElenco.add( TITOLO__13 );

            com.codecharge.components.Label REGIONE_DENOMINAZIONE__16 = new com.codecharge.components.Label("REGIONE_DENOMINAZIONE", "REGIONE_DENOMINAZIONE", this );
            REGIONE_DENOMINAZIONE__16.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE_DENOMINAZIONE__16.setHtmlEncode( true );
            ZoneAslElenco.add(REGIONE_DENOMINAZIONE__16);

            com.codecharge.components.Label AFCNavigator__22 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__22.setType( com.codecharge.components.ControlType.TEXT );
            ZoneAslElenco.add(AFCNavigator__22);
            add(ZoneAslElenco);
        } // End definition of ZoneAslElenco grid model
//End ZoneAslElenco grid

//Ad4DizionariZoneAslElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariZoneAslElencoModel class tail

