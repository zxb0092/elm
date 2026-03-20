package org.example.elm_spring.interceptor;

import com.alibaba.fastjson2.JSONObject;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 登录拦截器。
 * 在请求到达 Controller 之前，检查请求头中的 Authorization 字段是否携带有效的 JWT token。
 * 若 token 不存在或解析失败，则拦截请求并返回未登录的错误信息。
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    JwtUtils jwtUtils = new JwtUtils();

    /**
     * 请求预处理方法，在 Controller 方法执行前调用。
     * 从请求头中获取 Authorization token 并进行 JWT 解析验证：
     * <ul>
     *   <li>若 token 为 null，返回"没有登录"错误并阻止请求继续；</li>
     *   <li>若 token 解析成功，放行请求；</li>
     *   <li>若 token 解析抛出异常（过期或非法），阻止请求继续。</li>
     * </ul>
     *
     * @param request  当前 HTTP 请求
     * @param response 当前 HTTP 响应
     * @param handler  处理该请求的处理器对象
     * @return {@code true} 表示放行，{@code false} 表示拦截
     * @throws Exception 写响应时可能抛出的异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            Result error = Result.error("没有登录");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        } else {
            try {
                Map<String, Object> map = jwtUtils.parseToken(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
