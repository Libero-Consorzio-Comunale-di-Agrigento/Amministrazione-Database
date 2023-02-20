//Ad4DizionariBancheElencoModel imports @1-D5F4A81E
package restrict.Ad4DizionariBancheElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariBancheElencoModel imports

//Ad4DizionariBancheElencoModel class head @1-DEA71127
public class Ad4DizionariBancheElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariBancheElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariBancheElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariBancheElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariBancheElencoModel class head

//page settings @1-97EA6570
        super("Ad4DizionariBancheElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariBancheGuida__24 = new com.codecharge.components.IncludePage("Ad4DizionariBancheGuida", this );
            Ad4DizionariBancheGuida__24.setVisible( true );
            add( Ad4DizionariBancheGuida__24 );
        } // end page
//End page settings

//BancheFiltro record @3-D1A7250F
        
        /*
            Model of BancheFiltro record defining.
        */
        {
            com.codecharge.components.Record BancheFiltro = new com.codecharge.components.Record("BancheFiltro");
            BancheFiltro.setPageModel( this );
            BancheFiltro.addExcludeParam( "ccsForm" );
            BancheFiltro.setVisible( true );
            BancheFiltro.setAllowInsert(false);
            BancheFiltro.setAllowUpdate(false);
            BancheFiltro.setAllowDelete(false);
            BancheFiltro.setPreserveType(PreserveParameterType.GET);
            BancheFiltro.setReturnPage("Ad4DizionariBancheElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__4 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__4.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__4.setHrefType( "Page" );
            FILTER_SEARCH__4.setConvertRule("Relative");
            FILTER_SEARCH__4.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__4.addExcludeParam( "s_BANCA" );
            BancheFiltro.add( FILTER_SEARCH__4 );

            com.codecharge.components.TextBox s_BANCA__5 = new com.codecharge.components.TextBox("s_BANCA", "S_BANCA", this );
            s_BANCA__5.setType( com.codecharge.components.ControlType.TEXT );
            s_BANCA__5.setHtmlEncode( true );
            s_BANCA__5.setCaption( "Banca" );
            BancheFiltro.add( s_BANCA__5 );

            com.codecharge.components.Button DoSearch__6 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__6.addExcludeParam( "ccsForm" );
            DoSearch__6.addExcludeParam( "DoSearch" );
            DoSearch__6.setOperation( "Search" );
            BancheFiltro.add( DoSearch__6 );
            add(BancheFiltro);
        } // End definition of BancheFiltro record model.
//End BancheFiltro record

//BancheElenco grid @8-87D14999
        
        /*
            // Begin definition of BancheElenco grid model.
        */
        {
            com.codecharge.components.Grid BancheElenco = new com.codecharge.components.Grid("BancheElenco");
            BancheElenco.setPageModel( this );
            BancheElenco.setFetchSize(25);
            BancheElenco.setVisible( true );
            BancheElenco.addGridListener( new BancheElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            BancheElenco.add( Aggiungi__9 );

            com.codecharge.components.Label ABI__12 = new com.codecharge.components.Label("ABI", "ABI", this );
            ABI__12.setType( com.codecharge.components.ControlType.TEXT );
            ABI__12.setHtmlEncode( true );
            BancheElenco.add(ABI__12);

            com.codecharge.components.Label LINK_SPORTELLI__23 = new com.codecharge.components.Label("LINK_SPORTELLI", "LINK_SPORTELLI", this );
            LINK_SPORTELLI__23.setType( com.codecharge.components.ControlType.TEXT );
            BancheElenco.add(LINK_SPORTELLI__23);

            com.codecharge.components.Link DENOMINAZIONE__13 = new com.codecharge.components.Link("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__13.setHtmlEncode( true );
            DENOMINAZIONE__13.setHrefType( "Page" );
            DENOMINAZIONE__13.setConvertRule("Relative");
            DENOMINAZIONE__13.setPreserveType(PreserveParameterType.GET);
            DENOMINAZIONE__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            DENOMINAZIONE__13.addParameter( new LinkParameter( "BANCA", "ABI", ParameterSource.DATAFIELD) );
            BancheElenco.add( DENOMINAZIONE__13 );

            com.codecharge.components.Label CIN_ABI__16 = new com.codecharge.components.Label("CIN_ABI", "CIN_ABI", this );
            CIN_ABI__16.setType( com.codecharge.components.ControlType.TEXT );
            CIN_ABI__16.setHtmlEncode( true );
            BancheElenco.add(CIN_ABI__16);

            com.codecharge.components.Label AFCNavigator__22 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__22.setType( com.codecharge.components.ControlType.TEXT );
            BancheElenco.add(AFCNavigator__22);
            add(BancheElenco);
        } // End definition of BancheElenco grid model
//End BancheElenco grid

//Ad4DizionariBancheElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariBancheElencoModel class tail
