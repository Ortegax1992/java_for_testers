package manager;

import io.qameta.allure.Step;
import manager.hbm.ContactsRecord;
import manager.hbm.GroupRecord;
import model.ContactsData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);


        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactsRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    static List<ContactsData> convertContactsList(List<ContactsRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static ContactsData convert(ContactsRecord record) {
        return new ContactsData().withId("" + record.id)
                .withFirstname(record.firstname)
                .withMiddlename(record.middlename)
                .withLastname(record.lastname)
                .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work)
                .withSecondary(record.phone2)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withAddress(record.address);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static ContactsRecord convert(ContactsData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactsRecord(Integer.parseInt(id), data.firstname(), data.middlename(), data.lastname(),
                data.home(), data.mobile(), data.work(), data.secondary(), data.address(), data.email(), data.email2(), data.email3());
    }

    @Step
    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public List<ContactsData> getContactsList() {
        return convertContactsList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactsRecord", ContactsRecord.class).list();
        }));
    }

    public long getContactsCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactsRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactsData contactsData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactsData));
            session.getTransaction().commit();
        });
    }

    public List<ContactsData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactsList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public boolean checkingContactInGroup(GroupData group, ContactsData contact) {
        return getContactsInGroup(group).contains(contact);
    }
}
