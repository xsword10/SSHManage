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
        }

        User user = userMapper.selectById(userid);

        if(user == null) {
            throw new RuntimeException("用户未登陆！请先登陆以使用更多功能呢～");
        }

        UserDetailsImpl loginUser = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
