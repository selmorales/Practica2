package parte1;
import java.util.Random;

public class BusquedaLineal {
	
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
	
	public static int[] linearMatSearch(int[][] mat, int valor) {
		int m = mat.length;
		int n = mat[0].length;
		int counter = 0;
		int[] result = { -1, -1, counter };
		int x = mat[0].length; // filas
		int y = mat.length; // columnas
		
		// para la primera fila buscar en columnas
		for(int i = 0; i < y; i++){ // Desde la fila 0 a la 2
			//System.out.println("Fila: "+ i);
			for(int j = 0; j < x; j++){//Desde columna 0 a 2 dentro de una fila i
				//System.out.println("Columna: "+ j);
				counter++;
				if (mat[i][j] == valor ) {
					result[0] = i;
					result[1] = j;
				}
			}
		} result[2] = counter;
		//%-15s alinea a la izq con 15 caracteres
				System.out.printf("%-15s | %-5s | %-11s | %s\n", "Descripcion", "Talla",
						"Iteraciones", "Resultado");
				System.out.printf("%-15s | %-5d | %-11d | %s\n", "linearMatSearch",
						m*n, result[2], "["+result[0]+","+result[1]+"]");
		return result;
		
		}

	
	public static int[] successfulMatSearch(int[][] mat, int v) {
		int m = mat.length;
		int n = mat[0].length;
		int counter = 0;
		int[] result = { -1, -1, counter };
		int x = mat[0].length; // columnas
		int y = mat.length; // filas
		boolean encontrado = false;
	
			for(int i = 0; i < y; i++){ // Desde la fila 0 a la 2
				//System.out.println("Fila: "+ i);
				for(int j = 0; j < x; j++){//Desde columna 0 a 2 dentro de una fila i
					//System.out.println("Columna: "+ j);
					counter++;
					if (mat[i][j] == v ) {
						result[0] = i;
						result[1] = j;
						encontrado = true;
						break;
					}
				}
				if (encontrado == true) break;
			} result[2] = counter;
			//System.out.println(result[0]+ " " +result[1]+ " " +result[2]+ " " );
		
		System.out.printf("%-15s | %-5s | %-11s | %s\n", "Descripcion", "Talla",
				"Iteraciones", "Resultado");
		System.out.printf("%-15s | %-5d | %-11d | %s\n", "linearMatSearch",
				m*n, result[2], "["+result[0]+","+result[1]+"]");
		return result;
		}

	
	
//////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		int m = 5;
		int n = 5;
		
		int[][] matrix = generateOrderedMatrix(m,n);
		int elementoABuscar = matrix[1][1];
		int[] resultado = linearMatSearch(matrix, elementoABuscar);
		//Comprobaciones
		System.out.println("Elemento buscado: " + elementoABuscar);
		System.out.println("Elemento encontrado: " + matrix[resultado[0]][resultado[1]]);
		System.out.println(resultado[0] + ", " + resultado[1]);
		
		//Para visualizar la matriz:
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
			System.out.print(matrix[i][j] + " ");
			}
			System.out.println(",");
			}
		/* PARA LINEARMATSEARCH El coste de los casos mejor y peor será siempre el mismo,
		 * ya que el algoritmo tiene que recorrer siempre todo el array.
		 * Será m*n .
		 * 
		 * Si la matriz es de 50x50 y el elemento no se encuentra en ella, 
		 * hará 50 iteraciones.
		 */
		
		int[] resultado2 = successfulMatSearch(matrix, elementoABuscar);
		
		int iterationSum=0;
		int[] resultado3;

		// PROMEDIO
		System.out.println("\n********PROMEDIO********");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				resultado3=successfulMatSearch(matrix,matrix[i][j]);
				iterationSum+=resultado3[2];
			}
		}
		System.out.printf("%s; m*n: %d ; iterationSum: %d ;%s\n",
				"successfulMatSearchPromedio", m*n, iterationSum/((m*n)-1), "");
		
		//CASO MEJOR 
		int elementoCasoMejor = matrix[0][0];
		System.out.println("\n********CASO MEJOR********");
		int[] resultadoCasoMejor = successfulMatSearch(matrix, elementoCasoMejor);
		
		//CASO PEOR: el elemento no está o está en la última posicion
		int elementoCasoPeor1 = matrix[m-1][n-1];
		System.out.println("\n********CASO PEOR********");
		int[] resultadoCasoPeor = successfulMatSearch(matrix, elementoCasoPeor1);
		
		
		

		/* En el mejor caso, se detiene inmediatamente.
		 * En el peor caso, recorre toda la matriz.
		*/
		
		
		/* Si que ha mejorado respecto al algoritmo anterior, ya que no tiene que recorrer toda 
		 * la matriz
		 */
		 
}
}
