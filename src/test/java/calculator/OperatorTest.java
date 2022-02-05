package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Operator 클래스")
class OperatorTest {

    @Nested
    @DisplayName("from 메소드는")
    class Describe_from {

        Operator subject(String symbol) {
            return Operator.from(symbol);
        }

        @Nested
        @DisplayName("+ 문자열이 주어진다면")
        class Context_with_add_symbol {
            @Test
            @DisplayName("SUM 을 반환한다")
            void it_returns_a_sum() {
                assertThat(subject("+")).isEqualTo(Operator.SUM);
            }
        }

        @Nested
        @DisplayName("- 문자열이 주어진다면")
        class Context_with_subtraction_symbol {
            @Test
            @DisplayName("SUBTRACTION 을 반환한다")
            void it_returns_a_subtraction() {
                assertThat(subject("-")).isEqualTo(Operator.SUBTRACTION);
            }
        }

        @Nested
        @DisplayName("* 문자열이 주어진다면")
        class Context_with_multiplication_symbol {
            @Test
            @DisplayName("MULTIPLICATION 을 반환한다")
            void it_returns_a_multiplication() {
                assertThat(subject("*")).isEqualTo(Operator.MULTIPLICATION);
            }
        }

        @Nested
        @DisplayName("/ 문자열이 주어진다면")
        class Context_with_division_symbol {
            @Test
            @DisplayName("DIVISION 을 반환한다")
            void it_returns_a_division() {
                assertThat(subject("/")).isEqualTo(Operator.DIVISION);
            }
        }

        @Nested
        @DisplayName("허용되지 않은 문자열이 주어진다면")
        class Context_with_other_symbol {
            @ParameterizedTest
            @ValueSource(strings = {",", ":"})
            @DisplayName("예외를 던진다")
            void it_throws_exception(String symbol) {
                assertThrows(IllegalArgumentException.class, () -> {
                    subject(symbol);
                });
            }
        }
    }

    @Nested
    @DisplayName("calculate 메소드는")
    class Describe_calculate {
        private int a, b;

        @BeforeEach
        void prepareNumbers() {
            a = 10;
            b = 3;
        }

        int subject(Operator operator, int a, int b) {
            return operator.calculate(a, b);
        }

        @Nested
        @DisplayName("덧셈 상태일 때 만약 두 정수가 주어진다면")
        class Context_with_add_and_numbers {
            @Test
            @DisplayName("두 수의 합을 리턴한다")
            void it_returns_a_sum() {
                assertThat(subject(Operator.SUM, a, b)).isEqualTo(13);
            }
        }

        @Nested
        @DisplayName("뺄셈 상태일 때 만약 두 정수가 주어진다면")
        class Context_with_subtract_and_numbers {
            @Test
            @DisplayName("두 수의 뺄셈 결과를 리턴한다")
            void it_returns_a_subtraction() {
                assertThat(subject(Operator.SUBTRACTION, a, b)).isEqualTo(7);
            }
        }

        @Nested
        @DisplayName("곱셈 상태일 때 만약 두 정수가 주어진다면")
        class Context_with_multiply_and_numbers {
            @Test
            @DisplayName("두 수의 곱을 리턴한다")
            void it_returns_a_multiplication() {
                assertThat(subject(Operator.MULTIPLICATION, a, b)).isEqualTo(30);
            }
        }

        @Nested
        @DisplayName("나눗셈 상태일 때 만약 두 정수가 주어질 때 나누는 수가 0이 아니라면")
        class Context_with_divide_and_numbers {
            @Test
            @DisplayName("두 수의 나눗셈의 결과를 리턴한다. 결과 값은 항상 정수이다")
            void it_returns_a_division() {
                assertThat(subject(Operator.DIVISION, a, b)).isEqualTo(3);
            }
        }

        @Nested
        @DisplayName("나눗셈 상태일 때 만약 두 정수가 주어질 때 나누는 수가 0이라면")
        class Context_with_divide_and_numbers_with_zero {
            @Test
            @DisplayName("예외를 던진다")
            void it_throws_exception() {
                assertThrows(RuntimeException.class, () -> {
                    subject(Operator.DIVISION, a, 0);
                });
            }
        }
    }
}