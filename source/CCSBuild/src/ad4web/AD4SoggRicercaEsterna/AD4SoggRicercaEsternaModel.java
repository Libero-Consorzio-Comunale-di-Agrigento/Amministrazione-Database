//AD4SoggRicercaEsternaModel imports @1-65FAB66D
package ad4web.AD4SoggRicercaEsterna;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4SoggRicercaEsternaModel imports

//AD4SoggRicercaEsternaModel class head @1-2173A4F1
public class AD4SoggRicercaEsternaModel extends com.codecharge.components.Page {
    public AD4SoggRicercaEsternaModel() {
        this( new CCSLocale(), null );
    }

    public AD4SoggRicercaEsternaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4SoggRicercaEsternaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4SoggRicercaEsternaModel class head

//page settings @1-91F5F22D
        super("AD4SoggRicercaEsterna", locale );
        setResponse(response);
        addPageListener(new AD4SoggRicercaEsternaPageHandler());
        {
            com.codecharge.components.IncludePage AmvStyle__4 = new com.codecharge.components.IncludePage("AmvStyle", this );
            AmvStyle__4.setVisible( true );
            add( AmvStyle__4 );
            com.codecharge.components.IncludePage AmvControl__5 = new com.codecharge.components.IncludePage("AmvControl", this );
            AmvControl__5.setVisible( true );
            add( AmvControl__5 );
            com.codecharge.components.IncludePage AD4SoggRicercaInclusa__3 = new com.codecharge.components.IncludePage("AD4SoggRicercaInclusa", this );
            AD4SoggRicercaInclusa__3.setVisible( true );
            add( AD4SoggRicercaInclusa__3 );
        } // end page
//End page settings

//AD4SoggRicercaEsternaModel class tail @1-F5FC18C5
    }
}
//End AD4SoggRicercaEsternaModel class tail
