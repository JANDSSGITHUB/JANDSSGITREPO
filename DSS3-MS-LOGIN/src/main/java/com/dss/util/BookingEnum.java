package com.dss.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookingEnum {

    public static String LOADING_TOP = "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38" +
            "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38" +
            "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38" +
            "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38" +
            "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D" +
            "\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D" +
            "\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D" +
            "\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D" +
            "\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38" +
            "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D" +
            "\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38" +
            "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D" +
            "\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38" +
            "\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38\uD83D\uDD38";

    public static String WELCOME = "✈✈ Welcome to Patrick Airlines ✈✈";
    public static String CHOOSE_TRANSACTION = "Please choose transaction \uD83D\uDCB5";


    public static String ADD_FLIGHT = "Book a flight \uD83D\uDCBE";
    public static String VIEW_FLIGHT = "View flights \uD83D\uDDA5";
    public static String SEARCH_FLIGHT = "Search flight \uD83D\uDD0D";
    public static String UPDATE_FLIGHT = "Update flight ✏";
    public static String DELETE_FLIGHTS = "Delete flight \uD83E\uDDE8";


    public static String DESTINATION = "Destination \uD83D\uDDFE";
    public static String NO_OF_PASSENGERS = "Number of passengers \uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC67";
    public static String FLIGHT_CLASS = "Flight Class \uD83C\uDF9F";
    public static String AIRPORT = "Airport \uD83D\uDEEB";
    public static String PLANE_NO = "Plane Number \uD83C\uDFAB";

    public static String DATE_OF_FLIGHT = "Date of Flight";

    public static String FLIGHT_ID_NO = "Enter Flight ID number \uD83C\uDFF7";

    public static String LOADING = "⌛️ Loading...   ⏳";
    public static String LOADING_MSG = "Here are the list of booked flights";


    //EXCEPTION MESSAGES
    public static String NO_BOOK_FLIGHTS = "No booked flights!";
    public static String BOOKING_NOT_FOUND = "Booking not found!";

    public static String BOOKING_EMPTY = "Bookings are empty!";





    //SAVE
    public static String INPUT_DESTINATION = "Enter destination";
    public static String INPUT_PASSENGERS = "Enter number of passengers";
    public static String INPUT_FLIGHT_CLASS = "Enter flight class";
    public static String INPUT_AIRPORT_TERMINAL = "Enter airport terminal";
    public static String INPUT_PLANE_NO = "Enter plane number";

    //EDIT
    public static String EDIT_QUESTION = "What would you want to update?";
    public static String INPUT_NEW_DESTINATION = "Enter new destination";
    public static String INPUT_NEW_PASSENGERS = "Enter new number of passengers";
    public static String INPUT_NEW_FLIGHT_CLASS = "Enter new flight class";
    public static String INPUT_NEW_AIRPORT_TERMINAL = "Enter new airport terminal";
    public static String INPUT_NEW_PLANE_NO = "Enter new plane number";



    public static String REF_NO = "Your reference number is -> ";
    public static String SUCCESS = "Successfully ";

    public static String FLIGHT_ID = " flight with id -> ";

    public static String LIST_OR_MAP = " Through LIST / MAP ";

    public static String DELETED = "deleted";
    public static String SAVED = "saved";




    public static String LIST = "List";
    public static String MAP = "Map";

    public static String ADD = "Save";
    public static String SEARCH = "Search";
    public static String VIEW = "View";
    public static String UPDATE = "Update";
    public static String DELETE = "Delete";



    public static Map<String,String> choices = new LinkedHashMap<>();
    public static void ChoiceValueKey(){
        choices.put("[1].", DESTINATION);
        choices.put("[2].", NO_OF_PASSENGERS);
        choices.put("[3].", FLIGHT_CLASS);
        choices.put("[4].", AIRPORT);
        choices.put("[5].", PLANE_NO);//add new otions
    }

    public static Map<String,String> listOptions = new LinkedHashMap<>();
    public static void ChoiceListOptions(){
        listOptions.put("[1].", LIST);
        listOptions.put("[2].", MAP);
    }




}
