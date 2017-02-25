package source;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by lbrunori on 23/02/17.
 */
public class PantallaPrincipal {

    private JPanel panelPrincipal;
    private JButton iniciarSimulaciónButton;
    private JTextField tamanioCola;
    private JTextField alfa;
    private JTextField gamma;
    private JTextField n;
    private JTextField k;
    private JComboBox politica;
    private JTextField epsilon;
    private JTextField minutosASimular;
    private JTextField horasASimular;
    private JTextField diasASimular;
    private JRadioButton milisegundos;
    private JRadioButton segundos;
    private JRadioButton minutos;
    private JTextField temperatura;
    private ButtonGroup group = new ButtonGroup();

    public PantallaPrincipal() {

        milisegundos.setMnemonic(0);
        group.add(milisegundos);
        segundos.setMnemonic(1);
        group.add(segundos);
        minutos.setMnemonic(2);
        group.add(minutos);
        minutos.setSelected(true);
        tamanioCola.setText("8");
        alfa.setText("0.1");
        gamma.setText("0.85");
        epsilon.setText("0.2");
        temperatura.setText("0.5");
        temperatura.setEditable(false);
        n.setText("100");
        k.setText("0.25");
        minutosASimular.setText("0");
        horasASimular.setText("0");
        diasASimular.setText("30");
        politica.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if(politica.getSelectedIndex() == 0){
                    temperatura.setEditable(false);
                }else{
                    temperatura.setEditable(true);
                }
            }
        });
        iniciarSimulaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double tiempoASimular = getTiempoASimular();
                ControladorSimulacion simulacion = new ControladorSimulacion();
                simulacion.iniciarSimulacion(
                        Integer.valueOf(getTamanioCola().getText()),
                        tiempoASimular,
                        getPolitica().getSelectedItem().toString(),
                        Double.valueOf(getEpsilon().getText()),
                        Double.valueOf(getTemperatura().getText()),
                        Double.valueOf(getGamma().getText()),
                        Double.valueOf(getAlfa().getText()),
                        Integer.valueOf(getN().getText()),
                        Double.valueOf(getK().getText()));
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

    public double getTiempoASimular() {
        if(group.getSelection().getMnemonic() == 0){
            BigDecimal tiempoASimular = new BigDecimal((Integer.valueOf(diasASimular.getText()) * 24 * 60 * 60 * 1000) + (Integer.valueOf(horasASimular.getText()) * 60 * 60 * 1000) + (Integer.valueOf(minutosASimular.getText()) * 60 * 1000));
            System.out.println("Se simularan : " + tiempoASimular + " milisegundos.");
            return tiempoASimular.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        if(group.getSelection().getMnemonic() == 1){
            BigDecimal tiempoASimular = new BigDecimal((Integer.valueOf(diasASimular.getText()) * 24 * 60 * 60) + (Integer.valueOf(horasASimular.getText()) * 60 * 60) + (Integer.valueOf(minutosASimular.getText()) * 60));
            System.out.println("Se simularan : " + tiempoASimular + " segundos.");
            return tiempoASimular.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        if(group.getSelection().getMnemonic() == 2){
            BigDecimal tiempoASimular = new BigDecimal((Integer.valueOf(diasASimular.getText()) * 24 * 60 ) + (Integer.valueOf(horasASimular.getText()) * 60) + (Integer.valueOf(minutosASimular.getText())));
            System.out.println("Se simularan : " + tiempoASimular + " minutos.");
            return tiempoASimular.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return 1000;
    }

    public JTextField getTemperatura() {
        return temperatura;
    }

    public void setTamanioCola(JTextField tamanioCola) {
        this.tamanioCola = tamanioCola;
    }

    public void setAlfa(JTextField alfa) {
        this.alfa = alfa;
    }

    public void setGamma(JTextField gamma) {
        this.gamma = gamma;
    }

    public void setN(JTextField n) {
        this.n = n;
    }

    public void setK(JTextField k) {
        this.k = k;
    }

    public void setPolitica(JComboBox politica) {
        this.politica = politica;
    }

    public void setEpsilon(JTextField epsilon) {
        this.epsilon = epsilon;
    }

    public void setMinutosASimular(JTextField minutosASimular) {
        this.minutosASimular = minutosASimular;
    }

    public void setHorasASimular(JTextField horasASimular) {
        this.horasASimular = horasASimular;
    }

    public void setDiasASimular(JTextField diasASimular) {
        this.diasASimular = diasASimular;
    }

    public JTextField getTamanioCola() {
        return tamanioCola;
    }

    public JTextField getAlfa() {
        return alfa;
    }

    public JTextField getGamma() {
        return gamma;
    }

    public JTextField getN() {
        return n;
    }

    public JTextField getK() {
        return k;
    }

    public JComboBox getPolitica() {
        return politica;
    }

    public JTextField getEpsilon() {
        return epsilon;
    }

    public JTextField getMinutosASimular() {
        return minutosASimular;
    }

    public JTextField getHorasASimular() {
        return horasASimular;
    }

    public JTextField getDiasASimular() {
        return diasASimular;
    }

}
