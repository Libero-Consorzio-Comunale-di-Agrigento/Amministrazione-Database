//AD4SoggettoInclusaModel imports @1-4FDAAADC
package ad4web.AD4SoggettoInclusa;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4SoggettoInclusaModel imports

//AD4SoggettoInclusaModel class head @1-CF6684F4
public class AD4SoggettoInclusaModel extends com.codecharge.components.Page {
    public AD4SoggettoInclusaModel() {
        this( new CCSLocale(), null );
    }

    public AD4SoggettoInclusaModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4SoggettoInclusaModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4SoggettoInclusaModel class head

//page settings @1-17F802AE
        super("AD4SoggettoInclusa", locale );
        setResponse(response);
        setIncluded(true);
        addPageListener(new AD4SoggettoInclusaPageHandler());
        {
        } // end page
//End page settings

//AD4_SOGGETTO record @6-A4232BD6
        
        /*
            Model of AD4_SOGGETTO record defining.
        */
        {
            com.codecharge.components.Record AD4_SOGGETTO = new com.codecharge.components.Record("AD4_SOGGETTO");
            AD4_SOGGETTO.setPageModel( this );
            AD4_SOGGETTO.addExcludeParam( "ccsForm" );
            AD4_SOGGETTO.setVisible( true );
            AD4_SOGGETTO.setAllowDelete(false);
            AD4_SOGGETTO.setPreserveType(PreserveParameterType.GET);
            AD4_SOGGETTO.setReturnPage("AD4SoggettoInclusa" + Names.ACTION_SUFFIX);
            AD4_SOGGETTO.addRecordListener(new AD4_SOGGETTORecordHandler());

            com.codecharge.components.Label ID_SOGGETTO__158 = new com.codecharge.components.Label("ID_SOGGETTO", "SOGGETTO", this );
            ID_SOGGETTO__158.setType( com.codecharge.components.ControlType.TEXT );
            ID_SOGGETTO__158.setHtmlEncode( true );
            AD4_SOGGETTO.add(ID_SOGGETTO__158);

            com.codecharge.components.TextBox COGNOME__160 = new com.codecharge.components.TextBox("COGNOME", "COGNOME", this );
            COGNOME__160.setType( com.codecharge.components.ControlType.TEXT );
            COGNOME__160.setHtmlEncode( true );
            COGNOME__160.setCaption( "COGNOME" );
            AD4_SOGGETTO.add( COGNOME__160 );

            com.codecharge.components.TextBox NOME__162 = new com.codecharge.components.TextBox("NOME", "NOME", this );
            NOME__162.setType( com.codecharge.components.ControlType.TEXT );
            NOME__162.setHtmlEncode( true );
            NOME__162.setCaption( "NOME" );
            AD4_SOGGETTO.add( NOME__162 );

            com.codecharge.components.Hidden SOGGETTO__113 = new com.codecharge.components.Hidden("SOGGETTO", "SOGGETTO", this );
            SOGGETTO__113.setType( com.codecharge.components.ControlType.TEXT );
            SOGGETTO__113.setHtmlEncode( true );
            AD4_SOGGETTO.add( SOGGETTO__113 );

            com.codecharge.components.ListBox SESSO__161 = new com.codecharge.components.ListBox("SESSO", "SESSO", this );
            SESSO__161.setType( com.codecharge.components.ControlType.TEXT );
            SESSO__161.setHtmlEncode( true );
            SESSO__161.setCaption( "SESSO" );
            AD4_SOGGETTO.add( SESSO__161 );

            com.codecharge.components.ListBox STATO__116 = new com.codecharge.components.ListBox("STATO", "STATO_TERRITORIO", this );
            STATO__116.setType( com.codecharge.components.ControlType.INTEGER );
            STATO__116.setHtmlEncode( true );
            STATO__116.setCaption( "STATO" );
            STATO__116.setBoundColumn( "STATO_TERRITORIO" );
            STATO__116.setTextColumn( "DENOMINAZIONE" );
            AD4_SOGGETTO.add( STATO__116 );

            com.codecharge.components.Hidden ISLISTBOX__157 = new com.codecharge.components.Hidden("ISLISTBOX", "", this );
            ISLISTBOX__157.setType( com.codecharge.components.ControlType.TEXT );
            ISLISTBOX__157.setHtmlEncode( true );
            AD4_SOGGETTO.add( ISLISTBOX__157 );

            com.codecharge.components.ListBox PROVINCIA_NAS__123 = new com.codecharge.components.ListBox("PROVINCIA_NAS", "PROVINCIA_NAS", this );
            PROVINCIA_NAS__123.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA_NAS__123.setHtmlEncode( true );
            PROVINCIA_NAS__123.setCaption( "PROVINCIA_NAS" );
            PROVINCIA_NAS__123.setBoundColumn( "PROVINCIA" );
            PROVINCIA_NAS__123.setTextColumn( "DENOMINAZIONE" );
            AD4_SOGGETTO.add( PROVINCIA_NAS__123 );

            com.codecharge.components.ListBox COMUNE_NASCITA__129 = new com.codecharge.components.ListBox("COMUNE_NASCITA", "COMUNE_NAS", this );
            COMUNE_NASCITA__129.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE_NASCITA__129.setHtmlEncode( true );
            COMUNE_NASCITA__129.setCaption( "COMUNE_NASCITA" );
            COMUNE_NASCITA__129.setBoundColumn( "COMUNE" );
            COMUNE_NASCITA__129.setTextColumn( "DENOMINAZIONE" );
            AD4_SOGGETTO.add( COMUNE_NASCITA__129 );

            com.codecharge.components.TextBox DATA_NASCITA__115 = new com.codecharge.components.TextBox("DATA_NASCITA", "DATA_NASCITA", this );
            DATA_NASCITA__115.setType( com.codecharge.components.ControlType.DATE );
            DATA_NASCITA__115.setHtmlEncode( true );
            DATA_NASCITA__115.setFormatPattern( "dd/MM/yyyy" );
            DATA_NASCITA__115.setCaption( "DATA_NASCITA" );
            AD4_SOGGETTO.add( DATA_NASCITA__115 );

            com.codecharge.components.TextBox CODICE_FISCALE__114 = new com.codecharge.components.TextBox("CODICE_FISCALE", "CODICE_FISCALE", this );
            CODICE_FISCALE__114.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_FISCALE__114.setHtmlEncode( true );
            CODICE_FISCALE__114.setCaption( "CODICE_FISCALE" );
            AD4_SOGGETTO.add( CODICE_FISCALE__114 );

            com.codecharge.components.TextBox INDIRIZZO_COMPLETO__133 = new com.codecharge.components.TextBox("INDIRIZZO_COMPLETO", "INDIRIZZO", this );
            INDIRIZZO_COMPLETO__133.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO_COMPLETO__133.setHtmlEncode( true );
            INDIRIZZO_COMPLETO__133.setCaption( "INDIRIZZO" );
            AD4_SOGGETTO.add( INDIRIZZO_COMPLETO__133 );

            com.codecharge.components.ListBox PROVINCIA__134 = new com.codecharge.components.ListBox("PROVINCIA", "PROVINCIA", this );
            PROVINCIA__134.setType( com.codecharge.components.ControlType.INTEGER );
            PROVINCIA__134.setHtmlEncode( true );
            PROVINCIA__134.setCaption( "PROVINCIA" );
            PROVINCIA__134.setBoundColumn( "PROVINCIA" );
            PROVINCIA__134.setTextColumn( "DENOMINAZIONE" );
            AD4_SOGGETTO.add( PROVINCIA__134 );

            com.codecharge.components.ListBox COMUNE__147 = new com.codecharge.components.ListBox("COMUNE", "COMUNE", this );
            COMUNE__147.setType( com.codecharge.components.ControlType.INTEGER );
            COMUNE__147.setHtmlEncode( true );
            COMUNE__147.setCaption( "COMUNE" );
            COMUNE__147.setBoundColumn( "COMUNE" );
            COMUNE__147.setTextColumn( "DENOMINAZIONE" );
            AD4_SOGGETTO.add( COMUNE__147 );

            com.codecharge.components.TextBox CAP__146 = new com.codecharge.components.TextBox("CAP", "CAP", this );
            CAP__146.setType( com.codecharge.components.ControlType.TEXT );
            CAP__146.setHtmlEncode( true );
            CAP__146.setCaption( "CAP" );
            CAP__146.addValidateHandler( new  RegexpJDK14Handler( "^\\d{5}$", "Validazione errata per il campo CAP." ));
            AD4_SOGGETTO.add( CAP__146 );

            com.codecharge.components.TextBox INDIRIZZO_WEB__109 = new com.codecharge.components.TextBox("INDIRIZZO_WEB", "INDIRIZZO_WEB", this );
            INDIRIZZO_WEB__109.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO_WEB__109.setHtmlEncode( true );
            INDIRIZZO_WEB__109.setCaption( "INDIRIZZO_WEB" );
            AD4_SOGGETTO.add( INDIRIZZO_WEB__109 );

            com.codecharge.components.TextBox TELEFONO__107 = new com.codecharge.components.TextBox("TELEFONO", "TELEFONO", this );
            TELEFONO__107.setType( com.codecharge.components.ControlType.TEXT );
            TELEFONO__107.setHtmlEncode( true );
            TELEFONO__107.setCaption( "TELEFONO" );
            AD4_SOGGETTO.add( TELEFONO__107 );

            com.codecharge.components.TextBox FAX__108 = new com.codecharge.components.TextBox("FAX", "FAX", this );
            FAX__108.setType( com.codecharge.components.ControlType.TEXT );
            FAX__108.setHtmlEncode( true );
            FAX__108.setCaption( "FAX" );
            AD4_SOGGETTO.add( FAX__108 );

            com.codecharge.components.TextArea NOTE__106 = new com.codecharge.components.TextArea("NOTE", "NOTE", this );
            NOTE__106.setType( com.codecharge.components.ControlType.TEXT );
            NOTE__106.setHtmlEncode( true );
            NOTE__106.setCaption( "NOTE" );
            AD4_SOGGETTO.add( NOTE__106 );

            com.codecharge.components.Button Refresh__155 = new com.codecharge.components.Button("Refresh", this);
            Refresh__155.addExcludeParam( "ccsForm" );
            Refresh__155.addExcludeParam( "Refresh" );
            Refresh__155.setOperation( "Search" );
            AD4_SOGGETTO.add( Refresh__155 );

            com.codecharge.components.Button Button_Insert__181 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__181.addButtonListener(new AD4_SOGGETTOButton_InsertHandler());
            Button_Insert__181.addExcludeParam( "ccsForm" );
            Button_Insert__181.addExcludeParam( "Button_Insert" );
            Button_Insert__181.setOperation( "Insert" );
            AD4_SOGGETTO.add( Button_Insert__181 );

            com.codecharge.components.Button Button_Update__22 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__22.addButtonListener(new AD4_SOGGETTOButton_UpdateHandler());
            Button_Update__22.addExcludeParam( "ccsForm" );
            Button_Update__22.addExcludeParam( "Button_Update" );
            Button_Update__22.setOperation( "Update" );
            AD4_SOGGETTO.add( Button_Update__22 );

            com.codecharge.components.Button Button_Cancel__23 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__23.addButtonListener(new AD4_SOGGETTOButton_CancelHandler());
            Button_Cancel__23.addExcludeParam( "ccsForm" );
            Button_Cancel__23.addExcludeParam( "Button_Cancel" );
            Button_Cancel__23.setOperation( "Cancel" );
            AD4_SOGGETTO.add( Button_Cancel__23 );
            add(AD4_SOGGETTO);
        } // End definition of AD4_SOGGETTO record model.
//End AD4_SOGGETTO record

//AD4SoggettoInclusaModel class tail @1-F5FC18C5
    }
}
//End AD4SoggettoInclusaModel class tail
