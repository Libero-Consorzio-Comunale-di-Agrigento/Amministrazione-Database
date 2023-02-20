//AD4GrupElencoModel imports @1-BC310C09
package ad4web.AD4GrupElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GrupElencoModel imports

//AD4GrupElencoModel class head @1-228CCD71
public class AD4GrupElencoModel extends com.codecharge.components.Page {
    public AD4GrupElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4GrupElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GrupElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GrupElencoModel class head

//page settings @1-051BD16A
        super("AD4GrupElenco", locale );
        setResponse(response);
        addPageListener(new AD4GrupElencoPageHandler());
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

//DIRITTI_ACCESSO grid @146-6B8C8B8F
        
        /*
            // Begin definition of DIRITTI_ACCESSO grid model.
        */
        {
            com.codecharge.components.Grid DIRITTI_ACCESSO = new com.codecharge.components.Grid("DIRITTI_ACCESSO");
            DIRITTI_ACCESSO.setPageModel( this );
            DIRITTI_ACCESSO.setFetchSize(10);
            DIRITTI_ACCESSO.setVisible( true );

            com.codecharge.components.Label NOMINATIVO__148 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__148.setType( com.codecharge.components.ControlType.TEXT );
            DIRITTI_ACCESSO.add(NOMINATIVO__148);
            add(DIRITTI_ACCESSO);
        } // End definition of DIRITTI_ACCESSO grid model
//End DIRITTI_ACCESSO grid

//AD4_GRUPPI grid @6-3F1AA8F3
        
        /*
            // Begin definition of AD4_GRUPPI grid model.
        */
        {
            com.codecharge.components.Grid AD4_GRUPPI = new com.codecharge.components.Grid("AD4_GRUPPI");
            AD4_GRUPPI.setPageModel( this );
            AD4_GRUPPI.setFetchSize(10);
            AD4_GRUPPI.setVisible( true );
            AD4_GRUPPI.addGridListener( new AD4_GRUPPIGridHandler() );
            com.codecharge.components.Sorter Sorter_GRUPPO = new com.codecharge.components.Sorter("Sorter_GRUPPO", AD4_GRUPPI, this);
            Sorter_GRUPPO.setColumn("GRUPPO");
            AD4_GRUPPI.add(Sorter_GRUPPO);
            com.codecharge.components.Sorter Sorter_DESCRIZIONE = new com.codecharge.components.Sorter("Sorter_DESCRIZIONE", AD4_GRUPPI, this);
            Sorter_DESCRIZIONE.setColumn("DESCRIZIONE");
            AD4_GRUPPI.add(Sorter_DESCRIZIONE);
            com.codecharge.components.Sorter Sorter_ISTANZA = new com.codecharge.components.Sorter("Sorter_ISTANZA", AD4_GRUPPI, this);
            Sorter_ISTANZA.setColumn("ISTANZA");
            AD4_GRUPPI.add(Sorter_ISTANZA);

            com.codecharge.components.Label GRUPPO__22 = new com.codecharge.components.Label("GRUPPO", "GRUPPO", this );
            GRUPPO__22.setType( com.codecharge.components.ControlType.TEXT );
            AD4_GRUPPI.add(GRUPPO__22);

            com.codecharge.components.Hidden UTENTE__70 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__70.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__70.setHtmlEncode( true );
            AD4_GRUPPI.add( UTENTE__70 );

            com.codecharge.components.Label DESCRIZIONE__17 = new com.codecharge.components.Label("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__17.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__17.setHtmlEncode( true );
            AD4_GRUPPI.add(DESCRIZIONE__17);

            com.codecharge.components.Label IMPORTANZA__20 = new com.codecharge.components.Label("IMPORTANZA", "IMPORTANZA", this );
            IMPORTANZA__20.setType( com.codecharge.components.ControlType.TEXT );
            IMPORTANZA__20.setHtmlEncode( true );
            AD4_GRUPPI.add(IMPORTANZA__20);

            com.codecharge.components.Label AFCNavigator__135 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__135.setType( com.codecharge.components.ControlType.TEXT );
            AD4_GRUPPI.add(AFCNavigator__135);
            add(AD4_GRUPPI);
        } // End definition of AD4_GRUPPI grid model
//End AD4_GRUPPI grid

//AD4GrupElencoModel class tail @1-F5FC18C5
    }
}
//End AD4GrupElencoModel class tail
