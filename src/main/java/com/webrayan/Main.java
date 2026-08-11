package com.webrayan;

import com.webrayan.agent.ProductAgent;

public class Main {

    public static void main(String[] args) {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("خطا: متغیر محیطی OPENAI_API_KEY تنظیم نشده است.");
            System.exit(1);
        }

        ProductAgent agent = new ProductAgent(apiKey);

        try {
            String result = agent.ask("مشخصات MacBook Pro M4 چیه؟");
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("خطا در اجرای agent: " + e.getMessage());
            e.printStackTrace();
        }
    }
}