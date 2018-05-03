package asquero.com.myapplication;

class ContestDetailList {

    private String contestHeading;
    private String contestDetails;

    ContestDetailList(String contestHeading, String contestDetails) {
        this.contestHeading = contestHeading;
        this.contestDetails = contestDetails;
    }

    public String getContestHeading() {
        return contestHeading;
    }

    public String getContestDetails() {
        return contestDetails;
    }

}
