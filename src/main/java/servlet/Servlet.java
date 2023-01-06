package servlet;

import utils.Request;
import utils.Response;

/**
 * @author huagenda
 */
public interface Servlet {
    void init() throws Exception;

    /**
     * delete the connection
     * @throws Exception
     */
    void destroy() throws Exception;
    /**
     * this use to process request and response
     * @param request
     * @param response
     * @throws Exception
     */
    void service(Request request, Response response) throws Exception;
}
