package Day19;

import java.util.*;
import java.io.*;
import ExtraClasses.*;

public class advent38 {
    static List<List<Ponto3D>> input = new ArrayList<List<Ponto3D>>();
    static List<Ponto3D> scanners = new ArrayList<Ponto3D>();
    static List<Ponto3D> relativeScanners = new ArrayList<Ponto3D>();
    static List<Ponto> pares = new ArrayList<Ponto>();
    static List<Integer> orientations = new ArrayList<Integer>();
    static List<Character[]> positions = new ArrayList<Character[]>();
    static List<int[]> signals = new ArrayList<int[]>();

    public static void main(String[] args) throws IOException {
        File f = new File("AdventOfCode2021/advent37.txt");
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();

        // Leitura do input

        while (sc.hasNextLine()) {
            List<Ponto3D> temp = new ArrayList<Ponto3D>();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.isEmpty()) {
                    continue;
                } else if (line.startsWith("---")) {
                    break;
                }
                String[] linha = line.split(",");
                temp.add(new Ponto3D(Integer.parseInt(linha[0]), Integer.parseInt(linha[1]),
                        Integer.parseInt(linha[2])));
            }
            input.add(temp);
        }

        // definir o size das listas

        for (int i = 0; i < input.size(); i++) {
            scanners.add(null);
            relativeScanners.add(null);
            pares.add(null);
            orientations.add(-1);
            positions.add(null);
            signals.add(null);
        }
        scanners.set(0, new Ponto3D());
        relativeScanners.set(0, new Ponto3D());
        orientations.set(0, 1);
        pares.set(0, new Ponto());
        positions.set(0, new Character[] { 'x', 'y', 'z' });
        signals.set(0, new int[] { 1, 1, 1 });

        // Obter os Scanners relativamente aos seus pares

        while (orientations.contains(-1)) {
            getScanners();
        }

        getRelativeScanners();
        /*
         * System.out.println("\nScanners relativos ao Scanner 0\n");
         * for (int i = 0; i < relativeScanners.size(); i++) {
         * System.out.println("Scanner" + i);
         * System.out.println(relativeScanners.get(i).toString());
         * }
         */
        int max = 0;
        int temp = 0;
        for (int i = 0; i < relativeScanners.size(); i++) {
            for (int j = 0; j < relativeScanners.size(); j++) {
                temp = ManhattanDistance(relativeScanners.get(i), relativeScanners.get(j));
                if (temp > max) {
                    max = temp;
                }
            }
        }
        System.out.println(max);

        sc.close();
    }

    public static int ManhattanDistance(Ponto3D p1, Ponto3D p2) {
        int res = Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()) + Math.abs(p1.getZ() - p2.getZ());
        return res;
    }

    public static void getRelativeScanners() {
        for (int i = 0; i < relativeScanners.size(); i++) {
            getRelativeScannerPosition(getPath(i), i);
        }
    }

    public static void getRelativeScannerPosition(Stack<Integer> path, int index) {
        Ponto3D scanner = new Ponto3D();
        int size = path.size();

        Stack<Integer> temp = new Stack<Integer>();
        int sc = 0;
        Character[] tempPosition = new Character[] { 'x', 'y', 'z' };
        int[] tempSinal = new int[] { 1, 1, 1 };
        for (int i = 0; i < size; i++) {
            sc = path.pop();
            temp.add(sc);
            tempPosition = getPosition(temp, 0);
            tempSinal = getSinal(temp, 0);
            scanner = scanner.customOrientationAdd(scanners.get(sc), tempPosition, tempSinal);
        }
        positions.set(sc, getPosition(temp, 1));
        signals.set(sc, getSinal(temp, 1));
        relativeScanners.set(index, scanner);
        return;
    }

    public static Character[] getPosition(Stack<Integer> path, int index) {
        Character[] position = new Character[3];
        Ponto3D temp = new Ponto3D(1, 2, 3);
        for (int i = path.size() - 2 + index; i >= 0; i--) {
            temp = (new Ponto3D()).differentOrientationsAdd(temp, orientations.get(path.get(i)));
        }
        temp = new Ponto3D(Math.abs(temp.getX()), Math.abs(temp.getY()), Math.abs(temp.getZ()));
        int x = temp.getX();
        int y = temp.getY();
        if (x == 1) {
            if (y == 2) {
                position[0] = 'x';
                position[1] = 'y';
                position[2] = 'z';
            } else if (y == 3) {
                position[0] = 'x';
                position[1] = 'z';
                position[2] = 'y';
            }
        } else if (x == 2) {
            if (y == 1) {
                position[0] = 'y';
                position[1] = 'x';
                position[2] = 'z';
            } else if (y == 3) {
                position[0] = 'y';
                position[1] = 'z';
                position[2] = 'x';
            }
        } else if (x == 3) {
            if (y == 1) {
                position[0] = 'z';
                position[1] = 'x';
                position[2] = 'y';
            } else if (y == 2) {
                position[0] = 'z';
                position[1] = 'y';
                position[2] = 'x';
            }
        }

        return position;
    }

    public static int[] getSinal(Stack<Integer> path, int index) {
        int[] sinal = new int[3];
        Ponto3D temp = new Ponto3D(1, 1, 1);
        for (int i = path.size() - 2 + index; i >= 0; i--) {
            temp = (new Ponto3D()).differentOrientationsAdd(temp, orientations.get(path.get(i)));
        }
        int x = temp.getX();
        int y = temp.getY();
        int z = temp.getZ();
        if (x < 0) {
            sinal[0] = -1;
        } else {
            sinal[0] = 1;
        }
        if (y < 0) {
            sinal[1] = -1;
        } else {
            sinal[1] = 1;
        }
        if (z < 0) {
            sinal[2] = -1;
        } else {
            sinal[2] = 1;
        }
        return sinal;
    }

    public static Stack<Integer> getPath(int start) {
        Stack<Integer> path = new Stack<Integer>();
        if (start == 0) {
            return path;
        }
        path.add(start);

        int temp = start;
        do {
            temp = pares.get(temp).getX();
            path.add(temp);
        } while ((temp != 0));
        path.pop();
        return path;
    }

    public static void getScanners() {
        int n = 0;
        int inputSize = input.size();
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                if (i == j || scanners.get(j) != null) {
                    continue;
                }
                Ponto3D temp = findScanners(input.get(i), input.get(j), i, j); // posição do scanner "j" relativo a "i"

                if (temp != null) { // caso seja um Scanner
                    System.out.println("Scanner " + n++);
                    System.out.println(i + ", " + j);
                    System.out.println(temp.toString());
                    if (scanners.get(j) == null) { // caso este Scanner ainda não esteja definido
                        scanners.set(j, temp);
                        pares.set(j, new Ponto(i, j)); // definir a relatividade entre Scanners
                    }
                }

            }
        }

        return;
    }

    public static Ponto3D findScanners(List<Ponto3D> lista1, List<Ponto3D> lista2, int first, int second) {
        Ponto3D diff = null;
        int size1 = lista1.size();
        int size2 = lista2.size();
        int lista1Orientation = orientations.get(first);
        if (lista1Orientation == -1) {
            // System.out.println(first+","+second);
            return diff;
        }

        for (int i = 0; i < size1; i++) { // percorrer os pontos da lista1
            Ponto3D p1 = lista1.get(i);
            for (int j = 0; j < size2; j++) { // percorrer os pontos da lista2
                for (int possibleOrientation = 1; possibleOrientation <= 24; possibleOrientation++) {
                    Ponto3D p2 = lista2.get(j);

                    // obter a diferença entre os dois pontos que poderá ser as coordenadas de um
                    // Scanner
                    diff = p1.differentOrientationsSub(p2, possibleOrientation);
                    if (checkDiff(diff, lista1, lista2, possibleOrientation)) { // verificar se diff é um Scanner
                        if (orientations.get(second) == -1) { // definir a orientação do Scanner
                            orientations.set(second, possibleOrientation);
                        }
                        return diff;
                    }
                }

            }

            diff = null;
        }
        return diff;
    }

    public static boolean checkDiff(Ponto3D diff, List<Ponto3D> lista1, List<Ponto3D> lista2, int lista2Orientation) {
        int counter = 0;
        int size = lista2.size();

        for (int j = 0; j < size - 11 + counter; j++) {
            if (containsPonto3D(lista1, diff.differentOrientationsAdd(lista2.get(j), lista2Orientation))) {
                counter++;
            }
            if (counter == 12) {
                return true;
            }
        }
        counter = 0;

        return false;
    }

    public static boolean containsPonto3D(List<Ponto3D> lista1, Ponto3D p) {
        int size = lista1.size();
        for (int i = 0; i < size; i++) {
            if (lista1.get(i).equals(p)) {
                return true;
            }
        }
        return false;
    }
}
