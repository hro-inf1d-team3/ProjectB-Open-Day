package app.inf1d_team3.open_day;

import java.util.ArrayList;

public class LocalDatabase {

    public static ArrayList<OpenDay> openDaysList = new ArrayList<>();

    public static void init(){
        openDaysList.add(new OpenDay("Business IT & Management",
                "Als BIM’er sla je de brug tussen ICT-techniek, organisaties en medewerkers. Bij elke verandering in het bedrijfsproces houd je rekening met deze drie partijen."));
        openDaysList.add(new OpenDay("Communicatie",
                "Heb je een scherp oog en oor voor de verhalen van mensen en merken? Ben je creatief met tekst en beeld en gefascineerd door de wereld van media? Dan is de opleiding Communicatie iets voor jou!"));
        openDaysList.add(new OpenDay("Communication and Multimedia Design",
                "Als CMD’er ontwerp je interactieve producten en diensten. Dat doe je met een creatief team voor échte opdrachtgevers in Studio’s en Labs."));
        openDaysList.add(new OpenDay("Creative Media and Game Technologies",
                "Creatieve technologen bedenken en ontwikkelen innovatieve digitale producten, waarbij de gebruiker centraal staat."));
        openDaysList.add(new OpenDay("Crossmediale Communicatie",
                "Zie jij jezelf de communicatie verzorgen voor een bedrijf of instelling? Denk je wel een opvallende, interactieve campagne te kunnen bedenken en ben je graag bezig met social media?"));
        openDaysList.add(new OpenDay("ICT Service Management",
                "De tweejarige, praktijkgerichte Ad ICT Service Management is bedoeld voor afgestudeerden van mbo en havo en al werkende ICT-professionals. De Ad leidt op tot professional ICT Service Management."));
        openDaysList.add(new OpenDay("Informatica",
                "We leiden je op tot software engineer. Je gaat aan de slag met het ontwikkelen van software voor diverse toepassingen. Je bent in staat complexe ICT-systemen te analyseren, ontwerpen en implementeren."));
        openDaysList.add(new OpenDay("Media Design and Communication",
                "Media Design & Communication, part of the Master of Arts in Fine Art and Design, is a research orientated master programme with an emphasis on the connection between practice and theory."));
        openDaysList.add(new OpenDay("Technische Informatica",
                "Als technisch informaticus werk je aan moderne ICT-systemen waarbij hard- en software geïntegreerd zijn. Dankzij je kennis van programmeren en hardware kun je juist op het snijpunt daarvan opereren."));
    }

    public static void destroy(){
        openDaysList.clear();
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
