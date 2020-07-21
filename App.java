import java.util.Scanner;

public class App {
    public App() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dimensions = scanner.nextLine();
        int x = Integer.parseInt(dimensions.split(" ")[0]);
        int y = Integer.parseInt(dimensions.split(" ")[1]);
        Matrix matrix = new Matrix(x, y);

        for(int i = 0; i < x; ++i) {
            matrix.setLine(i, scanner.nextLine());
        }

        System.out.println(matrix.toString());
        String[] watch = scanner.nextLine().split(", ");
        int coord_X = Integer.parseInt(watch[0]);
        int coord_Y = Integer.parseInt(watch[1]);
        int passes = Integer.parseInt(watch[2]);
        int result = 0;
        Matrix prevGeneration = Matrix.copyFrom(matrix);

        for(int n = 0; n < passes; ++n) {
            Matrix nextGeneration = Matrix.copyFrom(prevGeneration);

            for(int i = 0; i < prevGeneration.getX(); ++i) {
                for(int j = 0; j < prevGeneration.getY(); ++j) {
                    nextGeneration.setElementAndGetNewValue(i, j, prevGeneration.applyRules(i, j));
                }
            }

            if (nextGeneration.isGreen(nextGeneration.getElement(coord_X, coord_Y))) {
                ++result;
            }

            prevGeneration = nextGeneration;
            System.out.println(nextGeneration.toString());
            System.out.println(result);
        }

    }
}
