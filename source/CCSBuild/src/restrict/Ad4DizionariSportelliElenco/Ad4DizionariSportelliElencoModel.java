//Ad4DizionariSportelliElencoModel imports @1-9AFB3F83
package restrict.Ad4DizionariSportelliElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariSportelliElencoModel imports

//Ad4DizionariSportelliElencoModel class head @1-44689B28
public class Ad4DizionariSportelliElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariSportelliElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariSportelliElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariSportelliElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariSportelliElencoModel class head

//page settings @1-AB98E0CA
        super("Ad4DizionariSportelliElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariBancheGuida__2 = new com.codecharge.components.IncludePage("Ad4DizionariBancheGuida", this );
            Ad4DizionariBancheGuida__2.setVisible( true );
            add( Ad4DizionariBancheGuida__2 );
        } // end page
//End page settings

//SportelliFiltro record @3-CE3C719F
        
        /*
            Model of SportelliFiltro record defining.
        */
        {
            com.codecharge.components.Record SportelliFiltro = new com.codecharge.components.Record("SportelliFiltro");
            SportelliFiltro.setPageModel( this );
            SportelliFiltro.addExcludeParam( "ccsForm" );
            SportelliFiltro.addExcludeParam( "SportelliElencoPage" );
            SportelliFiltro.setVisible( true );
            SportelliFiltro.setAllowInsert(false);
            SportelliFiltro.setAllowUpdate(false);
            SportelliFiltro.setAllowDelete(false);
            SportelliFiltro.setPreserveType(PreserveParameterType.GET);
            SportelliFiltro.setReturnPage("Ad4DizionariSportelliElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__24 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__24.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__24.setHrefType( "Page" );
            FILTER_SEARCH__24.setConvertRule("Relative");
            FILTER_SEARCH__24.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__24.addExcludeParam( "s_BANCA" );
            FILTER_SEARCH__24.addExcludeParam( "s_SPORTELLO" );
            SportelliFiltro.add( FILTER_SEARCH__24 );

            com.codecharge.components.ListBox s_BANCA__26 = new com.codecharge.components.ListBox("s_BANCA", "S_BANCA", this );
            s_BANCA__26.setType( com.codecharge.components.ControlType.TEXT );
            s_BANCA__26.setHtmlEncode( true );
            SportelliFiltro.add( s_BANCA__26 );

            com.codecharge.components.Button DoSearch__25 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__25.addExcludeParam( "ccsForm" );
            DoSearch__25.addExcludeParam( "DoSearch" );
            DoSearch__25.setOperation( "Search" );
            SportelliFiltro.add( DoSearch__25 );

            com.codecharge.components.TextBox s_SPORTELLO__5 = new com.codecharge.components.TextBox("s_SPORTELLO", "S_SPORTELLO", this );
            s_SPORTELLO__5.setType( com.codecharge.components.ControlType.TEXT );
            s_SPORTELLO__5.setHtmlEncode( true );
            s_SPORTELLO__5.setCaption( "Sportello" );
            SportelliFiltro.add( s_SPORTELLO__5 );
            add(SportelliFiltro);
        } // End definition of SportelliFiltro record model.
//End SportelliFiltro record

//SportelliElenco grid @8-00EA74C4
        
        /*
            // Begin definition of SportelliElenco grid model.
        */
        {
            com.codecharge.components.Grid SportelliElenco = new com.codecharge.components.Grid("SportelliElenco");
            SportelliElenco.setPageModel( this );
            SportelliElenco.setFetchSize(25);
            SportelliElenco.setVisible( true );
            SportelliElenco.addGridListener( new SportelliElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.addControlListener( new SportelliElencoAggiungiHandler());
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            SportelliElenco.add( Aggiungi__9 );

            com.codecharge.components.Link CAB__23 = new com.codecharge.components.Link("CAB", "CAB", this );
            CAB__23.setType( com.codecharge.components.ControlType.TEXT );
            CAB__23.setHtmlEncode( true );
            CAB__23.setHrefType( "Page" );
            CAB__23.setConvertRule("Relative");
            CAB__23.setPreserveType(PreserveParameterType.GET);
            CAB__23.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            CAB__23.addParameter( new LinkParameter( "SPORTELLO", "CAB", ParameterSource.DATAFIELD) );
            CAB__23.addParameter( new LinkParameter( "BANCA", "ABI", ParameterSource.DATAFIELD) );
            SportelliElenco.add( CAB__23 );

            com.codecharge.components.Hidden ABI__39 = new com.codecharge.components.Hidden("ABI", "ABI", this );
            ABI__39.setType( com.codecharge.components.ControlType.TEXT );
            ABI__39.setHtmlEncode( true );
            SportelliElenco.add( ABI__39 );

            com.codecharge.components.Label CIN_CAB__33 = new com.codecharge.components.Label("CIN_CAB", "CIN_CAB", this );
            CIN_CAB__33.setType( com.codecharge.components.ControlType.TEXT );
            CIN_CAB__33.setHtmlEncode( true );
            SportelliElenco.add(CIN_CAB__33);

            com.codecharge.components.Label DESCRIZIONE__13 = new com.codecharge.components.Label("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__13.setHtmlEncode( true );
            SportelliElenco.add(DESCRIZIONE__13);

            com.codecharge.components.Label INDIRIZZO__16 = new com.codecharge.components.Label("INDIRIZZO", "INDIRIZZO", this );
            INDIRIZZO__16.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO__16.setHtmlEncode( true );
            SportelliElenco.add(INDIRIZZO__16);

            com.codecharge.components.Label LOCALITA__12 = new com.codecharge.components.Label("LOCALITA", "LOCALITA", this );
            LOCALITA__12.setType( com.codecharge.components.ControlType.TEXT );
            LOCALITA__12.setHtmlEncode( true );
            SportelliElenco.add(LOCALITA__12);

            com.codecharge.components.Label COMUNE__29 = new com.codecharge.components.Label("COMUNE", "COMUNE", this );
            COMUNE__29.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE__29.setHtmlEncode( true );
            SportelliElenco.add(COMUNE__29);

            com.codecharge.components.Label PROVINCIA__30 = new com.codecharge.components.Label("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__30.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA__30.setHtmlEncode( true );
            SportelliElenco.add(PROVINCIA__30);

            com.codecharge.components.Label CAP__21 = new com.codecharge.components.Label("CAP", "CAP", this );
            CAP__21.setType( com.codecharge.components.ControlType.TEXT );
            CAP__21.setHtmlEncode( true );
            SportelliElenco.add(CAP__21);

            com.codecharge.components.Label DIPENDENZA__31 = new com.codecharge.components.Label("DIPENDENZA", "DIPENDENZA", this );
            DIPENDENZA__31.setType( com.codecharge.components.ControlType.TEXT );
            DIPENDENZA__31.setHtmlEncode( true );
            SportelliElenco.add(DIPENDENZA__31);

            com.codecharge.components.Label BIC__32 = new com.codecharge.components.Label("BIC", "BIC", this );
            BIC__32.setType( com.codecharge.components.ControlType.TEXT );
            BIC__32.setHtmlEncode( true );
            SportelliElenco.add(BIC__32);

            com.codecharge.components.Label BANCA__20 = new com.codecharge.components.Label("BANCA", "BANCA", this );
            BANCA__20.setType( com.codecharge.components.ControlType.TEXT );
            BANCA__20.setHtmlEncode( true );
            SportelliElenco.add(BANCA__20);

            com.codecharge.components.Label AFCNavigator__28 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__28.setType( com.codecharge.components.ControlType.TEXT );
            SportelliElenco.add(AFCNavigator__28);
            add(SportelliElenco);
        } // End definition of SportelliElenco grid model
//End SportelliElenco grid

//Ad4DizionariSportelliElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariSportelliElencoModel class tail
