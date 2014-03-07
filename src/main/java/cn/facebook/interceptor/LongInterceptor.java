package cn.facebook.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.facebook.exception.AuthorizationException;

public class LongInterceptor implements HandlerInterceptor {
	private final String SESSION_USER = "user";

	/**
	 * 该方法将在Controller处理之前进行调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String requestUrl = request.getRequestURI();
		System.out.println("request url : " + requestUrl);

		HttpSession session = request.getSession();

		Object user = session.getAttribute(SESSION_USER);

		if (user == null) {
			System.out.println("你没有登陆....");
			throw new AuthorizationException();
		} else {
			return true;
		}
	}

	/**
	 * 也就是在Controller的方法调用之后执行,但是它会在DispatcherServlet进行视图的渲染之前执行，
	 * 也就是说在这个方法中你可以对ModelAndView进行操
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图后执行， 这个方法的主要作用是用于清理资源的
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
