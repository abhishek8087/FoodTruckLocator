package com.ft.business;

import com.ft.entity.TruckDetail;
import com.ft.repository.TruckDataRepository;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class TruckDataService implements DataTraversal{


    TruckDataRepository truckDataRepository;

    public TruckDataService(TruckDataRepository truckDataRepository) {
        this.truckDataRepository = truckDataRepository;
    }

    private List<TruckDetail> truckDetails;

    private static final String DELIMITER = " || ";

    /**
     * variable to track current data in list
     */
    private int dataSize=0;

    /**
     * variable to hold page size
     */
    private static final int PAGE_SIZE = 10;

    private int currentPoint=0;

    /**
     * Refresh data from Socrata API
     */
    @Override
    public  void refreshData(){
        try {
            truckDetails = Arrays.asList(truckDataRepository.getData()).stream()
                    .filter(x -> isAvailableNow.test(x.getStart24(),x.getEnd24())).sorted()
                            .collect(Collectors.toList());
            currentPoint=0;
            dataSize = truckDetails.size();
            display();
            displayoptions();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int noOfTruckData(){
        return truckDetails.size();
    }

    /**
     * Display command line options to choose from
     */
    @Override
    public void displayoptions() {

        System.out.println( " ***** " +currentPoint + " of " + truckDetails.size() + " ***** " );
        System.out.println("--------------------Please choose from below options---------------------------");
        if(currentPoint < dataSize && (currentPoint + PAGE_SIZE < dataSize || (dataSize%PAGE_SIZE) >0))
            System.out.print("1. next  ");

        if(currentPoint - PAGE_SIZE > 0)
            System.out.print("2. previous ");

        System.out.print("3. refresh ");
        System.out.print("4. exit");
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("####################################################################");
        System.out.println("Your choice (Should be one of above mentioned options) : ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        System.out.println("Name"+DELIMITER +"Address ");
        switch (option){
            case 1:
                next();
                break;
            case 2:
                previous();
                break;
            case 3:
                refreshData();
                break;
            case 4:
                break;
        }
    }

    /**
     * Predicate to filter records based on current time.
     */
    private final BiPredicate<String,String> isAvailableNow = (start,end) ->{
        DateTimeFormatter formatterTime1 = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern("HH:mm").toFormatter(Locale.US);
        LocalTime curr = LocalTime.from(ZonedDateTime.now());
        LocalTime localStartTime = LocalTime.parse(start, formatterTime1);
        LocalTime localEndTime = LocalTime.parse(end, formatterTime1);
        return (curr.isAfter(localStartTime) && curr.isBefore(localEndTime));
    };


    /**
     * Next 10 records to be printed to command line
     */
    @Override
    public void next() {
        int nextPoint = currentPoint + PAGE_SIZE;
        for(;currentPoint>=0 && currentPoint< dataSize && currentPoint<nextPoint ; currentPoint++ ){
            TruckDetail currDetail = truckDetails.get(currentPoint);
            System.out.println(currDetail.getApplicant() + DELIMITER + currDetail.getLocation());
        }

        displayoptions();
    }

    /**
     * Previous 10 records to be printed to command line
     */
    @Override
    public void previous() {
        StringBuilder sb = new StringBuilder();

        int nextPoint=0;
        if(currentPoint%PAGE_SIZE >0){
            nextPoint = currentPoint - (currentPoint%PAGE_SIZE);
            currentPoint--;
        }else {
             nextPoint = currentPoint - PAGE_SIZE;
        }
        for(;currentPoint>0 &&currentPoint>nextPoint ; currentPoint-- ){
            TruckDetail currDetail = truckDetails.get(currentPoint);
            sb.insert(0,currDetail.getApplicant() + DELIMITER + currDetail.getLocation()+"\n");
        }
        System.out.println(sb);
        displayoptions();
    }

    /**
     * Initial print of 1st 10 records
     */
    @Override
    public void display() {
        System.out.println("Name"+DELIMITER +"Address ");
        for(;currentPoint<PAGE_SIZE ; currentPoint++ ){
            TruckDetail currDetail = truckDetails.get(currentPoint);
            System.out.println(currDetail.getApplicant() + DELIMITER + currDetail.getLocation());
        }
    }
}
