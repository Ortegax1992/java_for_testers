package model;

public record ContactsData(String firstname, String middlename, String lastname, String nickname, String title) {
    public ContactsData() {
        this("", "", "", "", "");
    }
}
