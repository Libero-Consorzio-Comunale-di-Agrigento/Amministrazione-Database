//AD4LdapGruppoModel imports @1-51EAC6B7
package ad4web.AD4LdapGruppo;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4LdapGruppoModel imports

//AD4LdapGruppoModel class head @1-336FFD6C
public class AD4LdapGruppoModel extends com.codecharge.components.Page {
    public AD4LdapGruppoModel() {
        this( new CCSLocale(), null );
    }

    public AD4LdapGruppoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4LdapGruppoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4LdapGruppoModel class head

//page settings @1-2F023BF3
        super("AD4LdapGruppo", locale );
        setResponse(response);
        addPageListener(new AD4LdapGruppoPageHandler());
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

//ldap_gruppo record @136-715BF830
        
        /*
            Model of ldap_gruppo record defining.
        */
        {
            com.codecharge.components.Record ldap_gruppo = new com.codecharge.components.Record("ldap_gruppo");
            ldap_gruppo.setPageModel( this );
            ldap_gruppo.addExcludeParam( "ccsForm" );
            ldap_gruppo.setVisible( true );
            ldap_gruppo.setPreserveType(PreserveParameterType.GET);
            ldap_gruppo.setReturnPage("AD4LdapGruppo" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox STRINGA__137 = new com.codecharge.components.ListBox("STRINGA", "STRINGA", this );
            STRINGA__137.setType( com.codecharge.components.ControlType.TEXT );
            STRINGA__137.setHtmlEncode( true );
            STRINGA__137.setBoundColumn( "UTENTE" );
            STRINGA__137.setTextColumn( "DESCRIZIONE" );
            STRINGA__137.addValidateHandler( new RequiredHandler( "Il valore nel campo STRINGA è richiesto." ) );
            ldap_gruppo.add( STRINGA__137 );

            com.codecharge.components.Hidden CHIAVE__150 = new com.codecharge.components.Hidden("CHIAVE", "CHIAVE", this );
            CHIAVE__150.setType( com.codecharge.components.ControlType.TEXT );
            CHIAVE__150.setHtmlEncode( true );
            ldap_gruppo.add( CHIAVE__150 );

            com.codecharge.components.Hidden OLD_STRINGA__159 = new com.codecharge.components.Hidden("OLD_STRINGA", "OLD_STRINGA", this );
            OLD_STRINGA__159.setType( com.codecharge.components.ControlType.TEXT );
            OLD_STRINGA__159.setHtmlEncode( true );
            ldap_gruppo.add( OLD_STRINGA__159 );

            com.codecharge.components.TextBox VALORE__138 = new com.codecharge.components.TextBox("VALORE", "VALORE", this );
            VALORE__138.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__138.setHtmlEncode( true );
            ldap_gruppo.add( VALORE__138 );

            com.codecharge.components.Button Button_Insert__139 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__139.addButtonListener(new ldap_gruppoButton_InsertHandler());
            Button_Insert__139.addExcludeParam( "ccsForm" );
            Button_Insert__139.addExcludeParam( "Button_Insert" );
            Button_Insert__139.setOperation( "Insert" );
            ldap_gruppo.add( Button_Insert__139 );

            com.codecharge.components.Button Button_Update__140 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__140.addButtonListener(new ldap_gruppoButton_UpdateHandler());
            Button_Update__140.addExcludeParam( "ccsForm" );
            Button_Update__140.addExcludeParam( "Button_Update" );
            Button_Update__140.setOperation( "Update" );
            ldap_gruppo.add( Button_Update__140 );

            com.codecharge.components.Button Button_Delete__141 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__141.addButtonListener(new ldap_gruppoButton_DeleteHandler());
            Button_Delete__141.addExcludeParam( "ccsForm" );
            Button_Delete__141.addExcludeParam( "Button_Delete" );
            Button_Delete__141.setOperation( "Delete" );
            ldap_gruppo.add( Button_Delete__141 );

            com.codecharge.components.Button Button_Cancel__146 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__146.addButtonListener(new ldap_gruppoButton_CancelHandler());
            Button_Cancel__146.addExcludeParam( "ccsForm" );
            Button_Cancel__146.addExcludeParam( "Button_Cancel" );
            Button_Cancel__146.addExcludeParam( "stringa" );
            Button_Cancel__146.setOperation( "Cancel" );
            ldap_gruppo.add( Button_Cancel__146 );
            add(ldap_gruppo);
        } // End definition of ldap_gruppo record model.
//End ldap_gruppo record

//AD4LdapGruppoModel class tail @1-F5FC18C5
    }
}
//End AD4LdapGruppoModel class tail
