//Ad4DizionariZonaAslComuneImpostaModel imports @1-0DF5C40A
package ad4web.Ad4DizionariZonaAslComuneImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariZonaAslComuneImpostaModel imports

//Ad4DizionariZonaAslComuneImpostaModel class head @1-F2E0749B
public class Ad4DizionariZonaAslComuneImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariZonaAslComuneImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariZonaAslComuneImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariZonaAslComuneImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariZonaAslComuneImpostaModel class head

//page settings @1-B79C6934
        super("Ad4DizionariZonaAslComuneImposta", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AttributoHeader grid @44-CDEB6EAE
        
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
            Indietro__45.addExcludeParam( "PROVINCIA" );
            Indietro__45.addExcludeParam( "COMUNE" );
            Indietro__45.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AttributoHeader.add( Indietro__45 );
            add(AttributoHeader);
        } // End definition of AttributoHeader grid model
//End AttributoHeader grid

//ZonaAslComuneImposta record @5-D891FC4D
        
        /*
            Model of ZonaAslComuneImposta record defining.
        */
        {
            com.codecharge.components.Record ZonaAslComuneImposta = new com.codecharge.components.Record("ZonaAslComuneImposta");
            ZonaAslComuneImposta.setPageModel( this );
            ZonaAslComuneImposta.addExcludeParam( "ccsForm" );
            ZonaAslComuneImposta.addExcludeParam( "MVPG" );
            ZonaAslComuneImposta.addExcludeParam( "PROVINCIA" );
            ZonaAslComuneImposta.addExcludeParam( "COMUNE" );
            ZonaAslComuneImposta.setVisible( true );
            ZonaAslComuneImposta.setAllowUpdate(false);
            ZonaAslComuneImposta.setPreserveType(PreserveParameterType.GET);
            ZonaAslComuneImposta.setReturnPage("Ad4DizionariZonaAslComuneImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label TITOLO__6 = new com.codecharge.components.Label("TITOLO", "TITOLO", this );
            TITOLO__6.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO__6.setHtmlEncode( true );
            ZonaAslComuneImposta.add(TITOLO__6);

            com.codecharge.components.Hidden ID_ZONA_ASL__52 = new com.codecharge.components.Hidden("ID_ZONA_ASL", "ID_ZONA_ASL", this );
            ID_ZONA_ASL__52.setType( com.codecharge.components.ControlType.INTEGER );
            ID_ZONA_ASL__52.setHtmlEncode( true );
            ZonaAslComuneImposta.add( ID_ZONA_ASL__52 );

            com.codecharge.components.TextBox COMUNE_DESC__10 = new com.codecharge.components.TextBox("COMUNE_DESC", "COMUNE_DESC", this );
            COMUNE_DESC__10.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE_DESC__10.setHtmlEncode( true );
            COMUNE_DESC__10.setCaption( "Descrizione" );
            COMUNE_DESC__10.addValidateHandler( new RequiredHandler( "Il valore nel campo Descrizione è richiesto." ) );
            ZonaAslComuneImposta.add( COMUNE_DESC__10 );

            com.codecharge.components.Label COMUNE_LOV__53 = new com.codecharge.components.Label("COMUNE_LOV", "COMUNE_LOV", this );
            COMUNE_LOV__53.setType( com.codecharge.components.ControlType.TEXT );
            ZonaAslComuneImposta.add(COMUNE_LOV__53);

            com.codecharge.components.Hidden PROVINCIA__49 = new com.codecharge.components.Hidden("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__49.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA__49.setHtmlEncode( true );
            ZonaAslComuneImposta.add( PROVINCIA__49 );

            com.codecharge.components.Hidden COMUNE__50 = new com.codecharge.components.Hidden("COMUNE", "COMUNE", this );
            COMUNE__50.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE__50.setHtmlEncode( true );
            ZonaAslComuneImposta.add( COMUNE__50 );

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new ZonaAslComuneImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            ZonaAslComuneImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new ZonaAslComuneImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            ZonaAslComuneImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new ZonaAslComuneImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            ZonaAslComuneImposta.add( Button_Cancel__19 );
            add(ZonaAslComuneImposta);
        } // End definition of ZonaAslComuneImposta record model.
//End ZonaAslComuneImposta record

//Ad4DizionariZonaAslComuneImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariZonaAslComuneImpostaModel class tail

