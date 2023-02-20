//ComuneImpostaRow: import @5-D20624D9
package restrict.Ad4DizionariComuneImposta;

import java.util.*;
import com.codecharge.db.*;
//End ComuneImpostaRow: import

//ComuneImpostaRow: class head @5-5BC6A497
public class ComuneImpostaRow {
//End ComuneImpostaRow: class head

//ComuneImpostaRow: declare fiels @5-B2FE7FC5
    private TextField PROVINCIA_LABEL = new TextField("PROVINCIA_LABEL", "PROVINCIA_STATO");
    private TextField HIDE_BEGIN = new TextField("HIDE_BEGIN", "HIDE_BEGIN");
    private LongField PROVINCIA_STATO = new LongField("PROVINCIA_STATO", "PROVINCIA_STATO");
    private TextField HIDE_END = new TextField("HIDE_END", "HIDE_END");
    private TextField COMUNE_LABEL = new TextField("COMUNE_LABEL", "COMUNE");
    private TextField HIDE_BEGIN2 = new TextField("HIDE_BEGIN2", "HIDE_BEGIN");
    private LongField COMUNE = new LongField("COMUNE", "COMUNE");
    private TextField HIDE_END2 = new TextField("HIDE_END2", "HIDE_END");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField TRADUZIONE = new TextField("TRADUZIONE", "OPEN_TRADUZIONE");
    private TextField DENOMINAZIONE_BREVE = new TextField("DENOMINAZIONE_BREVE", "DENOMINAZIONE_BREVE");
    private TextField TRADUZIONE_BREVE = new TextField("TRADUZIONE_BREVE", "OPEN_TRADUZIONE_BR");
    private TextField CAPOLUOGO_PROVINCIA = new TextField("CAPOLUOGO_PROVINCIA", "CAPOLUOGO_PROVINCIA");
    private LongField CAP = new LongField("CAP", "CAP");
    private TextField TRIBUNALE_DESC = new TextField("TRIBUNALE_DESC", "DENOMINAZIONE");
    private TextField TRIBUNALE_LOV = new TextField("TRIBUNALE_LOV", "TRIBUNALE_LOV");
    private LongField PROVINCIA_TRIBUNALE = new LongField("PROVINCIA_TRIBUNALE", "PROVINCIA_TRIBUNALE");
    private LongField COMUNE_TRIBUNALE = new LongField("COMUNE_TRIBUNALE", "COMUNE_TRIBUNALE");
    private TextField DATA_SOPPRESSIONE = new TextField("DATA_SOPPRESSIONE", "DATA_SOPPRESSIONE");
    private TextField FUSIONE_DESC = new TextField("FUSIONE_DESC", "DENOMINAZIONE");
    private TextField FUSIONE_LOV = new TextField("FUSIONE_LOV", "FUSIONE_LOV");
    private LongField PROVINCIA_FUSIONE = new LongField("PROVINCIA_FUSIONE", "PROVINCIA_FUSIONE");
    private LongField COMUNE_FUSIONE = new LongField("COMUNE_FUSIONE", "COMUNE_FUSIONE");
    private TextField SIGLA_CFIS = new TextField("SIGLA_CFIS", "SIGLA_CFIS");
    private TextField DATA_ISTITUZIONE = new TextField("DATA_ISTITUZIONE", "DATA_ISTITUZIONE");
    private TextField LABEL_UPD = new TextField("LABEL_UPD", "LABEL_UPD");
//End ComuneImpostaRow: declare fiels

//ComuneImpostaRow: constructor @5-B4FE0BAB
    public ComuneImpostaRow() {
    }
//End ComuneImpostaRow: constructor

//ComuneImpostaRow: method(s) of PROVINCIA_LABEL @6-5A891313
    public TextField getPROVINCIA_LABELField() {
        return PROVINCIA_LABEL;
    }

    public String getPROVINCIA_LABEL() {
        return PROVINCIA_LABEL.getValue();
    }

    public void setPROVINCIA_LABEL(String value) {
        this.PROVINCIA_LABEL.setValue(value);
    }
//End ComuneImpostaRow: method(s) of PROVINCIA_LABEL

//ComuneImpostaRow: method(s) of HIDE_BEGIN @7-C8D37CAD
    public TextField getHIDE_BEGINField() {
        return HIDE_BEGIN;
    }

    public String getHIDE_BEGIN() {
        return HIDE_BEGIN.getValue();
    }

    public void setHIDE_BEGIN(String value) {
        this.HIDE_BEGIN.setValue(value);
    }
//End ComuneImpostaRow: method(s) of HIDE_BEGIN

//ComuneImpostaRow: method(s) of PROVINCIA_STATO @8-7724CFC4
    public LongField getPROVINCIA_STATOField() {
        return PROVINCIA_STATO;
    }

    public Long getPROVINCIA_STATO() {
        return PROVINCIA_STATO.getValue();
    }

