package databinding.trimstrings.sample

class Book {

    String title

    static constraints = {
        title blank: false
    }
}
