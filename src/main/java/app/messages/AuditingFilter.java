package app.messages;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuditingFilter extends GenericFilterBean {
	
	private final static Log log = LogFactory.getLog(AuditingFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		long start = new Date().getTime();
		chain.doFilter(req, res);
		long elapsed = new Date().getTime() - start;
		HttpServletRequest request = (HttpServletRequest) req;
		// logger.debug("Request[uri=" + request.getRequestURI() + ", method=" + request.getMethod() + "] completed in " + elapsed + "ms");
		log.info("Request[uri=" + request.getRequestURI() + ", method=" + request.getMethod() + "] completed in " + elapsed + "ms");
	}

}
