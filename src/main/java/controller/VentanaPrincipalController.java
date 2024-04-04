package controller;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import model.Funcion;
import java.util.ArrayList;

public class VentanaPrincipalController {
    @FXML
    TextField compX;
    @FXML
    TextField compY;
    @FXML
    TextField compZ;
    @FXML
    TextField valorLim;
    @FXML
    Label resultLabel;

    public void calcularLimiteVectorial(){
        double valorLimi= Double.parseDouble(this.valorLim.getText());
        String compX = calcularLimiteIndividual(this.compX.getText(),valorLimi,"x");
        String compY = calcularLimiteIndividual(this.compY.getText(),valorLimi,"y");
        String compZ = calcularLimiteIndividual(this.compZ.getText(),valorLimi,"z");
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add(compX);
        resultados.add(compY);
        resultados.add(compZ);
        String resultadoFinal="";
        if(resultados.contains("NaN")||resultados.contains("infinito")){
           if(resultados.get(0).contains("NaN" )|| resultados.get(0).contains("infinito")){
               resultadoFinal="El limite no existe porque el límite en X no existe";
           } else if (resultados.get(1).contains("NaN")||  resultados.get(1).contains("infinito")) {
               resultadoFinal="El limite no existe porque el límite en Y no existe";
           } else if (resultados.get(2).contains("NaN")|| resultados.get(2).contains("infinito")) {
               resultadoFinal="El limite no existe porque el limite en Z no existe";
           }
        }
        else {
            resultadoFinal=compX+" , "+compY+" , "+compZ;
        }
        resultLabel.setText(resultadoFinal);
    }
    public String calcularLimiteIndividual(String x, double valor, String componente){
        Funcion propia= new Funcion(x, valor, componente);
        Funcion izq = new Funcion(x,valor-1, componente);
        Funcion der= new Funcion(x, valor+1,componente);
        double valorIzq= izq.getResultado();
        double valorDer= der.getResultado();
        double valorParada= Math.abs(valorIzq-valorDer);
        int count=0;
        while(Math.abs(valorDer-valorIzq)>= 0.0001){
            System.out.println(""+propia.getResultado());
            if((""+propia.getResultado()).contains("Infinity")){
                return("infinito");
            } else if ((""+propia.getResultado()).contains("NaN")) {
                return("NaN");
            }
            System.out.println(""+propia.getResultado());
            izq.setValorX((izq.getValorX()+valor)/2);
            valorIzq= izq.getResultado();
            der.setValorX((der.getValorX()+valor)/2);
            valorDer= der.getResultado();
            count+=1;
            if(count>6009&& Math.abs(valorDer-valorIzq)>valorParada){
                return "N/E";
            }
        }
        return (String.format("%.5f",valorDer));
    }
}
