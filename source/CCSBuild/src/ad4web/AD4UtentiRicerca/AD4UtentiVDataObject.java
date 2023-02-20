//AD4UtentiV DataSource @142-186E581C
package ad4web.AD4UtentiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4UtentiV DataSource

//class DataObject Header @142-2CB52E4E
public class AD4UtentiVDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @142-E908F7A6
    

    TextField urlS_NOMINATIVO = new TextField(null, null);
    
    TextField urlS_DATA_DA = new TextField(null, null);
    
    TextField urlS_DATA_A = new TextField(null, null);
    
    TextField urlS_NOME = new TextField(null, null);
    
    TextField urlS_RICERCA = new TextField(null, null);
    
    TextField urlS_COD_FISCALE = new TextField(null, null);
    
    TextField urlS_STATO = new TextField(null, null);
    
    TextField urlS_ACCESSO = new TextField(null, null);
    
    TextField urlS_GRUPPO = new TextField(null, null);
    
    TextField urlUTENTE_ALIMENTARE = new TextField(null, null);
    
    TextField urlUTENTE_ALIMENTARE_UNIFICARE = new TextField(null, null);
    
    TextField urlS_GRUPPO_LAVORO = new TextField(null, null);
    

    private AD4UtentiVRow[] rows = new AD4UtentiVRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @142-D8F25B7C

    public void  setUrlS_NOMINATIVO( String param ) {
        this.urlS_NOMINATIVO.setValue( param );
    }

    public void  setUrlS_NOMINATIVO( Object param ) {
        this.urlS_NOMINATIVO.setValue( param );
    }

    public void  setUrlS_NOMINATIVO( Object param, Format ignore ) {
        this.urlS_NOMINATIVO.setValue( param );
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

    public void  setUrlS_NOME( String param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param, Format ignore ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_RICERCA( String param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param, Format ignore ) {
        this.urlS_RICERCA.setValue( param );
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

    public void  setUrlS_ACCESSO( String param ) {
        this.urlS_ACCESSO.setValue( param );
    }

    public void  setUrlS_ACCESSO( Object param ) {
        this.urlS_ACCESSO.setValue( param );
    }

    public void  setUrlS_ACCESSO( Object param, Format ignore ) {
        this.urlS_ACCESSO.setValue( param );
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

    public void  setUrlUTENTE_ALIMENTARE( String param ) {
        this.urlUTENTE_ALIMENTARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE( Object param ) {
        this.urlUTENTE_ALIMENTARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE( Object param, Format ignore ) {
        this.urlUTENTE_ALIMENTARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE_UNIFICARE( String param ) {
        this.urlUTENTE_ALIMENTARE_UNIFICARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE_UNIFICARE( Object param ) {
        this.urlUTENTE_ALIMENTARE_UNIFICARE.setValue( param );
    }

    public void  setUrlUTENTE_ALIMENTARE_UNIFICARE( Object param, Format ignore ) {
        this.urlUTENTE_ALIMENTARE_UNIFICARE.setValue( param );
    }

    public void  setUrlS_GRUPPO_LAVORO( String param ) {
        this.urlS_GRUPPO_LAVORO.setValue( param );
    }

    public void  setUrlS_GRUPPO_LAVORO( Object param ) {
        this.urlS_GRUPPO_LAVORO.setValue( param );
    }

    public void  setUrlS_GRUPPO_LAVORO( Object param, Format ignore ) {
        this.urlS_GRUPPO_LAVORO.setValue( param );
    }

    public AD4UtentiVRow[] getRows() {
        return rows;
    }

    public void setRows(AD4UtentiVRow[] rows) {
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

//constructor @142-53B8D993
    public AD4UtentiVDataObject(Page page) {
        super(page);
    }
//End constructor

//load @142-BBCE9D72
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT utente, "
                    + "       NOMINATIVO,  "
                    + "     "
                    + "       AD4WEB.GET_DATI_UTENTE(uten.UTENTE) DATI_UTENTE, "
                    + "       DECODE('{UTENTE_ALIMENTARE}', NULL,  "
                    + "TO_CHAR(NULL) "
                    + "                              ,  "
                    + "'<img title=\"Copia profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_ALIMENTARE}','Y',0)||'\" src=\"../common/images/AMV/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">')  COPIA_PROFILO, "
                    + "       DECODE('{UTENTE_ALIMENTARE_UNIFICARE}', NULL, TO_CHAR(NULL) "
                    + "                              , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_ALIMENTARE_UNIFICARE}','Y',0)||'\" src=\"../common/images/AMV/unif.gif\" height=\"18\" width=\"18\" border=\"0\">')  UNIFICA_PROFILO "
                    + "  FROM UTENTI uten "
                    + " WHERE (upper(utente) like upper('%{s_NOMINATIVO}%') "
                    + "    or upper(nominativo) like upper('%{s_NOMINATIVO}%')) "
                    + "   and nvl(stato,' ') like upper(nvl('{s_STATO}','%')) "
                    + "   and (   (ultimo_tentativo is null and  "
                    + "            '{s_ACCESSO}' = 'N') "
                    + "        or (ultimo_tentativo between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') "
                    + " and             "
                    + "            '{s_ACCESSO}' = 'Y') "
                    + "        or (nvl(ultimo_tentativo,to_date('01/01/1951','dd/mm/yyyy')) between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') "
                    + " and "
                    + "            '{s_ACCESSO}' is null) "
                    + "       ) "
                    + "   and '{s_GRUPPO}' is null "
                    + "   and ('{s_GRUPPO_LAVORO}' is null OR gruppo_lavoro = '{s_GRUPPO_LAVORO}' )    "
                    + "   and TIPO_UTENTE = 'U' "
                    + "   and '{s_RICERCA}' = 'Y' "
                    + "   and '{s_NOMINATIVO}' is not null "
                    + "HAVING upper(NVL(AD4_SOGGETTO.GET_NOME(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y',0)),' ')) like UPPER('%{s_NOME}%') "
                    + "   and upper(NVL(AD4_SOGGETTO.GET_CODICE_FISCALE(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y',0)),' ')) like UPPER('%{s_COD_FISCALE}%') "
                    + "GROUP BY uten.utente, nominativo    "
                    + "union all "
                    + "SELECT uten.utente, "
                    + "       NOMINATIVO,      "
                    + "       AD4WEB.GET_DATI_UTENTE(uten.UTENTE) DATI_UTENTE, "
                    + "       DECODE('{UTENTE_ALIMENTARE}', NULL, TO_CHAR(NULL) "
                    + "                              , '<img title=\"Copia profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_ALIMENTARE}','Y',0)||'\" src=\"../common/images/AMV/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">')  COPIA_PROFILO, "
                    + "       DECODE('{UTENTE_UNIFICARE}', NULL, TO_CHAR(NULL) "
                    + "                              , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_UNIFICARE}','Y',0)||'\" src=\"../common/images/AMV/unif.gif\" height=\"18\" width=\"18\" border=\"0\">')  UNIFICA_PROFILO "
                    + "  FROM UTENTI uten, utenti_gruppo utgr "
                    + " WHERE uten.utente = utgr.utente  "
                    + "   and utgr.gruppo = '{s_GRUPPO}' "
                    + "   AND (upper(uten.utente) like upper('%{s_NOMINATIVO}%') "
                    + "    or upper(nominativo) like upper('%{s_NOMINATIVO}%')) "
                    + "   and nvl(stato,' ') like nvl('{s_STATO}','%') "
                    + "   and (   (ultimo_tentativo is null and  "
                    + "            '{s_ACCESSO}' = 'N') "
                    + "        or (ultimo_tentativo between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') "
                    + " and             "
                    + "            '{s_ACCESSO}' = 'Y') "
                    + "        or (nvl(ultimo_tentativo,to_date('01/01/1951','dd/mm/yyyy')) between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') "
                    + " and "
                    + "            '{s_ACCESSO}' is null) "
                    + "       ) "
                    + "   and TIPO_UTENTE = 'U' "
                    + "   and '{s_GRUPPO}' is not null "
                    + "   and ('{s_GRUPPO_LAVORO}' is null OR gruppo_lavoro = '{s_GRUPPO_LAVORO}' ) "
                    + "   and '{s_RICERCA}' = 'Y' "
                    + "   and '{s_NOMINATIVO}' is not null "
                    + "HAVING upper(NVL(AD4_SOGGETTO.GET_NOME(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y')),' ')) like UPPER('%{s_NOME}%') "
                    + "   and upper(NVL(AD4_SOGGETTO.GET_CODICE_FISCALE(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y')),' ')) like UPPER('%{s_COD_FISCALE}%')    "
                    + "GROUP BY uten.utente, nominativo        "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlS_NOMINATIVO.getObjectValue() ) ) urlS_NOMINATIVO.setValue( "" );
        command.addParameter( "s_NOMINATIVO", urlS_NOMINATIVO, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_DA.getObjectValue() ) ) urlS_DATA_DA.setValue( "" );
        command.addParameter( "s_DATA_DA", urlS_DATA_DA, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_A.getObjectValue() ) ) urlS_DATA_A.setValue( "" );
        command.addParameter( "s_DATA_A", urlS_DATA_A, null );
        if ( StringUtils.isEmpty( (String) urlS_NOME.getObjectValue() ) ) urlS_NOME.setValue( "" );
        command.addParameter( "s_NOME", urlS_NOME, null );
        if ( StringUtils.isEmpty( (String) urlS_RICERCA.getObjectValue() ) ) urlS_RICERCA.setValue( "N" );
        command.addParameter( "s_RICERCA", urlS_RICERCA, null );
        if ( StringUtils.isEmpty( (String) urlS_COD_FISCALE.getObjectValue() ) ) urlS_COD_FISCALE.setValue( "" );
        command.addParameter( "s_COD_FISCALE", urlS_COD_FISCALE, null );
        if ( StringUtils.isEmpty( (String) urlS_STATO.getObjectValue() ) ) urlS_STATO.setValue( "" );
        command.addParameter( "s_STATO", urlS_STATO, null );
        if ( StringUtils.isEmpty( (String) urlS_ACCESSO.getObjectValue() ) ) urlS_ACCESSO.setValue( "" );
        command.addParameter( "s_ACCESSO", urlS_ACCESSO, null );
        if ( StringUtils.isEmpty( (String) urlS_GRUPPO.getObjectValue() ) ) urlS_GRUPPO.setValue( "" );
        command.addParameter( "s_GRUPPO", urlS_GRUPPO, null );
        if ( StringUtils.isEmpty( (String) urlUTENTE_ALIMENTARE.getObjectValue() ) ) urlUTENTE_ALIMENTARE.setValue( "" );
        command.addParameter( "UTENTE_ALIMENTARE", urlUTENTE_ALIMENTARE, null );
        if ( StringUtils.isEmpty( (String) urlUTENTE_ALIMENTARE_UNIFICARE.getObjectValue() ) ) urlUTENTE_ALIMENTARE_UNIFICARE.setValue( "" );
        command.addParameter( "UTENTE_ALIMENTARE_UNIFICARE", urlUTENTE_ALIMENTARE_UNIFICARE, null );
        if ( StringUtils.isEmpty( (String) urlS_GRUPPO_LAVORO.getObjectValue() ) ) urlS_GRUPPO_LAVORO.setValue( "" );
        command.addParameter( "s_GRUPPO_LAVORO", urlS_GRUPPO_LAVORO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT utente, NOMINATIVO, AD4WEB.GET_DATI_UTENTE(uten.UTENTE) DATI_UTENTE,  "
                                                         + "            DECODE('{UTENTE_ALIMENTARE}', NULL, TO_CHAR(NULL) ,  "
                                                         + "            '<img title=\"Copia profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_ALIMENTARE}','Y',0)||'\" src=\"../common/images/AMV/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">') COPIA_PROFILO, DECODE('{UTENTE_ALIMENTARE_UNIFICARE}', NULL, TO_CHAR(NULL) , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_ALIMENTARE_UNIFICARE}','Y',0)||'\" src=\"../common/images/AMV/unif.gif\" height=\"18\" width=\"18\" border=\"0\">') UNIFICA_PROFILO FROM UTENTI uten WHERE (upper(utente) like upper('%{s_NOMINATIVO}%') or upper(nominativo) like upper('%{s_NOMINATIVO}%')) and nvl(stato,' ') like upper(nvl('{s_STATO}','%')) and ( (ultimo_tentativo is null and '{s_ACCESSO}' = 'N') or (ultimo_tentativo between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') and '{s_ACCESSO}' = 'Y') or (nvl(ultimo_tentativo,to_date('01/01/1951','dd/mm/yyyy')) between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') and '{s_ACCESSO}' is null) ) and '{s_GRUPPO}' is null and ('{s_GRUPPO_LAVORO}' is null OR gruppo_lavoro = '{s_GRUPPO_LAVORO}' ) and TIPO_UTENTE = 'U' and '{s_RICERCA}' = 'Y' and '{s_NOMINATIVO}' is not null HAVING upper(NVL(AD4_SOGGETTO.GET_NOME(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y',0)),' ')) like UPPER('%{s_NOME}%') and upper(NVL(AD4_SOGGETTO.GET_CODICE_FISCALE(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y',0)),' ')) like UPPER('%{s_COD_FISCALE}%') GROUP BY uten.utente, nominativo union all SELECT uten.utente, NOMINATIVO, AD4WEB.GET_DATI_UTENTE(uten.UTENTE) DATI_UTENTE, DECODE('{UTENTE_ALIMENTARE}', NULL, TO_CHAR(NULL) , '<img title=\"Copia profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_ALIMENTARE}','Y',0)||'\" src=\"../common/images/AMV/dupl.gif\" height=\"18\" width=\"18\" border=\"0\">') COPIA_PROFILO, DECODE('{UTENTE_UNIFICARE}', NULL, TO_CHAR(NULL) , '<img title=\"Unifica profilo su '||UTENTE.GET_NOMINATIVO('{UTENTE_UNIFICARE}','Y',0)||'\" src=\"../common/images/AMV/unif.gif\" height=\"18\" width=\"18\" border=\"0\">') UNIFICA_PROFILO FROM UTENTI uten, utenti_gruppo utgr WHERE uten.utente = utgr.utente and utgr.gruppo = '{s_GRUPPO}' AND (upper(uten.utente) like upper('%{s_NOMINATIVO}%') or upper(nominativo) like upper('%{s_NOMINATIVO}%')) and nvl(stato,' ') like nvl('{s_STATO}','%') and ( (ultimo_tentativo is null and '{s_ACCESSO}' = 'N') or (ultimo_tentativo between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') and '{s_ACCESSO}' = 'Y') or (nvl(ultimo_tentativo,to_date('01/01/1951','dd/mm/yyyy')) between to_date(nvl('{s_DATA_DA}','01/01/1951'),'dd/mm/yyyy') and to_date(nvl('{s_DATA_A}','31/12/2999'),'dd/mm/yyyy') and '{s_ACCESSO}' is null) ) and TIPO_UTENTE = 'U' and '{s_GRUPPO}' is not null and ('{s_GRUPPO_LAVORO}' is null OR gruppo_lavoro = '{s_GRUPPO_LAVORO}' ) and '{s_RICERCA}' = 'Y' and '{s_NOMINATIVO}' is not null HAVING upper(NVL(AD4_SOGGETTO.GET_NOME(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y')),' ')) like UPPER('%{s_NOME}%') and upper(NVL(AD4_SOGGETTO.GET_CODICE_FISCALE(AD4_UTENTE.GET_SOGGETTO(uten.UTENTE,'Y')),' ')) like UPPER('%{s_COD_FISCALE}%') GROUP BY uten.utente, nominativo  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "2 ASC" );
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

