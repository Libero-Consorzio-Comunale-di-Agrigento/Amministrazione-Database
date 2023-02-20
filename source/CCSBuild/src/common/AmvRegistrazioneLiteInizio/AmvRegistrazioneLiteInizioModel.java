//AmvRegistrazioneLiteInizioModel imports @1-6C4E9038
package common.AmvRegistrazioneLiteInizio;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AmvRegistrazioneLiteInizioModel imports

//AmvRegistrazioneLiteInizioModel class head @1-E55F215F
public class AmvRegistrazioneLiteInizioModel extends com.codecharge.components.Page {
    public AmvRegistrazioneLiteInizioModel() {
        this( new CCSLocale(), null );
    }

    public AmvRegistrazioneLiteInizioModel(CCSLocale locale) {
        this( locale, null );
    }

    public AmvRegistrazioneLiteInizioModel( CCSLocale locale, HttpServletResponse response ) {
//End AmvRegistrazioneLiteInizioModel class head

//page settings @1-07D0A8E8
        super("AmvRegistrazioneLiteInizio", locale );
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

//PRIVACY grid @14-00E15752
        
        /*
            // Begin definition of PRIVACY grid model.
        */
        {
            com.codecharge.components.Grid PRIVACY = new com.codecharge.components.Grid("PRIVACY");
            PRIVACY.setPageModel( this );
            PRIVACY.setFetchSize(20);
            PRIVACY.setVisible( true );

            com.codecharge.components.Label PRIVACY__15 = new com.codecharge.components.Label("PRIVACY", "PRIVACY", this );
            PRIVACY__15.setType( com.codecharge.components.ControlType.TEXT );
            PRIVACY.add(PRIVACY__15);
            add(PRIVACY);
        } // End definition of PRIVACY grid model
//End PRIVACY grid

//AD4_UTENTE record @6-89AA898E
        
        /*
            Model of AD4_UTENTE record defining.
        */
        {
            com.codecharge.components.Record AD4_UTENTE = new com.codecharge.components.Record("AD4_UTENTE");
            AD4_UTENTE.setPageModel( this );
            AD4_UTENTE.addExcludeParam( "ccsForm" );
            AD4_UTENTE.setVisible( true );
            AD4_UTENTE.setAllowInsert(false);
            AD4_UTENTE.setAllowDelete(false);
            AD4_UTENTE.setPreserveType(PreserveParameterType.GET);
            AD4_UTENTE.setReturnPage("AmvRegistrazioneLiteInizio" + Names.ACTION_SUFFIX);
            AD4_UTENTE.addRecordListener(new AD4_UTENTERecordHandler());

            com.codecharge.components.TextBox NOMINATIVO__18 = new com.codecharge.components.TextBox("NOMINATIVO", "", this );
            NOMINATIVO__18.setType( com.codecharge.components.ControlType.TEXT );
            NOMINATIVO__18.setHtmlEncode( true );
            NOMINATIVO__18.setCaption( "NOMINATIVO" );
            AD4_UTENTE.add( NOMINATIVO__18 );

            com.codecharge.components.TextBox COGNOME__19 = new com.codecharge.components.TextBox("COGNOME", "", this );
            COGNOME__19.setType( com.codecharge.components.ControlType.TEXT );
            COGNOME__19.setHtmlEncode( true );
            COGNOME__19.setCaption( "COGNOME" );
            AD4_UTENTE.add( COGNOME__19 );

            com.codecharge.components.TextBox NOME__20 = new com.codecharge.components.TextBox("NOME", "", this );
            NOME__20.setType( com.codecharge.components.ControlType.TEXT );
            NOME__20.setHtmlEncode( true );
            NOME__20.setCaption( "NOME" );
            AD4_UTENTE.add( NOME__20 );

            com.codecharge.components.Label POSTA_OBBL1__47 = new com.codecharge.components.Label("POSTA_OBBL1", "POSTA_OBBL", this );
            POSTA_OBBL1__47.setType( com.codecharge.components.ControlType.TEXT );
            POSTA_OBBL1__47.setHtmlEncode( true );
            AD4_UTENTE.add(POSTA_OBBL1__47);

            com.codecharge.components.ListBox VIA__35 = new com.codecharge.components.ListBox("VIA", "", this );
            VIA__35.setType( com.codecharge.components.ControlType.TEXT );
            VIA__35.setHtmlEncode( true );
            VIA__35.setCaption( "VIA" );
            AD4_UTENTE.add( VIA__35 );

            com.codecharge.components.TextBox INDIRIZZO__36 = new com.codecharge.components.TextBox("INDIRIZZO", "", this );
            INDIRIZZO__36.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO__36.setHtmlEncode( true );
            INDIRIZZO__36.setCaption( "INDIRIZZO" );
            AD4_UTENTE.add( INDIRIZZO__36 );

            com.codecharge.components.TextBox NUM__37 = new com.codecharge.components.TextBox("NUM", "", this );
            NUM__37.setType( com.codecharge.components.ControlType.TEXT );
            NUM__37.setHtmlEncode( true );
            NUM__37.setCaption( "NUM" );
            AD4_UTENTE.add( NUM__37 );

            com.codecharge.components.Label POSTA_OBBL2__48 = new com.codecharge.components.Label("POSTA_OBBL2", "POSTA_OBBL", this );
            POSTA_OBBL2__48.setType( com.codecharge.components.ControlType.TEXT );
            POSTA_OBBL2__48.setHtmlEncode( true );
            AD4_UTENTE.add(POSTA_OBBL2__48);

            com.codecharge.components.ListBox PROVINCIA__38 = new com.codecharge.components.ListBox("PROVINCIA", "", this );
            PROVINCIA__38.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA__38.setHtmlEncode( true );
            PROVINCIA__38.setCaption( "PROVINCIA" );
            PROVINCIA__38.setBoundColumn( "PROVINCIA" );
            PROVINCIA__38.setTextColumn( "DENOMINAZIONE" );
            AD4_UTENTE.add( PROVINCIA__38 );

            com.codecharge.components.Label POSTA_OBBL3__49 = new com.codecharge.components.Label("POSTA_OBBL3", "POSTA_OBBL", this );
            POSTA_OBBL3__49.setType( com.codecharge.components.ControlType.TEXT );
            POSTA_OBBL3__49.setHtmlEncode( true );
            AD4_UTENTE.add(POSTA_OBBL3__49);

            com.codecharge.components.ListBox COMUNE__40 = new com.codecharge.components.ListBox("COMUNE", "", this );
            COMUNE__40.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE__40.setHtmlEncode( true );
            COMUNE__40.setCaption( "COMUNE" );
            COMUNE__40.setBoundColumn( "COMUNE" );
            COMUNE__40.setTextColumn( "DENOMINAZIONE" );
            AD4_UTENTE.add( COMUNE__40 );

            com.codecharge.components.Label POSTA_OBBL4__50 = new com.codecharge.components.Label("POSTA_OBBL4", "POSTA_OBBL", this );
            POSTA_OBBL4__50.setType( com.codecharge.components.ControlType.TEXT );
            POSTA_OBBL4__50.setHtmlEncode( true );
            AD4_UTENTE.add(POSTA_OBBL4__50);

            com.codecharge.components.TextBox CAP__43 = new com.codecharge.components.TextBox("CAP", "", this );
            CAP__43.setType( com.codecharge.components.ControlType.TEXT );
            CAP__43.setHtmlEncode( true );
            CAP__43.setCaption( "CAP" );
            AD4_UTENTE.add( CAP__43 );

            com.codecharge.components.Label MAIL_OBBL1__51 = new com.codecharge.components.Label("MAIL_OBBL1", "MAIL_OBBL", this );
            MAIL_OBBL1__51.setType( com.codecharge.components.ControlType.TEXT );
            MAIL_OBBL1__51.setHtmlEncode( true );
            AD4_UTENTE.add(MAIL_OBBL1__51);

            com.codecharge.components.TextBox INDIRIZZO_WEB__44 = new com.codecharge.components.TextBox("INDIRIZZO_WEB", "", this );
            INDIRIZZO_WEB__44.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO_WEB__44.setHtmlEncode( true );
            INDIRIZZO_WEB__44.setCaption( "E-MAIL" );
            INDIRIZZO_WEB__44.addValidateHandler( new  RegexpJDK14Handler( "^[\\w\\.-]{1,}\\@([\\da-zA-Z-]{1,}\\.){1,}[\\da-zA-Z-]+$", "Validazione errata per il campo E-MAIL." ));
            AD4_UTENTE.add( INDIRIZZO_WEB__44 );

            com.codecharge.components.Label SMS_OBBL1__52 = new com.codecharge.components.Label("SMS_OBBL1", "SMS_OBBL", this );
            SMS_OBBL1__52.setType( com.codecharge.components.ControlType.TEXT );
            SMS_OBBL1__52.setHtmlEncode( true );
            AD4_UTENTE.add(SMS_OBBL1__52);

            com.codecharge.components.TextBox TELEFONO__45 = new com.codecharge.components.TextBox("TELEFONO", "", this );
            TELEFONO__45.setType( com.codecharge.components.ControlType.TEXT );
            TELEFONO__45.setHtmlEncode( true );
            TELEFONO__45.setCaption( "TELEFONO" );
            AD4_UTENTE.add( TELEFONO__45 );

            com.codecharge.components.Label FAX_OBBL1__53 = new com.codecharge.components.Label("FAX_OBBL1", "FAX_OBBL", this );
            FAX_OBBL1__53.setType( com.codecharge.components.ControlType.TEXT );
            FAX_OBBL1__53.setHtmlEncode( true );
            AD4_UTENTE.add(FAX_OBBL1__53);

            com.codecharge.components.TextBox FAX__46 = new com.codecharge.components.TextBox("FAX", "", this );
            FAX__46.setType( com.codecharge.components.ControlType.TEXT );
            FAX__46.setHtmlEncode( true );
            FAX__46.setCaption( "FAX" );
            AD4_UTENTE.add( FAX__46 );

            com.codecharge.components.Hidden RR__60 = new com.codecharge.components.Hidden("RR", "", this );
            RR__60.setType( com.codecharge.components.ControlType.TEXT );
            RR__60.setHtmlEncode( true );
            RR__60.setCaption( "Rimuovi Richiesta" );
            AD4_UTENTE.add( RR__60 );

            com.codecharge.components.Hidden REDIRECTION__65 = new com.codecharge.components.Hidden("REDIRECTION", "REDIRECTION", this );
            REDIRECTION__65.setType( com.codecharge.components.ControlType.TEXT );
            REDIRECTION__65.setHtmlEncode( true );
            AD4_UTENTE.add( REDIRECTION__65 );

            com.codecharge.components.Button Button_Insert__9 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__9.addExcludeParam( "ccsForm" );
            Button_Insert__9.addExcludeParam( "Button_Insert" );
            Button_Insert__9.setOperation( "Insert" );
            AD4_UTENTE.add( Button_Insert__9 );

            com.codecharge.components.Button Button_Update__10 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__10.addExcludeParam( "ccsForm" );
            Button_Update__10.addExcludeParam( "Button_Update" );
            Button_Update__10.setOperation( "Update" );
            AD4_UTENTE.add( Button_Update__10 );

            com.codecharge.components.Button Button_Delete__11 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__11.addExcludeParam( "ccsForm" );
            Button_Delete__11.addExcludeParam( "Button_Delete" );
            Button_Delete__11.setOperation( "Delete" );
            AD4_UTENTE.add( Button_Delete__11 );
            add(AD4_UTENTE);
        } // End definition of AD4_UTENTE record model.
//End AD4_UTENTE record

//AmvRegistrazioneLiteInizioModel class tail @1-F5FC18C5
    }
}
//End AmvRegistrazioneLiteInizioModel class tail
