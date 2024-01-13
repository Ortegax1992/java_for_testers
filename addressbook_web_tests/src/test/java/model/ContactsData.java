package model;

public record ContactsData(
        String id,
        String firstname,
        String middlename,
        String lastname,
        String home,
        String mobile,
        String work,
        String secondary,
        String email,
        String email2,
        String address,
        String email3) {

    public ContactsData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactsData withId(String id) {
        return new ContactsData(id, this.firstname, this.middlename, this.lastname, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withFirstname(String firstname) {
        return new ContactsData(this.id, firstname, this.middlename, this.lastname, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withMiddlename(String middlename) {
        return new ContactsData(this.id, this.firstname, middlename, this.lastname, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withLastname(String lastname) {
        return new ContactsData(this.id, this.firstname, middlename, lastname, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withHome(String home) {
        return new ContactsData(this.id, this.firstname, this.middlename, this.lastname, home, this.mobile, this.work, this.secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withMobile(String mobile) {
        return new ContactsData(this.id, this.firstname, this.middlename, this.lastname, this.home, mobile, this.work, this.secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withWork(String work) {
        return new ContactsData(this.id, this.firstname, this.middlename, this.lastname, this.home, this.mobile, work, this.secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withSecondary(String secondary) {
        return new ContactsData(this.id, this.firstname, this.middlename, lastname, this.home, this.mobile, this.work, secondary, this.email, this.email2, this.address, this.email3);
    }

    public ContactsData withEmail(String email) {
        return new ContactsData(this.id, this.firstname, this.middlename, lastname, this.home, this.mobile, this.work, this.secondary, email, this.email2, this.address, this.email3);
    }

    public ContactsData withEmail2(String email2) {
        return new ContactsData(this.id, this.firstname, this.middlename, lastname, this.home, this.mobile, this.work, this.secondary, this.email, email2, this.address, this.email3);
    }

    public ContactsData withAddress(String address) {
        return new ContactsData(this.id, this.firstname, this.middlename, lastname, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, address, this.email3);
    }

    public ContactsData withEmail3(String email3) {
        return new ContactsData(this.id, this.firstname, this.middlename, lastname, this.home, this.mobile, this.work, this.secondary, this.email, this.email2, this.address, email3);
    }
}
