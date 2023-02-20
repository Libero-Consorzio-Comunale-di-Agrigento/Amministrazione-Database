//Ad4DizionariProvinceElencoModel imports @1-DC5C5F84
package ad4web.Ad4DizionariProvinceElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariProvinceElencoModel imports

//Ad4DizionariProvinceElencoModel class head @1-BEE7C306
public class Ad4DizionariProvinceElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariProvinceElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariProvinceElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariProvinceElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariProvinceElencoModel class head

//page settings @1-B2013742
        super("Ad4DizionariProvinceElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariGuida__2 = new com.codecharge.components.IncludePage("Ad4DizionariGuida", this );
            Ad4DizionariGuida__2.setVisible( true );
            add( Ad4DizionariGuida__2 );
        } // end page
//End page settings

//ProvinceFiltro record @3-47B3BA94
        
        /*
            Model of ProvinceFiltro record defining.
        */
        {
            com.codecharge.components.Record ProvinceFiltro = new com.codecharge.components.Record("ProvinceFiltro");
            ProvinceFiltro.setPageModel( this );
            ProvinceFiltro.addExcludeParam( "ccsForm" );
            ProvinceFiltro.setVisible( true );
            ProvinceFiltro.setAllowInsert(false);
            ProvinceFiltro.setAllowUpdate(false);
            ProvinceFiltro.setAllowDelete(false);
            ProvinceFiltro.setPreserveType(PreserveParameterType.GET);
            ProvinceFiltro.setReturnPage("Ad4DizionariProvinceElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__24 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__24.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__24.setHrefType( "Page" );
            FILTER_SEARCH__24.setConvertRule("Relative");
            FILTER_SEARCH__24.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__24.addExcludeParam( "s_REGIONE" );
            FILTER_SEARCH__24.addExcludeParam( "s_PROVINCIA" );
            ProvinceFiltro.add( FILTER_SEARCH__24 );

            com.codecharge.components.ListBox s_REGIONE__26 = new com.codecharge.components.ListBox("s_REGIONE", "S_REGIONE", this );
            s_REGIONE__26.setType( com.codecharge.components.ControlType.TEXT );
            s_REGIONE__26.setHtmlEncode( true );
            ProvinceFiltro.add( s_REGIONE__26 );

            com.codecharge.components.Button DoSearch__25 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__25.addExcludeParam( "ccsForm" );
            DoSearch__25.addExcludeParam( "DoSearch" );
            DoSearch__25.setOperation( "Search" );
            ProvinceFiltro.add( DoSearch__25 );

            com.codecharge.components.TextBox s_PROVINCIA__5 = new com.codecharge.components.TextBox("s_PROVINCIA", "S_PROVINCIA", this );
            s_PROVINCIA__5.setType( com.codecharge.components.ControlType.TEXT );
            s_PROVINCIA__5.setHtmlEncode( true );
            s_PROVINCIA__5.setCaption( "Regione" );
            ProvinceFiltro.add( s_PROVINCIA__5 );
            add(ProvinceFiltro);
        } // End definition of ProvinceFiltro record model.
//End ProvinceFiltro record

//ProvinceElenco grid @8-9163BF77
        
        /*
            // Begin definition of ProvinceElenco grid model.
        */
        {
            com.codecharge.components.Grid ProvinceElenco = new com.codecharge.components.Grid("ProvinceElenco");
            ProvinceElenco.setPageModel( this );
            ProvinceElenco.setFetchSize(25);
            ProvinceElenco.setVisible( true );
            ProvinceElenco.addGridListener( new ProvinceElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            ProvinceElenco.add( Aggiungi__9 );

            com.codecharge.components.Label PROVINCIA__23 = new com.codecharge.components.Label("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__23.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA__23.setHtmlEncode( true );
            ProvinceElenco.add(PROVINCIA__23);

            com.codecharge.components.Label LINK_COMUNI__29 = new com.codecharge.components.Label("LINK_COMUNI", "LINK_COMUNI", this );
            LINK_COMUNI__29.setType( com.codecharge.components.ControlType.TEXT );
            ProvinceElenco.add(LINK_COMUNI__29);

            com.codecharge.components.Link DENOMINAZIONE__13 = new com.codecharge.components.Link("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__13.setHtmlEncode( true );
            DENOMINAZIONE__13.setHrefType( "Page" );
            DENOMINAZIONE__13.setConvertRule("Relative");
            DENOMINAZIONE__13.setPreserveType(PreserveParameterType.GET);
            DENOMINAZIONE__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            DENOMINAZIONE__13.addParameter( new LinkParameter( "PROVINCIA", "PROVINCIA", ParameterSource.DATAFIELD) );
            ProvinceElenco.add( DENOMINAZIONE__13 );

            com.codecharge.components.Label SIGLA__16 = new com.codecharge.components.Label("SIGLA", "SIGLA", this );
            SIGLA__16.setType( com.codecharge.components.ControlType.TEXT );
            SIGLA__16.setHtmlEncode( true );
            ProvinceElenco.add(SIGLA__16);

            com.codecharge.components.Label REGIONE__12 = new com.codecharge.components.Label("REGIONE", "REGIONE", this );
            REGIONE__12.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE__12.setHtmlEncode( true );
            ProvinceElenco.add(REGIONE__12);

            com.codecharge.components.Label UTENTE_AGGIORNAMENTO__21 = new com.codecharge.components.Label("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO", this );
            UTENTE_AGGIORNAMENTO__21.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_AGGIORNAMENTO__21.setHtmlEncode( true );
            ProvinceElenco.add(UTENTE_AGGIORNAMENTO__21);

            com.codecharge.components.Label DATA_AGGIORNAMENTO__20 = new com.codecharge.components.Label("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO", this );
            DATA_AGGIORNAMENTO__20.setType( com.codecharge.components.ControlType.TEXT );
            DATA_AGGIORNAMENTO__20.setHtmlEncode( true );
            ProvinceElenco.add(DATA_AGGIORNAMENTO__20);

            com.codecharge.components.Label AFCNavigator__28 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__28.setType( com.codecharge.components.ControlType.TEXT );
            ProvinceElenco.add(AFCNavigator__28);
            add(ProvinceElenco);
        } // End definition of ProvinceElenco grid model
//End ProvinceElenco grid

//Ad4DizionariProvinceElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariProvinceElencoModel class tail

