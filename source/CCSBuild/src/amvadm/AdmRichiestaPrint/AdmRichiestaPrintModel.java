//AdmRichiestaPrintModel imports @1-8B967265
package amvadm.AdmRichiestaPrint;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AdmRichiestaPrintModel imports

//AdmRichiestaPrintModel class head @1-EBE810CE
public class AdmRichiestaPrintModel extends com.codecharge.components.Page {
    public AdmRichiestaPrintModel() {
        this( new CCSLocale(), null );
    }

    public AdmRichiestaPrintModel(CCSLocale locale) {
        this( locale, null );
    }

    public AdmRichiestaPrintModel( CCSLocale locale, HttpServletResponse response ) {
//End AdmRichiestaPrintModel class head

//page settings @1-2B638BC3
        super("AdmRichiestaPrint", locale );
        setResponse(response);
        {
            com.codecharge.components.IncludePage AmvFooter__85 = new com.codecharge.components.IncludePage("AmvFooter", this );
            AmvFooter__85.setVisible( true );
            add( AmvFooter__85 );
        } // end page
//End page settings

//AD4_UTENTEGrid grid @71-D2AD9548
        
        /*
            // Begin definition of AD4_UTENTEGrid grid model.
        */
        {
            com.codecharge.components.Grid AD4_UTENTEGrid = new com.codecharge.components.Grid("AD4_UTENTEGrid");
            AD4_UTENTEGrid.setPageModel( this );
            AD4_UTENTEGrid.setFetchSize(20);
            AD4_UTENTEGrid.setVisible( true );

            com.codecharge.components.Label DATA_RICHIESTA__84 = new com.codecharge.components.Label("DATA_RICHIESTA", "DATA", this );
            DATA_RICHIESTA__84.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTEGrid.add(DATA_RICHIESTA__84);

            com.codecharge.components.Label NOME_SOGGETTO__72 = new com.codecharge.components.Label("NOME_SOGGETTO", "NOME_SOGGETTO", this );
            NOME_SOGGETTO__72.setType( com.codecharge.components.ControlType.TEXT );
            NOME_SOGGETTO__72.setHtmlEncode( true );
            AD4_UTENTEGrid.add(NOME_SOGGETTO__72);

            com.codecharge.components.Label DATA_NASCITA__73 = new com.codecharge.components.Label("DATA_NASCITA", "DATA_NASCITA", this );
            DATA_NASCITA__73.setType( com.codecharge.components.ControlType.TEXT );
            DATA_NASCITA__73.setHtmlEncode( true );
            AD4_UTENTEGrid.add(DATA_NASCITA__73);

            com.codecharge.components.Label COMUNE_NASCITA__74 = new com.codecharge.components.Label("COMUNE_NASCITA", "COMUNE_NASCITA", this );
            COMUNE_NASCITA__74.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE_NASCITA__74.setHtmlEncode( true );
            AD4_UTENTEGrid.add(COMUNE_NASCITA__74);

            com.codecharge.components.Label PROVINCIA_NASCITA__75 = new com.codecharge.components.Label("PROVINCIA_NASCITA", "PROVINCIA_NASCITA", this );
            PROVINCIA_NASCITA__75.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA_NASCITA__75.setHtmlEncode( true );
            AD4_UTENTEGrid.add(PROVINCIA_NASCITA__75);

            com.codecharge.components.Label CODICE_FISCALE__76 = new com.codecharge.components.Label("CODICE_FISCALE", "CODICE_FISCALE", this );
            CODICE_FISCALE__76.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_FISCALE__76.setHtmlEncode( true );
            AD4_UTENTEGrid.add(CODICE_FISCALE__76);

            com.codecharge.components.Label INDIRIZZO_COMPLETO__77 = new com.codecharge.components.Label("INDIRIZZO_COMPLETO", "INDIRIZZO_COMPLETO", this );
            INDIRIZZO_COMPLETO__77.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO_COMPLETO__77.setHtmlEncode( true );
            AD4_UTENTEGrid.add(INDIRIZZO_COMPLETO__77);

            com.codecharge.components.Label INDIRIZZO_WEB__78 = new com.codecharge.components.Label("INDIRIZZO_WEB", "INDIRIZZO_WEB", this );
            INDIRIZZO_WEB__78.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO_WEB__78.setHtmlEncode( true );
            AD4_UTENTEGrid.add(INDIRIZZO_WEB__78);

            com.codecharge.components.Label TELEFONO__79 = new com.codecharge.components.Label("TELEFONO", "TELEFONO", this );
            TELEFONO__79.setType( com.codecharge.components.ControlType.TEXT );
            TELEFONO__79.setHtmlEncode( true );
            AD4_UTENTEGrid.add(TELEFONO__79);

            com.codecharge.components.Label FAX__80 = new com.codecharge.components.Label("FAX", "FAX", this );
            FAX__80.setType( com.codecharge.components.ControlType.TEXT );
            FAX__80.setHtmlEncode( true );
            AD4_UTENTEGrid.add(FAX__80);

            com.codecharge.components.Label NOMINATIVO__81 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__81.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTEGrid.add(NOMINATIVO__81);

            com.codecharge.components.Label PASSWORD__82 = new com.codecharge.components.Label("PASSWORD", "PASSWORD", this );
            PASSWORD__82.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTEGrid.add(PASSWORD__82);
            add(AD4_UTENTEGrid);
        } // End definition of AD4_UTENTEGrid grid model
//End AD4_UTENTEGrid grid

//AdmRichiestaPrintModel class tail @1-F5FC18C5
    }
}
//End AdmRichiestaPrintModel class tail

