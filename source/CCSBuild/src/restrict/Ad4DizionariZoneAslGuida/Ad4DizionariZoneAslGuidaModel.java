//Ad4DizionariZoneAslGuidaModel imports @1-F8DEB5A3
package restrict.Ad4DizionariZoneAslGuida;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariZoneAslGuidaModel imports

//Ad4DizionariZoneAslGuidaModel class head @1-6FA710D1
public class Ad4DizionariZoneAslGuidaModel extends com.codecharge.components.Page {
    public Ad4DizionariZoneAslGuidaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariZoneAslGuidaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariZoneAslGuidaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariZoneAslGuidaModel class head

//page settings @1-65C4E38F
        super("Ad4DizionariZoneAslGuida", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//Guida grid @2-B9E6646D
        
        /*
            // Begin definition of Guida grid model.
        */
        {
            com.codecharge.components.Grid Guida = new com.codecharge.components.Grid("Guida");
            Guida.setPageModel( this );
            Guida.setFetchSize(1);
            Guida.setVisible( true );

            com.codecharge.components.Label FOLDER1__3 = new com.codecharge.components.Label("FOLDER1", "FOLDER1", this );
            FOLDER1__3.setType( com.codecharge.components.ControlType.TEXT );
            Guida.add(FOLDER1__3);

            com.codecharge.components.Label FOLDER2__4 = new com.codecharge.components.Label("FOLDER2", "FOLDER2", this );
            FOLDER2__4.setType( com.codecharge.components.ControlType.TEXT );
            Guida.add(FOLDER2__4);
            add(Guida);
        } // End definition of Guida grid model
//End Guida grid

//Ad4DizionariZoneAslGuidaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariZoneAslGuidaModel class tail

