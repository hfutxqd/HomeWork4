package xyz.imxqd.homework4.net.model;

public class HttpResult<T> {
    public Result<T> result;


    public static class Result<T> {
        public boolean success;
        public String error;
        public int code;
        public long nextNodeId;
        public T data;
    }
}
