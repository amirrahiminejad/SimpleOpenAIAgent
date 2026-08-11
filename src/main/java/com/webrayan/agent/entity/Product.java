package com.webrayan.agent.entity;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("اطلاعات کامل یک محصول")
public record Product(
        @JsonPropertyDescription("شناسه یکتای محصول")
        Long id,

        @JsonPropertyDescription("نام محصول")
        String name,

        @JsonPropertyDescription("توضیحات محصول")
        String description,

        @JsonPropertyDescription("دسته‌بندی محصول")
        String category,

        @JsonPropertyDescription("قیمت محصول به تومان")
        long price,

        @JsonPropertyDescription("تعداد موجودی در انبار")
        int stock
) {}