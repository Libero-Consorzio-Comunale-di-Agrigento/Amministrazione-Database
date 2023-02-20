//ComuniFiltro DataSource @3-6BBCCF02
package ad4web.Ad4DizionariComuniElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ComuniFiltro DataSource

//class DataObject Header @3-59CFE62C
public class ComuniFiltroDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @3-F381710B
    

    TextField urlS_PROVINCIA = new TextField(null, null);
    
    TextField urlS_COMUNE = new TextField(null, null);
    

    private ComuniFiltroRow row = new ComuniFiltroRow();

//End attributes of DataObject

//properties of DataObject @3-F66F1B11

    public void  setUrlS_PROVINCIA( String param ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_PROVINCIA( Object param ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_PROVINCIA( Object param, Format ignore ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_COMUNE( String param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param, Format ignore ) {
        this.urlS_COMUNE.setValue( param );
    }

    public ComuniFiltroRow getRow() {
        return row;
    }

    public void setRow( ComuniFiltroRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @3-9DD4AE2B
    public ComuniFiltroDataObject(Page page) {
        super(page);
    }
//End constructor

//load @3-B649ECE1
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select ad4_ccs.filter_search('{s_COMUNE}'||'{s_PROVINCIA}') filter_search "
                    + "      ,'{s_PROVINCIA}'                      s_provincia "
                    + "      ,'{s_COMUNE}'                         s_comune "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlS_PROVINCIA.getObjectValue() ) ) urlS_PROVINCIA.setValue( "" );
        command.addParameter( "s_PROVINCIA", urlS_PROVINCIA, null );
        if ( StringUtils.isEmpty( (String) urlS_COMUNE.getObjectValue() ) ) urlS_COMUNE.setValue( "" );
        command.addParameter( "s_COMUNE", urlS_COMUNE, null );
        if ( ! command.isSetAllParams() ) {
            empty = true;
            ds.closeConnection();
            return true;
        }
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        }

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        DbRow record = null;
        if ( ! ds.hasErrors() ) {
            record = command.getOneRow();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @3-EB20A534
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setFILTER_SEARCH(Utils.convertToString(ds.parse(record.get("FILTER_SEARCH"), row.getFILTER_SEARCHField())));
            row.setS_PROVINCIA(Utils.convertToString(ds.parse(record.get("S_PROVINCIA"), row.getS_PROVINCIAField())));
            row.setS_COMUNE(Utils.convertToString(ds.parse(record.get("S_COMUNE"), row.getS_COMUNEField())));
        }
//End loadDataBind

//End of load @3-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @3-625FE7C5
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_PROVINCIA".equals(name) && "url".equals(prefix) ) {
                param = urlS_PROVINCIA;
            } else if ( "s_PROVINCIA".equals(name) && prefix == null ) {
                param = urlS_PROVINCIA;
            }
            if ( "s_COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlS_COMUNE;
            } else if ( "s_COMUNE".equals(name) && prefix == null ) {
                param = urlS_COMUNE;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @3-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @3-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @3-305A023C
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @3-D00ACF95
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @3-3BAD39CE
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildInsertEvent @3-FBA08B71
    public void fireBeforeBuildInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildInsert(e);
        }
    }
//End fireBeforeBuildInsertEvent

//fireBeforeExecuteInsertEvent @3-47AFA6A5
    public void fireBeforeExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteInsert(e);
        }
    }
//End fireBeforeExecuteInsertEvent

//fireAfterExecuteInsertEvent @3-E9CE95AE
    public void fireAfterExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteInsert(e);
        }
    }
//End fireAfterExecuteInsertEvent

//fireBeforeBuildSelectEvent @3-2405BE8B
    public void fireBeforeBuildUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildUpdate(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @3-E9DFF86B
    public void fireBeforeExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteUpdate(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @3-580A2987
    public void fireAfterExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteUpdate(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildSelectEvent @3-D021D0EA
    public void fireBeforeBuildDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildDelete(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteDeleteEvent @3-DD540FBB
    public void fireBeforeExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteDelete(e);
        }
    }
//End fireBeforeExecuteDeleteEvent

//fireAfterExecuteDeleteEvent @3-2A6E2049
    public void fireAfterExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteDelete(e);
        }
    }
//End fireAfterExecuteDeleteEvent

//class DataObject Tail @3-ED3F53A4
} // End of class DS
//End class DataObject Tail

