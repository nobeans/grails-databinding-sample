package databinding.trimstrings.sample

import spock.lang.*


class BookIntegrationSpec extends Specification {

    @Unroll
    void "data binding"() {
        given:
        def book = new Book(title: title)
 
        when:
        book.validate()

        then:
        book.hasErrors() == invalid
        assertCause(book, "title", caused)
        book.title == converted

        where:
        title                | invalid | caused     | converted
        "Programming Grails" | false   | null       | "Programming Grails"
        " with spaces "      | false   | null       | "with spaces"
        "   "                | true    | "nullable" | null
        ""                   | true    | "nullable" | null
        null                 | true    | "nullable" | null
    }

    private void assertCause(book, property,  caused) {
        if (caused) {
            assert book.errors.getFieldErrors(property)?.code.contains(caused)
        } else {
            assert book.errors.getFieldErrors(property)?.code.empty
        }
    }
}
