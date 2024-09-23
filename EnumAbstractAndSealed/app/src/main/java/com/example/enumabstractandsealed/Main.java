package com.example.enumabstractandsealed;

public class Main {
    public static void main(String[] args) {
        //In object : const - behave like static filed while accessing
        System.out.println(Application.name);

        //lateinit: Accessing using instance method setNewName()
        Application.INSTANCE.setNewName("MyNewApplication-lateinit");
        //lateinit: Accessing using instance method getNewName()
        System.out.println(Application.INSTANCE.getNewName());
        //Accessing using direct call to newName (lateinit as static field in object)
        System.out.println(Application.newName);

        Application.newName = "YourNewApplication-lateinit";

        //Accessing using instance method getNewName()
        System.out.println(Application.INSTANCE.getNewName());
        //Direct property access  (lateinit as static field in object)
        System.out.println(Application.newName);//

        System.out.println(Application.nameAsStaticField);
    }
}
