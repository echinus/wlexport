/**
 * @author Chris Pearson (chris@twock.com)
 */
class DataIO {
  static read(DataInputStream stream, String fieldType) {
    switch(fieldType) {
      case "short":
        return readShort(stream)
      case "unsigned_short":
        return readUnsignedShort(stream)
      case "BYTE":
        return stream.readByte() & 0xff
      default:
        throw new RuntimeException("Unhandled field type " + fieldType);
    }
  }

  static readLong(it) {
    int ch1 = it.read()
    int ch2 = it.read()
    int ch3 = it.read()
    int ch4 = it.read()
    if((ch1 | ch2 | ch3 | ch4) < 0)
      throw new EOFException();
    return ((ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0));
  }

  static readShort(it) {
    int ch1 = it.read();
    int ch2 = it.read();
    if((ch1 | ch2) < 0)
      throw new EOFException();
    if((ch2 & 0x80) == 0) {
      return (ch2 << 8) + (ch1 << 0);
    } else {
      return -(((ch2 & 0x7f) << 8) + (ch1 << 0) + 1);
    }
  }

  static readUnsignedShort(it) {
    int ch1 = it.read();
    int ch2 = it.read();
    if((ch1 | ch2) < 0)
      throw new EOFException();
    return (ch2 << 8) + (ch1 << 0);
  }
}
