//AD4Accessi_VSearch DataSource @358-281BB8A3
package ad4web.AD4AccessiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4Accessi_VSearch DataSource

//class DataObject Header @358-146D30BA
public class AD4Accessi_VSearchDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @358-C8701735
    

    TextField urlS_TIPO = new TextField(null, null);
    
    TextField urlS_TERMINALE = new TextField(null, null);
    
    TextField urlS_UTENTE = new TextField(null, null);
    
    TextField urlS_MODULO = new TextField(null, null);
    
    TextField urlS_ISTANZA = new TextField(null, null);
    
    TextField urlS_DB_USER = new TextField(null, null);
    
    TextField urlS_DATA_DA = new TextField(null, null);
    
    TextField urlS_DATA_A = new TextField(null, null);
    
    TextField urlS_NOTE = new TextField(null, null);
    
    TextField urlS_AUTENTICAZIONE = new TextField(null, null);
    
    TextField urlS_STATO = new TextField(null, null);
    
    LongField sesAD4ACCEELI = new LongField(null, null);
    

    private AD4Accessi_VSearchRow row = new AD4Accessi_VSearchRow();

//End attributes of DataObject

//properties of DataObject @358-F95BAF82

    public void  setUrlS_TIPO( String param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param, Format ignore ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TERMINALE( String param ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_TERMINALE( Object param ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_TERMINALE( Object param, Format ignore ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_UTENTE( String param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param, Format ignore ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_MODULO( String param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param, Format ignore ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_ISTANZA( String param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param, Format ignore ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_DB_USER( String param ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DB_USER( Object param ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DB_USER( Object param, Format ignore ) {
        this.urlS_DB_USER.setValue( param );
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

    public void  setUrlS_NOTE( String param ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_NOTE( Object param ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_NOTE( Object param, Format ignore ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( String param ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( Object param ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( Object param, Format ignore ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
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

    public void  setSesAD4ACCEELI( long param ) {
        this.sesAD4ACCEELI.setValue( param );
    }

    public void  setSesAD4ACCEELI( long param, Format ignore ) throws java.text.ParseException {
        this.sesAD4ACCEELI.setValue( param );
    }

    public void  setSesAD4ACCEELI( Object param, Format format ) throws java.text.ParseException {
        this.sesAD4ACCEELI.setValue( param, format );
    }

    public void  setSesAD4ACCEELI( Long param ) {
        this.sesAD4ACCEELI.setValue( param );
    }

    public AD4Accessi_VSearchRow getRow() {
        return row;
    }

    public void setRow( AD4Accessi_VSearchRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @358-6E21BCF5
    public AD4Accessi_VSearchDataObject(Page page) {
        super(page);
    }
//End constructor

//load @358-78FC2052
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select '<img title=\"'||decode('{s_TERMINALE}', "
                    + "                               null, "
                    + "                               decode('{s_UTENTE}', "
                    + "                                       null, "
                    + "                                       decode('{s_ISTANZA}', "
                    + "                                               null, "
                    + "                                               decode('{s_MODULO}', "
                    + "                                                       null, "
                    + "                                                       decode('{s_DB_USER}', "
                    + "                                                               null, "
                    + "                                                               decode('{s_DATA_DA}', "
                    + "                                                                       null, "
                    + "                                                                       decode('{s_DATA_A}', "
                    + "                                                                               null, "
                    + "                                                                               decode('{s_NOTE}', "
                    + "                                                                                       null, "
                    + "                                                                                       decode('{s_AUTENTICAZIONE}', "
                    + "                                                                                               null, "
                    + "                                                                                               decode('{s_STATO}', "
                    + "                                                                                                       null, "
                    + "                                                                                                       decode('{s_TIPO}', "
                    + "                                                                                                               null, "
                    + "                                                                                                               'Filtro non attivo', "
                    + "                                                                                                               'Filtro attivo' "
                    + "                                                                                                             ), "
                    + "                                                                                                       'Filtro attivo' "
                    + "                                                                                                     ), "
                    + "                                                                                               'Filtro attivo' "
                    + "                                                                                             ), "
                    + "                                                                                       'Filtro attivo' "
                    + "                                                                                     ), "
                    + "                                                                               'Filtro attivo' "
                    + "                                                                             ), "
                    + "                                                                       'Filtro attivo' "
                    + "                                                                     ), "
                    + "                                                               'Filtro attivo' "
                    + "                                                             ), "
                    + "                                                       'Filtro attivo' "
                    + "                                                     ), "
                    + "                                               'Filtro attivo' "
                    + "                                             ), "
                    + "                                       'Filtro attivo' "
                    + "                                     ), "
                    + "                                    'Filtro attivo' "
                    + "                                  ) "
                    + "          ||'\" alt=\"'||decode('{s_TERMINALE}', "
                    + "                               null, "
                    + "                               decode('{s_UTENTE}', "
                    + "                                       null, "
                    + "                                       decode('{s_ISTANZA}', "
                    + "                                               null, "
                    + "                                               decode('{s_MODULO}', "
                    + "                                                       null, "
                    + "                                                       decode('{s_DB_USER}', "
                    + "                                                               null, "
                    + "                                                               decode('{s_DATA_DA}', "
                    + "                                                                       null, "
                    + "                                                                       decode('{s_DATA_A}', "
                    + "                                                                               null, "
                    + "                                                                               decode('{s_NOTE}', "
                    + "                                                                                       null, "
                    + "                                                                                       decode('{s_AUTENTICAZIONE}', "
                    + "                                                                                               null, "
                    + "                                                                                               decode('{s_STATO}', "
                    + "                                                                                                       null, "
                    + "                                                                                                       decode('{s_TIPO}', "
                    + "                                                                                                               null, "
                    + "                                                                                                               'Filtro non attivo', "
                    + "                                                                                                               'Filtro attivo' "
                    + "                                                                                                             ), "
                    + "                                                                                                       'Filtro attivo' "
                    + "                                                                                                     ), "
                    + "                                                                                               'Filtro attivo' "
                    + "                                                                                             ), "
                    + "                                                                                       'Filtro attivo' "
                    + "                                                                                     ), "
                    + "                                                                               'Filtro attivo' "
                    + "                                                                             ), "
                    + "                                                                       'Filtro attivo' "
                    + "                                                                     ), "
                    + "                                                               'Filtro attivo' "
                    + "                                                             ), "
                    + "                                                       'Filtro attivo' "
                    + "                                                     ), "
                    + "                                               'Filtro attivo' "
                    + "                                             ), "
                    + "                                       'Filtro attivo' "
                    + "                                     ), "
                    + "                               'Filtro attivo' "
                    + "                             ) "
                    + "		       	  ||'\" height=\"18\"' "
                    + "           ||' src=\"'||decode('{s_TERMINALE}', "
                    + "                               null, "
                    + "                               decode('{s_UTENTE}', "
                    + "                                       null, "
                    + "                                       decode('{s_ISTANZA}', "
                    + "                                               null, "
                    + "                                               decode('{s_MODULO}', "
                    + "                                                       null, "
                    + "                                                       decode('{s_DB_USER}', "
                    + "                                                               null, "
                    + "                                                               decode('{s_DATA_DA}', "
                    + "                                                                       null, "
                    + "                                                                       decode('{s_DATA_A}', "
                    + "                                                                               null, "
                    + "                                                                               decode('{s_NOTE}', "
                    + "                                                                                       null, "
                    + "                                                                                       decode('{s_AUTENTICAZIONE}', "
                    + "                                                                                               null, "
                    + "                                                                                               decode('{s_STATO}', "
                    + "                                                                                                       null, "
                    + "                                                                                                       decode('{s_TIPO}', "
                    + "                                                                                                               null, "
                    + "                                                                                                               '../common/images/AD4/filtro_off.gif', "
                    + "                                                                                                               '../common/images/AD4/filtro_on.gif' "
                    + "                                                                                                             ), "
                    + "                                                                                                       '../common/images/AD4/filtro_on.gif' "
                    + "                                                                                                     ), "
                    + "                                                                                               '../common/images/AD4/filtro_on.gif' "
                    + "                                                                                             ), "
                    + "                                                                                       '../common/images/AD4/filtro_on.gif' "
                    + "                                                                                     ), "
                    + "                                                                               '../common/images/AD4/filtro_on.gif' "
                    + "                                                                             ), "
                    + "                                                                       '../common/images/AD4/filtro_on.gif' "
                    + "                                                                     ), "
                    + "                                                               '../common/images/AD4/filtro_on.gif' "
                    + "                                                             ), "
                    + "                                                       '../common/images/AD4/filtro_on.gif' "
                    + "                                                     ), "
                    + "                                               '../common/images/AD4/filtro_on.gif' "
                    + "                                             ), "
                    + "                                       '../common/images/AD4/filtro_on.gif' "
                    + "                                     ),							    "
                    + "                               '../common/images/AD4/filtro_on.gif') "
                    + "        ||'\" width=\"18\" border=\"0\">' immagine_filtro, "
                    + "       {AD4ACCEELI} ELIMINATI "
                    + "  from dual" );
        if ( StringUtils.isEmpty( (String) urlS_TIPO.getObjectValue() ) ) urlS_TIPO.setValue( "" );
        command.addParameter( "s_TIPO", urlS_TIPO, null );
        if ( StringUtils.isEmpty( (String) urlS_TERMINALE.getObjectValue() ) ) urlS_TERMINALE.setValue( "" );
        command.addParameter( "s_TERMINALE", urlS_TERMINALE, null );
        if ( StringUtils.isEmpty( (String) urlS_UTENTE.getObjectValue() ) ) urlS_UTENTE.setValue( "" );
        command.addParameter( "s_UTENTE", urlS_UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlS_MODULO.getObjectValue() ) ) urlS_MODULO.setValue( "" );
        command.addParameter( "s_MODULO", urlS_MODULO, null );
        if ( StringUtils.isEmpty( (String) urlS_ISTANZA.getObjectValue() ) ) urlS_ISTANZA.setValue( "" );
        command.addParameter( "s_ISTANZA", urlS_ISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlS_DB_USER.getObjectValue() ) ) urlS_DB_USER.setValue( "" );
        command.addParameter( "s_DB_USER", urlS_DB_USER, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_DA.getObjectValue() ) ) urlS_DATA_DA.setValue( "" );
        command.addParameter( "s_DATA_DA", urlS_DATA_DA, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_A.getObjectValue() ) ) urlS_DATA_A.setValue( "" );
        command.addParameter( "s_DATA_A", urlS_DATA_A, null );
        if ( StringUtils.isEmpty( (String) urlS_NOTE.getObjectValue() ) ) urlS_NOTE.setValue( "" );
        command.addParameter( "s_NOTE", urlS_NOTE, null );
        if ( StringUtils.isEmpty( (String) urlS_AUTENTICAZIONE.getObjectValue() ) ) urlS_AUTENTICAZIONE.setValue( "" );
        command.addParameter( "s_AUTENTICAZIONE", urlS_AUTENTICAZIONE, null );
        if ( StringUtils.isEmpty( (String) urlS_STATO.getObjectValue() ) ) urlS_STATO.setValue( "" );
        command.addParameter( "s_STATO", urlS_STATO, null );
        if ( sesAD4ACCEELI.getObjectValue() == null ) sesAD4ACCEELI.setValue( 0 );
        command.addParameter( "AD4ACCEELI", sesAD4ACCEELI, null );
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

//loadDataBind @358-E19D3B2B
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setDESCRIZIONE_FILTRO(Utils.convertToString(ds.parse(record.get("DESCRIZIONE_FILTRO"), row.getDESCRIZIONE_FILTROField())));
            row.setIMMAGINE_FILTRO(Utils.convertToString(ds.parse(record.get("IMMAGINE_FILTRO"), row.getIMMAGINE_FILTROField())));
            row.setELIMINATI(Utils.convertToString(ds.parse(record.get("ELIMINATI"), row.getELIMINATIField())));
        }
//End loadDataBind

//End of load @358-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @358-1EC1775B
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_TIPO".equals(name) && "url".equals(prefix) ) {
                param = urlS_TIPO;
            } else if ( "s_TIPO".equals(name) && prefix == null ) {
                param = urlS_TIPO;
            }
            if ( "s_TERMINALE".equals(name) && "url".equals(prefix) ) {
                param = urlS_TERMINALE;
            } else if ( "s_TERMINALE".equals(name) && prefix == null ) {
                param = urlS_TERMINALE;
            }
            if ( "s_UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_UTENTE;
            } else if ( "s_UTENTE".equals(name) && prefix == null ) {
                param = urlS_UTENTE;
            }
            if ( "s_MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlS_MODULO;
            } else if ( "s_MODULO".equals(name) && prefix == null ) {
                param = urlS_MODULO;
            }
            if ( "s_ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlS_ISTANZA;
            } else if ( "s_ISTANZA".equals(name) && prefix == null ) {
                param = urlS_ISTANZA;
            }
            if ( "s_DB_USER".equals(name) && "url".equals(prefix) ) {
                param = urlS_DB_USER;
            } else if ( "s_DB_USER".equals(name) && prefix == null ) {
                param = urlS_DB_USER;
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
            if ( "s_NOTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOTE;
            } else if ( "s_NOTE".equals(name) && prefix == null ) {
                param = urlS_NOTE;
            }
            if ( "s_AUTENTICAZIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_AUTENTICAZIONE;
            } else if ( "s_AUTENTICAZIONE".equals(name) && prefix == null ) {
                param = urlS_AUTENTICAZIONE;
            }
            if ( "s_STATO".equals(name) && "url".equals(prefix) ) {
                param = urlS_STATO;
            } else if ( "s_STATO".equals(name) && prefix == null ) {
                param = urlS_STATO;
            }
            if ( "AD4ACCEELI".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4ACCEELI;
            } else if ( "AD4ACCEELI".equals(name) && prefix == null ) {
                param = sesAD4ACCEELI;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @358-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @358-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @358-305A023C
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

//fireBeforeExecuteSelectEvent @358-D00ACF95
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

//fireAfterExecuteSelectEvent @358-3BAD39CE
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

//fireBeforeBuildInsertEvent @358-FBA08B71
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

//fireBeforeExecuteInsertEvent @358-47AFA6A5
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

//fireAfterExecuteInsertEvent @358-E9CE95AE
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

//fireBeforeBuildSelectEvent @358-2405BE8B
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

//fireBeforeExecuteSelectEvent @358-E9DFF86B
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

//fireAfterExecuteSelectEvent @358-580A2987
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

//fireBeforeBuildSelectEvent @358-D021D0EA
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

//fireBeforeExecuteDeleteEvent @358-DD540FBB
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

//fireAfterExecuteDeleteEvent @358-2A6E2049
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

//class DataObject Tail @358-ED3F53A4
} // End of class DS
//End class DataObject Tail

