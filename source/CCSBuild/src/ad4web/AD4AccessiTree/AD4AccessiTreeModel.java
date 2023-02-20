//AD4AccessiTreeModel imports @1-1261256C
package ad4web.AD4AccessiTree;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4AccessiTreeModel imports

//AD4AccessiTreeModel class head @1-C293BDC6
public class AD4AccessiTreeModel extends com.codecharge.components.Page {
    public AD4AccessiTreeModel() {
        this( new CCSLocale(), null );
    }

    public AD4AccessiTreeModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4AccessiTreeModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4AccessiTreeModel class head

//page settings @1-AC770F47
        super("AD4AccessiTree", locale );
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

//AD4_ACCESSI grid @6-44D675EA
        
        /*
            // Begin definition of AD4_ACCESSI grid model.
        */
        {
            com.codecharge.components.Grid AD4_ACCESSI = new com.codecharge.components.Grid("AD4_ACCESSI");
            AD4_ACCESSI.setPageModel( this );
            AD4_ACCESSI.setFetchSize(20);
            AD4_ACCESSI.setVisible( true );

            com.codecharge.components.Label ALBERO__7 = new com.codecharge.components.Label("ALBERO", "ALBERO", this );
            ALBERO__7.setType( com.codecharge.components.ControlType.TEXT );
            AD4_ACCESSI.add(ALBERO__7);
            add(AD4_ACCESSI);
        } // End definition of AD4_ACCESSI grid model
//End AD4_ACCESSI grid

//TITOLO grid @31-404A717B
        
        /*
            // Begin definition of TITOLO grid model.
        */
        {
            com.codecharge.components.Grid TITOLO = new com.codecharge.components.Grid("TITOLO");
            TITOLO.setPageModel( this );
            TITOLO.setFetchSize(20);
            TITOLO.setVisible( true );

            com.codecharge.components.Label UTENTE__32 = new com.codecharge.components.Label("UTENTE", "UTENTE", this );
            UTENTE__32.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__32.setHtmlEncode( true );
            TITOLO.add(UTENTE__32);
            add(TITOLO);
        } // End definition of TITOLO grid model
//End TITOLO grid

//AccessiDettaglio grid @22-09D74405
        
        /*
            // Begin definition of AccessiDettaglio grid model.
        */
        {
            com.codecharge.components.Grid AccessiDettaglio = new com.codecharge.components.Grid("AccessiDettaglio");
            AccessiDettaglio.setPageModel( this );
            AccessiDettaglio.setFetchSize(20);
            AccessiDettaglio.setVisible( true );
            AccessiDettaglio.addGridListener( new AccessiDettaglioGridHandler() );

            com.codecharge.components.Label DES_ACCESSO__23 = new com.codecharge.components.Label("DES_ACCESSO", "DES_ACCESSO", this );
            DES_ACCESSO__23.setType( com.codecharge.components.ControlType.TEXT );
            DES_ACCESSO__23.setHtmlEncode( true );
            AccessiDettaglio.add(DES_ACCESSO__23);

            com.codecharge.components.Label DES_ORA__24 = new com.codecharge.components.Label("DES_ORA", "DES_ORA", this );
            DES_ORA__24.setType( com.codecharge.components.ControlType.TEXT );
            DES_ORA__24.setHtmlEncode( true );
            AccessiDettaglio.add(DES_ORA__24);

            com.codecharge.components.Label NOTE__30 = new com.codecharge.components.Label("NOTE", "NOTE", this );
            NOTE__30.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__30.setHtmlEncode( true );
            AccessiDettaglio.add(NOTE__30);

            com.codecharge.components.Label DSP_SESSIONE__25 = new com.codecharge.components.Label("DSP_SESSIONE", "DSP_SESSIONE", this );
            DSP_SESSIONE__25.setType( com.codecharge.components.ControlType.TEXT );
            AccessiDettaglio.add(DSP_SESSIONE__25);

            com.codecharge.components.Label AFCNavigator__26 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__26.setType( com.codecharge.components.ControlType.TEXT );
            AccessiDettaglio.add(AFCNavigator__26);
            add(AccessiDettaglio);
        } // End definition of AccessiDettaglio grid model
//End AccessiDettaglio grid

//AD4AccessiTreeModel class tail @1-F5FC18C5
    }
}
//End AD4AccessiTreeModel class tail
