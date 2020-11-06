package utilities;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Utilities {

    public static String byteBufferToString(ByteBuffer buff) {
        return StandardCharsets.UTF_8.decode(buff).toString();
    }

    public static String[] stringSplitter(String input) {
        return input.split(" ");
    }

    public static ByteBuffer stringToByteBuffer(String input) {
        return ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String removeNewLine(String input) {
        return input.replace("\n", "");
    }

}
