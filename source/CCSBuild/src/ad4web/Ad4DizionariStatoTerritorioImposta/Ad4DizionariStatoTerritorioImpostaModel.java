//Ad4DizionariStatoTerritorioImpostaModel imports @1-B5622A73
package ad4web.Ad4DizionariStatoTerritorioImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariStatoTerritorioImpostaModel imports

//Ad4DizionariStatoTerritorioImpostaModel class head @1-04598A4D
public class Ad4DizionariStatoTerritorioImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariStatoTerritorioImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariStatoTerritorioImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariStatoTerritorioImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariStatoTerritorioImpostaModel class head

//page settings @1-6BAC5FD6
        super("Ad4DizionariStatoTerritorioImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AttributoHeader grid @2-D54B9062
        
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
            Indietro__3.addExcludeParam( "STATO_TERRITORIO" );
            Indietro__3.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__3 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//StatoTerritorioImposta record @5-83C44FBA
        
        /*
            Model of StatoTerritorioImposta record defining.
        */
        {
            com.codecharge.components.Record StatoTerritorioImposta = new com.codecharge.components.Record("StatoTerritorioImposta");
            StatoTerritorioImposta.setPageModel( this );
            StatoTerritorioImposta.addExcludeParam( "ccsForm" );
            StatoTerritorioImposta.addExcludeParam( "MVPG" );
            StatoTerritorioImposta.addExcludeParam( "STATO_TERRITORIO" );
            StatoTerritorioImposta.setVisible( true );
            StatoTerritorioImposta.setPreserveType(PreserveParameterType.GET);
            StatoTerritorioImposta.setReturnPage("Ad4DizionariStatoTerritorioImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label STATO_TERRITORIO_LABEL__6 = new com.codecharge.components.Label("STATO_TERRITORIO_LABEL", "STATO_TERRITORIO", this );
            STATO_TERRITORIO_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            STATO_TERRITORIO_LABEL__6.setHtmlEncode( true );
            StatoTerritorioImposta.add(STATO_TERRITORIO_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            StatoTerritorioImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.TextBox STATO_TERRITORIO__8 = new com.codecharge.components.TextBox("STATO_TERRITORIO", "STATO_TERRITORIO", this );
            STATO_TERRITORIO__8.setType( com.codecharge.components.ControlType.INTEGER );
            STATO_TERRITORIO__8.setHtmlEncode( true );
            STATO_TERRITORIO__8.setCaption( "Stato Territorio" );
            STATO_TERRITORIO__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Stato Territorio è richiesto." ) );
            StatoTerritorioImposta.add( STATO_TERRITORIO__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            StatoTerritorioImposta.add(HIDE_END__9);

            com.codecharge.components.TextBox DENOMINAZIONE__10 = new com.codecharge.components.TextBox("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__10.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__10.setHtmlEncode( true );
            DENOMINAZIONE__10.setCaption( "Denominazione" );
            DENOMINAZIONE__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione è richiesto." ) );
            StatoTerritorioImposta.add( DENOMINAZIONE__10 );

            com.codecharge.components.TextBox SIGLA__44 = new com.codecharge.components.TextBox("SIGLA", "SIGLA", this );
            SIGLA__44.setType( com.codecharge.components.ControlType.TEXT );
            SIGLA__44.setHtmlEncode( true );
            SIGLA__44.setCaption( "Sigla" );
            StatoTerritorioImposta.add( SIGLA__44 );

            com.codecharge.components.TextBox DESC_CITTADINANZA__43 = new com.codecharge.components.TextBox("DESC_CITTADINANZA", "DESC_CITTADINANZA", this );
            DESC_CITTADINANZA__43.setType( com.codecharge.components.ControlType.TEXT );
            DESC_CITTADINANZA__43.setHtmlEncode( true );
            DESC_CITTADINANZA__43.setCaption( "Cittadinanza" );
            StatoTerritorioImposta.add( DESC_CITTADINANZA__43 );

            com.codecharge.components.TextBox RAGGRUPPAMENTO__45 = new com.codecharge.components.TextBox("RAGGRUPPAMENTO", "RAGGRUPPAMENTO", this );
            RAGGRUPPAMENTO__45.setType( com.codecharge.components.ControlType.INTEGER );
            RAGGRUPPAMENTO__45.setHtmlEncode( true );
            RAGGRUPPAMENTO__45.setCaption( "Raggruppamento" );
            StatoTerritorioImposta.add( RAGGRUPPAMENTO__45 );

            com.codecharge.components.TextBox STATO_APPARTENENZA__41 = new com.codecharge.components.TextBox("STATO_APPARTENENZA", "STATO_APPARTENENZA", this );
            STATO_APPARTENENZA__41.setType( com.codecharge.components.ControlType.INTEGER );
            STATO_APPARTENENZA__41.setHtmlEncode( true );
            STATO_APPARTENENZA__41.setCaption( "Stato Appartenenza" );
            StatoTerritorioImposta.add( STATO_APPARTENENZA__41 );

            com.codecharge.components.Label LABEL_UPD__42 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__42.setType( com.codecharge.components.ControlType.TEXT );
            StatoTerritorioImposta.add(LABEL_UPD__42);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new StatoTerritorioImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            StatoTerritorioImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new StatoTerritorioImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            StatoTerritorioImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new StatoTerritorioImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            StatoTerritorioImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new StatoTerritorioImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            StatoTerritorioImposta.add( Button_Cancel__19 );
            add(StatoTerritorioImposta);
        } // End definition of StatoTerritorioImposta record model.
//End StatoTerritorioImposta record

//Ad4DizionariStatoTerritorioImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariStatoTerritorioImpostaModel class tail

