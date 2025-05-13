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
		int counter = 0;
		int[] result = { -1, -1, counter };
		int x = mat[0].length; // filas
		int y = mat.length; // columnas
		
		// para la primera fila buscar en columnas
		for(int i = 0; i < y; i++){ // Desde la fila 0 a la 2
			//System.out.println("Fila: "+ i);
			for(int j = 0; j < x; j++){//Desde columna 0 a 2 dentro de una fila i
				//System.out.println("Columna: "+ i);
				counter++;
				if (mat[i][j] == valor ) {
					result[0] = i;
					result[1] = j;
				}
			}
		} result[2] = counter;
		return result;
		
		}

	
	public static int[] successfulMatSearch(int[][] mat, int v) {
		int counter = 0;
		int[] result = { -1, -1, counter };
		int x = mat[0].length; // filas
		int y = mat.length; // columnas
		boolean encontrado = false;
		while(encontrado == false) {
			for(int i = 0; i < y; i++){ // Desde la fila 0 a la 2
				//System.out.println("Fila: "+ i);
				for(int j = 0; j < x; j++){//Desde columna 0 a 2 dentro de una fila i
					//System.out.println("Columna: "+ i);
					counter++;
					if (mat[i][j] == v ) {
						result[0] = i;
						result[1] = j;
						encontrado = true;
					}
				}
			} result[2] = counter;
		}
		return result;
		}

	
	
//////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		int m = 7;
		int n = 7;
		
		int[][] matrix = generateOrderedMatrix(m,n);
		int elementoABuscar = matrix[4][3];
		int[] resultado = linearMatSearch(matrix, elementoABuscar);
		//Comprobaciones
		System.out.println("Elemento buscado: " + elementoABuscar);
		System.out.println("Elemento encontrado: " + matrix[resultado[0]][resultado[1]]);
		System.out.println(resultado[0] + ", " + resultado[1]);
		
		
		//%-15s alinea a la izq con 15 caracteres
		System.out.printf("%-15s | %-5s | %-11s | %s\n", "Descripcion", "Talla",
				"Iteraciones", "Resultado");
		System.out.printf("%-15s | %-5d | %-11d | %s\n", "linearMatSearch",
				m*n, resultado[2], "["+resultado[0]+","+resultado[1]+"]");
		
		//Para visualizar la matriz:
		/*for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
			System.out.print(matrix[i][j] + " ");
			}
			System.out.println(",");
			}*/
		
/* El coste de los casos mejor y peor será siempre el mismo,
 * ya que el algoritmo tiene que recorrer siempre todo el array.
 * Será m*n .
 * 
 * Si la matriz es de 50x50 y el elemento no se encuentra en ella, 
 * hará 50 iteraciones.
 */
		
}
}
