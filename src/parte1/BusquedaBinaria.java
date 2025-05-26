package parte1;

import java.util.Random; 

public class BusquedaBinaria {

	public static int[][] generateOrderedMatrix(int m, int n) {
		Random rand = new Random();
		int[][] matrix = new int[m][n];
		int value = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				value +=rand.nextInt(9)+1; //Inc. aleatorio entre 1 y 10
				matrix[i][j] = value;
			}
		}
		return matrix;
	}

	/* caso mejor array[0]
	   caso peor array[b]
	   caso promedio array[random(n)]
	 */
	public static int[] getCoordinates(int i, int ancho) {//matrices CUADRADAS
		//Pide índice 2d, y ancho de la matriz, devuelve fila y columna

		//int fila = (int) Math.floor((float) i/ancho);
		int fila = i/ancho; // división entera
		int columna = i % ancho; //resto de la división
		int[] coordenadas = {fila, columna};
		return coordenadas;
	}
	/*public int[] getCoordinates(int fila, int columna, int ancho) {//matrices CUADRADAS, al contrario de la función anterior
		//pide fila y columna y devuelve índice
		int i =  fila*ancho + columna;
	}*/


	public static int[] binaryMatSearch(int[][] matrix, int target) {
		// 1. Calcular el número de filas
		// 2. Calcular el número de columnas
		int m = matrix.length; //filas
		int n = matrix[0].length; //columnas
		int counter = 0;
		int[] resultado = {-1, -1, counter};

		// 3. Establecer el índice inicial como 0
		int inicio = 0;

		// 4. Establecer el último índice como la posición del último elemento
		int ultimoElemento =  m*n -1; // ya que los índices empiezan en 0

		// 5. Mientras el índice inicial sea menor o igual que el último índice
		// 5.1 Calcular el índice para la posición media, como un entero
		// 5.2 Traducir este índice a coordenadas 2D (usando la función)
		// 5.3 Obtener el valor de la matriz en la posición media
		// 5.4 Si el valor en la posición media es igual al objetivo->
		// 5.5 Devolver las coordenadas 2D donde se encuentra
		// 5.6 En caso contrario: Si el valor en la posición media es menor que el objetivo->
		// 5.7 Establecer el índice inicial como el índice medio + 1
		// 5.8 En caso contrario (el valor en la posición media es mayor que el objetivo)->
		// 5.9 Establecer el último índice como medio - 1

		while (inicio <= ultimoElemento) {
			counter++;
			int medio = (inicio + ultimoElemento)/2; //en indice
			int[] coordenadasMedio = getCoordinates(medio, n); //pide indice, ancho; devuelve fila, columna
			//System.out.println("elemento en matriz medio: " + matrix[coordenadasMedio[0]][coordenadasMedio[1]] + " target: " + target);

			if (matrix[coordenadasMedio[0]][coordenadasMedio[1]] == target) {
				resultado[0] = coordenadasMedio[0];
				resultado[1] = coordenadasMedio[1];
				break;
			}else if (matrix[coordenadasMedio[0]][coordenadasMedio[1]] < target) {
				inicio = medio + 1 ; 
				//System.out.println("nuevo inicio: "+ inicio);
			}else {
				ultimoElemento = medio - 1;
				//System.out.println("nuevo ultimoElemento: "+ ultimoElemento );
			}
		} 
		// 6. Ejecutar este código significa que el objetivo no está en la matriz, devuelve [-1,-1]
		if (resultado[0] == -1 && resultado[1] == -1) {
			System.out.println("El elemento buscado no está en la matriz.");
		}
		resultado[2] = counter;
		return resultado;
	}

	public static int[] binaryMatRecursiveSearch (int[][] matrix, int target,int inicio, int fin){
		int[] resultado = { -1, -1, 0 }; // fila, columna, iteraciones

		if(inicio <= fin) {
			if (inicio <= fin) {
		        int medio = (inicio + fin) / 2;
		        int numColumnas = matrix[0].length;

		        int fila = medio / numColumnas;
		        int columna = medio % numColumnas;

		        int valor = matrix[fila][columna];
		        resultado[2]++; // 1 iteración

		        if (valor == target) {
		            resultado[0] = fila;
		            resultado[1] = columna;
		            return resultado;
		        } else if (valor < target) {
		            int[] siguiente = binaryMatRecursiveSearch(matrix, target, medio + 1, fin);
		            siguiente[2] += resultado[2]; // acumula las iteraciones
		            return siguiente;
		        } else {
		            int[] siguiente = binaryMatRecursiveSearch(matrix, target, inicio, medio - 1);
		            siguiente[2] += resultado[2];
		            return siguiente;
		        }
		    }
		}
		if (resultado[0] == -1 && resultado[1] == -1) {
			System.out.println("El elemento buscado no está en la matriz.");
		}
		return resultado;
	}

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 50;
		int n = m;

		int[][] matrix = generateOrderedMatrix(m,n);
		int elementoABuscar = matrix[1][1];
		int[] resultado = binaryMatSearch(matrix, elementoABuscar);
		//Comprobaciones
		System.out.println("Elemento buscado: " + elementoABuscar);
		if (resultado[0] != -1 && resultado[1] != -1) {
			System.out.println("Elemento encontrado: " + matrix[resultado[0]][resultado[1]]);
		}
		//System.out.println(resultado[0] + ", " + resultado[1]);

		/*Para visualizar la matriz:
		System.out.println("\nMatriz: ");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(",");
		}
		*/

		// PROMEDIO
		int iterationSum=0;
		int[] resultado3;
		System.out.println("\n********PROMEDIO********");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				resultado3 = binaryMatSearch(matrix,matrix[i][j]);
				iterationSum += resultado3[2];
			}
		}
		System.out.printf("\n%s; m*n: %d ; iterationSum: %d ;%s\n",
				"BinaryMatSearchPromedio", m*n, iterationSum/((m*n)), "");

		//CASO MEJOR 
		//El elemento buscado está justo en la posición media: coste O(1)
		int ultimoElemento1 =  m*n -1;
		int medio1 = ultimoElemento1/2; //en indice
		int[] coord = getCoordinates(medio1, n);
		
		int elementoCasoMejor = matrix[coord[0]][coord[1]];
		System.out.println("\n********CASO MEJOR********");
		int[] resultadoCasoMejor = binaryMatSearch(matrix, elementoCasoMejor);
		System.out.printf("%-15s | %-5s | %-11s | %s\n", "Descripcion", "Talla",
				"Iteraciones", "Resultado");
		System.out.printf("%-15s | %-5d | %-11d | %s\n", "linearMatSearch",
				m*n, resultadoCasoMejor[2], "["+resultadoCasoMejor[0]+","+resultadoCasoMejor[1]+"]");

		//CASO PEOR: el elemento no está o está en la última posicion
		int elementoCasoPeor1 = -8;
		System.out.println("\n********CASO PEOR********");
		int[] resultadoCasoPeor = binaryMatSearch(matrix, elementoCasoPeor1);
		System.out.printf("%-15s | %-5s | %-11s | %s\n", "Descripcion", "Talla",
				"Iteraciones", "Resultado");
		System.out.printf("%-15s | %-5d | %-11d | %s\n", "linearMatSearch",
				m*n, resultadoCasoPeor[2], "["+resultadoCasoPeor[0]+","+resultadoCasoPeor[1]+"]");

		
		/*Para este algoritmo, ¿existe caso mejor y caso peor respecto al coste
		* temporal? Explica tu respuesta y compara con los resultados de los
		* algoritmos anteriores.
		* Si existe, y el resultado es mejor que en los otros algoritmos
		*/
		
		
		
		/*Si la matriz es de tamaño 50x50, y el elemento se encuentra en el
		* centro, ¿cuántas iteraciones realiza el algoritmo? Demuéstralo con un
		* ejemplo en el código.
		* Realiza 1 iteración ya que es el primero que encuentra
		*/

		
		/*Si la matriz es de tamaño 50x50, y el elemento no encuentra en ella,
		* ¿cuántas iteraciones realiza el algoritmo? Demuéstralo con un
		* ejemplo en el código.
		* Realiza 11 iteraciones.
		*/

		
	//////////////////////RECURSIVA 	
		// PROMEDIO
		int iterationSum2=0;
		int[] resultado4;
		System.out.println("\n********PROMEDIO********");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				resultado4 = binaryMatRecursiveSearch(matrix,matrix[i][j],0, m*n);
				iterationSum2 += resultado4[2];
			}
		}
		System.out.printf("\n%s; m*n: %d ; iterationSum: %d ;%s\n",
				"BinaryMatRecursiveSearchPromedio", m*n, iterationSum2/((m*n)), "");

		//CASO MEJOR 
		//El elemento buscado está justo en la posición media: coste O(1)
		int ultimoElemento2 =  m*n -1;
		int medio2 = ultimoElemento2/2; //en indice
		int[] coord2 = getCoordinates(medio2, n);

		int elementoCasoMejor2 = matrix[coord2[0]][coord2[1]];
		System.out.println("\n********CASO MEJOR********");
		int[] resultadoCasoMejor2 = binaryMatRecursiveSearch(matrix, elementoCasoMejor2, 0 , m*n-1);
		System.out.printf("%-15s | %-5s | %-11s | %s\n", "Descripcion", "Talla",
				"Iteraciones", "Resultado");
		System.out.printf("%-15s | %-5d | %-11d | %s\n", "binaryMatRecursiveSearch",
				m * n, resultadoCasoMejor2[2], "["+resultadoCasoMejor2[0]+","+resultadoCasoMejor2[1]+"]");

		//CASO PEOR: el elemento no está o está en la última posicion
		int elementoCasoPeor2 = -8;
		System.out.println("\n********CASO PEOR********");
		int[] resultadoCasoPeor2 = binaryMatRecursiveSearch(matrix, elementoCasoPeor2, 0, m*n-1);
		System.out.printf("%-15s | %-5s | %-11s | %s\n", "Descripcion", "Talla",
				"Iteraciones", "Resultado");
		System.out.printf("%-15s | %-5d | %-11d | %s\n", "binaryMatRecursiveSearch",
				m * n, resultadoCasoPeor2[2], "["+resultadoCasoPeor2[0]+","+resultadoCasoPeor2[1]+"]");
		
		
		
		// Para matrices mucho más grandes como 5000x5000, el promedio de iteraciones es de 23
	}
}
