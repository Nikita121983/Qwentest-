package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class BlockRealMatrix extends AbstractRealMatrix implements Serializable {
    public static final int BLOCK_SIZE = 52;
    private static final long serialVersionUID = 4991895511313664478L;
    private final int blockColumns;
    private final int blockRows;
    private final double[][] blocks;
    private final int columns;
    private final int rows;

    public BlockRealMatrix(int rows, int columns) throws NotStrictlyPositiveException {
        super(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.blockRows = ((rows + 52) - 1) / 52;
        this.blockColumns = ((columns + 52) - 1) / 52;
        this.blocks = createBlocksLayout(rows, columns);
    }

    public BlockRealMatrix(double[][] rawData) throws DimensionMismatchException, NotStrictlyPositiveException {
        this(rawData.length, rawData[0].length, toBlocksLayout(rawData), false);
    }

    public BlockRealMatrix(int rows, int columns, double[][] blockData, boolean copyArray) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.blockRows = ((rows + 52) - 1) / 52;
        this.blockColumns = ((columns + 52) - 1) / 52;
        if (copyArray) {
            this.blocks = new double[this.blockRows * this.blockColumns];
        } else {
            this.blocks = blockData;
        }
        int index = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            int jBlock = 0;
            while (jBlock < this.blockColumns) {
                if (blockData[index].length != blockWidth(jBlock) * iHeight) {
                    throw new DimensionMismatchException(blockData[index].length, blockWidth(jBlock) * iHeight);
                }
                if (copyArray) {
                    this.blocks[index] = (double[]) blockData[index].clone();
                }
                jBlock++;
                index++;
            }
        }
    }

    public static double[][] toBlocksLayout(double[][] rawData) throws DimensionMismatchException {
        int rows = rawData.length;
        int columns = rawData[0].length;
        int blockRows = ((rows + 52) - 1) / 52;
        int blockColumns = ((columns + 52) - 1) / 52;
        for (double[] dArr : rawData) {
            int length = dArr.length;
            if (length != columns) {
                throw new DimensionMismatchException(columns, length);
            }
        }
        int i = blockRows * blockColumns;
        double[][] blocks = new double[i];
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, rows);
            int iHeight = pEnd - pStart;
            int jBlock = 0;
            while (jBlock < blockColumns) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, columns);
                int jWidth = qEnd - qStart;
                double[] block = new double[iHeight * jWidth];
                blocks[blockIndex] = block;
                int index = 0;
                int index2 = rows;
                int rows2 = pStart;
                while (rows2 < pEnd) {
                    int p = rows2;
                    System.arraycopy(rawData[p], qStart, block, index, jWidth);
                    index += jWidth;
                    rows2 = p + 1;
                }
                blockIndex++;
                jBlock++;
                rows = index2;
            }
        }
        return blocks;
    }

    public static double[][] createBlocksLayout(int rows, int columns) {
        int blockRows = ((rows + 52) - 1) / 52;
        int blockColumns = ((columns + 52) - 1) / 52;
        double[][] blocks = new double[blockRows * blockColumns];
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, rows);
            int iHeight = pEnd - pStart;
            for (int jBlock = 0; jBlock < blockColumns; jBlock++) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, columns);
                int jWidth = qEnd - qStart;
                blocks[blockIndex] = new double[iHeight * jWidth];
                blockIndex++;
            }
        }
        return blocks;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        return new BlockRealMatrix(rowDimension, columnDimension);
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix copy() {
        BlockRealMatrix copied = new BlockRealMatrix(this.rows, this.columns);
        for (int i = 0; i < this.blocks.length; i++) {
            System.arraycopy(this.blocks[i], 0, copied.blocks[i], 0, this.blocks[i].length);
        }
        return copied;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix add(RealMatrix m) throws MatrixDimensionMismatchException {
        try {
            return add((BlockRealMatrix) m);
        } catch (ClassCastException e) {
            MatrixUtils.checkAdditionCompatible(this, m);
            BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
                for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
                    double[] outBlock = out.blocks[blockIndex];
                    double[] tBlock = this.blocks[blockIndex];
                    int pStart = iBlock * 52;
                    int pEnd = FastMath.min(pStart + 52, this.rows);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    int k = 0;
                    for (int p = pStart; p < pEnd; p++) {
                        for (int q = qStart; q < qEnd; q++) {
                            outBlock[k] = tBlock[k] + m.getEntry(p, q);
                            k++;
                        }
                    }
                    blockIndex++;
                }
            }
            return out;
        }
    }

    public BlockRealMatrix add(BlockRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, m);
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            double[] mBlock = m.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; k++) {
                outBlock[k] = tBlock[k] + mBlock[k];
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix subtract(RealMatrix m) throws MatrixDimensionMismatchException {
        try {
            return subtract((BlockRealMatrix) m);
        } catch (ClassCastException e) {
            MatrixUtils.checkSubtractionCompatible(this, m);
            BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
            int blockIndex = 0;
            for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
                for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
                    double[] outBlock = out.blocks[blockIndex];
                    double[] tBlock = this.blocks[blockIndex];
                    int pStart = iBlock * 52;
                    int pEnd = FastMath.min(pStart + 52, this.rows);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    int k = 0;
                    for (int p = pStart; p < pEnd; p++) {
                        for (int q = qStart; q < qEnd; q++) {
                            outBlock[k] = tBlock[k] - m.getEntry(p, q);
                            k++;
                        }
                    }
                    blockIndex++;
                }
            }
            return out;
        }
    }

    public BlockRealMatrix subtract(BlockRealMatrix m) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, m);
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            double[] mBlock = m.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; k++) {
                outBlock[k] = tBlock[k] - mBlock[k];
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix scalarAdd(double d) {
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; k++) {
                outBlock[k] = tBlock[k] + d;
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public RealMatrix scalarMultiply(double d) {
        BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
        for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
            double[] outBlock = out.blocks[blockIndex];
            double[] tBlock = this.blocks[blockIndex];
            for (int k = 0; k < outBlock.length; k++) {
                outBlock[k] = tBlock[k] * d;
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix multiply(RealMatrix m) throws DimensionMismatchException {
        BlockRealMatrix blockRealMatrix = this;
        try {
            return blockRealMatrix.multiply((BlockRealMatrix) m);
        } catch (ClassCastException e) {
            cce = e;
            MatrixUtils.checkMultiplicationCompatible(this, m);
            BlockRealMatrix out = new BlockRealMatrix(blockRealMatrix.rows, m.getColumnDimension());
            int blockIndex = 0;
            int iBlock = 0;
            while (iBlock < out.blockRows) {
                int pStart = iBlock * 52;
                int pEnd = FastMath.min(pStart + 52, blockRealMatrix.rows);
                int jBlock = 0;
                while (jBlock < out.blockColumns) {
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, m.getColumnDimension());
                    double[] outBlock = out.blocks[blockIndex];
                    int kBlock = 0;
                    while (kBlock < blockRealMatrix.blockColumns) {
                        int kWidth = blockRealMatrix.blockWidth(kBlock);
                        double[] tBlock = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * iBlock) + kBlock];
                        int rStart = kBlock * 52;
                        int k = 0;
                        ClassCastException cce = cce;
                        int lEnd = pStart;
                        while (lEnd < pEnd) {
                            int lStart = (lEnd - pStart) * kWidth;
                            int p = lEnd;
                            int p2 = lStart + kWidth;
                            int q = qStart;
                            while (q < qEnd) {
                                double sum = 0.0d;
                                BlockRealMatrix out2 = out;
                                int r = rStart;
                                int blockIndex2 = blockIndex;
                                for (int blockIndex3 = lStart; blockIndex3 < p2; blockIndex3++) {
                                    sum += tBlock[blockIndex3] * m.getEntry(r, q);
                                    r++;
                                }
                                outBlock[k] = outBlock[k] + sum;
                                k++;
                                q++;
                                out = out2;
                                blockIndex = blockIndex2;
                            }
                            lEnd = p + 1;
                        }
                        kBlock++;
                        blockRealMatrix = this;
                        cce = cce;
                    }
                    blockIndex++;
                    jBlock++;
                    blockRealMatrix = this;
                }
                iBlock++;
                blockRealMatrix = this;
            }
            return out;
        }
    }

    public BlockRealMatrix multiply(BlockRealMatrix m) throws DimensionMismatchException {
        double[] tBlock;
        BlockRealMatrix blockRealMatrix = this;
        BlockRealMatrix blockRealMatrix2 = m;
        MatrixUtils.checkMultiplicationCompatible(this, m);
        BlockRealMatrix out = new BlockRealMatrix(blockRealMatrix.rows, blockRealMatrix2.columns);
        int blockIndex = 0;
        int iBlock = 0;
        while (iBlock < out.blockRows) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, blockRealMatrix.rows);
            int jBlock = 0;
            while (jBlock < out.blockColumns) {
                int jWidth = out.blockWidth(jBlock);
                int jWidth2 = jWidth + jWidth;
                int jWidth3 = jWidth2 + jWidth;
                int jWidth4 = jWidth3 + jWidth;
                double[] outBlock = out.blocks[blockIndex];
                int kBlock = 0;
                while (kBlock < blockRealMatrix.blockColumns) {
                    int kWidth = blockRealMatrix.blockWidth(kBlock);
                    BlockRealMatrix out2 = out;
                    double[] tBlock2 = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * iBlock) + kBlock];
                    double[] mBlock = blockRealMatrix2.blocks[(blockRealMatrix2.blockColumns * kBlock) + jBlock];
                    int k = 0;
                    int lEnd = pStart;
                    while (lEnd < pEnd) {
                        int lStart = (lEnd - pStart) * kWidth;
                        int p = lEnd;
                        int p2 = lStart + kWidth;
                        int nStart = 0;
                        while (nStart < jWidth) {
                            double sum = 0.0d;
                            int n = nStart;
                            int l = nStart;
                            int nStart2 = lStart;
                            while (true) {
                                tBlock = tBlock2;
                                if (nStart2 >= p2 - 3) {
                                    break;
                                }
                                sum += (tBlock[nStart2] * mBlock[n]) + (tBlock[nStart2 + 1] * mBlock[n + jWidth]) + (tBlock[nStart2 + 2] * mBlock[n + jWidth2]) + (tBlock[nStart2 + 3] * mBlock[n + jWidth3]);
                                nStart2 += 4;
                                n += jWidth4;
                                tBlock2 = tBlock;
                            }
                            while (nStart2 < p2) {
                                int l2 = nStart2 + 1;
                                sum += tBlock[nStart2] * mBlock[n];
                                n += jWidth;
                                nStart2 = l2;
                            }
                            outBlock[k] = outBlock[k] + sum;
                            k++;
                            nStart = l + 1;
                            tBlock2 = tBlock;
                        }
                        lEnd = p + 1;
                    }
                    kBlock++;
                    blockRealMatrix = this;
                    blockRealMatrix2 = m;
                    out = out2;
                }
                blockIndex++;
                jBlock++;
                blockRealMatrix = this;
                blockRealMatrix2 = m;
            }
            iBlock++;
            blockRealMatrix = this;
            blockRealMatrix2 = m;
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double[][] getData() {
        double[][] data = (double[][]) Array.newInstance((Class<?>) Double.TYPE, getRowDimension(), getColumnDimension());
        int lastColumns = this.columns - ((this.blockColumns - 1) * 52);
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            int regularPos = 0;
            int lastPos = 0;
            for (int p = pStart; p < pEnd; p++) {
                double[] dataP = data[p];
                int blockIndex = this.blockColumns * iBlock;
                int dataPos = 0;
                int jBlock = 0;
                while (jBlock < this.blockColumns - 1) {
                    System.arraycopy(this.blocks[blockIndex], regularPos, dataP, dataPos, 52);
                    dataPos += 52;
                    jBlock++;
                    blockIndex++;
                }
                System.arraycopy(this.blocks[blockIndex], lastPos, dataP, dataPos, lastColumns);
                regularPos += 52;
                lastPos += lastColumns;
            }
        }
        return data;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double getNorm() {
        double[] colSums = new double[52];
        double maxColSum = 0.0d;
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            Arrays.fill(colSums, 0, jWidth, 0.0d);
            for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
                int iHeight = blockHeight(iBlock);
                double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
                for (int j = 0; j < jWidth; j++) {
                    double sum = 0.0d;
                    for (int i = 0; i < iHeight; i++) {
                        sum += FastMath.abs(block[(i * jWidth) + j]);
                    }
                    colSums[j] = colSums[j] + sum;
                }
            }
            for (int j2 = 0; j2 < jWidth; j2++) {
                maxColSum = FastMath.max(maxColSum, colSums[j2]);
            }
        }
        return maxColSum;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double getFrobeniusNorm() {
        double sum2 = 0.0d;
        for (int blockIndex = 0; blockIndex < this.blocks.length; blockIndex++) {
            double[] arr$ = this.blocks[blockIndex];
            for (double entry : arr$) {
                sum2 += entry * entry;
            }
        }
        return FastMath.sqrt(sum2);
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0021 */
    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.linear.BlockRealMatrix getSubMatrix(int r28, int r29, int r30, int r31) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.exception.NumberIsTooSmallException {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.BlockRealMatrix.getSubMatrix(int, int, int, int):org.apache.commons.math3.linear.BlockRealMatrix");
    }

    private void copyBlockPart(double[] srcBlock, int srcWidth, int srcStartRow, int srcEndRow, int srcStartColumn, int srcEndColumn, double[] dstBlock, int dstWidth, int dstStartRow, int dstStartColumn) {
        int length = srcEndColumn - srcStartColumn;
        int srcPos = (srcStartRow * srcWidth) + srcStartColumn;
        int dstPos = (dstStartRow * dstWidth) + dstStartColumn;
        for (int srcRow = srcStartRow; srcRow < srcEndRow; srcRow++) {
            System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
            srcPos += srcWidth;
            dstPos += dstWidth;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setSubMatrix(double[][] subMatrix, int row, int column) throws OutOfRangeException, NoDataException, NullArgumentException, DimensionMismatchException {
        BlockRealMatrix blockRealMatrix = this;
        int i = row;
        int i2 = column;
        MathUtils.checkNotNull(subMatrix);
        int refLength = subMatrix[0].length;
        if (refLength == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        int endRow = (subMatrix.length + i) - 1;
        int endColumn = (i2 + refLength) - 1;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, i, endRow, i2, endColumn);
        for (double[] subRow : subMatrix) {
            if (subRow.length != refLength) {
                throw new DimensionMismatchException(refLength, subRow.length);
            }
        }
        int blockStartRow = i / 52;
        int blockEndRow = (endRow + 52) / 52;
        int blockStartColumn = i2 / 52;
        int blockEndColumn = (endColumn + 52) / 52;
        int iBlock = blockStartRow;
        while (iBlock < blockEndRow) {
            int iHeight = blockRealMatrix.blockHeight(iBlock);
            int firstRow = iBlock * 52;
            int iStart = FastMath.max(i, firstRow);
            int iEnd = FastMath.min(endRow + 1, firstRow + iHeight);
            int jBlock = blockStartColumn;
            while (jBlock < blockEndColumn) {
                int jWidth = blockRealMatrix.blockWidth(jBlock);
                int firstColumn = jBlock * 52;
                int jStart = FastMath.max(i2, firstColumn);
                int jEnd = FastMath.min(endColumn + 1, firstColumn + jWidth);
                int jLength = jEnd - jStart;
                double[] block = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * iBlock) + jBlock];
                int i3 = iStart;
                while (i3 < iEnd) {
                    int i4 = i3;
                    int iEnd2 = iEnd;
                    int iEnd3 = jStart - column;
                    System.arraycopy(subMatrix[i3 - row], iEnd3, block, ((i4 - firstRow) * jWidth) + (jStart - firstColumn), jLength);
                    i3 = i4 + 1;
                    iEnd = iEnd2;
                    refLength = refLength;
                }
                jBlock++;
                blockRealMatrix = this;
                i2 = column;
            }
            iBlock++;
            blockRealMatrix = this;
            i = row;
            i2 = column;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix getRowMatrix(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        BlockRealMatrix out = new BlockRealMatrix(1, this.columns);
        int iBlock = row / 52;
        int iRow = row - (iBlock * 52);
        int outBlockIndex = 0;
        int outIndex = 0;
        double[] outBlock = out.blocks[0];
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int available = outBlock.length - outIndex;
            if (jWidth > available) {
                System.arraycopy(block, iRow * jWidth, outBlock, outIndex, available);
                outBlockIndex++;
                outBlock = out.blocks[outBlockIndex];
                System.arraycopy(block, iRow * jWidth, outBlock, 0, jWidth - available);
                outIndex = jWidth - available;
            } else {
                System.arraycopy(block, iRow * jWidth, outBlock, outIndex, jWidth);
                outIndex += jWidth;
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setRowMatrix(int row, RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRowMatrix(row, (BlockRealMatrix) matrix);
        } catch (ClassCastException e) {
            super.setRowMatrix(row, matrix);
        }
    }

    public void setRowMatrix(int row, BlockRealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = getColumnDimension();
        if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        int iBlock = row / 52;
        int iRow = row - (iBlock * 52);
        int mBlockIndex = 0;
        int mIndex = 0;
        double[] mBlock = matrix.blocks[0];
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int available = mBlock.length - mIndex;
            if (jWidth > available) {
                System.arraycopy(mBlock, mIndex, block, iRow * jWidth, available);
                mBlockIndex++;
                mBlock = matrix.blocks[mBlockIndex];
                System.arraycopy(mBlock, 0, block, iRow * jWidth, jWidth - available);
                mIndex = jWidth - available;
            } else {
                System.arraycopy(mBlock, mIndex, block, iRow * jWidth, jWidth);
                mIndex += jWidth;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix getColumnMatrix(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        BlockRealMatrix out = new BlockRealMatrix(this.rows, 1);
        int jBlock = column / 52;
        int jColumn = column - (jBlock * 52);
        int jWidth = blockWidth(jBlock);
        int outBlockIndex = 0;
        int outIndex = 0;
        double[] outBlock = out.blocks[0];
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int i = 0;
            while (i < iHeight) {
                if (outIndex >= outBlock.length) {
                    outBlockIndex++;
                    outBlock = out.blocks[outBlockIndex];
                    outIndex = 0;
                }
                outBlock[outIndex] = block[(i * jWidth) + jColumn];
                i++;
                outIndex++;
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setColumnMatrix(int column, RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumnMatrix(column, (BlockRealMatrix) matrix);
        } catch (ClassCastException e) {
            super.setColumnMatrix(column, matrix);
        }
    }

    void setColumnMatrix(int column, BlockRealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = getRowDimension();
        if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        int jBlock = column / 52;
        int jColumn = column - (jBlock * 52);
        int jWidth = blockWidth(jBlock);
        int mBlockIndex = 0;
        int mIndex = 0;
        double[] mBlock = matrix.blocks[0];
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int i = 0;
            while (i < iHeight) {
                if (mIndex >= mBlock.length) {
                    mBlockIndex++;
                    mBlock = matrix.blocks[mBlockIndex];
                    mIndex = 0;
                }
                block[(i * jWidth) + jColumn] = mBlock[mIndex];
                i++;
                mIndex++;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public RealVector getRowVector(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        double[] outData = new double[this.columns];
        int iBlock = row / 52;
        int iRow = row - (iBlock * 52);
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            System.arraycopy(block, iRow * jWidth, outData, outIndex, jWidth);
            outIndex += jWidth;
        }
        return new ArrayRealVector(outData, false);
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setRowVector(int row, RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRow(row, ((ArrayRealVector) vector).getDataRef());
        } catch (ClassCastException e) {
            super.setRowVector(row, vector);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public RealVector getColumnVector(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        double[] outData = new double[this.rows];
        int jBlock = column / 52;
        int jColumn = column - (jBlock * 52);
        int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int i = 0;
            while (i < iHeight) {
                outData[outIndex] = block[(i * jWidth) + jColumn];
                i++;
                outIndex++;
            }
        }
        return new ArrayRealVector(outData, false);
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setColumnVector(int column, RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(column, ((ArrayRealVector) vector).getDataRef());
        } catch (ClassCastException e) {
            super.setColumnVector(column, vector);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double[] getRow(int row) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, row);
        double[] out = new double[this.columns];
        int iBlock = row / 52;
        int iRow = row - (iBlock * 52);
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            System.arraycopy(block, iRow * jWidth, out, outIndex, jWidth);
            outIndex += jWidth;
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setRow(int row, double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, row);
        int nCols = getColumnDimension();
        if (array.length != nCols) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        int iBlock = row / 52;
        int iRow = row - (iBlock * 52);
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            System.arraycopy(array, outIndex, block, iRow * jWidth, jWidth);
            outIndex += jWidth;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double[] getColumn(int column) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, column);
        double[] out = new double[this.rows];
        int jBlock = column / 52;
        int jColumn = column - (jBlock * 52);
        int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int i = 0;
            while (i < iHeight) {
                out[outIndex] = block[(i * jWidth) + jColumn];
                i++;
                outIndex++;
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setColumn(int column, double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, column);
        int nRows = getRowDimension();
        if (array.length != nRows) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        int jBlock = column / 52;
        int jColumn = column - (jBlock * 52);
        int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int i = 0;
            while (i < iHeight) {
                block[(i * jWidth) + jColumn] = array[outIndex];
                i++;
                outIndex++;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double getEntry(int row, int column) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = ((row - (iBlock * 52)) * blockWidth(jBlock)) + (column - (jBlock * 52));
        return this.blocks[(this.blockColumns * iBlock) + jBlock][k];
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void setEntry(int row, int column, double value) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = ((row - (iBlock * 52)) * blockWidth(jBlock)) + (column - (jBlock * 52));
        this.blocks[(this.blockColumns * iBlock) + jBlock][k] = value;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = ((row - (iBlock * 52)) * blockWidth(jBlock)) + (column - (jBlock * 52));
        double[] dArr = this.blocks[(this.blockColumns * iBlock) + jBlock];
        dArr[k] = dArr[k] + increment;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, row, column);
        int iBlock = row / 52;
        int jBlock = column / 52;
        int k = ((row - (iBlock * 52)) * blockWidth(jBlock)) + (column - (jBlock * 52));
        double[] dArr = this.blocks[(this.blockColumns * iBlock) + jBlock];
        dArr[k] = dArr[k] * factor;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public BlockRealMatrix transpose() {
        BlockRealMatrix blockRealMatrix = this;
        int nRows = blockRealMatrix.getRowDimension();
        int nCols = blockRealMatrix.getColumnDimension();
        BlockRealMatrix out = new BlockRealMatrix(nCols, nRows);
        int blockIndex = 0;
        int iBlock = 0;
        while (iBlock < blockRealMatrix.blockColumns) {
            int jBlock = 0;
            while (jBlock < blockRealMatrix.blockRows) {
                double[] outBlock = out.blocks[blockIndex];
                double[] tBlock = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * jBlock) + iBlock];
                int pStart = iBlock * 52;
                int pEnd = FastMath.min(pStart + 52, blockRealMatrix.columns);
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, blockRealMatrix.rows);
                int k = 0;
                for (int p = pStart; p < pEnd; p++) {
                    int lInc = pEnd - pStart;
                    int l = p - pStart;
                    for (int q = qStart; q < qEnd; q++) {
                        outBlock[k] = tBlock[l];
                        k++;
                        l += lInc;
                    }
                }
                blockIndex++;
                jBlock++;
                blockRealMatrix = this;
            }
            iBlock++;
            blockRealMatrix = this;
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealLinearOperator, org.apache.commons.math3.linear.AnyMatrix
    public int getRowDimension() {
        return this.rows;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealLinearOperator, org.apache.commons.math3.linear.AnyMatrix
    public int getColumnDimension() {
        return this.columns;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double[] operate(double[] v) throws DimensionMismatchException {
        if (v.length != this.columns) {
            throw new DimensionMismatchException(v.length, this.columns);
        }
        double[] out = new double[this.rows];
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, this.columns);
                int k = 0;
                for (int p = pStart; p < pEnd; p++) {
                    double sum = 0.0d;
                    int q = qStart;
                    while (q < qEnd - 3) {
                        sum += (block[k] * v[q]) + (block[k + 1] * v[q + 1]) + (block[k + 2] * v[q + 2]) + (block[k + 3] * v[q + 3]);
                        k += 4;
                        q += 4;
                    }
                    while (q < qEnd) {
                        sum += block[k] * v[q];
                        q++;
                        k++;
                    }
                    out[p] = out[p] + sum;
                }
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double[] preMultiply(double[] v) throws DimensionMismatchException {
        int jBlock;
        if (v.length != this.rows) {
            throw new DimensionMismatchException(v.length, this.rows);
        }
        double[] out = new double[this.columns];
        int jBlock2 = 0;
        while (jBlock2 < this.blockColumns) {
            int jWidth = blockWidth(jBlock2);
            int jWidth2 = jWidth + jWidth;
            int jWidth3 = jWidth2 + jWidth;
            int jWidth4 = jWidth3 + jWidth;
            int qStart = jBlock2 * 52;
            int qEnd = FastMath.min(qStart + 52, this.columns);
            for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
                double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock2];
                int pStart = iBlock * 52;
                int pEnd = FastMath.min(pStart + 52, this.rows);
                int q = qStart;
                while (q < qEnd) {
                    int k = q - qStart;
                    double sum = 0.0d;
                    double[] out2 = out;
                    int p = pStart;
                    while (true) {
                        jBlock = jBlock2;
                        int jBlock3 = pEnd - 3;
                        if (p >= jBlock3) {
                            break;
                        }
                        sum += (block[k] * v[p]) + (block[k + jWidth] * v[p + 1]) + (block[k + jWidth2] * v[p + 2]) + (block[k + jWidth3] * v[p + 3]);
                        k += jWidth4;
                        p += 4;
                        jBlock2 = jBlock;
                    }
                    while (p < pEnd) {
                        sum += block[k] * v[p];
                        k += jWidth;
                        p++;
                    }
                    out2[q] = out2[q] + sum;
                    q++;
                    out = out2;
                    jBlock2 = jBlock;
                }
            }
            jBlock2++;
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInRowOrder(RealMatrixChangingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int p = pStart; p < pEnd; p++) {
                for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                    int jWidth = blockWidth(jBlock);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
                    int k = (p - pStart) * jWidth;
                    for (int q = qStart; q < qEnd; q++) {
                        block[k] = visitor.visit(p, q, block[k]);
                        k++;
                    }
                }
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInRowOrder(RealMatrixPreservingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int p = pStart; p < pEnd; p++) {
                for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                    int jWidth = blockWidth(jBlock);
                    int qStart = jBlock * 52;
                    int qEnd = FastMath.min(qStart + 52, this.columns);
                    double[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
                    int k = (p - pStart) * jWidth;
                    for (int q = qStart; q < qEnd; q++) {
                        visitor.visit(p, q, block[k]);
                        k++;
                    }
                }
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockRealMatrix blockRealMatrix = this;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, startRow, endRow, startColumn, endColumn);
        visitor.start(blockRealMatrix.rows, blockRealMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 52;
        while (iBlock < (endRow / 52) + 1) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, endRow + 1);
            int p = pStart;
            while (p < pEnd) {
                int jBlock = startColumn / 52;
                while (jBlock < (endColumn / 52) + 1) {
                    int jWidth = blockRealMatrix.blockWidth(jBlock);
                    int q0 = jBlock * 52;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 52, endColumn + 1);
                    double[] block = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * iBlock) + jBlock];
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        block[k] = visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                        p0 = p0;
                    }
                    jBlock++;
                    blockRealMatrix = this;
                    p0 = p0;
                }
                p++;
                blockRealMatrix = this;
                p0 = p0;
            }
            iBlock++;
            blockRealMatrix = this;
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockRealMatrix blockRealMatrix = this;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, startRow, endRow, startColumn, endColumn);
        visitor.start(blockRealMatrix.rows, blockRealMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 52;
        while (iBlock < (endRow / 52) + 1) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, endRow + 1);
            int p = pStart;
            while (p < pEnd) {
                int jBlock = startColumn / 52;
                while (jBlock < (endColumn / 52) + 1) {
                    int jWidth = blockRealMatrix.blockWidth(jBlock);
                    int q0 = jBlock * 52;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 52, endColumn + 1);
                    double[] block = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * iBlock) + jBlock];
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                        p0 = p0;
                    }
                    jBlock++;
                    blockRealMatrix = this;
                    p0 = p0;
                }
                p++;
                blockRealMatrix = this;
                p0 = p0;
            }
            iBlock++;
            blockRealMatrix = this;
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, this.columns);
                double[] block = this.blocks[blockIndex];
                int k = 0;
                for (int p = pStart; p < pEnd; p++) {
                    for (int q = qStart; q < qEnd; q++) {
                        block[k] = visitor.visit(p, q, block[k]);
                        k++;
                    }
                }
                blockIndex++;
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 52;
            int pEnd = FastMath.min(pStart + 52, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                int qStart = jBlock * 52;
                int qEnd = FastMath.min(qStart + 52, this.columns);
                double[] block = this.blocks[blockIndex];
                int k = 0;
                for (int p = pStart; p < pEnd; p++) {
                    for (int q = qStart; q < qEnd; q++) {
                        visitor.visit(p, q, block[k]);
                        k++;
                    }
                }
                blockIndex++;
            }
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockRealMatrix blockRealMatrix = this;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, startRow, endRow, startColumn, endColumn);
        visitor.start(blockRealMatrix.rows, blockRealMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 52;
        while (iBlock < (endRow / 52) + 1) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, endRow + 1);
            int jBlock = startColumn / 52;
            while (jBlock < (endColumn / 52) + 1) {
                int jWidth = blockRealMatrix.blockWidth(jBlock);
                int q0 = jBlock * 52;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 52, endColumn + 1);
                double[] block = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * iBlock) + jBlock];
                int p = pStart;
                while (p < pEnd) {
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        block[k] = visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                        p0 = p0;
                    }
                    p++;
                    p0 = p0;
                }
                jBlock++;
                blockRealMatrix = this;
                p0 = p0;
            }
            iBlock++;
            blockRealMatrix = this;
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractRealMatrix, org.apache.commons.math3.linear.RealMatrix
    public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockRealMatrix blockRealMatrix = this;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, startRow, endRow, startColumn, endColumn);
        visitor.start(blockRealMatrix.rows, blockRealMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 52;
        while (iBlock < (endRow / 52) + 1) {
            int p0 = iBlock * 52;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 52, endRow + 1);
            int jBlock = startColumn / 52;
            while (jBlock < (endColumn / 52) + 1) {
                int jWidth = blockRealMatrix.blockWidth(jBlock);
                int q0 = jBlock * 52;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 52, endColumn + 1);
                double[] block = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * iBlock) + jBlock];
                int p = pStart;
                while (p < pEnd) {
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                        p0 = p0;
                    }
                    p++;
                    p0 = p0;
                }
                jBlock++;
                blockRealMatrix = this;
                p0 = p0;
            }
            iBlock++;
            blockRealMatrix = this;
        }
        return visitor.end();
    }

    private int blockHeight(int blockRow) {
        if (blockRow == this.blockRows - 1) {
            return this.rows - (blockRow * 52);
        }
        return 52;
    }

    private int blockWidth(int blockColumn) {
        if (blockColumn == this.blockColumns - 1) {
            return this.columns - (blockColumn * 52);
        }
        return 52;
    }
}
