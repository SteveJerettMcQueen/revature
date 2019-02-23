package abstraction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestHandler {

	void handleGet(HttpServletRequest request, HttpServletResponse response);

	void handlePost(HttpServletRequest request, HttpServletResponse response);

	void handlePut(HttpServletRequest request, HttpServletResponse response);

}
