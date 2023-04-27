package edu.goit.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/time/*")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String timezone = req.getParameter("timezone");

        ZoneId zoneId = timezone != null ? ZoneId.of(URLEncoder.encode(timezone, StandardCharsets.UTF_8))
                : ZoneId.of("UTC+3");

        DateTimeFormatter dateTimeFormatter = timezone != null ? DateTimeFormatter.ofPattern("yyyy-MM-dd k:m:s z") :
                DateTimeFormatter.ofPattern("yyyy-MM-dd k:m:s 'UTC'");

        resp.setContentType("text/plain");
        resp.getWriter().write(ZonedDateTime.now(zoneId).format(dateTimeFormatter));
    }
}
