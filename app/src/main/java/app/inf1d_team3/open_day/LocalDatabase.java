package app.inf1d_team3.open_day;

import java.util.ArrayList;

public class LocalDatabase {

    public static ArrayList<OpenDay> openDaysList = new ArrayList<>();

    public static void init(){
        openDaysList.add(new OpenDay("Business IT & Management", ""));
        openDaysList.add(new OpenDay("Communicatie",""));
        openDaysList.add(new OpenDay("Communication and Multimedia Design",""));
        openDaysList.add(new OpenDay("Creative Media and Game Technologies",""));
        openDaysList.add(new OpenDay("Crossmediale Communicatie",""));
        openDaysList.add(new OpenDay("ICT Service Management",""));
        openDaysList.add(new OpenDay("Informatica",""));
        openDaysList.add(new OpenDay("Media Design and Communication",""));
        openDaysList.add(new OpenDay("Technische Informatica", ""));
    }

    public static class OpenDay {
        private String name, description;

        public OpenDay(String name, String description){
            this.name = name;
            this.description = description;
        }

        public String getName(){
            return name;
        }

        public String getDescription(){
            return description;
        }
    }





}
