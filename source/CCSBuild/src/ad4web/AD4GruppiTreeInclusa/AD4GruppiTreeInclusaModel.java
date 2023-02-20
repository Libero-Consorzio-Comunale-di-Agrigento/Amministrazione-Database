//AD4GruppiTreeInclusaModel imports @1-705B3CFD
package ad4web.AD4GruppiTreeInclusa;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GruppiTreeInclusaModel imports

//AD4GruppiTreeInclusaModel class head @1-B48DD998
public class AD4GruppiTreeInclusaModel extends com.codecharge.components.Page {
    public AD4GruppiTreeInclusaModel() {
        this( new CCSLocale(), null );
    }

    public AD4GruppiTreeInclusaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GruppiTreeInclusaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GruppiTreeInclusaModel class head

//page settings @1-8E33D0CC
        super("AD4GruppiTreeInclusa", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AD4_GRUPPI grid @6-6F107B08
        
        /*
            // Begin definition of AD4_GRUPPI grid model.
        */
        {
            com.codecharge.components.Grid AD4_GRUPPI = new com.codecharge.components.Grid("AD4_GRUPPI");
            AD4_GRUPPI.setPageModel( this );
            AD4_GRUPPI.setFetchSize(20);
            AD4_GRUPPI.setVisible( true );

            com.codecharge.components.Label ALBERO__7 = new com.codecharge.components.Label("ALBERO", "ALBERO", this );
            ALBERO__7.setType( com.codecharge.components.ControlType.TEXT );
            AD4_GRUPPI.add(ALBERO__7);
            add(AD4_GRUPPI);
        } // End definition of AD4_GRUPPI grid model
//End AD4_GRUPPI grid

//AD4GruppiTreeInclusaModel class tail @1-F5FC18C5
    }
}
//End AD4GruppiTreeInclusaModel class tail
