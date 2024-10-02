package com.example.farmerassist.responses;

import java.util.ArrayList;

public class DaysWeatherResponse {


    public String cod;
    public int message;
    public int cnt;
    public ArrayList<List> list;
    public City city;


    public String getCod() {
        return cod;
    }

    public int getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public ArrayList<List> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public class City{
        public int id;
        public String name;
        public Coord coord;
        public String country;
        public int population;
        public int timezone;
        public int sunrise;
        public int sunset;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Coord getCoord() {
            return coord;
        }

        public String getCountry() {
            return country;
        }

        public int getPopulation() {
            return population;
        }

        public int getTimezone() {
            return timezone;
        }

        public int getSunrise() {
            return sunrise;
        }

        public int getSunset() {
            return sunset;
        }
    }

    public class Clouds{
        public int all;
    }

    public class Coord{
        public double lat;
        public double lon;
    }

    public class List{
        public int dt;
        public Main main;
        public ArrayList<Weather> weather;
        public Clouds clouds;
        public Wind wind;
        public int visibility;
        public double pop;
        public Sys sys;
        public String dt_txt;

        public int getDt() {
            return dt;
        }

        public Main getMain() {
            return main;
        }

        public ArrayList<Weather> getWeather() {
            return weather;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public int getVisibility() {
            return visibility;
        }

        public double getPop() {
            return pop;
        }

        public Sys getSys() {
            return sys;
        }

        public String getDt_txt() {
            return dt_txt;
        }
    }

    public class Main{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int sea_level;
        public int grnd_level;
        public int humidity;
        public double temp_kf;

        public int getHumidity() {
            return humidity;
        }

        public double getTemp() {
            return temp;
        }
    }





    public class Sys{
        public String pod;
    }

    public class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;

        public String getDescription() {
            return description;
        }
    }

    public class Wind{
        public double speed;
        public int deg;
        public double gust;

        public double getSpeed() {
            return speed;
        }
    }


}
