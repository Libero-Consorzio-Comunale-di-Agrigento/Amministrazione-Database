//AD4DiacElencoSTEModel imports @1-BC639DF6
package common.AD4DiacElencoSTE;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4DiacElencoSTEModel imports

//AD4DiacElencoSTEModel class head @1-A021E0DC
public class AD4DiacElencoSTEModel extends com.codecharge.components.Page {
    public AD4DiacElencoSTEModel() {
        this( new CCSLocale(), null );
    }

    public AD4DiacElencoSTEModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4DiacElencoSTEModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4DiacElencoSTEModel class head

//page settings @1-7108FF2B
        super("AD4DiacElencoSTE", locale );
        setResponse(response);
        addPageListener(new AD4DiacElencoSTEPageHandler());
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

//DIRITTI_ACCESSO grid @146-3A25EED2
        
        /*
            // Begin definition of DIRITTI_ACCESSO grid model.
        */
        {
            com.codecharge.components.Grid DIRITTI_ACCESSO = new com.codecharge.components.Grid("DIRITTI_ACCESSO");
            DIRITTI_ACCESSO.setPageModel( this );
            DIRITTI_ACCESSO.setFetchSize(10);
            DIRITTI_ACCESSO.setVisible( true );

            com.codecharge.components.Label NOMINATIVO__147 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__147.setType( com.codecharge.components.ControlType.TEXT );
            DIRITTI_ACCESSO.add(NOMINATIVO__147);

            com.codecharge.components.ImageLink Nuovo__151 = new com.codecharge.components.ImageLink("Nuovo", "", this );
            Nuovo__151.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__151.setHrefSourceValue( "AD4DirittoAccesso" + Names.ACTION_SUFFIX );
            Nuovo__151.setHrefType( "Page" );
            Nuovo__151.setConvertRule("Relative");
            Nuovo__151.setPreserveType(PreserveParameterType.GET);
            Nuovo__151.addExcludeParam( "ISTANZA" );
            Nuovo__151.addExcludeParam( "MODULO" );
            Nuovo__151.addExcludeParam( "MVVC" );
            Nuovo__151.addParameter( new LinkParameter( "MVIDP", "MVID", ParameterSource.URL) );
            DIRITTI_ACCESSO.add( Nuovo__151 );

            com.codecharge.components.ImageLink Copia__162 = new com.codecharge.components.ImageLink("Copia", "", this );
            Copia__162.setType( com.codecharge.components.ControlType.TEXT );
            Copia__162.setHrefSourceValue( "AD4UtentiRicerca" + Names.ACTION_SUFFIX );
            Copia__162.setHrefType( "Page" );
            Copia__162.setConvertRule("Relative");
            Copia__162.setPreserveType(PreserveParameterType.GET);
            Copia__162.addExcludeParam( "ISTANZA" );
            Copia__162.addExcludeParam( "MODULO" );
            Copia__162.addExcludeParam( "MVVC" );
            Copia__162.addParameter( new LinkParameter( "MVIDP", "MVID", ParameterSource.URL) );
            Copia__162.addParameter( new LinkParameter( "UTENTE_ALIMENTARE", "AD4UTENTE", ParameterSource.SESSION) );
            DIRITTI_ACCESSO.add( Copia__162 );

            com.codecharge.components.ImageLink Unifica__165 = new com.codecharge.components.ImageLink("Unifica", "", this );
            Unifica__165.setType( com.codecharge.components.ControlType.TEXT );
            Unifica__165.setHrefSourceValue( "AD4UtentiRicerca" + Names.ACTION_SUFFIX );
            Unifica__165.setHrefType( "Page" );
            Unifica__165.setConvertRule("Relative");
            Unifica__165.setPreserveType(PreserveParameterType.GET);
            Unifica__165.addExcludeParam( "ISTANZA" );
            Unifica__165.addExcludeParam( "MODULO" );
            Unifica__165.addExcludeParam( "MVVC" );
            Unifica__165.addParameter( new LinkParameter( "MVIDP", "MVID", ParameterSource.URL) );
            Unifica__165.addParameter( new LinkParameter( "UTENTE_ALIMENTARE_UNIFICARE", "AD4UTENTE", ParameterSource.SESSION) );
            DIRITTI_ACCESSO.add( Unifica__165 );
            add(DIRITTI_ACCESSO);
        } // End definition of DIRITTI_ACCESSO grid model
//End DIRITTI_ACCESSO grid

//AD4_DIRITTI_ACCESSO grid @6-5F866494
        
