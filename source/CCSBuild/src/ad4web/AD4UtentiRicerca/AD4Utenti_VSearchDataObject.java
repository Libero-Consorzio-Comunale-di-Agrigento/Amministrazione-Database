//AD4Utenti_VSearch DataSource @6-186E581C
package ad4web.AD4UtentiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4Utenti_VSearch DataSource

//class DataObject Header @6-F7D62787
public class AD4Utenti_VSearchDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-4F22129F
    

    TextField urlS_NOMINATIVO = new TextField(null, null);
    
    TextField urlS_NOME = new TextField(null, null);
    
    TextField urlS_COD_FISCALE = new TextField(null, null);
    
    TextField urlS_STATO = new TextField(null, null);
    
    TextField urlS_GRUPPO = new TextField(null, null);
    
    TextField urlS_ACCESSO = new TextField(null, null);
    
    TextField urlS_DATA_DA = new TextField(null, null);
    
    TextField urlS_DATA_A = new TextField(null, null);
    

    private AD4Utenti_VSearchRow row = new AD4Utenti_VSearchRow();

//End attributes of DataObject

//properties of DataObject @6-87A89ACD

    public void  setUrlS_NOMINATIVO( String param ) {
        this.urlS_NOMINATIVO.setValue( param );
    }

    public void  setUrlS_NOMINATIVO( Object param ) {
        this.urlS_NOMINATIVO.setValue( param );
    }

    public void  setUrlS_NOMINATIVO( Object param, Format ignore ) {
        this.urlS_NOMINATIVO.setValue( param );
    }

    public void  setUrlS_NOME( String param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param, Format ignore ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_COD_FISCALE( String param ) {
        this.urlS_COD_FISCALE.setValue( param );
    }

    public void  setUrlS_COD_FISCALE( Object param ) {
        this.urlS_COD_FISCALE.setValue( param );
    }

    public void  setUrlS_COD_FISCALE( Object param, Format ignore ) {
        this.urlS_COD_FISCALE.setValue( param );
    }

    public void  setUrlS_STATO( String param ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_STATO( Object param ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_STATO( Object param, Format ignore ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_GRUPPO( String param ) {
        this.urlS_GRUPPO.setValue( param );
    }

    public void  setUrlS_GRUPPO( Object param ) {
        this.urlS_GRUPPO.setValue( param );
    }

    public void  setUrlS_GRUPPO( Object param, Format ignore ) {
        this.urlS_GRUPPO.setValue( param );
    }

    public void  setUrlS_ACCESSO( String param ) {
        this.urlS_ACCESSO.setValue( param );
    }

    public void  setUrlS_ACCESSO( Object param ) {
        this.urlS_ACCESSO.setValue( param );
    }

    public void  setUrlS_ACCESSO( Object param, Format ignore ) {
        this.urlS_ACCESSO.setValue( param );
    }

    public void  setUrlS_DATA_DA( String param ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_DA( Object param ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_DA( Object param, Format ignore ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_A( String param ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_DATA_A( Object param ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_DATA_A( Object param, Format ignore ) {
        this.urlS_DATA_A.setValue( param );
    }

    public AD4Utenti_VSearchRow getRow() {
        return row;
    }

    public void setRow( AD4Utenti_VSearchRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-A83A081B
    public AD4Utenti_VSearchDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-118B95BF
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select '<img title=\"'||decode('{s_NOMINATIVO}', "
                    + "                               null, "
                    + "                               'Filtro non attivo', "
                    + "                               'Filtro attivo' "
                    + "                             ) "
                    + "          ||'\" alt=\"'||decode('{s_NOMINATIVO}', "
                    + "                               null, "
                    + "                               'Filtro non attivo', "
                    + "                               'Filtro attivo' "
                    + "                             ) "
                    + "		       	  ||'\" height=\"18\"' "
                    + "           ||' src=\"'||decode('{s_NOMINATIVO}', "
                    + "                               null, "
                    + "                               '../common/images/AD4/filtro_off.gif', "
                    + "                               '../common/images/AD4/filtro_on.gif') "
                    + "        ||'\" width=\"18\" border=\"0\">' immagine_filtro "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlS_NOMINATIVO.getObjectValue() ) ) urlS_NOMINATIVO.setValue( "" );
        command.addParameter( "s_NOMINATIVO", urlS_NOMINATIVO, null );
        if ( StringUtils.isEmpty( (String) urlS_NOME.getObjectValue() ) ) urlS_NOME.setValue( "" );
        command.addParameter( "s_NOME", urlS_NOME, null );
        if ( StringUtils.isEmpty( (String) urlS_COD_FISCALE.getObjectValue() ) ) urlS_COD_FISCALE.setValue( "" );
        command.addParameter( "s_COD_FISCALE", urlS_COD_FISCALE, null );
        if ( StringUtils.isEmpty( (String) urlS_STATO.getObjectValue() ) ) urlS_STATO.setValue( "" );
        command.addParameter( "s_STATO", urlS_STATO, null );
        if ( StringUtils.isEmpty( (String) urlS_GRUPPO.getObjectValue() ) ) urlS_GRUPPO.setValue( "" );
        command.addParameter( "s_GRUPPO", urlS_GRUPPO, null );
        if ( StringUtils.isEmpty( (String) urlS_ACCESSO.getObjectValue() ) ) urlS_ACCESSO.setValue( "" );
        command.addParameter( "s_ACCESSO", urlS_ACCESSO, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_DA.getObjectValue() ) ) urlS_DATA_DA.setValue( "" );
        command.addParameter( "s_DATA_DA", urlS_DATA_DA, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_A.getObjectValue() ) ) urlS_DATA_A.setValue( "" );
        command.addParameter( "s_DATA_A", urlS_DATA_A, null );
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

//loadDataBind @6-7CF53621
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setIMMAGINE_FILTRO(Utils.convertToString(ds.parse(record.get("IMMAGINE_FILTRO"), row.getIMMAGINE_FILTROField())));
            row.setDESCRIZIONE_FILTRO(Utils.convertToString(ds.parse(record.get("DESCRIZIONE_FILTRO"), row.getDESCRIZIONE_FILTROField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @6-FF0CD27F
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_NOMINATIVO".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOMINATIVO;
            } else if ( "s_NOMINATIVO".equals(name) && prefix == null ) {
                param = urlS_NOMINATIVO;
            }
            if ( "s_NOME".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOME;
            } else if ( "s_NOME".equals(name) && prefix == null ) {
                param = urlS_NOME;
            }
            if ( "s_COD_FISCALE".equals(name) && "url".equals(prefix) ) {
                param = urlS_COD_FISCALE;
            } else if ( "s_COD_FISCALE".equals(name) && prefix == null ) {
                param = urlS_COD_FISCALE;
            }
            if ( "s_STATO".equals(name) && "url".equals(prefix) ) {
                param = urlS_STATO;
            } else if ( "s_STATO".equals(name) && prefix == null ) {
                param = urlS_STATO;
            }
            if ( "s_GRUPPO".equals(name) && "url".equals(prefix) ) {
                param = urlS_GRUPPO;
            } else if ( "s_GRUPPO".equals(name) && prefix == null ) {
                param = urlS_GRUPPO;
            }
            if ( "s_ACCESSO".equals(name) && "url".equals(prefix) ) {
                param = urlS_ACCESSO;
            } else if ( "s_ACCESSO".equals(name) && prefix == null ) {
                param = urlS_ACCESSO;
            }
            if ( "s_DATA_DA".equals(name) && "url".equals(prefix) ) {
                param = urlS_DATA_DA;
            } else if ( "s_DATA_DA".equals(name) && prefix == null ) {
                param = urlS_DATA_DA;
            }
            if ( "s_DATA_A".equals(name) && "url".equals(prefix) ) {
                param = urlS_DATA_A;
            } else if ( "s_DATA_A".equals(name) && prefix == null ) {
                param = urlS_DATA_A;
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

