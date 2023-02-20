//AD4UnificaProfiloModel imports @1-169CFA5D
package ad4web.AD4UnificaProfilo;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4UnificaProfiloModel imports

//AD4UnificaProfiloModel class head @1-A235A2B1
public class AD4UnificaProfiloModel extends com.codecharge.components.Page {
    public AD4UnificaProfiloModel() {
        this( new CCSLocale(), null );
    }

    public AD4UnificaProfiloModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4UnificaProfiloModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4UnificaProfiloModel class head

//page settings @1-6B94A3EF
        super("AD4UnificaProfilo", locale );
        setResponse(response);
        addPageListener(new AD4UnificaProfiloPageHandler());
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

//AD4_UTENTE_ALIMENTARE record @6-59980833
        
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
            AD4_UTENTE_ALIMENTARE.setReturnPage("AD4UnificaProfilo" + Names.ACTION_SUFFIX);
            AD4_UTENTE_ALIMENTARE.addRecordListener(new AD4_UTENTE_ALIMENTARERecordHandler());

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

            com.codecharge.components.Label DATI_SOGGETTO_ALIMENTARE__117 = new com.codecharge.components.Label("DATI_SOGGETTO_ALIMENTARE", "DATI_SOGGETTO_ALIMENTARE", this );
            DATI_SOGGETTO_ALIMENTARE__117.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(DATI_SOGGETTO_ALIMENTARE__117);

            com.codecharge.components.Hidden SOGGETTO_ASSEGNARE__123 = new com.codecharge.components.Hidden("SOGGETTO_ASSEGNARE", "SOGGETTO_ALIMENTARE", this );
            SOGGETTO_ASSEGNARE__123.setType( com.codecharge.components.ControlType.INTEGER );
            SOGGETTO_ASSEGNARE__123.setHtmlEncode( true );
            AD4_UTENTE_ALIMENTARE.add( SOGGETTO_ASSEGNARE__123 );

            com.codecharge.components.Hidden SOGGETTO_ALIMENTARE__124 = new com.codecharge.components.Hidden("SOGGETTO_ALIMENTARE", "SOGGETTO_ALIMENTARE", this );
            SOGGETTO_ALIMENTARE__124.setType( com.codecharge.components.ControlType.INTEGER );
            SOGGETTO_ALIMENTARE__124.setHtmlEncode( true );
            AD4_UTENTE_ALIMENTARE.add( SOGGETTO_ALIMENTARE__124 );

            com.codecharge.components.CheckBox SOGGETTO_ORIGINE_CHK__120=  new com.codecharge.components.CheckBox( "SOGGETTO_ORIGINE_CHK", this );
            SOGGETTO_ORIGINE_CHK__120.setType( com.codecharge.components.ControlType.INTEGER );
            SOGGETTO_ORIGINE_CHK__120.setCheckedValue( 1 );
            SOGGETTO_ORIGINE_CHK__120.setUncheckedValue( 0 );
            AD4_UTENTE_ALIMENTARE.add(SOGGETTO_ORIGINE_CHK__120);

            com.codecharge.components.Label DATI_SOGGETTO_ORIGINE__118 = new com.codecharge.components.Label("DATI_SOGGETTO_ORIGINE", "DATI_SOGGETTO_ORIGINE", this );
            DATI_SOGGETTO_ORIGINE__118.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTE_ALIMENTARE.add(DATI_SOGGETTO_ORIGINE__118);

            com.codecharge.components.Hidden SOGGETTO_ORIGINE__126 = new com.codecharge.components.Hidden("SOGGETTO_ORIGINE", "SOGGETTO_ORIGINE", this );
            SOGGETTO_ORIGINE__126.setType( com.codecharge.components.ControlType.INTEGER );
            SOGGETTO_ORIGINE__126.setHtmlEncode( true );
            AD4_UTENTE_ALIMENTARE.add( SOGGETTO_ORIGINE__126 );

            com.codecharge.components.CheckBox MANTIENI_GRUPPI__113=  new com.codecharge.components.CheckBox( "MANTIENI_GRUPPI", "MANTIENI_GRP_SI_NO", this );
            MANTIENI_GRUPPI__113.setType( com.codecharge.components.ControlType.TEXT );
            MANTIENI_GRUPPI__113.setCheckedValue( "SI" );
            MANTIENI_GRUPPI__113.setUncheckedValue( "NO" );
            AD4_UTENTE_ALIMENTARE.add(MANTIENI_GRUPPI__113);

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

            com.codecharge.components.CheckBox MANTIENI_DIAC__114=  new com.codecharge.components.CheckBox( "MANTIENI_DIAC", "MANTIENI_DIAC_SI_NO", this );
            MANTIENI_DIAC__114.setType( com.codecharge.components.ControlType.TEXT );
            MANTIENI_DIAC__114.setCheckedValue( "SI" );
            MANTIENI_DIAC__114.setUncheckedValue( "NO" );
            AD4_UTENTE_ALIMENTARE.add(MANTIENI_DIAC__114);

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

            com.codecharge.components.CheckBox MANTIENI_CAAC__115=  new com.codecharge.components.CheckBox( "MANTIENI_CAAC", "MANTIENI_CAAC_SI_NO", this );
            MANTIENI_CAAC__115.setType( com.codecharge.components.ControlType.TEXT );
            MANTIENI_CAAC__115.setCheckedValue( "SI" );
            MANTIENI_CAAC__115.setUncheckedValue( "NO" );
            AD4_UTENTE_ALIMENTARE.add(MANTIENI_CAAC__115);

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
            Button_Update__111.addExcludeParam( "UTENTE_UNIFICARE" );
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

//AD4UnificaProfiloModel class tail @1-F5FC18C5
    }
}
//End AD4UnificaProfiloModel class tail
