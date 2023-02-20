//ldap_config DataSource @110-BC76E0B9
package ad4web.AD4Omonimie;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ldap_config DataSource

//class DataObject Header @110-C464E9C0
public class ldap_configDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @110-3AF761EA
    

    LongField urlS_daignorare = new LongField(null, null);
    
    LongField urlS_copiati = new LongField(null, null);
    
    TextField urlS_unificati = new TextField(null, null);
    

    private ldap_configRow[] rows = new ldap_configRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @110-AE98F4D4

    public void  setUrlS_daignorare( long param ) {
        this.urlS_daignorare.setValue( param );
    }

    public void  setUrlS_daignorare( long param, Format ignore ) throws java.text.ParseException {
        this.urlS_daignorare.setValue( param );
    }

    public void  setUrlS_daignorare( Object param, Format format ) throws java.text.ParseException {
        this.urlS_daignorare.setValue( param, format );
    }

    public void  setUrlS_daignorare( Long param ) {
        this.urlS_daignorare.setValue( param );
    }

    public void  setUrlS_copiati( long param ) {
        this.urlS_copiati.setValue( param );
    }

    public void  setUrlS_copiati( long param, Format ignore ) throws java.text.ParseException {
        this.urlS_copiati.setValue( param );
    }

    public void  setUrlS_copiati( Object param, Format format ) throws java.text.ParseException {
        this.urlS_copiati.setValue( param, format );
    }

    public void  setUrlS_copiati( Long param ) {
        this.urlS_copiati.setValue( param );
    }

    public void  setUrlS_unificati( String param ) {
        this.urlS_unificati.setValue( param );
    }

    public void  setUrlS_unificati( Object param ) {
        this.urlS_unificati.setValue( param );
    }

    public void  setUrlS_unificati( Object param, Format ignore ) {
        this.urlS_unificati.setValue( param );
    }

    public ldap_configRow[] getRows() {
        return rows;
    }

    public void setRows(ldap_configRow[] rows) {
        this.rows = rows;
    }

    public void setPageNum( int pageNum ) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

//End properties of DataObject

//constructor @110-CB2DD2D7
    public ldap_configDataObject(Page page) {
        super(page);
    }
//End constructor

