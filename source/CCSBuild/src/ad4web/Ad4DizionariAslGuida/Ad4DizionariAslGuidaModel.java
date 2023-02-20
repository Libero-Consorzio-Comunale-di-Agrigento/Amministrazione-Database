//Ad4DizionariAslGuidaModel imports @1-FAACB784
package ad4web.Ad4DizionariAslGuida;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariAslGuidaModel imports

//Ad4DizionariAslGuidaModel class head @1-B1C129A7
public class Ad4DizionariAslGuidaModel extends com.codecharge.components.Page {
    public Ad4DizionariAslGuidaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariAslGuidaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariAslGuidaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariAslGuidaModel class head

//page settings @1-C2FB429C
        super("Ad4DizionariAslGuida", locale );
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

//Ad4DizionariAslGuidaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariAslGuidaModel class tail

