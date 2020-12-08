
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Runnable {
    public static int rooms = 0;
    public static Module[][] modules;
    public static int m, n;
    public static int[] area;
    public static int maxPotentialArea;
    public static int maxArea;
    public static int unvisited;

    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        m = scanner.nextInt();
        n = scanner.nextInt();
        unvisited = m * n;
        modules = new Module[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                modules[i][j] = new Module();
                modules[i][j].walls = Module.getWalls(scanner.nextInt());
            }
        }
        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                if (modules[r][c].roomNumber == -1) {
                    paintRoom(r, c);
                    rooms++;
                }
            }
        }
        calculateMaxArea();
        calculateMaxPotentialArea();
        try {
            fileWriter.write(rooms + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write(maxArea + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write(maxPotentialArea + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void paintRoom(int r, int c) {
        if (modules[r][c].roomNumber == -1) {
            modules[r][c].roomNumber = rooms;
            unvisited--;
            if ((!modules[r][c].walls[0]) && (c > 1)) {
                paintRoom(r, c - 1);
            }
            if ((!modules[r][c].walls[1]) && (r > 1)) {
                paintRoom(r - 1, c);
            }
            if ((!modules[r][c].walls[2]) && (c < n)) {
                paintRoom(r, c + 1);
            }
            if ((!modules[r][c].walls[3]) && (r < m)) {
                paintRoom(r + 1, c);
            }
        }
    }


    public static void calculateMaxArea() {
        area = new int[rooms];
        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                area[modules[r][c].roomNumber]++;
            }
        }
        for (int j : area) {
            if (j > maxArea) {
                maxArea = j;
            }
        }
    }

    public static void update(int roomNumber1, int roomNumber2) {
        int tmp;
        if (roomNumber1 == roomNumber2) {
            tmp = area[roomNumber1];
        } else {
            tmp = area[roomNumber1] + area[roomNumber2];
        }
        if (tmp > maxPotentialArea) {
            maxPotentialArea = tmp;
        }
    }

    public static void calculateMaxPotentialArea() {
        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                if (modules[r][c].walls[3] && r < n) {
                    update(modules[r][c].roomNumber, modules[r + 1][c].roomNumber);
                }
                if (modules[r][c].walls[2] && c < n) {
                    update(modules[r][c].roomNumber, modules[r][c + 1].roomNumber);
                }
            }
        }
    }
}

class Module {
    boolean[] walls;//(west, north, east, south)
    int roomNumber = -1;

    public static boolean[] getWalls(int a) {
        switch (a) {
            case 1:
                return new boolean[]{true, false, false, false};
            case 2:
                return new boolean[]{false, true, false, false};
            case 3:
                return new boolean[]{true, true, false, false};
            case 4:
                return new boolean[]{false, false, true, false};
            case 5:
                return new boolean[]{true, false, true, false};
            case 6:
                return new boolean[]{false, true, true, false};
            case 7:
                return new boolean[]{true, true, true, false};
            case 8:
                return new boolean[]{false, false, false, true};
            case 9:
                return new boolean[]{true, false, false, true};
            case 10:
                return new boolean[]{false, true, false, true};
            case 11:
                return new boolean[]{true, true, false, true};
            case 12:
                return new boolean[]{false, false, true, true};
            case 13:
                return new boolean[]{true, false, true, true};
            case 14:
                return new boolean[]{false, true, true, true};
            case 15:
                return new boolean[]{true, true, true, true};
            default:
                return new boolean[4];
        }
    }
}