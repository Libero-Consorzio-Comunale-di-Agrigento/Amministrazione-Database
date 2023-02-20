//AD4ModParametriModel imports @1-A422747D
package ad4web.AD4ModParametri;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4ModParametriModel imports

//AD4ModParametriModel class head @1-B8D3C1A9
public class AD4ModParametriModel extends com.codecharge.components.Page {
    public AD4ModParametriModel() {
        this( new CCSLocale(), null );
    }

    public AD4ModParametriModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4ModParametriModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4ModParametriModel class head

//page settings @1-CFE193EF
        super("AD4ModParametri", locale );
        setResponse(response);
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__5 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__5.setVisible( true );
            add( Guida__5 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4_PARAMETRO record @23-4815E2EE
        
        /*
            Model of AD4_PARAMETRO record defining.
        */
        {
            com.codecharge.components.Record AD4_PARAMETRO = new com.codecharge.components.Record("AD4_PARAMETRO");
            AD4_PARAMETRO.setPageModel( this );
            AD4_PARAMETRO.addExcludeParam( "ccsForm" );
            AD4_PARAMETRO.addExcludeParam( "IDPAR" );
            AD4_PARAMETRO.setVisible( true );
            AD4_PARAMETRO.setAllowInsert(false);
            AD4_PARAMETRO.setPreserveType(PreserveParameterType.GET);
            AD4_PARAMETRO.setReturnPage("AD4ModParametri" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label PARAMETRO__30 = new com.codecharge.components.Label("PARAMETRO", "PARAMETRO", this );
            PARAMETRO__30.setType( com.codecharge.components.ControlType.TEXT );
            AD4_PARAMETRO.add(PARAMETRO__30);

            com.codecharge.components.Hidden ID_PARAMETRO__41 = new com.codecharge.components.Hidden("ID_PARAMETRO", "ID_PARAMETRO", this );
            ID_PARAMETRO__41.setType( com.codecharge.components.ControlType.TEXT );
            ID_PARAMETRO__41.setHtmlEncode( true );
            AD4_PARAMETRO.add( ID_PARAMETRO__41 );

            com.codecharge.components.Hidden ID_RICHIESTA__73 = new com.codecharge.components.Hidden("ID_RICHIESTA", "ID_RICHIESTA", this );
            ID_RICHIESTA__73.setType( com.codecharge.components.ControlType.TEXT );
            ID_RICHIESTA__73.setHtmlEncode( true );
            AD4_PARAMETRO.add( ID_RICHIESTA__73 );

            com.codecharge.components.TextBox VALORE__31 = new com.codecharge.components.TextBox("VALORE", "VALORE", this );
            VALORE__31.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__31.setHtmlEncode( true );
            AD4_PARAMETRO.add( VALORE__31 );

            com.codecharge.components.Button Button_Update__25 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__25.addExcludeParam( "ccsForm" );
            Button_Update__25.addExcludeParam( "Button_Update" );
            Button_Update__25.addExcludeParam( "IDPAR" );
            Button_Update__25.setOperation( "Update" );
            AD4_PARAMETRO.add( Button_Update__25 );

            com.codecharge.components.Button Button_Delete__26 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__26.addExcludeParam( "ccsForm" );
            Button_Delete__26.addExcludeParam( "Button_Delete" );
            Button_Delete__26.addExcludeParam( "IDPAR" );
            Button_Delete__26.setOperation( "Delete" );
            AD4_PARAMETRO.add( Button_Delete__26 );

            com.codecharge.components.Button Button_Cancel__28 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__28.addExcludeParam( "ccsForm" );
            Button_Cancel__28.addExcludeParam( "Button_Cancel" );
            Button_Cancel__28.addExcludeParam( "IDPAR" );
            Button_Cancel__28.setOperation( "Cancel" );
            AD4_PARAMETRO.add( Button_Cancel__28 );
            add(AD4_PARAMETRO);
        } // End definition of AD4_PARAMETRO record model.
//End AD4_PARAMETRO record

//AD4ModParametriModel class tail @1-F5FC18C5
    }
}
//End AD4ModParametriModel class tail
