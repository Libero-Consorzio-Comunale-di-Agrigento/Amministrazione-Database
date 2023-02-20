//AD4GruppiUtenteElencoModel imports @1-6031EE5D
package ad4web.AD4GruppiUtenteElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GruppiUtenteElencoModel imports

//AD4GruppiUtenteElencoModel class head @1-11A53A65
public class AD4GruppiUtenteElencoModel extends com.codecharge.components.Page {
    public AD4GruppiUtenteElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4GruppiUtenteElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GruppiUtenteElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GruppiUtenteElencoModel class head

//page settings @1-832C208A
        super("AD4GruppiUtenteElenco", locale );
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

//AD4_UTENTI record @78-09BCFAC5
        
        /*
            Model of AD4_UTENTI record defining.
        */
        {
            com.codecharge.components.Record AD4_UTENTI = new com.codecharge.components.Record("AD4_UTENTI");
            AD4_UTENTI.setPageModel( this );
            AD4_UTENTI.addExcludeParam( "ccsForm" );
            AD4_UTENTI.setVisible( true );
            AD4_UTENTI.setAllowInsert(false);
            AD4_UTENTI.setAllowUpdate(false);
            AD4_UTENTI.setAllowDelete(false);
            AD4_UTENTI.setPreserveType(PreserveParameterType.GET);
            AD4_UTENTI.setReturnPage("AD4GruppiUtenteElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label NOMINATIVO__80 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__80.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__80.setHtmlEncode( true );
            AD4_UTENTI.add(NOMINATIVO__80);
            add(AD4_UTENTI);
        } // End definition of AD4_UTENTI record model.
//End AD4_UTENTI record

//DISPONIBILI record @52-458A9F45
        
        /*
            Model of DISPONIBILI record defining.
        */
        {
            com.codecharge.components.Record DISPONIBILI = new com.codecharge.components.Record("DISPONIBILI");
            DISPONIBILI.setPageModel( this );
            DISPONIBILI.addExcludeParam( "ccsForm" );
            DISPONIBILI.setVisible( true );
            DISPONIBILI.setAllowInsert(false);
            DISPONIBILI.setPreserveType(PreserveParameterType.GET);
            DISPONIBILI.setReturnPage("AD4GruppiUtenteElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox UTENTE_D__53 = new com.codecharge.components.ListBox("UTENTE_D", "", this );
            UTENTE_D__53.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_D__53.setHtmlEncode( true );
            UTENTE_D__53.setBoundColumn( "UTENTE" );
            UTENTE_D__53.setTextColumn( "NOME_UTENTE" );
            DISPONIBILI.add( UTENTE_D__53 );

            com.codecharge.components.Button Tutti__55 = new com.codecharge.components.Button("Tutti", this);
            Tutti__55.addExcludeParam( "ccsForm" );
            Tutti__55.addExcludeParam( "Tutti" );
            Tutti__55.setOperation( "Delete" );
            DISPONIBILI.add( Tutti__55 );

            com.codecharge.components.Button Uno__56 = new com.codecharge.components.Button("Uno", this);
            Uno__56.addExcludeParam( "ccsForm" );
            Uno__56.addExcludeParam( "Uno" );
            Uno__56.setOperation( "Update" );
            DISPONIBILI.add( Uno__56 );
            add(DISPONIBILI);
        } // End definition of DISPONIBILI record model.
//End DISPONIBILI record

//ASSEGNATI record @63-3A4CE214
        
        /*
            Model of ASSEGNATI record defining.
        */
        {
            com.codecharge.components.Record ASSEGNATI = new com.codecharge.components.Record("ASSEGNATI");
            ASSEGNATI.setPageModel( this );
            ASSEGNATI.addExcludeParam( "ccsForm" );
            ASSEGNATI.setVisible( true );
            ASSEGNATI.setAllowInsert(false);
            ASSEGNATI.setPreserveType(PreserveParameterType.GET);
            ASSEGNATI.setReturnPage("AD4GruppiUtenteElenco" + Names.ACTION_SUFFIX);

            com.codecharge.components.ListBox UTENTE_A__64 = new com.codecharge.components.ListBox("UTENTE_A", "", this );
            UTENTE_A__64.setType( com.codecharge.components.ControlType.TEXT );
            UTENTE_A__64.setHtmlEncode( true );
            UTENTE_A__64.setBoundColumn( "UTENTE" );
            UTENTE_A__64.setTextColumn( "NOME_UTENTE" );
            ASSEGNATI.add( UTENTE_A__64 );

            com.codecharge.components.Button Uno__66 = new com.codecharge.components.Button("Uno", this);
            Uno__66.addExcludeParam( "ccsForm" );
            Uno__66.addExcludeParam( "Uno" );
            Uno__66.setOperation( "Update" );
            ASSEGNATI.add( Uno__66 );

            com.codecharge.components.Button Tutti__68 = new com.codecharge.components.Button("Tutti", this);
            Tutti__68.addExcludeParam( "ccsForm" );
            Tutti__68.addExcludeParam( "Tutti" );
            Tutti__68.setOperation( "Delete" );
            ASSEGNATI.add( Tutti__68 );
            add(ASSEGNATI);
        } // End definition of ASSEGNATI record model.
//End ASSEGNATI record

//AD4GruppiUtenteElencoModel class tail @1-F5FC18C5
    }
}
//End AD4GruppiUtenteElencoModel class tail

