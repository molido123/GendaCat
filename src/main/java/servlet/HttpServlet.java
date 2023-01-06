package servlet;

import utils.Request;
import utils.Response;

/**
 * @author huagenda
 */
public abstract class HttpServlet implements Servlet {
    public abstract void doGet(Request request, Response response);
    public abstract void doPost(Request request, Response response);
    @Override
    public void init() throws Exception{};
    @Override
    public void destroy() throws Exception {};
    @Override
    public void service(Request request,Response response) throws Exception{
        if(request.getMethod().equals("GET")){
            doGet(request, response);
        }else{
            doPost(request,response);
        }
    }
}
