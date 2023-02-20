//AD4OmonimiaModel imports @1-4C49753A
package ad4web.AD4Omonimia;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4OmonimiaModel imports

//AD4OmonimiaModel class head @1-D2CA5201
public class AD4OmonimiaModel extends com.codecharge.components.Page {
    public AD4OmonimiaModel() {
        this( new CCSLocale(), null );
    }

    public AD4OmonimiaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4OmonimiaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4OmonimiaModel class head

//page settings @1-4AEFF4AA
        super("AD4Omonimia", locale );
        setResponse(response);
        addPageListener(new AD4OmonimiaPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__4 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__4.setVisible( true );
            add( Guida__4 );
            com.codecharge.components.IncludePage Footer__5 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__5.setVisible( true );
            add( Footer__5 );
        } // end page
//End page settings

//OMONIMIA record @6-12D18FC3
        
        /*
            Model of OMONIMIA record defining.
        */
        {
            com.codecharge.components.Record OMONIMIA = new com.codecharge.components.Record("OMONIMIA");
            OMONIMIA.setPageModel( this );
            OMONIMIA.addExcludeParam( "ccsForm" );
            OMONIMIA.setVisible( true );
            OMONIMIA.setAllowInsert(false);
            OMONIMIA.setAllowDelete(false);
            OMONIMIA.setPreserveType(PreserveParameterType.GET);
            OMONIMIA.setReturnPage("AD4Omonimia" + Names.ACTION_SUFFIX);
            OMONIMIA.addRecordListener(new OMONIMIARecordHandler());

            com.codecharge.components.Label UTENTE__78 = new com.codecharge.components.Label("UTENTE", "UTENTE", this );
            UTENTE__78.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__78.setHtmlEncode( true );
            OMONIMIA.add(UTENTE__78);

            com.codecharge.components.Label NOMINATIVO__122 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__122.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__122.setHtmlEncode( true );
            OMONIMIA.add(NOMINATIVO__122);

            com.codecharge.components.Hidden ID_OMONIMIA__127 = new com.codecharge.components.Hidden("ID_OMONIMIA", "ID_OMONIMIA", this );
            ID_OMONIMIA__127.setType( com.codecharge.components.ControlType.INTEGER );
            ID_OMONIMIA__127.setHtmlEncode( true );
            OMONIMIA.add( ID_OMONIMIA__127 );

            com.codecharge.components.Hidden SCELTO_PRIMARIO__128 = new com.codecharge.components.Hidden("SCELTO_PRIMARIO", "SCELTO_PRIMARIO", this );
            SCELTO_PRIMARIO__128.setType( com.codecharge.components.ControlType.INTEGER );
            SCELTO_PRIMARIO__128.setHtmlEncode( true );
            OMONIMIA.add( SCELTO_PRIMARIO__128 );

            com.codecharge.components.Label DATI_SOGGETTO__125 = new com.codecharge.components.Label("DATI_SOGGETTO", "DATI_SOGGETTO", this );
            DATI_SOGGETTO__125.setType( com.codecharge.components.ControlType.TEXT );
            OMONIMIA.add(DATI_SOGGETTO__125);

            com.codecharge.components.Hidden UNIFICATO__129 = new com.codecharge.components.Hidden("UNIFICATO", "UNIFICATO", this );
            UNIFICATO__129.setType( com.codecharge.components.ControlType.INTEGER );
            UNIFICATO__129.setHtmlEncode( true );
            OMONIMIA.add( UNIFICATO__129 );

            com.codecharge.components.Hidden COPIATO__130 = new com.codecharge.components.Hidden("COPIATO", "COPIATO", this );
            COPIATO__130.setType( com.codecharge.components.ControlType.INTEGER );
            COPIATO__130.setHtmlEncode( true );
            OMONIMIA.add( COPIATO__130 );

            com.codecharge.components.Hidden P_UTENTE__131 = new com.codecharge.components.Hidden("P_UTENTE", "UTENTE", this );
            P_UTENTE__131.setType( com.codecharge.components.ControlType.TEXT );
            P_UTENTE__131.setHtmlEncode( true );
            OMONIMIA.add( P_UTENTE__131 );

            com.codecharge.components.Label SOSIA__119 = new com.codecharge.components.Label("SOSIA", "SOSIA", this );
            SOSIA__119.setType( com.codecharge.components.ControlType.TEXT );
            SOSIA__119.setHtmlEncode( true );
            OMONIMIA.add(SOSIA__119);

            com.codecharge.components.Label SOSIA_NOMINATIVO__123 = new com.codecharge.components.Label("SOSIA_NOMINATIVO", "SOSIA_NOMINATIVO", this );
            SOSIA_NOMINATIVO__123.setType( com.codecharge.components.ControlType.TEXT );
            SOSIA_NOMINATIVO__123.setHtmlEncode( true );
            OMONIMIA.add(SOSIA_NOMINATIVO__123);

            com.codecharge.components.Label DATI_SOGGETTO_SOSIA__126 = new com.codecharge.components.Label("DATI_SOGGETTO_SOSIA", "DATI_SOGGETTO_SOSIA", this );
            DATI_SOGGETTO_SOSIA__126.setType( com.codecharge.components.ControlType.TEXT );
            OMONIMIA.add(DATI_SOGGETTO_SOSIA__126);

            com.codecharge.components.Hidden P_SOSIA__132 = new com.codecharge.components.Hidden("P_SOSIA", "SOSIA", this );
            P_SOSIA__132.setType( com.codecharge.components.ControlType.TEXT );
            P_SOSIA__132.setHtmlEncode( true );
            OMONIMIA.add( P_SOSIA__132 );

            com.codecharge.components.CheckBox s_primario__133=  new com.codecharge.components.CheckBox( "s_primario", this );
            s_primario__133.setType( com.codecharge.components.ControlType.INTEGER );
            s_primario__133.setCheckedValue( 1 );
            s_primario__133.setUncheckedValue( 0 );
            OMONIMIA.add(s_primario__133);

            com.codecharge.components.CheckBox s_ignorare__124=  new com.codecharge.components.CheckBox( "s_ignorare", "IGNORARE", this );
            s_ignorare__124.setType( com.codecharge.components.ControlType.INTEGER );
            s_ignorare__124.setCheckedValue( 1 );
            s_ignorare__124.setUncheckedValue( 0 );
            OMONIMIA.add(s_ignorare__124);

            com.codecharge.components.TextArea NOTE__22 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__22.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__22.setHtmlEncode( true );
            NOTE__22.addControlListener( new OMONIMIANOTEHandler());
            NOTE__22.setCaption( "NOTE" );
            OMONIMIA.add( NOTE__22 );

            com.codecharge.components.Button Button_Update__8 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__8.addExcludeParam( "ccsForm" );
            Button_Update__8.addExcludeParam( "Button_Update" );
            Button_Update__8.addExcludeParam( "SE_NUOVO" );
            Button_Update__8.addExcludeParam( "MVVC" );
            Button_Update__8.setOperation( "Update" );
            OMONIMIA.add( Button_Update__8 );

            com.codecharge.components.Button Button_Cancel__11 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__11.addButtonListener(new OMONIMIAButton_CancelHandler());
            Button_Cancel__11.addExcludeParam( "ccsForm" );
            Button_Cancel__11.addExcludeParam( "Button_Cancel" );
            Button_Cancel__11.setOperation( "Cancel" );
            OMONIMIA.add( Button_Cancel__11 );
            add(OMONIMIA);
        } // End definition of OMONIMIA record model.
//End OMONIMIA record

//AD4OmonimiaModel class tail @1-F5FC18C5
    }
}
//End AD4OmonimiaModel class tail
