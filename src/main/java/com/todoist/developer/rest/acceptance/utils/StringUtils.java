package com.todoist.developer.rest.acceptance.utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String randomAlphabeticString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
    public static String randomAlphanumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

}
