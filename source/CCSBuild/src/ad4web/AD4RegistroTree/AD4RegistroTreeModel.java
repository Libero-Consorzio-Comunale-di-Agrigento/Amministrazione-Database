//AD4RegistroTreeModel imports @1-B5A9AED6
package ad4web.AD4RegistroTree;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4RegistroTreeModel imports

//AD4RegistroTreeModel class head @1-059BD171
public class AD4RegistroTreeModel extends com.codecharge.components.Page {
    public AD4RegistroTreeModel() {
        this( new CCSLocale(), null );
    }

    public AD4RegistroTreeModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4RegistroTreeModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4RegistroTreeModel class head

//page settings @1-5A72A4EC
        super("AD4RegistroTree", locale );
        setResponse(response);
        addPageListener(new AD4RegistroTreePageHandler());
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
            com.codecharge.components.IncludePage AD4RegistroElenco__20 = new com.codecharge.components.IncludePage("AD4RegistroElenco", this );
            AD4RegistroElenco__20.setVisible( true );
            add( AD4RegistroElenco__20 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4Registro_VSearch record @10-2A23A631
        
        /*
            Model of AD4Registro_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Registro_VSearch = new com.codecharge.components.Record("AD4Registro_VSearch");
            AD4Registro_VSearch.setPageModel( this );
            AD4Registro_VSearch.addExcludeParam( "ccsForm" );
            AD4Registro_VSearch.setVisible( true );
            AD4Registro_VSearch.setAllowInsert(false);
            AD4Registro_VSearch.setAllowUpdate(false);
            AD4Registro_VSearch.setAllowDelete(false);
            AD4Registro_VSearch.setPreserveType(PreserveParameterType.ALL);
            AD4Registro_VSearch.setReturnPage("AD4RegistroTree" + Names.ACTION_SUFFIX);

            com.codecharge.components.Hidden DESCRIZIONE_FILTRO__11 = new com.codecharge.components.Hidden("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO", this );
            DESCRIZIONE_FILTRO__11.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE_FILTRO__11.setHtmlEncode( true );
            AD4Registro_VSearch.add( DESCRIZIONE_FILTRO__11 );

            com.codecharge.components.TextBox s_DESCRIZIONE__13 = new com.codecharge.components.TextBox("s_DESCRIZIONE", "", this );
            s_DESCRIZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            s_DESCRIZIONE__13.setHtmlEncode( true );
            AD4Registro_VSearch.add( s_DESCRIZIONE__13 );

            com.codecharge.components.Hidden USRORCL__32 = new com.codecharge.components.Hidden("USRORCL", "USRORCL", this );
            USRORCL__32.setType( com.codecharge.components.ControlType.TEXT );
            USRORCL__32.setHtmlEncode( true );
            AD4Registro_VSearch.add( USRORCL__32 );

            com.codecharge.components.Button DoSearch__14 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__14.addExcludeParam( "ccsForm" );
            DoSearch__14.addExcludeParam( "DoSearch" );
            DoSearch__14.setOperation( "Search" );
            AD4Registro_VSearch.add( DoSearch__14 );
            add(AD4Registro_VSearch);
        } // End definition of AD4Registro_VSearch record model.
//End AD4Registro_VSearch record

//Titolo grid @43-7BA9D634
        
        /*
            // Begin definition of Titolo grid model.
        */
        {
            com.codecharge.components.Grid Titolo = new com.codecharge.components.Grid("Titolo");
            Titolo.setPageModel( this );
            Titolo.setFetchSize(20);
            Titolo.setVisible( true );
            Titolo.addGridListener( new TitoloGridHandler() );

            com.codecharge.components.Label USRORCL__65 = new com.codecharge.components.Label("USRORCL", "USRORCL", this );
            USRORCL__65.setType( com.codecharge.components.ControlType.TEXT );
            USRORCL__65.setHtmlEncode( true );
            Titolo.add(USRORCL__65);

            com.codecharge.components.Link ModChiave__44 = new com.codecharge.components.Link("ModChiave", "MODIFICA", this );
            ModChiave__44.setType( com.codecharge.components.ControlType.TEXT );
            ModChiave__44.setHrefSourceValue( "AD4Stringa" + Names.ACTION_SUFFIX );
            ModChiave__44.setHrefType( "Page" );
            ModChiave__44.setConvertRule("Relative");
            ModChiave__44.setPreserveType(PreserveParameterType.GET);
            ModChiave__44.addParameter( new LinkParameter( "ID", "ID", ParameterSource.URL) );
            ModChiave__44.addParameter( new LinkParameter( "TIPOR", "", ParameterSource.EXPRESSION) );
            ModChiave__44.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            Titolo.add( ModChiave__44 );

            com.codecharge.components.Link NuovaChiave__48 = new com.codecharge.components.Link("NuovaChiave", "NUOVA_CHIAVE", this );
            NuovaChiave__48.setType( com.codecharge.components.ControlType.TEXT );
            NuovaChiave__48.setHrefSourceValue( "AD4Stringa" + Names.ACTION_SUFFIX );
            NuovaChiave__48.setHrefType( "Page" );
            NuovaChiave__48.setConvertRule("Relative");
            NuovaChiave__48.setPreserveType(PreserveParameterType.GET);
            NuovaChiave__48.addParameter( new LinkParameter( "ID", "ID", ParameterSource.URL) );
            NuovaChiave__48.addParameter( new LinkParameter( "TIPOR", "", ParameterSource.EXPRESSION) );
            NuovaChiave__48.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            Titolo.add( NuovaChiave__48 );

            com.codecharge.components.Link NuovaStringa__52 = new com.codecharge.components.Link("NuovaStringa", "NUOVA_STRINGA", this );
            NuovaStringa__52.setType( com.codecharge.components.ControlType.TEXT );
            NuovaStringa__52.setHrefSourceValue( "AD4Stringa" + Names.ACTION_SUFFIX );
            NuovaStringa__52.setHrefType( "Page" );
            NuovaStringa__52.setConvertRule("Relative");
            NuovaStringa__52.setPreserveType(PreserveParameterType.GET);
            NuovaStringa__52.addParameter( new LinkParameter( "ID", "ID", ParameterSource.URL) );
            NuovaStringa__52.addParameter( new LinkParameter( "TIPOR", "", ParameterSource.EXPRESSION) );
            Titolo.add( NuovaStringa__52 );

            com.codecharge.components.Link INDIETRO__57 = new com.codecharge.components.Link("INDIETRO", "INDIETRO", this );
            INDIETRO__57.setType( com.codecharge.components.ControlType.TEXT );
            INDIETRO__57.setHrefSource( "AD4BP" );
            INDIETRO__57.setHrefType( "Database" );
            INDIETRO__57.setConvertRule("Relative");
            INDIETRO__57.setPreserveType(PreserveParameterType.NONE);
            Titolo.add( INDIETRO__57 );
            add(Titolo);
        } // End definition of Titolo grid model
//End Titolo grid

//AD4_REGISTRO grid @6-34C0CE3D
        
        /*
            // Begin definition of AD4_REGISTRO grid model.
        */
        {
            com.codecharge.components.Grid AD4_REGISTRO = new com.codecharge.components.Grid("AD4_REGISTRO");
            AD4_REGISTRO.setPageModel( this );
            AD4_REGISTRO.setFetchSize(20);
            AD4_REGISTRO.setVisible( true );

            com.codecharge.components.Label ALBERO__7 = new com.codecharge.components.Label("ALBERO", "ALBERO", this );
            ALBERO__7.setType( com.codecharge.components.ControlType.TEXT );
            AD4_REGISTRO.add(ALBERO__7);
            add(AD4_REGISTRO);
        } // End definition of AD4_REGISTRO grid model
//End AD4_REGISTRO grid

//AD4RegistroTreeModel class tail @1-F5FC18C5
    }
}
//End AD4RegistroTreeModel class tail
