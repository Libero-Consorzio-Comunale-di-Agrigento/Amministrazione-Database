//AD4GruppoModel imports @1-50C0216D
package ad4web.AD4Gruppo;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GruppoModel imports

//AD4GruppoModel class head @1-1509FA8A
public class AD4GruppoModel extends com.codecharge.components.Page {
    public AD4GruppoModel() {
        this( new CCSLocale(), null );
    }

    public AD4GruppoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GruppoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GruppoModel class head

//page settings @1-CD12C8A7
        super("AD4Gruppo", locale );
        setResponse(response);
        addPageListener(new AD4GruppoPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__5 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__5.setVisible( true );
            add( Guida__5 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4_UTENTI record @23-1C82F167
        
        /*
            Model of AD4_UTENTI record defining.
        */
        {
            com.codecharge.components.Record AD4_UTENTI = new com.codecharge.components.Record("AD4_UTENTI");
            AD4_UTENTI.setPageModel( this );
            AD4_UTENTI.addExcludeParam( "ccsForm" );
            AD4_UTENTI.setVisible( true );
            AD4_UTENTI.setAllowInsert(false);
            AD4_UTENTI.setPreserveType(PreserveParameterType.GET);
            AD4_UTENTI.setReturnPage("AD4Gruppo" + Names.ACTION_SUFFIX);
            AD4_UTENTI.addRecordListener(new AD4_UTENTIRecordHandler());

            com.codecharge.components.ImageLink NUOVO__82 = new com.codecharge.components.ImageLink("NUOVO", "", this );
            NUOVO__82.setType( com.codecharge.components.ControlType.TEXT );
            NUOVO__82.setHrefSourceValue( "AD4Gruppo" + Names.ACTION_SUFFIX );
            NUOVO__82.setHrefType( "Page" );
            NUOVO__82.setConvertRule("Relative");
            NUOVO__82.setPreserveType(PreserveParameterType.GET);
            NUOVO__82.addExcludeParam( "GRUPPO" );
            NUOVO__82.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            AD4_UTENTI.add( NUOVO__82 );

            com.codecharge.components.Label UTENTE__30 = new com.codecharge.components.Label("UTENTE", "UTENTE", this );
            UTENTE__30.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTI.add(UTENTE__30);

            com.codecharge.components.Hidden UTENTE_ORIG__41 = new com.codecharge.components.Hidden("UTENTE_ORIG", "UTENTE_ORIG", this );
            UTENTE_ORIG__41.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_ORIG__41.setHtmlEncode( true );
            UTENTE_ORIG__41.setCaption( "UTENTE_ORIG" );
            AD4_UTENTI.add( UTENTE_ORIG__41 );

            com.codecharge.components.Hidden SE_NUOVO__103 = new com.codecharge.components.Hidden("SE_NUOVO", "SE_NUOVO", this );
            SE_NUOVO__103.setType( com.codecharge.components.ControlType.TEXT );
            SE_NUOVO__103.setHtmlEncode( true );
            AD4_UTENTI.add( SE_NUOVO__103 );

            com.codecharge.components.TextBox NOMINATIVO__31 = new com.codecharge.components.TextBox("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__31.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__31.setHtmlEncode( true );
            NOMINATIVO__31.setCaption( "NOMINATIVO" );
            NOMINATIVO__31.addValidateHandler( new RequiredHandler( "Il valore nel campo NOMINATIVO è richiesto." ) );
            AD4_UTENTI.add( NOMINATIVO__31 );

            com.codecharge.components.Hidden TIPO_UTENTE__109 = new com.codecharge.components.Hidden("TIPO_UTENTE", "TIPO_UTENTE", this );
            TIPO_UTENTE__109.setType( com.codecharge.components.ControlType.TEXT );
            TIPO_UTENTE__109.setHtmlEncode( true );
            TIPO_UTENTE__109.addControlListener( new AD4_UTENTITIPO_UTENTEHandler());
            AD4_UTENTI.add( TIPO_UTENTE__109 );

            com.codecharge.components.TextArea NOTE__33 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__33.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__33.setHtmlEncode( true );
            NOTE__33.addControlListener( new AD4_UTENTINOTEHandler());
            NOTE__33.setCaption( "NOTE" );
            AD4_UTENTI.add( NOTE__33 );

            com.codecharge.components.Label GRUPPO_LAVORO__114 = new com.codecharge.components.Label("GRUPPO_LAVORO", "GRUPPO_LAVORO", this );
            GRUPPO_LAVORO__114.setType( com.codecharge.components.ControlType.TEXT );
            GRUPPO_LAVORO__114.setHtmlEncode( true );
            AD4_UTENTI.add(GRUPPO_LAVORO__114);

            com.codecharge.components.Button Button_Update__25 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__25.addButtonListener(new AD4_UTENTIButton_UpdateHandler());
            Button_Update__25.addExcludeParam( "ccsForm" );
            Button_Update__25.addExcludeParam( "Button_Update" );
            Button_Update__25.addExcludeParam( "GRUPPO" );
            Button_Update__25.addExcludeParam( "SE_NUOVO" );
            Button_Update__25.setOperation( "Update" );
            AD4_UTENTI.add( Button_Update__25 );

            com.codecharge.components.Button Button_Delete__26 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__26.addButtonListener(new AD4_UTENTIButton_DeleteHandler());
            Button_Delete__26.addExcludeParam( "ccsForm" );
            Button_Delete__26.addExcludeParam( "Button_Delete" );
            Button_Delete__26.addExcludeParam( "GRUPPO" );
            Button_Delete__26.setOperation( "Delete" );
            AD4_UTENTI.add( Button_Delete__26 );

            com.codecharge.components.Button Button_Cancel__28 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__28.addButtonListener(new AD4_UTENTIButton_CancelHandler());
            Button_Cancel__28.addExcludeParam( "ccsForm" );
            Button_Cancel__28.addExcludeParam( "Button_Cancel" );
            Button_Cancel__28.addExcludeParam( "GRUPPO" );
            Button_Cancel__28.addExcludeParam( "MVVC" );
            Button_Cancel__28.addExcludeParam( "MVID" );
            Button_Cancel__28.setOperation( "Cancel" );
            AD4_UTENTI.add( Button_Cancel__28 );
            add(AD4_UTENTI);
        } // End definition of AD4_UTENTI record model.
//End AD4_UTENTI record

//TOOLBOX grid @97-56A1EA84
        
        /*
            // Begin definition of TOOLBOX grid model.
        */
        {
            com.codecharge.components.Grid TOOLBOX = new com.codecharge.components.Grid("TOOLBOX");
            TOOLBOX.setPageModel( this );
            TOOLBOX.setFetchSize(20);
            TOOLBOX.setVisible( true );
            TOOLBOX.addGridListener( new TOOLBOXGridHandler() );

            com.codecharge.components.Link UTGR_INS_MOD__101 = new com.codecharge.components.Link("UTGR_INS_MOD", "UTGR_INS_MOD", this );
            UTGR_INS_MOD__101.setType( com.codecharge.components.ControlType.TEXT );
            UTGR_INS_MOD__101.setHrefSourceValue( "AD4GruppoMod" + Names.ACTION_SUFFIX );
            UTGR_INS_MOD__101.setHrefType( "Page" );
            UTGR_INS_MOD__101.setConvertRule("Relative");
            UTGR_INS_MOD__101.setPreserveType(PreserveParameterType.GET);
            UTGR_INS_MOD__101.addExcludeParam( "MVVC" );
            UTGR_INS_MOD__101.addParameter( new LinkParameter( "GRUPPO", "GRUPPO", ParameterSource.DATAFIELD) );
            TOOLBOX.add( UTGR_INS_MOD__101 );
            add(TOOLBOX);
        } // End definition of TOOLBOX grid model
//End TOOLBOX grid

//UTENTI_GRUPPO grid @78-B3B77933
        
        /*
            // Begin definition of UTENTI_GRUPPO grid model.
        */
        {
            com.codecharge.components.Grid UTENTI_GRUPPO = new com.codecharge.components.Grid("UTENTI_GRUPPO");
            UTENTI_GRUPPO.setPageModel( this );
            UTENTI_GRUPPO.setFetchSize(20);
            UTENTI_GRUPPO.setVisible( true );
            UTENTI_GRUPPO.addGridListener( new UTENTI_GRUPPOGridHandler() );

            com.codecharge.components.Label NOMINATIVO__79 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__79.setType( com.codecharge.components.ControlType.TEXT );
            UTENTI_GRUPPO.add(NOMINATIVO__79);

            com.codecharge.components.Label CHECK_DIAC_CAAC__87 = new com.codecharge.components.Label("CHECK_DIAC_CAAC", "CHECK_DIAC_CAAC", this );
            CHECK_DIAC_CAAC__87.setType( com.codecharge.components.ControlType.TEXT );
            UTENTI_GRUPPO.add(CHECK_DIAC_CAAC__87);

            com.codecharge.components.Label AFCNavigator__88 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__88.setType( com.codecharge.components.ControlType.TEXT );
            UTENTI_GRUPPO.add(AFCNavigator__88);
            add(UTENTI_GRUPPO);
        } // End definition of UTENTI_GRUPPO grid model
//End UTENTI_GRUPPO grid

//AD4GruppoModel class tail @1-F5FC18C5
    }
}
//End AD4GruppoModel class tail

