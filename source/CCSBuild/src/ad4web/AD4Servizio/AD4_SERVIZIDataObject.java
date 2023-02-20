//AD4_SERVIZI DataSource @38-E4E0C113
package ad4web.AD4Servizio;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_SERVIZI DataSource

//class DataObject Header @38-69F0F6F8
public class AD4_SERVIZIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @38-F50149F3
    

    DoubleField postID_SERVIZIO = new DoubleField(null, null);
    
    TextField postISTANZA = new TextField(null, null);
    
    TextField postMODULO = new TextField(null, null);
    
    TextField postLIVELLO = new TextField(null, null);
    
    TextField postABILITAZIONE = new TextField(null, null);
    
    TextField postMULTIPLO = new TextField(null, null);
    
    TextField postGRUPPO_ABILITAZIONE = new TextField(null, null);
    
    TextField postTIPO_NOTIFICA = new TextField(null, null);
    
    TextField postMAIL_NOTIFICHE = new TextField(null, null);
    
    TextField postCCR_NOTIFICHE = new TextField(null, null);
    
    TextField ctrlRECUPERO_PASSWORD = new TextField(null, null);
    
    TextField postTAG_CIM = new TextField(null, null);
    
    TextField sesAD4PROGETTO = new TextField(null, null);
    
    TextField urlID_SERVIZIO = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    
    TextField postRECUPERO_PASSWORD = new TextField(null, null);
    

    private AD4_SERVIZIRow row = new AD4_SERVIZIRow();

//End attributes of DataObject

