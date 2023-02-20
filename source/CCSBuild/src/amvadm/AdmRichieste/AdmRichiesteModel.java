//AdmRichiesteModel imports @1-75FDA710
package amvadm.AdmRichieste;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AdmRichiesteModel imports

//AdmRichiesteModel class head @1-24EB95FA
public class AdmRichiesteModel extends com.codecharge.components.Page {
    public AdmRichiesteModel() {
        this( new CCSLocale(), null );
    }

    public AdmRichiesteModel(CCSLocale locale) {
        this( locale, null );
    }

    public AdmRichiesteModel( CCSLocale locale, HttpServletResponse response ) {
//End AdmRichiesteModel class head

//page settings @1-F2A47F4C
        super("AdmRichieste", locale );
        setResponse(response);
        {
            com.codecharge.components.IncludePage Header__26 = new com.codecharge.components.IncludePage("Header", this );
            Header__26.setVisible( true );
            add( Header__26 );
            com.codecharge.components.IncludePage Left__27 = new com.codecharge.components.IncludePage("Left", this );
            Left__27.setVisible( true );
            add( Left__27 );
            com.codecharge.components.IncludePage Guida__29 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__29.setVisible( true );
            add( Guida__29 );
            com.codecharge.components.IncludePage AdmRichiesteStampa__194 = new com.codecharge.components.IncludePage("AdmRichiesteStampa", this );
            AdmRichiesteStampa__194.setVisible( true );
            add( AdmRichiesteStampa__194 );
            com.codecharge.components.IncludePage Footer__28 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__28.setVisible( true );
            add( Footer__28 );
        } // end page
//End page settings

//TITLEGrid grid @148-73833825
        
        /*
            // Begin definition of TITLEGrid grid model.
        */
        {
            com.codecharge.components.Grid TITLEGrid = new com.codecharge.components.Grid("TITLEGrid");
            TITLEGrid.setPageModel( this );
            TITLEGrid.setFetchSize(20);
            TITLEGrid.setVisible( true );

            com.codecharge.components.Label STATO__149 = new com.codecharge.components.Label("STATO", "STATO", this );
            STATO__149.setType( com.codecharge.components.ControlType.TEXT );
            STATO__149.setHtmlEncode( true );
            TITLEGrid.add(STATO__149);
            add(TITLEGrid);
        } // End definition of TITLEGrid grid model
//End TITLEGrid grid

//AD4_RICHIESTE_ABILITAZIONE grid @30-7F2F7B93
        
        /*
            // Begin definition of AD4_RICHIESTE_ABILITAZIONE grid model.
        */
        {
            com.codecharge.components.Grid AD4_RICHIESTE_ABILITAZIONE = new com.codecharge.components.Grid("AD4_RICHIESTE_ABILITAZIONE");
            AD4_RICHIESTE_ABILITAZIONE.setPageModel( this );
            AD4_RICHIESTE_ABILITAZIONE.setFetchSize(20);
            AD4_RICHIESTE_ABILITAZIONE.setVisible( true );
            AD4_RICHIESTE_ABILITAZIONE.addGridListener( new AD4_RICHIESTE_ABILITAZIONEGridHandler() );
            com.codecharge.components.Sorter Sorter_SERVIZIO = new com.codecharge.components.Sorter("Sorter_SERVIZIO", AD4_RICHIESTE_ABILITAZIONE, this);
            Sorter_SERVIZIO.setColumn("SERVIZIO");
            AD4_RICHIESTE_ABILITAZIONE.add(Sorter_SERVIZIO);

            com.codecharge.components.Link SERVIZIO__37 = new com.codecharge.components.Link("SERVIZIO", "SERVIZIO", this );
            SERVIZIO__37.setType( com.codecharge.components.ControlType.TEXT );
            SERVIZIO__37.setHtmlEncode( true );
            SERVIZIO__37.setHrefSourceValue( "AdmRichieste" + Names.ACTION_SUFFIX );
            SERVIZIO__37.setHrefType( "Page" );
            SERVIZIO__37.setConvertRule("Relative");
            SERVIZIO__37.setPreserveType(PreserveParameterType.GET);
            SERVIZIO__37.addParameter( new LinkParameter( "MOD", "MODULO", ParameterSource.DATAFIELD) );
            SERVIZIO__37.addParameter( new LinkParameter( "IST", "ISTANZA", ParameterSource.DATAFIELD) );
            SERVIZIO__37.addParameter( new LinkParameter( "STATO", "STATO", ParameterSource.DATAFIELD) );
            AD4_RICHIESTE_ABILITAZIONE.add( SERVIZIO__37 );

            com.codecharge.components.Label ABILITAZIONE__165 = new com.codecharge.components.Label("ABILITAZIONE", "ABILITAZIONE", this );
            ABILITAZIONE__165.setType( com.codecharge.components.ControlType.TEXT );
            ABILITAZIONE__165.setHtmlEncode( true );
            AD4_RICHIESTE_ABILITAZIONE.add(ABILITAZIONE__165);

            com.codecharge.components.Label LIVELLO__102 = new com.codecharge.components.Label("LIVELLO", "LIVELLO", this );
            LIVELLO__102.setType( com.codecharge.components.ControlType.TEXT );
            LIVELLO__102.setHtmlEncode( true );
            AD4_RICHIESTE_ABILITAZIONE.add(LIVELLO__102);

            com.codecharge.components.Label TOTALE_RICHIESTE__39 = new com.codecharge.components.Label("TOTALE_RICHIESTE", "TOTALE_RICHIESTE", this );
            TOTALE_RICHIESTE__39.setType( com.codecharge.components.ControlType.TEXT );
            TOTALE_RICHIESTE__39.setHtmlEncode( true );
            AD4_RICHIESTE_ABILITAZIONE.add(TOTALE_RICHIESTE__39);

            com.codecharge.components.Label AFCNavigator__168 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__168.setType( com.codecharge.components.ControlType.TEXT );
            AD4_RICHIESTE_ABILITAZIONE.add(AFCNavigator__168);
            add(AD4_RICHIESTE_ABILITAZIONE);
        } // End definition of AD4_RICHIESTE_ABILITAZIONE grid model
//End AD4_RICHIESTE_ABILITAZIONE grid

//AD4_SERVIZIO_SEL grid @98-2B380447
        
        /*
            // Begin definition of AD4_SERVIZIO_SEL grid model.
        */
        {
            com.codecharge.components.Grid AD4_SERVIZIO_SEL = new com.codecharge.components.Grid("AD4_SERVIZIO_SEL");
            AD4_SERVIZIO_SEL.setPageModel( this );
            AD4_SERVIZIO_SEL.setFetchSize(20);
            AD4_SERVIZIO_SEL.setVisible( true );
            AD4_SERVIZIO_SEL.addGridListener( new AD4_SERVIZIO_SELGridHandler() );

            com.codecharge.components.Label SERVIZIO__99 = new com.codecharge.components.Label("SERVIZIO", "SERVIZIO", this );
            SERVIZIO__99.setType( com.codecharge.components.ControlType.TEXT );
            SERVIZIO__99.setHtmlEncode( true );
            AD4_SERVIZIO_SEL.add(SERVIZIO__99);
            add(AD4_SERVIZIO_SEL);
        } // End definition of AD4_SERVIZIO_SEL grid model
//End AD4_SERVIZIO_SEL grid

//TAB_FOLDER grid @186-636E4D0E
        
        /*
            // Begin definition of TAB_FOLDER grid model.
        */
        {
            com.codecharge.components.Grid TAB_FOLDER = new com.codecharge.components.Grid("TAB_FOLDER");
            TAB_FOLDER.setPageModel( this );
            TAB_FOLDER.setFetchSize(20);
            TAB_FOLDER.setVisible( true );

            com.codecharge.components.Label TAB__187 = new com.codecharge.components.Label("TAB", "TAB", this );
            TAB__187.setType( com.codecharge.components.ControlType.TEXT );
            TAB_FOLDER.add(TAB__187);
            add(TAB_FOLDER);
        } // End definition of TAB_FOLDER grid model
//End TAB_FOLDER grid

//AD4_RICHIESTE_SERVIZIO grid @123-94FF8673
        
        /*
            // Begin definition of AD4_RICHIESTE_SERVIZIO grid model.
        */
        {
            com.codecharge.components.Grid AD4_RICHIESTE_SERVIZIO = new com.codecharge.components.Grid("AD4_RICHIESTE_SERVIZIO");
            AD4_RICHIESTE_SERVIZIO.setPageModel( this );
            AD4_RICHIESTE_SERVIZIO.setFetchSize(20);
            AD4_RICHIESTE_SERVIZIO.setVisible( true );
            AD4_RICHIESTE_SERVIZIO.addGridListener( new AD4_RICHIESTE_SERVIZIOGridHandler() );

            com.codecharge.components.Label DATA__124 = new com.codecharge.components.Label("DATA", "DATA", this );
            DATA__124.setType( com.codecharge.components.ControlType.TEXT );
            AD4_RICHIESTE_SERVIZIO.add(DATA__124);

            com.codecharge.components.Label AUTORE__125 = new com.codecharge.components.Label("AUTORE", "AUTORE", this );
            AUTORE__125.setType( com.codecharge.components.ControlType.TEXT );
            AUTORE__125.setHtmlEncode( true );
            AD4_RICHIESTE_SERVIZIO.add(AUTORE__125);

            com.codecharge.components.Label INDIRIZZO_WEB__126 = new com.codecharge.components.Label("INDIRIZZO_WEB", "INDIRIZZO_WEB", this );
            INDIRIZZO_WEB__126.setType( com.codecharge.components.ControlType.TEXT );
            AD4_RICHIESTE_SERVIZIO.add(INDIRIZZO_WEB__126);

            com.codecharge.components.Label DATI__178 = new com.codecharge.components.Label("DATI", "DATI", this );
            DATI__178.setType( com.codecharge.components.ControlType.TEXT );
            AD4_RICHIESTE_SERVIZIO.add(DATI__178);

            com.codecharge.components.Label AFCNavigator__166 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__166.setType( com.codecharge.components.ControlType.TEXT );
            AD4_RICHIESTE_SERVIZIO.add(AFCNavigator__166);
            add(AD4_RICHIESTE_SERVIZIO);
        } // End definition of AD4_RICHIESTE_SERVIZIO grid model
//End AD4_RICHIESTE_SERVIZIO grid

//AdmRichiesteModel class tail @1-F5FC18C5
    }
}
//End AdmRichiesteModel class tail

