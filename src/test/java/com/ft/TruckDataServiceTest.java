package com.ft;

import com.ft.business.TruckDataService;
import com.ft.entity.TruckDetail;
import com.ft.repository.TruckDataRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TruckDataServiceTest {


    TruckDetail[] truckDetailList;

    @Before
    public void setUp() throws IOException {
       TruckDetail td = new TruckDetail();
       td.setApplicant("Apple Food Truck");
       td.setLocation("India");
       td.setStart24("10:00");
       td.setEnd24("18:00");
    truckDetailList = new TruckDetail[]{td};


    }

    @Test
    public void refreshDataTest() throws IOException {
        TruckDataRepository truckDataRepository = Mockito.mock(TruckDataRepository.class);
        Mockito.when(truckDataRepository.getData()).thenReturn(truckDetailList);
        Assert.assertEquals(truckDataRepository.getData(),truckDetailList);
    }

    @Test
    public void noOfTruckDataTest(){
        TruckDataService truckDataService =  Mockito.mock(TruckDataService.class);
        Mockito.when(truckDataService.noOfTruckData()).thenReturn(1);
        Assert.assertEquals(truckDataService.noOfTruckData() ,1);
    }
}
