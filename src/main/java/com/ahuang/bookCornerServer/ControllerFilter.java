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
import javax.servlet.http.HttpSession;

import org.jboss.logging.MDC;

import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.util.StringUtil;

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
        HttpSession session = request.getSession();
        CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
        String userName = "";
        if(!StringUtil.isNullOrEmpty(bindUser)) {
        	userName = bindUser.getUserName();
        }
        MDC.put("method", request.getRequestURI());// 接口名
        MDC.put("userName", userName); // 用户名
		log.info("BEGIN:" + request.getRequestURI());
//		log.info("request:" + request.toString());
		try {
			chain.doFilter(request, response);
		} catch(Exception e) {
			log.error("error", e);
		}
//		log.info("response:" + response.toString());
		log.info("END:" + request.getRequestURI());
		MDC.put("method", "");
		MDC.put("userName", "");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("过滤器初始化");
	}

}
