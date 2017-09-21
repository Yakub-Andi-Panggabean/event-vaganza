package com.special.gift.app.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mashape.unirest.http.JsonNode;
import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorService;
import com.special.gift.app.util.CommonUtil;
import com.special.gift.app.util.HttpUtil;

@WebFilter(urlPatterns = "/login")
public class AuthenticationFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

  private UserService userService;

  private VendorService vendorService;

  private ShaPasswordEncoder passwordEncoder;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.debug("initialize filter");
    final ApplicationContext context = WebApplicationContextUtils
        .getRequiredWebApplicationContext(filterConfig.getServletContext());

    userService = context.getBean(UserService.class);
    vendorService = context.getBean(VendorService.class);
    passwordEncoder = context.getBean(ShaPasswordEncoder.class);

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

        if (userService == null) {
          log.debug("service null");
        }

        log.debug("principal : {}", principal);
        log.debug("credential : {}", credential);

        if (userService.checkUserByPrincipal(principal)) {

          final User user = userService.findUserByPrincipal(principal);

          if (user.getStatus() != '1') {
            printResponse(res, HttpServletResponse.SC_FORBIDDEN, " user is not activated yet");
          } else {

            if (CommonUtil.encryptHashSHA(credential).equals(user.getPassword())) {

              final List<Vendor> vendors = vendorService.findByUser(user);


              final JSONObject jsonObject = HttpUtil.getApiToken(principal, credential).getObject();

              log.info(jsonObject.toString());

              final JsonNode json = new JsonNode(jsonObject.get("data").toString());


              request.setAttribute("isVendorExist", vendors.size() > 0);
              request.setAttribute("user", user.getUsername());
              request.setAttribute("userId", user.getUserId());
              request.setAttribute("userEmail", user.getEmail());
              request.setAttribute("accessToken", json.getObject().get("access_token"));

              chain.doFilter(request, response);

            } else {
              printResponse(res, HttpServletResponse.SC_FORBIDDEN,
                  new StringBuilder().append(" username and password is not matched").toString());
            }
          }



        } else {
          printResponse(res, HttpServletResponse.SC_FORBIDDEN, "email is not valid or registered");
        }

      } else {
        printResponse(res, HttpServletResponse.SC_FORBIDDEN, "Autentikasi Gagal");
      }

    } catch (final JSONException exception) {
      log.error("api exception occured : {}", exception.getMessage());
      final String message =
          exception.getMessage() != null && !exception.getMessage().equals("null")
              ? exception.getMessage() : "Forbidden";
      printResponse(res, HttpServletResponse.SC_FORBIDDEN, "api exception :" + message);

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
