//AD4StringaModel imports @1-3B3AAF20
package ad4web.AD4Stringa;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4StringaModel imports

//AD4StringaModel class head @1-21A99916
public class AD4StringaModel extends com.codecharge.components.Page {
    public AD4StringaModel() {
        this( new CCSLocale(), null );
    }

    public AD4StringaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4StringaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4StringaModel class head

//page settings @1-0ED9C9FF
        super("AD4Stringa", locale );
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

//AD4_STRINGHE record @23-55097610
        
        /*
            Model of AD4_STRINGHE record defining.
        */
        {
            com.codecharge.components.Record AD4_STRINGHE = new com.codecharge.components.Record("AD4_STRINGHE");
            AD4_STRINGHE.setPageModel( this );
            AD4_STRINGHE.addExcludeParam( "ccsForm" );
            AD4_STRINGHE.setVisible( true );
            AD4_STRINGHE.setAllowInsert(false);
            AD4_STRINGHE.setPreserveType(PreserveParameterType.GET);
            AD4_STRINGHE.setReturnPage("AD4Stringa" + Names.ACTION_SUFFIX);
            AD4_STRINGHE.addRecordListener(new AD4_STRINGHERecordHandler());

            com.codecharge.components.TextBox CHIAVE__41 = new com.codecharge.components.TextBox("CHIAVE", "CHIAVE", this );
            CHIAVE__41.setType( com.codecharge.components.ControlType.TEXT );
            AD4_STRINGHE.add( CHIAVE__41 );

            com.codecharge.components.Hidden CHIAVE_OLD__83 = new com.codecharge.components.Hidden("CHIAVE_OLD", "CHIAVE_OLD", this );
            CHIAVE_OLD__83.setType( com.codecharge.components.ControlType.TEXT );
            CHIAVE_OLD__83.setHtmlEncode( true );
            AD4_STRINGHE.add( CHIAVE_OLD__83 );

            com.codecharge.components.TextBox STRINGA_NEW__178 = new com.codecharge.components.TextBox("STRINGA_NEW", "STRINGA", this );
            STRINGA_NEW__178.setType( com.codecharge.components.ControlType.TEXT );
            AD4_STRINGHE.add( STRINGA_NEW__178 );

            com.codecharge.components.Hidden STRINGA_OLD__84 = new com.codecharge.components.Hidden("STRINGA_OLD", "STRINGA_OLD", this );
            STRINGA_OLD__84.setType( com.codecharge.components.ControlType.TEXT );
            STRINGA_OLD__84.setHtmlEncode( true );
            AD4_STRINGHE.add( STRINGA_OLD__84 );

            com.codecharge.components.TextArea VALORE__57 = new com.codecharge.components.TextArea("VALORE", "VALORE", this );
            VALORE__57.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__57.addControlListener( new AD4_STRINGHEVALOREHandler());
            AD4_STRINGHE.add( VALORE__57 );

            com.codecharge.components.Hidden VALORE_OLD__85 = new com.codecharge.components.Hidden("VALORE_OLD", "VALORE_OLD", this );
            VALORE_OLD__85.setType( com.codecharge.components.ControlType.TEXT );
            VALORE_OLD__85.setHtmlEncode( true );
            AD4_STRINGHE.add( VALORE_OLD__85 );

            com.codecharge.components.TextArea COMMENTO__60 = new com.codecharge.components.TextArea("COMMENTO", "COMMENTO", this );
            COMMENTO__60.setType( com.codecharge.components.ControlType.TEXT );
            COMMENTO__60.addControlListener( new AD4_STRINGHECOMMENTOHandler());
            AD4_STRINGHE.add( COMMENTO__60 );

            com.codecharge.components.Hidden TIPOR_OLD__177 = new com.codecharge.components.Hidden("TIPOR_OLD", "TIPOR", this );
            TIPOR_OLD__177.setType( com.codecharge.components.ControlType.TEXT );
            TIPOR_OLD__177.setHtmlEncode( true );
            AD4_STRINGHE.add( TIPOR_OLD__177 );

            com.codecharge.components.Button Button_Update__25 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__25.addButtonListener(new AD4_STRINGHEButton_UpdateHandler());
            Button_Update__25.addExcludeParam( "ccsForm" );
            Button_Update__25.addExcludeParam( "Button_Update" );
            Button_Update__25.addExcludeParam( "SE_NUOVO" );
            Button_Update__25.setOperation( "Update" );
            AD4_STRINGHE.add( Button_Update__25 );

            com.codecharge.components.Button Button_Delete__26 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__26.addButtonListener(new AD4_STRINGHEButton_DeleteHandler());
            Button_Delete__26.addExcludeParam( "ccsForm" );
            Button_Delete__26.addExcludeParam( "Button_Delete" );
            Button_Delete__26.addExcludeParam( "SE_NUOVO" );
            Button_Delete__26.setOperation( "Delete" );
            AD4_STRINGHE.add( Button_Delete__26 );

            com.codecharge.components.Button Button_Cancel__28 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__28.addButtonListener(new AD4_STRINGHEButton_CancelHandler());
            Button_Cancel__28.addExcludeParam( "ccsForm" );
            Button_Cancel__28.addExcludeParam( "Button_Cancel" );
            Button_Cancel__28.addExcludeParam( "SE_NUOVO" );
            Button_Cancel__28.setOperation( "Cancel" );
            AD4_STRINGHE.add( Button_Cancel__28 );
            add(AD4_STRINGHE);
        } // End definition of AD4_STRINGHE record model.
//End AD4_STRINGHE record

//AD4_CHIAVI record @99-E2BC7B12
        
