package backend.aanspraak.model;

public class Werkgever {

    private String employerId;
    private String companyName;

    public Werkgever(String employerId, String companyName) {
        this.employerId = employerId;
        this.companyName = companyName;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getCompanyName() {
        return companyName;
    }
}
