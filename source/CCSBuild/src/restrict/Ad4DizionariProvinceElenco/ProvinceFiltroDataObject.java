//ProvinceFiltro DataSource @3-68B4A733
package restrict.Ad4DizionariProvinceElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ProvinceFiltro DataSource

//class DataObject Header @3-5A985110
public class ProvinceFiltroDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @3-104E84D2
    

    TextField urlS_PROVINCIA = new TextField(null, null);
    
    TextField urlS_REGIONE = new TextField(null, null);
    

    private ProvinceFiltroRow row = new ProvinceFiltroRow();

//End attributes of DataObject

//properties of DataObject @3-06F10AA8

    public void  setUrlS_PROVINCIA( String param ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_PROVINCIA( Object param ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_PROVINCIA( Object param, Format ignore ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_REGIONE( String param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param, Format ignore ) {
        this.urlS_REGIONE.setValue( param );
    }

    public ProvinceFiltroRow getRow() {
        return row;
    }

    public void setRow( ProvinceFiltroRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @3-9F7C27FF
    public ProvinceFiltroDataObject(Page page) {
        super(page);
    }
//End constructor

//load @3-9A91BDCF
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select ad4_ccs.filter_search('{s_REGIONE}'||'{s_REGIONE}') filter_search "
                    + "      ,'{s_PROVINCIA}'                      s_provincia "
                    + "      ,'{s_REGIONE}'                        s_regione "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlS_PROVINCIA.getObjectValue() ) ) urlS_PROVINCIA.setValue( "" );
        command.addParameter( "s_PROVINCIA", urlS_PROVINCIA, null );
        if ( StringUtils.isEmpty( (String) urlS_REGIONE.getObjectValue() ) ) urlS_REGIONE.setValue( "" );
        command.addParameter( "s_REGIONE", urlS_REGIONE, null );
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

//loadDataBind @3-0314BF65
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setFILTER_SEARCH(Utils.convertToString(ds.parse(record.get("FILTER_SEARCH"), row.getFILTER_SEARCHField())));
            row.setS_REGIONE(Utils.convertToString(ds.parse(record.get("S_REGIONE"), row.getS_REGIONEField())));
            row.setS_PROVINCIA(Utils.convertToString(ds.parse(record.get("S_PROVINCIA"), row.getS_PROVINCIAField())));
        }
//End loadDataBind

//End of load @3-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @3-85B962C0
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_PROVINCIA".equals(name) && "url".equals(prefix) ) {
                param = urlS_PROVINCIA;
            } else if ( "s_PROVINCIA".equals(name) && prefix == null ) {
                param = urlS_PROVINCIA;
            }
            if ( "s_REGIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_REGIONE;
            } else if ( "s_REGIONE".equals(name) && prefix == null ) {
                param = urlS_REGIONE;
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

