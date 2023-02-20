//AD4OmonimieModel imports @1-D30865CA
package ad4web.AD4Omonimie;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4OmonimieModel imports

//AD4OmonimieModel class head @1-A0DA4FC7
public class AD4OmonimieModel extends com.codecharge.components.Page {
    public AD4OmonimieModel() {
        this( new CCSLocale(), null );
    }

    public AD4OmonimieModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4OmonimieModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4OmonimieModel class head

//page settings @1-53D2301B
        super("AD4Omonimie", locale );
        setResponse(response);
        addPageListener(new AD4OmonimiePageHandler());
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

//AD4Ruoli_VSearch record @132-6C7DC60D
        
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
            AD4Ruoli_VSearch.setReturnPage("AD4Omonimie" + Names.ACTION_SUFFIX);
            AD4Ruoli_VSearch.addRecordListener(new AD4Ruoli_VSearchRecordHandler());

            com.codecharge.components.ListBox s_daignorare__134 = new com.codecharge.components.ListBox("s_daignorare", this);
            s_daignorare__134.setType( com.codecharge.components.ControlType.INTEGER );
            s_daignorare__134.setHtmlEncode( true );
            AD4Ruoli_VSearch.add( s_daignorare__134 );

            com.codecharge.components.ListBox s_unificati__141 = new com.codecharge.components.ListBox("s_unificati", this);
            s_unificati__141.setType( com.codecharge.components.ControlType.INTEGER );
            s_unificati__141.setHtmlEncode( true );
            AD4Ruoli_VSearch.add( s_unificati__141 );

            com.codecharge.components.ListBox s_copiati__142 = new com.codecharge.components.ListBox("s_copiati", this);
            s_copiati__142.setType( com.codecharge.components.ControlType.INTEGER );
            s_copiati__142.setHtmlEncode( true );
            AD4Ruoli_VSearch.add( s_copiati__142 );

            com.codecharge.components.Button DoSearch__135 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__135.addExcludeParam( "ccsForm" );
            DoSearch__135.addExcludeParam( "DoSearch" );
            DoSearch__135.setOperation( "Search" );
            AD4Ruoli_VSearch.add( DoSearch__135 );
            add(AD4Ruoli_VSearch);
        } // End definition of AD4Ruoli_VSearch record model.
//End AD4Ruoli_VSearch record

//ldap_config grid @110-40921B47
        
        /*
            // Begin definition of ldap_config grid model.
        */
        {
            com.codecharge.components.Grid ldap_config = new com.codecharge.components.Grid("ldap_config");
            ldap_config.setPageModel( this );
            ldap_config.setFetchSize(20);
            ldap_config.setVisible( true );
            ldap_config.addGridListener( new ldap_configGridHandler() );

            com.codecharge.components.Label NOMINATIVO__111 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__111.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__111.setHtmlEncode( true );
            ldap_config.add(NOMINATIVO__111);

            com.codecharge.components.Label SOSIA_NOMINATIVO__112 = new com.codecharge.components.Label("SOSIA_NOMINATIVO", "SOSIA_NOMINATIVO", this );
            SOSIA_NOMINATIVO__112.setType( com.codecharge.components.ControlType.TEXT );
            SOSIA_NOMINATIVO__112.setHtmlEncode( true );
            ldap_config.add(SOSIA_NOMINATIVO__112);

            com.codecharge.components.Link UNIFICA_PROFILO__145 = new com.codecharge.components.Link("UNIFICA_PROFILO", "UNIFICA_PROFILO", this );
            UNIFICA_PROFILO__145.setType( com.codecharge.components.ControlType.TEXT );
            UNIFICA_PROFILO__145.setHrefSourceValue( "AD4UnificaProfilo" + Names.ACTION_SUFFIX );
            UNIFICA_PROFILO__145.setHrefType( "Page" );
            UNIFICA_PROFILO__145.setConvertRule("Relative");
            UNIFICA_PROFILO__145.setPreserveType(PreserveParameterType.GET);
            UNIFICA_PROFILO__145.addParameter( new LinkParameter( "UTENTE_UNIFICARE", "SOSIA", ParameterSource.DATAFIELD) );
            UNIFICA_PROFILO__145.addParameter( new LinkParameter( "UTENTE_ALIMENTARE_UNIFICARE", "UTENTE", ParameterSource.DATAFIELD) );
            ldap_config.add( UNIFICA_PROFILO__145 );

            com.codecharge.components.Link COPIA_PROFILO__146 = new com.codecharge.components.Link("COPIA_PROFILO", "COPIA_PROFILO", this );
            COPIA_PROFILO__146.setType( com.codecharge.components.ControlType.TEXT );
            COPIA_PROFILO__146.setHrefSourceValue( "AD4CopiaProfilo" + Names.ACTION_SUFFIX );
            COPIA_PROFILO__146.setHrefType( "Page" );
            COPIA_PROFILO__146.setConvertRule("Relative");
            COPIA_PROFILO__146.setPreserveType(PreserveParameterType.GET);
            COPIA_PROFILO__146.addParameter( new LinkParameter( "UTENTE_ALIMENTARE", "UTENTE", ParameterSource.DATAFIELD) );
            COPIA_PROFILO__146.addParameter( new LinkParameter( "UTENTE_ORIGINE", "SOSIA", ParameterSource.DATAFIELD) );
            ldap_config.add( COPIA_PROFILO__146 );

            com.codecharge.components.Label IGNORA__151 = new com.codecharge.components.Label("IGNORA", "IGNORA", this );
            IGNORA__151.setType( com.codecharge.components.ControlType.TEXT );
            ldap_config.add(IGNORA__151);

            com.codecharge.components.Link MODIFICA__154 = new com.codecharge.components.Link("MODIFICA", "MODIFICA", this );
            MODIFICA__154.setType( com.codecharge.components.ControlType.TEXT );
            MODIFICA__154.setHrefSourceValue( "AD4Omonimia" + Names.ACTION_SUFFIX );
            MODIFICA__154.setHrefType( "Page" );
            MODIFICA__154.setConvertRule("Relative");
            MODIFICA__154.setPreserveType(PreserveParameterType.GET);
            MODIFICA__154.addParameter( new LinkParameter( "S_SOSIA", "SOSIA", ParameterSource.DATAFIELD) );
            MODIFICA__154.addParameter( new LinkParameter( "S_UTENTE", "UTENTE", ParameterSource.DATAFIELD) );
            ldap_config.add( MODIFICA__154 );

            com.codecharge.components.Label AFCNavigator__131 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__131.setType( com.codecharge.components.ControlType.TEXT );
            ldap_config.add(AFCNavigator__131);
            add(ldap_config);
        } // End definition of ldap_config grid model
//End ldap_config grid

//AD4OmonimieModel class tail @1-F5FC18C5
    }
}
//End AD4OmonimieModel class tail
