//Ad4DizionariAslComuneImpostaModel imports @1-11ED9E01
package restrict.Ad4DizionariAslComuneImposta;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariAslComuneImpostaModel imports

//Ad4DizionariAslComuneImpostaModel class head @1-025E1456
public class Ad4DizionariAslComuneImpostaModel extends com.codecharge.components.Page {
    public Ad4DizionariAslComuneImpostaModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariAslComuneImpostaModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariAslComuneImpostaModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariAslComuneImpostaModel class head

//page settings @1-0A18481B
        super("Ad4DizionariAslComuneImposta", locale );
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

//AslComuneImposta record @5-32CBEA47
        
        /*
            Model of AslComuneImposta record defining.
        */
        {
            com.codecharge.components.Record AslComuneImposta = new com.codecharge.components.Record("AslComuneImposta");
            AslComuneImposta.setPageModel( this );
            AslComuneImposta.addExcludeParam( "ccsForm" );
            AslComuneImposta.addExcludeParam( "MVPG" );
            AslComuneImposta.addExcludeParam( "PROVINCIA" );
            AslComuneImposta.addExcludeParam( "COMUNE" );
            AslComuneImposta.setVisible( true );
            AslComuneImposta.setPreserveType(PreserveParameterType.GET);
            AslComuneImposta.setReturnPage("Ad4DizionariAslComuneImposta" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label ASL_DESC__6 = new com.codecharge.components.Label("ASL_DESC", "ASL_DESC", this );
            ASL_DESC__6.setType( com.codecharge.components.ControlType.TEXT );
            ASL_DESC__6.setHtmlEncode( true );
            AslComuneImposta.add(ASL_DESC__6);

            com.codecharge.components.Hidden REGIONE_ASL__52 = new com.codecharge.components.Hidden("REGIONE_ASL", "REGIONE_ASL", this );
            REGIONE_ASL__52.setType( com.codecharge.components.ControlType.INTEGER );
            REGIONE_ASL__52.setHtmlEncode( true );
            AslComuneImposta.add( REGIONE_ASL__52 );

            com.codecharge.components.Hidden CODICE_ASL__53 = new com.codecharge.components.Hidden("CODICE_ASL", "CODICE_ASL", this );
            CODICE_ASL__53.setType( com.codecharge.components.ControlType.INTEGER );
            CODICE_ASL__53.setHtmlEncode( true );
            AslComuneImposta.add( CODICE_ASL__53 );

            com.codecharge.components.Label Label1__54 = new com.codecharge.components.Label("Label1", "HIDE_BEGIN", this );
            Label1__54.setType( com.codecharge.components.ControlType.TEXT );
            AslComuneImposta.add(Label1__54);

            com.codecharge.components.ListBox COD_ASL__55 = new com.codecharge.components.ListBox("COD_ASL", "COD_ASL", this );
            COD_ASL__55.setType( com.codecharge.components.ControlType.TEXT );
            COD_ASL__55.setHtmlEncode( true );
            AslComuneImposta.add( COD_ASL__55 );

            com.codecharge.components.Label Label2__56 = new com.codecharge.components.Label("Label2", "HIDE_END", this );
            Label2__56.setType( com.codecharge.components.ControlType.TEXT );
            AslComuneImposta.add(Label2__56);

            com.codecharge.components.Label COMUNE_DESC__10 = new com.codecharge.components.Label("COMUNE_DESC", "COMUNE_DESC", this );
            COMUNE_DESC__10.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE_DESC__10.setHtmlEncode( true );
            AslComuneImposta.add(COMUNE_DESC__10);

            com.codecharge.components.Hidden PROVINCIA__49 = new com.codecharge.components.Hidden("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__49.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA__49.setHtmlEncode( true );
            AslComuneImposta.add( PROVINCIA__49 );

            com.codecharge.components.Hidden COMUNE__50 = new com.codecharge.components.Hidden("COMUNE", "COMUNE", this );
            COMUNE__50.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE__50.setHtmlEncode( true );
            AslComuneImposta.add( COMUNE__50 );

            com.codecharge.components.Label Label3__57 = new com.codecharge.components.Label("Label3", "HIDE_BEGIN2", this );
            Label3__57.setType( com.codecharge.components.ControlType.TEXT );
            AslComuneImposta.add(Label3__57);

            com.codecharge.components.ListBox COD_COMUNE_ASL__58 = new com.codecharge.components.ListBox("COD_COMUNE_ASL", "COD_COMUNE_ASL", this );
            COD_COMUNE_ASL__58.setType( com.codecharge.components.ControlType.TEXT );
            COD_COMUNE_ASL__58.setHtmlEncode( true );
            COD_COMUNE_ASL__58.setBoundColumn( "COD_COM_ASL" );
            COD_COMUNE_ASL__58.setTextColumn( "DENOMINAZIONE" );
            AslComuneImposta.add( COD_COMUNE_ASL__58 );

            com.codecharge.components.Label Label4__59 = new com.codecharge.components.Label("Label4", "HIDE_END2", this );
            Label4__59.setType( com.codecharge.components.ControlType.TEXT );
            AslComuneImposta.add(Label4__59);

            com.codecharge.components.CheckBox PROPOSTA__51=  new com.codecharge.components.CheckBox( "PROPOSTA", "PROPOSTA", this );
            PROPOSTA__51.setType( com.codecharge.components.ControlType.TEXT );
            PROPOSTA__51.setCheckedValue( "S" );
            AslComuneImposta.add(PROPOSTA__51);

            com.codecharge.components.CheckBox ATTIVA__41=  new com.codecharge.components.CheckBox( "ATTIVA", "ATTIVA", this );
            ATTIVA__41.setType( com.codecharge.components.ControlType.TEXT );
            ATTIVA__41.setCheckedValue( "S" );
            AslComuneImposta.add(ATTIVA__41);

            com.codecharge.components.Label LABEL_UPD__39 = new com.codecharge.components.Label("LABEL_UPD", "LABEL_UPD", this );
            LABEL_UPD__39.setType( com.codecharge.components.ControlType.TEXT );
            AslComuneImposta.add(LABEL_UPD__39);

            com.codecharge.components.Button Button_Insert__12 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__12.addButtonListener(new AslComuneImpostaButton_InsertHandler());
            Button_Insert__12.addExcludeParam( "ccsForm" );
            Button_Insert__12.addExcludeParam( "Button_Insert" );
            Button_Insert__12.setOperation( "Insert" );
            AslComuneImposta.add( Button_Insert__12 );

            com.codecharge.components.Button Button_Update__14 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__14.addButtonListener(new AslComuneImpostaButton_UpdateHandler());
            Button_Update__14.addExcludeParam( "ccsForm" );
            Button_Update__14.addExcludeParam( "Button_Update" );
            Button_Update__14.setOperation( "Update" );
            AslComuneImposta.add( Button_Update__14 );

            com.codecharge.components.Button Button_Delete__16 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__16.addButtonListener(new AslComuneImpostaButton_DeleteHandler());
            Button_Delete__16.addExcludeParam( "ccsForm" );
            Button_Delete__16.addExcludeParam( "Button_Delete" );
            Button_Delete__16.setOperation( "Delete" );
            AslComuneImposta.add( Button_Delete__16 );

            com.codecharge.components.Button Button_Cancel__19 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__19.addButtonListener(new AslComuneImpostaButton_CancelHandler());
            Button_Cancel__19.addExcludeParam( "ccsForm" );
            Button_Cancel__19.addExcludeParam( "Button_Cancel" );
            Button_Cancel__19.setOperation( "Cancel" );
            AslComuneImposta.add( Button_Cancel__19 );
            add(AslComuneImposta);
        } // End definition of AslComuneImposta record model.
//End AslComuneImposta record

//Ad4DizionariAslComuneImpostaModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariAslComuneImpostaModel class tail

