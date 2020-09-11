package com.ft.entity;


import java.util.List;
public class Response {

    private List<TruckDetail> truckDetails;

    public List<TruckDetail> getRows() {
        return truckDetails;
    }

    public void setRows(List<TruckDetail> truckDetails) {
        this.truckDetails = truckDetails;
    }

    @Override
    public String toString() {
        return "Response{" +
                "rows=" + truckDetails +
                '}';
    }
}
