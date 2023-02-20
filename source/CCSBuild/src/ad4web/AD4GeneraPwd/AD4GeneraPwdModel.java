//AD4GeneraPwdModel imports @1-C09DD717
package ad4web.AD4GeneraPwd;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GeneraPwdModel imports

//AD4GeneraPwdModel class head @1-EA8516B3
public class AD4GeneraPwdModel extends com.codecharge.components.Page {
    public AD4GeneraPwdModel() {
        this( new CCSLocale(), null );
    }

    public AD4GeneraPwdModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GeneraPwdModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GeneraPwdModel class head

//page settings @1-7B42C374
        super("AD4GeneraPwd", locale );
        setResponse(response);
        addPageListener(new AD4GeneraPwdPageHandler());
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

//UTENTI record @6-3DC0CE40
        
        /*
            Model of UTENTI record defining.
        */
        {
            com.codecharge.components.Record UTENTI = new com.codecharge.components.Record("UTENTI");
            UTENTI.setPageModel( this );
            UTENTI.addExcludeParam( "ccsForm" );
            UTENTI.setVisible( true );
            UTENTI.setAllowInsert(false);
            UTENTI.setAllowDelete(false);
            UTENTI.setPreserveType(PreserveParameterType.GET);
            UTENTI.setReturnPage("AD4GeneraPwd" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label NOMINATIVO__19 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__19.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__19.setHtmlEncode( true );
            UTENTI.add(NOMINATIVO__19);

            com.codecharge.components.Label DESCR__20 = new com.codecharge.components.Label("DESCR", "DESCR", this );
            DESCR__20.setType( com.codecharge.components.ControlType.TEXT );
            DESCR__20.setHtmlEncode( true );
            UTENTI.add(DESCR__20);

            com.codecharge.components.Hidden UTENTE__7 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__7.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__7.setHtmlEncode( true );
            UTENTI.add( UTENTE__7 );

            com.codecharge.components.TextBox MIN_PWD_LENGTH__21 = new com.codecharge.components.TextBox("MIN_PWD_LENGTH", "MIN_PWD_LENGTH", this );
            MIN_PWD_LENGTH__21.setType( com.codecharge.components.ControlType.INTEGER );
            MIN_PWD_LENGTH__21.setHtmlEncode( true );
            UTENTI.add( MIN_PWD_LENGTH__21 );

            com.codecharge.components.Label PWD__22 = new com.codecharge.components.Label("PWD", "PWD", this );
            PWD__22.setType( com.codecharge.components.ControlType.TEXT );
            UTENTI.add(PWD__22);

            com.codecharge.components.Button Button_Insert__9 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__9.addExcludeParam( "ccsForm" );
            Button_Insert__9.addExcludeParam( "Button_Insert" );
            Button_Insert__9.setOperation( "Insert" );
            UTENTI.add( Button_Insert__9 );

            com.codecharge.components.Button Button_Update__10 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__10.addExcludeParam( "ccsForm" );
            Button_Update__10.addExcludeParam( "Button_Update" );
            Button_Update__10.setOperation( "Update" );
            UTENTI.add( Button_Update__10 );

            com.codecharge.components.Button Button_Delete__11 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__11.addExcludeParam( "ccsForm" );
            Button_Delete__11.addExcludeParam( "Button_Delete" );
            Button_Delete__11.setOperation( "Delete" );
            UTENTI.add( Button_Delete__11 );

            com.codecharge.components.Button Button_Cancel__12 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__12.addButtonListener(new UTENTIButton_CancelHandler());
            Button_Cancel__12.addExcludeParam( "ccsForm" );
            Button_Cancel__12.addExcludeParam( "Button_Cancel" );
            Button_Cancel__12.setOperation( "Cancel" );
            UTENTI.add( Button_Cancel__12 );
            add(UTENTI);
        } // End definition of UTENTI record model.
//End UTENTI record

//AD4GeneraPwdModel class tail @1-F5FC18C5
    }
}
//End AD4GeneraPwdModel class tail
