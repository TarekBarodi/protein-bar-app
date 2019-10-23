package xml_parser;

import bars.Bar;
import bars.Reviewer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XmlHandler extends DefaultHandler {
    private boolean isBar = false;
    private boolean isFett = false;
    private boolean isEnergy = false;
    private boolean isKolhydrat = false;
    private boolean isProtein = false;
    private boolean isFiber = false;
    private boolean isReview = false;
    private boolean isReviewer = false;
    private boolean isDate = false;
    private boolean isScore = false;

    //Declare bar to be available for beneath methods
    private Bar bar = new Bar();
    private Reviewer reviewer = new Reviewer();

    public List<Bar> getBars() {
        return bars;
    }

    private List<Bar> bars = new ArrayList<>();

    //parser starts parsing a specific element inside the document
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("Start Element :" + qName);
        if (qName.toLowerCase().endsWith("bar")) {
            isBar = true;
            bar = new Bar();
            bar.setName(qName);
        }

        if (qName.equalsIgnoreCase("fett")) {
            isFett = isBar;
        }

        if (qName.equalsIgnoreCase("energy")) {
            isEnergy = isBar;
        }

        if (qName.equalsIgnoreCase("kolhydrat")) {
            isKolhydrat = isBar;
        }

        if (qName.equalsIgnoreCase("protein")) {
            isProtein = isBar;
        }

        if (qName.equalsIgnoreCase("fiber")) {
            isFiber = isBar;
        }

        if (qName.equalsIgnoreCase("review")) {
            isReview = isBar;
        }

        if (qName.equalsIgnoreCase("reviewer")) {
            reviewer = new Reviewer();
            reviewer.setId(attributes.getValue(0));
            isReviewer = isReview;
        }


        if (qName.equalsIgnoreCase("date")) {
            isDate = isReviewer;
        }

        if (qName.equalsIgnoreCase("score")) {
            isScore = isReviewer;
        }

    }

    //parser ends parsing the specific element inside the document
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("reviewer")){
            bar.addReviewer(reviewer);
            reviewer = null;
            isReviewer = false;
        }

        if (qName.equalsIgnoreCase("review")){
            isReview = false;
        }

        if (qName.toLowerCase().endsWith("bar")){
            bars.add(bar);
            bar = null;
            isBar = false;
        }

    }



    //reads the text value of the currently parsed element
    public void characters(char ch[], int start, int length) throws SAXException
    {
        if (isBar && !isReview) {
            double valueDbl;
            if (isFett) {
                valueDbl = Double.parseDouble(new String(ch, start, length));
                bar.setFett(valueDbl);
                isFett = false;
            }

            if (isEnergy) {
                valueDbl = Double.parseDouble(new String(ch, start, length));
                bar.setEnergy(valueDbl);
                isEnergy = false;
            }

            if (isKolhydrat) {
                valueDbl = Double.parseDouble(new String(ch, start, length));
                bar.setKolhydrat(valueDbl);
                isKolhydrat = false;
            }

            if (isProtein) {
                valueDbl = Double.parseDouble(new String(ch, start, length));
                bar.setProtein(valueDbl);
                isProtein = false;
            }

            if (isFiber) {
                valueDbl = Double.parseDouble(new String(ch, start, length));
                bar.setFiber(valueDbl);
                isFiber = false;
            }

            //if isReviewer is true this means that we have created already an objects of type Reviewer
            //and assigned it id field.
        } else if (isReviewer){
            if (isDate){
                reviewer.setDate(new String(ch, start, length));
                isDate = false;
            } else if (isScore){
                reviewer.setScore(Integer.parseInt(new String(ch,start,length)));
                isScore = false;
            }
        }


    }
}
