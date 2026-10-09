/**
 * The Contact class represents a contact with a name, 
 * phone number, and email address.
 */
public class Contact {
    private String name;
    private int phone;
    private String email;

    /**
     * Constructs a Contact object with the specified name, 
     * phone number, and email address.
     *
     * @param name the name of the contact
     * @param phone the phone number of the contact
     * @param email the email address of the contact
     */
    public Contact(String name, int phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the name of the contact.
     *
     * @return the name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the phone number of the contact.
     *
     * @return the phone number of the contact
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Gets the email address of the contact.
     *
     * @return the email address of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes the phone number of the contact.
     *
     * @param phone the new phone number
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * Changes the email address of the contact.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Compares this Contact with another Contact for equality 
     * based on their names.
     *
     * @param otherContact the other contact to compare
     * @return true if the names are equal, false otherwise
     */
    public boolean equals(Contact otherContact) {
        return name.equals(otherContact.getName());
    }
}
