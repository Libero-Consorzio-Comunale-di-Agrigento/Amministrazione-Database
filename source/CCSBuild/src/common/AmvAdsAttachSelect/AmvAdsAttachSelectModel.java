//AmvAdsAttachSelectModel imports @1-6F222125
package common.AmvAdsAttachSelect;

import com.codecharge.*;
import com.codecharge.validation.*;
import com.codecharge.util.*;
import com.codecharge.util.multipart.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import java.text.*;
import java.util.*;
import javax.servlet.http.*;

//End AmvAdsAttachSelectModel imports

//AmvAdsAttachSelectModel class head @1-7AC0D569
public class AmvAdsAttachSelectModel extends com.codecharge.components.Page {
    public AmvAdsAttachSelectModel() {
        this( new CCSLocale(), null );
    }

    public AmvAdsAttachSelectModel(CCSLocale locale) {
        this( locale, null );
    }

    public AmvAdsAttachSelectModel( CCSLocale locale, HttpServletResponse response ) {
//End AmvAdsAttachSelectModel class head

//page settings @1-0BEF6ED7
        super("AmvAdsAttachSelect", locale );
        setResponse(response);
        {
            com.codecharge.components.IncludePage AmvStyle__14 = new com.codecharge.components.IncludePage("AmvStyle", this );
            AmvStyle__14.setVisible( true );
            add( AmvStyle__14 );
        } // end page
//End page settings

//FILE_LIST record @2-813B0007
        
        /*
            Model of FILE_LIST record defining.
        */
        {
            com.codecharge.components.Record FILE_LIST = new com.codecharge.components.Record("FILE_LIST");
            FILE_LIST.setPageModel( this );
            FILE_LIST.addExcludeParam( "ccsForm" );
            FILE_LIST.setVisible( true );
            FILE_LIST.setAllowInsert(false);
            FILE_LIST.setAllowUpdate(false);
            FILE_LIST.setAllowDelete(false);
            FILE_LIST.setPreserveType(PreserveParameterType.GET);
            FILE_LIST.setReturnPage("AmvAdsAttachSelect" + Names.ACTION_SUFFIX);

            com.codecharge.components.Label TITOLO__10 = new com.codecharge.components.Label("TITOLO", "TITOLO", this );
            TITOLO__10.setType( com.codecharge.components.ControlType.TEXT );
            FILE_LIST.add(TITOLO__10);

            com.codecharge.components.ListBox DOCUMENTO__27 = new com.codecharge.components.ListBox("DOCUMENTO", "VALORE", this );
            DOCUMENTO__27.setType( com.codecharge.components.ControlType.TEXT );
            DOCUMENTO__27.setHtmlEncode( true );
            DOCUMENTO__27.setBoundColumn( "VALORE" );
            DOCUMENTO__27.setTextColumn( "NOME" );
            FILE_LIST.add( DOCUMENTO__27 );

            com.codecharge.components.Hidden DATASOURCE__37 = new com.codecharge.components.Hidden("DATASOURCE", "", this );
            DATASOURCE__37.setType( com.codecharge.components.ControlType.TEXT );
            DATASOURCE__37.setHtmlEncode( true );
            DATASOURCE__37.addControlListener( new FILE_LISTDATASOURCEHandler());
            FILE_LIST.add( DATASOURCE__37 );
            add(FILE_LIST);
        } // End definition of FILE_LIST record model.
//End FILE_LIST record

//AmvAdsAttachSelectModel class tail @1-F5FC18C5
    }
}
//End AmvAdsAttachSelectModel class tail
