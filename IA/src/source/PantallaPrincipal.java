package source;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lbrunori on 23/02/17.
 */
public class PantallaPrincipal {

    private JPanel panelPrincipal;
    private JButton iniciarSimulaciónButton;
    private JTextField textField1;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JTextField textField3;

    public PantallaPrincipal() {
        iniciarSimulaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorSimulacion simulacion = new ControladorSimulacion();
                simulacion.iniciarSimulacion(8,10, 0.9, 0.75, 0.95, 100, 0.25);
            }
        });
    }

    public void abrirVentana(){
     JFrame frame = new JFrame("Gestor de procesos");
     frame.setContentPane(new PantallaPrincipal().panelPrincipal);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.pack();
     frame.setVisible(true);
    }
}
