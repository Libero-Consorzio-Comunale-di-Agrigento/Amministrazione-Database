//AD4RichiestaParametriModel imports @1-86FCC237
package ad4web.AD4RichiestaParametri;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4RichiestaParametriModel imports

//AD4RichiestaParametriModel class head @1-BBBD8199
public class AD4RichiestaParametriModel extends com.codecharge.components.Page {
    public AD4RichiestaParametriModel() {
        this( new CCSLocale(), null );
    }

    public AD4RichiestaParametriModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4RichiestaParametriModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4RichiestaParametriModel class head

//page settings @1-96B3C267
        super("AD4RichiestaParametri", locale );
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

//Titolo grid @135-EF797916
        
        /*
            // Begin definition of Titolo grid model.
        */
        {
            com.codecharge.components.Grid Titolo = new com.codecharge.components.Grid("Titolo");
            Titolo.setPageModel( this );
            Titolo.setFetchSize(10);
            Titolo.setVisible( true );

            com.codecharge.components.ImageLink Nuovo__143 = new com.codecharge.components.ImageLink("Nuovo", "1", this );
            Nuovo__143.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__143.setHrefSourceValue( "AD4ModParametri" + Names.ACTION_SUFFIX );
            Nuovo__143.setHrefType( "Page" );
            Nuovo__143.setConvertRule("Relative");
            Nuovo__143.setPreserveType(PreserveParameterType.GET);
            Nuovo__143.addParameter( new LinkParameter( "ID", "ID", ParameterSource.URL) );
            Titolo.add( Nuovo__143 );
            add(Titolo);
        } // End definition of Titolo grid model
//End Titolo grid

//PARAMETRI_RICHIESTA grid @6-C9EC2DF1
        
        /*
            // Begin definition of PARAMETRI_RICHIESTA grid model.
        */
        {
            com.codecharge.components.Grid PARAMETRI_RICHIESTA = new com.codecharge.components.Grid("PARAMETRI_RICHIESTA");
            PARAMETRI_RICHIESTA.setPageModel( this );
            PARAMETRI_RICHIESTA.setFetchSize(20);
            PARAMETRI_RICHIESTA.setVisible( true );
            PARAMETRI_RICHIESTA.addGridListener( new PARAMETRI_RICHIESTAGridHandler() );

            com.codecharge.components.Hidden ID_PARAMETRO__166 = new com.codecharge.components.Hidden("ID_PARAMETRO", "ID_PARAMETRO", this );
            ID_PARAMETRO__166.setType( com.codecharge.components.ControlType.TEXT );
            ID_PARAMETRO__166.setHtmlEncode( true );
            PARAMETRI_RICHIESTA.add( ID_PARAMETRO__166 );

            com.codecharge.components.Hidden ID_RICHIESTA__167 = new com.codecharge.components.Hidden("ID_RICHIESTA", "ID_RICHIESTA", this );
            ID_RICHIESTA__167.setType( com.codecharge.components.ControlType.TEXT );
            ID_RICHIESTA__167.setHtmlEncode( true );
            PARAMETRI_RICHIESTA.add( ID_RICHIESTA__167 );

            com.codecharge.components.Link PARAMETRO__118 = new com.codecharge.components.Link("PARAMETRO", "PARAMETRO", this );
            PARAMETRO__118.setType( com.codecharge.components.ControlType.TEXT );
            PARAMETRO__118.setHtmlEncode( true );
            PARAMETRO__118.setHrefSourceValue( "AD4ModParametri" + Names.ACTION_SUFFIX );
            PARAMETRO__118.setHrefType( "Page" );
            PARAMETRO__118.setConvertRule("Relative");
            PARAMETRO__118.setPreserveType(PreserveParameterType.GET);
            PARAMETRO__118.addParameter( new LinkParameter( "IDPAR", "ID_PARAMETRO", ParameterSource.DATAFIELD) );
            PARAMETRI_RICHIESTA.add( PARAMETRO__118 );

            com.codecharge.components.Label VALORE__120 = new com.codecharge.components.Label("VALORE", "VALORE", this );
            VALORE__120.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__120.setHtmlEncode( true );
            PARAMETRI_RICHIESTA.add(VALORE__120);

            com.codecharge.components.Link Indietro__168 = new com.codecharge.components.Link("Indietro", this);
            Indietro__168.setType( com.codecharge.components.ControlType.TEXT );
            Indietro__168.setHtmlEncode( true );
            Indietro__168.setHrefSourceValue( "../amvadm/AdmRichiesta" + Names.ACTION_SUFFIX );
            Indietro__168.setHrefType( "Page" );
            Indietro__168.setConvertRule("Relative");
            Indietro__168.setPreserveType(PreserveParameterType.GET);
            PARAMETRI_RICHIESTA.add( Indietro__168 );
            add(PARAMETRI_RICHIESTA);
        } // End definition of PARAMETRI_RICHIESTA grid model
//End PARAMETRI_RICHIESTA grid

//AD4RichiestaParametriModel class tail @1-F5FC18C5
    }
}
//End AD4RichiestaParametriModel class tail
