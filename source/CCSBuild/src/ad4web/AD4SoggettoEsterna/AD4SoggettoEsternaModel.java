//AD4SoggettoEsternaModel imports @1-3BE196F9
package ad4web.AD4SoggettoEsterna;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4SoggettoEsternaModel imports

//AD4SoggettoEsternaModel class head @1-078EDCD7
public class AD4SoggettoEsternaModel extends com.codecharge.components.Page {
    public AD4SoggettoEsternaModel() {
        this( new CCSLocale(), null );
    }

    public AD4SoggettoEsternaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4SoggettoEsternaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4SoggettoEsternaModel class head

//page settings @1-2C92D8CA
        super("AD4SoggettoEsterna", locale );
        setResponse(response);
        {
            com.codecharge.components.IncludePage AmvStyle__4 = new com.codecharge.components.IncludePage("AmvStyle", this );
            AmvStyle__4.setVisible( true );
            add( AmvStyle__4 );
            com.codecharge.components.IncludePage AmvControl__5 = new com.codecharge.components.IncludePage("AmvControl", this );
            AmvControl__5.setVisible( true );
            add( AmvControl__5 );
            com.codecharge.components.IncludePage AD4SoggettoInclusa__3 = new com.codecharge.components.IncludePage("AD4SoggettoInclusa", this );
            AD4SoggettoInclusa__3.setVisible( true );
            add( AD4SoggettoInclusa__3 );
        } // end page
//End page settings

//AD4SoggettoEsternaModel class tail @1-F5FC18C5
    }
}
//End AD4SoggettoEsternaModel class tail
