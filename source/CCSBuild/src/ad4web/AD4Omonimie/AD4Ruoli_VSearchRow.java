//AD4Ruoli_VSearchRow: import @132-71960620
package ad4web.AD4Omonimie;

import java.util.*;
import com.codecharge.db.*;
//End AD4Ruoli_VSearchRow: import

//AD4Ruoli_VSearchRow: class head @132-67EE2AB8
public class AD4Ruoli_VSearchRow {
//End AD4Ruoli_VSearchRow: class head

//AD4Ruoli_VSearchRow: declare fiels @132-982E1059
    private LongField s_daignorare = new LongField("s_daignorare", "s_daignorare");
    private LongField s_unificati = new LongField("s_unificati", "s_unificati");
    private LongField s_copiati = new LongField("s_copiati", "s_copiati");
//End AD4Ruoli_VSearchRow: declare fiels

//AD4Ruoli_VSearchRow: constructor @132-A113A82C
    public AD4Ruoli_VSearchRow() {
    }
//End AD4Ruoli_VSearchRow: constructor

//AD4Ruoli_VSearchRow: method(s) of s_daignorare @134-EC606DAA
    public LongField getS_daignorareField() {
        return s_daignorare;
    }

    public Long getS_daignorare() {
        return s_daignorare.getValue();
    }

    public void setS_daignorare(Long value) {
        this.s_daignorare.setValue(value);
    }
//End AD4Ruoli_VSearchRow: method(s) of s_daignorare

//AD4Ruoli_VSearchRow: method(s) of s_unificati @141-4C021FB7
    public LongField getS_unificatiField() {
        return s_unificati;
    }

    public Long getS_unificati() {
        return s_unificati.getValue();
    }

    public void setS_unificati(Long value) {
        this.s_unificati.setValue(value);
    }
//End AD4Ruoli_VSearchRow: method(s) of s_unificati

//AD4Ruoli_VSearchRow: method(s) of s_copiati @142-77F0DC1A
    public LongField getS_copiatiField() {
        return s_copiati;
    }

    public Long getS_copiati() {
        return s_copiati.getValue();
    }

    public void setS_copiati(Long value) {
        this.s_copiati.setValue(value);
    }
//End AD4Ruoli_VSearchRow: method(s) of s_copiati

//AD4Ruoli_VSearchRow: class tail @132-FCB6E20C
}
//End AD4Ruoli_VSearchRow: class tail

