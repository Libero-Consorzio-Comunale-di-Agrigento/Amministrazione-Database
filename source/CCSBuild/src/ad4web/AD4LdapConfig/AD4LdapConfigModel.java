//AD4LdapConfigModel imports @1-AD549011
package ad4web.AD4LdapConfig;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4LdapConfigModel imports

//AD4LdapConfigModel class head @1-56F3D548
public class AD4LdapConfigModel extends com.codecharge.components.Page {
    public AD4LdapConfigModel() {
        this( new CCSLocale(), null );
    }

    public AD4LdapConfigModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4LdapConfigModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4LdapConfigModel class head

//page settings @1-B0AD98A4
        super("AD4LdapConfig", locale );
        setResponse(response);
        addPageListener(new AD4LdapConfigPageHandler());
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

//guida_ldap grid @83-6E2DE5BA
        
        /*
            // Begin definition of guida_ldap grid model.
        */
        {
            com.codecharge.components.Grid guida_ldap = new com.codecharge.components.Grid("guida_ldap");
            guida_ldap.setPageModel( this );
            guida_ldap.setFetchSize(20);
            guida_ldap.setVisible( true );

            com.codecharge.components.Label guidaLDAP__84 = new com.codecharge.components.Label("guidaLDAP", "GUIDALDAP", this );
            guidaLDAP__84.setType( com.codecharge.components.ControlType.TEXT );
            guida_ldap.add(guidaLDAP__84);
            add(guida_ldap);
        } // End definition of guida_ldap grid model
//End guida_ldap grid

//toolbox_grid grid @120-5F664B7D
        
        /*
            // Begin definition of toolbox_grid grid model.
        */
        {
            com.codecharge.components.Grid toolbox_grid = new com.codecharge.components.Grid("toolbox_grid");
            toolbox_grid.setPageModel( this );
            toolbox_grid.setFetchSize(20);
            toolbox_grid.setVisible( true );
            toolbox_grid.addGridListener( new toolbox_gridGridHandler() );

            com.codecharge.components.Label TIPO_SERVER__129 = new com.codecharge.components.Label("TIPO_SERVER", "TIPO_SERVER", this );
            TIPO_SERVER__129.setType( com.codecharge.components.ControlType.TEXT );
            TIPO_SERVER__129.setHtmlEncode( true );
            toolbox_grid.add(TIPO_SERVER__129);

            com.codecharge.components.Hidden CHIAVE__132 = new com.codecharge.components.Hidden("CHIAVE", "CHIAVE", this );
            CHIAVE__132.setType( com.codecharge.components.ControlType.TEXT );
            CHIAVE__132.setHtmlEncode( true );
            toolbox_grid.add( CHIAVE__132 );

            com.codecharge.components.ImageLink Nuovo__122 = new com.codecharge.components.ImageLink("Nuovo", "", this );
            Nuovo__122.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__122.setHrefSourceValue( "AD4Stringa" + Names.ACTION_SUFFIX );
            Nuovo__122.setHrefType( "Page" );
            Nuovo__122.setConvertRule("Relative");
            Nuovo__122.setPreserveType(PreserveParameterType.GET);
            Nuovo__122.addParameter( new LinkParameter( "TIPOR", "", ParameterSource.EXPRESSION) );
            Nuovo__122.addParameter( new LinkParameter( "ID", "CHIAVE", ParameterSource.DATAFIELD) );
            toolbox_grid.add( Nuovo__122 );

            com.codecharge.components.Label CREA_ALTERNATIVO__130 = new com.codecharge.components.Label("CREA_ALTERNATIVO", "CREA_ALTERNATIVO", this );
            CREA_ALTERNATIVO__130.setType( com.codecharge.components.ControlType.TEXT );
            toolbox_grid.add(CREA_ALTERNATIVO__130);

            com.codecharge.components.Label ELIMINA_ALTERNATIVO__131 = new com.codecharge.components.Label("ELIMINA_ALTERNATIVO", "ELIMINA_ALTERNATIVO", this );
            ELIMINA_ALTERNATIVO__131.setType( com.codecharge.components.ControlType.TEXT );
            toolbox_grid.add(ELIMINA_ALTERNATIVO__131);
            add(toolbox_grid);
        } // End definition of toolbox_grid grid model
//End toolbox_grid grid

//ldap_config grid @110-0AE8433F
        
        /*
            // Begin definition of ldap_config grid model.
        */
        {
            com.codecharge.components.Grid ldap_config = new com.codecharge.components.Grid("ldap_config");
            ldap_config.setPageModel( this );
            ldap_config.setFetchSize(20);
            ldap_config.setVisible( true );
            ldap_config.addGridListener( new ldap_configGridHandler() );

            com.codecharge.components.Link STRINGA__111 = new com.codecharge.components.Link("STRINGA", "STRINGA", this );
            STRINGA__111.setType( com.codecharge.components.ControlType.TEXT );
            STRINGA__111.setHtmlEncode( true );
            STRINGA__111.setHrefSourceValue( "AD4Stringa" + Names.ACTION_SUFFIX );
            STRINGA__111.setHrefType( "Page" );
            STRINGA__111.setConvertRule("Relative");
            STRINGA__111.setPreserveType(PreserveParameterType.GET);
            STRINGA__111.addParameter( new LinkParameter( "ID", "CHIAVE", ParameterSource.DATAFIELD) );
            STRINGA__111.addParameter( new LinkParameter( "STRINGA", "STRINGA", ParameterSource.DATAFIELD) );
            STRINGA__111.addParameter( new LinkParameter( "TIPOR", "", ParameterSource.EXPRESSION) );
            ldap_config.add( STRINGA__111 );

            com.codecharge.components.Label VALORE__112 = new com.codecharge.components.Label("VALORE", "VALORE", this );
            VALORE__112.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__112.setHtmlEncode( true );
            ldap_config.add(VALORE__112);

            com.codecharge.components.Label COMMENTO__137 = new com.codecharge.components.Label("COMMENTO", "COMMENTO", this );
            COMMENTO__137.setType( com.codecharge.components.ControlType.TEXT );
            COMMENTO__137.setHtmlEncode( true );
            ldap_config.add(COMMENTO__137);
            add(ldap_config);
        } // End definition of ldap_config grid model
//End ldap_config grid

//AD4LdapConfigModel class tail @1-F5FC18C5
    }
}
//End AD4LdapConfigModel class tail
