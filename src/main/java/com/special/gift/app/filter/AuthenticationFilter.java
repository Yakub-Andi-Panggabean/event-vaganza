package com.special.gift.app.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;

import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorService;
import com.special.gift.app.util.CommonUtil;

@WebFilter(urlPatterns = "/login")
public class AuthenticationFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

  @Inject
  private UserService userService;

  @Inject
  private VendorService vendorService;


  @Inject
  private ShaPasswordEncoder passwordEncoder;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.debug("initialize filter");
    while (filterConfig.getInitParameterNames().hasMoreElements()) {
      log.debug("init parameter name :{}", filterConfig.getInitParameterNames().nextElement());
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // TODO Auto-generated method stub
    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse res = (HttpServletResponse) response;

    final String authentication = req.getHeader(CommonUtil.AUTH_HEADER);

    log.debug("custom header value : {}", authentication);

    try {

      if (authentication != null && decodeHeader(authentication).length > 1) {
        final String[] auth = decodeHeader(authentication);
        final String principal = auth[0];
        final String credential = auth[1];
        // check is user is exist or not by username
        if (userService.checkUserByPrincipal(principal)) {

          final User user = userService.findUserByPrincipal(principal);

          if (passwordEncoder.isPasswordValid(user.getPassword(), credential, CommonUtil.SALT)) {

            final List<Vendor> vendors = vendorService.findByUser(user);

            request.setAttribute("isVendorExist", vendors.size() > 0);
            request.setAttribute("user", user.getUsername());
            request.setAttribute("userEmail", user.getEmail());



            chain.doFilter(request, response);

          } else {
            printResponse(res, HttpServletResponse.SC_FORBIDDEN, new StringBuilder("user : ")
                .append(principal).append(" username and password is not matched").toString());
          }

        } else {
          printResponse(res, HttpServletResponse.SC_FORBIDDEN, "email is not valid or registered");
        }

      } else {
        printResponse(res, HttpServletResponse.SC_FORBIDDEN, "Autentikasi Gagal");
      }

    } catch (final Exception exception) {
      exception.printStackTrace();
      log.error("exception occured : {}", exception.getMessage());
      final String message =
          exception.getMessage() != null && !exception.getMessage().equals("null")
              ? exception.getMessage() : "Forbidden";
      printResponse(res, HttpServletResponse.SC_FORBIDDEN, message);
    }
  }

  @Override
  public void destroy() {
    log.debug("destroying filter");
  }

  private String[] decodeHeader(String authorization) {
    // Decode the Auth Header to get the username and password
    try {
      final byte[] decoded = Base64.decode(authorization.getBytes("UTF-8"));
      final String credentials = new String(decoded);
      log.debug("credential : {}", credentials);
      return credentials.split(":");
    } catch (final UnsupportedEncodingException e) {
      throw new UnsupportedOperationException(e);
    }
  }


  public void printResponse(HttpServletResponse res, int statusCode, String message) {
    try {
      res.setStatus(HttpServletResponse.SC_FORBIDDEN);
      res.setContentType("Application/json");
      res.getWriter().println("{\"status\":\"" + HttpServletResponse.SC_FORBIDDEN
          + "\",\"errorMessage\":\"" + message + "\"}");
    } catch (final IOException e) {
      log.error("failed to print response because {}", e.getMessage());
    }
  }


}
