package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotAgente extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

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
		 double B0=0, B1=0;
	      int arreglox[];
	      arreglox = new int []{ 23,26,30,34,43,48,52,57,58 };
	      int arregloy[];
	      arregloy = new int []{ 651,762,856,1063,1190,1298,1421,1440,1518 };
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
    }
}
