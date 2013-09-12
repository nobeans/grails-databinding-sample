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
        given:
        def book = new Book(title: title)
 
        when:
        book.validate()

        then:
        book.hasErrors() == invalid
        book.errors["title"] == caused
        book.title == converted

        where:
        title                | invalid | caused     | converted
        "Programming Grails" | false   | null       | "Programming Grails"
        " with spaces "      | false   | null       | "with spaces"
        "   "                | true    | "nullable" | null
        ""                   | true    | "nullable" | null
        null                 | true    | "nullable" | null
    }
}
