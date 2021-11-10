package dtos;

public class RandomUserDTO {
    private String gender;
    private String name;

    public RandomUserDTO() {
    }

    public RandomUserDTO(String gender, String name) {
        this.gender = gender;
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
