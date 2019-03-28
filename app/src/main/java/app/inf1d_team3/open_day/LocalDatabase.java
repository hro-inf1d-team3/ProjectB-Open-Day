package app.inf1d_team3.open_day;

import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LocalDatabase {

    public static ArrayList<OpenDay> openDaysList = new ArrayList<>();

    public static void init(){
        Calendar openDayDate = Calendar.getInstance();
        openDayDate.clear();
        openDayDate.set(2020, 1, 15);

        openDaysList.add(new OpenDay("Business IT & Management", openDayDate,
                "Als BIM’er sla je de brug tussen ICT-techniek, organisaties en medewerkers. Bij elke verandering in het bedrijfsproces houd je rekening met deze drie partijen."));
        openDaysList.add(new OpenDay("Communicatie", openDayDate,
                "Heb je een scherp oog en oor voor de verhalen van mensen en merken? Ben je creatief met tekst en beeld en gefascineerd door de wereld van media? Dan is de opleiding Communicatie iets voor jou!"));
        openDaysList.add(new OpenDay("Communication and Multimedia Design", openDayDate,
                "Als CMD’er ontwerp je interactieve producten en diensten. Dat doe je met een creatief team voor échte opdrachtgevers in Studio’s en Labs."));
        openDaysList.add(new OpenDay("Creative Media and Game Technologies", openDayDate,
                "Creatieve technologen bedenken en ontwikkelen innovatieve digitale producten, waarbij de gebruiker centraal staat."));
        openDaysList.add(new OpenDay("Crossmediale Communicatie", openDayDate,
                "Zie jij jezelf de communicatie verzorgen voor een bedrijf of instelling? Denk je wel een opvallende, interactieve campagne te kunnen bedenken en ben je graag bezig met social media?"));
        openDaysList.add(new OpenDay("ICT Service Management", openDayDate,
                "De tweejarige, praktijkgerichte Ad ICT Service Management is bedoeld voor afgestudeerden van mbo en havo en al werkende ICT-professionals. De Ad leidt op tot professional ICT Service Management."));
        openDaysList.add(new OpenDay("Informatica", openDayDate,
                "We leiden je op tot software engineer. Je gaat aan de slag met het ontwikkelen van software voor diverse toepassingen. Je bent in staat complexe ICT-systemen te analyseren, ontwerpen en implementeren."));
        openDaysList.add(new OpenDay("Media Design and Communication", openDayDate,
                "Media Design & Communication, part of the Master of Arts in Fine Art and Design, is a research orientated master programme with an emphasis on the connection between practice and theory."));
        openDaysList.add(new OpenDay("Technische Informatica", openDayDate,
                "Als technisch informaticus werk je aan moderne ICT-systemen waarbij hard- en software geïntegreerd zijn. Dankzij je kennis van programmeren en hardware kun je juist op het snijpunt daarvan opereren."));

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
        private String name, description;
        private Date dateTime;

        public OpenDayEvent(String name, String description){
            this.name = name;
            this.description = description;
        }

        public String getName(){
            return this.name;
        }

        public String getDescription(){
            return this.description;
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
        private String name, description;
        private ArrayList<OpenDayEvent> events;
        private Calendar date;

        public OpenDay(String name, Calendar date, String description){
            this.name = name;
            this.date = date;
            this.description = description;
            this.events = new ArrayList<>();
        }

        public String getName(){
            return name;
        }

        public String getDescription(){
            return description;
        }

        public String getDate(){
            return SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault()).format(this.date.getTime());
        }

        public OpenDayEvent[] addEvent(OpenDayEvent event, int hourOfDay, int minute){
            Calendar openDayDateTime = Calendar.getInstance();
            openDayDateTime.setTime(this.date.getTime());
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
