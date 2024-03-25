package model;
import org.nfunk.jep.JEP;
import lombok.NoArgsConstructor;
public class Funcion {
    private String funcion="";
    private double valorX;
    private double resultado;
    private String componente;

    public Funcion(String funcion, double valorX, String componente){
        this.funcion= funcion;
        this.valorX= valorX;
        this.componente= componente;
    }
    public Funcion(){

    }
    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public double getValorX() {
        return valorX;
    }

    public void setValorX(double valorX) {
        this.valorX = valorX;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public double getResultado() {

        JEP jep = new JEP();
        jep.addStandardConstants();
        jep.addStandardFunctions();
        jep.addVariable(this.componente, this.valorX);
        jep.parseExpression(this.funcion);
        this.resultado= jep.getValue();
        return  resultado;
    }
        /*public void setResultado() {
        JEP jep = new JEP();
        jep.addStandardConstants();
        jep.addStandardFunctions();
        jep.addVariable(this.componente, this.valorX);
        jep.parseExpression(this.funcion);
        this.resultado = jep.getValue();
    }*/
}
