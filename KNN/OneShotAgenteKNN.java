package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.lang.Math;
import java.util.*;

public class OneShotAgenteKNN extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    public void action() {
        System.out.println("Agent's action method executed");
		PrivateImpl impl = new PrivateImpl();
		impl.regressionKNN();

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
    public void regressionKNN() {
         System.out.println("Iniciando Algoritmo KNN ...");
		 double sumax=0, sumay=0, sumaxy=0,sumaxCuadrada=0, sumayCuadrada=0;
		 double promedioax=0, promedioay=0;
		 double sumdifax2xmean=0, sumdifay2ymean=0;
		 double stdevax=0, stdevay=0;
		 double B0=0, B1=0, B2=0, B3=0, B4=0,B5=0;
		 /* 
		 tamaño de K, aqui debemos modificar cuantos K necesitamos, desde 1 hasta sqrt de tamaño de arreglo x "The optimal K value usually found is the square root of N, where N is the total number of samples."
		 */
		 int k= 3;
		 /* 
		 tamaño de K 
		 */
		 /*	creacion de arreglo para almacenar vecinos mas cercanos basado en tamaño de K*/
		 String arreglovecinoscercanos[] = new String [k];
		 /* fin de arreglo vecinos cercanos */
		 
		 //double arregloDistanciasnormalizadas[] = new double [100];
		 double arregloxnormalizado[] = new double [100];
		 double arregloynormalizado[] = new double [100];
		 double arreglorankresult[] = new double [100];     //clase setosa=clase1, virginica=clase2, versicolor=clase3
		 int iclaseresultante = 0;
		 //double x4newtry=0, y4newtry=0;		//default iniar en cero, pero se comenta ya que tenemos ya un requerimiento de flor nueva x=5.2, y=3.1
		 //double x4newtry=5.2, y4newtry=3.1;		//nueva flor a clasificar
		 //double x4newtry=5.5, y4newtry=2.4;		//nueva flor a clasificar
	  //double x4newtry=5.2, y4newtry=3.1;		//nueva flor a clasificar setosa
	  //otros valores a predecir o clasificar son 5.5 y 2.4 versicolor
	  //otros valores a predecir o clasificar son 5.5 y 2.4 versicolor
		 
		 double x4newtry=7.3, y4newtry=2.9;		//nueva flor a clasificar
		 double x4newtrynormalizada=0, y4newtrynormalizada=0;		//nueva flor se normaliza mas adelante

		  //Declaramos y poblamos los arreglos
		  double arreglox1[];
	      arreglox1 = new double []{ 5.3,5.1,7.2,5.4,5.1,5.4,7.4,6.1,7.3,6.0,5.8,6.3,5.1,6.3,5.5 };
		  
		  double arregloDistancias[] = new double [arreglox1.length];
		  double arregloDistanciasnormalizadas[] = new double [arreglox1.length];

	      double arregloy[];
	      arregloy = new double []{ 3.7,3.8,3.0,3.4,3.3,3.9,2.8,2.8,2.9,2.7,2.8,2.3,2.5,2.5,2.4 };
		  
		  //clase setosa=clase1, virginica=clase2, versicolor=clase3
		  int arregloclase[];
		  arregloclase = new int []{ 1,1,2,1,1,1,2,3,2,3,2,3,3,3,3 };
		  String arregloclaseString[] = {"setosa","setosa","virginica","setosa","setosa","setosa","virginica","versicolor","virginica","versicolor","virginica","versicolor","versicolor","versicolor","versicolor"};
		  //Checar cuantos arreglos X y Y no son vacios o nulos
		  if (arreglox1!=null){
			  System.out.println("estoy dentro de arreglox1 not null");
			
			for (int icnt=0; icnt<arreglox1.length;icnt++){
				sumax=sumax+arreglox1[icnt];
			}
			promedioax=sumax/arreglox1.length;
			for (int icnt=0; icnt<arreglox1.length;icnt++){
				sumdifax2xmean=sumdifax2xmean+((arreglox1[icnt]-promedioax)*(arreglox1[icnt]-promedioax));
			}			
			
			stdevax=Math.sqrt(sumdifax2xmean/arreglox1.length); 
			System.out.println("Promedio Arreglo X= "+ promedioax);
			System.out.println("STDEV Arreglo X= "+ stdevax);
			
			//en esta linea normalizamos arreglox
			for (int icnt=0; icnt<arreglox1.length;icnt++){
				arregloxnormalizado[icnt]= (arreglox1[icnt]-promedioax)/stdevax;
				System.out.println("Arreglo X normalizado= "+ arregloxnormalizado[icnt]);
			}			
			
			
		  }



		  if (arregloy!=null){
			  System.out.println("estoy dentro de arregloy not null");
			for (int icnt=0; icnt<arregloy.length;icnt++){
				sumay=sumay+arregloy[icnt];
			}
			promedioay=sumay/arregloy.length;
			for (int icnt=0; icnt<arregloy.length;icnt++){
				sumdifay2ymean=sumdifay2ymean+((arregloy[icnt]-promedioay)*(arregloy[icnt]-promedioay));
			}			
			
			stdevay=Math.sqrt(sumdifay2ymean/arregloy.length); 
			System.out.println("Promedio Arreglo Y= "+ promedioay);
			System.out.println("STDEV Arreglo Y= "+ stdevay);
			
			//en esta linea normalizamos arregloy
			for (int icnt=0; icnt<arregloy.length;icnt++){
				arregloynormalizado[icnt]= (arregloy[icnt]-promedioay)/stdevay;
				System.out.println("Arreglo Y normalizado= "+ arregloynormalizado[icnt]);
			}			
		  }		  
		  
			// Normalizar x y y de la nueva flor a clasificar
			x4newtrynormalizada=(x4newtry-promedioax)/stdevax; 
			y4newtrynormalizada=(y4newtry-promedioay)/stdevay;
			System.out.println("X nueva flor normalizada= "+ x4newtrynormalizada);
			System.out.println("Y nueva flor normalizada= "+ y4newtrynormalizada);
			
			/// distancias para nueva flor, datos de nueva flor ya normalizada y medicion de distancias hacia arreglo de ya normalizadas.
			for (int icnt=0; icnt<arreglox1.length;icnt++){
				arregloDistancias[icnt]=Math.sqrt(((x4newtrynormalizada-arregloxnormalizado[icnt])*(x4newtrynormalizada-arregloxnormalizado[icnt]))+((y4newtrynormalizada-arregloynormalizado[icnt])*(y4newtrynormalizada-arregloynormalizado[icnt])));
				System.out.println("Arreglo Distancias normalizado= "+ arregloDistancias[icnt]);
			}

			//hacer bubblesort para ordenar de la distancia mas pequeña hasta la mas alta, al mismo tiempo ordenar el arreglo de especie o clase. 
			for (int i = 0; i < arregloDistancias.length - 1; i++){
				for (int j = 0; j < arregloDistancias.length - i - 1; j++){
					if (arregloDistancias[j] > arregloDistancias[j + 1]) {
						// swap arr[j+1] and arr[j]
						double temp = arregloDistancias[j];
						String tempstr = arregloclaseString[j];
						arregloDistancias[j] = arregloDistancias[j + 1];
						arregloclaseString[j] = arregloclaseString[j + 1];
						arregloDistancias[j + 1] = temp;
						arregloclaseString[j + 1] = tempstr;
					}
				}
			}
			for (int i = 0; i < arregloDistancias.length; ++i){
            System.out.print(arregloDistancias[i] + " ");
			
			}
			System.out.println();
			for (int i = 0; i < arregloclaseString.length; ++i){
            System.out.println(arregloclaseString[i] + " ");
			
			}
			System.out.println();			
			
			
			//llenar el arreglo con los vecinos mas cercanos basado en el tamaño de K
			for (int i=0;i<k;i++){
				arreglovecinoscercanos[i]=arregloclaseString[i];
			}
			//despliega arreglo vecinos cercanos 
			for (int i=0;i<arreglovecinoscercanos.length;i++){
				System.out.println("Arreglo vecinos cercanos = "+arreglovecinoscercanos[i]);
			}		
			// determinar del arreglo de vecinos mas cercanos cual es la clase o especie mas comun para ese clase asignarla a la nueva flor
			String sol = findWord(arreglovecinoscercanos);
			System.out.println();
			System.out.println("***************************************************Solucion***************************************************");
		    System.out.println("Nueva Flor x= "+x4newtry+" y= "+y4newtry+" Para K= "+k+" Clase asignada a nueva flor = "+sol);
			System.out.println("***************************************************Solucion***************************************************");
	  
	  
	  //double x4newtry=5.2, y4newtry=3.1;		//nueva flor a clasificar setosa
	  //otros valores a predecir o clasificar son 5.5 y 2.4 versicolor
	  //otros valores a predecir o clasificar son 5.5 y 2.4 versicolor
	  

		  
		  
		  
    }
	
	
	static String findWord(String[] arr)
	{

		// Create HashMap to store word and it's frequency
		HashMap<String, Integer> hs = new HashMap<String, Integer>();

		// Iterate through array of words
		for (int i = 0; i < arr.length; i++) {
			// If word already exist in HashMap then increase it's count by 1
			if (hs.containsKey(arr[i])) {
				hs.put(arr[i], hs.get(arr[i]) + 1);
			}
			// Otherwise add word to HashMap
			else {
				hs.put(arr[i], 1);
			}
		}

		// Create set to iterate over HashMap
		Set<Map.Entry<String, Integer> > set = hs.entrySet();
		String key = "";
		int value = 0;

		for (Map.Entry<String, Integer> me : set) {
			// Check for word having highest frequency
			if (me.getValue() > value) {
				value = me.getValue();
				key = me.getKey();
			}
		}

		// Return word having highest frequency
		return key;
	}	

	
	

}
