package sample.model;

import java.math.BigInteger;
import java.net.URL;

public class File {


    public File(String name, BigInteger size, String date, URL url, String status, String type) {
        this.name = name;
        this.size = size;
        this.date = date;
        this.url = url;
        this.status = status;
        this.type = type;
    }

    private String name;
    private BigInteger size;
    private String date;
    private URL url;
    private String status;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
