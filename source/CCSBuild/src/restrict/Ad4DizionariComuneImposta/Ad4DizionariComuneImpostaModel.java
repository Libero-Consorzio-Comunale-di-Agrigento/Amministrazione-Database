//Ad4DizionariComuneImpostaModel imports @1-CD66C873
package restrict.Ad4DizionariComuneImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariComuneImpostaModel imports

//Ad4DizionariComuneImpostaModel class head @1-13BD1F80
public class Ad4DizionariComuneImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariComuneImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariComuneImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariComuneImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariComuneImpostaModel class head

//page settings @1-43FF63A3
        super("Ad4DizionariComuneImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AttributoHeader grid @2-62F82BC4
        
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
            Indietro__3.addExcludeParam( "PROVINCIA_STATO" );
            Indietro__3.addExcludeParam( " COMUNE" );
            Indietro__3.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__3 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//ComuneImposta record @5-61269823
        
        /*
            Model of ComuneImposta record defining.
        */
        {
            com.codecharge.components.Record ComuneImposta = new com.codecharge.components.Record("ComuneImposta");
            ComuneImposta.setPageModel( this );
            ComuneImposta.addExcludeParam( "ccsForm" );
            ComuneImposta.addExcludeParam( "MVPG" );
            ComuneImposta.addExcludeParam( "PROVINCIA_STATO" );
            ComuneImposta.addExcludeParam( "COMUNE" );
            ComuneImposta.setVisible( true );
            ComuneImposta.setPreserveType(PreserveParameterType.GET);
            ComuneImposta.setReturnPage("Ad4DizionariComuneImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label PROVINCIA_LABEL__6 = new com.codecharge.components.Label("PROVINCIA_LABEL", "PROVINCIA_STATO", this );
            PROVINCIA_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA_LABEL__6.setHtmlEncode( true );
            ComuneImposta.add(PROVINCIA_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.ListBox PROVINCIA_STATO__8 = new com.codecharge.components.ListBox("PROVINCIA_STATO", "PROVINCIA_STATO", this );
            PROVINCIA_STATO__8.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA_STATO__8.setHtmlEncode( true );
            PROVINCIA_STATO__8.setCaption( "Provincia" );
            PROVINCIA_STATO__8.setBoundColumn( "PROVINCIA" );
            PROVINCIA_STATO__8.setTextColumn( "DENOMINAZIONE" );
            PROVINCIA_STATO__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Provincia è richiesto." ) );
            ComuneImposta.add( PROVINCIA_STATO__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(HIDE_END__9);

            com.codecharge.components.Label COMUNE_LABEL__43 = new com.codecharge.components.Label("COMUNE_LABEL", "COMUNE", this );
            COMUNE_LABEL__43.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE_LABEL__43.setHtmlEncode( true );
            ComuneImposta.add(COMUNE_LABEL__43);

            com.codecharge.components.Label HIDE_BEGIN2__44 = new com.codecharge.components.Label("HIDE_BEGIN2", "HIDE_BEGIN", this );
            HIDE_BEGIN2__44.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(HIDE_BEGIN2__44);

            com.codecharge.components.TextBox COMUNE__45 = new com.codecharge.components.TextBox("COMUNE", "COMUNE", this );
            COMUNE__45.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE__45.setHtmlEncode( true );
            COMUNE__45.setCaption( "Comune" );
            COMUNE__45.addValidateHandler( new RequiredHandler( "Il valore nel campo Comune è richiesto." ) );
            ComuneImposta.add( COMUNE__45 );

            com.codecharge.components.Label HIDE_END2__46 = new com.codecharge.components.Label("HIDE_END2", "HIDE_END", this );
            HIDE_END2__46.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(HIDE_END2__46);

            com.codecharge.components.TextBox DENOMINAZIONE__10 = new com.codecharge.components.TextBox("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__10.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__10.setHtmlEncode( true );
            DENOMINAZIONE__10.setCaption( "Denominazione" );
            DENOMINAZIONE__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione è richiesto." ) );
            ComuneImposta.add( DENOMINAZIONE__10 );

            com.codecharge.components.Label TRADUZIONE__63 = new com.codecharge.components.Label("TRADUZIONE", "OPEN_TRADUZIONE", this );
            TRADUZIONE__63.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(TRADUZIONE__63);

            com.codecharge.components.TextBox DENOMINAZIONE_BREVE__47 = new com.codecharge.components.TextBox("DENOMINAZIONE_BREVE", "DENOMINAZIONE_BREVE", this );
            DENOMINAZIONE_BREVE__47.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE_BREVE__47.setHtmlEncode( true );
            DENOMINAZIONE_BREVE__47.setCaption( "Denominazione Breve" );
            DENOMINAZIONE_BREVE__47.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione Breve è richiesto." ) );
            ComuneImposta.add( DENOMINAZIONE_BREVE__47 );

            com.codecharge.components.Label TRADUZIONE_BREVE__64 = new com.codecharge.components.Label("TRADUZIONE_BREVE", "OPEN_TRADUZIONE_BR", this );
            TRADUZIONE_BREVE__64.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(TRADUZIONE_BREVE__64);

            com.codecharge.components.CheckBox CAPOLUOGO_PROVINCIA__48=  new com.codecharge.components.CheckBox( "CAPOLUOGO_PROVINCIA", "CAPOLUOGO_PROVINCIA", this );
            CAPOLUOGO_PROVINCIA__48.setType( com.codecharge.components.ControlType.TEXT );
            CAPOLUOGO_PROVINCIA__48.setCheckedValue( "S" );
            ComuneImposta.add(CAPOLUOGO_PROVINCIA__48);

            com.codecharge.components.TextBox CAP__49 = new com.codecharge.components.TextBox("CAP", "CAP", this );
            CAP__49.setType( com.codecharge.components.ControlType.INTEGER );
            CAP__49.setHtmlEncode( true );
            ComuneImposta.add( CAP__49 );

            com.codecharge.components.TextBox TRIBUNALE_DESC__52 = new com.codecharge.components.TextBox("TRIBUNALE_DESC", "DENOMINAZIONE", this );
            TRIBUNALE_DESC__52.setType( com.codecharge.components.ControlType.TEXT );
            TRIBUNALE_DESC__52.setHtmlEncode( true );
            TRIBUNALE_DESC__52.setCaption( "Denominazione" );
            ComuneImposta.add( TRIBUNALE_DESC__52 );

            com.codecharge.components.Label TRIBUNALE_LOV__53 = new com.codecharge.components.Label("TRIBUNALE_LOV", "TRIBUNALE_LOV", this );
            TRIBUNALE_LOV__53.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(TRIBUNALE_LOV__53);

            com.codecharge.components.Hidden PROVINCIA_TRIBUNALE__50 = new com.codecharge.components.Hidden("PROVINCIA_TRIBUNALE", "PROVINCIA_TRIBUNALE", this );
            PROVINCIA_TRIBUNALE__50.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA_TRIBUNALE__50.setHtmlEncode( true );
            ComuneImposta.add( PROVINCIA_TRIBUNALE__50 );

            com.codecharge.components.Hidden COMUNE_TRIBUNALE__51 = new com.codecharge.components.Hidden("COMUNE_TRIBUNALE", "COMUNE_TRIBUNALE", this );
            COMUNE_TRIBUNALE__51.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE_TRIBUNALE__51.setHtmlEncode( true );
            ComuneImposta.add( COMUNE_TRIBUNALE__51 );

            com.codecharge.components.TextBox DATA_SOPPRESSIONE__54 = new com.codecharge.components.TextBox("DATA_SOPPRESSIONE", "DATA_SOPPRESSIONE", this );
            DATA_SOPPRESSIONE__54.setType( com.codecharge.components.ControlType.TEXT );
            DATA_SOPPRESSIONE__54.setHtmlEncode( true );
            DATA_SOPPRESSIONE__54.setCaption( "Data Soppressione" );
            ComuneImposta.add( DATA_SOPPRESSIONE__54 );
            com.codecharge.components.DatePicker DatePicker_DATA_SOPPRESSIONE__55 = new com.codecharge.components.DatePicker("DatePicker_DATA_SOPPRESSIONE", this);
            DatePicker_DATA_SOPPRESSIONE__55.setControlName("DATA_SOPPRESSIONE");
            DatePicker_DATA_SOPPRESSIONE__55.setStyleName("../Themes/AFC/Style.css");
            ComuneImposta.add(DatePicker_DATA_SOPPRESSIONE__55);

            com.codecharge.components.TextBox FUSIONE_DESC__56 = new com.codecharge.components.TextBox("FUSIONE_DESC", "DENOMINAZIONE", this );
            FUSIONE_DESC__56.setType( com.codecharge.components.ControlType.TEXT );
            FUSIONE_DESC__56.setHtmlEncode( true );
            FUSIONE_DESC__56.setCaption( "Denominazione" );
            ComuneImposta.add( FUSIONE_DESC__56 );

            com.codecharge.components.Label FUSIONE_LOV__57 = new com.codecharge.components.Label("FUSIONE_LOV", "FUSIONE_LOV", this );
            FUSIONE_LOV__57.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(FUSIONE_LOV__57);

            com.codecharge.components.Hidden PROVINCIA_FUSIONE__58 = new com.codecharge.components.Hidden("PROVINCIA_FUSIONE", "PROVINCIA_FUSIONE", this );
            PROVINCIA_FUSIONE__58.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA_FUSIONE__58.setHtmlEncode( true );
            ComuneImposta.add( PROVINCIA_FUSIONE__58 );

            com.codecharge.components.Hidden COMUNE_FUSIONE__59 = new com.codecharge.components.Hidden("COMUNE_FUSIONE", "COMUNE_FUSIONE", this );
            COMUNE_FUSIONE__59.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE_FUSIONE__59.setHtmlEncode( true );
            ComuneImposta.add( COMUNE_FUSIONE__59 );

            com.codecharge.components.TextBox SIGLA_CFIS__60 = new com.codecharge.components.TextBox("SIGLA_CFIS", "SIGLA_CFIS", this );
            SIGLA_CFIS__60.setType( com.codecharge.components.ControlType.TEXT );
            SIGLA_CFIS__60.setHtmlEncode( true );
            ComuneImposta.add( SIGLA_CFIS__60 );

            com.codecharge.components.TextBox DATA_ISTITUZIONE__65 = new com.codecharge.components.TextBox("DATA_ISTITUZIONE", "DATA_ISTITUZIONE", this );
            DATA_ISTITUZIONE__65.setType( com.codecharge.components.ControlType.TEXT );
            DATA_ISTITUZIONE__65.setHtmlEncode( true );
            DATA_ISTITUZIONE__65.setCaption( "Data Istituzione" );
            ComuneImposta.add( DATA_ISTITUZIONE__65 );
            com.codecharge.components.DatePicker DatePicker_DATA_ISTITUZIONE__66 = new com.codecharge.components.DatePicker("DatePicker_DATA_ISTITUZIONE", this);
            DatePicker_DATA_ISTITUZIONE__66.setControlName("DATA_ISTITUZIONE");
            DatePicker_DATA_ISTITUZIONE__66.setStyleName("../Themes/AFC/Style.css");
            ComuneImposta.add(DatePicker_DATA_ISTITUZIONE__66);

            com.codecharge.components.Label LABEL_UPD__67 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__67.setType( com.codecharge.components.ControlType.TEXT );
            ComuneImposta.add(LABEL_UPD__67);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new ComuneImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            ComuneImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new ComuneImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            ComuneImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new ComuneImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            ComuneImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new ComuneImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            ComuneImposta.add( Button_Cancel__19 );
            add(ComuneImposta);
        } // End definition of ComuneImposta record model.
//End ComuneImposta record

//Ad4DizionariComuneImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariComuneImpostaModel class tail

