package utils;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author huagenda
 */
@Setter
@Getter
public class Request {
    private String method;
    private String url;
    private InputStream inputStream;
    public Request(InputStream inputStream) throws IOException{
        this.inputStream = inputStream;
        int count = 0;
        while(count == 0){
            count = inputStream.available();
        }
        byte[] bytes = new byte[count];
        inputStream.read(bytes);
        String inputSte = new String(bytes);
        String firstLine = inputSte.split("\\n")[0];
        String[] strings = firstLine.split(" ");
        this.method = strings[0];
        this.url = strings[1];
        System.out.println("method=====>>"+method);
        System.out.println("url=====>>"+url);
    }
}
