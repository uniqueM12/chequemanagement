package com.wizer.test.config.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tenece on 16/08/2019.
 */
public class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String message = "{" +
                "\n\"userId\":\"" + "N/A" + "\","+
                "\n\"message\":\"Successfully logged out\"," +
                "\n\"authenticated\":false" +
                "\n}";
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
