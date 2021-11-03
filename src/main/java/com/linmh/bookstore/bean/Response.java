package com.linmh.bookstore.bean;

import lombok.Data;

@Data
public class Response {
    private Integer code;
    private String status;
    private String msg="";
    private Object data;

    public Response(Integer code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }
    public Response(Integer code, String status,String msg, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public static Response success(Object data){
        return new Response(200, "OK", data);
    }
    public static Response success(int code,Object data){
        return new Response(code, "OK", data);
    }
    public static Response success(String msg,Object data){
        return new Response(200, "OK",msg, data);
    }
    public static Response success(){
        return new Response(200, "OK", "");
    }
    public static Response failure(){
        return new Response(500, "failure", "");
    }
    public static Response failure(int code,Object data){
        return new Response(code, "failure", data);
    }
    public static Response failure(String msg){
        return new Response(500, "failure", msg);
    }
}
