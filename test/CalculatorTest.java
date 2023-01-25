import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.example.calculator.CalculatorModel;

public class CalculatorTest {

    @Test
    public void testAdd() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(1);
        model.setOperand2(2);
        model.add();
        assertEquals(3, model.getResult());
    }

    @Test
    public void testSubtract() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(1);
        model.setOperand2(2);
        model.subtract();
        assertEquals(-1, model.getResult());
    }

    @Test
    public void testMultiply() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(1);
        model.setOperand2(2);
        model.multiply();
        assertEquals(2, model.getResult());
    }

    @Test
    public void testDivide() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(1);
        model.setOperand2(2);
        model.divide();
        assertEquals(0.5, model.getResult());
    }

    @Test
    public void testExponent() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(2);
        model.setOperand2(3);
        model.exponent();
        assertEquals(8, model.getResult());
    }

    @Test
    public void testModulo() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(5);
        model.setOperand2(2);
        model.modulo();
        assertEquals(1, model.getResult());
    }

    @Test
    public void testFloorDivision() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(5);
        model.setOperand2(2);
        model.floorDivision();
        assertEquals(2, model.getResult());
    }

    @Test
    public void testGgt() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(5);
        model.setOperand2(2);
        model.ggt();
        assertEquals(1, model.getResult());
    }

    @Test
    void testDivideByZero() {
        CalculatorModel model = new CalculatorModel();
        model.setOperand1(1);
        model.setOperand2(0);
        model.divide();
        // 0 is the default value
        assertEquals(0, model.getResult());
    }
}
