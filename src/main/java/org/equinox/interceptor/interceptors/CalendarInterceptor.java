package org.equinox.interceptor.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;
    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= open && hour < close) {
            String message = "Bienvenidos al horario de atencion a clientes, atendemos desde las " + open + " hrs. hasta las " + close + " hrs. gracias por su visita!";
            request.setAttribute("message", message);
            return true;
        }
        Map<String, String> data = new HashMap<>();
        data.put("message", "Cerrado fuera del horario de atencion por favor visitanos desde las " + open + " hrs. hasta las " + close + " hrs.");
        data.put("date", new Date().toString());

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().print(mapper.writeValueAsString(data));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

}
