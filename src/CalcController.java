package src;
import java.awt.event.*;

public class CalcController {
    private CalcModel m_model;
    private CalcView  m_view;

    CalcController(CalcModel model, CalcView view) {
        m_model = model;
        m_view  = view;

        // Inyectamos las estrategias al Listener genérico
        view.addMultiplyListener(new OperationListener(new MultiplyOperation()));
        view.addAddListener(new OperationListener(new AddOperation()));
        view.addClearListener(new ClearListener());
    }

    class OperationListener implements ActionListener {
        private IOperation operation;

        public OperationListener(IOperation op) {
            this.operation = op;
        }

        public void actionPerformed(ActionEvent e) {
            String userInput = "";
            try {
                userInput = m_view.getUserInput();
                m_model.executeOperation(operation, userInput);
                m_view.setTotal(m_model.getValue());
            } catch (NumberFormatException nfex) {
                m_view.showError("Bad input: '" + userInput + "'");
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            m_model.reset();
            m_view.reset();
        }
    }
}