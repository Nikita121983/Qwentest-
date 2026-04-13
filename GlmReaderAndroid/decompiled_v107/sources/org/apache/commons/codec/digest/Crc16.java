package org.apache.commons.codec.digest;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.zip.Checksum;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes9.dex */
public final class Crc16 implements Checksum {
    private static final int ARC_INIT = 0;
    private static final int CCITT_INIT = 0;
    private static final int DNP_INIT = 0;
    private static final int DNP_XOROUT = 65535;
    private static final int IBM_SDLC_INIT = 65535;
    private static final int IBM_SDLC_XOROUT = 65535;
    private static final int MAXIM_INIT = 0;
    private static final int MAXIM_XOROUT = 65535;
    private static final int MCRF4XX_INIT = 65535;
    private static final int MODBUS_INIT = 65535;
    private static final int NRSC5_INIT = 65535;
    private static final int USB_INIT = 65535;
    private static final int USB_XOROUT = 65535;
    private int crc;
    private final int init;
    private final int[] table;
    private final int xorOut;
    private static final int[] ARC = {0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, 7616, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, 61441, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, 16000, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, TarConstants.DEFAULT_BLKSIZE, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, 8704, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, 32000, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448};
    private static final int[] CCITT = {0, 4489, 8978, 12955, 17956, 22445, 25910, 29887, 35912, 40385, 44890, 48851, 51820, 56293, 59774, 63735, 4225, 264, 13203, 8730, 22181, 18220, 30135, 25662, 40137, 36160, 49115, 44626, 56045, 52068, 63999, 59510, 8450, 12427, 528, 5017, 26406, 30383, 17460, 21949, 44362, 48323, 36440, 40913, 60270, 64231, 51324, 55797, 12675, 8202, 4753, 792, 30631, 26158, 21685, 17724, 48587, 44098, 40665, 36688, 64495, 60006, 55549, 51572, 16900, 21389, 24854, 28831, 1056, 5545, 10034, 14011, 52812, 57285, 60766, 64727, 34920, 39393, 43898, 47859, 21125, 17164, 29079, 24606, 5281, 1320, 14259, 9786, 57037, 53060, 64991, 60502, 39145, 35168, 48123, 43634, 25350, 29327, 16404, 20893, 9506, 13483, 1584, 6073, 61262, 65223, 52316, 56789, 43370, 47331, 35448, 39921, 29575, 25102, 20629, 16668, 13731, 9258, 5809, 1848, 65487, 60998, 56541, 52564, 47595, 43106, 39673, 35696, 33800, 38273, 42778, 46739, 49708, 54181, 57662, 61623, 2112, 6601, 11090, 15067, 20068, 24557, 28022, 31999, 38025, 34048, 47003, 42514, 53933, 49956, 61887, 57398, 6337, 2376, 15315, 10842, 24293, 20332, 32247, 27774, 42250, 46211, 34328, 38801, 58158, 62119, 49212, 53685, 10562, 14539, 2640, 7129, 28518, 32495, 19572, 24061, 46475, 41986, 38553, 34576, 62383, 57894, 53437, 49460, 14787, 10314, 6865, 2904, 32743, 28270, 23797, 19836, 50700, 55173, 58654, 62615, 32808, 37281, 41786, 45747, 19012, 23501, 26966, 30943, 3168, 7657, 12146, 16123, 54925, 50948, 62879, 58390, 37033, 33056, 46011, 41522, 23237, 19276, 31191, 26718, 7393, 3432, 16371, 11898, 59150, 63111, 50204, 54677, 41258, 45219, 33336, 37809, 27462, 31439, 18516, 23005, 11618, 15595, 3696, 8185, 63375, 58886, 54429, 50452, 45483, 40994, 37561, 33584, 31687, 27214, 22741, 18780, 15843, 11370, 7921, 3960};
    private static final int[] DNP = {0, 13918, 27836, 23266, 55672, 61222, 46532, 33690, 65417, 51671, 37685, 42347, 9969, 4271, 19021, 31763, 45675, 33845, 57047, 59529, 27411, 23885, 1967, 12785, 19938, 31676, 8542, 5888, 38042, 41668, 63526, 52856, 10671, 8177, 17683, 29517, 61655, 50825, 40043, 43573, 54822, 57464, 47770, 36036, 3934, 14592, 25570, 21948, 39876, 44442, 63352, 49446, 17084, 29922, 11776, 6238, 25677, 21011, 2289, 16047, 48437, 35691, 53641, 59351, 21342, 25856, 16354, 2492, 35366, 48248, 59034, 53444, 44247, 39561, 49259, 63029, 30127, 17393, 6419, 12109, 57653, 55147, 36233, 48087, 14413, 3603, 21745, 25263, 7868, 10466, 29184, 17502, 51140, 61850, 43896, 40230, 31473, 19631, 5709, 8211, 41865, 38359, 53045, 63851, 34168, 45862, 59844, 57242, 23552, 27230, 12476, 1762, 51354, 65220, 42022, 37496, 4578, 10172, 32094, 19200, 14099, 333, 23471, 28145, 61035, 55349, 33495, 46217, 42684, 37090, 51712, 64606, 32708, 18842, 4984, 9510, 22837, 28523, 13705, 983, 32845, 46611, 60657, 55983, 5335, 8841, 30827, 20021, 52655, 64497, 41235, 38733, 60254, 56576, 34786, 45500, 12838, 1144, 24218, 26820, 36627, 47437, 58287, 54769, 22123, 24629, 15063, 3209, 28826, 18116, 7206, 10872, 43490, 40892, 50526, 62208, 15736, 2854, 20932, 26522, 58368, 53854, 35004, 48866, 49905, 62639, 44621, 38931, 7049, 11735, 30517, 16747, 62946, 50108, 39262, 44800, 11418, 6852, 16422, 30328, 2667, 15413, 26327, 20617, 54035, 58701, 49071, 35313, 18313, 29143, 11061, 7531, 40689, 43183, 62029, 50195, 47104, 36446, 54460, 58082, 24952, 22310, 3524, 15258, 56397, 59923, 45297, 34479, 1333, 13163, 27017, 24535, 9156, 5530, 20344, 31014, 64188, 52450, 38400, 41054, 28198, 22648, 666, 13508, 46942, 33024, 56290, 60860, 37295, 42993, 64787, 52045, 18647, 32393, 9323, 4661};
    private static final int[] IBM_SDLC = CCITT;
    private static final int[] MAXIM = ARC;
    private static final int[] MCRF4XX = CCITT;
    private static final int[] MODBUS = ARC;
    private static final int[] NRSC5 = {0, 13732, 27464, 24300, 54928, 58164, 48600, 34940, 3329, 14501, 26185, 21485, 56209, 60981, 45273, 34173, 6658, 12198, 29002, 17646, 52370, 63798, 42970, 37502, 5891, 8871, 31819, 18927, 49555, 62519, 43739, 40831, 13316, TypedValues.CycleType.TYPE_PATH_ROTATE, 24396, 27368, 58004, 55088, 35292, 48248, 14597, 3233, 21069, 26601, 61333, 55857, 34013, 45433, 11782, 7074, 17742, 28906, 63638, 52530, 37854, 42618, 8967, 5795, 18511, 32235, 62871, 49203, 40671, 43899, 26632, 23980, 832, 14052, 48792, 35644, 54736, 57460, 25865, 20653, 3649, 15333, 45977, 34365, 55505, 60789, 29194, 18350, 6466, 11494, 42138, 37182, 53202, 64118, 32523, 19119, 5187, 8679, 43419, 39999, 49875, 63351, 23564, 27048, 14148, 736, 35484, 48952, 57812, 54384, 20749, 25769, 14917, 4065, 34717, 45625, 60629, 55665, 17934, 29610, 11590, 6370, 37022, 42298, 64470, 52850, 19215, 32427, 8263, 5603, 40351, 43067, 63191, 50035, 53264, 58804, 47960, 36604, 1664, 13092, 28104, 22636, 56593, 59573, 46681, 33789, 2945, 15909, 24777, 21869, 51730, 65462, 41306, 38142, 7298, 10534, 30666, 17006, 50963, 62135, 44123, 39423, 4483, 9255, 31435, 20335, 58388, 53680, 36700, 47864, 12932, 1824, 22988, 27752, 59669, 56497, 33373, 47097, 16261, 2593, 21709, 24937, 65046, 52146, 38238, 41210, 10374, 7458, 17358, 30314, 62231, 50867, 39007, 44539, 9607, 4131, 20175, 31595, 47128, 36284, 54096, 59124, 28296, 23340, 1472, 12388, 46361, 32957, 56913, 60405, 25481, 22061, 2241, 15717, 41498, 38846, 51538, 64758, 29834, 16686, 8130, 10854, 44827, 39615, 50259, 61943, 31115, 19503, 4803, 10087, 35868, 47544, 59220, 54000, 23180, 28456, 12740, 1120, 33053, 46265, 59989, 57329, 22413, 25129, 15557, 2401, 38430, 41914, 64854, 51442, 16526, 29994, 11206, 7778, 39711, 44731, 61527, 50675, 19855, 30763, 9927, 4963};
    private static final int[] USB = ARC;

