package src;
import java.math.BigDecimal;

public class CalcModel {
    private static final String INITIAL_VALUE = "1";
    private BigDecimal m_total; 

    public CalcModel() {
        reset();
    }

    public void reset() {
        m_total = new BigDecimal(INITIAL_VALUE);
    }

    public void executeOperation(IOperation operation, String operand) {
        BigDecimal opValue = new BigDecimal(operand.trim());
        m_total = operation.execute(m_total, opValue);
    }

    public void setValue(String value) {
        m_total = new BigDecimal(value.trim());
    }

    public String getValue() {
        return m_total.toPlainString();
    }
}