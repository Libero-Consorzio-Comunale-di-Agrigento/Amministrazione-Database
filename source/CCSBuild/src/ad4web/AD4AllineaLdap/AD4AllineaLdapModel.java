//AD4AllineaLdapModel imports @1-06E26467
package ad4web.AD4AllineaLdap;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4AllineaLdapModel imports

//AD4AllineaLdapModel class head @1-B1747C41
public class AD4AllineaLdapModel extends com.codecharge.components.Page {
    public AD4AllineaLdapModel() {
        this( new CCSLocale(), null );
    }

    public AD4AllineaLdapModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4AllineaLdapModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4AllineaLdapModel class head

//page settings @1-090688E1
        super("AD4AllineaLdap", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//allineaLdap record @11-4FBBE19E
        
        /*
            Model of allineaLdap record defining.
        */
        {
            com.codecharge.components.Record allineaLdap = new com.codecharge.components.Record("allineaLdap");
            allineaLdap.setPageModel( this );
            allineaLdap.addExcludeParam( "ccsForm" );
            allineaLdap.setVisible( true );
            allineaLdap.setAllowInsert(false);
            allineaLdap.setAllowDelete(false);
            allineaLdap.setPreserveType(PreserveParameterType.GET);
            allineaLdap.setReturnPage("AD4AllineaLdap" + Names.ACTION_SUFFIX);

            com.codecharge.components.Button Button_Update__13 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__13.addExcludeParam( "ccsForm" );
            Button_Update__13.addExcludeParam( "Button_Update" );
            Button_Update__13.setOperation( "Update" );
            allineaLdap.add( Button_Update__13 );

            com.codecharge.components.Button Button_Cancel__14 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__14.addExcludeParam( "ccsForm" );
            Button_Cancel__14.addExcludeParam( "Button_Cancel" );
            Button_Cancel__14.setOperation( "Cancel" );
            allineaLdap.add( Button_Cancel__14 );
            add(allineaLdap);
        } // End definition of allineaLdap record model.
//End allineaLdap record

//AD4AllineaLdapModel class tail @1-F5FC18C5
    }
}
//End AD4AllineaLdapModel class tail
