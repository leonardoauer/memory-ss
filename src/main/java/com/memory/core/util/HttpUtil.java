package com.memory.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.NotImplementedException;

public class HttpUtil {

	private HttpUtil() {
	}

	public static boolean isAjaxRequest(HttpServletRequest req) {
		return "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
	}

	/**
	 * Returns the Client IP Address
	 * 
	 * @param req
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest req) {

		// Is client behind something?
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}

		return ipAddress;
	}

	/**
	 * Make this method better, I copy it from the internet.
	 * 
	 * @param request
	 * @param out
	 */
	public static void printRequestData(HttpServletRequest request, PrintStream out) {

		try {

			System.out.println();
			out.println(request.getMethod());

			Enumeration<?> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				out.println(request.getHeader(headerName));
			}

			Enumeration<?> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String paramName = (String) params.nextElement();
				out.println("Attribute Name - " + paramName + ", Value - " + request.getParameter(paramName));
			}

			InputStream body = request.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(body, writer);
			String theString = writer.toString();
			out.println(theString);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Make this method better, I copy it from the internet.
	 * 
	 * @param request
	 * @param out
	 */
	public static Map<String, String> listHeaderParams(HttpServletRequest request, PrintStream out) {

		if (request.getHeaderNames() == null) {
			return null;
		}

		Map<String, String> headerParams = new HashMap<String, String>();

		try {

			Enumeration<?> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				out.println(request.getHeader(headerName));
			}

			Enumeration<?> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String paramName = (String) params.nextElement();
				out.println("Attribute Name - " + paramName + ", Value - " + request.getParameter(paramName));
				headerParams.put("Attribute Name - " + paramName + ", Value - ", request.getParameter(paramName));
			}

