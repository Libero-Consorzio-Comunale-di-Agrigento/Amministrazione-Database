//RegistrazioneFineModel imports @1-218F876F
package common.RegistrazioneFine;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End RegistrazioneFineModel imports

//RegistrazioneFineModel class head @1-BDF711A2
public class RegistrazioneFineModel extends com.codecharge.components.Page {
    public RegistrazioneFineModel() {
        this( new CCSLocale(), null );
    }

    public RegistrazioneFineModel(CCSLocale locale) {
        this( locale, null );
    }

    public RegistrazioneFineModel( CCSLocale locale, HttpServletResponse response ) {
//End RegistrazioneFineModel class head

//page settings @1-359177D8
        super("RegistrazioneFine", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//RegistrazioneFineModel class tail @1-F5FC18C5
    }
}
//End RegistrazioneFineModel class tail

