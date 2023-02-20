//AD4RegistroElencoModel imports @1-61FDFDF2
package ad4web.AD4RegistroElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4RegistroElencoModel imports

//AD4RegistroElencoModel class head @1-AE1669C2
public class AD4RegistroElencoModel extends com.codecharge.components.Page {
    public AD4RegistroElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4RegistroElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4RegistroElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4RegistroElencoModel class head

//page settings @1-B0C7A825
        super("AD4RegistroElenco", locale );
        setResponse(response);
        setIncluded(true);
        {
        } // end page
//End page settings

//AD4_REGISTRO_SEL grid @10-5D557701
        
        /*
            // Begin definition of AD4_REGISTRO_SEL grid model.
        */
        {
            com.codecharge.components.Grid AD4_REGISTRO_SEL = new com.codecharge.components.Grid("AD4_REGISTRO_SEL");
            AD4_REGISTRO_SEL.setPageModel( this );
            AD4_REGISTRO_SEL.setFetchSize(20);
            AD4_REGISTRO_SEL.setVisible( true );

            com.codecharge.components.Label NOME_SEZIONE__11 = new com.codecharge.components.Label("NOME_SEZIONE", "NOME", this );
            NOME_SEZIONE__11.setType( com.codecharge.components.ControlType.TEXT );
            NOME_SEZIONE__11.setHtmlEncode( true );
            AD4_REGISTRO_SEL.add(NOME_SEZIONE__11);
            add(AD4_REGISTRO_SEL);
        } // End definition of AD4_REGISTRO_SEL grid model
//End AD4_REGISTRO_SEL grid

//ELENCO_CHIAVI grid @2-56591157
        
        /*
            // Begin definition of ELENCO_CHIAVI grid model.
        */
        {
            com.codecharge.components.Grid ELENCO_CHIAVI = new com.codecharge.components.Grid("ELENCO_CHIAVI");
            ELENCO_CHIAVI.setPageModel( this );
            ELENCO_CHIAVI.setFetchSize(50);
            ELENCO_CHIAVI.setVisible( true );
            ELENCO_CHIAVI.addGridListener( new ELENCO_CHIAVIGridHandler() );

            com.codecharge.components.Link STRINGA__3 = new com.codecharge.components.Link("STRINGA", "STRINGA", this );
            STRINGA__3.setType( com.codecharge.components.ControlType.TEXT );
            STRINGA__3.setHtmlEncode( true );
            STRINGA__3.setHrefSourceValue( "/ad4web/AD4Stringa" + Names.ACTION_SUFFIX );
            STRINGA__3.setHrefType( "Page" );
            STRINGA__3.setConvertRule("Relative");
            STRINGA__3.setPreserveType(PreserveParameterType.NONE);
            STRINGA__3.addParameter( new LinkParameter( "ID", "ID", ParameterSource.URL) );
            STRINGA__3.addParameter( new LinkParameter( "STRINGA", "STRINGA", ParameterSource.DATAFIELD) );
            STRINGA__3.addParameter( new LinkParameter( "TIPOR", "", ParameterSource.EXPRESSION) );
            ELENCO_CHIAVI.add( STRINGA__3 );

            com.codecharge.components.Label VALORE__21 = new com.codecharge.components.Label("VALORE", "VALORE", this );
            VALORE__21.setType( com.codecharge.components.ControlType.TEXT );
            VALORE__21.setHtmlEncode( true );
            ELENCO_CHIAVI.add(VALORE__21);

            com.codecharge.components.Label COMMENTO__30 = new com.codecharge.components.Label("COMMENTO", "COMMENTO", this );
            COMMENTO__30.setType( com.codecharge.components.ControlType.TEXT );
            COMMENTO__30.setHtmlEncode( true );
            ELENCO_CHIAVI.add(COMMENTO__30);

            com.codecharge.components.Hidden CHIAVE__40 = new com.codecharge.components.Hidden("CHIAVE", "CHIAVE", this );
            CHIAVE__40.setType( com.codecharge.components.ControlType.TEXT );
            CHIAVE__40.setHtmlEncode( true );
            ELENCO_CHIAVI.add( CHIAVE__40 );

            com.codecharge.components.Label AFCNavigator__31 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__31.setType( com.codecharge.components.ControlType.TEXT );
            ELENCO_CHIAVI.add(AFCNavigator__31);
            add(ELENCO_CHIAVI);
        } // End definition of ELENCO_CHIAVI grid model
//End ELENCO_CHIAVI grid

//AD4RegistroElencoModel class tail @1-F5FC18C5
    }
}
//End AD4RegistroElencoModel class tail
