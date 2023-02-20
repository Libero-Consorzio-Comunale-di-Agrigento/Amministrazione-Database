//Ad4DizionariBancaTraduzioneModel imports @1-8A9BE35F
package restrict.Ad4DizionariBancaTraduzione;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariBancaTraduzioneModel imports

//Ad4DizionariBancaTraduzioneModel class head @1-22347976
public class Ad4DizionariBancaTraduzioneModel extends com.codecharge.components.Page {
    public Ad4DizionariBancaTraduzioneModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariBancaTraduzioneModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariBancaTraduzioneModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariBancaTraduzioneModel class head

//page settings @1-E7E0134F
        super("Ad4DizionariBancaTraduzione", locale );
        setResponse(response);
        {
        } // end page
//End page settings

//ComuneImposta record @5-CFA9D8CB
        
        /*
            Model of ComuneImposta record defining.
        */
        {
            com.codecharge.components.Record ComuneImposta = new com.codecharge.components.Record("ComuneImposta");
            ComuneImposta.setPageModel( this );
            ComuneImposta.addExcludeParam( "ccsForm" );
            ComuneImposta.addExcludeParam( "MVPG" );
            ComuneImposta.setVisible( true );
            ComuneImposta.setPreserveType(PreserveParameterType.GET);
            ComuneImposta.setReturnPage("Ad4DizionariBancaTraduzione" + Names.ACTION_SUFFIX);

            com.codecharge.components.Hidden ABI_LABEL__43 = new com.codecharge.components.Hidden("ABI_LABEL", "ABI", this );
            ABI_LABEL__43.setType( com.codecharge.components.ControlType.TEXT );
            ABI_LABEL__43.setHtmlEncode( true );
            ComuneImposta.add( ABI_LABEL__43 );

            com.codecharge.components.Label DENOMINAZIONE__44 = new com.codecharge.components.Label("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__44.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__44.setHtmlEncode( true );
            ComuneImposta.add(DENOMINAZIONE__44);

            com.codecharge.components.TextBox DENOMINAZIONE_ALT__10 = new com.codecharge.components.TextBox("DENOMINAZIONE_ALT", "DENOMINAZIONE_ALT", this );
            DENOMINAZIONE_ALT__10.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE_ALT__10.setHtmlEncode( true );
            DENOMINAZIONE_ALT__10.setCaption( "Denominazione" );
            DENOMINAZIONE_ALT__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione è richiesto." ) );
            ComuneImposta.add( DENOMINAZIONE_ALT__10 );

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
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            ComuneImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            ComuneImposta.add( Button_Cancel__19 );
            add(ComuneImposta);
        } // End definition of ComuneImposta record model.
//End ComuneImposta record

//Ad4DizionariBancaTraduzioneModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariBancaTraduzioneModel class tail
