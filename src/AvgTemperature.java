
import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;
public class AvgTemperature {
public Double averageTemperatureInFile (CSVParser parser){
    double count = 0.0;
    double totalTemp = 0.0;
for (CSVRecord rec : parser){
double currTemp = Double.parseDouble(rec.get("TemperatureF"));
totalTemp += currTemp;
count += 1;
}
Double Avg = totalTemp/count;
return Avg;
}

public Double averageTemperatureWithHighHumidityInFile
(CSVParser parser, int value){
int count = 0;
double total =0.0;
for(CSVRecord rec : parser){
 
   double humidity =  Double.parseDouble(rec.get("Humidity"));
if(humidity >= value){
double currTemp = Double.parseDouble(rec.get("TemperatureF"));
total += currTemp;
count += 1;
}
}
Double Avg = total/count;
return Avg;
}


public void testAvg(){
FileResource fr = new FileResource();
CSVParser parser = fr.getCSVParser();
double avg = averageTemperatureInFile(parser);
System.out.println("Average temperature in file is "+avg);
double avgWithHum = averageTemperatureWithHighHumidityInFile(parser,80);
System.out.println("Average temperature in file with high humidity "+avgWithHum);
}
public static void main(String[] args){
    AvgTemperature data = new AvgTemperature();
    data.testAvg();
}

}
