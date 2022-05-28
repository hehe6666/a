package com.xuan.practice.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xuan.practice.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.tags.BindTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器咯");
        //如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        // 这里是个坑，因为带请求带headers时，ajax会发送两次请求，
        // 第一次会发送OPTIONS请求，第二次才会发生get/post请求，所以要放行OPTIONS请求
        // 如果是OPTIONS请求，让其响应一个 200状态码，说明可以正常访问
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            // 放行OPTIONS请求
            return true;
        }

        boolean verify = false;
        //要跟前端协商好
        String token = request.getHeader("token");
        try{
            verify = JwtUtils.verifyToken(token,"hehe");
        }catch (TokenExpiredException tokenExpiredException){
            log.info("TokenExpiredException:   token过期了");
        } catch (SignatureVerificationException signatureVerificationException){
            log.info("ignatureVerificationException:   token验证失败");
        } catch (JWTDecodeException jwtDecodeException){
            log.info("JWTDecodeException:   token解码失败");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        log.info("拦截器verify是否通过？  :"+verify);
        return verify;
    }
}
