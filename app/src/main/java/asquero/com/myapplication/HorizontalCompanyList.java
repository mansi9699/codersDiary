package asquero.com.myapplication;

public class HorizontalCompanyList {

    private String company;
    private int companyImage;

    HorizontalCompanyList(String company, int companyImage) {
        this.company = company;
        this.companyImage = companyImage;
    }

    public String getCompany() {
        return company;
    }

    public int getCompanyImage() {
        return companyImage;
    }
}
