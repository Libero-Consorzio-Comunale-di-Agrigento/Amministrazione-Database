//AD4IstaElencoModel imports @1-5FFB92BA
package ad4web.AD4IstaElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4IstaElencoModel imports

//AD4IstaElencoModel class head @1-208859E6
public class AD4IstaElencoModel extends com.codecharge.components.Page {
    public AD4IstaElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4IstaElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4IstaElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4IstaElencoModel class head

//page settings @1-FCC2111C
        super("AD4IstaElenco", locale );
        setResponse(response);
        addPageListener(new AD4IstaElencoPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__5 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__5.setVisible( true );
            add( Guida__5 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4Istanze_VSearch record @175-43E770CD
        
        /*
            Model of AD4Istanze_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Istanze_VSearch = new com.codecharge.components.Record("AD4Istanze_VSearch");
            AD4Istanze_VSearch.setPageModel( this );
            AD4Istanze_VSearch.addExcludeParam( "ccsForm" );
            AD4Istanze_VSearch.addExcludeParam( "DESCRIZIONE_FILTRO" );
            AD4Istanze_VSearch.setVisible( true );
            AD4Istanze_VSearch.setAllowInsert(false);
            AD4Istanze_VSearch.setAllowUpdate(false);
            AD4Istanze_VSearch.setAllowDelete(false);
            AD4Istanze_VSearch.setPreserveType(PreserveParameterType.GET);
            AD4Istanze_VSearch.setReturnPage("AD4IstaElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link IMMAGINE_FILTRO__176 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__176.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__176.setHrefSourceValue( "AD4IstaElenco" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__176.setHrefType( "Page" );
            IMMAGINE_FILTRO__176.setConvertRule("Relative");
            IMMAGINE_FILTRO__176.setPreserveType(PreserveParameterType.NONE);
            AD4Istanze_VSearch.add( IMMAGINE_FILTRO__176 );

            com.codecharge.components.TextBox s_DESCRIZIONE__177 = new com.codecharge.components.TextBox("s_DESCRIZIONE", "", this );
            s_DESCRIZIONE__177.setType( com.codecharge.components.ControlType.TEXT );
            s_DESCRIZIONE__177.setHtmlEncode( true );
            AD4Istanze_VSearch.add( s_DESCRIZIONE__177 );

            com.codecharge.components.Button DoSearch__178 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__178.addExcludeParam( "ccsForm" );
            DoSearch__178.addExcludeParam( "DoSearch" );
            DoSearch__178.setOperation( "Search" );
            AD4Istanze_VSearch.add( DoSearch__178 );
            add(AD4Istanze_VSearch);
        } // End definition of AD4Istanze_VSearch record model.
//End AD4Istanze_VSearch record

//PROGETTI grid @135-7C5D6074
        
        /*
            // Begin definition of PROGETTI grid model.
        */
        {
            com.codecharge.components.Grid PROGETTI = new com.codecharge.components.Grid("PROGETTI");
            PROGETTI.setPageModel( this );
            PROGETTI.setFetchSize(10);
            PROGETTI.setVisible( true );

            com.codecharge.components.Label DESC_PROGETTO__136 = new com.codecharge.components.Label("DESC_PROGETTO", "DESC_PROGETTO", this );
            DESC_PROGETTO__136.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTI.add(DESC_PROGETTO__136);

            com.codecharge.components.ImageLink Nuovo__143 = new com.codecharge.components.ImageLink("Nuovo", "ISTANZA", this );
            Nuovo__143.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__143.setHrefSourceValue( "AD4Istanza" + Names.ACTION_SUFFIX );
            Nuovo__143.setHrefType( "Page" );
            Nuovo__143.setConvertRule("Relative");
            Nuovo__143.setPreserveType(PreserveParameterType.GET);
            Nuovo__143.addExcludeParam( "MVVC" );
            Nuovo__143.addExcludeParam( "ISTANZA" );
            Nuovo__143.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            PROGETTI.add( Nuovo__143 );
            add(PROGETTI);
        } // End definition of PROGETTI grid model
//End PROGETTI grid

//AD4_ISTANZE grid @6-4CE65362
        
        /*
            // Begin definition of AD4_ISTANZE grid model.
        */
        {
            com.codecharge.components.Grid AD4_ISTANZE = new com.codecharge.components.Grid("AD4_ISTANZE");
            AD4_ISTANZE.setPageModel( this );
            AD4_ISTANZE.setFetchSize(10);
            AD4_ISTANZE.setVisible( true );
            AD4_ISTANZE.addGridListener( new AD4_ISTANZEGridHandler() );
            com.codecharge.components.Sorter Sorter_ISTANZA = new com.codecharge.components.Sorter("Sorter_ISTANZA", AD4_ISTANZE, this);
            Sorter_ISTANZA.setColumn("DESCRIZIONE");
            AD4_ISTANZE.add(Sorter_ISTANZA);
            com.codecharge.components.Sorter Sorter_DESCRIZIONE = new com.codecharge.components.Sorter("Sorter_DESCRIZIONE", AD4_ISTANZE, this);
            Sorter_DESCRIZIONE.setColumn("DESCRIZIONE");
            AD4_ISTANZE.add(Sorter_DESCRIZIONE);

            com.codecharge.components.Link DESCRIZIONE__118 = new com.codecharge.components.Link("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__118.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__118.setHtmlEncode( true );
            DESCRIZIONE__118.setHrefSourceValue( "AD4Istanza" + Names.ACTION_SUFFIX );
            DESCRIZIONE__118.setHrefType( "Page" );
            DESCRIZIONE__118.setConvertRule("Relative");
            DESCRIZIONE__118.setPreserveType(PreserveParameterType.GET);
            DESCRIZIONE__118.addExcludeParam( "MVVC" );
            DESCRIZIONE__118.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            DESCRIZIONE__118.addParameter( new LinkParameter( "MVID", "", ParameterSource.EXPRESSION) );
            AD4_ISTANZE.add( DESCRIZIONE__118 );

            com.codecharge.components.Label DATI__120 = new com.codecharge.components.Label("DATI", "DATI", this );
            DATI__120.setType( com.codecharge.components.ControlType.TEXT );
            AD4_ISTANZE.add(DATI__120);

            com.codecharge.components.ImageLink CaratteristicheAccesso__156 = new com.codecharge.components.ImageLink("CaratteristicheAccesso", "", this );
            CaratteristicheAccesso__156.setType( com.codecharge.components.ControlType.TEXT );
            CaratteristicheAccesso__156.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            CaratteristicheAccesso__156.setHrefType( "Page" );
            CaratteristicheAccesso__156.setConvertRule("Relative");
            CaratteristicheAccesso__156.setPreserveType(PreserveParameterType.NONE);
            CaratteristicheAccesso__156.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__156.addParameter( new LinkParameter( "TIPO_ACCESSO", "", ParameterSource.EXPRESSION) );
            CaratteristicheAccesso__156.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            AD4_ISTANZE.add( CaratteristicheAccesso__156 );

            com.codecharge.components.ImageLink Abilitazioni__151 = new com.codecharge.components.ImageLink("Abilitazioni", "", this );
            Abilitazioni__151.setType( com.codecharge.components.ControlType.TEXT );
            Abilitazioni__151.setHrefSourceValue( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX );
            Abilitazioni__151.setHrefType( "Page" );
            Abilitazioni__151.setConvertRule("Relative");
            Abilitazioni__151.setPreserveType(PreserveParameterType.GET);
            Abilitazioni__151.addExcludeParam( "MVVC" );
            Abilitazioni__151.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            AD4_ISTANZE.add( Abilitazioni__151 );

            com.codecharge.components.ImageLink Registro__160 = new com.codecharge.components.ImageLink("Registro", "", this );
            Registro__160.setType( com.codecharge.components.ControlType.TEXT );
            Registro__160.setHrefSourceValue( "AD4RegistroTree" + Names.ACTION_SUFFIX );
            Registro__160.setHrefType( "Page" );
            Registro__160.setConvertRule("Relative");
            Registro__160.setPreserveType(PreserveParameterType.GET);
            Registro__160.addExcludeParam( "MVVC" );
            Registro__160.addParameter( new LinkParameter( "USRORCL", "USER_ORACLE", ParameterSource.DATAFIELD) );
            Registro__160.addParameter( new LinkParameter( "MENU", "", ParameterSource.EXPRESSION) );
            AD4_ISTANZE.add( Registro__160 );

            com.codecharge.components.Label AFCNavigator__145 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__145.setType( com.codecharge.components.ControlType.TEXT );
            AD4_ISTANZE.add(AFCNavigator__145);
            add(AD4_ISTANZE);
        } // End definition of AD4_ISTANZE grid model
//End AD4_ISTANZE grid

//AD4IstaElencoModel class tail @1-F5FC18C5
    }
}
//End AD4IstaElencoModel class tail

