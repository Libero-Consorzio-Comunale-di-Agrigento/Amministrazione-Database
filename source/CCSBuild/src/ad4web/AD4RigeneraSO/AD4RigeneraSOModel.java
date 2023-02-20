//AD4RigeneraSOModel imports @1-6B5AF678
package ad4web.AD4RigeneraSO;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4RigeneraSOModel imports

//AD4RigeneraSOModel class head @1-42943262
public class AD4RigeneraSOModel extends com.codecharge.components.Page {
    public AD4RigeneraSOModel() {
        this( new CCSLocale(), null );
    }

    public AD4RigeneraSOModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4RigeneraSOModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4RigeneraSOModel class head

//page settings @1-7B9F0109
        super("AD4RigeneraSO", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//rigenera record @11-6A5C79E5
        
        /*
            Model of rigenera record defining.
        */
        {
            com.codecharge.components.Record rigenera = new com.codecharge.components.Record("rigenera");
            rigenera.setPageModel( this );
            rigenera.addExcludeParam( "ccsForm" );
            rigenera.setVisible( true );
            rigenera.setAllowInsert(false);
            rigenera.setAllowDelete(false);
            rigenera.setPreserveType(PreserveParameterType.GET);
            rigenera.setReturnPage("AD4RigeneraSO" + Names.ACTION_SUFFIX);

            com.codecharge.components.Button Button_Update__13 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__13.addExcludeParam( "ccsForm" );
            Button_Update__13.addExcludeParam( "Button_Update" );
            Button_Update__13.setOperation( "Update" );
            rigenera.add( Button_Update__13 );

            com.codecharge.components.Button Button_Cancel__14 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__14.addExcludeParam( "ccsForm" );
            Button_Cancel__14.addExcludeParam( "Button_Cancel" );
            Button_Cancel__14.setOperation( "Cancel" );
            rigenera.add( Button_Cancel__14 );
            add(rigenera);
        } // End definition of rigenera record model.
//End rigenera record

//AD4RigeneraSOModel class tail @1-F5FC18C5
    }
}
//End AD4RigeneraSOModel class tail
