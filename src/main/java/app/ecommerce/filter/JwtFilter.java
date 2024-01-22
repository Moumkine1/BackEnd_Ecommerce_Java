package app.ecommerce.filter;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import app.ecommerce.service.UserDetailService;
import app.ecommerce.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailService service;


	/*
	 * @Override protected void doFilterInternal(HttpServletRequest
	 * httpServletRequest, HttpServletResponse httpServletResponse, FilterChain
	 * filterChain) throws ServletException, IOException {
	 * 
	 * String authorizationHeader = httpServletRequest.getHeader("Authorization");
	 * 
	 * String token = null; String userName = null;
	 * 
	 * if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
	 * { token = authorizationHeader.substring(7); userName =
	 * jwtUtil.extractUsername(token); }
	 * 
	 * if (userName != null &&
	 * SecurityContextHolder.getContext().getAuthentication() == null) {
	 * 
	 * UserDetails userDetails = service.loadUserByUsername(userName);
	 * 
	 * if (jwtUtil.validateToken(token, userDetails)) {
	 * 
	 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
	 * UsernamePasswordAuthenticationToken(userDetails, null,
	 * userDetails.getAuthorities()); usernamePasswordAuthenticationToken
	 * .setDetails(new
	 * WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
	 * SecurityContextHolder.getContext().setAuthentication(
	 * usernamePasswordAuthenticationToken); } }
	 * filterChain.doFilter(httpServletRequest, httpServletResponse); }
	 */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
	
}