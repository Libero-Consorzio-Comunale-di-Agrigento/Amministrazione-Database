//AD4Soggetti_VSearch DataSource @6-1FE29091
package ad4web.AD4SoggRicercaInclusa;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4Soggetti_VSearch DataSource

//class DataObject Header @6-814DAAF5
public class AD4Soggetti_VSearchDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-9B04DE7B
    

    TextField urlS_NOME = new TextField(null, null);
    
    TextField sesAD4PaginaSoggetto = new TextField(null, null);
    

    private AD4Soggetti_VSearchRow row = new AD4Soggetti_VSearchRow();

//End attributes of DataObject

//properties of DataObject @6-3342CD50

    public void  setUrlS_NOME( String param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param, Format ignore ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setSesAD4PaginaSoggetto( String param ) {
        this.sesAD4PaginaSoggetto.setValue( param );
    }

    public void  setSesAD4PaginaSoggetto( Object param ) {
        this.sesAD4PaginaSoggetto.setValue( param );
    }

    public void  setSesAD4PaginaSoggetto( Object param, Format ignore ) {
        this.sesAD4PaginaSoggetto.setValue( param );
    }

    public AD4Soggetti_VSearchRow getRow() {
        return row;
    }

    public void setRow( AD4Soggetti_VSearchRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-EC3CABCE
    public AD4Soggetti_VSearchDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-F790BE49
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select '<img title=\"'||decode('{s_NOME}', "
                    + "                               null, "
                    + "                               'Filtro non attivo', "
                    + "                               'Filtro attivo' "
                    + "                             ) "
                    + "          ||'\" alt=\"'||decode('{s_NOME}', "
                    + "                               null, "
                    + "                               'Filtro non attivo', "
                    + "                               'Filtro attivo' "
                    + "                             ) "
                    + "		       	  ||'\" height=\"18\"' "
                    + "           ||' src=\"'||decode('{s_NOME}', "
                    + "                               null, "
                    + "                               '../common/images/AD4/filtro_off.gif', "
                    + "                               '../common/images/AD4/filtro_on.gif') "
                    + "        ||'\" width=\"18\" border=\"0\">' immagine_filtro, "
                    + "        '<a class=\"AFCDataLink\" href=\"'||nvl('{AD4PaginaSoggetto}','AD4Soggetto.do')||'\">Nuovo</a>' pagina "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlS_NOME.getObjectValue() ) ) urlS_NOME.setValue( "" );
        command.addParameter( "s_NOME", urlS_NOME, null );
        if ( StringUtils.isEmpty( (String) sesAD4PaginaSoggetto.getObjectValue() ) ) sesAD4PaginaSoggetto.setValue( "" );
        command.addParameter( "AD4PaginaSoggetto", sesAD4PaginaSoggetto, null );
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

//loadDataBind @6-0273C898
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setIMMAGINE_FILTRO(Utils.convertToString(ds.parse(record.get("IMMAGINE_FILTRO"), row.getIMMAGINE_FILTROField())));
            row.setNuovo(Utils.convertToString(ds.parse(record.get("PAGINA"), row.getNuovoField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @6-0587FFBA
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_NOME".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOME;
            } else if ( "s_NOME".equals(name) && prefix == null ) {
                param = urlS_NOME;
            }
            if ( "AD4PaginaSoggetto".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PaginaSoggetto;
            } else if ( "AD4PaginaSoggetto".equals(name) && prefix == null ) {
                param = sesAD4PaginaSoggetto;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @6-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @6-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @6-305A023C
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

//fireBeforeExecuteSelectEvent @6-D00ACF95
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

//fireAfterExecuteSelectEvent @6-3BAD39CE
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

//fireBeforeBuildInsertEvent @6-FBA08B71
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

//fireBeforeExecuteInsertEvent @6-47AFA6A5
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

//fireAfterExecuteInsertEvent @6-E9CE95AE
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

//fireBeforeBuildSelectEvent @6-2405BE8B
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

//fireBeforeExecuteSelectEvent @6-E9DFF86B
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

//fireAfterExecuteSelectEvent @6-580A2987
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

//fireBeforeBuildSelectEvent @6-D021D0EA
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

//fireBeforeExecuteDeleteEvent @6-DD540FBB
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

//fireAfterExecuteDeleteEvent @6-2A6E2049
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

//class DataObject Tail @6-ED3F53A4
} // End of class DS
//End class DataObject Tail

