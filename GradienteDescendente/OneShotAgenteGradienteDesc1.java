package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.lang.Math;
import java.util.*;

public class OneShotAgenteGradienteDesc1 extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    public void action() {
        System.out.println("Agent's action method executed");
		PrivateImpl impl = new PrivateImpl();
		impl.CalculaDescensoGradiente();

    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    
}

		class Dataset{
		private int[] x = {23,26,30,34,43,48,52,57,58};
		private int[] y = {651,762,856,1063,1190,1298,1421,1440,1518};
		
		
			public int[] GetX()
			{
				return x;
			}
			public int[] GetY()
			{
				return y;
			}
					
	}

class PrivateImpl extends Dataset{
    int implementationData;
    int totaldeiteraciones;
	double m=9;											//Valor de m = a numero de elementos en x o y, es decir n 
	double error=0,hipotesisb=0,iteraciones=10000000;
	double b0derivada,b1derivada;
	double b0gradiente=1,b1gradiente=1,learningrate=0.001;
	double closest2zero=1e-2;
	// valor a clasificar o predecir Y
	int val2classify = 53;
	//valor a clasificar o predecir Y
	
    public void gradientedescendente(){

	}
			public double fcosto(int _x[], int _y[], double b0gradiente, double b1gradiente){
		
				double valorfcosto=0;
				double hipotesisfcosto=0;


				for (int i=0; i<9; i++)
				{
					hipotesisfcosto = b0gradiente+b1gradiente*_x[i];
					valorfcosto += (_y[i] - hipotesisfcosto)*(_y[i] - hipotesisfcosto);
					valorfcosto = valorfcosto / (2*m);
					valorfcosto = valorfcosto / (2*m);
					
				}
		
				return valorfcosto / (2*m);
			}
			
	
			public void CalculaDescensoGradiente()
			{
				for (int i=0; i<iteraciones; i++)
				{
					b1derivada=0;
					b0derivada=0;
			
					for (int j=0; j<9; j++)
					{
						hipotesisb = b0gradiente+b1gradiente*GetX()[j];
						b0derivada += hipotesisb - GetY()[j];
						b1derivada += (hipotesisb - GetY()[j]) * GetX()[j];
						error=0;
						error= fcosto(GetX(), GetY(), b0gradiente, b1gradiente);
						
						if(error<closest2zero)
						{
							break;
						}
					}
					
					b0gradiente -= (b0derivada / m) * learningrate;
					b1gradiente -= (b1derivada / m) * learningrate;
					totaldeiteraciones = i;
				
				}
	
			System.out.println("El valor de beta0 en Gradiente Descendente = " + b0gradiente);
			System.out.println("El valor de beta1 en Gradiente Descendente = " + b1gradiente);		
			System.out.println("El valor de error disminuido a casi CERO = " + error);
			System.out.println("El total de iteraciones = " + totaldeiteraciones);	
			System.out.println("");	
			System.out.println("");
			System.out.println("Para el Valor X = " + val2classify +" Y predicho =" + (b0gradiente+(b1gradiente*val2classify)));
	
			}

			
	

}
