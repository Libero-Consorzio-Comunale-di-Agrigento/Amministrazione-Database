//AD4UtentiRicercaModel imports @1-89A5B4A6
package ad4web.AD4UtentiRicerca;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4UtentiRicercaModel imports

//AD4UtentiRicercaModel class head @1-BCABB681
public class AD4UtentiRicercaModel extends com.codecharge.components.Page {
    public AD4UtentiRicercaModel() {
        this( new CCSLocale(), null );
    }

    public AD4UtentiRicercaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4UtentiRicercaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4UtentiRicercaModel class head

//page settings @1-1CC1199B
        super("AD4UtentiRicerca", locale );
        setResponse(response);
        addPageListener(new AD4UtentiRicercaPageHandler());
        {
            com.codecharge.components.IncludePage Header__2 = new com.codecharge.components.IncludePage("Header", this );
            Header__2.setVisible( true );
            add( Header__2 );
            com.codecharge.components.IncludePage Left__3 = new com.codecharge.components.IncludePage("Left", this );
            Left__3.setVisible( true );
            add( Left__3 );
            com.codecharge.components.IncludePage Guida__305 = new com.codecharge.components.IncludePage("Guida", this );
            Guida__305.setVisible( true );
            add( Guida__305 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//AD4Utenti_VSearch record @6-A35DA2C5
        
        /*
            Model of AD4Utenti_VSearch record defining.
        */
        {
            com.codecharge.components.Record AD4Utenti_VSearch = new com.codecharge.components.Record("AD4Utenti_VSearch");
            AD4Utenti_VSearch.setPageModel( this );
            AD4Utenti_VSearch.addExcludeParam( "ccsForm" );
            AD4Utenti_VSearch.addExcludeParam( "AD4UtentiVPage" );
            AD4Utenti_VSearch.setVisible( true );
            AD4Utenti_VSearch.setAllowInsert(false);
            AD4Utenti_VSearch.setAllowUpdate(false);
            AD4Utenti_VSearch.setAllowDelete(false);
            AD4Utenti_VSearch.setPreserveType(PreserveParameterType.GET);
            AD4Utenti_VSearch.setReturnPage("AD4UtentiRicerca" + Names.ACTION_SUFFIX);
            AD4Utenti_VSearch.addRecordListener(new AD4Utenti_VSearchRecordHandler());

            com.codecharge.components.Link IMMAGINE_FILTRO__363 = new com.codecharge.components.Link("IMMAGINE_FILTRO", "IMMAGINE_FILTRO", this );
            IMMAGINE_FILTRO__363.setType( com.codecharge.components.ControlType.TEXT );
            IMMAGINE_FILTRO__363.setHrefSourceValue( "AD4UtentiRicerca" + Names.ACTION_SUFFIX );
            IMMAGINE_FILTRO__363.setHrefType( "Page" );
            IMMAGINE_FILTRO__363.setConvertRule("Relative");
            IMMAGINE_FILTRO__363.setPreserveType(PreserveParameterType.NONE);
            AD4Utenti_VSearch.add( IMMAGINE_FILTRO__363 );

            com.codecharge.components.TextBox s_NOMINATIVO__331 = new com.codecharge.components.TextBox("s_NOMINATIVO", "", this );
            s_NOMINATIVO__331.setType( com.codecharge.components.ControlType.TEXT );
            s_NOMINATIVO__331.setHtmlEncode( true );
            AD4Utenti_VSearch.add( s_NOMINATIVO__331 );

            com.codecharge.components.Hidden DESCRIZIONE_FILTRO__362 = new com.codecharge.components.Hidden("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO", this );
            DESCRIZIONE_FILTRO__362.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE_FILTRO__362.setHtmlEncode( true );
            AD4Utenti_VSearch.add( DESCRIZIONE_FILTRO__362 );

            com.codecharge.components.TextBox s_NOME__292 = new com.codecharge.components.TextBox("s_NOME", "", this );
            s_NOME__292.setType( com.codecharge.components.ControlType.TEXT );
            s_NOME__292.setHtmlEncode( true );
            AD4Utenti_VSearch.add( s_NOME__292 );

            com.codecharge.components.TextBox s_COD_FISCALE__92 = new com.codecharge.components.TextBox("s_COD_FISCALE", "", this );
            s_COD_FISCALE__92.setType( com.codecharge.components.ControlType.TEXT );
            s_COD_FISCALE__92.setHtmlEncode( true );
            AD4Utenti_VSearch.add( s_COD_FISCALE__92 );

            com.codecharge.components.ListBox s_STATO__322 = new com.codecharge.components.ListBox("s_STATO", "", this );
            s_STATO__322.setType( com.codecharge.components.ControlType.TEXT );
            s_STATO__322.setHtmlEncode( true );
            AD4Utenti_VSearch.add( s_STATO__322 );

            com.codecharge.components.ListBox s_GRUPPO__324 = new com.codecharge.components.ListBox("s_GRUPPO", "", this );
            s_GRUPPO__324.setType( com.codecharge.components.ControlType.TEXT );
            s_GRUPPO__324.setHtmlEncode( true );
            s_GRUPPO__324.setBoundColumn( "UTENTE" );
            s_GRUPPO__324.setTextColumn( "NOMINATIVO" );
            AD4Utenti_VSearch.add( s_GRUPPO__324 );

            com.codecharge.components.ListBox s_ACCESSO__325 = new com.codecharge.components.ListBox("s_ACCESSO", "", this );
            s_ACCESSO__325.setType( com.codecharge.components.ControlType.TEXT );
            s_ACCESSO__325.setHtmlEncode( true );
            AD4Utenti_VSearch.add( s_ACCESSO__325 );

            com.codecharge.components.TextBox s_DATA_DA__284 = new com.codecharge.components.TextBox("s_DATA_DA", "", this );
            s_DATA_DA__284.setType( com.codecharge.components.ControlType.DATE );
            s_DATA_DA__284.setHtmlEncode( true );
            s_DATA_DA__284.setFormatPattern( "dd/MM/yyyy" );
            AD4Utenti_VSearch.add( s_DATA_DA__284 );

            com.codecharge.components.TextBox s_DATA_A__288 = new com.codecharge.components.TextBox("s_DATA_A", "", this );
            s_DATA_A__288.setType( com.codecharge.components.ControlType.DATE );
            s_DATA_A__288.setHtmlEncode( true );
            s_DATA_A__288.setFormatPattern( "dd/MM/yyyy" );
            AD4Utenti_VSearch.add( s_DATA_A__288 );

            com.codecharge.components.Hidden s_RICERCA__289 = new com.codecharge.components.Hidden("s_RICERCA", "", this );
            s_RICERCA__289.setType( com.codecharge.components.ControlType.TEXT );
            s_RICERCA__289.setHtmlEncode( true );
            AD4Utenti_VSearch.add( s_RICERCA__289 );

            com.codecharge.components.ListBox s_GRUPPO_LAVORO__372 = new com.codecharge.components.ListBox("s_GRUPPO_LAVORO", "", this );
            s_GRUPPO_LAVORO__372.setType( com.codecharge.components.ControlType.TEXT );
            s_GRUPPO_LAVORO__372.setHtmlEncode( true );
            s_GRUPPO_LAVORO__372.setBoundColumn( "RUOLO" );
            s_GRUPPO_LAVORO__372.setTextColumn( "DESCRIZIONE" );
            AD4Utenti_VSearch.add( s_GRUPPO_LAVORO__372 );

            com.codecharge.components.Button RicercaAvanzata__341 = new com.codecharge.components.Button("RicercaAvanzata", this);
            RicercaAvanzata__341.addExcludeParam( "ccsForm" );
            RicercaAvanzata__341.addExcludeParam( "RicercaAvanzata" );
            AD4Utenti_VSearch.add( RicercaAvanzata__341 );

            com.codecharge.components.Button DoSearch__7 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__7.addExcludeParam( "ccsForm" );
            DoSearch__7.addExcludeParam( "DoSearch" );
            DoSearch__7.setOperation( "Search" );
            AD4Utenti_VSearch.add( DoSearch__7 );

            com.codecharge.components.Link Nuovo__351 = new com.codecharge.components.Link("Nuovo", this);
            Nuovo__351.setType( com.codecharge.components.ControlType.TEXT );
            Nuovo__351.setHtmlEncode( true );
            Nuovo__351.setHrefSourceValue( "AD4Utente" + Names.ACTION_SUFFIX );
            Nuovo__351.setHrefType( "Page" );
            Nuovo__351.setConvertRule("Relative");
            Nuovo__351.setPreserveType(PreserveParameterType.NONE);
            Nuovo__351.addParameter( new LinkParameter( "MVVC", "", ParameterSource.EXPRESSION) );
            Nuovo__351.addParameter( new LinkParameter( "SE_NUOVO", "", ParameterSource.EXPRESSION) );
            AD4Utenti_VSearch.add( Nuovo__351 );
            add(AD4Utenti_VSearch);
        } // End definition of AD4Utenti_VSearch record model.
//End AD4Utenti_VSearch record

//AD4UtentiV grid @142-D18A1C99
        
        /*
            // Begin definition of AD4UtentiV grid model.
        */
        {
            com.codecharge.components.Grid AD4UtentiV = new com.codecharge.components.Grid("AD4UtentiV");
            AD4UtentiV.setPageModel( this );
            AD4UtentiV.setFetchSize(10);
            AD4UtentiV.setVisible( true );
            AD4UtentiV.addGridListener( new AD4UtentiVGridHandler() );

            com.codecharge.components.Link NOMINATIVO__278 = new com.codecharge.components.Link("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__278.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__278.setHtmlEncode( true );
            NOMINATIVO__278.setHrefSourceValue( "AD4Utente" + Names.ACTION_SUFFIX );
            NOMINATIVO__278.setHrefType( "Page" );
            NOMINATIVO__278.setConvertRule("Relative");
            NOMINATIVO__278.setPreserveType(PreserveParameterType.NONE);
            NOMINATIVO__278.addParameter( new LinkParameter( "UTENTE", "UTENTE", ParameterSource.DATAFIELD) );
            AD4UtentiV.add( NOMINATIVO__278 );

            com.codecharge.components.Hidden UTENTE__334 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__334.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__334.setHtmlEncode( true );
            AD4UtentiV.add( UTENTE__334 );

            com.codecharge.components.Label DATI_UTENTE__263 = new com.codecharge.components.Label("DATI_UTENTE", "DATI_UTENTE", this );
            DATI_UTENTE__263.setType( com.codecharge.components.ControlType.TEXT );
            AD4UtentiV.add(DATI_UTENTE__263);

            com.codecharge.components.Link COPIA_PROFILO__364 = new com.codecharge.components.Link("COPIA_PROFILO", "COPIA_PROFILO", this );
            COPIA_PROFILO__364.setType( com.codecharge.components.ControlType.TEXT );
            COPIA_PROFILO__364.setHrefSourceValue( "AD4CopiaProfilo" + Names.ACTION_SUFFIX );
            COPIA_PROFILO__364.setHrefType( "Page" );
            COPIA_PROFILO__364.setConvertRule("Relative");
            COPIA_PROFILO__364.setPreserveType(PreserveParameterType.NONE);
            COPIA_PROFILO__364.addParameter( new LinkParameter( "UTENTE_ORIGINE", "UTENTE", ParameterSource.DATAFIELD) );
            COPIA_PROFILO__364.addParameter( new LinkParameter( "UTENTE_ALIMENTARE", "UTENTE_ALIMENTARE", ParameterSource.URL) );
            AD4UtentiV.add( COPIA_PROFILO__364 );

            com.codecharge.components.Link UNIFICA_PROFILO__369 = new com.codecharge.components.Link("UNIFICA_PROFILO", "UNIFICA_PROFILO", this );
            UNIFICA_PROFILO__369.setType( com.codecharge.components.ControlType.TEXT );
            UNIFICA_PROFILO__369.setHrefSourceValue( "AD4UnificaProfilo" + Names.ACTION_SUFFIX );
            UNIFICA_PROFILO__369.setHrefType( "Page" );
            UNIFICA_PROFILO__369.setConvertRule("Relative");
            UNIFICA_PROFILO__369.setPreserveType(PreserveParameterType.NONE);
            UNIFICA_PROFILO__369.addParameter( new LinkParameter( "UTENTE_UNIFICARE", "UTENTE", ParameterSource.DATAFIELD) );
            UNIFICA_PROFILO__369.addParameter( new LinkParameter( "UTENTE_ALIMENTARE_UNIFICARE", "UTENTE_ALIMENTARE_UNIFICARE", ParameterSource.URL) );
            AD4UtentiV.add( UNIFICA_PROFILO__369 );

            com.codecharge.components.Label AFCNavigator__340 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__340.setType( com.codecharge.components.ControlType.TEXT );
            AD4UtentiV.add(AFCNavigator__340);
            add(AD4UtentiV);
        } // End definition of AD4UtentiV grid model
//End AD4UtentiV grid

//AD4UtentiRicercaModel class tail @1-F5FC18C5
    }
}
//End AD4UtentiRicercaModel class tail