			InputStream body = request.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(body, writer);
			String theString = writer.toString();
			out.println(theString);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return headerParams;
	}

	public static void printResponseData(HttpServletResponse response, PrintWriter out) {
		// Have to implement this method yet
		throw new NotImplementedException("Have to implement this method yet");
	}

	/**
	 * Gets the host from the request without the protocol, port number or
	 * resource path (eg: screenlibrary.com)
	 * <p>
	 * 
	 * @param httpRequest
	 *            The request to be parsed to retrieve the host.
	 * @return The host or <code>null</code> if the request could not be parsed
	 */
	public static String getHost(HttpServletRequest httpRequest) {

		String uri = httpRequest.getRequestURL().toString();
		String host = "";

		try {
			URI u = new URI(uri);
			host = u.getHost();
		} catch (URISyntaxException uriSynEx) {
			uriSynEx.printStackTrace();
			return null;
		}

		return host;

	}

	/**
	 * Gets the protocol from the URL request (eg: http)
	 * 
	 * @param httpRequest
	 *            The request to be parsed to retrieve the protocol.
	 * @return The protocol as a <code>String</code>, if no URL could be
	 *         retrieved then an empty string is returned.
	 */
	public static String getProtocol(HttpServletRequest httpRequest) {

		String url = httpRequest.getRequestURL().toString();
		String protocol = "";

		try {
			URL u = new URL(url);
			protocol = u.getProtocol();
		} catch (MalformedURLException malUrlEx) {
			malUrlEx.printStackTrace();
			return "";
		}

		return protocol;

	}

	public static String getURLAndContextPath(HttpServletRequest httpRequest) {

		String url = httpRequest.getRequestURL().toString();
		String uri = httpRequest.getRequestURI().toString();
		String contextPath = httpRequest.getContextPath();

		StringBuilder urlAndContextPath = new StringBuilder();
		urlAndContextPath.append(url.substring(0, url.length() - uri.length()));
		urlAndContextPath.append(contextPath);

		return urlAndContextPath.toString();
	}

	/**
	 * Writes a file's contents into the http response to display on the page.
	 * <p>
	 * 
	 * @param httpResponse
	 *            The http response to output the file contents
	 * @param httpSession
	 *            The http session
	 * @param contentType
	 *            The contentType type that the contents should be treated as by
	 *            the client
	 * @param path
	 *            The path of the file to read the contents from
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public static void writeFileToResponse(HttpServletResponse httpResponse, HttpSession httpSession, String contentType, String path) throws IOException {

		httpResponse.setContentType(contentType);

		// no caching allowed
		httpResponse.setHeader("Cache-Control:", "no-cache, no-store, must-revalidate"); // HTTP
																							// 1.1
		httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
		httpResponse.setHeader("Expires", "0"); // Proxies

		PrintWriter out = httpResponse.getWriter();

		try {
			ServletContext sc = httpSession.getServletContext();
			InputStream is = sc.getResourceAsStream(path);

			if (is != null) {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(isr);
				String line = "";
				while ((line = reader.readLine()) != null) {
					out.println(line);
				}
			}
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * Writes the contents of a <code>StringWriter</code> into the HTTP response
	 * to display on the page.
	 * 
	 * @param httpResponse
	 *            a <code>HttpServletResponse</code> of the response to be sent
	 * @param contentType
	 *            the contentType that the contents should be treated by the
	 *            client
	 * @param content
	 *            a <code>StringWriter</code> containing the <code>String</code>
	 *            contents to be written into the response
	 * @throws IOException
	 */
	public static void writeContentToResponse(HttpServletResponse httpResponse, String contentType, StringWriter content) throws IOException {

		httpResponse.setContentType(contentType);

		// no caching allowed
		httpResponse.setHeader("Cache-Control:", "no-cache, no-store, must-revalidate"); // HTTP
																							// 1.1
		httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
		httpResponse.setHeader("Expires", "0"); // Proxies

		PrintWriter out = httpResponse.getWriter();

		try {
			StringReader sr = new StringReader(content.toString());
			BufferedReader reader = new BufferedReader(sr);
			String line = "";
			while ((line = reader.readLine()) != null) {
				out.println(line);
			}
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			out.close();
		}

	}

	/**
	 * Writes the contents of a <code>String</code> into the HTTP response to
	 * display on the page. <br>
	 * <br>
	 * DO NOT USE THIS METHOD FOR LARGE STRINGS. USE A <code>StringWriter</code>
	 * AND INVOKE
	 * {@link #writeContentToResponse(javax.servlet.http.HttpServletResponse, java.lang.String, java.io.StringWriter)}
	 * <br>
	 * 
	 * @param httpResponse
	 * @param contentType
	 * @param content
	 * @throws IOException
	 */
	public static void writeContentToResponse(HttpServletResponse httpResponse, String contentType, String content) throws IOException {
		StringWriter sw = new StringWriter();
		sw.write(content);
		writeContentToResponse(httpResponse, contentType, sw);
	}

	/**
	 * Writes the contents of a <code>StringWriter</code> into the HTTP
	 * response.
	 * 
	 * @param httpResponse
	 *            a <code>HttpServletResponse</code> of the response to be sent
	 * @param contentType
	 *            the contentType that the contents should be treated by the
	 *            client
	 * @param content
	 *            a <code>StringWriter</code> containing the <code>String</code>
	 *            contents to be written into the response
	 * @param filename
	 *            a <code>String</code> of the filename that the file should be
	 *            named as for download
	 * @throws IOException
	 */
	public static void writeContentForFileDownload(HttpServletResponse httpResponse, String contentType, StringWriter content, String filename) throws IOException {

		httpResponse.setContentType(contentType);

		httpResponse.setHeader("content-disposition", "attachment;filename=" + filename);

		// no caching allowed
		httpResponse.setHeader("Cache-Control:", "no-cache, no-store, must-revalidate"); // HTTP
																							// 1.1
		httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
		httpResponse.setHeader("Expires", "0"); // Proxies

		PrintWriter out = httpResponse.getWriter();

		try {
			StringReader sr = new StringReader(content.toString());
			BufferedReader reader = new BufferedReader(sr);
			String line = "";
			while ((line = reader.readLine()) != null) {
				out.println(line);
			}
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			out.close();
		}
	}

	public static void redirect(HttpServletResponse response, String toPage) {
		try {
			response.sendRedirect(toPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
