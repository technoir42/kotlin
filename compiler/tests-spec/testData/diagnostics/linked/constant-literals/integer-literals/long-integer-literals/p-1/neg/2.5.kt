// !CHECK_TYPE
// SKIP_TXT

/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTIONS: constant-literals, integer-literals, long-integer-literals
 PARAGRAPH: 1
 SENTENCE: [2] An integer literal with the long literal mark has type kotlin.Long; an integer literal without it has one of the types kotlin.Int/kotlin.Short/kotlin.Byte (the selected type is dependent on the context), if its value is in range of the corresponding type, or type kotlin.Long otherwise.
 NUMBER: 5
 DESCRIPTION: Check of integer type selection depends on the context (incopatible types).
 */

// FILE: functions.kt

package functions

fun f1(x1: Byte) = x1

fun f2(x1: Short) = x1

fun f3(x1: Int) = x1

fun f4(x1: Long) = x1

// FILE: usages.kt

import functions.*

fun case_1() {
    f1(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>128<!>)
    f1(<!TYPE_MISMATCH!>-129<!>)
    f1(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>32767<!>)
    f1(<!TYPE_MISMATCH!>-32768<!>)
    f1(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>2147483647<!>)
    f1(<!TYPE_MISMATCH!>-2147483648<!>)
    f1(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>9223372036854775807<!>)
    f1(<!TYPE_MISMATCH!>-9223372036854775807<!>)
    f1(<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!>)
    f1(<!TYPE_MISMATCH!>-<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!><!>)
}

fun case_2() {
    f2(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>32768<!>)
    f2(<!TYPE_MISMATCH!>-32769<!>)
    f2(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>2147483647<!>)
    f2(<!TYPE_MISMATCH!>-2147483648<!>)
    f2(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>9223372036854775807<!>)
    f2(<!TYPE_MISMATCH!>-9223372036854775807<!>)
    f2(<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!>)
    f2(<!TYPE_MISMATCH!>-<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!><!>)
}

fun case_3() {
    f3(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>9223372036854775807<!>)
    f3(<!TYPE_MISMATCH!>-9223372036854775807<!>)
    f3(<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!>)
    f3(-<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!>)
}

fun case_4() {
    f3(<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!>)
    f3(-<!INT_LITERAL_OUT_OF_RANGE!>1000000000000000000000000000000000000000000000000<!>)
}
