//Ad4DizionariGuidaModel imports @1-A152459D
package restrict.Ad4DizionariGuida;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariGuidaModel imports

//Ad4DizionariGuidaModel class head @1-57BADAFB
public class Ad4DizionariGuidaModel extends com.codecharge.components.Page {
    public Ad4DizionariGuidaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariGuidaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariGuidaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariGuidaModel class head

//page settings @1-616BC589
        super("Ad4DizionariGuida", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//Guida grid @2-857943ED
        
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

            com.codecharge.components.Label FOLDER3__5 = new com.codecharge.components.Label("FOLDER3", "FOLDER3", this );
            FOLDER3__5.setType( com.codecharge.components.ControlType.TEXT );
            Guida.add(FOLDER3__5);

            com.codecharge.components.Label FOLDER4__6 = new com.codecharge.components.Label("FOLDER4", "FOLDER4", this );
            FOLDER4__6.setType( com.codecharge.components.ControlType.TEXT );
            Guida.add(FOLDER4__6);

            com.codecharge.components.Label FOLDER5__7 = new com.codecharge.components.Label("FOLDER5", "FOLDER5", this );
            FOLDER5__7.setType( com.codecharge.components.ControlType.TEXT );
            Guida.add(FOLDER5__7);

            com.codecharge.components.Label FOLDER6__10 = new com.codecharge.components.Label("FOLDER6", "FOLDER6", this );
            FOLDER6__10.setType( com.codecharge.components.ControlType.TEXT );
            Guida.add(FOLDER6__10);
            add(Guida);
        } // End definition of Guida grid model
//End Guida grid

//Ad4DizionariGuidaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariGuidaModel class tail