//load @110-38BE85C3
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "  SELECT utente utente "
                    + "       , sosia sosia "
                    + "       ,  "
                    + "nominativo nominativo "
                    + "       , sosia_nominativo sosia_nominativo "
                    + "       ,  "
                    + "'<img title=\"Copia profilo su '||UTENTE.GET_NOMINATIVO(utente,'Y',0)||'\" src=\"../common/images/AD4/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">'  COPIA_PROFILO "
                    + "       , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO(utente,'Y',0)||'\" src=\"../common/images/AD4/unif.gif\" height=\"18\" width=\"18\" border=\"0\">'  UNIFICA_PROFILO "
                    + "       , decode (omge.ignorare,1,'<img title=\"Ignora\" src=\"../common/images/AD4/rosso.gif\" height=\"18\" width=\"18\" border=\"0\">' , "
                    + "                                 '<img title=\"Ignora\" src=\"../common/images/AD4/verde.gif\" height=\"18\" width=\"18\" border=\"0\">') IGNORA "
                    + "       , '<img title=\"Modifica\" src=\"../common/images/AD4/edit.gif\" height=\"18\" width=\"18\" border=\"0\">'  MODIFICA "
                    + "    FROM ricerca_omonimie riom "
                    + "       , omonimie_gestite omge "
                    + "   WHERE omge.primario (+)= utente "
                    + "      AND omge.secondario (+)= sosia "
                    + "      AND (nvl(omge.ignorare,0) = {s_daignorare} or {s_daignorare} is null) "
                    + "      AND (nvl(omge.copiato,0)= {s_copiati} or {s_copiati} is null) "
                    + "      AND (nvl(omge.unificato,0)= {s_unificati} or {s_unificati} is null) "
                    + "      and not exists (select 1 from omonimie_gestite   "
                    + "                       where primario = sosia and secondario = utente)  "
                    + "UNION ALL "
                    + "SELECT sosia utente "
                    + "       , utente sosia "
                    + "       , sosia_nominativo nominativo "
                    + "       , nominativo sosia_nominativo "
                    + "       , '<img title=\"Copia profilo su '|| UTENTE.GET_NOMINATIVO (sosia, 'Y', 0)     || '\" src=\"../common/images/AD4/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">'          COPIA_PROFILO "
                    + "       , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO(sosia,'Y',0)||'\" src=\"../common/images/AD4/unif.gif\" height=\"18\" width=\"18\" border=\"0\">'  UNIFICA_PROFILO "
                    + "       , decode (omge.ignorare,1,'<img title=\"Ignora\" src=\"../common/images/AD4/rosso.gif\" height=\"18\" width=\"18\" border=\"0\">' , "
                    + "                                 '<img title=\"Ignora\" src=\"../common/images/AD4/verde.gif\" height=\"18\" width=\"18\" border=\"0\">') IGNORA "
                    + "       , '<img title=\"Modifica\" src=\"../common/images/AD4/edit.gif\" height=\"18\" width=\"18\" border=\"0\">'  MODIFICA "
                    + "  FROM ricerca_omonimie riom, omonimie_gestite omge "
                    + " WHERE     omge.primario = sosia "
                    + "       AND omge.secondario = utente "
                    + "       AND (NVL (omge.ignorare, 0) = {s_daignorare} OR {s_daignorare} IS NULL) "
                    + "       AND (NVL (omge.copiato, 0) = {s_copiati} OR {s_copiati} IS NULL) "
                    + "       AND (NVL (omge.unificato, 0) = {s_unificati} OR {s_unificati} IS NULL)" );
        if ( urlS_daignorare.getObjectValue() == null ) urlS_daignorare.setValue( null );
        command.addParameter( "s_daignorare", urlS_daignorare, null );
        if ( urlS_copiati.getObjectValue() == null ) urlS_copiati.setValue( null );
        command.addParameter( "s_copiati", urlS_copiati, null );
        if ( StringUtils.isEmpty( (String) urlS_unificati.getObjectValue() ) ) urlS_unificati.setValue( null );
        command.addParameter( "s_unificati", urlS_unificati, null );
        command.setCountSql( "SELECT COUNT(*) FROM (  SELECT utente utente , sosia sosia , nominativo nominativo ,  "
                                                         + "            sosia_nominativo sosia_nominativo ,  "
                                                         + "            '<img title=\"Copia profilo su '||UTENTE.GET_NOMINATIVO(utente,'Y',0)||'\" src=\"../common/images/AD4/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">' COPIA_PROFILO , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO(utente,'Y',0)||'\" src=\"../common/images/AD4/unif.gif\" height=\"18\" width=\"18\" border=\"0\">' UNIFICA_PROFILO , decode (omge.ignorare,1,'<img title=\"Ignora\" src=\"../common/images/AD4/rosso.gif\" height=\"18\" width=\"18\" border=\"0\">' , '<img title=\"Ignora\" src=\"../common/images/AD4/verde.gif\" height=\"18\" width=\"18\" border=\"0\">') IGNORA , '<img title=\"Modifica\" src=\"../common/images/AD4/edit.gif\" height=\"18\" width=\"18\" border=\"0\">' MODIFICA FROM ricerca_omonimie riom , omonimie_gestite omge WHERE omge.primario (+)= utente AND omge.secondario (+)= sosia AND (nvl(omge.ignorare,0) = {s_daignorare} or {s_daignorare} is null) AND (nvl(omge.copiato,0)= {s_copiati} or {s_copiati} is null) AND (nvl(omge.unificato,0)= {s_unificati} or {s_unificati} is null) and not exists (select 1 from omonimie_gestite where primario = sosia and secondario = utente) UNION ALL SELECT sosia utente , utente sosia , sosia_nominativo nominativo , nominativo sosia_nominativo , '<img title=\"Copia profilo su '|| UTENTE.GET_NOMINATIVO (sosia, 'Y', 0) || '\" src=\"../common/images/AD4/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">' COPIA_PROFILO , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO(sosia,'Y',0)||'\" src=\"../common/images/AD4/unif.gif\" height=\"18\" width=\"18\" border=\"0\">' UNIFICA_PROFILO , decode (omge.ignorare,1,'<img title=\"Ignora\" src=\"../common/images/AD4/rosso.gif\" height=\"18\" width=\"18\" border=\"0\">' , '<img title=\"Ignora\" src=\"../common/images/AD4/verde.gif\" height=\"18\" width=\"18\" border=\"0\">') IGNORA , '<img title=\"Modifica\" src=\"../common/images/AD4/edit.gif\" height=\"18\" width=\"18\" border=\"0\">' MODIFICA FROM ricerca_omonimie riom, omonimie_gestite omge WHERE omge.primario = sosia AND omge.secondario = utente AND (NVL (omge.ignorare, 0) = {s_daignorare} OR {s_daignorare} IS NULL) AND (NVL (omge.copiato, 0) = {s_copiati} OR {s_copiati} IS NULL) AND (NVL (omge.unificato, 0) = {s_unificati} OR {s_unificati} IS NULL) ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        }
        command.setStartPos( ( pageNum - 1 ) * pageSize + 1 );
        command.setFetchSize( pageSize );

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        if ( ! StringUtils.isEmpty( command.getCountSql() ) ) {
            if ( ! ds.hasErrors() ) {
                amountOfRows = command.count();
                CCLogger.getInstance().debug(command.toString());
            }
        }
        Enumeration records = null;
        if ( ! ds.hasErrors() ) {
            records = command.getRows();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @110-6421E004
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ldap_configRow row = new ldap_configRow();
                DbRow record = (DbRow) records.nextElement();
                row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
                row.setSOSIA_NOMINATIVO(Utils.convertToString(ds.parse(record.get("SOSIA_NOMINATIVO"), row.getSOSIA_NOMINATIVOField())));
                row.setUNIFICA_PROFILO(Utils.convertToString(ds.parse(record.get("UNIFICA_PROFILO"), row.getUNIFICA_PROFILOField())));
                row.setCOPIA_PROFILO(Utils.convertToString(ds.parse(record.get("COPIA_PROFILO"), row.getCOPIA_PROFILOField())));
                row.setIGNORA(Utils.convertToString(ds.parse(record.get("IGNORA"), row.getIGNORAField())));
                row.setMODIFICA(Utils.convertToString(ds.parse(record.get("MODIFICA"), row.getMODIFICAField())));
                row.setUTENTE_UNIFICARE(Utils.convertToString(ds.parse(record.get("SOSIA"), row.getUTENTE_UNIFICAREField())));
                row.setSOSIA(Utils.convertToString(ds.parse(record.get("SOSIA"), row.getSOSIAField())));
                row.setUTENTE_ALIMENTARE_UNIFICARE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTE_ALIMENTARE_UNIFICAREField())));
                row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
                row.setUTENTE_ALIMENTARE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTE_ALIMENTAREField())));
                row.setUTENTE_ORIGINE(Utils.convertToString(ds.parse(record.get("SOSIA"), row.getUTENTE_ORIGINEField())));
                row.setS_SOSIA(Utils.convertToString(ds.parse(record.get("SOSIA"), row.getS_SOSIAField())));
                row.setS_UTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getS_UTENTEField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @110-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @110-281A3FA0
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_daignorare".equals(name) && "url".equals(prefix) ) {
                param = urlS_daignorare;
            } else if ( "s_daignorare".equals(name) && prefix == null ) {
                param = urlS_daignorare;
            }
            if ( "s_copiati".equals(name) && "url".equals(prefix) ) {
                param = urlS_copiati;
            } else if ( "s_copiati".equals(name) && prefix == null ) {
                param = urlS_copiati;
            }
            if ( "s_unificati".equals(name) && "url".equals(prefix) ) {
                param = urlS_unificati;
            } else if ( "s_unificati".equals(name) && prefix == null ) {
                param = urlS_unificati;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @110-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @110-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @110-238A81BB
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource(this);
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @110-9DA7B025
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @110-F7E8A616
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//class DataObject Tail @110-ED3F53A4
} // End of class DS
//End class DataObject Tail

