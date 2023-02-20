//AD4_ISTANZE DataSource @56-9D01D9AB
package ad4web.AD4Istanza;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_ISTANZE DataSource

//class DataObject Header @56-D9888AE3
public class AD4_ISTANZEDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @56-840EC859
    

    TextField postISTANZA = new TextField(null, null);
    
    TextField postISTANZA_ORIG = new TextField(null, null);
    
    TextField postPROGETTO = new TextField(null, null);
    
    TextField postDESCRIZIONE = new TextField(null, null);
    
    TextField postENTE = new TextField(null, null);
    
    TextField postUSER_ORACLE = new TextField(null, null);
    
    TextField postPASSWORD_ORACLE = new TextField(null, null);
    
    TextField ctrlPWD_MODIFIED = new TextField(null, null);
    
    TextField postDISLOCAZIONE = new TextField(null, null);
    
    TextField postDISLOCAZIONE_TEMPORANEA = new TextField(null, null);
    
    TextField postLINGUA = new TextField(null, null);
    
    TextField postLINK_ORACLE = new TextField(null, null);
    
    TextField postDATABASE_LINK = new TextField(null, null);
    
    TextField postSERVIZIO = new TextField(null, null);
    
    TextField postNOTE = new TextField(null, null);
    
    TextField postDATABASE_DRIVER = new TextField(null, null);
    
    TextField postISTANZA_AMMINISTRATORE = new TextField(null, null);
    
    TextField ctrlISTANZA_AMMINISTRATORE_ORIG = new TextField(null, null);
    
    TextField ctrlISTANZA_ORIG = new TextField(null, null);
    
    TextField urlISTANZA = new TextField(null, null);
    
    TextField sesAD4PROGETTO = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    

    private AD4_ISTANZERow row = new AD4_ISTANZERow();

//End attributes of DataObject

