package utils;

/**
 * @author huagenda
 */
public class HttpProtocal {
    public static String getHttpHeader(String Length){
        return "HTTP/1.1 200 OK \n" +

                "Content-Type: text/html \n" +

                "Content-Length: "+ Length +"\n"+

                "\r\n";
    }
    public static String getHttpHeader404(){

        String str404="<h1>404 not found</h1>";

        return "HTTP/1.1 404 NOT Found \n" +

                "Content-Type: text/html \n" +

                "Content-Length: "+str404.getBytes().length +"\n"+

                "\r\n" + str404;

    }
}
