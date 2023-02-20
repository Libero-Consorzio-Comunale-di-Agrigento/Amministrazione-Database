//Ad4DizionariAslComuniElencoModel imports @1-F4F41907
package ad4web.Ad4DizionariAslComuniElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariAslComuniElencoModel imports

//Ad4DizionariAslComuniElencoModel class head @1-66A390D3
public class Ad4DizionariAslComuniElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariAslComuniElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariAslComuniElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariAslComuniElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariAslComuniElencoModel class head

//page settings @1-90AA7C2B
        super("Ad4DizionariAslComuniElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//ComuniFiltro record @3-AF41F0AA
        
        /*
            Model of ComuniFiltro record defining.
        */
        {
            com.codecharge.components.Record ComuniFiltro = new com.codecharge.components.Record("ComuniFiltro");
            ComuniFiltro.setPageModel( this );
            ComuniFiltro.addExcludeParam( "ccsForm" );
            ComuniFiltro.setVisible( true );
            ComuniFiltro.setAllowInsert(false);
            ComuniFiltro.setAllowUpdate(false);
            ComuniFiltro.setAllowDelete(false);
            ComuniFiltro.setPreserveType(PreserveParameterType.GET);
            ComuniFiltro.setReturnPage("Ad4DizionariAslComuniElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__24 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__24.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__24.setHrefType( "Page" );
            FILTER_SEARCH__24.setConvertRule("Relative");
            FILTER_SEARCH__24.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__24.addExcludeParam( "s_COMUNE" );
            ComuniFiltro.add( FILTER_SEARCH__24 );

            com.codecharge.components.TextBox s_COMUNE__5 = new com.codecharge.components.TextBox("s_COMUNE", "S_COMUNE", this );
            s_COMUNE__5.setType( com.codecharge.components.ControlType.TEXT );
            s_COMUNE__5.setHtmlEncode( true );
            s_COMUNE__5.setCaption( "Comune" );
            ComuniFiltro.add( s_COMUNE__5 );

            com.codecharge.components.Button DoSearch__25 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__25.addExcludeParam( "ccsForm" );
            DoSearch__25.addExcludeParam( "DoSearch" );
            DoSearch__25.setOperation( "Search" );
            ComuniFiltro.add( DoSearch__25 );
            add(ComuniFiltro);
        } // End definition of ComuniFiltro record model.
//End ComuniFiltro record

//Titolo grid @40-3865A8C0
        
        /*
            // Begin definition of Titolo grid model.
        */
        {
            com.codecharge.components.Grid Titolo = new com.codecharge.components.Grid("Titolo");
            Titolo.setPageModel( this );
            Titolo.setFetchSize(20);
            Titolo.setVisible( true );
            Titolo.addGridListener( new TitoloGridHandler() );

            com.codecharge.components.Label TITOLO__41 = new com.codecharge.components.Label("TITOLO", "TITOLO", this );
            TITOLO__41.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO__41.setHtmlEncode( true );
            Titolo.add(TITOLO__41);

            com.codecharge.components.Link Aggiungi__45 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__45.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__45.setHtmlEncode( true );
            Aggiungi__45.setHrefType( "Page" );
            Aggiungi__45.setConvertRule("Relative");
            Aggiungi__45.setPreserveType(PreserveParameterType.GET);
            Aggiungi__45.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            Titolo.add( Aggiungi__45 );
            add(Titolo);
        } // End definition of Titolo grid model
//End Titolo grid

//ComuniElenco grid @8-30028E0F
        
        /*
            // Begin definition of ComuniElenco grid model.
        */
        {
            com.codecharge.components.Grid ComuniElenco = new com.codecharge.components.Grid("ComuniElenco");
            ComuniElenco.setPageModel( this );
            ComuniElenco.setFetchSize(25);
            ComuniElenco.setVisible( true );
            ComuniElenco.addGridListener( new ComuniElencoGridHandler() );

            com.codecharge.components.Link COMUNE_DESC__13 = new com.codecharge.components.Link("COMUNE_DESC", "COMUNE_DESC", this );
            COMUNE_DESC__13.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE_DESC__13.setHtmlEncode( true );
            COMUNE_DESC__13.setHrefType( "Page" );
            COMUNE_DESC__13.setConvertRule("Relative");
            COMUNE_DESC__13.setPreserveType(PreserveParameterType.GET);
            COMUNE_DESC__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            COMUNE_DESC__13.addParameter( new LinkParameter( "COMUNE", "COMUNE", ParameterSource.DATAFIELD) );
            COMUNE_DESC__13.addParameter( new LinkParameter( "PROVINCIA", "PROVINCIA", ParameterSource.DATAFIELD) );
            COMUNE_DESC__13.addParameter( new LinkParameter( "REGIONE_ASL", "REGIONE_ASL", ParameterSource.DATAFIELD) );
            COMUNE_DESC__13.addParameter( new LinkParameter( "CODICE_ASL", "CODICE_ASL", ParameterSource.DATAFIELD) );
            ComuniElenco.add( COMUNE_DESC__13 );

            com.codecharge.components.Label UTENTE_AGGIORNAMENTO__21 = new com.codecharge.components.Label("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO", this );
            UTENTE_AGGIORNAMENTO__21.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_AGGIORNAMENTO__21.setHtmlEncode( true );
            ComuniElenco.add(UTENTE_AGGIORNAMENTO__21);

            com.codecharge.components.Label DATA_AGGIORNAMENTO__20 = new com.codecharge.components.Label("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO", this );
            DATA_AGGIORNAMENTO__20.setType( com.codecharge.components.ControlType.TEXT );
            DATA_AGGIORNAMENTO__20.setHtmlEncode( true );
            ComuniElenco.add(DATA_AGGIORNAMENTO__20);

            com.codecharge.components.Label AFCNavigator__28 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__28.setType( com.codecharge.components.ControlType.TEXT );
            ComuniElenco.add(AFCNavigator__28);
            add(ComuniElenco);
        } // End definition of ComuniElenco grid model
//End ComuniElenco grid

//Ad4DizionariAslComuniElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariAslComuniElencoModel class tail