//properties of DataObject @56-671CFDA5

    public void  setPostISTANZA( String param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param, Format ignore ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA_ORIG( String param ) {
        this.postISTANZA_ORIG.setValue( param );
    }

    public void  setPostISTANZA_ORIG( Object param ) {
        this.postISTANZA_ORIG.setValue( param );
    }

    public void  setPostISTANZA_ORIG( Object param, Format ignore ) {
        this.postISTANZA_ORIG.setValue( param );
    }

    public void  setPostPROGETTO( String param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param, Format ignore ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostDESCRIZIONE( String param ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostDESCRIZIONE( Object param ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostDESCRIZIONE( Object param, Format ignore ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostENTE( String param ) {
        this.postENTE.setValue( param );
    }

    public void  setPostENTE( Object param ) {
        this.postENTE.setValue( param );
    }

    public void  setPostENTE( Object param, Format ignore ) {
        this.postENTE.setValue( param );
    }

    public void  setPostUSER_ORACLE( String param ) {
        this.postUSER_ORACLE.setValue( param );
    }

    public void  setPostUSER_ORACLE( Object param ) {
        this.postUSER_ORACLE.setValue( param );
    }

    public void  setPostUSER_ORACLE( Object param, Format ignore ) {
        this.postUSER_ORACLE.setValue( param );
    }

    public void  setPostPASSWORD_ORACLE( String param ) {
        this.postPASSWORD_ORACLE.setValue( param );
    }

    public void  setPostPASSWORD_ORACLE( Object param ) {
        this.postPASSWORD_ORACLE.setValue( param );
    }

    public void  setPostPASSWORD_ORACLE( Object param, Format ignore ) {
        this.postPASSWORD_ORACLE.setValue( param );
    }

    public void  setCtrlPWD_MODIFIED( String param ) {
        this.ctrlPWD_MODIFIED.setValue( param );
    }

    public void  setCtrlPWD_MODIFIED( Object param ) {
        this.ctrlPWD_MODIFIED.setValue( param );
    }

    public void  setCtrlPWD_MODIFIED( Object param, Format ignore ) {
        this.ctrlPWD_MODIFIED.setValue( param );
    }

    public void  setPostDISLOCAZIONE( String param ) {
        this.postDISLOCAZIONE.setValue( param );
    }

    public void  setPostDISLOCAZIONE( Object param ) {
        this.postDISLOCAZIONE.setValue( param );
    }

    public void  setPostDISLOCAZIONE( Object param, Format ignore ) {
        this.postDISLOCAZIONE.setValue( param );
    }

    public void  setPostDISLOCAZIONE_TEMPORANEA( String param ) {
        this.postDISLOCAZIONE_TEMPORANEA.setValue( param );
    }

    public void  setPostDISLOCAZIONE_TEMPORANEA( Object param ) {
        this.postDISLOCAZIONE_TEMPORANEA.setValue( param );
    }

    public void  setPostDISLOCAZIONE_TEMPORANEA( Object param, Format ignore ) {
        this.postDISLOCAZIONE_TEMPORANEA.setValue( param );
    }

    public void  setPostLINGUA( String param ) {
        this.postLINGUA.setValue( param );
    }

    public void  setPostLINGUA( Object param ) {
        this.postLINGUA.setValue( param );
    }

    public void  setPostLINGUA( Object param, Format ignore ) {
        this.postLINGUA.setValue( param );
    }

    public void  setPostLINK_ORACLE( String param ) {
        this.postLINK_ORACLE.setValue( param );
    }

    public void  setPostLINK_ORACLE( Object param ) {
        this.postLINK_ORACLE.setValue( param );
    }

    public void  setPostLINK_ORACLE( Object param, Format ignore ) {
        this.postLINK_ORACLE.setValue( param );
    }

    public void  setPostDATABASE_LINK( String param ) {
        this.postDATABASE_LINK.setValue( param );
    }

    public void  setPostDATABASE_LINK( Object param ) {
        this.postDATABASE_LINK.setValue( param );
    }

    public void  setPostDATABASE_LINK( Object param, Format ignore ) {
        this.postDATABASE_LINK.setValue( param );
    }

    public void  setPostSERVIZIO( String param ) {
        this.postSERVIZIO.setValue( param );
    }

    public void  setPostSERVIZIO( Object param ) {
        this.postSERVIZIO.setValue( param );
    }

    public void  setPostSERVIZIO( Object param, Format ignore ) {
        this.postSERVIZIO.setValue( param );
    }

    public void  setPostNOTE( String param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param, Format ignore ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostDATABASE_DRIVER( String param ) {
        this.postDATABASE_DRIVER.setValue( param );
    }

    public void  setPostDATABASE_DRIVER( Object param ) {
        this.postDATABASE_DRIVER.setValue( param );
    }

    public void  setPostDATABASE_DRIVER( Object param, Format ignore ) {
        this.postDATABASE_DRIVER.setValue( param );
    }

    public void  setPostISTANZA_AMMINISTRATORE( String param ) {
        this.postISTANZA_AMMINISTRATORE.setValue( param );
    }

    public void  setPostISTANZA_AMMINISTRATORE( Object param ) {
        this.postISTANZA_AMMINISTRATORE.setValue( param );
    }

    public void  setPostISTANZA_AMMINISTRATORE( Object param, Format ignore ) {
        this.postISTANZA_AMMINISTRATORE.setValue( param );
    }

    public void  setCtrlISTANZA_AMMINISTRATORE_ORIG( String param ) {
        this.ctrlISTANZA_AMMINISTRATORE_ORIG.setValue( param );
    }

    public void  setCtrlISTANZA_AMMINISTRATORE_ORIG( Object param ) {
        this.ctrlISTANZA_AMMINISTRATORE_ORIG.setValue( param );
    }

    public void  setCtrlISTANZA_AMMINISTRATORE_ORIG( Object param, Format ignore ) {
        this.ctrlISTANZA_AMMINISTRATORE_ORIG.setValue( param );
    }

    public void  setCtrlISTANZA_ORIG( String param ) {
        this.ctrlISTANZA_ORIG.setValue( param );
    }

    public void  setCtrlISTANZA_ORIG( Object param ) {
        this.ctrlISTANZA_ORIG.setValue( param );
    }

    public void  setCtrlISTANZA_ORIG( Object param, Format ignore ) {
        this.ctrlISTANZA_ORIG.setValue( param );
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

    public void  setSesAD4PROGETTO( String param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param ) {
        this.sesAD4PROGETTO.setValue( param );
    }

    public void  setSesAD4PROGETTO( Object param, Format ignore ) {
        this.sesAD4PROGETTO.setValue( param );
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

    public AD4_ISTANZERow getRow() {
        return row;
    }

    public void setRow( AD4_ISTANZERow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @56-E1ECFEE1
    public AD4_ISTANZEDataObject(Page page) {
        super(page);
    }
//End constructor

//load @56-AD192C3C
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '<input class=\"AFCInputDisplay\" maxlength=\"10\" size=\"10\" value=\"'||ISTA.ISTANZA||'\" name=\"ISTANZA\" readonly ></input>' ISTANZA, "
                    + "       ISTA.ISTANZA ISTANZA_ORIG,  "
                    + " "
                    + "       PROG.PROGETTO PROGETTO, "
                    + "       PROG.DESCRIZIONE DESC_PROGETTO,  "
                    + "       ISTA.ENTE,  "
                    + "       ISTA.DESCRIZIONE,  "
                    + "       ISTA.USER_ORACLE,  "
                    + " "
                    + "       ISTA.PASSWORD_ORACLE,  "
                    + "       ISTA.DISLOCAZIONE,  "
                    + " "
                    + "       ISTA.DISLOCAZIONE_TEMPORANEA,  "
                    + "       ISTA.INSTALLAZIONE,  "
                    + "       ISTA.VERSIONE,  "
                    + " "
                    + "       ISTA.NOTE,  "
                    + "       ISTA.LINGUA,  "
                    + "       ISTA.LINK_ORACLE,  "
                    + "       ISTA.SERVIZIO,  "
                    + " "
                    + "       ISTA.DATABASE_LINK, "
                    + "       ISTA.DATABASE_DRIVER, "
                    + "       ISTA.ISTANZA_AMMINISTRATORE, "
                    + "       ISTA.ISTANZA_AMMINISTRATORE ISTANZA_AMMINISTRATORE_ORIG, "
                    + "       '<img title=\"Caratteristiche\" height=\"18\" src=\"../common/images/AD4/caac.GIF\" width=\"18\" border=\"0\">'||'Caratteristiche' Caratteristiche, "
                    + "       '<img title=\"Abilitazioni\" height=\"18\" src=\"../common/images/AD4/UtenDiac.gif\" width=\"18\" border=\"0\">'||'Abilitazioni' Abilitazioni, "
                    + "       '<img title=\"Registro\" height=\"18\" src=\"../common/images/AD4/registro.gif\" width=\"18\" border=\"0\">'||'Registro' registro "
                    + "  FROM ISTANZE ISTA, PROGETTI PROG "
                    + " WHERE ISTA.PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   AND ISTA.PROGETTO = PROG.PROGETTO "
                    + "   AND ISTANZA = '{ISTANZA}' "
                    + "UNION "
                    + "SELECT '<input class=\"AFCInput\" style=\"TEXT-TRANSFORM: uppercase\" maxlength=\"10\" size=\"10\" value=\"'||'{ISTA_FORM}'||'\" name=\"ISTANZA\"></input>' ISTANZA,  "
                    + "       '' ISTANZA_ORIG,  "
                    + "       PROGETTO PROGETTO, "
                    + "       DESCRIZIONE DESC_PROGETTO,  "
                    + "       '{ENTE_FORM}' ENTE,  "
                    + "       '{DESC_FORM}' DESCRIZIONE,  "
                    + "       '{USRO_FORM}' USER_ORACLE,  "
                    + "       '{PSWO_FORM}' PASSWORD_ORACLE,  "
                    + "       '{DISL_FORM}' DISLOCAZIONE,  "
                    + "       '{DITE_FORM}' DISLOCAZIONE_TEMPORANEA,  "
                    + "       '' INSTALLAZIONE,  "
                    + "       '' VERSIONE,  "
                    + "       '{NOTE_FORM}' NOTE,  "
                    + "       '{LING_FORM}' LINGUA,  "
                    + "       '{LINK_FORM}' LINK_ORACLE,  "
                    + "       '{SERV_FORM}' SERVIZIO,  "
                    + "       '{DBLI_FORM}' DATABASE_LINK, "
                    + "       '{DBDR_FORM}' DATABASE_DRIVER, "
                    + "       '' ISTANZA_AMMINISTRATORE, "
                    + "       '' ISTANZA_AMMINISTRATORE_ORIG, "
                    + "       '' Caratteristiche, "
                    + "       '' ABILITAZIONI , "
                    + "       '' registro "
                    + "  FROM PROGETTI "
                    + " WHERE PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   AND '{ISTANZA}' IS NULL" );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZA", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) postISTANZA.getObjectValue() ) ) postISTANZA.setValue( "" );
        command.addParameter( "ISTA_FORM", postISTANZA, null );
        if ( StringUtils.isEmpty( (String) postENTE.getObjectValue() ) ) postENTE.setValue( "" );
        command.addParameter( "ENTE_FORM", postENTE, null );
        if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
        command.addParameter( "DESC_FORM", postDESCRIZIONE, null );
        if ( StringUtils.isEmpty( (String) postUSER_ORACLE.getObjectValue() ) ) postUSER_ORACLE.setValue( "" );
        command.addParameter( "USRO_FORM", postUSER_ORACLE, null );
        if ( StringUtils.isEmpty( (String) postPASSWORD_ORACLE.getObjectValue() ) ) postPASSWORD_ORACLE.setValue( "" );
        command.addParameter( "PSWO_FORM", postPASSWORD_ORACLE, null );
        if ( StringUtils.isEmpty( (String) postDISLOCAZIONE.getObjectValue() ) ) postDISLOCAZIONE.setValue( "" );
        command.addParameter( "DISL_FORM", postDISLOCAZIONE, null );
        if ( StringUtils.isEmpty( (String) postDISLOCAZIONE_TEMPORANEA.getObjectValue() ) ) postDISLOCAZIONE_TEMPORANEA.setValue( "" );
        command.addParameter( "DITE_FORM", postDISLOCAZIONE_TEMPORANEA, null );
        if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
        command.addParameter( "NOTE_FORM", postNOTE, null );
        if ( StringUtils.isEmpty( (String) postLINGUA.getObjectValue() ) ) postLINGUA.setValue( "" );
        command.addParameter( "LING_FORM", postLINGUA, null );
        if ( StringUtils.isEmpty( (String) postLINK_ORACLE.getObjectValue() ) ) postLINK_ORACLE.setValue( "" );
        command.addParameter( "LINK_FORM", postLINK_ORACLE, null );
        if ( StringUtils.isEmpty( (String) postSERVIZIO.getObjectValue() ) ) postSERVIZIO.setValue( "" );
        command.addParameter( "SERV_FORM", postSERVIZIO, null );
        if ( StringUtils.isEmpty( (String) postDATABASE_LINK.getObjectValue() ) ) postDATABASE_LINK.setValue( "" );
        command.addParameter( "DBLI_FORM", postDATABASE_LINK, null );
        if ( StringUtils.isEmpty( (String) postDATABASE_DRIVER.getObjectValue() ) ) postDATABASE_DRIVER.setValue( "" );
        command.addParameter( "DBDR_FORM", postDATABASE_DRIVER, null );
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

//loadDataBind @56-83C69025
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setDESC_PROGETTO(Utils.convertToString(ds.parse(record.get("DESC_PROGETTO"), row.getDESC_PROGETTOField())));
            row.setCARATTERISTICHE(Utils.convertToString(ds.parse(record.get("CARATTERISTICHE"), row.getCARATTERISTICHEField())));
            row.setABILITAZIONI(Utils.convertToString(ds.parse(record.get("ABILITAZIONI"), row.getABILITAZIONIField())));
            row.setREGISTRO(Utils.convertToString(ds.parse(record.get("REGISTRO"), row.getREGISTROField())));
            row.setISTANZA(Utils.convertToString(ds.parse(record.get("ISTANZA"), row.getISTANZAField())));
            row.setISTANZA_ORIG(Utils.convertToString(ds.parse(record.get("ISTANZA_ORIG"), row.getISTANZA_ORIGField())));
            row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
            row.setENTE(Utils.convertToString(ds.parse(record.get("ENTE"), row.getENTEField())));
            row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
            row.setLINGUA(Utils.convertToString(ds.parse(record.get("LINGUA"), row.getLINGUAField())));
            row.setDISLOCAZIONE(Utils.convertToString(ds.parse(record.get("DISLOCAZIONE"), row.getDISLOCAZIONEField())));
            row.setDISLOCAZIONE_TEMPORANEA(Utils.convertToString(ds.parse(record.get("DISLOCAZIONE_TEMPORANEA"), row.getDISLOCAZIONE_TEMPORANEAField())));
            row.setUSER_ORACLE(Utils.convertToString(ds.parse(record.get("USER_ORACLE"), row.getUSER_ORACLEField())));
            row.setPASSWORD_ORACLE(Utils.convertToString(ds.parse(record.get("PASSWORD_ORACLE"), row.getPASSWORD_ORACLEField())));
            row.setISTANZA_AMMINISTRATORE(Utils.convertToString(ds.parse(record.get("ISTANZA_AMMINISTRATORE"), row.getISTANZA_AMMINISTRATOREField())));
            row.setISTANZA_AMMINISTRATORE_ORIG(Utils.convertToString(ds.parse(record.get("ISTANZA_AMMINISTRATORE_ORIG"), row.getISTANZA_AMMINISTRATORE_ORIGField())));
            row.setLINK_ORACLE(Utils.convertToString(ds.parse(record.get("LINK_ORACLE"), row.getLINK_ORACLEField())));
            row.setDATABASE_LINK(Utils.convertToString(ds.parse(record.get("DATABASE_LINK"), row.getDATABASE_LINKField())));
            row.setDATABASE_DRIVER(Utils.convertToString(ds.parse(record.get("DATABASE_DRIVER"), row.getDATABASE_DRIVERField())));
            row.setSERVIZIO(Utils.convertToString(ds.parse(record.get("SERVIZIO"), row.getSERVIZIOField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
            row.setINSTALLAZIONE(Utils.convertToString(ds.parse(record.get("INSTALLAZIONE"), row.getINSTALLAZIONEField())));
            row.setVERSIONE(Utils.convertToString(ds.parse(record.get("VERSIONE"), row.getVERSIONEField())));
            row.setUSRORCL(Utils.convertToString(ds.parse(record.get("USER_ORACLE"), row.getUSRORCLField())));
        }
//End loadDataBind

//End of load @56-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @56-85A46D86
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{? = call AD4WEB.ISTANZE_PM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postISTANZA.getObjectValue() ) ) postISTANZA.setValue( "" );
            command.addParameter( "P_ISTANZA", postISTANZA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postISTANZA_ORIG.getObjectValue() ) ) postISTANZA_ORIG.setValue( "" );
            command.addParameter( "P_ISTANZA_OLD", postISTANZA_ORIG, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPROGETTO.getObjectValue() ) ) postPROGETTO.setValue( "" );
            command.addParameter( "P_PROGETTO", postPROGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
            command.addParameter( "P_DESCRIZIONE", postDESCRIZIONE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postENTE.getObjectValue() ) ) postENTE.setValue( "" );
            command.addParameter( "P_ENTE", postENTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postUSER_ORACLE.getObjectValue() ) ) postUSER_ORACLE.setValue( "" );
            command.addParameter( "P_USER_ORACLE", postUSER_ORACLE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPASSWORD_ORACLE.getObjectValue() ) ) postPASSWORD_ORACLE.setValue( "" );
            command.addParameter( "P_PASSWORD_ORACLE", postPASSWORD_ORACLE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getPWD_MODIFIED()) ) row.setPWD_MODIFIED( "N" );
            command.addParameter( "P_PWD_MODIFIED", row.getPWD_MODIFIEDField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDISLOCAZIONE.getObjectValue() ) ) postDISLOCAZIONE.setValue( "" );
            command.addParameter( "P_DISLOCAZIONE", postDISLOCAZIONE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDISLOCAZIONE_TEMPORANEA.getObjectValue() ) ) postDISLOCAZIONE_TEMPORANEA.setValue( "" );
            command.addParameter( "P_DISLOCAZIONE_TEMPORANEA", postDISLOCAZIONE_TEMPORANEA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postLINGUA.getObjectValue() ) ) postLINGUA.setValue( "" );
            command.addParameter( "P_LINGUA", postLINGUA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postLINK_ORACLE.getObjectValue() ) ) postLINK_ORACLE.setValue( "" );
            command.addParameter( "P_LINK_ORACLE", postLINK_ORACLE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDATABASE_LINK.getObjectValue() ) ) postDATABASE_LINK.setValue( "" );
            command.addParameter( "P_DATABASE_LINK", postDATABASE_LINK, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postSERVIZIO.getObjectValue() ) ) postSERVIZIO.setValue( "" );
            command.addParameter( "P_SERVIZIO", postSERVIZIO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDATABASE_DRIVER.getObjectValue() ) ) postDATABASE_DRIVER.setValue( "" );
            command.addParameter( "p_DATABASE_DRIVER", postDATABASE_DRIVER, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postISTANZA_AMMINISTRATORE.getObjectValue() ) ) postISTANZA_AMMINISTRATORE.setValue( "" );
            command.addParameter( "p_ISTANZA_AMMINISTRATORE", postISTANZA_AMMINISTRATORE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getISTANZA_AMMINISTRATORE_ORIG()) ) row.setISTANZA_AMMINISTRATORE_ORIG( "" );
            command.addParameter( "p_ISTANZA_AMMINISTRATORE_OLD", row.getISTANZA_AMMINISTRATORE_ORIGField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @56-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @56-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @56-74ECCF8F
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call ISTANZA_AMM_PKG.del_commit(?,?,?)}" );
            if ( StringUtils.isEmpty( (String) row.getISTANZA_ORIG()) ) row.setISTANZA_ORIG( "" );
            command.addParameter( "P_ISTANZA", row.getISTANZA_ORIGField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postUSER_ORACLE.getObjectValue() ) ) postUSER_ORACLE.setValue( "" );
            command.addParameter( "P_DATABASE_USER", postUSER_ORACLE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getISTANZA_AMMINISTRATORE_ORIG()) ) row.setISTANZA_AMMINISTRATORE_ORIG( "" );
            command.addParameter( "P_ISTANZA_AMMINISTRATORE", row.getISTANZA_AMMINISTRATORE_ORIGField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @56-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @56-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @56-3E4B0EBB
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ISTANZA".equals(name) && "post".equals(prefix) ) {
                param = postISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = postISTANZA;
            }
            if ( "ISTANZA_ORIG".equals(name) && "post".equals(prefix) ) {
                param = postISTANZA_ORIG;
            } else if ( "ISTANZA_ORIG".equals(name) && prefix == null ) {
                param = postISTANZA_ORIG;
            }
            if ( "PROGETTO".equals(name) && "post".equals(prefix) ) {
                param = postPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = postPROGETTO;
            }
            if ( "DESCRIZIONE".equals(name) && "post".equals(prefix) ) {
                param = postDESCRIZIONE;
            } else if ( "DESCRIZIONE".equals(name) && prefix == null ) {
                param = postDESCRIZIONE;
            }
            if ( "ENTE".equals(name) && "post".equals(prefix) ) {
                param = postENTE;
            } else if ( "ENTE".equals(name) && prefix == null ) {
                param = postENTE;
            }
            if ( "USER_ORACLE".equals(name) && "post".equals(prefix) ) {
                param = postUSER_ORACLE;
            } else if ( "USER_ORACLE".equals(name) && prefix == null ) {
                param = postUSER_ORACLE;
            }
            if ( "PASSWORD_ORACLE".equals(name) && "post".equals(prefix) ) {
                param = postPASSWORD_ORACLE;
            } else if ( "PASSWORD_ORACLE".equals(name) && prefix == null ) {
                param = postPASSWORD_ORACLE;
            }
            if ( "PWD_MODIFIED".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlPWD_MODIFIED;
            } else if ( "PWD_MODIFIED".equals(name) && prefix == null ) {
                param = ctrlPWD_MODIFIED;
            }
            if ( "DISLOCAZIONE".equals(name) && "post".equals(prefix) ) {
                param = postDISLOCAZIONE;
            } else if ( "DISLOCAZIONE".equals(name) && prefix == null ) {
                param = postDISLOCAZIONE;
            }
            if ( "DISLOCAZIONE_TEMPORANEA".equals(name) && "post".equals(prefix) ) {
                param = postDISLOCAZIONE_TEMPORANEA;
            } else if ( "DISLOCAZIONE_TEMPORANEA".equals(name) && prefix == null ) {
                param = postDISLOCAZIONE_TEMPORANEA;
            }
            if ( "LINGUA".equals(name) && "post".equals(prefix) ) {
                param = postLINGUA;
            } else if ( "LINGUA".equals(name) && prefix == null ) {
                param = postLINGUA;
            }
            if ( "LINK_ORACLE".equals(name) && "post".equals(prefix) ) {
                param = postLINK_ORACLE;
            } else if ( "LINK_ORACLE".equals(name) && prefix == null ) {
                param = postLINK_ORACLE;
            }
            if ( "DATABASE_LINK".equals(name) && "post".equals(prefix) ) {
                param = postDATABASE_LINK;
            } else if ( "DATABASE_LINK".equals(name) && prefix == null ) {
                param = postDATABASE_LINK;
            }
            if ( "SERVIZIO".equals(name) && "post".equals(prefix) ) {
                param = postSERVIZIO;
            } else if ( "SERVIZIO".equals(name) && prefix == null ) {
                param = postSERVIZIO;
            }
            if ( "NOTE".equals(name) && "post".equals(prefix) ) {
                param = postNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = postNOTE;
            }
            if ( "DATABASE_DRIVER".equals(name) && "post".equals(prefix) ) {
                param = postDATABASE_DRIVER;
            } else if ( "DATABASE_DRIVER".equals(name) && prefix == null ) {
                param = postDATABASE_DRIVER;
            }
            if ( "ISTANZA_AMMINISTRATORE".equals(name) && "post".equals(prefix) ) {
                param = postISTANZA_AMMINISTRATORE;
            } else if ( "ISTANZA_AMMINISTRATORE".equals(name) && prefix == null ) {
                param = postISTANZA_AMMINISTRATORE;
            }
            if ( "ISTANZA_AMMINISTRATORE_ORIG".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlISTANZA_AMMINISTRATORE_ORIG;
            } else if ( "ISTANZA_AMMINISTRATORE_ORIG".equals(name) && prefix == null ) {
                param = ctrlISTANZA_AMMINISTRATORE_ORIG;
            }
            if ( "ISTANZA_ORIG".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlISTANZA_ORIG;
            } else if ( "ISTANZA_ORIG".equals(name) && prefix == null ) {
                param = ctrlISTANZA_ORIG;
            }
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "AD4PROGETTO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PROGETTO;
            } else if ( "AD4PROGETTO".equals(name) && prefix == null ) {
                param = sesAD4PROGETTO;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @56-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @56-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @56-305A023C
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

//fireBeforeExecuteSelectEvent @56-D00ACF95
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

//fireAfterExecuteSelectEvent @56-3BAD39CE
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

//fireBeforeBuildInsertEvent @56-FBA08B71
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

//fireBeforeExecuteInsertEvent @56-47AFA6A5
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

//fireAfterExecuteInsertEvent @56-E9CE95AE
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

//fireBeforeBuildSelectEvent @56-2405BE8B
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

//fireBeforeExecuteSelectEvent @56-E9DFF86B
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

//fireAfterExecuteSelectEvent @56-580A2987
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

//fireBeforeBuildSelectEvent @56-D021D0EA
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

//fireBeforeExecuteDeleteEvent @56-DD540FBB
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

//fireAfterExecuteDeleteEvent @56-2A6E2049
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

//class DataObject Tail @56-ED3F53A4
} // End of class DS
//End class DataObject Tail

