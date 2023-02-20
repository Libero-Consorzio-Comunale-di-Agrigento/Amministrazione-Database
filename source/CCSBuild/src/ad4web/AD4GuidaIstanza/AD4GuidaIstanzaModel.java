//AD4GuidaIstanzaModel imports @1-5F9C4A0C
package ad4web.AD4GuidaIstanza;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GuidaIstanzaModel imports

//AD4GuidaIstanzaModel class head @1-216563AE
public class AD4GuidaIstanzaModel extends com.codecharge.components.Page {
    public AD4GuidaIstanzaModel() {
        this( new CCSLocale(), null );
    }

    public AD4GuidaIstanzaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GuidaIstanzaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GuidaIstanzaModel class head

//page settings @1-C8732FDB
        super("AD4GuidaIstanza", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//NewGrid1 grid @2-F331B4B7
        
        /*
            // Begin definition of NewGrid1 grid model.
        */
        {
            com.codecharge.components.Grid NewGrid1 = new com.codecharge.components.Grid("NewGrid1");
            NewGrid1.setPageModel( this );
            NewGrid1.setFetchSize(20);
            NewGrid1.setVisible( true );

            com.codecharge.components.Label FOLDER__3 = new com.codecharge.components.Label("FOLDER", "FOLDER", this );
            FOLDER__3.setType( com.codecharge.components.ControlType.TEXT );
            NewGrid1.add(FOLDER__3);
            add(NewGrid1);
        } // End definition of NewGrid1 grid model
//End NewGrid1 grid

//AD4GuidaIstanzaModel class tail @1-F5FC18C5
    }
}
//End AD4GuidaIstanzaModel class tail

