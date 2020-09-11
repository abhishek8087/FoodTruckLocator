package com.ft.application;

import com.ft.business.TruckDataService;
import com.ft.repository.TruckDataRepository;

import java.text.ParseException;

public class TruckDetailsExtractor {

    public static void main(String[] args) {
        System.out.println("***** Food Trucks Available now ******");
        TruckDataRepository truckDataRepository = new TruckDataRepository();
        TruckDataService truckDataRepo = new TruckDataService(truckDataRepository);
        truckDataRepo.refreshData();
    }
}
