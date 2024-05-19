package com.todoService.todoService.config;

public class RequestContext {
    public static final String REQUEST_HEADER_NAME = "Authorization";
    private static final ThreadLocal<RequestContext> Context = new ThreadLocal<>();
    private String token;
//    public static RequestContext getContext(){
//        RequestContext requestContext = Context.get();
//        if(requestContext == null){
//            requestContext = new RequestContext();
//            Context.set(requestContext);
//        }
//        return requestContext;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
