//AD4ServElencoModel imports @1-8C4D0516
package ad4web.AD4ServElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4ServElencoModel imports

//AD4ServElencoModel class head @1-4ECE696E
public class AD4ServElencoModel extends com.codecharge.components.Page {
    public AD4ServElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4ServElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4ServElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4ServElencoModel class head

//page settings @1-BAF0182A
        super("AD4ServElenco", locale );
        setResponse(response);
        addPageListener(new AD4ServElencoPageHandler());
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

//PROGETTO grid @160-E6257418
        
        /*
            // Begin definition of PROGETTO grid model.
        */
        {
            com.codecharge.components.Grid PROGETTO = new com.codecharge.components.Grid("PROGETTO");
            PROGETTO.setPageModel( this );
            PROGETTO.setFetchSize(20);
            PROGETTO.setVisible( true );

            com.codecharge.components.Label DESC_PROGETTO__161 = new com.codecharge.components.Label("DESC_PROGETTO", "DESC_PROGETTO", this );
            DESC_PROGETTO__161.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO.add(DESC_PROGETTO__161);

            com.codecharge.components.ImageLink AD4_DIRITTI_ACCESSO_Insert__165 = new com.codecharge.components.ImageLink("AD4_DIRITTI_ACCESSO_Insert", "", this );
            AD4_DIRITTI_ACCESSO_Insert__165.setType( com.codecharge.components.ControlType.TEXT );
            AD4_DIRITTI_ACCESSO_Insert__165.setHrefSourceValue( "AD4Servizio" + Names.ACTION_SUFFIX );
            AD4_DIRITTI_ACCESSO_Insert__165.setHrefType( "Page" );
            AD4_DIRITTI_ACCESSO_Insert__165.setConvertRule("Relative");
            AD4_DIRITTI_ACCESSO_Insert__165.setPreserveType(PreserveParameterType.GET);
            AD4_DIRITTI_ACCESSO_Insert__165.addExcludeParam( "ID_SERVIZIO" );
            AD4_DIRITTI_ACCESSO_Insert__165.addExcludeParam( "ISTANZA_LISTBOX" );
            AD4_DIRITTI_ACCESSO_Insert__165.addExcludeParam( "MODULO_LISTBOX" );
            AD4_DIRITTI_ACCESSO_Insert__165.addExcludeParam( "MVVC" );
            PROGETTO.add( AD4_DIRITTI_ACCESSO_Insert__165 );
            add(PROGETTO);
        } // End definition of PROGETTO grid model
//End PROGETTO grid

//AD4_SERVIZI grid @6-D8829BAD
        