    public void setPROVINCIA_STATO(Long value) {
        this.PROVINCIA_STATO.setValue(value);
    }
//End ComuneImpostaRow: method(s) of PROVINCIA_STATO

//ComuneImpostaRow: method(s) of HIDE_END @9-A2CCED87
    public TextField getHIDE_ENDField() {
        return HIDE_END;
    }

    public String getHIDE_END() {
        return HIDE_END.getValue();
    }

    public void setHIDE_END(String value) {
        this.HIDE_END.setValue(value);
    }
//End ComuneImpostaRow: method(s) of HIDE_END

//ComuneImpostaRow: method(s) of COMUNE_LABEL @43-D981A933
    public TextField getCOMUNE_LABELField() {
        return COMUNE_LABEL;
    }

    public String getCOMUNE_LABEL() {
        return COMUNE_LABEL.getValue();
    }

    public void setCOMUNE_LABEL(String value) {
        this.COMUNE_LABEL.setValue(value);
    }
//End ComuneImpostaRow: method(s) of COMUNE_LABEL

//ComuneImpostaRow: method(s) of HIDE_BEGIN2 @44-53F068DF
    public TextField getHIDE_BEGIN2Field() {
        return HIDE_BEGIN2;
    }

    public String getHIDE_BEGIN2() {
        return HIDE_BEGIN2.getValue();
    }

    public void setHIDE_BEGIN2(String value) {
        this.HIDE_BEGIN2.setValue(value);
    }
//End ComuneImpostaRow: method(s) of HIDE_BEGIN2

//ComuneImpostaRow: method(s) of COMUNE @45-532A21D6
    public LongField getCOMUNEField() {
        return COMUNE;
    }

