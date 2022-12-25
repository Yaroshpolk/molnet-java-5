package com.yaroshevich.app.filter;

public class FilterDataObject {

    private final int districtId;

    private final int regionId;

    private final String filterQr;

    private final String searchQr;

    public FilterDataObject(Integer districtId, Integer regionId, Integer filterType, String search) {
        this.districtId = districtId;
        this.regionId = regionId;
        this.filterQr = createFilterQuery(filterType);
        this.searchQr = createSearchQuery(search.toLowerCase().trim());
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
        String res = "";

        if (!searchLine.equals("")) {
            res = "((LOWER(first_name) LIKE '%" + searchLine + "%' or LOWER(last_name) LIKE '%" + searchLine +
                    "%' or LOWER(patronymic) LIKE '%" + searchLine + "%') OR (CONCAT(LOWER(last_name), ' ', " +
                    "LOWER(first_name), ' ', LOWER(first_name))) LIKE '%" + searchLine + "%')";
        }

        return res;
    }

    public String generateQuery() {
        String res = "";

        if (this.districtId != 0 && this.regionId != 0) {
            res = "WHERE d2.id = " + this.districtId + " and " + "d.id = " + this.regionId +
                    " ";
        } else if (this.districtId != 0 && this.regionId == 0) {
            res = "WHERE d2.id = " + this.districtId + " ";
        } else if (this.districtId == 0 && this.regionId != 0) {
            res = "WHERE d.id = " + this.regionId + " ";
        }

        if (!this.searchQr.equals("")) {
            if (res.equals("")) {
                res += "WHERE " + this.searchQr;
            } else {
                res += " and " + this.searchQr;
            }
        }

        res += this.filterQr;

        return res;
    }

}
