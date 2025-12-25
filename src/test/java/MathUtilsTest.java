import com.abusmac.math.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MathUtilsTest {
    @Test
    public void testFormatToHumanReadable() {
        String humanReadableString = MathUtils.nanoToHumanReadableString(1_000_000_000);

        Assertions.assertEquals("1s 0ms 0mu 0ns", humanReadableString);

        humanReadableString = MathUtils.nanoToHumanReadableString(123_045_007);

        Assertions.assertEquals("0s 123ms 45mu 7ns", humanReadableString);
    }
}
