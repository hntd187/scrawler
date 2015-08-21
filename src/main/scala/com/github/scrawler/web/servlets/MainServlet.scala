package com.github.scrawler.web.servlets

import javax.servlet.http.{HttpServlet, HttpServletResponse, HttpServletRequest}
import scala.xml.NodeSeq


class MainServlet extends HttpServlet{
  
  override def service(request: HttpServletRequest, response: HttpServletResponse){
    response.setContentType("text/html")
    response.setCharacterEncoding("UTF-8")
    val responseBody: NodeSeq = 
      <html>
				<head>
					<title>Test Page</title>
					<link rel="stylesheet" type="text/css" href="/css/style.css" />
				</head>
				<body>
					<h1>Hi there!</h1>
				</body>
			</html>
    response.getWriter.write(responseBody.toString())
  }
}