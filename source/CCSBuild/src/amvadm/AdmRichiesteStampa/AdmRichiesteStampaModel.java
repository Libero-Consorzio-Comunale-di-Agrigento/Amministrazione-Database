//AdmRichiesteStampaModel imports @1-D8A2334C
package amvadm.AdmRichiesteStampa;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AdmRichiesteStampaModel imports

//AdmRichiesteStampaModel class head @1-75F1259A
public class AdmRichiesteStampaModel extends com.codecharge.components.Page {
    public AdmRichiesteStampaModel() {
        this( new CCSLocale(), null );
    }

    public AdmRichiesteStampaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AdmRichiesteStampaModel( CCSLocale locale, HttpServletResponse response ) {
//End AdmRichiesteStampaModel class head

//page settings @1-F416C70F
        super("AdmRichiesteStampa", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//RICHIESTE_STAMPA EditGrid @2-DDB7127E
        
        /*
            // Begin definition of RICHIESTE_STAMPA editable grid model.
        */
        {
            com.codecharge.components.EditableGrid RICHIESTE_STAMPA = new com.codecharge.components.EditableGrid("RICHIESTE_STAMPA");
            RICHIESTE_STAMPA.setPageModel( this );
            RICHIESTE_STAMPA.addExcludeParam( "ccsForm" );
            RICHIESTE_STAMPA.setFetchSize(1000);
            RICHIESTE_STAMPA.setAllowInsert(false);
            RICHIESTE_STAMPA.setAllowDelete(false);
            RICHIESTE_STAMPA.setVisible( true );
            RICHIESTE_STAMPA.setPreserveType(PreserveParameterType.GET);
            RICHIESTE_STAMPA.setNumberEmptyRows( 0 );
            RICHIESTE_STAMPA.addCachedColumn( "ID_CREDENZIALE", com.codecharge.components.ControlType.FLOAT);

            RICHIESTE_STAMPA.addEditableGridListener( new RICHIESTE_STAMPAEditableGridHandler() );

            com.codecharge.components.Label DATA__4 = new com.codecharge.components.Label("DATA", "DATA", this );
            DATA__4.setType( com.codecharge.components.ControlType.TEXT );
            DATA__4.setHtmlEncode( true );
            RICHIESTE_STAMPA.addRowControl( "DATA" );
            RICHIESTE_STAMPA.add(DATA__4);

            com.codecharge.components.Label REPORT__55 = new com.codecharge.components.Label("REPORT", "LINK", this );
            REPORT__55.setType( com.codecharge.components.ControlType.TEXT );
            RICHIESTE_STAMPA.addRowControl( "REPORT" );
            RICHIESTE_STAMPA.add(REPORT__55);

            com.codecharge.components.Label AUTORE__49 = new com.codecharge.components.Label("AUTORE", "AUTORE", this );
            AUTORE__49.setType( com.codecharge.components.ControlType.TEXT );
            AUTORE__49.setHtmlEncode( true );
            RICHIESTE_STAMPA.addRowControl( "AUTORE" );
            RICHIESTE_STAMPA.add(AUTORE__49);

            com.codecharge.components.Hidden ID_RICHIESTA__51 = new com.codecharge.components.Hidden("ID_RICHIESTA", "ID_RICHIESTA", this );
            ID_RICHIESTA__51.setType( com.codecharge.components.ControlType.TEXT );
            ID_RICHIESTA__51.setHtmlEncode( true );
            RICHIESTE_STAMPA.addRowControl( "ID_RICHIESTA" );
            RICHIESTE_STAMPA.add( ID_RICHIESTA__51 );

            com.codecharge.components.Label NOTIFICATA__52 = new com.codecharge.components.Label("NOTIFICATA", "NOTIFICATA", this );
            NOTIFICATA__52.setType( com.codecharge.components.ControlType.TEXT );
            NOTIFICATA__52.setHtmlEncode( true );
            RICHIESTE_STAMPA.addRowControl( "NOTIFICATA" );
            RICHIESTE_STAMPA.add(NOTIFICATA__52);

            com.codecharge.components.Label BEGIN_HIDE__57 = new com.codecharge.components.Label("BEGIN_HIDE", "BEGIN_HIDE", this );
            BEGIN_HIDE__57.setType( com.codecharge.components.ControlType.TEXT );
            RICHIESTE_STAMPA.addRowControl( "BEGIN_HIDE" );
            RICHIESTE_STAMPA.add(BEGIN_HIDE__57);

            com.codecharge.components.CheckBox NOTIFICATO__11=  new com.codecharge.components.CheckBox( "NOTIFICATO", "NOTIFICATO", this );
            NOTIFICATO__11.setType( com.codecharge.components.ControlType.INTEGER );
            NOTIFICATO__11.setCheckedValue( 1 );
            NOTIFICATO__11.setUncheckedValue( 0 );
            RICHIESTE_STAMPA.add(NOTIFICATO__11);

            com.codecharge.components.CheckBox STAMPA__50=  new com.codecharge.components.CheckBox( "STAMPA", "STAMPA", this );
            STAMPA__50.setType( com.codecharge.components.ControlType.INTEGER );
            STAMPA__50.setCheckedValue( 1 );
            STAMPA__50.setUncheckedValue( 0 );
            RICHIESTE_STAMPA.add(STAMPA__50);

            com.codecharge.components.Label END_HIDE__58 = new com.codecharge.components.Label("END_HIDE", "END_HIDE", this );
            END_HIDE__58.setType( com.codecharge.components.ControlType.TEXT );
            RICHIESTE_STAMPA.addRowControl( "END_HIDE" );
            RICHIESTE_STAMPA.add(END_HIDE__58);

            com.codecharge.components.Label LINK__61 = new com.codecharge.components.Label("LINK", "", this );
            LINK__61.setType( com.codecharge.components.ControlType.TEXT );
            RICHIESTE_STAMPA.addStaticControl( "LINK" );
            LINK__61.addControlListener( new RICHIESTE_STAMPALINKHandler());
            RICHIESTE_STAMPA.add(LINK__61);

            com.codecharge.components.Button Button_Submit__12 = new com.codecharge.components.Button("Button_Submit", this);
            Button_Submit__12.addExcludeParam( "ccsForm" );
            Button_Submit__12.addExcludeParam( "Button_Submit" );
            Button_Submit__12.setOperation( "Submit" );
            RICHIESTE_STAMPA.add( Button_Submit__12 );
            add(RICHIESTE_STAMPA);
        } // End definition of RICHIESTE_STAMPA editable grid model
//End RICHIESTE_STAMPA EditGrid

//AdmRichiesteStampaModel class tail @1-F5FC18C5
    }
}
//End AdmRichiesteStampaModel class tail

