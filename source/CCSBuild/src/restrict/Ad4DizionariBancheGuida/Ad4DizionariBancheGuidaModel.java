//Ad4DizionariBancheGuidaModel imports @1-E34F255D
package restrict.Ad4DizionariBancheGuida;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariBancheGuidaModel imports

//Ad4DizionariBancheGuidaModel class head @1-9D990E58
public class Ad4DizionariBancheGuidaModel extends com.codecharge.components.Page {
    public Ad4DizionariBancheGuidaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariBancheGuidaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariBancheGuidaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariBancheGuidaModel class head

//page settings @1-39A925A7
        super("Ad4DizionariBancheGuida", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//Guida grid @2-EB4174AF
        
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

            com.codecharge.components.Label FOLDER5__7 = new com.codecharge.components.Label("FOLDER5", "FOLDER5", this );
            FOLDER5__7.setType( com.codecharge.components.ControlType.TEXT );
            Guida.add(FOLDER5__7);
            add(Guida);
        } // End definition of Guida grid model
//End Guida grid

//Ad4DizionariBancheGuidaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariBancheGuidaModel class tail
