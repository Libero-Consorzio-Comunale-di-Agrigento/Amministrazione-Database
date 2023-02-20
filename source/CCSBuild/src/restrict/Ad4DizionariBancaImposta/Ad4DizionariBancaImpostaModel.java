//Ad4DizionariBancaImpostaModel imports @1-0D29D6FE
package restrict.Ad4DizionariBancaImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariBancaImpostaModel imports

//Ad4DizionariBancaImpostaModel class head @1-43F5B554
public class Ad4DizionariBancaImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariBancaImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariBancaImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariBancaImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariBancaImpostaModel class head

//page settings @1-1B995FA2
        super("Ad4DizionariBancaImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AttributoHeader grid @2-180F319F
        
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
            Indietro__3.addExcludeParam( "BANCA" );
            Indietro__3.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__3 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//BancaImposta record @5-E5E624E2
        
        /*
            Model of BancaImposta record defining.
        */
        {
            com.codecharge.components.Record BancaImposta = new com.codecharge.components.Record("BancaImposta");
            BancaImposta.setPageModel( this );
            BancaImposta.addExcludeParam( "ccsForm" );
            BancaImposta.addExcludeParam( "MVPG" );
            BancaImposta.addExcludeParam( "BANCA" );
            BancaImposta.setVisible( true );
            BancaImposta.setPreserveType(PreserveParameterType.GET);
            BancaImposta.setReturnPage("Ad4DizionariBancaImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label BANCA_LABEL__6 = new com.codecharge.components.Label("BANCA_LABEL", "BANCA", this );
            BANCA_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            BANCA_LABEL__6.setHtmlEncode( true );
            BancaImposta.add(BANCA_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            BancaImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.TextBox BANCA__8 = new com.codecharge.components.TextBox("BANCA", "BANCA", this );
            BANCA__8.setType( com.codecharge.components.ControlType.TEXT );
            BANCA__8.setHtmlEncode( true );
            BANCA__8.setCaption( "Regione" );
            BANCA__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Regione è richiesto." ) );
            BancaImposta.add( BANCA__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            BancaImposta.add(HIDE_END__9);

            com.codecharge.components.TextBox DENOMINAZIONE__10 = new com.codecharge.components.TextBox("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__10.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__10.setHtmlEncode( true );
            DENOMINAZIONE__10.setCaption( "Denominazione" );
            DENOMINAZIONE__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione è richiesto." ) );
            BancaImposta.add( DENOMINAZIONE__10 );

            com.codecharge.components.Label TRADUZIONE__40 = new com.codecharge.components.Label("TRADUZIONE", "OPEN_TRADUZIONE", this );
            TRADUZIONE__40.setType( com.codecharge.components.ControlType.TEXT );
            BancaImposta.add(TRADUZIONE__40);

            com.codecharge.components.TextBox CIN_ABI__38 = new com.codecharge.components.TextBox("CIN_ABI", "CIN_ABI", this );
            CIN_ABI__38.setType( com.codecharge.components.ControlType.TEXT );
            CIN_ABI__38.setHtmlEncode( true );
            CIN_ABI__38.setCaption( "CinAbi" );
            CIN_ABI__38.addValidateHandler( new RequiredHandler( "Il valore nel campo CinAbi è richiesto." ) );
            BancaImposta.add( CIN_ABI__38 );

            com.codecharge.components.Label LABEL_UPD__39 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__39.setType( com.codecharge.components.ControlType.TEXT );
            BancaImposta.add(LABEL_UPD__39);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new BancaImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            BancaImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new BancaImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            BancaImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new BancaImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            BancaImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new BancaImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            BancaImposta.add( Button_Cancel__19 );
            add(BancaImposta);
        } // End definition of BancaImposta record model.
//End BancaImposta record

//Ad4DizionariBancaImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariBancaImpostaModel class tail
