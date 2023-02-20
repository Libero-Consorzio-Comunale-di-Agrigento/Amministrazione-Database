//AD4UtenDiacElencoModel imports @1-238C46C6
package ad4web.AD4UtenDiacElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4UtenDiacElencoModel imports

//AD4UtenDiacElencoModel class head @1-73AA611E
public class AD4UtenDiacElencoModel extends com.codecharge.components.Page {
    public AD4UtenDiacElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4UtenDiacElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4UtenDiacElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4UtenDiacElencoModel class head

//page settings @1-D8761880
        super("AD4UtenDiacElenco", locale );
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

//AD4_DIRITTI_ACCESSOSearch record @151-0DB1FC53
        
        /*
            Model of AD4_DIRITTI_ACCESSOSearch record defining.
        */
        {
            com.codecharge.components.Record AD4_DIRITTI_ACCESSOSearch = new com.codecharge.components.Record("AD4_DIRITTI_ACCESSOSearch");
            AD4_DIRITTI_ACCESSOSearch.setPageModel( this );
            AD4_DIRITTI_ACCESSOSearch.addExcludeParam( "ccsForm" );
            AD4_DIRITTI_ACCESSOSearch.addExcludeParam( "AD4_DIRITTI_ACCESSOPage" );
            AD4_DIRITTI_ACCESSOSearch.setVisible( true );
            AD4_DIRITTI_ACCESSOSearch.setAllowInsert(false);
            AD4_DIRITTI_ACCESSOSearch.setAllowUpdate(false);
            AD4_DIRITTI_ACCESSOSearch.setAllowDelete(false);
            AD4_DIRITTI_ACCESSOSearch.setPreserveType(PreserveParameterType.GET);
            AD4_DIRITTI_ACCESSOSearch.setReturnPage("AD4UtenDiacElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox s_MODULO__161 = new com.codecharge.components.ListBox("s_MODULO", "", this );
            s_MODULO__161.setType( com.codecharge.components.ControlType.TEXT );
            s_MODULO__161.setHtmlEncode( true );
            s_MODULO__161.setBoundColumn( "MODULO" );
            s_MODULO__161.setTextColumn( "DESCRIZIONE" );
            AD4_DIRITTI_ACCESSOSearch.add( s_MODULO__161 );

            com.codecharge.components.Label MODULO__176 = new com.codecharge.components.Label("MODULO", "MODULO", this );
            MODULO__176.setType( com.codecharge.components.ControlType.TEXT );
            MODULO__176.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSOSearch.add(MODULO__176);

            com.codecharge.components.ListBox s_ISTANZA__162 = new com.codecharge.components.ListBox("s_ISTANZA", "", this );
            s_ISTANZA__162.setType( com.codecharge.components.ControlType.TEXT );
            s_ISTANZA__162.setHtmlEncode( true );
            s_ISTANZA__162.setBoundColumn( "ISTANZA" );
            s_ISTANZA__162.setTextColumn( "DESCRIZIONE" );
            AD4_DIRITTI_ACCESSOSearch.add( s_ISTANZA__162 );

            com.codecharge.components.Label ISTANZA__177 = new com.codecharge.components.Label("ISTANZA", "ISTANZA", this );
            ISTANZA__177.setType( com.codecharge.components.ControlType.TEXT );
            ISTANZA__177.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSOSearch.add(ISTANZA__177);

            com.codecharge.components.ListBox s_RUOLO__163 = new com.codecharge.components.ListBox("s_RUOLO", "", this );
            s_RUOLO__163.setType( com.codecharge.components.ControlType.TEXT );
            s_RUOLO__163.setHtmlEncode( true );
            s_RUOLO__163.setBoundColumn( "RUOLO" );
            s_RUOLO__163.setTextColumn( "DESCRIZIONE" );
            AD4_DIRITTI_ACCESSOSearch.add( s_RUOLO__163 );

            com.codecharge.components.ListBox s_GRUPPO__164 = new com.codecharge.components.ListBox("s_GRUPPO", "", this );
            s_GRUPPO__164.setType( com.codecharge.components.ControlType.TEXT );
            s_GRUPPO__164.setHtmlEncode( true );
            s_GRUPPO__164.setBoundColumn( "UTENTE" );
            s_GRUPPO__164.setTextColumn( "NOMINATIVO" );
            AD4_DIRITTI_ACCESSOSearch.add( s_GRUPPO__164 );

            com.codecharge.components.Button Button_DoSearch__152 = new com.codecharge.components.Button("Button_DoSearch", this);
            Button_DoSearch__152.addExcludeParam( "ccsForm" );
            Button_DoSearch__152.addExcludeParam( "Button_DoSearch" );
            Button_DoSearch__152.setOperation( "Search" );
            AD4_DIRITTI_ACCESSOSearch.add( Button_DoSearch__152 );
            add(AD4_DIRITTI_ACCESSOSearch);
        } // End definition of AD4_DIRITTI_ACCESSOSearch record model.
//End AD4_DIRITTI_ACCESSOSearch record

//TITOLO grid @146-720ACAE3
        
        /*
            // Begin definition of TITOLO grid model.
        */
        {
            com.codecharge.components.Grid TITOLO = new com.codecharge.components.Grid("TITOLO");
            TITOLO.setPageModel( this );
            TITOLO.setFetchSize(20);
            TITOLO.setVisible( true );
            TITOLO.addGridListener( new TITOLOGridHandler() );

            com.codecharge.components.Label TITOLO__147 = new com.codecharge.components.Label("TITOLO", "TITOLO", this );
            TITOLO__147.setType( com.codecharge.components.ControlType.TEXT );
            TITOLO__147.setHtmlEncode( true );
            TITOLO.add(TITOLO__147);

            com.codecharge.components.Link INDIETRO__184 = new com.codecharge.components.Link("INDIETRO", "INDIETRO", this );
            INDIETRO__184.setType( com.codecharge.components.ControlType.TEXT );
            INDIETRO__184.setHrefSource( "AD4BP" );
            INDIETRO__184.setHrefType( "Database" );
            INDIETRO__184.setConvertRule("Relative");
            INDIETRO__184.setPreserveType(PreserveParameterType.NONE);
            TITOLO.add( INDIETRO__184 );
            add(TITOLO);
        } // End definition of TITOLO grid model
//End TITOLO grid

//AD4_DIRITTI_ACCESSO grid @6-9AB3EC9D
        
        /*
            // Begin definition of AD4_DIRITTI_ACCESSO grid model.
        */
        {
            com.codecharge.components.Grid AD4_DIRITTI_ACCESSO = new com.codecharge.components.Grid("AD4_DIRITTI_ACCESSO");
            AD4_DIRITTI_ACCESSO.setPageModel( this );
            AD4_DIRITTI_ACCESSO.setFetchSize(10);
            AD4_DIRITTI_ACCESSO.setVisible( true );
            AD4_DIRITTI_ACCESSO.addGridListener( new AD4_DIRITTI_ACCESSOGridHandler() );
            com.codecharge.components.Sorter Sorter_NOMINATIVO = new com.codecharge.components.Sorter("Sorter_NOMINATIVO", AD4_DIRITTI_ACCESSO, this);
            Sorter_NOMINATIVO.setColumn("NOMINATIVO");
            AD4_DIRITTI_ACCESSO.add(Sorter_NOMINATIVO);
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

            com.codecharge.components.Link NOMINATIVO__135 = new com.codecharge.components.Link("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__135.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__135.setHrefSourceValue( "AD4Utente" + Names.ACTION_SUFFIX );
            NOMINATIVO__135.setHrefType( "Page" );
            NOMINATIVO__135.setConvertRule("Relative");
            NOMINATIVO__135.setPreserveType(PreserveParameterType.NONE);
            NOMINATIVO__135.addParameter( new LinkParameter( "UTENTE", "UTENTE", ParameterSource.DATAFIELD) );
            AD4_DIRITTI_ACCESSO.add( NOMINATIVO__135 );

            com.codecharge.components.Label SEQUENZA__22 = new com.codecharge.components.Label("SEQUENZA", "SEQUENZA", this );
            SEQUENZA__22.setType( com.codecharge.components.ControlType.INTEGER );
            SEQUENZA__22.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO.add(SEQUENZA__22);

            com.codecharge.components.Hidden UTENTE__70 = new com.codecharge.components.Hidden("UTENTE", "UTENTE", this );
            UTENTE__70.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE__70.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO.add( UTENTE__70 );

            com.codecharge.components.Label DES_MODULO__17 = new com.codecharge.components.Label("DES_MODULO", "DES_MODULO", this );
            DES_MODULO__17.setType( com.codecharge.components.ControlType.TEXT );
            DES_MODULO__17.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO.add(DES_MODULO__17);

            com.codecharge.components.Label DES_ISTANZA__20 = new com.codecharge.components.Label("DES_ISTANZA", "DES_ISTANZA", this );
            DES_ISTANZA__20.setType( com.codecharge.components.ControlType.TEXT );
            DES_ISTANZA__20.setHtmlEncode( true );
            AD4_DIRITTI_ACCESSO.add(DES_ISTANZA__20);

            com.codecharge.components.Label DATI__132 = new com.codecharge.components.Label("DATI", "DATI", this );
            DATI__132.setType( com.codecharge.components.ControlType.TEXT );
            AD4_DIRITTI_ACCESSO.add(DATI__132);

            com.codecharge.components.Label AFCNavigator__145 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__145.setType( com.codecharge.components.ControlType.TEXT );
            AD4_DIRITTI_ACCESSO.add(AFCNavigator__145);
            add(AD4_DIRITTI_ACCESSO);
        } // End definition of AD4_DIRITTI_ACCESSO grid model
//End AD4_DIRITTI_ACCESSO grid

//AD4UtenDiacElencoModel class tail @1-F5FC18C5
    }
}
//End AD4UtenDiacElencoModel class tail

