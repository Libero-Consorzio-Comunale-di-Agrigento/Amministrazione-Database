//SportelloImposta DataSource @5-EAA7E716
package restrict.Ad4DizionariSportelloImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End SportelloImposta DataSource

//class DataObject Header @5-C3F4662E
public class SportelloImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-96702CD2
    

    TextField ctrlABI = new TextField(null, null);
    
    TextField ctrlCAB = new TextField(null, null);
    
    TextField ctrlCIN_CAB = new TextField(null, null);
    
    TextField ctrlDESCRIZIONE = new TextField(null, null);
    
    TextField ctrlINDIRIZZO = new TextField(null, null);
    
    TextField ctrlLOCALITA = new TextField(null, null);
    
    TextField ctrlCOMUNE = new TextField(null, null);
    
    TextField ctrlPROVINCIA = new TextField(null, null);
    
    TextField ctrlCAP = new TextField(null, null);
    
    TextField ctrlDIPENDENZA = new TextField(null, null);
    
    TextField ctrlBIC = new TextField(null, null);
    
    TextField urlBANCA = new TextField(null, null);
    
    TextField urlSPORTELLO = new TextField(null, null);
    

    private SportelloImpostaRow row = new SportelloImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-01130D49

    public void  setCtrlABI( String param ) {
        this.ctrlABI.setValue( param );
    }

    public void  setCtrlABI( Object param ) {
        this.ctrlABI.setValue( param );
    }

    public void  setCtrlABI( Object param, Format ignore ) {
        this.ctrlABI.setValue( param );
    }

    public void  setCtrlCAB( String param ) {
        this.ctrlCAB.setValue( param );
    }

    public void  setCtrlCAB( Object param ) {
        this.ctrlCAB.setValue( param );
    }

    public void  setCtrlCAB( Object param, Format ignore ) {
        this.ctrlCAB.setValue( param );
    }

    public void  setCtrlCIN_CAB( String param ) {
        this.ctrlCIN_CAB.setValue( param );
    }

    public void  setCtrlCIN_CAB( Object param ) {
        this.ctrlCIN_CAB.setValue( param );
    }

    public void  setCtrlCIN_CAB( Object param, Format ignore ) {
        this.ctrlCIN_CAB.setValue( param );
    }

    public void  setCtrlDESCRIZIONE( String param ) {
        this.ctrlDESCRIZIONE.setValue( param );
    }

    public void  setCtrlDESCRIZIONE( Object param ) {
        this.ctrlDESCRIZIONE.setValue( param );
    }

    public void  setCtrlDESCRIZIONE( Object param, Format ignore ) {
        this.ctrlDESCRIZIONE.setValue( param );
    }

    public void  setCtrlINDIRIZZO( String param ) {
        this.ctrlINDIRIZZO.setValue( param );
    }

    public void  setCtrlINDIRIZZO( Object param ) {
        this.ctrlINDIRIZZO.setValue( param );
    }

    public void  setCtrlINDIRIZZO( Object param, Format ignore ) {
        this.ctrlINDIRIZZO.setValue( param );
    }

    public void  setCtrlLOCALITA( String param ) {
        this.ctrlLOCALITA.setValue( param );
    }

    public void  setCtrlLOCALITA( Object param ) {
        this.ctrlLOCALITA.setValue( param );
    }

    public void  setCtrlLOCALITA( Object param, Format ignore ) {
        this.ctrlLOCALITA.setValue( param );
    }

    public void  setCtrlCOMUNE( String param ) {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlCOMUNE( Object param ) {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlCOMUNE( Object param, Format ignore ) {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlPROVINCIA( String param ) {
        this.ctrlPROVINCIA.setValue( param );
    }

    public void  setCtrlPROVINCIA( Object param ) {
        this.ctrlPROVINCIA.setValue( param );
    }

    public void  setCtrlPROVINCIA( Object param, Format ignore ) {
        this.ctrlPROVINCIA.setValue( param );
    }

    public void  setCtrlCAP( String param ) {
        this.ctrlCAP.setValue( param );
    }

    public void  setCtrlCAP( Object param ) {
        this.ctrlCAP.setValue( param );
    }

    public void  setCtrlCAP( Object param, Format ignore ) {
        this.ctrlCAP.setValue( param );
    }

    public void  setCtrlDIPENDENZA( String param ) {
        this.ctrlDIPENDENZA.setValue( param );
    }

    public void  setCtrlDIPENDENZA( Object param ) {
        this.ctrlDIPENDENZA.setValue( param );
    }

    public void  setCtrlDIPENDENZA( Object param, Format ignore ) {
        this.ctrlDIPENDENZA.setValue( param );
    }

    public void  setCtrlBIC( String param ) {
        this.ctrlBIC.setValue( param );
    }

    public void  setCtrlBIC( Object param ) {
        this.ctrlBIC.setValue( param );
    }

    public void  setCtrlBIC( Object param, Format ignore ) {
        this.ctrlBIC.setValue( param );
    }

    public void  setUrlBANCA( String param ) {
        this.urlBANCA.setValue( param );
    }

    public void  setUrlBANCA( Object param ) {
        this.urlBANCA.setValue( param );
    }

    public void  setUrlBANCA( Object param, Format ignore ) {
        this.urlBANCA.setValue( param );
    }

    public void  setUrlSPORTELLO( String param ) {
        this.urlSPORTELLO.setValue( param );
    }

    public void  setUrlSPORTELLO( Object param ) {
        this.urlSPORTELLO.setValue( param );
    }

    public void  setUrlSPORTELLO( Object param, Format ignore ) {
        this.urlSPORTELLO.setValue( param );
    }

    public SportelloImpostaRow getRow() {
        return row;
    }

    public void setRow( SportelloImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-CB1ECF1B
    public SportelloImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-157AE908
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select abi "
                    + "      ,cab "
                    + "      ,cin_cab "
                    + "      ,descrizione "
                    + ",  "
                    + "ad4_ccs.get_link_traduzione_SPORTELLO(ABI,CAB) open_traduzione "
                    + "      ,indirizzo "
                    + "      ,localita "
                    + "      ,comune "
                    + "      ,provincia "
                    + "      ,cap "
                    + "      ,dipendenza "
                    + "      ,bic "
                    + "      ,'<!--' hide_begin "
                    + "      ,'-->' hide_end "
                    + "  from ad4_sportelli "
                    + " where abi = '{BANCA}' "
                    + "   and cab = '{SPORTELLO}' "
                    + "union "
                    + "select null abi "
                    + "      , null cab "
                    + "      , null cin_cab "
                    + "      , null descrizione "
                    + ", NULL OPEN_TRADUZIONE "
                    + "      , null indirizzo "
                    + "      , null localita "
                    + "      , null comune "
                    + "      , null provincia "
                    + "      , null cap "
                    + "      , null dipendenza "
                    + "      , null bic       "
                    + "      ,'<!--'                    hide_begin "
                    + "      ,'-->'                     hide_end "
                    + "  from ad4_sportelli "
                    + " where (   abi is null "
                    + "        or abi = -1 "
                    + "       ) "
                    + "   and (   cab is null "
                    + "        or cab = -1 "
                    + "       )" );
        if ( StringUtils.isEmpty( (String) urlBANCA.getObjectValue() ) ) urlBANCA.setValue( null );
        command.addParameter( "BANCA", urlBANCA, null );
        if ( StringUtils.isEmpty( (String) urlSPORTELLO.getObjectValue() ) ) urlSPORTELLO.setValue( null );
        command.addParameter( "SPORTELLO", urlSPORTELLO, null );
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

//loadDataBind @5-8503EFA6
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setABI_LABEL(Utils.convertToString(ds.parse(record.get("ABI"), row.getABI_LABELField())));
            row.setHIDE_BEGIN(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGINField())));
            row.setABI(Utils.convertToString(ds.parse(record.get("ABI"), row.getABIField())));
            row.setHIDE_END(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_ENDField())));
            row.setCAB_LABEL(Utils.convertToString(ds.parse(record.get("CAB"), row.getCAB_LABELField())));
            row.setHIDE_BEGIN2(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGIN2Field())));
            row.setCAB(Utils.convertToString(ds.parse(record.get("CAB"), row.getCABField())));
            row.setHIDE_END2(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_END2Field())));
            row.setCIN_CAB(Utils.convertToString(ds.parse(record.get("CIN_CAB"), row.getCIN_CABField())));
            row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
            row.setTRADUZIONE(Utils.convertToString(ds.parse(record.get("OPEN_TRADUZIONE"), row.getTRADUZIONEField())));
            row.setINDIRIZZO(Utils.convertToString(ds.parse(record.get("INDIRIZZO"), row.getINDIRIZZOField())));
            row.setLOCALITA(Utils.convertToString(ds.parse(record.get("LOCALITA"), row.getLOCALITAField())));
            row.setCOMUNE(Utils.convertToString(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
            row.setPROVINCIA(Utils.convertToString(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
            row.setCAP(Utils.convertToString(ds.parse(record.get("CAP"), row.getCAPField())));
            row.setDIPENDENZA(Utils.convertToString(ds.parse(record.get("DIPENDENZA"), row.getDIPENDENZAField())));
            row.setBIC(Utils.convertToString(ds.parse(record.get("BIC"), row.getBICField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-CC12F7CF
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.SPORTELLO_INS(?,?,?,?,?,?,?,?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) row.getABI()) ) row.setABI( "" );
            command.addParameter( "IN_ABI", row.getABIField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCAB()) ) row.setCAB( "" );
            command.addParameter( "IN_CAB", row.getCABField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCIN_CAB()) ) row.setCIN_CAB( "" );
            command.addParameter( "IN_CIN_CAB", row.getCIN_CABField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDESCRIZIONE()) ) row.setDESCRIZIONE( "" );
            command.addParameter( "IN_DESCRIZIONE", row.getDESCRIZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getINDIRIZZO()) ) row.setINDIRIZZO( "" );
            command.addParameter( "IN_INDIRIZZO", row.getINDIRIZZOField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getLOCALITA()) ) row.setLOCALITA( "" );
            command.addParameter( "IN_LOCALITA", row.getLOCALITAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOMUNE()) ) row.setCOMUNE( "" );
            command.addParameter( "IN_COMUNE", row.getCOMUNEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPROVINCIA()) ) row.setPROVINCIA( "" );
            command.addParameter( "IN_PROVINCIA", row.getPROVINCIAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCAP()) ) row.setCAP( "" );
            command.addParameter( "IN_CAP", row.getCAPField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDIPENDENZA()) ) row.setDIPENDENZA( "" );
            command.addParameter( "IN_DIPENDENZA", row.getDIPENDENZAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getBIC()) ) row.setBIC( "" );
            command.addParameter( "IN_BIC", row.getBICField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//update @5-CEA25E0F
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.SPORTELLO_UPD(?,?,?,?,?,?,?,?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) urlBANCA.getObjectValue() ) ) urlBANCA.setValue( "" );
            command.addParameter( "IN_ABI", urlBANCA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlSPORTELLO.getObjectValue() ) ) urlSPORTELLO.setValue( "" );
            command.addParameter( "IN_CAB", urlSPORTELLO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCIN_CAB()) ) row.setCIN_CAB( "" );
            command.addParameter( "IN_CIN_CAB", row.getCIN_CABField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDESCRIZIONE()) ) row.setDESCRIZIONE( "" );
            command.addParameter( "IN_DESCRIZIONE", row.getDESCRIZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getINDIRIZZO()) ) row.setINDIRIZZO( "" );
            command.addParameter( "IN_INDIRIZZO", row.getINDIRIZZOField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getLOCALITA()) ) row.setLOCALITA( "" );
            command.addParameter( "IN_LOCALITA", row.getLOCALITAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCOMUNE()) ) row.setCOMUNE( "" );
            command.addParameter( "IN_COMUNE", row.getCOMUNEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPROVINCIA()) ) row.setPROVINCIA( "" );
            command.addParameter( "IN_PROVINCIA", row.getPROVINCIAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCAP()) ) row.setCAP( "" );
            command.addParameter( "IN_CAP", row.getCAPField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDIPENDENZA()) ) row.setDIPENDENZA( "" );
            command.addParameter( "IN_DIPENDENZA", row.getDIPENDENZAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getBIC()) ) row.setBIC( "" );
            command.addParameter( "IN_BIC", row.getBICField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @5-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @5-C0FEF773
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.SPORTELLO_DEL(?,?)}" );
            if ( StringUtils.isEmpty( (String) urlBANCA.getObjectValue() ) ) urlBANCA.setValue( "" );
            command.addParameter( "IN_ABI", urlBANCA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlSPORTELLO.getObjectValue() ) ) urlSPORTELLO.setValue( "" );
            command.addParameter( "IN_CAB", urlSPORTELLO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @5-431C7A17
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ABI".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlABI;
            } else if ( "ABI".equals(name) && prefix == null ) {
                param = ctrlABI;
            }
            if ( "CAB".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCAB;
            } else if ( "CAB".equals(name) && prefix == null ) {
                param = ctrlCAB;
            }
            if ( "CIN_CAB".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCIN_CAB;
            } else if ( "CIN_CAB".equals(name) && prefix == null ) {
                param = ctrlCIN_CAB;
            }
            if ( "DESCRIZIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDESCRIZIONE;
            } else if ( "DESCRIZIONE".equals(name) && prefix == null ) {
                param = ctrlDESCRIZIONE;
            }
            if ( "INDIRIZZO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlINDIRIZZO;
            } else if ( "INDIRIZZO".equals(name) && prefix == null ) {
                param = ctrlINDIRIZZO;
            }
            if ( "LOCALITA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlLOCALITA;
            } else if ( "LOCALITA".equals(name) && prefix == null ) {
                param = ctrlLOCALITA;
            }
            if ( "COMUNE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = ctrlCOMUNE;
            }
            if ( "PROVINCIA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPROVINCIA;
            } else if ( "PROVINCIA".equals(name) && prefix == null ) {
                param = ctrlPROVINCIA;
            }
            if ( "CAP".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCAP;
            } else if ( "CAP".equals(name) && prefix == null ) {
                param = ctrlCAP;
            }
            if ( "DIPENDENZA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDIPENDENZA;
            } else if ( "DIPENDENZA".equals(name) && prefix == null ) {
                param = ctrlDIPENDENZA;
            }
            if ( "BIC".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlBIC;
            } else if ( "BIC".equals(name) && prefix == null ) {
                param = ctrlBIC;
            }
            if ( "BANCA".equals(name) && "url".equals(prefix) ) {
                param = urlBANCA;
            } else if ( "BANCA".equals(name) && prefix == null ) {
                param = urlBANCA;
            }
            if ( "SPORTELLO".equals(name) && "url".equals(prefix) ) {
                param = urlSPORTELLO;
            } else if ( "SPORTELLO".equals(name) && prefix == null ) {
                param = urlSPORTELLO;
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

