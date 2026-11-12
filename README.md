# Calculadora MVC - Actividad PD3

Este repositorio contiene la implementación de una calculadora básica utilizando el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**, modificada para soportar nuevas operaciones matemáticas de forma escalable.

## Estrategia Arquitectónica: Incremento de Operaciones

Para cumplir con el requerimiento de incrementar las operaciones de la calculadora (suma, resta, etc.) manteniendo el patrón MVC, se evaluaron dos patrones de diseño de comportamiento:

### Análisis del Patrón Command
El patrón **Command** encapsula una petición como un objeto, lo que permite parametrizar clientes con diferentes peticiones, hacer cola o registrar las peticiones, y soportar operaciones que se pueden deshacer.
*   **Desventajas en este contexto:** Para una calculadora básica que no requiere un historial de operaciones ni la funcionalidad de "Deshacer/Rehacer" (Undo/Redo), el patrón Command resulta en **sobreingeniería**. Exige la creación de múltiples interfaces y roles estrictos (Command, ConcreteCommand, Invoker, Receiver), lo que aumenta innecesariamente la complejidad y la cantidad de clases del sistema.

### Estrategia Implementada: Patrón Strategy
Dado que los argumentos indican que el patrón Command no es conveniente por su complejidad excesiva para este caso de uso, se optó por implementar el **Patrón Strategy**.
*   **Justificación:** Strategy permite definir una familia de algoritmos (las operaciones matemáticas), encapsular cada uno y hacerlos intercambiables. 
*   **Ventajas sobre Command:**
    1.  **Simplicidad:** Se enfoca puramente en intercambiar el algoritmo (la operación) en tiempo de ejecución sin el peso estructural de un Invocador o un Receptor de historial.
    2.  **Principio Abierto/Cerrado (OCP):** Para agregar una nueva operación (ej. Raíz, Potencia), solo se necesita crear una nueva clase que implemente la interfaz `IOperation` y agregar el botón en la Vista. El Modelo y el Controlador permanecen intactos.
    3.  **Bajo acoplamiento:** El Controlador inyecta la estrategia concreta (ej. `MultiplyOperation`, `AddOperation`) en un *Listener* genérico, pasándola al Modelo para su ejecución.

## Diagrama de Clases

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