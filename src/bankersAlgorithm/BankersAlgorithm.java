package bankersAlgorithm;

public class BankersAlgorithm {

    // Numero de procesos y recursos
    static int PROCESSES = 3;
    static int RESOURCES = 4;

    /*
    // Matriz de asignación (segura)
    static int[][] allocation = {
            {1, 2, 0, 0},
            {1, 2, 1, 0},
            {0, 0, 1, 0}
    };*/

    // Matriz de asignación (insegura)
    static int[][] allocation = {
            {1, 2, 0, 0},
            {1, 2, 2, 1},
            {1, 1, 1, 0}
    };

    // Matriz de necesidad
    static int[][] need = {
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0}
    };

    // Vector de recursos totales (instancias)
    static int[] totalResources = {4, 4, 4, 4};

    public static void main(String[] args) {
        // Calculamos los recursos disponibles
        int[] available = calculateAvailable();

        System.out.println("Matriz de Asignación:");
        printMatrix(allocation);
        System.out.println("Matriz de Necesidad:");
        printMatrix(need);
        System.out.println("Vector de Recursos Disponibles:");
        printVector(available);

        // Verificamos si el sistema está en un estado seguro
        if (isSafeState(available)) {
            System.out.println("\nEl sistema está en un estado seguro.");
        } else {
            System.out.println("\nEl sistema NO está en un estado seguro (puede haber interbloqueo).");
        }
    }

    // Algoritmo para verificar si el sistema está en un estado seguro
    private static boolean isSafeState(int[] available) {
        boolean[] finish = new boolean[PROCESSES];
        int[] work = available.clone();
        int[] safeSequence = new int[PROCESSES];
        int count = 0;

        while (count < PROCESSES) {
            boolean found = false;
            for (int p = 0; p < PROCESSES; p++) {
                if (!finish[p] && canAllocate(work, need[p])) {
                    // Asignamos recursos hipotéticamente (liberamos)
                    for (int j = 0; j < RESOURCES; j++) {
                        work[j] += allocation[p][j];
                    }
                    safeSequence[count++] = p;
                    finish[p] = true;
                    found = true;
                }
            }
            if (!found) {
                // No encontramos un proceso que pueda ejecutarse
                return false;
            }
        }

        // Si llegamos aquí, el sistema está en un estado seguro (existe una secuencia de ejecucion segura)
        System.out.print("Secuencia segura: ");
        for (int i = 0; i < PROCESSES; i++) {
            System.out.print("P" + safeSequence[i] + " ");
        }
        System.out.println();
        return true;
    }

    public static int[] calculateAvailable() {
        int[] available = new int[RESOURCES];
        for (int j = 0; j < RESOURCES; j++) {
            int sumAllocated = 0;
            for (int i = 0; i < PROCESSES; i++) {
                sumAllocated += allocation[i][j];
            }
            available[j] = totalResources[j] - sumAllocated;
        }
        return available;
    }

    private static boolean canAllocate(int[] available, int[] need) {
        for (int j = 0; j < RESOURCES; j++) {
            if (need[j] > available[j]) {
                return false;
            }
        }
        return true;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private static void printVector(int[] vector) {
        for (int value : vector) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
