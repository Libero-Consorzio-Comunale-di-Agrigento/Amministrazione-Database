//AdmPreferenzeImpostazioneModel imports @1-AB03D846
package amvadm.AdmPreferenzeImpostazione;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AdmPreferenzeImpostazioneModel imports

//AdmPreferenzeImpostazioneModel class head @1-7499CC68
public class AdmPreferenzeImpostazioneModel extends com.codecharge.components.Page {
    public AdmPreferenzeImpostazioneModel() {
        this( new CCSLocale(), null );
    }

    public AdmPreferenzeImpostazioneModel(CCSLocale locale) {
        this( locale, null );
    }

    public AdmPreferenzeImpostazioneModel( CCSLocale locale, HttpServletResponse response ) {
//End AdmPreferenzeImpostazioneModel class head

//page settings @1-74D636AF
        super("AdmPreferenzeImpostazione", locale );
        setResponse(response);
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

//TITLEGrid grid @31-372F41CE
        
        /*
            // Begin definition of TITLEGrid grid model.
        */
        {
            com.codecharge.components.Grid TITLEGrid = new com.codecharge.components.Grid("TITLEGrid");
            TITLEGrid.setPageModel( this );
            TITLEGrid.setFetchSize(20);
            TITLEGrid.setVisible( true );

            com.codecharge.components.Label TITOLO__32 = new com.codecharge.components.Label("TITOLO", "TITOLO", this );
            TITOLO__32.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO__32.setHtmlEncode( true );
            TITLEGrid.add(TITOLO__32);

            com.codecharge.components.Label LIVELLO__42 = new com.codecharge.components.Label("LIVELLO", "LIVELLO", this );
            LIVELLO__42.setType( com.codecharge.components.ControlType.TEXT );
            LIVELLO__42.setHtmlEncode( true );
            TITLEGrid.add(LIVELLO__42);
            add(TITLEGrid);
        } // End definition of TITLEGrid grid model
//End TITLEGrid grid

//PREFERENZE grid @6-62D6BDA7
        
        /*
            // Begin definition of PREFERENZE grid model.
        */
        {
            com.codecharge.components.Grid PREFERENZE = new com.codecharge.components.Grid("PREFERENZE");
            PREFERENZE.setPageModel( this );
            PREFERENZE.setFetchSize(20);
            PREFERENZE.setVisible( true );
            PREFERENZE.addGridListener( new PREFERENZEGridHandler() );

            com.codecharge.components.Link STRINGA__16 = new com.codecharge.components.Link("STRINGA", "STRINGA", this );
            STRINGA__16.setType( com.codecharge.components.ControlType.TEXT );
            STRINGA__16.setHtmlEncode( true );
            STRINGA__16.setHrefSourceValue( "AdmPreferenzeImpostazione" + Names.ACTION_SUFFIX );
            STRINGA__16.setHrefType( "Page" );
            STRINGA__16.setConvertRule("Relative");
            STRINGA__16.setPreserveType(PreserveParameterType.GET);
            STRINGA__16.addParameter( new LinkParameter( "STRINGA", "STRINGA", ParameterSource.DATAFIELD) );
            PREFERENZE.add( STRINGA__16 );

            com.codecharge.components.Label IMPOSTATA__41 = new com.codecharge.components.Label("IMPOSTATA", "IMPOSTATA", this );
            IMPOSTATA__41.setType( com.codecharge.components.ControlType.TEXT );
            PREFERENZE.add(IMPOSTATA__41);

            com.codecharge.components.Label VALORE__7 = new com.codecharge.components.Label("VALORE", "VALORE", this );
            VALORE__7.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__7.setHtmlEncode( true );
            PREFERENZE.add(VALORE__7);

            com.codecharge.components.Hidden COMMENTO__40 = new com.codecharge.components.Hidden("COMMENTO", "COMMENTO", this );
            COMMENTO__40.setType( com.codecharge.components.ControlType.TEXT );
            COMMENTO__40.setHtmlEncode( true );
            PREFERENZE.add( COMMENTO__40 );

            com.codecharge.components.Label AFCNavigator__43 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__43.setType( com.codecharge.components.ControlType.TEXT );
            PREFERENZE.add(AFCNavigator__43);
            add(PREFERENZE);
        } // End definition of PREFERENZE grid model
//End PREFERENZE grid

//PREFERENZA_NEW record @45-A0FDF646
        
        /*
            Model of PREFERENZA_NEW record defining.
        */
        {
            com.codecharge.components.Record PREFERENZA_NEW = new com.codecharge.components.Record("PREFERENZA_NEW");
            PREFERENZA_NEW.setPageModel( this );
            PREFERENZA_NEW.addExcludeParam( "ccsForm" );
            PREFERENZA_NEW.addExcludeParam( "STRINGA" );
            PREFERENZA_NEW.setVisible( true );
            PREFERENZA_NEW.setAllowInsert(false);
            PREFERENZA_NEW.setPreserveType(PreserveParameterType.GET);
            PREFERENZA_NEW.setReturnPage("AdmPreferenzeImpostazione" + Names.ACTION_SUFFIX);
            PREFERENZA_NEW.addRecordListener(new PREFERENZA_NEWRecordHandler());

            com.codecharge.components.Label STRINGA_LABEL__60 = new com.codecharge.components.Label("STRINGA_LABEL", "STRINGA_ALIAS", this );
            STRINGA_LABEL__60.setType( com.codecharge.components.ControlType.TEXT );
            STRINGA_LABEL__60.setHtmlEncode( true );
            PREFERENZA_NEW.add(STRINGA_LABEL__60);

            com.codecharge.components.Label COMMENTO__55 = new com.codecharge.components.Label("COMMENTO", "COMMENTO", this );
            COMMENTO__55.setType( com.codecharge.components.ControlType.TEXT );
            COMMENTO__55.setHtmlEncode( true );
            PREFERENZA_NEW.add(COMMENTO__55);

            com.codecharge.components.Hidden STRINGA_ALIAS__46 = new com.codecharge.components.Hidden("STRINGA_ALIAS", "STRINGA_ALIAS", this );
            STRINGA_ALIAS__46.setType( com.codecharge.components.ControlType.TEXT );
            STRINGA_ALIAS__46.setHtmlEncode( true );
            PREFERENZA_NEW.add( STRINGA_ALIAS__46 );

            com.codecharge.components.Hidden MODULO__56 = new com.codecharge.components.Hidden("MODULO", "MODULO", this );
            MODULO__56.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__56.setHtmlEncode( true );
            MODULO__56.setCaption( "MODULO" );
            PREFERENZA_NEW.add( MODULO__56 );

            com.codecharge.components.TextArea VALORE__47 = new com.codecharge.components.TextArea("VALORE", "VALORE", this );
            VALORE__47.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__47.setHtmlEncode( true );
            VALORE__47.setCaption( "VALORE" );
            PREFERENZA_NEW.add( VALORE__47 );

            com.codecharge.components.Button Button_Update__49 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__49.addExcludeParam( "ccsForm" );
            Button_Update__49.addExcludeParam( "Button_Update" );
            Button_Update__49.setOperation( "Update" );
            PREFERENZA_NEW.add( Button_Update__49 );

            com.codecharge.components.Button Button_Delete__50 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__50.addExcludeParam( "ccsForm" );
            Button_Delete__50.addExcludeParam( "Button_Delete" );
            Button_Delete__50.setOperation( "Delete" );
            PREFERENZA_NEW.add( Button_Delete__50 );

            com.codecharge.components.Button Cancel__54 = new com.codecharge.components.Button("Cancel", this);
            Cancel__54.addExcludeParam( "ccsForm" );
            Cancel__54.addExcludeParam( "Cancel" );
            Cancel__54.addExcludeParam( "STRINGA" );
            Cancel__54.setOperation( "Cancel" );
            PREFERENZA_NEW.add( Cancel__54 );
            add(PREFERENZA_NEW);
        } // End definition of PREFERENZA_NEW record model.
//End PREFERENZA_NEW record

//AdmPreferenzeImpostazioneModel class tail @1-F5FC18C5
    }
}
//End AdmPreferenzeImpostazioneModel class tail




