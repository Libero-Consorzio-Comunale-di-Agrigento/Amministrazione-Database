//Ad4DizionariProvinciaImpostaModel imports @1-A3E94342
package ad4web.Ad4DizionariProvinciaImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariProvinciaImpostaModel imports

//Ad4DizionariProvinciaImpostaModel class head @1-0554AA23
public class Ad4DizionariProvinciaImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariProvinciaImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariProvinciaImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariProvinciaImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariProvinciaImpostaModel class head

//page settings @1-68A2590F
        super("Ad4DizionariProvinciaImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AttributoHeader grid @2-5A9FC853
        
        /*
            // Begin definition of AttributoHeader grid model.
        */
        {
            com.codecharge.components.Grid AttributoHeader = new com.codecharge.components.Grid("AttributoHeader");
            AttributoHeader.setPageModel( this );
            AttributoHeader.setFetchSize(20);
            AttributoHeader.setVisible( true );
            AttributoHeader.addGridListener( new AttributoHeaderGridHandler() );

            com.codecharge.components.ImageLink Indietro__3 = new com.codecharge.components.ImageLink("Indietro", this);
            Indietro__3.setType( com.codecharge.components.ControlType.TEXT );
            Indietro__3.setHrefType( "Page" );
            Indietro__3.setConvertRule("Relative");
            Indietro__3.setPreserveType(PreserveParameterType.GET);
            Indietro__3.addExcludeParam( "PROVINCIA" );
            Indietro__3.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__3 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//ProvinciaImposta record @5-CB3A6BA0
        
        /*
            Model of ProvinciaImposta record defining.
        */
        {
            com.codecharge.components.Record ProvinciaImposta = new com.codecharge.components.Record("ProvinciaImposta");
            ProvinciaImposta.setPageModel( this );
            ProvinciaImposta.addExcludeParam( "ccsForm" );
            ProvinciaImposta.addExcludeParam( "MVPG" );
            ProvinciaImposta.addExcludeParam( "PROVINCIA" );
            ProvinciaImposta.setVisible( true );
            ProvinciaImposta.setPreserveType(PreserveParameterType.GET);
            ProvinciaImposta.setReturnPage("Ad4DizionariProvinciaImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label PROVINCIA_LABEL__6 = new com.codecharge.components.Label("PROVINCIA_LABEL", "PROVINCIA", this );
            PROVINCIA_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA_LABEL__6.setHtmlEncode( true );
            ProvinciaImposta.add(PROVINCIA_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            ProvinciaImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.TextBox PROVINCIA__8 = new com.codecharge.components.TextBox("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__8.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA__8.setHtmlEncode( true );
            PROVINCIA__8.setCaption( "Provincia" );
            PROVINCIA__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Provincia è richiesto." ) );
            ProvinciaImposta.add( PROVINCIA__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            ProvinciaImposta.add(HIDE_END__9);

            com.codecharge.components.TextBox DENOMINAZIONE__10 = new com.codecharge.components.TextBox("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__10.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__10.setHtmlEncode( true );
            DENOMINAZIONE__10.setCaption( "Denominazione" );
            DENOMINAZIONE__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione è richiesto." ) );
            ProvinciaImposta.add( DENOMINAZIONE__10 );

            com.codecharge.components.TextBox SIGLA__41 = new com.codecharge.components.TextBox("SIGLA", "SIGLA", this );
            SIGLA__41.setType( com.codecharge.components.ControlType.TEXT );
            SIGLA__41.setHtmlEncode( true );
            SIGLA__41.setCaption( "Denominazione" );
            SIGLA__41.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione è richiesto." ) );
            ProvinciaImposta.add( SIGLA__41 );

            com.codecharge.components.ListBox REGIONE__40 = new com.codecharge.components.ListBox("REGIONE", "REGIONE", this );
            REGIONE__40.setType( com.codecharge.components.ControlType.INTEGER );
            REGIONE__40.setHtmlEncode( true );
            REGIONE__40.setBoundColumn( "REGIONE" );
            REGIONE__40.setTextColumn( "DENOMINAZIONE" );
            REGIONE__40.addValidateHandler( new RequiredHandler( "Il valore nel campo REGIONE è richiesto." ) );
            ProvinciaImposta.add( REGIONE__40 );

            com.codecharge.components.Label LABEL_UPD__39 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__39.setType( com.codecharge.components.ControlType.TEXT );
            ProvinciaImposta.add(LABEL_UPD__39);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new ProvinciaImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            ProvinciaImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new ProvinciaImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            ProvinciaImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new ProvinciaImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            ProvinciaImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new ProvinciaImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            ProvinciaImposta.add( Button_Cancel__19 );
            add(ProvinciaImposta);
        } // End definition of ProvinciaImposta record model.
//End ProvinciaImposta record

//Ad4DizionariProvinciaImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariProvinciaImpostaModel class tail

