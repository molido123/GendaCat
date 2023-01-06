package servlet;

import utils.HttpProtocal;
import utils.Request;
import utils.Response;

import java.io.IOException;

/**
 * @author huagenda
 */
public class GendaServlet extends HttpServlet {

    /**
     * @param request
     * @param response
     */
    @Override
    public void doGet(Request request, Response response) {
        String content = "GendaServlet get";
        try {
            response.outPut(HttpProtocal.getHttpHeader(String.valueOf(content.length()))+content);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param request
     * @param response
     */
    @Override
    public void doPost(Request request, Response response) {
        String content = "<h1>GendaServlet post 冬雪莲这么可爱给个关注吧</h1>";
        try {
            response.outPut(HttpProtocal.getHttpHeader(String.valueOf(content.length()))+content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void init() throws Exception{
        super.init();
    }
    @Override
    public void destroy() throws Exception{
        super.destroy();
    }
}
