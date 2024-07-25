package dev.patika.Library_Management_Systems_RestAPI.Core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    private T data;

    public ResultData(boolean status, String message, String code, T data) {
        super(status, message, code);
        this.data = data;
    }

}
