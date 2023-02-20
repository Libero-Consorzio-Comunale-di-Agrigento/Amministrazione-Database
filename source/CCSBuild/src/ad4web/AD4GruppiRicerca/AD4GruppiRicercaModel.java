//AD4GruppiRicercaModel imports @1-D87E00AC
package ad4web.AD4GruppiRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GruppiRicercaModel imports

//AD4GruppiRicercaModel class head @1-D1FFBF46
public class AD4GruppiRicercaModel extends com.codecharge.components.Page {
    public AD4GruppiRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4GruppiRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GruppiRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GruppiRicercaModel class head

//page settings @1-4052E05B
        super("AD4GruppiRicerca", locale );
        setResponse(response);
        addPageListener(new AD4GruppiRicercaPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__305 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__305.setVisible( true );
            add( Guida__305 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4Gruppi_VSearch record @6-9F37171C
        
        /*
            Model of AD4Gruppi_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Gruppi_VSearch = new com.codecharge.components.Record("AD4Gruppi_VSearch");
            AD4Gruppi_VSearch.setPageModel( this );
            AD4Gruppi_VSearch.addExcludeParam( "ccsForm" );
            AD4Gruppi_VSearch.setVisible( true );
            AD4Gruppi_VSearch.setAllowInsert(false);
            AD4Gruppi_VSearch.setAllowUpdate(false);
            AD4Gruppi_VSearch.setAllowDelete(false);
            AD4Gruppi_VSearch.setPreserveType(PreserveParameterType.GET);
            AD4Gruppi_VSearch.setReturnPage("AD4GruppiRicerca" + Names.ACTION_SUFFIX);
            AD4Gruppi_VSearch.addRecordListener(new AD4Gruppi_VSearchRecordHandler());

            com.codecharge.components.Link IMMAGINE_FILTRO__346 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "FILTRO", this );
            IMMAGINE_FILTRO__346.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__346.setHrefSourceValue( "AD4GruppiRicerca" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__346.setHrefType( "Page" );
            IMMAGINE_FILTRO__346.setConvertRule("Relative");
            IMMAGINE_FILTRO__346.setPreserveType(PreserveParameterType.NONE);
            AD4Gruppi_VSearch.add( IMMAGINE_FILTRO__346 );

            com.codecharge.components.TextBox s_DESCRIZIONE__331 = new com.codecharge.components.TextBox("s_DESCRIZIONE", "", this );
            s_DESCRIZIONE__331.setType( com.codecharge.components.ControlType.TEXT );
            s_DESCRIZIONE__331.setHtmlEncode( true );
            AD4Gruppi_VSearch.add( s_DESCRIZIONE__331 );

            com.codecharge.components.ListBox s_UTENTE__338 = new com.codecharge.components.ListBox("s_UTENTE", "", this );
            s_UTENTE__338.setType( com.codecharge.components.ControlType.TEXT );
            s_UTENTE__338.setHtmlEncode( true );
            AD4Gruppi_VSearch.add( s_UTENTE__338 );

            com.codecharge.components.Button DoSearch__7 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__7.addExcludeParam( "ccsForm" );
            DoSearch__7.addExcludeParam( "DoSearch" );
            DoSearch__7.setOperation( "Search" );
            AD4Gruppi_VSearch.add( DoSearch__7 );

            com.codecharge.components.Link Nuovo__328 = new com.codecharge.components.Link("Nuovo", this);
            Nuovo__328.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__328.setHtmlEncode( true );
            Nuovo__328.setHrefSourceValue( "AD4Gruppo" + Names.ACTION_SUFFIX );
            Nuovo__328.setHrefType( "Page" );
            Nuovo__328.setConvertRule("Relative");
            Nuovo__328.setPreserveType(PreserveParameterType.NONE);
            Nuovo__328.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            Nuovo__328.addParameter( new LinkParameter( "MVVC", "", ParameterSource.EXPRESSION) );
            AD4Gruppi_VSearch.add( Nuovo__328 );
            add(AD4Gruppi_VSearch);
        } // End definition of AD4Gruppi_VSearch record model.
//End AD4Gruppi_VSearch record

//AD4GruppiV grid @142-3C866B3A
        
        /*
            // Begin definition of AD4GruppiV grid model.
        */
        {
            com.codecharge.components.Grid AD4GruppiV = new com.codecharge.components.Grid("AD4GruppiV");
            AD4GruppiV.setPageModel( this );
            AD4GruppiV.setFetchSize(20);
            AD4GruppiV.setVisible( true );
            AD4GruppiV.addGridListener( new AD4GruppiVGridHandler() );

            com.codecharge.components.Link GRUPPO__278 = new com.codecharge.components.Link("GRUPPO", "GRUPPO", this );
            GRUPPO__278.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO__278.setHtmlEncode( true );
            GRUPPO__278.setHrefSourceValue( "AD4Gruppo" + Names.ACTION_SUFFIX );
            GRUPPO__278.setHrefType( "Page" );
            GRUPPO__278.setConvertRule("Relative");
            GRUPPO__278.setPreserveType(PreserveParameterType.NONE);
            GRUPPO__278.addParameter( new LinkParameter( "GRUPPO", "GRUPPO", ParameterSource.DATAFIELD) );
            AD4GruppiV.add( GRUPPO__278 );

            com.codecharge.components.Label DESCRIZIONE__263 = new com.codecharge.components.Label("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__263.setType( com.codecharge.components.ControlType.TEXT );
            AD4GruppiV.add(DESCRIZIONE__263);

            com.codecharge.components.Label NOTE__335 = new com.codecharge.components.Label("NOTE", "NOTE", this );
            NOTE__335.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__335.setHtmlEncode( true );
            AD4GruppiV.add(NOTE__335);

            com.codecharge.components.Label AFCNavigator__345 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__345.setType( com.codecharge.components.ControlType.TEXT );
            AD4GruppiV.add(AFCNavigator__345);
            add(AD4GruppiV);
        } // End definition of AD4GruppiV grid model
//End AD4GruppiV grid

//AD4GruppiRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4GruppiRicercaModel class tail

