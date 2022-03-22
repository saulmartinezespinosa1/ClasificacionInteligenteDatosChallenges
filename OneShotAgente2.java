package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotAgente2 extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MySLROneShotBehaviour());
  } 

  private class MySLROneShotBehaviour extends OneShotBehaviour {

    public void action() {
        System.out.println("Agent's action method executed");
		PrivateImpl impl = new PrivateImpl();
		impl.regression();

    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
class PrivateImpl{
    int implementationData;
    //System.out.println("Hello World! estoy dentro de otra clase My name is");
    public void regression() {
         System.out.println("Iniciando Regresion...");
		 double sumax=0, sumay=0, sumaxy=0,sumaxCuadrada=0, sumayCuadrada=0;
		 double B0=0, B1=0, yPronosticada, xAPronosticar;
	      int arreglox[];
	      arreglox = new int []{ 1,2,3,4,5,6,7,8,9,10};
	      int arregloy[];
	      arregloy = new int []{ 2,4,6,8,10,12,14,16,18,20 };
	      int arregloxy[];
	      arregloxy = new int [10];
	      
	    //acceder a los elementos del array
	      for (int i = 0; i < arreglox.length; i++) {
	      System.out.println("Elemento en el indicex " + i + " : "+ arreglox[i]);
	      System.out.println("Elemento en el indicey " + i + " : "+ arregloy[i]);
	      sumax= sumax + arreglox[i];
	      sumay= sumay + arregloy[i];
	      sumaxy= sumaxy + (arreglox[i]*arregloy[i]);
	      sumaxCuadrada= sumaxCuadrada + (arreglox[i]*arreglox[i]);
	      sumayCuadrada= sumayCuadrada + (arregloy[i]*arregloy[i]);
	      }
	      
	      B0= ((sumay*sumaxCuadrada)-(sumax*sumaxy))/((arreglox.length*sumaxCuadrada)-(sumax*sumax));
	      B1=((arreglox.length*sumaxy)-(sumax*sumay))/((arreglox.length*sumaxCuadrada)-(sumax*sumax));
	      System.out.println("Suma de X= "+ sumax);
	      System.out.println("Suma de y= "+ sumay);
	      System.out.println("Suma de xy= "+ sumaxy);
	      System.out.println("Suma de x Cuadrada= "+ sumaxCuadrada);
	      System.out.println("Suma de y Cuadrada= "+ sumayCuadrada);
	      System.out.printf("B0= "+ "%.2f %n", B0);
	      System.out.printf("B1= "+ "%.2f %n", B1);
		  System.out.println("Final de Regresion...");
		  System.out.println("Pronosticar Y para X= 11 ");
		  xAPronosticar=11.0;
		  System.out.println("Con X= 11, El valor de Y pronosticado = "+ (B0+(xAPronosticar*B1)));
		  System.out.println("Pronosticar Y para X= 12 ");
		  xAPronosticar=12.0;
		  System.out.println("Con X= 12, El valor de Y pronosticado = "+ (B0+(xAPronosticar*B1)));
    }
}
