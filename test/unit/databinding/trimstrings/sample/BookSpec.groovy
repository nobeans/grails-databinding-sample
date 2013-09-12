package databinding.trimstrings.sample

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Book)
class BookSpec extends Specification {

    def setup() {
        mockForConstraintsTests(Book)
    }

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
        book.errors["title"] == caused

        where:
        title                | converted            | invalid | caused
        "Programming Grails" | "Programming Grails" | false   | null
        " with spaces "      | " with spaces "      | false   | null
        //"   "                | null                 | true    | "nullable"
        //""                   | null                 | true    | "nullable"
        "   "                | "   "                | true    | "blank"
        ""                   | ""                   | true    | "blank"
        null                 | null                 | true    | "nullable"
    }
}
