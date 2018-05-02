package asquero.com.myapplication;

public class GridCompanyList {

    private String company;
    private int companyImage;

    GridCompanyList(String company, int companyImage) {
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
