
import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

/**
 * Write a description of lowest_humidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindLowestHumidity {
public CSVRecord lowestHumidityInFile(CSVParser parser){
    CSVRecord humidity = null;
    for(CSVRecord currTemp : parser){
        if(currTemp.get("Humidity").equals("N/A")){continue;}
    humidity = compareTwo(humidity,currTemp);
    }   
    return humidity;
}

    public CSVRecord compareTwo(CSVRecord element1, CSVRecord element2){
    if(element1 == null){element1 = element2;}           
          else{               
            double coldestTemp = Double.parseDouble(element1.get("Humidity"));
            double currTemp = Double.parseDouble(element2.get("Humidity"));
            if(coldestTemp > currTemp){
            element1 = element2;            
            } 
                       
        }   
return element1;

}
public void testFile(){
FileResource fr = new FileResource();
CSVParser parser = fr.getCSVParser();
CSVRecord rec = lowestHumidityInFile(parser);
System.out.println("Lowest Humidity: " +rec.get("Humidity")+" was at "+
 rec.get("DateUTC"));
}

public CSVRecord fileWithLowestHumidity(){
CSVRecord lowestHumidity = null;
CSVRecord coldestHrs = null;
DirectoryResource dr = new DirectoryResource();
for(File f : dr.selectedFiles()){
    FileResource fr = new FileResource(f);
    CSVParser parser = fr.getCSVParser();
    CSVRecord currlowest = lowestHumidityInFile(parser);
     if(lowestHumidity == null ){
         lowestHumidity = currlowest;
        }           
          else{
            double coldest_Temp = Double.parseDouble(lowestHumidity.get("Humidity"));
            double curr_Temp = Double.parseDouble(currlowest.get("Humidity"));
            if(coldest_Temp > curr_Temp){
            lowestHumidity = currlowest;            
           
        }   
        }     
}   
    
return lowestHumidity;
}

public void  testLowestHumidityInManyFiles(){
CSVRecord fname = fileWithLowestHumidity();
System.out.println("Humidity : "+fname.get("Humidity")+"  at  "+ fname.get("DateUTC"));
}

public static void main(String[] args){
    FindLowestHumidity data = new FindLowestHumidity();
    data.testLowestHumidityInManyFiles();
}

}
