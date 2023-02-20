//AmvBackModel imports @1-00C4519D
package common.AmvBack;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AmvBackModel imports

//AmvBackModel class head @1-F6C91356
public class AmvBackModel extends com.codecharge.components.Page {
    public AmvBackModel() {
        this( new CCSLocale(), null );
    }

    public AmvBackModel(CCSLocale locale) {
        this( locale, null );
    }

    public AmvBackModel( CCSLocale locale, HttpServletResponse response ) {
//End AmvBackModel class head

//page settings @1-4CA91977
        super("AmvBack", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//REDIRECT_TAG grid @2-797FBF0E
        
        /*
            // Begin definition of REDIRECT_TAG grid model.
        */
        {
            com.codecharge.components.Grid REDIRECT_TAG = new com.codecharge.components.Grid("REDIRECT_TAG");
            REDIRECT_TAG.setPageModel( this );
            REDIRECT_TAG.setFetchSize(20);
            REDIRECT_TAG.setVisible( true );
            REDIRECT_TAG.addGridListener( new REDIRECT_TAGGridHandler() );

            com.codecharge.components.Label Redirection__3 = new com.codecharge.components.Label("Redirection", "REDIRECT", this );
            Redirection__3.setType( com.codecharge.components.ControlType.TEXT );
            REDIRECT_TAG.add(Redirection__3);
            add(REDIRECT_TAG);
        } // End definition of REDIRECT_TAG grid model
//End REDIRECT_TAG grid

//AmvBackModel class tail @1-F5FC18C5
    }
}
//End AmvBackModel class tail
