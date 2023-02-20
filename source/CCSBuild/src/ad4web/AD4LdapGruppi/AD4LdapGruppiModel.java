//AD4LdapGruppiModel imports @1-810B5E3F
package ad4web.AD4LdapGruppi;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4LdapGruppiModel imports

//AD4LdapGruppiModel class head @1-DBD4E2C3
public class AD4LdapGruppiModel extends com.codecharge.components.Page {
    public AD4LdapGruppiModel() {
        this( new CCSLocale(), null );
    }

    public AD4LdapGruppiModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4LdapGruppiModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4LdapGruppiModel class head

//page settings @1-C695450D
        super("AD4LdapGruppi", locale );
        setResponse(response);
        addPageListener(new AD4LdapGruppiPageHandler());
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

            com.codecharge.components.ImageLink Nuovo__128 = new com.codecharge.components.ImageLink("Nuovo", "", this );
            Nuovo__128.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__128.setHrefSourceValue( "AD4LdapGruppo" + Names.ACTION_SUFFIX );
            Nuovo__128.setHrefType( "Page" );
            Nuovo__128.setConvertRule("Relative");
            Nuovo__128.setPreserveType(PreserveParameterType.GET);
            add( Nuovo__128 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//ldap_config grid @110-126E600A
        
        /*
            // Begin definition of ldap_config grid model.
        */
        {
            com.codecharge.components.Grid ldap_config = new com.codecharge.components.Grid("ldap_config");
            ldap_config.setPageModel( this );
            ldap_config.setFetchSize(20);
            ldap_config.setVisible( true );
            ldap_config.addGridListener( new ldap_configGridHandler() );

            com.codecharge.components.Link GRUPPO_AD4__111 = new com.codecharge.components.Link("GRUPPO_AD4", "GRUPPO_AD4", this );
            GRUPPO_AD4__111.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO_AD4__111.setHtmlEncode( true );
            GRUPPO_AD4__111.setHrefSourceValue( "AD4LdapGruppo" + Names.ACTION_SUFFIX );
            GRUPPO_AD4__111.setHrefType( "Page" );
            GRUPPO_AD4__111.setConvertRule("Relative");
            GRUPPO_AD4__111.setPreserveType(PreserveParameterType.GET);
            GRUPPO_AD4__111.addParameter( new LinkParameter( "STRINGA", "STRINGA", ParameterSource.DATAFIELD) );
            ldap_config.add( GRUPPO_AD4__111 );

            com.codecharge.components.Label VALORE__112 = new com.codecharge.components.Label("VALORE", "VALORE", this );
            VALORE__112.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__112.setHtmlEncode( true );
            ldap_config.add(VALORE__112);

            com.codecharge.components.Label AFCNavigator__131 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__131.setType( com.codecharge.components.ControlType.TEXT );
            ldap_config.add(AFCNavigator__131);
            add(ldap_config);
        } // End definition of ldap_config grid model
//End ldap_config grid

//checkAuthorizationMapping grid @130-2CCA5C21
        
        /*
            // Begin definition of checkAuthorizationMapping grid model.
        */
        {
            com.codecharge.components.Grid checkAuthorizationMapping = new com.codecharge.components.Grid("checkAuthorizationMapping");
            checkAuthorizationMapping.setPageModel( this );
            checkAuthorizationMapping.setFetchSize(20);
            checkAuthorizationMapping.setVisible( true );
            add(checkAuthorizationMapping);
        } // End definition of checkAuthorizationMapping grid model
//End checkAuthorizationMapping grid

//AD4LdapGruppiModel class tail @1-F5FC18C5
    }
}
//End AD4LdapGruppiModel class tail
