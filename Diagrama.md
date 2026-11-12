# Diagrama de Clases

```mermaid
classDiagram
    class CalcMVC {
        +main(String[] args)
    }
    class CalcModel {
        -String INITIAL_VALUE
        -BigDecimal m_total
        +reset()
        +executeOperation(IOperation operation, String operand)
        +setValue(String value)
        +getValue() String
    }
    class CalcView {
        -JTextField m_userInputTf
        -JTextField m_totalTf
        -JButton m_multiplyBtn
        -JButton m_addBtn
        -JButton m_clearBtn
        +getUserInput() String
        +setTotal(String newTotal)
        +showError(String errMessage)
        +addMultiplyListener(ActionListener al)
        +addAddListener(ActionListener al)
        +addClearListener(ActionListener al)
    }
    class CalcController {
        -CalcModel m_model
        -CalcView m_view
        +CalcController(CalcModel, CalcView)
    }
    class IOperation {
        <<interface>>
        +execute(BigDecimal current, BigDecimal operand) BigDecimal
    }
    class MultiplyOperation {
        +execute(BigDecimal current, BigDecimal operand) BigDecimal
    }
    class AddOperation {
        +execute(BigDecimal current, BigDecimal operand) BigDecimal
    }

    CalcMVC --> CalcModel : Instancia
    CalcMVC --> CalcView : Instancia
    CalcMVC --> CalcController : Instancia
    CalcController --> CalcModel : Modifica
    CalcController --> CalcView : Actualiza / Lee
    CalcController ..> IOperation : Inyecta
    CalcModel ..> IOperation : Ejecuta
    IOperation <|.. MultiplyOperation : Implementa
    IOperation <|.. AddOperation : Implementa