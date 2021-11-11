package dtos;

public class OurDTO {
    private String joke1;
    private String joke2;
    private String joke3;
    private String joke4;
    private String joke5;


    public OurDTO() {
    }

    public OurDTO(BoredDTO boredDTO, CatDTO catDTO, DogDTO dogDTO, GenderizeDTO genderizeDTO, RandomUserDTO randomUserDTO) {
        this.joke1 = boredDTO.getActivity();
        this.joke2 = catDTO.getFact();
        this.joke3 = dogDTO.getMessage();
        this.joke4 = genderizeDTO.getGender();
        this.joke5 = randomUserDTO.getGender();
    }

    public String getJoke1() {
        return joke1;
    }

    public void setJoke1(String joke1) {
        this.joke1 = joke1;
    }

    public String getJoke2() {
        return joke2;
    }

    public void setJoke2(String joke2) {
        this.joke2 = joke2;
    }

    public String getJoke3() {
        return joke3;
    }

    public void setJoke3(String joke3) {
        this.joke3 = joke3;
    }

    public String getJoke4() {
        return joke4;
    }

    public void setJoke4(String joke4) {
        this.joke4 = joke4;
    }

    public String getJoke5() {
        return joke5;
    }

    public void setJoke5(String joke5) {
        this.joke5 = joke5;
    }
}
