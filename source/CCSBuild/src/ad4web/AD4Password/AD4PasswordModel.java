//AD4PasswordModel imports @1-9B7BF4BA
package ad4web.AD4Password;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4PasswordModel imports

//AD4PasswordModel class head @1-A44F161C
public class AD4PasswordModel extends com.codecharge.components.Page {
    public AD4PasswordModel() {
        this( new CCSLocale(), null );
    }

    public AD4PasswordModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4PasswordModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4PasswordModel class head

//page settings @1-96CF9D7E
        super("AD4Password", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//AD4_UTENTI record @2-1D1022AF
        
        /*
            Model of AD4_UTENTI record defining.
        */
        {
            com.codecharge.components.Record AD4_UTENTI = new com.codecharge.components.Record("AD4_UTENTI");
            AD4_UTENTI.setPageModel( this );
            AD4_UTENTI.addExcludeParam( "ccsForm" );
            AD4_UTENTI.setVisible( true );
            AD4_UTENTI.setAllowInsert(false);
            AD4_UTENTI.setAllowUpdate(false);
            AD4_UTENTI.setAllowDelete(false);
            AD4_UTENTI.setPreserveType(PreserveParameterType.GET);
            AD4_UTENTI.setReturnPage("AD4Password" + Names.ACTION_SUFFIX);

            com.codecharge.components.TextBox NUOVA_PASSWORD__4 = new com.codecharge.components.TextBox("NUOVA_PASSWORD", "", this );
            NUOVA_PASSWORD__4.setType( com.codecharge.components.ControlType.TEXT );
            NUOVA_PASSWORD__4.setHtmlEncode( true );
            NUOVA_PASSWORD__4.setCaption( "NUOVA_PASSWORD" );
            AD4_UTENTI.add( NUOVA_PASSWORD__4 );

            com.codecharge.components.TextBox CONFERMA_PASSWORD__5 = new com.codecharge.components.TextBox("CONFERMA_PASSWORD", "", this );
            CONFERMA_PASSWORD__5.setType( com.codecharge.components.ControlType.TEXT );
            CONFERMA_PASSWORD__5.setHtmlEncode( true );
            CONFERMA_PASSWORD__5.setCaption( "CONFERMA_PASSWORD" );
            AD4_UTENTI.add( CONFERMA_PASSWORD__5 );

            com.codecharge.components.Button Button_Update__6 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__6.addExcludeParam( "ccsForm" );
            Button_Update__6.addExcludeParam( "Button_Update" );
            AD4_UTENTI.add( Button_Update__6 );

            com.codecharge.components.Button Button_Cancel__7 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__7.addExcludeParam( "ccsForm" );
            Button_Cancel__7.addExcludeParam( "Button_Cancel" );
            Button_Cancel__7.setOperation( "Cancel" );
            AD4_UTENTI.add( Button_Cancel__7 );
            add(AD4_UTENTI);
        } // End definition of AD4_UTENTI record model.
//End AD4_UTENTI record

//AD4PasswordModel class tail @1-F5FC18C5
    }
}
//End AD4PasswordModel class tail

