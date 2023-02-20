//VersioneModel imports @1-36A9539E
package common.Versione;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End VersioneModel imports

//VersioneModel class head @1-92A4854B
public class VersioneModel extends com.codecharge.components.Page {
    public VersioneModel() {
        this( new CCSLocale(), null );
    }

    public VersioneModel(CCSLocale locale) {
        this( locale, null );
    }

    public VersioneModel( CCSLocale locale, HttpServletResponse response ) {
//End VersioneModel class head

//page settings @1-E8331C5A
        super("Versione", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//VersioneGrid grid @3-71DB9B83
        
        /*
            // Begin definition of VersioneGrid grid model.
        */
        {
            com.codecharge.components.Grid VersioneGrid = new com.codecharge.components.Grid("VersioneGrid");
            VersioneGrid.setPageModel( this );
            VersioneGrid.setFetchSize(20);
            VersioneGrid.setVisible( true );
            VersioneGrid.addGridListener( new VersioneGridGridHandler() );

            com.codecharge.components.Label VERSIONE__5 = new com.codecharge.components.Label("VERSIONE", this);
            VERSIONE__5.setType( com.codecharge.components.ControlType.TEXT );
            VERSIONE__5.setHtmlEncode( true );
            VersioneGrid.add(VERSIONE__5);

            com.codecharge.components.Label VERSIONE_DB__4 = new com.codecharge.components.Label("VERSIONE_DB", "VERSIONE_DB", this );
            VERSIONE_DB__4.setType( com.codecharge.components.ControlType.TEXT );
            VERSIONE_DB__4.setHtmlEncode( true );
            VersioneGrid.add(VERSIONE_DB__4);
            add(VersioneGrid);
        } // End definition of VersioneGrid grid model
//End VersioneGrid grid

//VersioneModel class tail @1-F5FC18C5
    }
}
//End VersioneModel class tail

