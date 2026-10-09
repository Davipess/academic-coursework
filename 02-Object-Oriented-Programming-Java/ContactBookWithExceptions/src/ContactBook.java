/**
 * The ContactBook class represents a collection of contacts.
 */
public class ContactBook {
    private static final int DEFAULT_SIZE = 100;
    private static final int NOT_FOUND = -1;
    private static final int FACTOR_RESIZE = 2;

    private int counter;
    private Contact[] contacts;

    public ContactBook() {
        counter = 0;
        contacts = new Contact[DEFAULT_SIZE];
    }

    public boolean hasContact(String name) {
        return searchIndex(name) >= 0;
    }

    public boolean hasPhone(int phone) {
        return searchIndexNum(phone) >= 0;
    }

    public String getNameWithNumber(int phone) {
        return contacts[searchIndexNum(phone)].getName();
    }

    public int getNumberOfContacts() {
        return counter;
    }

    public void addContact(String name, int phone, String email) throws ContactAlreadyExistsException {
        if (hasContact(name)) {
            throw new ContactAlreadyExistsException();
        }
        if (counter == contacts.length)
            resize();
        contacts[counter] = new Contact(name, phone, email);
        counter++;
    }

    public void deleteContact(String name) throws ContactDoesNotExistException {
        int index = searchIndex(name);
        if (index == NOT_FOUND) {
            throw new ContactDoesNotExistException();
        }
        for (int i = index; i < counter - 1; i++)
            contacts[i] = contacts[i + 1];
        counter--;
    }

    public int getPhone(String name) throws ContactDoesNotExistException {
        int index = searchIndex(name);
        if (index == NOT_FOUND) {
            throw new ContactDoesNotExistException();
        }
        return contacts[index].getPhone();
    }

    public String getEmail(String name) throws ContactDoesNotExistException {
        int index = searchIndex(name);
        if (index == NOT_FOUND) {
            throw new ContactDoesNotExistException();
        }
        return contacts[index].getEmail();
    }

    public void setPhone(String name, int phone) throws ContactDoesNotExistException {
        int index = searchIndex(name);
        if (index == NOT_FOUND) {
            throw new ContactDoesNotExistException();
        }
        contacts[index].setPhone(phone);
    }

    public void setEmail(String name, String email) throws ContactDoesNotExistException {
        int index = searchIndex(name);
        if (index == NOT_FOUND) {
            throw new ContactDoesNotExistException();
        }
        contacts[index].setEmail(email);
    }

    public ContactIterator iterator() {
        return new ContactIterator(contacts, counter);
    }

    private int searchIndex(String name) {
        int i = 0;
        int result = NOT_FOUND;
        boolean found = false;
        while (i < counter && !found)
            if (contacts[i].getName().equals(name))
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }

    private int searchIndexNum(int phone) {
        int i = 0;
        int result = NOT_FOUND;
        boolean found = false;
        while (i < counter && !found)
            if (contacts[i].getPhone() == phone)
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }

    private void resize() {
        Contact tmp[] = new Contact[FACTOR_RESIZE * contacts.length];
        for (int i = 0; i < counter; i++)
            tmp[i] = contacts[i];
        contacts = tmp;
    }

    public boolean contactsComparer() {
        boolean found = false;
        for (int i = 0; i < counter && !found; i++) {
            for (int j = i + 1; j < counter && !found; j++) {
                if (contacts[i].getPhone() == contacts[j].getPhone()) {
                    found = true;
                }
            }
        }
        return found;
    }
}