    /* loaded from: classes9.dex */
    public static final class Builder implements Supplier<Crc16> {
        private int init;
        private int[] table;
        private int xorOut;

        @Override // java.util.function.Supplier
        public Crc16 get() {
            return new Crc16(this);
        }

        public Builder setInit(int init) {
            this.init = init;
            return this;
        }

        public Builder setTable(int[] table) {
            return table((int[]) ((int[]) Objects.requireNonNull(table, "table")).clone());
        }

        public Builder setXorOut(int xorOut) {
            this.xorOut = xorOut;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder table(int[] table) {
            this.table = (int[]) Objects.requireNonNull(table, "table");
            return this;
        }
    }

    public static Crc16 arc() {
        return builder().setInit(0).table(ARC).get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Crc16 ccitt() {
        return builder().setInit(0).table(CCITT).get();
    }

    public static Crc16 dnp() {
        return builder().setInit(0).setXorOut(65535).table(DNP).get();
    }

    public static int[] getArcTable() {
        return (int[]) ARC.clone();
    }

    public static int[] getCcittTable() {
        return (int[]) CCITT.clone();
    }

    public static int[] getDnpTable() {
        return (int[]) DNP.clone();
    }

    public static int[] getIbmSdlcTable() {
        return (int[]) IBM_SDLC.clone();
    }

    public static int[] getMaximTable() {
        return (int[]) MAXIM.clone();
    }

    public static int[] getMcrf4xxTable() {
        return (int[]) MCRF4XX.clone();
    }

    public static int[] getModbusTable() {
        return (int[]) MODBUS.clone();
    }

    public static int[] getNrsc5Table() {
        return (int[]) NRSC5.clone();
    }

    public static Crc16 ibmSdlc() {
        return builder().setInit(65535).setXorOut(65535).table(IBM_SDLC).get();
    }

    public static Crc16 maxim() {
        return builder().setInit(0).setXorOut(65535).table(MAXIM).get();
    }

    public static Crc16 mcrf4xx() {
        return builder().setInit(65535).table(MCRF4XX).get();
    }

    public static Crc16 modbus() {
        return builder().setInit(65535).table(MODBUS).get();
    }

    public static Crc16 nrsc5() {
        return builder().setInit(65535).table(NRSC5).get();
    }

    public static Crc16 usb() {
        return builder().setInit(65535).setXorOut(65535).table(USB).get();
    }

    private Crc16(Builder builder) {
        this.init = builder.init;
        this.xorOut = builder.xorOut;
        this.crc = builder.init;
        this.table = (int[]) Objects.requireNonNull(builder.table, "table");
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return this.crc ^ this.xorOut;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.crc = this.init;
    }

    public String toString() {
        return String.format("%s [init=0x%04X, crc=0x%04X, xorOut=0x%04X, crc^xorOut=0x%04X]", getClass().getSimpleName(), Integer.valueOf(this.init), Integer.valueOf(this.crc), Integer.valueOf(this.xorOut), Long.valueOf(getValue()));
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] b, int off, int len) {
        int end = len + off;
        for (int i = off; i < end; i++) {
            update(b[i]);
        }
    }

    @Override // java.util.zip.Checksum
    public void update(int b) {
        this.crc = (this.crc >>> 8) ^ this.table[(this.crc ^ b) & 255];
    }
}
