package asquero.com.myapplication;

class ContestDetailList {

    private String contestHeading;
    private String contestDetails;
    private int svgImage;

    public ContestDetailList(String contestHeading, String contestDetails, int svgImage) {
        this.contestHeading = contestHeading;
        this.contestDetails = contestDetails;
        this.svgImage = svgImage;
    }

    public String getContestHeading() {
        return contestHeading;
    }

    public String getContestDetails() {
        return contestDetails;
    }

    public int getSvgImage() {
        return svgImage;
    }
}
