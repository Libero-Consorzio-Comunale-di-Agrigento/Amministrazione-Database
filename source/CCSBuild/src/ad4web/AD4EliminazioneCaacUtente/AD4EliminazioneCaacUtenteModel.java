//AD4EliminazioneCaacUtenteModel imports @1-9B2ED8D2
package ad4web.AD4EliminazioneCaacUtente;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4EliminazioneCaacUtenteModel imports

//AD4EliminazioneCaacUtenteModel class head @1-D729E3F1
public class AD4EliminazioneCaacUtenteModel extends com.codecharge.components.Page {
    public AD4EliminazioneCaacUtenteModel() {
        this( new CCSLocale(), null );
    }

    public AD4EliminazioneCaacUtenteModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4EliminazioneCaacUtenteModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4EliminazioneCaacUtenteModel class head

//page settings @1-83CBDFBF
        super("AD4EliminazioneCaacUtente", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//ad4CaacElimina record @11-0A3148CB
        
        /*
            Model of ad4CaacElimina record defining.
        */
        {
            com.codecharge.components.Record ad4CaacElimina = new com.codecharge.components.Record("ad4CaacElimina");
            ad4CaacElimina.setPageModel( this );
            ad4CaacElimina.addExcludeParam( "ccsForm" );
            ad4CaacElimina.setVisible( true );
            ad4CaacElimina.setAllowInsert(false);
            ad4CaacElimina.setAllowDelete(false);
            ad4CaacElimina.setPreserveType(PreserveParameterType.GET);
            ad4CaacElimina.setReturnPage("AD4EliminazioneCaacUtente" + Names.ACTION_SUFFIX);

            com.codecharge.components.Button Button_Update__13 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__13.addExcludeParam( "ccsForm" );
            Button_Update__13.addExcludeParam( "Button_Update" );
            Button_Update__13.setOperation( "Update" );
            ad4CaacElimina.add( Button_Update__13 );

            com.codecharge.components.Button Button_Cancel__14 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__14.addExcludeParam( "ccsForm" );
            Button_Cancel__14.addExcludeParam( "Button_Cancel" );
            Button_Cancel__14.setOperation( "Cancel" );
            ad4CaacElimina.add( Button_Cancel__14 );
            add(ad4CaacElimina);
        } // End definition of ad4CaacElimina record model.
//End ad4CaacElimina record

//AD4EliminazioneCaacUtenteModel class tail @1-F5FC18C5
    }
}
//End AD4EliminazioneCaacUtenteModel class tail
