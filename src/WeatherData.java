
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Scanner;

public class WeatherData {
    //Find Hottest:
    
    public  CSVRecord maxTemp(CSVParser parser, String field){
      
    CSVRecord largest = null;
    double largetTemp = 0.0;
    String TimeEST = null;
        for (CSVRecord record : parser){
            
            String temp = record.get(field);
            double currtemp = Double.parseDouble(temp);
            if(largetTemp < currtemp){
            largetTemp = currtemp;
            largest = record;
            }
        
        }
    //String Largest = "Maximum Temprature is: "+largetTemp+" at time: "+TimeEST;
    return largest;
}
//compare multiple data sheets and find the hottest hour of yhe day in them
public CSVRecord hottestInManyDays(){
    
    DirectoryResource dr = new DirectoryResource();
    CSVRecord largest = null;    
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter field name from CSV file to find Hottest Day:");
    String field = input.nextLine();
    for(File f : dr.selectedFiles()){        
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();

        CSVRecord curr_Temp = maxTemp(parser,field);
        largest = compareTwo(largest,curr_Temp,field);

    }
return largest;    
}

public CSVRecord compareTwo(CSVRecord element1, CSVRecord element2,String field ){
if(element1 == null){element1 = element2;}           
          else{
            double largestTemp = Double.parseDouble(element1.get(field));
            double currTemp = Double.parseDouble(element2.get(field));
        
            if(largestTemp < currTemp && currTemp > 0){
            element1 = element2;
            }   
        }   
return element1;
}

public void test(){
    

CSVRecord maxTemp = hottestInManyDays();
//System.out.println(maxTemp);
System.out.println("Maximum Temperature is: "+maxTemp.get("TemperatureF")+
" at time: "+maxTemp.get("TimeEST"));
}

public static void main(String[] args){
WeatherData data = new WeatherData();
data.test();
}

}
//TemperatureF