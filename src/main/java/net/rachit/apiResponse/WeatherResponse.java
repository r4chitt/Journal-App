package net.rachit.apiResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
        private Current current;



   @Getter
   @Setter
    public static class Current{

        public int temperature;

        public int feelslike;

    }







}
