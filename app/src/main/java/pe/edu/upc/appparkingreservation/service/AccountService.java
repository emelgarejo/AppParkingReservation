package pe.edu.upc.appparkingreservation.service;

import pe.edu.upc.appparkingreservation.model.Person;

/**
 * Created by Edgar Melgarejo on 25/03/2016.
 */
public class AccountService {
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "emelgarejo@app.com:1234", "rcarril@app.com:1234"
    };

    public Person validateAccount(String mEmail, String mPassword) {
        Person person = null;
        try {
            // Simulate network access .
            //cambiar por invocación a servico
            Thread.sleep(2000);

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail) && pieces[1].equals(mPassword)) {
                    person = new Person();
                    person.setName("Edgar");
                    person.setLastName("Melgarejo");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return person;
    }

    public boolean registerPerson(Person person){

        try {
            // Simulate network access .
            //cambiar por invocación a servico
            Thread.sleep(2000);



        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
