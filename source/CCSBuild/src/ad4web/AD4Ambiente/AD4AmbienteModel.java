//AD4AmbienteModel imports @1-649B62FF
package ad4web.AD4Ambiente;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4AmbienteModel imports

//AD4AmbienteModel class head @1-D3D43DF1
public class AD4AmbienteModel extends com.codecharge.components.Page {
    public AD4AmbienteModel() {
        this( new CCSLocale(), null );
    }

    public AD4AmbienteModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4AmbienteModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4AmbienteModel class head

//page settings @1-A3377510
        super("AD4Ambiente", locale );
        setResponse(response);
        addPageListener(new AD4AmbientePageHandler());
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

//AD4_AMBIENTE grid @6-B708E112
        
        /*
            // Begin definition of AD4_AMBIENTE grid model.
        */
        {
            com.codecharge.components.Grid AD4_AMBIENTE = new com.codecharge.components.Grid("AD4_AMBIENTE");
            AD4_AMBIENTE.setPageModel( this );
            AD4_AMBIENTE.setFetchSize(20);
            AD4_AMBIENTE.setVisible( true );
            AD4_AMBIENTE.addGridListener( new AD4_AMBIENTEGridHandler() );

            com.codecharge.components.Label AMBIENTE__7 = new com.codecharge.components.Label("AMBIENTE", "AMBIENTE", this );
            AMBIENTE__7.setType( com.codecharge.components.ControlType.TEXT );
            AD4_AMBIENTE.add(AMBIENTE__7);

            com.codecharge.components.Hidden EXTERNAL_AUTENTICATION__15 = new com.codecharge.components.Hidden("EXTERNAL_AUTENTICATION", "EXTERNAL_AUTENTICATION", this );
            EXTERNAL_AUTENTICATION__15.setType( com.codecharge.components.ControlType.TEXT );
            AD4_AMBIENTE.add( EXTERNAL_AUTENTICATION__15 );
            add(AD4_AMBIENTE);
        } // End definition of AD4_AMBIENTE grid model
//End AD4_AMBIENTE grid

//AD4AmbienteModel class tail @1-F5FC18C5
    }
}
//End AD4AmbienteModel class tail

