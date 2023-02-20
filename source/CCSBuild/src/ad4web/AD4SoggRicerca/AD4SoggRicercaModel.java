//AD4SoggRicercaModel imports @1-4DEFBF16
package ad4web.AD4SoggRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4SoggRicercaModel imports

//AD4SoggRicercaModel class head @1-F00D0FE4
public class AD4SoggRicercaModel extends com.codecharge.components.Page {
    public AD4SoggRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4SoggRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4SoggRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4SoggRicercaModel class head

//page settings @1-21A5436E
        super("AD4SoggRicerca", locale );
        setResponse(response);
        addPageListener(new AD4SoggRicercaPageHandler());
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
            com.codecharge.components.IncludePage AD4SoggRicercaInclusa__6 = new com.codecharge.components.IncludePage("AD4SoggRicercaInclusa", this );
            AD4SoggRicercaInclusa__6.setVisible( true );
            add( AD4SoggRicercaInclusa__6 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4SoggRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4SoggRicercaModel class tail
