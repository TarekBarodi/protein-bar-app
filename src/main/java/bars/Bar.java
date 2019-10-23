package bars;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class Bar {
    private String name;
    private double fett;
    private double energy;
    private double kolhydrat;
    private double protein;
    private double fiber;
    private List<Reviewer> reviewers = new ArrayList<>();

    public Bar(String name, double fett, double energy, double kolhydrat, double protein, double fiber, List<Reviewer> reviewers) {
        this.name = name;
        this.fett = fett;
        this.energy = energy;
        this.kolhydrat = kolhydrat;
        this.protein = protein;
        this.fiber = fiber;
        this.reviewers = reviewers;
    }

    public Bar(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFett(double fett) {
        this.fett = fett;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void setKolhydrat(double kolhydrat) {
        this.kolhydrat = kolhydrat;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public void setReviewers(List<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

    public void addReviewer(Reviewer reviewer){
        this.reviewers.add(reviewer);
    }


    public String getName() {
        return name;
    }

    public double getFett() {
        return fett;
    }

    public double getEnergy() {
        return energy;
    }

    public double getKolhydrat() {
        return kolhydrat;
    }

    public double getProtein() {
        return protein;
    }

    public double getFiber() {
        return fiber;
    }

    public List<Reviewer> getReviewers() {
        return reviewers;
    }
}
