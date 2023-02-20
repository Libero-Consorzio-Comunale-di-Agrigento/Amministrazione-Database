//Ad4DizionariModel imports @1-C7BA732A
package restrict.Ad4Dizionari;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariModel imports

//Ad4DizionariModel class head @1-C802D457
public class Ad4DizionariModel extends com.codecharge.components.Page {
    public Ad4DizionariModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariModel class head

//page settings @1-E849A5A5
        super("Ad4Dizionari", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//Ad4DizionariModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariModel class tail

