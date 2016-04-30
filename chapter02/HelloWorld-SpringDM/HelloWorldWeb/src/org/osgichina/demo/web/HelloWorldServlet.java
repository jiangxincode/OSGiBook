package org.osgichina.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgichina.demo.timeservice.TimeService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 一个普通的Servlet
 * @author chris
 *
 */
public class HelloWorldServlet extends HttpServlet{

	private static final long serialVersionUID = -6421103301902391763L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		sendResponse(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		sendResponse(req, resp);
	}

	private void sendResponse(HttpServletRequest req, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		out.println("<html>");
		out.println("<head><title>HelloWorld</title></head>");
		out.println("<body>");
		out.println("Hello World!<BR>");
		//得到Application Context
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
		//获取服务
		TimeService timeService = (TimeService)ctx.getBean("osgiTimeService");
		out.println("Current time is " + timeService.getCurrentTime());
		out.println("</body></html>");
	}
	
}
