//Ad4DizionariRegioneImpostaModel imports @1-C46394F8
package ad4web.Ad4DizionariRegioneImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariRegioneImpostaModel imports

//Ad4DizionariRegioneImpostaModel class head @1-99449E1F
public class Ad4DizionariRegioneImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariRegioneImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariRegioneImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariRegioneImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariRegioneImpostaModel class head

//page settings @1-B6C6F7BC
        super("Ad4DizionariRegioneImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AttributoHeader grid @2-94B40647
        
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
            Indietro__3.addExcludeParam( "REGIONE" );
            Indietro__3.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__3 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//RegioneImposta record @5-FB302D46
        
        /*
            Model of RegioneImposta record defining.
        */
        {
            com.codecharge.components.Record RegioneImposta = new com.codecharge.components.Record("RegioneImposta");
            RegioneImposta.setPageModel( this );
            RegioneImposta.addExcludeParam( "ccsForm" );
            RegioneImposta.addExcludeParam( "MVPG" );
            RegioneImposta.addExcludeParam( "REGIONE" );
            RegioneImposta.setVisible( true );
            RegioneImposta.setPreserveType(PreserveParameterType.GET);
            RegioneImposta.setReturnPage("Ad4DizionariRegioneImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label REGIONE_LABEL__6 = new com.codecharge.components.Label("REGIONE_LABEL", "REGIONE", this );
            REGIONE_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE_LABEL__6.setHtmlEncode( true );
            RegioneImposta.add(REGIONE_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            RegioneImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.TextBox REGIONE__8 = new com.codecharge.components.TextBox("REGIONE", "REGIONE", this );
            REGIONE__8.setType( com.codecharge.components.ControlType.INTEGER );
            REGIONE__8.setHtmlEncode( true );
            REGIONE__8.setCaption( "Regione" );
            REGIONE__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Regione è richiesto." ) );
            RegioneImposta.add( REGIONE__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            RegioneImposta.add(HIDE_END__9);

            com.codecharge.components.TextBox DENOMINAZIONE__10 = new com.codecharge.components.TextBox("DENOMINAZIONE", "DENOMINAZIONE", this );
            DENOMINAZIONE__10.setType( com.codecharge.components.ControlType.TEXT );
            DENOMINAZIONE__10.setHtmlEncode( true );
            DENOMINAZIONE__10.setCaption( "Denominazione" );
            DENOMINAZIONE__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Denominazione è richiesto." ) );
            RegioneImposta.add( DENOMINAZIONE__10 );

            com.codecharge.components.TextBox ID_REGIONE__38 = new com.codecharge.components.TextBox("ID_REGIONE", "ID_REGIONE", this );
            ID_REGIONE__38.setType( com.codecharge.components.ControlType.INTEGER );
            ID_REGIONE__38.setHtmlEncode( true );
            ID_REGIONE__38.setCaption( "IdRegione" );
            ID_REGIONE__38.addValidateHandler( new RequiredHandler( "Il valore nel campo IdRegione è richiesto." ) );
            RegioneImposta.add( ID_REGIONE__38 );

            com.codecharge.components.Label LABEL_UPD__39 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__39.setType( com.codecharge.components.ControlType.TEXT );
            RegioneImposta.add(LABEL_UPD__39);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new RegioneImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            RegioneImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new RegioneImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            RegioneImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new RegioneImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            RegioneImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new RegioneImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            RegioneImposta.add( Button_Cancel__19 );
            add(RegioneImposta);
        } // End definition of RegioneImposta record model.
//End RegioneImposta record

//Ad4DizionariRegioneImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariRegioneImpostaModel class tail

