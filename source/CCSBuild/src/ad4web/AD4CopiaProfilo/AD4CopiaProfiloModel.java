//AD4CopiaProfiloModel imports @1-A7D1CFCA
package ad4web.AD4CopiaProfilo;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4CopiaProfiloModel imports

//AD4CopiaProfiloModel class head @1-1CF99C68
public class AD4CopiaProfiloModel extends com.codecharge.components.Page {
    public AD4CopiaProfiloModel() {
        this( new CCSLocale(), null );
    }

    public AD4CopiaProfiloModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4CopiaProfiloModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4CopiaProfiloModel class head

//page settings @1-0B437614
        super("AD4CopiaProfilo", locale );
        setResponse(response);
        addPageListener(new AD4CopiaProfiloPageHandler());
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

//AD4_UTENTE_ALIMENTARE record @6-90D7FBD8
        
        /*
            Model of AD4_UTENTE_ALIMENTARE record defining.
        */
        {
            com.codecharge.components.Record AD4_UTENTE_ALIMENTARE = new com.codecharge.components.Record("AD4_UTENTE_ALIMENTARE");
            AD4_UTENTE_ALIMENTARE.setPageModel( this );
            AD4_UTENTE_ALIMENTARE.addExcludeParam( "ccsForm" );
            AD4_UTENTE_ALIMENTARE.addExcludeParam( "MVVC" );
            AD4_UTENTE_ALIMENTARE.setVisible( true );
            AD4_UTENTE_ALIMENTARE.setAllowInsert(false);
            AD4_UTENTE_ALIMENTARE.setAllowDelete(false);
            AD4_UTENTE_ALIMENTARE.setPreserveType(PreserveParameterType.GET);
            AD4_UTENTE_ALIMENTARE.setReturnPage("AD4CopiaProfilo" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label UTENTE_ALIMENTARE__87 = new com.codecharge.components.Label("UTENTE_ALIMENTARE", "UTENTE_ALIMENTARE", this );
            UTENTE_ALIMENTARE__87.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(UTENTE_ALIMENTARE__87);

            com.codecharge.components.Label UTENTE_ORIGINE__88 = new com.codecharge.components.Label("UTENTE_ORIGINE", "UTENTE_ORIGINE", this );
            UTENTE_ORIGINE__88.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(UTENTE_ORIGINE__88);

            com.codecharge.components.Label NOMINATIVO_ALIMENTARE__94 = new com.codecharge.components.Label("NOMINATIVO_ALIMENTARE", "NOMINATIVO_ALIMENTARE", this );
            NOMINATIVO_ALIMENTARE__94.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(NOMINATIVO_ALIMENTARE__94);

            com.codecharge.components.Label NOMINATIVO_ORIGINE__96 = new com.codecharge.components.Label("NOMINATIVO_ORIGINE", "NOMINATIVO_ORIGINE", this );
            NOMINATIVO_ORIGINE__96.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(NOMINATIVO_ORIGINE__96);

            com.codecharge.components.Label DATI_UTENTE_ALIMENTARE__95 = new com.codecharge.components.Label("DATI_UTENTE_ALIMENTARE", "DATI_UTENTE_ALIMENTARE", this );
            DATI_UTENTE_ALIMENTARE__95.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(DATI_UTENTE_ALIMENTARE__95);

            com.codecharge.components.Label DATI_UTENTE_ORIGINE__97 = new com.codecharge.components.Label("DATI_UTENTE_ORIGINE", "DATI_UTENTE_ORIGINE", this );
            DATI_UTENTE_ORIGINE__97.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(DATI_UTENTE_ORIGINE__97);

            com.codecharge.components.Label GRUPPI_DIAC_ALIMENTARE__103 = new com.codecharge.components.Label("GRUPPI_DIAC_ALIMENTARE", "GRUPPI_DIAC_ALIMENTARE", this );
            GRUPPI_DIAC_ALIMENTARE__103.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(GRUPPI_DIAC_ALIMENTARE__103);

            com.codecharge.components.CheckBox COPIA_GRUPPI__84=  new com.codecharge.components.CheckBox( "COPIA_GRUPPI", "GRP_SI_NO", this );
            COPIA_GRUPPI__84.setType( com.codecharge.components.ControlType.TEXT );
            COPIA_GRUPPI__84.setCheckedValue( "SI" );
            COPIA_GRUPPI__84.setUncheckedValue( "NO" );
            AD4_UTENTE_ALIMENTARE.add(COPIA_GRUPPI__84);

            com.codecharge.components.Label GRUPPI_DIAC_ORIGINE__106 = new com.codecharge.components.Label("GRUPPI_DIAC_ORIGINE", "GRUPPI_DIAC_ORIGINE", this );
            GRUPPI_DIAC_ORIGINE__106.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(GRUPPI_DIAC_ORIGINE__106);

            com.codecharge.components.Label DIAC_ALIMENTARE__105 = new com.codecharge.components.Label("DIAC_ALIMENTARE", "DIAC_ALIMENTARE", this );
            DIAC_ALIMENTARE__105.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(DIAC_ALIMENTARE__105);

            com.codecharge.components.CheckBox COPIA_DIAC__85=  new com.codecharge.components.CheckBox( "COPIA_DIAC", "DIAC_SI_NO", this );
            COPIA_DIAC__85.setType( com.codecharge.components.ControlType.TEXT );
            COPIA_DIAC__85.setCheckedValue( "SI" );
            COPIA_DIAC__85.setUncheckedValue( "NO" );
            AD4_UTENTE_ALIMENTARE.add(COPIA_DIAC__85);

            com.codecharge.components.Label DIAC_ORIGINE__107 = new com.codecharge.components.Label("DIAC_ORIGINE", "DIAC_ORIGINE", this );
            DIAC_ORIGINE__107.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(DIAC_ORIGINE__107);

            com.codecharge.components.Label CAAC_ALIMENTARE__108 = new com.codecharge.components.Label("CAAC_ALIMENTARE", "CAAC_ALIMENTARE", this );
            CAAC_ALIMENTARE__108.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(CAAC_ALIMENTARE__108);

            com.codecharge.components.CheckBox COPIA_CAAC__86=  new com.codecharge.components.CheckBox( "COPIA_CAAC", "CAAC_SI_NO", this );
            COPIA_CAAC__86.setType( com.codecharge.components.ControlType.TEXT );
            COPIA_CAAC__86.setCheckedValue( "SI" );
            COPIA_CAAC__86.setUncheckedValue( "NO" );
            AD4_UTENTE_ALIMENTARE.add(COPIA_CAAC__86);

            com.codecharge.components.Label CAAC_ORIGINE__109 = new com.codecharge.components.Label("CAAC_ORIGINE", "CAAC_ORIGINE", this );
            CAAC_ORIGINE__109.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(CAAC_ORIGINE__109);

            com.codecharge.components.Button Button_Update__111 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__111.addExcludeParam( "ccsForm" );
            Button_Update__111.addExcludeParam( "Button_Update" );
            Button_Update__111.addExcludeParam( "UTENTE_ORIGINE" );
            Button_Update__111.addExcludeParam( "UTENTE_ALIMENTARE" );
            Button_Update__111.setOperation( "Update" );
            AD4_UTENTE_ALIMENTARE.add( Button_Update__111 );

            com.codecharge.components.Button Button_Cancel__99 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__99.addButtonListener(new AD4_UTENTE_ALIMENTAREButton_CancelHandler());
            Button_Cancel__99.addExcludeParam( "ccsForm" );
            Button_Cancel__99.addExcludeParam( "Button_Cancel" );
            Button_Cancel__99.addExcludeParam( "UTENTE_ORIGINE" );
            Button_Cancel__99.setOperation( "Cancel" );
            AD4_UTENTE_ALIMENTARE.add( Button_Cancel__99 );
            add(AD4_UTENTE_ALIMENTARE);
        } // End definition of AD4_UTENTE_ALIMENTARE record model.
//End AD4_UTENTE_ALIMENTARE record

//AD4CopiaProfiloModel class tail @1-F5FC18C5
    }
}
//End AD4CopiaProfiloModel class tail

