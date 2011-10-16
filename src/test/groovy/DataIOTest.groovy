import org.testng.Assert
import org.testng.annotations.Test

/**
 * @author Chris Pearson (chris@twock.com)
 */
public class DataIOTest {
  @Test
  public void testShorts() {
    byte[] arr = [(byte) 0xff, (byte) 0xff];
    Assert.assertEquals(DataIO.read(new DataInputStream(new ByteArrayInputStream(arr)), "unsigned_short"), 65535);
    Assert.assertEquals(DataIO.read(new DataInputStream(new ByteArrayInputStream(arr)), "short"), -32768);
  }
}
