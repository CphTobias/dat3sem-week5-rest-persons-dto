package dtos;

public class CountryDTO {

    private String name;
    private String population;
    private String area;
    private String[] borders;

    public CountryDTO(String countryName, String population, String area, String[] borders) {
        this.name = countryName;
        this.population = population;
        this.area = area;
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
            "name='" + name + '\'' +
            ", population='" + population + '\'' +
            ", area='" + area + '\'' +
            ", borders='" + borders + '\'' +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }
}
