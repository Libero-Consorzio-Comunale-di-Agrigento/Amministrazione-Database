//AmvErrorModel imports @1-39704F04
package common.AmvError;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AmvErrorModel imports

//AmvErrorModel class head @1-12189C0C
public class AmvErrorModel extends com.codecharge.components.Page {
    public AmvErrorModel() {
        this( new CCSLocale(), null );
    }

    public AmvErrorModel(CCSLocale locale) {
        this( locale, null );
    }

    public AmvErrorModel( CCSLocale locale, HttpServletResponse response ) {
//End AmvErrorModel class head

//page settings @1-FA414EA6
        super("AmvError", locale );
        setResponse(response);
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//MESSAGGIO_ERRORE grid @5-0069D075
        
        /*
            // Begin definition of MESSAGGIO_ERRORE grid model.
        */
        {
            com.codecharge.components.Grid MESSAGGIO_ERRORE = new com.codecharge.components.Grid("MESSAGGIO_ERRORE");
            MESSAGGIO_ERRORE.setPageModel( this );
            MESSAGGIO_ERRORE.setFetchSize(20);
            MESSAGGIO_ERRORE.setVisible( true );

            com.codecharge.components.Label MSG__6 = new com.codecharge.components.Label("MSG", "MSG", this );
            MSG__6.setType( com.codecharge.components.ControlType.TEXT );
            MESSAGGIO_ERRORE.add(MSG__6);

            com.codecharge.components.Label CUSTOM_MSG__17 = new com.codecharge.components.Label("CUSTOM_MSG", "CUSTOM_MSG", this );
            CUSTOM_MSG__17.setType( com.codecharge.components.ControlType.TEXT );
            MESSAGGIO_ERRORE.add(CUSTOM_MSG__17);
            add(MESSAGGIO_ERRORE);
        } // End definition of MESSAGGIO_ERRORE grid model
//End MESSAGGIO_ERRORE grid

//AmvErrorModel class tail @1-F5FC18C5
    }
}
//End AmvErrorModel class tail
