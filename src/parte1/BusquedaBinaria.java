package parte1;

public class BusquedaBinaria {

	/* caso mejor array[0]
	   caso peor array[b]
	   caso promedio array[random(n)]
	   */
	public int[] getCoordinates(int i, int ancho) {//matrices CUADRADAS
		//int fila = (int) Math.floor((float) i/ancho);
		int fila = i/ancho; // división entera
		int columna = i % ancho; //resto de la división
		int[] coordenadas = {fila, columna};
		return coordenadas;
	}
	

	public static int[] binaryMatSearch(int[][] matrix, int target) {
		// 1. Calcular el número de filas
		// 2. Calcular el número de columnas
		// 3. Establecer el índice inicial como 0
		// 4. Establecer el último índice como la posición del último elemento
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
		// 6. Ejecutar este código significa que el objetivo no está en la matriz, devuelve [-1,-1]

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
