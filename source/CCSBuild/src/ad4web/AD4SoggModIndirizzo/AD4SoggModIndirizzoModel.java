//AD4SoggModIndirizzoModel imports @1-D16B99EE
package ad4web.AD4SoggModIndirizzo;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4SoggModIndirizzoModel imports

//AD4SoggModIndirizzoModel class head @1-C0646A95
public class AD4SoggModIndirizzoModel extends com.codecharge.components.Page {
    public AD4SoggModIndirizzoModel() {
        this( new CCSLocale(), null );
    }

    public AD4SoggModIndirizzoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4SoggModIndirizzoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4SoggModIndirizzoModel class head

//page settings @1-7DF5DB02
        super("AD4SoggModIndirizzo", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//AD4_UTENTI record @2-E960A3B3
        
        /*
            Model of AD4_UTENTI record defining.
        */
        {
            com.codecharge.components.Record AD4_UTENTI = new com.codecharge.components.Record("AD4_UTENTI");
            AD4_UTENTI.setPageModel( this );
            AD4_UTENTI.addExcludeParam( "ccsForm" );
            AD4_UTENTI.setVisible( true );
            AD4_UTENTI.setAllowInsert(false);
            AD4_UTENTI.setAllowUpdate(false);
            AD4_UTENTI.setAllowDelete(false);
            AD4_UTENTI.setPreserveType(PreserveParameterType.GET);
            AD4_UTENTI.setReturnPage("AD4SoggModIndirizzo" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox VIA__18 = new com.codecharge.components.ListBox("VIA", "", this );
            VIA__18.setType( com.codecharge.components.ControlType.TEXT );
            VIA__18.setHtmlEncode( true );
            VIA__18.setCaption( "VIA" );
            AD4_UTENTI.add( VIA__18 );

            com.codecharge.components.TextBox INDIRIZZO__4 = new com.codecharge.components.TextBox("INDIRIZZO", "", this );
            INDIRIZZO__4.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO__4.setHtmlEncode( true );
            INDIRIZZO__4.setCaption( "INDIRIZZO" );
            AD4_UTENTI.add( INDIRIZZO__4 );

            com.codecharge.components.TextBox NUM__19 = new com.codecharge.components.TextBox("NUM", "", this );
            NUM__19.setType( com.codecharge.components.ControlType.INTEGER );
            NUM__19.setHtmlEncode( true );
            NUM__19.setCaption( "NUM" );
            AD4_UTENTI.add( NUM__19 );

            com.codecharge.components.Button Button_Update__6 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__6.addExcludeParam( "ccsForm" );
            Button_Update__6.addExcludeParam( "Button_Update" );
            AD4_UTENTI.add( Button_Update__6 );

            com.codecharge.components.Button Button_Cancel__7 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__7.addExcludeParam( "ccsForm" );
            Button_Cancel__7.addExcludeParam( "Button_Cancel" );
            Button_Cancel__7.setOperation( "Cancel" );
            AD4_UTENTI.add( Button_Cancel__7 );
            add(AD4_UTENTI);
        } // End definition of AD4_UTENTI record model.
//End AD4_UTENTI record

//AD4SoggModIndirizzoModel class tail @1-F5FC18C5
    }
}
//End AD4SoggModIndirizzoModel class tail
