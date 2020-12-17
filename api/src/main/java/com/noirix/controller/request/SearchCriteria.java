package com.noirix.controller.request;

import lombok.Data;

@Data
public class SearchCriteria {
    private Long userLowerId;

    private String query;

    private Long limit;

    private Long offset;
}
