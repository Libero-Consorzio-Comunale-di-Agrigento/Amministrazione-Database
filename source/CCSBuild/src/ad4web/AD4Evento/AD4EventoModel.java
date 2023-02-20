//AD4EventoModel imports @1-C7851E31
package ad4web.AD4Evento;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4EventoModel imports

//AD4EventoModel class head @1-2D05B5D7
public class AD4EventoModel extends com.codecharge.components.Page {
    public AD4EventoModel() {
        this( new CCSLocale(), null );
    }

    public AD4EventoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4EventoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4EventoModel class head

//page settings @1-F8D07B23
        super("AD4Evento", locale );
        setResponse(response);
        addPageListener(new AD4EventoPageHandler());
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

//EVENTI record @56-743A24C5
        
        /*
            Model of EVENTI record defining.
        */
        {
            com.codecharge.components.Record EVENTI = new com.codecharge.components.Record("EVENTI");
            EVENTI.setPageModel( this );
            EVENTI.addExcludeParam( "ccsForm" );
            EVENTI.addExcludeParam( "MVVC" );
            EVENTI.setVisible( true );
            EVENTI.setAllowInsert(false);
            EVENTI.setPreserveType(PreserveParameterType.GET);
            EVENTI.setReturnPage("AD4Evento" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label ID_EVENTO__135 = new com.codecharge.components.Label("ID_EVENTO", "ID_EVENTO", this );
            ID_EVENTO__135.setType( com.codecharge.components.ControlType.TEXT );
            ID_EVENTO__135.setHtmlEncode( true );
            EVENTI.add(ID_EVENTO__135);

            com.codecharge.components.Label DATA__175 = new com.codecharge.components.Label("DATA", "DATA", this );
            DATA__175.setType( com.codecharge.components.ControlType.TEXT );
            DATA__175.setHtmlEncode( true );
            EVENTI.add(DATA__175);

            com.codecharge.components.Hidden ID_EVENTO_HIDDEN__181 = new com.codecharge.components.Hidden("ID_EVENTO_HIDDEN", "ID_EVENTO", this );
            ID_EVENTO_HIDDEN__181.setType( com.codecharge.components.ControlType.TEXT );
            ID_EVENTO_HIDDEN__181.setHtmlEncode( true );
            EVENTI.add( ID_EVENTO_HIDDEN__181 );

            com.codecharge.components.Label DESC_TIPO__176 = new com.codecharge.components.Label("DESC_TIPO", "DESC_TIPO", this );
            DESC_TIPO__176.setType( com.codecharge.components.ControlType.TEXT );
            DESC_TIPO__176.setHtmlEncode( true );
            EVENTI.add(DESC_TIPO__176);

            com.codecharge.components.Hidden TIPO__182 = new com.codecharge.components.Hidden("TIPO", "TIPO", this );
            TIPO__182.setType( com.codecharge.components.ControlType.TEXT );
            TIPO__182.setHtmlEncode( true );
            EVENTI.add( TIPO__182 );

            com.codecharge.components.Label TESTO_FILE__177 = new com.codecharge.components.Label("TESTO_FILE", "TESTO_FILE", this );
            TESTO_FILE__177.setType( com.codecharge.components.ControlType.TEXT );
            EVENTI.add(TESTO_FILE__177);

            com.codecharge.components.Hidden CHECK_FILE_LOCATOR__183 = new com.codecharge.components.Hidden("CHECK_FILE_LOCATOR", "CHECK_FILE_LOCATOR", this );
            CHECK_FILE_LOCATOR__183.setType( com.codecharge.components.ControlType.TEXT );
            CHECK_FILE_LOCATOR__183.setHtmlEncode( true );
            EVENTI.add( CHECK_FILE_LOCATOR__183 );

            com.codecharge.components.Label NOMINATIVO__104 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__104.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__104.setHtmlEncode( true );
            EVENTI.add(NOMINATIVO__104);

            com.codecharge.components.Hidden RIPRISTINATI__186 = new com.codecharge.components.Hidden("RIPRISTINATI", "RIPRISTINATI", this );
            RIPRISTINATI__186.setType( com.codecharge.components.ControlType.TEXT );
            RIPRISTINATI__186.setHtmlEncode( true );
            EVENTI.add( RIPRISTINATI__186 );

            com.codecharge.components.Label DB_USER__193 = new com.codecharge.components.Label("DB_USER", "DB_USER", this );
            DB_USER__193.setType( com.codecharge.components.ControlType.TEXT );
            DB_USER__193.setHtmlEncode( true );
            EVENTI.add(DB_USER__193);

            com.codecharge.components.Label ISTANZA_DESC__105 = new com.codecharge.components.Label("ISTANZA_DESC", "ISTANZA_DESC", this );
            ISTANZA_DESC__105.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA_DESC__105.setHtmlEncode( true );
            EVENTI.add(ISTANZA_DESC__105);

            com.codecharge.components.Label MODULO_DESC__106 = new com.codecharge.components.Label("MODULO_DESC", "MODULO_DESC", this );
            MODULO_DESC__106.setType( com.codecharge.components.ControlType.TEXT );
            MODULO_DESC__106.setHtmlEncode( true );
            EVENTI.add(MODULO_DESC__106);

            com.codecharge.components.Label DESC_GRAVITA__108 = new com.codecharge.components.Label("DESC_GRAVITA", "DESC_GRAVITA", this );
            DESC_GRAVITA__108.setType( com.codecharge.components.ControlType.TEXT );
            DESC_GRAVITA__108.setHtmlEncode( true );
            EVENTI.add(DESC_GRAVITA__108);

            com.codecharge.components.Label DESC_STATO__70 = new com.codecharge.components.Label("DESC_STATO", "DESC_STATO", this );
            DESC_STATO__70.setType( com.codecharge.components.ControlType.TEXT );
            DESC_STATO__70.setHtmlEncode( true );
            EVENTI.add(DESC_STATO__70);

            com.codecharge.components.Label ANNOTAZIONI__178 = new com.codecharge.components.Label("ANNOTAZIONI", "ANNOTAZIONI", this );
            ANNOTAZIONI__178.setType( com.codecharge.components.ControlType.TEXT );
            ANNOTAZIONI__178.setHtmlEncode( true );
            EVENTI.add(ANNOTAZIONI__178);

            com.codecharge.components.Button Button_Update__179 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__179.addExcludeParam( "ccsForm" );
            Button_Update__179.addExcludeParam( "Button_Update" );
            Button_Update__179.setOperation( "Update" );
            EVENTI.add( Button_Update__179 );

            com.codecharge.components.Button Button_Delete__58 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__58.addButtonListener(new EVENTIButton_DeleteHandler());
            Button_Delete__58.addExcludeParam( "ccsForm" );
            Button_Delete__58.addExcludeParam( "Button_Delete" );
            Button_Delete__58.setOperation( "Delete" );
            EVENTI.add( Button_Delete__58 );

            com.codecharge.components.Button Button_Cancel__60 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__60.addButtonListener(new EVENTIButton_CancelHandler());
            Button_Cancel__60.addExcludeParam( "ccsForm" );
            Button_Cancel__60.addExcludeParam( "Button_Cancel" );
            Button_Cancel__60.setOperation( "Cancel" );
            EVENTI.add( Button_Cancel__60 );
            add(EVENTI);
        } // End definition of EVENTI record model.
//End EVENTI record

//AD4EventoModel class tail @1-F5FC18C5
    }
}
//End AD4EventoModel class tail
