package org.appops.webserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.appops.infra.appserver.AppServerEmbeddedEntryPoint;

import com.google.gson.Gson;

/***Servlet implementation

class WebAppEntryPoint
 */

 public class WebAppEntryPoint extends HttpServlet {

 private static Logger logger = Logger.getLogger(WebAppEntryPoint.class.getCanonicalName());
 private static final long serialVersionUID = 1L;
 private AppServerEmbeddedEntryPoint entryPoint = null ;
 /**
 * @see HttpServlet#HttpServlet()
 */

 public WebAppEntryPoint() {
 super();
 }

 @Inject
 public void setAppServerEntryPoint(AppServerEmbeddedEntryPoint entry){
 entryPoint = entry ;
 }

 /**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 logger.log(Level.FINE, "Request Url - " + request.getRequestURL().toString());
 logger.log(Level.FINE, "Query String in request - " + request.getQueryString());

 String paramName = "q";
 String paramValue = request.getParameter(paramName);

 Object obj = entryPoint.dispatchRestRequest(paramValue) ;
 Gson gson = new Gson() ;

 String jsonResult = gson.toJson(obj);

 response.getWriter().write(jsonResult);
 response.getWriter().flush();

 }

 /**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {

 doGet(request, response);
 }

 }
