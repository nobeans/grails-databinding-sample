package databinding.trimstrings.sample

import spock.lang.*


class BookIntegrationSpec extends Specification {

    @Unroll
    void "data binding"() {
        when:
        def book = new Book(title: title)

        then:
        book.title == converted
 
        when:
        book.validate()

        then:
        book.hasErrors() == invalid
        assertCause(book, "title", caused)

        where:
        title                | converted            | invalid | caused
        "Programming Grails" | "Programming Grails" | false   | null
        " with spaces "      | "with spaces"        | false   | null
        //"   "                | null                 | true    | "nullable"
        //""                   | null                 | true    | "nullable"
        "   "                | ""                   | true    | "blank"
        ""                   | ""                   | true    | "blank"
        null                 | null                 | true    | "nullable"
    }

    private void assertCause(book, property,  caused) {
        if (caused) {
            assert book.errors.getFieldErrors(property)?.code.contains(caused)
        } else {
            assert book.errors.getFieldErrors(property)?.code.empty
        }
    }
}
