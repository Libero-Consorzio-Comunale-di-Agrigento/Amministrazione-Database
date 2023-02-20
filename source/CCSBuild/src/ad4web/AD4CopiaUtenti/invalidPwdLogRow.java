//invalidPwdLogRow: import @245-057DA08A
package ad4web.AD4CopiaUtenti;

import java.util.*;
import com.codecharge.db.*;
//End invalidPwdLogRow: import

//invalidPwdLogRow: class head @245-388BC22D
public class invalidPwdLogRow {
//End invalidPwdLogRow: class head

//invalidPwdLogRow: declare fiels @245-C22ACBCB
    private LongTextField INVALID_PWD_LOG = new LongTextField("INVALID_PWD_LOG", "INVALID_PWD_LOG");
//End invalidPwdLogRow: declare fiels

//invalidPwdLogRow: constructor @245-47FD8102
    public invalidPwdLogRow() {
    }
//End invalidPwdLogRow: constructor

//invalidPwdLogRow: method(s) of INVALID_PWD_LOG @246-11ACADD6
    public LongTextField getINVALID_PWD_LOGField() {
        return INVALID_PWD_LOG;
    }

    public String getINVALID_PWD_LOG() {
        return INVALID_PWD_LOG.getValue();
    }

    public void setINVALID_PWD_LOG(String value) {
        this.INVALID_PWD_LOG.setValue(value);
    }
//End invalidPwdLogRow: method(s) of INVALID_PWD_LOG

//invalidPwdLogRow: class tail @245-FCB6E20C
}
//End invalidPwdLogRow: class tail

