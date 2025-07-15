package cn.xsword.sshmanage.bean;

import cn.xsword.sshmanage.entity.User;
import cn.xsword.sshmanage.entity.UserDetailsImpl;
import cn.xsword.sshmanage.mapper.UserMapper;
import cn.xsword.sshmanage.util.JwtUtil;
import cn.xsword.sshmanage.util.StringUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-09 18:04
 * @description: 通过jwt对用户身份进行认证的自定义Spring Security过滤器
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(!StringUtil.hasText(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        header = header.substring(7);
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(header);
            userid = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
            /*
             *TODO
             * spring security处理token不合法时无法到达Controller层，
             * 即无法返回错误信息给前端，
             * 故在此不throw exception，而是先放行return，再在Controller层处理错误。
             */
//            logger.error("token不合法!");
//            filterChain.doFilter(request, response);
//            return;
        }

        User user = userMapper.selectById(userid);

        if(user == null) {
            logger.error("用户不存在！");
            throw new RuntimeException("用户未登陆！请先登陆以使用更多功能呢～");
        }

        UserDetailsImpl loginUser = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
