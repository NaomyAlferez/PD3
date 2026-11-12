package src;
import java.math.BigDecimal;

public interface IOperation {
    BigDecimal execute(BigDecimal currentTotal, BigDecimal operand);
}