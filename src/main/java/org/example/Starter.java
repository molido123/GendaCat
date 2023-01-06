package org.example;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import servlet.HttpServlet;
import utils.FileUtils;
import utils.HttpProtocal;
import utils.Request;
import utils.Response;
import org.dom4j.io.*;

/**
 * @author huagenda
 */
@Getter
@Setter
public class Starter {
    private int port = 11451;
    private Map<String, HttpServlet> servletMap = new HashMap<>();
    /**
     * the entrance of program
     * @param args
     */
    public static void main(String[] args) {
        Starter starter = new Starter();
        try{
            starter.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void start() throws Exception {
        //load the configure file miao.xml
        loadServlet();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("========>>GendaCat start on port："+port);
        //This loop is always waiting for connection
        while(true){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            //build the stream of the return data
            Request request = new Request(inputStream);
            Response response = new Response(outputStream);
            String url = request.getUrl();
            if(servletMap.get(url)==null){
                System.out.println("try to access static resource");
                response.outPutStream(url);
            }else {
                System.out.println("access dynamic resource");
                HttpServlet httpServlet = servletMap.get(url);
                httpServlet.service(request,response);
            }
            socket.close();
        }

    }
    private void loadServlet(){
        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("miao.xml");
        SAXReader saxReader = new SAXReader();
        try{
            Document document = saxReader.read(resourceStream);
            //root element
            Element rootElement = document.getRootElement();
            List<Element> selectNodes = rootElement.selectNodes("//servlet");
            for (int i =0;i<selectNodes.size();++i){
                Element element = selectNodes.get(i);
                //try to find servletName and servletClass
                ////<servlet-name>dxh</servlet-name>
                Element servletNameElement = (Element) element.selectSingleNode("servlet-name");
                String servletName = servletNameElement.getStringValue();
                //<servlet-class>server.DxhServlet</servlet-class>
                Element servletClassElement = (Element) element.selectSingleNode("servlet-class");
                String servletClass = servletClassElement.getStringValue();
                // use servletName to query
                Element servletMapping = (Element) rootElement.selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
                String urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();
                servletMap.put(urlPattern,(HttpServlet) Class.forName(servletClass).newInstance());
            }
        }catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}