        /*
            // Begin definition of AD4_DIRITTI_ACCESSO grid model.
        */
        {
            com.codecharge.components.Grid AD4_DIRITTI_ACCESSO = new com.codecharge.components.Grid("AD4_DIRITTI_ACCESSO");
            AD4_DIRITTI_ACCESSO.setPageModel( this );
            AD4_DIRITTI_ACCESSO.setFetchSize(10);
            AD4_DIRITTI_ACCESSO.setVisible( true );
            AD4_DIRITTI_ACCESSO.addGridListener( new AD4_DIRITTI_ACCESSOGridHandler() );
            com.codecharge.components.Sorter Sorter_SEQUENZA = new com.codecharge.components.Sorter("Sorter_SEQUENZA", AD4_DIRITTI_ACCESSO, this);
            Sorter_SEQUENZA.setColumn("SEQUENZA");
            AD4_DIRITTI_ACCESSO.add(Sorter_SEQUENZA);
            com.codecharge.components.Sorter Sorter_MODULO = new com.codecharge.components.Sorter("Sorter_MODULO", AD4_DIRITTI_ACCESSO, this);
            Sorter_MODULO.setColumn("MODULO");
            AD4_DIRITTI_ACCESSO.add(Sorter_MODULO);
            com.codecharge.components.Sorter Sorter_ISTANZA = new com.codecharge.components.Sorter("Sorter_ISTANZA", AD4_DIRITTI_ACCESSO, this);
            Sorter_ISTANZA.setColumn("ISTANZA");
            AD4_DIRITTI_ACCESSO.add(Sorter_ISTANZA);
            com.codecharge.components.Sorter Sorter_DATI = new com.codecharge.components.Sorter("Sorter_DATI", AD4_DIRITTI_ACCESSO, this);
            Sorter_DATI.setColumn("DATI");
            AD4_DIRITTI_ACCESSO.add(Sorter_DATI);

            com.codecharge.components.Link SEQUENZA__22 = new com.codecharge.components.Link("SEQUENZA", "SEQUENZA", this );
            SEQUENZA__22.setType( com.codecharge.components.ControlType.INTEGER );
            SEQUENZA__22.setHtmlEncode( true );
            SEQUENZA__22.setHrefSourceValue( "AD4DirittoAccesso" + Names.ACTION_SUFFIX );
            SEQUENZA__22.setHrefType( "Page" );
            SEQUENZA__22.setConvertRule("Relative");
            SEQUENZA__22.setPreserveType(PreserveParameterType.GET);
            SEQUENZA__22.addExcludeParam( "MVVC" );
            SEQUENZA__22.addExcludeParam( "MVID" );
            SEQUENZA__22.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            SEQUENZA__22.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            SEQUENZA__22.addParameter( new LinkParameter( "MODULO_LISTBOX", "", ParameterSource.EXPRESSION) );
            SEQUENZA__22.addParameter( new LinkParameter( "ISTANZA_LISTBOX", "", ParameterSource.EXPRESSION) );
            SEQUENZA__22.addParameter( new LinkParameter( "MVIDP", "MVID", ParameterSource.URL) );
            AD4_DIRITTI_ACCESSO.add( SEQUENZA__22 );

            com.codecharge.components.Hidden UTENTE__70 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__70.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__70.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO.add( UTENTE__70 );

            com.codecharge.components.Link DES_MODULO__17 = new com.codecharge.components.Link("DES_MODULO", "DES_MODULO", this );
            DES_MODULO__17.setType( com.codecharge.components.ControlType.TEXT );
            DES_MODULO__17.setHtmlEncode( true );
            DES_MODULO__17.setHrefSourceValue( "AD4DirittoAccesso" + Names.ACTION_SUFFIX );
            DES_MODULO__17.setHrefType( "Page" );
            DES_MODULO__17.setConvertRule("Relative");
            DES_MODULO__17.setPreserveType(PreserveParameterType.GET);
            DES_MODULO__17.addExcludeParam( "MVVC" );
            DES_MODULO__17.addExcludeParam( "MVID" );
            DES_MODULO__17.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            DES_MODULO__17.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            DES_MODULO__17.addParameter( new LinkParameter( "MODULO_LISTBOX", "", ParameterSource.EXPRESSION) );
            DES_MODULO__17.addParameter( new LinkParameter( "ISTANZA_LISTBOX", "", ParameterSource.EXPRESSION) );
            DES_MODULO__17.addParameter( new LinkParameter( "MVIDP", "MVID", ParameterSource.URL) );
            AD4_DIRITTI_ACCESSO.add( DES_MODULO__17 );

            com.codecharge.components.Link DES_ISTANZA__20 = new com.codecharge.components.Link("DES_ISTANZA", "DES_ISTANZA", this );
            DES_ISTANZA__20.setType( com.codecharge.components.ControlType.TEXT );
            DES_ISTANZA__20.setHtmlEncode( true );
            DES_ISTANZA__20.setHrefSourceValue( "AD4DirittoAccesso" + Names.ACTION_SUFFIX );
            DES_ISTANZA__20.setHrefType( "Page" );
            DES_ISTANZA__20.setConvertRule("Relative");
            DES_ISTANZA__20.setPreserveType(PreserveParameterType.GET);
            DES_ISTANZA__20.addExcludeParam( "MVVC" );
            DES_ISTANZA__20.addExcludeParam( "MVID" );
            DES_ISTANZA__20.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            DES_ISTANZA__20.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            DES_ISTANZA__20.addParameter( new LinkParameter( "MODULO_LISTBOX", "", ParameterSource.EXPRESSION) );
            DES_ISTANZA__20.addParameter( new LinkParameter( "ISTANZA_LISTBOX", "", ParameterSource.EXPRESSION) );
            DES_ISTANZA__20.addParameter( new LinkParameter( "MVIDP", "MVID", ParameterSource.URL) );
            AD4_DIRITTI_ACCESSO.add( DES_ISTANZA__20 );

            com.codecharge.components.Label DATI__132 = new com.codecharge.components.Label("DATI", "DATI", this );
            DATI__132.setType( com.codecharge.components.ControlType.TEXT );
            AD4_DIRITTI_ACCESSO.add(DATI__132);

            com.codecharge.components.ImageLink CaratteristicheAccesso__141 = new com.codecharge.components.ImageLink("CaratteristicheAccesso", "", this );
            CaratteristicheAccesso__141.setType( com.codecharge.components.ControlType.TEXT );
            CaratteristicheAccesso__141.setHrefSourceValue( "AD4CaratteristicheAccesso" + Names.ACTION_SUFFIX );
            CaratteristicheAccesso__141.setHrefType( "Page" );
            CaratteristicheAccesso__141.setConvertRule("Relative");
            CaratteristicheAccesso__141.setPreserveType(PreserveParameterType.NONE);
            CaratteristicheAccesso__141.addParameter( new LinkParameter( "TIPO_ACCESSO", "TIPO_ACCESSO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__141.addParameter( new LinkParameter( "PROGETTO", "PROGETTO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__141.addParameter( new LinkParameter( "MODULO", "MODULO", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__141.addParameter( new LinkParameter( "ISTANZA", "ISTANZA", ParameterSource.DATAFIELD) );
            CaratteristicheAccesso__141.addParameter( new LinkParameter( "UTENTE", "UTENTE", ParameterSource.DATAFIELD) );
            AD4_DIRITTI_ACCESSO.add( CaratteristicheAccesso__141 );

            com.codecharge.components.Label AFCNavigator__135 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__135.setType( com.codecharge.components.ControlType.TEXT );
            AD4_DIRITTI_ACCESSO.add(AFCNavigator__135);
            add(AD4_DIRITTI_ACCESSO);
        } // End definition of AD4_DIRITTI_ACCESSO grid model
//End AD4_DIRITTI_ACCESSO grid

//AD4DiacElencoSTEModel class tail @1-F5FC18C5
    }
}
//End AD4DiacElencoSTEModel class tail
