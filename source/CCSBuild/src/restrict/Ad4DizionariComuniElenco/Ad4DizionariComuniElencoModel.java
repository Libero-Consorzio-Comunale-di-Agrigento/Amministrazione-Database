//Ad4DizionariComuniElencoModel imports @1-B5E01E97
package restrict.Ad4DizionariComuniElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariComuniElencoModel imports

//Ad4DizionariComuniElencoModel class head @1-BE7FE3FF
public class Ad4DizionariComuniElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariComuniElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariComuniElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariComuniElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariComuniElencoModel class head

//page settings @1-191EAF0C
        super("Ad4DizionariComuniElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariGuida__2 = new com.codecharge.components.IncludePage("Ad4DizionariGuida", this );
            Ad4DizionariGuida__2.setVisible( true );
            add( Ad4DizionariGuida__2 );
        } // end page
//End page settings

//ComuniFiltro record @3-97993CF5
        
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
            ComuniFiltro.setReturnPage("Ad4DizionariComuniElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__24 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__24.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__24.setHrefType( "Page" );
            FILTER_SEARCH__24.setConvertRule("Relative");
            FILTER_SEARCH__24.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__24.addExcludeParam( "s_COMUNE" );
            FILTER_SEARCH__24.addExcludeParam( "s_PROVINCIA" );
            ComuniFiltro.add( FILTER_SEARCH__24 );

            com.codecharge.components.ListBox s_PROVINCIA__26 = new com.codecharge.components.ListBox("s_PROVINCIA", "S_PROVINCIA", this );
            s_PROVINCIA__26.setType( com.codecharge.components.ControlType.TEXT );
            s_PROVINCIA__26.setHtmlEncode( true );
            s_PROVINCIA__26.setBoundColumn( "PROVINCIA" );
            s_PROVINCIA__26.setTextColumn( "DENOMINAZIONE" );
            ComuniFiltro.add( s_PROVINCIA__26 );

            com.codecharge.components.Button DoSearch__25 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__25.addExcludeParam( "ccsForm" );
            DoSearch__25.addExcludeParam( "DoSearch" );
            DoSearch__25.setOperation( "Search" );
            ComuniFiltro.add( DoSearch__25 );

            com.codecharge.components.TextBox s_COMUNE__5 = new com.codecharge.components.TextBox("s_COMUNE", "S_COMUNE", this );
            s_COMUNE__5.setType( com.codecharge.components.ControlType.TEXT );
            s_COMUNE__5.setHtmlEncode( true );
            s_COMUNE__5.setCaption( "Comune" );
            ComuniFiltro.add( s_COMUNE__5 );
            add(ComuniFiltro);
        } // End definition of ComuniFiltro record model.
//End ComuniFiltro record

//ComuniElenco grid @8-5495B23D
        
        /*
            // Begin definition of ComuniElenco grid model.
        */
        {
            com.codecharge.components.Grid ComuniElenco = new com.codecharge.components.Grid("ComuniElenco");
            ComuniElenco.setPageModel( this );
            ComuniElenco.setFetchSize(25);
            ComuniElenco.setVisible( true );
            ComuniElenco.addGridListener( new ComuniElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            ComuniElenco.add( Aggiungi__9 );

            com.codecharge.components.Label PROVINCIA_STATO__31 = new com.codecharge.components.Label("PROVINCIA_STATO", "PROVINCIA_STATO", this );
            PROVINCIA_STATO__31.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA_STATO__31.setHtmlEncode( true );
            ComuniElenco.add(PROVINCIA_STATO__31);

            com.codecharge.components.Label COMUNE__32 = new com.codecharge.components.Label("COMUNE", "COMUNE", this );
            COMUNE__32.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE__32.setHtmlEncode( true );
            ComuniElenco.add(COMUNE__32);

            com.codecharge.components.Link DENOMINAZIONE__13 = new com.codecharge.components.Link("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__13.setHtmlEncode( true );
            DENOMINAZIONE__13.setHrefType( "Page" );
            DENOMINAZIONE__13.setConvertRule("Relative");
            DENOMINAZIONE__13.setPreserveType(PreserveParameterType.GET);
            DENOMINAZIONE__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            DENOMINAZIONE__13.addParameter( new LinkParameter( "COMUNE", "COMUNE", ParameterSource.DATAFIELD) );
            DENOMINAZIONE__13.addParameter( new LinkParameter( "PROVINCIA_STATO", "PROVINCIA_STATO", ParameterSource.DATAFIELD) );
            ComuniElenco.add( DENOMINAZIONE__13 );

            com.codecharge.components.Label CAP__16 = new com.codecharge.components.Label("CAP", "CAP", this );
            CAP__16.setType( com.codecharge.components.ControlType.TEXT );
            CAP__16.setHtmlEncode( true );
            ComuniElenco.add(CAP__16);

            com.codecharge.components.Label PROVINCIA_DESC__12 = new com.codecharge.components.Label("PROVINCIA_DESC", "PROVINCIA_DESC", this );
            PROVINCIA_DESC__12.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA_DESC__12.setHtmlEncode( true );
            ComuniElenco.add(PROVINCIA_DESC__12);

            com.codecharge.components.Label SOPPRESSIONED_DATA__33 = new com.codecharge.components.Label("SOPPRESSIONED_DATA", "SOPPRESSIONED_DATA", this );
            SOPPRESSIONED_DATA__33.setType( com.codecharge.components.ControlType.TEXT );
            SOPPRESSIONED_DATA__33.setHtmlEncode( true );
            ComuniElenco.add(SOPPRESSIONED_DATA__33);

            com.codecharge.components.Label FUSIONE_DESC__34 = new com.codecharge.components.Label("FUSIONE_DESC", "FUSIONE_DESC", this );
            FUSIONE_DESC__34.setType( com.codecharge.components.ControlType.TEXT );
            FUSIONE_DESC__34.setHtmlEncode( true );
            ComuniElenco.add(FUSIONE_DESC__34);

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

//Ad4DizionariComuniElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariComuniElencoModel class tail

