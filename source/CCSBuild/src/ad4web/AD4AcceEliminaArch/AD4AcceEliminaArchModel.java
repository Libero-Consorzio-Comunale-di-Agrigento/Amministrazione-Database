//AD4AcceEliminaArchModel imports @1-16C41764
package ad4web.AD4AcceEliminaArch;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4AcceEliminaArchModel imports

//AD4AcceEliminaArchModel class head @1-CAF5FB0A
public class AD4AcceEliminaArchModel extends com.codecharge.components.Page {
    public AD4AcceEliminaArchModel() {
        this( new CCSLocale(), null );
    }

    public AD4AcceEliminaArchModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4AcceEliminaArchModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4AcceEliminaArchModel class head

//page settings @1-AB648030
        super("AD4AcceEliminaArch", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//accessi record @11-4CEFD83D
        
        /*
            Model of accessi record defining.
        */
        {
            com.codecharge.components.Record accessi = new com.codecharge.components.Record("accessi");
            accessi.setPageModel( this );
            accessi.addExcludeParam( "ccsForm" );
            accessi.setVisible( true );
            accessi.setAllowInsert(false);
            accessi.setAllowDelete(false);
            accessi.setPreserveType(PreserveParameterType.GET);
            accessi.setReturnPage("AD4AcceEliminaArch" + Names.ACTION_SUFFIX);

            com.codecharge.components.Button Button_Update__13 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__13.addExcludeParam( "ccsForm" );
            Button_Update__13.addExcludeParam( "Button_Update" );
            Button_Update__13.setOperation( "Update" );
            accessi.add( Button_Update__13 );

            com.codecharge.components.Button Button_Cancel__14 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__14.addExcludeParam( "ccsForm" );
            Button_Cancel__14.addExcludeParam( "Button_Cancel" );
            Button_Cancel__14.setOperation( "Cancel" );
            accessi.add( Button_Cancel__14 );
            add(accessi);
        } // End definition of accessi record model.
//End accessi record

//AD4AcceEliminaArchModel class tail @1-F5FC18C5
    }
}
//End AD4AcceEliminaArchModel class tail

