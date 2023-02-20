//Guida DataSource @2-78498DAD
package ad4web.Ad4DizionariZoneAslGuida;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End Guida DataSource

//class DataObject Header @2-7855E245
public class GuidaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @2-92990930
    

    TextField urlMVPG = new TextField(null, null);
    
    TextField urlID_ZONA_ASL = new TextField(null, null);
    

    private GuidaRow[] rows = new GuidaRow[1];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @2-6413E1B4

    public void  setUrlMVPG( String param ) {
        this.urlMVPG.setValue( param );
    }

    public void  setUrlMVPG( Object param ) {
        this.urlMVPG.setValue( param );
    }

    public void  setUrlMVPG( Object param, Format ignore ) {
        this.urlMVPG.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( String param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( Object param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( Object param, Format ignore ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public GuidaRow[] getRows() {
        return rows;
    }

    public void setRows(GuidaRow[] rows) {
        this.rows = rows;
    }

    public void setPageNum( int pageNum ) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

//End properties of DataObject

//constructor @2-08426B70
    public GuidaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @2-A72EBDF7
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select ad4_ccs.tab_folder "
                    + "       ('Zona ASL' "
                    + "      ,  "
                    + " '?'||'MVPG=Ad4DizionariZonaAslImposta' "
                    + "            ||'&'||'ID_ZONA_ASL='||'{ID_ZONA_ASL}' "
                    + "      ,decode('{MVPG}','Ad4DizionariZonaAslImposta','S','N') "
                    + "      ) folder1 "
                    + "      ,ad4_ccs.tab_folder "
                    + "       ('Comuni Zona ASL' "
                    + "       ,  '?'||'MVPG=Ad4DizionariZoneAslComuniElenco' "
                    + "             ||'&'||'ID_ZONA_ASL='||'{ID_ZONA_ASL}' "
                    + "       ,decode('{MVPG}','Ad4DizionariZoneAslComuniElenco','S','N') "
                    + "       ) folder2 "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlMVPG.getObjectValue() ) ) urlMVPG.setValue( "" );
        command.addParameter( "MVPG", urlMVPG, null );
        if ( StringUtils.isEmpty( (String) urlID_ZONA_ASL.getObjectValue() ) ) urlID_ZONA_ASL.setValue( "" );
        command.addParameter( "ID_ZONA_ASL", urlID_ZONA_ASL, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select ad4_ccs.tab_folder ('Zona ASL' ,  "
                                                         + "            '?'||'MVPG=Ad4DizionariZonaAslImposta' ||'&'||'ID_ZONA_ASL='||'{ID_ZONA_ASL}' ,decode('{MVPG}','Ad4DizionariZonaAslImposta','S','N') ) folder1 ,ad4_ccs.tab_folder ('Comuni Zona ASL' , '?'||'MVPG=Ad4DizionariZoneAslComuniElenco' ||'&'||'ID_ZONA_ASL='||'{ID_ZONA_ASL}' ,decode('{MVPG}','Ad4DizionariZoneAslComuniElenco','S','N') ) folder2 from dual ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        }
        command.setStartPos( ( pageNum - 1 ) * pageSize + 1 );
        command.setFetchSize( pageSize );

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        if ( ! StringUtils.isEmpty( command.getCountSql() ) ) {
            if ( ! ds.hasErrors() ) {
                amountOfRows = command.count();
                CCLogger.getInstance().debug(command.toString());
            }
        }
        Enumeration records = null;
        if ( ! ds.hasErrors() ) {
            records = command.getRows();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @2-E2E49DDD
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                GuidaRow row = new GuidaRow();
                DbRow record = (DbRow) records.nextElement();
                row.setFOLDER1(Utils.convertToString(ds.parse(record.get("FOLDER1"), row.getFOLDER1Field())));
                row.setFOLDER2(Utils.convertToString(ds.parse(record.get("FOLDER2"), row.getFOLDER2Field())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @2-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @2-85010616
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "MVPG".equals(name) && "url".equals(prefix) ) {
                param = urlMVPG;
            } else if ( "MVPG".equals(name) && prefix == null ) {
                param = urlMVPG;
            }
            if ( "ID_ZONA_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlID_ZONA_ASL;
            } else if ( "ID_ZONA_ASL".equals(name) && prefix == null ) {
                param = urlID_ZONA_ASL;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @2-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @2-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @2-238A81BB
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource(this);
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @2-9DA7B025
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @2-F7E8A616
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//class DataObject Tail @2-ED3F53A4
} // End of class DS
//End class DataObject Tail

