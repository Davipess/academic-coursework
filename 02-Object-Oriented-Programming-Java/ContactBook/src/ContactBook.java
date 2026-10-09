/**
 * The ContactBook class represents a collection of contacts.
 */
public class ContactBook {
    private static final int DEFAULT_SIZE = 100;
    private static final int NOT_FOUND = -1;
    private static final int FACTOR_RESIZE = 2;

    private int counter;
    private Contact[] contacts;

    /**
     * Constructs a ContactBook with a default size and initializes the counter.
     */
    public ContactBook() {
        counter = 0;
        contacts = new Contact[DEFAULT_SIZE];
    }

    /**
     * Checks if a contact with the given name exists in the contact book.
     *
     * @param name the name of the contact to check
     * @return true if the contact exists, false otherwise
     */
    public boolean hasContact(String name) {
        return searchIndex(name) >= 0;
    }

    /**
     * Checks if a contact with the given phone exists in the contact book.
     *
     * @param phone the name of the contact to check
     * @return true if the contact exists, false otherwise
     */
    public boolean hasPhone(int phone) {
        return searchIndexNum(phone) >= 0;
    }

    /**
     * Gets the name of a contact with a given phone number.
     *
     * @param phone the name of the contact to check
     * @return name of the contact with the given phone number.
     */
    public String getNameWithNumber(int phone) {
        return contacts[searchIndexNum(phone)].getName();
    }

    /**
     * Gets the number of contacts in the contact book.
     *
     * @return the number of contacts
     */
    public int getNumberOfContacts() {
        return counter;
    }

    /**
     * Adds a new contact to the contact book.
     *
     * @param name  the name of the contact
     * @param phone the phone number of the contact
     * @param email the email address of the contact
     * @pre !hasContact(name)
     */
    public void addContact(String name, int phone, String email) {
        if (counter == contacts.length)
            resize();
        contacts[counter] = new Contact(name, phone, email);
        counter++;
    }

    /**
     * Deletes a contact from the contact book.
     *
     * @param name the name of the contact to delete
     * @pre hasContact(name)
     */
    public void deleteContact(String name) {
        int index = searchIndex(name);
        for (int i = index; i < counter; i++)
            contacts[i] = contacts[i + 1];
        counter--;
    }

    /**
     * Gets the phone number of a contact with the given name.
     *
     * @param name the name of the contact
     * @return the phone number of the contact
     * @pre hasContact(name)
     */
    public int getPhone(String name) {
        return contacts[searchIndex(name)].getPhone();
    }

    /**
     * Gets the email address of a contact with the given name.
     *
     * @param name the name of the contact
     * @return the email address of the contact
     * @pre hasContact(name)
     */
    public String getEmail(String name) {
        return contacts[searchIndex(name)].getEmail();
    }

    /**
     * Changes the phone number of a contact with the given name.
     *
     * @param name  the name of the contact
     * @param phone the new phone number
     * @pre hasContact(name)
     */
    public void setPhone(String name, int phone) {
        contacts[searchIndex(name)].setPhone(phone);
    }

    /**
     * Changes the email address of a contact with the given name.
     *
     * @param name  the name of the contact
     * @param email the new email address
     * @pre hasContact(name)
     */
    public void setEmail(String name, String email) {
        contacts[searchIndex(name)].setEmail(email);
    }

    /**
     * Returns an iterator for the contacts in the contact book.
     *
     * @return a ContactIterator for iterating over contacts
     */
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