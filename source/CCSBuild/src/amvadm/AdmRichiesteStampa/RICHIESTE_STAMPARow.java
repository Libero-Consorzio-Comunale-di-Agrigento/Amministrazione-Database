//RICHIESTE_STAMPARow: import @2-7C61AE3B
package amvadm.AdmRichiesteStampa;

import java.util.*;
import com.codecharge.db.*;
//End RICHIESTE_STAMPARow: import

//RICHIESTE_STAMPARow: class head @2-5B3C4CC2
public class RICHIESTE_STAMPARow {
//End RICHIESTE_STAMPARow: class head

//RICHIESTE_STAMPARow: declare fiels @2-5D4731AD
    private TextField DATA = new TextField("DATA", "DATA");
    private TextField REPORT = new TextField("REPORT", "LINK");
    private TextField AUTORE = new TextField("AUTORE", "AUTORE");
    private TextField ID_RICHIESTA = new TextField("ID_RICHIESTA", "ID_RICHIESTA");
    private TextField NOTIFICATA = new TextField("NOTIFICATA", "NOTIFICATA");
    private TextField BEGIN_HIDE = new TextField("BEGIN_HIDE", "BEGIN_HIDE");
    private LongField NOTIFICATO = new LongField("NOTIFICATO", "NOTIFICATO");
    private LongField STAMPA = new LongField("STAMPA", "STAMPA");
    private TextField END_HIDE = new TextField("END_HIDE", "END_HIDE");
    private TextField LINK = new TextField("LINK", "");
//End RICHIESTE_STAMPARow: declare fiels

//RICHIESTE_STAMPARow: constructor @2-A30F3A4F
    public RICHIESTE_STAMPARow() {
    }
//End RICHIESTE_STAMPARow: constructor

//RICHIESTE_STAMPARow: method(s) of DATA @4-28E61A6F
    public TextField getDATAField() {
        return DATA;
    }

    public String getDATA() {
        return DATA.getValue();
    }

    public void setDATA(String value) {
        this.DATA.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of DATA

//RICHIESTE_STAMPARow: method(s) of REPORT @55-5621B940
    public TextField getREPORTField() {
        return REPORT;
    }

    public String getREPORT() {
        return REPORT.getValue();
    }

    public void setREPORT(String value) {
        this.REPORT.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of REPORT

//RICHIESTE_STAMPARow: method(s) of AUTORE @49-583F757A
    public TextField getAUTOREField() {
        return AUTORE;
    }

    public String getAUTORE() {
        return AUTORE.getValue();
    }

    public void setAUTORE(String value) {
        this.AUTORE.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of AUTORE

//RICHIESTE_STAMPARow: method(s) of ID_RICHIESTA @51-1BA7EEE1
    public TextField getID_RICHIESTAField() {
        return ID_RICHIESTA;
    }

    public String getID_RICHIESTA() {
        return ID_RICHIESTA.getValue();
    }

    public void setID_RICHIESTA(String value) {
        this.ID_RICHIESTA.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of ID_RICHIESTA

//RICHIESTE_STAMPARow: method(s) of NOTIFICATA @52-2314858D
    public TextField getNOTIFICATAField() {
        return NOTIFICATA;
    }

    public String getNOTIFICATA() {
        return NOTIFICATA.getValue();
    }

    public void setNOTIFICATA(String value) {
        this.NOTIFICATA.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of NOTIFICATA

//RICHIESTE_STAMPARow: method(s) of BEGIN_HIDE @57-BC057994
    public TextField getBEGIN_HIDEField() {
        return BEGIN_HIDE;
    }

    public String getBEGIN_HIDE() {
        return BEGIN_HIDE.getValue();
    }

    public void setBEGIN_HIDE(String value) {
        this.BEGIN_HIDE.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of BEGIN_HIDE

//RICHIESTE_STAMPARow: method(s) of NOTIFICATO @11-166852F9
    public LongField getNOTIFICATOField() {
        return NOTIFICATO;
    }

    public Long getNOTIFICATO() {
        return NOTIFICATO.getValue();
    }

    public void setNOTIFICATO(Long value) {
        this.NOTIFICATO.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of NOTIFICATO

//RICHIESTE_STAMPARow: method(s) of STAMPA @50-7AFA4D29
    public LongField getSTAMPAField() {
        return STAMPA;
    }

    public Long getSTAMPA() {
        return STAMPA.getValue();
    }

    public void setSTAMPA(Long value) {
        this.STAMPA.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of STAMPA

//RICHIESTE_STAMPARow: method(s) of END_HIDE @58-F7FC088F
    public TextField getEND_HIDEField() {
        return END_HIDE;
    }

    public String getEND_HIDE() {
        return END_HIDE.getValue();
    }

    public void setEND_HIDE(String value) {
        this.END_HIDE.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of END_HIDE

//RICHIESTE_STAMPARow: method(s) of LINK @61-E8490594
    public TextField getLINKField() {
        return LINK;
    }

    public String getLINK() {
        return LINK.getValue();
    }

    public void setLINK(String value) {
        this.LINK.setValue(value);
    }
//End RICHIESTE_STAMPARow: method(s) of LINK

//RICHIESTE_STAMPARow: isDeleted @$id-FBA9727F
    boolean rowIsApply = true;
    boolean rowIsNew;
    boolean rowIsDeleted;

    public boolean isApply() { return rowIsApply; }
    public void setApply(boolean apply) { this.rowIsApply = apply; }

    public boolean isNew() { return rowIsNew; }
    public void setNew(boolean deleted) { this.rowIsNew = deleted; }

    public boolean isDeleted() { return rowIsDeleted; }
    public void setDeleted(boolean deleted) { this.rowIsDeleted = deleted; }
//End RICHIESTE_STAMPARow: isDeleted

//RICHIESTE_STAMPARow: primary key @$id-516AB1C1
    PK_RICHIESTE_STAMPA primaryKey;
//End RICHIESTE_STAMPARow: primary key

//RICHIESTE_STAMPARow: methods of primary key @$id-2639EEFC
    public PK_RICHIESTE_STAMPA getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey( PK_RICHIESTE_STAMPA primaryKey ) {
        this.primaryKey = primaryKey;
    }
//End RICHIESTE_STAMPARow: methods of primary key

//CCSCachedColumns @$id-0CB8C20D
    ArrayList CCSCachedColumns = new ArrayList();

    public ArrayList getCCSCachedColumns() {
        return CCSCachedColumns;
    }

    public void setCCSCachedColumns(ArrayList columns) {
        CCSCachedColumns = columns;
    }

    public com.codecharge.components.CachedColumn getCCSCachedColumn(String name) {
        com.codecharge.components.CachedColumn column = null;
        if (com.codecharge.util.StringUtils.isEmpty(name)) return column;
        for (int i = 0; i < CCSCachedColumns.size(); i++ ) {
            com.codecharge.components.CachedColumn c = (com.codecharge.components.CachedColumn) CCSCachedColumns.get(i);
            if ( name.equals(c.getName()) ) {
                column = c;
                break;
            }
        }
        return column;
    }
//End CCSCachedColumns

//RICHIESTE_STAMPARow: class tail @2-FCB6E20C
}
//End RICHIESTE_STAMPARow: class tail

