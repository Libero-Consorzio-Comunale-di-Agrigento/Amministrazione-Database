//Ad4DizionariAslImpostaModel imports @1-E5C1BC06
package ad4web.Ad4DizionariAslImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariAslImpostaModel imports

//Ad4DizionariAslImpostaModel class head @1-D8FD3C7E
public class Ad4DizionariAslImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariAslImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariAslImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariAslImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariAslImpostaModel class head

//page settings @1-FB196FF6
        super("Ad4DizionariAslImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariAslComuniElenco__47 = new com.codecharge.components.IncludePage("Ad4DizionariAslComuniElenco", this );
            Ad4DizionariAslComuniElenco__47.setVisible( true );
            add( Ad4DizionariAslComuniElenco__47 );
        } // end page
//End page settings

//AttributoHeader grid @44-5BBB6494
        
        /*
            // Begin definition of AttributoHeader grid model.
        */
        {
            com.codecharge.components.Grid AttributoHeader = new com.codecharge.components.Grid("AttributoHeader");
            AttributoHeader.setPageModel( this );
            AttributoHeader.setFetchSize(20);
            AttributoHeader.setVisible( true );
            AttributoHeader.addGridListener( new AttributoHeaderGridHandler() );

            com.codecharge.components.ImageLink Indietro__45 = new com.codecharge.components.ImageLink("Indietro", this);
            Indietro__45.setType( com.codecharge.components.ControlType.TEXT );
            Indietro__45.setHrefType( "Page" );
            Indietro__45.setConvertRule("Relative");
            Indietro__45.setPreserveType(PreserveParameterType.GET);
            Indietro__45.addExcludeParam( "REGIONE_ASL" );
            Indietro__45.addExcludeParam( "CODICE_ASL" );
            Indietro__45.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__45 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//AslImposta record @5-276E7FBC
        
        /*
            Model of AslImposta record defining.
        */
        {
            com.codecharge.components.Record AslImposta = new com.codecharge.components.Record("AslImposta");
            AslImposta.setPageModel( this );
            AslImposta.addExcludeParam( "ccsForm" );
            AslImposta.addExcludeParam( "MVPG" );
            AslImposta.addExcludeParam( "REGIONE_ASL" );
            AslImposta.addExcludeParam( "CODICE_ASL" );
            AslImposta.setVisible( true );
            AslImposta.setPreserveType(PreserveParameterType.GET);
            AslImposta.setReturnPage("Ad4DizionariAslImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox REGIONE_ASL__40 = new com.codecharge.components.ListBox("REGIONE_ASL", "REGIONE_ASL", this );
            REGIONE_ASL__40.setType( com.codecharge.components.ControlType.INTEGER );
            REGIONE_ASL__40.setHtmlEncode( true );
            REGIONE_ASL__40.setBoundColumn( "REGIONE" );
            REGIONE_ASL__40.setTextColumn( "DENOMINAZIONE" );
            REGIONE_ASL__40.addValidateHandler( new RequiredHandler( "Il valore nel campo REGIONE_ASL è richiesto." ) );
            AslImposta.add( REGIONE_ASL__40 );

            com.codecharge.components.Label CODICE_ASL_LABEL__6 = new com.codecharge.components.Label("CODICE_ASL_LABEL", "CODICE_ASL", this );
            CODICE_ASL_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_ASL_LABEL__6.setHtmlEncode( true );
            AslImposta.add(CODICE_ASL_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            AslImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.TextBox CODICE_ASL__8 = new com.codecharge.components.TextBox("CODICE_ASL", "CODICE_ASL", this );
            CODICE_ASL__8.setType( com.codecharge.components.ControlType.INTEGER );
            CODICE_ASL__8.setHtmlEncode( true );
            CODICE_ASL__8.setCaption( "Codice" );
            CODICE_ASL__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Codice è richiesto." ) );
            AslImposta.add( CODICE_ASL__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            AslImposta.add(HIDE_END__9);

            com.codecharge.components.TextBox DESCRIZIONE__10 = new com.codecharge.components.TextBox("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__10.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__10.setHtmlEncode( true );
            DESCRIZIONE__10.setCaption( "Descrizione" );
            DESCRIZIONE__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Descrizione è richiesto." ) );
            AslImposta.add( DESCRIZIONE__10 );

            com.codecharge.components.CheckBox ATTIVA__41=  new com.codecharge.components.CheckBox( "ATTIVA", "ATTIVA", this );
            ATTIVA__41.setType( com.codecharge.components.ControlType.TEXT );
            ATTIVA__41.setCheckedValue( "S" );
            AslImposta.add(ATTIVA__41);

            com.codecharge.components.Label LABEL_UPD__39 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__39.setType( com.codecharge.components.ControlType.TEXT );
            AslImposta.add(LABEL_UPD__39);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new AslImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            AslImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new AslImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            AslImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new AslImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            AslImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new AslImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            AslImposta.add( Button_Cancel__19 );
            add(AslImposta);
        } // End definition of AslImposta record model.
//End AslImposta record

//Ad4DizionariAslImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariAslImpostaModel class tail

