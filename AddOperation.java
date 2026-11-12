import java.math.BigDecimal;

public class AddOperation implements IOperation {
    @Override
    public BigDecimal execute(BigDecimal currentTotal, BigDecimal operand) {
        return currentTotal.add(operand);
    }
}