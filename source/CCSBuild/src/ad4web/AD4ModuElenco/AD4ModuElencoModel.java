//AD4ModuElencoModel imports @1-F19E8FA7
package ad4web.AD4ModuElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4ModuElencoModel imports

//AD4ModuElencoModel class head @1-46961115
public class AD4ModuElencoModel extends com.codecharge.components.Page {
    public AD4ModuElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4ModuElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4ModuElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4ModuElencoModel class head

//page settings @1-D28B8CD1
        super("AD4ModuElenco", locale );
        setResponse(response);
        addPageListener(new AD4ModuElencoPageHandler());
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

//PROGETTI grid @62-42E431F2
        
        /*
            // Begin definition of PROGETTI grid model.
        */
        {
            com.codecharge.components.Grid PROGETTI = new com.codecharge.components.Grid("PROGETTI");
            PROGETTI.setPageModel( this );
            PROGETTI.setFetchSize(10);
            PROGETTI.setVisible( true );

            com.codecharge.components.Label DESC_PROGETTO__63 = new com.codecharge.components.Label("DESC_PROGETTO", "DESC_PROGETTO", this );
            DESC_PROGETTO__63.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTI.add(DESC_PROGETTO__63);

            com.codecharge.components.ImageLink Nuovo__66 = new com.codecharge.components.ImageLink("Nuovo", "", this );
            Nuovo__66.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__66.setHrefSourceValue( "AD4Modulo" + Names.ACTION_SUFFIX );
            Nuovo__66.setHrefType( "Page" );
            Nuovo__66.setConvertRule("Relative");
            Nuovo__66.setPreserveType(PreserveParameterType.GET);
            Nuovo__66.addExcludeParam( "MODULO" );
            Nuovo__66.addExcludeParam( "MVVC" );
            Nuovo__66.addExcludeParam( "MVID" );
            Nuovo__66.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            PROGETTI.add( Nuovo__66 );
            add(PROGETTI);
        } // End definition of PROGETTI grid model
//End PROGETTI grid

//AD4_MODULI grid @6-F780EE8B
        
        /*
            // Begin definition of AD4_MODULI grid model.
        */
        {
            com.codecharge.components.Grid AD4_MODULI = new com.codecharge.components.Grid("AD4_MODULI");
            AD4_MODULI.setPageModel( this );
            AD4_MODULI.setFetchSize(10);
            AD4_MODULI.setVisible( true );
            AD4_MODULI.addGridListener( new AD4_MODULIGridHandler() );
            com.codecharge.components.Sorter Sorter_MODULO = new com.codecharge.components.Sorter("Sorter_MODULO", AD4_MODULI, this);
            Sorter_MODULO.setColumn("MODULO");
            AD4_MODULI.add(Sorter_MODULO);
            com.codecharge.components.Sorter Sorter_DESCRIZIONE = new com.codecharge.components.Sorter("Sorter_DESCRIZIONE", AD4_MODULI, this);
            Sorter_DESCRIZIONE.setColumn("DESCRIZIONE");
            AD4_MODULI.add(Sorter_DESCRIZIONE);
            com.codecharge.components.Sorter Sorter_AMMINISTRATORE = new com.codecharge.components.Sorter("Sorter_AMMINISTRATORE", AD4_MODULI, this);
            Sorter_AMMINISTRATORE.setColumn("AMMINISTRATORE");
            AD4_MODULI.add(Sorter_AMMINISTRATORE);
            com.codecharge.components.Sorter Sorter_NOTE = new com.codecharge.components.Sorter("Sorter_NOTE", AD4_MODULI, this);
            Sorter_NOTE.setColumn("NOTE");
            AD4_MODULI.add(Sorter_NOTE);

            com.codecharge.components.Link MODULO__12 = new com.codecharge.components.Link("MODULO", "MODULO", this );
            MODULO__12.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__12.setHtmlEncode( true );
            MODULO__12.setHrefSourceValue( "AD4Modulo" + Names.ACTION_SUFFIX );
            MODULO__12.setHrefType( "Page" );
            MODULO__12.setConvertRule("Relative");
            MODULO__12.setPreserveType(PreserveParameterType.GET);
            MODULO__12.addExcludeParam( "MVVC" );
            MODULO__12.addExcludeParam( "MVID" );
            MODULO__12.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            MODULO__12.addParameter( new LinkParameter( "MVIDP", "MVID", ParameterSource.URL) );
            AD4_MODULI.add( MODULO__12 );

            com.codecharge.components.Hidden PROGETTO__83 = new com.codecharge.components.Hidden("PROGETTO", "PROGETTO", this );
            PROGETTO__83.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO__83.setHtmlEncode( true );
            AD4_MODULI.add( PROGETTO__83 );

            com.codecharge.components.Label DESCRIZIONE__14 = new com.codecharge.components.Label("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__14.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__14.setHtmlEncode( true );
            AD4_MODULI.add(DESCRIZIONE__14);

            com.codecharge.components.CheckBox AMMINISTRATORE__84=  new com.codecharge.components.CheckBox( "AMMINISTRATORE", "AMMINISTRATORE", this );
            AMMINISTRATORE__84.setType( com.codecharge.components.ControlType.TEXT );
            AMMINISTRATORE__84.setCheckedValue( "S" );
            AMMINISTRATORE__84.setUncheckedValue( "N" );
            AD4_MODULI.add(AMMINISTRATORE__84);

            com.codecharge.components.Label NOTE__16 = new com.codecharge.components.Label("NOTE", "NOTE", this );
            NOTE__16.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__16.setHtmlEncode( true );
            AD4_MODULI.add(NOTE__16);

            com.codecharge.components.ImageLink CaratteristicheAccesso__78 = new com.codecharge.components.ImageLink("CaratteristicheAccesso", "", this );
            CaratteristicheAccesso__78.setType( com.codecharge.components.ControlType.TEXT );
            CaratteristicheAccesso__78.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            CaratteristicheAccesso__78.setHrefType( "Page" );
            CaratteristicheAccesso__78.setConvertRule("Relative");
            CaratteristicheAccesso__78.setPreserveType(PreserveParameterType.NONE);
            CaratteristicheAccesso__78.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__78.addParameter( new LinkParameter( "TIPO_ACCESSO", "", ParameterSource.EXPRESSION) );
            CaratteristicheAccesso__78.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            AD4_MODULI.add( CaratteristicheAccesso__78 );

            com.codecharge.components.ImageLink Abilitazioni__76 = new com.codecharge.components.ImageLink("Abilitazioni", "", this );
            Abilitazioni__76.setType( com.codecharge.components.ControlType.TEXT );
            Abilitazioni__76.setHrefSourceValue( "AD4UtenDiacElenco" + Names.ACTION_SUFFIX );
            Abilitazioni__76.setHrefType( "Page" );
            Abilitazioni__76.setConvertRule("Relative");
            Abilitazioni__76.setPreserveType(PreserveParameterType.GET);
            Abilitazioni__76.addExcludeParam( "MVVC" );
            Abilitazioni__76.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            AD4_MODULI.add( Abilitazioni__76 );

            com.codecharge.components.Label AFCNavigator__68 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__68.setType( com.codecharge.components.ControlType.TEXT );
            AD4_MODULI.add(AFCNavigator__68);
            add(AD4_MODULI);
        } // End definition of AD4_MODULI grid model
//End AD4_MODULI grid

//AD4ModuElencoModel class tail @1-F5FC18C5
    }
}
//End AD4ModuElencoModel class tail

