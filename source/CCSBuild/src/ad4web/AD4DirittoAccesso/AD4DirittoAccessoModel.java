//AD4DirittoAccessoModel imports @1-11A8A871
package ad4web.AD4DirittoAccesso;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4DirittoAccessoModel imports

//AD4DirittoAccessoModel class head @1-A326D81C
public class AD4DirittoAccessoModel extends com.codecharge.components.Page {
    public AD4DirittoAccessoModel() {
        this( new CCSLocale(), null );
    }

    public AD4DirittoAccessoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4DirittoAccessoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4DirittoAccessoModel class head

//page settings @1-385CD309
        super("AD4DirittoAccesso", locale );
        setResponse(response);
        addPageListener(new AD4DirittoAccessoPageHandler());
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

            com.codecharge.components.Label JS_REFRESH_SLAVES__165 = new com.codecharge.components.Label("JS_REFRESH_SLAVES", "REFRESH_SLAVES", this );
            JS_REFRESH_SLAVES__165.setType( com.codecharge.components.ControlType.TEXT );
            JS_REFRESH_SLAVES__165.addControlListener( new AD4DirittoAccessoJS_REFRESH_SLAVESHandler());
            add( JS_REFRESH_SLAVES__165 );
        } // end page
//End page settings

//AD4_DIRITTI_ACCESSO1 record @38-030A2187
        
        /*
            Model of AD4_DIRITTI_ACCESSO1 record defining.
        */
        {
            com.codecharge.components.Record AD4_DIRITTI_ACCESSO1 = new com.codecharge.components.Record("AD4_DIRITTI_ACCESSO1");
            AD4_DIRITTI_ACCESSO1.setPageModel( this );
            AD4_DIRITTI_ACCESSO1.addExcludeParam( "ccsForm" );
            AD4_DIRITTI_ACCESSO1.setVisible( true );
            AD4_DIRITTI_ACCESSO1.setAllowInsert(false);
            AD4_DIRITTI_ACCESSO1.setPreserveType(PreserveParameterType.GET);
            AD4_DIRITTI_ACCESSO1.setReturnPage("AD4DirittoAccesso" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label NOMINATIVO__138 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__138.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__138.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO1.add(NOMINATIVO__138);

            com.codecharge.components.ImageLink CaratteristicheAccesso__139 = new com.codecharge.components.ImageLink("CaratteristicheAccesso", "", this );
            CaratteristicheAccesso__139.setType( com.codecharge.components.ControlType.TEXT );
            CaratteristicheAccesso__139.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            CaratteristicheAccesso__139.setHrefType( "Page" );
            CaratteristicheAccesso__139.setConvertRule("Relative");
            CaratteristicheAccesso__139.setPreserveType(PreserveParameterType.NONE);
            CaratteristicheAccesso__139.addParameter( new LinkParameter( "TIPO_ACCESSO", "TIPO_ACCESSO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__139.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__139.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__139.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__139.addParameter( new LinkParameter( "UTENTE", "UTENTE", ParameterSource.DATAFIELD) );
            AD4_DIRITTI_ACCESSO1.add( CaratteristicheAccesso__139 );

            com.codecharge.components.TextBox SEQUENZA__49 = new com.codecharge.components.TextBox("SEQUENZA", "SEQUENZA", this );
            SEQUENZA__49.setType( com.codecharge.components.ControlType.INTEGER );
            SEQUENZA__49.setHtmlEncode( true );
            SEQUENZA__49.setCaption( "SEQUENZA" );
            AD4_DIRITTI_ACCESSO1.add( SEQUENZA__49 );

            com.codecharge.components.Hidden UTENTE__149 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__149.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__149.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO1.add( UTENTE__149 );

            com.codecharge.components.Hidden ISLISTBOX__153 = new com.codecharge.components.Hidden("ISLISTBOX", "", this );
            ISLISTBOX__153.setType( com.codecharge.components.ControlType.TEXT );
            ISLISTBOX__153.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO1.add( ISLISTBOX__153 );

            com.codecharge.components.ListBox MODULO__46 = new com.codecharge.components.ListBox("MODULO", "MODULO", this );
            MODULO__46.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__46.setHtmlEncode( true );
            MODULO__46.setCaption( "MODULO" );
            MODULO__46.setBoundColumn( "MODULO" );
            MODULO__46.setTextColumn( "DESCRIZIONE" );
            AD4_DIRITTI_ACCESSO1.add( MODULO__46 );

            com.codecharge.components.Hidden MODULO_ORIG__83 = new com.codecharge.components.Hidden("MODULO_ORIG", "MODULO_ORIG", this );
            MODULO_ORIG__83.setType( com.codecharge.components.ControlType.TEXT );
            MODULO_ORIG__83.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO1.add( MODULO_ORIG__83 );

            com.codecharge.components.ListBox ISTANZA__47 = new com.codecharge.components.ListBox("ISTANZA", "ISTANZA", this );
            ISTANZA__47.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA__47.setHtmlEncode( true );
            ISTANZA__47.setCaption( "ISTANZA" );
            ISTANZA__47.setBoundColumn( "ISTANZA" );
            ISTANZA__47.setTextColumn( "DESCRIZIONE" );
            AD4_DIRITTI_ACCESSO1.add( ISTANZA__47 );

            com.codecharge.components.Hidden ISTANZA_ORIG__84 = new com.codecharge.components.Hidden("ISTANZA_ORIG", "ISTANZA_ORIG", this );
            ISTANZA_ORIG__84.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA_ORIG__84.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO1.add( ISTANZA_ORIG__84 );

            com.codecharge.components.ListBox RUOLO__48 = new com.codecharge.components.ListBox("RUOLO", "RUOLO", this );
            RUOLO__48.setType( com.codecharge.components.ControlType.TEXT );
            RUOLO__48.setHtmlEncode( true );
            RUOLO__48.setCaption( "RUOLO" );
            RUOLO__48.setBoundColumn( "RUOLO" );
            RUOLO__48.setTextColumn( "DESCRIZIONE" );
            AD4_DIRITTI_ACCESSO1.add( RUOLO__48 );

            com.codecharge.components.TextArea NOTE__53 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__53.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__53.setHtmlEncode( true );
            NOTE__53.addControlListener( new AD4_DIRITTI_ACCESSO1NOTEHandler());
            NOTE__53.setCaption( "NOTE" );
            AD4_DIRITTI_ACCESSO1.add( NOTE__53 );

            com.codecharge.components.Label ULTIMO_ACCESSO__50 = new com.codecharge.components.Label("ULTIMO_ACCESSO", "ULTIMO_ACCESSO", this );
            ULTIMO_ACCESSO__50.setType( com.codecharge.components.ControlType.DATE );
            ULTIMO_ACCESSO__50.setHtmlEncode( true );
            ULTIMO_ACCESSO__50.setFormatPattern( "dd/MM/yyyy" );
            AD4_DIRITTI_ACCESSO1.add(ULTIMO_ACCESSO__50);

            com.codecharge.components.Label NUMERO_ACCESSI__51 = new com.codecharge.components.Label("NUMERO_ACCESSI", "NUMERO_ACCESSI", this );
            NUMERO_ACCESSI__51.setType( com.codecharge.components.ControlType.INTEGER );
            NUMERO_ACCESSI__51.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO1.add(NUMERO_ACCESSI__51);

            com.codecharge.components.Label GRUPPO__52 = new com.codecharge.components.Label("GRUPPO", "GRUPPO", this );
            GRUPPO__52.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO__52.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO1.add(GRUPPO__52);

            com.codecharge.components.Button Refresh__152 = new com.codecharge.components.Button("Refresh", this);
            Refresh__152.addExcludeParam( "ccsForm" );
            Refresh__152.addExcludeParam( "Refresh" );
            Refresh__152.setOperation( "Search" );
            AD4_DIRITTI_ACCESSO1.add( Refresh__152 );

            com.codecharge.components.Button Button_Update__40 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__40.addExcludeParam( "ccsForm" );
            Button_Update__40.addExcludeParam( "Button_Update" );
            Button_Update__40.addExcludeParam( "MVID" );
            Button_Update__40.addExcludeParam( "MODULO" );
            Button_Update__40.addExcludeParam( "ISTANZA" );
            Button_Update__40.addExcludeParam( "ISLISTBOX" );
            Button_Update__40.setOperation( "Update" );
            AD4_DIRITTI_ACCESSO1.add( Button_Update__40 );

            com.codecharge.components.Button Button_Delete__41 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__41.addExcludeParam( "ccsForm" );
            Button_Delete__41.addExcludeParam( "Button_Delete" );
            Button_Delete__41.addExcludeParam( "MVID" );
            Button_Delete__41.addExcludeParam( "MODULO_LISTBOX" );
            Button_Delete__41.addExcludeParam( "ISTANZA_LISTBOX" );
            Button_Delete__41.setOperation( "Delete" );
            AD4_DIRITTI_ACCESSO1.add( Button_Delete__41 );

            com.codecharge.components.Button Button_Cancel__43 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__43.addButtonListener(new AD4_DIRITTI_ACCESSO1Button_CancelHandler());
            Button_Cancel__43.addExcludeParam( "ccsForm" );
            Button_Cancel__43.addExcludeParam( "Button_Cancel" );
            Button_Cancel__43.addExcludeParam( "MVID" );
            Button_Cancel__43.addExcludeParam( "MODULO_LISTBOX" );
            Button_Cancel__43.addExcludeParam( "ISTANZA_LISTBOX" );
            Button_Cancel__43.setOperation( "Cancel" );
            AD4_DIRITTI_ACCESSO1.add( Button_Cancel__43 );
            add(AD4_DIRITTI_ACCESSO1);
        } // End definition of AD4_DIRITTI_ACCESSO1 record model.
//End AD4_DIRITTI_ACCESSO1 record

//AD4DirittoAccessoModel class tail @1-F5FC18C5
    }
}
//End AD4DirittoAccessoModel class tail
