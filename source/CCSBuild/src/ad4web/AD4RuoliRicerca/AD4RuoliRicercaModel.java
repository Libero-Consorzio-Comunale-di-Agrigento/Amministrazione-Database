//AD4RuoliRicercaModel imports @1-1AF0B980
package ad4web.AD4RuoliRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4RuoliRicercaModel imports

//AD4RuoliRicercaModel class head @1-9FE4C014
public class AD4RuoliRicercaModel extends com.codecharge.components.Page {
    public AD4RuoliRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4RuoliRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4RuoliRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4RuoliRicercaModel class head

//page settings @1-2369302F
        super("AD4RuoliRicerca", locale );
        setResponse(response);
        addPageListener(new AD4RuoliRicercaPageHandler());
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

//AD4Ruoli_VSearch record @6-BC2E7825
        
        /*
            Model of AD4Ruoli_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Ruoli_VSearch = new com.codecharge.components.Record("AD4Ruoli_VSearch");
            AD4Ruoli_VSearch.setPageModel( this );
            AD4Ruoli_VSearch.addExcludeParam( "ccsForm" );
            AD4Ruoli_VSearch.setVisible( true );
            AD4Ruoli_VSearch.setAllowInsert(false);
            AD4Ruoli_VSearch.setAllowUpdate(false);
            AD4Ruoli_VSearch.setAllowDelete(false);
            AD4Ruoli_VSearch.setPreserveType(PreserveParameterType.NONE);
            AD4Ruoli_VSearch.setReturnPage("AD4RuoliRicerca" + Names.ACTION_SUFFIX);
            AD4Ruoli_VSearch.addRecordListener(new AD4Ruoli_VSearchRecordHandler());

            com.codecharge.components.Link IMMAGINE_FILTRO__388 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__388.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__388.setHrefSourceValue( "AD4RuoliRicerca" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__388.setHrefType( "Page" );
            IMMAGINE_FILTRO__388.setConvertRule("Relative");
            IMMAGINE_FILTRO__388.setPreserveType(PreserveParameterType.NONE);
            AD4Ruoli_VSearch.add( IMMAGINE_FILTRO__388 );

            com.codecharge.components.TextBox s_DESCRIZIONE__335 = new com.codecharge.components.TextBox("s_DESCRIZIONE", "", this );
            s_DESCRIZIONE__335.setType( com.codecharge.components.ControlType.TEXT );
            s_DESCRIZIONE__335.setHtmlEncode( true );
            AD4Ruoli_VSearch.add( s_DESCRIZIONE__335 );

            com.codecharge.components.Button DoSearch__7 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__7.addExcludeParam( "ccsForm" );
            DoSearch__7.addExcludeParam( "DoSearch" );
            DoSearch__7.setOperation( "Search" );
            AD4Ruoli_VSearch.add( DoSearch__7 );

            com.codecharge.components.Link AD4_RUOLI_Insert__384 = new com.codecharge.components.Link("AD4_RUOLI_Insert", "", this );
            AD4_RUOLI_Insert__384.setType( com.codecharge.components.ControlType.TEXT );
            AD4_RUOLI_Insert__384.setHtmlEncode( true );
            AD4_RUOLI_Insert__384.setHrefSourceValue( "AD4Ruolo" + Names.ACTION_SUFFIX );
            AD4_RUOLI_Insert__384.setHrefType( "Page" );
            AD4_RUOLI_Insert__384.setConvertRule("Relative");
            AD4_RUOLI_Insert__384.setPreserveType(PreserveParameterType.GET);
            AD4_RUOLI_Insert__384.addExcludeParam( "RUOLO" );
            AD4_RUOLI_Insert__384.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            AD4Ruoli_VSearch.add( AD4_RUOLI_Insert__384 );
            add(AD4Ruoli_VSearch);
        } // End definition of AD4Ruoli_VSearch record model.
//End AD4Ruoli_VSearch record

//AD4RuoliV grid @142-C8B988DF
        
        /*
            // Begin definition of AD4RuoliV grid model.
        */
        {
            com.codecharge.components.Grid AD4RuoliV = new com.codecharge.components.Grid("AD4RuoliV");
            AD4RuoliV.setPageModel( this );
            AD4RuoliV.setFetchSize(100);
            AD4RuoliV.setVisible( true );
            AD4RuoliV.addGridListener( new AD4RuoliVGridHandler() );

            com.codecharge.components.Link RUOLO__351 = new com.codecharge.components.Link("RUOLO", "RUOLO", this );
            RUOLO__351.setType( com.codecharge.components.ControlType.TEXT );
            RUOLO__351.setHtmlEncode( true );
            RUOLO__351.setHrefSourceValue( "AD4Ruolo" + Names.ACTION_SUFFIX );
            RUOLO__351.setHrefType( "Page" );
            RUOLO__351.setConvertRule("Relative");
            RUOLO__351.setPreserveType(PreserveParameterType.GET);
            RUOLO__351.addParameter( new LinkParameter( "RUOLO", "RUOLO", ParameterSource.DATAFIELD) );
            AD4RuoliV.add( RUOLO__351 );

            com.codecharge.components.Link DESCRIZIONE__263 = new com.codecharge.components.Link("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__263.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__263.setHtmlEncode( true );
            DESCRIZIONE__263.setHrefSourceValue( "AD4Ruolo" + Names.ACTION_SUFFIX );
            DESCRIZIONE__263.setHrefType( "Page" );
            DESCRIZIONE__263.setConvertRule("Relative");
            DESCRIZIONE__263.setPreserveType(PreserveParameterType.GET);
            DESCRIZIONE__263.addParameter( new LinkParameter( "RUOLO", "RUOLO", ParameterSource.DATAFIELD) );
            AD4RuoliV.add( DESCRIZIONE__263 );

            com.codecharge.components.Label PROGETTO__359 = new com.codecharge.components.Label("PROGETTO", "PROGETTO", this );
            PROGETTO__359.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO__359.setHtmlEncode( true );
            AD4RuoliV.add(PROGETTO__359);

            com.codecharge.components.Label MODULO__373 = new com.codecharge.components.Label("MODULO", "MODULO", this );
            MODULO__373.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__373.setHtmlEncode( true );
            AD4RuoliV.add(MODULO__373);

            com.codecharge.components.Label PROFILO__375 = new com.codecharge.components.Label("PROFILO", "PROFILO", this );
            PROFILO__375.setType( com.codecharge.components.ControlType.INTEGER );
            PROFILO__375.setHtmlEncode( true );
            AD4RuoliV.add(PROFILO__375);

            com.codecharge.components.Label STATO__392 = new com.codecharge.components.Label("STATO", "STATO", this );
            STATO__392.setType( com.codecharge.components.ControlType.TEXT );
            AD4RuoliV.add(STATO__392);

            com.codecharge.components.Label GRUPPO_LAVORO__393 = new com.codecharge.components.Label("GRUPPO_LAVORO", "GRUPPO_LAVORO", this );
            GRUPPO_LAVORO__393.setType( com.codecharge.components.ControlType.TEXT );
            AD4RuoliV.add(GRUPPO_LAVORO__393);

            com.codecharge.components.Label GRUPPO_SO__394 = new com.codecharge.components.Label("GRUPPO_SO", "GRUPPO_SO", this );
            GRUPPO_SO__394.setType( com.codecharge.components.ControlType.TEXT );
            AD4RuoliV.add(GRUPPO_SO__394);

            com.codecharge.components.Label AFCNavigator__387 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__387.setType( com.codecharge.components.ControlType.TEXT );
            AD4RuoliV.add(AFCNavigator__387);
            add(AD4RuoliV);
        } // End definition of AD4RuoliV grid model
//End AD4RuoliV grid

//AD4RuoliRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4RuoliRicercaModel class tail

