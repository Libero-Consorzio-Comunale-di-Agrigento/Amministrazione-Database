//ComuneImposta DataSource @5-EAA7E716
package restrict.Ad4DizionariSportelloImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ComuneImposta DataSource

//class DataObject Header @5-91960CA4
public class ComuneImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-DB1E36C8
    

    LongField ctrlPROVINCIA_STATO = new LongField(null, null);
    
    LongField ctrlCOMUNE = new LongField(null, null);
    
    TextField ctrlDENOMINAZIONE = new TextField(null, null);
    
    TextField ctrlDENOMINAZIONE_BREVE = new TextField(null, null);
    
    TextField ctrlCAPOLUOGO_PROVINCIA = new TextField(null, null);
    
    LongField ctrlCAP = new LongField(null, null);
    
    LongField ctrlPROVINCIA_TRIBUNALE = new LongField(null, null);
    
    LongField ctrlCOMUNE_TRIBUNALE = new LongField(null, null);
    
    TextField ctrlDATA_SOPPRESSIONE = new TextField(null, null);
    
    LongField ctrlPROVINCIA_FUSIONE = new LongField(null, null);
    
    LongField ctrlCOMUNE_FUSIONE = new LongField(null, null);
    
    TextField ctrlSIGLA_CFIS = new TextField(null, null);
    
    TextField sesUtente = new TextField(null, null);
    
    LongField urlPROVINCIA_STATO = new LongField(null, null);
    
    LongField urlCOMUNE = new LongField(null, null);
    

    private ComuneImpostaRow row = new ComuneImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-206D67E0

    public void  setCtrlPROVINCIA_STATO( long param ) {
        this.ctrlPROVINCIA_STATO.setValue( param );
    }

    public void  setCtrlPROVINCIA_STATO( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlPROVINCIA_STATO.setValue( param );
    }

    public void  setCtrlPROVINCIA_STATO( Object param, Format format ) throws java.text.ParseException {
        this.ctrlPROVINCIA_STATO.setValue( param, format );
    }

    public void  setCtrlPROVINCIA_STATO( Long param ) {
        this.ctrlPROVINCIA_STATO.setValue( param );
    }

    public void  setCtrlCOMUNE( long param ) {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlCOMUNE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlCOMUNE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCOMUNE.setValue( param, format );
    }

    public void  setCtrlCOMUNE( Long param ) {
        this.ctrlCOMUNE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( String param ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( Object param ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( Object param, Format ignore ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE_BREVE( String param ) {
        this.ctrlDENOMINAZIONE_BREVE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE_BREVE( Object param ) {
        this.ctrlDENOMINAZIONE_BREVE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE_BREVE( Object param, Format ignore ) {
        this.ctrlDENOMINAZIONE_BREVE.setValue( param );
    }

    public void  setCtrlCAPOLUOGO_PROVINCIA( String param ) {
        this.ctrlCAPOLUOGO_PROVINCIA.setValue( param );
    }

    public void  setCtrlCAPOLUOGO_PROVINCIA( Object param ) {
        this.ctrlCAPOLUOGO_PROVINCIA.setValue( param );
    }

    public void  setCtrlCAPOLUOGO_PROVINCIA( Object param, Format ignore ) {
        this.ctrlCAPOLUOGO_PROVINCIA.setValue( param );
    }

    public void  setCtrlCAP( long param ) {
        this.ctrlCAP.setValue( param );
    }

    public void  setCtrlCAP( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCAP.setValue( param );
    }

    public void  setCtrlCAP( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCAP.setValue( param, format );
    }

    public void  setCtrlCAP( Long param ) {
        this.ctrlCAP.setValue( param );
    }

    public void  setCtrlPROVINCIA_TRIBUNALE( long param ) {
        this.ctrlPROVINCIA_TRIBUNALE.setValue( param );
    }

    public void  setCtrlPROVINCIA_TRIBUNALE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlPROVINCIA_TRIBUNALE.setValue( param );
    }

    public void  setCtrlPROVINCIA_TRIBUNALE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlPROVINCIA_TRIBUNALE.setValue( param, format );
    }

    public void  setCtrlPROVINCIA_TRIBUNALE( Long param ) {
        this.ctrlPROVINCIA_TRIBUNALE.setValue( param );
    }

    public void  setCtrlCOMUNE_TRIBUNALE( long param ) {
        this.ctrlCOMUNE_TRIBUNALE.setValue( param );
    }

    public void  setCtrlCOMUNE_TRIBUNALE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCOMUNE_TRIBUNALE.setValue( param );
    }

    public void  setCtrlCOMUNE_TRIBUNALE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCOMUNE_TRIBUNALE.setValue( param, format );
    }

    public void  setCtrlCOMUNE_TRIBUNALE( Long param ) {
        this.ctrlCOMUNE_TRIBUNALE.setValue( param );
    }

    public void  setCtrlDATA_SOPPRESSIONE( String param ) {
        this.ctrlDATA_SOPPRESSIONE.setValue( param );
    }

    public void  setCtrlDATA_SOPPRESSIONE( Object param ) {
        this.ctrlDATA_SOPPRESSIONE.setValue( param );
    }

    public void  setCtrlDATA_SOPPRESSIONE( Object param, Format ignore ) {
        this.ctrlDATA_SOPPRESSIONE.setValue( param );
    }

    public void  setCtrlPROVINCIA_FUSIONE( long param ) {
        this.ctrlPROVINCIA_FUSIONE.setValue( param );
    }

    public void  setCtrlPROVINCIA_FUSIONE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlPROVINCIA_FUSIONE.setValue( param );
    }

    public void  setCtrlPROVINCIA_FUSIONE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlPROVINCIA_FUSIONE.setValue( param, format );
    }

    public void  setCtrlPROVINCIA_FUSIONE( Long param ) {
        this.ctrlPROVINCIA_FUSIONE.setValue( param );
    }

    public void  setCtrlCOMUNE_FUSIONE( long param ) {
        this.ctrlCOMUNE_FUSIONE.setValue( param );
    }

    public void  setCtrlCOMUNE_FUSIONE( long param, Format ignore ) throws java.text.ParseException {
        this.ctrlCOMUNE_FUSIONE.setValue( param );
    }

    public void  setCtrlCOMUNE_FUSIONE( Object param, Format format ) throws java.text.ParseException {
        this.ctrlCOMUNE_FUSIONE.setValue( param, format );
    }

    public void  setCtrlCOMUNE_FUSIONE( Long param ) {
        this.ctrlCOMUNE_FUSIONE.setValue( param );
    }

    public void  setCtrlSIGLA_CFIS( String param ) {
        this.ctrlSIGLA_CFIS.setValue( param );
    }

    public void  setCtrlSIGLA_CFIS( Object param ) {
        this.ctrlSIGLA_CFIS.setValue( param );
    }

    public void  setCtrlSIGLA_CFIS( Object param, Format ignore ) {
        this.ctrlSIGLA_CFIS.setValue( param );
    }

    public void  setSesUtente( String param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param, Format ignore ) {
        this.sesUtente.setValue( param );
    }

    public void  setUrlPROVINCIA_STATO( long param ) {
        this.urlPROVINCIA_STATO.setValue( param );
    }

    public void  setUrlPROVINCIA_STATO( long param, Format ignore ) throws java.text.ParseException {
        this.urlPROVINCIA_STATO.setValue( param );
    }

    public void  setUrlPROVINCIA_STATO( Object param, Format format ) throws java.text.ParseException {
        this.urlPROVINCIA_STATO.setValue( param, format );
    }

    public void  setUrlPROVINCIA_STATO( Long param ) {
        this.urlPROVINCIA_STATO.setValue( param );
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

    public ComuneImpostaRow getRow() {
        return row;
    }

    public void setRow( ComuneImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-AEA8D68A
    public ComuneImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-57549DDD
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select provincia_stato "
                    + "      ,comune "
                    + "      ,denominazione "
                    + "      ,denominazione_breve "
                    + "      ,capoluogo_provincia "
                    + "      ,cap "
                    + "      ,provincia_tribunale "
                    + "      ,comune_tribunale "
                    + "      ,ad4_ccs.format_data_trunc(data_soppressione) data_soppressione "
                    + "      ,provincia_fusione "
                    + "      ,comune_fusione "
                    + "      ,sigla_cfis "
                    + "      ,consolato "
                    + "      ,tipo_consolato "
                    + "      ,  "
                    + " ad4_ccs.link_multi_lov('Ad4DizionariComuniLov','','Cerca Comune..','ComuneImposta','PROVINCIA_TRIBUNALE#COMUNE_TRIBUNALE#TRIBUNALE_DESC')  "
                    + "       ||'<a class=\"AFCDataLink\" href=\"#\" onclick=\"' "
                    + "       ||'self.document.forms[0].PROVINCIA_TRIBUNALE.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE_TRIBUNALE.value='''';' "
                    + "       ||'self.document.forms[0].TRIBUNALE_DESC.value='''';' "
                    + "       ||'\" title=\"Elimina Comune\">' "
                    + "       ||'<img height=\"18\" src=\"../images/clear.gif\" width=\"18\" border=\"0\">' "
                    + "       ||'</a>'                                                                  tribunale_lov "
                    + "      ,ad4_ccs.get_comune_denominazione(provincia_tribunale,comune_tribunale)    tribunale_desc "
                    + "      ,ad4_ccs.link_multi_lov('Ad4DizionariComuniLov','','Cerca Comune..','ComuneImposta','PROVINCIA_FUSIONE#COMUNE_FUSIONE#FUSIONE_DESC')  "
                    + "       ||'<a class=\"AFCDataLink\" href=\"#\" onclick=\"' "
                    + "       ||'self.document.forms[0].PROVINCIA_FUSIONE.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE_FSUIONE.value='''';' "
                    + "       ||'self.document.forms[0].FUSIONE_DESC.value='''';' "
                    + "       ||'\" title=\"Elimina Comune\">' "
                    + "       ||'<img height=\"18\" src=\"../images/clear.gif\" width=\"18\" border=\"0\">' "
                    + "       ||'</a>'                                                                  fusione_lov "
                    + "      ,ad4_ccs.get_comune_denominazione(provincia_fusione,comune_fusione)        fusione_desc "
                    + "      ,'<!--' hide_begin "
                    + "      ,'-->' hide_end "
                    + "      ,  '</td></tr><tr><td class=\"AFCDataTD\" align=\"right\" colspan=\"2\">' "
                    + "       ||utente_aggiornamento "
                    + "       ||' ' "
                    + "       ||ad4_ccs.format_data_trunc(data_aggiornamento) label_upd "
                    + "  from ad4_comuni "
                    + " where provincia_stato = {PROVINCIA_STATO} "
                    + "   and comune = {COMUNE} "
                    + "union "
                    + "select null provincia_stato "
                    + "      ,null comune "
                    + "      ,null denominazione "
                    + "      ,null denominazione_breve "
                    + "      ,null capoluogo_provincia "
                    + "      ,null cap "
                    + "      ,null provincia_tribunale "
                    + "      ,null comune_tribunale "
                    + "      ,null data_soppressione "
                    + "      ,null provincia_fusione "
                    + "      ,null comune_fusione "
                    + "      ,null sigla_cfis "
                    + "      ,null consolato "
                    + "      ,null tipo_consolato "
                    + "      ,  ad4_ccs.link_multi_lov('Ad4DizionariComuniLov','','Cerca Comune..','ComuneImposta','PROVINCIA_TRIBUNALE#COMUNE_TRIBUNALE#TRIBUNALE_DESC')  "
                    + "       ||'<a class=\"AFCDataLink\" href=\"#\" onclick=\"' "
                    + "       ||'self.document.forms[0].PROVINCIA_TRIBUNALE.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE_TRIBUNALE.value='''';' "
                    + "       ||'self.document.forms[0].TRIBUNALE_DESC.value='''';' "
                    + "       ||'\" title=\"Elimina Comune\">' "
                    + "       ||'<img height=\"18\" src=\"../images/clear.gif\" width=\"18\" border=\"0\">' "
                    + "       ||'</a>'                                                                  tribunale_lov "
                    + "      ,null                      tribunale_desc "
                    + "      ,ad4_ccs.link_multi_lov('Ad4DizionariComuniLov','','Cerca Comune..','ComuneImposta','PROVINCIA_FUSIONE#COMUNE_FUSIONE#FUSIONE_DESC')  "
                    + "       ||'<a class=\"AFCDataLink\" href=\"#\" onclick=\"' "
                    + "       ||'self.document.forms[0].PROVINCIA_FUSIONE.value='''';' "
                    + "       ||'self.document.forms[0].COMUNE_FSUIONE.value='''';' "
                    + "       ||'self.document.forms[0].FUSIONE_DESC.value='''';' "
                    + "       ||'\" title=\"Elimina Comune\">' "
                    + "       ||'<img height=\"18\" src=\"../images/clear.gif\" width=\"18\" border=\"0\">' "
                    + "       ||'</a>'                                                                  fusione_lov "
                    + "      ,null                      fusione_desc "
                    + "      ,'<!--'                    hide_begin "
                    + "      ,'-->'                     hide_end "
                    + "      ,null                      label_upd "
                    + "  from ad4_comuni "
                    + " where (   provincia_stato is null "
                    + "        or provincia_stato = -1 "
                    + "       ) "
                    + "   and (   comune is null "
                    + "        or comune = -1 "
                    + "       )" );
        if ( urlPROVINCIA_STATO.getObjectValue() == null ) urlPROVINCIA_STATO.setValue( -1 );
        command.addParameter( "PROVINCIA_STATO", urlPROVINCIA_STATO, null );
        if ( urlCOMUNE.getObjectValue() == null ) urlCOMUNE.setValue( -1 );
        command.addParameter( "COMUNE", urlCOMUNE, null );
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

//loadDataBind @5-963F4B47
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setPROVINCIA_LABEL(Utils.convertToString(ds.parse(record.get("PROVINCIA_STATO"), row.getPROVINCIA_LABELField())));
            row.setHIDE_BEGIN(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGINField())));
            row.setPROVINCIA_STATO(Utils.convertToLong(ds.parse(record.get("PROVINCIA_STATO"), row.getPROVINCIA_STATOField())));
            row.setHIDE_END(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_ENDField())));
            row.setCOMUNE_LABEL(Utils.convertToString(ds.parse(record.get("COMUNE"), row.getCOMUNE_LABELField())));
            row.setHIDE_BEGIN2(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGIN2Field())));
            row.setCOMUNE(Utils.convertToLong(ds.parse(record.get("PROVINCIA"), row.getCOMUNEField())));
            row.setHIDE_END2(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_END2Field())));
            row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
            row.setDENOMINAZIONE_BREVE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE_BREVE"), row.getDENOMINAZIONE_BREVEField())));
            row.setCAPOLUOGO_PROVINCIA(Utils.convertToString(ds.parse(record.get("CAPOLUOGO_PROVINCIA"), row.getCAPOLUOGO_PROVINCIAField())));
            row.setCAP(Utils.convertToLong(ds.parse(record.get("CAP"), row.getCAPField())));
            row.setTRIBUNALE_DESC(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getTRIBUNALE_DESCField())));
            row.setTRIBUNALE_LOV(Utils.convertToString(ds.parse(record.get("TRIBUNALE_LOV"), row.getTRIBUNALE_LOVField())));
            row.setPROVINCIA_TRIBUNALE(Utils.convertToLong(ds.parse(record.get("PROVINCIA_TRIBUNALE"), row.getPROVINCIA_TRIBUNALEField())));
            row.setCOMUNE_TRIBUNALE(Utils.convertToLong(ds.parse(record.get("COMUNE_TRIBUNALE"), row.getCOMUNE_TRIBUNALEField())));
            row.setDATA_SOPPRESSIONE(Utils.convertToString(ds.parse(record.get("DATA_SOPPRESSIONE"), row.getDATA_SOPPRESSIONEField())));
            row.setFUSIONE_DESC(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getFUSIONE_DESCField())));
            row.setFUSIONE_LOV(Utils.convertToString(ds.parse(record.get("FUSIONE_LOV"), row.getFUSIONE_LOVField())));
            row.setPROVINCIA_FUSIONE(Utils.convertToLong(ds.parse(record.get("PROVINCIA_FUSIONE"), row.getPROVINCIA_FUSIONEField())));
            row.setCOMUNE_FUSIONE(Utils.convertToLong(ds.parse(record.get("COMUNE_FUSIONE"), row.getCOMUNE_FUSIONEField())));
            row.setSIGLA_CFIS(Utils.convertToString(ds.parse(record.get("SIGLA_CFIS"), row.getSIGLA_CFISField())));
            row.setLABEL_UPD(Utils.convertToString(ds.parse(record.get("LABEL_UPD"), row.getLABEL_UPDField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-423BF37A
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.COMUNE_INS(?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            command.addParameter( "IN_PROVINCIA_STATO", row.getPROVINCIA_STATOField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", row.getCOMUNEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE_BREVE()) ) row.setDENOMINAZIONE_BREVE( "" );
            command.addParameter( "IN_DENOMINAZIONE_BREVE", row.getDENOMINAZIONE_BREVEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCAPOLUOGO_PROVINCIA()) ) row.setCAPOLUOGO_PROVINCIA( "" );
            command.addParameter( "IN_CAPOLUOGO_PROVINCIA", row.getCAPOLUOGO_PROVINCIAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CAP", row.getCAPField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_PROVINCIA_TRIBUNALE", row.getPROVINCIA_TRIBUNALEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE_TRIBUNALE", row.getCOMUNE_TRIBUNALEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDATA_SOPPRESSIONE()) ) row.setDATA_SOPPRESSIONE( "" );
            command.addParameter( "IN_DATA_SOPPRESSIONE", row.getDATA_SOPPRESSIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_PROVINCIA_FUSIONE", row.getPROVINCIA_FUSIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE_FUSIONE", row.getCOMUNE_FUSIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSIGLA_CFIS()) ) row.setSIGLA_CFIS( "" );
            command.addParameter( "IN_SIGLA_CFIS", row.getSIGLA_CFISField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//update @5-C3B3EDDF
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.COMUNE_UPD(?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            command.addParameter( "IN_PROVINCIA_STATO", urlPROVINCIA_STATO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", urlCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE_BREVE()) ) row.setDENOMINAZIONE_BREVE( "" );
            command.addParameter( "IN_DENOMINAZIONE_BREVE", row.getDENOMINAZIONE_BREVEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCAPOLUOGO_PROVINCIA()) ) row.setCAPOLUOGO_PROVINCIA( "" );
            command.addParameter( "IN_CAPOLUOGO_PROVINCIA", row.getCAPOLUOGO_PROVINCIAField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_CAP", row.getCAPField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_PROVINCIA_TRIBUNALE", row.getPROVINCIA_TRIBUNALEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE_TRIBUNALE", row.getCOMUNE_TRIBUNALEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDATA_SOPPRESSIONE()) ) row.setDATA_SOPPRESSIONE( "" );
            command.addParameter( "IN_DATA_SOPPRESSIONE", row.getDATA_SOPPRESSIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_PROVINCIA_FUSIONE", row.getPROVINCIA_FUSIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE_FUSIONE", row.getCOMUNE_FUSIONEField(), java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSIGLA_CFIS()) ) row.setSIGLA_CFIS( "" );
            command.addParameter( "IN_SIGLA_CFIS", row.getSIGLA_CFISField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
            command.addParameter( "IN_UTENTE", sesUtente, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @5-E92904AF
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.COMUNE_DEL(?,?)}" );
            command.addParameter( "IN_PROVINCIA_STATO", urlPROVINCIA_STATO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
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

//getParameterByName @5-5941825C
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "PROVINCIA_STATO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPROVINCIA_STATO;
            } else if ( "PROVINCIA_STATO".equals(name) && prefix == null ) {
                param = ctrlPROVINCIA_STATO;
            }
            if ( "COMUNE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = ctrlCOMUNE;
            }
            if ( "DENOMINAZIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDENOMINAZIONE;
            } else if ( "DENOMINAZIONE".equals(name) && prefix == null ) {
                param = ctrlDENOMINAZIONE;
            }
            if ( "DENOMINAZIONE_BREVE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDENOMINAZIONE_BREVE;
            } else if ( "DENOMINAZIONE_BREVE".equals(name) && prefix == null ) {
                param = ctrlDENOMINAZIONE_BREVE;
            }
            if ( "CAPOLUOGO_PROVINCIA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCAPOLUOGO_PROVINCIA;
            } else if ( "CAPOLUOGO_PROVINCIA".equals(name) && prefix == null ) {
                param = ctrlCAPOLUOGO_PROVINCIA;
            }
            if ( "CAP".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCAP;
            } else if ( "CAP".equals(name) && prefix == null ) {
                param = ctrlCAP;
            }
            if ( "PROVINCIA_TRIBUNALE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPROVINCIA_TRIBUNALE;
            } else if ( "PROVINCIA_TRIBUNALE".equals(name) && prefix == null ) {
                param = ctrlPROVINCIA_TRIBUNALE;
            }
            if ( "COMUNE_TRIBUNALE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOMUNE_TRIBUNALE;
            } else if ( "COMUNE_TRIBUNALE".equals(name) && prefix == null ) {
                param = ctrlCOMUNE_TRIBUNALE;
            }
            if ( "DATA_SOPPRESSIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDATA_SOPPRESSIONE;
            } else if ( "DATA_SOPPRESSIONE".equals(name) && prefix == null ) {
                param = ctrlDATA_SOPPRESSIONE;
            }
            if ( "PROVINCIA_FUSIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPROVINCIA_FUSIONE;
            } else if ( "PROVINCIA_FUSIONE".equals(name) && prefix == null ) {
                param = ctrlPROVINCIA_FUSIONE;
            }
            if ( "COMUNE_FUSIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCOMUNE_FUSIONE;
            } else if ( "COMUNE_FUSIONE".equals(name) && prefix == null ) {
                param = ctrlCOMUNE_FUSIONE;
            }
            if ( "SIGLA_CFIS".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSIGLA_CFIS;
            } else if ( "SIGLA_CFIS".equals(name) && prefix == null ) {
                param = ctrlSIGLA_CFIS;
            }
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "PROVINCIA_STATO".equals(name) && "url".equals(prefix) ) {
                param = urlPROVINCIA_STATO;
            } else if ( "PROVINCIA_STATO".equals(name) && prefix == null ) {
                param = urlPROVINCIA_STATO;
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

