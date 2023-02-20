//NewGrid1 DataSource @2-B391272D
package ad4web.AD4GuidaIstanza;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End NewGrid1 DataSource

//class DataObject Header @2-0F2DC472
public class NewGrid1DataObject extends DS {
//End class DataObject Header

//attributes of DataObject @2-7A91A292
    

    TextField urlSEL = new TextField(null, null);
    
    TextField urlISTANZA = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    
    TextField sesAD4PROGETTO = new TextField(null, null);
    

    private NewGrid1Row[] rows = new NewGrid1Row[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @2-1207F967

    public void  setUrlSEL( String param ) {
        this.urlSEL.setValue( param );
    }

    public void  setUrlSEL( Object param ) {
        this.urlSEL.setValue( param );
    }

    public void  setUrlSEL( Object param, Format ignore ) {
        this.urlSEL.setValue( param );
    }

    public void  setUrlISTANZA( String param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param, Format ignore ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlPROGETTO( String param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param, Format ignore ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( String param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param, Format ignore ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public NewGrid1Row[] getRows() {
        return rows;
    }

    public void setRows(NewGrid1Row[] rows) {
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

//constructor @2-49E16DB2
    public NewGrid1DataObject(Page page) {
        super(page);
    }
//End constructor

//load @2-9EF761FA
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT AD4WEB.GET_GUIDA_ISTANZA (ISTA.PROGETTO , ISTANZA, ISTANZA,  "
                    + "'{SEL}') FOLDER "
                    + "  FROM ISTANZE ISTA,  "
                    + "PROGETTI PROG "
                    + " WHERE ISTA.PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   AND ISTA.PROGETTO = PROG.PROGETTO "
                    + "   AND ISTANZA = '{ISTANZA}'" );
        if ( StringUtils.isEmpty( (String) urlSEL.getObjectValue() ) ) urlSEL.setValue( "" );
        command.addParameter( "SEL", urlSEL, null );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZA", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT AD4WEB.GET_GUIDA_ISTANZA (ISTA.PROGETTO , ISTANZA, ISTANZA,  "
                                                         + "            '{SEL}') FOLDER FROM ISTANZE ISTA,  "
                                                         + "            PROGETTI PROG WHERE ISTA.PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') AND ISTA.PROGETTO = PROG.PROGETTO AND ISTANZA = '{ISTANZA}' ) cnt " );
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

//loadDataBind @2-EDC6D636
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                NewGrid1Row row = new NewGrid1Row();
                DbRow record = (DbRow) records.nextElement();
                row.setFOLDER(Utils.convertToString(ds.parse(record.get("FOLDER"), row.getFOLDERField())));
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

//getParameterByName @2-18CA0FB9
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "SEL".equals(name) && "url".equals(prefix) ) {
                param = urlSEL;
            } else if ( "SEL".equals(name) && prefix == null ) {
                param = urlSEL;
            }
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "AD4PROGETTO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PROGETTO;
            } else if ( "AD4PROGETTO".equals(name) && prefix == null ) {
                param = sesAD4PROGETTO;
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

