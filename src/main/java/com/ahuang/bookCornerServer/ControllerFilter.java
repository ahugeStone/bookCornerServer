package com.ahuang.bookCornerServer;

import com.ahuang.bookCornerServer.entity.CustBindUsersEntity;
import com.ahuang.bookCornerServer.util.JWTUtil;
import com.ahuang.bookCornerServer.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@WebFilter(filterName="ControllerFilter",urlPatterns="/*")
public class ControllerFilter implements Filter{

	@Override
	public void destroy() {
		log.info("过滤器销毁");
	}

    /**
     * JWT加密密钥
     */
    @Value("${jwt.secret}")
    protected String SECRET;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String token = request.getHeader("Authorization");
        log.debug("token:" + token);
        HttpSession session = request.getSession();
        // 老版本通过session获取用户信息
        CustBindUsersEntity bindUser = (CustBindUsersEntity)session.getAttribute("bindUser");
        try {
            // 新版本通过token获取用户信息
            bindUser = JWTUtil.getInfo(token, SECRET);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
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
	public void init(FilterConfig arg0) {
		log.info("过滤器初始化");
	}

}
