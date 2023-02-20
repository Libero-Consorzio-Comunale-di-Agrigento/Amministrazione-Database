//AmvDatiRegistrazioneModel imports @1-C861979F
package common.AmvDatiRegistrazione;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AmvDatiRegistrazioneModel imports

//AmvDatiRegistrazioneModel class head @1-B22CEAE6
public class AmvDatiRegistrazioneModel extends com.codecharge.components.Page {
    public AmvDatiRegistrazioneModel() {
        this( new CCSLocale(), null );
    }

    public AmvDatiRegistrazioneModel(CCSLocale locale) {
        this( locale, null );
    }

    public AmvDatiRegistrazioneModel( CCSLocale locale, HttpServletResponse response ) {
//End AmvDatiRegistrazioneModel class head

//page settings @1-1A6EC1D5
        super("AmvDatiRegistrazione", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AD4_UTENTEGrid grid @2-59C16306
        
        /*
            // Begin definition of AD4_UTENTEGrid grid model.
        */
        {
            com.codecharge.components.Grid AD4_UTENTEGrid = new com.codecharge.components.Grid("AD4_UTENTEGrid");
            AD4_UTENTEGrid.setPageModel( this );
            AD4_UTENTEGrid.setFetchSize(20);
            AD4_UTENTEGrid.setVisible( true );

            com.codecharge.components.Label NOME_SOGGETTO__22 = new com.codecharge.components.Label("NOME_SOGGETTO", "NOME_SOGGETTO", this );
            NOME_SOGGETTO__22.setType( com.codecharge.components.ControlType.TEXT );
            NOME_SOGGETTO__22.setHtmlEncode( true );
            AD4_UTENTEGrid.add(NOME_SOGGETTO__22);

            com.codecharge.components.Label DATA_NASCITA__23 = new com.codecharge.components.Label("DATA_NASCITA", "DATA_NASCITA", this );
            DATA_NASCITA__23.setType( com.codecharge.components.ControlType.TEXT );
            DATA_NASCITA__23.setHtmlEncode( true );
            AD4_UTENTEGrid.add(DATA_NASCITA__23);

            com.codecharge.components.Label COMUNE_NASCITA__25 = new com.codecharge.components.Label("COMUNE_NASCITA", "COMUNE_NASCITA", this );
            COMUNE_NASCITA__25.setType( com.codecharge.components.ControlType.TEXT );
            COMUNE_NASCITA__25.setHtmlEncode( true );
            AD4_UTENTEGrid.add(COMUNE_NASCITA__25);

            com.codecharge.components.Label PROVINCIA_NASCITA__26 = new com.codecharge.components.Label("PROVINCIA_NASCITA", "PROVINCIA_NASCITA", this );
            PROVINCIA_NASCITA__26.setType( com.codecharge.components.ControlType.TEXT );
            PROVINCIA_NASCITA__26.setHtmlEncode( true );
            AD4_UTENTEGrid.add(PROVINCIA_NASCITA__26);

            com.codecharge.components.Label CODICE_FISCALE__24 = new com.codecharge.components.Label("CODICE_FISCALE", "CODICE_FISCALE", this );
            CODICE_FISCALE__24.setType( com.codecharge.components.ControlType.TEXT );
            CODICE_FISCALE__24.setHtmlEncode( true );
            AD4_UTENTEGrid.add(CODICE_FISCALE__24);

            com.codecharge.components.Label INDIRIZZO_COMPLETO__6 = new com.codecharge.components.Label("INDIRIZZO_COMPLETO", "INDIRIZZO_COMPLETO", this );
            INDIRIZZO_COMPLETO__6.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO_COMPLETO__6.setHtmlEncode( true );
            AD4_UTENTEGrid.add(INDIRIZZO_COMPLETO__6);

            com.codecharge.components.Label INDIRIZZO_WEB__9 = new com.codecharge.components.Label("INDIRIZZO_WEB", "INDIRIZZO_WEB", this );
            INDIRIZZO_WEB__9.setType( com.codecharge.components.ControlType.TEXT );
            INDIRIZZO_WEB__9.setHtmlEncode( true );
            AD4_UTENTEGrid.add(INDIRIZZO_WEB__9);

            com.codecharge.components.Label TELEFONO__12 = new com.codecharge.components.Label("TELEFONO", "TELEFONO", this );
            TELEFONO__12.setType( com.codecharge.components.ControlType.TEXT );
            TELEFONO__12.setHtmlEncode( true );
            AD4_UTENTEGrid.add(TELEFONO__12);

            com.codecharge.components.Label FAX__13 = new com.codecharge.components.Label("FAX", "FAX", this );
            FAX__13.setType( com.codecharge.components.ControlType.TEXT );
            FAX__13.setHtmlEncode( true );
            AD4_UTENTEGrid.add(FAX__13);

            com.codecharge.components.Label NOMINATIVO__3 = new com.codecharge.components.Label("NOMINATIVO", "NOMINATIVO", this );
            NOMINATIVO__3.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTEGrid.add(NOMINATIVO__3);

            com.codecharge.components.Label PASSWORD__4 = new com.codecharge.components.Label("PASSWORD", "PASSWORD", this );
            PASSWORD__4.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTEGrid.add(PASSWORD__4);

            com.codecharge.components.Label TIPO_NOTIFICA__27 = new com.codecharge.components.Label("TIPO_NOTIFICA", "TIPO_NOTIFICA", this );
            TIPO_NOTIFICA__27.setType( com.codecharge.components.ControlType.TEXT );
            TIPO_NOTIFICA__27.setHtmlEncode( true );
            AD4_UTENTEGrid.add(TIPO_NOTIFICA__27);

            com.codecharge.components.Label INDIRIZZO_NOTIFICA__5 = new com.codecharge.components.Label("INDIRIZZO_NOTIFICA", "INDIRIZZO_NOTIFICA", this );
            INDIRIZZO_NOTIFICA__5.setType( com.codecharge.components.ControlType.TEXT );
            AD4_UTENTEGrid.add(INDIRIZZO_NOTIFICA__5);
            add(AD4_UTENTEGrid);
        } // End definition of AD4_UTENTEGrid grid model
//End AD4_UTENTEGrid grid

//AmvDatiRegistrazioneModel class tail @1-F5FC18C5
    }
}
//End AmvDatiRegistrazioneModel class tail

