//AD4GruppiTreeModel imports @1-54029E18
package ad4web.AD4GruppiTree;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4GruppiTreeModel imports

//AD4GruppiTreeModel class head @1-ADDC8B5F
public class AD4GruppiTreeModel extends com.codecharge.components.Page {
    public AD4GruppiTreeModel() {
        this( new CCSLocale(), null );
    }

    public AD4GruppiTreeModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4GruppiTreeModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4GruppiTreeModel class head

//page settings @1-04BE3560
        super("AD4GruppiTree", locale );
        setResponse(response);
        addPageListener(new AD4GruppiTreePageHandler());
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
            com.codecharge.components.IncludePage AD4GruppiTreeInclusa__21 = new com.codecharge.components.IncludePage("AD4GruppiTreeInclusa", this );
            AD4GruppiTreeInclusa__21.setVisible( true );
            add( AD4GruppiTreeInclusa__21 );
            com.codecharge.components.IncludePage Footer__4 = new com.codecharge.components.IncludePage("Footer", this );
            Footer__4.setVisible( true );
            add( Footer__4 );
        } // end page
//End page settings

//se_rigenerata_SO record @28-39C4CBD0
        
        /*
            Model of se_rigenerata_SO record defining.
        */
        {
            com.codecharge.components.Record se_rigenerata_SO = new com.codecharge.components.Record("se_rigenerata_SO");
            se_rigenerata_SO.setPageModel( this );
            se_rigenerata_SO.addExcludeParam( "ccsForm" );
            se_rigenerata_SO.setVisible( true );
            se_rigenerata_SO.setAllowInsert(false);
            se_rigenerata_SO.setAllowUpdate(false);
            se_rigenerata_SO.setAllowDelete(false);
            se_rigenerata_SO.setPreserveType(PreserveParameterType.GET);
            se_rigenerata_SO.setReturnPage("AD4GruppiTree" + Names.ACTION_SUFFIX);

            com.codecharge.components.TextBox JOB_RIG_SO__29 = new com.codecharge.components.TextBox("JOB_RIG_SO", "JOB_RIG_SO", this );
            JOB_RIG_SO__29.setType( com.codecharge.components.ControlType.TEXT );
            JOB_RIG_SO__29.setHtmlEncode( true );
            se_rigenerata_SO.add( JOB_RIG_SO__29 );

            com.codecharge.components.TextBox JOB_LDAP_FROM_SO__37 = new com.codecharge.components.TextBox("JOB_LDAP_FROM_SO", "JOB_LDAP_FROM_SO", this );
            JOB_LDAP_FROM_SO__37.setType( com.codecharge.components.ControlType.TEXT );
            JOB_LDAP_FROM_SO__37.setHtmlEncode( true );
            se_rigenerata_SO.add( JOB_LDAP_FROM_SO__37 );

            com.codecharge.components.Button Button_Insert__31 = new com.codecharge.components.Button("Button_Insert", this);
            Button_Insert__31.addExcludeParam( "ccsForm" );
            Button_Insert__31.addExcludeParam( "Button_Insert" );
            Button_Insert__31.setOperation( "Insert" );
            se_rigenerata_SO.add( Button_Insert__31 );

            com.codecharge.components.Button Button_Update__32 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__32.addExcludeParam( "ccsForm" );
            Button_Update__32.addExcludeParam( "Button_Update" );
            Button_Update__32.setOperation( "Update" );
            se_rigenerata_SO.add( Button_Update__32 );

            com.codecharge.components.Button Button_Delete__33 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__33.addExcludeParam( "ccsForm" );
            Button_Delete__33.addExcludeParam( "Button_Delete" );
            Button_Delete__33.setOperation( "Delete" );
            se_rigenerata_SO.add( Button_Delete__33 );
            add(se_rigenerata_SO);
        } // End definition of se_rigenerata_SO record model.
//End se_rigenerata_SO record

//Rigenera_SO grid @22-5C2BC822
        
        /*
            // Begin definition of Rigenera_SO grid model.
        */
        {
            com.codecharge.components.Grid Rigenera_SO = new com.codecharge.components.Grid("Rigenera_SO");
            Rigenera_SO.setPageModel( this );
            Rigenera_SO.setFetchSize(10);
            Rigenera_SO.setVisible( true );

            com.codecharge.components.Label RIGENERA__23 = new com.codecharge.components.Label("RIGENERA", "RIGENERA", this );
            RIGENERA__23.setType( com.codecharge.components.ControlType.TEXT );
            Rigenera_SO.add(RIGENERA__23);

            com.codecharge.components.Label ALLINEA_LDAP__35 = new com.codecharge.components.Label("ALLINEA_LDAP", "ALLINEA_LDAP", this );
            ALLINEA_LDAP__35.setType( com.codecharge.components.ControlType.TEXT );
            Rigenera_SO.add(ALLINEA_LDAP__35);
            add(Rigenera_SO);
        } // End definition of Rigenera_SO grid model
//End Rigenera_SO grid

//AD4GruppiTreeModel class tail @1-F5FC18C5
    }
}
//End AD4GruppiTreeModel class tail
