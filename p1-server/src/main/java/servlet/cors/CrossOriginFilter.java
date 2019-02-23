package servlet.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * https://howtodoinjava.com/servlets/java-cors-filter-example/
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
 * */
public class CrossOriginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServRequest = (HttpServletRequest) request;

		// Authorize (allow) all domains to consume the content
		HttpServletResponse httpServResponse = (HttpServletResponse) response;
		httpServResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");// (*) For all domains
		httpServResponse.addHeader("Access-Control-Allow-Headers", "content-type");
		httpServResponse.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT");
		httpServResponse.addHeader("Access-Control-Allow-Credentials", "true");
		
		// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS
		if (httpServRequest.getMethod().equals("OPTIONS")) {
			httpServResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
		}

		// pass the request along the filter chain
		chain.doFilter(httpServRequest, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
