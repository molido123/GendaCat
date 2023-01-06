package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author huagenda
 */
public class FileUtils {
    public static void outPutStaticResource(InputStream inputStream, OutputStream outputStream) throws IOException {
        int count = 0;
        while (count==0){
            count = inputStream.available();
        }
        int resourceLength = count;
        int written = 0;
        int byteSize = 1024;
        outputStream.write(HttpProtocal.getHttpHeader(String.valueOf(resourceLength)).getBytes());
        //the size of buffer
        byte[] bytes = new byte[byteSize];
        //the container of stream
        while (written<resourceLength){
            //if the rest is smaller than container, we need to update its size as the rest.
            if(written+byteSize>resourceLength){
                byteSize = resourceLength-written;
                bytes = new byte[byteSize];
            }
            inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.flush();
            written+=byteSize;
        }


    }
}