        /*
            Model of AD4_CHIAVI record defining.
        */
        {
            com.codecharge.components.Record AD4_CHIAVI = new com.codecharge.components.Record("AD4_CHIAVI");
            AD4_CHIAVI.setPageModel( this );
            AD4_CHIAVI.addExcludeParam( "ccsForm" );
            AD4_CHIAVI.setVisible( true );
            AD4_CHIAVI.setAllowInsert(false);
            AD4_CHIAVI.setPreserveType(PreserveParameterType.GET);
            AD4_CHIAVI.setReturnPage("AD4Stringa" + Names.ACTION_SUFFIX);
            AD4_CHIAVI.addRecordListener(new AD4_CHIAVIRecordHandler());

            com.codecharge.components.Label TITOLO__165 = new com.codecharge.components.Label("TITOLO", "TITOLO", this );
            TITOLO__165.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO__165.setHtmlEncode( true );
            AD4_CHIAVI.add(TITOLO__165);

            com.codecharge.components.Hidden PADRE__161 = new com.codecharge.components.Hidden("PADRE", "PADRE", this );
            PADRE__161.setType( com.codecharge.components.ControlType.TEXT );
            PADRE__161.setHtmlEncode( true );
            AD4_CHIAVI.add( PADRE__161 );

            com.codecharge.components.TextBox SUB_CHIAVE__157 = new com.codecharge.components.TextBox("SUB_CHIAVE", "SUB_CHIAVE", this );
            SUB_CHIAVE__157.setType( com.codecharge.components.ControlType.TEXT );
            SUB_CHIAVE__157.setHtmlEncode( true );
            AD4_CHIAVI.add( SUB_CHIAVE__157 );

            com.codecharge.components.Hidden CHIAVE__162 = new com.codecharge.components.Hidden("CHIAVE", "CHIAVE", this );
            CHIAVE__162.setType( com.codecharge.components.ControlType.TEXT );
            CHIAVE__162.setHtmlEncode( true );
            AD4_CHIAVI.add( CHIAVE__162 );

            com.codecharge.components.Hidden CHIAVE_OLD__101 = new com.codecharge.components.Hidden("CHIAVE_OLD", "CHIAVE_OLD", this );
            CHIAVE_OLD__101.setType( com.codecharge.components.ControlType.TEXT );
            CHIAVE_OLD__101.setHtmlEncode( true );
            AD4_CHIAVI.add( CHIAVE_OLD__101 );

            com.codecharge.components.Hidden TIPOR__156 = new com.codecharge.components.Hidden("TIPOR", "TIPOR", this );
            TIPOR__156.setType( com.codecharge.components.ControlType.TEXT );
            TIPOR__156.setHtmlEncode( true );
            AD4_CHIAVI.add( TIPOR__156 );

            com.codecharge.components.Button Button_Update__113 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__113.addButtonListener(new AD4_CHIAVIButton_UpdateHandler());
            Button_Update__113.addExcludeParam( "ccsForm" );
            Button_Update__113.addExcludeParam( "Button_Update" );
            Button_Update__113.addExcludeParam( "ID" );
            Button_Update__113.addExcludeParam( "SE_NUOVO" );
            Button_Update__113.setOperation( "Update" );
            AD4_CHIAVI.add( Button_Update__113 );

            com.codecharge.components.Button Button_Delete__115 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__115.addButtonListener(new AD4_CHIAVIButton_DeleteHandler());
            Button_Delete__115.addExcludeParam( "ccsForm" );
            Button_Delete__115.addExcludeParam( "Button_Delete" );
            Button_Delete__115.addExcludeParam( "ID" );
            Button_Delete__115.addExcludeParam( "SE_NUOVO" );
            Button_Delete__115.setOperation( "Delete" );
            AD4_CHIAVI.add( Button_Delete__115 );

            com.codecharge.components.Button Button_Cancel__118 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__118.addButtonListener(new AD4_CHIAVIButton_CancelHandler());
            Button_Cancel__118.addExcludeParam( "ccsForm" );
            Button_Cancel__118.addExcludeParam( "Button_Cancel" );
            Button_Cancel__118.addExcludeParam( "SE_NUOVO" );
            Button_Cancel__118.setOperation( "Cancel" );
            AD4_CHIAVI.add( Button_Cancel__118 );
            add(AD4_CHIAVI);
        } // End definition of AD4_CHIAVI record model.
//End AD4_CHIAVI record

//AD4StringaModel class tail @1-F5FC18C5
    }
}
//End AD4StringaModel class tail
