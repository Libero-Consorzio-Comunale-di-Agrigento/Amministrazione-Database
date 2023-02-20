//AD4TemplateModel imports @1-EB6D49B6
package ad4web.AD4Template;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4TemplateModel imports

//AD4TemplateModel class head @1-BC08EACE
public class AD4TemplateModel extends com.codecharge.components.Page {
    public AD4TemplateModel() {
        this( new CCSLocale(), null );
    }

    public AD4TemplateModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4TemplateModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4TemplateModel class head

//page settings @1-D6C55F64
        super("AD4Template", locale );
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
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4TemplateModel class tail @1-F5FC18C5
    }
}
//End AD4TemplateModel class tail
