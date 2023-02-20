//AD4SoggRicercaInclusaModel imports @1-11C18A48
package ad4web.AD4SoggRicercaInclusa;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4SoggRicercaInclusaModel imports

//AD4SoggRicercaInclusaModel class head @1-77E2DFC1
public class AD4SoggRicercaInclusaModel extends com.codecharge.components.Page {
    public AD4SoggRicercaInclusaModel() {
        this( new CCSLocale(), null );
    }

    public AD4SoggRicercaInclusaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4SoggRicercaInclusaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4SoggRicercaInclusaModel class head

//page settings @1-67E93B28
        super("AD4SoggRicercaInclusa", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AD4Soggetti_VSearch record @6-3B267490
        
        /*
            Model of AD4Soggetti_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Soggetti_VSearch = new com.codecharge.components.Record("AD4Soggetti_VSearch");
            AD4Soggetti_VSearch.setPageModel( this );
            AD4Soggetti_VSearch.addExcludeParam( "ccsForm" );
            AD4Soggetti_VSearch.setVisible( true );
            AD4Soggetti_VSearch.setAllowInsert(false);
            AD4Soggetti_VSearch.setAllowUpdate(false);
            AD4Soggetti_VSearch.setAllowDelete(false);
            AD4Soggetti_VSearch.setPreserveType(PreserveParameterType.ALL);
            AD4Soggetti_VSearch.setReturnPage("AD4SoggRicercaInclusa" + Names.ACTION_SUFFIX);
            AD4Soggetti_VSearch.addRecordListener(new AD4Soggetti_VSearchRecordHandler());

            com.codecharge.components.Link IMMAGINE_FILTRO__389 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__389.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__389.setHrefSourceValue( "/ad4web/AD4SoggRicercaInclusa" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__389.setHrefType( "Page" );
            IMMAGINE_FILTRO__389.setConvertRule("Relative");
            IMMAGINE_FILTRO__389.setPreserveType(PreserveParameterType.GET);
            IMMAGINE_FILTRO__389.addExcludeParam( "s_NOME" );
            IMMAGINE_FILTRO__389.addExcludeParam( "s_RICERCA" );
            AD4Soggetti_VSearch.add( IMMAGINE_FILTRO__389 );

            com.codecharge.components.TextBox s_NOME__337 = new com.codecharge.components.TextBox("s_NOME", "", this );
            s_NOME__337.setType( com.codecharge.components.ControlType.TEXT );
            s_NOME__337.setHtmlEncode( true );
            s_NOME__337.addValidateHandler( new RequiredHandler( "Il valore nel campo s_NOME è richiesto." ) );
            AD4Soggetti_VSearch.add( s_NOME__337 );

            com.codecharge.components.Hidden s_RICERCA__347 = new com.codecharge.components.Hidden("s_RICERCA", "", this );
            s_RICERCA__347.setType( com.codecharge.components.ControlType.TEXT );
            s_RICERCA__347.setHtmlEncode( true );
            AD4Soggetti_VSearch.add( s_RICERCA__347 );

            com.codecharge.components.Hidden s_UTENTE__363 = new com.codecharge.components.Hidden("s_UTENTE", "", this );
            s_UTENTE__363.setType( com.codecharge.components.ControlType.TEXT );
            AD4Soggetti_VSearch.add( s_UTENTE__363 );

            com.codecharge.components.Hidden s_ENTE__378 = new com.codecharge.components.Hidden("s_ENTE", "", this );
            s_ENTE__378.setType( com.codecharge.components.ControlType.TEXT );
            s_ENTE__378.setHtmlEncode( true );
            AD4Soggetti_VSearch.add( s_ENTE__378 );

            com.codecharge.components.Button DoSearch__7 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__7.addExcludeParam( "ccsForm" );
            DoSearch__7.addExcludeParam( "DoSearch" );
            DoSearch__7.setOperation( "Search" );
            AD4Soggetti_VSearch.add( DoSearch__7 );

            com.codecharge.components.Label Nuovo__390 = new com.codecharge.components.Label("Nuovo", "PAGINA", this );
            Nuovo__390.setType( com.codecharge.components.ControlType.TEXT );
            AD4Soggetti_VSearch.add(Nuovo__390);
            add(AD4Soggetti_VSearch);
        } // End definition of AD4Soggetti_VSearch record model.
//End AD4Soggetti_VSearch record

//AD4SoggettiV grid @142-8BDCA16A
        
        /*
            // Begin definition of AD4SoggettiV grid model.
        */
        {
            com.codecharge.components.Grid AD4SoggettiV = new com.codecharge.components.Grid("AD4SoggettiV");
            AD4SoggettiV.setPageModel( this );
            AD4SoggettiV.setFetchSize(20);
            AD4SoggettiV.setVisible( true );
            AD4SoggettiV.addGridListener( new AD4SoggettiVGridHandler() );

            com.codecharge.components.Hidden SOGGETTO__387 = new com.codecharge.components.Hidden("SOGGETTO", "SOGGETTO", this );
            SOGGETTO__387.setType( com.codecharge.components.ControlType.TEXT );
            SOGGETTO__387.setHtmlEncode( true );
            AD4SoggettiV.add( SOGGETTO__387 );

            com.codecharge.components.Label SOGGETTO_VIS__351 = new com.codecharge.components.Label("SOGGETTO_VIS", "PAGINA", this );
            SOGGETTO_VIS__351.setType( com.codecharge.components.ControlType.TEXT );
            AD4SoggettiV.add(SOGGETTO_VIS__351);

            com.codecharge.components.Label DATI__263 = new com.codecharge.components.Label("DATI", "DATI", this );
            DATI__263.setType( com.codecharge.components.ControlType.TEXT );
            AD4SoggettiV.add(DATI__263);

            com.codecharge.components.Link RIPORTA_UTENTE__359 = new com.codecharge.components.Link("RIPORTA_UTENTE", "RIPORTA_UTENTE", this );
            RIPORTA_UTENTE__359.setType( com.codecharge.components.ControlType.TEXT );
            RIPORTA_UTENTE__359.setHrefSourceValue( "/ad4web/AD4Utente" + Names.ACTION_SUFFIX );
            RIPORTA_UTENTE__359.setHrefType( "Page" );
            RIPORTA_UTENTE__359.setConvertRule("Relative");
            RIPORTA_UTENTE__359.setPreserveType(PreserveParameterType.ALL);
            RIPORTA_UTENTE__359.addExcludeParam( "s_UTENTE" );
            RIPORTA_UTENTE__359.addExcludeParam( "s_RICERCA" );
            RIPORTA_UTENTE__359.addParameter( new LinkParameter( "UTENTE", "s_UTENTE", ParameterSource.URL) );
            RIPORTA_UTENTE__359.addParameter( new LinkParameter( "SOGGETTO", "SOGGETTO", ParameterSource.DATAFIELD) );
            AD4SoggettiV.add( RIPORTA_UTENTE__359 );

            com.codecharge.components.Link RIPORTA_ENTE__372 = new com.codecharge.components.Link("RIPORTA_ENTE", "RIPORTA_ENTE", this );
            RIPORTA_ENTE__372.setType( com.codecharge.components.ControlType.TEXT );
            RIPORTA_ENTE__372.setHrefSourceValue( "/ad4web/AD4Ente" + Names.ACTION_SUFFIX );
            RIPORTA_ENTE__372.setHrefType( "Page" );
            RIPORTA_ENTE__372.setConvertRule("Relative");
            RIPORTA_ENTE__372.setPreserveType(PreserveParameterType.NONE);
            RIPORTA_ENTE__372.addExcludeParam( "s_ENTE" );
            RIPORTA_ENTE__372.addExcludeParam( "s_RICERCA" );
            RIPORTA_ENTE__372.addParameter( new LinkParameter( "ENTE", "s_ENTE", ParameterSource.URL) );
            RIPORTA_ENTE__372.addParameter( new LinkParameter( "SOGGETTO", "SOGGETTO", ParameterSource.DATAFIELD) );
            AD4SoggettiV.add( RIPORTA_ENTE__372 );

            com.codecharge.components.Label AFCNavigator__379 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__379.setType( com.codecharge.components.ControlType.TEXT );
            AD4SoggettiV.add(AFCNavigator__379);
            add(AD4SoggettiV);
        } // End definition of AD4SoggettiV grid model
//End AD4SoggettiV grid

//AD4SoggRicercaInclusaModel class tail @1-F5FC18C5
    }
}
//End AD4SoggRicercaInclusaModel class tail
