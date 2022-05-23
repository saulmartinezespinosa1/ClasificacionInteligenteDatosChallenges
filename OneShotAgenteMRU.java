package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotAgenteMRU extends Agent {

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
         System.out.println("Iniciando Regresion MLR ...");
		 double sumax=0, sumay=0, sumaxy=0,sumaxCuadrada=0, sumayCuadrada=0;
		 double B0=0, B1=0, B2=0, B3=0, B4=0,B5=0;
		 int matrizJsize=0, matrizIsize=0;
		 double matrizX[][]= new double[100][100];
		 double matrizY[][]= new double[100][100];
		 double matrizXTranspuesta[][]= new double[100][100];
		 double matrizInvDeTranspByX[][]= new double[100][100];
		  //Declaramos y poblamos los arreglos
		  double arreglox1[];
	      arreglox1 = new double []{ 41.9,43.4,43.9,44.5,47.3,47.5,47.9,50.2,52.8,53.2,56.7,57,63.5,65.3,71.1,77,77.8 };
	      double arreglox2[];
	      arreglox2 = new double []{ 29.1,29.3,29.5,29.7,29.9,30.3,30.5,30.7,30.8,30.9,31.5,31.7,31.9,32,32.1,32.5,32.9 };	
	      double arreglox3[] = null;
	      //arreglox3 = new double []{ 23,26,30,34,43,48,52,57,58 };	
	      double arreglox4[]= null;
	      //arreglox4 = new double []{ 23,26,30,34,43,48,52,57,58 };
	      double arregloy[];
	      arregloy = new double []{ 251.3,251.3,248.3,267.5,273,276.5,270.3,274.9,285,290,297,302.5,304.5,309.3,321.7,330.7,349 };
	      //int arregloxy[];
	      //arregloxy = new int [10];

		  //Checar cuantos arreglos X y Y no son vacios o nulos
		  if (arreglox1!=null){
			  System.out.println("estoy dentro de arreglox1 not null");
			matrizJsize++;
			for (int icnt=0; icnt<arreglox1.length;icnt++){
				matrizX[icnt][0]=1;
				matrizX[icnt][1]=arreglox1[icnt];
			}
		  }
		  if (arreglox2!=null){
			  System.out.println("estoy dentro de arreglox2 not null");
			matrizJsize++;
			for (int icnt=0; icnt<arreglox2.length;icnt++){
				matrizX[icnt][2]=arreglox2[icnt];
			}
		  }
		  if (arreglox3!=null){
			  System.out.println("estoy dentro de arreglox3 not null");
			matrizJsize++;
			for (int icnt=0; icnt<arreglox3.length;icnt++){
				matrizX[icnt][3]=arreglox3[icnt];
			}
		  }
		  if (arreglox4!=null){
			  System.out.println("estoy dentro de arreglox4 not null");
			matrizJsize++;
			for (int icnt=0; icnt<arreglox4.length;icnt++){
				matrizX[icnt][4]=arreglox4[icnt];
			}
		  }
		  if (arregloy!=null){
			  System.out.println("estoy dentro de arregloy not null");
			for (int icnt=0; icnt<arreglox1.length;icnt++){
				matrizY[icnt][0]=arregloy[icnt];
				matrizY[icnt][1]=1;
			}
		  }		  
		  
		  System.out.println("Valor actual de MatrizJsize= "+matrizJsize);
		  //Checar todos los arreglos X no nulos son del mismo tamano


 
		  System.out.println("Desplegando valores de Matriz X");
		  for (int icnt=0;icnt<arreglox1.length;icnt++){
			  for (int jcnt=0;jcnt<=matrizJsize;jcnt++){
				  System.out.println("matriz x pos i= "+icnt+" matriz x pos j= "+jcnt+" matriz x pos i j valor= "+matrizX[icnt][jcnt]);
			  }
		  }
		  System.out.println("Haciendo transpuesta de Matriz X");
		  for (int icnt=0;icnt<arreglox1.length;icnt++){
			  for (int jcnt=0;jcnt<=matrizJsize;jcnt++){
				  //System.out.println("matriz x pos i= "+icnt+" matriz x pos j= "+jcnt+" matriz x pos i j valor= "+matrizX[icnt][jcnt]);
				  matrizXTranspuesta[jcnt][icnt]=matrizX[icnt][jcnt];
				/*
				xi xj tranxi tranxi
				0 0 0 0
				0 1 1 0
				0 2 2 0
				1 0 0 1
				1 1 1 1
				1 2 2 1
				2 0 0 2
				2 1 1 2
				2 2 2 2
				*/
				
				  
			  }
		  }	
		  System.out.println("Desplegando la transpuesta de Matriz X");
		  for (int icnt=0;icnt<=matrizJsize;icnt++){
			  for (int jcnt=0;jcnt<arreglox1.length;jcnt++){
				  //System.out.println("matriz x pos i= "+icnt+" matriz x pos j= "+jcnt+" matriz x pos i j valor= "+matrizX[icnt][jcnt]);
				  //matrizXTranspuesta[jcnt][icnt]=matrizX[icnt][jcnt];
				  System.out.println("matriz xtrans pos i= "+icnt+" matriz xtrans pos j= "+jcnt+" matriz xtrans pos i j valor= "+matrizXTranspuesta[icnt][jcnt]);
			  }
		  }			  
		    // Envia matrices y tamanos a multiplicar Matriz XTranspuesta * Matriz X
		    double resultMatrix[][] = matrixMultiplication(matrizXTranspuesta, matrizJsize+1, arreglox1.length, matrizX, arreglox1.length, matrizJsize+1);
			
			//despliega matriz producto resultante matriz Xtranspuesta por matriz X
			System.out.println();
			System.out.println("Resultado de Matriz XTranspuesta * Matriz X es:");
			for (int i = 0; i < resultMatrix.length; i++)
			{
				for (int j = 0;
					j < resultMatrix[i].length;
					j++)
				{
					System.out.print(resultMatrix[i][j] + "\t");
				}
				System.out.println();
			}
		  
		  
		  double resultMatrix1[][] =inversematrix(resultMatrix,resultMatrix.length);
		  
		  
		  
		  // Envia matrices y tamanos a multiplicar Matriz XTranspuesta * Matriz Y
		    double resultMatXtranspPorMatY[][] = matrixMultiplication(matrizXTranspuesta, matrizJsize+1, arreglox1.length, matrizY, arreglox1.length, 1);
			//despliega matriz producto resultante matriz Xtranspuesta por matriz y
			System.out.println();
			System.out.println("Resultado de Matrix Xtransp * Y es:");
			for (int i = 0; i < resultMatXtranspPorMatY.length; i++)
			{
				for (int j = 0;
					j < resultMatXtranspPorMatY[i].length;
					j++)
				{
					System.out.print(resultMatXtranspPorMatY[i][j] + "\t");
				}
				System.out.println();
			}

		  // Envia ultimas dos matrices y tamanos ((Xtransp*X)^-1)*(Xtransp*Y)
		    double resultMatFinal[][] = matrixMultiplication(resultMatrix1, resultMatrix1.length, resultMatrix1[0].length, resultMatXtranspPorMatY, resultMatXtranspPorMatY.length, resultMatXtranspPorMatY[0].length);
			//despliega matriz producto resultante matriz Xtranspuesta por matriz y
			System.out.println();
			System.out.println("Resulto Matriz Final es:");
			for (int i = 0; i < resultMatFinal.length; i++)
			{
				for (int j = 0;
					j < resultMatFinal[i].length;
					j++)
				{
					System.out.print(resultMatFinal[i][j] + "\t");
				}
				System.out.println();
			}			
		  System.out.println();
		  System.out.println("Final de Regresion...");
		  
		  System.out.println();
		  System.out.println("valores predichos...");
		  System.out.println();
		  System.out.println("Valor de Y' para x1= 82.5 y x2= 31.2 es= "+ ((resultMatFinal[0][0])+(resultMatFinal[1][0]*82.5)+(resultMatFinal[2][0]*31.2)));
		  System.out.println();
		  System.out.println("Valor de Y' para x1= 82.4 y x2= 32.3 es= "+ ((resultMatFinal[0][0])+(resultMatFinal[1][0]*82.4)+(resultMatFinal[2][0]*32.3)));
		  System.out.println();
		  System.out.println("Valor de Y' para x1= 81.2 y x2= 33.2 es= "+ ((resultMatFinal[0][0])+(resultMatFinal[1][0]*81.2)+(resultMatFinal[2][0]*33.2)));		  
		  
		  
    }
	public static double[][] matrixMultiplication(
		double[][] matrix1, int rows1, int cols1,
		double[][] matrix2, int rows2, int cols2)
		/*throws Exception*/
	{

		// Required condition for matrix multiplication
		if (cols1 != rows2) {
			//throw new Exception("Invalid matrix given.");
			System.out.println("tamano de matriz invalida");
		}

		// create a result matrix
		double resultMatrix[][] = new double[rows1][cols2];

		// Core logic for 2 matrices multiplication
		for (int i = 0; i < resultMatrix.length; i++)
		{
			for (int j = 0;
				j < resultMatrix[i].length;
				j++)
			{
				for (int k = 0; k < cols1; k++)
				{
					resultMatrix[i][j]
						+= matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		return resultMatrix;
	}
	
	public static double[][] inversematrix(
		double[][] matrix, int order)
		/*throws Exception*/
	{
		int iinvert,jinvert,kinvert,ninvert;
		ninvert=order;
		double Matrix2Invert[][] = new double[ninvert][ninvert];
		Matrix2Invert=matrix;
		double IdentMatrix[][] = new double[ninvert][ninvert];
		double ResultMatrix[][] = new double[ninvert][ninvert];
		double aux, pivote;
		for (iinvert=0;iinvert<ninvert;iinvert++){
			for (jinvert=0;jinvert<ninvert;jinvert++){
				if (iinvert==jinvert){
					IdentMatrix[iinvert][jinvert] = 1.0;
				}else{ 
				IdentMatrix[iinvert][jinvert] = 0.0;
				}
			}
		}
		/*despliega los valores de la matriz identidad inicializada*/
		/*for (iinvert=0;iinvert<ninvert;iinvert++){
			for (jinvert=0;jinvert<ninvert;jinvert++){
				System.out.println("valores: "+ IdentMatrix[iinvert][jinvert]);			
			}
		}*/		
		/*despliega los valores de la matriz identidad inicializada*/
		
		//reduccion por renglones
		for (iinvert=0;iinvert<ninvert;iinvert++){
			pivote=Matrix2Invert[iinvert][iinvert];
			//convertir el privote a 1 y aplicar la operación sobre toda la fila.
			for (kinvert=0;kinvert<ninvert;kinvert++){
				Matrix2Invert[iinvert][kinvert]=Matrix2Invert[iinvert][kinvert]/pivote;
				IdentMatrix[iinvert][kinvert]=IdentMatrix[iinvert][kinvert]/pivote;
			}
			for (jinvert=0;jinvert<ninvert;jinvert++){
				if (iinvert!=jinvert){//no esta en la diagonal
					aux=Matrix2Invert[jinvert][iinvert];
					for (kinvert=0;kinvert<ninvert;kinvert++){
						Matrix2Invert[jinvert][kinvert]=Matrix2Invert[jinvert][kinvert]-aux*Matrix2Invert[iinvert][kinvert];						//ver si no debe ser aux *A[j][k] en lugar de A[i][k]
						IdentMatrix[jinvert][kinvert]=IdentMatrix[jinvert][kinvert]-aux*IdentMatrix[iinvert][kinvert];						//ver si no debe ser aux *I[j][k] en lugar de I[i][k]
					}
				}
			}
		}
		
		/*despliega los valores de la matriz identidad ahora como matriz inversa*/
		/*for (iinvert=0;iinvert<ninvert;iinvert++){
			for (jinvert=0;jinvert<ninvert;jinvert++){
				System.out.println("valores: "+ IdentMatrix[iinvert][jinvert]);			
			}
		}*/
		/*despliega los valores de la matriz2invert ahora como matriz inversa*/
		/*for (iinvert=0;iinvert<ninvert;iinvert++){
			for (jinvert=0;jinvert<ninvert;jinvert++){
				System.out.println("valores: "+ Matrix2Invert[iinvert][jinvert]);			
			}
		}*/		
		
		return IdentMatrix;
	}
		

}





