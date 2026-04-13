package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class BlockFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
    public static final int BLOCK_SIZE = 36;
    private static final long serialVersionUID = -4602336630143123183L;
    private final int blockColumns;
    private final int blockRows;
    private final T[][] blocks;
    private final int columns;
    private final int rows;

    public BlockFieldMatrix(Field<T> field, int i, int i2) throws NotStrictlyPositiveException {
        super(field, i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = ((i + 36) - 1) / 36;
        this.blockColumns = ((i2 + 36) - 1) / 36;
        this.blocks = (T[][]) createBlocksLayout(field, i, i2);
    }

    public BlockFieldMatrix(T[][] rawData) throws DimensionMismatchException {
        this(rawData.length, rawData[0].length, toBlocksLayout(rawData), false);
    }

    public BlockFieldMatrix(int i, int i2, T[][] tArr, boolean z) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(extractField(tArr), i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = ((i + 36) - 1) / 36;
        this.blockColumns = ((i2 + 36) - 1) / 36;
        if (z) {
            this.blocks = (T[][]) ((FieldElement[][]) MathArrays.buildArray(getField(), this.blockRows * this.blockColumns, -1));
        } else {
            this.blocks = tArr;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.blockRows; i4++) {
            int blockHeight = blockHeight(i4);
            int i5 = 0;
            while (i5 < this.blockColumns) {
                if (tArr[i3].length != blockWidth(i5) * blockHeight) {
                    throw new DimensionMismatchException(tArr[i3].length, blockWidth(i5) * blockHeight);
                }
                if (z) {
                    ((T[][]) this.blocks)[i3] = (FieldElement[]) tArr[i3].clone();
                }
                i5++;
                i3++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends FieldElement<T>> T[][] toBlocksLayout(T[][] tArr) throws DimensionMismatchException {
        int length = tArr.length;
        int length2 = tArr[0].length;
        int i = ((length + 36) - 1) / 36;
        int i2 = ((length2 + 36) - 1) / 36;
        for (T[] tArr2 : tArr) {
            int length3 = tArr2.length;
            if (length3 != length2) {
                throw new DimensionMismatchException(length2, length3);
            }
        }
        Field extractField = extractField(tArr);
        T[][] tArr3 = (T[][]) ((FieldElement[][]) MathArrays.buildArray(extractField, i * i2, -1));
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i4 * 36;
            int min = FastMath.min(i5 + 36, length);
            int i6 = min - i5;
            int i7 = 0;
            while (i7 < i2) {
                int i8 = i7 * 36;
                int min2 = FastMath.min(i8 + 36, length2) - i8;
                FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(extractField, i6 * min2);
                tArr3[i3] = fieldElementArr;
                int i9 = length;
                int i10 = 0;
                int i11 = length2;
                int i12 = i5;
                while (i12 < min) {
                    int i13 = i12;
                    System.arraycopy(tArr[i13], i8, fieldElementArr, i10, min2);
                    i10 += min2;
                    i12 = i13 + 1;
                }
                i3++;
                i7++;
                length2 = i11;
                length = i9;
            }
        }
        return tArr3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends FieldElement<T>> T[][] createBlocksLayout(Field<T> field, int i, int i2) {
        int i3 = ((i + 36) - 1) / 36;
        int i4 = ((i2 + 36) - 1) / 36;
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(field, i3 * i4, -1));
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i6 * 36;
            int min = FastMath.min(i7 + 36, i) - i7;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = i8 * 36;
                tArr[i5] = (FieldElement[]) MathArrays.buildArray(field, min * (FastMath.min(i9 + 36, i2) - i9));
                i5++;
            }
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
        return new BlockFieldMatrix(getField(), rowDimension, columnDimension);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> copy() {
        BlockFieldMatrix<T> copied = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        for (int i = 0; i < this.blocks.length; i++) {
            System.arraycopy(this.blocks[i], 0, copied.blocks[i], 0, this.blocks[i].length);
        }
        return copied;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> add(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        try {
            return blockFieldMatrix.add((BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException e) {
            e = e;
            checkAdditionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix2 = new BlockFieldMatrix(blockFieldMatrix.getField(), blockFieldMatrix.rows, blockFieldMatrix.columns);
            int i = 0;
            int i2 = 0;
            while (i2 < blockFieldMatrix2.blockRows) {
                int i3 = 0;
                while (i3 < blockFieldMatrix2.blockColumns) {
                    FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix2.blocks)[i];
                    T[] tArr = blockFieldMatrix.blocks[i];
                    int i4 = i2 * 36;
                    int min = FastMath.min(i4 + 36, blockFieldMatrix.rows);
                    int i5 = i3 * 36;
                    int min2 = FastMath.min(i5 + 36, blockFieldMatrix.columns);
                    int i6 = 0;
                    for (int i7 = i4; i7 < min; i7++) {
                        int i8 = i5;
                        while (i8 < min2) {
                            fieldElementArr[i6] = (FieldElement) tArr[i6].add(fieldMatrix.getEntry(i7, i8));
                            i6++;
                            i8++;
                            e = e;
                        }
                    }
                    i++;
                    i3++;
                    blockFieldMatrix = this;
                }
                i2++;
                blockFieldMatrix = this;
            }
            return blockFieldMatrix2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BlockFieldMatrix<T> add(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkAdditionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix2.blocks.length; i++) {
            FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix2.blocks)[i];
            T[] tArr = this.blocks[i];
            T[] tArr2 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                fieldElementArr[i2] = (FieldElement) tArr[i2].add(tArr2[i2]);
            }
        }
        return blockFieldMatrix2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> subtract(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        try {
            return blockFieldMatrix.subtract((BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException e) {
            e = e;
            checkSubtractionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix2 = new BlockFieldMatrix(blockFieldMatrix.getField(), blockFieldMatrix.rows, blockFieldMatrix.columns);
            int i = 0;
            int i2 = 0;
            while (i2 < blockFieldMatrix2.blockRows) {
                int i3 = 0;
                while (i3 < blockFieldMatrix2.blockColumns) {
                    FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix2.blocks)[i];
                    T[] tArr = blockFieldMatrix.blocks[i];
                    int i4 = i2 * 36;
                    int min = FastMath.min(i4 + 36, blockFieldMatrix.rows);
                    int i5 = i3 * 36;
                    int min2 = FastMath.min(i5 + 36, blockFieldMatrix.columns);
                    int i6 = 0;
                    for (int i7 = i4; i7 < min; i7++) {
                        int i8 = i5;
                        while (i8 < min2) {
                            fieldElementArr[i6] = (FieldElement) tArr[i6].subtract(fieldMatrix.getEntry(i7, i8));
                            i6++;
                            i8++;
                            e = e;
                        }
                    }
                    i++;
                    i3++;
                    blockFieldMatrix = this;
                }
                i2++;
                blockFieldMatrix = this;
            }
            return blockFieldMatrix2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BlockFieldMatrix<T> subtract(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkSubtractionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix2.blocks.length; i++) {
            FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix2.blocks)[i];
            T[] tArr = this.blocks[i];
            T[] tArr2 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                fieldElementArr[i2] = (FieldElement) tArr[i2].subtract(tArr2[i2]);
            }
        }
        return blockFieldMatrix2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> scalarAdd(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix.blocks.length; i++) {
            FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix.blocks)[i];
            T[] tArr = this.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                fieldElementArr[i2] = (FieldElement) tArr[i2].add(t);
            }
        }
        return blockFieldMatrix;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> scalarMultiply(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix.blocks.length; i++) {
            FieldElement[] fieldElementArr = ((T[][]) blockFieldMatrix.blocks)[i];
            T[] tArr = this.blocks[i];
            for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                fieldElementArr[i2] = (FieldElement) tArr[i2].multiply(t);
            }
        }
        return blockFieldMatrix;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> multiply(FieldMatrix<T> m) throws DimensionMismatchException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        try {
            return blockFieldMatrix.multiply((BlockFieldMatrix) m);
        } catch (ClassCastException e) {
            cce = e;
            checkMultiplicationCompatible(m);
            BlockFieldMatrix<T> out = new BlockFieldMatrix<>(blockFieldMatrix.getField(), blockFieldMatrix.rows, m.getColumnDimension());
            T zero = blockFieldMatrix.getField().getZero();
            int blockIndex = 0;
            int iBlock = 0;
            while (iBlock < out.blockRows) {
                int pStart = iBlock * 36;
                int pEnd = FastMath.min(pStart + 36, blockFieldMatrix.rows);
                int jBlock = 0;
                while (jBlock < out.blockColumns) {
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, m.getColumnDimension());
                    FieldElement[] fieldElementArr = out.blocks[blockIndex];
                    int kBlock = 0;
                    while (kBlock < blockFieldMatrix.blockColumns) {
                        int kWidth = blockFieldMatrix.blockWidth(kBlock);
                        ClassCastException cce = cce;
                        T[] tBlock = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * iBlock) + kBlock];
                        int rStart = kBlock * 36;
                        int k = 0;
                        int lEnd = pStart;
                        while (lEnd < pEnd) {
                            int lStart = (lEnd - pStart) * kWidth;
                            int p = lEnd;
                            int lEnd2 = lStart + kWidth;
                            int q = qStart;
                            while (q < qEnd) {
                                T sum = zero;
                                BlockFieldMatrix<T> out2 = out;
                                FieldElement fieldElement = sum;
                                int r = rStart;
                                int r2 = blockIndex;
                                int blockIndex2 = lStart;
                                while (blockIndex2 < lEnd2) {
                                    int l = blockIndex2;
                                    fieldElement = (FieldElement) fieldElement.add(tBlock[blockIndex2].multiply(m.getEntry(r, q)));
                                    r++;
                                    blockIndex2 = l + 1;
                                    lEnd2 = lEnd2;
                                }
                                fieldElementArr[k] = (FieldElement) fieldElementArr[k].add(fieldElement);
                                k++;
                                q++;
                                zero = sum;
                                blockIndex = r2;
                                lEnd2 = lEnd2;
                                out = out2;
                            }
                            lEnd = p + 1;
                        }
                        kBlock++;
                        blockFieldMatrix = this;
                        cce = cce;
                    }
                    blockIndex++;
                    jBlock++;
                    blockFieldMatrix = this;
                }
                iBlock++;
                blockFieldMatrix = this;
            }
            return out;
        }
    }

    public BlockFieldMatrix<T> multiply(BlockFieldMatrix<T> m) throws DimensionMismatchException {
        T zero;
        BlockFieldMatrix<T> blockFieldMatrix = this;
        BlockFieldMatrix<T> blockFieldMatrix2 = m;
        checkMultiplicationCompatible(m);
        BlockFieldMatrix<T> out = new BlockFieldMatrix<>(blockFieldMatrix.getField(), blockFieldMatrix.rows, blockFieldMatrix2.columns);
        T zero2 = blockFieldMatrix.getField().getZero();
        int blockIndex = 0;
        int iBlock = 0;
        while (iBlock < out.blockRows) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, blockFieldMatrix.rows);
            int jBlock = 0;
            while (jBlock < out.blockColumns) {
                int jWidth = out.blockWidth(jBlock);
                int jWidth2 = jWidth + jWidth;
                int jWidth3 = jWidth2 + jWidth;
                int jWidth4 = jWidth3 + jWidth;
                FieldElement[] fieldElementArr = out.blocks[blockIndex];
                int kBlock = 0;
                while (kBlock < blockFieldMatrix.blockColumns) {
                    int kWidth = blockFieldMatrix.blockWidth(kBlock);
                    BlockFieldMatrix<T> out2 = out;
                    T[] tBlock = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * iBlock) + kBlock];
                    T[] mBlock = blockFieldMatrix2.blocks[(blockFieldMatrix2.blockColumns * kBlock) + jBlock];
                    int k = 0;
                    int lEnd = pStart;
                    while (lEnd < pEnd) {
                        int lStart = (lEnd - pStart) * kWidth;
                        int p = lEnd;
                        int lEnd2 = lStart + kWidth;
                        int nStart = 0;
                        while (nStart < jWidth) {
                            T sum = zero2;
                            int n = nStart;
                            int nStart2 = nStart;
                            FieldElement fieldElement = sum;
                            T[] tBlock2 = tBlock;
                            int l = lStart;
                            while (true) {
                                zero = zero2;
                                if (l >= lEnd2 - 3) {
                                    break;
                                }
                                int l2 = l;
                                FieldElement fieldElement2 = (FieldElement) fieldElement.add(tBlock2[l].multiply(mBlock[n]));
                                T t = tBlock2[l2 + 1];
                                T sum2 = mBlock[n + jWidth];
                                fieldElement = (FieldElement) ((FieldElement) ((FieldElement) fieldElement2.add(t.multiply(sum2))).add(tBlock2[l2 + 2].multiply(mBlock[n + jWidth2]))).add(tBlock2[l2 + 3].multiply(mBlock[n + jWidth3]));
                                l = l2 + 4;
                                n += jWidth4;
                                zero2 = zero;
                            }
                            while (l < lEnd2) {
                                fieldElement = (FieldElement) fieldElement.add(tBlock2[l].multiply(mBlock[n]));
                                n += jWidth;
                                l++;
                                lEnd2 = lEnd2;
                            }
                            fieldElementArr[k] = (FieldElement) fieldElementArr[k].add(fieldElement);
                            k++;
                            nStart = nStart2 + 1;
                            tBlock = tBlock2;
                            zero2 = zero;
                            lEnd2 = lEnd2;
                        }
                        lEnd = p + 1;
                    }
                    kBlock++;
                    blockFieldMatrix = this;
                    blockFieldMatrix2 = m;
                    out = out2;
                }
                blockIndex++;
                jBlock++;
                blockFieldMatrix = this;
                blockFieldMatrix2 = m;
            }
            iBlock++;
            blockFieldMatrix = this;
            blockFieldMatrix2 = m;
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[][] getData() {
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(getField(), getRowDimension(), getColumnDimension()));
        int i = this.columns - ((this.blockColumns - 1) * 36);
        for (int i2 = 0; i2 < this.blockRows; i2++) {
            int i3 = i2 * 36;
            int min = FastMath.min(i3 + 36, this.rows);
            int i4 = 0;
            int i5 = 0;
            for (int i6 = i3; i6 < min; i6++) {
                T[] tArr2 = tArr[i6];
                int i7 = this.blockColumns * i2;
                int i8 = 0;
                int i9 = 0;
                while (i9 < this.blockColumns - 1) {
                    System.arraycopy(this.blocks[i7], i4, tArr2, i8, 36);
                    i8 += 36;
                    i9++;
                    i7++;
                }
                System.arraycopy(this.blocks[i7], i5, tArr2, i8, i);
                i4 += 36;
                i5 += i;
            }
        }
        return tArr;
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0025 */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.linear.FieldMatrix<T> getSubMatrix(int r28, int r29, int r30, int r31) throws org.apache.commons.math3.exception.OutOfRangeException, org.apache.commons.math3.exception.NumberIsTooSmallException {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.BlockFieldMatrix.getSubMatrix(int, int, int, int):org.apache.commons.math3.linear.FieldMatrix");
    }

    private void copyBlockPart(T[] srcBlock, int srcWidth, int srcStartRow, int srcEndRow, int srcStartColumn, int srcEndColumn, T[] dstBlock, int dstWidth, int dstStartRow, int dstStartColumn) {
        int length = srcEndColumn - srcStartColumn;
        int srcPos = (srcStartRow * srcWidth) + srcStartColumn;
        int dstPos = (dstStartRow * dstWidth) + dstStartColumn;
        for (int srcRow = srcStartRow; srcRow < srcEndRow; srcRow++) {
            System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
            srcPos += srcWidth;
            dstPos += dstWidth;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setSubMatrix(T[][] subMatrix, int row, int column) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        int i = row;
        int i2 = column;
        MathUtils.checkNotNull(subMatrix);
        int refLength = subMatrix[0].length;
        if (refLength == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        int endRow = (subMatrix.length + i) - 1;
        int endColumn = (i2 + refLength) - 1;
        blockFieldMatrix.checkSubMatrixIndex(i, endRow, i2, endColumn);
        for (T[] subRow : subMatrix) {
            if (subRow.length != refLength) {
                throw new DimensionMismatchException(refLength, subRow.length);
            }
        }
        int blockStartRow = i / 36;
        int blockEndRow = (endRow + 36) / 36;
        int blockStartColumn = i2 / 36;
        int blockEndColumn = (endColumn + 36) / 36;
        int iBlock = blockStartRow;
        while (iBlock < blockEndRow) {
            int iHeight = blockFieldMatrix.blockHeight(iBlock);
            int firstRow = iBlock * 36;
            int iStart = FastMath.max(i, firstRow);
            int iEnd = FastMath.min(endRow + 1, firstRow + iHeight);
            int jBlock = blockStartColumn;
            while (jBlock < blockEndColumn) {
                int jWidth = blockFieldMatrix.blockWidth(jBlock);
                int firstColumn = jBlock * 36;
                int jStart = FastMath.max(i2, firstColumn);
                int jEnd = FastMath.min(endColumn + 1, firstColumn + jWidth);
                int jLength = jEnd - jStart;
                T[] block = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * iBlock) + jBlock];
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
                blockFieldMatrix = this;
                i2 = column;
            }
            iBlock++;
            blockFieldMatrix = this;
            i = row;
            i2 = column;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getRowMatrix(int row) throws OutOfRangeException {
        checkRowIndex(row);
        BlockFieldMatrix<T> out = new BlockFieldMatrix<>(getField(), 1, this.columns);
        int iBlock = row / 36;
        int iRow = row - (iBlock * 36);
        int outBlockIndex = 0;
        int outIndex = 0;
        T[] outBlock = out.blocks[0];
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setRowMatrix(int row, FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRowMatrix(row, (BlockFieldMatrix) matrix);
        } catch (ClassCastException e) {
            super.setRowMatrix(row, matrix);
        }
    }

    public void setRowMatrix(int row, BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkRowIndex(row);
        int nCols = getColumnDimension();
        if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
        }
        int iBlock = row / 36;
        int iRow = row - (iBlock * 36);
        int mBlockIndex = 0;
        int mIndex = 0;
        T[] mBlock = matrix.blocks[0];
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> getColumnMatrix(int column) throws OutOfRangeException {
        checkColumnIndex(column);
        BlockFieldMatrix<T> out = new BlockFieldMatrix<>(getField(), this.rows, 1);
        int jBlock = column / 36;
        int jColumn = column - (jBlock * 36);
        int jWidth = blockWidth(jBlock);
        int outBlockIndex = 0;
        int outIndex = 0;
        T[] outBlock = out.blocks[0];
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setColumnMatrix(int column, FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setColumnMatrix(column, (BlockFieldMatrix) matrix);
        } catch (ClassCastException e) {
            super.setColumnMatrix(column, matrix);
        }
    }

    void setColumnMatrix(int column, BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkColumnIndex(column);
        int nRows = getRowDimension();
        if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1) {
            throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
        }
        int jBlock = column / 36;
        int jColumn = column - (jBlock * 36);
        int jWidth = blockWidth(jBlock);
        int mBlockIndex = 0;
        int mIndex = 0;
        T[] mBlock = matrix.blocks[0];
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> getRowVector(int row) throws OutOfRangeException {
        checkRowIndex(row);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.columns);
        int iBlock = row / 36;
        int iRow = row - (iBlock * 36);
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            System.arraycopy(block, iRow * jWidth, fieldElementArr, outIndex, jWidth);
            outIndex += jWidth;
        }
        return new ArrayFieldVector((Field) getField(), fieldElementArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setRowVector(int row, FieldVector<T> vector) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRow(row, ((ArrayFieldVector) vector).getDataRef());
        } catch (ClassCastException e) {
            super.setRowVector(row, vector);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldVector<T> getColumnVector(int column) throws OutOfRangeException {
        checkColumnIndex(column);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.rows);
        int jBlock = column / 36;
        int jColumn = column - (jBlock * 36);
        int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int i = 0;
            while (i < iHeight) {
                fieldElementArr[outIndex] = block[(i * jWidth) + jColumn];
                i++;
                outIndex++;
            }
        }
        return new ArrayFieldVector((Field) getField(), fieldElementArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setColumnVector(int column, FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(column, ((ArrayFieldVector) vector).getDataRef());
        } catch (ClassCastException e) {
            super.setColumnVector(column, vector);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] getRow(int i) throws OutOfRangeException {
        checkRowIndex(i);
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), this.columns));
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, tArr, i4, blockWidth);
            i4 += blockWidth;
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setRow(int row, T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(row);
        int nCols = getColumnDimension();
        if (array.length != nCols) {
            throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
        }
        int iBlock = row / 36;
        int iRow = row - (iBlock * 36);
        int outIndex = 0;
        for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
            int jWidth = blockWidth(jBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            System.arraycopy(array, outIndex, block, iRow * jWidth, jWidth);
            outIndex += jWidth;
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] getColumn(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(getField(), this.rows));
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockRows; i5++) {
            int blockHeight = blockHeight(i5);
            T[] tArr2 = this.blocks[(this.blockColumns * i5) + i2];
            int i6 = 0;
            while (i6 < blockHeight) {
                tArr[i4] = tArr2[(i6 * blockWidth) + i3];
                i6++;
                i4++;
            }
        }
        return tArr;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setColumn(int column, T[] array) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkColumnIndex(column);
        int nRows = getRowDimension();
        if (array.length != nRows) {
            throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
        }
        int jBlock = column / 36;
        int jColumn = column - (jBlock * 36);
        int jWidth = blockWidth(jBlock);
        int outIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int iHeight = blockHeight(iBlock);
            T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
            int i = 0;
            while (i < iHeight) {
                block[(i * jWidth) + jColumn] = array[outIndex];
                i++;
                outIndex++;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T getEntry(int row, int column) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = ((row - (iBlock * 36)) * blockWidth(jBlock)) + (column - (jBlock * 36));
        return this.blocks[(this.blockColumns * iBlock) + jBlock][k];
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setEntry(int row, int column, T value) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = ((row - (iBlock * 36)) * blockWidth(jBlock)) + (column - (jBlock * 36));
        this.blocks[(this.blockColumns * iBlock) + jBlock][k] = value;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void addToEntry(int row, int column, T increment) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = ((row - (iBlock * 36)) * blockWidth(jBlock)) + (column - (jBlock * 36));
        FieldElement[] fieldElementArr = this.blocks[(this.blockColumns * iBlock) + jBlock];
        fieldElementArr[k] = (FieldElement) fieldElementArr[k].add(increment);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void multiplyEntry(int row, int column, T factor) throws OutOfRangeException {
        checkRowIndex(row);
        checkColumnIndex(column);
        int iBlock = row / 36;
        int jBlock = column / 36;
        int k = ((row - (iBlock * 36)) * blockWidth(jBlock)) + (column - (jBlock * 36));
        FieldElement[] fieldElementArr = this.blocks[(this.blockColumns * iBlock) + jBlock];
        fieldElementArr[k] = (FieldElement) fieldElementArr[k].multiply(factor);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> transpose() {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        int nRows = blockFieldMatrix.getRowDimension();
        int nCols = blockFieldMatrix.getColumnDimension();
        BlockFieldMatrix<T> out = new BlockFieldMatrix<>(blockFieldMatrix.getField(), nCols, nRows);
        int blockIndex = 0;
        int iBlock = 0;
        while (iBlock < blockFieldMatrix.blockColumns) {
            int jBlock = 0;
            while (jBlock < blockFieldMatrix.blockRows) {
                T[] outBlock = out.blocks[blockIndex];
                T[] tBlock = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * jBlock) + iBlock];
                int pStart = iBlock * 36;
                int pEnd = FastMath.min(pStart + 36, blockFieldMatrix.columns);
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, blockFieldMatrix.rows);
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
                blockFieldMatrix = this;
            }
            iBlock++;
            blockFieldMatrix = this;
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getRowDimension() {
        return this.rows;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getColumnDimension() {
        return this.columns;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] operate(T[] tArr) throws DimensionMismatchException {
        if (tArr.length == this.columns) {
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.rows);
            T zero = getField().getZero();
            for (int i = 0; i < this.blockRows; i++) {
                int i2 = i * 36;
                int min = FastMath.min(i2 + 36, this.rows);
                for (int i3 = 0; i3 < this.blockColumns; i3++) {
                    T[] tArr2 = this.blocks[(this.blockColumns * i) + i3];
                    int i4 = i3 * 36;
                    int min2 = FastMath.min(i4 + 36, this.columns);
                    int i5 = 0;
                    int i6 = i2;
                    while (i6 < min) {
                        FieldElement fieldElement = zero;
                        int i7 = i4;
                        while (i7 < min2 - 3) {
                            fieldElement = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement.add(tArr2[i5].multiply(tArr[i7]))).add(tArr2[i5 + 1].multiply(tArr[i7 + 1]))).add(tArr2[i5 + 2].multiply(tArr[i7 + 2]))).add(tArr2[i5 + 3].multiply(tArr[i7 + 3]));
                            i5 += 4;
                            i7 += 4;
                            fieldElementArr = fieldElementArr;
                            zero = zero;
                        }
                        FieldElement[] fieldElementArr2 = fieldElementArr;
                        T t = zero;
                        while (i7 < min2) {
                            fieldElement = (FieldElement) fieldElement.add(tArr2[i5].multiply(tArr[i7]));
                            i7++;
                            i5++;
                        }
                        fieldElementArr2[i6] = (FieldElement) fieldElementArr2[i6].add(fieldElement);
                        i6++;
                        fieldElementArr = fieldElementArr2;
                        zero = t;
                    }
                }
            }
            return (T[]) fieldElementArr;
        }
        throw new DimensionMismatchException(tArr.length, this.columns);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T[] preMultiply(T[] tArr) throws DimensionMismatchException {
        int i;
        if (tArr.length == this.rows) {
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(getField(), this.columns);
            T zero = getField().getZero();
            int i2 = 0;
            while (i2 < this.blockColumns) {
                int blockWidth = blockWidth(i2);
                int i3 = blockWidth + blockWidth;
                int i4 = i3 + blockWidth;
                int i5 = i4 + blockWidth;
                int i6 = i2 * 36;
                int min = FastMath.min(i6 + 36, this.columns);
                for (int i7 = 0; i7 < this.blockRows; i7++) {
                    T[] tArr2 = this.blocks[(this.blockColumns * i7) + i2];
                    int i8 = i7 * 36;
                    int min2 = FastMath.min(i8 + 36, this.rows);
                    int i9 = i6;
                    while (i9 < min) {
                        int i10 = i9 - i6;
                        FieldElement[] fieldElementArr2 = fieldElementArr;
                        FieldElement fieldElement = zero;
                        T t = zero;
                        int i11 = i8;
                        while (true) {
                            i = i2;
                            if (i11 >= min2 - 3) {
                                break;
                            }
                            int i12 = i11;
                            fieldElement = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement.add(tArr2[i10].multiply(tArr[i12]))).add(tArr2[i10 + blockWidth].multiply(tArr[i12 + 1]))).add(tArr2[i10 + i3].multiply(tArr[i12 + 2]))).add(tArr2[i10 + i4].multiply(tArr[i12 + 3]));
                            i10 += i5;
                            i11 = i12 + 4;
                            i2 = i;
                        }
                        while (i11 < min2) {
                            fieldElement = (FieldElement) fieldElement.add(tArr2[i10].multiply(tArr[i11]));
                            i10 += blockWidth;
                            i11++;
                        }
                        fieldElementArr2[i9] = (FieldElement) fieldElementArr2[i9].add(fieldElement);
                        i9++;
                        fieldElementArr = fieldElementArr2;
                        zero = t;
                        i2 = i;
                    }
                }
                i2++;
            }
            return (T[]) fieldElementArr;
        }
        throw new DimensionMismatchException(tArr.length, this.rows);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int p = pStart; p < pEnd; p++) {
                for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                    int jWidth = blockWidth(jBlock);
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, this.columns);
                    T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int p = pStart; p < pEnd; p++) {
                for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                    int jWidth = blockWidth(jBlock);
                    int qStart = jBlock * 36;
                    int qEnd = FastMath.min(qStart + 36, this.columns);
                    T[] block = this.blocks[(this.blockColumns * iBlock) + jBlock];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        blockFieldMatrix.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 36;
        while (iBlock < (endRow / 36) + 1) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, endRow + 1);
            int p = pStart;
            while (p < pEnd) {
                int jBlock = startColumn / 36;
                while (jBlock < (endColumn / 36) + 1) {
                    int jWidth = blockFieldMatrix.blockWidth(jBlock);
                    int q0 = jBlock * 36;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 36, endColumn + 1);
                    T[] block = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * iBlock) + jBlock];
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        block[k] = visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                    }
                    jBlock++;
                    blockFieldMatrix = this;
                    iBlock = iBlock;
                }
                p++;
                blockFieldMatrix = this;
                iBlock = iBlock;
            }
            iBlock++;
            blockFieldMatrix = this;
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        blockFieldMatrix.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 36;
        while (iBlock < (endRow / 36) + 1) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, endRow + 1);
            int p = pStart;
            while (p < pEnd) {
                int jBlock = startColumn / 36;
                while (jBlock < (endColumn / 36) + 1) {
                    int jWidth = blockFieldMatrix.blockWidth(jBlock);
                    int q0 = jBlock * 36;
                    int qStart = FastMath.max(startColumn, q0);
                    int qEnd = FastMath.min((jBlock + 1) * 36, endColumn + 1);
                    T[] block = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * iBlock) + jBlock];
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                    }
                    jBlock++;
                    blockFieldMatrix = this;
                    iBlock = iBlock;
                }
                p++;
                blockFieldMatrix = this;
                iBlock = iBlock;
            }
            iBlock++;
            blockFieldMatrix = this;
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, this.columns);
                T[] block = this.blocks[blockIndex];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor) {
        visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
            int pStart = iBlock * 36;
            int pEnd = FastMath.min(pStart + 36, this.rows);
            for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
                int qStart = jBlock * 36;
                int qEnd = FastMath.min(qStart + 36, this.columns);
                T[] block = this.blocks[blockIndex];
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

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        blockFieldMatrix.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 36;
        while (iBlock < (endRow / 36) + 1) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, endRow + 1);
            int jBlock = startColumn / 36;
            while (jBlock < (endColumn / 36) + 1) {
                int jWidth = blockFieldMatrix.blockWidth(jBlock);
                int q0 = jBlock * 36;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 36, endColumn + 1);
                T[] block = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * iBlock) + jBlock];
                int p = pStart;
                while (p < pEnd) {
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        block[k] = visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                    }
                    p++;
                    iBlock = iBlock;
                }
                jBlock++;
                blockFieldMatrix = this;
                iBlock = iBlock;
            }
            iBlock++;
            blockFieldMatrix = this;
        }
        return visitor.end();
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix<T> blockFieldMatrix = this;
        blockFieldMatrix.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
        visitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, startRow, endRow, startColumn, endColumn);
        int iBlock = startRow / 36;
        while (iBlock < (endRow / 36) + 1) {
            int p0 = iBlock * 36;
            int pStart = FastMath.max(startRow, p0);
            int pEnd = FastMath.min((iBlock + 1) * 36, endRow + 1);
            int jBlock = startColumn / 36;
            while (jBlock < (endColumn / 36) + 1) {
                int jWidth = blockFieldMatrix.blockWidth(jBlock);
                int q0 = jBlock * 36;
                int qStart = FastMath.max(startColumn, q0);
                int qEnd = FastMath.min((jBlock + 1) * 36, endColumn + 1);
                T[] block = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * iBlock) + jBlock];
                int p = pStart;
                while (p < pEnd) {
                    int k = (((p - p0) * jWidth) + qStart) - q0;
                    int q = qStart;
                    while (q < qEnd) {
                        visitor.visit(p, q, block[k]);
                        k++;
                        q++;
                        iBlock = iBlock;
                    }
                    p++;
                    iBlock = iBlock;
                }
                jBlock++;
                blockFieldMatrix = this;
                iBlock = iBlock;
            }
            iBlock++;
            blockFieldMatrix = this;
        }
        return visitor.end();
    }

    private int blockHeight(int blockRow) {
        if (blockRow == this.blockRows - 1) {
            return this.rows - (blockRow * 36);
        }
        return 36;
    }

    private int blockWidth(int blockColumn) {
        if (blockColumn == this.blockColumns - 1) {
            return this.columns - (blockColumn * 36);
        }
        return 36;
    }
}
