//Ad4DizionariZonaAslImpostaModel imports @1-91316773
package restrict.Ad4DizionariZonaAslImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariZonaAslImpostaModel imports

//Ad4DizionariZonaAslImpostaModel class head @1-0C228D6C
public class Ad4DizionariZonaAslImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariZonaAslImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariZonaAslImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariZonaAslImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariZonaAslImpostaModel class head

//page settings @1-F75BB6F6
        super("Ad4DizionariZonaAslImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariZoneAslComuniElenco__45 = new com.codecharge.components.IncludePage("Ad4DizionariZoneAslComuniElenco", this );
            Ad4DizionariZoneAslComuniElenco__45.setVisible( true );
            add( Ad4DizionariZoneAslComuniElenco__45 );
        } // end page
//End page settings

//AttributoHeader grid @2-02E54297
        
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
            Indietro__3.addExcludeParam( "ID_ZONA_ASL" );
            Indietro__3.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__3 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//ZonaAslImposta record @5-27CA12DF
        
        /*
            Model of ZonaAslImposta record defining.
        */
        {
            com.codecharge.components.Record ZonaAslImposta = new com.codecharge.components.Record("ZonaAslImposta");
            ZonaAslImposta.setPageModel( this );
            ZonaAslImposta.addExcludeParam( "ccsForm" );
            ZonaAslImposta.addExcludeParam( "MVPG" );
            ZonaAslImposta.addExcludeParam( "ID_ZONA_ASL" );
            ZonaAslImposta.setVisible( true );
            ZonaAslImposta.setPreserveType(PreserveParameterType.GET);
            ZonaAslImposta.setReturnPage("Ad4DizionariZonaAslImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label ID_ZONA_ASL_LABEL__6 = new com.codecharge.components.Label("ID_ZONA_ASL_LABEL", "ID_ZONA_ASL", this );
            ID_ZONA_ASL_LABEL__6.setType( com.codecharge.components.ControlType.TEXT );
            ID_ZONA_ASL_LABEL__6.setHtmlEncode( true );
            ZonaAslImposta.add(ID_ZONA_ASL_LABEL__6);

            com.codecharge.components.Label HIDE_BEGIN__7 = new com.codecharge.components.Label("HIDE_BEGIN", "HIDE_BEGIN", this );
            HIDE_BEGIN__7.setType( com.codecharge.components.ControlType.TEXT );
            ZonaAslImposta.add(HIDE_BEGIN__7);

            com.codecharge.components.TextBox ID_ZONA_ASL__8 = new com.codecharge.components.TextBox("ID_ZONA_ASL", "ID_ZONA_ASL", this );
            ID_ZONA_ASL__8.setType( com.codecharge.components.ControlType.INTEGER );
            ID_ZONA_ASL__8.setHtmlEncode( true );
            ID_ZONA_ASL__8.setCaption( "Id Zona Asl" );
            ID_ZONA_ASL__8.addValidateHandler( new RequiredHandler( "Il valore nel campo Id Zona Asl è richiesto." ) );
            ZonaAslImposta.add( ID_ZONA_ASL__8 );

            com.codecharge.components.Label HIDE_END__9 = new com.codecharge.components.Label("HIDE_END", "HIDE_END", this );
            HIDE_END__9.setType( com.codecharge.components.ControlType.TEXT );
            ZonaAslImposta.add(HIDE_END__9);

            com.codecharge.components.ListBox CODICE_REGIONE__40 = new com.codecharge.components.ListBox("CODICE_REGIONE", "CODICE_REGIONE", this );
            CODICE_REGIONE__40.setType( com.codecharge.components.ControlType.INTEGER );
            CODICE_REGIONE__40.setHtmlEncode( true );
            CODICE_REGIONE__40.setBoundColumn( "REGIONE" );
            CODICE_REGIONE__40.setTextColumn( "DENOMINAZIONE" );
            CODICE_REGIONE__40.addValidateHandler( new RequiredHandler( "Il valore nel campo CODICE_REGIONE è richiesto." ) );
            ZonaAslImposta.add( CODICE_REGIONE__40 );

            com.codecharge.components.TextBox CODICE_ZONA__43 = new com.codecharge.components.TextBox("CODICE_ZONA", "CODICE_ZONA", this );
            CODICE_ZONA__43.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_ZONA__43.setHtmlEncode( true );
            CODICE_ZONA__43.setCaption( "Codice Zona" );
            CODICE_ZONA__43.addValidateHandler( new RequiredHandler( "Il valore nel campo Codice Zona è richiesto." ) );
            ZonaAslImposta.add( CODICE_ZONA__43 );

            com.codecharge.components.TextBox TITOLO__10 = new com.codecharge.components.TextBox("TITOLO", "TITOLO", this );
            TITOLO__10.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO__10.setHtmlEncode( true );
            TITOLO__10.setCaption( "Titolo" );
            TITOLO__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Titolo è richiesto." ) );
            ZonaAslImposta.add( TITOLO__10 );

            com.codecharge.components.TextBox VAL_DISTRETTO_LEA__41 = new com.codecharge.components.TextBox("VAL_DISTRETTO_LEA", "VAL_DISTRETTO_LEA", this );
            VAL_DISTRETTO_LEA__41.setType( com.codecharge.components.ControlType.TEXT );
            VAL_DISTRETTO_LEA__41.setHtmlEncode( true );
            VAL_DISTRETTO_LEA__41.setCaption( "Val Distretto Lea" );
            VAL_DISTRETTO_LEA__41.addValidateHandler( new RequiredHandler( "Il valore nel campo Val Distretto Lea è richiesto." ) );
            ZonaAslImposta.add( VAL_DISTRETTO_LEA__41 );

            com.codecharge.components.Label LABEL_UPD__42 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__42.setType( com.codecharge.components.ControlType.TEXT );
            ZonaAslImposta.add(LABEL_UPD__42);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new ZonaAslImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            ZonaAslImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new ZonaAslImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            ZonaAslImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new ZonaAslImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            ZonaAslImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new ZonaAslImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            ZonaAslImposta.add( Button_Cancel__19 );
            add(ZonaAslImposta);
        } // End definition of ZonaAslImposta record model.
//End ZonaAslImposta record

//Ad4DizionariZonaAslImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariZonaAslImpostaModel class tail

