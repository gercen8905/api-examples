package pojo;

import java.util.List;

public class RequestBody {
    String request = "{\n" +
            "  \"location\": {\n" +
            "    \"lat\": -38.383494,\n" +
            "    \"lng\": 33.427362\n" +
            "  },\n" +
            "  \"accuracy\": 50,\n" +
            "  \"name\": \"Our home\",\n" +
            "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
            "  \"address\": \"29, side layout, cohen 09\",\n" +
            "  \"types\": [\n" +
            "    \"shoe park\",\n" +
            "    \"shop\"\n" +
            "  ],\n" +
            "  \"website\": \"http://google.com\",\n" +
            "  \"language\": \"French-IN\"\n" +
            "}\n";
    private Location location;
    private double accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types;
    private String website;
    private String language;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
