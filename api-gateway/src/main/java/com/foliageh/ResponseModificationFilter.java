package com.foliageh;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class ResponseModificationFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String uri = ctx.getRequest().getRequestURI();
        return uri.startsWith("/api/agency");
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            InputStreamReader reader = new InputStreamReader(ctx.getResponseDataStream(), StandardCharsets.UTF_8);

            String originalBody = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));

            String modifiedBody = originalBody.replaceFirst("\\{", "{ \"modifiedByGateway\": true, ");
            if (originalBody.contains("Внутренняя ошибка"))
                modifiedBody = originalBody.replace("Внутренняя ошибка", "Ошибка обработана на gateway :)");
            modifiedBody = modifiedBody.replace("Apartment", "Apartment GATEWAY");

            ctx.setResponseBody(modifiedBody);
            ctx.getResponse().setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}