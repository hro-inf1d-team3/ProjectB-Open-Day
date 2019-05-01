package app.inf1d_team3.open_day;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocalDatabase {

    public static ArrayList<OpenDay> openDaysList = new ArrayList<>();

    public static void init(){
        Calendar openDayCalendar = Calendar.getInstance();
        openDayCalendar.clear();
        openDayCalendar.set(2020, 1, 15);
        Date openDayDate = openDayCalendar.getTime();
        String location = "Wijnhaven 107, 3011 WN Rotterdam";

        openDaysList.add(new OpenDay("Business IT & Management","Als BIM’er sla je de brug tussen ICT-techniek, organisaties en medewerkers. Bij elke verandering in het bedrijfsproces houd je rekening met deze drie partijen.",
                openDayDate, location));
        openDaysList.add(new OpenDay("Communicatie","Heb je een scherp oog en oor voor de verhalen van mensen en merken? Ben je creatief met tekst en beeld en gefascineerd door de wereld van media? Dan is de opleiding Communicatie iets voor jou!",
                openDayDate, location));
        openDaysList.add(new OpenDay("Communication and Multimedia Design","Als CMD’er ontwerp je interactieve producten en diensten. Dat doe je met een creatief team voor échte opdrachtgevers in Studio’s en Labs.",
                openDayDate, location));
        openDaysList.add(new OpenDay("Creative Media and Game Technologies","Creatieve technologen bedenken en ontwikkelen innovatieve digitale producten, waarbij de gebruiker centraal staat.",
                openDayDate, location));
        openDaysList.add(new OpenDay("Crossmediale Communicatie","Zie jij jezelf de communicatie verzorgen voor een bedrijf of instelling? Denk je wel een opvallende, interactieve campagne te kunnen bedenken en ben je graag bezig met social media?",
                openDayDate, location));
        openDaysList.add(new OpenDay("ICT Service Management","De tweejarige, praktijkgerichte Ad ICT Service Management is bedoeld voor afgestudeerden van mbo en havo en al werkende ICT-professionals. De Ad leidt op tot professional ICT Service Management.",
                openDayDate, location));
        openDaysList.add(new OpenDay("Informatica","We leiden je op tot software engineer. Je gaat aan de slag met het ontwikkelen van software voor diverse toepassingen. Je bent in staat complexe ICT-systemen te analyseren, ontwerpen en implementeren.",
                openDayDate, location));
        openDaysList.add(new OpenDay("Media Design and Communication","Media Design & Communication, part of the Master of Arts in Fine Art and Design, is a research orientated master programme with an emphasis on the connection between practice and theory.",
                openDayDate, location));
        openDaysList.add(new OpenDay("Technische Informatica","Als technisch informaticus werk je aan moderne ICT-systemen waarbij hard- en software geïntegreerd zijn. Dankzij je kennis van programmeren en hardware kun je juist op het snijpunt daarvan opereren.",
                openDayDate, location));

        for (OpenDay openDay : openDaysList) {
            openDay.addEvent(new OpenDayEvent("Intro", "Introduction to the open day"), 13, 0);
            openDay.addEvent(new OpenDayEvent("Course information", "A dedicated half hour to explain what is going to happen during the course"), 14, 2);
            openDay.addEvent(new OpenDayEvent("Project presentation", "A couple of students will show what they made"), 15, 35);
        }
    }

    public static void destroy(){
        openDaysList.clear();
    }

    public static class OpenDayEvent {
        public String name, description;
        private Date dateTime;

        public OpenDayEvent(String name, String description){
            this.name = name;
            this.description = description;
        }

        public String getTime(){
            return SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT, Locale.getDefault()).format(dateTime);
        }

        public OpenDayEvent setDateTime(Date dateTime){
            if(this.dateTime == null) this.dateTime = dateTime;
            return this;
        }
    }

    public static class OpenDay {
        public String name, description, location;
        public Date date;
        private ArrayList<OpenDayEvent> events;

        public OpenDay(String name, String description, Date date, String location){
            this.name = name;
            this.description = description;
            this.location = location;
            this.date = date;
            this.events = new ArrayList<>();
        }

        public String getDateAsText(){
            return SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault()).format(this.date);
        }

        public OpenDayEvent[] addEvent(OpenDayEvent event, int hourOfDay, int minute){
            Calendar openDayDateTime = Calendar.getInstance();
            openDayDateTime.setTime(this.date);
            openDayDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            openDayDateTime.set(Calendar.MINUTE, minute);

            this.events.add(event.setDateTime(openDayDateTime.getTime()));

            return this.getOpenDayEvents();
        }

        public OpenDayEvent[] getOpenDayEvents(){
            return this.events.toArray(new OpenDayEvent[0]);
        }
    }
}