    public Long getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(Long value) {
        this.COMUNE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of COMUNE

//ComuneImpostaRow: method(s) of HIDE_END2 @46-6FD8C66C
    public TextField getHIDE_END2Field() {
        return HIDE_END2;
    }

    public String getHIDE_END2() {
        return HIDE_END2.getValue();
    }

    public void setHIDE_END2(String value) {
        this.HIDE_END2.setValue(value);
    }
//End ComuneImpostaRow: method(s) of HIDE_END2

//ComuneImpostaRow: method(s) of DENOMINAZIONE @10-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of DENOMINAZIONE

//ComuneImpostaRow: method(s) of TRADUZIONE @63-283362F9
    public TextField getTRADUZIONEField() {
        return TRADUZIONE;
    }

    public String getTRADUZIONE() {
        return TRADUZIONE.getValue();
    }

    public void setTRADUZIONE(String value) {
        this.TRADUZIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of TRADUZIONE

//ComuneImpostaRow: method(s) of DENOMINAZIONE_BREVE @47-7E29542D
    public TextField getDENOMINAZIONE_BREVEField() {
        return DENOMINAZIONE_BREVE;
    }

    public String getDENOMINAZIONE_BREVE() {
        return DENOMINAZIONE_BREVE.getValue();
    }

    public void setDENOMINAZIONE_BREVE(String value) {
        this.DENOMINAZIONE_BREVE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of DENOMINAZIONE_BREVE

//ComuneImpostaRow: method(s) of TRADUZIONE_BREVE @64-3702B8AB
    public TextField getTRADUZIONE_BREVEField() {
        return TRADUZIONE_BREVE;
    }

    public String getTRADUZIONE_BREVE() {
        return TRADUZIONE_BREVE.getValue();
    }

    public void setTRADUZIONE_BREVE(String value) {
        this.TRADUZIONE_BREVE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of TRADUZIONE_BREVE

//ComuneImpostaRow: method(s) of CAPOLUOGO_PROVINCIA @48-85035DD4
    public TextField getCAPOLUOGO_PROVINCIAField() {
        return CAPOLUOGO_PROVINCIA;
    }

    public String getCAPOLUOGO_PROVINCIA() {
        return CAPOLUOGO_PROVINCIA.getValue();
    }

    public void setCAPOLUOGO_PROVINCIA(String value) {
        this.CAPOLUOGO_PROVINCIA.setValue(value);
    }
//End ComuneImpostaRow: method(s) of CAPOLUOGO_PROVINCIA

//ComuneImpostaRow: method(s) of CAP @49-99C8E97A
    public LongField getCAPField() {
        return CAP;
    }

    public Long getCAP() {
        return CAP.getValue();
    }

    public void setCAP(Long value) {
        this.CAP.setValue(value);
    }
//End ComuneImpostaRow: method(s) of CAP

//ComuneImpostaRow: method(s) of TRIBUNALE_DESC @52-80AFEE8F
    public TextField getTRIBUNALE_DESCField() {
        return TRIBUNALE_DESC;
    }

    public String getTRIBUNALE_DESC() {
        return TRIBUNALE_DESC.getValue();
    }

    public void setTRIBUNALE_DESC(String value) {
        this.TRIBUNALE_DESC.setValue(value);
    }
//End ComuneImpostaRow: method(s) of TRIBUNALE_DESC

//ComuneImpostaRow: method(s) of TRIBUNALE_LOV @53-52A185A8
    public TextField getTRIBUNALE_LOVField() {
        return TRIBUNALE_LOV;
    }

    public String getTRIBUNALE_LOV() {
        return TRIBUNALE_LOV.getValue();
    }

    public void setTRIBUNALE_LOV(String value) {
        this.TRIBUNALE_LOV.setValue(value);
    }
//End ComuneImpostaRow: method(s) of TRIBUNALE_LOV

//ComuneImpostaRow: method(s) of PROVINCIA_TRIBUNALE @50-64212C0A
    public LongField getPROVINCIA_TRIBUNALEField() {
        return PROVINCIA_TRIBUNALE;
    }

    public Long getPROVINCIA_TRIBUNALE() {
        return PROVINCIA_TRIBUNALE.getValue();
    }

    public void setPROVINCIA_TRIBUNALE(Long value) {
        this.PROVINCIA_TRIBUNALE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of PROVINCIA_TRIBUNALE

//ComuneImpostaRow: method(s) of COMUNE_TRIBUNALE @51-559B259A
    public LongField getCOMUNE_TRIBUNALEField() {
        return COMUNE_TRIBUNALE;
    }

    public Long getCOMUNE_TRIBUNALE() {
        return COMUNE_TRIBUNALE.getValue();
    }

    public void setCOMUNE_TRIBUNALE(Long value) {
        this.COMUNE_TRIBUNALE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of COMUNE_TRIBUNALE

//ComuneImpostaRow: method(s) of DATA_SOPPRESSIONE @54-B443E04E
    public TextField getDATA_SOPPRESSIONEField() {
        return DATA_SOPPRESSIONE;
    }

    public String getDATA_SOPPRESSIONE() {
        return DATA_SOPPRESSIONE.getValue();
    }

    public void setDATA_SOPPRESSIONE(String value) {
        this.DATA_SOPPRESSIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of DATA_SOPPRESSIONE

//ComuneImpostaRow: method(s) of FUSIONE_DESC @56-40A66BD6
    public TextField getFUSIONE_DESCField() {
        return FUSIONE_DESC;
    }

    public String getFUSIONE_DESC() {
        return FUSIONE_DESC.getValue();
    }

    public void setFUSIONE_DESC(String value) {
        this.FUSIONE_DESC.setValue(value);
    }
//End ComuneImpostaRow: method(s) of FUSIONE_DESC

//ComuneImpostaRow: method(s) of FUSIONE_LOV @57-E52E8B23
    public TextField getFUSIONE_LOVField() {
        return FUSIONE_LOV;
    }

    public String getFUSIONE_LOV() {
        return FUSIONE_LOV.getValue();
    }

    public void setFUSIONE_LOV(String value) {
        this.FUSIONE_LOV.setValue(value);
    }
//End ComuneImpostaRow: method(s) of FUSIONE_LOV

//ComuneImpostaRow: method(s) of PROVINCIA_FUSIONE @58-7DD5575A
    public LongField getPROVINCIA_FUSIONEField() {
        return PROVINCIA_FUSIONE;
    }

    public Long getPROVINCIA_FUSIONE() {
        return PROVINCIA_FUSIONE.getValue();
    }

    public void setPROVINCIA_FUSIONE(Long value) {
        this.PROVINCIA_FUSIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of PROVINCIA_FUSIONE

//ComuneImpostaRow: method(s) of COMUNE_FUSIONE @59-91EF9831
    public LongField getCOMUNE_FUSIONEField() {
        return COMUNE_FUSIONE;
    }

    public Long getCOMUNE_FUSIONE() {
        return COMUNE_FUSIONE.getValue();
    }

    public void setCOMUNE_FUSIONE(Long value) {
        this.COMUNE_FUSIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of COMUNE_FUSIONE

//ComuneImpostaRow: method(s) of SIGLA_CFIS @60-5A1E68BF
    public TextField getSIGLA_CFISField() {
        return SIGLA_CFIS;
    }

    public String getSIGLA_CFIS() {
        return SIGLA_CFIS.getValue();
    }

    public void setSIGLA_CFIS(String value) {
        this.SIGLA_CFIS.setValue(value);
    }
//End ComuneImpostaRow: method(s) of SIGLA_CFIS

//ComuneImpostaRow: method(s) of DATA_ISTITUZIONE @65-20313F48
    public TextField getDATA_ISTITUZIONEField() {
        return DATA_ISTITUZIONE;
    }

    public String getDATA_ISTITUZIONE() {
        return DATA_ISTITUZIONE.getValue();
    }

    public void setDATA_ISTITUZIONE(String value) {
        this.DATA_ISTITUZIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of DATA_ISTITUZIONE

//ComuneImpostaRow: method(s) of LABEL_UPD @67-F6A687CF
    public TextField getLABEL_UPDField() {
        return LABEL_UPD;
    }

    public String getLABEL_UPD() {
        return LABEL_UPD.getValue();
    }

    public void setLABEL_UPD(String value) {
        this.LABEL_UPD.setValue(value);
    }
//End ComuneImpostaRow: method(s) of LABEL_UPD

//ComuneImpostaRow: class tail @5-FCB6E20C
}
//End ComuneImpostaRow: class tail