//properties of DataObject @38-D7F8BF56

    public void  setPostID_SERVIZIO( double param ) {
        this.postID_SERVIZIO.setValue( param );
    }

    public void  setPostID_SERVIZIO( double param, Format ignore ) throws java.text.ParseException {
        this.postID_SERVIZIO.setValue( param );
    }

    public void  setPostID_SERVIZIO( Object param, Format format ) throws java.text.ParseException {
        this.postID_SERVIZIO.setValue( param, format );
    }

    public void  setPostID_SERVIZIO( Double param ) {
        this.postID_SERVIZIO.setValue( param );
    }

    public void  setPostISTANZA( String param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param, Format ignore ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostMODULO( String param ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostMODULO( Object param ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostMODULO( Object param, Format ignore ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostLIVELLO( String param ) {
        this.postLIVELLO.setValue( param );
    }

    public void  setPostLIVELLO( Object param ) {
        this.postLIVELLO.setValue( param );
    }

    public void  setPostLIVELLO( Object param, Format ignore ) {
        this.postLIVELLO.setValue( param );
    }

    public void  setPostABILITAZIONE( String param ) {
        this.postABILITAZIONE.setValue( param );
    }

    public void  setPostABILITAZIONE( Object param ) {
        this.postABILITAZIONE.setValue( param );
    }

    public void  setPostABILITAZIONE( Object param, Format ignore ) {
        this.postABILITAZIONE.setValue( param );
    }

    public void  setPostMULTIPLO( String param ) {
        this.postMULTIPLO.setValue( param );
    }

    public void  setPostMULTIPLO( Object param ) {
        this.postMULTIPLO.setValue( param );
    }

    public void  setPostMULTIPLO( Object param, Format ignore ) {
        this.postMULTIPLO.setValue( param );
    }

    public void  setPostGRUPPO_ABILITAZIONE( String param ) {
        this.postGRUPPO_ABILITAZIONE.setValue( param );
    }

    public void  setPostGRUPPO_ABILITAZIONE( Object param ) {
        this.postGRUPPO_ABILITAZIONE.setValue( param );
    }

    public void  setPostGRUPPO_ABILITAZIONE( Object param, Format ignore ) {
        this.postGRUPPO_ABILITAZIONE.setValue( param );
    }

    public void  setPostTIPO_NOTIFICA( String param ) {
        this.postTIPO_NOTIFICA.setValue( param );
    }

    public void  setPostTIPO_NOTIFICA( Object param ) {
        this.postTIPO_NOTIFICA.setValue( param );
    }

    public void  setPostTIPO_NOTIFICA( Object param, Format ignore ) {
        this.postTIPO_NOTIFICA.setValue( param );
    }

    public void  setPostMAIL_NOTIFICHE( String param ) {
        this.postMAIL_NOTIFICHE.setValue( param );
    }

    public void  setPostMAIL_NOTIFICHE( Object param ) {
        this.postMAIL_NOTIFICHE.setValue( param );
    }

    public void  setPostMAIL_NOTIFICHE( Object param, Format ignore ) {
        this.postMAIL_NOTIFICHE.setValue( param );
    }

    public void  setPostCCR_NOTIFICHE( String param ) {
        this.postCCR_NOTIFICHE.setValue( param );
    }

    public void  setPostCCR_NOTIFICHE( Object param ) {
        this.postCCR_NOTIFICHE.setValue( param );
    }

    public void  setPostCCR_NOTIFICHE( Object param, Format ignore ) {
        this.postCCR_NOTIFICHE.setValue( param );
    }

    public void  setCtrlRECUPERO_PASSWORD( String param ) {
        this.ctrlRECUPERO_PASSWORD.setValue( param );
    }

    public void  setCtrlRECUPERO_PASSWORD( Object param ) {
        this.ctrlRECUPERO_PASSWORD.setValue( param );
    }

    public void  setCtrlRECUPERO_PASSWORD( Object param, Format ignore ) {
        this.ctrlRECUPERO_PASSWORD.setValue( param );
    }

    public void  setPostTAG_CIM( String param ) {
        this.postTAG_CIM.setValue( param );
    }

    public void  setPostTAG_CIM( Object param ) {
        this.postTAG_CIM.setValue( param );
    }

    public void  setPostTAG_CIM( Object param, Format ignore ) {
        this.postTAG_CIM.setValue( param );
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

    public void  setUrlID_SERVIZIO( String param ) {
        this.urlID_SERVIZIO.setValue( param );
    }

    public void  setUrlID_SERVIZIO( Object param ) {
        this.urlID_SERVIZIO.setValue( param );
    }

    public void  setUrlID_SERVIZIO( Object param, Format ignore ) {
        this.urlID_SERVIZIO.setValue( param );
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

    public void  setPostRECUPERO_PASSWORD( String param ) {
        this.postRECUPERO_PASSWORD.setValue( param );
    }

    public void  setPostRECUPERO_PASSWORD( Object param ) {
        this.postRECUPERO_PASSWORD.setValue( param );
    }

    public void  setPostRECUPERO_PASSWORD( Object param, Format ignore ) {
        this.postRECUPERO_PASSWORD.setValue( param );
    }

    public AD4_SERVIZIRow getRow() {
        return row;
    }

    public void setRow( AD4_SERVIZIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @38-95E5B596
    public AD4_SERVIZIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @38-8ACEB26F
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT SERV.ID_SERVIZIO, "
                    + "       NVL('{GRUPPO_ABILITAZIONE}',GRUPPO_ABILITAZIONE) GRUPPO_ABILITAZIONE, "
                    + "       PROG.PROGETTO PROGETTO, "
                    + "       PROG.DESCRIZIONE DESC_PROGETTO, "
                    + "       NVL('{MODULO}',SERV.MODULO) MODULO, "
                    + "       NVL('{ISTANZA}',  "
                    + "SERV.ISTANZA) ISTANZA,  "
                    + "       NVL('{LIVELLO}',LIVELLO) LIVELLO,  "
                    + " "
                    + "       NVL('{ABILITAZIONE}',ABILITAZIONE) ABILITAZIONE,  "
                    + " "
                    + "       NVL('{MAIL_NOTIFICHE}',MAIL_NOTIFICHE) MAIL_NOTIFICHE,  "
                    + " "
                    + "       NVL('{CCR_NOTIFICHE}',CCR_NOTIFICHE) CCR_NOTIFICHE,  "
                    + "       NVL('{MULTIPLO}',MULTIPLO) MULTIPLO,  "
                    + " "
                    + "       NVL('{TIPO_NOTIFICA}',TIPO_NOTIFICA) TIPO_NOTIFICA, "
                    + "       '<img title=\"Utenti Abilitati al servizio\" height=\"18\" src=\"../common/images/AD4/UtenDiac.gif\" width=\"18\" border=\"0\">'||'Abilitazioni' Abilitazioni, "
                    + "       decode(lower(SERV.TIPO_NOTIFICA), 'mail', NVL(LOWER(registro_pac.get_ad4_string('RICHIESTA_ABILITAZIONE/MAIL/CIM', 'Tag', SERV.MODULO, upper(ISTA.USER_ORACLE))),'mail'),'') tag_cim, "
                    + "       nvl(SERV.RECUPERO_PASSWORD,'N') RECUPERO_PASSWORD "
                    + "  FROM SERVIZI SERV, PROGETTI PROG, ISTANZE ISTA "
                    + " WHERE ISTA.ISTANZA = SERV.ISTANZA "
                    + "   AND SERV.ID_SERVIZIO = TO_NUMBER('{ID_SERVIZIO}') "
                    + "   AND PROG.PROGETTO    = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   AND '{ID_SERVIZIO}' IS NOT NULL "
                    + "UNION "
                    + "SELECT TO_NUMBER(NULL), "
                    + "       '{GRUPPO_ABILITAZIONE}', "
                    + "       PROG.PROGETTO PROGETTO, "
                    + "       PROG.DESCRIZIONE DESC_PROGETTO, "
                    + "       '{MODULO_LISTBOX}' MODULO, "
                    + "       '{ISTANZA_LISTBOX}' ISTANZA,  "
                    + "       '{LIVELLO}',  "
                    + "       '{ABILITAZIONE}',  "
                    + "       '{MAIL_NOTIFICHE}',  "
                    + "       '{CCR_NOTIFICHE}',  "
                    + "       '{MULTIPLO}',  "
                    + "       '{TIPO_NOTIFICA}', "
                    + "       '' ABILITAZIONI, "
                    + "       '{TAG_CIM}' TAG_CIM, "
                    + "       nvl('{RECUPERO_PASSWORD}','N') RECUPERO_PASSWORD "
                    + "  FROM PROGETTI PROG "
                    + " WHERE PROG.PROGETTO = DECODE('{PROGETTO}',NULL,'{AD4PROGETTO}','{PROGETTO}') "
                    + "   AND '{ID_SERVIZIO}' IS NULL" );
        if ( StringUtils.isEmpty( (String) sesAD4PROGETTO.getObjectValue() ) ) sesAD4PROGETTO.setValue( "" );
        command.addParameter( "AD4PROGETTO", sesAD4PROGETTO, null );
        if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
        command.addParameter( "MODULO", postMODULO, null );
        if ( StringUtils.isEmpty( (String) postISTANZA.getObjectValue() ) ) postISTANZA.setValue( "" );
        command.addParameter( "ISTANZA", postISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlID_SERVIZIO.getObjectValue() ) ) urlID_SERVIZIO.setValue( "" );
        command.addParameter( "ID_SERVIZIO", urlID_SERVIZIO, null );
        if ( StringUtils.isEmpty( (String) postLIVELLO.getObjectValue() ) ) postLIVELLO.setValue( "" );
        command.addParameter( "LIVELLO", postLIVELLO, null );
        if ( StringUtils.isEmpty( (String) postABILITAZIONE.getObjectValue() ) ) postABILITAZIONE.setValue( "C" );
        command.addParameter( "ABILITAZIONE", postABILITAZIONE, null );
        if ( StringUtils.isEmpty( (String) postGRUPPO_ABILITAZIONE.getObjectValue() ) ) postGRUPPO_ABILITAZIONE.setValue( "" );
        command.addParameter( "GRUPPO_ABILITAZIONE", postGRUPPO_ABILITAZIONE, null );
        if ( StringUtils.isEmpty( (String) postMULTIPLO.getObjectValue() ) ) postMULTIPLO.setValue( "N" );
        command.addParameter( "MULTIPLO", postMULTIPLO, null );
        if ( StringUtils.isEmpty( (String) postTIPO_NOTIFICA.getObjectValue() ) ) postTIPO_NOTIFICA.setValue( "" );
        command.addParameter( "TIPO_NOTIFICA", postTIPO_NOTIFICA, null );
        if ( StringUtils.isEmpty( (String) postMAIL_NOTIFICHE.getObjectValue() ) ) postMAIL_NOTIFICHE.setValue( "" );
        command.addParameter( "MAIL_NOTIFICHE", postMAIL_NOTIFICHE, null );
        if ( StringUtils.isEmpty( (String) postCCR_NOTIFICHE.getObjectValue() ) ) postCCR_NOTIFICHE.setValue( "" );
        command.addParameter( "CCR_NOTIFICHE", postCCR_NOTIFICHE, null );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) postTAG_CIM.getObjectValue() ) ) postTAG_CIM.setValue( "" );
        command.addParameter( "TAG_CIM", postTAG_CIM, null );
        if ( StringUtils.isEmpty( (String) postRECUPERO_PASSWORD.getObjectValue() ) ) postRECUPERO_PASSWORD.setValue( "" );
        command.addParameter( "RECUPERO_PASSWORD", postRECUPERO_PASSWORD, null );
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

//loadDataBind @38-D8B6DF92
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setDESC_PROGETTO(Utils.convertToString(ds.parse(record.get("DESC_PROGETTO"), row.getDESC_PROGETTOField())));
            row.setABILITAZIONI(Utils.convertToString(ds.parse(record.get("ABILITAZIONI"), row.getABILITAZIONIField())));
            row.setID_SERVIZIO(Utils.convertToLong(ds.parse(record.get("ID_SERVIZIO"), row.getID_SERVIZIOField())));
            row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
            row.setISTANZA(Utils.convertToString(ds.parse(record.get("ISTANZA"), row.getISTANZAField())));
            row.setLIVELLO(Utils.convertToString(ds.parse(record.get("LIVELLO"), row.getLIVELLOField())));
            row.setABILITAZIONE(Utils.convertToString(ds.parse(record.get("ABILITAZIONE"), row.getABILITAZIONEField())));
            row.setMULTIPLO(Utils.convertToString(ds.parse(record.get("MULTIPLO"), row.getMULTIPLOField())));
            row.setGRUPPO_ABILITAZIONE(Utils.convertToString(ds.parse(record.get("GRUPPO_ABILITAZIONE"), row.getGRUPPO_ABILITAZIONEField())));
            row.setTIPO_NOTIFICA(Utils.convertToString(ds.parse(record.get("TIPO_NOTIFICA"), row.getTIPO_NOTIFICAField())));
            row.setTAG_CIM(Utils.convertToString(ds.parse(record.get("TAG_CIM"), row.getTAG_CIMField())));
            row.setMAIL_NOTIFICHE(Utils.convertToString(ds.parse(record.get("MAIL_NOTIFICHE"), row.getMAIL_NOTIFICHEField())));
            row.setCCR_NOTIFICHE(Utils.convertToString(ds.parse(record.get("CCR_NOTIFICHE"), row.getCCR_NOTIFICHEField())));
            row.setRECUPERO_PASSWORD(Utils.convertToString(ds.parse(record.get("RECUPERO_PASSWORD"), row.getRECUPERO_PASSWORDField())));
        }
//End loadDataBind

//End of load @38-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @38-38CE1D5C
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ ? = call AD4WEB.SERVIZI_PM ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) }" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.DOUBLE, 0, SPParameter.OUTPUT_PARAMETER );
            command.addParameter( "P_ID_SERVIZIO", postID_SERVIZIO, java.sql.Types.NUMERIC, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postISTANZA.getObjectValue() ) ) postISTANZA.setValue( "" );
            command.addParameter( "P_ISTANZA", postISTANZA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
            command.addParameter( "P_MODULO", postMODULO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postLIVELLO.getObjectValue() ) ) postLIVELLO.setValue( "" );
            command.addParameter( "P_LIVELLO", postLIVELLO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postABILITAZIONE.getObjectValue() ) ) postABILITAZIONE.setValue( "" );
            command.addParameter( "P_ABILITAZIONE", postABILITAZIONE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMULTIPLO.getObjectValue() ) ) postMULTIPLO.setValue( "" );
            command.addParameter( "P_MULTIPLO", postMULTIPLO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postGRUPPO_ABILITAZIONE.getObjectValue() ) ) postGRUPPO_ABILITAZIONE.setValue( "" );
            command.addParameter( "P_GRUPPO_ABILITAZIONE", postGRUPPO_ABILITAZIONE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postTIPO_NOTIFICA.getObjectValue() ) ) postTIPO_NOTIFICA.setValue( "" );
            command.addParameter( "P_TIPO_NOTIFICA", postTIPO_NOTIFICA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMAIL_NOTIFICHE.getObjectValue() ) ) postMAIL_NOTIFICHE.setValue( "" );
            command.addParameter( "P_MAIL_NOTIFICHE", postMAIL_NOTIFICHE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postCCR_NOTIFICHE.getObjectValue() ) ) postCCR_NOTIFICHE.setValue( "" );
            command.addParameter( "P_CCR_NOTIFICHE", postCCR_NOTIFICHE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getRECUPERO_PASSWORD()) ) row.setRECUPERO_PASSWORD( "" );
            command.addParameter( "P_RECUPERO_PASSWORD", row.getRECUPERO_PASSWORDField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postTAG_CIM.getObjectValue() ) ) postTAG_CIM.setValue( "" );
            command.addParameter( "P_TAG_CIM", postTAG_CIM, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @38-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @38-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @38-21BC0F7F
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM AD4_SERVIZI");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1 = WhereParams.rawOperation( "ID_SERVIZIO", FieldOperation.EQUAL, postID_SERVIZIO, null, ds );
            if (StringUtils.isEmpty(where1)) command.setCmdExecution(false);
            String whereParams = where1;

            if ( where1 == null ) {
                addError(getResourceBundle().getString("CustomOperationError_MissingParameters"));
            }
            if ( ! StringUtils.isEmpty(whereParams) ) {
                if ( ! StringUtils.isEmpty(command.getWhere()) ) {
                    command.setWhere( command.getWhere() + " AND (" + whereParams + ")" );
                } else {
                    command.setWhere( whereParams );
                }
            }

//End delete

//deleteDataBound @38-5B959F17
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );
            if (!command.isCmdExecution()) {
                ds.closeConnection();
                return false;
            }

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @38-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @38-A881D589
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID_SERVIZIO".equals(name) && "post".equals(prefix) ) {
                param = postID_SERVIZIO;
            } else if ( "ID_SERVIZIO".equals(name) && prefix == null ) {
                param = postID_SERVIZIO;
            }
            if ( "ISTANZA".equals(name) && "post".equals(prefix) ) {
                param = postISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = postISTANZA;
            }
            if ( "MODULO".equals(name) && "post".equals(prefix) ) {
                param = postMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = postMODULO;
            }
            if ( "LIVELLO".equals(name) && "post".equals(prefix) ) {
                param = postLIVELLO;
            } else if ( "LIVELLO".equals(name) && prefix == null ) {
                param = postLIVELLO;
            }
            if ( "ABILITAZIONE".equals(name) && "post".equals(prefix) ) {
                param = postABILITAZIONE;
            } else if ( "ABILITAZIONE".equals(name) && prefix == null ) {
                param = postABILITAZIONE;
            }
            if ( "MULTIPLO".equals(name) && "post".equals(prefix) ) {
                param = postMULTIPLO;
            } else if ( "MULTIPLO".equals(name) && prefix == null ) {
                param = postMULTIPLO;
            }
            if ( "GRUPPO_ABILITAZIONE".equals(name) && "post".equals(prefix) ) {
                param = postGRUPPO_ABILITAZIONE;
            } else if ( "GRUPPO_ABILITAZIONE".equals(name) && prefix == null ) {
                param = postGRUPPO_ABILITAZIONE;
            }
            if ( "TIPO_NOTIFICA".equals(name) && "post".equals(prefix) ) {
                param = postTIPO_NOTIFICA;
            } else if ( "TIPO_NOTIFICA".equals(name) && prefix == null ) {
                param = postTIPO_NOTIFICA;
            }
            if ( "MAIL_NOTIFICHE".equals(name) && "post".equals(prefix) ) {
                param = postMAIL_NOTIFICHE;
            } else if ( "MAIL_NOTIFICHE".equals(name) && prefix == null ) {
                param = postMAIL_NOTIFICHE;
            }
            if ( "CCR_NOTIFICHE".equals(name) && "post".equals(prefix) ) {
                param = postCCR_NOTIFICHE;
            } else if ( "CCR_NOTIFICHE".equals(name) && prefix == null ) {
                param = postCCR_NOTIFICHE;
            }
            if ( "RECUPERO_PASSWORD".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlRECUPERO_PASSWORD;
            } else if ( "RECUPERO_PASSWORD".equals(name) && prefix == null ) {
                param = ctrlRECUPERO_PASSWORD;
            }
            if ( "TAG_CIM".equals(name) && "post".equals(prefix) ) {
                param = postTAG_CIM;
            } else if ( "TAG_CIM".equals(name) && prefix == null ) {
                param = postTAG_CIM;
            }
            if ( "AD4PROGETTO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PROGETTO;
            } else if ( "AD4PROGETTO".equals(name) && prefix == null ) {
                param = sesAD4PROGETTO;
            }
            if ( "ID_SERVIZIO".equals(name) && "url".equals(prefix) ) {
                param = urlID_SERVIZIO;
            } else if ( "ID_SERVIZIO".equals(name) && prefix == null ) {
                param = urlID_SERVIZIO;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "RECUPERO_PASSWORD".equals(name) && "post".equals(prefix) ) {
                param = postRECUPERO_PASSWORD;
            } else if ( "RECUPERO_PASSWORD".equals(name) && prefix == null ) {
                param = postRECUPERO_PASSWORD;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @38-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @38-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @38-305A023C
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

//fireBeforeExecuteSelectEvent @38-D00ACF95
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

//fireAfterExecuteSelectEvent @38-3BAD39CE
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

//fireBeforeBuildInsertEvent @38-FBA08B71
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

//fireBeforeExecuteInsertEvent @38-47AFA6A5
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

//fireAfterExecuteInsertEvent @38-E9CE95AE
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

//fireBeforeBuildSelectEvent @38-2405BE8B
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

//fireBeforeExecuteSelectEvent @38-E9DFF86B
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

//fireAfterExecuteSelectEvent @38-580A2987
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

//fireBeforeBuildSelectEvent @38-D021D0EA
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

//fireBeforeExecuteDeleteEvent @38-DD540FBB
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

//fireAfterExecuteDeleteEvent @38-2A6E2049
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

//class DataObject Tail @38-ED3F53A4
} // End of class DS
//End class DataObject Tail

