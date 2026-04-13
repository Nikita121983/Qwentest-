package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class SobolSequenceGenerator implements RandomVectorGenerator {
    private static final int BITS = 52;
    private static final String FILE_CHARSET = "US-ASCII";
    private static final int MAX_DIMENSION = 1000;
    private static final String RESOURCE_NAME = "/assets/org/apache/commons/math3/random/new-joe-kuo-6.1000";
    private static final double SCALE = FastMath.pow(2.0d, 52);
    private int count = 0;
    private final int dimension;
    private final long[][] direction;
    private final long[] x;

    public SobolSequenceGenerator(int dimension) throws OutOfRangeException {
        if (dimension < 1 || dimension > 1000) {
            throw new OutOfRangeException(Integer.valueOf(dimension), 1, 1000);
        }
        InputStream is = getClass().getResourceAsStream(RESOURCE_NAME);
        if (is == null) {
            throw new MathInternalError();
        }
        this.dimension = dimension;
        this.direction = (long[][]) Array.newInstance((Class<?>) Long.TYPE, dimension, 53);
        this.x = new long[dimension];
        try {
            try {
                initFromStream(is);
                try {
                    is.close();
                } catch (IOException e) {
                }
            } catch (IOException e2) {
                throw new MathInternalError();
            } catch (MathParseException e3) {
                throw new MathInternalError();
            }
        } catch (Throwable th) {
            try {
                is.close();
            } catch (IOException e4) {
            }
            throw th;
        }
    }

    public SobolSequenceGenerator(int dimension, InputStream is) throws NotStrictlyPositiveException, MathParseException, IOException {
        if (dimension < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(dimension));
        }
        this.dimension = dimension;
        this.direction = (long[][]) Array.newInstance((Class<?>) Long.TYPE, dimension, 53);
        this.x = new long[dimension];
        int lastDimension = initFromStream(is);
        if (lastDimension < dimension) {
            throw new OutOfRangeException(Integer.valueOf(dimension), 1, Integer.valueOf(lastDimension));
        }
    }

    private int initFromStream(InputStream is) throws MathParseException, IOException {
        for (int i = 1; i <= 52; i++) {
            this.direction[0][i] = 1 << (52 - i);
        }
        Charset charset = Charset.forName(FILE_CHARSET);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        int dim = -1;
        try {
            reader.readLine();
            int lineNumber = 2;
            int index = 1;
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    StringTokenizer st = new StringTokenizer(line, StringUtils.SPACE);
                    try {
                        dim = Integer.parseInt(st.nextToken());
                        if (dim >= 2 && dim <= this.dimension) {
                            int s = Integer.parseInt(st.nextToken());
                            int a = Integer.parseInt(st.nextToken());
                            int[] m = new int[s + 1];
                            for (int i2 = 1; i2 <= s; i2++) {
                                m[i2] = Integer.parseInt(st.nextToken());
                            }
                            int i3 = index + 1;
                            try {
                                initDirectionVector(index, a, m);
                                index = i3;
                            } catch (NumberFormatException e) {
                                throw new MathParseException(line, lineNumber);
                            } catch (NoSuchElementException e2) {
                                throw new MathParseException(line, lineNumber);
                            }
                        }
                        if (dim > this.dimension) {
                            return dim;
                        }
                        lineNumber++;
                    } catch (NumberFormatException e3) {
                    } catch (NoSuchElementException e4) {
                    }
                } else {
                    return dim;
                }
            }
        } finally {
            reader.close();
        }
    }

    private void initDirectionVector(int d, int a, int[] m) {
        int s = m.length - 1;
        for (int i = 1; i <= s; i++) {
            this.direction[d][i] = m[i] << (52 - i);
        }
        for (int i2 = s + 1; i2 <= 52; i2++) {
            this.direction[d][i2] = this.direction[d][i2 - s] ^ (this.direction[d][i2 - s] >> s);
            for (int k = 1; k <= s - 1; k++) {
                long[] jArr = this.direction[d];
                jArr[i2] = jArr[i2] ^ (((a >> ((s - 1) - k)) & 1) * this.direction[d][i2 - k]);
            }
        }
    }

    @Override // org.apache.commons.math3.random.RandomVectorGenerator
    public double[] nextVector() {
        double[] v = new double[this.dimension];
        if (this.count == 0) {
            this.count++;
            return v;
        }
        int c = 1;
        int value = this.count - 1;
        while ((value & 1) == 1) {
            value >>= 1;
            c++;
        }
        for (int i = 0; i < this.dimension; i++) {
            long[] jArr = this.x;
            jArr[i] = jArr[i] ^ this.direction[i][c];
            v[i] = this.x[i] / SCALE;
        }
        int i2 = this.count;
        this.count = i2 + 1;
        return v;
    }

    public double[] skipTo(int index) throws NotPositiveException {
        if (index == 0) {
            Arrays.fill(this.x, 0L);
        } else {
            int i = index - 1;
            long grayCode = (i >> 1) ^ i;
            for (int j = 0; j < this.dimension; j++) {
                long result = 0;
                for (int k = 1; k <= 52; k++) {
                    long shift = grayCode >> (k - 1);
                    if (shift == 0) {
                        break;
                    }
                    long ik = 1 & shift;
                    result ^= this.direction[j][k] * ik;
                }
                this.x[j] = result;
            }
        }
        this.count = index;
        return nextVector();
    }

    public int getNextIndex() {
        return this.count;
    }
}
