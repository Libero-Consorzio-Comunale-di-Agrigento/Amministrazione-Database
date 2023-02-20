//AD4LDAPGestioneAlternativiModel imports @1-522A694D
package ad4web.AD4LDAPGestioneAlternativi;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4LDAPGestioneAlternativiModel imports

//AD4LDAPGestioneAlternativiModel class head @1-536EE0E1
public class AD4LDAPGestioneAlternativiModel extends com.codecharge.components.Page {
    public AD4LDAPGestioneAlternativiModel() {
        this( new CCSLocale(), null );
    }

    public AD4LDAPGestioneAlternativiModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4LDAPGestioneAlternativiModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4LDAPGestioneAlternativiModel class head

//page settings @1-AC745BD5
        super("AD4LDAPGestioneAlternativi", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//ldap record @11-F7C8275B
        
        /*
            Model of ldap record defining.
        */
        {
            com.codecharge.components.Record ldap = new com.codecharge.components.Record("ldap");
            ldap.setPageModel( this );
            ldap.addExcludeParam( "ccsForm" );
            ldap.setVisible( true );
            ldap.setAllowInsert(false);
            ldap.setPreserveType(PreserveParameterType.GET);
            ldap.setReturnPage("AD4LDAPGestioneAlternativi" + Names.ACTION_SUFFIX);
            ldap.addRecordListener(new ldapRecordHandler());

            com.codecharge.components.Button Button_Update__13 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__13.addExcludeParam( "ccsForm" );
            Button_Update__13.addExcludeParam( "Button_Update" );
            Button_Update__13.setOperation( "Update" );
            ldap.add( Button_Update__13 );

            com.codecharge.components.Button Button_Delete__20 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__20.addExcludeParam( "ccsForm" );
            Button_Delete__20.addExcludeParam( "Button_Delete" );
            Button_Delete__20.addExcludeParam( "chiave" );
            Button_Delete__20.setOperation( "Delete" );
            ldap.add( Button_Delete__20 );

            com.codecharge.components.Button Button_Cancel__14 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__14.addButtonListener(new ldapButton_CancelHandler());
            Button_Cancel__14.addExcludeParam( "ccsForm" );
            Button_Cancel__14.addExcludeParam( "Button_Cancel" );
            Button_Cancel__14.setOperation( "Cancel" );
            ldap.add( Button_Cancel__14 );
            add(ldap);
        } // End definition of ldap record model.
//End ldap record

//AD4LDAPGestioneAlternativiModel class tail @1-F5FC18C5
    }
}
//End AD4LDAPGestioneAlternativiModel class tail
