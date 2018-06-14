package com.ahuang.bookCornerServer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName="ControllerFilter",urlPatterns="/*")
public class ControllerFilter implements Filter{

	@Override
	public void destroy() {
		log.info("过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        MDC.put("method", request.getRequestURI());
		log.info("BEGIN:" + request.getRequestURI());
//		log.info("request:" + request.toString());
		chain.doFilter(request, response);
//		log.info("response:" + response.toString());
		log.info("END:" + request.getRequestURI());
		MDC.put("method", "");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("过滤器初始化");
	}

}
