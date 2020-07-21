import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
    public int x;
    public int y;
    public int[][] matrix;

    public Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        this.matrix = new int[x][y];
    }

    private Matrix(int[][] origin) {
        this.x = origin.length;
        this.y = origin[0].length;
        this.matrix = origin;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public void setLine(int lineNum, String input) {
        char[] data = input.toCharArray();

        for(int i = 0; i < data.length; ++i) {
            this.matrix[lineNum][i] = Integer.parseInt(String.valueOf(data[i]));
        }

    }

    public int getElement(int x, int y) {
        return this.matrix[x][y];
    }

    public int setElementAndGetNewValue(int x, int y, int value) {
        this.matrix[x][y] = value;
        return this.matrix[x][y];
    }

    public int applyRules(int x, int y) {
        int neighboursSum;
        if (this.isRed(this.getElement(x, y))) {
            neighboursSum = this.getNeighboursSum(x, y);
            if (neighboursSum == 3 || neighboursSum == 6) {
                return 1;
            }
        } else if (this.isGreen(this.getElement(x, y))) {
            neighboursSum = this.getNeighboursSum(x, y);
            if (neighboursSum != 2 && neighboursSum != 3 && neighboursSum != 6) {
                return 0;
            }
        }

        return this.getElement(x, y);
    }

    private int getNeighboursSum(int x, int y) {
        List<Integer> sums = new ArrayList();
        sums.add(this.getTopLeft(x, y));
        sums.add(this.getTopRight(x, y));
        sums.add(this.getTop(x, y));
        sums.add(this.getLeft(x, y));
        sums.add(this.getRight(x, y));
        sums.add(this.getBottomLeft(x, y));
        sums.add(this.getBottomRight(x, y));
        sums.add(this.getBottom(x, y));
        return sums.stream().mapToInt(Integer::intValue).sum();
    }

    private int getBottom(int x, int y) {
        try {
            return this.getElement(x + 1, y);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    private int getBottomRight(int x, int y) {
        try {
            return this.getElement(x + 1, y + 1);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    private int getBottomLeft(int x, int y) {
        try {
            return this.getElement(x + 1, y - 1);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    private int getRight(int x, int y) {
        try {
            return this.getElement(x, y + 1);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    private int getLeft(int x, int y) {
        try {
            return this.getElement(x, y - 1);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    private int getTop(int x, int y) {
        try {
            return this.getElement(x - 1, y);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    private int getTopRight(int x, int y) {
        try {
            return this.getElement(x - 1, y + 1);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    private int getTopLeft(int x, int y) {
        try {
            return this.getElement(x - 1, y - 1);
        } catch (IndexOutOfBoundsException var4) {
            return 0;
        }
    }

    public boolean isGreen(int element) {
        return element == 1;
    }

    public boolean isRed(int element) {
        return element == 0;
    }

    public static Matrix copyFrom(Matrix origin) {
        return new Matrix((int[][])Arrays.stream(origin.getMatrix()).map(int[]::clone)
                .toArray(int[][]::new));
    }

    public String toString() {
        int var10000 = this.x;
        return "Matrix{x=" + var10000 + ", y=" + this.y + ", matrix=" + Arrays.deepToString(this.matrix) + "}";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}