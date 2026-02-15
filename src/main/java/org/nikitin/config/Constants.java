package org.nikitin.config;

public final class Constants {

    public static final Class<DataType> DATA_TYPE_CLASS = DataType.class;

    public static final String INTEGERS_OUTPUT_FILE_NAME = "integers.txt";
    public static final String FLOATS_OUTPUT_FILE_NAME = "floats.txt";
    public static final String STRINGS_OUTPUT_FILE_NAME = "strings.txt";

    public static final String EMPTY_STRING = "";

    public static final String REGEX_FLOATING_POINT_NUMBER = "-?\\d*\\.\\d+([eE][-+]?\\d+)?";
    public static final String REGEX_INTEGER_NUMBER = "-?\\d+";
    public static final String REGEX_INVALID_FILENAME_CHARS = ".*[\\\\/:*?\"<>|].*";

    public static final String STATISTIC_INTEGER_NUMBER = "\n\n**** Statistic Integer Number ****";
    public static final String STATISTIC_FLOATING_POINT_NUMBER = "\n\n**** Statistic Integer Floating Point Number ****";
    public static final String STATISTIC_STRING = "\n\n**** Statistic String ****";

    public static final String COUNT_NUMBER = "\nCount of elements: ";
    public static final String MIN_VALUE = "\nMin value: ";
    public static final String MAX_VALUE = "\nMax value: ";
    public static final String SUM_VALUE = "\nSum value: ";
    public static final String AVG_VALUE = "\nAvg value: ";

    public static final String MIN_LENGTH_LINE = "\nMin length line: ";
    public static final String MAX_LENGTH_LINE = "\nMax length: line: ";

    public static final String OPTIONS_STATISTIC_CANNOT_BE_TOGETHER = " Options -s and -f cannot be used together.";
    public static final String PREFIX_CONTAINS_INVALID_CHARACTERS = " Prefix contains invalid characters.";
    public static final String INCORRECT_PATH_FOR_OUTPUT_FILES = " Incorrect path for output files.";
    public static final String ERROR_READING_FILE = " Error reading file. ";
    public static final String ERROR_WRITE_TO_FILE = " Error write to file. ";
    public static final String FAILED_TO_CLOSE_WRITER = " Failed to close writer";

    private Constants() {
    }
}
