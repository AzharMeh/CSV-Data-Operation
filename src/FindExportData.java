
import edu.duke.*;
import org.apache.commons.csv.*;

public class FindExportData {
public void findExport(CSVParser parser, String exportOfintrest){
for (CSVRecord csv : parser){
    String export = csv.get("Exports");

    if(export.contains(exportOfintrest)){
        String country = csv.get("Country");
        System.out.println(country+" "+exportOfintrest);
        }
    }
}
// find the given country with its exports and values and print
public String countryInfo (CSVParser parser, String country){
    for(CSVRecord csv : parser){
    String f_country = csv.get("Country");
        if(f_country.contains(country)){
            
           String export = csv.get("Exports");
            String value = csv.get("Value (dollars)");
            String find = f_country+" : "+export+" : "+value;
            return find;
    
    }
    
    }
    return "no data found";
    
}
//find which country export both items 
public void listExportersTwoProducts (CSVParser parser, 
String exportItem1, String exportItem2){
    for (CSVRecord csv : parser){
    String export = csv.get("Exports");
        if(export.contains(exportItem1) && export.contains(exportItem2)){
        String country = csv.get("Country");
        System.out.println(country);
        }
        
    }
    
    
}
// returns the number of exporter country of the item
public int numberOfExporters(CSVParser parser, String exportItem){
    int count = 0;
for(CSVRecord csv : parser){
String export = csv.get("Exports");
if(export.contains(exportItem)){
count += 1;
}
}

return count;
}
public void bigExporters (CSVParser parser, String amount){
for(CSVRecord csv : parser){
String value = csv.get("Value (dollars)");
if(value.length() > amount.length()){
String country = csv.get("Country");
System.out.println(country+": "+value);
}
}


}
    public void testClassOfAllMethods(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    String containt = countryInfo(parser,"Nauru");
    System.out.println(containt);
    listExportersTwoProducts(parser, "cotton","flowers");
    int count = numberOfExporters(parser, "cocoa");
    System.out.printf("Total %d countries exports cocoa",count);    
    bigExporters(parser, "$999,999,999,999");
    
    }

    public static void main (String[] args){
        FindExportData exp = new FindExportData();
        exp.testClassOfAllMethods();
    }
}
