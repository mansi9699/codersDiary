package asquero.com.myapplication;

class DetailList {

    private String contestCode;
    private String contestName;
    private String startDate;
    private String endDate;
    private int contestSourceImg;
    private String imageUrl;
    private String AIC;
    private String contestSource;

    DetailList(String contestCode, String contestName, String startDate, String endDate, int contestSourceImg, String imageUrl, String AIC, String contestSource) {
        this.contestCode = contestCode;
        this.contestName = contestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contestSourceImg = contestSourceImg;
        this.imageUrl = imageUrl;
        this.AIC = AIC;
        this.contestSource = contestSource;
    }

    public String getContestCode() {
        return contestCode;
    }

    public String getContestName() {
        return contestName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getContestSourceImg() {
        return contestSourceImg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAIC() {
        return AIC;
    }

    public String getContestSource() {
        return contestSource;
    }
}