        /*
            // Begin definition of AD4_SERVIZI grid model.
        */
        {
            com.codecharge.components.Grid AD4_SERVIZI = new com.codecharge.components.Grid("AD4_SERVIZI");
            AD4_SERVIZI.setPageModel( this );
            AD4_SERVIZI.setFetchSize(10);
            AD4_SERVIZI.setVisible( true );
            AD4_SERVIZI.addGridListener( new AD4_SERVIZIGridHandler() );
            com.codecharge.components.Sorter Sorter_SEQUENZA = new com.codecharge.components.Sorter("Sorter_SEQUENZA", AD4_SERVIZI, this);
            Sorter_SEQUENZA.setColumn("SEQUENZA");
            AD4_SERVIZI.add(Sorter_SEQUENZA);
            com.codecharge.components.Sorter Sorter_MODULO = new com.codecharge.components.Sorter("Sorter_MODULO", AD4_SERVIZI, this);
            Sorter_MODULO.setColumn("MODULO");
            AD4_SERVIZI.add(Sorter_MODULO);
            com.codecharge.components.Sorter Sorter_ISTANZA = new com.codecharge.components.Sorter("Sorter_ISTANZA", AD4_SERVIZI, this);
            Sorter_ISTANZA.setColumn("ISTANZA");
            AD4_SERVIZI.add(Sorter_ISTANZA);
            com.codecharge.components.Sorter Sorter_RUOLO = new com.codecharge.components.Sorter("Sorter_RUOLO", AD4_SERVIZI, this);
            Sorter_RUOLO.setColumn("RUOLO");
            AD4_SERVIZI.add(Sorter_RUOLO);

            com.codecharge.components.Link ID_SERVIZIO__22 = new com.codecharge.components.Link("ID_SERVIZIO", "ID_SERVIZIO", this );
            ID_SERVIZIO__22.setType( com.codecharge.components.ControlType.INTEGER );
            ID_SERVIZIO__22.setHtmlEncode( true );
            ID_SERVIZIO__22.setHrefSourceValue( "AD4Servizio" + Names.ACTION_SUFFIX );
            ID_SERVIZIO__22.setHrefType( "Page" );
            ID_SERVIZIO__22.setConvertRule("Relative");
            ID_SERVIZIO__22.setPreserveType(PreserveParameterType.GET);
            ID_SERVIZIO__22.addExcludeParam( "MVVC" );
            ID_SERVIZIO__22.addParameter( new LinkParameter( "ID_SERVIZIO", "ID_SERVIZIO", ParameterSource.DATAFIELD) );
            AD4_SERVIZI.add( ID_SERVIZIO__22 );

            com.codecharge.components.Link DES_MODULO__17 = new com.codecharge.components.Link("DES_MODULO", "DES_MODULO", this );
            DES_MODULO__17.setType( com.codecharge.components.ControlType.TEXT );
            DES_MODULO__17.setHtmlEncode( true );
            DES_MODULO__17.setHrefSourceValue( "AD4Servizio" + Names.ACTION_SUFFIX );
            DES_MODULO__17.setHrefType( "Page" );
            DES_MODULO__17.setConvertRule("Relative");
            DES_MODULO__17.setPreserveType(PreserveParameterType.GET);
            DES_MODULO__17.addExcludeParam( "MVVC" );
            DES_MODULO__17.addParameter( new LinkParameter( "ID_SERVIZIO", "ID_SERVIZIO", ParameterSource.DATAFIELD) );
            AD4_SERVIZI.add( DES_MODULO__17 );

            com.codecharge.components.Link DES_ISTANZA__20 = new com.codecharge.components.Link("DES_ISTANZA", "DES_ISTANZA", this );
            DES_ISTANZA__20.setType( com.codecharge.components.ControlType.TEXT );
            DES_ISTANZA__20.setHtmlEncode( true );
            DES_ISTANZA__20.setHrefSourceValue( "AD4Servizio" + Names.ACTION_SUFFIX );
            DES_ISTANZA__20.setHrefType( "Page" );
            DES_ISTANZA__20.setConvertRule("Relative");
            DES_ISTANZA__20.setPreserveType(PreserveParameterType.GET);
            DES_ISTANZA__20.addExcludeParam( "MVVC" );
            DES_ISTANZA__20.addParameter( new LinkParameter( "ID_SERVIZIO", "ID_SERVIZIO", ParameterSource.DATAFIELD) );
            AD4_SERVIZI.add( DES_ISTANZA__20 );

            com.codecharge.components.Label DATI__21 = new com.codecharge.components.Label("DATI", "DATI", this );
            DATI__21.setType( com.codecharge.components.ControlType.TEXT );
            AD4_SERVIZI.add(DATI__21);

            com.codecharge.components.ImageLink Abilitazioni__166 = new com.codecharge.components.ImageLink("Abilitazioni", "", this );
            Abilitazioni__166.setType( com.codecharge.components.ControlType.TEXT );
            Abilitazioni__166.setHrefSourceValue( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX );
            Abilitazioni__166.setHrefType( "Page" );
            Abilitazioni__166.setConvertRule("Relative");
            Abilitazioni__166.setPreserveType(PreserveParameterType.GET);
            Abilitazioni__166.addExcludeParam( "MVVC" );
            Abilitazioni__166.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            Abilitazioni__166.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            AD4_SERVIZI.add( Abilitazioni__166 );

            com.codecharge.components.Label AFCNavigator__159 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__159.setType( com.codecharge.components.ControlType.TEXT );
            AD4_SERVIZI.add(AFCNavigator__159);
            add(AD4_SERVIZI);
        } // End definition of AD4_SERVIZI grid model
//End AD4_SERVIZI grid

//AD4ServElencoModel class tail @1-F5FC18C5
    }
}
//End AD4ServElencoModel class tail

