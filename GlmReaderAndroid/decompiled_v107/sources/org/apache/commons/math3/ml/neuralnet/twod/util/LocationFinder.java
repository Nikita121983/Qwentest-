package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;

/* loaded from: classes10.dex */
public class LocationFinder {
    private final Map<Long, Location> locations = new HashMap();

    /* loaded from: classes10.dex */
    public static class Location {
        private final int column;
        private final int row;

        public Location(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }
    }

    public LocationFinder(NeuronSquareMesh2D map) {
        int nR = map.getNumberOfRows();
        int nC = map.getNumberOfColumns();
        for (int r = 0; r < nR; r++) {
            for (int c = 0; c < nC; c++) {
                Long id = Long.valueOf(map.getNeuron(r, c).getIdentifier());
                if (this.locations.get(id) != null) {
                    throw new MathIllegalStateException();
                }
                this.locations.put(id, new Location(r, c));
            }
        }
    }

    public Location getLocation(Neuron n) {
        return this.locations.get(Long.valueOf(n.getIdentifier()));
    }
}
