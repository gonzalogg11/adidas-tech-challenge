package com.adidas.notification.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LangEnum {

    EN("en"),
    ES("es");

    private final String language;

    public static String getLanguage(LangEnum langEnum) {
        if (langEnum != null) {
            return langEnum.getLanguage();
        }
        return EN.getLanguage();
    }

}
