//AD4UserJobModel imports @1-22AA6651
package ad4web.AD4UserJob;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4UserJobModel imports

//AD4UserJobModel class head @1-2D9CBE60
public class AD4UserJobModel extends com.codecharge.components.Page {
    public AD4UserJobModel() {
        this( new CCSLocale(), null );
    }

    public AD4UserJobModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4UserJobModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4UserJobModel class head

//page settings @1-503BFC66
        super("AD4UserJob", locale );
        setResponse(response);
        addPageListener(new AD4UserJobPageHandler());
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

//USER_JOBS record @56-2A8B32A7
        
        /*
            Model of USER_JOBS record defining.
        */
        {
            com.codecharge.components.Record USER_JOBS = new com.codecharge.components.Record("USER_JOBS");
            USER_JOBS.setPageModel( this );
            USER_JOBS.addExcludeParam( "ccsForm" );
            USER_JOBS.addExcludeParam( "MVVC" );
            USER_JOBS.setVisible( true );
            USER_JOBS.setAllowInsert(false);
            USER_JOBS.setPreserveType(PreserveParameterType.GET);
            USER_JOBS.setReturnPage("AD4UserJob" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label JOB__195 = new com.codecharge.components.Label("JOB", "JOB", this );
            JOB__195.setType( com.codecharge.components.ControlType.TEXT );
            JOB__195.setHtmlEncode( true );
            USER_JOBS.add(JOB__195);

            com.codecharge.components.Label WHAT__176 = new com.codecharge.components.Label("WHAT", "WHAT", this );
            WHAT__176.setType( com.codecharge.components.ControlType.TEXT );
            WHAT__176.setHtmlEncode( true );
            USER_JOBS.add(WHAT__176);

            com.codecharge.components.Hidden WHAT_HIDDEN__193 = new com.codecharge.components.Hidden("WHAT_HIDDEN", "WHAT", this );
            WHAT_HIDDEN__193.setType( com.codecharge.components.ControlType.TEXT );
            WHAT_HIDDEN__193.setHtmlEncode( true );
            USER_JOBS.add( WHAT_HIDDEN__193 );

            com.codecharge.components.Hidden JOB_NUM__180 = new com.codecharge.components.Hidden("JOB_NUM", "JOB", this );
            JOB_NUM__180.setType( com.codecharge.components.ControlType.INTEGER );
            JOB_NUM__180.setHtmlEncode( true );
            USER_JOBS.add( JOB_NUM__180 );

            com.codecharge.components.TextBox NEXT_DATE__177 = new com.codecharge.components.TextBox("NEXT_DATE", "NEXT_DATE", this );
            NEXT_DATE__177.setType( com.codecharge.components.ControlType.TEXT );
            NEXT_DATE__177.setHtmlEncode( true );
            NEXT_DATE__177.setCaption( "Data Ora della successiva elaborazione." );
            NEXT_DATE__177.addValidateHandler( new RequiredHandler( "Il valore nel campo Data Ora della successiva elaborazione. è richiesto." ) );
            USER_JOBS.add( NEXT_DATE__177 );

            com.codecharge.components.Hidden BROKEN_JOB__185 = new com.codecharge.components.Hidden("BROKEN_JOB", "BROKEN_JOB", this );
            BROKEN_JOB__185.setType( com.codecharge.components.ControlType.TEXT );
            BROKEN_JOB__185.setHtmlEncode( true );
            USER_JOBS.add( BROKEN_JOB__185 );

            com.codecharge.components.Label BROKEN__179 = new com.codecharge.components.Label("BROKEN", "BROKEN", this );
            BROKEN__179.setType( com.codecharge.components.ControlType.TEXT );
            USER_JOBS.add(BROKEN__179);

            com.codecharge.components.TextBox INTERVAL__178 = new com.codecharge.components.TextBox("INTERVAL", "INTERVAL", this );
            INTERVAL__178.setType( com.codecharge.components.ControlType.TEXT );
            INTERVAL__178.setHtmlEncode( true );
            INTERVAL__178.setCaption( "Intervallo di ripetizione dell'elaborazione." );
            INTERVAL__178.addValidateHandler( new RequiredHandler( "Il valore nel campo Intervallo di ripetizione dell'elaborazione. è richiesto." ) );
            USER_JOBS.add( INTERVAL__178 );

            com.codecharge.components.Button Button_Delete__181 = new com.codecharge.components.Button("Button_Delete", this);
            Button_Delete__181.addExcludeParam( "ccsForm" );
            Button_Delete__181.addExcludeParam( "Button_Delete" );
            Button_Delete__181.setOperation( "Delete" );
            USER_JOBS.add( Button_Delete__181 );

            com.codecharge.components.Button Button_Update__57 = new com.codecharge.components.Button("Button_Update", this);
            Button_Update__57.addExcludeParam( "ccsForm" );
            Button_Update__57.addExcludeParam( "Button_Update" );
            Button_Update__57.setOperation( "Update" );
            USER_JOBS.add( Button_Update__57 );

            com.codecharge.components.Button Button_Cancel__60 = new com.codecharge.components.Button("Button_Cancel", this);
            Button_Cancel__60.addButtonListener(new USER_JOBSButton_CancelHandler());
            Button_Cancel__60.addExcludeParam( "ccsForm" );
            Button_Cancel__60.addExcludeParam( "Button_Cancel" );
            Button_Cancel__60.setOperation( "Cancel" );
            USER_JOBS.add( Button_Cancel__60 );
            add(USER_JOBS);
        } // End definition of USER_JOBS record model.
//End USER_JOBS record

//AD4UserJobModel class tail @1-F5FC18C5
    }
}
//End AD4UserJobModel class tail
