package model;

public record ContactsData(String id, String firstname, String middlename, String lastname) {
    public ContactsData() {
        this("", "", "", "");
    }

    public ContactsData withId(String id) {
        return new ContactsData(id, this.firstname, this.middlename, this.lastname);
    }

    public ContactsData withFirstname(String firstname) {
        return new ContactsData(this.id, firstname, this.middlename, this.lastname);
    }

    public ContactsData withMiddlename(String middlename) {
        return new ContactsData(this.id, this.firstname, middlename, this.lastname);
    }

    public ContactsData withLastname(String lastname) {
        return new ContactsData(this.id, this.firstname, this.middlename, lastname);
    }


}
