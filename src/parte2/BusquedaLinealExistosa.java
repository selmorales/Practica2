package parte2;

import java.io.PrintStream;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class BusquedaLinealExistosa {
	final static int INIT_SIZE = 100000; // Talla inicial
	final static int MAX_SIZE = 1000000; // Talla final
	final static int STEP = 100000;
	// A cada medida, incrementamos la talla en STEP
	final static int REPS = 1000;
	// Repeticiones para una media más fiable


	public static int[] linealSearchIterative(int[] array, int valor) {
		int pos = -1;
		int iterations= 0;
		int[] result = { pos, iterations};
		boolean found = false;
		for (int i = 0; i < array.length && !found; i++) {
			iterations++;
			if (valor == array[i]) {
				pos = i;
				found = true;
			}
		}
		result[0]=pos;
		result[1]=iterations;
		return result;
	}



	public static int[] generateOrderedArray(int n) { 
		Random rand = new Random();
		int[] array = new int[n];
		int value = 0;
		for (int i = 0; i < n; i++) {
			value +=rand.nextInt(9)+1; //Inc. aleatorio entre 1 y 10
			array[i] = value;
		}
		return array;
	}

	public static void main(String[] args) {
		Random rand = new Random();

		/*System.out.printf("%10s;%10s;%10s;%10s\n", "Talla", "Mejor",
				"Peor", "Promedio");
		for (int size=INIT_SIZE;size<=MAX_SIZE;size+=STEP) {
			int[] array=generateOrderedArray(size);

			// ---- Mejor caso ----
			int vBest = array[0];
			double timeStart = System.nanoTime();
			for (int rep = 0; rep < REPS; rep++) {
				linealSearchIterative(array, vBest);
			}
			double timeEnd = System.nanoTime();
			int tBest = (int) ((timeEnd - timeStart) / REPS);

			// ---- Peor caso ----
			int vWorst = array[array.length - 1];
			timeStart = System.nanoTime();
			for (int rep = 0; rep < REPS; rep++) {
				linealSearchIterative(array, vWorst);
			}
			timeEnd = System.nanoTime();
			int tWorst = (int) ((timeEnd - timeStart) / REPS);

			// ---- Promedio ----
			timeStart = System.nanoTime();
			for (int rep = 0; rep < REPS; rep++) {
				int idx = rand.nextInt(array.length);
				int value = array[idx];
				linealSearchIterative(array, value);
			}
			timeEnd = System.nanoTime();
			int tAvg = (int) ((timeEnd - timeStart) / REPS);

			// ---- Imprimir ----
			System.out.printf("%10d;%10d;%10d;%10d\n", size, tBest, tWorst, tAvg); 
			*/

////////////////////////

			PrintStream csvPrintStream = null;

			try {
				csvPrintStream = new PrintStream(new FileOutputStream("output.csv"));
				csvPrintStream.printf("%10s;%10s;%10s;%10s\n", "Talla", "Mejor", "Peor", "Promedio");

				for (int size = INIT_SIZE; size <= MAX_SIZE; size += STEP) {
					int[] array = generateOrderedArray(size);

					// Mejor caso
					int best = array[0];
					long start = System.nanoTime();
					for (int r = 0; r < REPS; r++) linealSearchIterative(array, best);
					long end = System.nanoTime();
					int tBest = (int) ((end - start) / REPS);

					// Peor caso
					int worst = array[array.length - 1];
					start = System.nanoTime();
					for (int r = 0; r < REPS; r++) linealSearchIterative(array, worst);
					end = System.nanoTime();
					int tWorst = (int) ((end - start) / REPS);

					// Promedio
					start = System.nanoTime();
					for (int r = 0; r < REPS; r++) {
						int idx = rand.nextInt(array.length);
						linealSearchIterative(array, array[idx]);
					}
					end = System.nanoTime();
					int tAvg = (int) ((end - start) / REPS);

					csvPrintStream.printf("%10d;%10d;%10d;%10d\n", size, tBest, tWorst, tAvg);
				}
			} catch (FileNotFoundException e) {
				System.err.println("Error: No se pudo abrir archivo CSV: " + e);
			} finally {
				if (csvPrintStream != null) csvPrintStream.close();
			}
		}
}