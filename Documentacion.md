# Documentación de lo Implementado

Para cumplir con el requerimiento de escalar las operaciones de la calculadora (suma, resta, etc.) manteniendo el patrón MVC, se evaluó la estructura del código base y las alternativas de diseño.

## ¿Por qué se descartó el patrón Command?
La rúbrica sugiere evaluar el patrón de diseño **Command**. Este patrón es ideal cuando se necesita guardar un historial de peticiones o implementar funciones de "Deshacer/Rehacer" (Undo/Redo). Sin embargo, para esta calculadora básica, utilizar Command resultaría en **sobreingeniería**. Nos obligaría a crear demasiadas clases y roles estrictos (Invocador, Receptor, Comando Concreto) que añadirían una complejidad innecesaria al proyecto.

## Estrategia elegida: Patrón Strategy
Para mantener la simplicidad, evitar la sobreingeniería y respetar el patrón MVC, se implementó el **Patrón Strategy**. 

*   **Implementación:** Se creó una interfaz `IOperation` con un método `execute()`. Cada operación matemática (multiplicación, suma, etc.) es una clase independiente que implementa esta interfaz.
*   **Ventajas:** Cumple perfectamente con el Principio Abierto/Cerrado (Open/Closed Principle). Si en el futuro se requiere agregar una nueva operación, solo se debe crear una nueva clase para esa operación y agregar el botón en la Vista. El código del Modelo y del Controlador no sufren modificaciones, manteniendo un bajo acoplamiento.