package com.example.farmerassist.responses;

import java.util.List;

public class WeatherResponse {

     private String base;
     private double visibility;
     private double dt;
     private double timezone;
     private double id;
     private String name;
     private int cod;
     private Coord coord;
     private List<Weather> weather;
     private Main main;
     private Wind wind;
     private Clouds clouds;
     private Sys sys;

     public class Coord {
         private double lon;
         private double lat;

         public double getLon() {
             return lon;
         }

         public double getLat() {
             return lat;
         }
     }

     public class Weather {
         private int id;
         private String main;
         private String description;
         private String icon;

         public int getId() {
             return id;
         }

         public String getMain() {
             return main;
         }

         public String getDescription() {
             return description;
         }

         public String getIcon() {
             return icon;
         }
     }

     public class Main {
         private double temp;
         private double feels_like;
         private double temp_min;
         private double temp_max;
         private double pressure;
         private int humidity;
         private double sea_level;
         private double grnd_level;

         public double getTemp() {
             return temp;
         }

         public double getFeels_like() {
             return feels_like;
         }

         public double getTemp_min() {
             return temp_min;
         }

         public double getTemp_max() {
             return temp_max;
         }

         public double getPressure() {
             return pressure;
         }

         public int getHumidity() {
             return humidity;
         }

         public double getSea_level() {
             return sea_level;
         }

         public double getGrnd_level() {
             return grnd_level;
         }
     }

     public class Wind {
         private double speed;
         private double deg;

         public double getSpeed() {
             return speed;
         }

         public double getDeg() {
             return deg;
         }
     }

     public class Clouds {
         private double all;

         public double getAll() {
             return all;
         }
     }

     public class Sys {
         private int type;
         private int id;
         private String country;
         private long sunrise;
         private long sunset;

         public int getType() {
             return type;
         }

         public int getId() {
             return id;
         }

         public String getCountry() {
             return country;
         }

         public long getSunrise() {
             return sunrise;
         }

         public long getSunset() {
             return sunset;
         }
     }

    public String getBase() {
        return base;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getDt() {
        return dt;
    }

    public double getTimezone() {
        return timezone;
    }

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Sys getSys() {
        return sys;
    }
}
