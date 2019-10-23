package xml_parser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import bars.Bar;
import bars.Reviewer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProteinParser
{
    public static void main(String args[]){
        //bars list is to hold all the data in the XML file, every bar has its own list of viewers
        List<Bar> bars = new ArrayList<>();

        try
        {
            //Create the SAX Parser Factory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            //Get an instance of the SAX Parser from the factory, the parser will parse the file using the handler
            SAXParser saxParser = factory.newSAXParser();

            //Create the handler which contains the code to parse the file
            XmlHandler handler = new XmlHandler();

            //This parsing command will trigger the handler code to run and parse the xml file
            saxParser.parse("../protein-bars/bars.xml", handler);

            //After parsing, the handler object is holding updated bars list, just we get it
            bars = handler.getBars();

            //Display options to the user
            System.out.println("Pick an option:");
            System.out.println("(1)\tShow all bars names.");
            System.out.println("(2)\tShow all bars based on highest protein.");
            System.out.println("(3)\tShow all bars based on highest fat.");
            System.out.println("(4)\tShow all bars whose fett less than certain fett based on highest fat");
            System.out.println("Enter a number between 1 and 3:");

            //Get the user input
            int option = 0;
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                option = scanner.nextInt();
            }

            switch (option){
                case 0:
                    System.out.println("Thank you good luck!");
                    break;
                case 1:
                    //Print the bars names
                    List<String> barsNames = getBarsNames(bars);
                    System.out.println("All Bars Names: ");
                    printListOfStrings(barsNames);
                    break;
                case 2:
                    //Print the bars names sorted based on highest protein
                    List<String> sortedBarsNamesByProtein = getBarsNamesSortedByProtein(bars);
                    System.out.println("All Bars Names Sorted by protein: ");
                    printListOfStrings(sortedBarsNamesByProtein);
                    break;
                case 3:
                    //Print the bars names sorted based on highest fett
                    List<String> sortedBarsNamesByFett = getBarsNamesSortedByFett(bars);
                    System.out.println("All Bars Names Sorted by fett: ");
                    printListOfStrings(sortedBarsNamesByFett);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getBarsNamesSortedByFett(List<Bar> bars) {
        List<Bar> sortedBarsByFett = bars.stream().sorted(Comparator.comparing(Bar::getFett).reversed())
                                     .collect(Collectors.toList());
        return getBarsNames(sortedBarsByFett);
    }

    private static List<String> getBarsNamesSortedByProtein(List<Bar> bars) {
        List<Bar> sortedBarsByProtein = bars.stream().sorted(Comparator.comparing(Bar::getProtein).reversed())
                                        .collect(Collectors.toList());
        return getBarsNames(sortedBarsByProtein);

    }

    private static void printListOfStrings(List<String> listOfStrings) {
        for (String txt:listOfStrings){
            System.out.println(txt);
        }

    }

    private static List<String> getBarsNames(List<Bar> bars) {
        List<String> list = new ArrayList<>();

        for (Bar bar:bars){
            list.add(bar.getName());
        }
        return list;
    }
}