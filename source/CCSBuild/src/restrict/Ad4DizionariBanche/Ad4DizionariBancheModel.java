//Ad4DizionariBancheModel imports @1-615A42ED
package restrict.Ad4DizionariBanche;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariBancheModel imports

//Ad4DizionariBancheModel class head @1-4DF6F77D
public class Ad4DizionariBancheModel extends com.codecharge.components.Page {
    public Ad4DizionariBancheModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariBancheModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariBancheModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariBancheModel class head

//page settings @1-0AD3BAD8
        super("Ad4DizionariBanche", locale );
        setResponse(response);
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
            com.codecharge.components.IncludePage AmvInclude__6 = new com.codecharge.components.IncludePage("AmvInclude", this );
            AmvInclude__6.setVisible( true );
            add( AmvInclude__6 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//Ad4DizionariBancheModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariBancheModel class tail
