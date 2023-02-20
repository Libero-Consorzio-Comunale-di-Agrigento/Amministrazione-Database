//Ad4DizionariSportelloImpostaModel imports @1-5CE7AEF8
package restrict.Ad4DizionariSportelloImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariSportelloImpostaModel imports

//Ad4DizionariSportelloImpostaModel class head @1-F5B3F054
public class Ad4DizionariSportelloImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariSportelloImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariSportelloImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariSportelloImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariSportelloImpostaModel class head

//page settings @1-13F7D9F6
        super("Ad4DizionariSportelloImposta", locale );
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

//SportelloImposta record @5-35F1626E
        
        /*
            Model of SportelloImposta record defining.
        */
        {
            com.codecharge.components.Record SportelloImposta = new com.codecharge.components.Record("SportelloImposta");
            SportelloImposta.setPageModel( this );
            SportelloImposta.addExcludeParam( "ccsForm" );
            SportelloImposta.addExcludeParam( "MVPG" );
            SportelloImposta.addExcludeParam( "BANCA" );
            SportelloImposta.addExcludeParam( "SPORTELLO" );
            SportelloImposta.setVisible( true );
            SportelloImposta.setPreserveType(PreserveParameterType.GET);
            SportelloImposta.setReturnPage("Ad4DizionariSportelloImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label ABI_LABEL__6 = new com.codecharge.components.Label("ABI_LABEL", "ABI", this );
            ABI_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            ABI_LABEL__6.setHtmlEncode( true );
            SportelloImposta.add(ABI_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            SportelloImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.ListBox ABI__8 = new com.codecharge.components.ListBox("ABI", "ABI", this );
            ABI__8.setType( com.codecharge.components.ControlType.TEXT );
            ABI__8.setHtmlEncode( true );
            ABI__8.setCaption( "Banca" );
            ABI__8.setBoundColumn( "ABI" );
            ABI__8.setTextColumn( "DENOMINAZIONE" );
            ABI__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Banca è richiesto." ) );
            SportelloImposta.add( ABI__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            SportelloImposta.add(HIDE_END__9);

            com.codecharge.components.Label CAB_LABEL__43 = new com.codecharge.components.Label("CAB_LABEL", "CAB", this );
            CAB_LABEL__43.setType( com.codecharge.components.ControlType.TEXT );
            CAB_LABEL__43.setHtmlEncode( true );
            SportelloImposta.add(CAB_LABEL__43);

            com.codecharge.components.Label HIDE_BEGIN2__44 = new com.codecharge.components.Label("HIDE_BEGIN2", "HIDE_BEGIN", this );
            HIDE_BEGIN2__44.setType( com.codecharge.components.ControlType.TEXT );
            SportelloImposta.add(HIDE_BEGIN2__44);

            com.codecharge.components.TextBox CAB__45 = new com.codecharge.components.TextBox("CAB", "CAB", this );
            CAB__45.setType( com.codecharge.components.ControlType.TEXT );
            CAB__45.setHtmlEncode( true );
            CAB__45.setCaption( "Sportello" );
            CAB__45.addValidateHandler( new RequiredHandler( "Il valore nel campo Sportello è richiesto." ) );
            SportelloImposta.add( CAB__45 );

            com.codecharge.components.Label HIDE_END2__46 = new com.codecharge.components.Label("HIDE_END2", "HIDE_END", this );
            HIDE_END2__46.setType( com.codecharge.components.ControlType.TEXT );
            SportelloImposta.add(HIDE_END2__46);

            com.codecharge.components.TextBox CIN_CAB__62 = new com.codecharge.components.TextBox("CIN_CAB", "CIN_CAB", this );
            CIN_CAB__62.setType( com.codecharge.components.ControlType.TEXT );
            CIN_CAB__62.setHtmlEncode( true );
            CIN_CAB__62.setCaption( "Cin CAB" );
            SportelloImposta.add( CIN_CAB__62 );

            com.codecharge.components.TextBox DESCRIZIONE__10 = new com.codecharge.components.TextBox("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__10.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__10.setHtmlEncode( true );
            DESCRIZIONE__10.setCaption( "Descrizione" );
            SportelloImposta.add( DESCRIZIONE__10 );

            com.codecharge.components.Label TRADUZIONE__64 = new com.codecharge.components.Label("TRADUZIONE", "OPEN_TRADUZIONE", this );
            TRADUZIONE__64.setType( com.codecharge.components.ControlType.TEXT );
            SportelloImposta.add(TRADUZIONE__64);

            com.codecharge.components.TextBox INDIRIZZO__47 = new com.codecharge.components.TextBox("INDIRIZZO", "INDIRIZZO", this );
            INDIRIZZO__47.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO__47.setHtmlEncode( true );
            INDIRIZZO__47.setCaption( "Indirizzo" );
            SportelloImposta.add( INDIRIZZO__47 );

            com.codecharge.components.TextBox LOCALITA__48 = new com.codecharge.components.TextBox("LOCALITA", "LOCALITA", this );
            LOCALITA__48.setType( com.codecharge.components.ControlType.TEXT );
            LOCALITA__48.setHtmlEncode( true );
            LOCALITA__48.setCaption( "Localita'" );
            SportelloImposta.add( LOCALITA__48 );

            com.codecharge.components.ListBox COMUNE__52 = new com.codecharge.components.ListBox("COMUNE", "COMUNE", this );
            COMUNE__52.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE__52.setHtmlEncode( true );
            COMUNE__52.setCaption( "Comune" );
            COMUNE__52.setBoundColumn( "DENOMINAZIONE" );
            COMUNE__52.setTextColumn( "DENOMINAZIONE_V" );
            SportelloImposta.add( COMUNE__52 );

            com.codecharge.components.TextBox PROVINCIA__54 = new com.codecharge.components.TextBox("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__54.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA__54.setHtmlEncode( true );
            PROVINCIA__54.setCaption( "Provincia" );
            SportelloImposta.add( PROVINCIA__54 );

            com.codecharge.components.TextBox CAP__49 = new com.codecharge.components.TextBox("CAP", "CAP", this );
            CAP__49.setType( com.codecharge.components.ControlType.TEXT );
            CAP__49.setHtmlEncode( true );
            CAP__49.setCaption( "Cap" );
            SportelloImposta.add( CAP__49 );

            com.codecharge.components.TextBox DIPENDENZA__56 = new com.codecharge.components.TextBox("DIPENDENZA", "DIPENDENZA", this );
            DIPENDENZA__56.setType( com.codecharge.components.ControlType.TEXT );
            DIPENDENZA__56.setHtmlEncode( true );
            DIPENDENZA__56.setCaption( "Dipendenza" );
            SportelloImposta.add( DIPENDENZA__56 );

            com.codecharge.components.TextBox BIC__60 = new com.codecharge.components.TextBox("BIC", "BIC", this );
            BIC__60.setType( com.codecharge.components.ControlType.TEXT );
            BIC__60.setHtmlEncode( true );
            BIC__60.setCaption( "Bic" );
            SportelloImposta.add( BIC__60 );

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new SportelloImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            SportelloImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new SportelloImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            SportelloImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new SportelloImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            SportelloImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new SportelloImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            SportelloImposta.add( Button_Cancel__19 );
            add(SportelloImposta);
        } // End definition of SportelloImposta record model.
//End SportelloImposta record

//Ad4DizionariSportelloImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariSportelloImpostaModel class tail
