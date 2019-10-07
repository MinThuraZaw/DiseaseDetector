package com.example.diseasedetector.model;

public class Result {

    private String count;

    private Resp_data[] resp_data;

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public Resp_data[] getResp_data ()
    {
        return resp_data;
    }

    public void setResp_data (Resp_data[] resp_data)
    {
        this.resp_data = resp_data;
    }
}
