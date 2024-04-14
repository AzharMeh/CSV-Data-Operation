import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;
/**
 the program to find the coldest day in csv files and otherfunctions related 
 to csv data
 */

public class FindColdestWeather {
    public CSVRecord coldestHourInFile(CSVParser parser){
    CSVRecord coldest = null;
    for(CSVRecord currTemp : parser){
    coldest = compareTwo(coldest,currTemp);
    }   
    return coldest;
}

    public CSVRecord compareTwo(CSVRecord element1, CSVRecord element2){
    if(element1 == null){element1 = element2;}           
          else{
            double coldestTemp = Double.parseDouble(element1.get("TemperatureF"));
            double currTemp = Double.parseDouble(element2.get("TemperatureF"));
            if(coldestTemp > currTemp){
            element1 = element2;
            
            }   
        }   
return element1;
}
public File fileWithColdestTemperature(){
   CSVRecord coldestTemp = null;
   File fname = null;
   CSVRecord coldestHrs = null;
DirectoryResource dr = new DirectoryResource();
for(File f : dr.selectedFiles()){
    FileResource fr = new FileResource(f);
    CSVParser parser = fr.getCSVParser();
    CSVRecord currTemp = coldestHourInFile(parser);
     if(coldestTemp == null ){coldestTemp = currTemp;}           
          else{
            double coldest_Temp = Double.parseDouble(coldestTemp.get("TemperatureF"));
            double curr_Temp = Double.parseDouble(currTemp.get("TemperatureF"));
            if(coldest_Temp > curr_Temp && curr_Temp > -9999){
            coldestTemp = currTemp;            
            fname = f;
            }   
        } 
    
}
    
    
return fname;
}
public void testFiles(){
File fname = fileWithColdestTemperature();
System.out.println("Coldest day was in file :"+fname.getName());
FileResource fr = new FileResource(fname);
CSVParser parser = fr.getCSVParser();
CSVRecord coldestTemp = coldestHourInFile(parser);
System.out.println("Coldest hour is : "+coldestTemp.get("TemperatureF"));
//printALL(fname);
}
public void printALL(File fname){
FileResource fr = new FileResource(fname);
CSVParser parser = fr.getCSVParser();
for(CSVRecord rec : parser){
System.out.println(rec.get("DateUTC")+" "+rec.get("TemperatureF"));
}


}
public void testColdestHourInFile(){
    
    
FileResource fr = new FileResource();
CSVParser parser = fr.getCSVParser();
CSVRecord coldestHour = coldestHourInFile(parser);
System.out.println("Coldest day was in file "+"" +coldestHour.get("TemperatureF")+
" at time: "+coldestHour.get("TimeEST"));

}

public static void main(String[] args){
    FindColdestWeather data = new FindColdestWeather();
    data.testFiles();
}

}