//loadDataBind @142-0EC87B03
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4UtentiVRow row = new AD4UtentiVRow();
                DbRow record = (DbRow) records.nextElement();
                row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
                row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
                row.setDATI_UTENTE(Utils.convertToString(ds.parse(record.get("DATI_UTENTE"), row.getDATI_UTENTEField())));
                row.setCOPIA_PROFILO(Utils.convertToString(ds.parse(record.get("COPIA_PROFILO"), row.getCOPIA_PROFILOField())));
                row.setUNIFICA_PROFILO(Utils.convertToString(ds.parse(record.get("UNIFICA_PROFILO"), row.getUNIFICA_PROFILOField())));
                row.setUTENTE_ORIGINE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTE_ORIGINEField())));
                row.setUTENTE_UNIFICARE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTE_UNIFICAREField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @142-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @142-4F852B33
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_NOMINATIVO".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOMINATIVO;
            } else if ( "s_NOMINATIVO".equals(name) && prefix == null ) {
                param = urlS_NOMINATIVO;
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
            if ( "s_NOME".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOME;
            } else if ( "s_NOME".equals(name) && prefix == null ) {
                param = urlS_NOME;
            }
            if ( "s_RICERCA".equals(name) && "url".equals(prefix) ) {
                param = urlS_RICERCA;
            } else if ( "s_RICERCA".equals(name) && prefix == null ) {
                param = urlS_RICERCA;
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
            if ( "s_ACCESSO".equals(name) && "url".equals(prefix) ) {
                param = urlS_ACCESSO;
            } else if ( "s_ACCESSO".equals(name) && prefix == null ) {
                param = urlS_ACCESSO;
            }
            if ( "s_GRUPPO".equals(name) && "url".equals(prefix) ) {
                param = urlS_GRUPPO;
            } else if ( "s_GRUPPO".equals(name) && prefix == null ) {
                param = urlS_GRUPPO;
            }
            if ( "UTENTE_ALIMENTARE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE_ALIMENTARE;
            } else if ( "UTENTE_ALIMENTARE".equals(name) && prefix == null ) {
                param = urlUTENTE_ALIMENTARE;
            }
            if ( "UTENTE_ALIMENTARE_UNIFICARE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE_ALIMENTARE_UNIFICARE;
            } else if ( "UTENTE_ALIMENTARE_UNIFICARE".equals(name) && prefix == null ) {
                param = urlUTENTE_ALIMENTARE_UNIFICARE;
            }
            if ( "s_GRUPPO_LAVORO".equals(name) && "url".equals(prefix) ) {
                param = urlS_GRUPPO_LAVORO;
            } else if ( "s_GRUPPO_LAVORO".equals(name) && prefix == null ) {
                param = urlS_GRUPPO_LAVORO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @142-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @142-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @142-238A81BB
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

//fireBeforeExecuteSelectEvent @142-9DA7B025
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

//fireAfterExecuteSelectEvent @142-F7E8A616
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

//class DataObject Tail @142-ED3F53A4
} // End of class DS
//End class DataObject Tail

