package userModels;

public enum Genders {
FEMME("Femme"), HOMME("Homme");

private String value;

Genders(String value){
    this.value =value;
}
@Override
    public String toString(){
    return this.value;
}
}
