//AD4RuoloModel imports @1-FBAA69A7
package ad4web.AD4Ruolo;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4RuoloModel imports

//AD4RuoloModel class head @1-333631E0
public class AD4RuoloModel extends com.codecharge.components.Page {
    public AD4RuoloModel() {
        this( new CCSLocale(), null );
    }

    public AD4RuoloModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4RuoloModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4RuoloModel class head

//page settings @1-DDD13129
        super("AD4Ruolo", locale );
        setResponse(response);
        addPageListener(new AD4RuoloPageHandler());
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

//AD4_RUOLI record @23-C417B44B
        
        /*
            Model of AD4_RUOLI record defining.
        */
        {
            com.codecharge.components.Record AD4_RUOLI = new com.codecharge.components.Record("AD4_RUOLI");
            AD4_RUOLI.setPageModel( this );
            AD4_RUOLI.addExcludeParam( "ccsForm" );
            AD4_RUOLI.setVisible( true );
            AD4_RUOLI.setAllowInsert(false);
            AD4_RUOLI.setPreserveType(PreserveParameterType.GET);
            AD4_RUOLI.setReturnPage("AD4Ruolo" + Names.ACTION_SUFFIX);
            AD4_RUOLI.addRecordListener(new AD4_RUOLIRecordHandler());

            com.codecharge.components.Label RUOLO__30 = new com.codecharge.components.Label("RUOLO", "RUOLO", this );
            RUOLO__30.setType( com.codecharge.components.ControlType.TEXT );
            AD4_RUOLI.add(RUOLO__30);

            com.codecharge.components.Hidden RUOLO_ORIG__41 = new com.codecharge.components.Hidden("RUOLO_ORIG", "RUOLO_ORIG", this );
            RUOLO_ORIG__41.setType( com.codecharge.components.ControlType.TEXT );
            RUOLO_ORIG__41.setHtmlEncode( true );
            RUOLO_ORIG__41.setCaption( "RUOLO_ORIG" );
            AD4_RUOLI.add( RUOLO_ORIG__41 );

            com.codecharge.components.TextBox DESCRIZIONE__31 = new com.codecharge.components.TextBox("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__31.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__31.setHtmlEncode( true );
            DESCRIZIONE__31.setCaption( "DESCRIZIONE" );
            DESCRIZIONE__31.addValidateHandler( new RequiredHandler( "Il valore nel campo DESCRIZIONE è richiesto." ) );
            AD4_RUOLI.add( DESCRIZIONE__31 );

            com.codecharge.components.ListBox PROGETTO__61 = new com.codecharge.components.ListBox("PROGETTO", "PROGETTO", this );
            PROGETTO__61.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO__61.setHtmlEncode( true );
            PROGETTO__61.setCaption( "PROGETTO" );
            PROGETTO__61.setBoundColumn( "PROGETTO" );
            PROGETTO__61.setTextColumn( "DESCRIZIONE" );
            AD4_RUOLI.add( PROGETTO__61 );

            com.codecharge.components.Hidden ISLISTBOX__75 = new com.codecharge.components.Hidden("ISLISTBOX", "", this );
            ISLISTBOX__75.setType( com.codecharge.components.ControlType.TEXT );
            ISLISTBOX__75.setHtmlEncode( true );
            AD4_RUOLI.add( ISLISTBOX__75 );

            com.codecharge.components.ListBox MODULO__33 = new com.codecharge.components.ListBox("MODULO", "MODULO", this );
            MODULO__33.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__33.setHtmlEncode( true );
            MODULO__33.setCaption( "MODULO" );
            MODULO__33.setBoundColumn( "MODULO" );
            MODULO__33.setTextColumn( "DESCRIZIONE" );
            AD4_RUOLI.add( MODULO__33 );

            com.codecharge.components.TextBox PROFILO__51 = new com.codecharge.components.TextBox("PROFILO", "PROFILO", this );
            PROFILO__51.setType( com.codecharge.components.ControlType.INTEGER );
            PROFILO__51.setHtmlEncode( true );
            AD4_RUOLI.add( PROFILO__51 );

            com.codecharge.components.CheckBox STATO__82=  new com.codecharge.components.CheckBox( "STATO", "STATO", this );
            STATO__82.setType( com.codecharge.components.ControlType.TEXT );
            STATO__82.setCheckedValue( "U" );
            STATO__82.setUncheckedValue( "R" );
            AD4_RUOLI.add(STATO__82);

            com.codecharge.components.CheckBox GRUPPO_LAVORO__83=  new com.codecharge.components.CheckBox( "GRUPPO_LAVORO", "GRUPPO_LAVORO", this );
            GRUPPO_LAVORO__83.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO_LAVORO__83.setCheckedValue( "S" );
            GRUPPO_LAVORO__83.setUncheckedValue( "N" );
            AD4_RUOLI.add(GRUPPO_LAVORO__83);

            com.codecharge.components.CheckBox GRUPPO_SO__84=  new com.codecharge.components.CheckBox( "GRUPPO_SO", "GRUPPO_SO", this );
            GRUPPO_SO__84.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO_SO__84.setCheckedValue( "S" );
            GRUPPO_SO__84.setUncheckedValue( "N" );
            AD4_RUOLI.add(GRUPPO_SO__84);

            com.codecharge.components.Button Refresh__73 = new com.codecharge.components.Button("Refresh", this);
            Refresh__73.addExcludeParam( "ccsForm" );
            Refresh__73.addExcludeParam( "Refresh" );
            Refresh__73.setOperation( "Search" );
            AD4_RUOLI.add( Refresh__73 );

            com.codecharge.components.Button Button_Update__25 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__25.addButtonListener(new AD4_RUOLIButton_UpdateHandler());
            Button_Update__25.addExcludeParam( "ccsForm" );
            Button_Update__25.addExcludeParam( "Button_Update" );
            Button_Update__25.addExcludeParam( "RUOLO" );
            Button_Update__25.addExcludeParam( "SE_NUOVO" );
            Button_Update__25.addExcludeParam( "DESCRIZIONE" );
            Button_Update__25.addExcludeParam( "PROGETTO" );
            Button_Update__25.addExcludeParam( "MODULO" );
            Button_Update__25.addExcludeParam( "PROFILO" );
            Button_Update__25.addExcludeParam( "RUOLO_ORIG" );
            Button_Update__25.setOperation( "Update" );
            AD4_RUOLI.add( Button_Update__25 );

            com.codecharge.components.Button Button_Delete__26 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__26.addButtonListener(new AD4_RUOLIButton_DeleteHandler());
            Button_Delete__26.addExcludeParam( "ccsForm" );
            Button_Delete__26.addExcludeParam( "Button_Delete" );
            Button_Delete__26.addExcludeParam( "RUOLO" );
            Button_Delete__26.addExcludeParam( "SE_NUOVO" );
            Button_Delete__26.addExcludeParam( "DESCRIZIONE" );
            Button_Delete__26.addExcludeParam( "PROGETTO" );
            Button_Delete__26.addExcludeParam( "MODULO" );
            Button_Delete__26.addExcludeParam( "PROFILO" );
            Button_Delete__26.addExcludeParam( "RUOLO_ORIG" );
            Button_Delete__26.setOperation( "Delete" );
            AD4_RUOLI.add( Button_Delete__26 );

            com.codecharge.components.Button Button_Cancel__28 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__28.addButtonListener(new AD4_RUOLIButton_CancelHandler());
            Button_Cancel__28.addExcludeParam( "ccsForm" );
            Button_Cancel__28.addExcludeParam( "Button_Cancel" );
            Button_Cancel__28.addExcludeParam( "RUOLO" );
            Button_Cancel__28.addExcludeParam( "SE_NUOVO" );
            Button_Cancel__28.addExcludeParam( "DESCRIZIONE" );
            Button_Cancel__28.addExcludeParam( "PROGETTO" );
            Button_Cancel__28.addExcludeParam( "MODULO" );
            Button_Cancel__28.addExcludeParam( "PROFILO" );
            Button_Cancel__28.addExcludeParam( "RUOLO_ORIG" );
            Button_Cancel__28.setOperation( "Cancel" );
            AD4_RUOLI.add( Button_Cancel__28 );
            add(AD4_RUOLI);
        } // End definition of AD4_RUOLI record model.
//End AD4_RUOLI record

//AD4RuoloModel class tail @1-F5FC18C5
    }
}
//End AD4RuoloModel class tail

