//AD4ModuloModel imports @1-94FB9143
package ad4web.AD4Modulo;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4ModuloModel imports

//AD4ModuloModel class head @1-091A6E81
public class AD4ModuloModel extends com.codecharge.components.Page {
    public AD4ModuloModel() {
        this( new CCSLocale(), null );
    }

    public AD4ModuloModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4ModuloModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4ModuloModel class head

//page settings @1-F6BEB9F3
        super("AD4Modulo", locale );
        setResponse(response);
        addPageListener(new AD4ModuloPageHandler());
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

//AD4_MODULI record @23-23C124CA
        
        /*
            Model of AD4_MODULI record defining.
        */
        {
            com.codecharge.components.Record AD4_MODULI = new com.codecharge.components.Record("AD4_MODULI");
            AD4_MODULI.setPageModel( this );
            AD4_MODULI.addExcludeParam( "ccsForm" );
            AD4_MODULI.setVisible( true );
            AD4_MODULI.setAllowInsert(false);
            AD4_MODULI.setPreserveType(PreserveParameterType.GET);
            AD4_MODULI.setReturnPage("AD4Modulo" + Names.ACTION_SUFFIX);
            AD4_MODULI.addRecordListener(new AD4_MODULIRecordHandler());

            com.codecharge.components.Label PROGETTO_DESC__66 = new com.codecharge.components.Label("PROGETTO_DESC", "PROGETTO_DESC", this );
            PROGETTO_DESC__66.setType( com.codecharge.components.ControlType.TEXT );
            AD4_MODULI.add(PROGETTO_DESC__66);

            com.codecharge.components.Link CARATTERISTICHE__67 = new com.codecharge.components.Link("CARATTERISTICHE", "CARATTERISTICHE", this );
            CARATTERISTICHE__67.setType( com.codecharge.components.ControlType.TEXT );
            CARATTERISTICHE__67.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            CARATTERISTICHE__67.setHrefType( "Page" );
            CARATTERISTICHE__67.setConvertRule("Relative");
            CARATTERISTICHE__67.setPreserveType(PreserveParameterType.NONE);
            CARATTERISTICHE__67.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            CARATTERISTICHE__67.addParameter( new LinkParameter( "TIPO_ACCESSO", "", ParameterSource.EXPRESSION) );
            CARATTERISTICHE__67.addParameter( new LinkParameter( "MODULO", "MODULO_ORIG", ParameterSource.DATAFIELD) );
            AD4_MODULI.add( CARATTERISTICHE__67 );

            com.codecharge.components.Link ABILITAZIONI__64 = new com.codecharge.components.Link("ABILITAZIONI", "ABILITAZIONI", this );
            ABILITAZIONI__64.setType( com.codecharge.components.ControlType.TEXT );
            ABILITAZIONI__64.setHrefSourceValue( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX );
            ABILITAZIONI__64.setHrefType( "Page" );
            ABILITAZIONI__64.setConvertRule("Relative");
            ABILITAZIONI__64.setPreserveType(PreserveParameterType.GET);
            ABILITAZIONI__64.addExcludeParam( "MVVC" );
            ABILITAZIONI__64.addParameter( new LinkParameter( "MODULO", "MODULO_ORIG", ParameterSource.DATAFIELD) );
            AD4_MODULI.add( ABILITAZIONI__64 );

            com.codecharge.components.Label MODULO__30 = new com.codecharge.components.Label("MODULO", "MODULO", this );
            MODULO__30.setType( com.codecharge.components.ControlType.TEXT );
            AD4_MODULI.add(MODULO__30);

            com.codecharge.components.Hidden MODULO_ORIG__45 = new com.codecharge.components.Hidden("MODULO_ORIG", "MODULO_ORIG", this );
            MODULO_ORIG__45.setType( com.codecharge.components.ControlType.TEXT );
            MODULO_ORIG__45.setHtmlEncode( true );
            AD4_MODULI.add( MODULO_ORIG__45 );

            com.codecharge.components.Hidden PROGETTO_ORIG__71 = new com.codecharge.components.Hidden("PROGETTO_ORIG", "PROGETTO", this );
            PROGETTO_ORIG__71.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO_ORIG__71.setHtmlEncode( true );
            AD4_MODULI.add( PROGETTO_ORIG__71 );

            com.codecharge.components.TextBox DESCRIZIONE__31 = new com.codecharge.components.TextBox("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__31.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__31.setHtmlEncode( true );
            DESCRIZIONE__31.setCaption( "DESCRIZIONE" );
            DESCRIZIONE__31.addValidateHandler( new RequiredHandler( "Il valore nel campo DESCRIZIONE è richiesto." ) );
            AD4_MODULI.add( DESCRIZIONE__31 );

            com.codecharge.components.CheckBox AMMINISTRATORE__84=  new com.codecharge.components.CheckBox( "AMMINISTRATORE", "AMMINISTRATORE", this );
            AMMINISTRATORE__84.setType( com.codecharge.components.ControlType.TEXT );
            AMMINISTRATORE__84.setCheckedValue( "S" );
            AMMINISTRATORE__84.setUncheckedValue( "N" );
            AD4_MODULI.add(AMMINISTRATORE__84);

            com.codecharge.components.TextArea NOTE__33 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__33.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__33.setHtmlEncode( true );
            NOTE__33.addControlListener( new AD4_MODULINOTEHandler());
            NOTE__33.setCaption( "NOTE" );
            AD4_MODULI.add( NOTE__33 );

            com.codecharge.components.Button Button_Update__25 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__25.addButtonListener(new AD4_MODULIButton_UpdateHandler());
            Button_Update__25.addExcludeParam( "ccsForm" );
            Button_Update__25.addExcludeParam( "Button_Update" );
            Button_Update__25.setOperation( "Update" );
            AD4_MODULI.add( Button_Update__25 );

            com.codecharge.components.Button Button_Delete__26 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__26.addButtonListener(new AD4_MODULIButton_DeleteHandler());
            Button_Delete__26.addExcludeParam( "ccsForm" );
            Button_Delete__26.addExcludeParam( "Button_Delete" );
            Button_Delete__26.setOperation( "Delete" );
            AD4_MODULI.add( Button_Delete__26 );

            com.codecharge.components.Button Button_Cancel__28 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__28.addButtonListener(new AD4_MODULIButton_CancelHandler());
            Button_Cancel__28.addExcludeParam( "ccsForm" );
            Button_Cancel__28.addExcludeParam( "Button_Cancel" );
            Button_Cancel__28.setOperation( "Cancel" );
            AD4_MODULI.add( Button_Cancel__28 );
            add(AD4_MODULI);
        } // End definition of AD4_MODULI record model.
//End AD4_MODULI record

//AD4ModuloModel class tail @1-F5FC18C5
    }
}
//End AD4ModuloModel class tail
