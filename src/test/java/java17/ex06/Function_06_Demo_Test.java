package java17.ex06;

import java.util.function.Supplier;

import org.junit.Test;

import java17.data.Person;

/**
 * DEMO (pas un exercice noté) : pourquoi Supplier<Person> est nécessaire pour
 * pouvoir écrire une lambda, contrairement à un simple paramètre Person.
 */
public class Function_06_Demo_Test {

    // Comme dans Function_06_Test : Supplier<Person> est une interface fonctionnelle,
    // donc une lambda () -> new Person(...) est acceptée ici.
    String formatAgeWithSupplier(Supplier<Person> supplier) {
        return "[age=" + supplier.get().getAge() + "]";
    }

    // Ici le paramètre attend directement un objet Person, pas un "fabricant" de Person.
    String formatAgeWithPerson(Person person) {
        return "[age=" + person.getAge() + "]";
    }

    @Test
    public void test_supplier_accepts_lambda() throws Exception {
        // OK : Supplier<Person> est une interface fonctionnelle -> lambda acceptée
        String result = formatAgeWithSupplier(() -> new Person("John", "France", 35, "pass"));

        assert result.equals("[age=35]");
    }

    @Test
    public void test_person_needs_a_real_object() throws Exception {
        // NE COMPILE PAS : Person est une classe concrète, pas une interface fonctionnelle.
        // Décommente la ligne suivante pour voir l'erreur dans VSCode :
        // String broken = formatAgeWithPerson(() -> new Person("John", "France", 35, "pass"));

        // Pour formatAgeWithPerson, il faut donner l'objet directement, déjà construit :
        String result = formatAgeWithPerson(new Person("John", "France", 35, "pass"));

        assert result.equals("[age=35]");
    }
}
