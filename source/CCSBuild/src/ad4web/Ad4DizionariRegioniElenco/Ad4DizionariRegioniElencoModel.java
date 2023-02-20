//Ad4DizionariRegioniElencoModel imports @1-06F0BE9B
package ad4web.Ad4DizionariRegioniElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariRegioniElencoModel imports

//Ad4DizionariRegioniElencoModel class head @1-B20F30DA
public class Ad4DizionariRegioniElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariRegioniElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariRegioniElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariRegioniElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariRegioniElencoModel class head

//page settings @1-00C36E2A
        super("Ad4DizionariRegioniElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariGuida__24 = new com.codecharge.components.IncludePage("Ad4DizionariGuida", this );
            Ad4DizionariGuida__24.setVisible( true );
            add( Ad4DizionariGuida__24 );
        } // end page
//End page settings

//RegioniFiltro record @3-F63FF6F3
        
        /*
            Model of RegioniFiltro record defining.
        */
        {
            com.codecharge.components.Record RegioniFiltro = new com.codecharge.components.Record("RegioniFiltro");
            RegioniFiltro.setPageModel( this );
            RegioniFiltro.addExcludeParam( "ccsForm" );
            RegioniFiltro.setVisible( true );
            RegioniFiltro.setAllowInsert(false);
            RegioniFiltro.setAllowUpdate(false);
            RegioniFiltro.setAllowDelete(false);
            RegioniFiltro.setPreserveType(PreserveParameterType.GET);
            RegioniFiltro.setReturnPage("Ad4DizionariRegioniElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__4 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__4.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__4.setHrefType( "Page" );
            FILTER_SEARCH__4.setConvertRule("Relative");
            FILTER_SEARCH__4.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__4.addExcludeParam( "s_REGIONE" );
            RegioniFiltro.add( FILTER_SEARCH__4 );

            com.codecharge.components.TextBox s_REGIONE__5 = new com.codecharge.components.TextBox("s_REGIONE", "S_REGIONE", this );
            s_REGIONE__5.setType( com.codecharge.components.ControlType.TEXT );
            s_REGIONE__5.setHtmlEncode( true );
            s_REGIONE__5.setCaption( "Regione" );
            RegioniFiltro.add( s_REGIONE__5 );

            com.codecharge.components.Button DoSearch__6 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__6.addExcludeParam( "ccsForm" );
            DoSearch__6.addExcludeParam( "DoSearch" );
            DoSearch__6.setOperation( "Search" );
            RegioniFiltro.add( DoSearch__6 );
            add(RegioniFiltro);
        } // End definition of RegioniFiltro record model.
//End RegioniFiltro record

//RegioniElenco grid @8-F681D9D3
        
        /*
            // Begin definition of RegioniElenco grid model.
        */
        {
            com.codecharge.components.Grid RegioniElenco = new com.codecharge.components.Grid("RegioniElenco");
            RegioniElenco.setPageModel( this );
            RegioniElenco.setFetchSize(25);
            RegioniElenco.setVisible( true );
            RegioniElenco.addGridListener( new RegioniElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            RegioniElenco.add( Aggiungi__9 );

            com.codecharge.components.Label REGIONE__12 = new com.codecharge.components.Label("REGIONE", "REGIONE", this );
            REGIONE__12.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE__12.setHtmlEncode( true );
            RegioniElenco.add(REGIONE__12);

            com.codecharge.components.Label LINK_PROVINCE__23 = new com.codecharge.components.Label("LINK_PROVINCE", "LINK_PROVINCE", this );
            LINK_PROVINCE__23.setType( com.codecharge.components.ControlType.TEXT );
            RegioniElenco.add(LINK_PROVINCE__23);

            com.codecharge.components.Link DENOMINAZIONE__13 = new com.codecharge.components.Link("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__13.setHtmlEncode( true );
            DENOMINAZIONE__13.setHrefType( "Page" );
            DENOMINAZIONE__13.setConvertRule("Relative");
            DENOMINAZIONE__13.setPreserveType(PreserveParameterType.GET);
            DENOMINAZIONE__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            DENOMINAZIONE__13.addParameter( new LinkParameter( "REGIONE", "REGIONE", ParameterSource.DATAFIELD) );
            RegioniElenco.add( DENOMINAZIONE__13 );

            com.codecharge.components.Label ID_REGIONE__16 = new com.codecharge.components.Label("ID_REGIONE", "ID_REGIONE", this );
            ID_REGIONE__16.setType( com.codecharge.components.ControlType.TEXT );
            ID_REGIONE__16.setHtmlEncode( true );
            RegioniElenco.add(ID_REGIONE__16);

            com.codecharge.components.Label UTENTE_AGGIORNAMENTO__21 = new com.codecharge.components.Label("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO", this );
            UTENTE_AGGIORNAMENTO__21.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_AGGIORNAMENTO__21.setHtmlEncode( true );
            RegioniElenco.add(UTENTE_AGGIORNAMENTO__21);

            com.codecharge.components.Label DATA_AGGIORNAMENTO__20 = new com.codecharge.components.Label("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO", this );
            DATA_AGGIORNAMENTO__20.setType( com.codecharge.components.ControlType.TEXT );
            DATA_AGGIORNAMENTO__20.setHtmlEncode( true );
            RegioniElenco.add(DATA_AGGIORNAMENTO__20);

            com.codecharge.components.Label AFCNavigator__22 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__22.setType( com.codecharge.components.ControlType.TEXT );
            RegioniElenco.add(AFCNavigator__22);
            add(RegioniElenco);
        } // End definition of RegioniElenco grid model
//End RegioniElenco grid

//Ad4DizionariRegioniElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariRegioniElencoModel class tail

