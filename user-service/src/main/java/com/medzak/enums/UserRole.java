package com.medzak.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum UserRole {
    CLIENT("CLIENT"),
    AGENCY("AGENCY"),
    HOUSEKEEPER("HOUSEKEEPER");

    private String description;
}
