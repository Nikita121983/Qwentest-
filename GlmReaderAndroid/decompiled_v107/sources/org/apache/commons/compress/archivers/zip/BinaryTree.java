package org.apache.commons.compress.archivers.zip;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.ArrayFill;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;

/* loaded from: classes9.dex */
final class BinaryTree {
    private static final int NODE = -2;
    private static final int UNDEFINED = -1;
    private final int[] tree;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BinaryTree decode(InputStream inputStream, int totalNumberOfValues) throws IOException {
        int[] codes;
        byte[] encodedTree;
        if (totalNumberOfValues < 0) {
            throw new IllegalArgumentException("totalNumberOfValues must be bigger than 0, is " + totalNumberOfValues);
        }
        int size = inputStream.read() + 1;
        if (size == 0) {
            throw new IOException("Cannot read the size of the encoded tree, unexpected end of stream");
        }
        byte[] encodedTree2 = IOUtils.readRange(inputStream, size);
        if (encodedTree2.length != size) {
            throw new EOFException();
        }
        int maxLength = 0;
        int[] originalBitLengths = new int[totalNumberOfValues];
        int pos = 0;
        for (byte b : encodedTree2) {
            int numberOfValues = ((b & 240) >> 4) + 1;
            if (pos + numberOfValues > totalNumberOfValues) {
                throw new IOException("Number of values exceeds given total number of values");
            }
            int bitLength = (b & IntersectionPtg.sid) + 1;
            int j = 0;
            while (j < numberOfValues) {
                originalBitLengths[pos] = bitLength;
                j++;
                pos++;
            }
            maxLength = Math.max(maxLength, bitLength);
        }
        int oBitLengths = originalBitLengths.length;
        int[] permutation = new int[oBitLengths];
        for (int k = 0; k < permutation.length; k++) {
            permutation[k] = k;
        }
        int c = 0;
        int[] sortedBitLengths = new int[oBitLengths];
        for (int k2 = 0; k2 < oBitLengths; k2++) {
            for (int l = 0; l < oBitLengths; l++) {
                if (originalBitLengths[l] == k2) {
                    sortedBitLengths[c] = k2;
                    permutation[c] = l;
                    c++;
                }
            }
        }
        int code = 0;
        int codeIncrement = 0;
        int lastBitLength = 0;
        int[] codes2 = new int[totalNumberOfValues];
        for (int i = totalNumberOfValues - 1; i >= 0; i--) {
            code += codeIncrement;
            if (sortedBitLengths[i] != lastBitLength) {
                int lastBitLength2 = sortedBitLengths[i];
                codeIncrement = 1 << (16 - lastBitLength2);
                lastBitLength = lastBitLength2;
            }
            int lastBitLength3 = permutation[i];
            codes2[lastBitLength3] = code;
        }
        BinaryTree tree = new BinaryTree(maxLength);
        int size2 = 0;
        while (size2 < codes2.length) {
            int bitLength2 = originalBitLengths[size2];
            if (bitLength2 <= 0) {
                codes = codes2;
                encodedTree = encodedTree2;
            } else {
                codes = codes2;
                encodedTree = encodedTree2;
                tree.addLeaf(0, Integer.reverse(codes2[size2] << 16), bitLength2, size2);
            }
            size2++;
            encodedTree2 = encodedTree;
            codes2 = codes;
        }
        return tree;
    }

    BinaryTree(int depth) {
        if (depth < 0 || depth > 30) {
            throw new IllegalArgumentException("depth must be bigger than 0 and not bigger than 30 but is " + depth);
        }
        this.tree = ArrayFill.fill(new int[(int) ((1 << (depth + 1)) - 1)], -1);
    }

    public void addLeaf(int node, int path, int depth, int value) {
        if (depth == 0) {
            if (this.tree[node] != -1) {
                throw new IllegalArgumentException("Tree value at index " + node + " has already been assigned (" + this.tree[node] + ")");
            }
            this.tree[node] = value;
        } else {
            this.tree[node] = -2;
            int nextChild = (node * 2) + 1 + (path & 1);
            addLeaf(nextChild, path >>> 1, depth - 1, value);
        }
    }

    public int read(BitStream stream) throws IOException {
        int currentIndex = 0;
        while (true) {
            int bit = stream.readBit();
            if (bit == -1) {
                return -1;
            }
            int childIndex = (currentIndex * 2) + 1 + bit;
            int value = this.tree[childIndex];
            if (value == -2) {
                currentIndex = childIndex;
            } else {
                if (value != -1) {
                    return value;
                }
                throw new IOException("The child " + bit + " of node at index " + currentIndex + " is not defined");
            }
        }
    }
}
