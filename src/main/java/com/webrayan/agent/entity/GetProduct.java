package com.webrayan.agent.entity;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription(
        "Gets detailed information about a product"
)
public class GetProduct {
    @JsonPropertyDescription(
            "The exact name of the product"
    )
    public String productName;

}
