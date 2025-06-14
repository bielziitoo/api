package br.com.aula.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.aula.api.User.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class securityFilter extends OncePerRequestFilter {

    @Autowired 
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
      var token = this.recoverToken(request);
      if (token != null) {
        var subject = tokenService.validateToken(token);
        UserDetails user = userRepository.findByLogin(subject);

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }
}
