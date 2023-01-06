package utils;

import jdk.dynalink.beans.StaticClass;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

import java.io.OutputStream;

/**
 * @author huagenda
 */
@Getter
@Setter
public class Response {
    private OutputStream outputStream;
    public Response(OutputStream outputStream){
        this.outputStream = outputStream;
    }
    public Response(){

    }
    public void outPutStream(String path) throws IOException {
        File file = new File("src/main/resources"+path);
        String abs = file.getAbsolutePath();
        System.out.println(abs);
        if(file.exists()&&file.isFile()){
            FileUtils.outPutStaticResource(new FileInputStream(file),outputStream);
        }else {
            System.out.println("not Found static resource");
            outPut(HttpProtocal.getHttpHeader404());
        }
    }
    public void outPut(String content) throws IOException {
        outputStream.write(content.getBytes());
    }
}
