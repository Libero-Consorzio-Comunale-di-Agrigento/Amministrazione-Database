//AD4EntiRicercaModel imports @1-75154917
package ad4web.AD4EntiRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4EntiRicercaModel imports

//AD4EntiRicercaModel class head @1-729D7473
public class AD4EntiRicercaModel extends com.codecharge.components.Page {
    public AD4EntiRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4EntiRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4EntiRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4EntiRicercaModel class head

//page settings @1-06C874BE
        super("AD4EntiRicerca", locale );
        setResponse(response);
        addPageListener(new AD4EntiRicercaPageHandler());
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

//AD4Enti_VSearch record @6-5C4D19BF
        
        /*
            Model of AD4Enti_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Enti_VSearch = new com.codecharge.components.Record("AD4Enti_VSearch");
            AD4Enti_VSearch.setPageModel( this );
            AD4Enti_VSearch.addExcludeParam( "ccsForm" );
            AD4Enti_VSearch.addExcludeParam( "DESCRIZIONE_FILTRO" );
            AD4Enti_VSearch.setVisible( true );
            AD4Enti_VSearch.setAllowInsert(false);
            AD4Enti_VSearch.setAllowUpdate(false);
            AD4Enti_VSearch.setAllowDelete(false);
            AD4Enti_VSearch.setPreserveType(PreserveParameterType.GET);
            AD4Enti_VSearch.setReturnPage("AD4EntiRicerca" + Names.ACTION_SUFFIX);
            AD4Enti_VSearch.addRecordListener(new AD4Enti_VSearchRecordHandler());

            com.codecharge.components.Link IMMAGINE_FILTRO__343 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__343.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__343.setHrefSourceValue( "AD4EntiRicerca" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__343.setHrefType( "Page" );
            IMMAGINE_FILTRO__343.setConvertRule("Relative");
            IMMAGINE_FILTRO__343.setPreserveType(PreserveParameterType.NONE);
            AD4Enti_VSearch.add( IMMAGINE_FILTRO__343 );

            com.codecharge.components.TextBox s_DESCRIZIONE__344 = new com.codecharge.components.TextBox("s_DESCRIZIONE", "", this );
            s_DESCRIZIONE__344.setType( com.codecharge.components.ControlType.TEXT );
            s_DESCRIZIONE__344.setHtmlEncode( true );
            AD4Enti_VSearch.add( s_DESCRIZIONE__344 );

            com.codecharge.components.Button DoSearch__342 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__342.addExcludeParam( "ccsForm" );
            DoSearch__342.addExcludeParam( "DoSearch" );
            DoSearch__342.setOperation( "Search" );
            AD4Enti_VSearch.add( DoSearch__342 );

            com.codecharge.components.Link Nuovo__354 = new com.codecharge.components.Link("Nuovo", this);
            Nuovo__354.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__354.setHtmlEncode( true );
            Nuovo__354.setHrefSourceValue( "AD4Ente" + Names.ACTION_SUFFIX );
            Nuovo__354.setHrefType( "Page" );
            Nuovo__354.setConvertRule("Relative");
            Nuovo__354.setPreserveType(PreserveParameterType.NONE);
            Nuovo__354.addExcludeParam( "MVVC" );
            Nuovo__354.addExcludeParam( "s_DESCRIZIONE" );
            Nuovo__354.addExcludeParam( "DESCRIZIONE_FILTRO" );
            Nuovo__354.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            Nuovo__354.addParameter( new LinkParameter( "MVVC", "", ParameterSource.EXPRESSION) );
            AD4Enti_VSearch.add( Nuovo__354 );
            add(AD4Enti_VSearch);
        } // End definition of AD4Enti_VSearch record model.
//End AD4Enti_VSearch record

//AD4EntiV grid @142-931B3643
        
        /*
            // Begin definition of AD4EntiV grid model.
        */
        {
            com.codecharge.components.Grid AD4EntiV = new com.codecharge.components.Grid("AD4EntiV");
            AD4EntiV.setPageModel( this );
            AD4EntiV.setFetchSize(20);
            AD4EntiV.setVisible( true );
            AD4EntiV.addGridListener( new AD4EntiVGridHandler() );

            com.codecharge.components.Link DESCRIZIONE__278 = new com.codecharge.components.Link("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__278.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__278.setHtmlEncode( true );
            DESCRIZIONE__278.setHrefSourceValue( "AD4Ente" + Names.ACTION_SUFFIX );
            DESCRIZIONE__278.setHrefType( "Page" );
            DESCRIZIONE__278.setConvertRule("Relative");
            DESCRIZIONE__278.setPreserveType(PreserveParameterType.NONE);
            DESCRIZIONE__278.addParameter( new LinkParameter( "ENTE", "ENTE", ParameterSource.DATAFIELD) );
            AD4EntiV.add( DESCRIZIONE__278 );

            com.codecharge.components.Label DATI_ENTE__263 = new com.codecharge.components.Label("DATI_ENTE", "DATI_ENTE", this );
            DATI_ENTE__263.setType( com.codecharge.components.ControlType.TEXT );
            AD4EntiV.add(DATI_ENTE__263);

            com.codecharge.components.Label AFCNavigator__340 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__340.setType( com.codecharge.components.ControlType.TEXT );
            AD4EntiV.add(AFCNavigator__340);
            add(AD4EntiV);
        } // End definition of AD4EntiV grid model
//End AD4EntiV grid

//AD4EntiRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4EntiRicercaModel class tail

