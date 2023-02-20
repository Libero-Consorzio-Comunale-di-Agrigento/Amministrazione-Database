//AD4ProgettoModel imports @1-B82263A2
package ad4web.AD4Progetto;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4ProgettoModel imports

//AD4ProgettoModel class head @1-9CAA065B
public class AD4ProgettoModel extends com.codecharge.components.Page {
    public AD4ProgettoModel() {
        this( new CCSLocale(), null );
    }

    public AD4ProgettoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4ProgettoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4ProgettoModel class head

//page settings @1-8BEEF629
        super("AD4Progetto", locale );
        setResponse(response);
        addPageListener(new AD4ProgettoPageHandler());
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

//AD4_PROGETTI record @23-41647711
        
        /*
            Model of AD4_PROGETTI record defining.
        */
        {
            com.codecharge.components.Record AD4_PROGETTI = new com.codecharge.components.Record("AD4_PROGETTI");
            AD4_PROGETTI.setPageModel( this );
            AD4_PROGETTI.addExcludeParam( "ccsForm" );
            AD4_PROGETTI.addExcludeParam( "MVVC" );
            AD4_PROGETTI.setVisible( true );
            AD4_PROGETTI.setAllowInsert(false);
            AD4_PROGETTI.setPreserveType(PreserveParameterType.GET);
            AD4_PROGETTI.setReturnPage("AD4Progetto" + Names.ACTION_SUFFIX);
            AD4_PROGETTI.addRecordListener(new AD4_PROGETTIRecordHandler());

            com.codecharge.components.ImageLink Caratteristiche__49 = new com.codecharge.components.ImageLink("Caratteristiche", "", this );
            Caratteristiche__49.setType( com.codecharge.components.ControlType.TEXT );
            Caratteristiche__49.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            Caratteristiche__49.setHrefType( "Page" );
            Caratteristiche__49.setConvertRule("Relative");
            Caratteristiche__49.setPreserveType(PreserveParameterType.GET);
            Caratteristiche__49.addParameter( new LinkParameter( "PROGETTO", "PROGETTO_ORIG", ParameterSource.DATAFIELD) );
            Caratteristiche__49.addParameter( new LinkParameter( "TIPO_ACCESSO", "", ParameterSource.EXPRESSION) );
            AD4_PROGETTI.add( Caratteristiche__49 );

            com.codecharge.components.Label PROGETTO__30 = new com.codecharge.components.Label("PROGETTO", "PROGETTO", this );
            PROGETTO__30.setType( com.codecharge.components.ControlType.TEXT );
            AD4_PROGETTI.add(PROGETTO__30);

            com.codecharge.components.Hidden PROGETTO_ORIG__41 = new com.codecharge.components.Hidden("PROGETTO_ORIG", "PROGETTO_ORIG", this );
            PROGETTO_ORIG__41.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO_ORIG__41.setHtmlEncode( true );
            PROGETTO_ORIG__41.setCaption( "PROGETTO_ORIG" );
            AD4_PROGETTI.add( PROGETTO_ORIG__41 );

            com.codecharge.components.TextBox DESCRIZIONE__31 = new com.codecharge.components.TextBox("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__31.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__31.setHtmlEncode( true );
            DESCRIZIONE__31.setCaption( "DESCRIZIONE" );
            DESCRIZIONE__31.addValidateHandler( new RequiredHandler( "Il valore nel campo DESCRIZIONE è richiesto." ) );
            AD4_PROGETTI.add( DESCRIZIONE__31 );

            com.codecharge.components.TextBox PRIORITA__32 = new com.codecharge.components.TextBox("PRIORITA", "PRIORITA", this );
            PRIORITA__32.setType( com.codecharge.components.ControlType.FLOAT );
            PRIORITA__32.setHtmlEncode( true );
            PRIORITA__32.setCaption( "PRIORITA" );
            AD4_PROGETTI.add( PRIORITA__32 );

            com.codecharge.components.TextArea NOTE__33 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__33.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__33.setHtmlEncode( true );
            NOTE__33.addControlListener( new AD4_PROGETTINOTEHandler());
            NOTE__33.setCaption( "NOTE" );
            AD4_PROGETTI.add( NOTE__33 );

            com.codecharge.components.Button Button_Update__25 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__25.addButtonListener(new AD4_PROGETTIButton_UpdateHandler());
            Button_Update__25.addExcludeParam( "ccsForm" );
            Button_Update__25.addExcludeParam( "Button_Update" );
            Button_Update__25.addExcludeParam( "PROGETTO" );
            Button_Update__25.addExcludeParam( "SE_NUOVO" );
            Button_Update__25.setOperation( "Update" );
            AD4_PROGETTI.add( Button_Update__25 );

            com.codecharge.components.Button Button_Delete__26 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__26.addButtonListener(new AD4_PROGETTIButton_DeleteHandler());
            Button_Delete__26.addExcludeParam( "ccsForm" );
            Button_Delete__26.addExcludeParam( "Button_Delete" );
            Button_Delete__26.addExcludeParam( "PROGETTO" );
            Button_Delete__26.setOperation( "Delete" );
            AD4_PROGETTI.add( Button_Delete__26 );

            com.codecharge.components.Button Button_Cancel__28 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__28.addButtonListener(new AD4_PROGETTIButton_CancelHandler());
            Button_Cancel__28.addExcludeParam( "ccsForm" );
            Button_Cancel__28.addExcludeParam( "Button_Cancel" );
            Button_Cancel__28.addExcludeParam( "PROGETTO" );
            Button_Cancel__28.setOperation( "Cancel" );
            AD4_PROGETTI.add( Button_Cancel__28 );
            add(AD4_PROGETTI);
        } // End definition of AD4_PROGETTI record model.
//End AD4_PROGETTI record

//AD4ProgettoModel class tail @1-F5FC18C5
    }
}
//End AD4ProgettoModel class tail

