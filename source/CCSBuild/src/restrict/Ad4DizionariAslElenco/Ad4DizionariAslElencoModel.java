//Ad4DizionariAslElencoModel imports @1-5985F120
package restrict.Ad4DizionariAslElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End Ad4DizionariAslElencoModel imports

//Ad4DizionariAslElencoModel class head @1-EE23C224
public class Ad4DizionariAslElencoModel extends com.codecharge.components.Page {
    public Ad4DizionariAslElencoModel() {
        this( new CCSLocale(), null );
    }

    public Ad4DizionariAslElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public Ad4DizionariAslElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End Ad4DizionariAslElencoModel class head

//page settings @1-8A02A7F8
        super("Ad4DizionariAslElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
            com.codecharge.components.IncludePage Ad4DizionariGuida__2 = new com.codecharge.components.IncludePage("Ad4DizionariGuida", this );
            Ad4DizionariGuida__2.setVisible( true );
            add( Ad4DizionariGuida__2 );
        } // end page
//End page settings

//SportelliFiltro record @32-5EE0676B
        
        /*
            Model of SportelliFiltro record defining.
        */
        {
            com.codecharge.components.Record SportelliFiltro = new com.codecharge.components.Record("SportelliFiltro");
            SportelliFiltro.setPageModel( this );
            SportelliFiltro.addExcludeParam( "ccsForm" );
            SportelliFiltro.addExcludeParam( "AslElencoPage" );
            SportelliFiltro.setVisible( true );
            SportelliFiltro.setAllowInsert(false);
            SportelliFiltro.setAllowUpdate(false);
            SportelliFiltro.setAllowDelete(false);
            SportelliFiltro.setPreserveType(PreserveParameterType.GET);
            SportelliFiltro.setReturnPage("Ad4DizionariAslElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Link FILTER_SEARCH__33 = new com.codecharge.components.Link("FILTER_SEARCH", "FILTER_SEARCH", this );
            FILTER_SEARCH__33.setType( com.codecharge.components.ControlType.TEXT );
            FILTER_SEARCH__33.setHrefType( "Page" );
            FILTER_SEARCH__33.setConvertRule("Relative");
            FILTER_SEARCH__33.setPreserveType(PreserveParameterType.GET);
            FILTER_SEARCH__33.addExcludeParam( "s_ASL" );
            FILTER_SEARCH__33.addExcludeParam( "s_REGIONE" );
            FILTER_SEARCH__33.addExcludeParam( "s_ATTIVA" );
            SportelliFiltro.add( FILTER_SEARCH__33 );

            com.codecharge.components.ListBox s_REGIONE__34 = new com.codecharge.components.ListBox("s_REGIONE", "S_REGIONE", this );
            s_REGIONE__34.setType( com.codecharge.components.ControlType.TEXT );
            s_REGIONE__34.setHtmlEncode( true );
            s_REGIONE__34.setCaption( "Regione" );
            SportelliFiltro.add( s_REGIONE__34 );

            com.codecharge.components.Button DoSearch__35 = new com.codecharge.components.Button("DoSearch", this);
            DoSearch__35.addExcludeParam( "ccsForm" );
            DoSearch__35.addExcludeParam( "DoSearch" );
            DoSearch__35.setOperation( "Search" );
            SportelliFiltro.add( DoSearch__35 );

            com.codecharge.components.TextBox s_ASL__36 = new com.codecharge.components.TextBox("s_ASL", "S_ASL", this );
            s_ASL__36.setType( com.codecharge.components.ControlType.TEXT );
            s_ASL__36.setHtmlEncode( true );
            s_ASL__36.setCaption( "ASL" );
            SportelliFiltro.add( s_ASL__36 );

            com.codecharge.components.ListBox s_ATTIVA__40 = new com.codecharge.components.ListBox("s_ATTIVA", "", this );
            s_ATTIVA__40.setType( com.codecharge.components.ControlType.TEXT );
            s_ATTIVA__40.setHtmlEncode( true );
            s_ATTIVA__40.setCaption( "Attiva" );
            SportelliFiltro.add( s_ATTIVA__40 );
            add(SportelliFiltro);
        } // End definition of SportelliFiltro record model.
//End SportelliFiltro record

//AslElenco grid @8-B4B8D9BA
        
        /*
            // Begin definition of AslElenco grid model.
        */
        {
            com.codecharge.components.Grid AslElenco = new com.codecharge.components.Grid("AslElenco");
            AslElenco.setPageModel( this );
            AslElenco.setFetchSize(25);
            AslElenco.setVisible( true );
            AslElenco.addGridListener( new AslElencoGridHandler() );

            com.codecharge.components.Link Aggiungi__9 = new com.codecharge.components.Link("Aggiungi", this);
            Aggiungi__9.setType( com.codecharge.components.ControlType.TEXT );
            Aggiungi__9.setHtmlEncode( true );
            Aggiungi__9.addControlListener( new AslElencoAggiungiHandler());
            Aggiungi__9.setHrefType( "Page" );
            Aggiungi__9.setConvertRule("Relative");
            Aggiungi__9.setPreserveType(PreserveParameterType.GET);
            Aggiungi__9.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            AslElenco.add( Aggiungi__9 );

            com.codecharge.components.Label REGIONE_ASL__29 = new com.codecharge.components.Label("REGIONE_ASL", "REGIONE_ASL", this );
            REGIONE_ASL__29.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE_ASL__29.setHtmlEncode( true );
            AslElenco.add(REGIONE_ASL__29);

            com.codecharge.components.Label CODICE_ASL__30 = new com.codecharge.components.Label("CODICE_ASL", "CODICE_ASL", this );
            CODICE_ASL__30.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_ASL__30.setHtmlEncode( true );
            AslElenco.add(CODICE_ASL__30);

            com.codecharge.components.Link DESCRIZIONE__13 = new com.codecharge.components.Link("DESCRIZIONE", "DESCRIZIONE", this );
            DESCRIZIONE__13.setType( com.codecharge.components.ControlType.TEXT );
            DESCRIZIONE__13.setHtmlEncode( true );
            DESCRIZIONE__13.setHrefType( "Page" );
            DESCRIZIONE__13.setConvertRule("Relative");
            DESCRIZIONE__13.setPreserveType(PreserveParameterType.GET);
            DESCRIZIONE__13.addParameter( new LinkParameter( "MVPG", "", ParameterSource.EXPRESSION) );
            DESCRIZIONE__13.addParameter( new LinkParameter( "REGIONE_ASL", "REGIONE_ASL", ParameterSource.DATAFIELD) );
            DESCRIZIONE__13.addParameter( new LinkParameter( "CODICE_ASL", "CODICE_ASL", ParameterSource.DATAFIELD) );
            AslElenco.add( DESCRIZIONE__13 );

            com.codecharge.components.Label REGIONE_DENOMINAZIONE__16 = new com.codecharge.components.Label("REGIONE_DENOMINAZIONE", "REGIONE_DENOMINAZIONE", this );
            REGIONE_DENOMINAZIONE__16.setType( com.codecharge.components.ControlType.TEXT );
            REGIONE_DENOMINAZIONE__16.setHtmlEncode( true );
            AslElenco.add(REGIONE_DENOMINAZIONE__16);

            com.codecharge.components.CheckBox ATTIVA__39=  new com.codecharge.components.CheckBox( "ATTIVA", "ATTIVA", this );
            ATTIVA__39.setType( com.codecharge.components.ControlType.TEXT );
            ATTIVA__39.setCheckedValue( "S" );
            ATTIVA__39.setUncheckedValue( "N" );
            AslElenco.add(ATTIVA__39);

            com.codecharge.components.Label UTENTE_AGGIORNAMENTO__21 = new com.codecharge.components.Label("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO", this );
            UTENTE_AGGIORNAMENTO__21.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_AGGIORNAMENTO__21.setHtmlEncode( true );
            AslElenco.add(UTENTE_AGGIORNAMENTO__21);

            com.codecharge.components.Label DATA_AGGIORNAMENTO__20 = new com.codecharge.components.Label("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO", this );
            DATA_AGGIORNAMENTO__20.setType( com.codecharge.components.ControlType.TEXT );
            DATA_AGGIORNAMENTO__20.setHtmlEncode( true );
            AslElenco.add(DATA_AGGIORNAMENTO__20);

            com.codecharge.components.Label AFCNavigator__22 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__22.setType( com.codecharge.components.ControlType.TEXT );
            AslElenco.add(AFCNavigator__22);
            add(AslElenco);
        } // End definition of AslElenco grid model
//End AslElenco grid

//Ad4DizionariAslElencoModel class tail @1-F5FC18C5
    }
}
//End Ad4DizionariAslElencoModel class tail

