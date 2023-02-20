//AD4ProgettiRicercaModel imports @1-E164B9C1
package ad4web.AD4ProgettiRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4ProgettiRicercaModel imports

//AD4ProgettiRicercaModel class head @1-62B04F87
public class AD4ProgettiRicercaModel extends com.codecharge.components.Page {
    public AD4ProgettiRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4ProgettiRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4ProgettiRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4ProgettiRicercaModel class head

//page settings @1-42BDD0C4
        super("AD4ProgettiRicerca", locale );
        setResponse(response);
        addPageListener(new AD4ProgettiRicercaPageHandler());
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

//AD4Progetti_VSearch record @6-1C07A6A5
        
        /*
            Model of AD4Progetti_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Progetti_VSearch = new com.codecharge.components.Record("AD4Progetti_VSearch");
            AD4Progetti_VSearch.setPageModel( this );
            AD4Progetti_VSearch.addExcludeParam( "ccsForm" );
            AD4Progetti_VSearch.setVisible( true );
            AD4Progetti_VSearch.setAllowInsert(false);
            AD4Progetti_VSearch.setAllowUpdate(false);
            AD4Progetti_VSearch.setAllowDelete(false);
            AD4Progetti_VSearch.setPreserveType(PreserveParameterType.GET);
            AD4Progetti_VSearch.setReturnPage("AD4ProgettiRicerca" + Names.ACTION_SUFFIX);
            AD4Progetti_VSearch.addRecordListener(new AD4Progetti_VSearchRecordHandler());

            com.codecharge.components.Link IMMAGINE_FILTRO__340 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__340.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__340.setHrefSourceValue( "AD4ProgettiRicerca" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__340.setHrefType( "Page" );
            IMMAGINE_FILTRO__340.setConvertRule("Relative");
            IMMAGINE_FILTRO__340.setPreserveType(PreserveParameterType.NONE);
            AD4Progetti_VSearch.add( IMMAGINE_FILTRO__340 );

            com.codecharge.components.TextBox s_DESCRIZIONE__331 = new com.codecharge.components.TextBox("s_DESCRIZIONE", "", this );
            s_DESCRIZIONE__331.setType( com.codecharge.components.ControlType.TEXT );
            s_DESCRIZIONE__331.setHtmlEncode( true );
            AD4Progetti_VSearch.add( s_DESCRIZIONE__331 );

            com.codecharge.components.Button DoSearch__7 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__7.addExcludeParam( "ccsForm" );
            DoSearch__7.addExcludeParam( "DoSearch" );
            DoSearch__7.setOperation( "Search" );
            AD4Progetti_VSearch.add( DoSearch__7 );

            com.codecharge.components.Link Nuovo__328 = new com.codecharge.components.Link("Nuovo", this);
            Nuovo__328.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__328.setHtmlEncode( true );
            Nuovo__328.setHrefSourceValue( "AD4Progetto" + Names.ACTION_SUFFIX );
            Nuovo__328.setHrefType( "Page" );
            Nuovo__328.setConvertRule("Relative");
            Nuovo__328.setPreserveType(PreserveParameterType.GET);
            Nuovo__328.addExcludeParam( "PROGETTO" );
            Nuovo__328.addExcludeParam( "MVVC" );
            Nuovo__328.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            Nuovo__328.addParameter( new LinkParameter( "MVVC", "", ParameterSource.EXPRESSION) );
            AD4Progetti_VSearch.add( Nuovo__328 );
            add(AD4Progetti_VSearch);
        } // End definition of AD4Progetti_VSearch record model.
//End AD4Progetti_VSearch record

//AD4ProgettiV grid @142-0136E51B
        
        /*
            // Begin definition of AD4ProgettiV grid model.
        */
        {
            com.codecharge.components.Grid AD4ProgettiV = new com.codecharge.components.Grid("AD4ProgettiV");
            AD4ProgettiV.setPageModel( this );
            AD4ProgettiV.setFetchSize(20);
            AD4ProgettiV.setVisible( true );
            AD4ProgettiV.addGridListener( new AD4ProgettiVGridHandler() );

            com.codecharge.components.Link PROGETTO__278 = new com.codecharge.components.Link("PROGETTO", "PROGETTO", this );
            PROGETTO__278.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO__278.setHtmlEncode( true );
            PROGETTO__278.setHrefSourceValue( "AD4Progetto" + Names.ACTION_SUFFIX );
            PROGETTO__278.setHrefType( "Page" );
            PROGETTO__278.setConvertRule("Relative");
            PROGETTO__278.setPreserveType(PreserveParameterType.NONE);
            PROGETTO__278.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            AD4ProgettiV.add( PROGETTO__278 );

            com.codecharge.components.Label DESCRIZIONE__263 = new com.codecharge.components.Label("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__263.setType( com.codecharge.components.ControlType.TEXT );
            AD4ProgettiV.add(DESCRIZIONE__263);

            com.codecharge.components.Label PRIORITA__333 = new com.codecharge.components.Label("PRIORITA", "PRIORITA", this );
            PRIORITA__333.setType( com.codecharge.components.ControlType.INTEGER );
            PRIORITA__333.setHtmlEncode( true );
            AD4ProgettiV.add(PRIORITA__333);

            com.codecharge.components.Label NOTE__335 = new com.codecharge.components.Label("NOTE", "NOTE", this );
            NOTE__335.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__335.setHtmlEncode( true );
            AD4ProgettiV.add(NOTE__335);

            com.codecharge.components.ImageLink CaratteristicheAccesso__343 = new com.codecharge.components.ImageLink("CaratteristicheAccesso", "", this );
            CaratteristicheAccesso__343.setType( com.codecharge.components.ControlType.TEXT );
            CaratteristicheAccesso__343.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            CaratteristicheAccesso__343.setHrefType( "Page" );
            CaratteristicheAccesso__343.setConvertRule("Relative");
            CaratteristicheAccesso__343.setPreserveType(PreserveParameterType.NONE);
            CaratteristicheAccesso__343.addParameter( new LinkParameter( "TIPO_ACCESSO", "", ParameterSource.EXPRESSION) );
            CaratteristicheAccesso__343.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            AD4ProgettiV.add( CaratteristicheAccesso__343 );

            com.codecharge.components.Label AFCNavigator__339 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__339.setType( com.codecharge.components.ControlType.TEXT );
            AD4ProgettiV.add(AFCNavigator__339);
            add(AD4ProgettiV);
        } // End definition of AD4ProgettiV grid model
//End AD4ProgettiV grid

//AD4ProgettiRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4ProgettiRicercaModel class tail

