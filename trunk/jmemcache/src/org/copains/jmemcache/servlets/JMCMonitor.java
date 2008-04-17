package org.copains.jmemcache.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.copains.jmemcache.JCacheMg;

public class JMCMonitor extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3228069593965933405L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO: handle output types (ie : csv / cacti / designed HTML
		
		ServletOutputStream out = resp.getOutputStream();
		JCacheMg inst = JCacheMg.getInstance();
		Vector<String> instances = inst.getInstanceNames();
		if ((null == instances) || (instances.size() == 0))
		{
			out.println("No instance inited");
		}
		for (String name : instances)
		{
			out.println("["+name+"]:"+inst.countElements(name));
		}
	}

}
