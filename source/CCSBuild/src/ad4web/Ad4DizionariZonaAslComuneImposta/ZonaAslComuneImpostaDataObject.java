//ZonaAslComuneImposta DataSource @5-6FE4D33A
package ad4web.Ad4DizionariZonaAslComuneImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ZonaAslComuneImposta DataSource

//class DataObject Header @5-54FC66AA
public class ZonaAslComuneImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-CF9D65F7
    

    LongField urlID_ZONA_ASL = new LongField(null, null);
    
    LongField urlPROVINCIA = new LongField(null, null);
    
    LongField urlCOMUNE = new LongField(null, null);
    

    private ZonaAslComuneImpostaRow row = new ZonaAslComuneImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-FCC942CB

    public void  setUrlID_ZONA_ASL( long param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( long param, Format ignore ) throws java.text.ParseException {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlID_ZONA_ASL( Object param, Format format ) throws java.text.ParseException {
        this.urlID_ZONA_ASL.setValue( param, format );
    }

    public void  setUrlID_ZONA_ASL( Long param ) {
        this.urlID_ZONA_ASL.setValue( param );
    }

    public void  setUrlPROVINCIA( long param ) {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlPROVINCIA( long param, Format ignore ) throws java.text.ParseException {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlPROVINCIA( Object param, Format format ) throws java.text.ParseException {
        this.urlPROVINCIA.setValue( param, format );
    }

    public void  setUrlPROVINCIA( Long param ) {
        this.urlPROVINCIA.setValue( param );
    }

    public void  setUrlCOMUNE( long param ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( long param, Format ignore ) throws java.text.ParseException {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( Object param, Format format ) throws java.text.ParseException {
        this.urlCOMUNE.setValue( param, format );
    }

    public void  setUrlCOMUNE( Long param ) {
        this.urlCOMUNE.setValue( param );
    }

    public ZonaAslComuneImpostaRow getRow() {
        return row;
    }

    public void setRow( ZonaAslComuneImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-D4B373AF
    public ZonaAslComuneImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-C8D17300
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select id_zona_asl "
                    + "      ,provincia "
                    + "      ,comune "
                    + "      ,ad4_ccs.get_zona_asl_titolo(id_zona_asl) titolo "
                    + "      ,ad4_ccs.get_comune_denominazione(provincia,comune) comune_desc "
                    + "      ,null                                               comune_lov "
                    + "  from zone_asl_comuni "
                    + " where provincia = {PROVINCIA} "
                    + "   and comune = {COMUNE} "
                    + "   and id_zona_asl = {ID_ZONA_ASL} "
                    + "union "
                    + "select {ID_ZONA_ASL} id_zona_asl "
                    + "      ,null          provincia "
                    + "      ,null          comune "
                    + "      ,ad4_ccs.get_zona_asl_titolo({ID_ZONA_ASL}) titolo "
                    + "      ,null comune_desc "
                    + "      ,ad4_ccs.link_multi_lov('Ad4DizionariComuniLov','','Cerca Comune..','ZonaAslComuneImposta','PROVINCIA#COMUNE#COMUNE_DESC')  "
                    + "       ||'<a class=\"AFCDataLink\" href=\"#\" onclick=\"' "
                    + "       ||'self.document.forms[0].PROVINCIA.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE_DESC.value='''';' "
                    + "       ||'\" title=\"Elimina Comune\">' "
                    + "       ||'<img height=\"18\" src=\"../images/clear.gif\" width=\"18\" border=\"0\">' "
                    + "       ||'</a>'                                                                  comune_lov "
                    + "  from dual "
                    + " where nvl({PROVINCIA},-1) = -1 "
                    + "   and nvl({COMUNE},-1) = -1" );
        if ( urlPROVINCIA.getObjectValue() == null ) urlPROVINCIA.setValue( -1 );
        command.addParameter( "PROVINCIA", urlPROVINCIA, null );
        if ( urlCOMUNE.getObjectValue() == null ) urlCOMUNE.setValue( -1 );
        command.addParameter( "COMUNE", urlCOMUNE, null );
        if ( urlID_ZONA_ASL.getObjectValue() == null ) urlID_ZONA_ASL.setValue( -1 );
        command.addParameter( "ID_ZONA_ASL", urlID_ZONA_ASL, null );
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

//loadDataBind @5-8C3DC5AF
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setTITOLO(Utils.convertToString(ds.parse(record.get("TITOLO"), row.getTITOLOField())));
            row.setID_ZONA_ASL(Utils.convertToLong(ds.parse(record.get("ID_ZONA_ASL"), row.getID_ZONA_ASLField())));
            row.setCOMUNE_DESC(Utils.convertToString(ds.parse(record.get("COMUNE_DESC"), row.getCOMUNE_DESCField())));
            row.setCOMUNE_LOV(Utils.convertToString(ds.parse(record.get("COMUNE_LOV"), row.getCOMUNE_LOVField())));
            row.setPROVINCIA(Utils.convertToLong(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
            row.setCOMUNE(Utils.convertToLong(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-8D6DEFF2
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ZONA_ASL_COMUNE_INS(?,?,?)}" );
            command.addParameter( "IN_ID_ZONA_ASL", urlID_ZONA_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_PROVINCIA", urlPROVINCIA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", urlCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildInsertEvent( new DataObjectEvent(command) );

//End insert

//insertDataBound @5-BC781F8A
            fireBeforeExecuteInsertEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteInsertEvent( new DataObjectEvent(command) );

//End insertDataBound

//End of insert @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of insert

//delete @5-97E26AB2
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.ZONA_ASL_COMUNE_DEL(?,?,?)}" );
            command.addParameter( "IN_ID_ZONA_ASL", urlID_ZONA_ASL, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_PROVINCIA", urlPROVINCIA, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", urlCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @5-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @5-654B31AE
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_ZONA_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlID_ZONA_ASL;
            } else if ( "ID_ZONA_ASL".equals(name) && prefix == null ) {
                param = urlID_ZONA_ASL;
            }
            if ( "PROVINCIA".equals(name) && "url".equals(prefix) ) {
                param = urlPROVINCIA;
            } else if ( "PROVINCIA".equals(name) && prefix == null ) {
                param = urlPROVINCIA;
            }
            if ( "COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = urlCOMUNE;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @5-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @5-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @5-305A023C
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

//fireBeforeExecuteSelectEvent @5-D00ACF95
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

//fireAfterExecuteSelectEvent @5-3BAD39CE
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

//fireBeforeBuildInsertEvent @5-FBA08B71
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

//fireBeforeExecuteInsertEvent @5-47AFA6A5
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

//fireAfterExecuteInsertEvent @5-E9CE95AE
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

//fireBeforeBuildSelectEvent @5-2405BE8B
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

//fireBeforeExecuteSelectEvent @5-E9DFF86B
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

//fireAfterExecuteSelectEvent @5-580A2987
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

//fireBeforeBuildSelectEvent @5-D021D0EA
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

//fireBeforeExecuteDeleteEvent @5-DD540FBB
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

//fireAfterExecuteDeleteEvent @5-2A6E2049
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

//class DataObject Tail @5-ED3F53A4
} // End of class DS
//End class DataObject Tail

