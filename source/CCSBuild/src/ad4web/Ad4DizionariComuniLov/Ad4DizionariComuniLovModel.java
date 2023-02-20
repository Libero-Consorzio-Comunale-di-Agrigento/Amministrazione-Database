//Ad4DizionariComuniLovModel imports @1-56907FED
package ad4web.Ad4DizionariComuniLov;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariComuniLovModel imports

//Ad4DizionariComuniLovModel class head @1-66023A5A
public class Ad4DizionariComuniLovModel extends com.codecharge.components.Page {
    public Ad4DizionariComuniLovModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariComuniLovModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariComuniLovModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariComuniLovModel class head

//page settings @1-6A373B2D
        super("Ad4DizionariComuniLov", locale );
        setResponse(response);
        addPageListener(new Ad4DizionariComuniLovPageHandler());
        {

            com.codecharge.components.Link CHIUDI__23 = new com.codecharge.components.Link("CHIUDI", this);
            CHIUDI__23.setType( com.codecharge.components.ControlType.TEXT );
            CHIUDI__23.setHtmlEncode( true );
            CHIUDI__23.setHrefType( "Page" );
            CHIUDI__23.setConvertRule("Relative");
            CHIUDI__23.setPreserveType(PreserveParameterType.GET);
            add( CHIUDI__23 );
        } // end page
//End page settings

//ComuniSearch record @2-0076D02D
        
        /*
            Model of ComuniSearch record defining.
        */
        {
            com.codecharge.components.Record ComuniSearch = new com.codecharge.components.Record("ComuniSearch");
            ComuniSearch.setPageModel( this );
            ComuniSearch.addExcludeParam( "ccsForm" );
            ComuniSearch.setVisible( true );
            ComuniSearch.setAllowInsert(false);
            ComuniSearch.setAllowUpdate(false);
            ComuniSearch.setAllowDelete(false);
            ComuniSearch.setPreserveType(PreserveParameterType.NONE);
            ComuniSearch.setReturnPage("Ad4DizionariComuniLov" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__20 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__20.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__20.setHrefSourceValue( "VcnComuniRecapitoLov" + Names.ACTION_SUFFIX );
            FILTER_SEARCH__20.setHrefType( "Page" );
            FILTER_SEARCH__20.setConvertRule("Relative");
            FILTER_SEARCH__20.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__20.addExcludeParam( "s_COMUNE" );
            FILTER_SEARCH__20.addExcludeParam( "s_PROVINCIA_SIGLA" );
            ComuniSearch.add( FILTER_SEARCH__20 );

            com.codecharge.components.TextBox s_COMUNE__3 = new com.codecharge.components.TextBox("s_COMUNE", "", this );
            s_COMUNE__3.setType( com.codecharge.components.ControlType.TEXT );
            s_COMUNE__3.setHtmlEncode( true );
            ComuniSearch.add( s_COMUNE__3 );

            com.codecharge.components.TextBox s_PROVINCIA_SIGLA__14 = new com.codecharge.components.TextBox("s_PROVINCIA_SIGLA", "", this );
            s_PROVINCIA_SIGLA__14.setType( com.codecharge.components.ControlType.TEXT );
            s_PROVINCIA_SIGLA__14.setHtmlEncode( true );
            ComuniSearch.add( s_PROVINCIA_SIGLA__14 );

            com.codecharge.components.Button DoSearch__4 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__4.addExcludeParam( "ccsForm" );
            DoSearch__4.addExcludeParam( "DoSearch" );
            DoSearch__4.setOperation( "Search" );
            ComuniSearch.add( DoSearch__4 );
            add(ComuniSearch);
        } // End definition of ComuniSearch record model.
//End ComuniSearch record

//Comuni grid @5-E09F60DB
        
        /*
            // Begin definition of Comuni grid model.
        */
        {
            com.codecharge.components.Grid Comuni = new com.codecharge.components.Grid("Comuni");
            Comuni.setPageModel( this );
            Comuni.setVisible( true );

            com.codecharge.components.Label COMUNE__6 = new com.codecharge.components.Label("COMUNE", "COMUNE", this );
            COMUNE__6.setType( com.codecharge.components.ControlType.TEXT );
            Comuni.add(COMUNE__6);

            com.codecharge.components.Label PROVINCIA__12 = new com.codecharge.components.Label("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__12.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA__12.setHtmlEncode( true );
            Comuni.add(PROVINCIA__12);

            com.codecharge.components.Label SOPPRESSIONE__24 = new com.codecharge.components.Label("SOPPRESSIONE", "SOPPRESSIONE", this );
            SOPPRESSIONE__24.setType( com.codecharge.components.ControlType.TEXT );
            SOPPRESSIONE__24.setHtmlEncode( true );
            Comuni.add(SOPPRESSIONE__24);
            add(Comuni);
        } // End definition of Comuni grid model
//End Comuni grid

//Focus grid @21-B18EC929
        
        /*
            // Begin definition of Focus grid model.
        */
        {
            com.codecharge.components.Grid Focus = new com.codecharge.components.Grid("Focus");
            Focus.setPageModel( this );
            Focus.setFetchSize(1);
            Focus.setVisible( true );

            com.codecharge.components.Label FIELD_FOCUS__22 = new com.codecharge.components.Label("FIELD_FOCUS", "FIELD_FOCUS", this );
            FIELD_FOCUS__22.setType( com.codecharge.components.ControlType.TEXT );
            Focus.add(FIELD_FOCUS__22);
            add(Focus);
        } // End definition of Focus grid model
//End Focus grid

//Ad4DizionariComuniLovModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariComuniLovModel class tail

