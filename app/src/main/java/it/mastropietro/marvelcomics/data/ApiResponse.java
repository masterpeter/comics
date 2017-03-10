package it.mastropietro.marvelcomics.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Angelo Mastropietro on 10/03/17.
 */

public class ApiResponse<T> {

    @SerializedName("code") private int code;
    @SerializedName("status") private String status;
    @SerializedName("data") private T data;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiResponse that = (ApiResponse) o;

        if (code != that.code) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override public int hashCode() {
        int result = code;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
