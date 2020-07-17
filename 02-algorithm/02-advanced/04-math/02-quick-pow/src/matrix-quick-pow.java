class Matrix {
    int row;
    int col;
    int[][] m;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        m = new int[row][col];
    }
}

static final int MOD = 10000;

public Matrix mul(Matrix a, Matrix b) {
    Matrix c = new Matrix(a.row, b.col); // 注意这里

    for (int i = 0; i < a.row; i++) {
        for (int j = 0; j < b.col; j++) {
            for (int k = 0; k < a.col; k++) {
                c.m[i][j] = (c.m[i][j] + a.m[i][k] * b.m[k][j]) % MOD;
            }
        }
    }
    return c;
}

public Matrix pow(Matrix a, int k) {
    Matrix res = new Matrix(a.row, a.col); // 方阵
    for (int i = 0; i < a.row; i++) {
        res.m[i][i] = 1;
    }
    while (k > 0) {
        if ((k & 1) != 0) {
            res = mul(res, a);
        }
        a = mul(a, a);
        k >>= 1;
    }
    return res;
}