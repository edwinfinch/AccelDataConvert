import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class AccelData {
    private static final String TAG = AccelData.class.getSimpleName();

    final public int x;
    final public int y;
    final public int z;

    public long timestamp = 0;
    final public boolean didVibrate;

    public AccelData(byte[] data) {
        x = (data[0] & 0xff) | (data[1] << 8);
        y = (data[2] & 0xff) | (data[3] << 8);
        z = (data[4] & 0xff) | (data[5] << 8);
        didVibrate = data[6] != 0;
        for (int i = 0; i < 8; i++) {
            timestamp |= ((long)(data[i+7] & 0xff)) << (i * 8);
        }
    }

    public static List<AccelData> fromDataArray(byte[] data) {
        List<AccelData> accels = new ArrayList<AccelData>();
        for (int i = 0; i < data.length; i += 15) {
            accels.add(new AccelData(Arrays.copyOfRange(data, i, i + 15)));
        }
        return accels;
    }
}
