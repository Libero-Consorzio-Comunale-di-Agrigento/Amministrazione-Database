//AmvLoginSecureModel imports @1-F62B1684
package common.AmvLoginSecure;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AmvLoginSecureModel imports

//AmvLoginSecureModel class head @1-FE788FE8
public class AmvLoginSecureModel extends com.codecharge.components.Page {
    public AmvLoginSecureModel() {
        this( new CCSLocale(), null );
    }

    public AmvLoginSecureModel(CCSLocale locale) {
        this( locale, null );
    }

    public AmvLoginSecureModel( CCSLocale locale, HttpServletResponse response ) {
//End AmvLoginSecureModel class head

//page settings @1-0820F912
        super("AmvLoginSecure", locale );
        setResponse(response);
        addPageListener(new AmvLoginSecurePageHandler());
        {
            com.codecharge.components.IncludePage Header__27 = new com.codecharge.components.IncludePage("Header", this );
            Header__27.setVisible( true );
            add( Header__27 );
        } // end page
//End page settings

//LOGIN record @2-A790145D
        
        /*
            Model of LOGIN record defining.
        */
        {
            com.codecharge.components.Record LOGIN = new com.codecharge.components.Record("LOGIN");
            LOGIN.setPageModel( this );
            LOGIN.addExcludeParam( "ccsForm" );
            LOGIN.setVisible( true );
            LOGIN.setAllowInsert(false);
            LOGIN.setAllowUpdate(false);
            LOGIN.setAllowDelete(false);
            LOGIN.setPreserveType(PreserveParameterType.GET);
            LOGIN.setReturnPage("AmvLoginSecure" + Names.ACTION_SUFFIX);
            LOGIN.addRecordListener(new LOGINRecordHandler());

            com.codecharge.components.Label ACTION__31 = new com.codecharge.components.Label("ACTION", this);
            ACTION__31.setType( com.codecharge.components.ControlType.TEXT );
            ACTION__31.addControlListener( new LOGINACTIONHandler());
            LOGIN.add(ACTION__31);

            com.codecharge.components.Label ERRORE__3 = new com.codecharge.components.Label("ERRORE", "ERRORE", this );
            ERRORE__3.setType( com.codecharge.components.ControlType.TEXT );
            LOGIN.add(ERRORE__3);

            com.codecharge.components.TextBox u__4 = new com.codecharge.components.TextBox("u", "USERNAME", this );
            u__4.setType( com.codecharge.components.ControlType.TEXT );
            u__4.setHtmlEncode( true );
            LOGIN.add( u__4 );

            com.codecharge.components.TextBox p__5 = new com.codecharge.components.TextBox("p", "PASSWORD", this );
            p__5.setType( com.codecharge.components.ControlType.TEXT );
            p__5.setHtmlEncode( true );
            LOGIN.add( p__5 );

            com.codecharge.components.Hidden sa4ck__25 = new com.codecharge.components.Hidden("sa4ck", this);
            sa4ck__25.setType( com.codecharge.components.ControlType.TEXT );
            sa4ck__25.setHtmlEncode( true );
            LOGIN.add( sa4ck__25 );

            com.codecharge.components.Label JSLOGIN__17 = new com.codecharge.components.Label("JSLOGIN", "JSLOGIN", this );
            JSLOGIN__17.setType( com.codecharge.components.ControlType.TEXT );
            JSLOGIN__17.addControlListener( new LOGINJSLOGINHandler());
            LOGIN.add(JSLOGIN__17);

            com.codecharge.components.Button Login__11 = new com.codecharge.components.Button("Login", this);
            Login__11.addExcludeParam( "ccsForm" );
            Login__11.addExcludeParam( "Login" );
            Login__11.setOperation( "Search" );
            LOGIN.add( Login__11 );
            add(LOGIN);
        } // End definition of LOGIN record model.
//End LOGIN record

//RECUPERO_LOGIN_CUSTOM grid @20-22301550
        
        /*
            // Begin definition of RECUPERO_LOGIN_CUSTOM grid model.
        */
        {
            com.codecharge.components.Grid RECUPERO_LOGIN_CUSTOM = new com.codecharge.components.Grid("RECUPERO_LOGIN_CUSTOM");
            RECUPERO_LOGIN_CUSTOM.setPageModel( this );
            RECUPERO_LOGIN_CUSTOM.setFetchSize(20);
            RECUPERO_LOGIN_CUSTOM.setVisible( true );

            com.codecharge.components.Label LOSTMSGCUSTOM__21 = new com.codecharge.components.Label("LOSTMSGCUSTOM", "LOSTMSGCUSTOM", this );
            LOSTMSGCUSTOM__21.setType( com.codecharge.components.ControlType.TEXT );
            RECUPERO_LOGIN_CUSTOM.add(LOSTMSGCUSTOM__21);
            add(RECUPERO_LOGIN_CUSTOM);
        } // End definition of RECUPERO_LOGIN_CUSTOM grid model
//End RECUPERO_LOGIN_CUSTOM grid

//RECUPERO_LOGIN grid @12-1CA719EE
        
        /*
            // Begin definition of RECUPERO_LOGIN grid model.
        */
        {
            com.codecharge.components.Grid RECUPERO_LOGIN = new com.codecharge.components.Grid("RECUPERO_LOGIN");
            RECUPERO_LOGIN.setPageModel( this );
            RECUPERO_LOGIN.setFetchSize(20);
            RECUPERO_LOGIN.setVisible( true );

            com.codecharge.components.Label LOSTMSG__13 = new com.codecharge.components.Label("LOSTMSG", "LOSTMSG", this );
            LOSTMSG__13.setType( com.codecharge.components.ControlType.TEXT );
            RECUPERO_LOGIN.add(LOSTMSG__13);
            add(RECUPERO_LOGIN);
        } // End definition of RECUPERO_LOGIN grid model
//End RECUPERO_LOGIN grid

//AmvLoginSecureModel class tail @1-F5FC18C5
    }
}
//End AmvLoginSecureModel class tail

