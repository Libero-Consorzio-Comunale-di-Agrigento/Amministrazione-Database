//AD4SoggettoModel imports @1-9DB00EAE
package ad4web.AD4Soggetto;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4SoggettoModel imports

//AD4SoggettoModel class head @1-FF2CABE6
public class AD4SoggettoModel extends com.codecharge.components.Page {
    public AD4SoggettoModel() {
        this( new CCSLocale(), null );
    }

    public AD4SoggettoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4SoggettoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4SoggettoModel class head

//page settings @1-FDA03547
        super("AD4Soggetto", locale );
        setResponse(response);
        addPageListener(new AD4SoggettoPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage AmvGuida__5 = new com.codecharge.components.IncludePage("AmvGuida", this );
            AmvGuida__5.setVisible( true );
            add( AmvGuida__5 );
            com.codecharge.components.IncludePage AD4SoggettoInclusa__190 = new com.codecharge.components.IncludePage("AD4SoggettoInclusa", this );
            AD4SoggettoInclusa__190.setVisible( true );
            add( AD4SoggettoInclusa__190 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4SoggettoModel class tail @1-F5FC18C5
    }
}
//End AD4SoggettoModel class tail
