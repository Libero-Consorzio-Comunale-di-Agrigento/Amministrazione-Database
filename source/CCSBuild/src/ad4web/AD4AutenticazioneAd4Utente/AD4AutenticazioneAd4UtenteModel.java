//AD4AutenticazioneAd4UtenteModel imports @1-A836368C
package ad4web.AD4AutenticazioneAd4Utente;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4AutenticazioneAd4UtenteModel imports

//AD4AutenticazioneAd4UtenteModel class head @1-096A9EBB
public class AD4AutenticazioneAd4UtenteModel extends com.codecharge.components.Page {
    public AD4AutenticazioneAd4UtenteModel() {
        this( new CCSLocale(), null );
    }

    public AD4AutenticazioneAd4UtenteModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4AutenticazioneAd4UtenteModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4AutenticazioneAd4UtenteModel class head

//page settings @1-4DF091B9
        super("AD4AutenticazioneAd4Utente", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//ad4Authenticate record @11-77A332C4
        
        /*
            Model of ad4Authenticate record defining.
        */
        {
            com.codecharge.components.Record ad4Authenticate = new com.codecharge.components.Record("ad4Authenticate");
            ad4Authenticate.setPageModel( this );
            ad4Authenticate.addExcludeParam( "ccsForm" );
            ad4Authenticate.setVisible( true );
            ad4Authenticate.setAllowInsert(false);
            ad4Authenticate.setAllowDelete(false);
            ad4Authenticate.setPreserveType(PreserveParameterType.GET);
            ad4Authenticate.setReturnPage("AD4AutenticazioneAd4Utente" + Names.ACTION_SUFFIX);

            com.codecharge.components.Button Button_Update__13 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__13.addExcludeParam( "ccsForm" );
            Button_Update__13.addExcludeParam( "Button_Update" );
            Button_Update__13.setOperation( "Update" );
            ad4Authenticate.add( Button_Update__13 );

            com.codecharge.components.Button Button_Cancel__14 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__14.addExcludeParam( "ccsForm" );
            Button_Cancel__14.addExcludeParam( "Button_Cancel" );
            Button_Cancel__14.setOperation( "Cancel" );
            ad4Authenticate.add( Button_Cancel__14 );
            add(ad4Authenticate);
        } // End definition of ad4Authenticate record model.
//End ad4Authenticate record

//AD4AutenticazioneAd4UtenteModel class tail @1-F5FC18C5
    }
}
//End AD4AutenticazioneAd4UtenteModel class tail
