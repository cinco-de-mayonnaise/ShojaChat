package com.abdullah.shojachat.actors;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;  // check email addresses


/**
 *
 * @author Abdullah
 */
// All users of British council have the following things in common. Ensure to inherit from this
public class User implements Serializable
{
    final protected String username;
    protected String nickname;
    final protected String Email;
    protected boolean verified;
    protected String pw_Hash;
    final protected Date DOJ;

    private boolean isValidUsername(String uname)
    {
        if (uname == null || uname.length() >= 32 || uname.isBlank() || uname.isEmpty())               // valid username must be 1-32 chars, cannot be whitespace.
            return false;
        else if (uname.indexOf(' ') != -1)      // valid username must not contain spaces
            return false;
        else if (uname.chars().allMatch(Character::isDigit))        // valid username must not entirely be a number
            return false;
        else if (false)              // TODO: ensure username is already not taken
            return false;
        else
            return true;
    }



    public String getUsername() {
        return username;
    }

    public User(String Name, String Email, String Password, Date DOJ, Date DOB) throws IllegalArgumentException
    {
        if (isValidUsername(Name)) // ensure username is alright
            this.username = Name;
        else
            throw new IllegalArgumentException("Username is invalid or already taken!");

        if (Email == null || Email.isEmpty() || Email.isBlank())
            this.Email = null;       // email not specified! check if the server allows users with no email checks
        else if (EmailValidator.getInstance().isValid(Email) || false)   // TODO: implement checking for existing email addresses!
            this.Email = Email;
        else
            throw new IllegalArgumentException("Email is invalid or already taken!");

        this.pw_Hash = Password;     // hashing very hard, store plaintext pw for now...
        this.DOJ = DOJ;
    }

    public String getEmail() {
        return Email;
    }

    public final boolean verifyPassword(String password) {
        return this.pw_Hash.equals(password);
    }

    protected final boolean setPassword(String oldPassword, String newPassword) {
        if (this.pw_Hash.equals(oldPassword))
        {
            this.pw_Hash = newPassword;
            return true;
        }
        else
            return false;
    }

    public Date getDOJ() {
        return DOJ;
    }



}