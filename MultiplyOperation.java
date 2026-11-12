import java.math.BigDecimal;

public class MultiplyOperation implements IOperation {
    @Override
    public BigDecimal execute(BigDecimal currentTotal, BigDecimal operand) {
        return currentTotal.multiply(operand);
    }
}