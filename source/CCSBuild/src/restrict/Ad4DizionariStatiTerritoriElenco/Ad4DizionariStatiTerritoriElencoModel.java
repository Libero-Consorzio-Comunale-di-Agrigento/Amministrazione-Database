//Ad4DizionariStatiTerritoriElencoModel imports @1-F4349847
package restrict.Ad4DizionariStatiTerritoriElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariStatiTerritoriElencoModel imports

//Ad4DizionariStatiTerritoriElencoModel class head @1-FBB67642
public class Ad4DizionariStatiTerritoriElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariStatiTerritoriElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariStatiTerritoriElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariStatiTerritoriElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariStatiTerritoriElencoModel class head

//page settings @1-FF22362A
        super("Ad4DizionariStatiTerritoriElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariGuida__2 = new com.codecharge.components.IncludePage("Ad4DizionariGuida", this );
            Ad4DizionariGuida__2.setVisible( true );
            add( Ad4DizionariGuida__2 );
        } // end page
//End page settings

//StatiFiltro record @3-205C2216
        
        /*
            Model of StatiFiltro record defining.
        */
        {
            com.codecharge.components.Record StatiFiltro = new com.codecharge.components.Record("StatiFiltro");
            StatiFiltro.setPageModel( this );
            StatiFiltro.addExcludeParam( "ccsForm" );
            StatiFiltro.setVisible( true );
            StatiFiltro.setAllowInsert(false);
            StatiFiltro.setAllowUpdate(false);
            StatiFiltro.setAllowDelete(false);
            StatiFiltro.setPreserveType(PreserveParameterType.GET);
            StatiFiltro.setReturnPage("Ad4DizionariStatiTerritoriElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__4 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__4.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__4.setHrefType( "Page" );
            FILTER_SEARCH__4.setConvertRule("Relative");
            FILTER_SEARCH__4.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__4.addExcludeParam( "s_STATO" );
            StatiFiltro.add( FILTER_SEARCH__4 );

            com.codecharge.components.TextBox s_STATO__5 = new com.codecharge.components.TextBox("s_STATO", "S_STATO", this );
            s_STATO__5.setType( com.codecharge.components.ControlType.TEXT );
            s_STATO__5.setHtmlEncode( true );
            s_STATO__5.setCaption( "Stato" );
            StatiFiltro.add( s_STATO__5 );

            com.codecharge.components.Button DoSearch__6 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__6.addExcludeParam( "ccsForm" );
            DoSearch__6.addExcludeParam( "DoSearch" );
            DoSearch__6.setOperation( "Search" );
            StatiFiltro.add( DoSearch__6 );
            add(StatiFiltro);
        } // End definition of StatiFiltro record model.
//End StatiFiltro record

//StatiElenco grid @8-E71F9834
        
        /*
            // Begin definition of StatiElenco grid model.
        */
        {
            com.codecharge.components.Grid StatiElenco = new com.codecharge.components.Grid("StatiElenco");
            StatiElenco.setPageModel( this );
            StatiElenco.setFetchSize(25);
            StatiElenco.setVisible( true );
            StatiElenco.addGridListener( new StatiElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            StatiElenco.add( Aggiungi__9 );

            com.codecharge.components.Label STATO_TERRITORIO__12 = new com.codecharge.components.Label("STATO_TERRITORIO", "STATO_TERRITORIO", this );
            STATO_TERRITORIO__12.setType( com.codecharge.components.ControlType.TEXT );
            STATO_TERRITORIO__12.setHtmlEncode( true );
            StatiElenco.add(STATO_TERRITORIO__12);

            com.codecharge.components.Label LINK_COMUNI__24 = new com.codecharge.components.Label("LINK_COMUNI", "LINK_COMUNI", this );
            LINK_COMUNI__24.setType( com.codecharge.components.ControlType.TEXT );
            StatiElenco.add(LINK_COMUNI__24);

            com.codecharge.components.Link DENOMINAZIONE__13 = new com.codecharge.components.Link("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__13.setHtmlEncode( true );
            DENOMINAZIONE__13.setHrefType( "Page" );
            DENOMINAZIONE__13.setConvertRule("Relative");
            DENOMINAZIONE__13.setPreserveType(PreserveParameterType.GET);
            DENOMINAZIONE__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            DENOMINAZIONE__13.addParameter( new LinkParameter( "STATO_TERRITORIO", "STATO_TERRITORIO", ParameterSource.DATAFIELD) );
            StatiElenco.add( DENOMINAZIONE__13 );

            com.codecharge.components.Label SIGLA__16 = new com.codecharge.components.Label("SIGLA", "SIGLA", this );
            SIGLA__16.setType( com.codecharge.components.ControlType.TEXT );
            SIGLA__16.setHtmlEncode( true );
            StatiElenco.add(SIGLA__16);

            com.codecharge.components.Label DESC_CITTADINANZA__23 = new com.codecharge.components.Label("DESC_CITTADINANZA", "DESC_CITTADINANZA", this );
            DESC_CITTADINANZA__23.setType( com.codecharge.components.ControlType.TEXT );
            DESC_CITTADINANZA__23.setHtmlEncode( true );
            StatiElenco.add(DESC_CITTADINANZA__23);

            com.codecharge.components.Label UTENTE_AGGIORNAMENTO__21 = new com.codecharge.components.Label("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO", this );
            UTENTE_AGGIORNAMENTO__21.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_AGGIORNAMENTO__21.setHtmlEncode( true );
            StatiElenco.add(UTENTE_AGGIORNAMENTO__21);

            com.codecharge.components.Label DATA_AGGIORNAMENTO__20 = new com.codecharge.components.Label("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO", this );
            DATA_AGGIORNAMENTO__20.setType( com.codecharge.components.ControlType.TEXT );
            DATA_AGGIORNAMENTO__20.setHtmlEncode( true );
            StatiElenco.add(DATA_AGGIORNAMENTO__20);

            com.codecharge.components.Label AFCNavigator__22 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__22.setType( com.codecharge.components.ControlType.TEXT );
            StatiElenco.add(AFCNavigator__22);
            add(StatiElenco);
        } // End definition of StatiElenco grid model
//End StatiElenco grid

//Ad4DizionariStatiTerritoriElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariStatiTerritoriElencoModel class tail

