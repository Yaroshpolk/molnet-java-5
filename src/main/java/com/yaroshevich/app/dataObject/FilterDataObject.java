package com.yaroshevich.app.dataObject;

public class FilterDataObject {

    private final int districtId;

    private final int regionId;

    private final String filterQr;

    private final String searchQr;

    public FilterDataObject(Integer districtId, Integer regionId, Integer filterType, String search) {
        this.districtId = districtId;
        this.regionId = regionId;
        this.filterQr = createFilterQuery(filterType);
        this.searchQr = createSearchQuery(search);
    }

    private String createFilterQuery(Integer filterType) {
        String res;

        switch (filterType) {
            case 1:
                res = "ORDER BY last_name, first_name, patronymic";
                break;
            case 2:
                res = "ORDER BY d2.district_name";
                break;
            case 3:
                res = "ORDER BY d.district_name";
                break;
            case 4:
                res = "ORDER BY age";
                break;
            default:
                res = "";
                break;
        }

        return res;
    }

    private String createSearchQuery(String searchLine) {
        String res = "(first_name LIKE \'%" + searchLine + "%\' or last_name LIKE \'%" + searchLine + "%\') OR " +
                "(CONCAT(last_name, \' \', first_name)) LIKE \'%" + searchLine + "%\'";

        System.out.println(res);

        return res;
    }

    public String generateQuery() {
        String res = "";

        if (this.districtId != 0 && this.regionId != 0) {
            res = "WHERE d2.id = " + this.districtId + " and " + "d.id = " + this.regionId +
                    " " + this.filterQr;
        } else if (this.districtId != 0 && this.regionId == 0) {
            res = "WHERE d2.id = " + this.districtId + " " + this.filterQr;
        } else if (this.districtId == 0 && this.regionId != 0) {
            res = "WHERE d.id = " + this.regionId + " " + this.filterQr;
        }

        if (!this.searchQr.equals("")) {
            if (res.equals("")) {
                res += "WHERE " + this.searchQr;
            } else {
                res += " and " + this.searchQr;
            }
        }

        res += this.filterQr;

        System.out.println(res);

        return res;
    }

}