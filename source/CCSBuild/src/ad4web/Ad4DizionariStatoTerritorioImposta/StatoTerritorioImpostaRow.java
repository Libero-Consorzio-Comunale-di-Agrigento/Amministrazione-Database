//StatoTerritorioImpostaRow: import @5-8C1999DB
package ad4web.Ad4DizionariStatoTerritorioImposta;

import java.util.*;
import com.codecharge.db.*;
//End StatoTerritorioImpostaRow: import

//StatoTerritorioImpostaRow: class head @5-5303E00D
public class StatoTerritorioImpostaRow {
//End StatoTerritorioImpostaRow: class head

//StatoTerritorioImpostaRow: declare fiels @5-F545DC44
    private TextField STATO_TERRITORIO_LABEL = new TextField("STATO_TERRITORIO_LABEL", "STATO_TERRITORIO");
    private TextField HIDE_BEGIN = new TextField("HIDE_BEGIN", "HIDE_BEGIN");
    private LongField STATO_TERRITORIO = new LongField("STATO_TERRITORIO", "STATO_TERRITORIO");
    private TextField HIDE_END = new TextField("HIDE_END", "HIDE_END");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField SIGLA = new TextField("SIGLA", "SIGLA");
    private TextField DESC_CITTADINANZA = new TextField("DESC_CITTADINANZA", "DESC_CITTADINANZA");
    private LongField RAGGRUPPAMENTO = new LongField("RAGGRUPPAMENTO", "RAGGRUPPAMENTO");
    private LongField STATO_APPARTENENZA = new LongField("STATO_APPARTENENZA", "STATO_APPARTENENZA");
    private TextField LABEL_UPD = new TextField("LABEL_UPD", "LABEL_UPD");
//End StatoTerritorioImpostaRow: declare fiels

//StatoTerritorioImpostaRow: constructor @5-1CC138C7
    public StatoTerritorioImpostaRow() {
    }
//End StatoTerritorioImpostaRow: constructor

//StatoTerritorioImpostaRow: method(s) of STATO_TERRITORIO_LABEL @6-76DF2D58
    public TextField getSTATO_TERRITORIO_LABELField() {
        return STATO_TERRITORIO_LABEL;
    }

    public String getSTATO_TERRITORIO_LABEL() {
        return STATO_TERRITORIO_LABEL.getValue();
    }

    public void setSTATO_TERRITORIO_LABEL(String value) {
        this.STATO_TERRITORIO_LABEL.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of STATO_TERRITORIO_LABEL

//StatoTerritorioImpostaRow: method(s) of HIDE_BEGIN @7-C8D37CAD
    public TextField getHIDE_BEGINField() {
        return HIDE_BEGIN;
    }

    public String getHIDE_BEGIN() {
        return HIDE_BEGIN.getValue();
    }

    public void setHIDE_BEGIN(String value) {
        this.HIDE_BEGIN.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of HIDE_BEGIN

//StatoTerritorioImpostaRow: method(s) of STATO_TERRITORIO @8-FC46849B
    public LongField getSTATO_TERRITORIOField() {
        return STATO_TERRITORIO;
    }

    public Long getSTATO_TERRITORIO() {
        return STATO_TERRITORIO.getValue();
    }

    public void setSTATO_TERRITORIO(Long value) {
        this.STATO_TERRITORIO.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of STATO_TERRITORIO

//StatoTerritorioImpostaRow: method(s) of HIDE_END @9-A2CCED87
    public TextField getHIDE_ENDField() {
        return HIDE_END;
    }

    public String getHIDE_END() {
        return HIDE_END.getValue();
    }

    public void setHIDE_END(String value) {
        this.HIDE_END.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of HIDE_END

//StatoTerritorioImpostaRow: method(s) of DENOMINAZIONE @10-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of DENOMINAZIONE

//StatoTerritorioImpostaRow: method(s) of SIGLA @44-3A41CFD1
    public TextField getSIGLAField() {
        return SIGLA;
    }

    public String getSIGLA() {
        return SIGLA.getValue();
    }

    public void setSIGLA(String value) {
        this.SIGLA.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of SIGLA

//StatoTerritorioImpostaRow: method(s) of DESC_CITTADINANZA @43-6F5A500C
    public TextField getDESC_CITTADINANZAField() {
        return DESC_CITTADINANZA;
    }

    public String getDESC_CITTADINANZA() {
        return DESC_CITTADINANZA.getValue();
    }

    public void setDESC_CITTADINANZA(String value) {
        this.DESC_CITTADINANZA.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of DESC_CITTADINANZA

//StatoTerritorioImpostaRow: method(s) of RAGGRUPPAMENTO @45-E22E3B60
    public LongField getRAGGRUPPAMENTOField() {
        return RAGGRUPPAMENTO;
    }

    public Long getRAGGRUPPAMENTO() {
        return RAGGRUPPAMENTO.getValue();
    }

    public void setRAGGRUPPAMENTO(Long value) {
        this.RAGGRUPPAMENTO.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of RAGGRUPPAMENTO

//StatoTerritorioImpostaRow: method(s) of STATO_APPARTENENZA @41-681D1DBD
    public LongField getSTATO_APPARTENENZAField() {
        return STATO_APPARTENENZA;
    }

    public Long getSTATO_APPARTENENZA() {
        return STATO_APPARTENENZA.getValue();
    }

    public void setSTATO_APPARTENENZA(Long value) {
        this.STATO_APPARTENENZA.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of STATO_APPARTENENZA

//StatoTerritorioImpostaRow: method(s) of LABEL_UPD @42-F6A687CF
    public TextField getLABEL_UPDField() {
        return LABEL_UPD;
    }

    public String getLABEL_UPD() {
        return LABEL_UPD.getValue();
    }

    public void setLABEL_UPD(String value) {
        this.LABEL_UPD.setValue(value);
    }
//End StatoTerritorioImpostaRow: method(s) of LABEL_UPD

//StatoTerritorioImpostaRow: class tail @5-FCB6E20C
}
//End StatoTerritorioImpostaRow: class tail

