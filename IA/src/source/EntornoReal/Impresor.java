package source.EntornoReal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lbrunori on 11/02/17.
 */
public class Impresor {

    private static DefaultCategoryDataset datosProcesos = new DefaultCategoryDataset();
    private static ArrayList<Double> xTiempos = new ArrayList<>();
    private static ArrayList<Double> yRewards = new ArrayList<>();
    private static double rewardAcumulado = 0.0;

    private static int cantT1Aceptados = 0;
    private static int cantT2Aceptados = 0;
    private static int cantT3Aceptados = 0;
    private static int cantT4Aceptados = 0;
    private static int cantT5Aceptados = 0;

    private static int cantT1Rechazadas = 0;
    private static int cantT2Rechazadas = 0;
    private static int cantT3Rechazadas = 0;
    private static int cantT4Rechazadas = 0;
    private static int cantT5Rechazadas = 0;

    public static void acumularReward(double reward, double unidadesDeTiempo) {
        rewardAcumulado = rewardAcumulado + reward;
        xTiempos.add(unidadesDeTiempo);
        yRewards.add(rewardAcumulado);
    }

    public static double getRewardAcumulado(){
        return rewardAcumulado;
    }

    public static void aumentarCantT1Aceptadas(){
        cantT1Aceptados++;
    }
    public static void aumentarCantT2Aceptadas(){
        cantT2Aceptados++;
    }
    public static void aumentarCantT3Aceptadas(){
        cantT3Aceptados++;
    }
    public static void aumentarCantT4Aceptadas(){
        cantT4Aceptados++;
    }
    public static void aumentarCantT5Aceptadas(){
        cantT5Aceptados++;
    }

    public static void aumentarCantT1Rechazadas(){
        cantT1Rechazadas++;
    }
    public static void aumentarCantT2Rechazadas(){
        cantT2Rechazadas++;
    }

    public static void aumentarCantT3Rechazadas(){
        cantT3Rechazadas++;
    }

    public static void aumentarCantT4Rechazadas(){
        cantT4Rechazadas++;
    }

    public static void aumentarCantT5Rechazadas(){
        cantT5Rechazadas++;
    }

    public static void crearGraficosSobreProcesos(){
        datosProcesos.addValue(cantT1Aceptados, "Aceptados", "Procesos T1");
        datosProcesos.addValue(cantT1Rechazadas, "Rechazados", "Procesos T1");
        datosProcesos.addValue(cantT2Aceptados, "Aceptados", "Procesos T2");
        datosProcesos.addValue(cantT2Rechazadas, "Rechazados", "Procesos T2");
        datosProcesos.addValue(cantT3Aceptados, "Aceptados", "Procesos T3");
        datosProcesos.addValue(cantT3Rechazadas, "Rechazados", "Procesos T3");
        datosProcesos.addValue(cantT4Aceptados, "Aceptados", "Procesos T4");
        datosProcesos.addValue(cantT4Rechazadas, "Rechazados", "Procesos T4");
        datosProcesos.addValue(cantT5Aceptados, "Aceptados", "Procesos T5");
        datosProcesos.addValue(cantT5Rechazadas, "Rechazados", "Procesos T5");

        XYSeries seriesXY = new XYSeries("Reward Acumulado");
        for (int i = 0; i < xTiempos.size(); i++){
            seriesXY.add(xTiempos.get(i),yRewards.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesXY);
        NumberAxis domain = new NumberAxis("Tiempo");
        NumberAxis range = new NumberAxis("Reward");
        XYSplineRenderer r = new XYSplineRenderer(5);
        XYPlot xyplot = new XYPlot(dataset, domain, range, r);
        JFreeChart chart = new JFreeChart(xyplot);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );

        JFreeChart graficoProcesos = ChartFactory.createBarChart("Gráfico de admisión de procesos",
                "Tipo de proceso", "Cantidad", datosProcesos,
                PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panel = new ChartPanel(graficoProcesos);
        panel.setPreferredSize(new java.awt.Dimension( 1000 , 400 ) );
        JFrame ventana1 = new JFrame();
        JFrame ventana2 = new JFrame();
        ventana2.getContentPane().add(chartPanel);
        ventana1.getContentPane().add(panel);
        ventana1.pack();
        ventana2.pack();
        ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana1.setVisible(true);
        ventana2.setVisible(true);

    }

    public static int getCantT1Aceptados() {
        return cantT1Aceptados;
    }

    public static void setCantT1Aceptados(int cantT1Aceptados) {
        Impresor.cantT1Aceptados = cantT1Aceptados;
    }

    public static int getCantT2Aceptados() {
        return cantT2Aceptados;
    }

    public static void setCantT2Aceptados(int cantT2Aceptados) {
        Impresor.cantT2Aceptados = cantT2Aceptados;
    }

    public static int getCantT3Aceptados() {
        return cantT3Aceptados;
    }

    public static void setCantT3Aceptados(int cantT3Aceptados) {
        Impresor.cantT3Aceptados = cantT3Aceptados;
    }

    public static int getCantT4Aceptados() {
        return cantT4Aceptados;
    }

    public static void setCantT4Aceptados(int cantT4Aceptados) {
        Impresor.cantT4Aceptados = cantT4Aceptados;
    }

    public static int getCantT5Aceptados() {
        return cantT5Aceptados;
    }

    public static void setCantT5Aceptados(int cantT5Aceptados) {
        Impresor.cantT5Aceptados = cantT5Aceptados;
    }

    public static int getCantT1Rechazadas() {
        return cantT1Rechazadas;
    }

    public static void setCantT1Rechazadas(int cantT1Rechazadas) {
        Impresor.cantT1Rechazadas = cantT1Rechazadas;
    }

    public static int getCantT2Rechazadas() {
        return cantT2Rechazadas;
    }

    public static void setCantT2Rechazadas(int cantT2Rechazadas) {
        Impresor.cantT2Rechazadas = cantT2Rechazadas;
    }

    public static int getCantT3Rechazadas() {
        return cantT3Rechazadas;
    }

    public static void setCantT3Rechazadas(int cantT3Rechazadas) {
        Impresor.cantT3Rechazadas = cantT3Rechazadas;
    }

    public static int getCantT4Rechazadas() {
        return cantT4Rechazadas;
    }

    public static void setCantT4Rechazadas(int cantT4Rechazadas) {
        Impresor.cantT4Rechazadas = cantT4Rechazadas;
    }

    public static int getCantT5Rechazadas() {
        return cantT5Rechazadas;
    }

    public static void setCantT5Rechazadas(int cantT5Rechazadas) {
        Impresor.cantT5Rechazadas = cantT5Rechazadas;
    }

    public static void imprimirEstadoPrograma(int segundosEnReloj, String estadoCPU, String estadoColaProcesamiento){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("resultado.txt", true))) {

            System.out.println("");
            bw.write("\n");
            System.out.println("Tiempo del reloj: " + segundosEnReloj + " ms");
            bw.write("Tiempo del reloj: " + segundosEnReloj + " ms \n");
            System.out.println("--------------------");
            bw.write("-------------------- \n");
            System.out.println("Estado de la cola");
            bw.write("Estado de la cola \n");
            System.out.println(estadoColaProcesamiento);
            bw.write(estadoColaProcesamiento + "\n");
            System.out.println("--------------------");
            bw.write("-------------------- \n");
            System.out.println("Estado del CPU ");
            bw.write("Estado del CPU \n");
            System.out.println(estadoCPU);
            bw.write(estadoCPU + "\n");


        } catch (IOException e) {

            e.printStackTrace();

        }
    }

}

