//AD4UsjoElencoModel imports @1-A207CD83
package ad4web.AD4UsjoElenco;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AD4UsjoElencoModel imports

//AD4UsjoElencoModel class head @1-9E506669
public class AD4UsjoElencoModel extends com.codecharge.components.Page {
    public AD4UsjoElencoModel() {
        this( new CCSLocale(), null );
    }

    public AD4UsjoElencoModel(CCSLocale locale) {
        this( locale, null );
    }

    public AD4UsjoElencoModel( CCSLocale locale, HttpServletResponse response ) {
//End AD4UsjoElencoModel class head

//page settings @1-ABE9DB07
        super("AD4UsjoElenco", locale );
        setResponse(response);
        addPageListener(new AD4UsjoElencoPageHandler());
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

//user_jobs grid @6-8186DBA0
        
        /*
            // Begin definition of user_jobs grid model.
        */
        {
            com.codecharge.components.Grid user_jobs = new com.codecharge.components.Grid("user_jobs");
            user_jobs.setPageModel( this );
            user_jobs.setFetchSize(10);
            user_jobs.setVisible( true );
            user_jobs.addGridListener( new user_jobsGridHandler() );
            com.codecharge.components.Sorter Sorter_JOB = new com.codecharge.components.Sorter("Sorter_JOB", user_jobs, this);
            Sorter_JOB.setColumn("JOB");
            user_jobs.add(Sorter_JOB);
            com.codecharge.components.Sorter Sorter_WHAT = new com.codecharge.components.Sorter("Sorter_WHAT", user_jobs, this);
            Sorter_WHAT.setColumn("WHAT");
            user_jobs.add(Sorter_WHAT);
            com.codecharge.components.Sorter Sorter_THIS_DATE = new com.codecharge.components.Sorter("Sorter_THIS_DATE", user_jobs, this);
            Sorter_THIS_DATE.setColumn("THIS_DATE");
            user_jobs.add(Sorter_THIS_DATE);
            com.codecharge.components.Sorter Sorter_NEXT_DATE = new com.codecharge.components.Sorter("Sorter_NEXT_DATE", user_jobs, this);
            Sorter_NEXT_DATE.setColumn("NEXT_DATE");
            user_jobs.add(Sorter_NEXT_DATE);
            com.codecharge.components.Sorter Sorter_LAST_DATE = new com.codecharge.components.Sorter("Sorter_LAST_DATE", user_jobs, this);
            Sorter_LAST_DATE.setColumn("LAST_DATE");
            user_jobs.add(Sorter_LAST_DATE);
            com.codecharge.components.Sorter Sorter_FAILURES = new com.codecharge.components.Sorter("Sorter_FAILURES", user_jobs, this);
            Sorter_FAILURES.setColumn("FAILURES");
            user_jobs.add(Sorter_FAILURES);
            com.codecharge.components.Sorter Sorter_TOTAL_TIME = new com.codecharge.components.Sorter("Sorter_TOTAL_TIME", user_jobs, this);
            Sorter_TOTAL_TIME.setColumn("FAILURES");
            user_jobs.add(Sorter_TOTAL_TIME);
            com.codecharge.components.Sorter Sorter_INTERVAL = new com.codecharge.components.Sorter("Sorter_INTERVAL", user_jobs, this);
            Sorter_INTERVAL.setColumn("INTERVAL");
            user_jobs.add(Sorter_INTERVAL);
            com.codecharge.components.Sorter Sorter_BROKEN = new com.codecharge.components.Sorter("Sorter_BROKEN", user_jobs, this);
            Sorter_BROKEN.setColumn("BROKEN");
            user_jobs.add(Sorter_BROKEN);

            com.codecharge.components.Link JOB__12 = new com.codecharge.components.Link("JOB", "JOB", this );
            JOB__12.setType( com.codecharge.components.ControlType.TEXT );
            JOB__12.setHtmlEncode( true );
            JOB__12.setHrefSourceValue( "AD4UserJob" + Names.ACTION_SUFFIX );
            JOB__12.setHrefType( "Page" );
            JOB__12.setConvertRule("Relative");
            JOB__12.setPreserveType(PreserveParameterType.GET);
            JOB__12.addExcludeParam( "MVVC" );
            JOB__12.addExcludeParam( "MVID" );
            JOB__12.addParameter( new LinkParameter( "ID_JOB", "JOB", ParameterSource.DATAFIELD) );
            user_jobs.add( JOB__12 );

            com.codecharge.components.Hidden PROGETTO__83 = new com.codecharge.components.Hidden("PROGETTO", "PROGETTO", this );
            PROGETTO__83.setType( com.codecharge.components.ControlType.TEXT );
            PROGETTO__83.setHtmlEncode( true );
            user_jobs.add( PROGETTO__83 );

            com.codecharge.components.Label WHAT__86 = new com.codecharge.components.Label("WHAT", "WHAT", this );
            WHAT__86.setType( com.codecharge.components.ControlType.TEXT );
            WHAT__86.setHtmlEncode( true );
            user_jobs.add(WHAT__86);

            com.codecharge.components.Label THIS_DATE__85 = new com.codecharge.components.Label("THIS_DATE", "THIS_DATE", this );
            THIS_DATE__85.setType( com.codecharge.components.ControlType.TEXT );
            THIS_DATE__85.setHtmlEncode( true );
            user_jobs.add(THIS_DATE__85);

            com.codecharge.components.Label NEXT_DATE__87 = new com.codecharge.components.Label("NEXT_DATE", "NEXT_DATE", this );
            NEXT_DATE__87.setType( com.codecharge.components.ControlType.TEXT );
            NEXT_DATE__87.setHtmlEncode( true );
            user_jobs.add(NEXT_DATE__87);

            com.codecharge.components.Label LAST_DATE__89 = new com.codecharge.components.Label("LAST_DATE", "LAST_DATE", this );
            LAST_DATE__89.setType( com.codecharge.components.ControlType.TEXT );
            LAST_DATE__89.setHtmlEncode( true );
            user_jobs.add(LAST_DATE__89);

            com.codecharge.components.Label FAILURES__91 = new com.codecharge.components.Label("FAILURES", "FAILURES", this );
            FAILURES__91.setType( com.codecharge.components.ControlType.TEXT );
            FAILURES__91.setHtmlEncode( true );
            user_jobs.add(FAILURES__91);

            com.codecharge.components.Label TOTAL_TIME__93 = new com.codecharge.components.Label("TOTAL_TIME", "TOTAL_TIME", this );
            TOTAL_TIME__93.setType( com.codecharge.components.ControlType.TEXT );
            TOTAL_TIME__93.setHtmlEncode( true );
            user_jobs.add(TOTAL_TIME__93);

            com.codecharge.components.Label INTERVAL__95 = new com.codecharge.components.Label("INTERVAL", "INTERVAL", this );
            INTERVAL__95.setType( com.codecharge.components.ControlType.TEXT );
            INTERVAL__95.setHtmlEncode( true );
            user_jobs.add(INTERVAL__95);

            com.codecharge.components.Label BROKEN__97 = new com.codecharge.components.Label("BROKEN", "BROKEN", this );
            BROKEN__97.setType( com.codecharge.components.ControlType.TEXT );
            BROKEN__97.setHtmlEncode( true );
            user_jobs.add(BROKEN__97);

            com.codecharge.components.Label AFCNavigator__68 = new com.codecharge.components.Label("AFCNavigator", this);
            AFCNavigator__68.setType( com.codecharge.components.ControlType.TEXT );
            user_jobs.add(AFCNavigator__68);
            add(user_jobs);
        } // End definition of user_jobs grid model
//End user_jobs grid

//AD4UsjoElencoModel class tail @1-F5FC18C5
    }
}
//End AD4UsjoElencoModel class tail
